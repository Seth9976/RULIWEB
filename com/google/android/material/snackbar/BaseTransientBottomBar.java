package com.google.android.material.snackbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityManager;
import android.widget.FrameLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R.attr;
import com.google.android.material.R.dimen;
import com.google.android.material.R.layout;
import com.google.android.material.R.styleable;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.behavior.SwipeDismissBehavior.OnDismissListener;
import com.google.android.material.behavior.SwipeDismissBehavior;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.internal.WindowUtils;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;

public abstract class BaseTransientBottomBar {
    static class Anchor implements View.OnAttachStateChangeListener, ViewTreeObserver.OnGlobalLayoutListener {
        private final WeakReference anchorView;
        private final WeakReference transientBottomBar;

        private Anchor(BaseTransientBottomBar baseTransientBottomBar0, View view0) {
            this.transientBottomBar = new WeakReference(baseTransientBottomBar0);
            this.anchorView = new WeakReference(view0);
        }

        static Anchor anchor(BaseTransientBottomBar baseTransientBottomBar0, View view0) {
            Anchor baseTransientBottomBar$Anchor0 = new Anchor(baseTransientBottomBar0, view0);
            if(ViewCompat.isAttachedToWindow(view0)) {
                ViewUtils.addOnGlobalLayoutListener(view0, baseTransientBottomBar$Anchor0);
            }
            view0.addOnAttachStateChangeListener(baseTransientBottomBar$Anchor0);
            return baseTransientBottomBar$Anchor0;
        }

        View getAnchorView() {
            return (View)this.anchorView.get();
        }

        @Override  // android.view.ViewTreeObserver$OnGlobalLayoutListener
        public void onGlobalLayout() {
            if(!this.unanchorIfNoTransientBottomBar() && ((BaseTransientBottomBar)this.transientBottomBar.get()).anchorViewLayoutListenerEnabled) {
                ((BaseTransientBottomBar)this.transientBottomBar.get()).recalculateAndUpdateMargins();
            }
        }

        @Override  // android.view.View$OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view0) {
            if(this.unanchorIfNoTransientBottomBar()) {
                return;
            }
            ViewUtils.addOnGlobalLayoutListener(view0, this);
        }

