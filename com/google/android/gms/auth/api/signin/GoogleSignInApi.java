package com.google.android.gms.auth.api.signin;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.PendingResult;

@Deprecated
public interface GoogleSignInApi {
    public static final String EXTRA_SIGN_IN_ACCOUNT = "signInAccount";

    Intent getSignInIntent(GoogleApiClient arg1);

    GoogleSignInResult getSignInResultFromIntent(Intent arg1);

    PendingResult revokeAccess(GoogleApiClient arg1);

    PendingResult signOut(GoogleApiClient arg1);

    OptionalPendingResult silentSignIn(GoogleApiClient arg1);
}

