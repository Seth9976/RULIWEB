package androidx.vectordrawable.graphics.drawable;

import android.animation.Animator.AnimatorListener;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources.Theme;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import androidx.collection.ArrayMap;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.graphics.drawable.DrawableCompat;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class AnimatedVectorDrawableCompat extends VectorDrawableCommon implements Animatable2Compat {
    static class AnimatedVectorDrawableCompatState extends Drawable.ConstantState {
        AnimatorSet mAnimatorSet;
        ArrayList mAnimators;
        int mChangingConfigurations;
        ArrayMap mTargetNameMap;
        VectorDrawableCompat mVectorDrawable;

        public AnimatedVectorDrawableCompatState(Context context0, AnimatedVectorDrawableCompatState animatedVectorDrawableCompat$AnimatedVectorDrawableCompatState0, Drawable.Callback drawable$Callback0, Resources resources0) {
            if(animatedVectorDrawableCompat$AnimatedVectorDrawableCompatState0 != null) {
                this.mChangingConfigurations = animatedVectorDrawableCompat$AnimatedVectorDrawableCompatState0.mChangingConfigurations;
                VectorDrawableCompat vectorDrawableCompat0 = animatedVectorDrawableCompat$AnimatedVectorDrawableCompatState0.mVectorDrawable;
                if(vectorDrawableCompat0 != null) {
                    Drawable.ConstantState drawable$ConstantState0 = vectorDrawableCompat0.getConstantState();
                    this.mVectorDrawable = resources0 == null ? ((VectorDrawableCompat)drawable$ConstantState0.newDrawable()) : ((VectorDrawableCompat)drawable$ConstantState0.newDrawable(resources0));
                    VectorDrawableCompat vectorDrawableCompat1 = (VectorDrawableCompat)this.mVectorDrawable.mutate();
                    this.mVectorDrawable = vectorDrawableCompat1;
                    vectorDrawableCompat1.setCallback(drawable$Callback0);
                    this.mVectorDrawable.setBounds(animatedVectorDrawableCompat$AnimatedVectorDrawableCompatState0.mVectorDrawable.getBounds());
                    this.mVectorDrawable.setAllowCaching(false);
                }
                ArrayList arrayList0 = animatedVectorDrawableCompat$AnimatedVectorDrawableCompatState0.mAnimators;
                if(arrayList0 != null) {
                    int v1 = arrayList0.size();
                    this.mAnimators = new ArrayList(v1);
                    this.mTargetNameMap = new ArrayMap(v1);
                    for(int v = 0; v < v1; ++v) {
                        Animator animator0 = (Animator)animatedVectorDrawableCompat$AnimatedVectorDrawableCompatState0.mAnimators.get(v);
                        Animator animator1 = animator0.clone();
                        String s = (String)animatedVectorDrawableCompat$AnimatedVectorDrawableCompatState0.mTargetNameMap.get(animator0);
                        animator1.setTarget(this.mVectorDrawable.getTargetByName(s));
                        this.mAnimators.add(animator1);
                        this.mTargetNameMap.put(animator1, s);
                    }
                    this.setupAnimatorSet();
                }
            }
        }

        @Override  // android.graphics.drawable.Drawable$ConstantState
        public int getChangingConfigurations() {
            return this.mChangingConfigurations;
        }

        @Override  // android.graphics.drawable.Drawable$ConstantState
        public Drawable newDrawable() {
            throw new IllegalStateException("No constant state support for SDK < 24.");
        }

        @Override  // android.graphics.drawable.Drawable$ConstantState
        public Drawable newDrawable(Resources resources0) {
            throw new IllegalStateException("No constant state support for SDK < 24.");
        }

        public void setupAnimatorSet() {
            if(this.mAnimatorSet == null) {
                this.mAnimatorSet = new AnimatorSet();
            }
            this.mAnimatorSet.playTogether(this.mAnimators);
        }
    }

    static class AnimatedVectorDrawableDelegateState extends Drawable.ConstantState {
        private final Drawable.ConstantState mDelegateState;

        public AnimatedVectorDrawableDelegateState(Drawable.ConstantState drawable$ConstantState0) {
            this.mDelegateState = drawable$ConstantState0;
        }

        @Override  // android.graphics.drawable.Drawable$ConstantState
        public boolean canApplyTheme() {
            return this.mDelegateState.canApplyTheme();
        }

        @Override  // android.graphics.drawable.Drawable$ConstantState
        public int getChangingConfigurations() {
            return this.mDelegateState.getChangingConfigurations();
        }

        @Override  // android.graphics.drawable.Drawable$ConstantState
        public Drawable newDrawable() {
            Drawable drawable0 = new AnimatedVectorDrawableCompat();
            drawable0.mDelegateDrawable = this.mDelegateState.newDrawable();
            drawable0.mDelegateDrawable.setCallback(drawable0.mCallback);
            return drawable0;
        }

        @Override  // android.graphics.drawable.Drawable$ConstantState
        public Drawable newDrawable(Resources resources0) {
            Drawable drawable0 = new AnimatedVectorDrawableCompat();
            drawable0.mDelegateDrawable = this.mDelegateState.newDrawable(resources0);
            drawable0.mDelegateDrawable.setCallback(drawable0.mCallback);
            return drawable0;
        }

        @Override  // android.graphics.drawable.Drawable$ConstantState
        public Drawable newDrawable(Resources resources0, Resources.Theme resources$Theme0) {
            Drawable drawable0 = new AnimatedVectorDrawableCompat();
            drawable0.mDelegateDrawable = this.mDelegateState.newDrawable(resources0, resources$Theme0);
            drawable0.mDelegateDrawable.setCallback(drawable0.mCallback);
            return drawable0;
        }
    }

    private static final String ANIMATED_VECTOR = "animated-vector";
    private static final boolean DBG_ANIMATION_VECTOR_DRAWABLE = false;
    private static final String LOGTAG = "AnimatedVDCompat";
    private static final String TARGET = "target";
    private AnimatedVectorDrawableCompatState mAnimatedVectorState;
    ArrayList mAnimationCallbacks;
    private Animator.AnimatorListener mAnimatorListener;
    private ArgbEvaluator mArgbEvaluator;
    AnimatedVectorDrawableDelegateState mCachedConstantStateDelegate;
    final Drawable.Callback mCallback;
    private Context mContext;

    AnimatedVectorDrawableCompat() {
        this(null, null, null);
    }

    private AnimatedVectorDrawableCompat(Context context0) {
        this(context0, null, null);
    }

    private AnimatedVectorDrawableCompat(Context context0, AnimatedVectorDrawableCompatState animatedVectorDrawableCompat$AnimatedVectorDrawableCompatState0, Resources resources0) {
        this.mArgbEvaluator = null;
        this.mAnimatorListener = null;
        this.mAnimationCallbacks = null;
        androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat.1 animatedVectorDrawableCompat$10 = new Drawable.Callback() {
            @Override  // android.graphics.drawable.Drawable$Callback
            public void invalidateDrawable(Drawable drawable0) {
                AnimatedVectorDrawableCompat.this.invalidateSelf();
            }

            @Override  // android.graphics.drawable.Drawable$Callback
            public void scheduleDrawable(Drawable drawable0, Runnable runnable0, long v) {
                AnimatedVectorDrawableCompat.this.scheduleSelf(runnable0, v);
            }

            @Override  // android.graphics.drawable.Drawable$Callback
            public void unscheduleDrawable(Drawable drawable0, Runnable runnable0) {
                AnimatedVectorDrawableCompat.this.unscheduleSelf(runnable0);
            }
        };
        this.mCallback = animatedVectorDrawableCompat$10;
        this.mContext = context0;
        if(animatedVectorDrawableCompat$AnimatedVectorDrawableCompatState0 != null) {
            this.mAnimatedVectorState = animatedVectorDrawableCompat$AnimatedVectorDrawableCompatState0;
            return;
        }
        this.mAnimatedVectorState = new AnimatedVectorDrawableCompatState(context0, null, animatedVectorDrawableCompat$10, resources0);
    }

    @Override  // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon
    public void applyTheme(Resources.Theme resources$Theme0) {
        if(this.mDelegateDrawable != null) {
            DrawableCompat.applyTheme(this.mDelegateDrawable, resources$Theme0);
        }
    }

    @Override  // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        return this.mDelegateDrawable == null ? false : DrawableCompat.canApplyTheme(this.mDelegateDrawable);
    }

    public static void clearAnimationCallbacks(Drawable drawable0) {
        if(!(drawable0 instanceof Animatable)) {
            return;
        }
        if(Build.VERSION.SDK_INT >= 24) {
            ((AnimatedVectorDrawable)drawable0).clearAnimationCallbacks();
            return;
        }
        ((AnimatedVectorDrawableCompat)drawable0).clearAnimationCallbacks();
    }

    @Override  // androidx.vectordrawable.graphics.drawable.Animatable2Compat
    public void clearAnimationCallbacks() {
        if(this.mDelegateDrawable != null) {
            ((AnimatedVectorDrawable)this.mDelegateDrawable).clearAnimationCallbacks();
            return;
        }
        this.removeAnimatorSetListener();
        ArrayList arrayList0 = this.mAnimationCallbacks;
        if(arrayList0 == null) {
            return;
        }
        arrayList0.clear();
    }

    @Override  // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon
    public void clearColorFilter() {
        super.clearColorFilter();
    }

    public static AnimatedVectorDrawableCompat create(Context context0, int v) {
        if(Build.VERSION.SDK_INT >= 24) {
            AnimatedVectorDrawableCompat animatedVectorDrawableCompat0 = new AnimatedVectorDrawableCompat(context0);
            animatedVectorDrawableCompat0.mDelegateDrawable = ResourcesCompat.getDrawable(context0.getResources(), v, context0.getTheme());
            animatedVectorDrawableCompat0.mDelegateDrawable.setCallback(animatedVectorDrawableCompat0.mCallback);
            animatedVectorDrawableCompat0.mCachedConstantStateDelegate = new AnimatedVectorDrawableDelegateState(animatedVectorDrawableCompat0.mDelegateDrawable.getConstantState());
            return animatedVectorDrawableCompat0;
        }
        Resources resources0 = context0.getResources();
        try {
            XmlResourceParser xmlResourceParser0 = resources0.getXml(v);
            AttributeSet attributeSet0 = Xml.asAttributeSet(xmlResourceParser0);
            do {
                int v1 = xmlResourceParser0.next();
            }
            while(v1 != 1 && v1 != 2);
            if(v1 != 2) {
                throw new XmlPullParserException("No start tag found");
            }
            return AnimatedVectorDrawableCompat.createFromXmlInner(context0, context0.getResources(), xmlResourceParser0, attributeSet0, context0.getTheme());
        }
        catch(XmlPullParserException xmlPullParserException0) {
            Log.e("AnimatedVDCompat", "parser error", xmlPullParserException0);
            return null;
        }
        catch(IOException iOException0) {
            Log.e("AnimatedVDCompat", "parser error", iOException0);
            return null;
        }
    }

    public static AnimatedVectorDrawableCompat createFromXmlInner(Context context0, Resources resources0, XmlPullParser xmlPullParser0, AttributeSet attributeSet0, Resources.Theme resources$Theme0) throws XmlPullParserException, IOException {
        AnimatedVectorDrawableCompat animatedVectorDrawableCompat0 = new AnimatedVectorDrawableCompat(context0);
        animatedVectorDrawableCompat0.inflate(resources0, xmlPullParser0, attributeSet0, resources$Theme0);
        return animatedVectorDrawableCompat0;
    }

    @Override  // android.graphics.drawable.Drawable
    public void draw(Canvas canvas0) {
        if(this.mDelegateDrawable != null) {
            this.mDelegateDrawable.draw(canvas0);
            return;
        }
        this.mAnimatedVectorState.mVectorDrawable.draw(canvas0);
        if(this.mAnimatedVectorState.mAnimatorSet.isStarted()) {
            this.invalidateSelf();
        }
    }

    @Override  // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.mDelegateDrawable == null ? this.mAnimatedVectorState.mVectorDrawable.getAlpha() : DrawableCompat.getAlpha(this.mDelegateDrawable);
    }

    @Override  // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        return this.mDelegateDrawable == null ? super.getChangingConfigurations() | this.mAnimatedVectorState.mChangingConfigurations : this.mDelegateDrawable.getChangingConfigurations();
    }

    @Override  // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        return this.mDelegateDrawable == null ? this.mAnimatedVectorState.mVectorDrawable.getColorFilter() : DrawableCompat.getColorFilter(this.mDelegateDrawable);
    }

    @Override  // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.mDelegateDrawable != null && Build.VERSION.SDK_INT >= 24 ? new AnimatedVectorDrawableDelegateState(this.mDelegateDrawable.getConstantState()) : null;
    }

    @Override  // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon
    public Drawable getCurrent() {
        return super.getCurrent();
    }

    @Override  // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.mDelegateDrawable == null ? this.mAnimatedVectorState.mVectorDrawable.getIntrinsicHeight() : this.mDelegateDrawable.getIntrinsicHeight();
    }

    @Override  // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.mDelegateDrawable == null ? this.mAnimatedVectorState.mVectorDrawable.getIntrinsicWidth() : this.mDelegateDrawable.getIntrinsicWidth();
    }

    @Override  // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon
    public int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    @Override  // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon
    public int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    @Override  // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.mDelegateDrawable == null ? this.mAnimatedVectorState.mVectorDrawable.getOpacity() : this.mDelegateDrawable.getOpacity();
    }

    @Override  // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon
    public boolean getPadding(Rect rect0) {
        return super.getPadding(rect0);
    }

    @Override  // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon
    public int[] getState() {
        return super.getState();
    }

    @Override  // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon
    public Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    @Override  // android.graphics.drawable.Drawable
    public void inflate(Resources resources0, XmlPullParser xmlPullParser0, AttributeSet attributeSet0) throws XmlPullParserException, IOException {
        this.inflate(resources0, xmlPullParser0, attributeSet0, null);
    }

    @Override  // android.graphics.drawable.Drawable
    public void inflate(Resources resources0, XmlPullParser xmlPullParser0, AttributeSet attributeSet0, Resources.Theme resources$Theme0) throws XmlPullParserException, IOException {
        if(this.mDelegateDrawable != null) {
            DrawableCompat.inflate(this.mDelegateDrawable, resources0, xmlPullParser0, attributeSet0, resources$Theme0);
            return;
        }
        int v = xmlPullParser0.getEventType();
        int v1 = xmlPullParser0.getDepth();
        while(v != 1 && (xmlPullParser0.getDepth() >= v1 + 1 || v != 3)) {
            if(v == 2) {
                String s = xmlPullParser0.getName();
                if("animated-vector".equals(s)) {
                    TypedArray typedArray0 = TypedArrayUtils.obtainAttributes(resources0, resources$Theme0, attributeSet0, AndroidResources.STYLEABLE_ANIMATED_VECTOR_DRAWABLE);
                    int v2 = typedArray0.getResourceId(0, 0);
                    if(v2 != 0) {
                        VectorDrawableCompat vectorDrawableCompat0 = VectorDrawableCompat.create(resources0, v2, resources$Theme0);
                        vectorDrawableCompat0.setAllowCaching(false);
                        vectorDrawableCompat0.setCallback(this.mCallback);
                        if(this.mAnimatedVectorState.mVectorDrawable != null) {
                            this.mAnimatedVectorState.mVectorDrawable.setCallback(null);
                        }
                        this.mAnimatedVectorState.mVectorDrawable = vectorDrawableCompat0;
                    }
                    typedArray0.recycle();
                }
                else if("target".equals(s)) {
                    TypedArray typedArray1 = resources0.obtainAttributes(attributeSet0, AndroidResources.STYLEABLE_ANIMATED_VECTOR_DRAWABLE_TARGET);
                    String s1 = typedArray1.getString(0);
                    int v3 = typedArray1.getResourceId(1, 0);
                    if(v3 != 0) {
                        Context context0 = this.mContext;
                        if(context0 != null) {
                            this.setupAnimatorsForTarget(s1, AnimatorInflaterCompat.loadAnimator(context0, v3));
                            goto label_31;
                        }
                        typedArray1.recycle();
                        throw new IllegalStateException("Context can\'t be null when inflating animators");
                    }
                label_31:
                    typedArray1.recycle();
                }
            }
            v = xmlPullParser0.next();
        }
        this.mAnimatedVectorState.setupAnimatorSet();
    }

    @Override  // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        return this.mDelegateDrawable == null ? this.mAnimatedVectorState.mVectorDrawable.isAutoMirrored() : DrawableCompat.isAutoMirrored(this.mDelegateDrawable);
    }

    @Override  // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.mDelegateDrawable == null ? this.mAnimatedVectorState.mAnimatorSet.isRunning() : ((AnimatedVectorDrawable)this.mDelegateDrawable).isRunning();
    }

    @Override  // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return this.mDelegateDrawable == null ? this.mAnimatedVectorState.mVectorDrawable.isStateful() : this.mDelegateDrawable.isStateful();
    }

    @Override  // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon
    public void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    @Override  // android.graphics.drawable.Drawable
    public Drawable mutate() {
        if(this.mDelegateDrawable != null) {
            this.mDelegateDrawable.mutate();
        }
        return this;
    }

    @Override  // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon
    protected void onBoundsChange(Rect rect0) {
        if(this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setBounds(rect0);
            return;
        }
        this.mAnimatedVectorState.mVectorDrawable.setBounds(rect0);
    }

    @Override  // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon
    protected boolean onLevelChange(int v) {
        return this.mDelegateDrawable == null ? this.mAnimatedVectorState.mVectorDrawable.setLevel(v) : this.mDelegateDrawable.setLevel(v);
    }

    @Override  // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] arr_v) {
        return this.mDelegateDrawable == null ? this.mAnimatedVectorState.mVectorDrawable.setState(arr_v) : this.mDelegateDrawable.setState(arr_v);
    }

    public static void registerAnimationCallback(Drawable drawable0, AnimationCallback animatable2Compat$AnimationCallback0) {
        if(drawable0 != null && animatable2Compat$AnimationCallback0 != null && drawable0 instanceof Animatable) {
            if(Build.VERSION.SDK_INT >= 24) {
                AnimatedVectorDrawableCompat.registerPlatformCallback(((AnimatedVectorDrawable)drawable0), animatable2Compat$AnimationCallback0);
                return;
            }
            ((AnimatedVectorDrawableCompat)drawable0).registerAnimationCallback(animatable2Compat$AnimationCallback0);
        }
    }

    @Override  // androidx.vectordrawable.graphics.drawable.Animatable2Compat
    public void registerAnimationCallback(AnimationCallback animatable2Compat$AnimationCallback0) {
        if(this.mDelegateDrawable != null) {
            AnimatedVectorDrawableCompat.registerPlatformCallback(((AnimatedVectorDrawable)this.mDelegateDrawable), animatable2Compat$AnimationCallback0);
            return;
        }
        if(animatable2Compat$AnimationCallback0 != null) {
            if(this.mAnimationCallbacks == null) {
                this.mAnimationCallbacks = new ArrayList();
            }
            if(!this.mAnimationCallbacks.contains(animatable2Compat$AnimationCallback0)) {
                this.mAnimationCallbacks.add(animatable2Compat$AnimationCallback0);
                if(this.mAnimatorListener == null) {
                    this.mAnimatorListener = new AnimatorListenerAdapter() {
                        @Override  // android.animation.AnimatorListenerAdapter
                        public void onAnimationEnd(Animator animator0) {
                            ArrayList arrayList0 = new ArrayList(AnimatedVectorDrawableCompat.this.mAnimationCallbacks);
                            int v = arrayList0.size();
                            for(int v1 = 0; v1 < v; ++v1) {
                                ((AnimationCallback)arrayList0.get(v1)).onAnimationEnd(AnimatedVectorDrawableCompat.this);
                            }
                        }

                        @Override  // android.animation.AnimatorListenerAdapter
                        public void onAnimationStart(Animator animator0) {
                            ArrayList arrayList0 = new ArrayList(AnimatedVectorDrawableCompat.this.mAnimationCallbacks);
                            int v = arrayList0.size();
                            for(int v1 = 0; v1 < v; ++v1) {
                                ((AnimationCallback)arrayList0.get(v1)).onAnimationStart(AnimatedVectorDrawableCompat.this);
                            }
                        }
                    };
                }
                this.mAnimatedVectorState.mAnimatorSet.addListener(this.mAnimatorListener);
            }
        }
    }

    private static void registerPlatformCallback(AnimatedVectorDrawable animatedVectorDrawable0, AnimationCallback animatable2Compat$AnimationCallback0) {
        animatedVectorDrawable0.registerAnimationCallback(animatable2Compat$AnimationCallback0.getPlatformCallback());
    }

    private void removeAnimatorSetListener() {
        if(this.mAnimatorListener != null) {
            this.mAnimatedVectorState.mAnimatorSet.removeListener(this.mAnimatorListener);
            this.mAnimatorListener = null;
        }
    }

    @Override  // android.graphics.drawable.Drawable
    public void setAlpha(int v) {
        if(this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setAlpha(v);
            return;
        }
        this.mAnimatedVectorState.mVectorDrawable.setAlpha(v);
    }

    @Override  // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        if(this.mDelegateDrawable != null) {
            DrawableCompat.setAutoMirrored(this.mDelegateDrawable, z);
            return;
        }
        this.mAnimatedVectorState.mVectorDrawable.setAutoMirrored(z);
    }

    @Override  // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon
    public void setChangingConfigurations(int v) {
        super.setChangingConfigurations(v);
    }

    @Override  // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon
    public void setColorFilter(int v, PorterDuff.Mode porterDuff$Mode0) {
        super.setColorFilter(v, porterDuff$Mode0);
    }

    @Override  // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter0) {
        if(this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setColorFilter(colorFilter0);
            return;
        }
        this.mAnimatedVectorState.mVectorDrawable.setColorFilter(colorFilter0);
    }

    @Override  // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon
    public void setFilterBitmap(boolean z) {
        super.setFilterBitmap(z);
    }

    @Override  // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon
    public void setHotspot(float f, float f1) {
        super.setHotspot(f, f1);
    }

    @Override  // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon
    public void setHotspotBounds(int v, int v1, int v2, int v3) {
        super.setHotspotBounds(v, v1, v2, v3);
    }

    @Override  // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon
    public boolean setState(int[] arr_v) {
        return super.setState(arr_v);
    }

    @Override  // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTint(int v) {
        if(this.mDelegateDrawable != null) {
            DrawableCompat.setTint(this.mDelegateDrawable, v);
            return;
        }
        this.mAnimatedVectorState.mVectorDrawable.setTint(v);
    }

    @Override  // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTintList(ColorStateList colorStateList0) {
        if(this.mDelegateDrawable != null) {
            DrawableCompat.setTintList(this.mDelegateDrawable, colorStateList0);
            return;
        }
        this.mAnimatedVectorState.mVectorDrawable.setTintList(colorStateList0);
    }

    @Override  // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTintMode(PorterDuff.Mode porterDuff$Mode0) {
        if(this.mDelegateDrawable != null) {
            DrawableCompat.setTintMode(this.mDelegateDrawable, porterDuff$Mode0);
            return;
        }
        this.mAnimatedVectorState.mVectorDrawable.setTintMode(porterDuff$Mode0);
    }

    @Override  // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z1) {
        if(this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.setVisible(z, z1);
        }
        this.mAnimatedVectorState.mVectorDrawable.setVisible(z, z1);
        return super.setVisible(z, z1);
    }

    private void setupAnimatorsForTarget(String s, Animator animator0) {
        animator0.setTarget(this.mAnimatedVectorState.mVectorDrawable.getTargetByName(s));
        if(this.mAnimatedVectorState.mAnimators == null) {
            AnimatedVectorDrawableCompatState animatedVectorDrawableCompat$AnimatedVectorDrawableCompatState0 = this.mAnimatedVectorState;
            animatedVectorDrawableCompat$AnimatedVectorDrawableCompatState0.mAnimators = new ArrayList();
            AnimatedVectorDrawableCompatState animatedVectorDrawableCompat$AnimatedVectorDrawableCompatState1 = this.mAnimatedVectorState;
            animatedVectorDrawableCompat$AnimatedVectorDrawableCompatState1.mTargetNameMap = new ArrayMap();
        }
        this.mAnimatedVectorState.mAnimators.add(animator0);
        this.mAnimatedVectorState.mTargetNameMap.put(animator0, s);
    }

    private void setupColorAnimator(Animator animator0) {
        if(animator0 instanceof AnimatorSet) {
            ArrayList arrayList0 = ((AnimatorSet)animator0).getChildAnimations();
            if(arrayList0 != null) {
                for(int v = 0; v < arrayList0.size(); ++v) {
                    this.setupColorAnimator(((Animator)arrayList0.get(v)));
                }
            }
        }
        if(animator0 instanceof ObjectAnimator) {
            String s = ((ObjectAnimator)animator0).getPropertyName();
            if("fillColor".equals(s) || "strokeColor".equals(s)) {
                if(this.mArgbEvaluator == null) {
                    this.mArgbEvaluator = new ArgbEvaluator();
                }
                ((ObjectAnimator)animator0).setEvaluator(this.mArgbEvaluator);
            }
        }
    }

    @Override  // android.graphics.drawable.Animatable
    public void start() {
        if(this.mDelegateDrawable != null) {
            ((AnimatedVectorDrawable)this.mDelegateDrawable).start();
            return;
        }
        if(this.mAnimatedVectorState.mAnimatorSet.isStarted()) {
            return;
        }
        this.mAnimatedVectorState.mAnimatorSet.start();
        this.invalidateSelf();
    }

    @Override  // android.graphics.drawable.Animatable
    public void stop() {
        if(this.mDelegateDrawable != null) {
            ((AnimatedVectorDrawable)this.mDelegateDrawable).stop();
            return;
        }
        this.mAnimatedVectorState.mAnimatorSet.end();
    }

    public static boolean unregisterAnimationCallback(Drawable drawable0, AnimationCallback animatable2Compat$AnimationCallback0) {
        if(drawable0 == null || animatable2Compat$AnimationCallback0 == null || !(drawable0 instanceof Animatable)) {
            return false;
        }
        return Build.VERSION.SDK_INT < 24 ? ((AnimatedVectorDrawableCompat)drawable0).unregisterAnimationCallback(animatable2Compat$AnimationCallback0) : AnimatedVectorDrawableCompat.unregisterPlatformCallback(((AnimatedVectorDrawable)drawable0), animatable2Compat$AnimationCallback0);
    }

    @Override  // androidx.vectordrawable.graphics.drawable.Animatable2Compat
    public boolean unregisterAnimationCallback(AnimationCallback animatable2Compat$AnimationCallback0) {
        if(this.mDelegateDrawable != null) {
            AnimatedVectorDrawableCompat.unregisterPlatformCallback(((AnimatedVectorDrawable)this.mDelegateDrawable), animatable2Compat$AnimationCallback0);
        }
        ArrayList arrayList0 = this.mAnimationCallbacks;
        if(arrayList0 != null && animatable2Compat$AnimationCallback0 != null) {
            boolean z = arrayList0.remove(animatable2Compat$AnimationCallback0);
            if(this.mAnimationCallbacks.size() == 0) {
                this.removeAnimatorSetListener();
            }
            return z;
        }
        return false;
    }

    private static boolean unregisterPlatformCallback(AnimatedVectorDrawable animatedVectorDrawable0, AnimationCallback animatable2Compat$AnimationCallback0) {
        return animatedVectorDrawable0.unregisterAnimationCallback(animatable2Compat$AnimationCallback0.getPlatformCallback());
    }
}

