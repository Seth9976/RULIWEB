package com.google.firebase.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.StartupTime;
import java.util.concurrent.atomic.AtomicBoolean;

public class FirebaseInitProvider extends ContentProvider {
    static final String EMPTY_APPLICATION_ID_PROVIDER_AUTHORITY = "com.google.firebase.firebaseinitprovider";
    private static final String TAG = "FirebaseInitProvider";
    private static AtomicBoolean currentlyInitializing;
    private static StartupTime startupTime;

    static {
        FirebaseInitProvider.startupTime = StartupTime.now();
        FirebaseInitProvider.currentlyInitializing = new AtomicBoolean(false);
    }

    @Override  // android.content.ContentProvider
    public void attachInfo(Context context0, ProviderInfo providerInfo0) {
        FirebaseInitProvider.checkContentProviderAuthority(providerInfo0);
        super.attachInfo(context0, providerInfo0);
    }

    private static void checkContentProviderAuthority(ProviderInfo providerInfo0) {
        Preconditions.checkNotNull(providerInfo0, "FirebaseInitProvider ProviderInfo cannot be null.");
        if("com.google.firebase.firebaseinitprovider".equals(providerInfo0.authority)) {
            throw new IllegalStateException("Incorrect provider authority in manifest. Most likely due to a missing applicationId variable in application\'s build.gradle.");
        }
    }

    @Override  // android.content.ContentProvider
    public int delete(Uri uri0, String s, String[] arr_s) {
        return 0;
    }

    public static StartupTime getStartupTime() {
        return FirebaseInitProvider.startupTime;
    }

    @Override  // android.content.ContentProvider
    public String getType(Uri uri0) {
        return null;
    }

    @Override  // android.content.ContentProvider
    public Uri insert(Uri uri0, ContentValues contentValues0) {
        return null;
    }

    public static boolean isCurrentlyInitializing() {
        return FirebaseInitProvider.currentlyInitializing.get();
    }

    @Override  // android.content.ContentProvider
    public boolean onCreate() {
        try {
            FirebaseInitProvider.currentlyInitializing.set(true);
            if(FirebaseApp.initializeApp(this.getContext()) == null) {
                Log.i("FirebaseInitProvider", "FirebaseApp initialization unsuccessful");
            }
            else {
                Log.i("FirebaseInitProvider", "FirebaseApp initialization successful");
            }
            return false;
        }
        finally {
            FirebaseInitProvider.currentlyInitializing.set(false);
        }
    }

    @Override  // android.content.ContentProvider
    public Cursor query(Uri uri0, String[] arr_s, String s, String[] arr_s1, String s1) {
        return null;
    }

    @Override  // android.content.ContentProvider
    public int update(Uri uri0, ContentValues contentValues0, String s, String[] arr_s) {
        return 0;
    }
}

