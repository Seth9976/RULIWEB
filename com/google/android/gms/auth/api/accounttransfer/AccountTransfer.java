package com.google.android.gms.auth.api.accounttransfer;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.auth.zzao;

public final class AccountTransfer {
    public static final String ACTION_ACCOUNT_EXPORT_DATA_AVAILABLE = "com.google.android.gms.auth.ACCOUNT_EXPORT_DATA_AVAILABLE";
    public static final String ACTION_ACCOUNT_IMPORT_DATA_AVAILABLE = "com.google.android.gms.auth.ACCOUNT_IMPORT_DATA_AVAILABLE";
    public static final String ACTION_START_ACCOUNT_EXPORT = "com.google.android.gms.auth.START_ACCOUNT_EXPORT";
    public static final String KEY_EXTRA_ACCOUNT_TYPE = "key_extra_account_type";
    public static final Api zza;
    @Deprecated
    public static final zzao zzb;
    @Deprecated
    public static final zzao zzc;
    private static final ClientKey zzd;
    private static final AbstractClientBuilder zze;

    static {
        ClientKey api$ClientKey0 = new ClientKey();
        AccountTransfer.zzd = api$ClientKey0;
        zza zza0 = new zza();
        AccountTransfer.zze = zza0;
        AccountTransfer.zza = new Api("AccountTransfer.ACCOUNT_TRANSFER_API", zza0, api$ClientKey0);
        AccountTransfer.zzb = new zzao();
        AccountTransfer.zzc = new zzao();
    }

    public static AccountTransferClient getAccountTransferClient(Activity activity0) {
        return new AccountTransferClient(activity0, null);
    }

    public static AccountTransferClient getAccountTransferClient(Context context0) {
        return new AccountTransferClient(context0, null);
    }
}

