package com.google.firebase.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import androidx.core.content.ContextCompat;
import com.google.firebase.DataCollectionDefaultChange;
import com.google.firebase.events.Event;
import com.google.firebase.events.Publisher;

public class DataCollectionConfigStorage {
    public static final String DATA_COLLECTION_DEFAULT_ENABLED = "firebase_data_collection_default_enabled";
    private static final String FIREBASE_APP_PREFS = "com.google.firebase.common.prefs:";
    private boolean dataCollectionDefaultEnabled;
    private final Context deviceProtectedContext;
    private final Publisher publisher;
    private final SharedPreferences sharedPreferences;

    public DataCollectionConfigStorage(Context context0, String s, Publisher publisher0) {
        Context context1 = DataCollectionConfigStorage.directBootSafe(context0);
        this.deviceProtectedContext = context1;
        this.sharedPreferences = context1.getSharedPreferences("com.google.firebase.common.prefs:" + s, 0);
        this.publisher = publisher0;
        this.dataCollectionDefaultEnabled = this.readAutoDataCollectionEnabled();
    }

    private static Context directBootSafe(Context context0) {
        return Build.VERSION.SDK_INT >= 24 ? ContextCompat.createDeviceProtectedStorageContext(context0) : context0;
    }

    public boolean isEnabled() {
        synchronized(this) {
        }
        return this.dataCollectionDefaultEnabled;
    }

    // 去混淆评级： 低(20)
    private boolean readAutoDataCollectionEnabled() {
        return this.sharedPreferences.contains("firebase_data_collection_default_enabled") ? this.sharedPreferences.getBoolean("firebase_data_collection_default_enabled", true) : this.readManifestDataCollectionEnabled();
    }

    private boolean readManifestDataCollectionEnabled() {
        try {
            PackageManager packageManager0 = this.deviceProtectedContext.getPackageManager();
            if(packageManager0 != null) {
                ApplicationInfo applicationInfo0 = packageManager0.getApplicationInfo("com.ruliweb.www.ruliapp", 0x80);
                return applicationInfo0 == null || applicationInfo0.metaData == null || !applicationInfo0.metaData.containsKey("firebase_data_collection_default_enabled") ? true : applicationInfo0.metaData.getBoolean("firebase_data_collection_default_enabled");
            }
        }
        catch(PackageManager.NameNotFoundException unused_ex) {
        }
        return true;
    }

    public void setEnabled(Boolean boolean0) {
        synchronized(this) {
            if(boolean0 == null) {
                this.sharedPreferences.edit().remove("firebase_data_collection_default_enabled").apply();
                this.updateDataCollectionDefaultEnabled(this.readManifestDataCollectionEnabled());
            }
            else {
                boolean z = Boolean.TRUE.equals(boolean0);
                this.sharedPreferences.edit().putBoolean("firebase_data_collection_default_enabled", z).apply();
                this.updateDataCollectionDefaultEnabled(z);
            }
        }
    }

    private void updateDataCollectionDefaultEnabled(boolean z) {
        synchronized(this) {
            if(this.dataCollectionDefaultEnabled != z) {
                this.dataCollectionDefaultEnabled = z;
                DataCollectionDefaultChange dataCollectionDefaultChange0 = new DataCollectionDefaultChange(z);
                Event event0 = new Event(DataCollectionDefaultChange.class, dataCollectionDefaultChange0);
                this.publisher.publish(event0);
            }
        }
    }
}

