package androidx.browser.trusted;

import androidx.concurrent.futures.ResolvableFuture;
import com.google.common.util.concurrent.ListenableFuture;

class FutureUtils {
    static ListenableFuture immediateFailedFuture(Throwable throwable0) {
        ListenableFuture listenableFuture0 = ResolvableFuture.create();
        ((ResolvableFuture)listenableFuture0).setException(throwable0);
        return listenableFuture0;
    }
}

