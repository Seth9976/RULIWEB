package com.google.android.gms.internal.auth-api;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.auth.api.identity.BeginSignInRequest.Builder;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.GetPhoneNumberHintIntentRequest;
import com.google.android.gms.auth.api.identity.GetSignInIntentRequest;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.identity.zbs;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApi.Settings;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zbap extends GoogleApi implements SignInClient {
    private static final ClientKey zba;
    private static final AbstractClientBuilder zbb;
    private static final Api zbc;
    private final String zbd;

    static {
        ClientKey api$ClientKey0 = new ClientKey();
        zbap.zba = api$ClientKey0;
        zbak zbak0 = new zbak();
        zbap.zbb = zbak0;
        zbap.zbc = new Api("Auth.Api.Identity.SignIn.API", zbak0, api$ClientKey0);
    }

    public zbap(Activity activity0, zbs zbs0) {
        super(activity0, zbap.zbc, zbs0, Settings.DEFAULT_SETTINGS);
        this.zbd = "b-bMu8YSmfOeioJ3QNJLaw";
    }

    public zbap(Context context0, zbs zbs0) {
        super(context0, zbap.zbc, zbs0, Settings.DEFAULT_SETTINGS);
        this.zbd = "ledrfds0PX1HIbIypSBl6g";
    }

    @Override  // com.google.android.gms.auth.api.identity.SignInClient
    public final Task beginSignIn(BeginSignInRequest beginSignInRequest0) {
        Preconditions.checkNotNull(beginSignInRequest0);
        Builder beginSignInRequest$Builder0 = BeginSignInRequest.zba(beginSignInRequest0);
        beginSignInRequest$Builder0.zba(this.zbd);
        BeginSignInRequest beginSignInRequest1 = beginSignInRequest$Builder0.build();
        return this.doRead(TaskApiCall.builder().setFeatures(new Feature[]{new Feature("auth_api_credentials_begin_sign_in", 8L)}).run(new zbai(this, beginSignInRequest1)).setAutoResolveMissingFeatures(false).setMethodKey(0x611).build());
    }

    @Override  // com.google.android.gms.auth.api.identity.SignInClient
    public final String getPhoneNumberFromIntent(Intent intent0) throws ApiException {
        if(intent0 == null) {
            throw new ApiException(Status.RESULT_INTERNAL_ERROR);
        }
        Status status0 = (Status)SafeParcelableSerializer.deserializeFromIntentExtra(intent0, "status", Status.CREATOR);
        if(status0 == null) {
            throw new ApiException(Status.RESULT_CANCELED);
        }
        if(!status0.isSuccess()) {
            throw new ApiException(status0);
        }
        String s = intent0.getStringExtra("phone_number_hint_result");
        if(s == null) {
            throw new ApiException(Status.RESULT_INTERNAL_ERROR);
        }
        return s;
    }

    @Override  // com.google.android.gms.auth.api.identity.SignInClient
    public final Task getPhoneNumberHintIntent(GetPhoneNumberHintIntentRequest getPhoneNumberHintIntentRequest0) {
        Preconditions.checkNotNull(getPhoneNumberHintIntentRequest0);
        return this.doRead(TaskApiCall.builder().setFeatures(new Feature[]{zbar.zbh}).run((zbaq zbaq0, TaskCompletionSource taskCompletionSource0) -> {
            zbao zbao0 = new zbao(this, taskCompletionSource0);
            ((zbv)zbaq0.getService()).zbd(zbao0, getPhoneNumberHintIntentRequest0, this.zbd);
        }).setMethodKey(0x675).build());
    }

    @Override  // com.google.android.gms.auth.api.identity.SignInClient
    public final SignInCredential getSignInCredentialFromIntent(Intent intent0) throws ApiException {
        if(intent0 == null) {
            throw new ApiException(Status.RESULT_INTERNAL_ERROR);
        }
        Status status0 = (Status)SafeParcelableSerializer.deserializeFromIntentExtra(intent0, "status", Status.CREATOR);
        if(status0 == null) {
            throw new ApiException(Status.RESULT_CANCELED);
        }
        if(!status0.isSuccess()) {
            throw new ApiException(status0);
        }
        SignInCredential signInCredential0 = (SignInCredential)SafeParcelableSerializer.deserializeFromIntentExtra(intent0, "sign_in_credential", SignInCredential.CREATOR);
        if(signInCredential0 == null) {
            throw new ApiException(Status.RESULT_INTERNAL_ERROR);
        }
        return signInCredential0;
    }

    @Override  // com.google.android.gms.auth.api.identity.SignInClient
    public final Task getSignInIntent(GetSignInIntentRequest getSignInIntentRequest0) {
        Preconditions.checkNotNull(getSignInIntentRequest0);
        com.google.android.gms.auth.api.identity.GetSignInIntentRequest.Builder getSignInIntentRequest$Builder0 = GetSignInIntentRequest.zba(getSignInIntentRequest0);
        getSignInIntentRequest$Builder0.zba(this.zbd);
        GetSignInIntentRequest getSignInIntentRequest1 = getSignInIntentRequest$Builder0.build();
        return this.doRead(TaskApiCall.builder().setFeatures(new Feature[]{zbar.zbf}).run(new zbaj(this, getSignInIntentRequest1)).setMethodKey(0x613).build());
    }

    @Override  // com.google.android.gms.auth.api.identity.SignInClient
    public final Task signOut() {
        this.getApplicationContext().getSharedPreferences("com.google.android.gms.signin", 0).edit().clear().apply();
        for(Object object0: GoogleApiClient.getAllClients()) {
            ((GoogleApiClient)object0).maybeSignOut();
        }
        GoogleApiManager.reportSignOut();
        return this.doWrite(TaskApiCall.builder().setFeatures(new Feature[]{zbar.zbb}).run((zbaq zbaq0, TaskCompletionSource taskCompletionSource0) -> {
            zbam zbam0 = new zbam(this, taskCompletionSource0);
            ((zbv)zbaq0.getService()).zbf(zbam0, this.zbd);
        }).setAutoResolveMissingFeatures(false).setMethodKey(0x612).build());
    }

    // 检测为 Lambda 实现
    final void zba(GetPhoneNumberHintIntentRequest getPhoneNumberHintIntentRequest0, zbaq zbaq0, TaskCompletionSource taskCompletionSource0) throws RemoteException [...]

    // 检测为 Lambda 实现
    final void zbb(zbaq zbaq0, TaskCompletionSource taskCompletionSource0) throws RemoteException [...]
}

