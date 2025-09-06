package androidx.window.layout;

import android.app.Activity;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.view.View.OnAttachStateChangeListener;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import androidx.window.core.Version;
import androidx.window.sidecar.SidecarDeviceState;
import androidx.window.sidecar.SidecarDisplayFeature;
import androidx.window.sidecar.SidecarInterface.SidecarCallback;
import androidx.window.sidecar.SidecarInterface;
import androidx.window.sidecar.SidecarProvider;
import androidx.window.sidecar.SidecarWindowLayoutInfo;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000B\n\u0002\b\u0006\b\u0000\u0018\u0000 !2\u00020\u0001:\u0005!\"#$%B\u000F\b\u0016\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004B\u001B\b\u0007\u0012\n\b\u0001\u0010\u0005\u001A\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001A\u00020\b¢\u0006\u0002\u0010\tJ\u0010\u0010\u0014\u001A\u00020\u00152\u0006\u0010\u0016\u001A\u00020\fH\u0007J\u0010\u0010\u0017\u001A\u00020\u00182\u0006\u0010\u0016\u001A\u00020\fH\u0016J\u0010\u0010\u0019\u001A\u00020\u00182\u0006\u0010\u0016\u001A\u00020\fH\u0016J\u0016\u0010\u001A\u001A\u00020\u00182\u0006\u0010\u001B\u001A\u00020\u00132\u0006\u0010\u0016\u001A\u00020\fJ\u0010\u0010\u001C\u001A\u00020\u00182\u0006\u0010\u0016\u001A\u00020\fH\u0002J\u0010\u0010\u001D\u001A\u00020\u00182\u0006\u0010\u000E\u001A\u00020\u000FH\u0016J\u0010\u0010\u001E\u001A\u00020\u00182\u0006\u0010\u0016\u001A\u00020\fH\u0002J\b\u0010\u001F\u001A\u00020 H\u0017R\u001A\u0010\n\u001A\u000E\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000BX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000E\u001A\u0004\u0018\u00010\u000FX\u0082\u000E¢\u0006\u0002\n\u0000R\u0013\u0010\u0005\u001A\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001A\u0004\b\u0010\u0010\u0011R\u000E\u0010\u0007\u001A\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001A\u0010\u0012\u001A\u000E\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\f0\u000BX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Landroidx/window/layout/SidecarCompat;", "Landroidx/window/layout/ExtensionInterfaceCompat;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "sidecar", "Landroidx/window/sidecar/SidecarInterface;", "sidecarAdapter", "Landroidx/window/layout/SidecarAdapter;", "(Landroidx/window/sidecar/SidecarInterface;Landroidx/window/layout/SidecarAdapter;)V", "componentCallbackMap", "", "Landroid/app/Activity;", "Landroid/content/ComponentCallbacks;", "extensionCallback", "Landroidx/window/layout/ExtensionInterfaceCompat$ExtensionCallbackInterface;", "getSidecar", "()Landroidx/window/sidecar/SidecarInterface;", "windowListenerRegisteredContexts", "Landroid/os/IBinder;", "getWindowLayoutInfo", "Landroidx/window/layout/WindowLayoutInfo;", "activity", "onWindowLayoutChangeListenerAdded", "", "onWindowLayoutChangeListenerRemoved", "register", "windowToken", "registerConfigurationChangeListener", "setExtensionCallback", "unregisterComponentCallback", "validateExtensionInterface", "", "Companion", "DistinctElementCallback", "DistinctSidecarElementCallback", "FirstAttachAdapter", "TranslatingCallback", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class SidecarCompat implements ExtensionInterfaceCompat {
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0019\u0010\t\u001A\u0004\u0018\u00010\n2\b\u0010\u000B\u001A\u0004\u0018\u00010\fH\u0000¢\u0006\u0002\b\rJ\u0017\u0010\u000E\u001A\u0004\u0018\u00010\u000F2\u0006\u0010\u0010\u001A\u00020\u0011H\u0000¢\u0006\u0002\b\u0012R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0013\u0010\u0005\u001A\u0004\u0018\u00010\u00068F¢\u0006\u0006\u001A\u0004\b\u0007\u0010\b¨\u0006\u0013"}, d2 = {"Landroidx/window/layout/SidecarCompat$Companion;", "", "()V", "TAG", "", "sidecarVersion", "Landroidx/window/core/Version;", "getSidecarVersion", "()Landroidx/window/core/Version;", "getActivityWindowToken", "Landroid/os/IBinder;", "activity", "Landroid/app/Activity;", "getActivityWindowToken$window_release", "getSidecarCompat", "Landroidx/window/sidecar/SidecarInterface;", "context", "Landroid/content/Context;", "getSidecarCompat$window_release", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final IBinder getActivityWindowToken$window_release(Activity activity0) {
            if(activity0 == null) {
                return null;
            }
            Window window0 = activity0.getWindow();
            if(window0 == null) {
                return null;
            }
            WindowManager.LayoutParams windowManager$LayoutParams0 = window0.getAttributes();
            return windowManager$LayoutParams0 == null ? null : windowManager$LayoutParams0.token;
        }

        public final SidecarInterface getSidecarCompat$window_release(Context context0) {
            Intrinsics.checkNotNullParameter(context0, "context");
            return SidecarProvider.getSidecarImpl(context0.getApplicationContext());
        }

        public final Version getSidecarVersion() {
            try {
                String s = SidecarProvider.getApiVersion();
                return TextUtils.isEmpty(s) ? null : Version.Companion.parse(s);
            }
            catch(NoClassDefFoundError | UnsupportedOperationException unused_ex) {
                return null;
            }
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0001¢\u0006\u0002\u0010\u0003J\u0018\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\u00062\u0006\u0010\r\u001A\u00020\u0007H\u0016R\u001C\u0010\u0004\u001A\u000E\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00058\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\b\u001A\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000E"}, d2 = {"Landroidx/window/layout/SidecarCompat$DistinctElementCallback;", "Landroidx/window/layout/ExtensionInterfaceCompat$ExtensionCallbackInterface;", "callbackInterface", "(Landroidx/window/layout/ExtensionInterfaceCompat$ExtensionCallbackInterface;)V", "activityWindowLayoutInfo", "Ljava/util/WeakHashMap;", "Landroid/app/Activity;", "Landroidx/window/layout/WindowLayoutInfo;", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "onWindowLayoutChanged", "", "activity", "newLayout", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    static final class DistinctElementCallback implements ExtensionCallbackInterface {
        private final WeakHashMap activityWindowLayoutInfo;
        private final ExtensionCallbackInterface callbackInterface;
        private final ReentrantLock lock;

        public DistinctElementCallback(ExtensionCallbackInterface extensionInterfaceCompat$ExtensionCallbackInterface0) {
            Intrinsics.checkNotNullParameter(extensionInterfaceCompat$ExtensionCallbackInterface0, "callbackInterface");
            super();
            this.callbackInterface = extensionInterfaceCompat$ExtensionCallbackInterface0;
            this.lock = new ReentrantLock();
            this.activityWindowLayoutInfo = new WeakHashMap();
        }

        @Override  // androidx.window.layout.ExtensionInterfaceCompat$ExtensionCallbackInterface
        public void onWindowLayoutChanged(Activity activity0, WindowLayoutInfo windowLayoutInfo0) {
            Intrinsics.checkNotNullParameter(activity0, "activity");
            Intrinsics.checkNotNullParameter(windowLayoutInfo0, "newLayout");
            Lock lock0 = this.lock;
            lock0.lock();
            try {
                if(Intrinsics.areEqual(windowLayoutInfo0, ((WindowLayoutInfo)this.activityWindowLayoutInfo.get(activity0)))) {
                    return;
                }
                WindowLayoutInfo windowLayoutInfo1 = (WindowLayoutInfo)this.activityWindowLayoutInfo.put(activity0, windowLayoutInfo0);
            }
            finally {
                lock0.unlock();
            }
            this.callbackInterface.onWindowLayoutChanged(activity0, windowLayoutInfo0);
        }
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0001¢\u0006\u0002\u0010\u0005J\u0010\u0010\u000E\u001A\u00020\u000F2\u0006\u0010\u0010\u001A\u00020\u0007H\u0016J\u0018\u0010\u0011\u001A\u00020\u000F2\u0006\u0010\u0012\u001A\u00020\f2\u0006\u0010\u0013\u001A\u00020\rH\u0016R\u000E\u0010\u0004\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001A\u0004\u0018\u00010\u00078\u0002@\u0002X\u0083\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\b\u001A\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001C\u0010\n\u001A\u000E\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000B8\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Landroidx/window/layout/SidecarCompat$DistinctSidecarElementCallback;", "Landroidx/window/sidecar/SidecarInterface$SidecarCallback;", "sidecarAdapter", "Landroidx/window/layout/SidecarAdapter;", "callbackInterface", "(Landroidx/window/layout/SidecarAdapter;Landroidx/window/sidecar/SidecarInterface$SidecarCallback;)V", "lastDeviceState", "Landroidx/window/sidecar/SidecarDeviceState;", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "mActivityWindowLayoutInfo", "Ljava/util/WeakHashMap;", "Landroid/os/IBinder;", "Landroidx/window/sidecar/SidecarWindowLayoutInfo;", "onDeviceStateChanged", "", "newDeviceState", "onWindowLayoutChanged", "token", "newLayout", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    static final class DistinctSidecarElementCallback implements SidecarInterface.SidecarCallback {
        private final SidecarInterface.SidecarCallback callbackInterface;
        private SidecarDeviceState lastDeviceState;
        private final ReentrantLock lock;
        private final WeakHashMap mActivityWindowLayoutInfo;
        private final SidecarAdapter sidecarAdapter;

        public DistinctSidecarElementCallback(SidecarAdapter sidecarAdapter0, SidecarInterface.SidecarCallback sidecarInterface$SidecarCallback0) {
            Intrinsics.checkNotNullParameter(sidecarAdapter0, "sidecarAdapter");
            Intrinsics.checkNotNullParameter(sidecarInterface$SidecarCallback0, "callbackInterface");
            super();
            this.sidecarAdapter = sidecarAdapter0;
            this.callbackInterface = sidecarInterface$SidecarCallback0;
            this.lock = new ReentrantLock();
            this.mActivityWindowLayoutInfo = new WeakHashMap();
        }

        public void onDeviceStateChanged(SidecarDeviceState sidecarDeviceState0) {
            Intrinsics.checkNotNullParameter(sidecarDeviceState0, "newDeviceState");
            Lock lock0 = this.lock;
            lock0.lock();
            try {
                if(this.sidecarAdapter.isEqualSidecarDeviceState(this.lastDeviceState, sidecarDeviceState0)) {
                    return;
                }
                this.lastDeviceState = sidecarDeviceState0;
                this.callbackInterface.onDeviceStateChanged(sidecarDeviceState0);
            }
            finally {
                lock0.unlock();
            }
        }

        public void onWindowLayoutChanged(IBinder iBinder0, SidecarWindowLayoutInfo sidecarWindowLayoutInfo0) {
            Intrinsics.checkNotNullParameter(iBinder0, "token");
            Intrinsics.checkNotNullParameter(sidecarWindowLayoutInfo0, "newLayout");
            synchronized(this.lock) {
                SidecarWindowLayoutInfo sidecarWindowLayoutInfo1 = (SidecarWindowLayoutInfo)this.mActivityWindowLayoutInfo.get(iBinder0);
                if(this.sidecarAdapter.isEqualSidecarWindowLayoutInfo(sidecarWindowLayoutInfo1, sidecarWindowLayoutInfo0)) {
                    return;
                }
                SidecarWindowLayoutInfo sidecarWindowLayoutInfo2 = (SidecarWindowLayoutInfo)this.mActivityWindowLayoutInfo.put(iBinder0, sidecarWindowLayoutInfo0);
            }
            this.callbackInterface.onWindowLayoutChanged(iBinder0, sidecarWindowLayoutInfo0);
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\rH\u0016J\u0010\u0010\u000E\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\rH\u0016R\u001C\u0010\u0007\u001A\u0010\u0012\f\u0012\n \t*\u0004\u0018\u00010\u00050\u00050\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000F"}, d2 = {"Landroidx/window/layout/SidecarCompat$FirstAttachAdapter;", "Landroid/view/View$OnAttachStateChangeListener;", "sidecarCompat", "Landroidx/window/layout/SidecarCompat;", "activity", "Landroid/app/Activity;", "(Landroidx/window/layout/SidecarCompat;Landroid/app/Activity;)V", "activityWeakReference", "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "onViewAttachedToWindow", "", "view", "Landroid/view/View;", "onViewDetachedFromWindow", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    static final class FirstAttachAdapter implements View.OnAttachStateChangeListener {
        private final WeakReference activityWeakReference;
        private final SidecarCompat sidecarCompat;

        public FirstAttachAdapter(SidecarCompat sidecarCompat0, Activity activity0) {
            Intrinsics.checkNotNullParameter(sidecarCompat0, "sidecarCompat");
            Intrinsics.checkNotNullParameter(activity0, "activity");
            super();
            this.sidecarCompat = sidecarCompat0;
            this.activityWeakReference = new WeakReference(activity0);
        }

        @Override  // android.view.View$OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view0) {
            Intrinsics.checkNotNullParameter(view0, "view");
            view0.removeOnAttachStateChangeListener(this);
            Activity activity0 = (Activity)this.activityWeakReference.get();
            IBinder iBinder0 = SidecarCompat.Companion.getActivityWindowToken$window_release(activity0);
            if(activity0 == null || iBinder0 == null) {
                return;
            }
            this.sidecarCompat.register(iBinder0, activity0);
        }

        @Override  // android.view.View$OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view0) {
            Intrinsics.checkNotNullParameter(view0, "view");
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0080\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0017J\u0018\u0010\u0007\u001A\u00020\u00042\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u000BH\u0017¨\u0006\f"}, d2 = {"Landroidx/window/layout/SidecarCompat$TranslatingCallback;", "Landroidx/window/sidecar/SidecarInterface$SidecarCallback;", "(Landroidx/window/layout/SidecarCompat;)V", "onDeviceStateChanged", "", "newDeviceState", "Landroidx/window/sidecar/SidecarDeviceState;", "onWindowLayoutChanged", "windowToken", "Landroid/os/IBinder;", "newLayout", "Landroidx/window/sidecar/SidecarWindowLayoutInfo;", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public final class TranslatingCallback implements SidecarInterface.SidecarCallback {
        public TranslatingCallback() {
            Intrinsics.checkNotNullParameter(sidecarCompat0, "this$0");
            SidecarCompat.this = sidecarCompat0;
            super();
        }

        public void onDeviceStateChanged(SidecarDeviceState sidecarDeviceState0) {
            Intrinsics.checkNotNullParameter(sidecarDeviceState0, "newDeviceState");
            Iterable iterable0 = SidecarCompat.this.windowListenerRegisteredContexts.values();
            SidecarCompat sidecarCompat0 = SidecarCompat.this;
            for(Object object0: iterable0) {
                Activity activity0 = (Activity)object0;
                IBinder iBinder0 = SidecarCompat.Companion.getActivityWindowToken$window_release(activity0);
                SidecarWindowLayoutInfo sidecarWindowLayoutInfo0 = null;
                if(iBinder0 != null) {
                    SidecarInterface sidecarInterface0 = sidecarCompat0.getSidecar();
                    if(sidecarInterface0 != null) {
                        sidecarWindowLayoutInfo0 = sidecarInterface0.getWindowLayoutInfo(iBinder0);
                    }
                }
                ExtensionCallbackInterface extensionInterfaceCompat$ExtensionCallbackInterface0 = sidecarCompat0.extensionCallback;
                if(extensionInterfaceCompat$ExtensionCallbackInterface0 != null) {
                    extensionInterfaceCompat$ExtensionCallbackInterface0.onWindowLayoutChanged(activity0, sidecarCompat0.sidecarAdapter.translate(sidecarWindowLayoutInfo0, sidecarDeviceState0));
                }
            }
        }

        public void onWindowLayoutChanged(IBinder iBinder0, SidecarWindowLayoutInfo sidecarWindowLayoutInfo0) {
            Intrinsics.checkNotNullParameter(iBinder0, "windowToken");
            Intrinsics.checkNotNullParameter(sidecarWindowLayoutInfo0, "newLayout");
            Activity activity0 = (Activity)SidecarCompat.this.windowListenerRegisteredContexts.get(iBinder0);
            if(activity0 == null) {
                Log.w("SidecarCompat", "Unable to resolve activity from window token. Missing a call to #onWindowLayoutChangeListenerAdded()?");
                return;
            }
            SidecarAdapter sidecarAdapter0 = SidecarCompat.this.sidecarAdapter;
            SidecarInterface sidecarInterface0 = SidecarCompat.this.getSidecar();
            SidecarDeviceState sidecarDeviceState0 = sidecarInterface0 == null ? null : sidecarInterface0.getDeviceState();
            if(sidecarDeviceState0 == null) {
                sidecarDeviceState0 = new SidecarDeviceState();
            }
            WindowLayoutInfo windowLayoutInfo0 = sidecarAdapter0.translate(sidecarWindowLayoutInfo0, sidecarDeviceState0);
            ExtensionCallbackInterface extensionInterfaceCompat$ExtensionCallbackInterface0 = SidecarCompat.this.extensionCallback;
            if(extensionInterfaceCompat$ExtensionCallbackInterface0 == null) {
                return;
            }
            extensionInterfaceCompat$ExtensionCallbackInterface0.onWindowLayoutChanged(activity0, windowLayoutInfo0);
        }
    }

    public static final Companion Companion = null;
    private static final String TAG = "SidecarCompat";
    private final Map componentCallbackMap;
    private ExtensionCallbackInterface extensionCallback;
    private final SidecarInterface sidecar;
    private final SidecarAdapter sidecarAdapter;
    private final Map windowListenerRegisteredContexts;

    static {
        SidecarCompat.Companion = new Companion(null);
    }

    public SidecarCompat(Context context0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        this(SidecarCompat.Companion.getSidecarCompat$window_release(context0), new SidecarAdapter(null, 1, null));
    }

    public SidecarCompat(SidecarInterface sidecarInterface0, SidecarAdapter sidecarAdapter0) {
        Intrinsics.checkNotNullParameter(sidecarAdapter0, "sidecarAdapter");
        super();
        this.sidecar = sidecarInterface0;
        this.sidecarAdapter = sidecarAdapter0;
        this.windowListenerRegisteredContexts = new LinkedHashMap();
        this.componentCallbackMap = new LinkedHashMap();
    }

    public final SidecarInterface getSidecar() {
        return this.sidecar;
    }

    public final WindowLayoutInfo getWindowLayoutInfo(Activity activity0) {
        Intrinsics.checkNotNullParameter(activity0, "activity");
        IBinder iBinder0 = SidecarCompat.Companion.getActivityWindowToken$window_release(activity0);
        if(iBinder0 == null) {
            return new WindowLayoutInfo(CollectionsKt.emptyList());
        }
        SidecarDeviceState sidecarDeviceState0 = null;
        SidecarWindowLayoutInfo sidecarWindowLayoutInfo0 = this.sidecar == null ? null : this.sidecar.getWindowLayoutInfo(iBinder0);
        SidecarAdapter sidecarAdapter0 = this.sidecarAdapter;
        SidecarInterface sidecarInterface0 = this.sidecar;
        if(sidecarInterface0 != null) {
            sidecarDeviceState0 = sidecarInterface0.getDeviceState();
        }
        if(sidecarDeviceState0 == null) {
            sidecarDeviceState0 = new SidecarDeviceState();
        }
        return sidecarAdapter0.translate(sidecarWindowLayoutInfo0, sidecarDeviceState0);
    }

    @Override  // androidx.window.layout.ExtensionInterfaceCompat
    public void onWindowLayoutChangeListenerAdded(Activity activity0) {
        Intrinsics.checkNotNullParameter(activity0, "activity");
        IBinder iBinder0 = SidecarCompat.Companion.getActivityWindowToken$window_release(activity0);
        if(iBinder0 != null) {
            this.register(iBinder0, activity0);
            return;
        }
        FirstAttachAdapter sidecarCompat$FirstAttachAdapter0 = new FirstAttachAdapter(this, activity0);
        activity0.getWindow().getDecorView().addOnAttachStateChangeListener(sidecarCompat$FirstAttachAdapter0);
    }

    @Override  // androidx.window.layout.ExtensionInterfaceCompat
    public void onWindowLayoutChangeListenerRemoved(Activity activity0) {
        Intrinsics.checkNotNullParameter(activity0, "activity");
        IBinder iBinder0 = SidecarCompat.Companion.getActivityWindowToken$window_release(activity0);
        if(iBinder0 != null) {
            SidecarInterface sidecarInterface0 = this.sidecar;
            if(sidecarInterface0 != null) {
                sidecarInterface0.onWindowLayoutChangeListenerRemoved(iBinder0);
            }
            this.unregisterComponentCallback(activity0);
            this.windowListenerRegisteredContexts.remove(iBinder0);
            if(this.windowListenerRegisteredContexts.size() == 1) {
                SidecarInterface sidecarInterface1 = this.sidecar;
                if(sidecarInterface1 != null) {
                    sidecarInterface1.onDeviceStateListenersChanged(true);
                }
            }
        }
    }

    public final void register(IBinder iBinder0, Activity activity0) {
        Intrinsics.checkNotNullParameter(iBinder0, "windowToken");
        Intrinsics.checkNotNullParameter(activity0, "activity");
        this.windowListenerRegisteredContexts.put(iBinder0, activity0);
        SidecarInterface sidecarInterface0 = this.sidecar;
        if(sidecarInterface0 != null) {
            sidecarInterface0.onWindowLayoutChangeListenerAdded(iBinder0);
        }
        if(this.windowListenerRegisteredContexts.size() == 1) {
            SidecarInterface sidecarInterface1 = this.sidecar;
            if(sidecarInterface1 != null) {
                sidecarInterface1.onDeviceStateListenersChanged(false);
            }
        }
        ExtensionCallbackInterface extensionInterfaceCompat$ExtensionCallbackInterface0 = this.extensionCallback;
        if(extensionInterfaceCompat$ExtensionCallbackInterface0 != null) {
            extensionInterfaceCompat$ExtensionCallbackInterface0.onWindowLayoutChanged(activity0, this.getWindowLayoutInfo(activity0));
        }
        this.registerConfigurationChangeListener(activity0);
    }

    private final void registerConfigurationChangeListener(Activity activity0) {
        if(this.componentCallbackMap.get(activity0) == null) {
            androidx.window.layout.SidecarCompat.registerConfigurationChangeListener.configChangeObserver.1 sidecarCompat$registerConfigurationChangeListener$configChangeObserver$10 = new ComponentCallbacks() {
                @Override  // android.content.ComponentCallbacks
                public void onConfigurationChanged(Configuration configuration0) {
                    Intrinsics.checkNotNullParameter(configuration0, "newConfig");
                    ExtensionCallbackInterface extensionInterfaceCompat$ExtensionCallbackInterface0 = activity0.extensionCallback;
                    if(extensionInterfaceCompat$ExtensionCallbackInterface0 == null) {
                        return;
                    }
                    WindowLayoutInfo windowLayoutInfo0 = activity0.getWindowLayoutInfo(this.$activity);
                    extensionInterfaceCompat$ExtensionCallbackInterface0.onWindowLayoutChanged(this.$activity, windowLayoutInfo0);
                }

                @Override  // android.content.ComponentCallbacks
                public void onLowMemory() {
                }
            };
            this.componentCallbackMap.put(activity0, sidecarCompat$registerConfigurationChangeListener$configChangeObserver$10);
            activity0.registerComponentCallbacks(sidecarCompat$registerConfigurationChangeListener$configChangeObserver$10);
        }
    }

    @Override  // androidx.window.layout.ExtensionInterfaceCompat
    public void setExtensionCallback(ExtensionCallbackInterface extensionInterfaceCompat$ExtensionCallbackInterface0) {
        Intrinsics.checkNotNullParameter(extensionInterfaceCompat$ExtensionCallbackInterface0, "extensionCallback");
        this.extensionCallback = new DistinctElementCallback(extensionInterfaceCompat$ExtensionCallbackInterface0);
        SidecarInterface sidecarInterface0 = this.sidecar;
        if(sidecarInterface0 == null) {
            return;
        }
        SidecarInterface.SidecarCallback sidecarInterface$SidecarCallback0 = new TranslatingCallback(this);
        sidecarInterface0.setSidecarCallback(new DistinctSidecarElementCallback(this.sidecarAdapter, sidecarInterface$SidecarCallback0));
    }

    private final void unregisterComponentCallback(Activity activity0) {
        activity0.unregisterComponentCallbacks(((ComponentCallbacks)this.componentCallbackMap.get(activity0)));
        this.componentCallbackMap.remove(activity0);
    }

    @Override  // androidx.window.layout.ExtensionInterfaceCompat
    public boolean validateExtensionInterface() {
        Method method3;
        Method method2;
        Method method1;
        Method method0;
        try {
            SidecarInterface sidecarInterface0 = this.sidecar;
            if(sidecarInterface0 == null) {
                method0 = null;
            }
            else {
                Class class0 = sidecarInterface0.getClass();
                method0 = class0 == null ? null : class0.getMethod("setSidecarCallback", SidecarInterface.SidecarCallback.class);
            }
            if(Intrinsics.areEqual((method0 == null ? null : method0.getReturnType()), Void.TYPE)) {
                SidecarInterface sidecarInterface1 = this.sidecar;
                if(sidecarInterface1 != null) {
                    sidecarInterface1.getDeviceState();
                }
                SidecarInterface sidecarInterface2 = this.sidecar;
                if(sidecarInterface2 != null) {
                    sidecarInterface2.onDeviceStateListenersChanged(true);
                }
                SidecarInterface sidecarInterface3 = this.sidecar;
                if(sidecarInterface3 == null) {
                    method1 = null;
                }
                else {
                    Class class1 = sidecarInterface3.getClass();
                    method1 = class1 == null ? null : class1.getMethod("getWindowLayoutInfo", IBinder.class);
                }
                if(Intrinsics.areEqual((method1 == null ? null : method1.getReturnType()), SidecarWindowLayoutInfo.class)) {
                    SidecarInterface sidecarInterface4 = this.sidecar;
                    if(sidecarInterface4 == null) {
                        method2 = null;
                    }
                    else {
                        Class class2 = sidecarInterface4.getClass();
                        method2 = class2 == null ? null : class2.getMethod("onWindowLayoutChangeListenerAdded", IBinder.class);
                    }
                    if(Intrinsics.areEqual((method2 == null ? null : method2.getReturnType()), Void.TYPE)) {
                        SidecarInterface sidecarInterface5 = this.sidecar;
                        if(sidecarInterface5 == null) {
                            method3 = null;
                        }
                        else {
                            Class class3 = sidecarInterface5.getClass();
                            method3 = class3 == null ? null : class3.getMethod("onWindowLayoutChangeListenerRemoved", IBinder.class);
                        }
                        if(Intrinsics.areEqual((method3 == null ? null : method3.getReturnType()), Void.TYPE)) {
                            new SidecarDeviceState().posture = 3;
                            SidecarDisplayFeature sidecarDisplayFeature0 = new SidecarDisplayFeature();
                            Rect rect0 = sidecarDisplayFeature0.getRect();
                            Intrinsics.checkNotNullExpressionValue(rect0, "displayFeature.rect");
                            sidecarDisplayFeature0.setRect(rect0);
                            sidecarDisplayFeature0.getType();
                            sidecarDisplayFeature0.setType(1);
                            new SidecarWindowLayoutInfo();
                            return true;
                        }
                    }
                }
            }
        }
        catch(Throwable unused_ex) {
        }
        return false;
    }
}

