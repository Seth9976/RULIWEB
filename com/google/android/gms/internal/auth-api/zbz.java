package com.google.android.gms.internal.auth-api;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.auth.api.identity.AuthorizationClient;
import com.google.android.gms.auth.api.identity.AuthorizationRequest.Builder;
import com.google.android.gms.auth.api.identity.AuthorizationRequest;
import com.google.android.gms.auth.api.identity.AuthorizationResult;
import com.google.android.gms.auth.api.identity.zba;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApi.Settings;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.tasks.Task;

public final class zbz extends GoogleApi implements AuthorizationClient {
    private static final ClientKey zba;
    private static final AbstractClientBuilder zbb;
    private static final Api zbc;
    private final String zbd;

    static {
        ClientKey api$ClientKey0 = new ClientKey();
        zbz.zba = api$ClientKey0;
        zbx zbx0 = new zbx();
        zbz.zbb = zbx0;
        zbz.zbc = new Api("Auth.Api.Identity.Authorization.API", zbx0, api$ClientKey0);
    }

    public zbz(Activity activity0, zba zba0) {
        super(activity0, zbz.zbc, zba0, Settings.DEFAULT_SETTINGS);
        this.zbd = "EMI8ElUXDj3Fwow-Qe1Lyw";
    }

    public zbz(Context context0, zba zba0) {
        super(context0, zbz.zbc, zba0, Settings.DEFAULT_SETTINGS);
        this.zbd = "uRjhDsB5FGenHJQXmJ9oHg";
    }

    @Override  // com.google.android.gms.auth.api.identity.AuthorizationClient
    public final Task authorize(AuthorizationRequest authorizationRequest0) {
        Preconditions.checkNotNull(authorizationRequest0);
        Builder authorizationRequest$Builder0 = AuthorizationRequest.zba(authorizationRequest0);
        authorizationRequest$Builder0.zbb(this.zbd);
        AuthorizationRequest authorizationRequest1 = authorizationRequest$Builder0.build();
        return this.doRead(TaskApiCall.builder().setFeatures(new Feature[]{zbar.zbc}).run(new zbw(this, authorizationRequest1)).setAutoResolveMissingFeatures(false).setMethodKey(0x5FE).build());
    }

    @Override  // com.google.android.gms.auth.api.identity.AuthorizationClient
    public final AuthorizationResult getAuthorizationResultFromIntent(Intent intent0) throws ApiException {
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
        AuthorizationResult authorizationResult0 = (AuthorizationResult)SafeParcelableSerializer.deserializeFromIntentExtra(intent0, "authorization_result", AuthorizationResult.CREATOR);
        if(authorizationResult0 == null) {
            throw new ApiException(Status.RESULT_INTERNAL_ERROR);
        }
        return authorizationResult0;
    }
}

