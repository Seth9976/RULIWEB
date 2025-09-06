package androidx.appcompat.app;

import android.content.Context;

public final class AppCompatDelegate..ExternalSyntheticLambda1 implements Runnable {
    public final Context f$0;

    public AppCompatDelegate..ExternalSyntheticLambda1(Context context0) {
        this.f$0 = context0;
    }

    @Override
    public final void run() {
        AppCompatDelegate.syncRequestedAndStoredLocales(this.f$0);
    }
}

