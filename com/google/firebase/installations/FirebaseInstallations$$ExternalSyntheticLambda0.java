package com.google.firebase.installations;

import com.google.firebase.FirebaseApp;
import com.google.firebase.inject.Provider;

public final class FirebaseInstallations..ExternalSyntheticLambda0 implements Provider {
    public final FirebaseApp f$0;

    public FirebaseInstallations..ExternalSyntheticLambda0(FirebaseApp firebaseApp0) {
        this.f$0 = firebaseApp0;
    }

    @Override  // com.google.firebase.inject.Provider
    public final Object get() {
        return FirebaseInstallations.lambda$new$0(this.f$0);
    }
}

