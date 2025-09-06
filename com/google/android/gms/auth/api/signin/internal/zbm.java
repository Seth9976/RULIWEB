package com.google.android.gms.auth.api.signin.internal;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.OptionalPendingResultImpl;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import java.util.HashSet;

public final class zbm {
    private static final Logger zba;

    static {
        zbm.zba = new Logger("GoogleSignInCommon", new String[0]);
    }

    public static Intent zba(Context context0, GoogleSignInOptions googleSignInOptions0) {
        zbm.zba.d("getFallbackSignInIntent()", new Object[0]);
        Intent intent0 = zbm.zbc(context0, googleSignInOptions0);
        intent0.setAction("com.google.android.gms.auth.APPAUTH_SIGN_IN");
        return intent0;
    }

    public static Intent zbb(Context context0, GoogleSignInOptions googleSignInOptions0) {
        zbm.zba.d("getNoImplementationSignInIntent()", new Object[0]);
        Intent intent0 = zbm.zbc(context0, googleSignInOptions0);
        intent0.setAction("com.google.android.gms.auth.NO_IMPL");
        return intent0;
    }

    public static Intent zbc(Context context0, GoogleSignInOptions googleSignInOptions0) {
        zbm.zba.d("getSignInIntent()", new Object[0]);
        SignInConfiguration signInConfiguration0 = new SignInConfiguration("com.ruliweb.www.ruliapp", googleSignInOptions0);
        Intent intent0 = new Intent("com.google.android.gms.auth.GOOGLE_SIGN_IN");
        intent0.setPackage("com.ruliweb.www.ruliapp");
        intent0.setClass(context0, SignInHubActivity.class);
        Bundle bundle0 = new Bundle();
        bundle0.putParcelable("config", signInConfiguration0);
        intent0.putExtra("config", bundle0);
        return intent0;
    }

    public static GoogleSignInResult zbd(Intent intent0) {
        if(intent0 == null) {
            return new GoogleSignInResult(null, Status.RESULT_INTERNAL_ERROR);
        }
        Status status0 = (Status)intent0.getParcelableExtra("googleSignInStatus");
        GoogleSignInAccount googleSignInAccount0 = (GoogleSignInAccount)intent0.getParcelableExtra("googleSignInAccount");
        if(googleSignInAccount0 == null) {
            if(status0 == null) {
                status0 = Status.RESULT_INTERNAL_ERROR;
            }
            return new GoogleSignInResult(null, status0);
        }
        return new GoogleSignInResult(googleSignInAccount0, Status.RESULT_SUCCESS);
    }

    public static OptionalPendingResult zbe(GoogleApiClient googleApiClient0, Context context0, GoogleSignInOptions googleSignInOptions0, boolean z) {
        Result result0;
        Logger logger0 = zbm.zba;
        logger0.d("silentSignIn()", new Object[0]);
        logger0.d("getEligibleSavedSignInResult()", new Object[0]);
        Preconditions.checkNotNull(googleSignInOptions0);
        GoogleSignInOptions googleSignInOptions1 = zbn.zbc(context0).zbb();
        if(googleSignInOptions1 == null) {
            result0 = null;
        }
        else {
            Account account0 = googleSignInOptions1.getAccount();
            Account account1 = googleSignInOptions0.getAccount();
            if(account0 != null) {
                if(account0.equals(account1)) {
                label_13:
                    if(googleSignInOptions0.isServerAuthCodeRequested() || googleSignInOptions0.isIdTokenRequested() && (!googleSignInOptions1.isIdTokenRequested() || !Objects.equal(googleSignInOptions0.getServerClientId(), googleSignInOptions1.getServerClientId())) || !new HashSet(googleSignInOptions1.getScopes()).containsAll(new HashSet(googleSignInOptions0.getScopes()))) {
                        result0 = null;
                    }
                    else {
                        GoogleSignInAccount googleSignInAccount0 = zbn.zbc(context0).zba();
                        result0 = googleSignInAccount0 == null || googleSignInAccount0.isExpired() ? null : new GoogleSignInResult(googleSignInAccount0, Status.RESULT_SUCCESS);
                    }
                }
                else {
                    result0 = null;
                }
            }
            else if(account1 != null) {
                result0 = null;
            }
            else {
                goto label_13;
            }
        }
        if(result0 != null) {
            logger0.d("Eligible saved sign in result found", new Object[0]);
            return PendingResults.immediatePendingResult(result0, googleApiClient0);
        }
        if(z) {
            return PendingResults.immediatePendingResult(new GoogleSignInResult(null, new Status(4)), googleApiClient0);
        }
        logger0.d("trySilentSignIn()", new Object[0]);
        return new OptionalPendingResultImpl(googleApiClient0.enqueue(new zbg(googleApiClient0, context0, googleSignInOptions0)));
    }

    public static PendingResult zbf(GoogleApiClient googleApiClient0, Context context0, boolean z) {
        zbm.zba.d("Revoking access", new Object[0]);
        String s = Storage.getInstance(context0).getSavedRefreshToken();
        zbm.zbh(context0);
        return z ? zbb.zba(s) : googleApiClient0.execute(new zbk(googleApiClient0));
    }

    public static PendingResult zbg(GoogleApiClient googleApiClient0, Context context0, boolean z) {
        zbm.zba.d("Signing out", new Object[0]);
        zbm.zbh(context0);
        return z ? PendingResults.immediatePendingResult(Status.RESULT_SUCCESS, googleApiClient0) : googleApiClient0.execute(new zbi(googleApiClient0));
    }

    private static void zbh(Context context0) {
        zbn.zbc(context0).zbd();
        for(Object object0: GoogleApiClient.getAllClients()) {
            ((GoogleApiClient)object0).maybeSignOut();
        }
        GoogleApiManager.reportSignOut();
    }
}

