package com.google.android.gms.auth.api.signin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.internal.zbm;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApi.Settings.Builder;
import com.google.android.gms.common.api.GoogleApi.Settings;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.tasks.Task;

@Deprecated
public class GoogleSignInClient extends GoogleApi {
    static int zba;
    private static final zba zbb;

    static {
        GoogleSignInClient.zbb = new zba(null);
        GoogleSignInClient.zba = 1;
    }

    GoogleSignInClient(Activity activity0, GoogleSignInOptions googleSignInOptions0) {
        ApiExceptionMapper apiExceptionMapper0 = new ApiExceptionMapper();
        super(activity0, Auth.GOOGLE_SIGN_IN_API, googleSignInOptions0, apiExceptionMapper0);
    }

    GoogleSignInClient(Context context0, GoogleSignInOptions googleSignInOptions0) {
        Settings googleApi$Settings0 = new Builder().setMapper(new ApiExceptionMapper()).build();
        super(context0, Auth.GOOGLE_SIGN_IN_API, googleSignInOptions0, googleApi$Settings0);
    }

    public Intent getSignInIntent() {
        Context context0 = this.getApplicationContext();
        int v = this.zba();
        if(v != 0) {
            switch(v - 1) {
                case 2: {
                    return zbm.zba(context0, ((GoogleSignInOptions)this.getApiOptions()));
                }
                case 3: {
                    return zbm.zbc(context0, ((GoogleSignInOptions)this.getApiOptions()));
                }
                default: {
                    return zbm.zbb(context0, ((GoogleSignInOptions)this.getApiOptions()));
                }
            }
        }
        throw null;
    }

    public Task revokeAccess() {
        GoogleApiClient googleApiClient0 = this.asGoogleApiClient();
        Context context0 = this.getApplicationContext();
        return this.zba() == 3 ? PendingResultUtil.toVoidTask(zbm.zbf(googleApiClient0, context0, true)) : PendingResultUtil.toVoidTask(zbm.zbf(googleApiClient0, context0, false));
    }

    public Task signOut() {
        GoogleApiClient googleApiClient0 = this.asGoogleApiClient();
        Context context0 = this.getApplicationContext();
        return this.zba() == 3 ? PendingResultUtil.toVoidTask(zbm.zbg(googleApiClient0, context0, true)) : PendingResultUtil.toVoidTask(zbm.zbg(googleApiClient0, context0, false));
    }

    public Task silentSignIn() {
        GoogleApiClient googleApiClient0 = this.asGoogleApiClient();
        Context context0 = this.getApplicationContext();
        GoogleSignInOptions googleSignInOptions0 = (GoogleSignInOptions)this.getApiOptions();
        return this.zba() == 3 ? PendingResultUtil.toTask(zbm.zbe(googleApiClient0, context0, googleSignInOptions0, true), GoogleSignInClient.zbb) : PendingResultUtil.toTask(zbm.zbe(googleApiClient0, context0, googleSignInOptions0, false), GoogleSignInClient.zbb);
    }

    private final int zba() {
        synchronized(this) {
            int v1 = GoogleSignInClient.zba;
            if(v1 == 1) {
                Context context0 = this.getApplicationContext();
                GoogleApiAvailability googleApiAvailability0 = GoogleApiAvailability.getInstance();
                int v2 = googleApiAvailability0.isGooglePlayServicesAvailable(context0, 12451000);
                if(v2 == 0) {
                    v1 = 4;
                    GoogleSignInClient.zba = 4;
                }
                else if(googleApiAvailability0.getErrorResolutionIntent(context0, v2, null) != null || DynamiteModule.getLocalVersion(context0, "com.google.android.gms.auth.api.fallback") == 0) {
                    v1 = 2;
                    GoogleSignInClient.zba = 2;
                }
                else {
                    v1 = 3;
                    GoogleSignInClient.zba = 3;
                }
            }
            return v1;
        }
    }
}

