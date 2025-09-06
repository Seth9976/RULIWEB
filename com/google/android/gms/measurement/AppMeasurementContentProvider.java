package com.google.android.gms.measurement;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.internal.zzgd;

@Deprecated
public class AppMeasurementContentProvider extends ContentProvider {
    @Override  // android.content.ContentProvider
    public void attachInfo(Context context0, ProviderInfo providerInfo0) {
        super.attachInfo(context0, providerInfo0);
        if("com.google.android.gms.measurement.google_measurement_service".equals(providerInfo0.authority)) {
            throw new IllegalStateException("Incorrect provider authority in manifest. Most likely due to a missing applicationId variable in application\'s build.gradle.");
        }
    }

    @Override  // android.content.ContentProvider
    public int delete(Uri uri0, String s, String[] arr_s) {
        return 0;
    }

    @Override  // android.content.ContentProvider
    public String getType(Uri uri0) {
        return null;
    }

    @Override  // android.content.ContentProvider
    public Uri insert(Uri uri0, ContentValues contentValues0) {
        return null;
    }

    @Override  // android.content.ContentProvider
    public boolean onCreate() {
        Context context0 = this.getContext();
        Preconditions.checkNotNull(context0);
        zzgd.zzp(context0, null, null);
        return false;
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

