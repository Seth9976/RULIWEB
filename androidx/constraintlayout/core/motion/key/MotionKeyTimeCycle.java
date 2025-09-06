package androidx.constraintlayout.core.motion.key;

import androidx.constraintlayout.core.motion.CustomVariable;
import androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet.CustomVarSet;
import androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet;
import androidx.constraintlayout.core.motion.utils.TypedValues.CycleType.-CC;
import androidx.constraintlayout.core.motion.utils.Utils;
import java.util.HashMap;
import java.util.HashSet;

public class MotionKeyTimeCycle extends MotionKey {
    public static final int KEY_TYPE = 3;
    static final String NAME = "KeyTimeCycle";
    private static final String TAG = "KeyTimeCycle";
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

    public MotionKeyTimeCycle() {
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
        this.mCustom = new HashMap();
    }

    public void addTimeValues(HashMap hashMap0) {
        for(Object object0: hashMap0.keySet()) {
            String s = (String)object0;
            TimeCycleSplineSet timeCycleSplineSet0 = (TimeCycleSplineSet)hashMap0.get(s);
            if(timeCycleSplineSet0 == null) {
            }
            else if(s.startsWith("CUSTOM")) {
                CustomVariable customVariable0 = (CustomVariable)this.mCustom.get(s.substring(7));
                if(customVariable0 == null) {
                    continue;
                }
                ((CustomVarSet)timeCycleSplineSet0).setPoint(this.mFramePosition, customVariable0, this.mWavePeriod, this.mWaveShape, this.mWaveOffset);
            }
            else {
                switch(s) {
                    case "alpha": {
                        if(Float.isNaN(this.mAlpha)) {
                            continue;
                        }
                        timeCycleSplineSet0.setPoint(this.mFramePosition, this.mAlpha, this.mWavePeriod, this.mWaveShape, this.mWaveOffset);
                        continue;
                    }
                    case "elevation": {
                        if(Float.isNaN(this.mTranslationZ)) {
                            continue;
                        }
                        timeCycleSplineSet0.setPoint(this.mFramePosition, this.mTranslationZ, this.mWavePeriod, this.mWaveShape, this.mWaveOffset);
                        continue;
                    }
                    case "pathRotate": {
                        if(!Float.isNaN(this.mTransitionPathRotate)) {
                            break;
                        }
                        continue;
                    }
                    case "progress": {
                        if(Float.isNaN(this.mProgress)) {
                            continue;
                        }
                        timeCycleSplineSet0.setPoint(this.mFramePosition, this.mProgress, this.mWavePeriod, this.mWaveShape, this.mWaveOffset);
                        continue;
                    }
                    case "rotationX": {
                        if(Float.isNaN(this.mRotationX)) {
                            continue;
                        }
                        timeCycleSplineSet0.setPoint(this.mFramePosition, this.mRotationX, this.mWavePeriod, this.mWaveShape, this.mWaveOffset);
                        continue;
                    }
                    case "rotationY": {
                        if(Float.isNaN(this.mRotationY)) {
                            continue;
                        }
                        timeCycleSplineSet0.setPoint(this.mFramePosition, this.mRotationY, this.mWavePeriod, this.mWaveShape, this.mWaveOffset);
                        continue;
                    }
                    case "rotationZ": {
                        if(Float.isNaN(this.mRotation)) {
                            continue;
                        }
                        timeCycleSplineSet0.setPoint(this.mFramePosition, this.mRotation, this.mWavePeriod, this.mWaveShape, this.mWaveOffset);
                        continue;
                    }
                    case "scaleX": {
                        if(Float.isNaN(this.mScaleX)) {
                            continue;
                        }
                        timeCycleSplineSet0.setPoint(this.mFramePosition, this.mScaleX, this.mWavePeriod, this.mWaveShape, this.mWaveOffset);
                        continue;
                    }
                    case "scaleY": {
                        if(Float.isNaN(this.mScaleY)) {
                            continue;
                        }
                        timeCycleSplineSet0.setPoint(this.mFramePosition, this.mScaleY, this.mWavePeriod, this.mWaveShape, this.mWaveOffset);
                        continue;
                    }
                    case "translationX": {
                        if(Float.isNaN(this.mTranslationX)) {
                            continue;
                        }
                        timeCycleSplineSet0.setPoint(this.mFramePosition, this.mTranslationX, this.mWavePeriod, this.mWaveShape, this.mWaveOffset);
                        continue;
                    }
                    case "translationY": {
                        if(Float.isNaN(this.mTranslationY)) {
                            continue;
                        }
                        timeCycleSplineSet0.setPoint(this.mFramePosition, this.mTranslationY, this.mWavePeriod, this.mWaveShape, this.mWaveOffset);
                        continue;
                    }
                    case "translationZ": {
                        if(Float.isNaN(this.mTranslationZ)) {
                            continue;
                        }
                        timeCycleSplineSet0.setPoint(this.mFramePosition, this.mTranslationZ, this.mWavePeriod, this.mWaveShape, this.mWaveOffset);
                        continue;
                    }
                    default: {
                        Utils.loge("KeyTimeCycles", "UNKNOWN addValues \"" + s + "\"");
                        continue;
                    }
                }
                timeCycleSplineSet0.setPoint(this.mFramePosition, this.mTransitionPathRotate, this.mWavePeriod, this.mWaveShape, this.mWaveOffset);
            }
        }
    }

