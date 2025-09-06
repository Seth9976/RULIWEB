package com.google.android.gms.internal.auth;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.auth.AccountChangeEventsRequest;
import com.google.android.gms.auth.zzd;
import com.google.android.gms.auth.zze;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi.Settings;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzab extends GoogleApi implements zzg {
    private static final ClientKey zza;
    private static final AbstractClientBuilder zzb;
    private static final Api zzc;
    private static final Logger zzd;
    private final Context zze;

    static {
        ClientKey api$ClientKey0 = new ClientKey();
        zzab.zza = api$ClientKey0;
        zzv zzv0 = new zzv();
        zzab.zzb = zzv0;
        zzab.zzc = new Api("GoogleAuthService.API", zzv0, api$ClientKey0);
        zzab.zzd = zzd.zza(new String[]{"GoogleAuthServiceClient"});
    }

    zzab(Context context0) {
        super(context0, zzab.zzc, NoOptions.NO_OPTIONS, Settings.DEFAULT_SETTINGS);
        this.zze = context0;
    }

    @Override  // com.google.android.gms.internal.auth.zzg
    public final Task zza(zzbw zzbw0) {
        return this.doWrite(TaskApiCall.builder().setFeatures(new Feature[]{zze.zzl}).run(new zzt(this, zzbw0)).setMethodKey(0x5E9).build());
    }

    @Override  // com.google.android.gms.internal.auth.zzg
    public final Task zzb(AccountChangeEventsRequest accountChangeEventsRequest0) {
        Preconditions.checkNotNull(accountChangeEventsRequest0, "request cannot be null.");
        return this.doWrite(TaskApiCall.builder().setFeatures(new Feature[]{zze.zzk}).run(new zzu(this, accountChangeEventsRequest0)).setMethodKey(0x5EB).build());
    }

    @Override  // com.google.android.gms.internal.auth.zzg
    public final Task zzc(Account account0, String s, Bundle bundle0) {
        Preconditions.checkNotNull(account0, "Account name cannot be null!");
        Preconditions.checkNotEmpty(s, "Scope cannot be null!");
        return this.doWrite(TaskApiCall.builder().setFeatures(new Feature[]{zze.zzl}).run(new zzs(this, account0, s, bundle0)).setMethodKey(0x5E8).build());
    }

    @Override  // com.google.android.gms.internal.auth.zzg
    public final Task zzd(Account account0) {
        Preconditions.checkNotNull(account0, "account cannot be null.");
        return this.doWrite(TaskApiCall.builder().setFeatures(new Feature[]{zze.zzk}).run(new zzr(this, account0)).setMethodKey(0x5ED).build());
    }

    @Override  // com.google.android.gms.internal.auth.zzg
    public final Task zze(String s) {
        Preconditions.checkNotNull(s, "Client package name cannot be null!");
        return this.doWrite(TaskApiCall.builder().setFeatures(new Feature[]{zze.zzk}).run(new zzq(this, s)).setMethodKey(0x5EA).build());
    }

    static void zzf(Status status0, Object object0, TaskCompletionSource taskCompletionSource0) {
        if(!TaskUtil.trySetResultOrApiException(status0, object0, taskCompletionSource0)) {
            zzab.zzd.w("The task is already complete.", new Object[0]);
        }
    }
}

