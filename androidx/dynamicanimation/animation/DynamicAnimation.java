package androidx.dynamicanimation.animation;

import android.os.Looper;
import android.util.AndroidRuntimeException;
import android.view.View;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;

public abstract class DynamicAnimation implements AnimationFrameCallback {
    static class MassState {
        float mValue;
        float mVelocity;

    }

    public interface OnAnimationEndListener {
        void onAnimationEnd(DynamicAnimation arg1, boolean arg2, float arg3, float arg4);
    }

    public interface OnAnimationUpdateListener {
        void onAnimationUpdate(DynamicAnimation arg1, float arg2, float arg3);
    }

    public static abstract class ViewProperty extends FloatPropertyCompat {
        private ViewProperty(String s) {
            super(s);
        }

        ViewProperty(String s, androidx.dynamicanimation.animation.DynamicAnimation.1 dynamicAnimation$10) {
            this(s);
        }
    }

    public static final ViewProperty ALPHA = null;
    public static final float MIN_VISIBLE_CHANGE_ALPHA = 0.003906f;
    public static final float MIN_VISIBLE_CHANGE_PIXELS = 1.0f;
    public static final float MIN_VISIBLE_CHANGE_ROTATION_DEGREES = 0.1f;
    public static final float MIN_VISIBLE_CHANGE_SCALE = 0.002f;
    public static final ViewProperty ROTATION = null;
    public static final ViewProperty ROTATION_X = null;
    public static final ViewProperty ROTATION_Y = null;
    public static final ViewProperty SCALE_X = null;
    public static final ViewProperty SCALE_Y = null;
    public static final ViewProperty SCROLL_X = null;
    public static final ViewProperty SCROLL_Y = null;
    private static final float THRESHOLD_MULTIPLIER = 0.75f;
    public static final ViewProperty TRANSLATION_X = null;
    public static final ViewProperty TRANSLATION_Y = null;
    public static final ViewProperty TRANSLATION_Z = null;
    private static final float UNSET = 3.402823E+38f;
    public static final ViewProperty X;
    public static final ViewProperty Y;
    public static final ViewProperty Z;
    private final ArrayList mEndListeners;
    private long mLastFrameTime;
    float mMaxValue;
    float mMinValue;
    private float mMinVisibleChange;
    final FloatPropertyCompat mProperty;
    boolean mRunning;
    boolean mStartValueIsSet;
    final Object mTarget;
    private final ArrayList mUpdateListeners;
    float mValue;
    float mVelocity;

