package com.realkarim.apps.imagestore;

import android.content.Context;

/**
 * Created by Karim Mostafa on 1/31/17.
 */

public class SearchPresenter implements SearchContract.Presenter {

    Context context;
    SearchContract.View view;

    SearchPresenter(Context context, SearchContract.View view){
        this.context = context;
        this.view = view;
    }

    @Override
    public void getImages(Integer page, Integer pageSize, String phrase) {
        view.showMessage("Search " + phrase);
    }
}
