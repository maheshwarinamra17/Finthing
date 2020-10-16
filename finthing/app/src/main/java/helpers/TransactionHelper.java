package helpers;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import database_layer.TransactionTB;
import models.AvailaibleBanks;
import models.PaymentMode;
import models.SMS;
import utils.HTTPCallHelper;
import utils.JaroWinklerDistance;
import utils.SMSConfig;
import utils.Utils;

/**
 * Created by namra on 25/06/18.
 */

public class TransactionHelper {

    private Context mCtx;
    private JSONObject bankData;

    public TransactionHelper(Context ctx) {
        this.mCtx = ctx;
    }

    private void fetchBankTemplates() throws IOException, JSONException {
         bankData = HTTPCallHelper.makeGETCall(SMSConfig.API_BANK_TEMPLATE);
    }

    public ArrayList<TransactionTB> parseTransFromSMS(Long maxTS) throws IOException, JSONException {
        ArrayList<TransactionTB> transList = new ArrayList<TransactionTB>();
        SmsHelper smsHelper = new SmsHelper(mCtx);
        fetchBankTemplates();
        ArrayList<SMS> smsArrayList = smsHelper.getSMSList(maxTS);
        for (int i=0; i < smsArrayList.size() ; i++){
            HashMap<String, String> smsVars = fetchValidSMS(smsArrayList.get(i));
            if(!smsVars.isEmpty()){
                transList.add(convertSmsToTrans(smsVars, smsArrayList.get(i)));
            }
        }
        return transList;
    }

    private TransactionTB convertSmsToTrans(HashMap<String, String> smsVars, SMS sms) throws JSONException {
        TransactionTB singleTrans = new TransactionTB();
        PaymentMode mode =  PaymentMode.fromString(smsVars.get(SMSConfig.SMS_PAYMENT_MODE_ID));
        switch(mode){
            case DEBIT_CARD:
                singleTrans.setAmount(-1* Double.valueOf(Utils.sanatizeNumberString(smsVars.get(SMSConfig.SMS_SPEND))));
                singleTrans.setBalance(Double.valueOf(Utils.sanatizeNumberString(smsVars.get(SMSConfig.SMS_BALANCE))));
                singleTrans.setLocation(smsVars.get(SMSConfig.SMS_LOCATION));
                singleTrans.setTransTS(Utils.stringTimeToEpoch("yyyy-MM-dd:HH:mm:ss",smsVars.get(SMSConfig.SMS_TIMESTAMP)));
                singleTrans.setNarration(smsVars.get(SMSConfig.SMS_SERVICE));
                singleTrans.setSmsTS(Long.valueOf(sms.getDate()));
                singleTrans.setBankID(smsVars.get(SMSConfig.SMS_BANK_ID));
                generateRefID(singleTrans);
                break;
            case NETBANKING:
                singleTrans.setAmount(-1* Double.valueOf(Utils.sanatizeNumberString(smsVars.get(SMSConfig.SMS_SPEND))));
                singleTrans.setTransTS(Long.valueOf(sms.getDate()));
                singleTrans.setSmsTS(Long.valueOf(sms.getDate()));
                singleTrans.setBankID(smsVars.get(SMSConfig.SMS_BANK_ID));
                if(smsVars.containsKey(SMSConfig.SMS_SERVICE))
                    singleTrans.setNarration(smsVars.get(SMSConfig.SMS_SERVICE));
                break;
            case CASH:
                singleTrans.setAmount(-1* Double.valueOf(Utils.sanatizeNumberString(smsVars.get(SMSConfig.SMS_SPEND))));
                singleTrans.setBalance(Double.valueOf(Utils.sanatizeNumberString(smsVars.get(SMSConfig.SMS_BALANCE))));
                singleTrans.setLocation(smsVars.get(SMSConfig.SMS_LOCATION));
                singleTrans.setTransTS(Utils.stringTimeToEpoch("yyyy-MM-dd:HH:mm:ss",smsVars.get(SMSConfig.SMS_TIMESTAMP)));
                singleTrans.setNarration(SMSConfig.CASH_NARRATION);
                singleTrans.setSmsTS(Long.valueOf(sms.getDate()));
                singleTrans.setBankID(smsVars.get(SMSConfig.SMS_BANK_ID));
                generateRefID(singleTrans);
                break;
            case UPI:
                singleTrans.setAmount(-1* Double.valueOf(Utils.sanatizeNumberString(smsVars.get(SMSConfig.SMS_SPEND))));
                singleTrans.setTransTS(Utils.stringTimeToEpoch("dd-MM-yyyy HH:mm:ss",smsVars.get(SMSConfig.SMS_TIMESTAMP)));
                singleTrans.setNarration(smsVars.get(SMSConfig.SMS_SERVICE));
                singleTrans.setSmsTS(Long.valueOf(sms.getDate()));
                singleTrans.setBankID(smsVars.get(SMSConfig.SMS_BANK_ID));
                generateRefID(singleTrans);
                break;
            case TRANSFERS:
                singleTrans.setAmount(-1* Double.valueOf(Utils.sanatizeNumberString(smsVars.get(SMSConfig.SMS_SPEND))));
                if(smsVars.containsKey(SMSConfig.SMS_TIMESTAMP))
                    singleTrans.setTransTS(Utils.stringTimeToEpoch("dd-MM-yy", smsVars.get(SMSConfig.SMS_TIMESTAMP)));
                singleTrans.setNarration(Utils.sanatizeNarrationString(smsVars.get(SMSConfig.SMS_SERVICE)));
                singleTrans.setSmsTS(Long.valueOf(sms.getDate()));
                singleTrans.setBankID(smsVars.get(SMSConfig.SMS_BANK_ID));
            default:
                break;
        }
        singleTrans.setMode(smsVars.containsKey(SMSConfig.SMS_PAYMENT_MODE_ID) ? smsVars.get(SMSConfig.SMS_PAYMENT_MODE_ID): "NA");
        singleTrans.setTempID(smsVars.containsKey(SMSConfig.SMS_TEMP_ID) ? smsVars.get(SMSConfig.SMS_TEMP_ID): "-1");
        return singleTrans;
    }

