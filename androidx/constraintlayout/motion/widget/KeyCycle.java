package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.constraintlayout.core.motion.utils.SplineSet;
import androidx.constraintlayout.motion.utils.ViewOscillator;
import androidx.constraintlayout.widget.ConstraintAttribute.AttributeType;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.R.styleable;
import java.util.HashMap;
import java.util.HashSet;

public class KeyCycle extends Key {
    static class Loader {
        private static final int ANDROID_ALPHA = 9;
        private static final int ANDROID_ELEVATION = 10;
        private static final int ANDROID_ROTATION = 11;
        private static final int ANDROID_ROTATION_X = 12;
        private static final int ANDROID_ROTATION_Y = 13;
        private static final int ANDROID_SCALE_X = 15;
        private static final int ANDROID_SCALE_Y = 16;
        private static final int ANDROID_TRANSLATION_X = 17;
        private static final int ANDROID_TRANSLATION_Y = 18;
        private static final int ANDROID_TRANSLATION_Z = 19;
        private static final int CURVE_FIT = 4;
        private static final int FRAME_POSITION = 2;
        private static final int PROGRESS = 20;
        private static final int TARGET_ID = 1;
        private static final int TRANSITION_EASING = 3;
        private static final int TRANSITION_PATH_ROTATE = 14;
        private static final int WAVE_OFFSET = 7;
        private static final int WAVE_PERIOD = 6;
        private static final int WAVE_PHASE = 21;
        private static final int WAVE_SHAPE = 5;
        private static final int WAVE_VARIES_BY = 8;
        private static SparseIntArray sAttrMap;

        static {
            SparseIntArray sparseIntArray0 = new SparseIntArray();
            Loader.sAttrMap = sparseIntArray0;
            sparseIntArray0.append(styleable.KeyCycle_motionTarget, 1);
            Loader.sAttrMap.append(styleable.KeyCycle_framePosition, 2);
            Loader.sAttrMap.append(styleable.KeyCycle_transitionEasing, 3);
            Loader.sAttrMap.append(styleable.KeyCycle_curveFit, 4);
            Loader.sAttrMap.append(styleable.KeyCycle_waveShape, 5);
            Loader.sAttrMap.append(styleable.KeyCycle_wavePeriod, 6);
            Loader.sAttrMap.append(styleable.KeyCycle_waveOffset, 7);
            Loader.sAttrMap.append(styleable.KeyCycle_waveVariesBy, 8);
            Loader.sAttrMap.append(styleable.KeyCycle_android_alpha, 9);
            Loader.sAttrMap.append(styleable.KeyCycle_android_elevation, 10);
            Loader.sAttrMap.append(styleable.KeyCycle_android_rotation, 11);
            Loader.sAttrMap.append(styleable.KeyCycle_android_rotationX, 12);
            Loader.sAttrMap.append(styleable.KeyCycle_android_rotationY, 13);
            Loader.sAttrMap.append(styleable.KeyCycle_transitionPathRotate, 14);
            Loader.sAttrMap.append(styleable.KeyCycle_android_scaleX, 15);
            Loader.sAttrMap.append(styleable.KeyCycle_android_scaleY, 16);
            Loader.sAttrMap.append(styleable.KeyCycle_android_translationX, 17);
            Loader.sAttrMap.append(styleable.KeyCycle_android_translationY, 18);
            Loader.sAttrMap.append(styleable.KeyCycle_android_translationZ, 19);
            Loader.sAttrMap.append(styleable.KeyCycle_motionProgress, 20);
            Loader.sAttrMap.append(styleable.KeyCycle_wavePhase, 21);
        }

