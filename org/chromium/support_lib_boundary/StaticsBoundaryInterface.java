package org.chromium.support_lib_boundary;

import android.content.Context;
import android.net.Uri;
import android.webkit.ValueCallback;
import java.util.List;
import java.util.Set;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface StaticsBoundaryInterface {
    Uri getSafeBrowsingPrivacyPolicyUrl();

    String getVariationsHeader();

    void initSafeBrowsing(Context arg1, ValueCallback arg2);

    boolean isMultiProcessEnabled();

    void setDefaultTrafficStatsTag(int arg1);

    void setDefaultTrafficStatsUid(int arg1);

    void setSafeBrowsingAllowlist(Set arg1, ValueCallback arg2);

    void setSafeBrowsingWhitelist(List arg1, ValueCallback arg2);
}

