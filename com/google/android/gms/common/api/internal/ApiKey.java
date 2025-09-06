package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.Objects;

public final class ApiKey {
    private final int zaa;
    private final Api zab;
    private final ApiOptions zac;
    private final String zad;

    private ApiKey(Api api0, ApiOptions api$ApiOptions0, String s) {
        this.zab = api0;
        this.zac = api$ApiOptions0;
        this.zad = s;
        this.zaa = Objects.hashCode(new Object[]{api0, api$ApiOptions0, s});
    }

    @Override
    public final boolean equals(Object object0) {
        if(object0 == null) {
            return false;
        }
        if(object0 == this) {
            return true;
        }
        return object0 instanceof ApiKey ? Objects.equal(this.zab, ((ApiKey)object0).zab) && Objects.equal(this.zac, ((ApiKey)object0).zac) && Objects.equal(this.zad, ((ApiKey)object0).zad) : false;
    }

    public static ApiKey getSharedApiKey(Api api0, ApiOptions api$ApiOptions0, String s) {
        return new ApiKey(api0, api$ApiOptions0, s);
    }

    @Override
    public final int hashCode() {
        return this.zaa;
    }

    public final String zaa() {
        return this.zab.zad();
    }
}

