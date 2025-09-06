package androidx.fragment.app.strictmode;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00C6\u0002\u0018\u00002\u00020\u0001:\u0003./0B\u0007\b\u0002\u00A2\u0006\u0002\u0010\u0002J\u0012\u0010\u000B\u001A\u00020\u00062\b\u0010\f\u001A\u0004\u0018\u00010\rH\u0002J\u0018\u0010\u000E\u001A\u00020\u000F2\u0006\u0010\u0010\u001A\u00020\u00062\u0006\u0010\u0011\u001A\u00020\u0012H\u0002J\u0010\u0010\u0013\u001A\u00020\u000F2\u0006\u0010\u0011\u001A\u00020\u0012H\u0002J\u0018\u0010\u0014\u001A\u00020\u000F2\u0006\u0010\f\u001A\u00020\r2\u0006\u0010\u0015\u001A\u00020\u0004H\u0007J\u001A\u0010\u0016\u001A\u00020\u000F2\u0006\u0010\f\u001A\u00020\r2\b\u0010\u0017\u001A\u0004\u0018\u00010\u0018H\u0007J\u0010\u0010\u0019\u001A\u00020\u000F2\u0006\u0010\f\u001A\u00020\rH\u0007J\u0010\u0010\u001A\u001A\u00020\u000F2\u0006\u0010\f\u001A\u00020\rH\u0007J\u0010\u0010\u001B\u001A\u00020\u000F2\u0006\u0010\f\u001A\u00020\rH\u0007J\u0010\u0010\u001C\u001A\u00020\u000F2\u0006\u0010\u0011\u001A\u00020\u0012H\u0007J\u0010\u0010\u001D\u001A\u00020\u000F2\u0006\u0010\f\u001A\u00020\rH\u0007J \u0010\u001E\u001A\u00020\u000F2\u0006\u0010\u001F\u001A\u00020\r2\u0006\u0010 \u001A\u00020\r2\u0006\u0010!\u001A\u00020\"H\u0007J\u0018\u0010#\u001A\u00020\u000F2\u0006\u0010\f\u001A\u00020\r2\u0006\u0010$\u001A\u00020%H\u0007J\u0018\u0010&\u001A\u00020\u000F2\u0006\u0010\f\u001A\u00020\r2\u0006\u0010\u0017\u001A\u00020\u0018H\u0007J\u0018\u0010\'\u001A\u00020\u000F2\u0006\u0010\f\u001A\u00020\r2\u0006\u0010(\u001A\u00020)H\u0002J0\u0010*\u001A\u00020%2\u0006\u0010\u0010\u001A\u00020\u00062\u000E\u0010+\u001A\n\u0012\u0006\b\u0001\u0012\u00020\r0,2\u000E\u0010-\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00120,H\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T\u00A2\u0006\u0002\n\u0000R\u001A\u0010\u0005\u001A\u00020\u0006X\u0086\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n\u00A8\u00061"}, d2 = {"Landroidx/fragment/app/strictmode/FragmentStrictMode;", "", "()V", "TAG", "", "defaultPolicy", "Landroidx/fragment/app/strictmode/FragmentStrictMode$Policy;", "getDefaultPolicy", "()Landroidx/fragment/app/strictmode/FragmentStrictMode$Policy;", "setDefaultPolicy", "(Landroidx/fragment/app/strictmode/FragmentStrictMode$Policy;)V", "getNearestPolicy", "fragment", "Landroidx/fragment/app/Fragment;", "handlePolicyViolation", "", "policy", "violation", "Landroidx/fragment/app/strictmode/Violation;", "logIfDebuggingEnabled", "onFragmentReuse", "previousFragmentId", "onFragmentTagUsage", "container", "Landroid/view/ViewGroup;", "onGetRetainInstanceUsage", "onGetTargetFragmentRequestCodeUsage", "onGetTargetFragmentUsage", "onPolicyViolation", "onSetRetainInstanceUsage", "onSetTargetFragmentUsage", "violatingFragment", "targetFragment", "requestCode", "", "onSetUserVisibleHint", "isVisibleToUser", "", "onWrongFragmentContainer", "runOnHostThread", "runnable", "Ljava/lang/Runnable;", "shouldHandlePolicyViolation", "fragmentClass", "Ljava/lang/Class;", "violationClass", "Flag", "OnViolationListener", "Policy", "fragment_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class FragmentStrictMode {
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\n\b\u0080\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000B"}, d2 = {"Landroidx/fragment/app/strictmode/FragmentStrictMode$Flag;", "", "(Ljava/lang/String;I)V", "PENALTY_LOG", "PENALTY_DEATH", "DETECT_FRAGMENT_REUSE", "DETECT_FRAGMENT_TAG_USAGE", "DETECT_RETAIN_INSTANCE_USAGE", "DETECT_SET_USER_VISIBLE_HINT", "DETECT_TARGET_FRAGMENT_USAGE", "DETECT_WRONG_FRAGMENT_CONTAINER", "fragment_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public static enum Flag {
        PENALTY_LOG,
        PENALTY_DEATH,
        DETECT_FRAGMENT_REUSE,
        DETECT_FRAGMENT_TAG_USAGE,
        DETECT_RETAIN_INSTANCE_USAGE,
        DETECT_SET_USER_VISIBLE_HINT,
        DETECT_TARGET_FRAGMENT_USAGE,
        DETECT_WRONG_FRAGMENT_CONTAINER;

        private static final Flag[] $values() [...] // Inlined contents
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Landroidx/fragment/app/strictmode/FragmentStrictMode$OnViolationListener;", "", "onViolation", "", "violation", "Landroidx/fragment/app/strictmode/Violation;", "fragment_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public interface OnViolationListener {
        void onViolation(Violation arg1);
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000E\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000B\u0018\u0000 \u00162\u00020\u0001:\u0002\u0015\u0016BA\b\u0000\u0012\f\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\u0010\u0005\u001A\u0004\u0018\u00010\u0006\u0012 \u0010\u0007\u001A\u001C\u0012\u0004\u0012\u00020\t\u0012\u0012\u0012\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\f0\u000B0\n0\b¢\u0006\u0002\u0010\rR\u001A\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u000E\u0010\u000FR\u0016\u0010\u0005\u001A\u0004\u0018\u00010\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0010\u0010\u0011R.\u0010\u0012\u001A\u001C\u0012\u0004\u0012\u00020\t\u0012\u0012\u0012\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\f0\u000B0\u00030\bX\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0013\u0010\u0014¨\u0006\u0017"}, d2 = {"Landroidx/fragment/app/strictmode/FragmentStrictMode$Policy;", "", "flags", "", "Landroidx/fragment/app/strictmode/FragmentStrictMode$Flag;", "listener", "Landroidx/fragment/app/strictmode/FragmentStrictMode$OnViolationListener;", "allowedViolations", "", "", "", "Ljava/lang/Class;", "Landroidx/fragment/app/strictmode/Violation;", "(Ljava/util/Set;Landroidx/fragment/app/strictmode/FragmentStrictMode$OnViolationListener;Ljava/util/Map;)V", "getFlags$fragment_release", "()Ljava/util/Set;", "getListener$fragment_release", "()Landroidx/fragment/app/strictmode/FragmentStrictMode$OnViolationListener;", "mAllowedViolations", "getMAllowedViolations$fragment_release", "()Ljava/util/Map;", "Builder", "Companion", "fragment_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public static final class Policy {
        @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000E\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J(\u0010\r\u001A\u00020\u00002\u000E\u0010\u000E\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u000F0\u000B2\u000E\u0010\u0010\u001A\n\u0012\u0006\b\u0001\u0012\u00020\f0\u000BH\u0007J \u0010\r\u001A\u00020\u00002\u0006\u0010\u000E\u001A\u00020\n2\u000E\u0010\u0010\u001A\n\u0012\u0006\b\u0001\u0012\u00020\f0\u000BH\u0007J\u0006\u0010\u0011\u001A\u00020\u0012J\b\u0010\u0013\u001A\u00020\u0000H\u0007J\b\u0010\u0014\u001A\u00020\u0000H\u0007J\b\u0010\u0015\u001A\u00020\u0000H\u0007J\b\u0010\u0016\u001A\u00020\u0000H\u0007J\b\u0010\u0017\u001A\u00020\u0000H\u0007J\b\u0010\u0018\u001A\u00020\u0000H\u0007J\b\u0010\u0019\u001A\u00020\u0000H\u0007J\u0010\u0010\u001A\u001A\u00020\u00002\u0006\u0010\u0006\u001A\u00020\u0007H\u0007J\b\u0010\u001B\u001A\u00020\u0000H\u0007R\u0014\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001A\u0004\u0018\u00010\u0007X\u0082\u000E¢\u0006\u0002\n\u0000R(\u0010\b\u001A\u001C\u0012\u0004\u0012\u00020\n\u0012\u0012\u0012\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\f0\u000B0\u00040\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001C"}, d2 = {"Landroidx/fragment/app/strictmode/FragmentStrictMode$Policy$Builder;", "", "()V", "flags", "", "Landroidx/fragment/app/strictmode/FragmentStrictMode$Flag;", "listener", "Landroidx/fragment/app/strictmode/FragmentStrictMode$OnViolationListener;", "mAllowedViolations", "", "", "Ljava/lang/Class;", "Landroidx/fragment/app/strictmode/Violation;", "allowViolation", "fragmentClass", "Landroidx/fragment/app/Fragment;", "violationClass", "build", "Landroidx/fragment/app/strictmode/FragmentStrictMode$Policy;", "detectFragmentReuse", "detectFragmentTagUsage", "detectRetainInstanceUsage", "detectSetUserVisibleHint", "detectTargetFragmentUsage", "detectWrongFragmentContainer", "penaltyDeath", "penaltyListener", "penaltyLog", "fragment_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
        public static final class Builder {
            private final Set flags;
            private OnViolationListener listener;
            private final Map mAllowedViolations;

            public Builder() {
                this.flags = new LinkedHashSet();
                this.mAllowedViolations = new LinkedHashMap();
            }

            public final Builder allowViolation(Class class0, Class class1) {
                Intrinsics.checkNotNullParameter(class0, "fragmentClass");
                Intrinsics.checkNotNullParameter(class1, "violationClass");
                String s = class0.getName();
                Intrinsics.checkNotNullExpressionValue(s, "fragmentClassString");
                return this.allowViolation(s, class1);
            }

            public final Builder allowViolation(String s, Class class0) {
                Intrinsics.checkNotNullParameter(s, "fragmentClass");
                Intrinsics.checkNotNullParameter(class0, "violationClass");
                Set set0 = (Set)this.mAllowedViolations.get(s);
                if(set0 == null) {
                    set0 = new LinkedHashSet();
                }
                set0.add(class0);
                this.mAllowedViolations.put(s, set0);
                return this;
            }

            public final Policy build() {
                if(this.listener == null && !this.flags.contains(Flag.PENALTY_DEATH)) {
                    this.penaltyLog();
                }
                return new Policy(this.flags, this.listener, this.mAllowedViolations);
            }

            public final Builder detectFragmentReuse() {
                this.flags.add(Flag.DETECT_FRAGMENT_REUSE);
                return this;
            }

            public final Builder detectFragmentTagUsage() {
                this.flags.add(Flag.DETECT_FRAGMENT_TAG_USAGE);
                return this;
            }

            public final Builder detectRetainInstanceUsage() {
                this.flags.add(Flag.DETECT_RETAIN_INSTANCE_USAGE);
                return this;
            }

            public final Builder detectSetUserVisibleHint() {
                this.flags.add(Flag.DETECT_SET_USER_VISIBLE_HINT);
                return this;
            }

            public final Builder detectTargetFragmentUsage() {
                this.flags.add(Flag.DETECT_TARGET_FRAGMENT_USAGE);
                return this;
            }

            public final Builder detectWrongFragmentContainer() {
                this.flags.add(Flag.DETECT_WRONG_FRAGMENT_CONTAINER);
                return this;
            }

            public final Builder penaltyDeath() {
                this.flags.add(Flag.PENALTY_DEATH);
                return this;
            }

            public final Builder penaltyListener(OnViolationListener fragmentStrictMode$OnViolationListener0) {
                Intrinsics.checkNotNullParameter(fragmentStrictMode$OnViolationListener0, "listener");
                this.listener = fragmentStrictMode$OnViolationListener0;
                return this;
            }

            public final Builder penaltyLog() {
                this.flags.add(Flag.PENALTY_LOG);
                return this;
            }
        }

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001A\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Landroidx/fragment/app/strictmode/FragmentStrictMode$Policy$Companion;", "", "()V", "LAX", "Landroidx/fragment/app/strictmode/FragmentStrictMode$Policy;", "fragment_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
        public static final class Companion {
            private Companion() {
            }

            public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
            }
        }

        public static final Companion Companion;
        public static final Policy LAX;
        private final Set flags;
        private final OnViolationListener listener;
        private final Map mAllowedViolations;

        static {
            Policy.Companion = new Companion(null);
            Policy.LAX = new Policy(SetsKt.emptySet(), null, MapsKt.emptyMap());
        }

        public Policy(Set set0, OnViolationListener fragmentStrictMode$OnViolationListener0, Map map0) {
            Intrinsics.checkNotNullParameter(set0, "flags");
            Intrinsics.checkNotNullParameter(map0, "allowedViolations");
            super();
            this.flags = set0;
            this.listener = fragmentStrictMode$OnViolationListener0;
            Map map1 = new LinkedHashMap();
            for(Object object0: map0.entrySet()) {
                map1.put(((String)((Map.Entry)object0).getKey()), ((Set)((Map.Entry)object0).getValue()));
            }
            this.mAllowedViolations = map1;
        }

        public final Set getFlags$fragment_release() {
            return this.flags;
        }

        public final OnViolationListener getListener$fragment_release() {
            return this.listener;
        }

        public final Map getMAllowedViolations$fragment_release() {
            return this.mAllowedViolations;
        }
    }

    public static final FragmentStrictMode INSTANCE = null;
    private static final String TAG = "FragmentStrictMode";
    private static Policy defaultPolicy;

    static {
        FragmentStrictMode.INSTANCE = new FragmentStrictMode();
        FragmentStrictMode.defaultPolicy = Policy.LAX;
    }

    public final Policy getDefaultPolicy() {
        return FragmentStrictMode.defaultPolicy;
    }

    private final Policy getNearestPolicy(Fragment fragment0) {
        while(fragment0 != null) {
            if(fragment0.isAdded()) {
                FragmentManager fragmentManager0 = fragment0.getParentFragmentManager();
                Intrinsics.checkNotNullExpressionValue(fragmentManager0, "declaringFragment.parentFragmentManager");
                if(fragmentManager0.getStrictModePolicy() != null) {
                    Policy fragmentStrictMode$Policy0 = fragmentManager0.getStrictModePolicy();
                    Intrinsics.checkNotNull(fragmentStrictMode$Policy0);
                    return fragmentStrictMode$Policy0;
                }
            }
            fragment0 = fragment0.getParentFragment();
        }
        return FragmentStrictMode.defaultPolicy;
    }

    private final void handlePolicyViolation(Policy fragmentStrictMode$Policy0, Violation violation0) {
        Fragment fragment0 = violation0.getFragment();
        String s = fragment0.getClass().getName();
        if(fragmentStrictMode$Policy0.getFlags$fragment_release().contains(Flag.PENALTY_LOG)) {
            Log.d("FragmentStrictMode", "Policy violation in " + s, violation0);
        }
        if(fragmentStrictMode$Policy0.getListener$fragment_release() != null) {
            this.runOnHostThread(fragment0, () -> {
                Intrinsics.checkNotNullParameter(fragmentStrictMode$Policy0, "$policy");
                Intrinsics.checkNotNullParameter(violation0, "$violation");
                fragmentStrictMode$Policy0.getListener$fragment_release().onViolation(violation0);
            });
        }
        if(fragmentStrictMode$Policy0.getFlags$fragment_release().contains(Flag.PENALTY_DEATH)) {
            this.runOnHostThread(fragment0, () -> {
                Intrinsics.checkNotNullParameter(violation0, "$violation");
                Log.e("FragmentStrictMode", "Policy violation with PENALTY_DEATH in " + s, violation0);
                throw violation0;
            });
        }
    }

    // 检测为 Lambda 实现
    private static final void handlePolicyViolation$lambda-0(Policy fragmentStrictMode$Policy0, Violation violation0) [...]

    // 检测为 Lambda 实现
    private static final void handlePolicyViolation$lambda-1(String s, Violation violation0) [...]

    private final void logIfDebuggingEnabled(Violation violation0) {
        if(FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "StrictMode violation in " + violation0.getFragment().getClass().getName(), violation0);
        }
    }

    @JvmStatic
    public static final void onFragmentReuse(Fragment fragment0, String s) {
        Intrinsics.checkNotNullParameter(fragment0, "fragment");
        Intrinsics.checkNotNullParameter(s, "previousFragmentId");
        Violation violation0 = new FragmentReuseViolation(fragment0, s);
        FragmentStrictMode fragmentStrictMode0 = FragmentStrictMode.INSTANCE;
        fragmentStrictMode0.logIfDebuggingEnabled(violation0);
        Policy fragmentStrictMode$Policy0 = fragmentStrictMode0.getNearestPolicy(fragment0);
        if(fragmentStrictMode$Policy0.getFlags$fragment_release().contains(Flag.DETECT_FRAGMENT_REUSE) && fragmentStrictMode0.shouldHandlePolicyViolation(fragmentStrictMode$Policy0, fragment0.getClass(), violation0.getClass())) {
            fragmentStrictMode0.handlePolicyViolation(fragmentStrictMode$Policy0, violation0);
        }
    }

    @JvmStatic
    public static final void onFragmentTagUsage(Fragment fragment0, ViewGroup viewGroup0) {
        Intrinsics.checkNotNullParameter(fragment0, "fragment");
        Violation violation0 = new FragmentTagUsageViolation(fragment0, viewGroup0);
        FragmentStrictMode fragmentStrictMode0 = FragmentStrictMode.INSTANCE;
        fragmentStrictMode0.logIfDebuggingEnabled(violation0);
        Policy fragmentStrictMode$Policy0 = fragmentStrictMode0.getNearestPolicy(fragment0);
        if(fragmentStrictMode$Policy0.getFlags$fragment_release().contains(Flag.DETECT_FRAGMENT_TAG_USAGE) && fragmentStrictMode0.shouldHandlePolicyViolation(fragmentStrictMode$Policy0, fragment0.getClass(), violation0.getClass())) {
            fragmentStrictMode0.handlePolicyViolation(fragmentStrictMode$Policy0, violation0);
        }
    }

    @JvmStatic
    public static final void onGetRetainInstanceUsage(Fragment fragment0) {
        Intrinsics.checkNotNullParameter(fragment0, "fragment");
        Violation violation0 = new GetRetainInstanceUsageViolation(fragment0);
        FragmentStrictMode fragmentStrictMode0 = FragmentStrictMode.INSTANCE;
        fragmentStrictMode0.logIfDebuggingEnabled(violation0);
        Policy fragmentStrictMode$Policy0 = fragmentStrictMode0.getNearestPolicy(fragment0);
        if(fragmentStrictMode$Policy0.getFlags$fragment_release().contains(Flag.DETECT_RETAIN_INSTANCE_USAGE) && fragmentStrictMode0.shouldHandlePolicyViolation(fragmentStrictMode$Policy0, fragment0.getClass(), violation0.getClass())) {
            fragmentStrictMode0.handlePolicyViolation(fragmentStrictMode$Policy0, violation0);
        }
    }

    @JvmStatic
    public static final void onGetTargetFragmentRequestCodeUsage(Fragment fragment0) {
        Intrinsics.checkNotNullParameter(fragment0, "fragment");
        Violation violation0 = new GetTargetFragmentRequestCodeUsageViolation(fragment0);
        FragmentStrictMode fragmentStrictMode0 = FragmentStrictMode.INSTANCE;
        fragmentStrictMode0.logIfDebuggingEnabled(violation0);
        Policy fragmentStrictMode$Policy0 = fragmentStrictMode0.getNearestPolicy(fragment0);
        if(fragmentStrictMode$Policy0.getFlags$fragment_release().contains(Flag.DETECT_TARGET_FRAGMENT_USAGE) && fragmentStrictMode0.shouldHandlePolicyViolation(fragmentStrictMode$Policy0, fragment0.getClass(), violation0.getClass())) {
            fragmentStrictMode0.handlePolicyViolation(fragmentStrictMode$Policy0, violation0);
        }
    }

    @JvmStatic
    public static final void onGetTargetFragmentUsage(Fragment fragment0) {
        Intrinsics.checkNotNullParameter(fragment0, "fragment");
        Violation violation0 = new GetTargetFragmentUsageViolation(fragment0);
        FragmentStrictMode fragmentStrictMode0 = FragmentStrictMode.INSTANCE;
        fragmentStrictMode0.logIfDebuggingEnabled(violation0);
        Policy fragmentStrictMode$Policy0 = fragmentStrictMode0.getNearestPolicy(fragment0);
        if(fragmentStrictMode$Policy0.getFlags$fragment_release().contains(Flag.DETECT_TARGET_FRAGMENT_USAGE) && fragmentStrictMode0.shouldHandlePolicyViolation(fragmentStrictMode$Policy0, fragment0.getClass(), violation0.getClass())) {
            fragmentStrictMode0.handlePolicyViolation(fragmentStrictMode$Policy0, violation0);
        }
    }

    public final void onPolicyViolation(Violation violation0) {
        Intrinsics.checkNotNullParameter(violation0, "violation");
        this.logIfDebuggingEnabled(violation0);
        Fragment fragment0 = violation0.getFragment();
        Policy fragmentStrictMode$Policy0 = this.getNearestPolicy(fragment0);
        if(this.shouldHandlePolicyViolation(fragmentStrictMode$Policy0, fragment0.getClass(), violation0.getClass())) {
            this.handlePolicyViolation(fragmentStrictMode$Policy0, violation0);
        }
    }

    @JvmStatic
    public static final void onSetRetainInstanceUsage(Fragment fragment0) {
        Intrinsics.checkNotNullParameter(fragment0, "fragment");
        Violation violation0 = new SetRetainInstanceUsageViolation(fragment0);
        FragmentStrictMode fragmentStrictMode0 = FragmentStrictMode.INSTANCE;
        fragmentStrictMode0.logIfDebuggingEnabled(violation0);
        Policy fragmentStrictMode$Policy0 = fragmentStrictMode0.getNearestPolicy(fragment0);
        if(fragmentStrictMode$Policy0.getFlags$fragment_release().contains(Flag.DETECT_RETAIN_INSTANCE_USAGE) && fragmentStrictMode0.shouldHandlePolicyViolation(fragmentStrictMode$Policy0, fragment0.getClass(), violation0.getClass())) {
            fragmentStrictMode0.handlePolicyViolation(fragmentStrictMode$Policy0, violation0);
        }
    }

    @JvmStatic
    public static final void onSetTargetFragmentUsage(Fragment fragment0, Fragment fragment1, int v) {
        Intrinsics.checkNotNullParameter(fragment0, "violatingFragment");
        Intrinsics.checkNotNullParameter(fragment1, "targetFragment");
        Violation violation0 = new SetTargetFragmentUsageViolation(fragment0, fragment1, v);
        FragmentStrictMode fragmentStrictMode0 = FragmentStrictMode.INSTANCE;
        fragmentStrictMode0.logIfDebuggingEnabled(violation0);
        Policy fragmentStrictMode$Policy0 = fragmentStrictMode0.getNearestPolicy(fragment0);
        if(fragmentStrictMode$Policy0.getFlags$fragment_release().contains(Flag.DETECT_TARGET_FRAGMENT_USAGE) && fragmentStrictMode0.shouldHandlePolicyViolation(fragmentStrictMode$Policy0, fragment0.getClass(), violation0.getClass())) {
            fragmentStrictMode0.handlePolicyViolation(fragmentStrictMode$Policy0, violation0);
        }
    }

    @JvmStatic
    public static final void onSetUserVisibleHint(Fragment fragment0, boolean z) {
        Intrinsics.checkNotNullParameter(fragment0, "fragment");
        Violation violation0 = new SetUserVisibleHintViolation(fragment0, z);
        FragmentStrictMode fragmentStrictMode0 = FragmentStrictMode.INSTANCE;
        fragmentStrictMode0.logIfDebuggingEnabled(violation0);
        Policy fragmentStrictMode$Policy0 = fragmentStrictMode0.getNearestPolicy(fragment0);
        if(fragmentStrictMode$Policy0.getFlags$fragment_release().contains(Flag.DETECT_SET_USER_VISIBLE_HINT) && fragmentStrictMode0.shouldHandlePolicyViolation(fragmentStrictMode$Policy0, fragment0.getClass(), violation0.getClass())) {
            fragmentStrictMode0.handlePolicyViolation(fragmentStrictMode$Policy0, violation0);
        }
    }

    @JvmStatic
    public static final void onWrongFragmentContainer(Fragment fragment0, ViewGroup viewGroup0) {
        Intrinsics.checkNotNullParameter(fragment0, "fragment");
        Intrinsics.checkNotNullParameter(viewGroup0, "container");
        Violation violation0 = new WrongFragmentContainerViolation(fragment0, viewGroup0);
        FragmentStrictMode fragmentStrictMode0 = FragmentStrictMode.INSTANCE;
        fragmentStrictMode0.logIfDebuggingEnabled(violation0);
        Policy fragmentStrictMode$Policy0 = fragmentStrictMode0.getNearestPolicy(fragment0);
        if(fragmentStrictMode$Policy0.getFlags$fragment_release().contains(Flag.DETECT_WRONG_FRAGMENT_CONTAINER) && fragmentStrictMode0.shouldHandlePolicyViolation(fragmentStrictMode$Policy0, fragment0.getClass(), violation0.getClass())) {
            fragmentStrictMode0.handlePolicyViolation(fragmentStrictMode$Policy0, violation0);
        }
    }

    private final void runOnHostThread(Fragment fragment0, Runnable runnable0) {
        if(fragment0.isAdded()) {
            Handler handler0 = fragment0.getParentFragmentManager().getHost().getHandler();
            Intrinsics.checkNotNullExpressionValue(handler0, "fragment.parentFragmentManager.host.handler");
            if(Intrinsics.areEqual(handler0.getLooper(), Looper.myLooper())) {
                runnable0.run();
                return;
            }
            handler0.post(runnable0);
            return;
        }
        runnable0.run();
    }

    public final void setDefaultPolicy(Policy fragmentStrictMode$Policy0) {
        Intrinsics.checkNotNullParameter(fragmentStrictMode$Policy0, "<set-?>");
        FragmentStrictMode.defaultPolicy = fragmentStrictMode$Policy0;
    }

    private final boolean shouldHandlePolicyViolation(Policy fragmentStrictMode$Policy0, Class class0, Class class1) {
        Set set0 = (Set)fragmentStrictMode$Policy0.getMAllowedViolations$fragment_release().get(class0.getName());
        if(set0 == null) {
            return true;
        }
        return Intrinsics.areEqual(class1.getSuperclass(), Violation.class) || !CollectionsKt.contains(set0, class1.getSuperclass()) ? !set0.contains(class1) : false;
    }
}

