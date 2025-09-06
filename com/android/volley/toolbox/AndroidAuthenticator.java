package com.android.volley.toolbox;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.android.volley.AuthFailureError;

public class AndroidAuthenticator implements Authenticator {
    private final Account mAccount;
    private final AccountManager mAccountManager;
    private final String mAuthTokenType;
    private final boolean mNotifyAuthFailure;

    AndroidAuthenticator(AccountManager accountManager0, Account account0, String s, boolean z) {
        this.mAccountManager = accountManager0;
        this.mAccount = account0;
        this.mAuthTokenType = s;
        this.mNotifyAuthFailure = z;
    }

    public AndroidAuthenticator(Context context0, Account account0, String s) {
        this(context0, account0, s, false);
    }

    public AndroidAuthenticator(Context context0, Account account0, String s, boolean z) {
        this(AccountManager.get(context0), account0, s, z);
    }

    public Account getAccount() {
        return this.mAccount;
    }

    @Override  // com.android.volley.toolbox.Authenticator
    public String getAuthToken() throws AuthFailureError {
        String s;
        Bundle bundle0;
        AccountManagerFuture accountManagerFuture0 = this.mAccountManager.getAuthToken(this.mAccount, this.mAuthTokenType, this.mNotifyAuthFailure, null, null);
        try {
            bundle0 = (Bundle)accountManagerFuture0.getResult();
        }
        catch(Exception exception0) {
            throw new AuthFailureError("Error while retrieving auth token", exception0);
        }
        if(!accountManagerFuture0.isDone() || accountManagerFuture0.isCancelled()) {
            s = null;
        }
        else {
            if(bundle0.containsKey("intent")) {
                throw new AuthFailureError(((Intent)bundle0.getParcelable("intent")));
            }
            s = bundle0.getString("authtoken");
        }
        if(s == null) {
            throw new AuthFailureError("Got null auth token for type: " + this.mAuthTokenType);
        }
        return s;
    }

    public String getAuthTokenType() {
        return this.mAuthTokenType;
    }

    @Override  // com.android.volley.toolbox.Authenticator
    public void invalidateAuthToken(String s) {
        this.mAccountManager.invalidateAuthToken(this.mAccount.type, s);
    }
}

