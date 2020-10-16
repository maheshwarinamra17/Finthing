package services;

import android.content.Context;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import database_layer.FinthingDB;
import database_layer.TransactionTB;
import utils.PossibleUserQueries;
import utils.SMSConfig;
import utils.Utils;

public class UserQueryService {

    private PossibleUserQueries queryList;
    private Context mCtx;

    public UserQueryService(Context ctx) {
        queryList = new PossibleUserQueries();
        mCtx = ctx;
    }

    public String processQuery(String query, HashMap<String,String> queryParams) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String queryResult = null;
        String userIntent = queryList.getUserIntent(query);
        String requiredMethodString = queryList.getMethodFromIntent(userIntent);
        if(Utils.isNotEmpty(requiredMethodString)){
            FinthingDB dbConn = FinthingDB.createDBInstance(mCtx);
            Method requiredMethod = this.getClass().getMethod(requiredMethodString, FinthingDB.class, HashMap.class, String.class);
            queryResult = (String) requiredMethod.invoke( this, dbConn, queryParams, userIntent);
        }
        return queryResult;
    }

    public String getCurrentBalance(FinthingDB dbConn, HashMap<String, String> queryParams, String userIntent){
        String result = null;
        Long maxTS = dbConn.transactionTBDao().getLastTransactionTS();
        if(Utils.isNotEmpty(maxTS)){
            TransactionTB lastTransaction = dbConn.transactionTBDao().getTransactions(maxTS,maxTS).get(0);
            HashMap<String,String> responseMap = new HashMap<>();
            responseMap.put(SMSConfig.TEMP_BANK_ID, lastTransaction.getBankID());
            responseMap.put(SMSConfig.TEMP_BALANCE, String.valueOf(lastTransaction.getBalance()));
            result = queryList.getReponseFromIntent(userIntent, responseMap);
        }
        return result;
    }

}

// Remember to close DB Connection