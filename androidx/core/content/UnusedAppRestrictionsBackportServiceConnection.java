package androidx.core.content;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import androidx.concurrent.futures.ResolvableFuture;
import androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportCallback.Stub;
import androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportCallback;
import androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportService;

class UnusedAppRestrictionsBackportServiceConnection implements ServiceConnection {
    private final Context mContext;
    private boolean mHasBoundService;
    ResolvableFuture mResultFuture;
    IUnusedAppRestrictionsBackportService mUnusedAppRestrictionsService;

    UnusedAppRestrictionsBackportServiceConnection(Context context0) {
        this.mUnusedAppRestrictionsService = null;
        this.mHasBoundService = false;
        this.mContext = context0;
    }

    public void connectAndFetchResult(ResolvableFuture resolvableFuture0) {
        if(this.mHasBoundService) {
            throw new IllegalStateException("Each UnusedAppRestrictionsBackportServiceConnection can only be bound once.");
        }
        this.mHasBoundService = true;
        this.mResultFuture = resolvableFuture0;
        Intent intent0 = new Intent("android.support.unusedapprestrictions.action.CustomUnusedAppRestrictionsBackportService").setPackage(PackageManagerCompat.getPermissionRevocationVerifierApp(this.mContext.getPackageManager()));
        this.mContext.bindService(intent0, this, 1);
    }

    public void disconnectFromService() {
        if(!this.mHasBoundService) {
            throw new IllegalStateException("bindService must be called before unbind");
        }
        this.mHasBoundService = false;
        this.mContext.unbindService(this);
    }

    private IUnusedAppRestrictionsBackportCallback getBackportCallback() {
        return new Stub() {
            @Override  // androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportCallback
            public void onIsPermissionRevocationEnabledForAppResult(boolean z, boolean z1) throws RemoteException {
                if(z) {
                    if(z1) {
                        UnusedAppRestrictionsBackportServiceConnection.this.mResultFuture.set(3);
                        return;
                    }
                    UnusedAppRestrictionsBackportServiceConnection.this.mResultFuture.set(2);
                    return;
                }
                UnusedAppRestrictionsBackportServiceConnection.this.mResultFuture.set(0);
                Log.e("PackageManagerCompat", "Unable to retrieve the permission revocation setting from the backport");
            }
        };
    }

    @Override  // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName0, IBinder iBinder0) {
        IUnusedAppRestrictionsBackportService iUnusedAppRestrictionsBackportService0 = androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportService.Stub.asInterface(iBinder0);
        this.mUnusedAppRestrictionsService = iUnusedAppRestrictionsBackportService0;
        try {
            iUnusedAppRestrictionsBackportService0.isPermissionRevocationEnabledForApp(this.getBackportCallback());
        }
        catch(RemoteException unused_ex) {
            this.mResultFuture.set(0);
        }
    }

    @Override  // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName0) {
        this.mUnusedAppRestrictionsService = null;
    }
}

