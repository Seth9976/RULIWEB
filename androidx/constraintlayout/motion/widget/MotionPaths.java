package androidx.constraintlayout.motion.widget;

import android.view.View.MeasureSpec;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintSet.Constraint;
import java.util.Arrays;
import java.util.LinkedHashMap;

class MotionPaths implements Comparable {
    static final int CARTESIAN = 0;
    public static final boolean DEBUG = false;
    static final int OFF_HEIGHT = 4;
    static final int OFF_PATH_ROTATE = 5;
    static final int OFF_POSITION = 0;
    static final int OFF_WIDTH = 3;
    static final int OFF_X = 1;
    static final int OFF_Y = 2;
    public static final boolean OLD_WAY = false;
    static final int PERPENDICULAR = 1;
    static final int SCREEN = 2;
    public static final String TAG = "MotionPaths";
    int mAnimateCircleAngleTo;
    int mAnimateRelativeTo;
    LinkedHashMap mAttributes;
    int mDrawPath;
    float mHeight;
    Easing mKeyFrameEasing;
    int mMode;
    int mPathMotionArc;
    float mPathRotate;
    float mPosition;
    float mProgress;
    float mRelativeAngle;
    MotionController mRelativeToController;
    double[] mTempDelta;
    double[] mTempValue;
    float mTime;
    float mWidth;
    float mX;
    float mY;
    static String[] sNames;

    static {
        MotionPaths.sNames = new String[]{"position", "x", "y", "width", "height", "pathRotate"};
    }

    MotionPaths() {
        this.mDrawPath = 0;
        this.mPathRotate = NaNf;
        this.mProgress = NaNf;
        this.mPathMotionArc = Key.UNSET;
        this.mAnimateRelativeTo = Key.UNSET;
        this.mRelativeAngle = NaNf;
        this.mRelativeToController = null;
        this.mAttributes = new LinkedHashMap();
        this.mMode = 0;
        this.mTempValue = new double[18];
        this.mTempDelta = new double[18];
    }

    MotionPaths(int v, int v1, KeyPosition keyPosition0, MotionPaths motionPaths0, MotionPaths motionPaths1) {
        this.mDrawPath = 0;
        this.mPathRotate = NaNf;
        this.mProgress = NaNf;
        this.mPathMotionArc = Key.UNSET;
        this.mAnimateRelativeTo = Key.UNSET;
        this.mRelativeAngle = NaNf;
        this.mRelativeToController = null;
        this.mAttributes = new LinkedHashMap();
        this.mMode = 0;
        this.mTempValue = new double[18];
        this.mTempDelta = new double[18];
        if(motionPaths0.mAnimateRelativeTo != Key.UNSET) {
            this.initPolar(v, v1, keyPosition0, motionPaths0, motionPaths1);
            return;
        }
        switch(keyPosition0.mPositionType) {
            case 1: {
                this.initPath(keyPosition0, motionPaths0, motionPaths1);
                return;
            }
            case 2: {
                this.initScreen(v, v1, keyPosition0, motionPaths0, motionPaths1);
                return;
            }
            case 3: {
                this.initAxis(keyPosition0, motionPaths0, motionPaths1);
                return;
            }
            default: {
                this.initCartesian(keyPosition0, motionPaths0, motionPaths1);
            }
        }
    }

    public void applyParameters(Constraint constraintSet$Constraint0) {
        this.mKeyFrameEasing = Easing.getInterpolator(constraintSet$Constraint0.motion.mTransitionEasing);
        this.mPathMotionArc = constraintSet$Constraint0.motion.mPathMotionArc;
        this.mAnimateRelativeTo = constraintSet$Constraint0.motion.mAnimateRelativeTo;
        this.mPathRotate = constraintSet$Constraint0.motion.mPathRotate;
        this.mDrawPath = constraintSet$Constraint0.motion.mDrawPath;
        this.mAnimateCircleAngleTo = constraintSet$Constraint0.motion.mAnimateCircleAngleTo;
        this.mProgress = constraintSet$Constraint0.propertySet.mProgress;
        this.mRelativeAngle = constraintSet$Constraint0.layout.circleAngle;
        for(Object object0: constraintSet$Constraint0.mCustomConstraints.keySet()) {
            String s = (String)object0;
            ConstraintAttribute constraintAttribute0 = (ConstraintAttribute)constraintSet$Constraint0.mCustomConstraints.get(s);
            if(constraintAttribute0 != null && constraintAttribute0.isContinuous()) {
                this.mAttributes.put(s, constraintAttribute0);
            }
        }
    }

