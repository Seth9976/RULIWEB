package com.google.android.gms.fido.fido2;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialCreationOptions;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialRequestOptions;
import com.google.android.gms.internal.fido.zzo;
import com.google.android.gms.tasks.Task;

public class Fido2ApiClient extends GoogleApi {
    private static final ClientKey zza;
    private static final Api zzb;

    static {
        ClientKey api$ClientKey0 = new ClientKey();
        Fido2ApiClient.zza = api$ClientKey0;
        Fido2ApiClient.zzb = new Api("Fido.FIDO2_API", new zzo(), api$ClientKey0);
    }

    @Deprecated
    public Fido2ApiClient(Activity activity0) {
        ApiExceptionMapper apiExceptionMapper0 = new ApiExceptionMapper();
        super(activity0, Fido2ApiClient.zzb, ApiOptions.NO_OPTIONS, apiExceptionMapper0);
    }

    @Deprecated
    public Fido2ApiClient(Context context0) {
        ApiExceptionMapper apiExceptionMapper0 = new ApiExceptionMapper();
        super(context0, Fido2ApiClient.zzb, ApiOptions.NO_OPTIONS, apiExceptionMapper0);
    }

    @Deprecated
    public Task getRegisterIntent(PublicKeyCredentialCreationOptions publicKeyCredentialCreationOptions0) {
        return this.doRead(TaskApiCall.builder().setMethodKey(5409).run(new zzd(this, publicKeyCredentialCreationOptions0)).build());
    }

    public Task getRegisterPendingIntent(PublicKeyCredentialCreationOptions publicKeyCredentialCreationOptions0) {
        return this.doRead(TaskApiCall.builder().run(new zzc(this, publicKeyCredentialCreationOptions0)).setMethodKey(5407).build());
    }

    @Deprecated
    public Task getSignIntent(PublicKeyCredentialRequestOptions publicKeyCredentialRequestOptions0) {
        return this.doRead(TaskApiCall.builder().setMethodKey(5410).run(new zza(this, publicKeyCredentialRequestOptions0)).build());
    }

    public Task getSignPendingIntent(PublicKeyCredentialRequestOptions publicKeyCredentialRequestOptions0) {
        return this.doRead(TaskApiCall.builder().run(new zze(this, publicKeyCredentialRequestOptions0)).setMethodKey(5408).build());
    }

    public Task isUserVerifyingPlatformAuthenticatorAvailable() {
        return this.doRead(TaskApiCall.builder().run(new zzb(this)).setFeatures(new Feature[]{com.google.android.gms.fido.zza.zzh}).setMethodKey(5411).build());
    }
}

