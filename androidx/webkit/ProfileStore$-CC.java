package androidx.webkit;

import androidx.webkit.internal.ProfileStoreImpl;
import androidx.webkit.internal.WebViewFeatureInternal;

public final class ProfileStore.-CC {
    public static ProfileStore getInstance() {
        if(!WebViewFeatureInternal.MULTI_PROFILE.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        return ProfileStoreImpl.getInstance();
    }
}

