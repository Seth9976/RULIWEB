package com.pairip.licensecheck;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

public class LicenseContentProvider extends ContentProvider {
    @Override  // android.content.ContentProvider
    public int delete(Uri uri0, String s, String[] arr_s) {
        throw new UnsupportedOperationException("Delete is not supported ");
    }

    @Override  // android.content.ContentProvider
    public String getType(Uri uri0) {
        throw new UnsupportedOperationException("GetType is not supported ");
    }

    @Override  // android.content.ContentProvider
    public Uri insert(Uri uri0, ContentValues contentValues0) {
        throw new UnsupportedOperationException("Insert is not supported ");
    }

    @Override  // android.content.ContentProvider
    public boolean onCreate() {
        new LicenseClient(this.getContext()).initializeLicenseCheck();
        return true;
    }

    @Override  // android.content.ContentProvider
    public Cursor query(Uri uri0, String[] arr_s, String s, String[] arr_s1, String s1) {
        throw new UnsupportedOperationException("Query is not supported ");
    }

    @Override  // android.content.ContentProvider
    public int update(Uri uri0, ContentValues contentValues0, String s, String[] arr_s) {
        throw new UnsupportedOperationException("Update is not supported ");
    }
}

