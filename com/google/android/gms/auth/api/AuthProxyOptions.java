package com.google.android.gms.auth.api;

import android.os.Bundle;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.internal.Objects;

public final class AuthProxyOptions implements Optional {
    public static final AuthProxyOptions zza;
    private final Bundle zzb;

    static {
        AuthProxyOptions.zza = new AuthProxyOptions(new Bundle(), null);
    }

    AuthProxyOptions(Bundle bundle0, zzb zzb0) {
        this.zzb = bundle0;
    }

    @Override
    public final boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        return object0 instanceof AuthProxyOptions ? Objects.checkBundlesEquality(this.zzb, ((AuthProxyOptions)object0).zzb) : false;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzb});
    }

    public final Bundle zza() {
        return new Bundle(this.zzb);
    }
}