    public int compareTo(MotionPaths motionPaths0) {
        return Float.compare(this.mPosition, motionPaths0.mPosition);
    }

    @Override
    public int compareTo(Object object0) {
        return this.compareTo(((MotionPaths)object0));
    }

    public void configureRelativeTo(MotionController motionController0) {
        motionController0.getPos(((double)this.mProgress));
    }

    // 去混淆评级： 低(20)
    private boolean diff(float f, float f1) {
        return Float.isNaN(f) || Float.isNaN(f1) ? Float.isNaN(f) != Float.isNaN(f1) : Math.abs(f - f1) > 0.000001f;
    }

    void different(MotionPaths motionPaths0, boolean[] arr_z, String[] arr_s, boolean z) {
        boolean z1 = this.diff(this.mX, motionPaths0.mX);
        boolean z2 = this.diff(this.mY, motionPaths0.mY);
        arr_z[0] |= this.diff(this.mPosition, motionPaths0.mPosition);
        int v = z1 | z2 | z;
        arr_z[1] |= v;
        arr_z[2] |= v;
        arr_z[3] |= this.diff(this.mWidth, motionPaths0.mWidth);
        arr_z[4] |= this.diff(this.mHeight, motionPaths0.mHeight);
    }

    void fillStandard(double[] arr_f, int[] arr_v) {
        float[] arr_f1 = new float[6];
        arr_f1[0] = this.mPosition;
        arr_f1[1] = this.mX;
        arr_f1[2] = this.mY;
        arr_f1[3] = this.mWidth;
        arr_f1[4] = this.mHeight;
        arr_f1[5] = this.mPathRotate;
        int v1 = 0;
        for(int v = 0; v < arr_v.length; ++v) {
            int v2 = arr_v[v];
            if(v2 < 6) {
                arr_f[v1] = (double)arr_f1[v2];
                ++v1;
            }
        }
    }

    void getBounds(int[] arr_v, double[] arr_f, float[] arr_f1, int v) {
        float f = this.mWidth;
        float f1 = this.mHeight;
        for(int v1 = 0; v1 < arr_v.length; ++v1) {
            float f2 = (float)arr_f[v1];
            switch(arr_v[v1]) {
                case 3: {
                    f = f2;
                    break;
                }
                case 4: {
                    f1 = f2;
                }
            }
        }
        arr_f1[v] = f;
        arr_f1[v + 1] = f1;
    }

    void getCenter(double f, int[] arr_v, double[] arr_f, float[] arr_f1, int v) {
        float f1 = this.mX;
        float f2 = this.mY;
        float f3 = this.mWidth;
        float f4 = this.mHeight;
        for(int v1 = 0; v1 < arr_v.length; ++v1) {
            float f5 = (float)arr_f[v1];
            switch(arr_v[v1]) {
                case 1: {
                    f1 = f5;
                    break;
                }
                case 2: {
                    f2 = f5;
                    break;
                }
                case 3: {
                    f3 = f5;
                    break;
                }
                case 4: {
                    f4 = f5;
                }
            }
        }
        MotionController motionController0 = this.mRelativeToController;
        if(motionController0 != null) {
            float[] arr_f2 = new float[2];
            motionController0.getCenter(f, arr_f2, new float[2]);
            float f6 = (float)(((double)arr_f2[1]) - ((double)f1) * Math.cos(f2) - ((double)(f4 / 2.0f)));
            f1 = (float)(((double)arr_f2[0]) + Math.sin(f2) * ((double)f1) - ((double)(f3 / 2.0f)));
            f2 = f6;
        }
        arr_f1[v] = f1 + f3 / 2.0f + 0.0f;
        arr_f1[v + 1] = f2 + f4 / 2.0f + 0.0f;
    }

