package androidx.activity;

import android.os.Build.VERSION;
import android.window.BackEvent;
import android.window.OnBackAnimationCallback;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.core.util.Consumer;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.Lifecycle.State;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import java.util.Collection;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000B\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000F\u0018\u00002\u00020\u0001:\u0004)*+,B\u0013\b\u0017\u0012\n\b\u0002\u0010\u0002\u001A\u0004\u0018\u00010\u0003\u00A2\u0006\u0002\u0010\u0004B\u001F\u0012\b\u0010\u0002\u001A\u0004\u0018\u00010\u0003\u0012\u000E\u0010\u0005\u001A\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006\u00A2\u0006\u0002\u0010\bJ\u0010\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0015\u001A\u00020\fH\u0007J\u0018\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0016\u001A\u00020\u00172\u0006\u0010\u0015\u001A\u00020\fH\u0007J\u0015\u0010\u0018\u001A\u00020\u00192\u0006\u0010\u0015\u001A\u00020\fH\u0001\u00A2\u0006\u0002\b\u001AJ\b\u0010\u001B\u001A\u00020\u0014H\u0007J\u0010\u0010\u001C\u001A\u00020\u00142\u0006\u0010\u001D\u001A\u00020\u001EH\u0007J\u0010\u0010\u001F\u001A\u00020\u00142\u0006\u0010\u001D\u001A\u00020\u001EH\u0007J\b\u0010\n\u001A\u00020\u0007H\u0007J\b\u0010 \u001A\u00020\u0014H\u0003J\b\u0010!\u001A\u00020\u0014H\u0007J\u0010\u0010\"\u001A\u00020\u00142\u0006\u0010\u001D\u001A\u00020\u001EH\u0003J\u0010\u0010#\u001A\u00020\u00142\u0006\u0010\u001D\u001A\u00020\u001EH\u0003J\u0010\u0010$\u001A\u00020\u00142\u0006\u0010%\u001A\u00020\u000EH\u0007J\u0010\u0010&\u001A\u00020\u00142\u0006\u0010\'\u001A\u00020\u0007H\u0003J\b\u0010(\u001A\u00020\u0014H\u0002R\u000E\u0010\t\u001A\u00020\u0007X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001A\u0004\u0018\u00010\u0003X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\n\u001A\u00020\u0007X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u000B\u001A\u0004\u0018\u00010\fX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0010\u0010\r\u001A\u0004\u0018\u00010\u000EX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u000F\u001A\u0004\u0018\u00010\u0010X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001A\b\u0012\u0004\u0012\u00020\f0\u0012X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001A\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0082\u0004\u00A2\u0006\u0002\n\u0000\u00A8\u0006-"}, d2 = {"Landroidx/activity/OnBackPressedDispatcher;", "", "fallbackOnBackPressed", "Ljava/lang/Runnable;", "(Ljava/lang/Runnable;)V", "onHasEnabledCallbacksChanged", "Landroidx/core/util/Consumer;", "", "(Ljava/lang/Runnable;Landroidx/core/util/Consumer;)V", "backInvokedCallbackRegistered", "hasEnabledCallbacks", "inProgressCallback", "Landroidx/activity/OnBackPressedCallback;", "invokedDispatcher", "Landroid/window/OnBackInvokedDispatcher;", "onBackInvokedCallback", "Landroid/window/OnBackInvokedCallback;", "onBackPressedCallbacks", "Lkotlin/collections/ArrayDeque;", "addCallback", "", "onBackPressedCallback", "owner", "Landroidx/lifecycle/LifecycleOwner;", "addCancellableCallback", "Landroidx/activity/Cancellable;", "addCancellableCallback$activity_release", "dispatchOnBackCancelled", "dispatchOnBackProgressed", "backEvent", "Landroidx/activity/BackEventCompat;", "dispatchOnBackStarted", "onBackCancelled", "onBackPressed", "onBackProgressed", "onBackStarted", "setOnBackInvokedDispatcher", "invoker", "updateBackInvokedCallbackState", "shouldBeRegistered", "updateEnabledCallbacks", "Api33Impl", "Api34Impl", "LifecycleOnBackPressedCancellable", "OnBackPressedCancellable", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class OnBackPressedDispatcher {
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001A\u00020\u00042\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u00020\u00070\u0006J\u001E\u0010\b\u001A\u00020\u00072\u0006\u0010\t\u001A\u00020\u00012\u0006\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\u0001J\u0016\u0010\r\u001A\u00020\u00072\u0006\u0010\t\u001A\u00020\u00012\u0006\u0010\f\u001A\u00020\u0001¨\u0006\u000E"}, d2 = {"Landroidx/activity/OnBackPressedDispatcher$Api33Impl;", "", "()V", "createOnBackInvokedCallback", "Landroid/window/OnBackInvokedCallback;", "onBackInvoked", "Lkotlin/Function0;", "", "registerOnBackInvokedCallback", "dispatcher", "priority", "", "callback", "unregisterOnBackInvokedCallback", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Api33Impl {
        public static final Api33Impl INSTANCE;

        static {
            Api33Impl.INSTANCE = new Api33Impl();
        }

        public final OnBackInvokedCallback createOnBackInvokedCallback(Function0 function00) {
            Intrinsics.checkNotNullParameter(function00, "onBackInvoked");
            return () -> function00.invoke();
        }

        // 检测为 Lambda 实现
        private static final void createOnBackInvokedCallback$lambda$0(Function0 function00) [...]

        public final void registerOnBackInvokedCallback(Object object0, int v, Object object1) {
            Intrinsics.checkNotNullParameter(object0, "dispatcher");
            Intrinsics.checkNotNullParameter(object1, "callback");
            ((OnBackInvokedDispatcher)object0).registerOnBackInvokedCallback(v, ((OnBackInvokedCallback)object1));
        }

        public final void unregisterOnBackInvokedCallback(Object object0, Object object1) {
            Intrinsics.checkNotNullParameter(object0, "dispatcher");
            Intrinsics.checkNotNullParameter(object1, "callback");
            ((OnBackInvokedDispatcher)object0).unregisterOnBackInvokedCallback(((OnBackInvokedCallback)object1));
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002Jh\u0010\u0003\u001A\u00020\u00042!\u0010\u0005\u001A\u001D\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000B0\u00062!\u0010\f\u001A\u001D\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000B0\u00062\f\u0010\r\u001A\b\u0012\u0004\u0012\u00020\u000B0\u000E2\f\u0010\u000F\u001A\b\u0012\u0004\u0012\u00020\u000B0\u000E¨\u0006\u0010"}, d2 = {"Landroidx/activity/OnBackPressedDispatcher$Api34Impl;", "", "()V", "createOnBackAnimationCallback", "Landroid/window/OnBackInvokedCallback;", "onBackStarted", "Lkotlin/Function1;", "Landroidx/activity/BackEventCompat;", "Lkotlin/ParameterName;", "name", "backEvent", "", "onBackProgressed", "onBackInvoked", "Lkotlin/Function0;", "onBackCancelled", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Api34Impl {
        public static final Api34Impl INSTANCE;

        static {
            Api34Impl.INSTANCE = new Api34Impl();
        }

        public final OnBackInvokedCallback createOnBackAnimationCallback(Function1 function10, Function1 function11, Function0 function00, Function0 function01) {
            Intrinsics.checkNotNullParameter(function10, "onBackStarted");
            Intrinsics.checkNotNullParameter(function11, "onBackProgressed");
            Intrinsics.checkNotNullParameter(function00, "onBackInvoked");
            Intrinsics.checkNotNullParameter(function01, "onBackCancelled");
            return new OnBackAnimationCallback() {
                @Override  // android.window.OnBackAnimationCallback
                public void onBackCancelled() {
                    this.$onBackCancelled.invoke();
                }

                @Override  // android.window.OnBackInvokedCallback
                public void onBackInvoked() {
                    function01.invoke();
                }

                @Override  // android.window.OnBackAnimationCallback
                public void onBackProgressed(BackEvent backEvent0) {
                    Intrinsics.checkNotNullParameter(backEvent0, "backEvent");
                    BackEventCompat backEventCompat0 = new BackEventCompat(backEvent0);
                    function00.invoke(backEventCompat0);
                }

                @Override  // android.window.OnBackAnimationCallback
                public void onBackStarted(BackEvent backEvent0) {
                    Intrinsics.checkNotNullParameter(backEvent0, "backEvent");
                    BackEventCompat backEventCompat0 = new BackEventCompat(backEvent0);
                    function11.invoke(backEventCompat0);
                }
            };
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u0012\u0006\u0010\u0005\u001A\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\t\u001A\u00020\nH\u0016J\u0018\u0010\u000B\u001A\u00020\n2\u0006\u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\u000FH\u0016R\u0010\u0010\b\u001A\u0004\u0018\u00010\u0002X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Landroidx/activity/OnBackPressedDispatcher$LifecycleOnBackPressedCancellable;", "Landroidx/lifecycle/LifecycleEventObserver;", "Landroidx/activity/Cancellable;", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "onBackPressedCallback", "Landroidx/activity/OnBackPressedCallback;", "(Landroidx/activity/OnBackPressedDispatcher;Landroidx/lifecycle/Lifecycle;Landroidx/activity/OnBackPressedCallback;)V", "currentCancellable", "cancel", "", "onStateChanged", "source", "Landroidx/lifecycle/LifecycleOwner;", "event", "Landroidx/lifecycle/Lifecycle$Event;", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    final class LifecycleOnBackPressedCancellable implements Cancellable, LifecycleEventObserver {
        private Cancellable currentCancellable;
        private final Lifecycle lifecycle;
        private final OnBackPressedCallback onBackPressedCallback;

        public LifecycleOnBackPressedCancellable(Lifecycle lifecycle0, OnBackPressedCallback onBackPressedCallback0) {
            Intrinsics.checkNotNullParameter(lifecycle0, "lifecycle");
            Intrinsics.checkNotNullParameter(onBackPressedCallback0, "onBackPressedCallback");
            OnBackPressedDispatcher.this = onBackPressedDispatcher0;
            super();
            this.lifecycle = lifecycle0;
            this.onBackPressedCallback = onBackPressedCallback0;
            lifecycle0.addObserver(this);
        }

        @Override  // androidx.activity.Cancellable
        public void cancel() {
            this.lifecycle.removeObserver(this);
            this.onBackPressedCallback.removeCancellable(this);
            Cancellable cancellable0 = this.currentCancellable;
            if(cancellable0 != null) {
                cancellable0.cancel();
            }
            this.currentCancellable = null;
        }

        @Override  // androidx.lifecycle.LifecycleEventObserver
        public void onStateChanged(LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) {
            Intrinsics.checkNotNullParameter(lifecycleOwner0, "source");
            Intrinsics.checkNotNullParameter(lifecycle$Event0, "event");
            if(lifecycle$Event0 == Event.ON_START) {
                this.currentCancellable = OnBackPressedDispatcher.this.addCancellableCallback$activity_release(this.onBackPressedCallback);
                return;
            }
            if(lifecycle$Event0 == Event.ON_STOP) {
                Cancellable cancellable0 = this.currentCancellable;
                if(cancellable0 != null) {
                    cancellable0.cancel();
                }
            }
            else if(lifecycle$Event0 == Event.ON_DESTROY) {
                this.cancel();
            }
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001A\u00020\u0006H\u0016R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Landroidx/activity/OnBackPressedDispatcher$OnBackPressedCancellable;", "Landroidx/activity/Cancellable;", "onBackPressedCallback", "Landroidx/activity/OnBackPressedCallback;", "(Landroidx/activity/OnBackPressedDispatcher;Landroidx/activity/OnBackPressedCallback;)V", "cancel", "", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    final class OnBackPressedCancellable implements Cancellable {
        private final OnBackPressedCallback onBackPressedCallback;

        public OnBackPressedCancellable(OnBackPressedCallback onBackPressedCallback0) {
            Intrinsics.checkNotNullParameter(onBackPressedCallback0, "onBackPressedCallback");
            OnBackPressedDispatcher.this = onBackPressedDispatcher0;
            super();
            this.onBackPressedCallback = onBackPressedCallback0;
        }

        @Override  // androidx.activity.Cancellable
        public void cancel() {
            OnBackPressedDispatcher.this.onBackPressedCallbacks.remove(this.onBackPressedCallback);
            if(Intrinsics.areEqual(OnBackPressedDispatcher.this.inProgressCallback, this.onBackPressedCallback)) {
                OnBackPressedDispatcher.this.inProgressCallback = null;
            }
            this.onBackPressedCallback.removeCancellable(this);
            Function0 function00 = this.onBackPressedCallback.getEnabledChangedCallback$activity_release();
            if(function00 != null) {
                function00.invoke();
            }
            this.onBackPressedCallback.setEnabledChangedCallback$activity_release(null);
        }
    }

    private boolean backInvokedCallbackRegistered;
    private final Runnable fallbackOnBackPressed;
    private boolean hasEnabledCallbacks;
    private OnBackPressedCallback inProgressCallback;
    private OnBackInvokedDispatcher invokedDispatcher;
    private OnBackInvokedCallback onBackInvokedCallback;
    private final ArrayDeque onBackPressedCallbacks;
    private final Consumer onHasEnabledCallbacksChanged;

    public OnBackPressedDispatcher() {
        this(null, 1, null);
    }

    public OnBackPressedDispatcher(Runnable runnable0) {
        this(runnable0, null);
    }

    public OnBackPressedDispatcher(Runnable runnable0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 1) != 0) {
            runnable0 = null;
        }
        this(runnable0);
    }

    public OnBackPressedDispatcher(Runnable runnable0, Consumer consumer0) {
        OnBackInvokedCallback onBackInvokedCallback0;
        this.fallbackOnBackPressed = runnable0;
        this.onHasEnabledCallbacksChanged = consumer0;
        this.onBackPressedCallbacks = new ArrayDeque();
        if(Build.VERSION.SDK_INT >= 33) {
            if(Build.VERSION.SDK_INT >= 34) {
                Function1 function10 = new Function1() {
                    {
                        OnBackPressedDispatcher.this = onBackPressedDispatcher0;
                        super(1);
                    }

                    @Override  // kotlin.jvm.functions.Function1
                    public Object invoke(Object object0) {
                        this.invoke(((BackEventCompat)object0));
                        return Unit.INSTANCE;
                    }

                    public final void invoke(BackEventCompat backEventCompat0) {
                        Intrinsics.checkNotNullParameter(backEventCompat0, "backEvent");
                        OnBackPressedDispatcher.this.onBackStarted(backEventCompat0);
                    }
                };
                Function1 function11 = new Function1() {
                    {
                        OnBackPressedDispatcher.this = onBackPressedDispatcher0;
                        super(1);
                    }

                    @Override  // kotlin.jvm.functions.Function1
                    public Object invoke(Object object0) {
                        this.invoke(((BackEventCompat)object0));
                        return Unit.INSTANCE;
                    }

                    public final void invoke(BackEventCompat backEventCompat0) {
                        Intrinsics.checkNotNullParameter(backEventCompat0, "backEvent");
                        OnBackPressedDispatcher.this.onBackProgressed(backEventCompat0);
                    }
                };
                Function0 function00 = new Function0() {
                    {
                        OnBackPressedDispatcher.this = onBackPressedDispatcher0;
                        super(0);
                    }

                    @Override  // kotlin.jvm.functions.Function0
                    public Object invoke() {
                        this.invoke();
                        return Unit.INSTANCE;
                    }

                    public final void invoke() {
                        OnBackPressedDispatcher.this.onBackPressed();
                    }
                };
                Function0 function01 = new Function0() {
                    {
                        OnBackPressedDispatcher.this = onBackPressedDispatcher0;
                        super(0);
                    }

                    @Override  // kotlin.jvm.functions.Function0
                    public Object invoke() {
                        this.invoke();
                        return Unit.INSTANCE;
                    }

                    public final void invoke() {
                        OnBackPressedDispatcher.this.onBackCancelled();
                    }
                };
                onBackInvokedCallback0 = Api34Impl.INSTANCE.createOnBackAnimationCallback(function10, function11, function00, function01);
            }
            else {
                Function0 function02 = new Function0() {
                    {
                        OnBackPressedDispatcher.this = onBackPressedDispatcher0;
                        super(0);
                    }

                    @Override  // kotlin.jvm.functions.Function0
                    public Object invoke() {
                        this.invoke();
                        return Unit.INSTANCE;
                    }

                    public final void invoke() {
                        OnBackPressedDispatcher.this.onBackPressed();
                    }
                };
                onBackInvokedCallback0 = Api33Impl.INSTANCE.createOnBackInvokedCallback(function02);
            }
            this.onBackInvokedCallback = onBackInvokedCallback0;
        }
    }

    public final void addCallback(OnBackPressedCallback onBackPressedCallback0) {
        Intrinsics.checkNotNullParameter(onBackPressedCallback0, "onBackPressedCallback");
        this.addCancellableCallback$activity_release(onBackPressedCallback0);
    }

    public final void addCallback(LifecycleOwner lifecycleOwner0, OnBackPressedCallback onBackPressedCallback0) {
        Intrinsics.checkNotNullParameter(lifecycleOwner0, "owner");
        Intrinsics.checkNotNullParameter(onBackPressedCallback0, "onBackPressedCallback");
        Lifecycle lifecycle0 = lifecycleOwner0.getLifecycle();
        if(lifecycle0.getCurrentState() == State.DESTROYED) {
            return;
        }
        onBackPressedCallback0.addCancellable(new LifecycleOnBackPressedCancellable(this, lifecycle0, onBackPressedCallback0));
        this.updateEnabledCallbacks();
        onBackPressedCallback0.setEnabledChangedCallback$activity_release(new Function0() {
            {
                super(0, object0, OnBackPressedDispatcher.class, "updateEnabledCallbacks", "updateEnabledCallbacks()V", 0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                this.invoke();
                return Unit.INSTANCE;
            }

            public final void invoke() {
                ((OnBackPressedDispatcher)this.receiver).updateEnabledCallbacks();
            }
        });
    }

    public final Cancellable addCancellableCallback$activity_release(OnBackPressedCallback onBackPressedCallback0) {
        Intrinsics.checkNotNullParameter(onBackPressedCallback0, "onBackPressedCallback");
        this.onBackPressedCallbacks.add(onBackPressedCallback0);
        Cancellable cancellable0 = new OnBackPressedCancellable(this, onBackPressedCallback0);
        onBackPressedCallback0.addCancellable(cancellable0);
        this.updateEnabledCallbacks();
        onBackPressedCallback0.setEnabledChangedCallback$activity_release(new Function0() {
            {
                super(0, object0, OnBackPressedDispatcher.class, "updateEnabledCallbacks", "updateEnabledCallbacks()V", 0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                this.invoke();
                return Unit.INSTANCE;
            }

            public final void invoke() {
                ((OnBackPressedDispatcher)this.receiver).updateEnabledCallbacks();
            }
        });
        return cancellable0;
    }

    public final void dispatchOnBackCancelled() {
        this.onBackCancelled();
    }

    public final void dispatchOnBackProgressed(BackEventCompat backEventCompat0) {
        Intrinsics.checkNotNullParameter(backEventCompat0, "backEvent");
        this.onBackProgressed(backEventCompat0);
    }

    public final void dispatchOnBackStarted(BackEventCompat backEventCompat0) {
        Intrinsics.checkNotNullParameter(backEventCompat0, "backEvent");
        this.onBackStarted(backEventCompat0);
    }

    public final boolean hasEnabledCallbacks() {
        return this.hasEnabledCallbacks;
    }

    private final void onBackCancelled() {
        if(this.inProgressCallback == null) {
            Object object0 = null;
            ListIterator listIterator0 = this.onBackPressedCallbacks.listIterator(this.onBackPressedCallbacks.size());
            while(listIterator0.hasPrevious()) {
                Object object1 = listIterator0.previous();
                if(((OnBackPressedCallback)object1).isEnabled()) {
                    object0 = object1;
                    break;
                }
            }
            OnBackPressedCallback onBackPressedCallback0 = (OnBackPressedCallback)object0;
        }
        this.inProgressCallback = null;
    }

    public final void onBackPressed() {
        OnBackPressedCallback onBackPressedCallback0 = this.inProgressCallback;
        if(onBackPressedCallback0 == null) {
            Object object0 = null;
            ListIterator listIterator0 = this.onBackPressedCallbacks.listIterator(this.onBackPressedCallbacks.size());
            while(listIterator0.hasPrevious()) {
                Object object1 = listIterator0.previous();
                if(((OnBackPressedCallback)object1).isEnabled()) {
                    object0 = object1;
                    break;
                }
            }
            onBackPressedCallback0 = (OnBackPressedCallback)object0;
        }
        this.inProgressCallback = null;
        if(onBackPressedCallback0 != null) {
            onBackPressedCallback0.handleOnBackPressed();
            return;
        }
        Runnable runnable0 = this.fallbackOnBackPressed;
        if(runnable0 != null) {
            runnable0.run();
        }
    }

    private final void onBackProgressed(BackEventCompat backEventCompat0) {
        OnBackPressedCallback onBackPressedCallback0 = this.inProgressCallback;
        if(onBackPressedCallback0 == null) {
            Object object0 = null;
            ListIterator listIterator0 = this.onBackPressedCallbacks.listIterator(this.onBackPressedCallbacks.size());
            while(listIterator0.hasPrevious()) {
                Object object1 = listIterator0.previous();
                if(((OnBackPressedCallback)object1).isEnabled()) {
                    object0 = object1;
                    break;
                }
            }
            onBackPressedCallback0 = (OnBackPressedCallback)object0;
        }
        if(onBackPressedCallback0 != null) {
            onBackPressedCallback0.handleOnBackProgressed(backEventCompat0);
        }
    }

    private final void onBackStarted(BackEventCompat backEventCompat0) {
        Object object0 = null;
        ListIterator listIterator0 = this.onBackPressedCallbacks.listIterator(this.onBackPressedCallbacks.size());
        while(listIterator0.hasPrevious()) {
            Object object1 = listIterator0.previous();
            if(((OnBackPressedCallback)object1).isEnabled()) {
                object0 = object1;
                break;
            }
        }
        if(this.inProgressCallback != null) {
            this.onBackCancelled();
        }
        this.inProgressCallback = (OnBackPressedCallback)object0;
        if(((OnBackPressedCallback)object0) != null) {
            ((OnBackPressedCallback)object0).handleOnBackStarted(backEventCompat0);
        }
    }

    public final void setOnBackInvokedDispatcher(OnBackInvokedDispatcher onBackInvokedDispatcher0) {
        Intrinsics.checkNotNullParameter(onBackInvokedDispatcher0, "invoker");
        this.invokedDispatcher = onBackInvokedDispatcher0;
        this.updateBackInvokedCallbackState(this.hasEnabledCallbacks);
    }

    private final void updateBackInvokedCallbackState(boolean z) {
        OnBackInvokedDispatcher onBackInvokedDispatcher0 = this.invokedDispatcher;
        OnBackInvokedCallback onBackInvokedCallback0 = this.onBackInvokedCallback;
        if(onBackInvokedDispatcher0 != null && onBackInvokedCallback0 != null) {
            if(z && !this.backInvokedCallbackRegistered) {
                Api33Impl.INSTANCE.registerOnBackInvokedCallback(onBackInvokedDispatcher0, 0, onBackInvokedCallback0);
                this.backInvokedCallbackRegistered = true;
                return;
            }
            if(!z && this.backInvokedCallbackRegistered) {
                Api33Impl.INSTANCE.unregisterOnBackInvokedCallback(onBackInvokedDispatcher0, onBackInvokedCallback0);
                this.backInvokedCallbackRegistered = false;
            }
        }
    }

    private final void updateEnabledCallbacks() {
        boolean z = this.hasEnabledCallbacks;
        Iterable iterable0 = this.onBackPressedCallbacks;
        boolean z1 = false;
        if(!(iterable0 instanceof Collection) || !((Collection)iterable0).isEmpty()) {
            for(Object object0: iterable0) {
                if(((OnBackPressedCallback)object0).isEnabled()) {
                    z1 = true;
                    break;
                }
                if(false) {
                    break;
                }
            }
        }
        this.hasEnabledCallbacks = z1;
        if(z1 != z) {
            Consumer consumer0 = this.onHasEnabledCallbacksChanged;
            if(consumer0 != null) {
                consumer0.accept(Boolean.valueOf(z1));
            }
            if(Build.VERSION.SDK_INT >= 33) {
                this.updateBackInvokedCallbackState(z1);
            }
        }
    }
}

