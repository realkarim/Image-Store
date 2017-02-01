package com.realkarim.apps.imagestore;

import java.util.ArrayList;

/**
 * Created by Karim Mostafa on 1/31/17.
 */

public interface SearchContract {

    interface View {
        void onPageReceived(ArrayList arrayList);

        void showMessage(String message);
    }

    interface Presenter {
        void getImages(Integer page, Integer pageSize, String phrase);
    }
}
