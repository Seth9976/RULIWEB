package com.google.android.gms.auth.api.identity;

import android.content.Intent;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.HasApiKey;
import com.google.android.gms.tasks.Task;

public interface AuthorizationClient extends HasApiKey {
    Task authorize(AuthorizationRequest arg1);

    AuthorizationResult getAuthorizationResultFromIntent(Intent arg1) throws ApiException;
}

