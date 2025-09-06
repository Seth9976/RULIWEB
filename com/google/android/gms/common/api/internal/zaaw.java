package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.AnyClientKey;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zab;
import com.google.android.gms.common.internal.zav;
import com.google.android.gms.signin.internal.zak;
import com.google.android.gms.signin.zae;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;

public final class zaaw implements zabf {
    private final zabi zaa;
    private final Lock zab;
    private final Context zac;
    private final GoogleApiAvailabilityLight zad;
    private ConnectionResult zae;
    private int zaf;
    private int zag;
    private int zah;
    private final Bundle zai;
    private final Set zaj;
    private zae zak;
    private boolean zal;
    private boolean zam;
    private boolean zan;
    private IAccountAccessor zao;
    private boolean zap;
    private boolean zaq;
    private final ClientSettings zar;
    private final Map zas;
    private final AbstractClientBuilder zat;
    private final ArrayList zau;

    public zaaw(zabi zabi0, ClientSettings clientSettings0, Map map0, GoogleApiAvailabilityLight googleApiAvailabilityLight0, AbstractClientBuilder api$AbstractClientBuilder0, Lock lock0, Context context0) {
        this.zag = 0;
        this.zai = new Bundle();
        this.zaj = new HashSet();
        this.zau = new ArrayList();
        this.zaa = zabi0;
        this.zar = clientSettings0;
        this.zas = map0;
        this.zad = googleApiAvailabilityLight0;
        this.zat = api$AbstractClientBuilder0;
        this.zab = lock0;
        this.zac = context0;
    }

    private final void zaA() {
        this.zam = false;
        this.zaa.zag.zad = Collections.EMPTY_SET;
        for(Object object0: this.zaj) {
            AnyClientKey api$AnyClientKey0 = (AnyClientKey)object0;
            if(!this.zaa.zab.containsKey(api$AnyClientKey0)) {
                ConnectionResult connectionResult0 = new ConnectionResult(17, null);
                this.zaa.zab.put(api$AnyClientKey0, connectionResult0);
            }
        }
    }

    private final void zaB(boolean z) {
        zae zae0 = this.zak;
        if(zae0 != null) {
            if(zae0.isConnected() && z) {
                zae0.zaa();
            }
            zae0.disconnect();
            ClientSettings clientSettings0 = (ClientSettings)Preconditions.checkNotNull(this.zar);
            this.zao = null;
        }
    }

    private final void zaC() {
        this.zaa.zai();
        zabj.zaa().execute(new zaak(this));
        zae zae0 = this.zak;
        if(zae0 != null) {
            if(this.zap) {
                zae0.zac(((IAccountAccessor)Preconditions.checkNotNull(this.zao)), this.zaq);
            }
            this.zaB(false);
        }
        for(Object object0: this.zaa.zab.keySet()) {
            ((Client)Preconditions.checkNotNull(((Client)this.zaa.zaa.get(((AnyClientKey)object0))))).disconnect();
        }
        this.zaa.zah.zab((this.zai.isEmpty() ? null : this.zai));
    }

    private final void zaD(ConnectionResult connectionResult0) {
        this.zaz();
        this.zaB(!connectionResult0.hasResolution());
        this.zaa.zak(connectionResult0);
        this.zaa.zah.zaa(connectionResult0);
    }

    private final void zaE(ConnectionResult connectionResult0, Api api0, boolean z) {
        if((!z || connectionResult0.hasResolution() || this.zad.getErrorResolutionIntent(connectionResult0.getErrorCode()) != null) && (this.zae == null || 0x7FFFFFFF < this.zaf)) {
            this.zae = connectionResult0;
            this.zaf = 0x7FFFFFFF;
        }
        this.zaa.zab.put(api0.zab(), connectionResult0);
    }

    private final void zaF() {
        if(this.zah == 0 && (!this.zam || this.zan)) {
            ArrayList arrayList0 = new ArrayList();
            this.zag = 1;
            this.zah = this.zaa.zaa.size();
            for(Object object0: this.zaa.zaa.keySet()) {
                AnyClientKey api$AnyClientKey0 = (AnyClientKey)object0;
                if(!this.zaa.zab.containsKey(api$AnyClientKey0)) {
                    arrayList0.add(((Client)this.zaa.zaa.get(api$AnyClientKey0)));
                }
                else if(this.zaH()) {
                    this.zaC();
                }
            }
            if(!arrayList0.isEmpty()) {
                Future future0 = zabj.zaa().submit(new zaap(this, arrayList0));
                this.zau.add(future0);
            }
        }
    }

