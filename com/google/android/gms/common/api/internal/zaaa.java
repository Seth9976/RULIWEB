package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.AnyClientKey;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.base.zap;
import com.google.android.gms.internal.base.zau;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

final class zaaa implements zaca {
    private final Context zaa;
    private final zabe zab;
    private final Looper zac;
    private final zabi zad;
    private final zabi zae;
    private final Map zaf;
    private final Set zag;
    private final Client zah;
    private Bundle zai;
    private ConnectionResult zaj;
    private ConnectionResult zak;
    private boolean zal;
    private final Lock zam;
    private int zan;

    private zaaa(Context context0, zabe zabe0, Lock lock0, Looper looper0, GoogleApiAvailabilityLight googleApiAvailabilityLight0, Map map0, Map map1, ClientSettings clientSettings0, AbstractClientBuilder api$AbstractClientBuilder0, Client api$Client0, ArrayList arrayList0, ArrayList arrayList1, Map map2, Map map3) {
        this.zag = Collections.newSetFromMap(new WeakHashMap());
        this.zaj = null;
        this.zak = null;
        this.zal = false;
        this.zan = 0;
        this.zaa = context0;
        this.zab = zabe0;
        this.zam = lock0;
        this.zac = looper0;
        this.zah = api$Client0;
        this.zad = new zabi(context0, zabe0, lock0, looper0, googleApiAvailabilityLight0, map1, null, map3, null, arrayList1, new zax(this, null));
        this.zae = new zabi(context0, zabe0, lock0, looper0, googleApiAvailabilityLight0, map0, clientSettings0, map2, api$AbstractClientBuilder0, arrayList0, new zaz(this, null));
        ArrayMap arrayMap0 = new ArrayMap();
        for(Object object0: map1.keySet()) {
            arrayMap0.put(((AnyClientKey)object0), this.zad);
        }
        for(Object object1: map0.keySet()) {
            arrayMap0.put(((AnyClientKey)object1), this.zae);
        }
        this.zaf = Collections.unmodifiableMap(arrayMap0);
    }

