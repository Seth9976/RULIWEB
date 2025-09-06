package com.airbnb.lottie;

import android.os.Handler;
import android.os.Looper;
import com.airbnb.lottie.utils.Logger;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class LottieTask {
    class LottieFutureTask extends FutureTask {
        LottieFutureTask(Callable callable0) {
            super(callable0);
        }

        @Override
        protected void done() {
            if(!this.isCancelled()) {
                try {
                    LottieResult lottieResult0 = (LottieResult)this.get();
                    LottieTask.this.setResult(lottieResult0);
                }
                catch(InterruptedException | ExecutionException interruptedException0) {
                    LottieResult lottieResult1 = new LottieResult(interruptedException0);
                    LottieTask.this.setResult(lottieResult1);
                }
            }
        }
    }

    public static Executor EXECUTOR;
    private final Set failureListeners;
    private final Handler handler;
    private volatile LottieResult result;
    private final Set successListeners;

    static {
        LottieTask.EXECUTOR = Executors.newCachedThreadPool();
    }

    public LottieTask(Callable callable0) {
        this(callable0, false);
    }

    LottieTask(Callable callable0, boolean z) {
        this.successListeners = new LinkedHashSet(1);
        this.failureListeners = new LinkedHashSet(1);
        this.handler = new Handler(Looper.getMainLooper());
        this.result = null;
        if(z) {
            try {
                this.setResult(((LottieResult)callable0.call()));
            }
            catch(Throwable throwable0) {
                this.setResult(new LottieResult(throwable0));
            }
            return;
        }
        LottieTask.EXECUTOR.execute(new LottieFutureTask(this, callable0));
    }

    static void access$100(LottieTask lottieTask0, Object object0) {
        lottieTask0.notifySuccessListeners(object0);
    }

    public LottieTask addFailureListener(LottieListener lottieListener0) {
        synchronized(this) {
            if(this.result != null && this.result.getException() != null) {
                lottieListener0.onResult(this.result.getException());
            }
            this.failureListeners.add(lottieListener0);
            return this;
        }
    }

    public LottieTask addListener(LottieListener lottieListener0) {
        synchronized(this) {
            if(this.result != null && this.result.getValue() != null) {
                lottieListener0.onResult(this.result.getValue());
            }
            this.successListeners.add(lottieListener0);
            return this;
        }
    }

    private void notifyFailureListeners(Throwable throwable0) {
        synchronized(this) {
            ArrayList arrayList0 = new ArrayList(this.failureListeners);
            if(arrayList0.isEmpty()) {
                Logger.warning("Lottie encountered an error but no failure listener was added:", throwable0);
                return;
            }
            for(Object object0: arrayList0) {
                ((LottieListener)object0).onResult(throwable0);
            }
        }
    }

    private void notifyListeners() {
        com.airbnb.lottie.LottieTask.1 lottieTask$10 = new Runnable() {
            @Override
            public void run() {
                if(LottieTask.this.result == null) {
                    return;
                }
                LottieResult lottieResult0 = LottieTask.this.result;
                if(lottieResult0.getValue() != null) {
                    LottieTask.access$100(LottieTask.this, lottieResult0.getValue());
                    return;
                }
                LottieTask.this.notifyFailureListeners(lottieResult0.getException());
            }
        };
        this.handler.post(lottieTask$10);
    }

    private void notifySuccessListeners(Object object0) {
        synchronized(this) {
            for(Object object1: new ArrayList(this.successListeners)) {
                ((LottieListener)object1).onResult(object0);
            }
        }
    }

    public LottieTask removeFailureListener(LottieListener lottieListener0) {
        synchronized(this) {
            this.failureListeners.remove(lottieListener0);
            return this;
        }
    }

    public LottieTask removeListener(LottieListener lottieListener0) {
        synchronized(this) {
            this.successListeners.remove(lottieListener0);
            return this;
        }
    }

    private void setResult(LottieResult lottieResult0) {
        if(this.result != null) {
            throw new IllegalStateException("A task may only be set once.");
        }
        this.result = lottieResult0;
        this.notifyListeners();
    }
}

