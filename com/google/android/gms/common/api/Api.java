package com.google.android.gms.common.api;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Looper;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.BaseGmsClient.ConnectionProgressReportCallbacks;
import com.google.android.gms.common.internal.BaseGmsClient.SignOutCallbacks;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class Api {
    public static abstract class AbstractClientBuilder extends BaseClientBuilder {
        @Deprecated
        public Client buildClient(Context context0, Looper looper0, ClientSettings clientSettings0, Object object0, ConnectionCallbacks googleApiClient$ConnectionCallbacks0, OnConnectionFailedListener googleApiClient$OnConnectionFailedListener0) {
            return this.buildClient(context0, looper0, clientSettings0, object0, googleApiClient$ConnectionCallbacks0, googleApiClient$OnConnectionFailedListener0);
        }

        public Client buildClient(Context context0, Looper looper0, ClientSettings clientSettings0, Object object0, com.google.android.gms.common.api.internal.ConnectionCallbacks connectionCallbacks0, com.google.android.gms.common.api.internal.OnConnectionFailedListener onConnectionFailedListener0) {
            throw new UnsupportedOperationException("buildClient must be implemented");
        }
    }

    public interface AnyClient {
    }

    public static class AnyClientKey {
    }

    public interface ApiOptions {
        public interface HasAccountOptions extends HasOptions, NotRequiredOptions {
            Account getAccount();
        }

        public interface HasGoogleSignInAccountOptions extends HasOptions {
            GoogleSignInAccount getGoogleSignInAccount();
        }

        public interface HasOptions extends ApiOptions {
        }

        public static final class NoOptions implements NotRequiredOptions {
            private NoOptions() {
            }

            NoOptions(zaa zaa0) {
            }
        }

        public interface NotRequiredOptions extends ApiOptions {
        }

        public interface Optional extends HasOptions, NotRequiredOptions {
        }

        public static final NoOptions NO_OPTIONS;

        static {
            ApiOptions.NO_OPTIONS = new NoOptions(null);
        }
    }

    public static abstract class BaseClientBuilder {
        public static final int API_PRIORITY_GAMES = 1;
        public static final int API_PRIORITY_OTHER = 0x7FFFFFFF;
        public static final int API_PRIORITY_PLUS = 2;

        public List getImpliedScopes(Object object0) {
            return Collections.EMPTY_LIST;
        }

        public int getPriority() [...] // 潜在的解密器
    }

    public interface Client extends AnyClient {
        void connect(ConnectionProgressReportCallbacks arg1);

        void disconnect();

        void disconnect(String arg1);

        void dump(String arg1, FileDescriptor arg2, PrintWriter arg3, String[] arg4);

        Feature[] getAvailableFeatures();

        String getEndpointPackageName();

        String getLastDisconnectMessage();

        int getMinApkVersion();

        void getRemoteService(IAccountAccessor arg1, Set arg2);

        Feature[] getRequiredFeatures();

        Set getScopesForConnectionlessNonSignIn();

        IBinder getServiceBrokerBinder();

        Intent getSignInIntent();

        boolean isConnected();

        boolean isConnecting();

        void onUserSignOut(SignOutCallbacks arg1);

        boolean providesSignIn();

        boolean requiresAccount();

        boolean requiresGooglePlayServices();

        boolean requiresSignIn();
    }

    public static final class ClientKey extends AnyClientKey {
    }

    private final AbstractClientBuilder zaa;
    private final ClientKey zab;
    private final String zac;

    public Api(String s, AbstractClientBuilder api$AbstractClientBuilder0, ClientKey api$ClientKey0) {
        Preconditions.checkNotNull(api$AbstractClientBuilder0, "Cannot construct an Api with a null ClientBuilder");
        Preconditions.checkNotNull(api$ClientKey0, "Cannot construct an Api with a null ClientKey");
        this.zac = s;
        this.zaa = api$AbstractClientBuilder0;
        this.zab = api$ClientKey0;
    }

    public final AbstractClientBuilder zaa() {
        return this.zaa;
    }

    public final AnyClientKey zab() {
        return this.zab;
    }

    public final BaseClientBuilder zac() {
        return this.zaa;
    }

    public final String zad() {
        return this.zac;
    }
}

