package androidx.constraintlayout.motion.widget;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.motion.utils.ViewSpline.CustomSet;
import androidx.constraintlayout.motion.utils.ViewSpline;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintSet.Constraint;
import androidx.constraintlayout.widget.ConstraintSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

class MotionConstrainedPoint implements Comparable {
    static final int CARTESIAN = 2;
    public static final boolean DEBUG = false;
    static final int PERPENDICULAR = 1;
    public static final String TAG = "MotionPaths";
    private float mAlpha;
    private int mAnimateRelativeTo;
    private boolean mApplyElevation;
    LinkedHashMap mAttributes;
    private int mDrawPath;
    private float mElevation;
    private float mHeight;
    private Easing mKeyFrameEasing;
    int mMode;
    private float mPathRotate;
    private float mPivotX;
    private float mPivotY;
    private float mPosition;
    private float mProgress;
    private float mRotation;
    private float mRotationX;
    private float mScaleX;
    private float mScaleY;
    double[] mTempDelta;
    double[] mTempValue;
    private float mTranslationX;
    private float mTranslationY;
    private float mTranslationZ;
    int mVisibility;
    int mVisibilityMode;
    private float mWidth;
    private float mX;
    private float mY;
    public float rotationY;
    static String[] sNames;

    static {
        MotionConstrainedPoint.sNames = new String[]{"position", "x", "y", "width", "height", "pathRotate"};
    }

    MotionConstrainedPoint() {
        this.rotationY = 0.0f;
        this.mVisibilityMode = 0;
        this.mAttributes = new LinkedHashMap();
        this.mMode = 0;
        this.mTempValue = new double[18];
        this.mTempDelta = new double[18];
        this.mAlpha = 1.0f;
        this.mApplyElevation = false;
        this.mElevation = 0.0f;
        this.mRotation = 0.0f;
        this.mRotationX = 0.0f;
        this.mScaleX = 1.0f;
        this.mScaleY = 1.0f;
        this.mPivotX = NaNf;
        this.mPivotY = NaNf;
        this.mTranslationX = 0.0f;
        this.mTranslationY = 0.0f;
        this.mTranslationZ = 0.0f;
        this.mDrawPath = 0;
        this.mPathRotate = NaNf;
        this.mProgress = NaNf;
        this.mAnimateRelativeTo = -1;
    }