    private final boolean zaG(int v) {
        if(this.zag != v) {
            Log.w("GACConnecting", this.zaa.zag.zaf());
            Log.w("GACConnecting", "Unexpected callback in " + this.toString());
            Log.w("GACConnecting", "mRemainingConnections=" + this.zah);
            Log.e("GACConnecting", "GoogleApiClient connecting is in step " + zaaw.zaJ(this.zag) + " but received callback for step " + zaaw.zaJ(v), new Exception());
            this.zaD(new ConnectionResult(8, null));
            return false;
        }
        return true;
    }

    private final boolean zaH() {
        int v = this.zah - 1;
        this.zah = v;
        if(v > 0) {
            return false;
        }
        if(v < 0) {
            Log.w("GACConnecting", this.zaa.zag.zaf());
            Log.wtf("GACConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", new Exception());
            this.zaD(new ConnectionResult(8, null));
            return false;
        }
        ConnectionResult connectionResult0 = this.zae;
        if(connectionResult0 != null) {
            this.zaa.zaf = this.zaf;
            this.zaD(connectionResult0);
            return false;
        }
        return true;
    }

    // 去混淆评级： 低(20)
    private final boolean zaI(ConnectionResult connectionResult0) {
        return this.zal && !connectionResult0.hasResolution();
    }

    private static final String zaJ(int v) {
        return v == 0 ? "STEP_SERVICE_BINDINGS_AND_SIGN_IN" : "STEP_GETTING_REMOTE_SERVICE";
    }

    @Override  // com.google.android.gms.common.api.internal.zabf
    public final ApiMethodImpl zaa(ApiMethodImpl baseImplementation$ApiMethodImpl0) {
        this.zaa.zag.zaa.add(baseImplementation$ApiMethodImpl0);
        return baseImplementation$ApiMethodImpl0;
    }