    private final void zaA(ConnectionResult connectionResult0) {
        switch(this.zan) {
            case 1: {
                this.zaB();
                break;
            }
            case 2: {
                this.zab.zaa(connectionResult0);
                this.zaB();
                break;
            }
            default: {
                Log.wtf("CompositeGAC", "Attempted to call failure callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new Exception());
            }
        }
        this.zan = 0;
    }

    private final void zaB() {
        for(Object object0: this.zag) {
            ((SignInConnectionListener)object0).onComplete();
        }
        this.zag.clear();
    }

    private final boolean zaC() {
        return this.zak != null && this.zak.getErrorCode() == 4;
    }

    private final boolean zaD(ApiMethodImpl baseImplementation$ApiMethodImpl0) {
        AnyClientKey api$AnyClientKey0 = baseImplementation$ApiMethodImpl0.getClientKey();
        zabi zabi0 = (zabi)this.zaf.get(api$AnyClientKey0);
        Preconditions.checkNotNull(zabi0, "GoogleApiClient is not configured to use the API required for this call.");
        return zabi0.equals(this.zae);
    }

    private static boolean zaE(ConnectionResult connectionResult0) {
        return connectionResult0 != null && connectionResult0.isSuccess();
    }

    @Override  // com.google.android.gms.common.api.internal.zaca
    public final ConnectionResult zab() {
        throw new UnsupportedOperationException();
    }

    @Override  // com.google.android.gms.common.api.internal.zaca
    public final ConnectionResult zac(long v, TimeUnit timeUnit0) {
        throw new UnsupportedOperationException();
    }

    @Override  // com.google.android.gms.common.api.internal.zaca
    public final ConnectionResult zad(Api api0) {
        if(Objects.equal(this.zaf.get(api0.zab()), this.zae)) {
            return this.zaC() ? new ConnectionResult(4, this.zaz()) : this.zae.zad(api0);
        }
        return this.zad.zad(api0);
    }

    @Override  // com.google.android.gms.common.api.internal.zaca
    public final ApiMethodImpl zae(ApiMethodImpl baseImplementation$ApiMethodImpl0) {
        if(this.zaD(baseImplementation$ApiMethodImpl0)) {
            if(this.zaC()) {
                baseImplementation$ApiMethodImpl0.setFailedResult(new Status(4, null, this.zaz()));
                return baseImplementation$ApiMethodImpl0;
            }
            this.zae.zae(baseImplementation$ApiMethodImpl0);
            return baseImplementation$ApiMethodImpl0;
        }
        this.zad.zae(baseImplementation$ApiMethodImpl0);
        return baseImplementation$ApiMethodImpl0;
    }

    @Override  // com.google.android.gms.common.api.internal.zaca
    public final ApiMethodImpl zaf(ApiMethodImpl baseImplementation$ApiMethodImpl0) {
        if(this.zaD(baseImplementation$ApiMethodImpl0)) {
            if(this.zaC()) {
                baseImplementation$ApiMethodImpl0.setFailedResult(new Status(4, null, this.zaz()));
                return baseImplementation$ApiMethodImpl0;
            }
            return this.zae.zaf(baseImplementation$ApiMethodImpl0);
        }
        return this.zad.zaf(baseImplementation$ApiMethodImpl0);
    }

    public static zaaa zag(Context context0, zabe zabe0, Lock lock0, Looper looper0, GoogleApiAvailabilityLight googleApiAvailabilityLight0, Map map0, ClientSettings clientSettings0, Map map1, AbstractClientBuilder api$AbstractClientBuilder0, ArrayList arrayList0) {
        ArrayMap arrayMap0 = new ArrayMap();
        ArrayMap arrayMap1 = new ArrayMap();
        Client api$Client0 = null;
        for(Object object0: map0.entrySet()) {
            Map.Entry map$Entry0 = (Map.Entry)object0;
            Client api$Client1 = (Client)map$Entry0.getValue();
            if(api$Client1.providesSignIn()) {
                api$Client0 = api$Client1;
            }
            if(api$Client1.requiresSignIn()) {
                arrayMap0.put(((AnyClientKey)map$Entry0.getKey()), api$Client1);
            }
            else {
                arrayMap1.put(((AnyClientKey)map$Entry0.getKey()), api$Client1);
            }
        }
        Preconditions.checkState(!arrayMap0.isEmpty(), "CompositeGoogleApiClient should not be used without any APIs that require sign-in.");
        ArrayMap arrayMap2 = new ArrayMap();
        ArrayMap arrayMap3 = new ArrayMap();
        for(Object object1: map1.keySet()) {
            Api api0 = (Api)object1;
            AnyClientKey api$AnyClientKey0 = api0.zab();
            if(arrayMap0.containsKey(api$AnyClientKey0)) {
                arrayMap2.put(api0, ((Boolean)map1.get(api0)));
            }
            else {
                if(!arrayMap1.containsKey(api$AnyClientKey0)) {
                    throw new IllegalStateException("Each API in the isOptionalMap must have a corresponding client in the clients map.");
                }
                arrayMap3.put(api0, ((Boolean)map1.get(api0)));
            }
        }
        ArrayList arrayList1 = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int v = arrayList0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            zat zat0 = (zat)arrayList0.get(v1);
            if(arrayMap2.containsKey(zat0.zaa)) {
                arrayList1.add(zat0);
            }
            else {
                if(!arrayMap3.containsKey(zat0.zaa)) {
                    throw new IllegalStateException("Each ClientCallbacks must have a corresponding API in the isOptionalMap");
                }
                arrayList2.add(zat0);
            }
        }
        return new zaaa(context0, zabe0, lock0, looper0, googleApiAvailabilityLight0, arrayMap0, arrayMap1, clientSettings0, api$AbstractClientBuilder0, api$Client0, arrayList1, arrayList2, arrayMap2, arrayMap3);
    }

    static void zan(zaaa zaaa0, int v, boolean z) {
        zaaa0.zab.zac(v, z);
        zaaa0.zak = null;
        zaaa0.zaj = null;
    }

    static void zao(zaaa zaaa0, Bundle bundle0) {
        Bundle bundle1 = zaaa0.zai;
        if(bundle1 == null) {
            zaaa0.zai = bundle0;
            return;
        }
        if(bundle0 != null) {
            bundle1.putAll(bundle0);
        }
    }

