package androidx.work;

import androidx.lifecycle.LiveData;
import com.google.common.util.concurrent.ListenableFuture;

public interface Operation {
    public static abstract class State {
        public static final class FAILURE extends State {
            private final Throwable mThrowable;

            public FAILURE(Throwable throwable0) {
                this.mThrowable = throwable0;
            }

            public Throwable getThrowable() {
                return this.mThrowable;
            }

            @Override
            public String toString() {
                return "FAILURE (" + this.mThrowable.getMessage() + ")";
            }
        }

        public static final class IN_PROGRESS extends State {
            private IN_PROGRESS() {
            }

            IN_PROGRESS(androidx.work.Operation.1 operation$10) {
            }

            @Override
            public String toString() {
                return "IN_PROGRESS";
            }
        }

        public static final class SUCCESS extends State {
            private SUCCESS() {
            }

            SUCCESS(androidx.work.Operation.1 operation$10) {
            }

            @Override
            public String toString() {
                return "SUCCESS";
            }
        }

    }

    public static final IN_PROGRESS IN_PROGRESS;
    public static final SUCCESS SUCCESS;

    static {
        Operation.SUCCESS = new SUCCESS(null);
        Operation.IN_PROGRESS = new IN_PROGRESS(null);
    }

    ListenableFuture getResult();

    LiveData getState();

    class androidx.work.Operation.1 {
    }

}

