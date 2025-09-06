package androidx.lifecycle;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import androidx.print.PrintHelper..ExternalSyntheticApiModelOutline0;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\b\u0017\u0018\u0000 \u00172\u00020\u0001:\u0003\u0016\u0017\u0018B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH\u0002J\u0012\u0010\t\u001A\u00020\u00062\b\u0010\n\u001A\u0004\u0018\u00010\u0004H\u0002J\u0012\u0010\u000B\u001A\u00020\u00062\b\u0010\n\u001A\u0004\u0018\u00010\u0004H\u0002J\u0012\u0010\f\u001A\u00020\u00062\b\u0010\n\u001A\u0004\u0018\u00010\u0004H\u0002J\u0012\u0010\r\u001A\u00020\u00062\b\u0010\u000E\u001A\u0004\u0018\u00010\u000FH\u0016J\b\u0010\u0010\u001A\u00020\u0006H\u0016J\b\u0010\u0011\u001A\u00020\u0006H\u0016J\b\u0010\u0012\u001A\u00020\u0006H\u0016J\b\u0010\u0013\u001A\u00020\u0006H\u0016J\b\u0010\u0014\u001A\u00020\u0006H\u0016J\u0010\u0010\u0015\u001A\u00020\u00062\b\u0010\u0003\u001A\u0004\u0018\u00010\u0004R\u0010\u0010\u0003\u001A\u0004\u0018\u00010\u0004X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Landroidx/lifecycle/ReportFragment;", "Landroid/app/Fragment;", "()V", "processListener", "Landroidx/lifecycle/ReportFragment$ActivityInitializationListener;", "dispatch", "", "event", "Landroidx/lifecycle/Lifecycle$Event;", "dispatchCreate", "listener", "dispatchResume", "dispatchStart", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPause", "onResume", "onStart", "onStop", "setProcessListener", "ActivityInitializationListener", "Companion", "LifecycleCallbacks", "lifecycle-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class ReportFragment extends Fragment {
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001A\u00020\u0003H&J\b\u0010\u0004\u001A\u00020\u0003H&J\b\u0010\u0005\u001A\u00020\u0003H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Landroidx/lifecycle/ReportFragment$ActivityInitializationListener;", "", "onCreate", "", "onResume", "onStart", "lifecycle-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public interface ActivityInitializationListener {
        void onCreate();

        void onResume();

        void onStart();
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001D\u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\u00072\u0006\u0010\u000F\u001A\u00020\u0010H\u0001¢\u0006\u0002\b\u0011J\u0010\u0010\u0012\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\u0007H\u0007R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001E\u0010\u0005\u001A\u00020\u0006*\u00020\u00078GX\u0087\u0004¢\u0006\f\u0012\u0004\b\b\u0010\t\u001A\u0004\b\n\u0010\u000B¨\u0006\u0013"}, d2 = {"Landroidx/lifecycle/ReportFragment$Companion;", "", "()V", "REPORT_FRAGMENT_TAG", "", "reportFragment", "Landroidx/lifecycle/ReportFragment;", "Landroid/app/Activity;", "get$annotations", "(Landroid/app/Activity;)V", "get", "(Landroid/app/Activity;)Landroidx/lifecycle/ReportFragment;", "dispatch", "", "activity", "event", "Landroidx/lifecycle/Lifecycle$Event;", "dispatch$lifecycle_runtime_release", "injectIfNeededIn", "lifecycle-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        @JvmStatic
        public final void dispatch$lifecycle_runtime_release(Activity activity0, Event lifecycle$Event0) {
            Intrinsics.checkNotNullParameter(activity0, "activity");
            Intrinsics.checkNotNullParameter(lifecycle$Event0, "event");
            if(activity0 instanceof LifecycleRegistryOwner) {
                ((LifecycleRegistryOwner)activity0).getLifecycle().handleLifecycleEvent(lifecycle$Event0);
                return;
            }
            if(activity0 instanceof LifecycleOwner) {
                Lifecycle lifecycle0 = ((LifecycleOwner)activity0).getLifecycle();
                if(lifecycle0 instanceof LifecycleRegistry) {
                    ((LifecycleRegistry)lifecycle0).handleLifecycleEvent(lifecycle$Event0);
                }
            }
        }

        public final ReportFragment get(Activity activity0) {
            Intrinsics.checkNotNullParameter(activity0, "<this>");
            Fragment fragment0 = activity0.getFragmentManager().findFragmentByTag("androidx.lifecycle.LifecycleDispatcher.report_fragment_tag");
            Intrinsics.checkNotNull(fragment0, "null cannot be cast to non-null type androidx.lifecycle.ReportFragment");
            return (ReportFragment)fragment0;
        }

        @JvmStatic
        public static void get$annotations(Activity activity0) {
        }

        @JvmStatic
        public final void injectIfNeededIn(Activity activity0) {
            Intrinsics.checkNotNullParameter(activity0, "activity");
            if(Build.VERSION.SDK_INT >= 29) {
                LifecycleCallbacks.Companion.registerIn(activity0);
            }
            FragmentManager fragmentManager0 = activity0.getFragmentManager();
            if(fragmentManager0.findFragmentByTag("androidx.lifecycle.LifecycleDispatcher.report_fragment_tag") == null) {
                fragmentManager0.beginTransaction().add(new ReportFragment(), "androidx.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
                fragmentManager0.executePendingTransactions();
            }
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000F\b\u0001\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0005¢\u0006\u0002\u0010\u0002J\u001A\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\b\u0010\u0007\u001A\u0004\u0018\u00010\bH\u0016J\u0010\u0010\t\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0016J\u0010\u0010\n\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0016J\u001A\u0010\u000B\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\b\u0010\f\u001A\u0004\u0018\u00010\bH\u0016J\u0010\u0010\r\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0016J\u0010\u0010\u000E\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0016J\u0010\u0010\u000F\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0016J\u0010\u0010\u0010\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0016J\u0010\u0010\u0011\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0016J\u0010\u0010\u0012\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0016J\u0018\u0010\u0013\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH\u0016J\u0010\u0010\u0014\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0016J\u0010\u0010\u0015\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0016¨\u0006\u0017"}, d2 = {"Landroidx/lifecycle/ReportFragment$LifecycleCallbacks;", "Landroid/app/Application$ActivityLifecycleCallbacks;", "()V", "onActivityCreated", "", "activity", "Landroid/app/Activity;", "bundle", "Landroid/os/Bundle;", "onActivityDestroyed", "onActivityPaused", "onActivityPostCreated", "savedInstanceState", "onActivityPostResumed", "onActivityPostStarted", "onActivityPreDestroyed", "onActivityPrePaused", "onActivityPreStopped", "onActivityResumed", "onActivitySaveInstanceState", "onActivityStarted", "onActivityStopped", "Companion", "lifecycle-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class LifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Landroidx/lifecycle/ReportFragment$LifecycleCallbacks$Companion;", "", "()V", "registerIn", "", "activity", "Landroid/app/Activity;", "lifecycle-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
        public static final class androidx.lifecycle.ReportFragment.LifecycleCallbacks.Companion {
            private androidx.lifecycle.ReportFragment.LifecycleCallbacks.Companion() {
            }

            public androidx.lifecycle.ReportFragment.LifecycleCallbacks.Companion(DefaultConstructorMarker defaultConstructorMarker0) {
            }

            @JvmStatic
            public final void registerIn(Activity activity0) {
                Intrinsics.checkNotNullParameter(activity0, "activity");
                PrintHelper..ExternalSyntheticApiModelOutline0.m(activity0, new LifecycleCallbacks());
            }
        }

        public static final androidx.lifecycle.ReportFragment.LifecycleCallbacks.Companion Companion;

        static {
            LifecycleCallbacks.Companion = new androidx.lifecycle.ReportFragment.LifecycleCallbacks.Companion(null);
        }

        @Override  // android.app.Application$ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity0, Bundle bundle0) {
            Intrinsics.checkNotNullParameter(activity0, "activity");
        }

        @Override  // android.app.Application$ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity0) {
            Intrinsics.checkNotNullParameter(activity0, "activity");
        }

        @Override  // android.app.Application$ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity0) {
            Intrinsics.checkNotNullParameter(activity0, "activity");
        }

        @Override  // android.app.Application$ActivityLifecycleCallbacks
        public void onActivityPostCreated(Activity activity0, Bundle bundle0) {
            Intrinsics.checkNotNullParameter(activity0, "activity");
            ReportFragment.Companion.dispatch$lifecycle_runtime_release(activity0, Event.ON_CREATE);
        }

        @Override  // android.app.Application$ActivityLifecycleCallbacks
        public void onActivityPostResumed(Activity activity0) {
            Intrinsics.checkNotNullParameter(activity0, "activity");
            ReportFragment.Companion.dispatch$lifecycle_runtime_release(activity0, Event.ON_RESUME);
        }

        @Override  // android.app.Application$ActivityLifecycleCallbacks
        public void onActivityPostStarted(Activity activity0) {
            Intrinsics.checkNotNullParameter(activity0, "activity");
            ReportFragment.Companion.dispatch$lifecycle_runtime_release(activity0, Event.ON_START);
        }

        @Override  // android.app.Application$ActivityLifecycleCallbacks
        public void onActivityPreDestroyed(Activity activity0) {
            Intrinsics.checkNotNullParameter(activity0, "activity");
            ReportFragment.Companion.dispatch$lifecycle_runtime_release(activity0, Event.ON_DESTROY);
        }

        @Override  // android.app.Application$ActivityLifecycleCallbacks
        public void onActivityPrePaused(Activity activity0) {
            Intrinsics.checkNotNullParameter(activity0, "activity");
            ReportFragment.Companion.dispatch$lifecycle_runtime_release(activity0, Event.ON_PAUSE);
        }

        @Override  // android.app.Application$ActivityLifecycleCallbacks
        public void onActivityPreStopped(Activity activity0) {
            Intrinsics.checkNotNullParameter(activity0, "activity");
            ReportFragment.Companion.dispatch$lifecycle_runtime_release(activity0, Event.ON_STOP);
        }

        @Override  // android.app.Application$ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity0) {
            Intrinsics.checkNotNullParameter(activity0, "activity");
        }

        @Override  // android.app.Application$ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity0, Bundle bundle0) {
            Intrinsics.checkNotNullParameter(activity0, "activity");
            Intrinsics.checkNotNullParameter(bundle0, "bundle");
        }

        @Override  // android.app.Application$ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity0) {
            Intrinsics.checkNotNullParameter(activity0, "activity");
        }

        @Override  // android.app.Application$ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity0) {
            Intrinsics.checkNotNullParameter(activity0, "activity");
        }

        @JvmStatic
        public static final void registerIn(Activity activity0) {
            LifecycleCallbacks.Companion.registerIn(activity0);
        }
    }

    public static final Companion Companion = null;
    private static final String REPORT_FRAGMENT_TAG = "androidx.lifecycle.LifecycleDispatcher.report_fragment_tag";
    private ActivityInitializationListener processListener;

    static {
        ReportFragment.Companion = new Companion(null);
    }

    private final void dispatch(Event lifecycle$Event0) {
        if(Build.VERSION.SDK_INT < 29) {
            Activity activity0 = this.getActivity();
            Intrinsics.checkNotNullExpressionValue(activity0, "activity");
            ReportFragment.Companion.dispatch$lifecycle_runtime_release(activity0, lifecycle$Event0);
        }
    }

    @JvmStatic
    public static final void dispatch$lifecycle_runtime_release(Activity activity0, Event lifecycle$Event0) {
        ReportFragment.Companion.dispatch$lifecycle_runtime_release(activity0, lifecycle$Event0);
    }

    private final void dispatchCreate(ActivityInitializationListener reportFragment$ActivityInitializationListener0) {
        if(reportFragment$ActivityInitializationListener0 != null) {
            reportFragment$ActivityInitializationListener0.onCreate();
        }
    }

    private final void dispatchResume(ActivityInitializationListener reportFragment$ActivityInitializationListener0) {
        if(reportFragment$ActivityInitializationListener0 != null) {
            reportFragment$ActivityInitializationListener0.onResume();
        }
    }

    private final void dispatchStart(ActivityInitializationListener reportFragment$ActivityInitializationListener0) {
        if(reportFragment$ActivityInitializationListener0 != null) {
            reportFragment$ActivityInitializationListener0.onStart();
        }
    }

    public static final ReportFragment get(Activity activity0) {
        return ReportFragment.Companion.get(activity0);
    }

    @JvmStatic
    public static final void injectIfNeededIn(Activity activity0) {
        ReportFragment.Companion.injectIfNeededIn(activity0);
    }

    @Override  // android.app.Fragment
    public void onActivityCreated(Bundle bundle0) {
        super.onActivityCreated(bundle0);
        this.dispatchCreate(this.processListener);
        this.dispatch(Event.ON_CREATE);
    }

    @Override  // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.dispatch(Event.ON_DESTROY);
        this.processListener = null;
    }

    @Override  // android.app.Fragment
    public void onPause() {
        super.onPause();
        this.dispatch(Event.ON_PAUSE);
    }

    @Override  // android.app.Fragment
    public void onResume() {
        super.onResume();
        this.dispatchResume(this.processListener);
        this.dispatch(Event.ON_RESUME);
    }

    @Override  // android.app.Fragment
    public void onStart() {
        super.onStart();
        this.dispatchStart(this.processListener);
        this.dispatch(Event.ON_START);
    }

    @Override  // android.app.Fragment
    public void onStop() {
        super.onStop();
        this.dispatch(Event.ON_STOP);
    }

    public final void setProcessListener(ActivityInitializationListener reportFragment$ActivityInitializationListener0) {
        this.processListener = reportFragment$ActivityInitializationListener0;
    }
}

