package androidx.webkit;

import android.os.Handler;
import android.webkit.WebMessagePort;
import java.lang.reflect.InvocationHandler;

public abstract class WebMessagePortCompat {
    public static abstract class WebMessageCallbackCompat {
        public void onMessage(WebMessagePortCompat webMessagePortCompat0, WebMessageCompat webMessageCompat0) {
        }
    }

    public abstract void close();

    public abstract WebMessagePort getFrameworkPort();

    public abstract InvocationHandler getInvocationHandler();

    public abstract void postMessage(WebMessageCompat arg1);

    public abstract void setWebMessageCallback(Handler arg1, WebMessageCallbackCompat arg2);

    public abstract void setWebMessageCallback(WebMessageCallbackCompat arg1);
}

