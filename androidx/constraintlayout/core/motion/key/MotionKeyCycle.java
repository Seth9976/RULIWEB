package androidx.constraintlayout.core.motion.key;

import androidx.constraintlayout.core.motion.CustomVariable;
import androidx.constraintlayout.core.motion.utils.KeyCycleOscillator;
import androidx.constraintlayout.core.motion.utils.Utils;
import java.util.HashMap;
import java.util.HashSet;

public class MotionKeyCycle extends MotionKey {
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

    public MotionKeyCycle() {
        this.mTransitionEasing = null;
        this.mCurveFit = 0;
        this.mWaveShape = -1;
        this.mCustomWaveShape = null;
        this.mWavePeriod = NaNf;
        this.mWaveOffset = 0.0f;
        this.mWavePhase = 0.0f;
        this.mProgress = NaNf;
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
        this.mCustom = new HashMap();
    }

    public void addCycleValues(HashMap hashMap0) {
        for(Object object0: hashMap0.keySet()) {
            String s = (String)object0;
            if(s.startsWith("CUSTOM")) {
                CustomVariable customVariable0 = (CustomVariable)this.mCustom.get(s.substring(7));
                if(customVariable0 == null || customVariable0.getType() != 901) {
                    continue;
                }
                KeyCycleOscillator keyCycleOscillator0 = (KeyCycleOscillator)hashMap0.get(s);
                if(keyCycleOscillator0 == null) {
                    continue;
                }
                keyCycleOscillator0.setPoint(this.mFramePosition, this.mWaveShape, this.mCustomWaveShape, -1, this.mWavePeriod, this.mWaveOffset, this.mWavePhase / 360.0f, customVariable0.getValueToInterpolate(), customVariable0);
            }
            else {
                float f = this.getValue(s);
                if(!Float.isNaN(f)) {
                    KeyCycleOscillator keyCycleOscillator1 = (KeyCycleOscillator)hashMap0.get(s);
                    if(keyCycleOscillator1 != null) {
                        keyCycleOscillator1.setPoint(this.mFramePosition, this.mWaveShape, this.mCustomWaveShape, -1, this.mWavePeriod, this.mWaveOffset, this.mWavePhase / 360.0f, f);
                    }
                }
            }
        }
    }

    @Override  // androidx.constraintlayout.core.motion.key.MotionKey
    public void addValues(HashMap hashMap0) {
    }

    @Override  // androidx.constraintlayout.core.motion.key.MotionKey
    public MotionKey clone() [...] // Inlined contents

    @Override  // androidx.constraintlayout.core.motion.key.MotionKey
    public Object clone() throws CloneNotSupportedException {
        return null;
    }

    public void dump() {
        System.out.println("MotionKeyCycle{mWaveShape=" + this.mWaveShape + ", mWavePeriod=" + this.mWavePeriod + ", mWaveOffset=" + this.mWaveOffset + ", mWavePhase=" + this.mWavePhase + ", mRotation=" + this.mRotation + '}');
    }

