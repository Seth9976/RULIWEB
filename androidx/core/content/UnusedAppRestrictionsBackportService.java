package androidx.core.content;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportCallback;
import androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportService.Stub;

public abstract class UnusedAppRestrictionsBackportService extends Service {
    public static final String ACTION_UNUSED_APP_RESTRICTIONS_BACKPORT_CONNECTION = "android.support.unusedapprestrictions.action.CustomUnusedAppRestrictionsBackportService";
    private Stub mBinder;

    public UnusedAppRestrictionsBackportService() {
        this.mBinder = new Stub() {
            @Override  // androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportService
            public void isPermissionRevocationEnabledForApp(IUnusedAppRestrictionsBackportCallback iUnusedAppRestrictionsBackportCallback0) throws RemoteException {
                if(iUnusedAppRestrictionsBackportCallback0 == null) {
                    return;
                }
                UnusedAppRestrictionsBackportCallback unusedAppRestrictionsBackportCallback0 = new UnusedAppRestrictionsBackportCallback(iUnusedAppRestrictionsBackportCallback0);
                UnusedAppRestrictionsBackportService.this.isPermissionRevocationEnabled(unusedAppRestrictionsBackportCallback0);
            }
        };
    }

    protected abstract void isPermissionRevocationEnabled(UnusedAppRestrictionsBackportCallback arg1);

    @Override  // android.app.Service
    public IBinder onBind(Intent intent0) {
        return this.mBinder;
    }
}

