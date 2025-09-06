package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.constraintlayout.core.motion.utils.SplineSet;
import androidx.constraintlayout.motion.utils.ViewSpline.CustomSet;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.R.styleable;
import java.util.HashMap;
import java.util.HashSet;

public class KeyAttributes extends Key {
    static class Loader {
        private static final int ANDROID_ALPHA = 1;
        private static final int ANDROID_ELEVATION = 2;
        private static final int ANDROID_PIVOT_X = 19;
        private static final int ANDROID_PIVOT_Y = 20;
        private static final int ANDROID_ROTATION = 4;
        private static final int ANDROID_ROTATION_X = 5;
        private static final int ANDROID_ROTATION_Y = 6;
        private static final int ANDROID_SCALE_X = 7;
        private static final int ANDROID_SCALE_Y = 14;
        private static final int ANDROID_TRANSLATION_X = 15;
        private static final int ANDROID_TRANSLATION_Y = 16;
        private static final int ANDROID_TRANSLATION_Z = 17;
        private static final int CURVE_FIT = 13;
        private static final int FRAME_POSITION = 12;
        private static final int PROGRESS = 18;
        private static final int TARGET_ID = 10;
        private static final int TRANSITION_EASING = 9;
        private static final int TRANSITION_PATH_ROTATE = 8;
        private static SparseIntArray sAttrMap;

        static {
            SparseIntArray sparseIntArray0 = new SparseIntArray();
            Loader.sAttrMap = sparseIntArray0;
            sparseIntArray0.append(styleable.KeyAttribute_android_alpha, 1);
            Loader.sAttrMap.append(styleable.KeyAttribute_android_elevation, 2);
            Loader.sAttrMap.append(styleable.KeyAttribute_android_rotation, 4);
            Loader.sAttrMap.append(styleable.KeyAttribute_android_rotationX, 5);
            Loader.sAttrMap.append(styleable.KeyAttribute_android_rotationY, 6);
            Loader.sAttrMap.append(styleable.KeyAttribute_android_transformPivotX, 19);
            Loader.sAttrMap.append(styleable.KeyAttribute_android_transformPivotY, 20);
            Loader.sAttrMap.append(styleable.KeyAttribute_android_scaleX, 7);
            Loader.sAttrMap.append(styleable.KeyAttribute_transitionPathRotate, 8);
            Loader.sAttrMap.append(styleable.KeyAttribute_transitionEasing, 9);
            Loader.sAttrMap.append(styleable.KeyAttribute_motionTarget, 10);
            Loader.sAttrMap.append(styleable.KeyAttribute_framePosition, 12);
            Loader.sAttrMap.append(styleable.KeyAttribute_curveFit, 13);
            Loader.sAttrMap.append(styleable.KeyAttribute_android_scaleY, 14);
            Loader.sAttrMap.append(styleable.KeyAttribute_android_translationX, 15);
            Loader.sAttrMap.append(styleable.KeyAttribute_android_translationY, 16);
            Loader.sAttrMap.append(styleable.KeyAttribute_android_translationZ, 17);
            Loader.sAttrMap.append(styleable.KeyAttribute_motionProgress, 18);
        }

