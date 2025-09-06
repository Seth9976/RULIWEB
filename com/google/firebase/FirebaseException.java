package com.google.firebase;

import com.google.android.gms.common.internal.Preconditions;

public class FirebaseException extends Exception {
    @Deprecated
    protected FirebaseException() {
    }

    public FirebaseException(String s) {
        Preconditions.checkNotEmpty(s, "Detail message must not be empty");
        super(s);
    }

    public FirebaseException(String s, Throwable throwable0) {
        Preconditions.checkNotEmpty(s, "Detail message must not be empty");
        super(s, throwable0);
    }
}

