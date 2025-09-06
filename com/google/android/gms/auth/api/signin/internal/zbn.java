package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public final class zbn {
    final Storage zba;
    GoogleSignInAccount zbb;
    GoogleSignInOptions zbc;
    private static zbn zbd;

    static {
    }

    private zbn(Context context0) {
        Storage storage0 = Storage.getInstance(context0);
        this.zba = storage0;
        this.zbb = storage0.getSavedDefaultGoogleSignInAccount();
        this.zbc = storage0.getSavedDefaultGoogleSignInOptions();
    }

    public final GoogleSignInAccount zba() {
        synchronized(this) {
        }
        return this.zbb;
    }

    public final GoogleSignInOptions zbb() {
        synchronized(this) {
        }
        return this.zbc;
    }

    public static zbn zbc(Context context0) {
        synchronized(zbn.class) {
            return zbn.zbf(context0.getApplicationContext());
        }
    }

    public final void zbd() {
        synchronized(this) {
            this.zba.clear();
            this.zbb = null;
            this.zbc = null;
        }
    }

    public final void zbe(GoogleSignInOptions googleSignInOptions0, GoogleSignInAccount googleSignInAccount0) {
        synchronized(this) {
            this.zba.saveDefaultGoogleSignInAccount(googleSignInAccount0, googleSignInOptions0);
            this.zbb = googleSignInAccount0;
            this.zbc = googleSignInOptions0;
        }
    }

    private static zbn zbf(Context context0) {
        synchronized(zbn.class) {
            zbn zbn0 = zbn.zbd;
            if(zbn0 != null) {
                return zbn0;
            }
            zbn zbn1 = new zbn(context0);
            zbn.zbd = zbn1;
            return zbn1;
        }
    }
}

