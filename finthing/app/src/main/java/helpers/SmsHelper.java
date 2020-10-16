package helpers;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;

import models.SMS;

/**
 * Created by namra on 24/06/18.
 */

public class SmsHelper {

    private Context mCtx;

    public SmsHelper(Context ctx) {
        mCtx = ctx;
    }

    protected ArrayList<SMS> getSMSList(Long maxTS){
        ArrayList<SMS> smsList = new ArrayList<SMS>();
        Uri uriSms = Uri.parse("content://sms/inbox");
        String searchQ = "date > ?";
        Cursor cursor = mCtx.getContentResolver().query(uriSms, SMS.getAllFieldNames() ,searchQ, new String[] { String.valueOf(maxTS)},"date ASC");
        if(cursor != null){
            cursor.moveToFirst();
            while (cursor.moveToNext())
            {
                SMS smsRow = new SMS(cursor);
                smsList.add(smsRow);
            }
            cursor.close();
        }
        return smsList;
    }

}