        public static void read(KeyAttributes keyAttributes0, TypedArray typedArray0) {
            int v = typedArray0.getIndexCount();
            for(int v1 = 0; v1 < v; ++v1) {
                int v2 = typedArray0.getIndex(v1);
                switch(Loader.sAttrMap.get(v2)) {
                    case 1: {
                        keyAttributes0.mAlpha = typedArray0.getFloat(v2, keyAttributes0.mAlpha);
                        break;
                    }
                    case 2: {
                        keyAttributes0.mElevation = typedArray0.getDimension(v2, keyAttributes0.mElevation);
                        break;
                    }
                    case 4: {
                        keyAttributes0.mRotation = typedArray0.getFloat(v2, keyAttributes0.mRotation);
                        break;
                    }
                    case 5: {
                        keyAttributes0.mRotationX = typedArray0.getFloat(v2, keyAttributes0.mRotationX);
                        break;
                    }
                    case 6: {
                        keyAttributes0.mRotationY = typedArray0.getFloat(v2, keyAttributes0.mRotationY);
                        break;
                    }
                    case 7: {
                        keyAttributes0.mScaleX = typedArray0.getFloat(v2, keyAttributes0.mScaleX);
                        break;
                    }
                    case 8: {
                        keyAttributes0.mTransitionPathRotate = typedArray0.getFloat(v2, keyAttributes0.mTransitionPathRotate);
                        break;
                    }
                    case 9: {
                        keyAttributes0.mTransitionEasing = typedArray0.getString(v2);
                        break;
                    }
                    case 10: {
                        if(MotionLayout.IS_IN_EDIT_MODE) {
                            keyAttributes0.mTargetId = typedArray0.getResourceId(v2, keyAttributes0.mTargetId);
                            if(keyAttributes0.mTargetId == -1) {
                                keyAttributes0.mTargetString = typedArray0.getString(v2);
                            }
                        }
                        else if(typedArray0.peekValue(v2).type == 3) {
                            keyAttributes0.mTargetString = typedArray0.getString(v2);
                        }
                        else {
                            keyAttributes0.mTargetId = typedArray0.getResourceId(v2, keyAttributes0.mTargetId);
                        }
                        break;
                    }
                    case 12: {
                        keyAttributes0.mFramePosition = typedArray0.getInt(v2, keyAttributes0.mFramePosition);
                        break;
                    }
                    case 13: {
                        keyAttributes0.mCurveFit = typedArray0.getInteger(v2, keyAttributes0.mCurveFit);
                        break;
                    }
                    case 14: {
                        keyAttributes0.mScaleY = typedArray0.getFloat(v2, keyAttributes0.mScaleY);
                        break;
                    }
                    case 15: {
                        keyAttributes0.mTranslationX = typedArray0.getDimension(v2, keyAttributes0.mTranslationX);
                        break;
                    }
                    case 16: {
                        keyAttributes0.mTranslationY = typedArray0.getDimension(v2, keyAttributes0.mTranslationY);
                        break;
                    }
                    case 17: {
                        keyAttributes0.mTranslationZ = typedArray0.getDimension(v2, keyAttributes0.mTranslationZ);
                        break;
                    }
                    case 18: {
                        keyAttributes0.mProgress = typedArray0.getFloat(v2, keyAttributes0.mProgress);
                        break;
                    }
                    case 19: {
                        keyAttributes0.mPivotX = typedArray0.getDimension(v2, keyAttributes0.mPivotX);
                        break;
                    }
                    case 20: {
                        keyAttributes0.mPivotY = typedArray0.getDimension(v2, keyAttributes0.mPivotY);
                        break;
                    }
                    default: {
                        Log.e("KeyAttribute", "unused attribute 0x" + Integer.toHexString(v2) + "   " + Loader.sAttrMap.get(v2));
                    }
                }
            }
        }
    }

    private static final boolean DEBUG = false;
    public static final int KEY_TYPE = 1;
    static final String NAME = "KeyAttribute";
    private static final String TAG = "KeyAttributes";
    private float mAlpha;
    private int mCurveFit;
    private float mElevation;
    private float mPivotX;
    private float mPivotY;
    private float mProgress;
    private float mRotation;
    private float mRotationX;
    private float mRotationY;
    private float mScaleX;
    private float mScaleY;
    private String mTransitionEasing;
    private float mTransitionPathRotate;
    private float mTranslationX;
    private float mTranslationY;
    private float mTranslationZ;
    private boolean mVisibility;

