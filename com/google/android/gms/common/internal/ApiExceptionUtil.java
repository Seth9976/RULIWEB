package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.common.api.Status;

public class ApiExceptionUtil {
    public static ApiException fromStatus(Status status0) {
        return status0.hasResolution() ? new ResolvableApiException(status0) : new ApiException(status0);
    }
}

