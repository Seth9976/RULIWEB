package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.AnyClientKey;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;

public final class zabi implements zaca, zau {
    final Map zaa;
    final Map zab;
    final ClientSettings zac;
    final Map zad;
    final AbstractClientBuilder zae;
    int zaf;
    final zabe zag;
    final zabz zah;
    private final Lock zai;
    private final Condition zaj;
    private final Context zak;
    private final GoogleApiAvailabilityLight zal;
    private final zabh zam;
    @NotOnlyInitialized
    private volatile zabf zan;
    private ConnectionResult zao;

    public zabi(Context context0, zabe zabe0, Lock lock0, Looper looper0, GoogleApiAvailabilityLight googleApiAvailabilityLight0, Map map0, ClientSettings clientSettings0, Map map1, AbstractClientBuilder api$AbstractClientBuilder0, ArrayList arrayList0, zabz zabz0) {
        this.zab = new HashMap();
        this.zao = null;
        this.zak = context0;
        this.zai = lock0;
        this.zal = googleApiAvailabilityLight0;
        this.zaa = map0;
        this.zac = clientSettings0;
        this.zad = map1;
        this.zae = api$AbstractClientBuilder0;
        this.zag = zabe0;
        this.zah = zabz0;
        int v = arrayList0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            ((zat)arrayList0.get(v1)).zaa(this);
        }
        this.zam = new zabh(this, looper0);
        this.zaj = lock0.newCondition();
        this.zan = new zaax(this);
    }

    @Override  // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnected(Bundle bundle0) {
        this.zai.lock();
        try {
            this.zan.zag(bundle0);
        }
        finally {
            this.zai.unlock();
        }
    }

    @Override  // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnectionSuspended(int v) {
        this.zai.lock();
        try {
            this.zan.zai(v);
        }
        finally {
            this.zai.unlock();
        }
    }

    @Override  // com.google.android.gms.common.api.internal.zau
    public final void zaa(ConnectionResult connectionResult0, Api api0, boolean z) {
        this.zai.lock();
        try {
            this.zan.zah(connectionResult0, api0, z);
        }
        finally {
            this.zai.unlock();
        }
    }

    @Override  // com.google.android.gms.common.api.internal.zaca
    public final ConnectionResult zab() {
        this.zaq();
        while(this.zan instanceof zaaw) {
            try {
                this.zaj.await();
            }
            catch(InterruptedException unused_ex) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, null);
            }
        }
        if(this.zan instanceof zaaj) {
            return ConnectionResult.RESULT_SUCCESS;
        }
        ConnectionResult connectionResult0 = this.zao;
        return connectionResult0 == null ? new ConnectionResult(13, null) : connectionResult0;
    }

    @Override  // com.google.android.gms.common.api.internal.zaca
    public final ConnectionResult zac(long v, TimeUnit timeUnit0) {
        this.zaq();
        for(long v1 = timeUnit0.toNanos(v); this.zan instanceof zaaw; v1 = this.zaj.awaitNanos(v1)) {
            try {
                if(v1 <= 0L) {
                    this.zar();
                    return new ConnectionResult(14, null);
                }
            }
            catch(InterruptedException unused_ex) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, null);
            }
        }
        if(this.zan instanceof zaaj) {
            return ConnectionResult.RESULT_SUCCESS;
        }
        ConnectionResult connectionResult0 = this.zao;
        return connectionResult0 == null ? new ConnectionResult(13, null) : connectionResult0;
    }

    @Override  // com.google.android.gms.common.api.internal.zaca
    public final ConnectionResult zad(Api api0) {
        AnyClientKey api$AnyClientKey0 = api0.zab();
        if(this.zaa.containsKey(api$AnyClientKey0)) {
            if(((Client)this.zaa.get(api$AnyClientKey0)).isConnected()) {
                return ConnectionResult.RESULT_SUCCESS;
            }
            return this.zab.containsKey(api$AnyClientKey0) ? ((ConnectionResult)this.zab.get(api$AnyClientKey0)) : null;
        }
        return null;
    }

    @Override  // com.google.android.gms.common.api.internal.zaca
    public final ApiMethodImpl zae(ApiMethodImpl baseImplementation$ApiMethodImpl0) {
        baseImplementation$ApiMethodImpl0.zak();
        this.zan.zaa(baseImplementation$ApiMethodImpl0);
        return baseImplementation$ApiMethodImpl0;
    }

    @Override  // com.google.android.gms.common.api.internal.zaca
    public final ApiMethodImpl zaf(ApiMethodImpl baseImplementation$ApiMethodImpl0) {
        baseImplementation$ApiMethodImpl0.zak();
        return this.zan.zab(baseImplementation$ApiMethodImpl0);
    }

    static zabf zag(zabi zabi0) {
        return zabi0.zan;
    }

    static Lock zah(zabi zabi0) {
        return zabi0.zai;
    }

    final void zai() {
        this.zai.lock();
        try {
            this.zag.zak();
            this.zan = new zaaj(this);
            this.zan.zad();
            this.zaj.signalAll();
        }
        finally {
            this.zai.unlock();
        }
    }

    final void zaj() {
        this.zai.lock();
        try {
            this.zan = new zaaw(this, this.zac, this.zad, this.zal, this.zae, this.zai, this.zak);
            this.zan.zad();
            this.zaj.signalAll();
        }
        finally {
            this.zai.unlock();
        }
    }

    final void zak(ConnectionResult connectionResult0) {
        this.zai.lock();
        try {
            this.zao = connectionResult0;
            this.zan = new zaax(this);
            this.zan.zad();
            this.zaj.signalAll();
        }
        finally {
            this.zai.unlock();
        }
    }

    final void zal(zabg zabg0) {
        Message message0 = this.zam.obtainMessage(1, zabg0);
        this.zam.sendMessage(message0);
    }

    final void zam(RuntimeException runtimeException0) {
        Message message0 = this.zam.obtainMessage(2, runtimeException0);
        this.zam.sendMessage(message0);
    }

    @Override  // com.google.android.gms.common.api.internal.zaca
    public final void zaq() {
        this.zan.zae();
    }

    @Override  // com.google.android.gms.common.api.internal.zaca
    public final void zar() {
        if(this.zan.zaj()) {
            this.zab.clear();
        }
    }

    @Override  // com.google.android.gms.common.api.internal.zaca
    public final void zas(String s, FileDescriptor fileDescriptor0, PrintWriter printWriter0, String[] arr_s) {
        printWriter0.append(s).append("mState=").println(this.zan);
        for(Object object0: this.zad.keySet()) {
            printWriter0.append(s).append(((Api)object0).zad()).println(":");
            ((Client)Preconditions.checkNotNull(((Client)this.zaa.get(((Api)object0).zab())))).dump(s + "  ", fileDescriptor0, printWriter0, arr_s);
        }
    }

    @Override  // com.google.android.gms.common.api.internal.zaca
    public final void zat() {
        if(this.zan instanceof zaaj) {
            ((zaaj)this.zan).zaf();
        }
    }

    @Override  // com.google.android.gms.common.api.internal.zaca
    public final void zau() {
    }

    @Override  // com.google.android.gms.common.api.internal.zaca
    public final boolean zaw() {
        return this.zan instanceof zaaj;
    }

    @Override  // com.google.android.gms.common.api.internal.zaca
    public final boolean zax() {
        return this.zan instanceof zaaw;
    }

    @Override  // com.google.android.gms.common.api.internal.zaca
    public final boolean zay(SignInConnectionListener signInConnectionListener0) {
        return false;
    }
}