        private static void read(KeyCycle keyCycle0, TypedArray typedArray0) {
            int v = typedArray0.getIndexCount();
            for(int v1 = 0; v1 < v; ++v1) {
                int v2 = typedArray0.getIndex(v1);
                switch(Loader.sAttrMap.get(v2)) {
                    case 1: {
                        if(MotionLayout.IS_IN_EDIT_MODE) {
                            keyCycle0.mTargetId = typedArray0.getResourceId(v2, keyCycle0.mTargetId);
                            if(keyCycle0.mTargetId == -1) {
                                keyCycle0.mTargetString = typedArray0.getString(v2);
                            }
                        }
                        else if(typedArray0.peekValue(v2).type == 3) {
                            keyCycle0.mTargetString = typedArray0.getString(v2);
                        }
                        else {
                            keyCycle0.mTargetId = typedArray0.getResourceId(v2, keyCycle0.mTargetId);
                        }
                        break;
                    }
                    case 2: {
                        keyCycle0.mFramePosition = typedArray0.getInt(v2, keyCycle0.mFramePosition);
                        break;
                    }
                    case 3: {
                        keyCycle0.mTransitionEasing = typedArray0.getString(v2);
                        break;
                    }
                    case 4: {
                        keyCycle0.mCurveFit = typedArray0.getInteger(v2, keyCycle0.mCurveFit);
                        break;
                    }
                    case 5: {
                        if(typedArray0.peekValue(v2).type == 3) {
                            keyCycle0.mCustomWaveShape = typedArray0.getString(v2);
                            keyCycle0.mWaveShape = 7;
                        }
                        else {
                            keyCycle0.mWaveShape = typedArray0.getInt(v2, keyCycle0.mWaveShape);
                        }
                        break;
                    }
                    case 6: {
                        keyCycle0.mWavePeriod = typedArray0.getFloat(v2, keyCycle0.mWavePeriod);
                        break;
                    }
                    case 7: {
                        keyCycle0.mWaveOffset = typedArray0.peekValue(v2).type == 5 ? typedArray0.getDimension(v2, keyCycle0.mWaveOffset) : typedArray0.getFloat(v2, keyCycle0.mWaveOffset);
                        break;
                    }
                    case 8: {
                        keyCycle0.mWaveVariesBy = typedArray0.getInt(v2, keyCycle0.mWaveVariesBy);
                        break;
                    }
                    case 9: {
                        keyCycle0.mAlpha = typedArray0.getFloat(v2, keyCycle0.mAlpha);
                        break;
                    }
                    case 10: {
                        keyCycle0.mElevation = typedArray0.getDimension(v2, keyCycle0.mElevation);
                        break;
                    }
                    case 11: {
                        keyCycle0.mRotation = typedArray0.getFloat(v2, keyCycle0.mRotation);
                        break;
                    }
                    case 12: {
                        keyCycle0.mRotationX = typedArray0.getFloat(v2, keyCycle0.mRotationX);
                        break;
                    }
                    case 13: {
                        keyCycle0.mRotationY = typedArray0.getFloat(v2, keyCycle0.mRotationY);
                        break;
                    }
                    case 14: {
                        keyCycle0.mTransitionPathRotate = typedArray0.getFloat(v2, keyCycle0.mTransitionPathRotate);
                        break;
                    }
                    case 15: {
                        keyCycle0.mScaleX = typedArray0.getFloat(v2, keyCycle0.mScaleX);
                        break;
                    }
                    case 16: {
                        keyCycle0.mScaleY = typedArray0.getFloat(v2, keyCycle0.mScaleY);
                        break;
                    }
                    case 17: {
                        keyCycle0.mTranslationX = typedArray0.getDimension(v2, keyCycle0.mTranslationX);
                        break;
                    }
                    case 18: {
                        keyCycle0.mTranslationY = typedArray0.getDimension(v2, keyCycle0.mTranslationY);
                        break;
                    }
                    case 19: {
                        keyCycle0.mTranslationZ = typedArray0.getDimension(v2, keyCycle0.mTranslationZ);
                        break;
                    }
                    case 20: {
                        keyCycle0.mProgress = typedArray0.getFloat(v2, keyCycle0.mProgress);
                        break;
                    }
                    case 21: {
                        keyCycle0.mWavePhase = typedArray0.getFloat(v2, keyCycle0.mWavePhase) / 360.0f;
                        break;
                    }
                    default: {
                        Log.e("KeyCycle", "unused attribute 0x" + Integer.toHexString(v2) + "   " + Loader.sAttrMap.get(v2));
                    }
                }
            }
        }
    }

    public static final int KEY_TYPE = 4;
    static final String NAME = "KeyCycle";
    public static final int SHAPE_BOUNCE = 6;
    public static final int SHAPE_COS_WAVE = 5;
    public static final int SHAPE_REVERSE_SAW_WAVE = 4;
    public static final int SHAPE_SAW_WAVE = 3;
    public static final int SHAPE_SIN_WAVE = 0;
    public static final int SHAPE_SQUARE_WAVE = 1;
    public static final int SHAPE_TRIANGLE_WAVE = 2;
    private static final String TAG = "KeyCycle";
    public static final String WAVE_OFFSET = "waveOffset";
    public static final String WAVE_PERIOD = "wavePeriod";
    public static final String WAVE_PHASE = "wavePhase";
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
    private float mWavePhase;
    private int mWaveShape;
    private int mWaveVariesBy;

