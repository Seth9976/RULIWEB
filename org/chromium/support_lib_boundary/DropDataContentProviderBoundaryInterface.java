package org.chromium.support_lib_boundary;

import android.content.ContentProvider;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import java.io.FileNotFoundException;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface DropDataContentProviderBoundaryInterface {
    Uri cache(byte[] arg1, String arg2, String arg3);

    Bundle call(String arg1, String arg2, Bundle arg3);

    String[] getStreamTypes(Uri arg1, String arg2);

    String getType(Uri arg1);

    boolean onCreate();

    void onDragEnd(boolean arg1);

    ParcelFileDescriptor openFile(ContentProvider arg1, Uri arg2) throws FileNotFoundException;

    Cursor query(Uri arg1, String[] arg2, String arg3, String[] arg4, String arg5);

    void setClearCachedDataIntervalMs(int arg1);
}