    public void addValues(HashMap hashMap0, int v) {
        for(Object object0: hashMap0.keySet()) {
            String s = (String)object0;
            ViewSpline viewSpline0 = (ViewSpline)hashMap0.get(s);
            if(viewSpline0 != null) {
                s.hashCode();
                int v1 = -1;
                switch(s) {
                    case "alpha": {
                        v1 = 13;
                        break;
                    }
                    case "elevation": {
                        v1 = 11;
                        break;
                    }
                    case "progress": {
                        v1 = 5;
                        break;
                    }
                    case "rotation": {
                        v1 = 10;
                        break;
                    }
                    case "rotationX": {
                        v1 = 0;
                        break;
                    }
                    case "rotationY": {
                        v1 = 1;
                        break;
                    }
                    case "scaleX": {
                        v1 = 6;
                        break;
                    }
                    case "scaleY": {
                        v1 = 7;
                        break;
                    }
                    case "transformPivotX": {
                        v1 = 8;
                        break;
                    }
                    case "transformPivotY": {
                        v1 = 9;
                        break;
                    }
                    case "transitionPathRotate": {
                        v1 = 12;
                        break;
                    }
                    case "translationX": {
                        v1 = 2;
                        break;
                    }
                    case "translationY": {
                        v1 = 3;
                        break;
                    }
                    case "translationZ": {
                        v1 = 4;
                    }
                }
                float f = 1.0f;
                float f1 = 0.0f;
                switch(v1) {
                    case 0: {
                        if(!Float.isNaN(this.mRotationX)) {
                            f1 = this.mRotationX;
                        }
                        viewSpline0.setPoint(v, f1);
                        continue;
                    }
                    case 1: {
                        if(!Float.isNaN(this.rotationY)) {
                            f1 = this.rotationY;
                        }
                        viewSpline0.setPoint(v, f1);
                        continue;
                    }
                    case 2: {
                        if(!Float.isNaN(this.mTranslationX)) {
                            f1 = this.mTranslationX;
                        }
                        viewSpline0.setPoint(v, f1);
                        continue;
                    }
                    case 3: {
                        if(!Float.isNaN(this.mTranslationY)) {
                            f1 = this.mTranslationY;
                        }
                        viewSpline0.setPoint(v, f1);
                        continue;
                    }
                    case 4: {
                        if(!Float.isNaN(this.mTranslationZ)) {
                            f1 = this.mTranslationZ;
                        }
                        viewSpline0.setPoint(v, f1);
                        continue;
                    }
                    case 5: {
                        if(!Float.isNaN(this.mProgress)) {
                            f1 = this.mProgress;
                        }
                        viewSpline0.setPoint(v, f1);
                        continue;
                    }
                    case 6: {
                        if(!Float.isNaN(this.mScaleX)) {
                            f = this.mScaleX;
                        }
                        viewSpline0.setPoint(v, f);
                        continue;
                    }
                    case 7: {
                        if(!Float.isNaN(this.mScaleY)) {
                            f = this.mScaleY;
                        }
                        viewSpline0.setPoint(v, f);
                        continue;
                    }
                    case 8: {
                        if(!Float.isNaN(this.mPivotX)) {
                            f1 = this.mPivotX;
                        }
                        viewSpline0.setPoint(v, f1);
                        continue;
                    }
                    case 9: {
                        if(!Float.isNaN(this.mPivotY)) {
                            f1 = this.mPivotY;
                        }
                        viewSpline0.setPoint(v, f1);
                        continue;
                    }
                    case 10: {
                        if(!Float.isNaN(this.mRotation)) {
                            f1 = this.mRotation;
                        }
                        viewSpline0.setPoint(v, f1);
                        continue;
                    }
                    case 11: {
                        if(!Float.isNaN(this.mElevation)) {
                            f1 = this.mElevation;
                        }
                        viewSpline0.setPoint(v, f1);
                        continue;
                    }
                    case 12: {
                        if(!Float.isNaN(this.mPathRotate)) {
                            f1 = this.mPathRotate;
                        }
                        viewSpline0.setPoint(v, f1);
                        continue;
                    }
                    case 13: {
                        if(!Float.isNaN(this.mAlpha)) {
                            f = this.mAlpha;
                        }
                        break;
                    }
                    default: {
                        if(s.startsWith("CUSTOM")) {
                            String s1 = s.split(",")[1];
                            if(!this.mAttributes.containsKey(s1)) {
                                continue;
                            }
                            ConstraintAttribute constraintAttribute0 = (ConstraintAttribute)this.mAttributes.get(s1);
                            if(viewSpline0 instanceof CustomSet) {
                                ((CustomSet)viewSpline0).setPoint(v, constraintAttribute0);
                            }
                            else {
                                Log.e("MotionPaths", s + " ViewSpline not a CustomSet frame = " + v + ", value" + constraintAttribute0.getValueToInterpolate() + viewSpline0);
                            }
                        }
                        else {
                            Log.e("MotionPaths", "UNKNOWN spline " + s);
                        }
                        continue;
                    }
                }
                viewSpline0.setPoint(v, f);
            }
        }
    }

    public void applyParameters(View view0) {
        this.mVisibility = view0.getVisibility();
        this.mAlpha = view0.getVisibility() == 0 ? view0.getAlpha() : 0.0f;
        this.mApplyElevation = false;
        this.mElevation = view0.getElevation();
        this.mRotation = view0.getRotation();
        this.mRotationX = view0.getRotationX();
        this.rotationY = view0.getRotationY();
        this.mScaleX = view0.getScaleX();
        this.mScaleY = view0.getScaleY();
        this.mPivotX = view0.getPivotX();
        this.mPivotY = view0.getPivotY();
        this.mTranslationX = view0.getTranslationX();
        this.mTranslationY = view0.getTranslationY();
        this.mTranslationZ = view0.getTranslationZ();
    }

