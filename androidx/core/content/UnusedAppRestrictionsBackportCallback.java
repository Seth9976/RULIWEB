package androidx.core.content;

import android.os.RemoteException;
import androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportCallback;

public class UnusedAppRestrictionsBackportCallback {
    private IUnusedAppRestrictionsBackportCallback mCallback;

    public UnusedAppRestrictionsBackportCallback(IUnusedAppRestrictionsBackportCallback iUnusedAppRestrictionsBackportCallback0) {
        this.mCallback = iUnusedAppRestrictionsBackportCallback0;
    }

    public void onResult(boolean z, boolean z1) throws RemoteException {
        this.mCallback.onIsPermissionRevocationEnabledForAppResult(z, z1);
    }
}

