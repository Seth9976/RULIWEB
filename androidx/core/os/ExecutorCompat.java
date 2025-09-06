package androidx.core.os;

import android.os.Handler;
import androidx.core.util.Preconditions;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

public final class ExecutorCompat {
    static class HandlerExecutor implements Executor {
        private final Handler mHandler;

        HandlerExecutor(Handler handler0) {
            this.mHandler = (Handler)Preconditions.checkNotNull(handler0);
        }

        @Override
        public void execute(Runnable runnable0) {
            Runnable runnable1 = (Runnable)Preconditions.checkNotNull(runnable0);
            if(!this.mHandler.post(runnable1)) {
                throw new RejectedExecutionException(this.mHandler + " is shutting down");
            }
        }
    }

    public static Executor create(Handler handler0) {
        return new HandlerExecutor(handler0);
    }
}

