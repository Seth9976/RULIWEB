package androidx.browser.customtabs;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.customtabs.ICustomTabsCallback.Stub;
import android.support.customtabs.ICustomTabsCallback;
import android.support.customtabs.IPostMessageService;
import android.util.Log;

public abstract class PostMessageServiceConnection implements ServiceConnection, PostMessageBackend {
    private static final String TAG = "PostMessageServConn";
    private final Object mLock;
    private boolean mMessageChannelCreated;
    private String mPackageName;
    private IPostMessageService mService;
    private final ICustomTabsCallback mSessionBinder;

    public PostMessageServiceConnection(CustomTabsSessionToken customTabsSessionToken0) {
        this.mLock = new Object();
        IBinder iBinder0 = customTabsSessionToken0.getCallbackBinder();
        if(iBinder0 == null) {
            throw new IllegalArgumentException("Provided session must have binder.");
        }
        this.mSessionBinder = Stub.asInterface(iBinder0);
    }

    public boolean bindSessionToPostMessageService(Context context0) {
        String s = this.mPackageName;
        if(s == null) {
            throw new IllegalStateException("setPackageName must be called before bindSessionToPostMessageService.");
        }
        return this.bindSessionToPostMessageService(context0, s);
    }

    public boolean bindSessionToPostMessageService(Context context0, String s) {
        Intent intent0 = new Intent();
        intent0.setClassName(s, "androidx.browser.customtabs.PostMessageService");
        boolean z = context0.bindService(intent0, this, 1);
        if(!z) {
            Log.w("PostMessageServConn", "Could not bind to PostMessageService in client.");
        }
        return z;
    }

    public void cleanup(Context context0) {
        if(this.isBoundToService()) {
            this.unbindFromContext(context0);
        }
    }

    private boolean isBoundToService() {
        return this.mService != null;
    }

    public final boolean notifyMessageChannelReady(Bundle bundle0) {
        this.mMessageChannelCreated = true;
        return this.notifyMessageChannelReadyInternal(bundle0);
    }

    private boolean notifyMessageChannelReadyInternal(Bundle bundle0) {
        if(this.mService == null) {
            return false;
        }
        synchronized(this.mLock) {
            try {
                this.mService.onMessageChannelReady(this.mSessionBinder, bundle0);
                return true;
            }
            catch(RemoteException unused_ex) {
                return false;
            }
        }
    }

    @Override  // androidx.browser.customtabs.PostMessageBackend
    public void onDisconnectChannel(Context context0) {
        this.unbindFromContext(context0);
    }

    @Override  // androidx.browser.customtabs.PostMessageBackend
    public final boolean onNotifyMessageChannelReady(Bundle bundle0) {
        return this.notifyMessageChannelReady(bundle0);
    }

    @Override  // androidx.browser.customtabs.PostMessageBackend
    public final boolean onPostMessage(String s, Bundle bundle0) {
        return this.postMessage(s, bundle0);
    }

    public void onPostMessageServiceConnected() {
        if(this.mMessageChannelCreated) {
            this.notifyMessageChannelReadyInternal(null);
        }
    }

    public void onPostMessageServiceDisconnected() {
    }

    @Override  // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName0, IBinder iBinder0) {
        this.mService = android.support.customtabs.IPostMessageService.Stub.asInterface(iBinder0);
        this.onPostMessageServiceConnected();
    }

    @Override  // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName0) {
        this.mService = null;
    }

    public final boolean postMessage(String s, Bundle bundle0) {
        if(this.mService == null) {
            return false;
        }
        synchronized(this.mLock) {
            try {
                this.mService.onPostMessage(this.mSessionBinder, s, bundle0);
                return true;
            }
            catch(RemoteException unused_ex) {
                return false;
            }
        }
    }

    public void setPackageName(String s) {
        this.mPackageName = s;
    }

    public void unbindFromContext(Context context0) {
        if(this.isBoundToService()) {
            context0.unbindService(this);
            this.mService = null;
        }
    }
}

