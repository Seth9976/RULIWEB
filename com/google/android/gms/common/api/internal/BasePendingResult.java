package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult.StatusListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.ICancelToken;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.base.zau;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public abstract class BasePendingResult extends PendingResult {
    public static class CallbackHandler extends zau {
        public CallbackHandler() {
            super(Looper.getMainLooper());
        }

        public CallbackHandler(Looper looper0) {
            super(looper0);
        }

        @Override  // android.os.Handler
        public final void handleMessage(Message message0) {
            switch(message0.what) {
                case 1: {
                    Pair pair0 = (Pair)message0.obj;
                    ResultCallback resultCallback0 = (ResultCallback)pair0.first;
                    Result result0 = (Result)pair0.second;
                    try {
                        resultCallback0.onResult(result0);
                        return;
                    }
                    catch(RuntimeException runtimeException0) {
                        BasePendingResult.zal(result0);
                        throw runtimeException0;
                    }
                }
                case 2: {
                    ((BasePendingResult)message0.obj).forceFailureUnlessReady(Status.RESULT_TIMEOUT);
                    return;
                }
                default: {
                    Log.wtf("BasePendingResult", "Don\'t know how to handle message: " + message0.what, new Exception());
                }
            }
        }

        public final void zaa(ResultCallback resultCallback0, Result result0) {
            this.sendMessage(this.obtainMessage(1, new Pair(((ResultCallback)Preconditions.checkNotNull(resultCallback0)), result0)));
        }
    }

    private zas resultGuardian;
    static final ThreadLocal zaa;
    protected final CallbackHandler zab;
    protected final WeakReference zac;
    public static final int zad;
    private final Object zae;
    private final CountDownLatch zaf;
    private final ArrayList zag;
    private ResultCallback zah;
    private final AtomicReference zai;
    private Result zaj;
    private Status zak;
    private volatile boolean zal;
    private boolean zam;
    private boolean zan;
    private ICancelToken zao;
    private volatile zada zap;
    private boolean zaq;

    static {
        BasePendingResult.zaa = new zaq();
    }

    @Deprecated
    BasePendingResult() {
        this.zae = new Object();
        this.zaf = new CountDownLatch(1);
        this.zag = new ArrayList();
        this.zai = new AtomicReference();
        this.zaq = false;
        this.zab = new CallbackHandler(Looper.getMainLooper());
        this.zac = new WeakReference(null);
    }

    @Deprecated
    protected BasePendingResult(Looper looper0) {
        this.zae = new Object();
        this.zaf = new CountDownLatch(1);
        this.zag = new ArrayList();
        this.zai = new AtomicReference();
        this.zaq = false;
        this.zab = new CallbackHandler(looper0);
        this.zac = new WeakReference(null);
    }

    protected BasePendingResult(GoogleApiClient googleApiClient0) {
        this.zae = new Object();
        this.zaf = new CountDownLatch(1);
        this.zag = new ArrayList();
        this.zai = new AtomicReference();
        this.zaq = false;
        this.zab = new CallbackHandler((googleApiClient0 == null ? Looper.getMainLooper() : googleApiClient0.getLooper()));
        this.zac = new WeakReference(googleApiClient0);
    }

    protected BasePendingResult(CallbackHandler basePendingResult$CallbackHandler0) {
        this.zae = new Object();
        this.zaf = new CountDownLatch(1);
        this.zag = new ArrayList();
        this.zai = new AtomicReference();
        this.zaq = false;
        this.zab = (CallbackHandler)Preconditions.checkNotNull(basePendingResult$CallbackHandler0, "CallbackHandler must not be null");
        this.zac = new WeakReference(null);
    }

    @Override  // com.google.android.gms.common.api.PendingResult
    public final void addStatusListener(StatusListener pendingResult$StatusListener0) {
        Preconditions.checkArgument(pendingResult$StatusListener0 != null, "Callback cannot be null.");
        synchronized(this.zae) {
            if(this.isReady()) {
                pendingResult$StatusListener0.onComplete(this.zak);
            }
            else {
                this.zag.add(pendingResult$StatusListener0);
            }
        }
    }

    @Override  // com.google.android.gms.common.api.PendingResult
    @ResultIgnorabilityUnspecified
    public final Result await() {
        Preconditions.checkNotMainThread("await must not be called on the UI thread");
        boolean z = true;
        Preconditions.checkState(!this.zal, "Result has already been consumed");
        if(this.zap != null) {
            z = false;
        }
        Preconditions.checkState(z, "Cannot await if then() has been called.");
        try {
            this.zaf.await();
        }
        catch(InterruptedException unused_ex) {
            this.forceFailureUnlessReady(Status.RESULT_INTERRUPTED);
        }
        Preconditions.checkState(this.isReady(), "Result is not ready.");
        return this.zaa();
    }

    @Override  // com.google.android.gms.common.api.PendingResult
    @ResultIgnorabilityUnspecified
    public final Result await(long v, TimeUnit timeUnit0) {
        if(v > 0L) {
            Preconditions.checkNotMainThread("await must not be called on the UI thread when time is greater than zero.");
        }
        boolean z = true;
        Preconditions.checkState(!this.zal, "Result has already been consumed.");
        if(this.zap != null) {
            z = false;
        }
        Preconditions.checkState(z, "Cannot await if then() has been called.");
        try {
            if(!this.zaf.await(v, timeUnit0)) {
                this.forceFailureUnlessReady(Status.RESULT_TIMEOUT);
            }
        }
        catch(InterruptedException unused_ex) {
            this.forceFailureUnlessReady(Status.RESULT_INTERRUPTED);
        }
        Preconditions.checkState(this.isReady(), "Result is not ready.");
        return this.zaa();
    }

    @Override  // com.google.android.gms.common.api.PendingResult
    public void cancel() {
        synchronized(this.zae) {
            if(!this.zam && !this.zal) {
                ICancelToken iCancelToken0 = this.zao;
                if(iCancelToken0 != null) {
                    try {
                        iCancelToken0.cancel();
                    }
                    catch(RemoteException unused_ex) {
                    }
                }
                BasePendingResult.zal(this.zaj);
                this.zam = true;
                this.zab(this.createFailedResult(Status.RESULT_CANCELED));
            }
        }
    }

    protected abstract Result createFailedResult(Status arg1);

    @Deprecated
    public final void forceFailureUnlessReady(Status status0) {
        synchronized(this.zae) {
            if(!this.isReady()) {
                this.setResult(this.createFailedResult(status0));
                this.zan = true;
            }
        }
    }

    @Override  // com.google.android.gms.common.api.PendingResult
    public final boolean isCanceled() {
        synchronized(this.zae) {
        }
        return this.zam;
    }

    public final boolean isReady() {
        return this.zaf.getCount() == 0L;
    }

    protected final void setCancelToken(ICancelToken iCancelToken0) {
        synchronized(this.zae) {
            this.zao = iCancelToken0;
        }
    }

    public final void setResult(Result result0) {
        synchronized(this.zae) {
            if(!this.zan && !this.zam) {
                this.isReady();
                Preconditions.checkState(!this.isReady(), "Results have already been set");
                Preconditions.checkState(!this.zal, "Result has already been consumed");
                this.zab(result0);
                return;
            }
            BasePendingResult.zal(result0);
        }
    }

    @Override  // com.google.android.gms.common.api.PendingResult
    public final void setResultCallback(ResultCallback resultCallback0) {
        synchronized(this.zae) {
            if(resultCallback0 == null) {
                this.zah = null;
                return;
            }
            boolean z = true;
            Preconditions.checkState(!this.zal, "Result has already been consumed.");
            if(this.zap != null) {
                z = false;
            }
            Preconditions.checkState(z, "Cannot set callbacks if then() has been called.");
            if(this.isCanceled()) {
                return;
            }
            if(this.isReady()) {
                Result result0 = this.zaa();
                this.zab.zaa(resultCallback0, result0);
            }
            else {
                this.zah = resultCallback0;
            }
        }
    }

    @Override  // com.google.android.gms.common.api.PendingResult
    public final void setResultCallback(ResultCallback resultCallback0, long v, TimeUnit timeUnit0) {
        synchronized(this.zae) {
            if(resultCallback0 == null) {
                this.zah = null;
                return;
            }
            boolean z = true;
            Preconditions.checkState(!this.zal, "Result has already been consumed.");
            if(this.zap != null) {
                z = false;
            }
            Preconditions.checkState(z, "Cannot set callbacks if then() has been called.");
            if(this.isCanceled()) {
                return;
            }
            if(this.isReady()) {
                Result result0 = this.zaa();
                this.zab.zaa(resultCallback0, result0);
            }
            else {
                this.zah = resultCallback0;
                long v2 = timeUnit0.toMillis(v);
                Message message0 = this.zab.obtainMessage(2, this);
                this.zab.sendMessageDelayed(message0, v2);
            }
        }
    }

    @Override  // com.google.android.gms.common.api.PendingResult
    public final TransformedResult then(ResultTransform resultTransform0) {
        boolean z = false;
        Preconditions.checkState(!this.zal, "Result has already been consumed.");
        synchronized(this.zae) {
            Preconditions.checkState(this.zap == null, "Cannot call then() twice.");
            if(this.zah == null) {
                z = true;
            }
            Preconditions.checkState(z, "Cannot call then() if callbacks are set.");
            Preconditions.checkState(!this.zam, "Cannot call then() if result was canceled.");
            this.zaq = true;
            this.zap = new zada(this.zac);
            TransformedResult transformedResult0 = this.zap.then(resultTransform0);
            if(this.isReady()) {
                zada zada0 = this.zap;
                Result result0 = this.zaa();
                this.zab.zaa(zada0, result0);
            }
            else {
                this.zah = this.zap;
            }
            return transformedResult0;
        }
    }

    private final Result zaa() {
        Result result0;
        synchronized(this.zae) {
            Preconditions.checkState(!this.zal, "Result has already been consumed.");
            Preconditions.checkState(this.isReady(), "Result is not ready.");
            result0 = this.zaj;
            this.zaj = null;
            this.zah = null;
            this.zal = true;
        }
        zadb zadb0 = (zadb)this.zai.getAndSet(null);
        if(zadb0 != null) {
            zadb0.zaa.zab.remove(this);
        }
        return (Result)Preconditions.checkNotNull(result0);
    }

    private final void zab(Result result0) {
        this.zaj = result0;
        this.zak = result0.getStatus();
        this.zao = null;
        this.zaf.countDown();
        if(this.zam) {
            this.zah = null;
        }
        else {
            ResultCallback resultCallback0 = this.zah;
            if(resultCallback0 != null) {
                this.zab.removeMessages(2);
                Result result1 = this.zaa();
                this.zab.zaa(resultCallback0, result1);
            }
            else if(this.zaj instanceof Releasable) {
                this.resultGuardian = new zas(this, null);
            }
        }
        ArrayList arrayList0 = this.zag;
        int v = arrayList0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            ((StatusListener)arrayList0.get(v1)).onComplete(this.zak);
        }
        this.zag.clear();
    }

    public final void zak() {
        this.zaq = this.zaq || ((Boolean)BasePendingResult.zaa.get()).booleanValue();
    }

    public static void zal(Result result0) {
        if(result0 instanceof Releasable) {
            try {
                ((Releasable)result0).release();
            }
            catch(RuntimeException runtimeException0) {
                Log.w("BasePendingResult", "Unable to release " + result0, runtimeException0);
            }
        }
    }

    public final boolean zam() {
        synchronized(this.zae) {
            if(((GoogleApiClient)this.zac.get()) == null || !this.zaq) {
                this.cancel();
            }
        }
        return this.isCanceled();
    }

    public final void zan(zadb zadb0) {
        this.zai.set(zadb0);
    }
}

