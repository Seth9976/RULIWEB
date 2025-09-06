package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.util.Log;
import androidx.loader.content.AsyncTaskLoader;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.SignInConnectionListener;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public final class zbc extends AsyncTaskLoader implements SignInConnectionListener {
    private final Semaphore zba;
    private final Set zbb;

    public zbc(Context context0, Set set0) {
        super(context0);
        this.zba = new Semaphore(0);
        this.zbb = set0;
    }

    @Override  // androidx.loader.content.AsyncTaskLoader
    public final Object loadInBackground() {
        int v = 0;
        for(Object object0: this.zbb) {
            if(((GoogleApiClient)object0).maybeSignIn(this)) {
                ++v;
            }
        }
        try {
            this.zba.tryAcquire(v, 5L, TimeUnit.SECONDS);
        }
        catch(InterruptedException interruptedException0) {
            Log.i("GACSignInLoader", "Unexpected InterruptedException", interruptedException0);
            Thread.currentThread().interrupt();
        }
        return null;
    }

    @Override  // com.google.android.gms.common.api.internal.SignInConnectionListener
    public final void onComplete() {
        this.zba.release();
    }

    @Override  // androidx.loader.content.Loader
    protected final void onStartLoading() {
        this.zba.drainPermits();
        this.forceLoad();
    }
}

