package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Set;

public final class zaaj implements zabf {
    private final zabi zaa;
    private boolean zab;

    public zaaj(zabi zabi0) {
        this.zab = false;
        this.zaa = zabi0;
    }

    @Override  // com.google.android.gms.common.api.internal.zabf
    public final ApiMethodImpl zaa(ApiMethodImpl baseImplementation$ApiMethodImpl0) {
        this.zab(baseImplementation$ApiMethodImpl0);
        return baseImplementation$ApiMethodImpl0;
    }

    @Override  // com.google.android.gms.common.api.internal.zabf
    public final ApiMethodImpl zab(ApiMethodImpl baseImplementation$ApiMethodImpl0) {
        try {
            this.zaa.zag.zai.zaa(baseImplementation$ApiMethodImpl0);
            Client api$Client0 = (Client)this.zaa.zag.zac.get(baseImplementation$ApiMethodImpl0.getClientKey());
            Preconditions.checkNotNull(api$Client0, "Appropriate Api was not requested.");
            if(!api$Client0.isConnected() && this.zaa.zab.containsKey(baseImplementation$ApiMethodImpl0.getClientKey())) {
                baseImplementation$ApiMethodImpl0.setFailedResult(new Status(17));
                return baseImplementation$ApiMethodImpl0;
            }
            baseImplementation$ApiMethodImpl0.run(api$Client0);
        }
        catch(DeadObjectException unused_ex) {
            zaah zaah0 = new zaah(this, this);
            this.zaa.zal(zaah0);
        }
        return baseImplementation$ApiMethodImpl0;
    }

    static zabi zac(zaaj zaaj0) {
        return zaaj0.zaa;
    }

    @Override  // com.google.android.gms.common.api.internal.zabf
    public final void zad() {
    }

    @Override  // com.google.android.gms.common.api.internal.zabf
    public final void zae() {
        if(this.zab) {
            this.zab = false;
            zaai zaai0 = new zaai(this, this);
            this.zaa.zal(zaai0);
        }
    }

    final void zaf() {
        if(this.zab) {
            this.zab = false;
            this.zaa.zag.zai.zab();
            this.zaj();
        }
    }

    @Override  // com.google.android.gms.common.api.internal.zabf
    public final void zag(Bundle bundle0) {
    }

    @Override  // com.google.android.gms.common.api.internal.zabf
    public final void zah(ConnectionResult connectionResult0, Api api0, boolean z) {
    }

    @Override  // com.google.android.gms.common.api.internal.zabf
    public final void zai(int v) {
        this.zaa.zak(null);
        this.zaa.zah.zac(v, this.zab);
    }

    @Override  // com.google.android.gms.common.api.internal.zabf
    public final boolean zaj() {
        if(!this.zab) {
            Set set0 = this.zaa.zag.zah;
            if(set0 != null && !set0.isEmpty()) {
                this.zab = true;
                for(Object object0: set0) {
                    ((zada)object0).zah();
                }
                return false;
            }
            this.zaa.zak(null);
            return true;
        }
        return false;
    }
}

