package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.internal.base.zau;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;

public final class zak implements Handler.Callback {
    final ArrayList zaa;
    @NotOnlyInitialized
    private final zaj zab;
    private final ArrayList zac;
    private final ArrayList zad;
    private volatile boolean zae;
    private final AtomicInteger zaf;
    private boolean zag;
    private final Handler zah;
    private final Object zai;

    public zak(Looper looper0, zaj zaj0) {
        this.zac = new ArrayList();
        this.zaa = new ArrayList();
        this.zad = new ArrayList();
        this.zae = false;
        this.zaf = new AtomicInteger(0);
        this.zag = false;
        this.zai = new Object();
        this.zab = zaj0;
        this.zah = new zau(looper0, this);
    }

    @Override  // android.os.Handler$Callback
    public final boolean handleMessage(Message message0) {
        if(message0.what == 1) {
            ConnectionCallbacks googleApiClient$ConnectionCallbacks0 = (ConnectionCallbacks)message0.obj;
            Object object0 = this.zai;
            synchronized(object0) {
                if(this.zae && this.zab.isConnected() && this.zac.contains(googleApiClient$ConnectionCallbacks0)) {
                    googleApiClient$ConnectionCallbacks0.onConnected(null);
                }
                return true;
            }
        }
        Log.wtf("GmsClientEvents", "Don\'t know how to handle message: " + message0.what, new Exception());
        return false;
    }

    public final void zaa() {
        this.zae = false;
        this.zaf.incrementAndGet();
    }

    public final void zab() {
        this.zae = true;
    }

    public final void zac(ConnectionResult connectionResult0) {
        Preconditions.checkHandlerThread(this.zah, "onConnectionFailure must only be called on the Handler thread");
        this.zah.removeMessages(1);
        synchronized(this.zai) {
            ArrayList arrayList0 = new ArrayList(this.zad);
            int v1 = this.zaf.get();
            for(Object object1: arrayList0) {
                OnConnectionFailedListener googleApiClient$OnConnectionFailedListener0 = (OnConnectionFailedListener)object1;
                if(this.zae && this.zaf.get() == v1) {
                    if(!this.zad.contains(googleApiClient$OnConnectionFailedListener0)) {
                        continue;
                    }
                    googleApiClient$OnConnectionFailedListener0.onConnectionFailed(connectionResult0);
                    continue;
                }
                return;
            }
        }
    }

    public final void zad(Bundle bundle0) {
        Preconditions.checkHandlerThread(this.zah, "onConnectionSuccess must only be called on the Handler thread");
        synchronized(this.zai) {
            Preconditions.checkState(!this.zag);
            this.zah.removeMessages(1);
            this.zag = true;
            Preconditions.checkState(this.zaa.isEmpty());
            ArrayList arrayList0 = new ArrayList(this.zac);
            int v1 = this.zaf.get();
            for(Object object1: arrayList0) {
                ConnectionCallbacks googleApiClient$ConnectionCallbacks0 = (ConnectionCallbacks)object1;
                if(!this.zae || !this.zab.isConnected() || this.zaf.get() != v1) {
                    break;
                }
                if(!this.zaa.contains(googleApiClient$ConnectionCallbacks0)) {
                    googleApiClient$ConnectionCallbacks0.onConnected(bundle0);
                }
            }
            this.zaa.clear();
            this.zag = false;
        }
    }

    public final void zae(int v) {
        Preconditions.checkHandlerThread(this.zah, "onUnintentionalDisconnection must only be called on the Handler thread");
        this.zah.removeMessages(1);
        synchronized(this.zai) {
            this.zag = true;
            ArrayList arrayList0 = new ArrayList(this.zac);
            int v2 = this.zaf.get();
            for(Object object1: arrayList0) {
                ConnectionCallbacks googleApiClient$ConnectionCallbacks0 = (ConnectionCallbacks)object1;
                if(!this.zae || this.zaf.get() != v2) {
                    break;
                }
                if(this.zac.contains(googleApiClient$ConnectionCallbacks0)) {
                    googleApiClient$ConnectionCallbacks0.onConnectionSuspended(v);
                }
            }
            this.zaa.clear();
            this.zag = false;
        }
    }

    public final void zaf(ConnectionCallbacks googleApiClient$ConnectionCallbacks0) {
        Preconditions.checkNotNull(googleApiClient$ConnectionCallbacks0);
        synchronized(this.zai) {
            if(this.zac.contains(googleApiClient$ConnectionCallbacks0)) {
                Log.w("GmsClientEvents", "registerConnectionCallbacks(): listener " + googleApiClient$ConnectionCallbacks0 + " is already registered");
            }
            else {
                this.zac.add(googleApiClient$ConnectionCallbacks0);
            }
        }
        if(this.zab.isConnected()) {
            Message message0 = this.zah.obtainMessage(1, googleApiClient$ConnectionCallbacks0);
            this.zah.sendMessage(message0);
        }
    }

    public final void zag(OnConnectionFailedListener googleApiClient$OnConnectionFailedListener0) {
        Preconditions.checkNotNull(googleApiClient$OnConnectionFailedListener0);
        synchronized(this.zai) {
            if(this.zad.contains(googleApiClient$OnConnectionFailedListener0)) {
                Log.w("GmsClientEvents", "registerConnectionFailedListener(): listener " + googleApiClient$OnConnectionFailedListener0 + " is already registered");
            }
            else {
                this.zad.add(googleApiClient$OnConnectionFailedListener0);
            }
        }
    }

    public final void zah(ConnectionCallbacks googleApiClient$ConnectionCallbacks0) {
        Preconditions.checkNotNull(googleApiClient$ConnectionCallbacks0);
        synchronized(this.zai) {
            if(!this.zac.remove(googleApiClient$ConnectionCallbacks0)) {
                Log.w("GmsClientEvents", "unregisterConnectionCallbacks(): listener " + googleApiClient$ConnectionCallbacks0 + " not found");
            }
            else if(this.zag) {
                this.zaa.add(googleApiClient$ConnectionCallbacks0);
            }
        }
    }

    public final void zai(OnConnectionFailedListener googleApiClient$OnConnectionFailedListener0) {
        Preconditions.checkNotNull(googleApiClient$OnConnectionFailedListener0);
        synchronized(this.zai) {
            if(!this.zad.remove(googleApiClient$OnConnectionFailedListener0)) {
                Log.w("GmsClientEvents", "unregisterConnectionFailedListener(): listener " + googleApiClient$OnConnectionFailedListener0 + " not found");
            }
        }
    }

    public final boolean zaj(ConnectionCallbacks googleApiClient$ConnectionCallbacks0) {
        Preconditions.checkNotNull(googleApiClient$ConnectionCallbacks0);
        synchronized(this.zai) {
        }
        return this.zac.contains(googleApiClient$ConnectionCallbacks0);
    }

    public final boolean zak(OnConnectionFailedListener googleApiClient$OnConnectionFailedListener0) {
        Preconditions.checkNotNull(googleApiClient$OnConnectionFailedListener0);
        synchronized(this.zai) {
        }
        return this.zad.contains(googleApiClient$OnConnectionFailedListener0);
    }
}

