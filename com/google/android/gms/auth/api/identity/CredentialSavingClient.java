package com.google.android.gms.auth.api.identity;

import android.content.Intent;
import com.google.android.gms.common.api.HasApiKey;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Task;

public interface CredentialSavingClient extends HasApiKey {
    Status getStatusFromIntent(Intent arg1);

    Task saveAccountLinkingToken(SaveAccountLinkingTokenRequest arg1);

    @Deprecated
    Task savePassword(SavePasswordRequest arg1);
}