    @Override  // com.google.android.gms.common.api.internal.zabf
    public final ApiMethodImpl zab(ApiMethodImpl baseImplementation$ApiMethodImpl0) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }

    static Context zac(zaaw zaaw0) {
        return zaaw0.zac;
    }

    @Override  // com.google.android.gms.common.api.internal.zabf
    public final void zad() {
        this.zaa.zab.clear();
        this.zam = false;
        this.zae = null;
        this.zag = 0;
        this.zal = true;
        this.zan = false;
        this.zap = false;
        HashMap hashMap0 = new HashMap();
        for(Object object0: this.zas.keySet()) {
            Api api0 = (Api)object0;
            Client api$Client0 = (Client)Preconditions.checkNotNull(((Client)this.zaa.zaa.get(api0.zab())));
            boolean z = ((Boolean)this.zas.get(api0)).booleanValue();
            if(api$Client0.requiresSignIn()) {
                this.zam = true;
                if(z) {
                    this.zaj.add(api0.zab());
                }
                else {
                    this.zal = false;
                }
            }
            hashMap0.put(api$Client0, new zaal(this, api0, z));
        }
        if(this.zam) {
            Preconditions.checkNotNull(this.zar);
            Preconditions.checkNotNull(this.zat);
            this.zar.zae(System.identityHashCode(this.zaa.zag));
            zaat zaat0 = new zaat(this, null);
            Looper looper0 = this.zaa.zag.getLooper();
            this.zak = this.zat.buildClient(this.zac, looper0, this.zar, this.zar.zaa(), zaat0, zaat0);
        }
        this.zah = this.zaa.zaa.size();
        Future future0 = zabj.zaa().submit(new zaao(this, hashMap0));
        this.zau.add(future0);
    }

    @Override  // com.google.android.gms.common.api.internal.zabf
    public final void zae() {
    }

    static GoogleApiAvailabilityLight zaf(zaaw zaaw0) {
        return zaaw0.zad;
    }

    @Override  // com.google.android.gms.common.api.internal.zabf
    public final void zag(Bundle bundle0) {
        if(this.zaG(1)) {
            if(bundle0 != null) {
                this.zai.putAll(bundle0);
            }
            if(this.zaH()) {
                this.zaC();
            }
        }
    }

    @Override  // com.google.android.gms.common.api.internal.zabf
    public final void zah(ConnectionResult connectionResult0, Api api0, boolean z) {
        if(this.zaG(1)) {
            this.zaE(connectionResult0, api0, z);
            if(this.zaH()) {
                this.zaC();
            }
        }
    }

    @Override  // com.google.android.gms.common.api.internal.zabf
    public final void zai(int v) {
        this.zaD(new ConnectionResult(8, null));
    }

    @Override  // com.google.android.gms.common.api.internal.zabf
    public final boolean zaj() {
        this.zaz();
        this.zaB(true);
        this.zaa.zak(null);
        return true;
    }

    static zabi zak(zaaw zaaw0) {
        return zaaw0.zaa;
    }

    static ClientSettings zal(zaaw zaaw0) {
        return zaaw0.zar;
    }

    static IAccountAccessor zam(zaaw zaaw0) {
        return zaaw0.zao;
    }

    static zae zan(zaaw zaaw0) {
        return zaaw0.zak;
    }

    static Set zao(zaaw zaaw0) {
        ClientSettings clientSettings0 = zaaw0.zar;
        if(clientSettings0 == null) {
            return Collections.EMPTY_SET;
        }
        Set set0 = new HashSet(clientSettings0.getRequiredScopes());
        Map map0 = zaaw0.zar.zad();
        for(Object object0: map0.keySet()) {
            Api api0 = (Api)object0;
            if(!zaaw0.zaa.zab.containsKey(api0.zab())) {
                set0.addAll(((zab)map0.get(api0)).zaa);
            }
        }
        return set0;
    }

    static Lock zap(zaaw zaaw0) {
        return zaaw0.zab;
    }

    static void zaq(zaaw zaaw0) {
        zaaw0.zaA();
    }

    static void zar(zaaw zaaw0, zak zak0) {
        if(!zaaw0.zaG(0)) {
            return;
        }
        ConnectionResult connectionResult0 = zak0.zaa();
        if(connectionResult0.isSuccess()) {
            zav zav0 = (zav)Preconditions.checkNotNull(zak0.zab());
            ConnectionResult connectionResult1 = zav0.zaa();
            if(!connectionResult1.isSuccess()) {
                Log.wtf("GACConnecting", "Sign-in succeeded with resolve account failure: " + connectionResult1, new Exception());
                zaaw0.zaD(connectionResult1);
                return;
            }
            zaaw0.zan = true;
            zaaw0.zao = (IAccountAccessor)Preconditions.checkNotNull(zav0.zab());
            zaaw0.zap = zav0.zac();
            zaaw0.zaq = zav0.zad();
            zaaw0.zaF();
            return;
        }
        if(zaaw0.zaI(connectionResult0)) {
            zaaw0.zaA();
            zaaw0.zaF();
            return;
        }
        zaaw0.zaD(connectionResult0);
    }

    static void zas(zaaw zaaw0, ConnectionResult connectionResult0) {
        zaaw0.zaD(connectionResult0);
    }

    static void zat(zaaw zaaw0, ConnectionResult connectionResult0, Api api0, boolean z) {
        zaaw0.zaE(connectionResult0, api0, z);
    }

    static void zau(zaaw zaaw0) {
        zaaw0.zaF();
    }

    static boolean zav(zaaw zaaw0) {
        return zaaw0.zam;
    }

    static boolean zaw(zaaw zaaw0, int v) {
        return zaaw0.zaG(0);
    }

    static boolean zax(zaaw zaaw0) {
        return zaaw0.zaH();
    }

    static boolean zay(zaaw zaaw0, ConnectionResult connectionResult0) {
        return zaaw0.zaI(connectionResult0);
    }

    private final void zaz() {
        ArrayList arrayList0 = this.zau;
        int v = arrayList0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            ((Future)arrayList0.get(v1)).cancel(true);
        }
        this.zau.clear();
    }
}

