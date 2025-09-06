package com.google.android.gms.auth.api.identity;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.auth-api.zbaf;
import com.google.android.gms.internal.auth-api.zbap;
import com.google.android.gms.internal.auth-api.zbz;

public final class Identity {
    public static AuthorizationClient getAuthorizationClient(Activity activity0) {
        return new zbz(((Activity)Preconditions.checkNotNull(activity0)), new zba(null));
    }

    public static AuthorizationClient getAuthorizationClient(Context context0) {
        return new zbz(((Context)Preconditions.checkNotNull(context0)), new zba(null));
    }

    public static CredentialSavingClient getCredentialSavingClient(Activity activity0) {
        return new zbaf(((Activity)Preconditions.checkNotNull(activity0)), new zbf());
    }

    public static CredentialSavingClient getCredentialSavingClient(Context context0) {
        return new zbaf(((Context)Preconditions.checkNotNull(context0)), new zbf());
    }

    public static SignInClient getSignInClient(Activity activity0) {
        return new zbap(((Activity)Preconditions.checkNotNull(activity0)), new zbs());
    }

    public static SignInClient getSignInClient(Context context0) {
        return new zbap(((Context)Preconditions.checkNotNull(context0)), new zbs());
    }
}

