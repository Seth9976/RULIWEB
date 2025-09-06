package com.google.android.gms.internal.auth-api-phone;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.auth.api.phone.SmsCodeBrowserClient;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi.Settings;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.Task;

public final class zzv extends GoogleApi implements SmsCodeBrowserClient {
    private static final ClientKey zza;
    private static final AbstractClientBuilder zzb;
    private static final Api zzc;

    static {
        ClientKey api$ClientKey0 = new ClientKey();
        zzv.zza = api$ClientKey0;
        zzt zzt0 = new zzt();
        zzv.zzb = zzt0;
        zzv.zzc = new Api("SmsCodeBrowser.API", zzt0, api$ClientKey0);
    }

    public zzv(Activity activity0) {
        super(activity0, zzv.zzc, ApiOptions.NO_OPTIONS, Settings.DEFAULT_SETTINGS);
    }

    public zzv(Context context0) {
        super(context0, zzv.zzc, ApiOptions.NO_OPTIONS, Settings.DEFAULT_SETTINGS);
    }

    @Override  // com.google.android.gms.auth.api.phone.SmsCodeBrowserClient
    public final Task startSmsCodeRetriever() {
        return this.doWrite(TaskApiCall.builder().setFeatures(new Feature[]{zzac.zzb}).run(new zzs(this)).setMethodKey(0x61E).build());
    }
}

