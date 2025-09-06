package com.google.android.gms.common.api.internal;

import android.app.Activity;
import androidx.collection.ArraySet;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.Preconditions;

public final class zaae extends zap {
    private final ArraySet zad;
    private final GoogleApiManager zae;

    zaae(LifecycleFragment lifecycleFragment0, GoogleApiManager googleApiManager0, GoogleApiAvailability googleApiAvailability0) {
        super(lifecycleFragment0, googleApiAvailability0);
        this.zad = new ArraySet();
        this.zae = googleApiManager0;
        this.mLifecycleFragment.addCallback("ConnectionlessLifecycleHelper", this);
    }

    @Override  // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onResume() {
        super.onResume();
        this.zae();
    }

    @Override  // com.google.android.gms.common.api.internal.zap
    public final void onStart() {
        super.onStart();
        this.zae();
    }

    @Override  // com.google.android.gms.common.api.internal.zap
    public final void onStop() {
        super.onStop();
        this.zae.zaB(this);
    }

    final ArraySet zaa() {
        return this.zad;
    }

    @Override  // com.google.android.gms.common.api.internal.zap
    protected final void zab(ConnectionResult connectionResult0, int v) {
        this.zae.zax(connectionResult0, v);
    }

    @Override  // com.google.android.gms.common.api.internal.zap
    protected final void zac() {
        this.zae.zay();
    }

    public static void zad(Activity activity0, GoogleApiManager googleApiManager0, ApiKey apiKey0) {
        LifecycleFragment lifecycleFragment0 = zaae.getFragment(activity0);
        zaae zaae0 = (zaae)lifecycleFragment0.getCallbackOrNull("ConnectionlessLifecycleHelper", zaae.class);
        if(zaae0 == null) {
            zaae0 = new zaae(lifecycleFragment0, googleApiManager0, GoogleApiAvailability.getInstance());
        }
        Preconditions.checkNotNull(apiKey0, "ApiKey cannot be null");
        zaae0.zad.add(apiKey0);
        googleApiManager0.zaA(zaae0);
    }

    private final void zae() {
        if(!this.zad.isEmpty()) {
            this.zae.zaA(this);
        }
    }
}

