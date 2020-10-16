package utils;

import java.util.HashMap;

public class PossibleUserQueries {

    private HashMap<String, String> userQueryToIntentMap;
    private HashMap<String, String> userIntentToMethodMap;
    private HashMap<String,String> userIntentToResponseMap;


    private void prepareUserQueryToIntentMap(){
        userQueryToIntentMap = new HashMap<>();
        userQueryToIntentMap.put("What is my current balance ?","account.balance");
    }

    private void prepareUserIntentToMethodMap(){
        userIntentToMethodMap = new HashMap<>();
        userIntentToMethodMap.put("account.balance", "getCurrentBalance");
    }

    private void prepareUserIntentToResponseMap(){
        userIntentToResponseMap = new HashMap<>();
        userIntentToResponseMap.put("account.balance", "Your current account balance for $" + SMSConfig.TEMP_BANK_ID + " is: $" + SMSConfig.TEMP_BALANCE);
    }

    public PossibleUserQueries(){
        prepareUserQueryToIntentMap();
        prepareUserIntentToMethodMap();
        prepareUserIntentToResponseMap();
    }

    public String getMethodFromIntent(String intent){
        String result = null;
        if(Utils.isNotEmpty(intent)){
          result = userIntentToMethodMap.get(intent);
        }
        return result;
    }

    public String getUserIntent(String userQuery){
        return userQueryToIntentMap.get(userQuery);
    }

    public String getReponseFromIntent(String intent, HashMap<String, String> responseVars){
        String result = null;
        if(Utils.isNotEmpty(intent)){
            result = userIntentToResponseMap.get(intent);
            for(String vars: responseVars.keySet()){
                result = result.replace("$"+vars, responseVars.get(vars));
            }
        }
        return result;
    }

}
