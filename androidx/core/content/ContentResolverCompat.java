package androidx.core.content;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.OperationCanceledException;

public final class ContentResolverCompat {
    public static Cursor query(ContentResolver contentResolver0, Uri uri0, String[] arr_s, String s, String[] arr_s1, String s1, CancellationSignal cancellationSignal0) {
        try {
            return contentResolver0.query(uri0, arr_s, s, arr_s1, s1, cancellationSignal0);
        }
        catch(Exception exception0) {
            if(exception0 instanceof OperationCanceledException) {
                throw new androidx.core.os.OperationCanceledException();
            }
            throw exception0;
        }
    }

    @Deprecated
    public static Cursor query(ContentResolver contentResolver0, Uri uri0, String[] arr_s, String s, String[] arr_s1, String s1, androidx.core.os.CancellationSignal cancellationSignal0) {
        return cancellationSignal0 == null ? ContentResolverCompat.query(contentResolver0, uri0, arr_s, s, arr_s1, s1, null) : ContentResolverCompat.query(contentResolver0, uri0, arr_s, s, arr_s1, s1, ((CancellationSignal)cancellationSignal0.getCancellationSignalObject()));
    }
}