    void getCenter(double f, int[] arr_v, double[] arr_f, float[] arr_f1, double[] arr_f2, float[] arr_f3) {
        float f1 = this.mX;
        float f2 = this.mY;
        float f3 = this.mWidth;
        float f4 = this.mHeight;
        float f5 = 0.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        float f8 = 0.0f;
        for(int v = 0; v < arr_v.length; ++v) {
            float f9 = (float)arr_f[v];
            float f10 = (float)arr_f2[v];
            switch(arr_v[v]) {
                case 1: {
                    f1 = f9;
                    f5 = f10;
                    break;
                }
                case 2: {
                    f2 = f9;
                    f7 = f10;
                    break;
                }
                case 3: {
                    f3 = f9;
                    f6 = f10;
                    break;
                }
                case 4: {
                    f4 = f9;
                    f8 = f10;
                }
            }
        }
        float f11 = f6 / 2.0f + f5;
        float f12 = f8 / 2.0f + f7;
        MotionController motionController0 = this.mRelativeToController;
        if(motionController0 != null) {
            float[] arr_f4 = new float[2];
            float[] arr_f5 = new float[2];
            motionController0.getCenter(f, arr_f4, arr_f5);
            double f13 = (double)f1;
            f1 = (float)(((double)arr_f4[0]) + Math.sin(f2) * f13 - ((double)(f3 / 2.0f)));
            double f14 = (double)f2;
            f2 = (float)(((double)arr_f4[1]) - Math.cos(f2) * f13 - ((double)(f4 / 2.0f)));
            f12 = (float)(((double)arr_f5[1]) - ((double)f5) * Math.cos(f14) + Math.sin(f14) * ((double)f7));
            f11 = (float)(((double)arr_f5[0]) + Math.sin(f14) * ((double)f5) + Math.cos(f14) * ((double)f7));
        }
        arr_f1[0] = f1 + f3 / 2.0f + 0.0f;
        arr_f1[1] = f2 + f4 / 2.0f + 0.0f;
        arr_f3[0] = f11;
        arr_f3[1] = f12;
    }

    void getCenterVelocity(double f, int[] arr_v, double[] arr_f, float[] arr_f1, int v) {
        float f1 = this.mX;
        float f2 = this.mY;
        float f3 = this.mWidth;
        float f4 = this.mHeight;
        for(int v1 = 0; v1 < arr_v.length; ++v1) {
            float f5 = (float)arr_f[v1];
            switch(arr_v[v1]) {
                case 1: {
                    f1 = f5;
                    break;
                }
                case 2: {
                    f2 = f5;
                    break;
                }
                case 3: {
                    f3 = f5;
                    break;
                }
                case 4: {
                    f4 = f5;
                }
            }
        }
        MotionController motionController0 = this.mRelativeToController;
        if(motionController0 != null) {
            float[] arr_f2 = new float[2];
            motionController0.getCenter(f, arr_f2, new float[2]);
            float f6 = (float)(((double)arr_f2[1]) - ((double)f1) * Math.cos(f2) - ((double)(f4 / 2.0f)));
            f1 = (float)(((double)arr_f2[0]) + Math.sin(f2) * ((double)f1) - ((double)(f3 / 2.0f)));
            f2 = f6;
        }
        arr_f1[v] = f1 + f3 / 2.0f + 0.0f;
        arr_f1[v + 1] = f2 + f4 / 2.0f + 0.0f;
    }

    int getCustomData(String s, double[] arr_f, int v) {
        ConstraintAttribute constraintAttribute0 = (ConstraintAttribute)this.mAttributes.get(s);
        int v1 = 0;
        if(constraintAttribute0 == null) {
            return 0;
        }
        if(constraintAttribute0.numberOfInterpolatedValues() == 1) {
            arr_f[v] = (double)constraintAttribute0.getValueToInterpolate();
            return 1;
        }
        int v2 = constraintAttribute0.numberOfInterpolatedValues();
        float[] arr_f1 = new float[v2];
        constraintAttribute0.getValuesToInterpolate(arr_f1);
        while(v1 < v2) {
            arr_f[v] = (double)arr_f1[v1];
            ++v1;
            ++v;
        }
        return v2;
    }

