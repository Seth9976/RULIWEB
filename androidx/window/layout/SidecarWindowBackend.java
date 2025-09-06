package androidx.window.layout;

import android.app.Activity;
import android.content.Context;
import androidx.core.util.Consumer;
import androidx.window.core.Version;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 \u001C2\u00020\u0001:\u0003\u001C\u001D\u001EB\u0011\b\u0007\u0012\b\u0010\u0002\u001A\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000F\u001A\u00020\u00102\u0006\u0010\u0011\u001A\u00020\u0012H\u0003J\u0010\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0011\u001A\u00020\u0012H\u0002J&\u0010\u0015\u001A\u00020\u00102\u0006\u0010\u0011\u001A\u00020\u00122\u0006\u0010\u0016\u001A\u00020\u00172\f\u0010\u0018\u001A\b\u0012\u0004\u0012\u00020\u001A0\u0019H\u0016J\u0016\u0010\u001B\u001A\u00020\u00102\f\u0010\u0018\u001A\b\u0012\u0004\u0012\u00020\u001A0\u0019H\u0016R \u0010\u0002\u001A\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004R\"\u0010\b\u001A\b\u0012\u0004\u0012\u00020\n0\t8\u0006X\u0087\u0004¢\u0006\u000E\n\u0000\u0012\u0004\b\u000B\u0010\f\u001A\u0004\b\r\u0010\u000E¨\u0006\u001F"}, d2 = {"Landroidx/window/layout/SidecarWindowBackend;", "Landroidx/window/layout/WindowBackend;", "windowExtension", "Landroidx/window/layout/ExtensionInterfaceCompat;", "(Landroidx/window/layout/ExtensionInterfaceCompat;)V", "getWindowExtension", "()Landroidx/window/layout/ExtensionInterfaceCompat;", "setWindowExtension", "windowLayoutChangeCallbacks", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Landroidx/window/layout/SidecarWindowBackend$WindowLayoutChangeCallbackWrapper;", "getWindowLayoutChangeCallbacks$annotations", "()V", "getWindowLayoutChangeCallbacks", "()Ljava/util/concurrent/CopyOnWriteArrayList;", "callbackRemovedForActivity", "", "activity", "Landroid/app/Activity;", "isActivityRegistered", "", "registerLayoutChangeCallback", "executor", "Ljava/util/concurrent/Executor;", "callback", "Landroidx/core/util/Consumer;", "Landroidx/window/layout/WindowLayoutInfo;", "unregisterLayoutChangeCallback", "Companion", "ExtensionListenerImpl", "WindowLayoutChangeCallbackWrapper", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class SidecarWindowBackend implements WindowBackend {
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000E\u0010\u000B\u001A\u00020\b2\u0006\u0010\f\u001A\u00020\rJ\u0010\u0010\u000E\u001A\u0004\u0018\u00010\u000F2\u0006\u0010\f\u001A\u00020\rJ\u0012\u0010\u0010\u001A\u00020\u00042\b\u0010\u0011\u001A\u0004\u0018\u00010\u0012H\u0007J\b\u0010\u0013\u001A\u00020\u0014H\u0007R\u000E\u0010\u0003\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001A\u0004\u0018\u00010\bX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Landroidx/window/layout/SidecarWindowBackend$Companion;", "", "()V", "DEBUG", "", "TAG", "", "globalInstance", "Landroidx/window/layout/SidecarWindowBackend;", "globalLock", "Ljava/util/concurrent/locks/ReentrantLock;", "getInstance", "context", "Landroid/content/Context;", "initAndVerifyExtension", "Landroidx/window/layout/ExtensionInterfaceCompat;", "isSidecarVersionSupported", "sidecarVersion", "Landroidx/window/core/Version;", "resetInstance", "", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final SidecarWindowBackend getInstance(Context context0) {
            Intrinsics.checkNotNullParameter(context0, "context");
            if(SidecarWindowBackend.globalInstance == null) {
                Lock lock0 = SidecarWindowBackend.globalLock;
                lock0.lock();
                try {
                    if(SidecarWindowBackend.globalInstance == null) {
                        SidecarWindowBackend.globalInstance = new SidecarWindowBackend(SidecarWindowBackend.Companion.initAndVerifyExtension(context0));
                    }
                }
                finally {
                    lock0.unlock();
                }
            }
            SidecarWindowBackend sidecarWindowBackend0 = SidecarWindowBackend.globalInstance;
            Intrinsics.checkNotNull(sidecarWindowBackend0);
            return sidecarWindowBackend0;
        }

        public final ExtensionInterfaceCompat initAndVerifyExtension(Context context0) {
            Intrinsics.checkNotNullParameter(context0, "context");
            try {
                if(!this.isSidecarVersionSupported(SidecarCompat.Companion.getSidecarVersion())) {
                    return null;
                }
                ExtensionInterfaceCompat extensionInterfaceCompat0 = new SidecarCompat(context0);
                return !((SidecarCompat)extensionInterfaceCompat0).validateExtensionInterface() ? null : extensionInterfaceCompat0;
            }
            catch(Throwable unused_ex) {
            }
            return null;
        }

        public final boolean isSidecarVersionSupported(Version version0) {
            return version0 == null ? false : version0.compareTo(Version.Companion.getVERSION_0_1()) >= 0;
        }

        public final void resetInstance() {
            SidecarWindowBackend.globalInstance = null;
        }
    }

    @Metadata(d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0081\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH\u0017¨\u0006\t"}, d2 = {"Landroidx/window/layout/SidecarWindowBackend$ExtensionListenerImpl;", "Landroidx/window/layout/ExtensionInterfaceCompat$ExtensionCallbackInterface;", "(Landroidx/window/layout/SidecarWindowBackend;)V", "onWindowLayoutChanged", "", "activity", "Landroid/app/Activity;", "newLayout", "Landroidx/window/layout/WindowLayoutInfo;", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public final class ExtensionListenerImpl implements ExtensionCallbackInterface {
        public ExtensionListenerImpl() {
            Intrinsics.checkNotNullParameter(sidecarWindowBackend0, "this$0");
            SidecarWindowBackend.this = sidecarWindowBackend0;
            super();
        }

        @Override  // androidx.window.layout.ExtensionInterfaceCompat$ExtensionCallbackInterface
        public void onWindowLayoutChanged(Activity activity0, WindowLayoutInfo windowLayoutInfo0) {
            Intrinsics.checkNotNullParameter(activity0, "activity");
            Intrinsics.checkNotNullParameter(windowLayoutInfo0, "newLayout");
            for(Object object0: SidecarWindowBackend.this.getWindowLayoutChangeCallbacks()) {
                WindowLayoutChangeCallbackWrapper sidecarWindowBackend$WindowLayoutChangeCallbackWrapper0 = (WindowLayoutChangeCallbackWrapper)object0;
                if(Intrinsics.areEqual(sidecarWindowBackend$WindowLayoutChangeCallbackWrapper0.getActivity(), activity0)) {
                    sidecarWindowBackend$WindowLayoutChangeCallbackWrapper0.accept(windowLayoutInfo0);
                }
            }
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000B\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\f\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ\u000E\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0015\u001A\u00020\bR\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\n\u0010\u000BR\u0017\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001A\u0004\b\f\u0010\rR\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001C\u0010\u000E\u001A\u0004\u0018\u00010\bX\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u000F\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u0016"}, d2 = {"Landroidx/window/layout/SidecarWindowBackend$WindowLayoutChangeCallbackWrapper;", "", "activity", "Landroid/app/Activity;", "executor", "Ljava/util/concurrent/Executor;", "callback", "Landroidx/core/util/Consumer;", "Landroidx/window/layout/WindowLayoutInfo;", "(Landroid/app/Activity;Ljava/util/concurrent/Executor;Landroidx/core/util/Consumer;)V", "getActivity", "()Landroid/app/Activity;", "getCallback", "()Landroidx/core/util/Consumer;", "lastInfo", "getLastInfo", "()Landroidx/window/layout/WindowLayoutInfo;", "setLastInfo", "(Landroidx/window/layout/WindowLayoutInfo;)V", "accept", "", "newLayoutInfo", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public static final class WindowLayoutChangeCallbackWrapper {
        private final Activity activity;
        private final Consumer callback;
        private final Executor executor;
        private WindowLayoutInfo lastInfo;

        // 检测为 Lambda 实现
        public static void $r8$lambda$ANrvAZ-VAwktb4ej0IJxPGmbc5c(WindowLayoutChangeCallbackWrapper sidecarWindowBackend$WindowLayoutChangeCallbackWrapper0, WindowLayoutInfo windowLayoutInfo0) [...]

        public WindowLayoutChangeCallbackWrapper(Activity activity0, Executor executor0, Consumer consumer0) {
            Intrinsics.checkNotNullParameter(activity0, "activity");
            Intrinsics.checkNotNullParameter(executor0, "executor");
            Intrinsics.checkNotNullParameter(consumer0, "callback");
            super();
            this.activity = activity0;
            this.executor = executor0;
            this.callback = consumer0;
        }

        public final void accept(WindowLayoutInfo windowLayoutInfo0) {
            Intrinsics.checkNotNullParameter(windowLayoutInfo0, "newLayoutInfo");
            this.lastInfo = windowLayoutInfo0;
            SidecarWindowBackend.WindowLayoutChangeCallbackWrapper..ExternalSyntheticLambda0 sidecarWindowBackend$WindowLayoutChangeCallbackWrapper$$ExternalSyntheticLambda00 = () -> WindowLayoutChangeCallbackWrapper.accept$lambda-0(this, windowLayoutInfo0);
            this.executor.execute(sidecarWindowBackend$WindowLayoutChangeCallbackWrapper$$ExternalSyntheticLambda00);
        }

        private static final void accept$lambda-0(WindowLayoutChangeCallbackWrapper sidecarWindowBackend$WindowLayoutChangeCallbackWrapper0, WindowLayoutInfo windowLayoutInfo0) {
            Intrinsics.checkNotNullParameter(sidecarWindowBackend$WindowLayoutChangeCallbackWrapper0, "this$0");
            Intrinsics.checkNotNullParameter(windowLayoutInfo0, "$newLayoutInfo");
            sidecarWindowBackend$WindowLayoutChangeCallbackWrapper0.callback.accept(windowLayoutInfo0);
        }

        public final Activity getActivity() {
            return this.activity;
        }

        public final Consumer getCallback() {
            return this.callback;
        }

        public final WindowLayoutInfo getLastInfo() {
            return this.lastInfo;
        }

        public final void setLastInfo(WindowLayoutInfo windowLayoutInfo0) {
            this.lastInfo = windowLayoutInfo0;
        }
    }

    public static final Companion Companion = null;
    public static final boolean DEBUG = false;
    private static final String TAG = "WindowServer";
    private static volatile SidecarWindowBackend globalInstance;
    private static final ReentrantLock globalLock;
    private ExtensionInterfaceCompat windowExtension;
    private final CopyOnWriteArrayList windowLayoutChangeCallbacks;

    static {
        SidecarWindowBackend.Companion = new Companion(null);
        SidecarWindowBackend.globalLock = new ReentrantLock();
    }

    public SidecarWindowBackend(ExtensionInterfaceCompat extensionInterfaceCompat0) {
        this.windowExtension = extensionInterfaceCompat0;
        this.windowLayoutChangeCallbacks = new CopyOnWriteArrayList();
        ExtensionInterfaceCompat extensionInterfaceCompat1 = this.windowExtension;
        if(extensionInterfaceCompat1 == null) {
            return;
        }
        extensionInterfaceCompat1.setExtensionCallback(new ExtensionListenerImpl(this));
    }

    private final void callbackRemovedForActivity(Activity activity0) {
        Iterable iterable0 = this.windowLayoutChangeCallbacks;
        if(!(iterable0 instanceof Collection) || !((Collection)iterable0).isEmpty()) {
            for(Object object0: iterable0) {
                if(Intrinsics.areEqual(((WindowLayoutChangeCallbackWrapper)object0).getActivity(), activity0)) {
                    return;
                }
                if(false) {
                    break;
                }
            }
        }
        ExtensionInterfaceCompat extensionInterfaceCompat0 = this.windowExtension;
        if(extensionInterfaceCompat0 == null) {
            return;
        }
        extensionInterfaceCompat0.onWindowLayoutChangeListenerRemoved(activity0);
    }

    public final ExtensionInterfaceCompat getWindowExtension() {
        return this.windowExtension;
    }

    public final CopyOnWriteArrayList getWindowLayoutChangeCallbacks() {
        return this.windowLayoutChangeCallbacks;
    }

    public static void getWindowLayoutChangeCallbacks$annotations() {
    }

    private final boolean isActivityRegistered(Activity activity0) {
        Iterable iterable0 = this.windowLayoutChangeCallbacks;
        if(iterable0 instanceof Collection && ((Collection)iterable0).isEmpty()) {
            return false;
        }
        for(Object object0: iterable0) {
            if(Intrinsics.areEqual(((WindowLayoutChangeCallbackWrapper)object0).getActivity(), activity0)) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    @Override  // androidx.window.layout.WindowBackend
    public void registerLayoutChangeCallback(Activity activity0, Executor executor0, Consumer consumer0) {
        WindowLayoutInfo windowLayoutInfo0;
        Intrinsics.checkNotNullParameter(activity0, "activity");
        Intrinsics.checkNotNullParameter(executor0, "executor");
        Intrinsics.checkNotNullParameter(consumer0, "callback");
        Lock lock0 = SidecarWindowBackend.globalLock;
        lock0.lock();
        try {
            ExtensionInterfaceCompat extensionInterfaceCompat0 = this.getWindowExtension();
            if(extensionInterfaceCompat0 == null) {
                consumer0.accept(new WindowLayoutInfo(CollectionsKt.emptyList()));
                return;
            }
            boolean z = this.isActivityRegistered(activity0);
            WindowLayoutChangeCallbackWrapper sidecarWindowBackend$WindowLayoutChangeCallbackWrapper0 = new WindowLayoutChangeCallbackWrapper(activity0, executor0, consumer0);
            this.getWindowLayoutChangeCallbacks().add(sidecarWindowBackend$WindowLayoutChangeCallbackWrapper0);
            if(z) {
                Object object0 = null;
                Iterator iterator0 = this.getWindowLayoutChangeCallbacks().iterator();
                while(true) {
                    windowLayoutInfo0 = null;
                    if(!iterator0.hasNext()) {
                        break;
                    }
                    Object object1 = iterator0.next();
                    if(Intrinsics.areEqual(activity0, ((WindowLayoutChangeCallbackWrapper)object1).getActivity())) {
                        object0 = object1;
                        break;
                    }
                }
                if(((WindowLayoutChangeCallbackWrapper)object0) != null) {
                    windowLayoutInfo0 = ((WindowLayoutChangeCallbackWrapper)object0).getLastInfo();
                }
                if(windowLayoutInfo0 != null) {
                    sidecarWindowBackend$WindowLayoutChangeCallbackWrapper0.accept(windowLayoutInfo0);
                }
            }
            else {
                extensionInterfaceCompat0.onWindowLayoutChangeListenerAdded(activity0);
            }
        }
        finally {
            lock0.unlock();
        }
    }

    public final void setWindowExtension(ExtensionInterfaceCompat extensionInterfaceCompat0) {
        this.windowExtension = extensionInterfaceCompat0;
    }

    @Override  // androidx.window.layout.WindowBackend
    public void unregisterLayoutChangeCallback(Consumer consumer0) {
        Intrinsics.checkNotNullParameter(consumer0, "callback");
        synchronized(SidecarWindowBackend.globalLock) {
            if(this.getWindowExtension() == null) {
                return;
            }
            List list0 = new ArrayList();
            for(Object object0: this.getWindowLayoutChangeCallbacks()) {
                WindowLayoutChangeCallbackWrapper sidecarWindowBackend$WindowLayoutChangeCallbackWrapper0 = (WindowLayoutChangeCallbackWrapper)object0;
                if(sidecarWindowBackend$WindowLayoutChangeCallbackWrapper0.getCallback() == consumer0) {
                    Intrinsics.checkNotNullExpressionValue(sidecarWindowBackend$WindowLayoutChangeCallbackWrapper0, "callbackWrapper");
                    list0.add(sidecarWindowBackend$WindowLayoutChangeCallbackWrapper0);
                }
            }
            this.getWindowLayoutChangeCallbacks().removeAll(list0);
            for(Object object1: list0) {
                this.callbackRemovedForActivity(((WindowLayoutChangeCallbackWrapper)object1).getActivity());
            }
        }
    }
}

