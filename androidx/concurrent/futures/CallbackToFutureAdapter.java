package androidx.concurrent.futures;

import com.google.common.util.concurrent.ListenableFuture;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class CallbackToFutureAdapter {
    public static final class Completer {
        private boolean attemptedSetting;
        private ResolvableFuture cancellationFuture;
        SafeFuture future;
        Object tag;

        Completer() {
            this.cancellationFuture = ResolvableFuture.create();
        }

        public void addCancellationListener(Runnable runnable0, Executor executor0) {
            ResolvableFuture resolvableFuture0 = this.cancellationFuture;
            if(resolvableFuture0 != null) {
                resolvableFuture0.addListener(runnable0, executor0);
            }
        }

        @Override
        protected void finalize() {
            SafeFuture callbackToFutureAdapter$SafeFuture0 = this.future;
            if(callbackToFutureAdapter$SafeFuture0 != null && !callbackToFutureAdapter$SafeFuture0.isDone()) {
                callbackToFutureAdapter$SafeFuture0.setException(new FutureGarbageCollectedException("The completer object was garbage collected - this future would otherwise never complete. The tag was: " + this.tag));
            }
            if(!this.attemptedSetting) {
                ResolvableFuture resolvableFuture0 = this.cancellationFuture;
                if(resolvableFuture0 != null) {
                    resolvableFuture0.set(null);
                }
            }
        }

        void fireCancellationListeners() {
            this.tag = null;
            this.future = null;
            this.cancellationFuture.set(null);
        }

        public boolean set(Object object0) {
            this.attemptedSetting = true;
            if(this.future == null || !this.future.set(object0)) {
                return false;
            }
            this.setCompletedNormally();
            return true;
        }

        public boolean setCancelled() {
            this.attemptedSetting = true;
            if(this.future == null || !this.future.cancelWithoutNotifyingCompleter(true)) {
                return false;
            }
            this.setCompletedNormally();
            return true;
        }

        private void setCompletedNormally() {
            this.tag = null;
            this.future = null;
            this.cancellationFuture = null;
        }

        public boolean setException(Throwable throwable0) {
            this.attemptedSetting = true;
            if(this.future == null || !this.future.setException(throwable0)) {
                return false;
            }
            this.setCompletedNormally();
            return true;
        }
    }

    static final class FutureGarbageCollectedException extends Throwable {
        FutureGarbageCollectedException(String s) {
            super(s);
        }

        @Override
        public Throwable fillInStackTrace() {
            synchronized(this) {
            }
            return this;
        }
    }

    public interface Resolver {
        Object attachCompleter(Completer arg1) throws Exception;
    }

    static final class SafeFuture implements ListenableFuture {
        final WeakReference completerWeakReference;
        private final AbstractResolvableFuture delegate;

        SafeFuture(Completer callbackToFutureAdapter$Completer0) {
            this.delegate = new AbstractResolvableFuture() {
                @Override  // androidx.concurrent.futures.AbstractResolvableFuture
                protected String pendingToString() {
                    Completer callbackToFutureAdapter$Completer0 = (Completer)SafeFuture.this.completerWeakReference.get();
                    return callbackToFutureAdapter$Completer0 == null ? "Completer object has been garbage collected, future will fail soon" : "tag=[" + callbackToFutureAdapter$Completer0.tag + "]";
                }
            };
            this.completerWeakReference = new WeakReference(callbackToFutureAdapter$Completer0);
        }

        @Override  // com.google.common.util.concurrent.ListenableFuture
        public void addListener(Runnable runnable0, Executor executor0) {
            this.delegate.addListener(runnable0, executor0);
        }

        @Override
        public boolean cancel(boolean z) {
            Completer callbackToFutureAdapter$Completer0 = (Completer)this.completerWeakReference.get();
            boolean z1 = this.delegate.cancel(z);
            if(z1 && callbackToFutureAdapter$Completer0 != null) {
                callbackToFutureAdapter$Completer0.fireCancellationListeners();
            }
            return z1;
        }

        boolean cancelWithoutNotifyingCompleter(boolean z) {
            return this.delegate.cancel(z);
        }

        @Override
        public Object get() throws InterruptedException, ExecutionException {
            return this.delegate.get();
        }

        @Override
        public Object get(long v, TimeUnit timeUnit0) throws InterruptedException, ExecutionException, TimeoutException {
            return this.delegate.get(v, timeUnit0);
        }

        @Override
        public boolean isCancelled() {
            return this.delegate.isCancelled();
        }

        @Override
        public boolean isDone() {
            return this.delegate.isDone();
        }

        boolean set(Object object0) {
            return this.delegate.set(object0);
        }

        boolean setException(Throwable throwable0) {
            return this.delegate.setException(throwable0);
        }

        @Override
        public String toString() {
            return this.delegate.toString();
        }
    }

    public static ListenableFuture getFuture(Resolver callbackToFutureAdapter$Resolver0) {
        Completer callbackToFutureAdapter$Completer0 = new Completer();
        SafeFuture callbackToFutureAdapter$SafeFuture0 = new SafeFuture(callbackToFutureAdapter$Completer0);
        callbackToFutureAdapter$Completer0.future = callbackToFutureAdapter$SafeFuture0;
        callbackToFutureAdapter$Completer0.tag = callbackToFutureAdapter$Resolver0.getClass();
        try {
            Object object0 = callbackToFutureAdapter$Resolver0.attachCompleter(callbackToFutureAdapter$Completer0);
            if(object0 != null) {
                callbackToFutureAdapter$Completer0.tag = object0;
                return callbackToFutureAdapter$SafeFuture0;
            }
        }
        catch(Exception exception0) {
            callbackToFutureAdapter$SafeFuture0.setException(exception0);
        }
        return callbackToFutureAdapter$SafeFuture0;
    }
}

