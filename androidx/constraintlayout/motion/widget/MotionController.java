package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.util.SparseArray;
import android.view.View.MeasureSpec;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import androidx.constraintlayout.core.motion.utils.CurveFit;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.core.motion.utils.KeyCache;
import androidx.constraintlayout.core.motion.utils.SplineSet;
import androidx.constraintlayout.core.motion.utils.VelocityMatrix;
import androidx.constraintlayout.motion.utils.CustomSupport;
import androidx.constraintlayout.motion.utils.ViewOscillator.PathRotateSet;
import androidx.constraintlayout.motion.utils.ViewOscillator;
import androidx.constraintlayout.motion.utils.ViewSpline;
import androidx.constraintlayout.motion.utils.ViewState;
import androidx.constraintlayout.motion.utils.ViewTimeCycle.PathRotate;
import androidx.constraintlayout.motion.utils.ViewTimeCycle;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams;
import androidx.constraintlayout.widget.ConstraintSet.Constraint;
import androidx.constraintlayout.widget.ConstraintSet;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class MotionController {
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
    boolean mForceMeasure;
    int mId;
    private double[] mInterpolateData;
    private int[] mInterpolateVariables;
    private double[] mInterpolateVelocity;
    private ArrayList mKeyList;
    private KeyTrigger[] mKeyTriggers;
    private int mMaxDimension;
    private ArrayList mMotionPaths;
    float mMotionStagger;
    private boolean mNoMovement;
    private int mPathMotionArc;
    private Interpolator mQuantizeMotionInterpolator;
    private float mQuantizeMotionPhase;
    private int mQuantizeMotionSteps;
    private CurveFit[] mSpline;
    float mStaggerOffset;
    float mStaggerScale;
    private MotionPaths mStartMotionPath;
    private MotionConstrainedPoint mStartPoint;
    Rect mTempRect;
    private HashMap mTimeCycleAttributesMap;
    private int mTransformPivotTarget;
    private View mTransformPivotView;
    private float[] mValuesBuff;
    private float[] mVelocity;
    View mView;

    MotionController(View view0) {
        this.mTempRect = new Rect();
        this.mForceMeasure = false;
        this.mCurveFitType = -1;
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
        this.mPathMotionArc = Key.UNSET;
        this.mTransformPivotTarget = Key.UNSET;
        this.mTransformPivotView = null;
        this.mQuantizeMotionSteps = Key.UNSET;
        this.mQuantizeMotionPhase = NaNf;
        this.mQuantizeMotionInterpolator = null;
        this.mNoMovement = false;
        this.setView(view0);
    }

    public void addKey(Key key0) {
        this.mKeyList.add(key0);
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
            ViewOscillator viewOscillator0 = (ViewOscillator)hashMap2.get("translationX");
        }
        HashMap hashMap3 = this.mCycleMap;
        if(hashMap3 != null) {
            ViewOscillator viewOscillator1 = (ViewOscillator)hashMap3.get("translationY");
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

    int buildKeyFrames(float[] arr_f, int[] arr_v) {
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
                this.mStartMotionPath.getCenter(arr_f1[v1], this.mInterpolateVariables, this.mInterpolateData, arr_f, v2);
                v2 += 2;
            }
            return v2 / 2;
        }
        return 0;
    }

    void buildPath(float[] arr_f, int v) {
        ViewOscillator viewOscillator0 = null;
        SplineSet splineSet0 = this.mAttributesMap == null ? null : ((SplineSet)this.mAttributesMap.get("translationX"));
        SplineSet splineSet1 = this.mAttributesMap == null ? null : ((SplineSet)this.mAttributesMap.get("translationY"));
        ViewOscillator viewOscillator1 = this.mCycleMap == null ? null : ((ViewOscillator)this.mCycleMap.get("translationX"));
        HashMap hashMap0 = this.mCycleMap;
        if(hashMap0 != null) {
            viewOscillator0 = (ViewOscillator)hashMap0.get("translationY");
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
            if(viewOscillator1 != null) {
                arr_f[v1 * 2] += viewOscillator1.get(f);
            }
            else if(splineSet0 != null) {
                arr_f[v1 * 2] += splineSet0.get(f);
            }
            if(viewOscillator0 != null) {
                int v2 = v1 * 2 + 1;
                arr_f[v2] += viewOscillator0.get(f);
            }
            else if(splineSet1 != null) {
                int v3 = v1 * 2 + 1;
                arr_f[v3] += splineSet1.get(f);
            }
        }
    }

    void buildRect(float f, float[] arr_f, int v) {
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
        if("button".equals(Debug.getName(this.mView)) && this.mKeyTriggers != null) {
            for(int v = 0; true; ++v) {
                KeyTrigger[] arr_keyTrigger = this.mKeyTriggers;
                if(v >= arr_keyTrigger.length) {
                    break;
                }
                arr_keyTrigger[v].conditionallyFire((z ? -100.0f : 100.0f), this.mView);
            }
        }
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

    public int getAnimateRelativeTo() {
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

    void getDpDt(float f, float f1, float f2, float[] arr_f) {
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

    private static Interpolator getInterpolator(Context context0, int v, String s, int v1) {
        if(v != -2) {
            switch(v) {
                case -1: {
                    return new Interpolator() {
                        @Override  // android.animation.TimeInterpolator
                        public float getInterpolation(float f) {
                            return (float)Easing.getInterpolator(s).get(((double)f));
                        }
                    };
                }
                case 0: {
                    return new AccelerateDecelerateInterpolator();
                }
                case 1: {
                    return new AccelerateInterpolator();
                }
                case 2: {
                    return new DecelerateInterpolator();
                }
                case 4: {
                    return new BounceInterpolator();
                }
                case 5: {
                    return new OvershootInterpolator();
                }
                default: {
                    return null;
                }
            }
        }
        return AnimationUtils.loadInterpolator(context0, v1);
    }

    MotionPaths getKeyFrame(int v) {
        return (MotionPaths)this.mMotionPaths.get(v);
    }

    public int getKeyFrameInfo(int v, int[] arr_v) {
        float[] arr_f = new float[2];
        int v1 = 0;
        int v2 = 0;
        for(Object object0: this.mKeyList) {
            Key key0 = (Key)object0;
            if(key0.mType == v || v != -1) {
                arr_v[v2] = 0;
                arr_v[v2 + 1] = key0.mType;
                arr_v[v2 + 2] = key0.mFramePosition;
                float f = ((float)key0.mFramePosition) / 100.0f;
                this.mSpline[0].getPos(((double)f), this.mInterpolateData);
                this.mStartMotionPath.getCenter(((double)f), this.mInterpolateVariables, this.mInterpolateData, arr_f, 0);
                arr_v[v2 + 3] = Float.floatToIntBits(arr_f[0]);
                int v3 = v2 + 4;
                arr_v[v3] = Float.floatToIntBits(arr_f[1]);
                if(key0 instanceof KeyPosition) {
                    arr_v[v2 + 5] = ((KeyPosition)key0).mPositionType;
                    arr_v[v2 + 6] = Float.floatToIntBits(((KeyPosition)key0).mPercentX);
                    v3 = v2 + 7;
                    arr_v[v3] = Float.floatToIntBits(((KeyPosition)key0).mPercentY);
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
            arr_v[v] = ((Key)object0).mFramePosition + ((Key)object0).mType * 1000;
            float f = ((float)((Key)object0).mFramePosition) / 100.0f;
            this.mSpline[0].getPos(((double)f), this.mInterpolateData);
            this.mStartMotionPath.getCenter(((double)f), this.mInterpolateVariables, this.mInterpolateData, arr_f, v1);
            v1 += 2;
            ++v;
        }
        return v;
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

    KeyPositionBase getPositionKeyframe(int v, int v1, float f, float f1) {
        RectF rectF0 = new RectF();
        rectF0.left = this.mStartMotionPath.mX;
        rectF0.top = this.mStartMotionPath.mY;
        rectF0.right = rectF0.left + this.mStartMotionPath.mWidth;
        rectF0.bottom = rectF0.top + this.mStartMotionPath.mHeight;
        RectF rectF1 = new RectF();
        rectF1.left = this.mEndMotionPath.mX;
        rectF1.top = this.mEndMotionPath.mY;
        rectF1.right = rectF1.left + this.mEndMotionPath.mWidth;
        rectF1.bottom = rectF1.top + this.mEndMotionPath.mHeight;
        for(Object object0: this.mKeyList) {
            Key key0 = (Key)object0;
            if(key0 instanceof KeyPositionBase && ((KeyPositionBase)key0).intersects(v, v1, rectF0, rectF1, f, f1)) {
                return (KeyPositionBase)key0;
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
        ViewOscillator viewOscillator0 = null;
        SplineSet splineSet0 = this.mAttributesMap == null ? null : ((SplineSet)this.mAttributesMap.get("translationX"));
        SplineSet splineSet1 = this.mAttributesMap == null ? null : ((SplineSet)this.mAttributesMap.get("translationY"));
        SplineSet splineSet2 = this.mAttributesMap == null ? null : ((SplineSet)this.mAttributesMap.get("rotation"));
        SplineSet splineSet3 = this.mAttributesMap == null ? null : ((SplineSet)this.mAttributesMap.get("scaleX"));
        SplineSet splineSet4 = this.mAttributesMap == null ? null : ((SplineSet)this.mAttributesMap.get("scaleY"));
        ViewOscillator viewOscillator1 = this.mCycleMap == null ? null : ((ViewOscillator)this.mCycleMap.get("translationX"));
        ViewOscillator viewOscillator2 = this.mCycleMap == null ? null : ((ViewOscillator)this.mCycleMap.get("translationY"));
        ViewOscillator viewOscillator3 = this.mCycleMap == null ? null : ((ViewOscillator)this.mCycleMap.get("rotation"));
        ViewOscillator viewOscillator4 = this.mCycleMap == null ? null : ((ViewOscillator)this.mCycleMap.get("scaleX"));
        HashMap hashMap0 = this.mCycleMap;
        if(hashMap0 != null) {
            viewOscillator0 = (ViewOscillator)hashMap0.get("scaleY");
        }
        VelocityMatrix velocityMatrix0 = new VelocityMatrix();
        velocityMatrix0.clear();
        velocityMatrix0.setRotationVelocity(splineSet2, f3);
        velocityMatrix0.setTranslationVelocity(splineSet0, splineSet1, f3);
        velocityMatrix0.setScaleVelocity(splineSet3, splineSet4, f3);
        velocityMatrix0.setRotationVelocity(viewOscillator3, f3);
        velocityMatrix0.setTranslationVelocity(viewOscillator1, viewOscillator2, f3);
        velocityMatrix0.setScaleVelocity(viewOscillator4, viewOscillator0, f3);
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
        velocityMatrix0.setRotationVelocity(viewOscillator3, f3);
        velocityMatrix0.setTranslationVelocity(viewOscillator1, viewOscillator2, f3);
        velocityMatrix0.setScaleVelocity(viewOscillator4, viewOscillator0, f3);
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

    public View getView() {
        return this.mView;
    }

    private void insertKey(MotionPaths motionPaths0) {
        int v = Collections.binarySearch(this.mMotionPaths, motionPaths0);
        if(v == 0) {
            Log.e("MotionController", " KeyPath position \"" + motionPaths0.mPosition + "\" outside of range");
        }
        this.mMotionPaths.add(-v - 1, motionPaths0);
    }

    boolean interpolate(View view0, float f, long v, KeyCache keyCache0) {
        double f6;
        PathRotate viewTimeCycle$PathRotate1;
        boolean z1;
        float f5;
        float f1 = this.getAdjustedPosition(f, null);
        if(this.mQuantizeMotionSteps != Key.UNSET) {
            float f2 = 1.0f / ((float)this.mQuantizeMotionSteps);
            float f3 = ((float)Math.floor(f1 / f2)) * f2;
            float f4 = f1 % f2 / f2;
            if(!Float.isNaN(this.mQuantizeMotionPhase)) {
                f4 = (f4 + this.mQuantizeMotionPhase) % 1.0f;
            }
            Interpolator interpolator0 = this.mQuantizeMotionInterpolator;
            if(interpolator0 == null) {
                f5 = ((double)f4) > 0.5 ? 1.0f : 0.0f;
            }
            else {
                f5 = interpolator0.getInterpolation(f4);
            }
            f1 = f5 * f2 + f3;
        }
        HashMap hashMap0 = this.mAttributesMap;
        if(hashMap0 != null) {
            for(Object object0: hashMap0.values()) {
                ((ViewSpline)object0).setProperty(view0, f1);
            }
        }
        HashMap hashMap1 = this.mTimeCycleAttributesMap;
        if(hashMap1 == null) {
            viewTimeCycle$PathRotate1 = null;
            z1 = false;
        }
        else {
            PathRotate viewTimeCycle$PathRotate0 = null;
            boolean z = false;
            for(Object object1: hashMap1.values()) {
                ViewTimeCycle viewTimeCycle0 = (ViewTimeCycle)object1;
                if(viewTimeCycle0 instanceof PathRotate) {
                    viewTimeCycle$PathRotate0 = (PathRotate)viewTimeCycle0;
                }
                else {
                    z |= viewTimeCycle0.setProperty(view0, f1, v, keyCache0);
                }
            }
            z1 = z;
            viewTimeCycle$PathRotate1 = viewTimeCycle$PathRotate0;
        }
        CurveFit[] arr_curveFit = this.mSpline;
        if(arr_curveFit == null) {
            float f9 = this.mStartMotionPath.mX + (this.mEndMotionPath.mX - this.mStartMotionPath.mX) * f1 + 0.5f;
            float f10 = this.mStartMotionPath.mY + (this.mEndMotionPath.mY - this.mStartMotionPath.mY) * f1 + 0.5f;
            int v7 = (int)(f9 + (this.mStartMotionPath.mWidth + (this.mEndMotionPath.mWidth - this.mStartMotionPath.mWidth) * f1));
            int v8 = (int)(f10 + (this.mStartMotionPath.mHeight + (this.mEndMotionPath.mHeight - this.mStartMotionPath.mHeight) * f1));
            if(this.mEndMotionPath.mWidth != this.mStartMotionPath.mWidth || this.mEndMotionPath.mHeight != this.mStartMotionPath.mHeight || this.mForceMeasure) {
                view0.measure(View.MeasureSpec.makeMeasureSpec(v7 - ((int)f9), 0x40000000), View.MeasureSpec.makeMeasureSpec(v8 - ((int)f10), 0x40000000));
                this.mForceMeasure = false;
            }
            view0.layout(((int)f9), ((int)f10), v7, v8);
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
            if(this.mNoMovement) {
                f6 = (double)f1;
            }
            else {
                f6 = (double)f1;
                this.mStartMotionPath.setView(f1, view0, this.mInterpolateVariables, this.mInterpolateData, this.mInterpolateVelocity, null, this.mForceMeasure);
                this.mForceMeasure = false;
            }
            if(this.mTransformPivotTarget != Key.UNSET) {
                if(this.mTransformPivotView == null) {
                    this.mTransformPivotView = ((View)view0.getParent()).findViewById(this.mTransformPivotTarget);
                }
                View view1 = this.mTransformPivotView;
                if(view1 != null) {
                    int v1 = view1.getTop();
                    int v2 = this.mTransformPivotView.getBottom();
                    int v3 = this.mTransformPivotView.getLeft();
                    int v4 = this.mTransformPivotView.getRight();
                    if(view0.getRight() - view0.getLeft() > 0 && view0.getBottom() - view0.getTop() > 0) {
                        float f7 = ((float)(v3 + v4)) / 2.0f - ((float)view0.getLeft());
                        float f8 = ((float)(v1 + v2)) / 2.0f - ((float)view0.getTop());
                        view0.setPivotX(f7);
                        view0.setPivotY(f8);
                    }
                }
            }
            HashMap hashMap2 = this.mAttributesMap;
            if(hashMap2 != null) {
                for(Object object2: hashMap2.values()) {
                    SplineSet splineSet0 = (SplineSet)object2;
                    if(splineSet0 instanceof androidx.constraintlayout.motion.utils.ViewSpline.PathRotate) {
                        double[] arr_f1 = this.mInterpolateVelocity;
                        if(arr_f1.length > 1) {
                            ((androidx.constraintlayout.motion.utils.ViewSpline.PathRotate)splineSet0).setPathRotate(view0, f1, arr_f1[0], arr_f1[1]);
                        }
                    }
                }
            }
            if(viewTimeCycle$PathRotate1 != null) {
                z1 |= viewTimeCycle$PathRotate1.setPathRotate(view0, keyCache0, f1, v, this.mInterpolateVelocity[0], this.mInterpolateVelocity[1]);
            }
            for(int v5 = 1; true; ++v5) {
                CurveFit[] arr_curveFit1 = this.mSpline;
                if(v5 >= arr_curveFit1.length) {
                    break;
                }
                arr_curveFit1[v5].getPos(f6, this.mValuesBuff);
                CustomSupport.setInterpolatedValue(((ConstraintAttribute)this.mStartMotionPath.mAttributes.get(this.mAttributeNames[v5 - 1])), view0, this.mValuesBuff);
            }
            if(this.mStartPoint.mVisibilityMode == 0) {
                if(f1 <= 0.0f) {
                    view0.setVisibility(this.mStartPoint.mVisibility);
                }
                else if(f1 >= 1.0f) {
                    view0.setVisibility(this.mEndPoint.mVisibility);
                }
                else if(this.mEndPoint.mVisibility != this.mStartPoint.mVisibility) {
                    view0.setVisibility(0);
                }
            }
            if(this.mKeyTriggers != null) {
                for(int v6 = 0; true; ++v6) {
                    KeyTrigger[] arr_keyTrigger = this.mKeyTriggers;
                    if(v6 >= arr_keyTrigger.length) {
                        break;
                    }
                    arr_keyTrigger[v6].conditionallyFire(f1, view0);
                }
            }
        }
        HashMap hashMap3 = this.mCycleMap;
        if(hashMap3 != null) {
            for(Object object3: hashMap3.values()) {
                ViewOscillator viewOscillator0 = (ViewOscillator)object3;
                if(viewOscillator0 instanceof PathRotateSet) {
                    ((PathRotateSet)viewOscillator0).setPathRotate(view0, f1, this.mInterpolateVelocity[0], this.mInterpolateVelocity[1]);
                }
                else {
                    viewOscillator0.setProperty(view0, f1);
                }
            }
        }
        return z1;
    }

    String name() {
        return this.mView.getContext().getResources().getResourceEntryName(this.mView.getId());
    }

    void positionKeyframe(View view0, KeyPositionBase keyPositionBase0, float f, float f1, String[] arr_s, float[] arr_f) {
        RectF rectF0 = new RectF();
        rectF0.left = this.mStartMotionPath.mX;
        rectF0.top = this.mStartMotionPath.mY;
        rectF0.right = rectF0.left + this.mStartMotionPath.mWidth;
        rectF0.bottom = rectF0.top + this.mStartMotionPath.mHeight;
        RectF rectF1 = new RectF();
        rectF1.left = this.mEndMotionPath.mX;
        rectF1.top = this.mEndMotionPath.mY;
        rectF1.right = rectF1.left + this.mEndMotionPath.mWidth;
        rectF1.bottom = rectF1.top + this.mEndMotionPath.mHeight;
        keyPositionBase0.positionAttributes(view0, rectF0, rectF1, f, f1, arr_s, arr_f);
    }

    private void readView(MotionPaths motionPaths0) {
        motionPaths0.setBounds(((float)(((int)this.mView.getX()))), ((float)(((int)this.mView.getY()))), ((float)this.mView.getWidth()), ((float)this.mView.getHeight()));
    }

    public void remeasure() {
        this.mForceMeasure = true;
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

    void setBothStates(View view0) {
        this.mStartMotionPath.mTime = 0.0f;
        this.mStartMotionPath.mPosition = 0.0f;
        this.mNoMovement = true;
        this.mStartMotionPath.setBounds(view0.getX(), view0.getY(), ((float)view0.getWidth()), ((float)view0.getHeight()));
        this.mEndMotionPath.setBounds(view0.getX(), view0.getY(), ((float)view0.getWidth()), ((float)view0.getHeight()));
        this.mStartPoint.setState(view0);
        this.mEndPoint.setState(view0);
    }

    public void setDrawPath(int v) {
        this.mStartMotionPath.mDrawPath = v;
    }

    void setEndState(Rect rect0, ConstraintSet constraintSet0, int v, int v1) {
        int v2 = constraintSet0.mRotate;
        if(v2 != 0) {
            this.rotate(rect0, this.mTempRect, v2, v, v1);
            rect0 = this.mTempRect;
        }
        this.mEndMotionPath.mTime = 1.0f;
        this.mEndMotionPath.mPosition = 1.0f;
        this.readView(this.mEndMotionPath);
        this.mEndMotionPath.setBounds(((float)rect0.left), ((float)rect0.top), ((float)rect0.width()), ((float)rect0.height()));
        this.mEndMotionPath.applyParameters(constraintSet0.getParameters(this.mId));
        this.mEndPoint.setState(rect0, constraintSet0, v2, this.mId);
    }

    public void setPathMotionArc(int v) {
        this.mPathMotionArc = v;
    }

    void setStartCurrentState(View view0) {
        this.mStartMotionPath.mTime = 0.0f;
        this.mStartMotionPath.mPosition = 0.0f;
        this.mStartMotionPath.setBounds(view0.getX(), view0.getY(), ((float)view0.getWidth()), ((float)view0.getHeight()));
        this.mStartPoint.setState(view0);
    }

    void setStartState(Rect rect0, ConstraintSet constraintSet0, int v, int v1) {
        int v2 = constraintSet0.mRotate;
        if(v2 != 0) {
            this.rotate(rect0, this.mTempRect, v2, v, v1);
        }
        this.mStartMotionPath.mTime = 0.0f;
        this.mStartMotionPath.mPosition = 0.0f;
        this.readView(this.mStartMotionPath);
        this.mStartMotionPath.setBounds(((float)rect0.left), ((float)rect0.top), ((float)rect0.width()), ((float)rect0.height()));
        Constraint constraintSet$Constraint0 = constraintSet0.getParameters(this.mId);
        this.mStartMotionPath.applyParameters(constraintSet$Constraint0);
        this.mMotionStagger = constraintSet$Constraint0.motion.mMotionStagger;
        this.mStartPoint.setState(rect0, constraintSet0, v2, this.mId);
        this.mTransformPivotTarget = constraintSet$Constraint0.transform.transformPivotTarget;
        this.mQuantizeMotionSteps = constraintSet$Constraint0.motion.mQuantizeMotionSteps;
        this.mQuantizeMotionPhase = constraintSet$Constraint0.motion.mQuantizeMotionPhase;
        this.mQuantizeMotionInterpolator = MotionController.getInterpolator(this.mView.getContext(), constraintSet$Constraint0.motion.mQuantizeInterpolatorType, constraintSet$Constraint0.motion.mQuantizeInterpolatorString, constraintSet$Constraint0.motion.mQuantizeInterpolatorID);
    }

    public void setStartState(ViewState viewState0, View view0, int v, int v1, int v2) {
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
        this.mStartPoint.setState(rect0, view0, v, viewState0.rotation);
    }

    public void setTransformPivotTarget(int v) {
        this.mTransformPivotTarget = v;
        this.mTransformPivotView = null;
    }

    public void setView(View view0) {
        this.mView = view0;
        this.mId = view0.getId();
        ViewGroup.LayoutParams viewGroup$LayoutParams0 = view0.getLayoutParams();
        if(viewGroup$LayoutParams0 instanceof LayoutParams) {
            this.mConstraintTag = ((LayoutParams)viewGroup$LayoutParams0).getConstraintTag();
        }
    }

    public void setup(int v, int v1, float f, long v2) {
        int v22;
        ViewTimeCycle viewTimeCycle0;
        int v3;
        ViewSpline viewSpline0;
        ArrayList arrayList1;
        new HashSet();
        HashSet hashSet0 = new HashSet();
        HashSet hashSet1 = new HashSet();
        HashSet hashSet2 = new HashSet();
        HashMap hashMap0 = new HashMap();
        if(this.mPathMotionArc != Key.UNSET) {
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
                Key key0 = (Key)object0;
                if(key0 instanceof KeyPosition) {
                    this.insertKey(new MotionPaths(v, v1, ((KeyPosition)key0), this.mStartMotionPath, this.mEndMotionPath));
                    if(((KeyPosition)key0).mCurveFit == Key.UNSET) {
                        continue;
                    }
                    this.mCurveFitType = ((KeyPosition)key0).mCurveFit;
                }
                else if(key0 instanceof KeyCycle) {
                    key0.getAttributeNames(hashSet2);
                }
                else if(key0 instanceof KeyTimeCycle) {
                    key0.getAttributeNames(hashSet0);
                }
                else if(key0 instanceof KeyTrigger) {
                    if(arrayList1 == null) {
                        arrayList1 = new ArrayList();
                    }
                    arrayList1.add(((KeyTrigger)key0));
                }
                else {
                    key0.setInterpolation(hashMap0);
                    key0.getAttributeNames(hashSet1);
                }
            }
        }
        if(arrayList1 != null) {
            this.mKeyTriggers = (KeyTrigger[])arrayList1.toArray(new KeyTrigger[0]);
        }
        if(!hashSet1.isEmpty()) {
            this.mAttributesMap = new HashMap();
            for(Object object1: hashSet1) {
                String s = (String)object1;
                if(s.startsWith("CUSTOM,")) {
                    SparseArray sparseArray0 = new SparseArray();
                    String s1 = s.split(",")[1];
                    for(Object object2: this.mKeyList) {
                        Key key1 = (Key)object2;
                        if(key1.mCustomConstraints != null) {
                            ConstraintAttribute constraintAttribute0 = (ConstraintAttribute)key1.mCustomConstraints.get(s1);
                            if(constraintAttribute0 != null) {
                                sparseArray0.append(key1.mFramePosition, constraintAttribute0);
                            }
                        }
                    }
                    viewSpline0 = ViewSpline.makeCustomSpline(s, sparseArray0);
                }
                else {
                    viewSpline0 = ViewSpline.makeSpline(s);
                }
                if(viewSpline0 != null) {
                    viewSpline0.setType(s);
                    this.mAttributesMap.put(s, viewSpline0);
                }
            }
            ArrayList arrayList2 = this.mKeyList;
            if(arrayList2 != null) {
                for(Object object3: arrayList2) {
                    Key key2 = (Key)object3;
                    if(key2 instanceof KeyAttributes) {
                        key2.addValues(this.mAttributesMap);
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
                SplineSet splineSet0 = (SplineSet)this.mAttributesMap.get(s2);
                if(splineSet0 != null) {
                    splineSet0.setup(v3);
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
                        SparseArray sparseArray1 = new SparseArray();
                        String s4 = s3.split(",")[1];
                        for(Object object6: this.mKeyList) {
                            Key key3 = (Key)object6;
                            if(key3.mCustomConstraints != null) {
                                ConstraintAttribute constraintAttribute1 = (ConstraintAttribute)key3.mCustomConstraints.get(s4);
                                if(constraintAttribute1 != null) {
                                    sparseArray1.append(key3.mFramePosition, constraintAttribute1);
                                }
                            }
                        }
                        viewTimeCycle0 = ViewTimeCycle.makeCustomSpline(s3, sparseArray1);
                    }
                    else {
                        viewTimeCycle0 = ViewTimeCycle.makeSpline(s3, v2);
                    }
                    if(viewTimeCycle0 != null) {
                        viewTimeCycle0.setType(s3);
                        this.mTimeCycleAttributesMap.put(s3, viewTimeCycle0);
                    }
                }
            }
            ArrayList arrayList3 = this.mKeyList;
            if(arrayList3 != null) {
                for(Object object7: arrayList3) {
                    Key key4 = (Key)object7;
                    if(key4 instanceof KeyTimeCycle) {
                        ((KeyTimeCycle)key4).addTimeValues(this.mTimeCycleAttributesMap);
                    }
                }
            }
            for(Object object8: this.mTimeCycleAttributesMap.keySet()) {
                String s5 = (String)object8;
                int v4 = hashMap0.containsKey(s5) ? ((int)(((Integer)hashMap0.get(s5)))) : 0;
                ((ViewTimeCycle)this.mTimeCycleAttributesMap.get(s5)).setup(v4);
            }
        }
        int v5 = this.mMotionPaths.size();
        MotionPaths[] arr_motionPaths = new MotionPaths[v5 + 2];
        arr_motionPaths[0] = this.mStartMotionPath;
        arr_motionPaths[v5 + 1] = this.mEndMotionPath;
        if(this.mMotionPaths.size() > 0 && this.mCurveFitType == -1) {
            this.mCurveFitType = 0;
        }
        int v6 = 1;
        for(Object object9: this.mMotionPaths) {
            arr_motionPaths[v6] = (MotionPaths)object9;
            ++v6;
        }
        HashSet hashSet3 = new HashSet();
        for(Object object10: this.mEndMotionPath.mAttributes.keySet()) {
            String s6 = (String)object10;
            if(this.mStartMotionPath.mAttributes.containsKey(s6) && !hashSet1.contains("CUSTOM," + s6)) {
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
                if(arr_motionPaths[v8].mAttributes.containsKey(s7)) {
                    ConstraintAttribute constraintAttribute2 = (ConstraintAttribute)arr_motionPaths[v8].mAttributes.get(s7);
                    if(constraintAttribute2 != null) {
                        this.mAttributeInterpolatorCount[v7] += constraintAttribute2.numberOfInterpolatedValues();
                        break;
                    }
                }
            }
        }
        boolean z = arr_motionPaths[0].mPathMotionArc != Key.UNSET;
        int v9 = this.mAttributeNames.length + 18;
        boolean[] arr_z = new boolean[v9];
        for(int v10 = 1; v10 < v5 + 2; ++v10) {
            arr_motionPaths[v10].different(arr_motionPaths[v10 - 1], arr_z, this.mAttributeNames, z);
        }
        int v12 = 0;
        for(int v11 = 1; v11 < v9; ++v11) {
            if(arr_z[v11]) {
                ++v12;
            }
        }
        this.mInterpolateVariables = new int[v12];
        int v13 = Math.max(2, v12);
        this.mInterpolateData = new double[v13];
        this.mInterpolateVelocity = new double[v13];
        int v15 = 0;
        for(int v14 = 1; v14 < v9; ++v14) {
            if(arr_z[v14]) {
                this.mInterpolateVariables[v15] = v14;
                ++v15;
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
            double[] arr_f1 = null;
            int v21 = 0;
            Object[] arr_object = null;
            for(int v20 = 0; v20 < v5 + 2; v20 = v22 + 1) {
                if(arr_motionPaths[v20].hasCustomData(s9)) {
                    if(arr_object == null) {
                        arr_f1 = new double[v5 + 2];
                        int[] arr_v1 = {v5 + 2, arr_motionPaths[v20].getCustomDataCount(s9)};
                        arr_object = (double[][])Array.newInstance(Double.TYPE, arr_v1);
                    }
                    v22 = v20;
                    arr_f1[v21] = (double)arr_motionPaths[v20].mTime;
                    arr_motionPaths[v22].getCustomData(s9, ((double[])arr_object[v21]), 0);
                    ++v21;
                }
                else {
                    v22 = v20;
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
        if(arr_motionPaths[0].mPathMotionArc != Key.UNSET) {
            int[] arr_v2 = new int[v5 + 2];
            double[] arr_f3 = new double[v5 + 2];
            double[][] arr2_f2 = new double[v5 + 2][2];
            for(int v23 = 0; v23 < v5 + 2; ++v23) {
                arr_v2[v23] = arr_motionPaths[v23].mPathMotionArc;
                arr_f3[v23] = (double)arr_motionPaths[v23].mTime;
                arr2_f2[v23][0] = (double)arr_motionPaths[v23].mX;
                arr2_f2[v23][1] = (double)arr_motionPaths[v23].mY;
            }
            this.mArcSpline = CurveFit.getArc(arr_v2, arr_f3, arr2_f2);
        }
        this.mCycleMap = new HashMap();
        if(this.mKeyList != null) {
            float f1 = NaNf;
            for(Object object11: hashSet2) {
                String s10 = (String)object11;
                ViewOscillator viewOscillator0 = ViewOscillator.makeSpline(s10);
                if(viewOscillator0 != null) {
                    if(viewOscillator0.variesByPath() && Float.isNaN(f1)) {
                        f1 = this.getPreCycleDistance();
                    }
                    viewOscillator0.setType(s10);
                    this.mCycleMap.put(s10, viewOscillator0);
                }
            }
            for(Object object12: this.mKeyList) {
                Key key5 = (Key)object12;
                if(key5 instanceof KeyCycle) {
                    ((KeyCycle)key5).addCycleValues(this.mCycleMap);
                }
            }
            for(Object object13: this.mCycleMap.values()) {
                ((ViewOscillator)object13).setup(f1);
            }
        }
    }

    public void setupRelative(MotionController motionController0) {
        this.mStartMotionPath.setupRelative(motionController0, motionController0.mStartMotionPath);
        this.mEndMotionPath.setupRelative(motionController0, motionController0.mEndMotionPath);
    }

    @Override
    public String toString() {
        return " start: x: " + this.mStartMotionPath.mX + " y: " + this.mStartMotionPath.mY + " end: x: " + this.mEndMotionPath.mX + " y: " + this.mEndMotionPath.mY;
    }
}