    int getCustomDataCount(String s) {
        ConstraintAttribute constraintAttribute0 = (ConstraintAttribute)this.mAttributes.get(s);
        return constraintAttribute0 == null ? 0 : constraintAttribute0.numberOfInterpolatedValues();
    }

    void getRect(int[] arr_v, double[] arr_f, float[] arr_f1, int v) {
        float f = this.mX;
        float f1 = this.mY;
        float f2 = this.mWidth;
        float f3 = this.mHeight;
        for(int v1 = 0; v1 < arr_v.length; ++v1) {
            float f4 = (float)arr_f[v1];
            switch(arr_v[v1]) {
                case 1: {
                    f = f4;
                    break;
                }
                case 2: {
                    f1 = f4;
                    break;
                }
                case 3: {
                    f2 = f4;
                    break;
                }
                case 4: {
                    f3 = f4;
                }
            }
        }
        MotionController motionController0 = this.mRelativeToController;
        if(motionController0 != null) {
            double f5 = Math.sin(f1);
            f1 = (float)(((double)this.mRelativeToController.getCenterY()) - ((double)f) * Math.cos(f1) - ((double)(f3 / 2.0f)));
            f = (float)(((double)motionController0.getCenterX()) + f5 * ((double)f) - ((double)(f2 / 2.0f)));
        }
        float f6 = f2 + f;
        float f7 = f3 + f1;
        arr_f1[v] = f + 0.0f;
        arr_f1[v + 1] = f1 + 0.0f;
        arr_f1[v + 2] = f6 + 0.0f;
        arr_f1[v + 3] = f1 + 0.0f;
        arr_f1[v + 4] = f6 + 0.0f;
        arr_f1[v + 5] = f7 + 0.0f;
        arr_f1[v + 6] = f + 0.0f;
        arr_f1[v + 7] = f7 + 0.0f;
    }

    boolean hasCustomData(String s) {
        return this.mAttributes.containsKey(s);
    }

    void initAxis(KeyPosition keyPosition0, MotionPaths motionPaths0, MotionPaths motionPaths1) {
        float f = ((float)keyPosition0.mFramePosition) / 100.0f;
        this.mTime = f;
        this.mDrawPath = keyPosition0.mDrawPath;
        float f1 = Float.isNaN(keyPosition0.mPercentWidth) ? f : keyPosition0.mPercentWidth;
        float f2 = Float.isNaN(keyPosition0.mPercentHeight) ? f : keyPosition0.mPercentHeight;
        float f3 = motionPaths0.mWidth;
        float f4 = motionPaths1.mWidth - f3;
        float f5 = motionPaths0.mHeight;
        float f6 = motionPaths1.mHeight - f5;
        this.mPosition = this.mTime;
        float f7 = motionPaths0.mX;
        float f8 = f3 / 2.0f + f7;
        float f9 = motionPaths0.mY;
        float f10 = f9 + f5 / 2.0f;
        float f11 = f;
        float f12 = motionPaths1.mX + motionPaths1.mWidth / 2.0f;
        float f13 = motionPaths1.mY + motionPaths1.mHeight / 2.0f;
        if(f8 > f12) {
            float f14 = f8;
            f8 = f12;
            f12 = f14;
        }
        if(f10 <= f13) {
            float f15 = f10;
            f10 = f13;
            f13 = f15;
        }
        float f16 = f12 - f8;
        float f17 = f10 - f13;
        float f18 = f4 * f1;
        this.mX = (float)(((int)(f7 + f16 * f11 - f18 / 2.0f)));
        float f19 = f6 * f2;
        this.mY = (float)(((int)(f9 + f17 * f11 - f19 / 2.0f)));
        this.mWidth = (float)(((int)(f3 + f18)));
        this.mHeight = (float)(((int)(f5 + f19)));
        float f20 = Float.isNaN(keyPosition0.mPercentX) ? f11 : keyPosition0.mPercentX;
        float f21 = 0.0f;
        float f22 = Float.isNaN(keyPosition0.mAltPercentY) ? 0.0f : keyPosition0.mAltPercentY;
        if(!Float.isNaN(keyPosition0.mPercentY)) {
            f11 = keyPosition0.mPercentY;
        }
        if(!Float.isNaN(keyPosition0.mAltPercentX)) {
            f21 = keyPosition0.mAltPercentX;
        }
        this.mMode = 0;
        this.mX = (float)(((int)(motionPaths0.mX + f20 * f16 + f21 * f17 - f18 / 2.0f)));
        this.mY = (float)(((int)(motionPaths0.mY + f16 * f22 + f17 * f11 - f19 / 2.0f)));
        this.mKeyFrameEasing = Easing.getInterpolator(keyPosition0.mTransitionEasing);
        this.mPathMotionArc = keyPosition0.mPathMotionArc;
    }

