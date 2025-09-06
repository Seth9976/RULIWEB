package androidx.window.layout;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build.VERSION;
import androidx.window.extensions.layout.WindowLayoutComponent;
import androidx.work.impl.utils.NetworkApi23..ExternalSyntheticApiModelOutline0;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000B\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\r\u001A\u00020\n2\u0006\u0010\u000E\u001A\u00020\u000FH\u0002J \u0010\u0010\u001A\u0012\u0012\u0002\b\u0003 \u0012*\b\u0012\u0002\b\u0003\u0018\u00010\u00110\u00112\u0006\u0010\u000E\u001A\u00020\u000FH\u0002J\u0010\u0010\u0013\u001A\u00020\n2\u0006\u0010\u000E\u001A\u00020\u000FH\u0002J\u0010\u0010\u0014\u001A\u00020\n2\u0006\u0010\u000E\u001A\u00020\u000FH\u0002J\u0010\u0010\u0015\u001A\u00020\n2\u0006\u0010\u000E\u001A\u00020\u000FH\u0003J\u0010\u0010\u0016\u001A\u00020\n2\u0006\u0010\u000E\u001A\u00020\u000FH\u0002J\u0016\u0010\u0017\u001A\u00020\n2\f\u0010\u0018\u001A\b\u0012\u0004\u0012\u00020\n0\u0019H\u0002J \u0010\u001A\u001A\u0012\u0012\u0002\b\u0003 \u0012*\b\u0012\u0002\b\u0003\u0018\u00010\u00110\u00112\u0006\u0010\u000E\u001A\u00020\u000FH\u0002J \u0010\u001B\u001A\u0012\u0012\u0002\b\u0003 \u0012*\b\u0012\u0002\b\u0003\u0018\u00010\u00110\u00112\u0006\u0010\u000E\u001A\u00020\u000FH\u0002J \u0010\u001C\u001A\u0012\u0012\u0002\b\u0003 \u0012*\b\u0012\u0002\b\u0003\u0018\u00010\u00110\u00112\u0006\u0010\u000E\u001A\u00020\u000FH\u0002J\u0018\u0010\u001D\u001A\u00020\n*\u00020\u000B2\n\u0010\u001E\u001A\u0006\u0012\u0002\b\u00030\u0011H\u0002J\u0018\u0010\u001D\u001A\u00020\n*\u00020\u000B2\n\u0010\u001E\u001A\u0006\u0012\u0002\b\u00030\u001FH\u0002R\u001D\u0010\u0003\u001A\u0004\u0018\u00010\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001A\u0004\b\u0005\u0010\u0006R\u0018\u0010\t\u001A\u00020\n*\u00020\u000B8BX\u0082\u0004¢\u0006\u0006\u001A\u0004\b\t\u0010\f¨\u0006 "}, d2 = {"Landroidx/window/layout/SafeWindowLayoutComponentProvider;", "", "()V", "windowLayoutComponent", "Landroidx/window/extensions/layout/WindowLayoutComponent;", "getWindowLayoutComponent", "()Landroidx/window/extensions/layout/WindowLayoutComponent;", "windowLayoutComponent$delegate", "Lkotlin/Lazy;", "isPublic", "", "Ljava/lang/reflect/Method;", "(Ljava/lang/reflect/Method;)Z", "canUseWindowLayoutComponent", "classLoader", "Ljava/lang/ClassLoader;", "foldingFeatureClass", "Ljava/lang/Class;", "kotlin.jvm.PlatformType", "isFoldingFeatureValid", "isWindowExtensionsValid", "isWindowLayoutComponentValid", "isWindowLayoutProviderValid", "validate", "block", "Lkotlin/Function0;", "windowExtensionsClass", "windowExtensionsProviderClass", "windowLayoutComponentClass", "doesReturn", "clazz", "Lkotlin/reflect/KClass;", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class SafeWindowLayoutComponentProvider {
    public static final SafeWindowLayoutComponentProvider INSTANCE;
    private static final Lazy windowLayoutComponent$delegate;

    static {
        SafeWindowLayoutComponentProvider.INSTANCE = new SafeWindowLayoutComponentProvider();
        SafeWindowLayoutComponentProvider.windowLayoutComponent$delegate = LazyKt.lazy(SafeWindowLayoutComponentProvider.windowLayoutComponent.2.INSTANCE);
    }

    public static final boolean access$canUseWindowLayoutComponent(SafeWindowLayoutComponentProvider safeWindowLayoutComponentProvider0, ClassLoader classLoader0) {
        return safeWindowLayoutComponentProvider0.canUseWindowLayoutComponent(classLoader0);
    }

    // 去混淆评级： 低(40)
    private final boolean canUseWindowLayoutComponent(ClassLoader classLoader0) {
        return Build.VERSION.SDK_INT >= 24 && this.isWindowLayoutProviderValid(classLoader0) && this.isWindowExtensionsValid(classLoader0) && this.isWindowLayoutComponentValid(classLoader0) && this.isFoldingFeatureValid(classLoader0);
    }

    private final boolean doesReturn(Method method0, Class class0) {
        return method0.getReturnType().equals(class0);
    }

    private final boolean doesReturn(Method method0, KClass kClass0) {
        return this.doesReturn(method0, JvmClassMappingKt.getJavaClass(kClass0));
    }

    private final Class foldingFeatureClass(ClassLoader classLoader0) {
        return classLoader0.loadClass("androidx.window.extensions.layout.FoldingFeature");
    }

    public final WindowLayoutComponent getWindowLayoutComponent() {
        return (WindowLayoutComponent)SafeWindowLayoutComponentProvider.windowLayoutComponent$delegate.getValue();
    }

    private final boolean isFoldingFeatureValid(ClassLoader classLoader0) {
        return this.validate(new Function0() {
            final ClassLoader $classLoader;

            {
                this.$classLoader = classLoader0;
                super(0);
            }

            public final Boolean invoke() {
                Class class0 = SafeWindowLayoutComponentProvider.INSTANCE.foldingFeatureClass(this.$classLoader);
                Method method0 = class0.getMethod("getBounds", null);
                Method method1 = class0.getMethod("getType", null);
                Method method2 = class0.getMethod("getState", null);
                Intrinsics.checkNotNullExpressionValue(method0, "getBoundsMethod");
                KClass kClass0 = Reflection.getOrCreateKotlinClass(Rect.class);
                if(SafeWindowLayoutComponentProvider.INSTANCE.doesReturn(method0, kClass0) && SafeWindowLayoutComponentProvider.INSTANCE.isPublic(method0)) {
                    Intrinsics.checkNotNullExpressionValue(method1, "getTypeMethod");
                    KClass kClass1 = Reflection.getOrCreateKotlinClass(Integer.TYPE);
                    if(SafeWindowLayoutComponentProvider.INSTANCE.doesReturn(method1, kClass1) && SafeWindowLayoutComponentProvider.INSTANCE.isPublic(method1)) {
                        Intrinsics.checkNotNullExpressionValue(method2, "getStateMethod");
                        KClass kClass2 = Reflection.getOrCreateKotlinClass(Integer.TYPE);
                        return !SafeWindowLayoutComponentProvider.INSTANCE.doesReturn(method2, kClass2) || !SafeWindowLayoutComponentProvider.INSTANCE.isPublic(method2) ? false : true;
                    }
                }
                return false;
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }
        });
    }

    private final boolean isPublic(Method method0) {
        return Modifier.isPublic(method0.getModifiers());
    }

    private final boolean isWindowExtensionsValid(ClassLoader classLoader0) {
        return this.validate(new Function0() {
            final ClassLoader $classLoader;

            {
                this.$classLoader = classLoader0;
                super(0);
            }

            public final Boolean invoke() {
                Method method0 = SafeWindowLayoutComponentProvider.INSTANCE.windowExtensionsClass(this.$classLoader).getMethod("getWindowLayoutComponent", null);
                Class class0 = SafeWindowLayoutComponentProvider.INSTANCE.windowLayoutComponentClass(this.$classLoader);
                Intrinsics.checkNotNullExpressionValue(method0, "getWindowLayoutComponentMethod");
                if(SafeWindowLayoutComponentProvider.INSTANCE.isPublic(method0)) {
                    Intrinsics.checkNotNullExpressionValue(class0, "windowLayoutComponentClass");
                    return SafeWindowLayoutComponentProvider.INSTANCE.doesReturn(method0, class0);
                }
                return false;
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }
        });
    }

    private final boolean isWindowLayoutComponentValid(ClassLoader classLoader0) {
        return this.validate(new Function0() {
            final ClassLoader $classLoader;

            {
                this.$classLoader = classLoader0;
                super(0);
            }

            public final Boolean invoke() {
                Class class0 = SafeWindowLayoutComponentProvider.INSTANCE.windowLayoutComponentClass(this.$classLoader);
                Class[] arr_class = new Class[2];
                boolean z = false;
                arr_class[0] = Activity.class;
                arr_class[1] = NetworkApi23..ExternalSyntheticApiModelOutline0.m();
                Method method0 = class0.getMethod("addWindowLayoutInfoListener", arr_class);
                Method method1 = class0.getMethod("removeWindowLayoutInfoListener", NetworkApi23..ExternalSyntheticApiModelOutline0.m());
                Intrinsics.checkNotNullExpressionValue(method0, "addListenerMethod");
                if(SafeWindowLayoutComponentProvider.INSTANCE.isPublic(method0)) {
                    Intrinsics.checkNotNullExpressionValue(method1, "removeListenerMethod");
                    if(SafeWindowLayoutComponentProvider.INSTANCE.isPublic(method1)) {
                        z = true;
                    }
                }
                return Boolean.valueOf(z);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }
        });
    }

    private final boolean isWindowLayoutProviderValid(ClassLoader classLoader0) {
        return this.validate(new Function0() {
            final ClassLoader $classLoader;

            {
                this.$classLoader = classLoader0;
                super(0);
            }

            public final Boolean invoke() {
                Method method0 = SafeWindowLayoutComponentProvider.INSTANCE.windowExtensionsProviderClass(this.$classLoader).getDeclaredMethod("getWindowExtensions", null);
                Class class0 = SafeWindowLayoutComponentProvider.INSTANCE.windowExtensionsClass(this.$classLoader);
                Intrinsics.checkNotNullExpressionValue(method0, "getWindowExtensionsMethod");
                Intrinsics.checkNotNullExpressionValue(class0, "windowExtensionsClass");
                return !SafeWindowLayoutComponentProvider.INSTANCE.doesReturn(method0, class0) || !SafeWindowLayoutComponentProvider.INSTANCE.isPublic(method0) ? false : true;
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }
        });
    }

    private final boolean validate(Function0 function00) {
        try {
            return ((Boolean)function00.invoke()).booleanValue();
        }
        catch(ClassNotFoundException | NoSuchMethodException unused_ex) {
            return false;
        }
    }

    private final Class windowExtensionsClass(ClassLoader classLoader0) {
        return classLoader0.loadClass("androidx.window.extensions.WindowExtensions");
    }

    private final Class windowExtensionsProviderClass(ClassLoader classLoader0) {
        return classLoader0.loadClass("androidx.window.extensions.WindowExtensionsProvider");
    }

    private final Class windowLayoutComponentClass(ClassLoader classLoader0) {
        return classLoader0.loadClass("androidx.window.extensions.layout.WindowLayoutComponent");
    }
}

