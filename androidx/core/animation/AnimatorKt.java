package androidx.core.animation;

import android.animation.Animator.AnimatorListener;
import android.animation.Animator.AnimatorPauseListener;
import android.animation.Animator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\u001A\u00A1\u0001\u0010\u0000\u001A\u00020\u0001*\u00020\u00022#\b\u0006\u0010\u0003\u001A\u001D\u0012\u0013\u0012\u00110\u0002\u00A2\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u00042#\b\u0006\u0010\t\u001A\u001D\u0012\u0013\u0012\u00110\u0002\u00A2\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u00042#\b\u0006\u0010\n\u001A\u001D\u0012\u0013\u0012\u00110\u0002\u00A2\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u00042#\b\u0006\u0010\u000B\u001A\u001D\u0012\u0013\u0012\u00110\u0002\u00A2\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0086\b\u001AT\u0010\f\u001A\u00020\r*\u00020\u00022#\b\u0002\u0010\u000E\u001A\u001D\u0012\u0013\u0012\u00110\u0002\u00A2\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u00042#\b\u0002\u0010\u000F\u001A\u001D\u0012\u0013\u0012\u00110\u0002\u00A2\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004\u001A2\u0010\u0010\u001A\u00020\u0001*\u00020\u00022#\b\u0004\u0010\u0011\u001A\u001D\u0012\u0013\u0012\u00110\u0002\u00A2\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0086\b\u001A2\u0010\u0012\u001A\u00020\u0001*\u00020\u00022#\b\u0004\u0010\u0011\u001A\u001D\u0012\u0013\u0012\u00110\u0002\u00A2\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0086\b\u001A-\u0010\u0013\u001A\u00020\r*\u00020\u00022!\u0010\u0011\u001A\u001D\u0012\u0013\u0012\u00110\u0002\u00A2\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004\u001A2\u0010\u0014\u001A\u00020\u0001*\u00020\u00022#\b\u0004\u0010\u0011\u001A\u001D\u0012\u0013\u0012\u00110\u0002\u00A2\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0086\b\u001A-\u0010\u0015\u001A\u00020\r*\u00020\u00022!\u0010\u0011\u001A\u001D\u0012\u0013\u0012\u00110\u0002\u00A2\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004\u001A2\u0010\u0016\u001A\u00020\u0001*\u00020\u00022#\b\u0004\u0010\u0011\u001A\u001D\u0012\u0013\u0012\u00110\u0002\u00A2\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0086\b\u00A8\u0006\u0017"}, d2 = {"addListener", "Landroid/animation/Animator$AnimatorListener;", "Landroid/animation/Animator;", "onEnd", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "animator", "", "onStart", "onCancel", "onRepeat", "addPauseListener", "Landroid/animation/Animator$AnimatorPauseListener;", "onResume", "onPause", "doOnCancel", "action", "doOnEnd", "doOnPause", "doOnRepeat", "doOnResume", "doOnStart", "core-ktx_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class AnimatorKt {
    public static final Animator.AnimatorListener addListener(Animator animator0, Function1 function10, Function1 function11, Function1 function12, Function1 function13) {
        Animator.AnimatorListener animator$AnimatorListener0 = new Animator.AnimatorListener() {
            @Override  // android.animation.Animator$AnimatorListener
            public void onAnimationCancel(Animator animator0) {
                function12.invoke(animator0);
            }

            @Override  // android.animation.Animator$AnimatorListener
            public void onAnimationEnd(Animator animator0) {
                function10.invoke(animator0);
            }

            @Override  // android.animation.Animator$AnimatorListener
            public void onAnimationRepeat(Animator animator0) {
                function13.invoke(animator0);
            }

            @Override  // android.animation.Animator$AnimatorListener
            public void onAnimationStart(Animator animator0) {
                function11.invoke(animator0);
            }
        };
        animator0.addListener(animator$AnimatorListener0);
        return animator$AnimatorListener0;
    }

    public static Animator.AnimatorListener addListener$default(Animator animator0, Function1 function10, Function1 function11, Function1 function12, Function1 function13, int v, Object object0) {
        if((v & 1) != 0) {
            function10 = androidx.core.animation.AnimatorKt.addListener.1.INSTANCE;
        }
        if((v & 2) != 0) {
            function11 = androidx.core.animation.AnimatorKt.addListener.2.INSTANCE;
        }
        if((v & 4) != 0) {
            function12 = androidx.core.animation.AnimatorKt.addListener.3.INSTANCE;
        }
        if((v & 8) != 0) {
            function13 = androidx.core.animation.AnimatorKt.addListener.4.INSTANCE;
        }
        Animator.AnimatorListener animator$AnimatorListener0 = new androidx.core.animation.AnimatorKt.addListener.listener.1(function13, function10, function12, function11);
        animator0.addListener(animator$AnimatorListener0);
        return animator$AnimatorListener0;

        @Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Landroid/animation/Animator;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0xB0)
        public final class androidx.core.animation.AnimatorKt.addListener.1 extends Lambda implements Function1 {
            public static final androidx.core.animation.AnimatorKt.addListener.1 INSTANCE;

            static {
                androidx.core.animation.AnimatorKt.addListener.1.INSTANCE = new androidx.core.animation.AnimatorKt.addListener.1();
            }

            public androidx.core.animation.AnimatorKt.addListener.1() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return Unit.INSTANCE;
            }

            public final void invoke(Animator animator0) {
            }
        }


        @Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Landroid/animation/Animator;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0xB0)
        public final class androidx.core.animation.AnimatorKt.addListener.2 extends Lambda implements Function1 {
            public static final androidx.core.animation.AnimatorKt.addListener.2 INSTANCE;

            static {
                androidx.core.animation.AnimatorKt.addListener.2.INSTANCE = new androidx.core.animation.AnimatorKt.addListener.2();
            }

            public androidx.core.animation.AnimatorKt.addListener.2() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return Unit.INSTANCE;
            }

            public final void invoke(Animator animator0) {
            }
        }


        @Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Landroid/animation/Animator;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0xB0)
        public final class androidx.core.animation.AnimatorKt.addListener.3 extends Lambda implements Function1 {
            public static final androidx.core.animation.AnimatorKt.addListener.3 INSTANCE;

            static {
                androidx.core.animation.AnimatorKt.addListener.3.INSTANCE = new androidx.core.animation.AnimatorKt.addListener.3();
            }

            public androidx.core.animation.AnimatorKt.addListener.3() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return Unit.INSTANCE;
            }

            public final void invoke(Animator animator0) {
            }
        }


        @Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Landroid/animation/Animator;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0xB0)
        public final class androidx.core.animation.AnimatorKt.addListener.4 extends Lambda implements Function1 {
            public static final androidx.core.animation.AnimatorKt.addListener.4 INSTANCE;

            static {
                androidx.core.animation.AnimatorKt.addListener.4.INSTANCE = new androidx.core.animation.AnimatorKt.addListener.4();
            }

            public androidx.core.animation.AnimatorKt.addListener.4() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return Unit.INSTANCE;
            }

            public final void invoke(Animator animator0) {
            }
        }

    }

    public static final Animator.AnimatorPauseListener addPauseListener(Animator animator0, Function1 function10, Function1 function11) {
        Animator.AnimatorPauseListener animator$AnimatorPauseListener0 = new Animator.AnimatorPauseListener() {
            @Override  // android.animation.Animator$AnimatorPauseListener
            public void onAnimationPause(Animator animator0) {
                function11.invoke(animator0);
            }

            @Override  // android.animation.Animator$AnimatorPauseListener
            public void onAnimationResume(Animator animator0) {
                function10.invoke(animator0);
            }
        };
        animator0.addPauseListener(animator$AnimatorPauseListener0);
        return animator$AnimatorPauseListener0;
    }

    public static Animator.AnimatorPauseListener addPauseListener$default(Animator animator0, Function1 function10, Function1 function11, int v, Object object0) {
        if((v & 1) != 0) {
            function10 = androidx.core.animation.AnimatorKt.addPauseListener.1.INSTANCE;
        }
        if((v & 2) != 0) {
            function11 = androidx.core.animation.AnimatorKt.addPauseListener.2.INSTANCE;
        }
        return AnimatorKt.addPauseListener(animator0, function10, function11);

        @Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Landroid/animation/Animator;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class androidx.core.animation.AnimatorKt.addPauseListener.1 extends Lambda implements Function1 {
            public static final androidx.core.animation.AnimatorKt.addPauseListener.1 INSTANCE;

            static {
                androidx.core.animation.AnimatorKt.addPauseListener.1.INSTANCE = new androidx.core.animation.AnimatorKt.addPauseListener.1();
            }

            androidx.core.animation.AnimatorKt.addPauseListener.1() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return Unit.INSTANCE;
            }

            public final void invoke(Animator animator0) {
            }
        }


        @Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Landroid/animation/Animator;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class androidx.core.animation.AnimatorKt.addPauseListener.2 extends Lambda implements Function1 {
            public static final androidx.core.animation.AnimatorKt.addPauseListener.2 INSTANCE;

            static {
                androidx.core.animation.AnimatorKt.addPauseListener.2.INSTANCE = new androidx.core.animation.AnimatorKt.addPauseListener.2();
            }

            androidx.core.animation.AnimatorKt.addPauseListener.2() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return Unit.INSTANCE;
            }

            public final void invoke(Animator animator0) {
            }
        }

    }

    public static final Animator.AnimatorListener doOnCancel(Animator animator0, Function1 function10) {
        Animator.AnimatorListener animator$AnimatorListener0 = new Animator.AnimatorListener() {
            @Override  // android.animation.Animator$AnimatorListener
            public void onAnimationCancel(Animator animator0) {
                function10.invoke(animator0);
            }

            @Override  // android.animation.Animator$AnimatorListener
            public void onAnimationEnd(Animator animator0) {
            }

            @Override  // android.animation.Animator$AnimatorListener
            public void onAnimationRepeat(Animator animator0) {
            }

            @Override  // android.animation.Animator$AnimatorListener
            public void onAnimationStart(Animator animator0) {
            }
        };
        animator0.addListener(animator$AnimatorListener0);
        return animator$AnimatorListener0;
    }

    public static final Animator.AnimatorListener doOnEnd(Animator animator0, Function1 function10) {
        Animator.AnimatorListener animator$AnimatorListener0 = new Animator.AnimatorListener() {
            @Override  // android.animation.Animator$AnimatorListener
            public void onAnimationCancel(Animator animator0) {
            }

            @Override  // android.animation.Animator$AnimatorListener
            public void onAnimationEnd(Animator animator0) {
                function10.invoke(animator0);
            }

            @Override  // android.animation.Animator$AnimatorListener
            public void onAnimationRepeat(Animator animator0) {
            }

            @Override  // android.animation.Animator$AnimatorListener
            public void onAnimationStart(Animator animator0) {
            }
        };
        animator0.addListener(animator$AnimatorListener0);
        return animator$AnimatorListener0;
    }

    public static final Animator.AnimatorPauseListener doOnPause(Animator animator0, Function1 function10) {
        return AnimatorKt.addPauseListener$default(animator0, null, function10, 1, null);
    }

    public static final Animator.AnimatorListener doOnRepeat(Animator animator0, Function1 function10) {
        Animator.AnimatorListener animator$AnimatorListener0 = new Animator.AnimatorListener() {
            @Override  // android.animation.Animator$AnimatorListener
            public void onAnimationCancel(Animator animator0) {
            }

            @Override  // android.animation.Animator$AnimatorListener
            public void onAnimationEnd(Animator animator0) {
            }

            @Override  // android.animation.Animator$AnimatorListener
            public void onAnimationRepeat(Animator animator0) {
                function10.invoke(animator0);
            }

            @Override  // android.animation.Animator$AnimatorListener
            public void onAnimationStart(Animator animator0) {
            }
        };
        animator0.addListener(animator$AnimatorListener0);
        return animator$AnimatorListener0;
    }

    public static final Animator.AnimatorPauseListener doOnResume(Animator animator0, Function1 function10) {
        return AnimatorKt.addPauseListener$default(animator0, function10, null, 2, null);
    }

    public static final Animator.AnimatorListener doOnStart(Animator animator0, Function1 function10) {
        Animator.AnimatorListener animator$AnimatorListener0 = new Animator.AnimatorListener() {
            @Override  // android.animation.Animator$AnimatorListener
            public void onAnimationCancel(Animator animator0) {
            }

            @Override  // android.animation.Animator$AnimatorListener
            public void onAnimationEnd(Animator animator0) {
            }

            @Override  // android.animation.Animator$AnimatorListener
            public void onAnimationRepeat(Animator animator0) {
            }

            @Override  // android.animation.Animator$AnimatorListener
            public void onAnimationStart(Animator animator0) {
                function10.invoke(animator0);
            }
        };
        animator0.addListener(animator$AnimatorListener0);
        return animator$AnimatorListener0;
    }
}

