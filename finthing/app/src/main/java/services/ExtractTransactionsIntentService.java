package services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import database_layer.FinthingDB;
import database_layer.TransactionTB;
import helpers.TransactionHelper;


public class ExtractTransactionsIntentService extends IntentService {

    public ExtractTransactionsIntentService() {
        super("ExtractTransactionsIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        FinthingDB dbConn = FinthingDB.createDBInstance(getApplicationContext());
        try {
            Long maxTS = dbConn.transactionTBDao().getLastSmsTS();
            if(maxTS == null)
                maxTS = Long.valueOf("0");
            ArrayList<TransactionTB> transactions = new TransactionHelper(getApplicationContext()).parseTransFromSMS(maxTS);
            dbConn.transactionTBDao().insertAll(transactions);
            FinthingDB.destroyDBInstance();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
