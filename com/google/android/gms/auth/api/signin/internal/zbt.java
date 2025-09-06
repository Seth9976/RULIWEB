package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.os.Binder;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.util.UidVerifier;

public final class zbt extends zbo {
    private final Context zba;

    public zbt(Context context0) {
        this.zba = context0;
    }

    @Override  // com.google.android.gms.auth.api.signin.internal.zbp
    public final void zbb() {
        this.zbd();
        zbn.zbc(this.zba).zbd();
    }

    @Override  // com.google.android.gms.auth.api.signin.internal.zbp
    public final void zbc() {
        this.zbd();
        Storage storage0 = Storage.getInstance(this.zba);
        GoogleSignInAccount googleSignInAccount0 = storage0.getSavedDefaultGoogleSignInAccount();
        GoogleSignInOptions googleSignInOptions0 = googleSignInAccount0 == null ? GoogleSignInOptions.DEFAULT_SIGN_IN : storage0.getSavedDefaultGoogleSignInOptions();
        GoogleSignInClient googleSignInClient0 = GoogleSignIn.getClient(this.zba, googleSignInOptions0);
        if(googleSignInAccount0 != null) {
            googleSignInClient0.revokeAccess();
            return;
        }
        googleSignInClient0.signOut();
    }

    private final void zbd() {
        int v = Binder.getCallingUid();
        if(!UidVerifier.isGooglePlayServicesUid(this.zba, v)) {
            throw new SecurityException("Calling UID " + Binder.getCallingUid() + " is not Google Play services.");
        }
    }
}

