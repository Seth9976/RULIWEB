package androidx.webkit.internal;

import androidx.webkit.OutcomeReceiverCompat;
import androidx.webkit.PrefetchException;
import androidx.webkit.PrefetchNetworkException;
import java.lang.reflect.InvocationHandler;
import org.chromium.support_lib_boundary.PrefetchExceptionBoundaryInterface;
import org.chromium.support_lib_boundary.PrefetchNetworkExceptionBoundaryInterface;
import org.chromium.support_lib_boundary.PrefetchOperationCallbackBoundaryInterface;
import org.chromium.support_lib_boundary.util.BoundaryInterfaceReflectionUtil;

public class PrefetchOperationCallbackAdapter {
    public static InvocationHandler buildInvocationHandler(OutcomeReceiverCompat outcomeReceiverCompat0) {
        return BoundaryInterfaceReflectionUtil.createInvocationHandlerFor(new PrefetchOperationCallbackBoundaryInterface() {
            @Override  // org.chromium.support_lib_boundary.PrefetchOperationCallbackBoundaryInterface
            public void onFailure(InvocationHandler invocationHandler0) {
                if(BoundaryInterfaceReflectionUtil.instanceOfInOwnClassLoader(invocationHandler0, "org.chromium.support_lib_boundary.PrefetchNetworkExceptionBoundaryInterface")) {
                    PrefetchNetworkException prefetchNetworkException0 = PrefetchOperationCallbackAdapter.getNetworkException(invocationHandler0);
                    outcomeReceiverCompat0.onError(prefetchNetworkException0);
                    return;
                }
                PrefetchException prefetchException0 = PrefetchOperationCallbackAdapter.getPrefetchException(invocationHandler0);
                outcomeReceiverCompat0.onError(prefetchException0);
            }

            @Override  // org.chromium.support_lib_boundary.PrefetchOperationCallbackBoundaryInterface
            public void onSuccess() {
                outcomeReceiverCompat0.onResult(null);
            }
        });
    }

    private static PrefetchNetworkException getNetworkException(InvocationHandler invocationHandler0) {
        PrefetchNetworkExceptionBoundaryInterface prefetchNetworkExceptionBoundaryInterface0 = (PrefetchNetworkExceptionBoundaryInterface)BoundaryInterfaceReflectionUtil.castToSuppLibClass(PrefetchNetworkExceptionBoundaryInterface.class, invocationHandler0);
        return prefetchNetworkExceptionBoundaryInterface0.getMessage() == null ? new PrefetchNetworkException(prefetchNetworkExceptionBoundaryInterface0.getHttpResponseStatusCode()) : new PrefetchNetworkException(prefetchNetworkExceptionBoundaryInterface0.getMessage(), prefetchNetworkExceptionBoundaryInterface0.getHttpResponseStatusCode());
    }

    private static PrefetchException getPrefetchException(InvocationHandler invocationHandler0) {
        PrefetchExceptionBoundaryInterface prefetchExceptionBoundaryInterface0 = (PrefetchExceptionBoundaryInterface)BoundaryInterfaceReflectionUtil.castToSuppLibClass(PrefetchExceptionBoundaryInterface.class, invocationHandler0);
        return prefetchExceptionBoundaryInterface0.getMessage() == null ? new PrefetchException() : new PrefetchException(prefetchExceptionBoundaryInterface0.getMessage());
    }
}

