package androidx.webkit;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import androidx.webkit.internal.WebViewGlueCommunicator;
import java.io.FileNotFoundException;
import org.chromium.support_lib_boundary.DropDataContentProviderBoundaryInterface;

public final class DropDataContentProvider extends ContentProvider {
    DropDataContentProviderBoundaryInterface mImpl;

    @Override  // android.content.ContentProvider
    public Bundle call(String s, String s1, Bundle bundle0) {
        return this.getDropImpl().call(s, s1, bundle0);
    }

    @Override  // android.content.ContentProvider
    public int delete(Uri uri0, String s, String[] arr_s) {
        throw new UnsupportedOperationException("delete method is not supported.");
    }

    private DropDataContentProviderBoundaryInterface getDropImpl() {
        if(this.mImpl == null) {
            DropDataContentProviderBoundaryInterface dropDataContentProviderBoundaryInterface0 = WebViewGlueCommunicator.getFactory().getDropDataProvider();
            this.mImpl = dropDataContentProviderBoundaryInterface0;
            dropDataContentProviderBoundaryInterface0.onCreate();
        }
        return this.mImpl;
    }

    @Override  // android.content.ContentProvider
    public String getType(Uri uri0) {
        return this.getDropImpl().getType(uri0);
    }

    @Override  // android.content.ContentProvider
    public Uri insert(Uri uri0, ContentValues contentValues0) {
        throw new UnsupportedOperationException("Insert method is not supported.");
    }

    @Override  // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    @Override  // android.content.ContentProvider
    public ParcelFileDescriptor openFile(Uri uri0, String s) throws FileNotFoundException {
        return this.getDropImpl().openFile(this, uri0);
    }

    @Override  // android.content.ContentProvider
    public Cursor query(Uri uri0, String[] arr_s, String s, String[] arr_s1, String s1) {
        return this.getDropImpl().query(uri0, arr_s, s, arr_s1, s1);
    }

    @Override  // android.content.ContentProvider
    public int update(Uri uri0, ContentValues contentValues0, String s, String[] arr_s) {
        throw new UnsupportedOperationException("update method is not supported.");
    }
}

