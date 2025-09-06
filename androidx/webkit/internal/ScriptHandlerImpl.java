package androidx.webkit.internal;

import androidx.webkit.ScriptHandler;
import java.lang.reflect.InvocationHandler;
import org.chromium.support_lib_boundary.ScriptHandlerBoundaryInterface;
import org.chromium.support_lib_boundary.util.BoundaryInterfaceReflectionUtil;

public class ScriptHandlerImpl implements ScriptHandler {
    private final ScriptHandlerBoundaryInterface mBoundaryInterface;

    private ScriptHandlerImpl(ScriptHandlerBoundaryInterface scriptHandlerBoundaryInterface0) {
        this.mBoundaryInterface = scriptHandlerBoundaryInterface0;
    }

    @Override  // androidx.webkit.ScriptHandler
    public void remove() {
        this.mBoundaryInterface.remove();
    }

    public static ScriptHandlerImpl toScriptHandler(InvocationHandler invocationHandler0) {
        return new ScriptHandlerImpl(((ScriptHandlerBoundaryInterface)BoundaryInterfaceReflectionUtil.castToSuppLibClass(ScriptHandlerBoundaryInterface.class, invocationHandler0)));
    }
}

