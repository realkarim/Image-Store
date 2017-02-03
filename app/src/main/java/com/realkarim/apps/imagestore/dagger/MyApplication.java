package com.realkarim.apps.imagestore.dagger;

import android.app.Application;

/**
 * Created by Karim Mostafa on 2/3/17.
 */

public class MyApplication extends Application {

    BaseComponent baseComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        baseComponent = DaggerBaseComponent.builder()
                .appModule(new AppModule(this))
                .build();

    }

    public BaseComponent getBaseComponent() {
        return baseComponent;
    }


}
