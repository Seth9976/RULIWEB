package com.google.firebase;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;

public class FirebaseExceptionMapper implements StatusExceptionMapper {
    @Override  // com.google.android.gms.common.api.internal.StatusExceptionMapper
    public final Exception getException(Status status0) {
        return status0.getStatusCode() == 8 ? new FirebaseException(status0.zza()) : new FirebaseApiNotAvailableException(status0.zza());
    }
}

