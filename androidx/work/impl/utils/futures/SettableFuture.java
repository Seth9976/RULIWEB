package androidx.work.impl.utils.futures;

import com.google.common.util.concurrent.ListenableFuture;

public final class SettableFuture extends AbstractFuture {
    public static SettableFuture create() {
        return new SettableFuture();
    }

    @Override  // androidx.work.impl.utils.futures.AbstractFuture
    public boolean set(Object object0) {
        return super.set(object0);
    }

    @Override  // androidx.work.impl.utils.futures.AbstractFuture
    public boolean setException(Throwable throwable0) {
        return super.setException(throwable0);
    }

    @Override  // androidx.work.impl.utils.futures.AbstractFuture
    public boolean setFuture(ListenableFuture listenableFuture0) {
        return super.setFuture(listenableFuture0);
    }
}

