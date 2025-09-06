package com.google.android.gms.auth.api.identity;

import android.content.Intent;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.HasApiKey;
import com.google.android.gms.tasks.Task;

public interface SignInClient extends HasApiKey {
    @Deprecated
    Task beginSignIn(BeginSignInRequest arg1);

    String getPhoneNumberFromIntent(Intent arg1) throws ApiException;

    Task getPhoneNumberHintIntent(GetPhoneNumberHintIntentRequest arg1);

    @Deprecated
    SignInCredential getSignInCredentialFromIntent(Intent arg1) throws ApiException;

    @Deprecated
    Task getSignInIntent(GetSignInIntentRequest arg1);

    @Deprecated
    Task signOut();
}