    void initCartesian(KeyPosition keyPosition0, MotionPaths motionPaths0, MotionPaths motionPaths1) {
        float f = ((float)keyPosition0.mFramePosition) / 100.0f;
        this.mTime = f;
        this.mDrawPath = keyPosition0.mDrawPath;
        this.mPosition = this.mTime;
        float f1 = f;
        float f2 = motionPaths1.mX + motionPaths1.mWidth / 2.0f - (motionPaths0.mWidth / 2.0f + motionPaths0.mX);
        float f3 = motionPaths1.mY + motionPaths1.mHeight / 2.0f - (motionPaths0.mY + motionPaths0.mHeight / 2.0f);
        float f4 = (motionPaths1.mWidth - motionPaths0.mWidth) * (Float.isNaN(keyPosition0.mPercentWidth) ? f : keyPosition0.mPercentWidth);
        this.mX = (float)(((int)(motionPaths0.mX + f2 * f1 - f4 / 2.0f)));
        float f5 = (motionPaths1.mHeight - motionPaths0.mHeight) * (Float.isNaN(keyPosition0.mPercentHeight) ? f : keyPosition0.mPercentHeight);
        this.mY = (float)(((int)(motionPaths0.mY + f3 * f1 - f5 / 2.0f)));
        this.mWidth = (float)(((int)(motionPaths0.mWidth + f4)));
        this.mHeight = (float)(((int)(motionPaths0.mHeight + f5)));
        float f6 = Float.isNaN(keyPosition0.mPercentX) ? f1 : keyPosition0.mPercentX;
        float f7 = 0.0f;
        float f8 = Float.isNaN(keyPosition0.mAltPercentY) ? 0.0f : keyPosition0.mAltPercentY;
        if(!Float.isNaN(keyPosition0.mPercentY)) {
            f1 = keyPosition0.mPercentY;
        }
        if(!Float.isNaN(keyPosition0.mAltPercentX)) {
            f7 = keyPosition0.mAltPercentX;
        }
        this.mMode = 0;
        this.mX = (float)(((int)(motionPaths0.mX + f6 * f2 + f7 * f3 - f4 / 2.0f)));
        this.mY = (float)(((int)(motionPaths0.mY + f2 * f8 + f3 * f1 - f5 / 2.0f)));
        this.mKeyFrameEasing = Easing.getInterpolator(keyPosition0.mTransitionEasing);
        this.mPathMotionArc = keyPosition0.mPathMotionArc;
    }