    public KeyCycle() {
        this.mTransitionEasing = null;
        this.mCurveFit = 0;
        this.mWaveShape = -1;
        this.mCustomWaveShape = null;
        this.mWavePeriod = NaNf;
        this.mWaveOffset = 0.0f;
        this.mWavePhase = 0.0f;
        this.mProgress = NaNf;
        this.mWaveVariesBy = -1;
        this.mAlpha = NaNf;
        this.mElevation = NaNf;
        this.mRotation = NaNf;
        this.mTransitionPathRotate = NaNf;
        this.mRotationX = NaNf;
        this.mRotationY = NaNf;
        this.mScaleX = NaNf;
        this.mScaleY = NaNf;
        this.mTranslationX = NaNf;
        this.mTranslationY = NaNf;
        this.mTranslationZ = NaNf;
        this.mType = 4;
        this.mCustomConstraints = new HashMap();
    }

    public void addCycleValues(HashMap hashMap0) {
        for(Object object0: hashMap0.keySet()) {
            String s = (String)object0;
            if(s.startsWith("CUSTOM")) {
                ConstraintAttribute constraintAttribute0 = (ConstraintAttribute)this.mCustomConstraints.get(s.substring(7));
                if(constraintAttribute0 == null || constraintAttribute0.getType() != AttributeType.FLOAT_TYPE) {
                    continue;
                }
                ViewOscillator viewOscillator0 = (ViewOscillator)hashMap0.get(s);
                if(viewOscillator0 == null) {
                    continue;
                }
                viewOscillator0.setPoint(this.mFramePosition, this.mWaveShape, this.mCustomWaveShape, this.mWaveVariesBy, this.mWavePeriod, this.mWaveOffset, this.mWavePhase, constraintAttribute0.getValueToInterpolate(), constraintAttribute0);
            }
            else {
                float f = this.getValue(s);
                if(!Float.isNaN(f)) {
                    ViewOscillator viewOscillator1 = (ViewOscillator)hashMap0.get(s);
                    if(viewOscillator1 != null) {
                        viewOscillator1.setPoint(this.mFramePosition, this.mWaveShape, this.mCustomWaveShape, this.mWaveVariesBy, this.mWavePeriod, this.mWaveOffset, this.mWavePhase, f);
                    }
                }
            }
        }
    }

    @Override  // androidx.constraintlayout.motion.widget.Key
    public void addValues(HashMap hashMap0) {
        Debug.logStack("KeyCycle", "add " + hashMap0.size() + " values", 2);
        for(Object object0: hashMap0.keySet()) {
            String s = (String)object0;
            SplineSet splineSet0 = (SplineSet)hashMap0.get(s);
            if(splineSet0 != null) {
                s.hashCode();
                switch(s) {
                    case "alpha": {
                        splineSet0.setPoint(this.mFramePosition, this.mAlpha);
                        break;
                    }
                    case "elevation": {
                        splineSet0.setPoint(this.mFramePosition, this.mElevation);
                        break;
                    }
                    case "progress": {
                        splineSet0.setPoint(this.mFramePosition, this.mProgress);
                        break;
                    }
                    case "rotation": {
                        splineSet0.setPoint(this.mFramePosition, this.mRotation);
                        break;
                    }
                    case "rotationX": {
                        splineSet0.setPoint(this.mFramePosition, this.mRotationX);
                        break;
                    }
                    case "rotationY": {
                        splineSet0.setPoint(this.mFramePosition, this.mRotationY);
                        break;
                    }
                    case "scaleX": {
                        splineSet0.setPoint(this.mFramePosition, this.mScaleX);
                        break;
                    }
                    case "scaleY": {
                        splineSet0.setPoint(this.mFramePosition, this.mScaleY);
                        break;
                    }
                    case "transitionPathRotate": {
                        splineSet0.setPoint(this.mFramePosition, this.mTransitionPathRotate);
                        break;
                    }
                    case "translationX": {
                        splineSet0.setPoint(this.mFramePosition, this.mTranslationX);
                        break;
                    }
                    case "translationY": {
                        splineSet0.setPoint(this.mFramePosition, this.mTranslationY);
                        break;
                    }
                    case "translationZ": {
                        splineSet0.setPoint(this.mFramePosition, this.mTranslationZ);
                        break;
                    }
                    case "waveOffset": {
                        splineSet0.setPoint(this.mFramePosition, this.mWaveOffset);
                        break;
                    }
                    case "wavePhase": {
                        splineSet0.setPoint(this.mFramePosition, this.mWavePhase);
                        break;
                    }
                    default: {
                        if(s.startsWith("CUSTOM")) {
                            continue;
                        }
                        Log.v("WARNING KeyCycle", "  UNKNOWN  " + s);
                    }
                }
            }
        }
    }

