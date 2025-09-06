package com.google.android.gms.common.api.internal;

import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public final class zak extends zap {
    private final SparseArray zad;

    private zak(LifecycleFragment lifecycleFragment0) {
        super(lifecycleFragment0, GoogleApiAvailability.getInstance());
        this.zad = new SparseArray();
        this.mLifecycleFragment.addCallback("AutoManageHelper", this);
    }

    @Override  // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void dump(String s, FileDescriptor fileDescriptor0, PrintWriter printWriter0, String[] arr_s) {
        for(int v = 0; v < this.zad.size(); ++v) {
            zaj zaj0 = this.zai(v);
            if(zaj0 != null) {
                printWriter0.append(s).append("GoogleApiClient #").print(zaj0.zaa);
                printWriter0.println(":");
                zaj0.zab.dump(s + "  ", fileDescriptor0, printWriter0, arr_s);
            }
        }
    }

    @Override  // com.google.android.gms.common.api.internal.zap
    public final void onStart() {
        super.onStart();
        Log.d("AutoManageHelper", "onStart " + this.zaa + " " + this.zad);
        if(this.zab.get() == null) {
            for(int v = 0; v < this.zad.size(); ++v) {
                zaj zaj0 = this.zai(v);
                if(zaj0 != null) {
                    zaj0.zab.connect();
                }
            }
        }
    }

    @Override  // com.google.android.gms.common.api.internal.zap
    public final void onStop() {
        super.onStop();
        for(int v = 0; v < this.zad.size(); ++v) {
            zaj zaj0 = this.zai(v);
            if(zaj0 != null) {
                zaj0.zab.disconnect();
            }
        }
    }

    public static zak zaa(LifecycleActivity lifecycleActivity0) {
        LifecycleFragment lifecycleFragment0 = zak.getFragment(lifecycleActivity0);
        zak zak0 = (zak)lifecycleFragment0.getCallbackOrNull("AutoManageHelper", zak.class);
        return zak0 == null ? new zak(lifecycleFragment0) : zak0;
    }

    @Override  // com.google.android.gms.common.api.internal.zap
    protected final void zab(ConnectionResult connectionResult0, int v) {
        Log.w("AutoManageHelper", "Unresolved error while connecting client. Stopping auto-manage.");
        if(v < 0) {
            Log.wtf("AutoManageHelper", "AutoManageLifecycleHelper received onErrorResolutionFailed callback but no failing client ID is set", new Exception());
            return;
        }
        zaj zaj0 = (zaj)this.zad.get(v);
        if(zaj0 != null) {
            this.zae(v);
            OnConnectionFailedListener googleApiClient$OnConnectionFailedListener0 = zaj0.zac;
            if(googleApiClient$OnConnectionFailedListener0 != null) {
                googleApiClient$OnConnectionFailedListener0.onConnectionFailed(connectionResult0);
            }
        }
    }

    @Override  // com.google.android.gms.common.api.internal.zap
    protected final void zac() {
        for(int v = 0; v < this.zad.size(); ++v) {
            zaj zaj0 = this.zai(v);
            if(zaj0 != null) {
                zaj0.zab.connect();
            }
        }
    }

    public final void zad(int v, GoogleApiClient googleApiClient0, OnConnectionFailedListener googleApiClient$OnConnectionFailedListener0) {
        Preconditions.checkNotNull(googleApiClient0, "GoogleApiClient instance cannot be null");
        Preconditions.checkState(this.zad.indexOfKey(v) < 0, "Already managing a GoogleApiClient with id " + v);
        zam zam0 = (zam)this.zab.get();
        Log.d("AutoManageHelper", "starting AutoManage for client " + v + " " + this.zaa + " " + zam0);
        zaj zaj0 = new zaj(this, v, googleApiClient0, googleApiClient$OnConnectionFailedListener0);
        googleApiClient0.registerConnectionFailedListener(zaj0);
        this.zad.put(v, zaj0);
        if(this.zaa && zam0 == null) {
            Log.d("AutoManageHelper", "connecting " + googleApiClient0.toString());
            googleApiClient0.connect();
        }
    }

    public final void zae(int v) {
        zaj zaj0 = (zaj)this.zad.get(v);
        this.zad.remove(v);
        if(zaj0 != null) {
            zaj0.zab.unregisterConnectionFailedListener(zaj0);
            zaj0.zab.disconnect();
        }
    }

    private final zaj zai(int v) {
        if(this.zad.size() <= v) {
            return null;
        }
        int v1 = this.zad.keyAt(v);
        return (zaj)this.zad.get(v1);
    }
}

