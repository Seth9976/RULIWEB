package com.google.android.gms.auth.account;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.GoogleApi.Settings;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.internal.auth.zzal;
import com.google.android.gms.tasks.Task;

public class WorkAccountClient extends GoogleApi {
    private final WorkAccountApi zza;

    WorkAccountClient(Activity activity0) {
        super(activity0, WorkAccount.API, NoOptions.NO_OPTIONS, Settings.DEFAULT_SETTINGS);
        this.zza = new zzal();
    }

    WorkAccountClient(Context context0) {
        super(context0, WorkAccount.API, NoOptions.NO_OPTIONS, Settings.DEFAULT_SETTINGS);
        this.zza = new zzal();
    }

    public Task addWorkAccount(String s) {
        GoogleApiClient googleApiClient0 = this.asGoogleApiClient();
        return PendingResultUtil.toTask(this.zza.addWorkAccount(googleApiClient0, s), new zzg(this));
    }

    public Task removeWorkAccount(Account account0) {
        GoogleApiClient googleApiClient0 = this.asGoogleApiClient();
        return PendingResultUtil.toVoidTask(this.zza.removeWorkAccount(googleApiClient0, account0));
    }

    public Task setWorkAuthenticatorEnabled(boolean z) {
        GoogleApiClient googleApiClient0 = this.asGoogleApiClient();
        return PendingResultUtil.toVoidTask(this.zza.setWorkAuthenticatorEnabledWithResult(googleApiClient0, z));
    }
}

