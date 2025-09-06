package com.google.android.gms.internal.auth;

import android.accounts.Account;
import com.google.android.gms.common.api.Status;

final class zzad extends zzah {
    final zzae zza;

    zzad(zzae zzae0) {
        this.zza = zzae0;
        super();
    }

    @Override  // com.google.android.gms.internal.auth.zzah
    public final void zzb(Account account0) {
        this.zza.setResult(new zzai((account0 == null ? zzal.zza() : Status.RESULT_SUCCESS), account0));
    }
}