    public void applyParameters(Constraint constraintSet$Constraint0) {
        this.mVisibilityMode = constraintSet$Constraint0.propertySet.mVisibilityMode;
        this.mVisibility = constraintSet$Constraint0.propertySet.visibility;
        this.mAlpha = constraintSet$Constraint0.propertySet.visibility == 0 || this.mVisibilityMode != 0 ? constraintSet$Constraint0.propertySet.alpha : 0.0f;
        this.mApplyElevation = constraintSet$Constraint0.transform.applyElevation;
        this.mElevation = constraintSet$Constraint0.transform.elevation;
        this.mRotation = constraintSet$Constraint0.transform.rotation;
        this.mRotationX = constraintSet$Constraint0.transform.rotationX;
        this.rotationY = constraintSet$Constraint0.transform.rotationY;
        this.mScaleX = constraintSet$Constraint0.transform.scaleX;
        this.mScaleY = constraintSet$Constraint0.transform.scaleY;
        this.mPivotX = constraintSet$Constraint0.transform.transformPivotX;
        this.mPivotY = constraintSet$Constraint0.transform.transformPivotY;
        this.mTranslationX = constraintSet$Constraint0.transform.translationX;
        this.mTranslationY = constraintSet$Constraint0.transform.translationY;
        this.mTranslationZ = constraintSet$Constraint0.transform.translationZ;
        this.mKeyFrameEasing = Easing.getInterpolator(constraintSet$Constraint0.motion.mTransitionEasing);
        this.mPathRotate = constraintSet$Constraint0.motion.mPathRotate;
        this.mDrawPath = constraintSet$Constraint0.motion.mDrawPath;
        this.mAnimateRelativeTo = constraintSet$Constraint0.motion.mAnimateRelativeTo;
        this.mProgress = constraintSet$Constraint0.propertySet.mProgress;
        for(Object object0: constraintSet$Constraint0.mCustomConstraints.keySet()) {
            String s = (String)object0;
            ConstraintAttribute constraintAttribute0 = (ConstraintAttribute)constraintSet$Constraint0.mCustomConstraints.get(s);
            if(constraintAttribute0.isContinuous()) {
                this.mAttributes.put(s, constraintAttribute0);
            }
        }
    }

    public int compareTo(MotionConstrainedPoint motionConstrainedPoint0) {
        return Float.compare(this.mPosition, motionConstrainedPoint0.mPosition);
    }

    @Override
    public int compareTo(Object object0) {
        return this.compareTo(((MotionConstrainedPoint)object0));
    }

    // 去混淆评级： 低(20)
    private boolean diff(float f, float f1) {
        return Float.isNaN(f) || Float.isNaN(f1) ? Float.isNaN(f) != Float.isNaN(f1) : Math.abs(f - f1) > 0.000001f;
    }

    void different(MotionConstrainedPoint motionConstrainedPoint0, HashSet hashSet0) {
        if(this.diff(this.mAlpha, motionConstrainedPoint0.mAlpha)) {
            hashSet0.add("alpha");
        }
        if(this.diff(this.mElevation, motionConstrainedPoint0.mElevation)) {
            hashSet0.add("elevation");
        }
        if(this.mVisibility != motionConstrainedPoint0.mVisibility && this.mVisibilityMode == 0 && (this.mVisibility == 0 || motionConstrainedPoint0.mVisibility == 0)) {
            hashSet0.add("alpha");
        }
        if(this.diff(this.mRotation, motionConstrainedPoint0.mRotation)) {
            hashSet0.add("rotation");
        }
        if(!Float.isNaN(this.mPathRotate) || !Float.isNaN(motionConstrainedPoint0.mPathRotate)) {
            hashSet0.add("transitionPathRotate");
        }
        if(!Float.isNaN(this.mProgress) || !Float.isNaN(motionConstrainedPoint0.mProgress)) {
            hashSet0.add("progress");
        }
        if(this.diff(this.mRotationX, motionConstrainedPoint0.mRotationX)) {
            hashSet0.add("rotationX");
        }
        if(this.diff(this.rotationY, motionConstrainedPoint0.rotationY)) {
            hashSet0.add("rotationY");
        }
        if(this.diff(this.mPivotX, motionConstrainedPoint0.mPivotX)) {
            hashSet0.add("transformPivotX");
        }
        if(this.diff(this.mPivotY, motionConstrainedPoint0.mPivotY)) {
            hashSet0.add("transformPivotY");
        }
        if(this.diff(this.mScaleX, motionConstrainedPoint0.mScaleX)) {
            hashSet0.add("scaleX");
        }
        if(this.diff(this.mScaleY, motionConstrainedPoint0.mScaleY)) {
            hashSet0.add("scaleY");
        }
        if(this.diff(this.mTranslationX, motionConstrainedPoint0.mTranslationX)) {
            hashSet0.add("translationX");
        }
        if(this.diff(this.mTranslationY, motionConstrainedPoint0.mTranslationY)) {
            hashSet0.add("translationY");
        }
        if(this.diff(this.mTranslationZ, motionConstrainedPoint0.mTranslationZ)) {
            hashSet0.add("translationZ");
        }
    }

