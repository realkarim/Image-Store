package com.realkarim.apps.imagestore.dagger;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by Karim Mostafa on 2/3/17.
 */
@Module
class AppModule {
    Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }

    @Provides
    Gson providesGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    OkHttpClient providesOkHttpClient() {
        return new OkHttpClient();
    }

    @Provides
    Handler providesHandler() {
        return new Handler(Looper.getMainLooper());
    }
}
