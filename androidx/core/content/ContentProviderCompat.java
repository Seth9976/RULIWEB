package androidx.core.content;

import android.content.ContentProvider;
import android.content.Context;

public final class ContentProviderCompat {
    public static Context requireContext(ContentProvider contentProvider0) {
        Context context0 = contentProvider0.getContext();
        if(context0 == null) {
            throw new IllegalStateException("Cannot find context from the provider.");
        }
        return context0;
    }
}

