package androidx.concurrent.futures;

import com.google.common.util.concurrent.ListenableFuture;

public final class ResolvableFuture extends AbstractResolvableFuture {
    public static ResolvableFuture create() {
        return new ResolvableFuture();
    }

    @Override  // androidx.concurrent.futures.AbstractResolvableFuture
    public boolean set(Object object0) {
        return super.set(object0);
    }

    @Override  // androidx.concurrent.futures.AbstractResolvableFuture
    public boolean setException(Throwable throwable0) {
        return super.setException(throwable0);
    }

    @Override  // androidx.concurrent.futures.AbstractResolvableFuture
    public boolean setFuture(ListenableFuture listenableFuture0) {
        return super.setFuture(listenableFuture0);
    }
}

