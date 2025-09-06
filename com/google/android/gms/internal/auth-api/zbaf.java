package com.google.android.gms.internal.auth-api;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.auth.api.identity.CredentialSavingClient;
import com.google.android.gms.auth.api.identity.SaveAccountLinkingTokenRequest.Builder;
import com.google.android.gms.auth.api.identity.SaveAccountLinkingTokenRequest;
import com.google.android.gms.auth.api.identity.SavePasswordRequest;
import com.google.android.gms.auth.api.identity.zbf;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi.Settings;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.tasks.Task;

public final class zbaf extends GoogleApi implements CredentialSavingClient {
    private static final ClientKey zba;
    private static final AbstractClientBuilder zbb;
    private static final Api zbc;
    private final String zbd;

    static {
        ClientKey api$ClientKey0 = new ClientKey();
        zbaf.zba = api$ClientKey0;
        zbac zbac0 = new zbac();
        zbaf.zbb = zbac0;
        zbaf.zbc = new Api("Auth.Api.Identity.CredentialSaving.API", zbac0, api$ClientKey0);
    }

    public zbaf(Activity activity0, zbf zbf0) {
        super(activity0, zbaf.zbc, zbf0, Settings.DEFAULT_SETTINGS);
        this.zbd = "J5V3PWtoorci3K4HbwfJEQ";
    }

    public zbaf(Context context0, zbf zbf0) {
        super(context0, zbaf.zbc, zbf0, Settings.DEFAULT_SETTINGS);
        this.zbd = "KwEI0Z5QrO0T0K4hOUG7VQ";
    }

    @Override  // com.google.android.gms.auth.api.identity.CredentialSavingClient
    public final Status getStatusFromIntent(Intent intent0) {
        if(intent0 == null) {
            return Status.RESULT_INTERNAL_ERROR;
        }
        Status status0 = (Status)SafeParcelableSerializer.deserializeFromIntentExtra(intent0, "status", Status.CREATOR);
        return status0 == null ? Status.RESULT_INTERNAL_ERROR : status0;
    }

    @Override  // com.google.android.gms.auth.api.identity.CredentialSavingClient
    public final Task saveAccountLinkingToken(SaveAccountLinkingTokenRequest saveAccountLinkingTokenRequest0) {
        Preconditions.checkNotNull(saveAccountLinkingTokenRequest0);
        Builder saveAccountLinkingTokenRequest$Builder0 = SaveAccountLinkingTokenRequest.zba(saveAccountLinkingTokenRequest0);
        saveAccountLinkingTokenRequest$Builder0.zba(this.zbd);
        SaveAccountLinkingTokenRequest saveAccountLinkingTokenRequest1 = saveAccountLinkingTokenRequest$Builder0.build();
        return this.doRead(TaskApiCall.builder().setFeatures(new Feature[]{zbar.zbg}).run(new zbaa(this, saveAccountLinkingTokenRequest1)).setAutoResolveMissingFeatures(false).setMethodKey(0x5FF).build());
    }

    @Override  // com.google.android.gms.auth.api.identity.CredentialSavingClient
    public final Task savePassword(SavePasswordRequest savePasswordRequest0) {
        Preconditions.checkNotNull(savePasswordRequest0);
        com.google.android.gms.auth.api.identity.SavePasswordRequest.Builder savePasswordRequest$Builder0 = SavePasswordRequest.zba(savePasswordRequest0);
        savePasswordRequest$Builder0.zba(this.zbd);
        SavePasswordRequest savePasswordRequest1 = savePasswordRequest$Builder0.build();
        return this.doRead(TaskApiCall.builder().setFeatures(new Feature[]{zbar.zbe}).run(new zbab(this, savePasswordRequest1)).setAutoResolveMissingFeatures(false).setMethodKey(0x600).build());
    }
}

