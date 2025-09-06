package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;

public final class zae extends zai {
    protected final ApiMethodImpl zaa;

    public zae(int v, ApiMethodImpl baseImplementation$ApiMethodImpl0) {
        super(v);
        this.zaa = (ApiMethodImpl)Preconditions.checkNotNull(baseImplementation$ApiMethodImpl0, "Null methods are not runnable.");
    }

    @Override  // com.google.android.gms.common.api.internal.zai
    public final void zad(Status status0) {
        try {
            this.zaa.setFailedResult(status0);
        }
        catch(IllegalStateException illegalStateException0) {
            Log.w("ApiCallRunner", "Exception reporting failure", illegalStateException0);
        }
    }

    @Override  // com.google.android.gms.common.api.internal.zai
    public final void zae(Exception exception0) {
        Status status0 = new Status(10, exception0.getClass().getSimpleName() + ": " + exception0.getLocalizedMessage());
        try {
            this.zaa.setFailedResult(status0);
        }
        catch(IllegalStateException illegalStateException0) {
            Log.w("ApiCallRunner", "Exception reporting failure", illegalStateException0);
        }
    }

    @Override  // com.google.android.gms.common.api.internal.zai
    public final void zaf(zabq zabq0) throws DeadObjectException {
        try {
            this.zaa.run(zabq0.zaf());
        }
        catch(RuntimeException runtimeException0) {
            this.zae(runtimeException0);
        }
    }

    @Override  // com.google.android.gms.common.api.internal.zai
    public final void zag(zaad zaad0, boolean z) {
        zaad0.zac(this.zaa, z);
    }
}