    private HashMap<String, String> fetchValidSMS(SMS sms) throws JSONException {
        HashMap<String, String>  result = new HashMap<String, String>();
        if(sms.getBody().startsWith("OTP") || sms.getBody().startsWith("One")){
            return result;
        }
        Boolean breakFlag = true;
        Iterator<String> keys = bankData.keys();
        while (keys.hasNext() && breakFlag){
            String bankId = keys.next();
            JSONObject data = bankData.getJSONObject(bankId);
            if(sms.getAddress().contains(bankId) && Utils.isNotEmpty(AvailaibleBanks.fromString(bankId))){
                JSONArray templates = data.getJSONArray("templates");
                for(int j = 0; j < templates.length(); j++){
                    JSONObject tempObject = templates.getJSONObject(j);
                    String template = tempObject.getString("template").replaceAll("\\{(.*?)\\}","").trim();
                    String smsBody = sms.getBody().replaceAll("\\b[0-9Rs.]+\\b","").trim();
                    if(smsBody.startsWith(tempObject.getString("starts_with"))
                            && isMatch(smsBody, template)){
                        result = fetchVariablesFromSMS(tempObject.getString("template"), sms.getBody());
                        result.put(SMSConfig.SMS_BANK_ID, bankId);
                        if(Utils.isNotEmpty(PaymentMode.fromString(tempObject.getString("mode")))){
                            result.put(SMSConfig.SMS_PAYMENT_MODE_ID, tempObject.getString("mode"));
                        }
                        result.put(SMSConfig.SMS_TEMP_ID, tempObject.getString("temp_id"));
                        breakFlag = false;
                        break;
                    }
                }
            }
            if(!result.isEmpty())
                break;
        }
        return result;
    }

    private HashMap<String,String> fetchVariablesFromSMS(String template, String smsText){
        HashMap<String, String> result = new HashMap<String, String>();
        String[] templateArray = template.split(" +");
        String[] textArray = smsText.split(" +");
        String prevVar = "";
        int t = 0;
        int s = 0;
        while(t < templateArray.length){
            while(s < textArray.length){
               if(templateArray[t].equals(textArray[s])){
                   t++;
                   s++;
               }else if(!templateArray[t].equals(textArray[s]) && templateArray[t].startsWith("{")){
                   result.put(templateArray[t].substring(1, templateArray[t].length()-1), textArray[s]);
                   prevVar = templateArray[t].substring(1, templateArray[t].length()-1);
                   t++;
                   s++;
               }else if(!templateArray[t].equals(textArray[s]) && !templateArray[t].startsWith("{")){
                    String existingValue  = result.get(prevVar);
                    result.put(prevVar, existingValue + " " + textArray[s]);
                    s++;
               }
            }
            t++;
        }
        return result;
    }

    private void generateRefID(TransactionTB transactionTB){
        transactionTB.setReferID(Utils.md5Hash(transactionTB.getNarration()
                + transactionTB.getSmsTS()
                + transactionTB.getTransTS()
                + transactionTB.getAmount()));
    }

    private Boolean isMatch(String text, String template){
        List<String> textArray = new ArrayList<String>(Arrays.asList(text.split(" ")));
        List<String> tempArray = new ArrayList<String>(Arrays.asList(template.split(" ")));
        tempArray.removeAll(Collections.singleton(""));
        return textArray.containsAll(tempArray);
    }
}