    public KeyAttributes() {
        this.mCurveFit = -1;
        this.mVisibility = false;
        this.mAlpha = NaNf;
        this.mElevation = NaNf;
        this.mRotation = NaNf;
        this.mRotationX = NaNf;
        this.mRotationY = NaNf;
        this.mPivotX = NaNf;
        this.mPivotY = NaNf;
        this.mTransitionPathRotate = NaNf;
        this.mScaleX = NaNf;
        this.mScaleY = NaNf;
        this.mTranslationX = NaNf;
        this.mTranslationY = NaNf;
        this.mTranslationZ = NaNf;
        this.mProgress = NaNf;
        this.mType = 1;
        this.mCustomConstraints = new HashMap();
    }

    @Override  // androidx.constraintlayout.motion.widget.Key
    public void addValues(HashMap hashMap0) {
        for(Object object0: hashMap0.keySet()) {
            String s = (String)object0;
            SplineSet splineSet0 = (SplineSet)hashMap0.get(s);
            if(splineSet0 == null) {
            }
            else if(s.startsWith("CUSTOM")) {
                ConstraintAttribute constraintAttribute0 = (ConstraintAttribute)this.mCustomConstraints.get(s.substring(7));
                if(constraintAttribute0 == null) {
                    continue;
                }
                ((CustomSet)splineSet0).setPoint(this.mFramePosition, constraintAttribute0);
            }
            else {
                switch(s) {
                    case "alpha": {
                        if(!Float.isNaN(this.mAlpha)) {
                            break;
                        }
                        continue;
                    }
                    case "elevation": {
                        if(Float.isNaN(this.mElevation)) {
                            continue;
                        }
                        splineSet0.setPoint(this.mFramePosition, this.mElevation);
                        continue;
                    }
                    case "progress": {
                        if(Float.isNaN(this.mProgress)) {
                            continue;
                        }
                        splineSet0.setPoint(this.mFramePosition, this.mProgress);
                        continue;
                    }
                    case "rotation": {
                        if(Float.isNaN(this.mRotation)) {
                            continue;
                        }
                        splineSet0.setPoint(this.mFramePosition, this.mRotation);
                        continue;
                    }
                    case "rotationX": {
                        if(Float.isNaN(this.mRotationX)) {
                            continue;
                        }
                        splineSet0.setPoint(this.mFramePosition, this.mRotationX);
                        continue;
                    }
                    case "rotationY": {
                        if(Float.isNaN(this.mRotationY)) {
                            continue;
                        }
                        splineSet0.setPoint(this.mFramePosition, this.mRotationY);
                        continue;
                    }
                    case "scaleX": {
                        if(Float.isNaN(this.mScaleX)) {
                            continue;
                        }
                        splineSet0.setPoint(this.mFramePosition, this.mScaleX);
                        continue;
                    }
                    case "scaleY": {
                        if(Float.isNaN(this.mScaleY)) {
                            continue;
                        }
                        splineSet0.setPoint(this.mFramePosition, this.mScaleY);
                        continue;
                    }
                    case "transformPivotX": {
                        if(Float.isNaN(this.mRotationX)) {
                            continue;
                        }
                        splineSet0.setPoint(this.mFramePosition, this.mPivotX);
                        continue;
                    }
                    case "transformPivotY": {
                        if(Float.isNaN(this.mRotationY)) {
                            continue;
                        }
                        splineSet0.setPoint(this.mFramePosition, this.mPivotY);
                        continue;
                    }
                    case "transitionPathRotate": {
                        if(Float.isNaN(this.mTransitionPathRotate)) {
                            continue;
                        }
                        splineSet0.setPoint(this.mFramePosition, this.mTransitionPathRotate);
                        continue;
                    }
                    case "translationX": {
                        if(Float.isNaN(this.mTranslationX)) {
                            continue;
                        }
                        splineSet0.setPoint(this.mFramePosition, this.mTranslationX);
                        continue;
                    }
                    case "translationY": {
                        if(Float.isNaN(this.mTranslationY)) {
                            continue;
                        }
                        splineSet0.setPoint(this.mFramePosition, this.mTranslationY);
                        continue;
                    }
                    case "translationZ": {
                        if(Float.isNaN(this.mTranslationZ)) {
                            continue;
                        }
                        splineSet0.setPoint(this.mFramePosition, this.mTranslationZ);
                        continue;
                    }
                    default: {
                        continue;
                    }
                }
                splineSet0.setPoint(this.mFramePosition, this.mAlpha);
            }
        }
    }

