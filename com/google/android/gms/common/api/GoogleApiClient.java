package com.google.android.gms.common.api;

import android.accounts.Account;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import androidx.collection.ArrayMap;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.common.api.internal.LifecycleActivity;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.SignInConnectionListener;
import com.google.android.gms.common.api.internal.zabe;
import com.google.android.gms.common.api.internal.zada;
import com.google.android.gms.common.api.internal.zak;
import com.google.android.gms.common.api.internal.zat;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zab;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zad;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Deprecated
public abstract class GoogleApiClient {
    @Deprecated
    public static final class Builder {
        private Account zaa;
        private final Set zab;
        private final Set zac;
        private int zad;
        private View zae;
        private String zaf;
        private String zag;
        private final Map zah;
        private final Context zai;
        private final Map zaj;
        private LifecycleActivity zak;
        private int zal;
        private OnConnectionFailedListener zam;
        private Looper zan;
        private GoogleApiAvailability zao;
        private AbstractClientBuilder zap;
        private final ArrayList zaq;
        private final ArrayList zar;

        public Builder(Context context0) {
            this.zab = new HashSet();
            this.zac = new HashSet();
            this.zah = new ArrayMap();
            this.zaj = new ArrayMap();
            this.zal = -1;
            this.zao = GoogleApiAvailability.getInstance();
            this.zap = zad.zac;
            this.zaq = new ArrayList();
            this.zar = new ArrayList();
            this.zai = context0;
            this.zan = context0.getMainLooper();
            this.zaf = "com.ruliweb.www.ruliapp";
            this.zag = context0.getClass().getName();
        }

        public Builder(Context context0, ConnectionCallbacks googleApiClient$ConnectionCallbacks0, OnConnectionFailedListener googleApiClient$OnConnectionFailedListener0) {
            this(context0);
            Preconditions.checkNotNull(googleApiClient$ConnectionCallbacks0, "Must provide a connected listener");
            this.zaq.add(googleApiClient$ConnectionCallbacks0);
            Preconditions.checkNotNull(googleApiClient$OnConnectionFailedListener0, "Must provide a connection failed listener");
            this.zar.add(googleApiClient$OnConnectionFailedListener0);
        }

        public Builder addApi(Api api0) {
            Preconditions.checkNotNull(api0, "Api must not be null");
            this.zaj.put(api0, null);
            List list0 = ((BaseClientBuilder)Preconditions.checkNotNull(api0.zac(), "Base client builder must not be null")).getImpliedScopes(null);
            this.zac.addAll(list0);
            this.zab.addAll(list0);
            return this;
        }

        public Builder addApi(Api api0, HasOptions api$ApiOptions$HasOptions0) {
            Preconditions.checkNotNull(api0, "Api must not be null");
            Preconditions.checkNotNull(api$ApiOptions$HasOptions0, "Null options are not permitted for this Api");
            this.zaj.put(api0, api$ApiOptions$HasOptions0);
            List list0 = ((BaseClientBuilder)Preconditions.checkNotNull(api0.zac(), "Base client builder must not be null")).getImpliedScopes(api$ApiOptions$HasOptions0);
            this.zac.addAll(list0);
            this.zab.addAll(list0);
            return this;
        }

        public Builder addApiIfAvailable(Api api0, HasOptions api$ApiOptions$HasOptions0, Scope[] arr_scope) {
            Preconditions.checkNotNull(api0, "Api must not be null");
            Preconditions.checkNotNull(api$ApiOptions$HasOptions0, "Null options are not permitted for this Api");
            this.zaj.put(api0, api$ApiOptions$HasOptions0);
            this.zab(api0, api$ApiOptions$HasOptions0, arr_scope);
            return this;
        }

        public Builder addApiIfAvailable(Api api0, Scope[] arr_scope) {
            Preconditions.checkNotNull(api0, "Api must not be null");
            this.zaj.put(api0, null);
            this.zab(api0, null, arr_scope);
            return this;
        }

        public Builder addConnectionCallbacks(ConnectionCallbacks googleApiClient$ConnectionCallbacks0) {
            Preconditions.checkNotNull(googleApiClient$ConnectionCallbacks0, "Listener must not be null");
            this.zaq.add(googleApiClient$ConnectionCallbacks0);
            return this;
        }

        public Builder addOnConnectionFailedListener(OnConnectionFailedListener googleApiClient$OnConnectionFailedListener0) {
            Preconditions.checkNotNull(googleApiClient$OnConnectionFailedListener0, "Listener must not be null");
            this.zar.add(googleApiClient$OnConnectionFailedListener0);
            return this;
        }

