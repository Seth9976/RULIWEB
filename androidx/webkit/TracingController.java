package androidx.webkit;

import androidx.webkit.internal.TracingControllerImpl;
import java.io.OutputStream;
import java.util.concurrent.Executor;

public abstract class TracingController {
    static class LAZY_HOLDER {
        static final TracingController INSTANCE;

        static {
            LAZY_HOLDER.INSTANCE = new TracingControllerImpl();
        }
    }

    public static TracingController getInstance() {
        return LAZY_HOLDER.INSTANCE;
    }

    public abstract boolean isTracing();

    public abstract void start(TracingConfig arg1);

    public abstract boolean stop(OutputStream arg1, Executor arg2);
}

