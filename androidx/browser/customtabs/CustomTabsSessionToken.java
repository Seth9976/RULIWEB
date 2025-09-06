package androidx.browser.customtabs;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.customtabs.ICustomTabsCallback.Stub;
import android.support.customtabs.ICustomTabsCallback;
import android.util.Log;
import androidx.core.app.BundleCompat;

public class CustomTabsSessionToken {
    static class MockCallback extends Stub {
        @Override  // android.support.customtabs.ICustomTabsCallback$Stub
        public IBinder asBinder() {
            return this;
        }

        @Override  // android.support.customtabs.ICustomTabsCallback
        public void extraCallback(String s, Bundle bundle0) {
        }

        @Override  // android.support.customtabs.ICustomTabsCallback
        public Bundle extraCallbackWithResult(String s, Bundle bundle0) {
            return null;
        }

        @Override  // android.support.customtabs.ICustomTabsCallback
        public void onMessageChannelReady(Bundle bundle0) {
        }

        @Override  // android.support.customtabs.ICustomTabsCallback
        public void onNavigationEvent(int v, Bundle bundle0) {
        }

        @Override  // android.support.customtabs.ICustomTabsCallback
        public void onPostMessage(String s, Bundle bundle0) {
        }

        @Override  // android.support.customtabs.ICustomTabsCallback
        public void onRelationshipValidationResult(int v, Uri uri0, boolean z, Bundle bundle0) {
        }
    }

    private static final String TAG = "CustomTabsSessionToken";
    private final CustomTabsCallback mCallback;
    final ICustomTabsCallback mCallbackBinder;
    private final PendingIntent mSessionId;

    CustomTabsSessionToken(ICustomTabsCallback iCustomTabsCallback0, PendingIntent pendingIntent0) {
        if(iCustomTabsCallback0 == null && pendingIntent0 == null) {
            throw new IllegalStateException("CustomTabsSessionToken must have either a session id or a callback (or both).");
        }
        this.mCallbackBinder = iCustomTabsCallback0;
        this.mSessionId = pendingIntent0;
        this.mCallback = iCustomTabsCallback0 == null ? null : new CustomTabsCallback() {
            @Override  // androidx.browser.customtabs.CustomTabsCallback
            public void extraCallback(String s, Bundle bundle0) {
                try {
                    CustomTabsSessionToken.this.mCallbackBinder.extraCallback(s, bundle0);
                }
                catch(RemoteException unused_ex) {
                    Log.e("CustomTabsSessionToken", "RemoteException during ICustomTabsCallback transaction");
                }
            }

            @Override  // androidx.browser.customtabs.CustomTabsCallback
            public Bundle extraCallbackWithResult(String s, Bundle bundle0) {
                try {
                    return CustomTabsSessionToken.this.mCallbackBinder.extraCallbackWithResult(s, bundle0);
                }
                catch(RemoteException unused_ex) {
                    Log.e("CustomTabsSessionToken", "RemoteException during ICustomTabsCallback transaction");
                    return null;
                }
            }

            @Override  // androidx.browser.customtabs.CustomTabsCallback
            public void onMessageChannelReady(Bundle bundle0) {
                try {
                    CustomTabsSessionToken.this.mCallbackBinder.onMessageChannelReady(bundle0);
                }
                catch(RemoteException unused_ex) {
                    Log.e("CustomTabsSessionToken", "RemoteException during ICustomTabsCallback transaction");
                }
            }

            @Override  // androidx.browser.customtabs.CustomTabsCallback
            public void onNavigationEvent(int v, Bundle bundle0) {
                try {
                    CustomTabsSessionToken.this.mCallbackBinder.onNavigationEvent(v, bundle0);
                }
                catch(RemoteException unused_ex) {
                    Log.e("CustomTabsSessionToken", "RemoteException during ICustomTabsCallback transaction");
                }
            }

            @Override  // androidx.browser.customtabs.CustomTabsCallback
            public void onPostMessage(String s, Bundle bundle0) {
                try {
                    CustomTabsSessionToken.this.mCallbackBinder.onPostMessage(s, bundle0);
                }
                catch(RemoteException unused_ex) {
                    Log.e("CustomTabsSessionToken", "RemoteException during ICustomTabsCallback transaction");
                }
            }

            @Override  // androidx.browser.customtabs.CustomTabsCallback
            public void onRelationshipValidationResult(int v, Uri uri0, boolean z, Bundle bundle0) {
                try {
                    CustomTabsSessionToken.this.mCallbackBinder.onRelationshipValidationResult(v, uri0, z, bundle0);
                }
                catch(RemoteException unused_ex) {
                    Log.e("CustomTabsSessionToken", "RemoteException during ICustomTabsCallback transaction");
                }
            }
        };
    }

    public static CustomTabsSessionToken createMockSessionTokenForTesting() {
        return new CustomTabsSessionToken(new MockCallback(), null);
    }

    @Override
    public boolean equals(Object object0) {
        if(!(object0 instanceof CustomTabsSessionToken)) {
            return false;
        }
        PendingIntent pendingIntent0 = ((CustomTabsSessionToken)object0).getId();
        PendingIntent pendingIntent1 = this.mSessionId;
        if((pendingIntent1 == null ? 1 : 0) != (pendingIntent0 == null ? 1 : 0)) {
            return false;
        }
        return pendingIntent1 == null ? this.getCallbackBinderAssertNotNull().equals(((CustomTabsSessionToken)object0).getCallbackBinderAssertNotNull()) : pendingIntent1.equals(pendingIntent0);
    }

    public CustomTabsCallback getCallback() {
        return this.mCallback;
    }

    IBinder getCallbackBinder() {
        return this.mCallbackBinder == null ? null : this.mCallbackBinder.asBinder();
    }

    private IBinder getCallbackBinderAssertNotNull() {
        ICustomTabsCallback iCustomTabsCallback0 = this.mCallbackBinder;
        if(iCustomTabsCallback0 == null) {
            throw new IllegalStateException("CustomTabSessionToken must have valid binder or pending session");
        }
        return iCustomTabsCallback0.asBinder();
    }

    PendingIntent getId() {
        return this.mSessionId;
    }

    public static CustomTabsSessionToken getSessionTokenFromIntent(Intent intent0) {
        Bundle bundle0 = intent0.getExtras();
        ICustomTabsCallback iCustomTabsCallback0 = null;
        if(bundle0 == null) {
            return null;
        }
        IBinder iBinder0 = BundleCompat.getBinder(bundle0, "android.support.customtabs.extra.SESSION");
        PendingIntent pendingIntent0 = (PendingIntent)intent0.getParcelableExtra("android.support.customtabs.extra.SESSION_ID");
        if(iBinder0 == null && pendingIntent0 == null) {
            return null;
        }
        if(iBinder0 != null) {
            iCustomTabsCallback0 = Stub.asInterface(iBinder0);
        }
        return new CustomTabsSessionToken(iCustomTabsCallback0, pendingIntent0);
    }

    public boolean hasCallback() {
        return this.mCallbackBinder != null;
    }

    public boolean hasId() {
        return this.mSessionId != null;
    }

    @Override
    public int hashCode() {
        return this.mSessionId == null ? this.getCallbackBinderAssertNotNull().hashCode() : this.mSessionId.hashCode();
    }

    public boolean isAssociatedWith(CustomTabsSession customTabsSession0) {
        return customTabsSession0.getBinder().equals(this.mCallbackBinder);
    }
}

