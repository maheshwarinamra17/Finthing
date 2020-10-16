package services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import database_layer.FinthingDB;
import database_layer.TagsTB;
import database_layer.TransactionTB;
import utils.Utils;

public class AssignTagsToTransactionsService extends IntentService {

    public AssignTagsToTransactionsService() {
        super("AssignTagsToTransactionsService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        FinthingDB dbConn = FinthingDB.createDBInstance(getApplicationContext());
        List<TransactionTB> nonTaggedTrans = dbConn.transactionTBDao().getNonTaggedTrans();
        List<TagsTB> tags = dbConn.tagsTBDao().getTags();
        for(TransactionTB trans: nonTaggedTrans){
            for(TagsTB tag: tags){
                if(Utils.isNotEmpty(trans.getNarration()) && trans.getNarration().contains(tag.getIdn())){
                    trans.setTagID(tag.getId());
                    dbConn.transactionTBDao().update(trans);
                }
            }
        }
    }

}