    void initPath(KeyPosition keyPosition0, MotionPaths motionPaths0, MotionPaths motionPaths1) {
        float f = ((float)keyPosition0.mFramePosition) / 100.0f;
        this.mTime = f;
        this.mDrawPath = keyPosition0.mDrawPath;
        float f1 = Float.isNaN(keyPosition0.mPercentWidth) ? f : keyPosition0.mPercentWidth;
        float f2 = Float.isNaN(keyPosition0.mPercentHeight) ? f : keyPosition0.mPercentHeight;
        float f3 = motionPaths1.mWidth - motionPaths0.mWidth;
        float f4 = motionPaths1.mHeight - motionPaths0.mHeight;
        this.mPosition = this.mTime;
        if(!Float.isNaN(keyPosition0.mPercentX)) {
            f = keyPosition0.mPercentX;
        }
        float f5 = motionPaths1.mX + motionPaths1.mWidth / 2.0f - (motionPaths0.mWidth / 2.0f + motionPaths0.mX);
        float f6 = motionPaths1.mY + motionPaths1.mHeight / 2.0f - (motionPaths0.mHeight / 2.0f + motionPaths0.mY);
        float f7 = f3 * f1;
        float f8 = f4 * f2;
        this.mWidth = (float)(((int)(motionPaths0.mWidth + f7)));
        this.mHeight = (float)(((int)(motionPaths0.mHeight + f8)));
        float f9 = Float.isNaN(keyPosition0.mPercentY) ? 0.0f : keyPosition0.mPercentY;
        this.mMode = 1;
        this.mX = ((float)(((int)(motionPaths0.mX + f5 * f - f7 / 2.0f)))) + -f6 * f9;
        this.mY = ((float)(((int)(motionPaths0.mY + f6 * f - f8 / 2.0f)))) + f5 * f9;
        this.mAnimateRelativeTo = this.mAnimateRelativeTo;
        this.mKeyFrameEasing = Easing.getInterpolator(keyPosition0.mTransitionEasing);
        this.mPathMotionArc = keyPosition0.mPathMotionArc;
    }

    void initPolar(int v, int v1, KeyPosition keyPosition0, MotionPaths motionPaths0, MotionPaths motionPaths1) {
        float f = ((float)keyPosition0.mFramePosition) / 100.0f;
        this.mTime = f;
        this.mDrawPath = keyPosition0.mDrawPath;
        this.mMode = keyPosition0.mPositionType;
        float f1 = Float.isNaN(keyPosition0.mPercentWidth) ? f : keyPosition0.mPercentWidth;
        float f2 = Float.isNaN(keyPosition0.mPercentHeight) ? f : keyPosition0.mPercentHeight;
        this.mPosition = this.mTime;
        this.mWidth = (float)(((int)(motionPaths0.mWidth + (motionPaths1.mWidth - motionPaths0.mWidth) * f1)));
        this.mHeight = (float)(((int)(motionPaths0.mHeight + (motionPaths1.mHeight - motionPaths0.mHeight) * f2)));
        if(keyPosition0.mPositionType == 2) {
            this.mX = Float.isNaN(keyPosition0.mPercentX) ? (motionPaths1.mX - motionPaths0.mX) * f + motionPaths0.mX : Math.min(f2, f1) * keyPosition0.mPercentX;
            this.mY = Float.isNaN(keyPosition0.mPercentY) ? f * (motionPaths1.mY - motionPaths0.mY) + motionPaths0.mY : keyPosition0.mPercentY;
        }
        else {
            this.mX = (Float.isNaN(keyPosition0.mPercentX) ? f : keyPosition0.mPercentX) * (motionPaths1.mX - motionPaths0.mX) + motionPaths0.mX;
            if(!Float.isNaN(keyPosition0.mPercentY)) {
                f = keyPosition0.mPercentY;
            }
            this.mY = f * (motionPaths1.mY - motionPaths0.mY) + motionPaths0.mY;
        }
        this.mAnimateRelativeTo = motionPaths0.mAnimateRelativeTo;
        this.mKeyFrameEasing = Easing.getInterpolator(keyPosition0.mTransitionEasing);
        this.mPathMotionArc = keyPosition0.mPathMotionArc;
    }

