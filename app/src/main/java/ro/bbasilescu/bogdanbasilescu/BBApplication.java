package ro.bbasilescu.bogdanbasilescu;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;

import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

public class BBApplication extends Application {

    private static BBApplication bbApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        bbApplication = this;
        // Initializing Fabric Crashlytics
        initializeFabricCrashlytics();
        // Initializing Timber for logging
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    private void initializeFabricCrashlytics() {
        Crashlytics crashlyticsKit = new Crashlytics.Builder()
                .core(new CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build())
                .build();
        // Disable Crashlytics for DEBUG build
        Fabric.with(this, crashlyticsKit);
    }

    public static BBApplication getInstance() {
        return bbApplication;
    }
}
