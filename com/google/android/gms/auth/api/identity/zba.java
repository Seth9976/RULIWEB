package com.google.android.gms.auth.api.identity;

import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.internal.Objects;

public final class zba implements Optional {
    public zba(String s) {
    }

    @Override
    public final boolean equals(Object object0) {
        return object0 instanceof zba;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(new Object[]{zba.class});
    }
}