    @Override  // androidx.constraintlayout.core.motion.key.MotionKey
    public void addValues(HashMap hashMap0) {
    }

    @Override  // androidx.constraintlayout.core.motion.key.MotionKey
    public MotionKey clone() {
        return new MotionKeyTimeCycle().copy(this);
    }

    @Override  // androidx.constraintlayout.core.motion.key.MotionKey
    public Object clone() throws CloneNotSupportedException {
        return this.clone();
    }

    @Override  // androidx.constraintlayout.core.motion.key.MotionKey
    public MotionKey copy(MotionKey motionKey0) {
        return this.copy(motionKey0);
    }

    public MotionKeyTimeCycle copy(MotionKey motionKey0) {
        super.copy(motionKey0);
        this.mTransitionEasing = ((MotionKeyTimeCycle)motionKey0).mTransitionEasing;
        this.mCurveFit = ((MotionKeyTimeCycle)motionKey0).mCurveFit;
        this.mWaveShape = ((MotionKeyTimeCycle)motionKey0).mWaveShape;
        this.mWavePeriod = ((MotionKeyTimeCycle)motionKey0).mWavePeriod;
        this.mWaveOffset = ((MotionKeyTimeCycle)motionKey0).mWaveOffset;
        this.mProgress = ((MotionKeyTimeCycle)motionKey0).mProgress;
        this.mAlpha = ((MotionKeyTimeCycle)motionKey0).mAlpha;
        this.mElevation = ((MotionKeyTimeCycle)motionKey0).mElevation;
        this.mRotation = ((MotionKeyTimeCycle)motionKey0).mRotation;
        this.mTransitionPathRotate = ((MotionKeyTimeCycle)motionKey0).mTransitionPathRotate;
        this.mRotationX = ((MotionKeyTimeCycle)motionKey0).mRotationX;
        this.mRotationY = ((MotionKeyTimeCycle)motionKey0).mRotationY;
        this.mScaleX = ((MotionKeyTimeCycle)motionKey0).mScaleX;
        this.mScaleY = ((MotionKeyTimeCycle)motionKey0).mScaleY;
        this.mTranslationX = ((MotionKeyTimeCycle)motionKey0).mTranslationX;
        this.mTranslationY = ((MotionKeyTimeCycle)motionKey0).mTranslationY;
        this.mTranslationZ = ((MotionKeyTimeCycle)motionKey0).mTranslationZ;
        return this;
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
        return TypedValues.CycleType.-CC.getId(s);
    }

    @Override  // androidx.constraintlayout.core.motion.key.MotionKey
    public boolean setValue(int v, float f) {
        switch(v) {
            case 304: {
                this.mTranslationX = this.toFloat(f);
                return true;
            }
            case 305: {
                this.mTranslationY = this.toFloat(f);
                return true;
            }
            case 306: {
                this.mTranslationZ = this.toFloat(f);
                return true;
            }
            case 307: {
                this.mElevation = this.toFloat(f);
                return true;
            }
            case 308: {
                this.mRotationX = this.toFloat(f);
                return true;
            }
            case 309: {
                this.mRotationY = this.toFloat(f);
                return true;
            }
            case 310: {
                this.mRotation = this.toFloat(f);
                return true;
            }
            case 311: {
                this.mScaleX = this.toFloat(f);
                return true;
            }
            case 312: {
                this.mScaleY = this.toFloat(f);
                return true;
            }
            case 315: {
                this.mProgress = this.toFloat(f);
                return true;
            }
            case 401: {
                this.mCurveFit = this.toInt(f);
                return true;
            }
            case 403: {
                this.mAlpha = f;
                return true;
            }
            case 0x1A0: {
                this.mTransitionPathRotate = this.toFloat(f);
                return true;
            }
            case 423: {
                this.mWavePeriod = this.toFloat(f);
                return true;
            }
            case 424: {
                this.mWaveOffset = this.toFloat(f);
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
            case 100: {
                this.mFramePosition = v1;
                return true;
            }
            case 421: {
                this.mWaveShape = v1;
                return true;
            }
            default: {
                return super.setValue(v, v1);
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
            case 421: {
                this.mWaveShape = 7;
                this.mCustomWaveShape = s;
                return true;
            }
            default: {
                return super.setValue(v, s);
            }
        }
    }

    @Override  // androidx.constraintlayout.core.motion.key.MotionKey
    public boolean setValue(int v, boolean z) {
        return super.setValue(v, z);
    }
}

