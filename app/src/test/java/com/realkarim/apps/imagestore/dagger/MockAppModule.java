package com.realkarim.apps.imagestore.dagger;

import android.app.Application;

import dagger.Module;

/**
 * Created by Karim Mostafa on 2/3/17.
 */
@Module
public class MockAppModule extends AppModule {
    public MockAppModule(Application application) {
        super(application);
    }

    // Mock objects could be provided here
}
