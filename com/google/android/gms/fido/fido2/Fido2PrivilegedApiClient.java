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
import com.google.android.gms.fido.fido2.api.common.BrowserPublicKeyCredentialCreationOptions;
import com.google.android.gms.fido.fido2.api.common.BrowserPublicKeyCredentialRequestOptions;
import com.google.android.gms.fido.zza;
import com.google.android.gms.internal.fido.zzj;
import com.google.android.gms.tasks.Task;

public class Fido2PrivilegedApiClient extends GoogleApi {
    private static final ClientKey zza;
    private static final Api zzb;

    static {
        ClientKey api$ClientKey0 = new ClientKey();
        Fido2PrivilegedApiClient.zza = api$ClientKey0;
        Fido2PrivilegedApiClient.zzb = new Api("Fido.FIDO2_PRIVILEGED_API", new zzj(), api$ClientKey0);
    }

    @Deprecated
    public Fido2PrivilegedApiClient(Activity activity0) {
        ApiExceptionMapper apiExceptionMapper0 = new ApiExceptionMapper();
        super(activity0, Fido2PrivilegedApiClient.zzb, ApiOptions.NO_OPTIONS, apiExceptionMapper0);
    }

    @Deprecated
    public Fido2PrivilegedApiClient(Context context0) {
        ApiExceptionMapper apiExceptionMapper0 = new ApiExceptionMapper();
        super(context0, Fido2PrivilegedApiClient.zzb, ApiOptions.NO_OPTIONS, apiExceptionMapper0);
    }

    public Task getCredentialList(String s) {
        return this.doRead(TaskApiCall.builder().run(new zzk(this, s)).setMethodKey(5430).build());
    }

    @Deprecated
    public Task getRegisterIntent(BrowserPublicKeyCredentialCreationOptions browserPublicKeyCredentialCreationOptions0) {
        return this.doRead(TaskApiCall.builder().setMethodKey(5414).run(new zzo(this, browserPublicKeyCredentialCreationOptions0)).build());
    }

    public Task getRegisterPendingIntent(BrowserPublicKeyCredentialCreationOptions browserPublicKeyCredentialCreationOptions0) {
        return this.doRead(TaskApiCall.builder().run(new zzl(this, browserPublicKeyCredentialCreationOptions0)).setMethodKey(5412).build());
    }

    @Deprecated
    public Task getSignIntent(BrowserPublicKeyCredentialRequestOptions browserPublicKeyCredentialRequestOptions0) {
        return this.doRead(TaskApiCall.builder().setMethodKey(5415).run(new zzn(this, browserPublicKeyCredentialRequestOptions0)).build());
    }

    public Task getSignPendingIntent(BrowserPublicKeyCredentialRequestOptions browserPublicKeyCredentialRequestOptions0) {
        return this.doRead(TaskApiCall.builder().run(new zzm(this, browserPublicKeyCredentialRequestOptions0)).setMethodKey(5413).build());
    }

    public Task isUserVerifyingPlatformAuthenticatorAvailable() {
        return this.doRead(TaskApiCall.builder().run(new zzp(this)).setFeatures(new Feature[]{zza.zzh}).setMethodKey(5416).build());
    }
}

