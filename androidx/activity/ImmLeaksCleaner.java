package androidx.activity;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import java.lang.reflect.Field;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 \f2\u00020\u0001:\u0004\u000B\f\r\u000EB\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\nH\u0016R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000F"}, d2 = {"Landroidx/activity/ImmLeaksCleaner;", "Landroidx/lifecycle/LifecycleEventObserver;", "activity", "Landroid/app/Activity;", "(Landroid/app/Activity;)V", "onStateChanged", "", "source", "Landroidx/lifecycle/LifecycleOwner;", "event", "Landroidx/lifecycle/Lifecycle$Event;", "Cleaner", "Companion", "FailedInitialization", "ValidCleaner", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class ImmLeaksCleaner implements LifecycleEventObserver {
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\u0007\b\u0004¢\u0006\u0002\u0010\u0002J\f\u0010\u000B\u001A\u00020\f*\u00020\u0004H&R\u0018\u0010\u0003\u001A\u0004\u0018\u00010\u0001*\u00020\u0004X¦\u0004¢\u0006\u0006\u001A\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0007\u001A\u0004\u0018\u00010\b*\u00020\u0004X¦\u0004¢\u0006\u0006\u001A\u0004\b\t\u0010\n\u0082\u0001\u0002\r\u000E¨\u0006\u000F"}, d2 = {"Landroidx/activity/ImmLeaksCleaner$Cleaner;", "", "()V", "lock", "Landroid/view/inputmethod/InputMethodManager;", "getLock", "(Landroid/view/inputmethod/InputMethodManager;)Ljava/lang/Object;", "servedView", "Landroid/view/View;", "getServedView", "(Landroid/view/inputmethod/InputMethodManager;)Landroid/view/View;", "clearNextServedView", "", "Landroidx/activity/ImmLeaksCleaner$FailedInitialization;", "Landroidx/activity/ImmLeaksCleaner$ValidCleaner;", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static abstract class Cleaner {
        private Cleaner() {
        }

        public Cleaner(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public abstract boolean clearNextServedView(InputMethodManager arg1);

        public abstract Object getLock(InputMethodManager arg1);

        public abstract View getServedView(InputMethodManager arg1);
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0087\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001B\u0010\u0003\u001A\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001A\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Landroidx/activity/ImmLeaksCleaner$Companion;", "", "()V", "cleaner", "Landroidx/activity/ImmLeaksCleaner$Cleaner;", "getCleaner", "()Landroidx/activity/ImmLeaksCleaner$Cleaner;", "cleaner$delegate", "Lkotlin/Lazy;", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final Cleaner getCleaner() {
            return (Cleaner)ImmLeaksCleaner.cleaner$delegate.getValue();
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\f\u0010\f\u001A\u00020\r*\u00020\u0005H\u0016R\u001A\u0010\u0003\u001A\u0004\u0018\u00010\u0004*\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0006\u0010\u0007R\u001A\u0010\b\u001A\u0004\u0018\u00010\t*\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\n\u0010\u000B¨\u0006\u000E"}, d2 = {"Landroidx/activity/ImmLeaksCleaner$FailedInitialization;", "Landroidx/activity/ImmLeaksCleaner$Cleaner;", "()V", "lock", "", "Landroid/view/inputmethod/InputMethodManager;", "getLock", "(Landroid/view/inputmethod/InputMethodManager;)Ljava/lang/Object;", "servedView", "Landroid/view/View;", "getServedView", "(Landroid/view/inputmethod/InputMethodManager;)Landroid/view/View;", "clearNextServedView", "", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class FailedInitialization extends Cleaner {
        public static final FailedInitialization INSTANCE;

        static {
            FailedInitialization.INSTANCE = new FailedInitialization();
        }

        private FailedInitialization() {
            super(null);
        }

        @Override  // androidx.activity.ImmLeaksCleaner$Cleaner
        public boolean clearNextServedView(InputMethodManager inputMethodManager0) {
            Intrinsics.checkNotNullParameter(inputMethodManager0, "<this>");
            return false;
        }

        @Override  // androidx.activity.ImmLeaksCleaner$Cleaner
        public Object getLock(InputMethodManager inputMethodManager0) {
            Intrinsics.checkNotNullParameter(inputMethodManager0, "<this>");
            return null;
        }

        @Override  // androidx.activity.ImmLeaksCleaner$Cleaner
        public View getServedView(InputMethodManager inputMethodManager0) {
            Intrinsics.checkNotNullParameter(inputMethodManager0, "<this>");
            return null;
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0000\u0018\u00002\u00020\u0001B\u001D\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0003\u0012\u0006\u0010\u0005\u001A\u00020\u0003¢\u0006\u0002\u0010\u0006J\f\u0010\u0010\u001A\u00020\u0011*\u00020\tH\u0016R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0004\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001A\u0010\u0007\u001A\u0004\u0018\u00010\b*\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\n\u0010\u000BR\u001A\u0010\f\u001A\u0004\u0018\u00010\r*\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u000E\u0010\u000F¨\u0006\u0012"}, d2 = {"Landroidx/activity/ImmLeaksCleaner$ValidCleaner;", "Landroidx/activity/ImmLeaksCleaner$Cleaner;", "hField", "Ljava/lang/reflect/Field;", "servedViewField", "nextServedViewField", "(Ljava/lang/reflect/Field;Ljava/lang/reflect/Field;Ljava/lang/reflect/Field;)V", "lock", "", "Landroid/view/inputmethod/InputMethodManager;", "getLock", "(Landroid/view/inputmethod/InputMethodManager;)Ljava/lang/Object;", "servedView", "Landroid/view/View;", "getServedView", "(Landroid/view/inputmethod/InputMethodManager;)Landroid/view/View;", "clearNextServedView", "", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class ValidCleaner extends Cleaner {
        private final Field hField;
        private final Field nextServedViewField;
        private final Field servedViewField;

        public ValidCleaner(Field field0, Field field1, Field field2) {
            Intrinsics.checkNotNullParameter(field0, "hField");
            Intrinsics.checkNotNullParameter(field1, "servedViewField");
            Intrinsics.checkNotNullParameter(field2, "nextServedViewField");
            super(null);
            this.hField = field0;
            this.servedViewField = field1;
            this.nextServedViewField = field2;
        }

        @Override  // androidx.activity.ImmLeaksCleaner$Cleaner
        public boolean clearNextServedView(InputMethodManager inputMethodManager0) {
            Intrinsics.checkNotNullParameter(inputMethodManager0, "<this>");
            try {
                this.nextServedViewField.set(inputMethodManager0, null);
                return true;
            }
            catch(IllegalAccessException unused_ex) {
                return false;
            }
        }

        @Override  // androidx.activity.ImmLeaksCleaner$Cleaner
        public Object getLock(InputMethodManager inputMethodManager0) {
            Intrinsics.checkNotNullParameter(inputMethodManager0, "<this>");
            try {
                return this.hField.get(inputMethodManager0);
            }
            catch(IllegalAccessException unused_ex) {
                return null;
            }
        }

        @Override  // androidx.activity.ImmLeaksCleaner$Cleaner
        public View getServedView(InputMethodManager inputMethodManager0) {
            Intrinsics.checkNotNullParameter(inputMethodManager0, "<this>");
            try {
                return (View)this.servedViewField.get(inputMethodManager0);
            }
            catch(IllegalAccessException | ClassCastException unused_ex) {
                return null;
            }
        }
    }

    public static final Companion Companion;
    private final Activity activity;
    private static final Lazy cleaner$delegate;

    static {
        ImmLeaksCleaner.Companion = new Companion(null);
        ImmLeaksCleaner.cleaner$delegate = LazyKt.lazy(ImmLeaksCleaner.Companion.cleaner.2.INSTANCE);
    }

    public ImmLeaksCleaner(Activity activity0) {
        Intrinsics.checkNotNullParameter(activity0, "activity");
        super();
        this.activity = activity0;
    }

    @Override  // androidx.lifecycle.LifecycleEventObserver
    public void onStateChanged(LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) {
        Intrinsics.checkNotNullParameter(lifecycleOwner0, "source");
        Intrinsics.checkNotNullParameter(lifecycle$Event0, "event");
        if(lifecycle$Event0 == Event.ON_DESTROY) {
            Object object0 = this.activity.getSystemService("input_method");
            Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
            Cleaner immLeaksCleaner$Cleaner0 = ImmLeaksCleaner.Companion.getCleaner();
            Object object1 = immLeaksCleaner$Cleaner0.getLock(((InputMethodManager)object0));
            if(object1 != null) {
                synchronized(object1) {
                    View view0 = immLeaksCleaner$Cleaner0.getServedView(((InputMethodManager)object0));
                    if(view0 == null) {
                        return;
                    }
                    if(view0.isAttachedToWindow()) {
                        return;
                    }
                    boolean z = immLeaksCleaner$Cleaner0.clearNextServedView(((InputMethodManager)object0));
                }
                if(z) {
                    ((InputMethodManager)object0).isActive();
                }
            }
        }
    }
}