    static {
        DynamicAnimation.TRANSLATION_X = new ViewProperty("translationX") {
            {
                super(s, null);
            }

            public float getValue(View view0) {
                return view0.getTranslationX();
            }

            @Override  // androidx.dynamicanimation.animation.FloatPropertyCompat
            public float getValue(Object object0) {
                return this.getValue(((View)object0));
            }

            public void setValue(View view0, float f) {
                view0.setTranslationX(f);
            }

            @Override  // androidx.dynamicanimation.animation.FloatPropertyCompat
            public void setValue(Object object0, float f) {
                this.setValue(((View)object0), f);
            }
        };
        DynamicAnimation.TRANSLATION_Y = new ViewProperty("translationY") {
            {
                super(s, null);
            }

            public float getValue(View view0) {
                return view0.getTranslationY();
            }

            @Override  // androidx.dynamicanimation.animation.FloatPropertyCompat
            public float getValue(Object object0) {
                return this.getValue(((View)object0));
            }

            public void setValue(View view0, float f) {
                view0.setTranslationY(f);
            }

            @Override  // androidx.dynamicanimation.animation.FloatPropertyCompat
            public void setValue(Object object0, float f) {
                this.setValue(((View)object0), f);
            }
        };
        DynamicAnimation.TRANSLATION_Z = new ViewProperty("translationZ") {
            {
                super(s, null);
            }

            public float getValue(View view0) {
                return ViewCompat.getTranslationZ(view0);
            }

            @Override  // androidx.dynamicanimation.animation.FloatPropertyCompat
            public float getValue(Object object0) {
                return this.getValue(((View)object0));
            }

            public void setValue(View view0, float f) {
                ViewCompat.setTranslationZ(view0, f);
            }

            @Override  // androidx.dynamicanimation.animation.FloatPropertyCompat
            public void setValue(Object object0, float f) {
                this.setValue(((View)object0), f);
            }
        };
        DynamicAnimation.SCALE_X = new ViewProperty("scaleX") {
            {
                super(s, null);
            }

            public float getValue(View view0) {
                return view0.getScaleX();
            }

            @Override  // androidx.dynamicanimation.animation.FloatPropertyCompat
            public float getValue(Object object0) {
                return this.getValue(((View)object0));
            }

            public void setValue(View view0, float f) {
                view0.setScaleX(f);
            }

            @Override  // androidx.dynamicanimation.animation.FloatPropertyCompat
            public void setValue(Object object0, float f) {
                this.setValue(((View)object0), f);
            }
        };
        DynamicAnimation.SCALE_Y = new ViewProperty("scaleY") {
            {
                super(s, null);
            }

            public float getValue(View view0) {
                return view0.getScaleY();
            }

            @Override  // androidx.dynamicanimation.animation.FloatPropertyCompat
            public float getValue(Object object0) {
                return this.getValue(((View)object0));
            }

            public void setValue(View view0, float f) {
                view0.setScaleY(f);
            }

            @Override  // androidx.dynamicanimation.animation.FloatPropertyCompat
            public void setValue(Object object0, float f) {
                this.setValue(((View)object0), f);
            }
        };
        DynamicAnimation.ROTATION = new ViewProperty("rotation") {
            {
                super(s, null);
            }

            public float getValue(View view0) {
                return view0.getRotation();
            }

            @Override  // androidx.dynamicanimation.animation.FloatPropertyCompat
            public float getValue(Object object0) {
                return this.getValue(((View)object0));
            }

            public void setValue(View view0, float f) {
                view0.setRotation(f);
            }

            @Override  // androidx.dynamicanimation.animation.FloatPropertyCompat
            public void setValue(Object object0, float f) {
                this.setValue(((View)object0), f);
            }
        };
        DynamicAnimation.ROTATION_X = new ViewProperty("rotationX") {
            {
                super(s, null);
            }

            public float getValue(View view0) {
                return view0.getRotationX();
            }

            @Override  // androidx.dynamicanimation.animation.FloatPropertyCompat
            public float getValue(Object object0) {
                return this.getValue(((View)object0));
            }

            public void setValue(View view0, float f) {
                view0.setRotationX(f);
            }

            @Override  // androidx.dynamicanimation.animation.FloatPropertyCompat
            public void setValue(Object object0, float f) {
                this.setValue(((View)object0), f);
            }
        };
        DynamicAnimation.ROTATION_Y = new ViewProperty("rotationY") {
            {
                super(s, null);
            }

            public float getValue(View view0) {
                return view0.getRotationY();
            }

            @Override  // androidx.dynamicanimation.animation.FloatPropertyCompat
            public float getValue(Object object0) {
                return this.getValue(((View)object0));
            }

            public void setValue(View view0, float f) {
                view0.setRotationY(f);
            }

            @Override  // androidx.dynamicanimation.animation.FloatPropertyCompat
            public void setValue(Object object0, float f) {
                this.setValue(((View)object0), f);
            }
        };
        DynamicAnimation.X = new ViewProperty("x") {
            {
                super(s, null);
            }

            public float getValue(View view0) {
                return view0.getX();
            }

            @Override  // androidx.dynamicanimation.animation.FloatPropertyCompat
            public float getValue(Object object0) {
                return this.getValue(((View)object0));
            }

            public void setValue(View view0, float f) {
                view0.setX(f);
            }

            @Override  // androidx.dynamicanimation.animation.FloatPropertyCompat
            public void setValue(Object object0, float f) {
                this.setValue(((View)object0), f);
            }
        };
        DynamicAnimation.Y = new ViewProperty("y") {
            {
                super(s, null);
            }

            public float getValue(View view0) {
                return view0.getY();
            }

            @Override  // androidx.dynamicanimation.animation.FloatPropertyCompat
            public float getValue(Object object0) {
                return this.getValue(((View)object0));
            }

            public void setValue(View view0, float f) {
                view0.setY(f);
            }

            @Override  // androidx.dynamicanimation.animation.FloatPropertyCompat
            public void setValue(Object object0, float f) {
                this.setValue(((View)object0), f);
            }
        };
        DynamicAnimation.Z = new ViewProperty("z") {
            {
                super(s, null);
            }

            public float getValue(View view0) {
                return ViewCompat.getZ(view0);
            }

            @Override  // androidx.dynamicanimation.animation.FloatPropertyCompat
            public float getValue(Object object0) {
                return this.getValue(((View)object0));
            }

            public void setValue(View view0, float f) {
                ViewCompat.setZ(view0, f);
            }

            @Override  // androidx.dynamicanimation.animation.FloatPropertyCompat
            public void setValue(Object object0, float f) {
                this.setValue(((View)object0), f);
            }
        };
        DynamicAnimation.ALPHA = new ViewProperty("alpha") {
            {
                super(s, null);
            }

            public float getValue(View view0) {
                return view0.getAlpha();
            }

            @Override  // androidx.dynamicanimation.animation.FloatPropertyCompat
            public float getValue(Object object0) {
                return this.getValue(((View)object0));
            }

            public void setValue(View view0, float f) {
                view0.setAlpha(f);
            }

            @Override  // androidx.dynamicanimation.animation.FloatPropertyCompat
            public void setValue(Object object0, float f) {
                this.setValue(((View)object0), f);
            }
        };
        DynamicAnimation.SCROLL_X = new ViewProperty("scrollX") {
            {
                super(s, null);
            }

            public float getValue(View view0) {
                return (float)view0.getScrollX();
            }

            @Override  // androidx.dynamicanimation.animation.FloatPropertyCompat
            public float getValue(Object object0) {
                return this.getValue(((View)object0));
            }

            public void setValue(View view0, float f) {
                view0.setScrollX(((int)f));
            }

            @Override  // androidx.dynamicanimation.animation.FloatPropertyCompat
            public void setValue(Object object0, float f) {
                this.setValue(((View)object0), f);
            }
        };
        DynamicAnimation.SCROLL_Y = new ViewProperty("scrollY") {
            {
                super(s, null);
            }

            public float getValue(View view0) {
                return (float)view0.getScrollY();
            }

            @Override  // androidx.dynamicanimation.animation.FloatPropertyCompat
            public float getValue(Object object0) {
                return this.getValue(((View)object0));
            }

            public void setValue(View view0, float f) {
                view0.setScrollY(((int)f));
            }

            @Override  // androidx.dynamicanimation.animation.FloatPropertyCompat
            public void setValue(Object object0, float f) {
                this.setValue(((View)object0), f);
            }
        };
    }

