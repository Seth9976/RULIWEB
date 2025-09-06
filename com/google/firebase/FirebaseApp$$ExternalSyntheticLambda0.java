package com.google.firebase;

import android.content.Context;
import com.google.firebase.inject.Provider;

public final class FirebaseApp..ExternalSyntheticLambda0 implements Provider {
    public final FirebaseApp f$0;
    public final Context f$1;

    public FirebaseApp..ExternalSyntheticLambda0(FirebaseApp firebaseApp0, Context context0) {
        this.f$0 = firebaseApp0;
        this.f$1 = context0;
    }

    @Override  // com.google.firebase.inject.Provider
    public final Object get() {
        return this.f$0.lambda$new$0$com-google-firebase-FirebaseApp(this.f$1);
    }
}