    @Override  // androidx.constraintlayout.motion.widget.Key
    public Key clone() {
        return new KeyAttributes().copy(this);
    }

    @Override  // androidx.constraintlayout.motion.widget.Key
    public Object clone() throws CloneNotSupportedException {
        return this.clone();
    }

    @Override  // androidx.constraintlayout.motion.widget.Key
    public Key copy(Key key0) {
        super.copy(key0);
        this.mCurveFit = ((KeyAttributes)key0).mCurveFit;
        this.mVisibility = ((KeyAttributes)key0).mVisibility;
        this.mAlpha = ((KeyAttributes)key0).mAlpha;
        this.mElevation = ((KeyAttributes)key0).mElevation;
        this.mRotation = ((KeyAttributes)key0).mRotation;
        this.mRotationX = ((KeyAttributes)key0).mRotationX;
        this.mRotationY = ((KeyAttributes)key0).mRotationY;
        this.mPivotX = ((KeyAttributes)key0).mPivotX;
        this.mPivotY = ((KeyAttributes)key0).mPivotY;
        this.mTransitionPathRotate = ((KeyAttributes)key0).mTransitionPathRotate;
        this.mScaleX = ((KeyAttributes)key0).mScaleX;
        this.mScaleY = ((KeyAttributes)key0).mScaleY;
        this.mTranslationX = ((KeyAttributes)key0).mTranslationX;
        this.mTranslationY = ((KeyAttributes)key0).mTranslationY;
        this.mTranslationZ = ((KeyAttributes)key0).mTranslationZ;
        this.mProgress = ((KeyAttributes)key0).mProgress;
        this.mTransitionEasing = ((KeyAttributes)key0).mTransitionEasing;
        return this;
    }

    @Override  // androidx.constraintlayout.motion.widget.Key
    public void getAttributeNames(HashSet hashSet0) {
        if(!Float.isNaN(this.mAlpha)) {
            hashSet0.add("alpha");
        }
        if(!Float.isNaN(this.mElevation)) {
            hashSet0.add("elevation");
        }
        if(!Float.isNaN(this.mRotation)) {
            hashSet0.add("rotation");
        }
        if(!Float.isNaN(this.mRotationX)) {
            hashSet0.add("rotationX");
        }
        if(!Float.isNaN(this.mRotationY)) {
            hashSet0.add("rotationY");
        }
        if(!Float.isNaN(this.mPivotX)) {
            hashSet0.add("transformPivotX");
        }
        if(!Float.isNaN(this.mPivotY)) {
            hashSet0.add("transformPivotY");
        }
        if(!Float.isNaN(this.mTranslationX)) {
            hashSet0.add("translationX");
        }
        if(!Float.isNaN(this.mTranslationY)) {
            hashSet0.add("translationY");
        }
        if(!Float.isNaN(this.mTranslationZ)) {
            hashSet0.add("translationZ");
        }
        if(!Float.isNaN(this.mTransitionPathRotate)) {
            hashSet0.add("transitionPathRotate");
        }
        if(!Float.isNaN(this.mScaleX)) {
            hashSet0.add("scaleX");
        }
        if(!Float.isNaN(this.mScaleY)) {
            hashSet0.add("scaleY");
        }
        if(!Float.isNaN(this.mProgress)) {
            hashSet0.add("progress");
        }
        if(this.mCustomConstraints.size() > 0) {
            for(Object object0: this.mCustomConstraints.keySet()) {
                hashSet0.add("CUSTOM," + ((String)object0));
            }
        }
    }

    int getCurveFit() {
        return this.mCurveFit;
    }

    @Override  // androidx.constraintlayout.motion.widget.Key
    public void load(Context context0, AttributeSet attributeSet0) {
        Loader.read(this, context0.obtainStyledAttributes(attributeSet0, styleable.KeyAttribute));
    }

