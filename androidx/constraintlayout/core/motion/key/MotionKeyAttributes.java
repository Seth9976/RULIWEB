package androidx.constraintlayout.core.motion.key;

import androidx.constraintlayout.core.motion.CustomVariable;
import androidx.constraintlayout.core.motion.utils.SplineSet.CustomSpline;
import androidx.constraintlayout.core.motion.utils.SplineSet;
import androidx.constraintlayout.core.motion.utils.TypedValues.AttributesType.-CC;
import java.util.HashMap;
import java.util.HashSet;

public class MotionKeyAttributes extends MotionKey {
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
    private int mVisibility;

    public MotionKeyAttributes() {
        this.mCurveFit = -1;
        this.mVisibility = 0;
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
        this.mCustom = new HashMap();
    }

    @Override  // androidx.constraintlayout.core.motion.key.MotionKey
    public void addValues(HashMap hashMap0) {
        for(Object object0: hashMap0.keySet()) {
            String s = (String)object0;
            SplineSet splineSet0 = (SplineSet)hashMap0.get(s);
            if(splineSet0 == null) {
            }
            else if(s.startsWith("CUSTOM")) {
                CustomVariable customVariable0 = (CustomVariable)this.mCustom.get(s.substring(7));
                if(customVariable0 == null) {
                    continue;
                }
                ((CustomSpline)splineSet0).setPoint(this.mFramePosition, customVariable0);
            }
            else {
                switch(s) {
                    case "alpha": {
                        if(Float.isNaN(this.mAlpha)) {
                            continue;
                        }
                        splineSet0.setPoint(this.mFramePosition, this.mAlpha);
                        continue;
                    }
                    case "elevation": {
                        if(Float.isNaN(this.mElevation)) {
                            continue;
                        }
                        splineSet0.setPoint(this.mFramePosition, this.mElevation);
                        continue;
                    }
                    case "pathRotate": {
                        if(!Float.isNaN(this.mTransitionPathRotate)) {
                            break;
                        }
                        continue;
                    }
                    case "pivotX": {
                        if(Float.isNaN(this.mRotationX)) {
                            continue;
                        }
                        splineSet0.setPoint(this.mFramePosition, this.mPivotX);
                        continue;
                    }
                    case "pivotY": {
                        if(Float.isNaN(this.mRotationY)) {
                            continue;
                        }
                        splineSet0.setPoint(this.mFramePosition, this.mPivotY);
                        continue;
                    }
                    case "progress": {
                        if(Float.isNaN(this.mProgress)) {
                            continue;
                        }
                        splineSet0.setPoint(this.mFramePosition, this.mProgress);
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
                    case "rotationZ": {
                        if(Float.isNaN(this.mRotation)) {
                            continue;
                        }
                        splineSet0.setPoint(this.mFramePosition, this.mRotation);
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
                        System.err.println("not supported by KeyAttributes " + s);
                        continue;
                    }
                }
                splineSet0.setPoint(this.mFramePosition, this.mTransitionPathRotate);
            }
        }
    }

    @Override  // androidx.constraintlayout.core.motion.key.MotionKey
    public MotionKey clone() [...] // Inlined contents

    @Override  // androidx.constraintlayout.core.motion.key.MotionKey
    public Object clone() throws CloneNotSupportedException {
        return null;
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
        if(!Float.isNaN(this.mPivotX)) {
            hashSet0.add("pivotX");
        }
        if(!Float.isNaN(this.mPivotY)) {
            hashSet0.add("pivotY");
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
            hashSet0.add("pathRotate");
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
        if(this.mCustom.size() > 0) {
            for(Object object0: this.mCustom.keySet()) {
                hashSet0.add("CUSTOM," + ((String)object0));
            }
        }
    }

    public int getCurveFit() {
        return this.mCurveFit;
    }

    private float getFloatValue(int v) {
        if(v != 100) {
            switch(v) {
                case 303: {
                    return this.mAlpha;
                }
                case 304: {
                    return this.mTranslationX;
                }
                case 305: {
                    return this.mTranslationY;
                }
                case 306: {
                    return this.mTranslationZ;
                }
                case 307: {
                    return this.mElevation;
                }
                case 308: {
                    return this.mRotationX;
                }
                case 309: {
                    return this.mRotationY;
                }
                case 310: {
                    return this.mRotation;
                }
                case 311: {
                    return this.mScaleX;
                }
                case 312: {
                    return this.mScaleY;
                }
                case 313: {
                    return this.mPivotX;
                }
                case 314: {
                    return this.mPivotY;
                }
                case 315: {
                    return this.mProgress;
                }
                case 316: {
                    return this.mTransitionPathRotate;
                }
                default: {
                    return NaNf;
                }
            }
        }
        return (float)this.mFramePosition;
    }

    @Override  // androidx.constraintlayout.core.motion.utils.TypedValues
    public int getId(String s) {
        return TypedValues.AttributesType.-CC.getId(s);
    }

    public void printAttributes() {
        HashSet hashSet0 = new HashSet();
        this.getAttributeNames(hashSet0);
        System.out.println(" ------------- " + this.mFramePosition + " -------------");
        String[] arr_s = (String[])hashSet0.toArray(new String[0]);
        for(int v = 0; v < arr_s.length; ++v) {
            System.out.println(arr_s[v] + ":" + this.getFloatValue(TypedValues.AttributesType.-CC.getId(arr_s[v])));
        }
    }

    @Override  // androidx.constraintlayout.core.motion.key.MotionKey
    public void setInterpolation(HashMap hashMap0) {
        if(!Float.isNaN(this.mAlpha)) {
            hashMap0.put("alpha", this.mCurveFit);
        }
        if(!Float.isNaN(this.mElevation)) {
            hashMap0.put("elevation", this.mCurveFit);
        }
        if(!Float.isNaN(this.mRotation)) {
            hashMap0.put("rotationZ", this.mCurveFit);
        }
        if(!Float.isNaN(this.mRotationX)) {
            hashMap0.put("rotationX", this.mCurveFit);
        }
        if(!Float.isNaN(this.mRotationY)) {
            hashMap0.put("rotationY", this.mCurveFit);
        }
        if(!Float.isNaN(this.mPivotX)) {
            hashMap0.put("pivotX", this.mCurveFit);
        }
        if(!Float.isNaN(this.mPivotY)) {
            hashMap0.put("pivotY", this.mCurveFit);
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
            hashMap0.put("pathRotate", this.mCurveFit);
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
        if(this.mCustom.size() > 0) {
            for(Object object0: this.mCustom.keySet()) {
                hashMap0.put("CUSTOM," + ((String)object0), this.mCurveFit);
            }
        }
    }

    @Override  // androidx.constraintlayout.core.motion.key.MotionKey
    public boolean setValue(int v, float f) {
        if(v != 100) {
            switch(v) {
                case 303: {
                    this.mAlpha = f;
                    return true;
                }
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
                case 313: {
                    this.mPivotX = f;
                    return true;
                }
                case 314: {
                    this.mPivotY = f;
                    return true;
                }
                case 315: {
                    this.mProgress = f;
                    return true;
                }
                case 316: {
                    this.mTransitionPathRotate = f;
                    return true;
                }
                default: {
                    return super.setValue(v, f);
                }
            }
        }
        this.mTransitionPathRotate = f;
        return true;
    }

    @Override  // androidx.constraintlayout.core.motion.key.MotionKey
    public boolean setValue(int v, int v1) {
        switch(v) {
            case 100: {
                this.mFramePosition = v1;
                return true;
            }
            case 301: {
                this.mCurveFit = v1;
                return true;
            }
            case 302: {
                this.mVisibility = v1;
                return true;
            }
            default: {
                return this.setValue(v, v1) ? true : super.setValue(v, v1);
            }
        }
    }

    @Override  // androidx.constraintlayout.core.motion.key.MotionKey
    public boolean setValue(int v, String s) {
        switch(v) {
            case 101: {
                this.mTargetString = s;
                return true;
            }
            case 317: {
                this.mTransitionEasing = s;
                return true;
            }
            default: {
                return super.setValue(v, s);
            }
        }
    }
}

