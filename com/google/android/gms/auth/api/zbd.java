package com.google.android.gms.auth.api;

import android.os.Bundle;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.internal.Objects;

@Deprecated
public final class zbd implements Optional {
    public static final zbd zba;
    private final String zbb;
    private final boolean zbc;
    private final String zbd;

    static {
        zbd.zba = new zbd(new zbc());
    }

    public zbd(zbc zbc0) {
        this.zbb = null;
        this.zbc = zbc0.zba.booleanValue();
        this.zbd = zbc0.zbb;
    }

    @Override
    public final boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        return object0 instanceof zbd ? Objects.equal(null, null) && this.zbc == ((zbd)object0).zbc && Objects.equal(this.zbd, ((zbd)object0).zbd) : false;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(new Object[]{null, Boolean.valueOf(this.zbc), this.zbd});
    }

    public final Bundle zba() {
        Bundle bundle0 = new Bundle();
        bundle0.putString("consumer_package", null);
        bundle0.putBoolean("force_save_dialog", this.zbc);
        bundle0.putString("log_session_id", this.zbd);
        return bundle0;
    }

    static String zbb(zbd zbd0) [...] // Inlined contents
}

