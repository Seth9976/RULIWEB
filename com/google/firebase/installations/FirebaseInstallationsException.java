package com.google.firebase.installations;

import com.google.firebase.FirebaseException;

public class FirebaseInstallationsException extends FirebaseException {
    public static enum Status {
        BAD_CONFIG,
        UNAVAILABLE,
        TOO_MANY_REQUESTS;

    }

    private final Status status;

    public FirebaseInstallationsException(Status firebaseInstallationsException$Status0) {
        this.status = firebaseInstallationsException$Status0;
    }

    public FirebaseInstallationsException(String s, Status firebaseInstallationsException$Status0) {
        super(s);
        this.status = firebaseInstallationsException$Status0;
    }

    public FirebaseInstallationsException(String s, Status firebaseInstallationsException$Status0, Throwable throwable0) {
        super(s, throwable0);
        this.status = firebaseInstallationsException$Status0;
    }

    public Status getStatus() {
        return this.status;
    }
}