    void different(MotionConstrainedPoint motionConstrainedPoint0, boolean[] arr_z, String[] arr_s) {
        arr_z[0] |= this.diff(this.mPosition, motionConstrainedPoint0.mPosition);
        arr_z[1] |= this.diff(this.mX, motionConstrainedPoint0.mX);
        arr_z[2] |= this.diff(this.mY, motionConstrainedPoint0.mY);
        arr_z[3] |= this.diff(this.mWidth, motionConstrainedPoint0.mWidth);
        arr_z[4] |= this.diff(this.mHeight, motionConstrainedPoint0.mHeight);
    }

    void fillStandard(double[] arr_f, int[] arr_v) {
        float[] arr_f1 = new float[18];
        int v = 0;
        arr_f1[0] = this.mPosition;
        arr_f1[1] = this.mX;
        arr_f1[2] = this.mY;
        arr_f1[3] = this.mWidth;
        arr_f1[4] = this.mHeight;
        arr_f1[5] = this.mAlpha;
        arr_f1[6] = this.mElevation;
        arr_f1[7] = this.mRotation;
        arr_f1[8] = this.mRotationX;
        arr_f1[9] = this.rotationY;
        arr_f1[10] = this.mScaleX;
        arr_f1[11] = this.mScaleY;
        arr_f1[12] = this.mPivotX;
        arr_f1[13] = this.mPivotY;
        arr_f1[14] = this.mTranslationX;
        arr_f1[15] = this.mTranslationY;
        arr_f1[16] = this.mTranslationZ;
        arr_f1[17] = this.mPathRotate;
        for(int v1 = 0; v1 < arr_v.length; ++v1) {
            int v2 = arr_v[v1];
            if(v2 < 18) {
                arr_f[v] = (double)arr_f1[v2];
                ++v;
            }
        }
    }

    int getCustomData(String s, double[] arr_f, int v) {
        ConstraintAttribute constraintAttribute0 = (ConstraintAttribute)this.mAttributes.get(s);
        if(constraintAttribute0.numberOfInterpolatedValues() == 1) {
            arr_f[v] = (double)constraintAttribute0.getValueToInterpolate();
            return 1;
        }
        int v1 = constraintAttribute0.numberOfInterpolatedValues();
        float[] arr_f1 = new float[v1];
        constraintAttribute0.getValuesToInterpolate(arr_f1);
        int v2 = 0;
        while(v2 < v1) {
            arr_f[v] = (double)arr_f1[v2];
            ++v2;
            ++v;
        }
        return v1;
    }

    int getCustomDataCount(String s) {
        return ((ConstraintAttribute)this.mAttributes.get(s)).numberOfInterpolatedValues();
    }

    boolean hasCustomData(String s) {
        return this.mAttributes.containsKey(s);
    }

    void setBounds(float f, float f1, float f2, float f3) {
        this.mX = f;
        this.mY = f1;
        this.mWidth = f2;
        this.mHeight = f3;
    }

    public void setState(Rect rect0, View view0, int v, float f) {
        this.setBounds(((float)rect0.left), ((float)rect0.top), ((float)rect0.width()), ((float)rect0.height()));
        this.applyParameters(view0);
        this.mPivotX = NaNf;
        this.mPivotY = NaNf;
        switch(v) {
            case 1: {
                this.mRotation = f - 90.0f;
                return;
            }
            case 2: {
                this.mRotation = f + 90.0f;
            }
        }
    }

    public void setState(Rect rect0, ConstraintSet constraintSet0, int v, int v1) {
        this.setBounds(((float)rect0.left), ((float)rect0.top), ((float)rect0.width()), ((float)rect0.height()));
        this.applyParameters(constraintSet0.getParameters(v1));
        switch(v) {
            case 1: 
            case 3: {
                this.mRotation -= 90.0f;
                return;
            }
            case 2: 
            case 4: {
                float f = this.mRotation + 90.0f;
                this.mRotation = f;
                if(f > 180.0f) {
                    this.mRotation = f - 360.0f;
                }
            }
        }
    }

    public void setState(View view0) {
        this.setBounds(view0.getX(), view0.getY(), ((float)view0.getWidth()), ((float)view0.getHeight()));
        this.applyParameters(view0);
    }
}