    void initScreen(int v, int v1, KeyPosition keyPosition0, MotionPaths motionPaths0, MotionPaths motionPaths1) {
        float f = ((float)keyPosition0.mFramePosition) / 100.0f;
        this.mTime = f;
        this.mDrawPath = keyPosition0.mDrawPath;
        this.mPosition = this.mTime;
        float f1 = (motionPaths1.mWidth - motionPaths0.mWidth) * (Float.isNaN(keyPosition0.mPercentWidth) ? f : keyPosition0.mPercentWidth);
        this.mX = (float)(((int)(motionPaths0.mX + (motionPaths1.mX + motionPaths1.mWidth / 2.0f - (motionPaths0.mWidth / 2.0f + motionPaths0.mX)) * f - f1 / 2.0f)));
        float f2 = (motionPaths1.mHeight - motionPaths0.mHeight) * (Float.isNaN(keyPosition0.mPercentHeight) ? f : keyPosition0.mPercentHeight);
        this.mY = (float)(((int)(motionPaths0.mY + (motionPaths1.mY + motionPaths1.mHeight / 2.0f - (motionPaths0.mY + motionPaths0.mHeight / 2.0f)) * f - f2 / 2.0f)));
        this.mWidth = (float)(((int)(motionPaths0.mWidth + f1)));
        this.mHeight = (float)(((int)(motionPaths0.mHeight + f2)));
        this.mMode = 2;
        if(!Float.isNaN(keyPosition0.mPercentX)) {
            this.mX = (float)(((int)(keyPosition0.mPercentX * ((float)(v - ((int)this.mWidth))))));
        }
        if(!Float.isNaN(keyPosition0.mPercentY)) {
            this.mY = (float)(((int)(keyPosition0.mPercentY * ((float)(v1 - ((int)this.mHeight))))));
        }
        this.mAnimateRelativeTo = this.mAnimateRelativeTo;
        this.mKeyFrameEasing = Easing.getInterpolator(keyPosition0.mTransitionEasing);
        this.mPathMotionArc = keyPosition0.mPathMotionArc;
    }

    void setBounds(float f, float f1, float f2, float f3) {
        this.mX = f;
        this.mY = f1;
        this.mWidth = f2;
        this.mHeight = f3;
    }

    void setDpDt(float f, float f1, float[] arr_f, int[] arr_v, double[] arr_f1, double[] arr_f2) {
        float f2 = 0.0f;
        float f3 = 0.0f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        for(int v = 0; v < arr_v.length; ++v) {
            float f6 = (float)arr_f1[v];
            double f7 = arr_f2[v];
            switch(arr_v[v]) {
                case 1: {
                    f2 = f6;
                    break;
                }
                case 2: {
                    f4 = f6;
                    break;
                }
                case 3: {
                    f3 = f6;
                    break;
                }
                case 4: {
                    f5 = f6;
                }
            }
        }
        float f8 = f2 - 0.0f * f3 / 2.0f;
        float f9 = f4 - 0.0f * f5 / 2.0f;
        arr_f[0] = f8 * (1.0f - f) + (f3 * 1.0f + f8) * f + 0.0f;
        arr_f[1] = f9 * (1.0f - f1) + (f5 * 1.0f + f9) * f1 + 0.0f;
    }