    static void zap(zaaa zaaa0) {
        if(zaaa.zaE(zaaa0.zaj)) {
            if(zaaa.zaE(zaaa0.zak) || zaaa0.zaC()) {
                goto label_10;
            }
            ConnectionResult connectionResult0 = zaaa0.zak;
            if(connectionResult0 != null) {
                if(zaaa0.zan == 1) {
                    zaaa0.zaB();
                    return;
                }
                zaaa0.zaA(connectionResult0);
                zaaa0.zad.zar();
                return;
            label_10:
                switch(zaaa0.zan) {
                    case 1: {
                        zaaa0.zaB();
                        break;
                    }
                    case 2: {
                        ((zabe)Preconditions.checkNotNull(zaaa0.zab)).zab(zaaa0.zai);
                        zaaa0.zaB();
                        break;
                    }
                    default: {
                        Log.wtf("CompositeGAC", "Attempted to call success callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new AssertionError());
                    }
                }
                zaaa0.zan = 0;
            }
        }
        else {
            if(zaaa0.zaj != null && zaaa.zaE(zaaa0.zak)) {
                zaaa0.zae.zar();
                zaaa0.zaA(((ConnectionResult)Preconditions.checkNotNull(zaaa0.zaj)));
                return;
            }
            ConnectionResult connectionResult1 = zaaa0.zaj;
            if(connectionResult1 != null) {
                ConnectionResult connectionResult2 = zaaa0.zak;
                if(connectionResult2 != null) {
                    if(zaaa0.zae.zaf < zaaa0.zad.zaf) {
                        connectionResult1 = connectionResult2;
                    }
                    zaaa0.zaA(connectionResult1);
                }
            }
        }
    }

    @Override  // com.google.android.gms.common.api.internal.zaca
    public final void zaq() {
        this.zan = 2;
        this.zal = false;
        this.zak = null;
        this.zaj = null;
        this.zad.zaq();
        this.zae.zaq();
    }

    @Override  // com.google.android.gms.common.api.internal.zaca
    public final void zar() {
        this.zak = null;
        this.zaj = null;
        this.zan = 0;
        this.zad.zar();
        this.zae.zar();
        this.zaB();
    }

    @Override  // com.google.android.gms.common.api.internal.zaca
    public final void zas(String s, FileDescriptor fileDescriptor0, PrintWriter printWriter0, String[] arr_s) {
        printWriter0.append(s).append("authClient").println(":");
        this.zae.zas(s + "  ", fileDescriptor0, printWriter0, arr_s);
        printWriter0.append(s).append("anonClient").println(":");
        this.zad.zas(s + "  ", fileDescriptor0, printWriter0, arr_s);
    }

    @Override  // com.google.android.gms.common.api.internal.zaca
    public final void zat() {
        this.zad.zat();
        this.zae.zat();
    }

    @Override  // com.google.android.gms.common.api.internal.zaca
    public final void zau() {
        this.zam.lock();
        try {
            boolean z = this.zax();
            this.zae.zar();
            this.zak = new ConnectionResult(4);
            if(z) {
                new zau(this.zac).post(new zav(this));
            }
            else {
                this.zaB();
            }
        }
        finally {
            this.zam.unlock();
        }
    }

    @Override  // com.google.android.gms.common.api.internal.zaca
    public final boolean zaw() {
        boolean z = false;
        this.zam.lock();
        try {
            if(this.zad.zaw() && (this.zae.zaw() || this.zaC() || this.zan == 1)) {
                z = true;
            }
            return z;
        }
        finally {
            this.zam.unlock();
        }
    }

    @Override  // com.google.android.gms.common.api.internal.zaca
    public final boolean zax() {
        this.zam.lock();
        boolean z = this.zan == 2;
        this.zam.unlock();
        return z;
    }

    @Override  // com.google.android.gms.common.api.internal.zaca
    public final boolean zay(SignInConnectionListener signInConnectionListener0) {
        boolean z = false;
        this.zam.lock();
        try {
            if((this.zax() || this.zaw()) && !this.zae.zaw()) {
                this.zag.add(signInConnectionListener0);
                z = true;
                if(this.zan == 0) {
                    this.zan = 1;
                }
                this.zak = null;
                this.zae.zaq();
            }
            return z;
        }
        finally {
            this.zam.unlock();
        }
    }

    private final PendingIntent zaz() {
        Client api$Client0 = this.zah;
        if(api$Client0 == null) {
            return null;
        }
        Intent intent0 = api$Client0.getSignInIntent();
        return PendingIntent.getActivity(this.zaa, System.identityHashCode(this.zab), intent0, zap.zaa | 0x8000000);
    }
}

