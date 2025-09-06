package com.google.android.gms.auth.api.accounttransfer;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi.Settings.Builder;
import com.google.android.gms.common.api.GoogleApi.Settings;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.auth.zzaq;
import com.google.android.gms.internal.auth.zzav;
import com.google.android.gms.internal.auth.zzax;
import com.google.android.gms.internal.auth.zzaz;
import com.google.android.gms.internal.auth.zzbb;
import com.google.android.gms.tasks.Task;

public class AccountTransferClient extends GoogleApi {
    public static final int zza;
    private static final ClientKey zzb;
    private static final AbstractClientBuilder zzc;
    private static final Api zzd;

    static {
        ClientKey api$ClientKey0 = new ClientKey();
        AccountTransferClient.zzb = api$ClientKey0;
        zzb zzb0 = new zzb();
        AccountTransferClient.zzc = zzb0;
        AccountTransferClient.zzd = new Api("AccountTransfer.ACCOUNT_TRANSFER_API", zzb0, api$ClientKey0);
    }

    AccountTransferClient(Activity activity0, zzr zzr0) {
        Settings googleApi$Settings0 = new Builder().setMapper(new ApiExceptionMapper()).build();
        super(activity0, AccountTransferClient.zzd, zzr.zza, googleApi$Settings0);
    }

    AccountTransferClient(Context context0, zzr zzr0) {
        Settings googleApi$Settings0 = new Builder().setMapper(new ApiExceptionMapper()).build();
        super(context0, AccountTransferClient.zzd, zzr.zza, googleApi$Settings0);
    }

    public Task getDeviceMetaData(String s) {
        Preconditions.checkNotNull(s);
        return this.doRead(new zzg(this, 1608, new zzaq(s)));
    }

    public Task notifyCompletion(String s, int v) {
        Preconditions.checkNotNull(s);
        return this.doWrite(new zzi(this, 1610, new zzav(s, v)));
    }

    public Task retrieveData(String s) {
        Preconditions.checkNotNull(s);
        return this.doRead(new zze(this, 1607, new zzax(s)));
    }

    public Task sendData(String s, byte[] arr_b) {
        Preconditions.checkNotNull(s);
        Preconditions.checkNotNull(arr_b);
        return this.doWrite(new zzc(this, 1606, new zzaz(s, arr_b)));
    }

    public Task showUserChallenge(String s, PendingIntent pendingIntent0) {
        Preconditions.checkNotNull(s);
        Preconditions.checkNotNull(pendingIntent0);
        return this.doWrite(new zzh(this, 1609, new zzbb(s, pendingIntent0)));
    }
}

