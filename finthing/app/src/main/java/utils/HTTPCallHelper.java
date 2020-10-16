package utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

/**
 * Created by namra on 27/06/18.
 */

public class HTTPCallHelper {

    public static JSONObject makeGETCall(String url) throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        JSONObject result = new JSONObject(response.body().string());
        return result.getJSONObject("data");
    }

}