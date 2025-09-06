package androidx.browser.trusted;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.customtabs.trusted.ITrustedWebActivityCallback.Stub;
import android.support.customtabs.trusted.ITrustedWebActivityCallback;

public class TrustedWebActivityCallbackRemote {
    private final ITrustedWebActivityCallback mCallbackBinder;

    private TrustedWebActivityCallbackRemote(ITrustedWebActivityCallback iTrustedWebActivityCallback0) {
        this.mCallbackBinder = iTrustedWebActivityCallback0;
    }

    static TrustedWebActivityCallbackRemote fromBinder(IBinder iBinder0) {
        ITrustedWebActivityCallback iTrustedWebActivityCallback0 = iBinder0 == null ? null : Stub.asInterface(iBinder0);
        return iTrustedWebActivityCallback0 == null ? null : new TrustedWebActivityCallbackRemote(iTrustedWebActivityCallback0);
    }

    public void runExtraCallback(String s, Bundle bundle0) throws RemoteException {
        this.mCallbackBinder.onExtraCallback(s, bundle0);
    }
}

