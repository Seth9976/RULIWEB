package androidx.core.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.os.Build.VERSION;
import android.view.View.OnApplyWindowInsetsListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowInsetsAnimation.Bounds;
import android.view.WindowInsetsAnimation.Callback;
import android.view.WindowInsetsAnimation;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import androidx.core.R.id;
import androidx.core.graphics.Insets;
import androidx.core.util.HalfKt..ExternalSyntheticApiModelOutline0;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public final class WindowInsetsAnimationCompat {
    public static final class BoundsCompat {
        private final Insets mLowerBound;
        private final Insets mUpperBound;

        private BoundsCompat(WindowInsetsAnimation.Bounds windowInsetsAnimation$Bounds0) {
            this.mLowerBound = Impl30.getLowerBounds(windowInsetsAnimation$Bounds0);
            this.mUpperBound = Impl30.getHigherBounds(windowInsetsAnimation$Bounds0);
        }

        public BoundsCompat(Insets insets0, Insets insets1) {
            this.mLowerBound = insets0;
            this.mUpperBound = insets1;
        }

        public Insets getLowerBound() {
            return this.mLowerBound;
        }

        public Insets getUpperBound() {
            return this.mUpperBound;
        }

        public BoundsCompat inset(Insets insets0) {
            return new BoundsCompat(WindowInsetsCompat.insetInsets(this.mLowerBound, insets0.left, insets0.top, insets0.right, insets0.bottom), WindowInsetsCompat.insetInsets(this.mUpperBound, insets0.left, insets0.top, insets0.right, insets0.bottom));
        }

        public WindowInsetsAnimation.Bounds toBounds() {
            return Impl30.createPlatformBounds(this);
        }

        public static BoundsCompat toBoundsCompat(WindowInsetsAnimation.Bounds windowInsetsAnimation$Bounds0) {
            return new BoundsCompat(windowInsetsAnimation$Bounds0);
        }

        @Override
        public String toString() {
            return "Bounds{lower=" + this.mLowerBound + " upper=" + this.mUpperBound + "}";
        }
    }

    public static abstract class Callback {
        @Retention(RetentionPolicy.SOURCE)
        public @interface DispatchMode {
        }

        public static final int DISPATCH_MODE_CONTINUE_ON_SUBTREE = 1;
        public static final int DISPATCH_MODE_STOP;
        WindowInsetsCompat mDispachedInsets;
        private final int mDispatchMode;

        public Callback(int v) {
            this.mDispatchMode = v;
        }

        public final int getDispatchMode() {
            return this.mDispatchMode;
        }

        public void onEnd(WindowInsetsAnimationCompat windowInsetsAnimationCompat0) {
        }

        public void onPrepare(WindowInsetsAnimationCompat windowInsetsAnimationCompat0) {
        }

        public abstract WindowInsetsCompat onProgress(WindowInsetsCompat arg1, List arg2);

        public BoundsCompat onStart(WindowInsetsAnimationCompat windowInsetsAnimationCompat0, BoundsCompat windowInsetsAnimationCompat$BoundsCompat0) {
            return windowInsetsAnimationCompat$BoundsCompat0;
        }
    }

    static class Impl21 extends Impl {
        static class Impl21OnApplyWindowInsetsListener implements View.OnApplyWindowInsetsListener {
            private static final int COMPAT_ANIMATION_DURATION_IME = 0xA0;
            private static final int COMPAT_ANIMATION_DURATION_SYSTEM_BAR = 0xFA;
            final Callback mCallback;
            private WindowInsetsCompat mLastInsets;

            Impl21OnApplyWindowInsetsListener(View view0, Callback windowInsetsAnimationCompat$Callback0) {
                this.mCallback = windowInsetsAnimationCompat$Callback0;
                WindowInsetsCompat windowInsetsCompat0 = ViewCompat.getRootWindowInsets(view0);
                this.mLastInsets = windowInsetsCompat0 == null ? null : new Builder(windowInsetsCompat0).build();
            }

            @Override  // android.view.View$OnApplyWindowInsetsListener
            public WindowInsets onApplyWindowInsets(View view0, WindowInsets windowInsets0) {
                if(!view0.isLaidOut()) {
                    this.mLastInsets = WindowInsetsCompat.toWindowInsetsCompat(windowInsets0, view0);
                    return Impl21.forwardToViewIfNeeded(view0, windowInsets0);
                }
                WindowInsetsCompat windowInsetsCompat0 = WindowInsetsCompat.toWindowInsetsCompat(windowInsets0, view0);
                if(this.mLastInsets == null) {
                    this.mLastInsets = ViewCompat.getRootWindowInsets(view0);
                }
                if(this.mLastInsets == null) {
                    this.mLastInsets = windowInsetsCompat0;
                    return Impl21.forwardToViewIfNeeded(view0, windowInsets0);
                }
                Callback windowInsetsAnimationCompat$Callback0 = Impl21.getCallback(view0);
                if(windowInsetsAnimationCompat$Callback0 != null && Objects.equals(windowInsetsAnimationCompat$Callback0.mDispachedInsets, windowInsetsCompat0)) {
                    return Impl21.forwardToViewIfNeeded(view0, windowInsets0);
                }
                int[] arr_v = new int[1];
                int[] arr_v1 = new int[1];
                Impl21.buildAnimationMask(windowInsetsCompat0, this.mLastInsets, arr_v, arr_v1);
                int v = arr_v[0];
                int v1 = arr_v1[0];
                int v2 = v | v1;
                if(v2 == 0) {
                    this.mLastInsets = windowInsetsCompat0;
                    return Impl21.forwardToViewIfNeeded(view0, windowInsets0);
                }
                WindowInsetsCompat windowInsetsCompat1 = this.mLastInsets;
                WindowInsetsAnimationCompat windowInsetsAnimationCompat0 = new WindowInsetsAnimationCompat(v2, Impl21.createInsetInterpolator(v, v1), ((8 & v2) == 0 ? 0xFAL : 0xA0L));
                windowInsetsAnimationCompat0.setFraction(0.0f);
                ValueAnimator valueAnimator0 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration(windowInsetsAnimationCompat0.getDurationMillis());
                BoundsCompat windowInsetsAnimationCompat$BoundsCompat0 = Impl21.computeAnimationBounds(windowInsetsCompat0, windowInsetsCompat1, v2);
                Impl21.dispatchOnPrepare(view0, windowInsetsAnimationCompat0, windowInsetsCompat0, false);
                valueAnimator0.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override  // android.animation.ValueAnimator$AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator valueAnimator0) {
                        float f = valueAnimator0.getAnimatedFraction();
                        windowInsetsAnimationCompat0.setFraction(f);
                        float f1 = windowInsetsAnimationCompat0.getInterpolatedFraction();
                        WindowInsetsCompat windowInsetsCompat0 = Impl21.interpolateInsets(windowInsetsCompat0, windowInsetsCompat1, f1, v2);
                        List list0 = Collections.singletonList(windowInsetsAnimationCompat0);
                        Impl21.dispatchOnProgress(view0, windowInsetsCompat0, list0);
                    }
                });
                valueAnimator0.addListener(new AnimatorListenerAdapter() {
                    @Override  // android.animation.AnimatorListenerAdapter
                    public void onAnimationEnd(Animator animator0) {
                        windowInsetsAnimationCompat0.setFraction(1.0f);
                        Impl21.dispatchOnEnd(view0, windowInsetsAnimationCompat0);
                    }
                });
                OneShotPreDrawListener.add(view0, new Runnable() {
                    @Override
                    public void run() {
                        Impl21.dispatchOnStart(view0, windowInsetsAnimationCompat0, windowInsetsAnimationCompat$BoundsCompat0);
                        valueAnimator0.start();
                    }
                });
                this.mLastInsets = windowInsetsCompat0;
                return Impl21.forwardToViewIfNeeded(view0, windowInsets0);
            }
        }

        private static final Interpolator HIDE_IME_INTERPOLATOR;
        private static final Interpolator HIDE_SYSTEM_BAR_INTERPOLATOR;
        private static final Interpolator SHOW_IME_INTERPOLATOR;
        private static final Interpolator SHOW_SYSTEM_BAR_INTERPOLATOR;

        static {
            Impl21.SHOW_IME_INTERPOLATOR = new PathInterpolator(0.0f, 1.1f, 0.0f, 1.0f);
            Impl21.HIDE_IME_INTERPOLATOR = new FastOutLinearInInterpolator();
            Impl21.SHOW_SYSTEM_BAR_INTERPOLATOR = new DecelerateInterpolator(1.5f);
            Impl21.HIDE_SYSTEM_BAR_INTERPOLATOR = new AccelerateInterpolator(1.5f);
        }

        Impl21(int v, Interpolator interpolator0, long v1) {
            super(v, interpolator0, v1);
        }

        static void buildAnimationMask(WindowInsetsCompat windowInsetsCompat0, WindowInsetsCompat windowInsetsCompat1, int[] arr_v, int[] arr_v1) {
            for(int v = 1; v <= 0x200; v <<= 1) {
                Insets insets0 = windowInsetsCompat0.getInsets(v);
                Insets insets1 = windowInsetsCompat1.getInsets(v);
                int v1 = insets0.left > insets1.left || insets0.top > insets1.top || insets0.right > insets1.right || insets0.bottom > insets1.bottom ? 1 : 0;
                if(v1 != (insets0.left < insets1.left || insets0.top < insets1.top || insets0.right < insets1.right || insets0.bottom < insets1.bottom ? 1 : 0)) {
                    if(v1 == 0) {
                        arr_v1[0] |= v;
                    }
                    else {
                        arr_v[0] |= v;
                    }
                }
            }
        }

        static BoundsCompat computeAnimationBounds(WindowInsetsCompat windowInsetsCompat0, WindowInsetsCompat windowInsetsCompat1, int v) {
            Insets insets0 = windowInsetsCompat0.getInsets(v);
            Insets insets1 = windowInsetsCompat1.getInsets(v);
            return new BoundsCompat(Insets.of(Math.min(insets0.left, insets1.left), Math.min(insets0.top, insets1.top), Math.min(insets0.right, insets1.right), Math.min(insets0.bottom, insets1.bottom)), Insets.of(Math.max(insets0.left, insets1.left), Math.max(insets0.top, insets1.top), Math.max(insets0.right, insets1.right), Math.max(insets0.bottom, insets1.bottom)));
        }

        static Interpolator createInsetInterpolator(int v, int v1) {
            if((8 & v) != 0) {
                return Impl21.SHOW_IME_INTERPOLATOR;
            }
            if((8 & v1) != 0) {
                return Impl21.HIDE_IME_INTERPOLATOR;
            }
            if((v & 0x207) != 0) {
                return Impl21.SHOW_SYSTEM_BAR_INTERPOLATOR;
            }
            return (0x207 & v1) == 0 ? null : Impl21.HIDE_SYSTEM_BAR_INTERPOLATOR;
        }

        private static View.OnApplyWindowInsetsListener createProxyListener(View view0, Callback windowInsetsAnimationCompat$Callback0) {
            return new Impl21OnApplyWindowInsetsListener(view0, windowInsetsAnimationCompat$Callback0);
        }

        static void dispatchOnEnd(View view0, WindowInsetsAnimationCompat windowInsetsAnimationCompat0) {
            Callback windowInsetsAnimationCompat$Callback0 = Impl21.getCallback(view0);
            if(windowInsetsAnimationCompat$Callback0 == null) {
            label_4:
                if(view0 instanceof ViewGroup) {
                    for(int v = 0; v < ((ViewGroup)view0).getChildCount(); ++v) {
                        Impl21.dispatchOnEnd(((ViewGroup)view0).getChildAt(v), windowInsetsAnimationCompat0);
                    }
                }
            }
            else {
                windowInsetsAnimationCompat$Callback0.onEnd(windowInsetsAnimationCompat0);
                if(windowInsetsAnimationCompat$Callback0.getDispatchMode() != 0) {
                    goto label_4;
                }
            }
        }

        static void dispatchOnPrepare(View view0, WindowInsetsAnimationCompat windowInsetsAnimationCompat0, WindowInsetsCompat windowInsetsCompat0, boolean z) {
            Callback windowInsetsAnimationCompat$Callback0 = Impl21.getCallback(view0);
            if(windowInsetsAnimationCompat$Callback0 != null) {
                windowInsetsAnimationCompat$Callback0.mDispachedInsets = windowInsetsCompat0;
                if(!z) {
                    windowInsetsAnimationCompat$Callback0.onPrepare(windowInsetsAnimationCompat0);
                    z = windowInsetsAnimationCompat$Callback0.getDispatchMode() == 0;
                }
            }
            if(view0 instanceof ViewGroup) {
                for(int v = 0; v < ((ViewGroup)view0).getChildCount(); ++v) {
                    Impl21.dispatchOnPrepare(((ViewGroup)view0).getChildAt(v), windowInsetsAnimationCompat0, windowInsetsCompat0, z);
                }
            }
        }

        static void dispatchOnProgress(View view0, WindowInsetsCompat windowInsetsCompat0, List list0) {
            Callback windowInsetsAnimationCompat$Callback0 = Impl21.getCallback(view0);
            if(windowInsetsAnimationCompat$Callback0 == null) {
            label_4:
                if(view0 instanceof ViewGroup) {
                    for(int v = 0; v < ((ViewGroup)view0).getChildCount(); ++v) {
                        Impl21.dispatchOnProgress(((ViewGroup)view0).getChildAt(v), windowInsetsCompat0, list0);
                    }
                }
            }
            else {
                windowInsetsCompat0 = windowInsetsAnimationCompat$Callback0.onProgress(windowInsetsCompat0, list0);
                if(windowInsetsAnimationCompat$Callback0.getDispatchMode() != 0) {
                    goto label_4;
                }
            }
        }

        static void dispatchOnStart(View view0, WindowInsetsAnimationCompat windowInsetsAnimationCompat0, BoundsCompat windowInsetsAnimationCompat$BoundsCompat0) {
            Callback windowInsetsAnimationCompat$Callback0 = Impl21.getCallback(view0);
            if(windowInsetsAnimationCompat$Callback0 == null) {
            label_4:
                if(view0 instanceof ViewGroup) {
                    for(int v = 0; v < ((ViewGroup)view0).getChildCount(); ++v) {
                        Impl21.dispatchOnStart(((ViewGroup)view0).getChildAt(v), windowInsetsAnimationCompat0, windowInsetsAnimationCompat$BoundsCompat0);
                    }
                }
            }
            else {
                windowInsetsAnimationCompat$Callback0.onStart(windowInsetsAnimationCompat0, windowInsetsAnimationCompat$BoundsCompat0);
                if(windowInsetsAnimationCompat$Callback0.getDispatchMode() != 0) {
                    goto label_4;
                }
            }
        }

        static WindowInsets forwardToViewIfNeeded(View view0, WindowInsets windowInsets0) {
            return view0.getTag(id.tag_on_apply_window_listener) == null ? view0.onApplyWindowInsets(windowInsets0) : windowInsets0;
        }

        static Callback getCallback(View view0) {
            Object object0 = view0.getTag(id.tag_window_insets_animation_callback);
            return object0 instanceof Impl21OnApplyWindowInsetsListener ? ((Impl21OnApplyWindowInsetsListener)object0).mCallback : null;
        }

        static WindowInsetsCompat interpolateInsets(WindowInsetsCompat windowInsetsCompat0, WindowInsetsCompat windowInsetsCompat1, float f, int v) {
            Builder windowInsetsCompat$Builder0 = new Builder(windowInsetsCompat0);
            for(int v1 = 1; v1 <= 0x200; v1 <<= 1) {
                if((v & v1) == 0) {
                    windowInsetsCompat$Builder0.setInsets(v1, windowInsetsCompat0.getInsets(v1));
                }
                else {
                    Insets insets0 = windowInsetsCompat0.getInsets(v1);
                    Insets insets1 = windowInsetsCompat1.getInsets(v1);
                    windowInsetsCompat$Builder0.setInsets(v1, WindowInsetsCompat.insetInsets(insets0, ((int)(((double)(((float)(insets0.left - insets1.left)) * (1.0f - f))) + 0.5)), ((int)(((double)(((float)(insets0.top - insets1.top)) * (1.0f - f))) + 0.5)), ((int)(((double)(((float)(insets0.right - insets1.right)) * (1.0f - f))) + 0.5)), ((int)(((double)(((float)(insets0.bottom - insets1.bottom)) * (1.0f - f))) + 0.5))));
                }
            }
            return windowInsetsCompat$Builder0.build();
        }

        static void setCallback(View view0, Callback windowInsetsAnimationCompat$Callback0) {
            View.OnApplyWindowInsetsListener view$OnApplyWindowInsetsListener0 = windowInsetsAnimationCompat$Callback0 == null ? null : Impl21.createProxyListener(view0, windowInsetsAnimationCompat$Callback0);
            view0.setTag(id.tag_window_insets_animation_callback, view$OnApplyWindowInsetsListener0);
            if(view0.getTag(id.tag_compat_insets_dispatch) == null && view0.getTag(id.tag_on_apply_window_listener) == null) {
                view0.setOnApplyWindowInsetsListener(view$OnApplyWindowInsetsListener0);
            }
        }
    }

    static class Impl30 extends Impl {
        static class ProxyCallback extends WindowInsetsAnimation.Callback {
            private final HashMap mAnimations;
            private final Callback mCompat;
            private List mRORunningAnimations;
            private ArrayList mTmpRunningAnimations;

            ProxyCallback(Callback windowInsetsAnimationCompat$Callback0) {
                super(windowInsetsAnimationCompat$Callback0.getDispatchMode());
                this.mAnimations = new HashMap();
                this.mCompat = windowInsetsAnimationCompat$Callback0;
            }

            private WindowInsetsAnimationCompat getWindowInsetsAnimationCompat(WindowInsetsAnimation windowInsetsAnimation0) {
                WindowInsetsAnimationCompat windowInsetsAnimationCompat0 = (WindowInsetsAnimationCompat)this.mAnimations.get(windowInsetsAnimation0);
                if(windowInsetsAnimationCompat0 == null) {
                    windowInsetsAnimationCompat0 = WindowInsetsAnimationCompat.toWindowInsetsAnimationCompat(windowInsetsAnimation0);
                    this.mAnimations.put(windowInsetsAnimation0, windowInsetsAnimationCompat0);
                }
                return windowInsetsAnimationCompat0;
            }

            @Override  // android.view.WindowInsetsAnimation$Callback
            public void onEnd(WindowInsetsAnimation windowInsetsAnimation0) {
                WindowInsetsAnimationCompat windowInsetsAnimationCompat0 = this.getWindowInsetsAnimationCompat(windowInsetsAnimation0);
                this.mCompat.onEnd(windowInsetsAnimationCompat0);
                this.mAnimations.remove(windowInsetsAnimation0);
            }

            @Override  // android.view.WindowInsetsAnimation$Callback
            public void onPrepare(WindowInsetsAnimation windowInsetsAnimation0) {
                WindowInsetsAnimationCompat windowInsetsAnimationCompat0 = this.getWindowInsetsAnimationCompat(windowInsetsAnimation0);
                this.mCompat.onPrepare(windowInsetsAnimationCompat0);
            }

            @Override  // android.view.WindowInsetsAnimation$Callback
            public WindowInsets onProgress(WindowInsets windowInsets0, List list0) {
                ArrayList arrayList0 = this.mTmpRunningAnimations;
                if(arrayList0 == null) {
                    ArrayList arrayList1 = new ArrayList(list0.size());
                    this.mTmpRunningAnimations = arrayList1;
                    this.mRORunningAnimations = Collections.unmodifiableList(arrayList1);
                }
                else {
                    arrayList0.clear();
                }
                for(int v = list0.size() - 1; v >= 0; --v) {
                    WindowInsetsAnimation windowInsetsAnimation0 = HalfKt..ExternalSyntheticApiModelOutline0.m(list0.get(v));
                    WindowInsetsAnimationCompat windowInsetsAnimationCompat0 = this.getWindowInsetsAnimationCompat(windowInsetsAnimation0);
                    windowInsetsAnimationCompat0.setFraction(windowInsetsAnimation0.getFraction());
                    this.mTmpRunningAnimations.add(windowInsetsAnimationCompat0);
                }
                WindowInsetsCompat windowInsetsCompat0 = WindowInsetsCompat.toWindowInsetsCompat(windowInsets0);
                return this.mCompat.onProgress(windowInsetsCompat0, this.mRORunningAnimations).toWindowInsets();
            }

            @Override  // android.view.WindowInsetsAnimation$Callback
            public WindowInsetsAnimation.Bounds onStart(WindowInsetsAnimation windowInsetsAnimation0, WindowInsetsAnimation.Bounds windowInsetsAnimation$Bounds0) {
                WindowInsetsAnimationCompat windowInsetsAnimationCompat0 = this.getWindowInsetsAnimationCompat(windowInsetsAnimation0);
                BoundsCompat windowInsetsAnimationCompat$BoundsCompat0 = BoundsCompat.toBoundsCompat(windowInsetsAnimation$Bounds0);
                return this.mCompat.onStart(windowInsetsAnimationCompat0, windowInsetsAnimationCompat$BoundsCompat0).toBounds();
            }
        }

        private final WindowInsetsAnimation mWrapped;

        Impl30(int v, Interpolator interpolator0, long v1) {
            this(HalfKt..ExternalSyntheticApiModelOutline0.m(v, interpolator0, v1));
        }

        Impl30(WindowInsetsAnimation windowInsetsAnimation0) {
            super(0, null, 0L);
            this.mWrapped = windowInsetsAnimation0;
        }

        public static WindowInsetsAnimation.Bounds createPlatformBounds(BoundsCompat windowInsetsAnimationCompat$BoundsCompat0) {
            return HalfKt..ExternalSyntheticApiModelOutline0.m(windowInsetsAnimationCompat$BoundsCompat0.getLowerBound().toPlatformInsets(), windowInsetsAnimationCompat$BoundsCompat0.getUpperBound().toPlatformInsets());
        }

        @Override  // androidx.core.view.WindowInsetsAnimationCompat$Impl
        public float getAlpha() {
            return this.mWrapped.getAlpha();
        }

        @Override  // androidx.core.view.WindowInsetsAnimationCompat$Impl
        public long getDurationMillis() {
            return this.mWrapped.getDurationMillis();
        }

        @Override  // androidx.core.view.WindowInsetsAnimationCompat$Impl
        public float getFraction() {
            return this.mWrapped.getFraction();
        }

        public static Insets getHigherBounds(WindowInsetsAnimation.Bounds windowInsetsAnimation$Bounds0) {
            return Insets.toCompatInsets(windowInsetsAnimation$Bounds0.getUpperBound());
        }

        @Override  // androidx.core.view.WindowInsetsAnimationCompat$Impl
        public float getInterpolatedFraction() {
            return this.mWrapped.getInterpolatedFraction();
        }

        @Override  // androidx.core.view.WindowInsetsAnimationCompat$Impl
        public Interpolator getInterpolator() {
            return this.mWrapped.getInterpolator();
        }

        public static Insets getLowerBounds(WindowInsetsAnimation.Bounds windowInsetsAnimation$Bounds0) {
            return Insets.toCompatInsets(windowInsetsAnimation$Bounds0.getLowerBound());
        }

        @Override  // androidx.core.view.WindowInsetsAnimationCompat$Impl
        public int getTypeMask() {
            return this.mWrapped.getTypeMask();
        }

        @Override  // androidx.core.view.WindowInsetsAnimationCompat$Impl
        public void setAlpha(float f) {
            this.mWrapped.setAlpha(f);
        }

        public static void setCallback(View view0, Callback windowInsetsAnimationCompat$Callback0) {
            view0.setWindowInsetsAnimationCallback((windowInsetsAnimationCompat$Callback0 == null ? null : new ProxyCallback(windowInsetsAnimationCompat$Callback0)));
        }

        @Override  // androidx.core.view.WindowInsetsAnimationCompat$Impl
        public void setFraction(float f) {
            this.mWrapped.setFraction(f);
        }
    }

    static class Impl {
        private float mAlpha;
        private final long mDurationMillis;
        private float mFraction;
        private final Interpolator mInterpolator;
        private final int mTypeMask;

        Impl(int v, Interpolator interpolator0, long v1) {
            this.mAlpha = 1.0f;
            this.mTypeMask = v;
            this.mInterpolator = interpolator0;
            this.mDurationMillis = v1;
        }

        public float getAlpha() {
            return this.mAlpha;
        }

        public long getDurationMillis() {
            return this.mDurationMillis;
        }

        public float getFraction() {
            return this.mFraction;
        }

        public float getInterpolatedFraction() {
            return this.mInterpolator == null ? this.mFraction : this.mInterpolator.getInterpolation(this.mFraction);
        }

        public Interpolator getInterpolator() {
            return this.mInterpolator;
        }

        public int getTypeMask() {
            return this.mTypeMask;
        }

        public void setAlpha(float f) {
            this.mAlpha = f;
        }

        public void setFraction(float f) {
            this.mFraction = f;
        }
    }

    private static final boolean DEBUG = false;
    private static final String TAG = "WindowInsetsAnimCompat";
    private Impl mImpl;

    public WindowInsetsAnimationCompat(int v, Interpolator interpolator0, long v1) {
        if(Build.VERSION.SDK_INT >= 30) {
            this.mImpl = new Impl30(v, interpolator0, v1);
            return;
        }
        this.mImpl = new Impl21(v, interpolator0, v1);
    }

    private WindowInsetsAnimationCompat(WindowInsetsAnimation windowInsetsAnimation0) {
        this(0, null, 0L);
        if(Build.VERSION.SDK_INT >= 30) {
            this.mImpl = new Impl30(windowInsetsAnimation0);
        }
    }

    public float getAlpha() {
        return this.mImpl.getAlpha();
    }

    public long getDurationMillis() {
        return this.mImpl.getDurationMillis();
    }

    public float getFraction() {
        return this.mImpl.getFraction();
    }

    public float getInterpolatedFraction() {
        return this.mImpl.getInterpolatedFraction();
    }

    public Interpolator getInterpolator() {
        return this.mImpl.getInterpolator();
    }

    public int getTypeMask() {
        return this.mImpl.getTypeMask();
    }

    public void setAlpha(float f) {
        this.mImpl.setAlpha(f);
    }

    static void setCallback(View view0, Callback windowInsetsAnimationCompat$Callback0) {
        if(Build.VERSION.SDK_INT >= 30) {
            Impl30.setCallback(view0, windowInsetsAnimationCompat$Callback0);
            return;
        }
        Impl21.setCallback(view0, windowInsetsAnimationCompat$Callback0);
    }

    public void setFraction(float f) {
        this.mImpl.setFraction(f);
    }

    static WindowInsetsAnimationCompat toWindowInsetsAnimationCompat(WindowInsetsAnimation windowInsetsAnimation0) {
        return new WindowInsetsAnimationCompat(windowInsetsAnimation0);
    }
}

