package com.google.android.gms.common.internal;

import android.content.Context;
import android.util.SparseIntArray;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api.Client;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;

public final class zal {
    private final SparseIntArray zaa;
    private GoogleApiAvailabilityLight zab;

    public zal() {
        this(GoogleApiAvailability.getInstance());
    }

    public zal(GoogleApiAvailabilityLight googleApiAvailabilityLight0) {
        this.zaa = new SparseIntArray();
        Preconditions.checkNotNull(googleApiAvailabilityLight0);
        this.zab = googleApiAvailabilityLight0;
    }

    public final int zaa(Context context0, int v) {
        return this.zaa.get(v, -1);
    }

    @ResultIgnorabilityUnspecified
    public final int zab(Context context0, Client api$Client0) {
        Preconditions.checkNotNull(context0);
        Preconditions.checkNotNull(api$Client0);
        int v = 0;
        if(!api$Client0.requiresGooglePlayServices()) {
            return 0;
        }
        int v1 = api$Client0.getMinApkVersion();
        int v2 = this.zaa(context0, v1);
        if(v2 != -1) {
            return v2;
        }
        for(int v3 = 0; true; ++v3) {
            if(v3 >= this.zaa.size()) {
                v = -1;
                break;
            }
            int v4 = this.zaa.keyAt(v3);
            if(v4 > v1 && this.zaa.get(v4) == 0) {
                break;
            }
        }
        if(v == -1) {
            v = this.zab.isGooglePlayServicesAvailable(context0, v1);
        }
        this.zaa.put(v1, v);
        return v;
    }

    public final void zac() {
        this.zaa.clear();
    }
}