        public Builder addScope(Scope scope0) {
            Preconditions.checkNotNull(scope0, "Scope must not be null");
            this.zab.add(scope0);
            return this;
        }

        @ResultIgnorabilityUnspecified
        public GoogleApiClient build() {
            Preconditions.checkArgument(!this.zaj.isEmpty(), "must call addApi() to add at least one API");
            ClientSettings clientSettings0 = this.zaa();
            Map map0 = clientSettings0.zad();
            ArrayMap arrayMap0 = new ArrayMap();
            ArrayMap arrayMap1 = new ArrayMap();
            ArrayList arrayList0 = new ArrayList();
            Api api0 = null;
            for(Object object0: this.zaj.keySet()) {
                Object object1 = this.zaj.get(((Api)object0));
                boolean z = map0.get(((Api)object0)) != null;
                arrayMap0.put(((Api)object0), Boolean.valueOf(z));
                zat zat0 = new zat(((Api)object0), z);
                arrayList0.add(zat0);
                Client api$Client0 = ((AbstractClientBuilder)Preconditions.checkNotNull(((Api)object0).zaa())).buildClient(this.zai, this.zan, clientSettings0, object1, zat0, zat0);
                arrayMap1.put(((Api)object0).zab(), api$Client0);
                if(api$Client0.providesSignIn()) {
                    if(api0 != null) {
                        throw new IllegalStateException(((Api)object0).zad() + " cannot be used with " + api0.zad());
                    }
                    api0 = (Api)object0;
                }
            }
            if(api0 != null) {
                Preconditions.checkState(this.zaa == null, "Must not set an account in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead", new Object[]{api0.zad()});
                Preconditions.checkState(this.zab.equals(this.zac), "Must not set scopes in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead.", new Object[]{api0.zad()});
            }
            int v = zabe.zad(arrayMap1.values(), true);
            ReentrantLock reentrantLock0 = new ReentrantLock();
            GoogleApiClient googleApiClient0 = new zabe(this.zai, reentrantLock0, this.zan, clientSettings0, this.zao, this.zap, arrayMap0, this.zaq, this.zar, arrayMap1, this.zal, v, arrayList0);
            synchronized(GoogleApiClient.zaa) {
                GoogleApiClient.zaa.add(googleApiClient0);
            }
            if(this.zal >= 0) {
                zak.zaa(this.zak).zad(this.zal, googleApiClient0, this.zam);
            }
            return googleApiClient0;
        }

        public Builder enableAutoManage(FragmentActivity fragmentActivity0, int v, OnConnectionFailedListener googleApiClient$OnConnectionFailedListener0) {
            LifecycleActivity lifecycleActivity0 = new LifecycleActivity(fragmentActivity0);
            Preconditions.checkArgument(v >= 0, "clientId must be non-negative");
            this.zal = v;
            this.zam = googleApiClient$OnConnectionFailedListener0;
            this.zak = lifecycleActivity0;
            return this;
        }

        public Builder enableAutoManage(FragmentActivity fragmentActivity0, OnConnectionFailedListener googleApiClient$OnConnectionFailedListener0) {
            this.enableAutoManage(fragmentActivity0, 0, googleApiClient$OnConnectionFailedListener0);
            return this;
        }

        public Builder setAccountName(String s) {
            this.zaa = s == null ? null : new Account(s, "com.google");
            return this;
        }

        public Builder setGravityForPopups(int v) {
            this.zad = v;
            return this;
        }

        public Builder setHandler(Handler handler0) {
            Preconditions.checkNotNull(handler0, "Handler must not be null");
            this.zan = handler0.getLooper();
            return this;
        }

        public Builder setViewForPopups(View view0) {
            Preconditions.checkNotNull(view0, "View must not be null");
            this.zae = view0;
            return this;
        }

        public Builder useDefaultAccount() {
            this.setAccountName("<<default account>>");
            return this;
        }

        public final ClientSettings zaa() {
            SignInOptions signInOptions0 = this.zaj.containsKey(zad.zag) ? ((SignInOptions)this.zaj.get(zad.zag)) : SignInOptions.zaa;
            return new ClientSettings(this.zaa, this.zab, this.zah, this.zad, this.zae, this.zaf, this.zag, signInOptions0, false);
        }

