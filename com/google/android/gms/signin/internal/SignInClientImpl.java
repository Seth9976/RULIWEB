package com.google.android.gms.signin.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.BaseGmsClient.LegacyClientCallbackAdapter;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zat;
import com.google.android.gms.signin.zae;

public class SignInClientImpl extends GmsClient implements zae {
    public static final int zaa;
    private final boolean zab;
    private final ClientSettings zac;
    private final Bundle zad;
    private final Integer zae;

    public SignInClientImpl(Context context0, Looper looper0, boolean z, ClientSettings clientSettings0, Bundle bundle0, ConnectionCallbacks googleApiClient$ConnectionCallbacks0, OnConnectionFailedListener googleApiClient$OnConnectionFailedListener0) {
        super(context0, looper0, 44, clientSettings0, googleApiClient$ConnectionCallbacks0, googleApiClient$OnConnectionFailedListener0);
        this.zab = true;
        this.zac = clientSettings0;
        this.zad = bundle0;
        this.zae = clientSettings0.zab();
    }

    public static Bundle createBundleFromClientSettings(ClientSettings clientSettings0) {
        Integer integer0 = clientSettings0.zab();
        Bundle bundle0 = new Bundle();
        bundle0.putParcelable("com.google.android.gms.signin.internal.clientRequestedAccount", clientSettings0.getAccount());
        if(integer0 != null) {
            bundle0.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", ((int)integer0));
        }
        bundle0.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", false);
        bundle0.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", false);
        bundle0.putString("com.google.android.gms.signin.internal.serverClientId", null);
        bundle0.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", true);
        bundle0.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", false);
        bundle0.putString("com.google.android.gms.signin.internal.hostedDomain", null);
        bundle0.putString("com.google.android.gms.signin.internal.logSessionId", null);
        bundle0.putBoolean("com.google.android.gms.signin.internal.waitForAccessTokenRefresh", false);
        return bundle0;
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient
    protected final IInterface createServiceInterface(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.signin.internal.ISignInService");
        return iInterface0 instanceof zaf ? ((zaf)iInterface0) : new zaf(iBinder0);
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient
    protected final Bundle getGetServiceRequestExtraArgs() {
        if(!"com.ruliweb.www.ruliapp".equals(this.zac.getRealClientPackageName())) {
            this.zad.putString("com.google.android.gms.signin.internal.realClientPackageName", this.zac.getRealClientPackageName());
        }
        return this.zad;
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api$Client
    public final int getMinApkVersion() {
        return 12451000;
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient
    protected final String getServiceDescriptor() {
        return "com.google.android.gms.signin.internal.ISignInService";
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient
    protected final String getStartServiceAction() {
        return "com.google.android.gms.signin.service.START";
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api$Client
    public final boolean requiresSignIn() {
        return this.zab;
    }

    @Override  // com.google.android.gms.signin.zae
    public final void zaa() {
        try {
            ((zaf)this.getService()).zae(((int)(((Integer)Preconditions.checkNotNull(this.zae)))));
        }
        catch(RemoteException unused_ex) {
            Log.w("SignInClientImpl", "Remote service probably died when clearAccountFromSessionStore is called");
        }
    }

    @Override  // com.google.android.gms.signin.zae
    public final void zab() {
        this.connect(new LegacyClientCallbackAdapter(this));
    }

    @Override  // com.google.android.gms.signin.zae
    public final void zac(IAccountAccessor iAccountAccessor0, boolean z) {
        try {
            ((zaf)this.getService()).zaf(iAccountAccessor0, ((int)(((Integer)Preconditions.checkNotNull(this.zae)))), z);
        }
        catch(RemoteException unused_ex) {
            Log.w("SignInClientImpl", "Remote service probably died when saveDefaultAccount is called");
        }
    }

    @Override  // com.google.android.gms.signin.zae
    public final void zad(com.google.android.gms.signin.internal.zae zae0) {
        Preconditions.checkNotNull(zae0, "Expecting a valid ISignInCallbacks");
        try {
            Account account0 = this.zac.getAccountOrDefault();
            GoogleSignInAccount googleSignInAccount0 = "<<default account>>".equals(account0.name) ? Storage.getInstance(this.getContext()).getSavedDefaultGoogleSignInAccount() : null;
            zat zat0 = new zat(account0, ((int)(((Integer)Preconditions.checkNotNull(this.zae)))), googleSignInAccount0);
            ((zaf)this.getService()).zag(new zai(1, zat0), zae0);
        }
        catch(RemoteException remoteException0) {
            Log.w("SignInClientImpl", "Remote service probably died when signIn is called");
            try {
                zae0.zab(new zak(1, new ConnectionResult(8, null), null));
            }
            catch(RemoteException unused_ex) {
                Log.wtf("SignInClientImpl", "ISignInCallbacks#onSignInComplete should be executed from the same process, unexpected RemoteException.", remoteException0);
            }
        }
    }
}

