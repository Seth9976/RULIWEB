package com.google.android.gms.internal.auth-api-phone;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.auth.api.phone.SmsCodeAutofillClient;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi.Settings;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;

public final class zzr extends GoogleApi implements SmsCodeAutofillClient {
    private static final ClientKey zza;
    private static final AbstractClientBuilder zzb;
    private static final Api zzc;

    static {
        ClientKey api$ClientKey0 = new ClientKey();
        zzr.zza = api$ClientKey0;
        zzn zzn0 = new zzn();
        zzr.zzb = zzn0;
        zzr.zzc = new Api("SmsCodeAutofill.API", zzn0, api$ClientKey0);
    }

    public zzr(Activity activity0) {
        super(activity0, zzr.zzc, ApiOptions.NO_OPTIONS, Settings.DEFAULT_SETTINGS);
    }

    public zzr(Context context0) {
        super(context0, zzr.zzc, ApiOptions.NO_OPTIONS, Settings.DEFAULT_SETTINGS);
    }

    @Override  // com.google.android.gms.auth.api.phone.SmsCodeAutofillClient
    public final Task checkPermissionState() {
        return this.doRead(TaskApiCall.builder().setFeatures(new Feature[]{zzac.zza}).run(new zzk(this)).setMethodKey(0x61C).build());
    }

    @Override  // com.google.android.gms.auth.api.phone.SmsCodeAutofillClient
    public final Task hasOngoingSmsRequest(String s) {
        Preconditions.checkNotNull(s);
        Preconditions.checkArgument(!s.isEmpty(), "The package name cannot be empty.");
        return this.doRead(TaskApiCall.builder().setFeatures(new Feature[]{zzac.zza}).run(new zzl(this, s)).setMethodKey(0x61D).build());
    }

    @Override  // com.google.android.gms.auth.api.phone.SmsCodeAutofillClient
    public final Task startSmsCodeRetriever() {
        return this.doWrite(TaskApiCall.builder().setFeatures(new Feature[]{zzac.zza}).run(new zzm(this)).setMethodKey(0x61B).build());
    }
}

