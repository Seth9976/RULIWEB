package androidx.constraintlayout.core.motion;

import androidx.constraintlayout.core.motion.key.MotionKey;
import androidx.constraintlayout.core.motion.key.MotionKeyAttributes;
import androidx.constraintlayout.core.motion.key.MotionKeyCycle;
import androidx.constraintlayout.core.motion.key.MotionKeyPosition;
import androidx.constraintlayout.core.motion.key.MotionKeyTimeCycle;
import androidx.constraintlayout.core.motion.key.MotionKeyTrigger;
import androidx.constraintlayout.core.motion.utils.CurveFit;
import androidx.constraintlayout.core.motion.utils.DifferentialInterpolator;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.core.motion.utils.FloatRect;
import androidx.constraintlayout.core.motion.utils.KeyCache;
import androidx.constraintlayout.core.motion.utils.KeyCycleOscillator.PathRotateSet;
import androidx.constraintlayout.core.motion.utils.KeyCycleOscillator;
import androidx.constraintlayout.core.motion.utils.KeyFrameArray.CustomVar;
import androidx.constraintlayout.core.motion.utils.Rect;
import androidx.constraintlayout.core.motion.utils.SplineSet;
import androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet;
import androidx.constraintlayout.core.motion.utils.TypedBundle;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.motion.utils.Utils;
import androidx.constraintlayout.core.motion.utils.VelocityMatrix;
import androidx.constraintlayout.core.motion.utils.ViewState;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class Motion implements TypedValues {
    static final int BOUNCE = 4;
    private static final boolean DEBUG = false;
    public static final int DRAW_PATH_AS_CONFIGURED = 4;
    public static final int DRAW_PATH_BASIC = 1;
    public static final int DRAW_PATH_CARTESIAN = 3;
    public static final int DRAW_PATH_NONE = 0;
    public static final int DRAW_PATH_RECTANGLE = 5;
    public static final int DRAW_PATH_RELATIVE = 2;
    public static final int DRAW_PATH_SCREEN = 6;
    static final int EASE_IN = 1;
    static final int EASE_IN_OUT = 0;
    static final int EASE_OUT = 2;
    private static final boolean FAVOR_FIXED_SIZE_VIEWS = false;
    public static final int HORIZONTAL_PATH_X = 2;
    public static final int HORIZONTAL_PATH_Y = 3;
    private static final int INTERPOLATOR_REFERENCE_ID = -2;
    private static final int INTERPOLATOR_UNDEFINED = -3;
    static final int LINEAR = 3;
    static final int OVERSHOOT = 5;
    public static final int PATH_PERCENT = 0;
    public static final int PATH_PERPENDICULAR = 1;
    public static final int ROTATION_LEFT = 2;
    public static final int ROTATION_RIGHT = 1;
    private static final int SPLINE_STRING = -1;
    private static final String TAG = "MotionController";
    public static final int VERTICAL_PATH_X = 4;
    public static final int VERTICAL_PATH_Y = 5;
    private CurveFit mArcSpline;
    private int[] mAttributeInterpolatorCount;
    private String[] mAttributeNames;
    String[] mAttributeTable;
    private HashMap mAttributesMap;
    String mConstraintTag;
    float mCurrentCenterX;
    float mCurrentCenterY;
    private int mCurveFitType;
    private HashMap mCycleMap;
    private MotionPaths mEndMotionPath;
    private MotionConstrainedPoint mEndPoint;
    public String mId;
    private double[] mInterpolateData;
    private int[] mInterpolateVariables;
    private double[] mInterpolateVelocity;
    private ArrayList mKeyList;
    private MotionKeyTrigger[] mKeyTriggers;
    private int mMaxDimension;
    private ArrayList mMotionPaths;
    float mMotionStagger;
    private boolean mNoMovement;
    private int mPathMotionArc;
    private DifferentialInterpolator mQuantizeMotionInterpolator;
    private float mQuantizeMotionPhase;
    private int mQuantizeMotionSteps;
    Motion mRelativeMotion;
    private CurveFit[] mSpline;
    float mStaggerOffset;
    float mStaggerScale;
    private MotionPaths mStartMotionPath;
    private MotionConstrainedPoint mStartPoint;
    Rect mTempRect;
    private HashMap mTimeCycleAttributesMap;
    private int mTransformPivotTarget;
    private MotionWidget mTransformPivotView;
    private float[] mValuesBuff;
    private float[] mVelocity;
    MotionWidget mView;

    public Motion(MotionWidget motionWidget0) {
        this.mTempRect = new Rect();
        this.mCurveFitType = 0;
        this.mStartMotionPath = new MotionPaths();
        this.mEndMotionPath = new MotionPaths();
        this.mStartPoint = new MotionConstrainedPoint();
        this.mEndPoint = new MotionConstrainedPoint();
        this.mMotionStagger = NaNf;
        this.mStaggerOffset = 0.0f;
        this.mStaggerScale = 1.0f;
        this.mMaxDimension = 4;
        this.mValuesBuff = new float[4];
        this.mMotionPaths = new ArrayList();
        this.mVelocity = new float[1];
        this.mKeyList = new ArrayList();
        this.mPathMotionArc = -1;
        this.mTransformPivotTarget = -1;
        this.mTransformPivotView = null;
        this.mQuantizeMotionSteps = -1;
        this.mQuantizeMotionPhase = NaNf;
        this.mQuantizeMotionInterpolator = null;
        this.mNoMovement = false;
        this.setView(motionWidget0);
    }

    public void addKey(MotionKey motionKey0) {
        this.mKeyList.add(motionKey0);
    }

    void addKeys(ArrayList arrayList0) {
        this.mKeyList.addAll(arrayList0);
    }

    void buildBounds(float[] arr_f, int v) {
        HashMap hashMap0 = this.mAttributesMap;
        if(hashMap0 != null) {
            SplineSet splineSet0 = (SplineSet)hashMap0.get("translationX");
        }
        HashMap hashMap1 = this.mAttributesMap;
        if(hashMap1 != null) {
            SplineSet splineSet1 = (SplineSet)hashMap1.get("translationY");
        }
        HashMap hashMap2 = this.mCycleMap;
        if(hashMap2 != null) {
            KeyCycleOscillator keyCycleOscillator0 = (KeyCycleOscillator)hashMap2.get("translationX");
        }
        HashMap hashMap3 = this.mCycleMap;
        if(hashMap3 != null) {
            KeyCycleOscillator keyCycleOscillator1 = (KeyCycleOscillator)hashMap3.get("translationY");
        }
        for(int v1 = 0; v1 < v; ++v1) {
            float f = ((float)v1) * (1.0f / ((float)(v - 1)));
            float f1 = this.mStaggerScale;
            float f2 = 0.0f;
            if(f1 != 1.0f) {
                float f3 = this.mStaggerOffset;
                if(f < f3) {
                    f = 0.0f;
                }
                if(f > f3 && ((double)f) < 1.0) {
                    f = Math.min((f - f3) * f1, 1.0f);
                }
            }
            double f4 = (double)f;
            Easing easing0 = this.mStartMotionPath.mKeyFrameEasing;
            float f5 = NaNf;
            for(Object object0: this.mMotionPaths) {
                MotionPaths motionPaths0 = (MotionPaths)object0;
                if(motionPaths0.mKeyFrameEasing == null) {
                }
                else if(motionPaths0.mTime < f) {
                    easing0 = motionPaths0.mKeyFrameEasing;
                    f2 = motionPaths0.mTime;
                }
                else if(Float.isNaN(f5)) {
                    f5 = motionPaths0.mTime;
                }
            }
            if(easing0 != null) {
                if(Float.isNaN(f5)) {
                    f5 = 1.0f;
                }
                f4 = (double)(((float)easing0.get(((double)((f - f2) / (f5 - f2))))) * (f5 - f2) + f2);
            }
            this.mSpline[0].getPos(f4, this.mInterpolateData);
            CurveFit curveFit0 = this.mArcSpline;
            if(curveFit0 != null) {
                double[] arr_f1 = this.mInterpolateData;
                if(arr_f1.length > 0) {
                    curveFit0.getPos(f4, arr_f1);
                }
            }
            this.mStartMotionPath.getBounds(this.mInterpolateVariables, this.mInterpolateData, arr_f, v1 * 2);
        }
    }

    int buildKeyBounds(float[] arr_f, int[] arr_v) {
        if(arr_f != null) {
            double[] arr_f1 = this.mSpline[0].getTimePoints();
            if(arr_v != null) {
                int v = 0;
                for(Object object0: this.mMotionPaths) {
                    arr_v[v] = ((MotionPaths)object0).mMode;
                    ++v;
                }
            }
            int v2 = 0;
            for(int v1 = 0; v1 < arr_f1.length; ++v1) {
                this.mSpline[0].getPos(arr_f1[v1], this.mInterpolateData);
                this.mStartMotionPath.getBounds(this.mInterpolateVariables, this.mInterpolateData, arr_f, v2);
                v2 += 2;
            }
            return v2 / 2;
        }
        return 0;
    }

    public int buildKeyFrames(float[] arr_f, int[] arr_v, int[] arr_v1) {
        if(arr_f != null) {
            double[] arr_f1 = this.mSpline[0].getTimePoints();
            if(arr_v != null) {
                int v = 0;
                for(Object object0: this.mMotionPaths) {
                    arr_v[v] = ((MotionPaths)object0).mMode;
                    ++v;
                }
            }
            if(arr_v1 != null) {
                int v1 = 0;
                for(Object object1: this.mMotionPaths) {
                    arr_v1[v1] = (int)(((MotionPaths)object1).mPosition * 100.0f);
                    ++v1;
                }
            }
            int v3 = 0;
            for(int v2 = 0; v2 < arr_f1.length; ++v2) {
                this.mSpline[0].getPos(arr_f1[v2], this.mInterpolateData);
                this.mStartMotionPath.getCenter(arr_f1[v2], this.mInterpolateVariables, this.mInterpolateData, arr_f, v3);
                v3 += 2;
            }
            return v3 / 2;
        }
        return 0;
    }

    public void buildPath(float[] arr_f, int v) {
        KeyCycleOscillator keyCycleOscillator0 = null;
        SplineSet splineSet0 = this.mAttributesMap == null ? null : ((SplineSet)this.mAttributesMap.get("translationX"));
        SplineSet splineSet1 = this.mAttributesMap == null ? null : ((SplineSet)this.mAttributesMap.get("translationY"));
        KeyCycleOscillator keyCycleOscillator1 = this.mCycleMap == null ? null : ((KeyCycleOscillator)this.mCycleMap.get("translationX"));
        HashMap hashMap0 = this.mCycleMap;
        if(hashMap0 != null) {
            keyCycleOscillator0 = (KeyCycleOscillator)hashMap0.get("translationY");
        }
        for(int v1 = 0; v1 < v; ++v1) {
            float f = ((float)v1) * (1.0f / ((float)(v - 1)));
            float f1 = this.mStaggerScale;
            float f2 = 0.0f;
            if(f1 != 1.0f) {
                float f3 = this.mStaggerOffset;
                if(f < f3) {
                    f = 0.0f;
                }
                if(f > f3 && ((double)f) < 1.0) {
                    f = Math.min((f - f3) * f1, 1.0f);
                }
            }
            double f4 = (double)f;
            Easing easing0 = this.mStartMotionPath.mKeyFrameEasing;
            float f5 = NaNf;
            for(Object object0: this.mMotionPaths) {
                MotionPaths motionPaths0 = (MotionPaths)object0;
                if(motionPaths0.mKeyFrameEasing == null) {
                }
                else if(motionPaths0.mTime < f) {
                    f2 = motionPaths0.mTime;
                    easing0 = motionPaths0.mKeyFrameEasing;
                }
                else if(Float.isNaN(f5)) {
                    f5 = motionPaths0.mTime;
                }
            }
            if(easing0 != null) {
                if(Float.isNaN(f5)) {
                    f5 = 1.0f;
                }
                f4 = (double)(((float)easing0.get(((double)((f - f2) / (f5 - f2))))) * (f5 - f2) + f2);
            }
            this.mSpline[0].getPos(f4, this.mInterpolateData);
            CurveFit curveFit0 = this.mArcSpline;
            if(curveFit0 != null) {
                double[] arr_f1 = this.mInterpolateData;
                if(arr_f1.length > 0) {
                    curveFit0.getPos(f4, arr_f1);
                }
            }
            this.mStartMotionPath.getCenter(f4, this.mInterpolateVariables, this.mInterpolateData, arr_f, v1 * 2);
            if(keyCycleOscillator1 != null) {
                arr_f[v1 * 2] += keyCycleOscillator1.get(f);
            }
            else if(splineSet0 != null) {
                arr_f[v1 * 2] += splineSet0.get(f);
            }
            if(keyCycleOscillator0 != null) {
                int v2 = v1 * 2 + 1;
                arr_f[v2] += keyCycleOscillator0.get(f);
            }
            else if(splineSet1 != null) {
                int v3 = v1 * 2 + 1;
                arr_f[v3] += splineSet1.get(f);
            }
        }
    }

    public void buildRect(float f, float[] arr_f, int v) {
        float f1 = this.getAdjustedPosition(f, null);
        this.mSpline[0].getPos(((double)f1), this.mInterpolateData);
        this.mStartMotionPath.getRect(this.mInterpolateVariables, this.mInterpolateData, arr_f, v);
    }

    void buildRectangles(float[] arr_f, int v) {
        for(int v1 = 0; v1 < v; ++v1) {
            float f = this.getAdjustedPosition(((float)v1) * (1.0f / ((float)(v - 1))), null);
            this.mSpline[0].getPos(((double)f), this.mInterpolateData);
            this.mStartMotionPath.getRect(this.mInterpolateVariables, this.mInterpolateData, arr_f, v1 * 8);
        }
    }

    void endTrigger(boolean z) {
    }

    private float getAdjustedPosition(float f, float[] arr_f) {
        float f1 = 0.0f;
        float f2 = 1.0f;
        if(arr_f == null) {
            float f3 = this.mStaggerScale;
            if(((double)f3) != 1.0) {
                float f4 = this.mStaggerOffset;
                if(f < f4) {
                    f = 0.0f;
                }
                if(f > f4 && ((double)f) < 1.0) {
                    f = Math.min((f - f4) * f3, 1.0f);
                }
            }
        }
        else {
            arr_f[0] = 1.0f;
        }
        Easing easing0 = this.mStartMotionPath.mKeyFrameEasing;
        float f5 = NaNf;
        for(Object object0: this.mMotionPaths) {
            MotionPaths motionPaths0 = (MotionPaths)object0;
            if(motionPaths0.mKeyFrameEasing == null) {
            }
            else if(motionPaths0.mTime < f) {
                easing0 = motionPaths0.mKeyFrameEasing;
                f1 = motionPaths0.mTime;
            }
            else if(Float.isNaN(f5)) {
                f5 = motionPaths0.mTime;
            }
        }
        if(easing0 != null) {
            if(!Float.isNaN(f5)) {
                f2 = f5;
            }
            float f6 = f2 - f1;
            double f7 = (double)((f - f1) / f6);
            f = ((float)easing0.get(f7)) * f6 + f1;
            if(arr_f != null) {
                arr_f[0] = (float)easing0.getDiff(f7);
            }
        }
        return f;
    }

    public String getAnimateRelativeTo() {
        return this.mStartMotionPath.mAnimateRelativeTo;
    }

    int getAttributeValues(String s, float[] arr_f, int v) {
        SplineSet splineSet0 = (SplineSet)this.mAttributesMap.get(s);
        if(splineSet0 == null) {
            return -1;
        }
        for(int v1 = 0; v1 < arr_f.length; ++v1) {
            arr_f[v1] = splineSet0.get(((float)(v1 / (arr_f.length - 1))));
        }
        return arr_f.length;
    }

    public void getCenter(double f, float[] arr_f, float[] arr_f1) {
        double[] arr_f2 = new double[4];
        double[] arr_f3 = new double[4];
        this.mSpline[0].getPos(f, arr_f2);
        this.mSpline[0].getSlope(f, arr_f3);
        Arrays.fill(arr_f1, 0.0f);
        this.mStartMotionPath.getCenter(f, this.mInterpolateVariables, arr_f2, arr_f, arr_f3, arr_f1);
    }

    public float getCenterX() {
        return this.mCurrentCenterX;
    }

    public float getCenterY() {
        return this.mCurrentCenterY;
    }

    public void getDpDt(float f, float f1, float f2, float[] arr_f) {
        double[] arr_f1;
        float f3 = this.getAdjustedPosition(f, this.mVelocity);
        CurveFit[] arr_curveFit = this.mSpline;
        if(arr_curveFit != null) {
            arr_curveFit[0].getSlope(((double)f3), this.mInterpolateVelocity);
            this.mSpline[0].getPos(((double)f3), this.mInterpolateData);
            float f4 = this.mVelocity[0];
            for(int v = 0; true; ++v) {
                arr_f1 = this.mInterpolateVelocity;
                if(v >= arr_f1.length) {
                    break;
                }
                arr_f1[v] *= (double)f4;
            }
            CurveFit curveFit0 = this.mArcSpline;
            if(curveFit0 != null) {
                double[] arr_f2 = this.mInterpolateData;
                if(arr_f2.length > 0) {
                    curveFit0.getPos(((double)f3), arr_f2);
                    this.mArcSpline.getSlope(((double)f3), this.mInterpolateVelocity);
                    this.mStartMotionPath.setDpDt(f1, f2, arr_f, this.mInterpolateVariables, this.mInterpolateVelocity, this.mInterpolateData);
                }
                return;
            }
            this.mStartMotionPath.setDpDt(f1, f2, arr_f, this.mInterpolateVariables, arr_f1, this.mInterpolateData);
            return;
        }
        float f5 = this.mEndMotionPath.mX - this.mStartMotionPath.mX;
        float f6 = this.mEndMotionPath.mY - this.mStartMotionPath.mY;
        float f7 = this.mEndMotionPath.mHeight - this.mStartMotionPath.mHeight + f6;
        arr_f[0] = f5 * (1.0f - f1) + (this.mEndMotionPath.mWidth - this.mStartMotionPath.mWidth + f5) * f1;
        arr_f[1] = f6 * (1.0f - f2) + f7 * f2;
    }

    public int getDrawPath() {
        int v = this.mStartMotionPath.mDrawPath;
        for(Object object0: this.mMotionPaths) {
            v = Math.max(v, ((MotionPaths)object0).mDrawPath);
        }
        return Math.max(v, this.mEndMotionPath.mDrawPath);
    }

    public float getFinalHeight() {
        return this.mEndMotionPath.mHeight;
    }

    public float getFinalWidth() {
        return this.mEndMotionPath.mWidth;
    }

    public float getFinalX() {
        return this.mEndMotionPath.mX;
    }

    public float getFinalY() {
        return this.mEndMotionPath.mY;
    }

    @Override  // androidx.constraintlayout.core.motion.utils.TypedValues
    public int getId(String s) {
        return 0;
    }

    private static DifferentialInterpolator getInterpolator(int v, String s, int v1) {
        return v != -1 ? null : new DifferentialInterpolator() {
            float mX;

            @Override  // androidx.constraintlayout.core.motion.utils.DifferentialInterpolator
            public float getInterpolation(float f) {
                this.mX = f;
                return (float)Easing.getInterpolator(s).get(((double)f));
            }

            @Override  // androidx.constraintlayout.core.motion.utils.DifferentialInterpolator
            public float getVelocity() {
                return (float)Easing.getInterpolator(s).getDiff(((double)this.mX));
            }
        };
    }

    public MotionPaths getKeyFrame(int v) {
        return (MotionPaths)this.mMotionPaths.get(v);
    }

    public int getKeyFrameInfo(int v, int[] arr_v) {
        float[] arr_f = new float[2];
        int v1 = 0;
        int v2 = 0;
        for(Object object0: this.mKeyList) {
            MotionKey motionKey0 = (MotionKey)object0;
            if(motionKey0.mType == v || v != -1) {
                arr_v[v2] = 0;
                arr_v[v2 + 1] = motionKey0.mType;
                arr_v[v2 + 2] = motionKey0.mFramePosition;
                float f = ((float)motionKey0.mFramePosition) / 100.0f;
                this.mSpline[0].getPos(((double)f), this.mInterpolateData);
                this.mStartMotionPath.getCenter(((double)f), this.mInterpolateVariables, this.mInterpolateData, arr_f, 0);
                arr_v[v2 + 3] = Float.floatToIntBits(arr_f[0]);
                int v3 = v2 + 4;
                arr_v[v3] = Float.floatToIntBits(arr_f[1]);
                if(motionKey0 instanceof MotionKeyPosition) {
                    arr_v[v2 + 5] = ((MotionKeyPosition)motionKey0).mPositionType;
                    arr_v[v2 + 6] = Float.floatToIntBits(((MotionKeyPosition)motionKey0).mPercentX);
                    v3 = v2 + 7;
                    arr_v[v3] = Float.floatToIntBits(((MotionKeyPosition)motionKey0).mPercentY);
                }
                arr_v[v2] = v3 + 1 - v2;
                ++v1;
                v2 = v3 + 1;
            }
        }
        return v1;
    }

    float getKeyFrameParameter(int v, float f, float f1) {
        float f2 = this.mEndMotionPath.mX - this.mStartMotionPath.mX;
        float f3 = this.mEndMotionPath.mY - this.mStartMotionPath.mY;
        float f4 = this.mStartMotionPath.mX + this.mStartMotionPath.mWidth / 2.0f;
        float f5 = this.mStartMotionPath.mY + this.mStartMotionPath.mHeight / 2.0f;
        float f6 = (float)Math.hypot(f2, f3);
        if(((double)f6) < 1.000000E-07) {
            return NaNf;
        }
        float f7 = f - f4;
        float f8 = f1 - f5;
        if(((float)Math.hypot(f7, f8)) == 0.0f) {
            return 0.0f;
        }
        float f9 = f7 * f2 + f8 * f3;
        switch(v) {
            case 0: {
                return f9 / f6;
            }
            case 1: {
                return (float)Math.sqrt(f6 * f6 - f9 * f9);
            }
            case 2: {
                return f7 / f2;
            }
            case 3: {
                return f8 / f2;
            }
            case 4: {
                return f7 / f3;
            }
            case 5: {
                return f8 / f3;
            }
            default: {
                return 0.0f;
            }
        }
    }

    public int getKeyFramePositions(int[] arr_v, float[] arr_f) {
        int v = 0;
        int v1 = 0;
        for(Object object0: this.mKeyList) {
            arr_v[v] = ((MotionKey)object0).mFramePosition + ((MotionKey)object0).mType * 1000;
            float f = ((float)((MotionKey)object0).mFramePosition) / 100.0f;
            this.mSpline[0].getPos(((double)f), this.mInterpolateData);
            this.mStartMotionPath.getCenter(((double)f), this.mInterpolateVariables, this.mInterpolateData, arr_f, v1);
            v1 += 2;
            ++v;
        }
        return v;
    }

    public float getMotionStagger() {
        return this.mMotionStagger;
    }

    double[] getPos(double f) {
        this.mSpline[0].getPos(f, this.mInterpolateData);
        CurveFit curveFit0 = this.mArcSpline;
        if(curveFit0 != null) {
            double[] arr_f = this.mInterpolateData;
            if(arr_f.length > 0) {
                curveFit0.getPos(f, arr_f);
            }
        }
        return this.mInterpolateData;
    }

    MotionKeyPosition getPositionKeyframe(int v, int v1, float f, float f1) {
        FloatRect floatRect0 = new FloatRect();
        floatRect0.left = this.mStartMotionPath.mX;
        floatRect0.top = this.mStartMotionPath.mY;
        floatRect0.right = floatRect0.left + this.mStartMotionPath.mWidth;
        floatRect0.bottom = floatRect0.top + this.mStartMotionPath.mHeight;
        FloatRect floatRect1 = new FloatRect();
        floatRect1.left = this.mEndMotionPath.mX;
        floatRect1.top = this.mEndMotionPath.mY;
        floatRect1.right = floatRect1.left + this.mEndMotionPath.mWidth;
        floatRect1.bottom = floatRect1.top + this.mEndMotionPath.mHeight;
        for(Object object0: this.mKeyList) {
            MotionKey motionKey0 = (MotionKey)object0;
            if(motionKey0 instanceof MotionKeyPosition && ((MotionKeyPosition)motionKey0).intersects(v, v1, floatRect0, floatRect1, f, f1)) {
                return (MotionKeyPosition)motionKey0;
            }
            if(false) {
                break;
            }
        }
        return null;
    }

    void getPostLayoutDvDp(float f, int v, int v1, float f1, float f2, float[] arr_f) {
        double[] arr_f2;
        float f3 = this.getAdjustedPosition(f, this.mVelocity);
        KeyCycleOscillator keyCycleOscillator0 = null;
        SplineSet splineSet0 = this.mAttributesMap == null ? null : ((SplineSet)this.mAttributesMap.get("translationX"));
        SplineSet splineSet1 = this.mAttributesMap == null ? null : ((SplineSet)this.mAttributesMap.get("translationY"));
        SplineSet splineSet2 = this.mAttributesMap == null ? null : ((SplineSet)this.mAttributesMap.get("rotationZ"));
        SplineSet splineSet3 = this.mAttributesMap == null ? null : ((SplineSet)this.mAttributesMap.get("scaleX"));
        SplineSet splineSet4 = this.mAttributesMap == null ? null : ((SplineSet)this.mAttributesMap.get("scaleY"));
        KeyCycleOscillator keyCycleOscillator1 = this.mCycleMap == null ? null : ((KeyCycleOscillator)this.mCycleMap.get("translationX"));
        KeyCycleOscillator keyCycleOscillator2 = this.mCycleMap == null ? null : ((KeyCycleOscillator)this.mCycleMap.get("translationY"));
        KeyCycleOscillator keyCycleOscillator3 = this.mCycleMap == null ? null : ((KeyCycleOscillator)this.mCycleMap.get("rotationZ"));
        KeyCycleOscillator keyCycleOscillator4 = this.mCycleMap == null ? null : ((KeyCycleOscillator)this.mCycleMap.get("scaleX"));
        HashMap hashMap0 = this.mCycleMap;
        if(hashMap0 != null) {
            keyCycleOscillator0 = (KeyCycleOscillator)hashMap0.get("scaleY");
        }
        VelocityMatrix velocityMatrix0 = new VelocityMatrix();
        velocityMatrix0.clear();
        velocityMatrix0.setRotationVelocity(splineSet2, f3);
        velocityMatrix0.setTranslationVelocity(splineSet0, splineSet1, f3);
        velocityMatrix0.setScaleVelocity(splineSet3, splineSet4, f3);
        velocityMatrix0.setRotationVelocity(keyCycleOscillator3, f3);
        velocityMatrix0.setTranslationVelocity(keyCycleOscillator1, keyCycleOscillator2, f3);
        velocityMatrix0.setScaleVelocity(keyCycleOscillator4, keyCycleOscillator0, f3);
        CurveFit curveFit0 = this.mArcSpline;
        if(curveFit0 != null) {
            double[] arr_f1 = this.mInterpolateData;
            if(arr_f1.length > 0) {
                curveFit0.getPos(((double)f3), arr_f1);
                this.mArcSpline.getSlope(((double)f3), this.mInterpolateVelocity);
                this.mStartMotionPath.setDpDt(f1, f2, arr_f, this.mInterpolateVariables, this.mInterpolateVelocity, this.mInterpolateData);
            }
            velocityMatrix0.applyTransform(f1, f2, v, v1, arr_f);
            return;
        }
        if(this.mSpline != null) {
            float f4 = this.getAdjustedPosition(f3, this.mVelocity);
            this.mSpline[0].getSlope(((double)f4), this.mInterpolateVelocity);
            this.mSpline[0].getPos(((double)f4), this.mInterpolateData);
            float f5 = this.mVelocity[0];
            for(int v2 = 0; true; ++v2) {
                arr_f2 = this.mInterpolateVelocity;
                if(v2 >= arr_f2.length) {
                    break;
                }
                arr_f2[v2] *= (double)f5;
            }
            this.mStartMotionPath.setDpDt(f1, f2, arr_f, this.mInterpolateVariables, arr_f2, this.mInterpolateData);
            velocityMatrix0.applyTransform(f1, f2, v, v1, arr_f);
            return;
        }
        float f6 = this.mEndMotionPath.mY - this.mStartMotionPath.mY;
        float f7 = this.mEndMotionPath.mX - this.mStartMotionPath.mX;
        float f8 = this.mEndMotionPath.mHeight - this.mStartMotionPath.mHeight + f6;
        arr_f[0] = f7 * (1.0f - f1) + (f7 + (this.mEndMotionPath.mWidth - this.mStartMotionPath.mWidth)) * f1;
        arr_f[1] = f6 * (1.0f - f2) + f8 * f2;
        velocityMatrix0.clear();
        velocityMatrix0.setRotationVelocity(splineSet2, f3);
        velocityMatrix0.setTranslationVelocity(splineSet0, splineSet1, f3);
        velocityMatrix0.setScaleVelocity(splineSet3, splineSet4, f3);
        velocityMatrix0.setRotationVelocity(keyCycleOscillator3, f3);
        velocityMatrix0.setTranslationVelocity(keyCycleOscillator1, keyCycleOscillator2, f3);
        velocityMatrix0.setScaleVelocity(keyCycleOscillator4, keyCycleOscillator0, f3);
        velocityMatrix0.applyTransform(f1, f2, v, v1, arr_f);
    }

    private float getPreCycleDistance() {
        float[] arr_f = new float[2];
        double f = 0.0;
        double f1 = 0.0;
        float f2 = 0.0f;
        for(int v = 0; v < 100; ++v) {
            float f3 = ((float)v) * 0.010101f;
            double f4 = (double)f3;
            Easing easing0 = this.mStartMotionPath.mKeyFrameEasing;
            float f5 = NaNf;
            float f6 = 0.0f;
            for(Object object0: this.mMotionPaths) {
                MotionPaths motionPaths0 = (MotionPaths)object0;
                if(motionPaths0.mKeyFrameEasing == null) {
                }
                else if(motionPaths0.mTime < f3) {
                    easing0 = motionPaths0.mKeyFrameEasing;
                    f6 = motionPaths0.mTime;
                }
                else if(Float.isNaN(f5)) {
                    f5 = motionPaths0.mTime;
                }
            }
            if(easing0 != null) {
                if(Float.isNaN(f5)) {
                    f5 = 1.0f;
                }
                f4 = (double)(((float)easing0.get(((double)((f3 - f6) / (f5 - f6))))) * (f5 - f6) + f6);
            }
            this.mSpline[0].getPos(f4, this.mInterpolateData);
            this.mStartMotionPath.getCenter(f4, this.mInterpolateVariables, this.mInterpolateData, arr_f, 0);
            if(v > 0) {
                f2 += (float)Math.hypot(f1 - ((double)arr_f[1]), f - ((double)arr_f[0]));
            }
            f = (double)arr_f[0];
            f1 = (double)arr_f[1];
        }
        return f2;
    }

    public float getStartHeight() {
        return this.mStartMotionPath.mHeight;
    }

    public float getStartWidth() {
        return this.mStartMotionPath.mWidth;
    }

    public float getStartX() {
        return this.mStartMotionPath.mX;
    }

    public float getStartY() {
        return this.mStartMotionPath.mY;
    }

    public int getTransformPivotTarget() {
        return this.mTransformPivotTarget;
    }

    public MotionWidget getView() {
        return this.mView;
    }

    private void insertKey(MotionPaths motionPaths0) {
        MotionPaths motionPaths1 = null;
        for(Object object0: this.mMotionPaths) {
            MotionPaths motionPaths2 = (MotionPaths)object0;
            if(motionPaths0.mPosition == motionPaths2.mPosition) {
                motionPaths1 = motionPaths2;
            }
        }
        if(motionPaths1 != null) {
            this.mMotionPaths.remove(motionPaths1);
        }
        int v = Collections.binarySearch(this.mMotionPaths, motionPaths0);
        if(v == 0) {
            Utils.loge("MotionController", " KeyPath position \"" + motionPaths0.mPosition + "\" outside of range");
        }
        this.mMotionPaths.add(-v - 1, motionPaths0);
    }

    public boolean interpolate(MotionWidget motionWidget0, float f, long v, KeyCache keyCache0) {
        float f4;
        float f1 = this.getAdjustedPosition(f, null);
        int v1 = this.mQuantizeMotionSteps;
        if(v1 != -1) {
            float f2 = ((float)Math.floor(f1 / (1.0f / ((float)v1)))) * (1.0f / ((float)v1));
            float f3 = f1 % (1.0f / ((float)v1)) / (1.0f / ((float)v1));
            if(!Float.isNaN(this.mQuantizeMotionPhase)) {
                f3 = (f3 + this.mQuantizeMotionPhase) % 1.0f;
            }
            DifferentialInterpolator differentialInterpolator0 = this.mQuantizeMotionInterpolator;
            if(differentialInterpolator0 == null) {
                f4 = ((double)f3) > 0.5 ? 1.0f : 0.0f;
            }
            else {
                f4 = differentialInterpolator0.getInterpolation(f3);
            }
            f1 = f4 * (1.0f / ((float)v1)) + f2;
        }
        HashMap hashMap0 = this.mAttributesMap;
        if(hashMap0 != null) {
            for(Object object0: hashMap0.values()) {
                ((SplineSet)object0).setProperty(motionWidget0, f1);
            }
        }
        CurveFit[] arr_curveFit = this.mSpline;
        if(arr_curveFit == null) {
            float f5 = this.mStartMotionPath.mX + (this.mEndMotionPath.mX - this.mStartMotionPath.mX) * f1 + 0.5f;
            float f6 = this.mStartMotionPath.mY + (this.mEndMotionPath.mY - this.mStartMotionPath.mY) * f1 + 0.5f;
            motionWidget0.layout(((int)f5), ((int)f6), ((int)(f5 + (this.mStartMotionPath.mWidth + (this.mEndMotionPath.mWidth - this.mStartMotionPath.mWidth) * f1))), ((int)(f6 + (this.mStartMotionPath.mHeight + (this.mEndMotionPath.mHeight - this.mStartMotionPath.mHeight) * f1))));
        }
        else {
            arr_curveFit[0].getPos(((double)f1), this.mInterpolateData);
            this.mSpline[0].getSlope(((double)f1), this.mInterpolateVelocity);
            CurveFit curveFit0 = this.mArcSpline;
            if(curveFit0 != null) {
                double[] arr_f = this.mInterpolateData;
                if(arr_f.length > 0) {
                    curveFit0.getPos(((double)f1), arr_f);
                    this.mArcSpline.getSlope(((double)f1), this.mInterpolateVelocity);
                }
            }
            if(!this.mNoMovement) {
                this.mStartMotionPath.setView(f1, motionWidget0, this.mInterpolateVariables, this.mInterpolateData, this.mInterpolateVelocity, null);
            }
            if(this.mTransformPivotTarget != -1) {
                if(this.mTransformPivotView == null) {
                    this.mTransformPivotView = null;
                }
                MotionWidget motionWidget1 = this.mTransformPivotView;
                if(motionWidget1 != null) {
                    int v2 = motionWidget1.getTop();
                    int v3 = this.mTransformPivotView.getBottom();
                    int v4 = this.mTransformPivotView.getLeft();
                    int v5 = this.mTransformPivotView.getRight();
                    if(motionWidget0.getRight() - motionWidget0.getLeft() > 0 && motionWidget0.getBottom() - motionWidget0.getTop() > 0) {
                        motionWidget0.setPivotX(((float)(v4 + v5)) / 2.0f - ((float)motionWidget0.getLeft()));
                        motionWidget0.setPivotY(((float)(v2 + v3)) / 2.0f - ((float)motionWidget0.getTop()));
                    }
                }
            }
            for(int v6 = 1; true; ++v6) {
                CurveFit[] arr_curveFit1 = this.mSpline;
                if(v6 >= arr_curveFit1.length) {
                    break;
                }
                arr_curveFit1[v6].getPos(((double)f1), this.mValuesBuff);
                ((CustomVariable)this.mStartMotionPath.mCustomAttributes.get(this.mAttributeNames[v6 - 1])).setInterpolatedValue(motionWidget0, this.mValuesBuff);
            }
            if(this.mStartPoint.mVisibilityMode == 0) {
                if(f1 <= 0.0f) {
                    motionWidget0.setVisibility(this.mStartPoint.mVisibility);
                }
                else if(f1 >= 1.0f) {
                    motionWidget0.setVisibility(this.mEndPoint.mVisibility);
                }
                else if(this.mEndPoint.mVisibility != this.mStartPoint.mVisibility) {
                    motionWidget0.setVisibility(4);
                }
            }
            if(this.mKeyTriggers != null) {
                for(int v7 = 0; v7 < this.mKeyTriggers.length; ++v7) {
                }
            }
        }
        HashMap hashMap1 = this.mCycleMap;
        if(hashMap1 != null) {
            for(Object object1: hashMap1.values()) {
                KeyCycleOscillator keyCycleOscillator0 = (KeyCycleOscillator)object1;
                if(keyCycleOscillator0 instanceof PathRotateSet) {
                    ((PathRotateSet)keyCycleOscillator0).setPathRotate(motionWidget0, f1, this.mInterpolateVelocity[0], this.mInterpolateVelocity[1]);
                }
                else {
                    keyCycleOscillator0.setProperty(motionWidget0, f1);
                }
            }
        }
        return false;
    }

    // 去混淆评级： 低(20)
    String name() {
        return "unknown";
    }

    void positionKeyframe(MotionWidget motionWidget0, MotionKeyPosition motionKeyPosition0, float f, float f1, String[] arr_s, float[] arr_f) {
        FloatRect floatRect0 = new FloatRect();
        floatRect0.left = this.mStartMotionPath.mX;
        floatRect0.top = this.mStartMotionPath.mY;
        floatRect0.right = floatRect0.left + this.mStartMotionPath.mWidth;
        floatRect0.bottom = floatRect0.top + this.mStartMotionPath.mHeight;
        FloatRect floatRect1 = new FloatRect();
        floatRect1.left = this.mEndMotionPath.mX;
        floatRect1.top = this.mEndMotionPath.mY;
        floatRect1.right = floatRect1.left + this.mEndMotionPath.mWidth;
        floatRect1.bottom = floatRect1.top + this.mEndMotionPath.mHeight;
        motionKeyPosition0.positionAttributes(motionWidget0, floatRect0, floatRect1, f, f1, arr_s, arr_f);
    }

    private void readView(MotionPaths motionPaths0) {
        motionPaths0.setBounds(((float)this.mView.getX()), ((float)this.mView.getY()), ((float)this.mView.getWidth()), ((float)this.mView.getHeight()));
    }

    void rotate(Rect rect0, Rect rect1, int v, int v1, int v2) {
        switch(v) {
            case 1: {
                int v3 = rect0.left + rect0.right;
                rect1.left = (rect0.top + rect0.bottom - rect0.width()) / 2;
                rect1.top = v2 - (v3 + rect0.height()) / 2;
                rect1.right = rect1.left + rect0.width();
                rect1.bottom = rect1.top + rect0.height();
                return;
            }
            case 2: {
                int v4 = rect0.left + rect0.right;
                rect1.left = v1 - (rect0.top + rect0.bottom + rect0.width()) / 2;
                rect1.top = (v4 - rect0.height()) / 2;
                rect1.right = rect1.left + rect0.width();
                rect1.bottom = rect1.top + rect0.height();
                return;
            }
            case 3: {
                int v5 = rect0.left + rect0.right;
                rect1.left = rect0.height() / 2 + rect0.top - v5 / 2;
                rect1.top = v2 - (v5 + rect0.height()) / 2;
                rect1.right = rect1.left + rect0.width();
                rect1.bottom = rect1.top + rect0.height();
                return;
            }
            case 4: {
                int v6 = rect0.left + rect0.right;
                rect1.left = v1 - (rect0.bottom + rect0.top + rect0.width()) / 2;
                rect1.top = (v6 - rect0.height()) / 2;
                rect1.right = rect1.left + rect0.width();
                rect1.bottom = rect1.top + rect0.height();
            }
        }
    }

    void setBothStates(MotionWidget motionWidget0) {
        this.mStartMotionPath.mTime = 0.0f;
        this.mStartMotionPath.mPosition = 0.0f;
        this.mNoMovement = true;
        this.mStartMotionPath.setBounds(((float)motionWidget0.getX()), ((float)motionWidget0.getY()), ((float)motionWidget0.getWidth()), ((float)motionWidget0.getHeight()));
        this.mEndMotionPath.setBounds(((float)motionWidget0.getX()), ((float)motionWidget0.getY()), ((float)motionWidget0.getWidth()), ((float)motionWidget0.getHeight()));
        this.mStartPoint.setState(motionWidget0);
        this.mEndPoint.setState(motionWidget0);
    }

    public void setDrawPath(int v) {
        this.mStartMotionPath.mDrawPath = v;
    }

    public void setEnd(MotionWidget motionWidget0) {
        this.mEndMotionPath.mTime = 1.0f;
        this.mEndMotionPath.mPosition = 1.0f;
        this.readView(this.mEndMotionPath);
        this.mEndMotionPath.setBounds(((float)motionWidget0.getLeft()), ((float)motionWidget0.getTop()), ((float)motionWidget0.getWidth()), ((float)motionWidget0.getHeight()));
        this.mEndMotionPath.applyParameters(motionWidget0);
        this.mEndPoint.setState(motionWidget0);
    }

    public void setIdString(String s) {
        this.mId = s;
        this.mStartMotionPath.mId = s;
    }

    public void setPathMotionArc(int v) {
        this.mPathMotionArc = v;
    }

    public void setStaggerOffset(float f) {
        this.mStaggerOffset = f;
    }

    public void setStaggerScale(float f) {
        this.mStaggerScale = f;
    }

    public void setStart(MotionWidget motionWidget0) {
        this.mStartMotionPath.mTime = 0.0f;
        this.mStartMotionPath.mPosition = 0.0f;
        this.mStartMotionPath.setBounds(((float)motionWidget0.getX()), ((float)motionWidget0.getY()), ((float)motionWidget0.getWidth()), ((float)motionWidget0.getHeight()));
        this.mStartMotionPath.applyParameters(motionWidget0);
        this.mStartPoint.setState(motionWidget0);
        TypedBundle typedBundle0 = motionWidget0.getWidgetFrame().getMotionProperties();
        if(typedBundle0 != null) {
            typedBundle0.applyDelta(this);
        }
    }

    public void setStartState(ViewState viewState0, MotionWidget motionWidget0, int v, int v1, int v2) {
        this.mStartMotionPath.mTime = 0.0f;
        this.mStartMotionPath.mPosition = 0.0f;
        Rect rect0 = new Rect();
        switch(v) {
            case 1: {
                int v3 = viewState0.left + viewState0.right;
                rect0.left = (viewState0.top + viewState0.bottom - viewState0.width()) / 2;
                rect0.top = v1 - (v3 + viewState0.height()) / 2;
                rect0.right = rect0.left + viewState0.width();
                rect0.bottom = rect0.top + viewState0.height();
                break;
            }
            case 2: {
                int v4 = viewState0.left + viewState0.right;
                rect0.left = v2 - (viewState0.top + viewState0.bottom + viewState0.width()) / 2;
                rect0.top = (v4 - viewState0.height()) / 2;
                rect0.right = rect0.left + viewState0.width();
                rect0.bottom = rect0.top + viewState0.height();
            }
        }
        this.mStartMotionPath.setBounds(((float)rect0.left), ((float)rect0.top), ((float)rect0.width()), ((float)rect0.height()));
        this.mStartPoint.setState(rect0, motionWidget0, v, viewState0.rotation);
    }

    public void setTransformPivotTarget(int v) {
        this.mTransformPivotTarget = v;
        this.mTransformPivotView = null;
    }

    @Override  // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int v, float f) {
        if(602 == v) {
            this.mQuantizeMotionPhase = f;
            return true;
        }
        if(600 == v) {
            this.mMotionStagger = f;
            return true;
        }
        return false;
    }

    @Override  // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int v, int v1) {
        switch(v) {
            case 509: {
                this.setPathMotionArc(v1);
                return true;
            }
            case 610: {
                this.mQuantizeMotionSteps = v1;
                return true;
            }
            case 704: {
                return true;
            }
            default: {
                return false;
            }
        }
    }

    @Override  // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int v, String s) {
        if(705 != v && 611 != v) {
            if(605 == v) {
                this.mStartMotionPath.mAnimateRelativeTo = s;
                return true;
            }
            return false;
        }
        this.mQuantizeMotionInterpolator = Motion.getInterpolator(-1, s, 0);
        return true;
    }

    @Override  // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int v, boolean z) {
        return false;
    }

    public void setView(MotionWidget motionWidget0) {
        this.mView = motionWidget0;
    }

    public void setup(int v, int v1, float f, long v2) {
        SplineSet splineSet2;
        int v3;
        SplineSet splineSet0;
        ArrayList arrayList1;
        new HashSet();
        HashSet hashSet0 = new HashSet();
        HashSet hashSet1 = new HashSet();
        HashSet hashSet2 = new HashSet();
        HashMap hashMap0 = new HashMap();
        this.setupRelative();
        if(this.mPathMotionArc != -1 && this.mStartMotionPath.mPathMotionArc == -1) {
            this.mStartMotionPath.mPathMotionArc = this.mPathMotionArc;
        }
        this.mStartPoint.different(this.mEndPoint, hashSet1);
        ArrayList arrayList0 = this.mKeyList;
        if(arrayList0 == null) {
            arrayList1 = null;
        }
        else {
            arrayList1 = null;
            for(Object object0: arrayList0) {
                MotionKey motionKey0 = (MotionKey)object0;
                if(motionKey0 instanceof MotionKeyPosition) {
                    this.insertKey(new MotionPaths(v, v1, ((MotionKeyPosition)motionKey0), this.mStartMotionPath, this.mEndMotionPath));
                    if(((MotionKeyPosition)motionKey0).mCurveFit == -1) {
                        continue;
                    }
                    this.mCurveFitType = ((MotionKeyPosition)motionKey0).mCurveFit;
                }
                else if(motionKey0 instanceof MotionKeyCycle) {
                    motionKey0.getAttributeNames(hashSet2);
                }
                else if(motionKey0 instanceof MotionKeyTimeCycle) {
                    motionKey0.getAttributeNames(hashSet0);
                }
                else if(motionKey0 instanceof MotionKeyTrigger) {
                    if(arrayList1 == null) {
                        arrayList1 = new ArrayList();
                    }
                    arrayList1.add(((MotionKeyTrigger)motionKey0));
                }
                else {
                    motionKey0.setInterpolation(hashMap0);
                    motionKey0.getAttributeNames(hashSet1);
                }
            }
        }
        if(arrayList1 != null) {
            this.mKeyTriggers = (MotionKeyTrigger[])arrayList1.toArray(new MotionKeyTrigger[0]);
        }
        if(!hashSet1.isEmpty()) {
            this.mAttributesMap = new HashMap();
            for(Object object1: hashSet1) {
                String s = (String)object1;
                if(s.startsWith("CUSTOM,")) {
                    CustomVar keyFrameArray$CustomVar0 = new CustomVar();
                    String s1 = s.split(",")[1];
                    for(Object object2: this.mKeyList) {
                        MotionKey motionKey1 = (MotionKey)object2;
                        if(motionKey1.mCustom != null) {
                            CustomVariable customVariable0 = (CustomVariable)motionKey1.mCustom.get(s1);
                            if(customVariable0 != null) {
                                keyFrameArray$CustomVar0.append(motionKey1.mFramePosition, customVariable0);
                            }
                        }
                    }
                    splineSet0 = SplineSet.makeCustomSplineSet(s, keyFrameArray$CustomVar0);
                }
                else {
                    splineSet0 = SplineSet.makeSpline(s, v2);
                }
                if(splineSet0 != null) {
                    splineSet0.setType(s);
                    this.mAttributesMap.put(s, splineSet0);
                }
            }
            ArrayList arrayList2 = this.mKeyList;
            if(arrayList2 != null) {
                for(Object object3: arrayList2) {
                    MotionKey motionKey2 = (MotionKey)object3;
                    if(motionKey2 instanceof MotionKeyAttributes) {
                        motionKey2.addValues(this.mAttributesMap);
                    }
                }
            }
            this.mStartPoint.addValues(this.mAttributesMap, 0);
            this.mEndPoint.addValues(this.mAttributesMap, 100);
            for(Object object4: this.mAttributesMap.keySet()) {
                String s2 = (String)object4;
                if(hashMap0.containsKey(s2)) {
                    Integer integer0 = (Integer)hashMap0.get(s2);
                    if(integer0 != null) {
                        v3 = (int)integer0;
                    }
                }
                else {
                    v3 = 0;
                }
                SplineSet splineSet1 = (SplineSet)this.mAttributesMap.get(s2);
                if(splineSet1 != null) {
                    splineSet1.setup(v3);
                }
            }
        }
        if(!hashSet0.isEmpty()) {
            if(this.mTimeCycleAttributesMap == null) {
                this.mTimeCycleAttributesMap = new HashMap();
            }
            for(Object object5: hashSet0) {
                String s3 = (String)object5;
                if(!this.mTimeCycleAttributesMap.containsKey(s3)) {
                    if(s3.startsWith("CUSTOM,")) {
                        CustomVar keyFrameArray$CustomVar1 = new CustomVar();
                        String s4 = s3.split(",")[1];
                        for(Object object6: this.mKeyList) {
                            MotionKey motionKey3 = (MotionKey)object6;
                            if(motionKey3.mCustom != null) {
                                CustomVariable customVariable1 = (CustomVariable)motionKey3.mCustom.get(s4);
                                if(customVariable1 != null) {
                                    keyFrameArray$CustomVar1.append(motionKey3.mFramePosition, customVariable1);
                                }
                            }
                        }
                        splineSet2 = SplineSet.makeCustomSplineSet(s3, keyFrameArray$CustomVar1);
                    }
                    else {
                        splineSet2 = SplineSet.makeSpline(s3, v2);
                    }
                    if(splineSet2 != null) {
                        splineSet2.setType(s3);
                    }
                }
            }
            ArrayList arrayList3 = this.mKeyList;
            if(arrayList3 != null) {
                for(Object object7: arrayList3) {
                    MotionKey motionKey4 = (MotionKey)object7;
                    if(motionKey4 instanceof MotionKeyTimeCycle) {
                        ((MotionKeyTimeCycle)motionKey4).addTimeValues(this.mTimeCycleAttributesMap);
                    }
                }
            }
            for(Object object8: this.mTimeCycleAttributesMap.keySet()) {
                String s5 = (String)object8;
                int v4 = hashMap0.containsKey(s5) ? ((int)(((Integer)hashMap0.get(s5)))) : 0;
                ((TimeCycleSplineSet)this.mTimeCycleAttributesMap.get(s5)).setup(v4);
            }
        }
        int v5 = this.mMotionPaths.size();
        MotionPaths[] arr_motionPaths = new MotionPaths[v5 + 2];
        arr_motionPaths[0] = this.mStartMotionPath;
        arr_motionPaths[v5 + 1] = this.mEndMotionPath;
        if(this.mMotionPaths.size() > 0 && this.mCurveFitType == MotionKey.UNSET) {
            this.mCurveFitType = 0;
        }
        int v6 = 1;
        for(Object object9: this.mMotionPaths) {
            arr_motionPaths[v6] = (MotionPaths)object9;
            ++v6;
        }
        HashSet hashSet3 = new HashSet();
        for(Object object10: this.mEndMotionPath.mCustomAttributes.keySet()) {
            String s6 = (String)object10;
            if(this.mStartMotionPath.mCustomAttributes.containsKey(s6) && !hashSet1.contains("CUSTOM," + s6)) {
                hashSet3.add(s6);
            }
        }
        String[] arr_s = (String[])hashSet3.toArray(new String[0]);
        this.mAttributeNames = arr_s;
        this.mAttributeInterpolatorCount = new int[arr_s.length];
        for(int v7 = 0; true; ++v7) {
            String[] arr_s1 = this.mAttributeNames;
            if(v7 >= arr_s1.length) {
                break;
            }
            String s7 = arr_s1[v7];
            this.mAttributeInterpolatorCount[v7] = 0;
            for(int v8 = 0; v8 < v5 + 2; ++v8) {
                if(arr_motionPaths[v8].mCustomAttributes.containsKey(s7)) {
                    CustomVariable customVariable2 = (CustomVariable)arr_motionPaths[v8].mCustomAttributes.get(s7);
                    if(customVariable2 != null) {
                        this.mAttributeInterpolatorCount[v7] += customVariable2.numberOfInterpolatedValues();
                        break;
                    }
                }
            }
        }
        boolean z = arr_motionPaths[0].mPathMotionArc != -1;
        int v9 = this.mAttributeNames.length + 18;
        boolean[] arr_z = new boolean[v9];
        for(int v10 = 1; v10 < v5 + 2; ++v10) {
            arr_motionPaths[v10].different(arr_motionPaths[v10 - 1], arr_z, this.mAttributeNames, z);
        }
        int v11 = 0;
        for(int v12 = 1; v12 < v9; ++v12) {
            if(arr_z[v12]) {
                ++v11;
            }
        }
        this.mInterpolateVariables = new int[v11];
        int v13 = Math.max(2, v11);
        this.mInterpolateData = new double[v13];
        this.mInterpolateVelocity = new double[v13];
        int v14 = 0;
        for(int v15 = 1; v15 < v9; ++v15) {
            if(arr_z[v15]) {
                this.mInterpolateVariables[v14] = v15;
                ++v14;
            }
        }
        double[][] arr2_f = new double[v5 + 2][this.mInterpolateVariables.length];
        double[] arr_f = new double[v5 + 2];
        for(int v16 = 0; v16 < v5 + 2; ++v16) {
            arr_motionPaths[v16].fillStandard(arr2_f[v16], this.mInterpolateVariables);
            arr_f[v16] = (double)arr_motionPaths[v16].mTime;
        }
        for(int v17 = 0; true; ++v17) {
            int[] arr_v = this.mInterpolateVariables;
            if(v17 >= arr_v.length) {
                break;
            }
            if(arr_v[v17] < MotionPaths.sNames.length) {
                String s8 = MotionPaths.sNames[this.mInterpolateVariables[v17]] + " [";
                for(int v18 = 0; v18 < v5 + 2; ++v18) {
                    s8 = s8 + arr2_f[v18][v17];
                }
            }
        }
        this.mSpline = new CurveFit[this.mAttributeNames.length + 1];
        int v19 = 0;
        while(true) {
            String[] arr_s2 = this.mAttributeNames;
            if(v19 >= arr_s2.length) {
                break;
            }
            String s9 = arr_s2[v19];
            int v21 = 0;
            double[] arr_f1 = null;
            Object[] arr_object = null;
            for(int v20 = 0; v20 < v5 + 2; ++v20) {
                if(arr_motionPaths[v20].hasCustomData(s9)) {
                    if(arr_object == null) {
                        arr_f1 = new double[v5 + 2];
                        int[] arr_v1 = {v5 + 2, arr_motionPaths[v20].getCustomDataCount(s9)};
                        arr_object = (double[][])Array.newInstance(Double.TYPE, arr_v1);
                    }
                    arr_f1[v21] = (double)arr_motionPaths[v20].mTime;
                    arr_motionPaths[v20].getCustomData(s9, ((double[])arr_object[v21]), 0);
                    ++v21;
                }
            }
            double[] arr_f2 = Arrays.copyOf(arr_f1, v21);
            double[][] arr2_f1 = (double[][])Arrays.copyOf(arr_object, v21);
            CurveFit[] arr_curveFit = this.mSpline;
            ++v19;
            arr_curveFit[v19] = CurveFit.get(this.mCurveFitType, arr_f2, arr2_f1);
        }
        CurveFit[] arr_curveFit1 = this.mSpline;
        arr_curveFit1[0] = CurveFit.get(this.mCurveFitType, arr_f, arr2_f);
        if(arr_motionPaths[0].mPathMotionArc != -1) {
            int[] arr_v2 = new int[v5 + 2];
            double[] arr_f3 = new double[v5 + 2];
            double[][] arr2_f2 = new double[v5 + 2][2];
            for(int v22 = 0; v22 < v5 + 2; ++v22) {
                arr_v2[v22] = arr_motionPaths[v22].mPathMotionArc;
                arr_f3[v22] = (double)arr_motionPaths[v22].mTime;
                arr2_f2[v22][0] = (double)arr_motionPaths[v22].mX;
                arr2_f2[v22][1] = (double)arr_motionPaths[v22].mY;
            }
            this.mArcSpline = CurveFit.getArc(arr_v2, arr_f3, arr2_f2);
        }
        this.mCycleMap = new HashMap();
        if(this.mKeyList != null) {
            float f1 = NaNf;
            for(Object object11: hashSet2) {
                String s10 = (String)object11;
                KeyCycleOscillator keyCycleOscillator0 = KeyCycleOscillator.makeWidgetCycle(s10);
                if(keyCycleOscillator0 != null) {
                    if(keyCycleOscillator0.variesByPath() && Float.isNaN(f1)) {
                        f1 = this.getPreCycleDistance();
                    }
                    keyCycleOscillator0.setType(s10);
                    this.mCycleMap.put(s10, keyCycleOscillator0);
                }
            }
            for(Object object12: this.mKeyList) {
                MotionKey motionKey5 = (MotionKey)object12;
                if(motionKey5 instanceof MotionKeyCycle) {
                    ((MotionKeyCycle)motionKey5).addCycleValues(this.mCycleMap);
                }
            }
            for(Object object13: this.mCycleMap.values()) {
                ((KeyCycleOscillator)object13).setup(f1);
            }
        }
    }

    private void setupRelative() {
        Motion motion0 = this.mRelativeMotion;
        if(motion0 == null) {
            return;
        }
        this.mStartMotionPath.setupRelative(motion0, motion0.mStartMotionPath);
        this.mEndMotionPath.setupRelative(this.mRelativeMotion, this.mRelativeMotion.mEndMotionPath);
    }

    public void setupRelative(Motion motion0) {
        this.mRelativeMotion = motion0;
    }

    @Override
    public String toString() {
        return " start: x: " + this.mStartMotionPath.mX + " y: " + this.mStartMotionPath.mY + " end: x: " + this.mEndMotionPath.mX + " y: " + this.mEndMotionPath.mY;
    }
}

