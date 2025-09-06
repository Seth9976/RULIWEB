package com.google.android.gms.internal.auth;

import android.accounts.Account;
import com.google.android.gms.auth.account.WorkAccount;
import com.google.android.gms.auth.account.WorkAccountApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

public final class zzal implements WorkAccountApi {
    private static final Status zza;

    static {
        zzal.zza = new Status(13);
    }

    @Override  // com.google.android.gms.auth.account.WorkAccountApi
    public final PendingResult addWorkAccount(GoogleApiClient googleApiClient0, String s) {
        return googleApiClient0.execute(new zzae(this, WorkAccount.API, googleApiClient0, s));
    }

    @Override  // com.google.android.gms.auth.account.WorkAccountApi
    public final PendingResult removeWorkAccount(GoogleApiClient googleApiClient0, Account account0) {
        return googleApiClient0.execute(new zzag(this, WorkAccount.API, googleApiClient0, account0));
    }

    @Override  // com.google.android.gms.auth.account.WorkAccountApi
    public final void setWorkAuthenticatorEnabled(GoogleApiClient googleApiClient0, boolean z) {
        this.setWorkAuthenticatorEnabledWithResult(googleApiClient0, z);
    }

    @Override  // com.google.android.gms.auth.account.WorkAccountApi
    public final PendingResult setWorkAuthenticatorEnabledWithResult(GoogleApiClient googleApiClient0, boolean z) {
        return googleApiClient0.execute(new zzac(this, WorkAccount.API, googleApiClient0, z));
    }

    static Status zza() {
        return zzal.zza;
    }
}

