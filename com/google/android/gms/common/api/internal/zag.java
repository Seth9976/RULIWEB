package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zag extends zac {
    private final TaskApiCall zaa;
    private final TaskCompletionSource zab;
    private final StatusExceptionMapper zad;

    public zag(int v, TaskApiCall taskApiCall0, TaskCompletionSource taskCompletionSource0, StatusExceptionMapper statusExceptionMapper0) {
        super(v);
        this.zab = taskCompletionSource0;
        this.zaa = taskApiCall0;
        this.zad = statusExceptionMapper0;
        if(v == 2 && taskApiCall0.shouldAutoResolveMissingFeatures()) {
            throw new IllegalArgumentException("Best-effort write calls cannot pass methods that should auto-resolve missing features.");
        }
    }

    @Override  // com.google.android.gms.common.api.internal.zac
    public final boolean zaa(zabq zabq0) {
        return this.zaa.shouldAutoResolveMissingFeatures();
    }

    @Override  // com.google.android.gms.common.api.internal.zac
    public final Feature[] zab(zabq zabq0) {
        return this.zaa.zab();
    }

    @Override  // com.google.android.gms.common.api.internal.zai
    public final void zad(Status status0) {
        Exception exception0 = this.zad.getException(status0);
        this.zab.trySetException(exception0);
    }

    @Override  // com.google.android.gms.common.api.internal.zai
    public final void zae(Exception exception0) {
        this.zab.trySetException(exception0);
    }

    @Override  // com.google.android.gms.common.api.internal.zai
    public final void zaf(zabq zabq0) throws DeadObjectException {
        try {
            this.zaa.doExecute(zabq0.zaf(), this.zab);
        }
        catch(DeadObjectException deadObjectException0) {
            throw deadObjectException0;
        }
        catch(RemoteException remoteException0) {
            this.zad(zai.zah(remoteException0));
        }
        catch(RuntimeException runtimeException0) {
            this.zab.trySetException(runtimeException0);
        }
    }

    @Override  // com.google.android.gms.common.api.internal.zai
    public final void zag(zaad zaad0, boolean z) {
        zaad0.zad(this.zab, z);
    }
}

