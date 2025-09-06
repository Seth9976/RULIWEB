package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;

public final class zada extends TransformedResult implements ResultCallback {
    private ResultTransform zaa;
    private zada zab;
    private volatile ResultCallbacks zac;
    private PendingResult zad;
    private final Object zae;
    private Status zaf;
    private final WeakReference zag;
    private final zacz zah;
    private boolean zai;

    public zada(WeakReference weakReference0) {
        this.zaa = null;
        this.zab = null;
        this.zac = null;
        this.zad = null;
        this.zae = new Object();
        this.zaf = null;
        this.zai = false;
        Preconditions.checkNotNull(weakReference0, "GoogleApiClient reference must not be null");
        this.zag = weakReference0;
        GoogleApiClient googleApiClient0 = (GoogleApiClient)weakReference0.get();
        this.zah = new zacz(this, (googleApiClient0 == null ? Looper.getMainLooper() : googleApiClient0.getLooper()));
    }

    @Override  // com.google.android.gms.common.api.TransformedResult
    public final void andFinally(ResultCallbacks resultCallbacks0) {
        boolean z = true;
        synchronized(this.zae) {
            Preconditions.checkState(this.zac == null, "Cannot call andFinally() twice.");
            if(this.zaa != null) {
                z = false;
            }
            Preconditions.checkState(z, "Cannot call then() and andFinally() on the same TransformedResult.");
            this.zac = resultCallbacks0;
            this.zak();
        }
    }

    @Override  // com.google.android.gms.common.api.ResultCallback
    public final void onResult(Result result0) {
        synchronized(this.zae) {
            if(!result0.getStatus().isSuccess()) {
                this.zaj(result0.getStatus());
                zada.zan(result0);
            }
            else if(this.zaa != null) {
                zaco.zaa().submit(new zacy(this, result0));
            }
            else if(this.zam()) {
                ((ResultCallbacks)Preconditions.checkNotNull(this.zac)).onSuccess(result0);
            }
        }
    }

    @Override  // com.google.android.gms.common.api.TransformedResult
    public final TransformedResult then(ResultTransform resultTransform0) {
        boolean z = true;
        synchronized(this.zae) {
            Preconditions.checkState(this.zaa == null, "Cannot call then() twice.");
            if(this.zac != null) {
                z = false;
            }
            Preconditions.checkState(z, "Cannot call then() and andFinally() on the same TransformedResult.");
            this.zaa = resultTransform0;
            zada zada0 = new zada(this.zag);
            this.zab = zada0;
            this.zak();
            return zada0;
        }
    }

    static ResultTransform zaa(zada zada0) {
        return zada0.zaa;
    }

    static zacz zab(zada zada0) {
        return zada0.zah;
    }

    static zada zac(zada zada0) {
        return zada0.zab;
    }

    static Object zad(zada zada0) {
        return zada0.zae;
    }

    static WeakReference zae(zada zada0) {
        return zada0.zag;
    }

    static void zaf(zada zada0, Result result0) {
        zada.zan(result0);
    }

    static void zag(zada zada0, Status status0) {
        zada0.zaj(status0);
    }

    final void zah() {
        this.zac = null;
    }

    public final void zai(PendingResult pendingResult0) {
        synchronized(this.zae) {
            this.zad = pendingResult0;
            this.zak();
        }
    }

    private final void zaj(Status status0) {
        synchronized(this.zae) {
            this.zaf = status0;
            this.zal(status0);
        }
    }

    private final void zak() {
        if(this.zaa != null || this.zac != null) {
            GoogleApiClient googleApiClient0 = (GoogleApiClient)this.zag.get();
            if(!this.zai && this.zaa != null && googleApiClient0 != null) {
                googleApiClient0.zao(this);
                this.zai = true;
            }
            Status status0 = this.zaf;
            if(status0 != null) {
                this.zal(status0);
                return;
            }
            PendingResult pendingResult0 = this.zad;
            if(pendingResult0 != null) {
                pendingResult0.setResultCallback(this);
            }
        }
    }

    private final void zal(Status status0) {
        synchronized(this.zae) {
            if(this.zaa != null) {
                Status status1 = (Status)Preconditions.checkNotNull(status0, "onFailure must not return null");
                ((zada)Preconditions.checkNotNull(this.zab)).zaj(status1);
            }
            else if(this.zam()) {
                ((ResultCallbacks)Preconditions.checkNotNull(this.zac)).onFailure(status0);
            }
        }
    }

    private final boolean zam() {
        GoogleApiClient googleApiClient0 = (GoogleApiClient)this.zag.get();
        return this.zac != null && googleApiClient0 != null;
    }

    private static final void zan(Result result0) {
        if(result0 instanceof Releasable) {
            try {
                ((Releasable)result0).release();
            }
            catch(RuntimeException runtimeException0) {
                Log.w("TransformedResultImpl", "Unable to release " + result0, runtimeException0);
            }
        }
    }
}

