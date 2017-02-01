package com.realkarim.apps.imagestore.getty;

import java.util.ArrayList;

/**
 * Created by Karim Mostafa on 2/1/17.
 */

public interface GettyPageResultCallback {
    void onStartRequest();

    void onResultReceived(ArrayList arrayList);

    void onError(String error);
}
