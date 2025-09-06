package androidx.webkit.internal;

import androidx.webkit.BlockingStartUpLocation;
import androidx.webkit.WebViewCompat.WebViewStartUpCallback;
import androidx.webkit.WebViewStartUpResult;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.chromium.support_lib_boundary.WebViewStartUpCallbackBoundaryInterface;
import org.chromium.support_lib_boundary.WebViewStartUpResultBoundaryInterface;
import org.chromium.support_lib_boundary.util.BoundaryInterfaceReflectionUtil;

public class WebViewStartUpCallbackAdapter implements WebViewStartUpCallbackBoundaryInterface {
    static class BlockingStartUpLocationImpl implements BlockingStartUpLocation {
        private final Throwable mThrowable;

        BlockingStartUpLocationImpl(Throwable throwable0) {
            this.mThrowable = throwable0;
        }

        @Override  // androidx.webkit.BlockingStartUpLocation
        public String getStackInformation() {
            StringWriter stringWriter0 = new StringWriter();
            PrintWriter printWriter0 = new PrintWriter(stringWriter0);
            this.mThrowable.printStackTrace(printWriter0);
            return stringWriter0.toString();
        }
    }

    private final WebViewStartUpCallback mWebViewStartUpCallback;

    public WebViewStartUpCallbackAdapter(WebViewStartUpCallback webViewCompat$WebViewStartUpCallback0) {
        this.mWebViewStartUpCallback = webViewCompat$WebViewStartUpCallback0;
    }

    @Override  // org.chromium.support_lib_boundary.WebViewStartUpCallbackBoundaryInterface
    public void onSuccess(InvocationHandler invocationHandler0) {
        WebViewStartUpResult webViewStartUpResult0 = this.webViewStartUpResultFromBoundaryInterface(((WebViewStartUpResultBoundaryInterface)Objects.requireNonNull(((WebViewStartUpResultBoundaryInterface)BoundaryInterfaceReflectionUtil.castToSuppLibClass(WebViewStartUpResultBoundaryInterface.class, invocationHandler0)))));
        this.mWebViewStartUpCallback.onSuccess(webViewStartUpResult0);
    }

    private WebViewStartUpResult webViewStartUpResultFromBoundaryInterface(WebViewStartUpResultBoundaryInterface webViewStartUpResultBoundaryInterface0) {
        return new WebViewStartUpResult() {
            private final List mBlockingStartUpLocations;

            {
                WebViewStartUpResultBoundaryInterface webViewStartUpResultBoundaryInterface0 = webViewStartUpResultBoundaryInterface0;  // 捕获的参数 （可能与外部方法变量命名冲突；考虑手动重命名）
                this.mBlockingStartUpLocations = this.convertFromThrowables(webViewStartUpResultBoundaryInterface0.getBlockingStartUpLocations());
            }

            private List convertFromThrowables(List list0) {
                List list1 = new ArrayList();
                for(Object object0: list0) {
                    list1.add(new BlockingStartUpLocationImpl(((Throwable)object0)));
                }
                return list1;
            }

            @Override  // androidx.webkit.WebViewStartUpResult
            public List getBlockingStartUpLocations() {
                return this.mBlockingStartUpLocations;
            }

            @Override  // androidx.webkit.WebViewStartUpResult
            public Long getMaxTimePerTaskInUiThreadMillis() {
                return webViewStartUpResultBoundaryInterface0.getMaxTimePerTaskInUiThreadMillis();
            }

            @Override  // androidx.webkit.WebViewStartUpResult
            public Long getTotalTimeInUiThreadMillis() {
                return webViewStartUpResultBoundaryInterface0.getTotalTimeInUiThreadMillis();
            }
        };
    }
}

