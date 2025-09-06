package com.google.android.gms.auth.api.phone;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi.Settings;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.tasks.Task;

public abstract class SmsRetrieverClient extends GoogleApi implements SmsRetrieverApi {
    private static final ClientKey zza;
    private static final AbstractClientBuilder zzb;
    private static final Api zzc;

    static {
        ClientKey api$ClientKey0 = new ClientKey();
        SmsRetrieverClient.zza = api$ClientKey0;
        zza zza0 = new zza();
        SmsRetrieverClient.zzb = zza0;
        SmsRetrieverClient.zzc = new Api("SmsRetriever.API", zza0, api$ClientKey0);
    }

    public SmsRetrieverClient(Activity activity0) {
        super(activity0, SmsRetrieverClient.zzc, ApiOptions.NO_OPTIONS, Settings.DEFAULT_SETTINGS);
    }

    public SmsRetrieverClient(Context context0) {
        super(context0, SmsRetrieverClient.zzc, ApiOptions.NO_OPTIONS, Settings.DEFAULT_SETTINGS);
    }

    @Override  // com.google.android.gms.auth.api.phone.SmsRetrieverApi
    public abstract Task startSmsRetriever();

    @Override  // com.google.android.gms.auth.api.phone.SmsRetrieverApi
    public abstract Task startSmsUserConsent(String arg1);
}