        @Override  // android.view.View$OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view0) {
            if(this.unanchorIfNoTransientBottomBar()) {
                return;
            }
            ViewUtils.removeOnGlobalLayoutListener(view0, this);
        }

        void unanchor() {
            if(this.anchorView.get() != null) {
                ((View)this.anchorView.get()).removeOnAttachStateChangeListener(this);
                ViewUtils.removeOnGlobalLayoutListener(((View)this.anchorView.get()), this);
            }
            this.anchorView.clear();
            this.transientBottomBar.clear();
        }

        private boolean unanchorIfNoTransientBottomBar() {
            if(this.transientBottomBar.get() == null) {
                this.unanchor();
                return true;
            }
            return false;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface AnimationMode {
    }

    public static abstract class BaseCallback {
        @Retention(RetentionPolicy.SOURCE)
        public @interface DismissEvent {
        }

        public static final int DISMISS_EVENT_ACTION = 1;
        public static final int DISMISS_EVENT_CONSECUTIVE = 4;
        public static final int DISMISS_EVENT_MANUAL = 3;
        public static final int DISMISS_EVENT_SWIPE = 0;
        public static final int DISMISS_EVENT_TIMEOUT = 2;

        public void onDismissed(Object object0, int v) {
        }

        public void onShown(Object object0) {
        }
    }

    public static class Behavior extends SwipeDismissBehavior {
        private final BehaviorDelegate delegate;

        public Behavior() {
            this.delegate = new BehaviorDelegate(this);
        }

        static void access$1100(Behavior baseTransientBottomBar$Behavior0, BaseTransientBottomBar baseTransientBottomBar0) {
            baseTransientBottomBar$Behavior0.setBaseTransientBottomBar(baseTransientBottomBar0);
        }

        @Override  // com.google.android.material.behavior.SwipeDismissBehavior
        public boolean canSwipeDismissView(View view0) {
            return this.delegate.canSwipeDismissView(view0);
        }

        @Override  // com.google.android.material.behavior.SwipeDismissBehavior
        public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout0, View view0, MotionEvent motionEvent0) {
            this.delegate.onInterceptTouchEvent(coordinatorLayout0, view0, motionEvent0);
            return super.onInterceptTouchEvent(coordinatorLayout0, view0, motionEvent0);
        }

        private void setBaseTransientBottomBar(BaseTransientBottomBar baseTransientBottomBar0) {
            this.delegate.setBaseTransientBottomBar(baseTransientBottomBar0);
        }
    }

    public static class BehaviorDelegate {
        private Callback managerCallback;

        public BehaviorDelegate(SwipeDismissBehavior swipeDismissBehavior0) {
            swipeDismissBehavior0.setStartAlphaSwipeDistance(0.1f);
            swipeDismissBehavior0.setEndAlphaSwipeDistance(0.6f);
            swipeDismissBehavior0.setSwipeDirection(0);
        }

        public boolean canSwipeDismissView(View view0) {
            return view0 instanceof SnackbarBaseLayout;
        }

        public void onInterceptTouchEvent(CoordinatorLayout coordinatorLayout0, View view0, MotionEvent motionEvent0) {
            switch(motionEvent0.getActionMasked()) {
                case 0: {
                    if(coordinatorLayout0.isPointInChildBounds(view0, ((int)motionEvent0.getX()), ((int)motionEvent0.getY()))) {
                        SnackbarManager.getInstance().pauseTimeout(this.managerCallback);
                    }
                    return;
                }
                case 1: 
                case 3: {
                    SnackbarManager.getInstance().restoreTimeoutIfPaused(this.managerCallback);
                }
            }
        }

        public void setBaseTransientBottomBar(BaseTransientBottomBar baseTransientBottomBar0) {
            this.managerCallback = baseTransientBottomBar0.managerCallback;
        }
    }

    @Deprecated
    public interface ContentViewCallback extends com.google.android.material.snackbar.ContentViewCallback {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Duration {
    }

    public static class SnackbarBaseLayout extends FrameLayout {
        private final float actionTextColorAlpha;
        private boolean addingToTargetParent;
        private int animationMode;
        private final float backgroundOverlayColorAlpha;
        private ColorStateList backgroundTint;
        private PorterDuff.Mode backgroundTintMode;
        private BaseTransientBottomBar baseTransientBottomBar;
        private static final View.OnTouchListener consumeAllTouchListener;
        private final int maxInlineActionWidth;
        private final int maxWidth;
        private Rect originalMargins;
        ShapeAppearanceModel shapeAppearanceModel;

        static {
            SnackbarBaseLayout.consumeAllTouchListener = new View.OnTouchListener() {
                @Override  // android.view.View$OnTouchListener
                public boolean onTouch(View view0, MotionEvent motionEvent0) {
                    return true;
                }
            };
        }

        protected SnackbarBaseLayout(Context context0) {
            this(context0, null);
        }

        protected SnackbarBaseLayout(Context context0, AttributeSet attributeSet0) {
            super(MaterialThemeOverlay.wrap(context0, attributeSet0, 0, 0), attributeSet0);
            Context context1 = this.getContext();
            TypedArray typedArray0 = context1.obtainStyledAttributes(attributeSet0, styleable.SnackbarLayout);
            if(typedArray0.hasValue(styleable.SnackbarLayout_elevation)) {
                ViewCompat.setElevation(this, ((float)typedArray0.getDimensionPixelSize(styleable.SnackbarLayout_elevation, 0)));
            }
            this.animationMode = typedArray0.getInt(styleable.SnackbarLayout_animationMode, 0);
            if(typedArray0.hasValue(styleable.SnackbarLayout_shapeAppearance) || typedArray0.hasValue(styleable.SnackbarLayout_shapeAppearanceOverlay)) {
                this.shapeAppearanceModel = ShapeAppearanceModel.builder(context1, attributeSet0, 0, 0).build();
            }
            this.backgroundOverlayColorAlpha = typedArray0.getFloat(styleable.SnackbarLayout_backgroundOverlayColorAlpha, 1.0f);
            this.setBackgroundTintList(MaterialResources.getColorStateList(context1, typedArray0, styleable.SnackbarLayout_backgroundTint));
            this.setBackgroundTintMode(ViewUtils.parseTintMode(typedArray0.getInt(styleable.SnackbarLayout_backgroundTintMode, -1), PorterDuff.Mode.SRC_IN));
            this.actionTextColorAlpha = typedArray0.getFloat(styleable.SnackbarLayout_actionTextColorAlpha, 1.0f);
            this.maxWidth = typedArray0.getDimensionPixelSize(styleable.SnackbarLayout_android_maxWidth, -1);
            this.maxInlineActionWidth = typedArray0.getDimensionPixelSize(styleable.SnackbarLayout_maxActionInlineWidth, -1);
            typedArray0.recycle();
            this.setOnTouchListener(SnackbarBaseLayout.consumeAllTouchListener);
            this.setFocusable(true);
            if(this.getBackground() == null) {
                ViewCompat.setBackground(this, this.createThemedBackground());
            }
        }

        static Rect access$1000(SnackbarBaseLayout baseTransientBottomBar$SnackbarBaseLayout0) {
            return baseTransientBottomBar$SnackbarBaseLayout0.originalMargins;
        }

        static void access$500(SnackbarBaseLayout baseTransientBottomBar$SnackbarBaseLayout0, BaseTransientBottomBar baseTransientBottomBar0) {
            baseTransientBottomBar$SnackbarBaseLayout0.setBaseTransientBottomBar(baseTransientBottomBar0);
        }

        void addToTargetParent(ViewGroup viewGroup0) {
            this.addingToTargetParent = true;
            viewGroup0.addView(this);
            this.addingToTargetParent = false;
        }

        private Drawable createThemedBackground() {
            int v = MaterialColors.layer(this, attr.colorSurface, attr.colorOnSurface, this.getBackgroundOverlayColorAlpha());
            ShapeAppearanceModel shapeAppearanceModel0 = this.shapeAppearanceModel;
            MaterialShapeDrawable materialShapeDrawable0 = shapeAppearanceModel0 == null ? BaseTransientBottomBar.createGradientDrawableBackground(v, this.getResources()) : BaseTransientBottomBar.createMaterialShapeDrawableBackground(v, shapeAppearanceModel0);
            if(this.backgroundTint != null) {
                Drawable drawable0 = DrawableCompat.wrap(materialShapeDrawable0);
                DrawableCompat.setTintList(drawable0, this.backgroundTint);
                return drawable0;
            }
            return DrawableCompat.wrap(materialShapeDrawable0);
        }

        float getActionTextColorAlpha() {
            return this.actionTextColorAlpha;
        }

        int getAnimationMode() {
            return this.animationMode;
        }

        float getBackgroundOverlayColorAlpha() {
            return this.backgroundOverlayColorAlpha;
        }

        int getMaxInlineActionWidth() {
            return this.maxInlineActionWidth;
        }

        int getMaxWidth() {
            return this.maxWidth;
        }

        @Override  // android.view.ViewGroup
        protected void onAttachedToWindow() {
            super.onAttachedToWindow();
            BaseTransientBottomBar baseTransientBottomBar0 = this.baseTransientBottomBar;
            if(baseTransientBottomBar0 != null) {
                baseTransientBottomBar0.onAttachedToWindow();
            }
            ViewCompat.requestApplyInsets(this);
        }

        @Override  // android.view.ViewGroup
        protected void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            BaseTransientBottomBar baseTransientBottomBar0 = this.baseTransientBottomBar;
            if(baseTransientBottomBar0 != null) {
                baseTransientBottomBar0.onDetachedFromWindow();
            }
        }

        @Override  // android.widget.FrameLayout
        protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
            super.onLayout(z, v, v1, v2, v3);
            BaseTransientBottomBar baseTransientBottomBar0 = this.baseTransientBottomBar;
            if(baseTransientBottomBar0 != null) {
                baseTransientBottomBar0.onLayoutChange();
            }
        }

        @Override  // android.widget.FrameLayout
        protected void onMeasure(int v, int v1) {
            super.onMeasure(v, v1);
            if(this.maxWidth > 0) {
                int v2 = this.getMeasuredWidth();
                int v3 = this.maxWidth;
                if(v2 > v3) {
                    super.onMeasure(View.MeasureSpec.makeMeasureSpec(v3, 0x40000000), v1);
                }
            }
        }

        void setAnimationMode(int v) {
            this.animationMode = v;
        }

        @Override  // android.view.View
        public void setBackground(Drawable drawable0) {
            this.setBackgroundDrawable(drawable0);
        }

        @Override  // android.view.View
        public void setBackgroundDrawable(Drawable drawable0) {
            if(drawable0 != null && this.backgroundTint != null) {
                drawable0 = DrawableCompat.wrap(drawable0.mutate());
                DrawableCompat.setTintList(drawable0, this.backgroundTint);
                DrawableCompat.setTintMode(drawable0, this.backgroundTintMode);
            }
            super.setBackgroundDrawable(drawable0);
        }

        @Override  // android.view.View
        public void setBackgroundTintList(ColorStateList colorStateList0) {
            this.backgroundTint = colorStateList0;
            if(this.getBackground() != null) {
                Drawable drawable0 = DrawableCompat.wrap(this.getBackground().mutate());
                DrawableCompat.setTintList(drawable0, colorStateList0);
                DrawableCompat.setTintMode(drawable0, this.backgroundTintMode);
                if(drawable0 != this.getBackground()) {
                    super.setBackgroundDrawable(drawable0);
                }
            }
        }

        @Override  // android.view.View
        public void setBackgroundTintMode(PorterDuff.Mode porterDuff$Mode0) {
            this.backgroundTintMode = porterDuff$Mode0;
            if(this.getBackground() != null) {
                Drawable drawable0 = DrawableCompat.wrap(this.getBackground().mutate());
                DrawableCompat.setTintMode(drawable0, porterDuff$Mode0);
                if(drawable0 != this.getBackground()) {
                    super.setBackgroundDrawable(drawable0);
                }
            }
        }

        private void setBaseTransientBottomBar(BaseTransientBottomBar baseTransientBottomBar0) {
            this.baseTransientBottomBar = baseTransientBottomBar0;
        }

        @Override  // android.view.View
        public void setLayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
            super.setLayoutParams(viewGroup$LayoutParams0);
            if(!this.addingToTargetParent && viewGroup$LayoutParams0 instanceof ViewGroup.MarginLayoutParams) {
                this.updateOriginalMargins(((ViewGroup.MarginLayoutParams)viewGroup$LayoutParams0));
                BaseTransientBottomBar baseTransientBottomBar0 = this.baseTransientBottomBar;
                if(baseTransientBottomBar0 != null) {
                    baseTransientBottomBar0.updateMargins();
                }
            }
        }

        @Override  // android.view.View
        public void setOnClickListener(View.OnClickListener view$OnClickListener0) {
            this.setOnTouchListener((view$OnClickListener0 == null ? SnackbarBaseLayout.consumeAllTouchListener : null));
            super.setOnClickListener(view$OnClickListener0);
        }

        private void updateOriginalMargins(ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0) {
            this.originalMargins = new Rect(viewGroup$MarginLayoutParams0.leftMargin, viewGroup$MarginLayoutParams0.topMargin, viewGroup$MarginLayoutParams0.rightMargin, viewGroup$MarginLayoutParams0.bottomMargin);
        }
    }

    public static final int ANIMATION_MODE_FADE = 1;
    public static final int ANIMATION_MODE_SLIDE = 0;
    private static final float ANIMATION_SCALE_FROM_VALUE = 0.8f;
    static final int DEFAULT_ANIMATION_FADE_DURATION = 180;
    private static final TimeInterpolator DEFAULT_ANIMATION_FADE_INTERPOLATOR = null;
    private static final int DEFAULT_ANIMATION_FADE_IN_DURATION = 150;
    private static final int DEFAULT_ANIMATION_FADE_OUT_DURATION = 75;
    private static final TimeInterpolator DEFAULT_ANIMATION_SCALE_INTERPOLATOR = null;
    private static final TimeInterpolator DEFAULT_ANIMATION_SLIDE_INTERPOLATOR = null;
    static final int DEFAULT_SLIDE_ANIMATION_DURATION = 0xFA;
    public static final int LENGTH_INDEFINITE = -2;
    public static final int LENGTH_LONG = 0;
    public static final int LENGTH_SHORT = -1;
    static final int MSG_DISMISS = 1;
    static final int MSG_SHOW;
    private static final int[] SNACKBAR_STYLE_ATTR;
    private static final String TAG;
    private static final boolean USE_OFFSET_API;
    private final AccessibilityManager accessibilityManager;
    private Anchor anchor;
    private boolean anchorViewLayoutListenerEnabled;
    private final int animationFadeInDuration;
    private final TimeInterpolator animationFadeInterpolator;
    private final int animationFadeOutDuration;
    private final TimeInterpolator animationScaleInterpolator;
    private final int animationSlideDuration;
    private final TimeInterpolator animationSlideInterpolator;
    private int appliedBottomMarginGestureInset;
    private Behavior behavior;
    private final Runnable bottomMarginGestureInsetRunnable;
    private List callbacks;
    private final com.google.android.material.snackbar.ContentViewCallback contentViewCallback;
    private final Context context;
    private int duration;
    private int extraBottomMarginAnchorView;
    private int extraBottomMarginGestureInset;
    private int extraBottomMarginWindowInset;
    private int extraLeftMarginWindowInset;
    private int extraRightMarginWindowInset;
    private boolean gestureInsetBottomIgnored;
    static final Handler handler;
    Callback managerCallback;
    private boolean pendingShowingView;
    private final ViewGroup targetParent;
    protected final SnackbarBaseLayout view;

    static {
        BaseTransientBottomBar.DEFAULT_ANIMATION_SLIDE_INTERPOLATOR = AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR;
        BaseTransientBottomBar.DEFAULT_ANIMATION_FADE_INTERPOLATOR = AnimationUtils.LINEAR_INTERPOLATOR;
        BaseTransientBottomBar.DEFAULT_ANIMATION_SCALE_INTERPOLATOR = AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR;
        BaseTransientBottomBar.USE_OFFSET_API = false;
        BaseTransientBottomBar.SNACKBAR_STYLE_ATTR = new int[]{attr.snackbarStyle};
        BaseTransientBottomBar.TAG = "BaseTransientBottomBar";
        BaseTransientBottomBar.handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override  // android.os.Handler$Callback
            public boolean handleMessage(Message message0) {
                switch(message0.what) {
                    case 0: {
                        ((BaseTransientBottomBar)message0.obj).showView();
                        return true;
                    }
                    case 1: {
                        ((BaseTransientBottomBar)message0.obj).hideView(message0.arg1);
                        return true;
                    }
                    default: {
                        return false;
                    }
                }
            }
        });
    }

    protected BaseTransientBottomBar(Context context0, ViewGroup viewGroup0, View view0, com.google.android.material.snackbar.ContentViewCallback contentViewCallback0) {
        this.anchorViewLayoutListenerEnabled = false;
        this.bottomMarginGestureInsetRunnable = new Runnable() {
            @Override
            public void run() {
                if(BaseTransientBottomBar.this.view != null && BaseTransientBottomBar.this.context != null) {
                    int v = WindowUtils.getCurrentWindowBounds(BaseTransientBottomBar.this.context).height() - BaseTransientBottomBar.this.getViewAbsoluteBottom() + ((int)BaseTransientBottomBar.this.view.getTranslationY());
                    if(v >= BaseTransientBottomBar.this.extraBottomMarginGestureInset) {
                        BaseTransientBottomBar.this.appliedBottomMarginGestureInset = BaseTransientBottomBar.this.extraBottomMarginGestureInset;
                        return;
                    }
                    ViewGroup.LayoutParams viewGroup$LayoutParams0 = BaseTransientBottomBar.this.view.getLayoutParams();
                    if(!(viewGroup$LayoutParams0 instanceof ViewGroup.MarginLayoutParams)) {
                        Log.w("BaseTransientBottomBar", "Unable to apply gesture inset because layout params are not MarginLayoutParams");
                        return;
                    }
                    BaseTransientBottomBar.this.appliedBottomMarginGestureInset = BaseTransientBottomBar.this.extraBottomMarginGestureInset;
                    ((ViewGroup.MarginLayoutParams)viewGroup$LayoutParams0).bottomMargin += BaseTransientBottomBar.this.extraBottomMarginGestureInset - v;
                    BaseTransientBottomBar.this.view.requestLayout();
                }
            }
        };
        this.managerCallback = new Callback() {
            @Override  // com.google.android.material.snackbar.SnackbarManager$Callback
            public void dismiss(int v) {
                Message message0 = BaseTransientBottomBar.handler.obtainMessage(1, v, 0, BaseTransientBottomBar.this);
                BaseTransientBottomBar.handler.sendMessage(message0);
            }

            @Override  // com.google.android.material.snackbar.SnackbarManager$Callback
            public void show() {
                Message message0 = BaseTransientBottomBar.handler.obtainMessage(0, BaseTransientBottomBar.this);
                BaseTransientBottomBar.handler.sendMessage(message0);
            }
        };
        if(viewGroup0 == null) {
            throw new IllegalArgumentException("Transient bottom bar must have non-null parent");
        }
        if(view0 == null) {
            throw new IllegalArgumentException("Transient bottom bar must have non-null content");
        }
        if(contentViewCallback0 == null) {
            throw new IllegalArgumentException("Transient bottom bar must have non-null callback");
        }
        this.targetParent = viewGroup0;
        this.contentViewCallback = contentViewCallback0;
        this.context = context0;
        ThemeEnforcement.checkAppCompatTheme(context0);
        SnackbarBaseLayout baseTransientBottomBar$SnackbarBaseLayout0 = (SnackbarBaseLayout)LayoutInflater.from(context0).inflate(this.getSnackbarBaseLayoutResId(), viewGroup0, false);
        this.view = baseTransientBottomBar$SnackbarBaseLayout0;
        SnackbarBaseLayout.access$500(baseTransientBottomBar$SnackbarBaseLayout0, this);
        if(view0 instanceof SnackbarContentLayout) {
            ((SnackbarContentLayout)view0).updateActionTextColorAlphaIfNeeded(baseTransientBottomBar$SnackbarBaseLayout0.getActionTextColorAlpha());
            ((SnackbarContentLayout)view0).setMaxInlineActionWidth(baseTransientBottomBar$SnackbarBaseLayout0.getMaxInlineActionWidth());
        }
        baseTransientBottomBar$SnackbarBaseLayout0.addView(view0);
        ViewCompat.setAccessibilityLiveRegion(baseTransientBottomBar$SnackbarBaseLayout0, 1);
        ViewCompat.setImportantForAccessibility(baseTransientBottomBar$SnackbarBaseLayout0, 1);
        ViewCompat.setFitsSystemWindows(baseTransientBottomBar$SnackbarBaseLayout0, true);
        ViewCompat.setOnApplyWindowInsetsListener(baseTransientBottomBar$SnackbarBaseLayout0, new OnApplyWindowInsetsListener() {
            @Override  // androidx.core.view.OnApplyWindowInsetsListener
            public WindowInsetsCompat onApplyWindowInsets(View view0, WindowInsetsCompat windowInsetsCompat0) {
                int v = windowInsetsCompat0.getSystemWindowInsetBottom();
                BaseTransientBottomBar.access$602(BaseTransientBottomBar.this, v);
                int v1 = windowInsetsCompat0.getSystemWindowInsetLeft();
                BaseTransientBottomBar.access$702(BaseTransientBottomBar.this, v1);
                int v2 = windowInsetsCompat0.getSystemWindowInsetRight();
                BaseTransientBottomBar.access$802(BaseTransientBottomBar.this, v2);
                BaseTransientBottomBar.access$900(BaseTransientBottomBar.this);
                return windowInsetsCompat0;
            }
        });
        ViewCompat.setAccessibilityDelegate(baseTransientBottomBar$SnackbarBaseLayout0, new AccessibilityDelegateCompat() {
            @Override  // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
                super.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfoCompat0);
                accessibilityNodeInfoCompat0.addAction(0x100000);
                accessibilityNodeInfoCompat0.setDismissable(true);
            }

            @Override  // androidx.core.view.AccessibilityDelegateCompat
            public boolean performAccessibilityAction(View view0, int v, Bundle bundle0) {
                if(v == 0x100000) {
                    BaseTransientBottomBar.this.dismiss();
                    return true;
                }
                return super.performAccessibilityAction(view0, v, bundle0);
            }
        });
        this.accessibilityManager = (AccessibilityManager)context0.getSystemService("accessibility");
        this.animationSlideDuration = MotionUtils.resolveThemeDuration(context0, attr.motionDurationLong2, 0xFA);
        this.animationFadeInDuration = MotionUtils.resolveThemeDuration(context0, attr.motionDurationLong2, 150);
        this.animationFadeOutDuration = MotionUtils.resolveThemeDuration(context0, attr.motionDurationMedium1, 75);
        this.animationFadeInterpolator = MotionUtils.resolveThemeInterpolator(context0, attr.motionEasingEmphasizedInterpolator, BaseTransientBottomBar.DEFAULT_ANIMATION_FADE_INTERPOLATOR);
        this.animationScaleInterpolator = MotionUtils.resolveThemeInterpolator(context0, attr.motionEasingEmphasizedInterpolator, BaseTransientBottomBar.DEFAULT_ANIMATION_SCALE_INTERPOLATOR);
        this.animationSlideInterpolator = MotionUtils.resolveThemeInterpolator(context0, attr.motionEasingEmphasizedInterpolator, BaseTransientBottomBar.DEFAULT_ANIMATION_SLIDE_INTERPOLATOR);
    }

    protected BaseTransientBottomBar(ViewGroup viewGroup0, View view0, com.google.android.material.snackbar.ContentViewCallback contentViewCallback0) {
        this(viewGroup0.getContext(), viewGroup0, view0, contentViewCallback0);
    }

    static String access$400() {
        return BaseTransientBottomBar.TAG;
    }

    static int access$602(BaseTransientBottomBar baseTransientBottomBar0, int v) {
        baseTransientBottomBar0.extraBottomMarginWindowInset = v;
        return v;
    }

    static int access$702(BaseTransientBottomBar baseTransientBottomBar0, int v) {
        baseTransientBottomBar0.extraLeftMarginWindowInset = v;
        return v;
    }

    static int access$802(BaseTransientBottomBar baseTransientBottomBar0, int v) {
        baseTransientBottomBar0.extraRightMarginWindowInset = v;
        return v;
    }

    public BaseTransientBottomBar addCallback(BaseCallback baseTransientBottomBar$BaseCallback0) {
        if(baseTransientBottomBar$BaseCallback0 == null) {
            return this;
        }
        if(this.callbacks == null) {
            this.callbacks = new ArrayList();
        }
        this.callbacks.add(baseTransientBottomBar$BaseCallback0);
        return this;
    }

    void animateViewIn() {
        com.google.android.material.snackbar.BaseTransientBottomBar.8 baseTransientBottomBar$80 = new Runnable() {
            @Override
            public void run() {
                if(BaseTransientBottomBar.this.view == null) {
                    return;
                }
                if(BaseTransientBottomBar.this.view.getParent() != null) {
                    BaseTransientBottomBar.this.view.setVisibility(0);
                }
                if(BaseTransientBottomBar.this.view.getAnimationMode() == 1) {
                    BaseTransientBottomBar.this.startFadeInAnimation();
                    return;
                }
                BaseTransientBottomBar.this.startSlideInAnimation();
            }
        };
        this.view.post(baseTransientBottomBar$80);
    }

    private void animateViewOut(int v) {
        if(this.view.getAnimationMode() == 1) {
            this.startFadeOutAnimation(v);
            return;
        }
        this.startSlideOutAnimation(v);
    }

    private int calculateBottomMarginForAnchorView() {
        if(this.getAnchorView() == null) {
            return 0;
        }
        int[] arr_v = new int[2];
        this.getAnchorView().getLocationOnScreen(arr_v);
        int v = arr_v[1];
        int[] arr_v1 = new int[2];
        this.targetParent.getLocationOnScreen(arr_v1);
        return arr_v1[1] + this.targetParent.getHeight() - v;
    }

    private static GradientDrawable createGradientDrawableBackground(int v, Resources resources0) {
        float f = resources0.getDimension(dimen.mtrl_snackbar_background_corner_radius);
        GradientDrawable gradientDrawable0 = new GradientDrawable();
        gradientDrawable0.setShape(0);
        gradientDrawable0.setCornerRadius(f);
        gradientDrawable0.setColor(v);
        return gradientDrawable0;
    }

    private static MaterialShapeDrawable createMaterialShapeDrawableBackground(int v, ShapeAppearanceModel shapeAppearanceModel0) {
        MaterialShapeDrawable materialShapeDrawable0 = new MaterialShapeDrawable(shapeAppearanceModel0);
        materialShapeDrawable0.setFillColor(ColorStateList.valueOf(v));
        return materialShapeDrawable0;
    }

    public void dismiss() {
        this.dispatchDismiss(3);
    }

    protected void dispatchDismiss(int v) {
        SnackbarManager.getInstance().dismiss(this.managerCallback, v);
    }

    private ValueAnimator getAlphaAnimator(float[] arr_f) {
        ValueAnimator valueAnimator0 = ValueAnimator.ofFloat(arr_f);
        valueAnimator0.setInterpolator(this.animationFadeInterpolator);
        valueAnimator0.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override  // android.animation.ValueAnimator$AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator0) {
                float f = (float)(((Float)valueAnimator0.getAnimatedValue()));
                BaseTransientBottomBar.this.view.setAlpha(f);
            }
        });
        return valueAnimator0;
    }

    public View getAnchorView() {
        return this.anchor == null ? null : this.anchor.getAnchorView();
    }

    public int getAnimationMode() {
        return this.view.getAnimationMode();
    }

    public Behavior getBehavior() {
        return this.behavior;
    }

    public Context getContext() {
        return this.context;
    }

    public int getDuration() {
        return this.duration;
    }

    protected SwipeDismissBehavior getNewBehavior() {
        return new Behavior();
    }

    private ValueAnimator getScaleAnimator(float[] arr_f) {
        ValueAnimator valueAnimator0 = ValueAnimator.ofFloat(arr_f);
        valueAnimator0.setInterpolator(this.animationScaleInterpolator);
        valueAnimator0.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override  // android.animation.ValueAnimator$AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator0) {
                float f = (float)(((Float)valueAnimator0.getAnimatedValue()));
                BaseTransientBottomBar.this.view.setScaleX(f);
                BaseTransientBottomBar.this.view.setScaleY(f);
            }
        });
        return valueAnimator0;
    }

    // 去混淆评级： 低(20)
    protected int getSnackbarBaseLayoutResId() {
        return this.hasSnackbarStyleAttr() ? layout.mtrl_layout_snackbar : layout.design_layout_snackbar;
    }

    private int getTranslationYBottom() {
        int v = this.view.getHeight();
        ViewGroup.LayoutParams viewGroup$LayoutParams0 = this.view.getLayoutParams();
        return viewGroup$LayoutParams0 instanceof ViewGroup.MarginLayoutParams ? v + ((ViewGroup.MarginLayoutParams)viewGroup$LayoutParams0).bottomMargin : v;
    }

    public View getView() {
        return this.view;
    }

    private int getViewAbsoluteBottom() {
        int[] arr_v = new int[2];
        this.view.getLocationInWindow(arr_v);
        return arr_v[1] + this.view.getHeight();
    }

    protected boolean hasSnackbarStyleAttr() {
        TypedArray typedArray0 = this.context.obtainStyledAttributes(BaseTransientBottomBar.SNACKBAR_STYLE_ATTR);
        int v = typedArray0.getResourceId(0, -1);
        typedArray0.recycle();
        return v != -1;
    }

    final void hideView(int v) {
        if(this.shouldAnimate() && this.view.getVisibility() == 0) {
            this.animateViewOut(v);
            return;
        }
        this.onViewHidden(v);
    }

    public boolean isAnchorViewLayoutListenerEnabled() {
        return this.anchorViewLayoutListenerEnabled;
    }

    public boolean isGestureInsetBottomIgnored() {
        return this.gestureInsetBottomIgnored;
    }

    public boolean isShown() {
        return SnackbarManager.getInstance().isCurrent(this.managerCallback);
    }

    public boolean isShownOrQueued() {
        return SnackbarManager.getInstance().isCurrentOrNext(this.managerCallback);
    }

    private boolean isSwipeDismissable() {
        ViewGroup.LayoutParams viewGroup$LayoutParams0 = this.view.getLayoutParams();
        return viewGroup$LayoutParams0 instanceof LayoutParams && ((LayoutParams)viewGroup$LayoutParams0).getBehavior() instanceof SwipeDismissBehavior;
    }

    void onAttachedToWindow() {
        if(Build.VERSION.SDK_INT >= 29) {
            WindowInsets windowInsets0 = LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.view);
            if(windowInsets0 != null) {
                this.extraBottomMarginGestureInset = windowInsets0.getMandatorySystemGestureInsets().bottom;
                this.updateMargins();
            }
        }
    }

    void onDetachedFromWindow() {
        if(this.isShownOrQueued()) {
            com.google.android.material.snackbar.BaseTransientBottomBar.6 baseTransientBottomBar$60 = () -> {
                SnackbarManager.getInstance().onDismissed(BaseTransientBottomBar.this.managerCallback);
                List list0 = BaseTransientBottomBar.this.callbacks;
                if(list0 != null) {
                    for(int v1 = list0.size() - 1; v1 >= 0; --v1) {
                        ((BaseCallback)BaseTransientBottomBar.this.callbacks.get(v1)).onDismissed(BaseTransientBottomBar.this, 3);
                    }
                }
                ViewParent viewParent0 = BaseTransientBottomBar.this.view.getParent();
                if(viewParent0 instanceof ViewGroup) {
                    ((ViewGroup)viewParent0).removeView(BaseTransientBottomBar.this.view);
                }
            };
            BaseTransientBottomBar.handler.post(baseTransientBottomBar$60);
        }

        class com.google.android.material.snackbar.BaseTransientBottomBar.6 implements Runnable {
            @Override
            public void run() {
                BaseTransientBottomBar.this.onViewHidden(3);
            }
        }

    }

    void onLayoutChange() {
        if(this.pendingShowingView) {
            this.showViewImpl();
            this.pendingShowingView = false;
        }
    }

    // 检测为 Lambda 实现
    void onViewHidden(int v) [...]

    void onViewShown() {
        SnackbarManager.getInstance().onShown(this.managerCallback);
        List list0 = this.callbacks;
        if(list0 != null) {
            for(int v = list0.size() - 1; v >= 0; --v) {
                ((BaseCallback)this.callbacks.get(v)).onShown(this);
            }
        }
    }

    private void recalculateAndUpdateMargins() {
        this.extraBottomMarginAnchorView = this.calculateBottomMarginForAnchorView();
        this.updateMargins();
    }

    public BaseTransientBottomBar removeCallback(BaseCallback baseTransientBottomBar$BaseCallback0) {
        if(baseTransientBottomBar$BaseCallback0 != null) {
            List list0 = this.callbacks;
            if(list0 != null) {
                list0.remove(baseTransientBottomBar$BaseCallback0);
                return this;
            }
        }
        return this;
    }

    public BaseTransientBottomBar setAnchorView(int v) {
        View view0 = this.targetParent.findViewById(v);
        if(view0 == null) {
            throw new IllegalArgumentException("Unable to find anchor view with id: " + v);
        }
        return this.setAnchorView(view0);
    }

    public BaseTransientBottomBar setAnchorView(View view0) {
        Anchor baseTransientBottomBar$Anchor0 = this.anchor;
        if(baseTransientBottomBar$Anchor0 != null) {
            baseTransientBottomBar$Anchor0.unanchor();
        }
        this.anchor = view0 == null ? null : Anchor.anchor(this, view0);
        return this;
    }

    public void setAnchorViewLayoutListenerEnabled(boolean z) {
        this.anchorViewLayoutListenerEnabled = z;
    }

    public BaseTransientBottomBar setAnimationMode(int v) {
        this.view.setAnimationMode(v);
        return this;
    }

    public BaseTransientBottomBar setBehavior(Behavior baseTransientBottomBar$Behavior0) {
        this.behavior = baseTransientBottomBar$Behavior0;
        return this;
    }

    public BaseTransientBottomBar setDuration(int v) {
        this.duration = v;
        return this;
    }

    public BaseTransientBottomBar setGestureInsetBottomIgnored(boolean z) {
        this.gestureInsetBottomIgnored = z;
        return this;
    }

    private void setUpBehavior(LayoutParams coordinatorLayout$LayoutParams0) {
        SwipeDismissBehavior swipeDismissBehavior0 = this.behavior;
        if(swipeDismissBehavior0 == null) {
            swipeDismissBehavior0 = this.getNewBehavior();
        }
        if(swipeDismissBehavior0 instanceof Behavior) {
            Behavior.access$1100(((Behavior)swipeDismissBehavior0), this);
        }
        swipeDismissBehavior0.setListener(new OnDismissListener() {
            @Override  // com.google.android.material.behavior.SwipeDismissBehavior$OnDismissListener
            public void onDismiss(View view0) {
                if(view0.getParent() != null) {
                    view0.setVisibility(8);
                }
                BaseTransientBottomBar.this.dispatchDismiss(0);
            }

            @Override  // com.google.android.material.behavior.SwipeDismissBehavior$OnDismissListener
            public void onDragStateChanged(int v) {
                switch(v) {
                    case 0: {
                        SnackbarManager.getInstance().restoreTimeoutIfPaused(BaseTransientBottomBar.this.managerCallback);
                        return;
                    }
                    case 1: 
                    case 2: {
                        SnackbarManager.getInstance().pauseTimeout(BaseTransientBottomBar.this.managerCallback);
                    }
                }
            }
        });
        coordinatorLayout$LayoutParams0.setBehavior(swipeDismissBehavior0);
        if(this.getAnchorView() == null) {
            coordinatorLayout$LayoutParams0.insetEdge = 80;
        }
    }

    boolean shouldAnimate() {
        AccessibilityManager accessibilityManager0 = this.accessibilityManager;
        if(accessibilityManager0 == null) {
            return true;
        }
        List list0 = accessibilityManager0.getEnabledAccessibilityServiceList(1);
        return list0 != null && list0.isEmpty();
    }

    // 去混淆评级： 低(20)
    private boolean shouldUpdateGestureInset() {
        return this.extraBottomMarginGestureInset > 0 && !this.gestureInsetBottomIgnored && this.isSwipeDismissable();
    }

    public void show() {
        SnackbarManager.getInstance().show(this.getDuration(), this.managerCallback);
    }

    final void showView() {
        if(this.view.getParent() == null) {
            ViewGroup.LayoutParams viewGroup$LayoutParams0 = this.view.getLayoutParams();
            if(viewGroup$LayoutParams0 instanceof LayoutParams) {
                this.setUpBehavior(((LayoutParams)viewGroup$LayoutParams0));
            }
            this.view.addToTargetParent(this.targetParent);
            this.recalculateAndUpdateMargins();
            this.view.setVisibility(4);
        }
        if(ViewCompat.isLaidOut(this.view)) {
            this.showViewImpl();
            return;
        }
        this.pendingShowingView = true;
    }

    private void showViewImpl() {
        if(this.shouldAnimate()) {
            this.animateViewIn();
            return;
        }
        if(this.view.getParent() != null) {
            this.view.setVisibility(0);
        }
        this.onViewShown();
    }

    private void startFadeInAnimation() {
        ValueAnimator valueAnimator0 = this.getAlphaAnimator(new float[]{0.0f, 1.0f});
        ValueAnimator valueAnimator1 = this.getScaleAnimator(new float[]{0.8f, 1.0f});
        AnimatorSet animatorSet0 = new AnimatorSet();
        animatorSet0.playTogether(new Animator[]{valueAnimator0, valueAnimator1});
        animatorSet0.setDuration(((long)this.animationFadeInDuration));
        animatorSet0.addListener(new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationEnd(Animator animator0) {
                BaseTransientBottomBar.this.onViewShown();
            }
        });
        animatorSet0.start();
    }

    private void startFadeOutAnimation(int v) {
        ValueAnimator valueAnimator0 = this.getAlphaAnimator(new float[]{1.0f, 0.0f});
        valueAnimator0.setDuration(((long)this.animationFadeOutDuration));
        valueAnimator0.addListener(new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationEnd(Animator animator0) {
                BaseTransientBottomBar.this.onViewHidden(v);
            }
        });
        valueAnimator0.start();
    }

    private void startSlideInAnimation() {
        int v = this.getTranslationYBottom();
        if(BaseTransientBottomBar.USE_OFFSET_API) {
            ViewCompat.offsetTopAndBottom(this.view, v);
        }
        else {
            this.view.setTranslationY(((float)v));
        }
        ValueAnimator valueAnimator0 = new ValueAnimator();
        valueAnimator0.setIntValues(new int[]{v, 0});
        valueAnimator0.setInterpolator(this.animationSlideInterpolator);
        valueAnimator0.setDuration(((long)this.animationSlideDuration));
        valueAnimator0.addListener(new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationEnd(Animator animator0) {
                BaseTransientBottomBar.this.onViewShown();
            }

            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationStart(Animator animator0) {
                BaseTransientBottomBar.this.contentViewCallback.animateContentIn(BaseTransientBottomBar.this.animationSlideDuration - BaseTransientBottomBar.this.animationFadeInDuration, BaseTransientBottomBar.this.animationFadeInDuration);
            }
        });
        valueAnimator0.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            private int previousAnimatedIntValue;

            {
                int v = v;  // 捕获的参数 （可能与外部方法变量命名冲突；考虑手动重命名）
                this.previousAnimatedIntValue = v;
            }

            @Override  // android.animation.ValueAnimator$AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator0) {
                int v = (int)(((Integer)valueAnimator0.getAnimatedValue()));
                if(BaseTransientBottomBar.USE_OFFSET_API) {
                    ViewCompat.offsetTopAndBottom(BaseTransientBottomBar.this.view, v - this.previousAnimatedIntValue);
                }
                else {
                    BaseTransientBottomBar.this.view.setTranslationY(((float)v));
                }
                this.previousAnimatedIntValue = v;
            }
        });
        valueAnimator0.start();
    }

    private void startSlideOutAnimation(int v) {
        ValueAnimator valueAnimator0 = new ValueAnimator();
        valueAnimator0.setIntValues(new int[]{0, this.getTranslationYBottom()});
        valueAnimator0.setInterpolator(this.animationSlideInterpolator);
        valueAnimator0.setDuration(((long)this.animationSlideDuration));
        valueAnimator0.addListener(new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationEnd(Animator animator0) {
                BaseTransientBottomBar.this.onViewHidden(v);
            }

            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationStart(Animator animator0) {
                int v = BaseTransientBottomBar.this.animationFadeOutDuration;
                BaseTransientBottomBar.this.contentViewCallback.animateContentOut(0, v);
            }
        });
        valueAnimator0.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            private int previousAnimatedIntValue;

            {
                this.previousAnimatedIntValue = 0;
            }

            @Override  // android.animation.ValueAnimator$AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator0) {
                int v = (int)(((Integer)valueAnimator0.getAnimatedValue()));
                if(BaseTransientBottomBar.USE_OFFSET_API) {
                    ViewCompat.offsetTopAndBottom(BaseTransientBottomBar.this.view, v - this.previousAnimatedIntValue);
                }
                else {
                    BaseTransientBottomBar.this.view.setTranslationY(((float)v));
                }
                this.previousAnimatedIntValue = v;
            }
        });
        valueAnimator0.start();
    }

    private void updateMargins() {
        ViewGroup.LayoutParams viewGroup$LayoutParams0 = this.view.getLayoutParams();
        if(!(viewGroup$LayoutParams0 instanceof ViewGroup.MarginLayoutParams)) {
            Log.w("BaseTransientBottomBar", "Unable to update margins because layout params are not MarginLayoutParams");
            return;
        }
        if(SnackbarBaseLayout.access$1000(this.view) == null) {
            Log.w("BaseTransientBottomBar", "Unable to update margins because original view margins are not set");
            return;
        }
        if(this.view.getParent() != null) {
            int v = this.getAnchorView() == null ? this.extraBottomMarginWindowInset : this.extraBottomMarginAnchorView;
            int v1 = SnackbarBaseLayout.access$1000(this.view).bottom + v;
            int v2 = SnackbarBaseLayout.access$1000(this.view).left + this.extraLeftMarginWindowInset;
            int v3 = SnackbarBaseLayout.access$1000(this.view).right + this.extraRightMarginWindowInset;
            int v4 = SnackbarBaseLayout.access$1000(this.view).top;
            boolean z = ((ViewGroup.MarginLayoutParams)viewGroup$LayoutParams0).bottomMargin != v1 || ((ViewGroup.MarginLayoutParams)viewGroup$LayoutParams0).leftMargin != v2 || ((ViewGroup.MarginLayoutParams)viewGroup$LayoutParams0).rightMargin != v3 || ((ViewGroup.MarginLayoutParams)viewGroup$LayoutParams0).topMargin != v4;
            if(z) {
                ((ViewGroup.MarginLayoutParams)viewGroup$LayoutParams0).bottomMargin = v1;
                ((ViewGroup.MarginLayoutParams)viewGroup$LayoutParams0).leftMargin = v2;
                ((ViewGroup.MarginLayoutParams)viewGroup$LayoutParams0).rightMargin = v3;
                ((ViewGroup.MarginLayoutParams)viewGroup$LayoutParams0).topMargin = v4;
                this.view.requestLayout();
            }
            if((z || this.appliedBottomMarginGestureInset != this.extraBottomMarginGestureInset) && Build.VERSION.SDK_INT >= 29 && this.shouldUpdateGestureInset()) {
                this.view.removeCallbacks(this.bottomMarginGestureInsetRunnable);
                this.view.post(this.bottomMarginGestureInsetRunnable);
            }
        }
    }
}

