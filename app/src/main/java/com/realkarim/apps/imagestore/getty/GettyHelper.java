package com.realkarim.apps.imagestore.getty;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.realkarim.apps.imagestore.R;
import com.realkarim.apps.imagestore.getty.models.Image;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Karim Mostafa on 2/1/17.
 */

public class GettyHelper {

    static private String TAG = GettyHelper.class.getName();

    private OkHttpClient okHttpClient;
    private Context context;
    private Gson gson;
    private Handler mainHandler;


    public GettyHelper(Context context) {
        this.context = context;
        okHttpClient = new OkHttpClient();
        gson = new Gson();
        mainHandler = new Handler(Looper.getMainLooper());
    }

    public void requestImagePage(Integer page, Integer pageSize, String phrase, final GettyPageResultCallback gettyPageResultCallback) {

        // notify method caller that request is getting started
        gettyPageResultCallback.onStartRequest();

        // create full url
        String baseURL = context.getString(R.string.search_url);
        Uri builtUri = Uri.parse(baseURL)
                .buildUpon()
                .appendQueryParameter("page", page.toString())
                .appendQueryParameter("page_size", pageSize.toString())
                .appendQueryParameter("phrase", phrase)
                .build();

        // create request
        Request request = new Request.Builder()
                .header("Api-Key", context.getString(R.string.api_Key))
                .url(builtUri.toString())
                .build();

        // send request
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                // forward error message to method caller
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        gettyPageResultCallback.onError(e.getMessage());
                        Log.e(TAG, "IOException: " + e.getCause());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // forward response to method caller
                final ArrayList<Image> imageArrayList = new ArrayList<Image>();
                String responseString = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(responseString);
                    JSONArray jsonArray = jsonObject.getJSONArray("images");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Image image = gson.fromJson(jsonArray.getJSONObject(i).toString(), Image.class);
                        imageArrayList.add(image);
                    }
                } catch (final JSONException e) {
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            gettyPageResultCallback.onError(e.getMessage());
                            Log.e(TAG, "IOException: " + e.getMessage());
                        }
                    });
                } finally {
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            gettyPageResultCallback.onResultReceived(imageArrayList);
                        }
                    });
                }
            }
        });

    }
}
