package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.Api;
import java.util.Collections;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;

public final class zaax implements zabf {
    @NotOnlyInitialized
    private final zabi zaa;

    public zaax(zabi zabi0) {
        this.zaa = zabi0;
    }

    @Override  // com.google.android.gms.common.api.internal.zabf
    public final ApiMethodImpl zaa(ApiMethodImpl baseImplementation$ApiMethodImpl0) {
        this.zaa.zag.zaa.add(baseImplementation$ApiMethodImpl0);
        return baseImplementation$ApiMethodImpl0;
    }

    @Override  // com.google.android.gms.common.api.internal.zabf
    public final ApiMethodImpl zab(ApiMethodImpl baseImplementation$ApiMethodImpl0) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }

    @Override  // com.google.android.gms.common.api.internal.zabf
    public final void zad() {
        for(Object object0: this.zaa.zaa.values()) {
            ((Client)object0).disconnect();
        }
        this.zaa.zag.zad = Collections.EMPTY_SET;
    }

    @Override  // com.google.android.gms.common.api.internal.zabf
    public final void zae() {
        this.zaa.zaj();
    }

    @Override  // com.google.android.gms.common.api.internal.zabf
    public final void zag(Bundle bundle0) {
    }

    @Override  // com.google.android.gms.common.api.internal.zabf
    public final void zah(ConnectionResult connectionResult0, Api api0, boolean z) {
    }

    @Override  // com.google.android.gms.common.api.internal.zabf
    public final void zai(int v) {
    }

    @Override  // com.google.android.gms.common.api.internal.zabf
    public final boolean zaj() {
        return true;
    }
}

