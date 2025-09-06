package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.constraintlayout.motion.utils.ViewTimeCycle.CustomSet;
import androidx.constraintlayout.motion.utils.ViewTimeCycle;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.R.styleable;
import java.util.HashMap;
import java.util.HashSet;

public class KeyTimeCycle extends Key {
    static class Loader {
        private static final int ANDROID_ALPHA = 1;
        private static final int ANDROID_ELEVATION = 2;
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
        private static final int WAVE_OFFSET = 21;
        private static final int WAVE_PERIOD = 20;
        private static final int WAVE_SHAPE = 19;
        private static SparseIntArray sAttrMap;

        static {
            SparseIntArray sparseIntArray0 = new SparseIntArray();
            Loader.sAttrMap = sparseIntArray0;
            sparseIntArray0.append(styleable.KeyTimeCycle_android_alpha, 1);
            Loader.sAttrMap.append(styleable.KeyTimeCycle_android_elevation, 2);
            Loader.sAttrMap.append(styleable.KeyTimeCycle_android_rotation, 4);
            Loader.sAttrMap.append(styleable.KeyTimeCycle_android_rotationX, 5);
            Loader.sAttrMap.append(styleable.KeyTimeCycle_android_rotationY, 6);
            Loader.sAttrMap.append(styleable.KeyTimeCycle_android_scaleX, 7);
            Loader.sAttrMap.append(styleable.KeyTimeCycle_transitionPathRotate, 8);
            Loader.sAttrMap.append(styleable.KeyTimeCycle_transitionEasing, 9);
            Loader.sAttrMap.append(styleable.KeyTimeCycle_motionTarget, 10);
            Loader.sAttrMap.append(styleable.KeyTimeCycle_framePosition, 12);
            Loader.sAttrMap.append(styleable.KeyTimeCycle_curveFit, 13);
            Loader.sAttrMap.append(styleable.KeyTimeCycle_android_scaleY, 14);
            Loader.sAttrMap.append(styleable.KeyTimeCycle_android_translationX, 15);
            Loader.sAttrMap.append(styleable.KeyTimeCycle_android_translationY, 16);
            Loader.sAttrMap.append(styleable.KeyTimeCycle_android_translationZ, 17);
            Loader.sAttrMap.append(styleable.KeyTimeCycle_motionProgress, 18);
            Loader.sAttrMap.append(styleable.KeyTimeCycle_wavePeriod, 20);
            Loader.sAttrMap.append(styleable.KeyTimeCycle_waveOffset, 21);
            Loader.sAttrMap.append(styleable.KeyTimeCycle_waveShape, 19);
        }

