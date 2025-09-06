package com.google.android.gms.common.api.internal;

import android.app.Activity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.CancellationException;

public final class zacc extends zap {
    private TaskCompletionSource zad;

    private zacc(LifecycleFragment lifecycleFragment0) {
        super(lifecycleFragment0, GoogleApiAvailability.getInstance());
        this.zad = new TaskCompletionSource();
        this.mLifecycleFragment.addCallback("GmsAvailabilityHelper", this);
    }

    @Override  // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onDestroy() {
        super.onDestroy();
        this.zad.trySetException(new CancellationException("Host activity was destroyed before Google Play services could be made available."));
    }

    public static zacc zaa(Activity activity0) {
        LifecycleFragment lifecycleFragment0 = zacc.getFragment(activity0);
        zacc zacc0 = (zacc)lifecycleFragment0.getCallbackOrNull("GmsAvailabilityHelper", zacc.class);
        if(zacc0 != null) {
            if(zacc0.zad.getTask().isComplete()) {
                zacc0.zad = new TaskCompletionSource();
            }
            return zacc0;
        }
        return new zacc(lifecycleFragment0);
    }

    @Override  // com.google.android.gms.common.api.internal.zap
    protected final void zab(ConnectionResult connectionResult0, int v) {
        String s = connectionResult0.getErrorMessage();
        if(s == null) {
            s = "Error connecting to Google Play services";
        }
        this.zad.setException(new ApiException(new Status(connectionResult0, s, connectionResult0.getErrorCode())));
    }

    @Override  // com.google.android.gms.common.api.internal.zap
    protected final void zac() {
        Activity activity0 = this.mLifecycleFragment.getLifecycleActivity();
        if(activity0 == null) {
            this.zad.trySetException(new ApiException(new Status(8)));
            return;
        }
        int v = this.zac.isGooglePlayServicesAvailable(activity0);
        if(v == 0) {
            this.zad.trySetResult(null);
            return;
        }
        if(!this.zad.getTask().isComplete()) {
            this.zah(new ConnectionResult(v, null), 0);
        }
    }

    public final Task zad() {
        return this.zad.getTask();
    }
}