    @Override  // androidx.constraintlayout.motion.widget.Key
    public void setInterpolation(HashMap hashMap0) {
        if(this.mCurveFit != -1) {
            if(!Float.isNaN(this.mAlpha)) {
                hashMap0.put("alpha", this.mCurveFit);
            }
            if(!Float.isNaN(this.mElevation)) {
                hashMap0.put("elevation", this.mCurveFit);
            }
            if(!Float.isNaN(this.mRotation)) {
                hashMap0.put("rotation", this.mCurveFit);
            }
            if(!Float.isNaN(this.mRotationX)) {
                hashMap0.put("rotationX", this.mCurveFit);
            }
            if(!Float.isNaN(this.mRotationY)) {
                hashMap0.put("rotationY", this.mCurveFit);
            }
            if(!Float.isNaN(this.mPivotX)) {
                hashMap0.put("transformPivotX", this.mCurveFit);
            }
            if(!Float.isNaN(this.mPivotY)) {
                hashMap0.put("transformPivotY", this.mCurveFit);
            }
            if(!Float.isNaN(this.mTranslationX)) {
                hashMap0.put("translationX", this.mCurveFit);
            }
            if(!Float.isNaN(this.mTranslationY)) {
                hashMap0.put("translationY", this.mCurveFit);
            }
            if(!Float.isNaN(this.mTranslationZ)) {
                hashMap0.put("translationZ", this.mCurveFit);
            }
            if(!Float.isNaN(this.mTransitionPathRotate)) {
                hashMap0.put("transitionPathRotate", this.mCurveFit);
            }
            if(!Float.isNaN(this.mScaleX)) {
                hashMap0.put("scaleX", this.mCurveFit);
            }
            if(!Float.isNaN(this.mScaleY)) {
                hashMap0.put("scaleY", this.mCurveFit);
            }
            if(!Float.isNaN(this.mProgress)) {
                hashMap0.put("progress", this.mCurveFit);
            }
            if(this.mCustomConstraints.size() > 0) {
                for(Object object0: this.mCustomConstraints.keySet()) {
                    hashMap0.put("CUSTOM," + ((String)object0), this.mCurveFit);
                }
            }
        }
    }

    @Override  // androidx.constraintlayout.motion.widget.Key
    public void setValue(String s, Object object0) {
        s.hashCode();
        switch(s) {
            case "alpha": {
                this.mAlpha = this.toFloat(object0);
                return;
            }
            case "curveFit": {
                this.mCurveFit = this.toInt(object0);
                return;
            }
            case "elevation": {
                this.mElevation = this.toFloat(object0);
                return;
            }
            case "motionProgress": {
                this.mProgress = this.toFloat(object0);
                return;
            }
            case "rotation": {
                this.mRotation = this.toFloat(object0);
                return;
            }
            case "rotationX": {
                this.mRotationX = this.toFloat(object0);
                return;
            }
            case "rotationY": {
                this.mRotationY = this.toFloat(object0);
                return;
            }
            case "scaleX": {
                this.mScaleX = this.toFloat(object0);
                return;
            }
            case "scaleY": {
                this.mScaleY = this.toFloat(object0);
                return;
            }
            case "transformPivotX": {
                this.mPivotX = this.toFloat(object0);
                return;
            }
            case "transformPivotY": {
                this.mPivotY = this.toFloat(object0);
                return;
            }
            case "transitionEasing": {
                this.mTransitionEasing = object0.toString();
                return;
            }
            case "transitionPathRotate": {
                this.mTransitionPathRotate = this.toFloat(object0);
                return;
            }
            case "translationX": {
                this.mTranslationX = this.toFloat(object0);
                return;
            }
            case "translationY": {
                this.mTranslationY = this.toFloat(object0);
                return;
            }
            case "translationZ": {
                this.mTranslationZ = this.toFloat(object0);
                return;
            }
            case "visibility": {
                this.mVisibility = this.toBoolean(object0);
            }
        }
    }
}