        public static void read(KeyTimeCycle keyTimeCycle0, TypedArray typedArray0) {
            int v = typedArray0.getIndexCount();
            for(int v1 = 0; v1 < v; ++v1) {
                int v2 = typedArray0.getIndex(v1);
                switch(Loader.sAttrMap.get(v2)) {
                    case 1: {
                        keyTimeCycle0.mAlpha = typedArray0.getFloat(v2, keyTimeCycle0.mAlpha);
                        break;
                    }
                    case 2: {
                        keyTimeCycle0.mElevation = typedArray0.getDimension(v2, keyTimeCycle0.mElevation);
                        break;
                    }
                    case 4: {
                        keyTimeCycle0.mRotation = typedArray0.getFloat(v2, keyTimeCycle0.mRotation);
                        break;
                    }
                    case 5: {
                        keyTimeCycle0.mRotationX = typedArray0.getFloat(v2, keyTimeCycle0.mRotationX);
                        break;
                    }
                    case 6: {
                        keyTimeCycle0.mRotationY = typedArray0.getFloat(v2, keyTimeCycle0.mRotationY);
                        break;
                    }
                    case 7: {
                        keyTimeCycle0.mScaleX = typedArray0.getFloat(v2, keyTimeCycle0.mScaleX);
                        break;
                    }
                    case 8: {
                        keyTimeCycle0.mTransitionPathRotate = typedArray0.getFloat(v2, keyTimeCycle0.mTransitionPathRotate);
                        break;
                    }
                    case 9: {
                        keyTimeCycle0.mTransitionEasing = typedArray0.getString(v2);
                        break;
                    }
                    case 10: {
                        if(MotionLayout.IS_IN_EDIT_MODE) {
                            keyTimeCycle0.mTargetId = typedArray0.getResourceId(v2, keyTimeCycle0.mTargetId);
                            if(keyTimeCycle0.mTargetId == -1) {
                                keyTimeCycle0.mTargetString = typedArray0.getString(v2);
                            }
                        }
                        else if(typedArray0.peekValue(v2).type == 3) {
                            keyTimeCycle0.mTargetString = typedArray0.getString(v2);
                        }
                        else {
                            keyTimeCycle0.mTargetId = typedArray0.getResourceId(v2, keyTimeCycle0.mTargetId);
                        }
                        break;
                    }
                    case 12: {
                        keyTimeCycle0.mFramePosition = typedArray0.getInt(v2, keyTimeCycle0.mFramePosition);
                        break;
                    }
                    case 13: {
                        keyTimeCycle0.mCurveFit = typedArray0.getInteger(v2, keyTimeCycle0.mCurveFit);
                        break;
                    }
                    case 14: {
                        keyTimeCycle0.mScaleY = typedArray0.getFloat(v2, keyTimeCycle0.mScaleY);
                        break;
                    }
                    case 15: {
                        keyTimeCycle0.mTranslationX = typedArray0.getDimension(v2, keyTimeCycle0.mTranslationX);
                        break;
                    }
                    case 16: {
                        keyTimeCycle0.mTranslationY = typedArray0.getDimension(v2, keyTimeCycle0.mTranslationY);
                        break;
                    }
                    case 17: {
                        keyTimeCycle0.mTranslationZ = typedArray0.getDimension(v2, keyTimeCycle0.mTranslationZ);
                        break;
                    }
                    case 18: {
                        keyTimeCycle0.mProgress = typedArray0.getFloat(v2, keyTimeCycle0.mProgress);
                        break;
                    }
                    case 19: {
                        if(typedArray0.peekValue(v2).type == 3) {
                            keyTimeCycle0.mCustomWaveShape = typedArray0.getString(v2);
                            keyTimeCycle0.mWaveShape = 7;
                        }
                        else {
                            keyTimeCycle0.mWaveShape = typedArray0.getInt(v2, keyTimeCycle0.mWaveShape);
                        }
                        break;
                    }
                    case 20: {
                        keyTimeCycle0.mWavePeriod = typedArray0.getFloat(v2, keyTimeCycle0.mWavePeriod);
                        break;
                    }
                    case 21: {
                        keyTimeCycle0.mWaveOffset = typedArray0.peekValue(v2).type == 5 ? typedArray0.getDimension(v2, keyTimeCycle0.mWaveOffset) : typedArray0.getFloat(v2, keyTimeCycle0.mWaveOffset);
                        break;
                    }
                    default: {
                        Log.e("KeyTimeCycle", "unused attribute 0x" + Integer.toHexString(v2) + "   " + Loader.sAttrMap.get(v2));
                    }
                }
            }
        }
    }

    public static final int KEY_TYPE = 3;
    static final String NAME = "KeyTimeCycle";
    public static final int SHAPE_BOUNCE = 6;
    public static final int SHAPE_COS_WAVE = 5;
    public static final int SHAPE_REVERSE_SAW_WAVE = 4;
    public static final int SHAPE_SAW_WAVE = 3;
    public static final int SHAPE_SIN_WAVE = 0;
    public static final int SHAPE_SQUARE_WAVE = 1;
    public static final int SHAPE_TRIANGLE_WAVE = 2;
    private static final String TAG = "KeyTimeCycle";
    public static final String WAVE_OFFSET = "waveOffset";
    public static final String WAVE_PERIOD = "wavePeriod";
    public static final String WAVE_SHAPE = "waveShape";
    private float mAlpha;
    private int mCurveFit;
    private String mCustomWaveShape;
    private float mElevation;
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
    private float mWaveOffset;
    private float mWavePeriod;
    private int mWaveShape;

