package androidx.window.layout;

import android.app.Activity;
import androidx.window.extensions.layout.WindowLayoutComponent;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001:\u0001\u0015B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J&\u0010\u000E\u001A\u00020\u000F2\u0006\u0010\u0010\u001A\u00020\u00072\u0006\u0010\u0011\u001A\u00020\u00122\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00020\r0\fH\u0016J\u0016\u0010\u0014\u001A\u00020\u000F2\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00020\r0\fH\u0016R\u001C\u0010\u0005\u001A\u000E\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00068\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u000B\u001A\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0004\u0012\u00020\u00070\u00068\u0002X\u0083\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Landroidx/window/layout/ExtensionWindowLayoutInfoBackend;", "Landroidx/window/layout/WindowBackend;", "component", "Landroidx/window/extensions/layout/WindowLayoutComponent;", "(Landroidx/window/extensions/layout/WindowLayoutComponent;)V", "activityToListeners", "", "Landroid/app/Activity;", "Landroidx/window/layout/ExtensionWindowLayoutInfoBackend$MulticastConsumer;", "extensionWindowBackendLock", "Ljava/util/concurrent/locks/ReentrantLock;", "listenerToActivity", "Landroidx/core/util/Consumer;", "Landroidx/window/layout/WindowLayoutInfo;", "registerLayoutChangeCallback", "", "activity", "executor", "Ljava/util/concurrent/Executor;", "callback", "unregisterLayoutChangeCallback", "MulticastConsumer", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class ExtensionWindowLayoutInfoBackend implements WindowBackend {
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000B\n\u0002\b\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001A\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\r\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u00020\u0002H\u0016J\u0014\u0010\u0010\u001A\u00020\u000E2\f\u0010\u0011\u001A\b\u0012\u0004\u0012\u00020\u00070\fJ\u0006\u0010\u0012\u001A\u00020\u0013J\u0014\u0010\u0014\u001A\u00020\u000E2\f\u0010\u0011\u001A\b\u0012\u0004\u0012\u00020\u00070\fR\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001A\u0004\u0018\u00010\u00078\u0002@\u0002X\u0083\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\b\u001A\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001C\u0010\n\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\f0\u000B8\u0002X\u0083\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Landroidx/window/layout/ExtensionWindowLayoutInfoBackend$MulticastConsumer;", "Ljava/util/function/Consumer;", "Landroidx/window/extensions/layout/WindowLayoutInfo;", "activity", "Landroid/app/Activity;", "(Landroid/app/Activity;)V", "lastKnownValue", "Landroidx/window/layout/WindowLayoutInfo;", "multicastConsumerLock", "Ljava/util/concurrent/locks/ReentrantLock;", "registeredListeners", "", "Landroidx/core/util/Consumer;", "accept", "", "value", "addListener", "listener", "isEmpty", "", "removeListener", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    static final class MulticastConsumer implements Consumer {
        private final Activity activity;
        private WindowLayoutInfo lastKnownValue;
        private final ReentrantLock multicastConsumerLock;
        private final Set registeredListeners;

        public MulticastConsumer(Activity activity0) {
            Intrinsics.checkNotNullParameter(activity0, "activity");
            super();
            this.activity = activity0;
            this.multicastConsumerLock = new ReentrantLock();
            this.registeredListeners = new LinkedHashSet();
        }

        public void accept(androidx.window.extensions.layout.WindowLayoutInfo windowLayoutInfo0) {
            Intrinsics.checkNotNullParameter(windowLayoutInfo0, "value");
            Lock lock0 = this.multicastConsumerLock;
            lock0.lock();
            try {
                this.lastKnownValue = ExtensionsWindowLayoutInfoAdapter.INSTANCE.translate$window_release(this.activity, windowLayoutInfo0);
                for(Object object0: this.registeredListeners) {
                    ((androidx.core.util.Consumer)object0).accept(this.lastKnownValue);
                }
            }
            finally {
                lock0.unlock();
            }
        }

        @Override
        public void accept(Object object0) {
            this.accept(((androidx.window.extensions.layout.WindowLayoutInfo)object0));
        }

        public final void addListener(androidx.core.util.Consumer consumer0) {
            Intrinsics.checkNotNullParameter(consumer0, "listener");
            Lock lock0 = this.multicastConsumerLock;
            lock0.lock();
            try {
                WindowLayoutInfo windowLayoutInfo0 = this.lastKnownValue;
                if(windowLayoutInfo0 != null) {
                    consumer0.accept(windowLayoutInfo0);
                }
                this.registeredListeners.add(consumer0);
            }
            finally {
                lock0.unlock();
            }
        }

        public final boolean isEmpty() {
            return this.registeredListeners.isEmpty();
        }

        public final void removeListener(androidx.core.util.Consumer consumer0) {
            Intrinsics.checkNotNullParameter(consumer0, "listener");
            this.multicastConsumerLock.lock();
            try {
                this.registeredListeners.remove(consumer0);
            }
            finally {
                this.multicastConsumerLock.unlock();
            }
        }
    }

    private final Map activityToListeners;
    private final WindowLayoutComponent component;
    private final ReentrantLock extensionWindowBackendLock;
    private final Map listenerToActivity;

    public ExtensionWindowLayoutInfoBackend(WindowLayoutComponent windowLayoutComponent0) {
        Intrinsics.checkNotNullParameter(windowLayoutComponent0, "component");
        super();
        this.component = windowLayoutComponent0;
        this.extensionWindowBackendLock = new ReentrantLock();
        this.activityToListeners = new LinkedHashMap();
        this.listenerToActivity = new LinkedHashMap();
    }

    @Override  // androidx.window.layout.WindowBackend
    public void registerLayoutChangeCallback(Activity activity0, Executor executor0, androidx.core.util.Consumer consumer0) {
        Unit unit0;
        Intrinsics.checkNotNullParameter(activity0, "activity");
        Intrinsics.checkNotNullParameter(executor0, "executor");
        Intrinsics.checkNotNullParameter(consumer0, "callback");
        Lock lock0 = this.extensionWindowBackendLock;
        lock0.lock();
        try {
            MulticastConsumer extensionWindowLayoutInfoBackend$MulticastConsumer0 = (MulticastConsumer)this.activityToListeners.get(activity0);
            if(extensionWindowLayoutInfoBackend$MulticastConsumer0 == null) {
                unit0 = null;
            }
            else {
                extensionWindowLayoutInfoBackend$MulticastConsumer0.addListener(consumer0);
                this.listenerToActivity.put(consumer0, activity0);
                unit0 = Unit.INSTANCE;
            }
            if(unit0 == null) {
                MulticastConsumer extensionWindowLayoutInfoBackend$MulticastConsumer1 = new MulticastConsumer(activity0);
                this.activityToListeners.put(activity0, extensionWindowLayoutInfoBackend$MulticastConsumer1);
                this.listenerToActivity.put(consumer0, activity0);
                extensionWindowLayoutInfoBackend$MulticastConsumer1.addListener(consumer0);
                this.component.addWindowLayoutInfoListener(activity0, extensionWindowLayoutInfoBackend$MulticastConsumer1);
            }
        }
        finally {
            lock0.unlock();
        }
    }

    @Override  // androidx.window.layout.WindowBackend
    public void unregisterLayoutChangeCallback(androidx.core.util.Consumer consumer0) {
        Intrinsics.checkNotNullParameter(consumer0, "callback");
        Lock lock0 = this.extensionWindowBackendLock;
        lock0.lock();
        try {
            Activity activity0 = (Activity)this.listenerToActivity.get(consumer0);
            if(activity0 == null) {
                return;
            }
            MulticastConsumer extensionWindowLayoutInfoBackend$MulticastConsumer0 = (MulticastConsumer)this.activityToListeners.get(activity0);
            if(extensionWindowLayoutInfoBackend$MulticastConsumer0 == null) {
                return;
            }
            extensionWindowLayoutInfoBackend$MulticastConsumer0.removeListener(consumer0);
            if(extensionWindowLayoutInfoBackend$MulticastConsumer0.isEmpty()) {
                this.component.removeWindowLayoutInfoListener(extensionWindowLayoutInfoBackend$MulticastConsumer0);
            }
        }
        finally {
            lock0.unlock();
        }
    }
}

