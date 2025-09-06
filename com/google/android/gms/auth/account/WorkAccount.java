package com.google.android.gms.auth.account;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.auth.zzal;

public class WorkAccount {
    public static final Api API;
    @Deprecated
    public static final WorkAccountApi WorkAccountApi;
    private static final ClientKey zza;
    private static final AbstractClientBuilder zzb;

    static {
        ClientKey api$ClientKey0 = new ClientKey();
        WorkAccount.zza = api$ClientKey0;
        zzf zzf0 = new zzf();
        WorkAccount.zzb = zzf0;
        WorkAccount.API = new Api("WorkAccount.API", zzf0, api$ClientKey0);
        WorkAccount.WorkAccountApi = new zzal();
    }

    public static WorkAccountClient getClient(Activity activity0) {
        return new WorkAccountClient(activity0);
    }

    public static WorkAccountClient getClient(Context context0) {
        return new WorkAccountClient(context0);
    }
}