    @Override  // androidx.constraintlayout.motion.widget.Key
    public Key clone() {
        return new KeyCycle().copy(this);
    }

    @Override  // androidx.constraintlayout.motion.widget.Key
    public Object clone() throws CloneNotSupportedException {
        return this.clone();
    }

    @Override  // androidx.constraintlayout.motion.widget.Key
    public Key copy(Key key0) {
        super.copy(key0);
        KeyCycle keyCycle0 = (KeyCycle)key0;
        this.mTransitionEasing = keyCycle0.mTransitionEasing;
        this.mCurveFit = keyCycle0.mCurveFit;
        this.mWaveShape = keyCycle0.mWaveShape;
        this.mCustomWaveShape = keyCycle0.mCustomWaveShape;
        this.mWavePeriod = keyCycle0.mWavePeriod;
        this.mWaveOffset = keyCycle0.mWaveOffset;
        this.mWavePhase = keyCycle0.mWavePhase;
        this.mProgress = keyCycle0.mProgress;
        this.mWaveVariesBy = keyCycle0.mWaveVariesBy;
        this.mAlpha = keyCycle0.mAlpha;
        this.mElevation = keyCycle0.mElevation;
        this.mRotation = keyCycle0.mRotation;
        this.mTransitionPathRotate = keyCycle0.mTransitionPathRotate;
        this.mRotationX = keyCycle0.mRotationX;
        this.mRotationY = keyCycle0.mRotationY;
        this.mScaleX = keyCycle0.mScaleX;
        this.mScaleY = keyCycle0.mScaleY;
        this.mTranslationX = keyCycle0.mTranslationX;
        this.mTranslationY = keyCycle0.mTranslationY;
        this.mTranslationZ = keyCycle0.mTranslationZ;
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
        if(!Float.isNaN(this.mScaleX)) {
            hashSet0.add("scaleX");
        }
        if(!Float.isNaN(this.mScaleY)) {
            hashSet0.add("scaleY");
        }
        if(!Float.isNaN(this.mTransitionPathRotate)) {
            hashSet0.add("transitionPathRotate");
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
        if(this.mCustomConstraints.size() > 0) {
            for(Object object0: this.mCustomConstraints.keySet()) {
                hashSet0.add("CUSTOM," + ((String)object0));
            }
        }
    }

    public float getValue(String s) {
        s.hashCode();
        switch(s) {
            case "alpha": {
                return this.mAlpha;
            }
            case "elevation": {
                return this.mElevation;
            }
            case "progress": {
                return this.mProgress;
            }
            case "rotation": {
                return this.mRotation;
            }
            case "rotationX": {
                return this.mRotationX;
            }
            case "rotationY": {
                return this.mRotationY;
            }
            case "scaleX": {
                return this.mScaleX;
            }
            case "scaleY": {
                return this.mScaleY;
            }
            case "transitionPathRotate": {
                return this.mTransitionPathRotate;
            }
            case "translationX": {
                return this.mTranslationX;
            }
            case "translationY": {
                return this.mTranslationY;
            }
            case "translationZ": {
                return this.mTranslationZ;
            }
            case "waveOffset": {
                return this.mWaveOffset;
            }
            case "wavePhase": {
                return this.mWavePhase;
            }
            default: {
                if(!s.startsWith("CUSTOM")) {
                    Log.v("WARNING! KeyCycle", "  UNKNOWN  " + s);
                }
                return NaNf;
            }
        }
    }

    @Override  // androidx.constraintlayout.motion.widget.Key
    public void load(Context context0, AttributeSet attributeSet0) {
        Loader.read(this, context0.obtainStyledAttributes(attributeSet0, styleable.KeyCycle));
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
            case "wavePhase": {
                this.mWavePhase = this.toFloat(object0);
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

