package org.chromium.support_lib_boundary;

import org.jspecify.annotations.NullMarked;

@NullMarked
public interface SafeBrowsingResponseBoundaryInterface {
    void backToSafety(boolean arg1);

    void proceed(boolean arg1);

    void showInterstitial(boolean arg1);
}

