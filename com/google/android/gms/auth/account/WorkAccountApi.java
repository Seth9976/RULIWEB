package com.google.android.gms.auth.account;

import android.accounts.Account;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;

@Deprecated
public interface WorkAccountApi {
    @Deprecated
    public interface AddAccountResult extends Result {
        Account getAccount();
    }

    @Deprecated
    PendingResult addWorkAccount(GoogleApiClient arg1, String arg2);

    @Deprecated
    PendingResult removeWorkAccount(GoogleApiClient arg1, Account arg2);

    @Deprecated
    void setWorkAuthenticatorEnabled(GoogleApiClient arg1, boolean arg2);

    @Deprecated
    PendingResult setWorkAuthenticatorEnabledWithResult(GoogleApiClient arg1, boolean arg2);
}