    @Override  // androidx.constraintlayout.core.motion.key.MotionKey
    public void getAttributeNames(HashSet hashSet0) {
        if(!Float.isNaN(this.mAlpha)) {
            hashSet0.add("alpha");
        }
        if(!Float.isNaN(this.mElevation)) {
            hashSet0.add("elevation");
        }
        if(!Float.isNaN(this.mRotation)) {
            hashSet0.add("rotationZ");
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
            hashSet0.add("pathRotate");
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
        if(this.mCustom.size() > 0) {
            for(Object object0: this.mCustom.keySet()) {
                hashSet0.add("CUSTOM," + ((String)object0));
            }
        }
    }

    @Override  // androidx.constraintlayout.core.motion.utils.TypedValues
    public int getId(String s) {
        s.hashCode();
        switch(s) {
            case "alpha": {
                return 403;
            }
            case "curveFit": {
                return 401;
            }
            case "customWave": {
                return 422;
            }
            case "easing": {
                return 420;
            }
            case "offset": {
                return 424;
            }
            case "pathRotate": {
                return 0x1A0;
            }
            case "period": {
                return 423;
            }
            case "phase": {
                return 425;
            }
            case "pivotX": {
                return 313;
            }
            case "pivotY": {
                return 314;
            }
            case "progress": {
                return 315;
            }
            case "rotationX": {
                return 308;
            }
            case "rotationY": {
                return 309;
            }
            case "rotationZ": {
                return 310;
            }
            case "scaleX": {
                return 311;
            }
            case "scaleY": {
                return 312;
            }
            case "translationX": {
                return 304;
            }
            case "translationY": {
                return 305;
            }
            case "translationZ": {
                return 306;
            }
            case "visibility": {
                return 402;
            }
            case "waveShape": {
                return 421;
            }
            default: {
                return -1;
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
            case "offset": {
                return this.mWaveOffset;
            }
            case "pathRotate": {
                return this.mTransitionPathRotate;
            }
            case "phase": {
                return this.mWavePhase;
            }
            case "progress": {
                return this.mProgress;
            }
            case "rotationX": {
                return this.mRotationX;
            }
            case "rotationY": {
                return this.mRotationY;
            }
            case "rotationZ": {
                return this.mRotation;
            }
            case "scaleX": {
                return this.mScaleX;
            }
            case "scaleY": {
                return this.mScaleY;
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
            default: {
                return NaNf;
            }
        }
    }

    public void printAttributes() {
        HashSet hashSet0 = new HashSet();
        this.getAttributeNames(hashSet0);
        Utils.log((" ------------- " + this.mFramePosition + " -------------"));
        Utils.log(("MotionKeyCycle{Shape=" + this.mWaveShape + ", Period=" + this.mWavePeriod + ", Offset=" + this.mWaveOffset + ", Phase=" + this.mWavePhase + '}'));
        String[] arr_s = (String[])hashSet0.toArray(new String[0]);
        for(int v = 0; v < arr_s.length; ++v) {
            Utils.log((arr_s[v] + ":" + this.getValue(arr_s[v])));
        }
    }

    @Override  // androidx.constraintlayout.core.motion.key.MotionKey
    public boolean setValue(int v, float f) {
        switch(v) {
            case 304: {
                this.mTranslationX = f;
                return true;
            }
            case 305: {
                this.mTranslationY = f;
                return true;
            }
            case 306: {
                this.mTranslationZ = f;
                return true;
            }
            case 307: {
                this.mElevation = f;
                return true;
            }
            case 308: {
                this.mRotationX = f;
                return true;
            }
            case 309: {
                this.mRotationY = f;
                return true;
            }
            case 310: {
                this.mRotation = f;
                return true;
            }
            case 311: {
                this.mScaleX = f;
                return true;
            }
            case 312: {
                this.mScaleY = f;
                return true;
            }
            case 315: {
                this.mProgress = f;
                return true;
            }
            case 403: {
                this.mAlpha = f;
                return true;
            }
            case 0x1A0: {
                this.mTransitionPathRotate = f;
                return true;
            }
            case 423: {
                this.mWavePeriod = f;
                return true;
            }
            case 424: {
                this.mWaveOffset = f;
                return true;
            }
            case 425: {
                this.mWavePhase = f;
                return true;
            }
            default: {
                return super.setValue(v, f);
            }
        }
    }

    @Override  // androidx.constraintlayout.core.motion.key.MotionKey
    public boolean setValue(int v, int v1) {
        switch(v) {
            case 401: {
                this.mCurveFit = v1;
                return true;
            }
            case 421: {
                this.mWaveShape = v1;
                return true;
            }
            default: {
                return this.setValue(v, ((float)v1)) ? true : super.setValue(v, v1);
            }
        }
    }

    @Override  // androidx.constraintlayout.core.motion.key.MotionKey
    public boolean setValue(int v, String s) {
        switch(v) {
            case 420: {
                this.mTransitionEasing = s;
                return true;
            }
            case 422: {
                this.mCustomWaveShape = s;
                return true;
            }
            default: {
                return super.setValue(v, s);
            }
        }
    }
}

