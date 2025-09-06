package androidx.webkit.internal;

import android.os.Build.VERSION;
import android.webkit.WebView;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.chromium.support_lib_boundary.WebViewProviderFactoryBoundaryInterface;
import org.chromium.support_lib_boundary.util.BoundaryInterfaceReflectionUtil;

public class WebViewGlueCommunicator {
    static class LAZY_COMPAT_CONVERTER_HOLDER {
        static final WebkitToCompatConverter INSTANCE;

        static {
            LAZY_COMPAT_CONVERTER_HOLDER.INSTANCE = new WebkitToCompatConverter(WebViewGlueCommunicator.getFactory().getWebkitToCompatConverter());
        }
    }

    static class LAZY_FACTORY_HOLDER {
        static final WebViewProviderFactory INSTANCE;

        static {
            LAZY_FACTORY_HOLDER.INSTANCE = WebViewGlueCommunicator.createGlueProviderFactory();
        }
    }

    private static final String GLUE_FACTORY_PROVIDER_FETCHER_CLASS = "org.chromium.support_lib_glue.SupportLibReflectionUtil";
    private static final String GLUE_FACTORY_PROVIDER_FETCHER_METHOD = "createWebViewProviderFactory";

    static WebViewProviderFactory createGlueProviderFactory() {
        try {
            InvocationHandler invocationHandler0 = WebViewGlueCommunicator.fetchGlueProviderFactoryImpl();
            return new WebViewProviderFactoryAdapter(((WebViewProviderFactoryBoundaryInterface)BoundaryInterfaceReflectionUtil.castToSuppLibClass(WebViewProviderFactoryBoundaryInterface.class, invocationHandler0)));
        }
        catch(IllegalAccessException | InvocationTargetException | NoSuchMethodException exception0) {
            throw new RuntimeException(exception0);
        }
        catch(ClassNotFoundException unused_ex) {
            return new IncompatibleApkWebViewProviderFactory();
        }
    }

    private static InvocationHandler fetchGlueProviderFactoryImpl() throws IllegalAccessException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException {
        return (InvocationHandler)Class.forName("org.chromium.support_lib_glue.SupportLibReflectionUtil", false, WebViewGlueCommunicator.getWebViewClassLoader()).getDeclaredMethod("createWebViewProviderFactory", null).invoke(null, null);
    }

    public static WebkitToCompatConverter getCompatConverter() {
        return LAZY_COMPAT_CONVERTER_HOLDER.INSTANCE;
    }

    public static WebViewProviderFactory getFactory() {
        return LAZY_FACTORY_HOLDER.INSTANCE;
    }

    public static ClassLoader getWebViewClassLoader() {
        return Build.VERSION.SDK_INT < 28 ? WebViewGlueCommunicator.getWebViewProviderFactory().getClass().getClassLoader() : ApiHelperForP.getWebViewClassLoader();
    }

    private static Object getWebViewProviderFactory() {
        try {
            Method method0 = WebView.class.getDeclaredMethod("getFactory", null);
            method0.setAccessible(true);
            return method0.invoke(null, null);
        }
        catch(NoSuchMethodException | InvocationTargetException | IllegalAccessException exception0) {
            throw new RuntimeException(exception0);
        }
    }
}

