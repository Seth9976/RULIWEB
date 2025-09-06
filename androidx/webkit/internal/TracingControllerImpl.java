package androidx.webkit.internal;

import androidx.webkit.TracingConfig;
import androidx.webkit.TracingController;
import java.io.OutputStream;
import java.util.concurrent.Executor;
import org.chromium.support_lib_boundary.TracingControllerBoundaryInterface;

public class TracingControllerImpl extends TracingController {
    private TracingControllerBoundaryInterface mBoundaryInterface;
    private android.webkit.TracingController mFrameworksImpl;

    public TracingControllerImpl() {
        this.mFrameworksImpl = ApiHelperForP.getTracingControllerInstance();
        this.mBoundaryInterface = null;
    }

    private TracingControllerBoundaryInterface getBoundaryInterface() {
        if(this.mBoundaryInterface == null) {
            this.mBoundaryInterface = WebViewGlueCommunicator.getFactory().getTracingController();
        }
        return this.mBoundaryInterface;
    }

    private android.webkit.TracingController getFrameworksImpl() {
        if(this.mFrameworksImpl == null) {
            this.mFrameworksImpl = ApiHelperForP.getTracingControllerInstance();
        }
        return this.mFrameworksImpl;
    }

    // 去混淆评级： 低(40)
    @Override  // androidx.webkit.TracingController
    public boolean isTracing() {
        return ApiHelperForP.isTracing(this.getFrameworksImpl());
    }

    @Override  // androidx.webkit.TracingController
    public void start(TracingConfig tracingConfig0) {
        if(tracingConfig0 == null) {
            throw new IllegalArgumentException("Tracing config must be non null");
        }
        ApiHelperForP.start(this.getFrameworksImpl(), tracingConfig0);
    }

    // 去混淆评级： 低(40)
    @Override  // androidx.webkit.TracingController
    public boolean stop(OutputStream outputStream0, Executor executor0) {
        return ApiHelperForP.stop(this.getFrameworksImpl(), outputStream0, executor0);
    }
}

