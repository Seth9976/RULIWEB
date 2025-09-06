package androidx.constraintlayout.core.state;

import androidx.constraintlayout.core.motion.CustomVariable;
import androidx.constraintlayout.core.motion.Motion;
import androidx.constraintlayout.core.motion.MotionWidget;
import androidx.constraintlayout.core.motion.key.MotionKeyAttributes;
import androidx.constraintlayout.core.motion.key.MotionKeyCycle;
import androidx.constraintlayout.core.motion.key.MotionKeyPosition;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.core.motion.utils.KeyCache;
import androidx.constraintlayout.core.motion.utils.SpringStopEngine;
import androidx.constraintlayout.core.motion.utils.StopEngine;
import androidx.constraintlayout.core.motion.utils.StopLogicEngine.Decelerate;
import androidx.constraintlayout.core.motion.utils.StopLogicEngine;
import androidx.constraintlayout.core.motion.utils.TypedBundle;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Transition implements TypedValues {
    static class KeyPosition {
        int mFrame;
        String mTarget;
        int mType;
        float mX;
        float mY;

        KeyPosition(String s, int v, int v1, float f, float f1) {
            this.mTarget = s;
            this.mFrame = v;
            this.mType = v1;
            this.mX = f;
            this.mY = f1;
        }
    }

    static class OnSwipe {
        public static final int ANCHOR_SIDE_BOTTOM = 3;
        public static final int ANCHOR_SIDE_END = 6;
        public static final int ANCHOR_SIDE_LEFT = 1;
        public static final int ANCHOR_SIDE_MIDDLE = 4;
        public static final int ANCHOR_SIDE_RIGHT = 2;
        public static final int ANCHOR_SIDE_START = 5;
        public static final int ANCHOR_SIDE_TOP = 0;
        public static final String[] BOUNDARY = null;
        public static final int BOUNDARY_BOUNCE_BOTH = 3;
        public static final int BOUNDARY_BOUNCE_END = 2;
        public static final int BOUNDARY_BOUNCE_START = 1;
        public static final int BOUNDARY_OVERSHOOT = 0;
        public static final String[] DIRECTIONS = null;
        public static final int DRAG_ANTICLOCKWISE = 7;
        public static final int DRAG_CLOCKWISE = 6;
        public static final int DRAG_DOWN = 1;
        public static final int DRAG_END = 5;
        public static final int DRAG_LEFT = 2;
        public static final int DRAG_RIGHT = 3;
        public static final int DRAG_START = 4;
        public static final int DRAG_UP = 0;
        public static final String[] MODE = null;
        public static final int MODE_CONTINUOUS_VELOCITY = 0;
        public static final int MODE_SPRING = 1;
        public static final int ON_UP_AUTOCOMPLETE = 0;
        public static final int ON_UP_AUTOCOMPLETE_TO_END = 2;
        public static final int ON_UP_AUTOCOMPLETE_TO_START = 1;
        public static final int ON_UP_DECELERATE = 4;
        public static final int ON_UP_DECELERATE_AND_COMPLETE = 5;
        public static final int ON_UP_NEVER_COMPLETE_TO_END = 7;
        public static final int ON_UP_NEVER_COMPLETE_TO_START = 6;
        public static final int ON_UP_STOP = 3;
        public static final String[] SIDES;
        private static final float[][] TOUCH_DIRECTION;
        private static final float[][] TOUCH_SIDES;
        public static final String[] TOUCH_UP;
        String mAnchorId;
        private int mAnchorSide;
        private int mAutoCompleteMode;
        private float mDestination;
        private int mDragDirection;
        private float mDragScale;
        private float mDragThreshold;
        private boolean mDragVertical;
        private StopEngine mEngine;
        String mLimitBoundsTo;
        private float mMaxAcceleration;
        private float mMaxVelocity;
        private int mOnTouchUp;
        private String mRotationCenterId;
        private int mSpringBoundary;
        private float mSpringDamping;
        private float mSpringMass;
        private float mSpringStiffness;
        private float mSpringStopThreshold;
        private long mStart;

        static {
            OnSwipe.SIDES = new String[]{"top", "left", "right", "bottom", "middle", "start", "end"};
            OnSwipe.TOUCH_SIDES = new float[][]{new float[]{0.5f, 0.0f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}, new float[]{0.5f, 1.0f}, new float[]{0.5f, 0.5f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}};
            OnSwipe.DIRECTIONS = new String[]{"up", "down", "left", "right", "start", "end", "clockwise", "anticlockwise"};
            OnSwipe.MODE = new String[]{"velocity", "spring"};
            OnSwipe.TOUCH_UP = new String[]{"autocomplete", "toStart", "toEnd", "stop", "decelerate", "decelerateComplete", "neverCompleteStart", "neverCompleteEnd"};
            OnSwipe.BOUNDARY = new String[]{"overshoot", "bounceStart", "bounceEnd", "bounceBoth"};
            OnSwipe.TOUCH_DIRECTION = new float[][]{new float[]{0.0f, -1.0f}, new float[]{0.0f, 1.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}};
        }

        OnSwipe() {
            this.mDragVertical = true;
            this.mDragDirection = 0;
            this.mDragScale = 1.0f;
            this.mDragThreshold = 10.0f;
            this.mAutoCompleteMode = 0;
            this.mMaxVelocity = 4.0f;
            this.mMaxAcceleration = 1.2f;
            this.mOnTouchUp = 0;
            this.mSpringMass = 1.0f;
            this.mSpringStiffness = 400.0f;
            this.mSpringDamping = 10.0f;
            this.mSpringStopThreshold = 0.01f;
            this.mDestination = 0.0f;
            this.mSpringBoundary = 0;
        }

        void config(float f, float f1, long v, float f2) {
            SpringStopEngine springStopEngine0;
            StopLogicEngine stopLogicEngine0;
            Decelerate stopLogicEngine$Decelerate0;
            this.mStart = v;
            float f3 = this.mMaxVelocity;
            if(Math.abs(f1) > f3) {
                f1 = Math.signum(f1) * f3;
            }
            float f4 = this.getDestinationPosition(f, f1, f2);
            this.mDestination = f4;
            if(f4 == f) {
                this.mEngine = null;
                return;
            }
            if(this.mOnTouchUp == 4 && this.mAutoCompleteMode == 0) {
                StopEngine stopEngine0 = this.mEngine;
                if(stopEngine0 instanceof Decelerate) {
                    stopLogicEngine$Decelerate0 = (Decelerate)stopEngine0;
                }
                else {
                    stopLogicEngine$Decelerate0 = new Decelerate();
                    this.mEngine = stopLogicEngine$Decelerate0;
                }
                stopLogicEngine$Decelerate0.config(f, this.mDestination, f1);
                return;
            }
            if(this.mAutoCompleteMode == 0) {
                StopEngine stopEngine1 = this.mEngine;
                if(stopEngine1 instanceof StopLogicEngine) {
                    stopLogicEngine0 = (StopLogicEngine)stopEngine1;
                }
                else {
                    stopLogicEngine0 = new StopLogicEngine();
                    this.mEngine = stopLogicEngine0;
                }
                stopLogicEngine0.config(f, this.mDestination, f1, f2, this.mMaxAcceleration, this.mMaxVelocity);
                return;
            }
            StopEngine stopEngine2 = this.mEngine;
            if(stopEngine2 instanceof SpringStopEngine) {
                springStopEngine0 = (SpringStopEngine)stopEngine2;
            }
            else {
                springStopEngine0 = new SpringStopEngine();
                this.mEngine = springStopEngine0;
            }
            springStopEngine0.springConfig(f, this.mDestination, f1, this.mSpringMass, this.mSpringStiffness, this.mSpringDamping, this.mSpringStopThreshold, this.mSpringBoundary);
        }

        float getDestinationPosition(float f, float f1, float f2) {
            float f3 = Math.abs(f1) * 0.5f * f1 / this.mMaxAcceleration + f;
            switch(this.mOnTouchUp) {
                case 1: {
                    return f >= 1.0f ? 1.0f : 0.0f;
                }
                case 2: {
                    return f <= 0.0f ? 0.0f : 1.0f;
                }
                case 3: {
                    return NaNf;
                }
                case 4: {
                    return Math.max(0.0f, Math.min(1.0f, f3));
                }
                case 5: {
                    if(f3 > 0.2f && f3 < 0.8f) {
                        return f3;
                    }
                    return f3 > 0.5f ? 1.0f : 0.0f;
                }
                case 6: {
                    return 1.0f;
                }
                case 7: {
                    return 0.0f;
                }
                default: {
                    return ((double)f3) > 0.5 ? 1.0f : 0.0f;
                }
            }
        }

        float[] getDirection() {
            return OnSwipe.TOUCH_DIRECTION[this.mDragDirection];
        }

        float getScale() {
            return this.mDragScale;
        }

        float[] getSide() {
            return OnSwipe.TOUCH_SIDES[this.mAnchorSide];
        }

        public float getTouchUpProgress(long v) {
            float f = this.mEngine.getInterpolation(((float)(v - this.mStart)) * 1.000000E-09f);
            return this.mEngine.isStopped() ? this.mDestination : f;
        }

        // 去混淆评级： 低(20)
        public boolean isNotDone(float f) {
            return this.mOnTouchUp == 3 ? false : this.mEngine != null && !this.mEngine.isStopped();
        }

        public void printInfo() {
            if(this.mAutoCompleteMode == 0) {
                System.out.println("velocity = " + this.mEngine.getVelocity());
                System.out.println("mMaxAcceleration = " + this.mMaxAcceleration);
                System.out.println("mMaxVelocity = " + this.mMaxVelocity);
                return;
            }
            System.out.println("mSpringMass          = " + this.mSpringMass);
            System.out.println("mSpringStiffness     = " + this.mSpringStiffness);
            System.out.println("mSpringDamping       = " + this.mSpringDamping);
            System.out.println("mSpringStopThreshold = " + this.mSpringStopThreshold);
            System.out.println("mSpringBoundary      = " + this.mSpringBoundary);
        }

        void setAnchorId(String s) {
            this.mAnchorId = s;
        }

        void setAnchorSide(int v) {
            this.mAnchorSide = v;
        }

        void setAutoCompleteMode(int v) {
            this.mAutoCompleteMode = v;
        }

        void setDragDirection(int v) {
            this.mDragDirection = v;
            this.mDragVertical = v < 2;
        }

        void setDragScale(float f) {
            if(Float.isNaN(f)) {
                return;
            }
            this.mDragScale = f;
        }

        void setDragThreshold(float f) {
            if(Float.isNaN(f)) {
                return;
            }
            this.mDragThreshold = f;
        }

        void setLimitBoundsTo(String s) {
            this.mLimitBoundsTo = s;
        }

        void setMaxAcceleration(float f) {
            if(Float.isNaN(f)) {
                return;
            }
            this.mMaxAcceleration = f;
        }

        void setMaxVelocity(float f) {
            if(Float.isNaN(f)) {
                return;
            }
            this.mMaxVelocity = f;
        }

        void setOnTouchUp(int v) {
            this.mOnTouchUp = v;
        }

        void setRotationCenterId(String s) {
            this.mRotationCenterId = s;
        }

        void setSpringBoundary(int v) {
            this.mSpringBoundary = v;
        }

        void setSpringDamping(float f) {
            if(Float.isNaN(f)) {
                return;
            }
            this.mSpringDamping = f;
        }

        void setSpringMass(float f) {
            if(Float.isNaN(f)) {
                return;
            }
            this.mSpringMass = f;
        }

        void setSpringStiffness(float f) {
            if(Float.isNaN(f)) {
                return;
            }
            this.mSpringStiffness = f;
        }

        void setSpringStopThreshold(float f) {
            if(Float.isNaN(f)) {
                return;
            }
            this.mSpringStopThreshold = f;
        }
    }

    public static class WidgetState {
        WidgetFrame mEnd;
        WidgetFrame mInterpolated;
        KeyCache mKeyCache;
        Motion mMotionControl;
        MotionWidget mMotionWidgetEnd;
        MotionWidget mMotionWidgetInterpolated;
        MotionWidget mMotionWidgetStart;
        boolean mNeedSetup;
        int mParentHeight;
        int mParentWidth;
        WidgetFrame mStart;

        public WidgetState() {
            this.mNeedSetup = true;
            this.mKeyCache = new KeyCache();
            this.mParentHeight = -1;
            this.mParentWidth = -1;
            this.mStart = new WidgetFrame();
            this.mEnd = new WidgetFrame();
            this.mInterpolated = new WidgetFrame();
            this.mMotionWidgetStart = new MotionWidget(this.mStart);
            this.mMotionWidgetEnd = new MotionWidget(this.mEnd);
            this.mMotionWidgetInterpolated = new MotionWidget(this.mInterpolated);
            Motion motion0 = new Motion(this.mMotionWidgetStart);
            this.mMotionControl = motion0;
            motion0.setStart(this.mMotionWidgetStart);
            this.mMotionControl.setEnd(this.mMotionWidgetEnd);
        }

        public WidgetFrame getFrame(int v) {
            if(v == 0) {
                return this.mStart;
            }
            return v == 1 ? this.mEnd : this.mInterpolated;
        }

        String getPathRelativeId() {
            return this.mMotionControl.getAnimateRelativeTo();
        }

        public void interpolate(int v, int v1, float f, Transition transition0) {
            this.mParentHeight = v1;
            this.mParentWidth = v;
            if(this.mNeedSetup) {
                this.mMotionControl.setup(v, v1, 1.0f, System.nanoTime());
                this.mNeedSetup = false;
            }
            WidgetFrame.interpolate(v, v1, this.mInterpolated, this.mStart, this.mEnd, transition0, f);
            this.mInterpolated.interpolatedPos = f;
            this.mMotionControl.interpolate(this.mMotionWidgetInterpolated, f, System.nanoTime(), this.mKeyCache);
        }

        public void setKeyAttribute(TypedBundle typedBundle0) {
            MotionKeyAttributes motionKeyAttributes0 = new MotionKeyAttributes();
            typedBundle0.applyDelta(motionKeyAttributes0);
            this.mMotionControl.addKey(motionKeyAttributes0);
        }

        public void setKeyAttribute(TypedBundle typedBundle0, CustomVariable[] arr_customVariable) {
            MotionKeyAttributes motionKeyAttributes0 = new MotionKeyAttributes();
            typedBundle0.applyDelta(motionKeyAttributes0);
            if(arr_customVariable != null) {
                for(int v = 0; v < arr_customVariable.length; ++v) {
                    motionKeyAttributes0.mCustom.put(arr_customVariable[v].getName(), arr_customVariable[v]);
                }
            }
            this.mMotionControl.addKey(motionKeyAttributes0);
        }

        public void setKeyCycle(TypedBundle typedBundle0) {
            MotionKeyCycle motionKeyCycle0 = new MotionKeyCycle();
            typedBundle0.applyDelta(motionKeyCycle0);
            this.mMotionControl.addKey(motionKeyCycle0);
        }

        public void setKeyPosition(TypedBundle typedBundle0) {
            MotionKeyPosition motionKeyPosition0 = new MotionKeyPosition();
            typedBundle0.applyDelta(motionKeyPosition0);
            this.mMotionControl.addKey(motionKeyPosition0);
        }

        public void setPathRelative(WidgetState transition$WidgetState0) {
            this.mMotionControl.setupRelative(transition$WidgetState0.mMotionControl);
        }

        public void update(ConstraintWidget constraintWidget0, int v) {
            switch(v) {
                case 0: {
                    this.mStart.update(constraintWidget0);
                    this.mMotionWidgetStart.updateMotion(this.mMotionWidgetStart);
                    this.mMotionControl.setStart(this.mMotionWidgetStart);
                    this.mNeedSetup = true;
                    break;
                }
                case 1: {
                    this.mEnd.update(constraintWidget0);
                    this.mMotionControl.setEnd(this.mMotionWidgetEnd);
                    this.mNeedSetup = true;
                }
            }
            this.mParentWidth = -1;
        }
    }

    static final int ANTICIPATE = 6;
    static final int BOUNCE = 4;
    private static final boolean DEBUG = false;
    static final int EASE_IN = 1;
    static final int EASE_IN_OUT = 0;
    static final int EASE_OUT = 2;
    public static final int END = 1;
    public static final int INTERPOLATED = 2;
    private static final int INTERPOLATOR_REFERENCE_ID = -2;
    static final int LINEAR = 3;
    static final int OVERSHOOT = 5;
    private static final int SPLINE_STRING = -1;
    public static final int START;
    private int mAutoTransition;
    private TypedBundle mBundle;
    private int mDefaultInterpolator;
    private String mDefaultInterpolatorString;
    private int mDuration;
    private Easing mEasing;
    private HashMap mKeyPositions;
    private OnSwipe mOnSwipe;
    int mParentEndHeight;
    int mParentEndWidth;
    int mParentInterpolateHeight;
    int mParentInterpolatedWidth;
    int mParentStartHeight;
    int mParentStartWidth;
    private float mStagger;
    private HashMap mState;
    final CorePixelDp mToPixel;
    boolean mWrap;

    public Transition(CorePixelDp corePixelDp0) {
        this.mKeyPositions = new HashMap();
        this.mState = new HashMap();
        this.mBundle = new TypedBundle();
        this.mDefaultInterpolator = 0;
        this.mDefaultInterpolatorString = null;
        this.mEasing = null;
        this.mAutoTransition = 0;
        this.mDuration = 400;
        this.mStagger = 0.0f;
        this.mOnSwipe = null;
        this.mToPixel = corePixelDp0;
    }

    public void addCustomColor(int v, String s, String s1, int v1) {
        this.getWidgetState(s, null, v).getFrame(v).addCustomColor(s1, v1);
    }

    public void addCustomFloat(int v, String s, String s1, float f) {
        this.getWidgetState(s, null, v).getFrame(v).addCustomFloat(s1, f);
    }

    public void addKeyAttribute(String s, TypedBundle typedBundle0) {
        this.getWidgetState(s, null, 0).setKeyAttribute(typedBundle0);
    }

    public void addKeyAttribute(String s, TypedBundle typedBundle0, CustomVariable[] arr_customVariable) {
        this.getWidgetState(s, null, 0).setKeyAttribute(typedBundle0, arr_customVariable);
    }

    public void addKeyCycle(String s, TypedBundle typedBundle0) {
        this.getWidgetState(s, null, 0).setKeyCycle(typedBundle0);
    }

    public void addKeyPosition(String s, int v, int v1, float f, float f1) {
        TypedBundle typedBundle0 = new TypedBundle();
        typedBundle0.add(510, 2);
        typedBundle0.add(100, v);
        typedBundle0.add(506, f);
        typedBundle0.add(507, f1);
        this.getWidgetState(s, null, 0).setKeyPosition(typedBundle0);
        KeyPosition transition$KeyPosition0 = new KeyPosition(s, v, v1, f, f1);
        HashMap hashMap0 = (HashMap)this.mKeyPositions.get(v);
        if(hashMap0 == null) {
            hashMap0 = new HashMap();
            this.mKeyPositions.put(v, hashMap0);
        }
        hashMap0.put(s, transition$KeyPosition0);
    }

    public void addKeyPosition(String s, TypedBundle typedBundle0) {
        this.getWidgetState(s, null, 0).setKeyPosition(typedBundle0);
    }

    public void calcStagger() {
        float f3;
        float f2;
        float f = this.mStagger;
        if(f != 0.0f) {
            float f1 = Math.abs(f);
            Iterator iterator0 = this.mState.keySet().iterator();
            while(true) {
                f2 = 3.402823E+38f;
                f3 = -3.402823E+38f;
                if(!iterator0.hasNext()) {
                    break;
                }
                Object object0 = iterator0.next();
                if(Float.isNaN(((WidgetState)this.mState.get(((String)object0))).mMotionControl.getMotionStagger())) {
                    continue;
                }
                for(Object object1: this.mState.keySet()) {
                    float f4 = ((WidgetState)this.mState.get(((String)object1))).mMotionControl.getMotionStagger();
                    if(!Float.isNaN(f4)) {
                        f2 = Math.min(f2, f4);
                        f3 = Math.max(f3, f4);
                    }
                }
                for(Object object2: this.mState.keySet()) {
                    Motion motion0 = ((WidgetState)this.mState.get(((String)object2))).mMotionControl;
                    float f5 = motion0.getMotionStagger();
                    if(!Float.isNaN(f5)) {
                        float f6 = f3 - f2;
                        float f7 = f1 - (f5 - f2) * f1 / f6;
                        if(((double)f) < 0.0) {
                            f7 = f1 - (f3 - f5) / f6 * f1;
                        }
                        motion0.setStaggerScale(1.0f / (1.0f - f1));
                        motion0.setStaggerOffset(f7);
                    }
                }
                return;
            }
            for(Object object3: this.mState.keySet()) {
                Motion motion1 = ((WidgetState)this.mState.get(((String)object3))).mMotionControl;
                float f8 = motion1.getFinalX() + motion1.getFinalY();
                f2 = Math.min(f2, f8);
                f3 = Math.max(f3, f8);
            }
            for(Object object4: this.mState.keySet()) {
                Motion motion2 = ((WidgetState)this.mState.get(((String)object4))).mMotionControl;
                float f9 = motion2.getFinalX() + motion2.getFinalY();
                float f10 = f3 - f2;
                float f11 = f1 - (f9 - f2) * f1 / f10;
                if(((double)f) < 0.0) {
                    f11 = f1 - (f3 - f9) / f10 * f1;
                }
                motion2.setStaggerScale(1.0f / (1.0f - f1));
                motion2.setStaggerOffset(f11);
            }
        }
    }

    private void calculateParentDimensions(float f) {
        this.mParentInterpolatedWidth = (int)(((float)this.mParentStartWidth) + 0.5f + ((float)(this.mParentEndWidth - this.mParentStartWidth)) * f);
        this.mParentInterpolateHeight = (int)(((float)this.mParentStartHeight) + 0.5f + ((float)(this.mParentEndHeight - this.mParentStartHeight)) * f);
    }

    public void clear() {
        this.mState.clear();
    }

    public boolean contains(String s) {
        return this.mState.containsKey(s);
    }

    OnSwipe createOnSwipe() {
        OnSwipe transition$OnSwipe0 = new OnSwipe();
        this.mOnSwipe = transition$OnSwipe0;
        return transition$OnSwipe0;
    }

    public float dragToProgress(float f, int v, int v1, float f1, float f2) {
        WidgetState transition$WidgetState0;
        Iterator iterator0 = this.mState.values().iterator();
        if(iterator0.hasNext()) {
            Object object0 = iterator0.next();
            transition$WidgetState0 = (WidgetState)object0;
        }
        else {
            transition$WidgetState0 = null;
        }
        OnSwipe transition$OnSwipe0 = this.mOnSwipe;
        if(transition$OnSwipe0 != null && transition$WidgetState0 != null) {
            if(transition$OnSwipe0.mAnchorId == null) {
                float[] arr_f = this.mOnSwipe.getDirection();
                float f3 = (float)transition$WidgetState0.mParentHeight;
                float f4 = (float)transition$WidgetState0.mParentHeight;
                float f5 = arr_f[0];
                return f5 == 0.0f ? f2 * Math.abs(arr_f[1]) / f4 * this.mOnSwipe.getScale() : f1 * Math.abs(f5) / f3 * this.mOnSwipe.getScale();
            }
            WidgetState transition$WidgetState1 = (WidgetState)this.mState.get(this.mOnSwipe.mAnchorId);
            float[] arr_f1 = this.mOnSwipe.getDirection();
            float[] arr_f2 = this.mOnSwipe.getSide();
            float[] arr_f3 = new float[2];
            transition$WidgetState1.interpolate(v, v1, f, this);
            transition$WidgetState1.mMotionControl.getDpDt(f, arr_f2[0], arr_f2[1], arr_f3);
            float f6 = arr_f1[0];
            return f6 == 0.0f ? f2 * Math.abs(arr_f1[1]) / arr_f3[1] * this.mOnSwipe.getScale() : f1 * Math.abs(f6) / arr_f3[0] * this.mOnSwipe.getScale();
        }
        return transition$WidgetState0 == null ? 1.0f : -f2 / ((float)transition$WidgetState0.mParentHeight);
    }

    public void fillKeyPositions(WidgetFrame widgetFrame0, float[] arr_f, float[] arr_f1, float[] arr_f2) {
        int v = 0;
        for(int v1 = 0; v1 <= 100; ++v1) {
            HashMap hashMap0 = (HashMap)this.mKeyPositions.get(v1);
            if(hashMap0 != null) {
                KeyPosition transition$KeyPosition0 = (KeyPosition)hashMap0.get(widgetFrame0.widget.stringId);
                if(transition$KeyPosition0 != null) {
                    arr_f[v] = transition$KeyPosition0.mX;
                    arr_f1[v] = transition$KeyPosition0.mY;
                    arr_f2[v] = (float)transition$KeyPosition0.mFrame;
                    ++v;
                }
            }
        }
    }

    public KeyPosition findNextPosition(String s, int v) {
        while(v <= 100) {
            HashMap hashMap0 = (HashMap)this.mKeyPositions.get(v);
            if(hashMap0 != null) {
                KeyPosition transition$KeyPosition0 = (KeyPosition)hashMap0.get(s);
                if(transition$KeyPosition0 != null) {
                    return transition$KeyPosition0;
                }
            }
            ++v;
        }
        return null;
    }

    public KeyPosition findPreviousPosition(String s, int v) {
        while(v >= 0) {
            HashMap hashMap0 = (HashMap)this.mKeyPositions.get(v);
            if(hashMap0 != null) {
                KeyPosition transition$KeyPosition0 = (KeyPosition)hashMap0.get(s);
                if(transition$KeyPosition0 != null) {
                    return transition$KeyPosition0;
                }
            }
            --v;
        }
        return null;
    }

    public int getAutoTransition() {
        return this.mAutoTransition;
    }

    public WidgetFrame getEnd(ConstraintWidget constraintWidget0) {
        return this.getWidgetState(constraintWidget0.stringId, null, 1).mEnd;
    }

    public WidgetFrame getEnd(String s) {
        WidgetState transition$WidgetState0 = (WidgetState)this.mState.get(s);
        return transition$WidgetState0 == null ? null : transition$WidgetState0.mEnd;
    }

    @Override  // androidx.constraintlayout.core.motion.utils.TypedValues
    public int getId(String s) {
        return 0;
    }

    public WidgetFrame getInterpolated(ConstraintWidget constraintWidget0) {
        return this.getWidgetState(constraintWidget0.stringId, null, 2).mInterpolated;
    }

    public WidgetFrame getInterpolated(String s) {
        WidgetState transition$WidgetState0 = (WidgetState)this.mState.get(s);
        return transition$WidgetState0 == null ? null : transition$WidgetState0.mInterpolated;
    }

    public int getInterpolatedHeight() {
        return this.mParentInterpolateHeight;
    }

    public int getInterpolatedWidth() {
        return this.mParentInterpolatedWidth;
    }

    public static Interpolator getInterpolator(int v, String s) {
        switch(v) {
            case -1: {
                return (float f) -> ((float)Easing.getInterpolator(s).get(((double)f)));
            }
            case 0: {
                return (float f) -> ((float)Easing.getInterpolator("standard").get(((double)f)));
            }
            case 1: {
                return (float f) -> ((float)Easing.getInterpolator("accelerate").get(((double)f)));
            }
            case 2: {
                return (float f) -> ((float)Easing.getInterpolator("decelerate").get(((double)f)));
            }
            case 3: {
                return (float f) -> ((float)Easing.getInterpolator("linear").get(((double)f)));
            }
            case 4: {
                return (float f) -> ((float)Easing.getInterpolator("spline(0.0, 0.2, 0.4, 0.6, 0.8 ,1.0, 0.8, 1.0, 0.9, 1.0)").get(((double)f)));
            }
            case 5: {
                return (float f) -> ((float)Easing.getInterpolator("overshoot").get(((double)f)));
            }
            case 6: {
                return (float f) -> ((float)Easing.getInterpolator("anticipate").get(((double)f)));
            }
            default: {
                return null;
            }
        }
    }

    public Interpolator getInterpolator() {
        return Transition.getInterpolator(this.mDefaultInterpolator, this.mDefaultInterpolatorString);
    }

    public int getKeyFrames(String s, float[] arr_f, int[] arr_v, int[] arr_v1) {
        return ((WidgetState)this.mState.get(s)).mMotionControl.buildKeyFrames(arr_f, arr_v, arr_v1);
    }

    public Motion getMotion(String s) {
        return this.getWidgetState(s, null, 0).mMotionControl;
    }

    public int getNumberKeyPositions(WidgetFrame widgetFrame0) {
        int v = 0;
        for(int v1 = 0; v1 <= 100; ++v1) {
            HashMap hashMap0 = (HashMap)this.mKeyPositions.get(v1);
            if(hashMap0 != null && ((KeyPosition)hashMap0.get(widgetFrame0.widget.stringId)) != null) {
                ++v;
            }
        }
        return v;
    }

    public float[] getPath(String s) {
        WidgetState transition$WidgetState0 = (WidgetState)this.mState.get(s);
        float[] arr_f = new float[0x7C];
        transition$WidgetState0.mMotionControl.buildPath(arr_f, 62);
        return arr_f;
    }

    public WidgetFrame getStart(ConstraintWidget constraintWidget0) {
        return this.getWidgetState(constraintWidget0.stringId, null, 0).mStart;
    }

    public WidgetFrame getStart(String s) {
        WidgetState transition$WidgetState0 = (WidgetState)this.mState.get(s);
        return transition$WidgetState0 == null ? null : transition$WidgetState0.mStart;
    }

    public float getTouchUpProgress(long v) {
        return this.mOnSwipe == null ? 0.0f : this.mOnSwipe.getTouchUpProgress(v);
    }

    private WidgetState getWidgetState(String s) {
        return (WidgetState)this.mState.get(s);
    }

    public WidgetState getWidgetState(String s, ConstraintWidget constraintWidget0, int v) {
        WidgetState transition$WidgetState0 = (WidgetState)this.mState.get(s);
        if(transition$WidgetState0 == null) {
            transition$WidgetState0 = new WidgetState();
            this.mBundle.applyDelta(transition$WidgetState0.mMotionControl);
            transition$WidgetState0.mMotionWidgetStart.updateMotion(transition$WidgetState0.mMotionControl);
            this.mState.put(s, transition$WidgetState0);
            if(constraintWidget0 != null) {
                transition$WidgetState0.update(constraintWidget0, v);
            }
        }
        return transition$WidgetState0;
    }

    public boolean hasOnSwipe() {
        return this.mOnSwipe != null;
    }

    public boolean hasPositionKeyframes() {
        return this.mKeyPositions.size() > 0;
    }

    public void interpolate(int v, int v1, float f) {
        if(this.mWrap) {
            this.calculateParentDimensions(f);
        }
        Easing easing0 = this.mEasing;
        if(easing0 != null) {
            f = (float)easing0.get(((double)f));
        }
        for(Object object0: this.mState.keySet()) {
            ((WidgetState)this.mState.get(((String)object0))).interpolate(v, v1, f, this);
        }
    }

    public boolean isEmpty() {
        return this.mState.isEmpty();
    }

    public boolean isFirstDownAccepted(float f, float f1) {
        OnSwipe transition$OnSwipe0 = this.mOnSwipe;
        if(transition$OnSwipe0 == null) {
            return false;
        }
        if(transition$OnSwipe0.mLimitBoundsTo != null) {
            WidgetState transition$WidgetState0 = (WidgetState)this.mState.get(this.mOnSwipe.mLimitBoundsTo);
            if(transition$WidgetState0 == null) {
                System.err.println("mLimitBoundsTo target is null");
                return false;
            }
            WidgetFrame widgetFrame0 = transition$WidgetState0.getFrame(2);
            return f >= ((float)widgetFrame0.left) && f < ((float)widgetFrame0.right) && f1 >= ((float)widgetFrame0.top) && f1 < ((float)widgetFrame0.bottom);
        }
        return true;
    }

    public boolean isTouchNotDone(float f) {
        return this.mOnSwipe.isNotDone(f);
    }

    // 检测为 Lambda 实现
    static float lambda$getInterpolator$0(String s, float f) [...]

    // 检测为 Lambda 实现
    static float lambda$getInterpolator$1(float f) [...]

    // 检测为 Lambda 实现
    static float lambda$getInterpolator$2(float f) [...]

    // 检测为 Lambda 实现
    static float lambda$getInterpolator$3(float f) [...]

    // 检测为 Lambda 实现
    static float lambda$getInterpolator$4(float f) [...]

    // 检测为 Lambda 实现
    static float lambda$getInterpolator$5(float f) [...]

    // 检测为 Lambda 实现
    static float lambda$getInterpolator$6(float f) [...]

    // 检测为 Lambda 实现
    static float lambda$getInterpolator$7(float f) [...]

    void resetProperties() {
        this.mOnSwipe = null;
        this.mBundle.clear();
    }

    public void setTouchUp(float f, long v, float f1, float f2) {
        OnSwipe transition$OnSwipe0 = this.mOnSwipe;
        if(transition$OnSwipe0 != null) {
            WidgetState transition$WidgetState0 = (WidgetState)this.mState.get(transition$OnSwipe0.mAnchorId);
            float[] arr_f = new float[2];
            float[] arr_f1 = this.mOnSwipe.getDirection();
            float[] arr_f2 = this.mOnSwipe.getSide();
            transition$WidgetState0.mMotionControl.getDpDt(f, arr_f2[0], arr_f2[1], arr_f);
            if(((double)Math.abs(arr_f1[0] * arr_f[0] + arr_f1[1] * arr_f[1])) < 0.01) {
                arr_f[0] = 0.01f;
                arr_f[1] = 0.01f;
            }
            this.mOnSwipe.config(f, (arr_f1[0] == 0.0f ? f2 / arr_f[1] : f1 / arr_f[0]) * this.mOnSwipe.getScale(), v, ((float)this.mDuration) * 0.001f);
        }
    }

    public void setTransitionProperties(TypedBundle typedBundle0) {
        typedBundle0.applyDelta(this.mBundle);
        typedBundle0.applyDelta(this);
    }

    @Override  // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int v, float f) {
        if(v == 706) {
            this.mStagger = f;
        }
        return false;
    }

    @Override  // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int v, int v1) {
        return false;
    }

    @Override  // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int v, String s) {
        if(v == 705) {
            this.mDefaultInterpolatorString = s;
            this.mEasing = Easing.getInterpolator(s);
        }
        return false;
    }

    @Override  // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int v, boolean z) {
        return false;
    }

    public void updateFrom(ConstraintWidgetContainer constraintWidgetContainer0, int v) {
        boolean z = true;
        boolean z1 = constraintWidgetContainer0.mListDimensionBehaviors[0] == DimensionBehaviour.WRAP_CONTENT;
        this.mWrap = z1;
        if(constraintWidgetContainer0.mListDimensionBehaviors[1] != DimensionBehaviour.WRAP_CONTENT) {
            z = false;
        }
        this.mWrap = z1 | z;
        if(v == 0) {
            int v2 = constraintWidgetContainer0.getWidth();
            this.mParentStartWidth = v2;
            this.mParentInterpolatedWidth = v2;
            int v3 = constraintWidgetContainer0.getHeight();
            this.mParentStartHeight = v3;
            this.mParentInterpolateHeight = v3;
        }
        else {
            this.mParentEndWidth = constraintWidgetContainer0.getWidth();
            this.mParentEndHeight = constraintWidgetContainer0.getHeight();
        }
        ArrayList arrayList0 = constraintWidgetContainer0.getChildren();
        int v4 = arrayList0.size();
        WidgetState[] arr_transition$WidgetState = new WidgetState[v4];
        for(int v1 = 0; v1 < v4; ++v1) {
            ConstraintWidget constraintWidget0 = (ConstraintWidget)arrayList0.get(v1);
            WidgetState transition$WidgetState0 = this.getWidgetState(constraintWidget0.stringId, null, v);
            arr_transition$WidgetState[v1] = transition$WidgetState0;
            transition$WidgetState0.update(constraintWidget0, v);
            String s = transition$WidgetState0.getPathRelativeId();
            if(s != null) {
                transition$WidgetState0.setPathRelative(this.getWidgetState(s, null, v));
            }
        }
        this.calcStagger();
    }
}