    void setView(float f, View view0, int[] arr_v, double[] arr_f, double[] arr_f1, double[] arr_f2, boolean z) {
        float f1 = this.mX;
        float f2 = this.mY;
        float f3 = this.mWidth;
        float f4 = this.mHeight;
        if(arr_v.length != 0 && this.mTempValue.length <= arr_v[arr_v.length - 1]) {
            int v = arr_v[arr_v.length - 1] + 1;
            this.mTempValue = new double[v];
            this.mTempDelta = new double[v];
        }
        Arrays.fill(this.mTempValue, NaN);
        for(int v1 = 0; v1 < arr_v.length; ++v1) {
            double[] arr_f3 = this.mTempValue;
            int v2 = arr_v[v1];
            arr_f3[v2] = arr_f[v1];
            this.mTempDelta[v2] = arr_f1[v1];
        }
        float f5 = NaNf;
        float f6 = 0.0f;
        float f7 = 0.0f;
        float f8 = 0.0f;
        float f9 = 0.0f;
        for(int v3 = 0; true; ++v3) {
            double f10 = 0.0;
            double[] arr_f4 = this.mTempValue;
            if(v3 >= arr_f4.length) {
                break;
            }
            if(!Double.isNaN(arr_f4[v3]) || arr_f2 != null && arr_f2[v3] != 0.0) {
                if(arr_f2 != null) {
                    f10 = arr_f2[v3];
                }
                if(!Double.isNaN(this.mTempValue[v3])) {
                    f10 = this.mTempValue[v3] + f10;
                }
                float f11 = (float)this.mTempDelta[v3];
                switch(v3) {
                    case 1: {
                        f6 = f11;
                        f1 = (float)f10;
                        break;
                    }
                    case 2: {
                        f7 = f11;
                        f2 = (float)f10;
                        break;
                    }
                    case 3: {
                        f8 = f11;
                        f3 = (float)f10;
                        break;
                    }
                    case 4: {
                        f9 = f11;
                        f4 = (float)f10;
                        break;
                    }
                    case 5: {
                        f5 = (float)f10;
                    }
                }
            }
        }
        MotionController motionController0 = this.mRelativeToController;
        if(motionController0 != null) {
            float[] arr_f5 = new float[2];
            float[] arr_f6 = new float[2];
            motionController0.getCenter(((double)f), arr_f5, arr_f6);
            double f12 = (double)f1;
            double f13 = (double)f2;
            f1 = (float)(((double)arr_f5[0]) + Math.sin(f13) * f12 - ((double)(f3 / 2.0f)));
            f2 = (float)(((double)arr_f5[1]) - Math.cos(f13) * f12 - ((double)(f4 / 2.0f)));
            float f14 = (float)(((double)arr_f6[0]) + Math.sin(f13) * ((double)f6) + Math.cos(f13) * f12 * ((double)f7));
            float f15 = (float)(((double)arr_f6[1]) - ((double)f6) * Math.cos(f13) + Math.sin(f13) * f12 * ((double)f7));
            if(arr_f1.length >= 2) {
                arr_f1[0] = (double)f14;
                arr_f1[1] = (double)f15;
            }
            if(!Float.isNaN(f5)) {
                view0.setRotation(((float)(((double)f5) + Math.toDegrees(Math.atan2(f15, f14)))));
            }
        }
        else if(!Float.isNaN(f5)) {
            view0.setRotation(f5 + ((float)Math.toDegrees(Math.atan2(f7 + f9 / 2.0f, f6 + f8 / 2.0f))) + 0.0f);
        }
        if(view0 instanceof FloatLayout) {
            ((FloatLayout)view0).layout(f1, f2, f3 + f1, f4 + f2);
            return;
        }
        int v4 = (int)(f1 + 0.5f + f3);
        int v5 = (int)(f2 + 0.5f + f4);
        int v6 = v4 - ((int)(f1 + 0.5f));
        int v7 = v5 - ((int)(f2 + 0.5f));
        if(v6 != view0.getMeasuredWidth() || v7 != view0.getMeasuredHeight() || z) {
            view0.measure(View.MeasureSpec.makeMeasureSpec(v6, 0x40000000), View.MeasureSpec.makeMeasureSpec(v7, 0x40000000));
        }
        view0.layout(((int)(f1 + 0.5f)), ((int)(f2 + 0.5f)), v4, v5);
    }

    public void setupRelative(MotionController motionController0, MotionPaths motionPaths0) {
        double f = (double)(this.mX + this.mWidth / 2.0f - motionPaths0.mX - motionPaths0.mWidth / 2.0f);
        double f1 = (double)(this.mY + this.mHeight / 2.0f - motionPaths0.mY - motionPaths0.mHeight / 2.0f);
        this.mRelativeToController = motionController0;
        this.mX = (float)Math.hypot(f1, f);
        if(Float.isNaN(this.mRelativeAngle)) {
            this.mY = (float)(Math.atan2(f1, f) + 1.570796);
            return;
        }
        this.mY = (float)Math.toRadians(this.mRelativeAngle);
    }

    private static float xRotate(float f, float f1, float f2, float f3, float f4, float f5) {
        return (f4 - f2) * f1 - (f5 - f3) * f + f2;
    }

    private static float yRotate(float f, float f1, float f2, float f3, float f4, float f5) {
        return (f4 - f2) * f + (f5 - f3) * f1 + f3;
    }
}

