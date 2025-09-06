package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ApiExceptionUtil;

public class ApiExceptionMapper implements StatusExceptionMapper {
    @Override  // com.google.android.gms.common.api.internal.StatusExceptionMapper
    public final Exception getException(Status status0) {
        return ApiExceptionUtil.fromStatus(status0);
    }
}