    DynamicAnimation(FloatValueHolder floatValueHolder0) {
        this.mVelocity = 0.0f;
        this.mValue = 3.402823E+38f;
        this.mStartValueIsSet = false;
        this.mRunning = false;
        this.mMaxValue = 3.402823E+38f;
        this.mMinValue = -2139095040.0f;
        this.mLastFrameTime = 0L;
        this.mEndListeners = new ArrayList();
        this.mUpdateListeners = new ArrayList();
        this.mTarget = null;
        this.mProperty = new FloatPropertyCompat("FloatValueHolder") {
            @Override  // androidx.dynamicanimation.animation.FloatPropertyCompat
            public float getValue(Object object0) {
                return floatValueHolder0.getValue();
            }

            @Override  // androidx.dynamicanimation.animation.FloatPropertyCompat
            public void setValue(Object object0, float f) {
                floatValueHolder0.setValue(f);
            }
        };
        this.mMinVisibleChange = 1.0f;
    }

    DynamicAnimation(Object object0, FloatPropertyCompat floatPropertyCompat0) {
        this.mVelocity = 0.0f;
        this.mValue = 3.402823E+38f;
        this.mStartValueIsSet = false;
        this.mRunning = false;
        this.mMaxValue = 3.402823E+38f;
        this.mMinValue = -2139095040.0f;
        this.mLastFrameTime = 0L;
        this.mEndListeners = new ArrayList();
        this.mUpdateListeners = new ArrayList();
        this.mTarget = object0;
        this.mProperty = floatPropertyCompat0;
        if(floatPropertyCompat0 != DynamicAnimation.ROTATION && floatPropertyCompat0 != DynamicAnimation.ROTATION_X && floatPropertyCompat0 != DynamicAnimation.ROTATION_Y) {
            if(floatPropertyCompat0 == DynamicAnimation.ALPHA) {
                this.mMinVisibleChange = 0.003906f;
                return;
            }
            if(floatPropertyCompat0 != DynamicAnimation.SCALE_X && floatPropertyCompat0 != DynamicAnimation.SCALE_Y) {
                this.mMinVisibleChange = 1.0f;
                return;
            }
            this.mMinVisibleChange = 0.003906f;
            return;
        }
        this.mMinVisibleChange = 0.1f;
    }

    public DynamicAnimation addEndListener(OnAnimationEndListener dynamicAnimation$OnAnimationEndListener0) {
        if(!this.mEndListeners.contains(dynamicAnimation$OnAnimationEndListener0)) {
            this.mEndListeners.add(dynamicAnimation$OnAnimationEndListener0);
        }
        return this;
    }

    public DynamicAnimation addUpdateListener(OnAnimationUpdateListener dynamicAnimation$OnAnimationUpdateListener0) {
        if(this.isRunning()) {
            throw new UnsupportedOperationException("Error: Update listeners must be added beforethe animation.");
        }
        if(!this.mUpdateListeners.contains(dynamicAnimation$OnAnimationUpdateListener0)) {
            this.mUpdateListeners.add(dynamicAnimation$OnAnimationUpdateListener0);
        }
        return this;
    }

    public void cancel() {
        if(Looper.myLooper() != Looper.getMainLooper()) {
            throw new AndroidRuntimeException("Animations may only be canceled on the main thread");
        }
        if(this.mRunning) {
            this.endAnimationInternal(true);
        }
    }

