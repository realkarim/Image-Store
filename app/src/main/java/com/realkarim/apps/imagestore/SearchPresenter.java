package com.realkarim.apps.imagestore;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.realkarim.apps.imagestore.getty.GettyHelper;
import com.realkarim.apps.imagestore.getty.GettyPageResultCallback;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Karim Mostafa on 1/31/17.
 */

public class SearchPresenter implements SearchContract.Presenter {

    private SearchContract.View view;

    @Inject
    GettyHelper gettyHelper;

    @Inject
    SearchPresenter(){

    }

    public void setView(SearchContract.View view){
        this.view = view;
    }

    @Override
    public void getImages(Integer page, Integer pageSize, String phrase) {

        gettyHelper.requestImagePage(page, pageSize, phrase, new GettyPageResultCallback() {
            @Override
            public void onStartRequest() {
                view.showMessage("Searching...");
            }

            @Override
            public void onResultReceived(ArrayList arrayList) {
                if(arrayList.size() == 0){
                    view.showMessage("No images found!");
                    return;
                }
                view.onPageReceived(arrayList);
            }

            @Override
            public void onError(String error) {
                view.showMessage("An error occurred while searching for images!");
            }
        });
    }
}