    public KeyTimeCycle() {
        this.mCurveFit = -1;
        this.mAlpha = NaNf;
        this.mElevation = NaNf;
        this.mRotation = NaNf;
        this.mRotationX = NaNf;
        this.mRotationY = NaNf;
        this.mTransitionPathRotate = NaNf;
        this.mScaleX = NaNf;
        this.mScaleY = NaNf;
        this.mTranslationX = NaNf;
        this.mTranslationY = NaNf;
        this.mTranslationZ = NaNf;
        this.mProgress = NaNf;
        this.mWaveShape = 0;
        this.mCustomWaveShape = null;
        this.mWavePeriod = NaNf;
        this.mWaveOffset = 0.0f;
        this.mType = 3;
        this.mCustomConstraints = new HashMap();
    }

    public void addTimeValues(HashMap hashMap0) {
        for(Object object0: hashMap0.keySet()) {
            String s = (String)object0;
            ViewTimeCycle viewTimeCycle0 = (ViewTimeCycle)hashMap0.get(s);
            if(viewTimeCycle0 == null) {
            }
            else if(s.startsWith("CUSTOM")) {
                ConstraintAttribute constraintAttribute0 = (ConstraintAttribute)this.mCustomConstraints.get(s.substring(7));
                if(constraintAttribute0 == null) {
                    continue;
                }
                ((CustomSet)viewTimeCycle0).setPoint(this.mFramePosition, constraintAttribute0, this.mWavePeriod, this.mWaveShape, this.mWaveOffset);
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
                        viewTimeCycle0.setPoint(this.mFramePosition, this.mElevation, this.mWavePeriod, this.mWaveShape, this.mWaveOffset);
                        continue;
                    }
                    case "progress": {
                        if(Float.isNaN(this.mProgress)) {
                            continue;
                        }
                        viewTimeCycle0.setPoint(this.mFramePosition, this.mProgress, this.mWavePeriod, this.mWaveShape, this.mWaveOffset);
                        continue;
                    }
                    case "rotation": {
                        if(Float.isNaN(this.mRotation)) {
                            continue;
                        }
                        viewTimeCycle0.setPoint(this.mFramePosition, this.mRotation, this.mWavePeriod, this.mWaveShape, this.mWaveOffset);
                        continue;
                    }
                    case "rotationX": {
                        if(Float.isNaN(this.mRotationX)) {
                            continue;
                        }
                        viewTimeCycle0.setPoint(this.mFramePosition, this.mRotationX, this.mWavePeriod, this.mWaveShape, this.mWaveOffset);
                        continue;
                    }
                    case "rotationY": {
                        if(Float.isNaN(this.mRotationY)) {
                            continue;
                        }
                        viewTimeCycle0.setPoint(this.mFramePosition, this.mRotationY, this.mWavePeriod, this.mWaveShape, this.mWaveOffset);
                        continue;
                    }
                    case "scaleX": {
                        if(Float.isNaN(this.mScaleX)) {
                            continue;
                        }
                        viewTimeCycle0.setPoint(this.mFramePosition, this.mScaleX, this.mWavePeriod, this.mWaveShape, this.mWaveOffset);
                        continue;
                    }
                    case "scaleY": {
                        if(Float.isNaN(this.mScaleY)) {
                            continue;
                        }
                        viewTimeCycle0.setPoint(this.mFramePosition, this.mScaleY, this.mWavePeriod, this.mWaveShape, this.mWaveOffset);
                        continue;
                    }
                    case "transitionPathRotate": {
                        if(Float.isNaN(this.mTransitionPathRotate)) {
                            continue;
                        }
                        viewTimeCycle0.setPoint(this.mFramePosition, this.mTransitionPathRotate, this.mWavePeriod, this.mWaveShape, this.mWaveOffset);
                        continue;
                    }
                    case "translationX": {
                        if(Float.isNaN(this.mTranslationX)) {
                            continue;
                        }
                        viewTimeCycle0.setPoint(this.mFramePosition, this.mTranslationX, this.mWavePeriod, this.mWaveShape, this.mWaveOffset);
                        continue;
                    }
                    case "translationY": {
                        if(Float.isNaN(this.mTranslationY)) {
                            continue;
                        }
                        viewTimeCycle0.setPoint(this.mFramePosition, this.mTranslationY, this.mWavePeriod, this.mWaveShape, this.mWaveOffset);
                        continue;
                    }
                    case "translationZ": {
                        if(Float.isNaN(this.mTranslationZ)) {
                            continue;
                        }
                        viewTimeCycle0.setPoint(this.mFramePosition, this.mTranslationZ, this.mWavePeriod, this.mWaveShape, this.mWaveOffset);
                        continue;
                    }
                    default: {
                        Log.e("KeyTimeCycles", "UNKNOWN addValues \"" + s + "\"");
                        continue;
                    }
                }
                viewTimeCycle0.setPoint(this.mFramePosition, this.mAlpha, this.mWavePeriod, this.mWaveShape, this.mWaveOffset);
            }
        }
    }

    @Override  // androidx.constraintlayout.motion.widget.Key
    public void addValues(HashMap hashMap0) {
        throw new IllegalArgumentException(" KeyTimeCycles do not support SplineSet");
    }

    @Override  // androidx.constraintlayout.motion.widget.Key
    public Key clone() {
        return new KeyTimeCycle().copy(this);
    }

    @Override  // androidx.constraintlayout.motion.widget.Key
    public Object clone() throws CloneNotSupportedException {
        return this.clone();
    }

    @Override  // androidx.constraintlayout.motion.widget.Key
    public Key copy(Key key0) {
        super.copy(key0);
        this.mTransitionEasing = ((KeyTimeCycle)key0).mTransitionEasing;
        this.mCurveFit = ((KeyTimeCycle)key0).mCurveFit;
        this.mWaveShape = ((KeyTimeCycle)key0).mWaveShape;
        this.mWavePeriod = ((KeyTimeCycle)key0).mWavePeriod;
        this.mWaveOffset = ((KeyTimeCycle)key0).mWaveOffset;
        this.mProgress = ((KeyTimeCycle)key0).mProgress;
        this.mAlpha = ((KeyTimeCycle)key0).mAlpha;
        this.mElevation = ((KeyTimeCycle)key0).mElevation;
        this.mRotation = ((KeyTimeCycle)key0).mRotation;
        this.mTransitionPathRotate = ((KeyTimeCycle)key0).mTransitionPathRotate;
        this.mRotationX = ((KeyTimeCycle)key0).mRotationX;
        this.mRotationY = ((KeyTimeCycle)key0).mRotationY;
        this.mScaleX = ((KeyTimeCycle)key0).mScaleX;
        this.mScaleY = ((KeyTimeCycle)key0).mScaleY;
        this.mTranslationX = ((KeyTimeCycle)key0).mTranslationX;
        this.mTranslationY = ((KeyTimeCycle)key0).mTranslationY;
        this.mTranslationZ = ((KeyTimeCycle)key0).mTranslationZ;
        this.mCustomWaveShape = ((KeyTimeCycle)key0).mCustomWaveShape;
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

    @Override  // androidx.constraintlayout.motion.widget.Key
    public void load(Context context0, AttributeSet attributeSet0) {
        Loader.read(this, context0.obtainStyledAttributes(attributeSet0, styleable.KeyTimeCycle));
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
            if(!Float.isNaN(this.mScaleX)) {
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
            case "waveOffset": {
                this.mWaveOffset = this.toFloat(object0);
                return;
            }
            case "wavePeriod": {
                this.mWavePeriod = this.toFloat(object0);
                return;
            }
            case "waveShape": {
                if(object0 instanceof Integer) {
                    this.mWaveShape = this.toInt(object0);
                    return;
                }
                this.mWaveShape = 7;
                this.mCustomWaveShape = object0.toString();
            }
        }
    }
}