    @Override  // androidx.dynamicanimation.animation.AnimationHandler$AnimationFrameCallback
    public boolean doAnimationFrame(long v) {
        long v1 = this.mLastFrameTime;
        if(v1 == 0L) {
            this.mLastFrameTime = v;
            this.setPropertyValue(this.mValue);
            return false;
        }
        this.mLastFrameTime = v;
        boolean z = this.updateValueAndVelocity(v - v1);
        float f = Math.max(Math.min(this.mValue, this.mMaxValue), this.mMinValue);
        this.mValue = f;
        this.setPropertyValue(f);
        if(z) {
            this.endAnimationInternal(false);
        }
        return z;
    }

    private void endAnimationInternal(boolean z) {
        this.mRunning = false;
        AnimationHandler.getInstance().removeCallback(this);
        this.mLastFrameTime = 0L;
        this.mStartValueIsSet = false;
        for(int v = 0; v < this.mEndListeners.size(); ++v) {
            if(this.mEndListeners.get(v) != null) {
                ((OnAnimationEndListener)this.mEndListeners.get(v)).onAnimationEnd(this, z, this.mValue, this.mVelocity);
            }
        }
        DynamicAnimation.removeNullEntries(this.mEndListeners);
    }

    abstract float getAcceleration(float arg1, float arg2);

    public float getMinimumVisibleChange() {
        return this.mMinVisibleChange;
    }

    private float getPropertyValue() {
        return this.mProperty.getValue(this.mTarget);
    }

    float getValueThreshold() {
        return this.mMinVisibleChange * 0.75f;
    }

    abstract boolean isAtEquilibrium(float arg1, float arg2);

    public boolean isRunning() {
        return this.mRunning;
    }

    public void removeEndListener(OnAnimationEndListener dynamicAnimation$OnAnimationEndListener0) {
        DynamicAnimation.removeEntry(this.mEndListeners, dynamicAnimation$OnAnimationEndListener0);
    }

    private static void removeEntry(ArrayList arrayList0, Object object0) {
        int v = arrayList0.indexOf(object0);
        if(v >= 0) {
            arrayList0.set(v, null);
        }
    }

    private static void removeNullEntries(ArrayList arrayList0) {
        for(int v = arrayList0.size() - 1; v >= 0; --v) {
            if(arrayList0.get(v) == null) {
                arrayList0.remove(v);
            }
        }
    }

    public void removeUpdateListener(OnAnimationUpdateListener dynamicAnimation$OnAnimationUpdateListener0) {
        DynamicAnimation.removeEntry(this.mUpdateListeners, dynamicAnimation$OnAnimationUpdateListener0);
    }

    public DynamicAnimation setMaxValue(float f) {
        this.mMaxValue = f;
        return this;
    }

    public DynamicAnimation setMinValue(float f) {
        this.mMinValue = f;
        return this;
    }

    public DynamicAnimation setMinimumVisibleChange(float f) {
        if(f <= 0.0f) {
            throw new IllegalArgumentException("Minimum visible change must be positive.");
        }
        this.mMinVisibleChange = f;
        this.setValueThreshold(f * 0.75f);
        return this;
    }

    void setPropertyValue(float f) {
        this.mProperty.setValue(this.mTarget, f);
        for(int v = 0; v < this.mUpdateListeners.size(); ++v) {
            if(this.mUpdateListeners.get(v) != null) {
                ((OnAnimationUpdateListener)this.mUpdateListeners.get(v)).onAnimationUpdate(this, this.mValue, this.mVelocity);
            }
        }
        DynamicAnimation.removeNullEntries(this.mUpdateListeners);
    }

    public DynamicAnimation setStartValue(float f) {
        this.mValue = f;
        this.mStartValueIsSet = true;
        return this;
    }

    public DynamicAnimation setStartVelocity(float f) {
        this.mVelocity = f;
        return this;
    }

    abstract void setValueThreshold(float arg1);

    public void start() {
        if(Looper.myLooper() != Looper.getMainLooper()) {
            throw new AndroidRuntimeException("Animations may only be started on the main thread");
        }
        if(!this.mRunning) {
            this.startAnimationInternal();
        }
    }

    private void startAnimationInternal() {
        if(!this.mRunning) {
            this.mRunning = true;
            if(!this.mStartValueIsSet) {
                this.mValue = this.getPropertyValue();
            }
            if(this.mValue > this.mMaxValue || this.mValue < this.mMinValue) {
                throw new IllegalArgumentException("Starting value need to be in between min value and max value");
            }
            AnimationHandler.getInstance().addAnimationFrameCallback(this, 0L);
        }
    }

    abstract boolean updateValueAndVelocity(long arg1);
}

