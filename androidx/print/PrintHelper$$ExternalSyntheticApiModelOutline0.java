package androidx.print;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.security.keystore.KeyGenParameterSpec.Builder;
import android.security.keystore.KeyGenParameterSpec;
import android.view.View;
import android.view.inputmethod.InputContentInfo;
import android.webkit.TracingConfig.Builder;
import android.webkit.WebMessage;
import android.webkit.WebMessagePort;

public final class PrintHelper..ExternalSyntheticApiModelOutline0 {
    public static KeyGenParameterSpec.Builder m(String s, int v) {
        return new KeyGenParameterSpec.Builder(s, v);
    }

    public static KeyGenParameterSpec m(Object object0) [...] // Inlined contents

    public static InputContentInfo m(Object object0) {
        return (InputContentInfo)object0;
    }

    public static TracingConfig.Builder m() {
        return new TracingConfig.Builder();
    }

    public static WebMessage m(String s, WebMessagePort[] arr_webMessagePort) {
        return new WebMessage(s, arr_webMessagePort);
    }

    public static void m() {
    }

    public static void m(Activity activity0, Application.ActivityLifecycleCallbacks application$ActivityLifecycleCallbacks0) {
        activity0.registerActivityLifecycleCallbacks(application$ActivityLifecycleCallbacks0);
    }

    public static void m(View view0, boolean z) {
        view0.setAccessibilityHeading(z);
    }
}

