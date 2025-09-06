package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.LifecycleKt..ExternalSyntheticBackportWithForwarding0;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.internal.base.zau;
import java.util.concurrent.atomic.AtomicReference;

public abstract class zap extends LifecycleCallback implements DialogInterface.OnCancelListener {
    protected volatile boolean zaa;
    protected final AtomicReference zab;
    protected final GoogleApiAvailability zac;
    private final Handler zad;

    zap(LifecycleFragment lifecycleFragment0, GoogleApiAvailability googleApiAvailability0) {
        super(lifecycleFragment0);
        this.zab = new AtomicReference(null);
        this.zad = new zau(Looper.getMainLooper());
        this.zac = googleApiAvailability0;
    }

    @Override  // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onActivityResult(int v, int v1, Intent intent0) {
        int v2 = 13;
        zam zam0 = (zam)this.zab.get();
    alab1:
        switch(v) {
            case 1: {
                switch(v1) {
                    case -1: {
                        this.zad();
                        return;
                    }
                    case 0: {
                        if(zam0 != null) {
                            if(intent0 != null) {
                                v2 = intent0.getIntExtra("<<ResolutionFailureErrorDetail>>", 13);
                            }
                            this.zaa(new ConnectionResult(v2, null, zam0.zab().toString()), zap.zae(zam0));
                            return;
                        }
                        break alab1;
                    }
                    default: {
                        goto label_20;
                    }
                }
            }
            case 2: {
                Activity activity0 = this.getActivity();
                int v3 = this.zac.isGooglePlayServicesAvailable(activity0);
                if(v3 == 0) {
                    this.zad();
                    return;
                }
                if(zam0 == null || zam0.zab().getErrorCode() == 18 && v3 == 18) {
                    return;
                }
            label_20:
                if(zam0 != null) {
                    this.zaa(zam0.zab(), zam0.zaa());
                }
                break;
            }
            default: {
                goto label_20;
            }
        }
    }

    @Override  // android.content.DialogInterface$OnCancelListener
    public final void onCancel(DialogInterface dialogInterface0) {
        this.zaa(new ConnectionResult(13, null), zap.zae(((zam)this.zab.get())));
    }

    @Override  // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onCreate(Bundle bundle0) {
        super.onCreate(bundle0);
        if(bundle0 != null) {
            zam zam0 = bundle0.getBoolean("resolving_error", false) ? new zam(new ConnectionResult(bundle0.getInt("failed_status"), ((PendingIntent)bundle0.getParcelable("failed_resolution"))), bundle0.getInt("failed_client_id", -1)) : null;
            this.zab.set(zam0);
        }
    }

    @Override  // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onSaveInstanceState(Bundle bundle0) {
        super.onSaveInstanceState(bundle0);
        zam zam0 = (zam)this.zab.get();
        if(zam0 == null) {
            return;
        }
        bundle0.putBoolean("resolving_error", true);
        bundle0.putInt("failed_client_id", zam0.zaa());
        bundle0.putInt("failed_status", zam0.zab().getErrorCode());
        bundle0.putParcelable("failed_resolution", zam0.zab().getResolution());
    }

    @Override  // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStart() {
        super.onStart();
        this.zaa = true;
    }

    @Override  // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStop() {
        super.onStop();
        this.zaa = false;
    }

    private final void zaa(ConnectionResult connectionResult0, int v) {
        this.zab.set(null);
        this.zab(connectionResult0, v);
    }

    protected abstract void zab(ConnectionResult arg1, int arg2);

    protected abstract void zac();

    private final void zad() {
        this.zab.set(null);
        this.zac();
    }

    private static final int zae(zam zam0) {
        return zam0 == null ? -1 : zam0.zaa();
    }

    public final void zah(ConnectionResult connectionResult0, int v) {
        zam zam0 = new zam(connectionResult0, v);
        do {
            AtomicReference atomicReference0 = this.zab;
            if(LifecycleKt..ExternalSyntheticBackportWithForwarding0.m(atomicReference0, null, zam0)) {
                zao zao0 = new zao(this, zam0);
                this.zad.post(zao0);
                return;
            }
        }
        while(atomicReference0.get() == null);
    }
}

