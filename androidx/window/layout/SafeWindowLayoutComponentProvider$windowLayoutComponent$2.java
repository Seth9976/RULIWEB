package androidx.window.layout;

import androidx.window.extensions.WindowExtensionsProvider;
import androidx.window.extensions.layout.WindowLayoutComponent;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/window/extensions/layout/WindowLayoutComponent;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 0x30)
final class SafeWindowLayoutComponentProvider.windowLayoutComponent.2 extends Lambda implements Function0 {
    public static final SafeWindowLayoutComponentProvider.windowLayoutComponent.2 INSTANCE;

    static {
        SafeWindowLayoutComponentProvider.windowLayoutComponent.2.INSTANCE = new SafeWindowLayoutComponentProvider.windowLayoutComponent.2();
    }

    SafeWindowLayoutComponentProvider.windowLayoutComponent.2() {
        super(0);
    }

    public final WindowLayoutComponent invoke() {
        ClassLoader classLoader0 = SafeWindowLayoutComponentProvider.class.getClassLoader();
        if(classLoader0 != null && SafeWindowLayoutComponentProvider.access$canUseWindowLayoutComponent(SafeWindowLayoutComponentProvider.INSTANCE, classLoader0)) {
            try {
                return WindowExtensionsProvider.getWindowExtensions().getWindowLayoutComponent();
            }
            catch(UnsupportedOperationException unused_ex) {
                return null;
            }
        }
        return null;
    }

    @Override  // kotlin.jvm.functions.Function0
    public Object invoke() {
        return this.invoke();
    }
}

