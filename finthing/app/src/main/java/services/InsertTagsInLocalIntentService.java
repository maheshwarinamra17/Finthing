package services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import database_layer.FinthingDB;
import database_layer.TagsTB;
import utils.HTTPCallHelper;
import utils.SMSConfig;
import utils.Utils;

public class InsertTagsInLocalIntentService extends IntentService {

    public InsertTagsInLocalIntentService() {
        super("InsertTagsInLocalIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            JSONObject tagData = HTTPCallHelper.makeGETCall(SMSConfig.API_TAGS_DATA);
            if(Utils.isNotEmpty(tagData)){
                FinthingDB dbConn = FinthingDB.createDBInstance(getApplicationContext());
                Iterator<String> tagIdns = tagData.keys();
                ArrayList<String> oldTagsList = new ArrayList<>(dbConn.tagsTBDao().getAllIdns());
                ArrayList<TagsTB> newTagsList = new ArrayList<>();
                while( tagIdns.hasNext() ) {
                    String idn = tagIdns.next();
                    if(oldTagsList.contains(idn))
                        continue;
                    if(tagData.get(idn) instanceof JSONObject) {
                        JSONObject tagObject = tagData.getJSONObject(idn);
                        TagsTB tagsTB = new TagsTB();
                        tagsTB.setIdn(idn);
                        tagsTB.setName(tagObject.optString("name"));
                        tagsTB.setType(tagObject.optString("type"));
                        tagsTB.setCategory(tagObject.optString("category"));
                        newTagsList.add(tagsTB);
                    }
                }
                if(!newTagsList.isEmpty()){
                    dbConn.tagsTBDao().insertAll(newTagsList);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}