        private final void zab(Api api0, ApiOptions api$ApiOptions0, Scope[] arr_scope) {
            HashSet hashSet0 = new HashSet(((BaseClientBuilder)Preconditions.checkNotNull(api0.zac(), "Base client builder must not be null")).getImpliedScopes(api$ApiOptions0));
            for(int v = 0; v < arr_scope.length; ++v) {
                hashSet0.add(arr_scope[v]);
            }
            zab zab0 = new zab(hashSet0);
            this.zah.put(api0, zab0);
        }
    }

    @Deprecated
    public interface ConnectionCallbacks extends com.google.android.gms.common.api.internal.ConnectionCallbacks {
        public static final int CAUSE_NETWORK_LOST = 2;
        public static final int CAUSE_SERVICE_DISCONNECTED = 1;

    }

    @Deprecated
    public interface OnConnectionFailedListener extends com.google.android.gms.common.api.internal.OnConnectionFailedListener {
    }

    public static final String DEFAULT_ACCOUNT = "<<default account>>";
    public static final int SIGN_IN_MODE_OPTIONAL = 2;
    public static final int SIGN_IN_MODE_REQUIRED = 1;
    private static final Set zaa;

    static {
        GoogleApiClient.zaa = Collections.newSetFromMap(new WeakHashMap());
    }

    @ResultIgnorabilityUnspecified
    public abstract ConnectionResult blockingConnect();

    @ResultIgnorabilityUnspecified
    public abstract ConnectionResult blockingConnect(long arg1, TimeUnit arg2);

    public abstract PendingResult clearDefaultAccountAndReconnect();

    public abstract void connect();

    public void connect(int v) {
        throw new UnsupportedOperationException();
    }

    public abstract void disconnect();

    public abstract void dump(String arg1, FileDescriptor arg2, PrintWriter arg3, String[] arg4);

    public static void dumpAll(String s, FileDescriptor fileDescriptor0, PrintWriter printWriter0, String[] arr_s) {
        Set set0 = GoogleApiClient.zaa;
        synchronized(set0) {
            int v1 = 0;
            for(Object object0: set0) {
                printWriter0.append(s).append("GoogleApiClient#").println(v1);
                ((GoogleApiClient)object0).dump(s + "  ", fileDescriptor0, printWriter0, arr_s);
                ++v1;
            }
        }
    }

    @ResultIgnorabilityUnspecified
    public ApiMethodImpl enqueue(ApiMethodImpl baseImplementation$ApiMethodImpl0) {
        throw new UnsupportedOperationException();
    }

    @ResultIgnorabilityUnspecified
    public ApiMethodImpl execute(ApiMethodImpl baseImplementation$ApiMethodImpl0) {
        throw new UnsupportedOperationException();
    }

    public static Set getAllClients() {
        synchronized(GoogleApiClient.zaa) {
        }
        return GoogleApiClient.zaa;
    }

    public Client getClient(AnyClientKey api$AnyClientKey0) {
        throw new UnsupportedOperationException();
    }

    public abstract ConnectionResult getConnectionResult(Api arg1);

    public Context getContext() {
        throw new UnsupportedOperationException();
    }

    public Looper getLooper() {
        throw new UnsupportedOperationException();
    }

    public boolean hasApi(Api api0) {
        throw new UnsupportedOperationException();
    }

    public abstract boolean hasConnectedApi(Api arg1);

    public abstract boolean isConnected();

    public abstract boolean isConnecting();

    public abstract boolean isConnectionCallbacksRegistered(ConnectionCallbacks arg1);

    public abstract boolean isConnectionFailedListenerRegistered(OnConnectionFailedListener arg1);

    public boolean maybeSignIn(SignInConnectionListener signInConnectionListener0) {
        throw new UnsupportedOperationException();
    }

    public void maybeSignOut() {
        throw new UnsupportedOperationException();
    }

    public abstract void reconnect();

    public abstract void registerConnectionCallbacks(ConnectionCallbacks arg1);

    public abstract void registerConnectionFailedListener(OnConnectionFailedListener arg1);

    public ListenerHolder registerListener(Object object0) {
        throw new UnsupportedOperationException();
    }

    public abstract void stopAutoManage(FragmentActivity arg1);

    public abstract void unregisterConnectionCallbacks(ConnectionCallbacks arg1);

    public abstract void unregisterConnectionFailedListener(OnConnectionFailedListener arg1);

    public void zao(zada zada0) {
        throw new UnsupportedOperationException();
    }

    public void zap(zada zada0) {
        throw new UnsupportedOperationException();
    }
}

