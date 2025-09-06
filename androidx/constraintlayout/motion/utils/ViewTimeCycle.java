package androidx.constraintlayout.motion.utils;

import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.CurveFit;
import androidx.constraintlayout.core.motion.utils.KeyCache;
import androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintAttribute;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class ViewTimeCycle extends TimeCycleSplineSet {
    static class AlphaSet extends ViewTimeCycle {
        @Override  // androidx.constraintlayout.motion.utils.ViewTimeCycle
        public boolean setProperty(View view0, float f, long v, KeyCache keyCache0) {
            view0.setAlpha(this.get(f, v, view0, keyCache0));
            return this.mContinue;
        }
    }

    public static class CustomSet extends ViewTimeCycle {
        String mAttributeName;
        SparseArray mConstraintAttributeList;
        float[] mTempValues;
        SparseArray mWaveProperties;

        public CustomSet(String s, SparseArray sparseArray0) {
            this.mWaveProperties = new SparseArray();
            this.mAttributeName = s.split(",")[1];
            this.mConstraintAttributeList = sparseArray0;
        }

        @Override  // androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet
        public void setPoint(int v, float f, float f1, int v1, float f2) {
            throw new RuntimeException("Wrong call for custom attribute");
        }

        public void setPoint(int v, ConstraintAttribute constraintAttribute0, float f, int v1, float f1) {
            this.mConstraintAttributeList.append(v, constraintAttribute0);
            this.mWaveProperties.append(v, new float[]{f, f1});
            this.mWaveShape = Math.max(this.mWaveShape, v1);
        }

        @Override  // androidx.constraintlayout.motion.utils.ViewTimeCycle
        public boolean setProperty(View view0, float f, long v, KeyCache keyCache0) {
            this.mCurveFit.getPos(((double)f), this.mTempValues);
            float[] arr_f = this.mTempValues;
            float f1 = arr_f[arr_f.length - 2];
            float f2 = arr_f[arr_f.length - 1];
            long v1 = v - this.mLastTime;
            if(Float.isNaN(this.mLastCycle)) {
                this.mLastCycle = keyCache0.getFloatValue(view0, this.mAttributeName, 0);
                if(Float.isNaN(this.mLastCycle)) {
                    this.mLastCycle = 0.0f;
                }
            }
            this.mLastCycle = (float)((((double)this.mLastCycle) + ((double)v1) * 1.000000E-09 * ((double)f1)) % 1.0);
            this.mLastTime = v;
            float f3 = this.calcWave(this.mLastCycle);
            this.mContinue = false;
            for(int v2 = 0; v2 < this.mCache.length; ++v2) {
                this.mContinue |= ((double)this.mTempValues[v2]) != 0.0;
                this.mCache[v2] = this.mTempValues[v2] * f3 + f2;
            }
            CustomSupport.setInterpolatedValue(((ConstraintAttribute)this.mConstraintAttributeList.valueAt(0)), view0, this.mCache);
            if(f1 != 0.0f) {
                this.mContinue = true;
            }
            return this.mContinue;
        }

        @Override  // androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet
        public void setup(int v) {
            int v1 = this.mConstraintAttributeList.size();
            int v2 = ((ConstraintAttribute)this.mConstraintAttributeList.valueAt(0)).numberOfInterpolatedValues();
            double[] arr_f = new double[v1];
            this.mTempValues = new float[v2 + 2];
            this.mCache = new float[v2];
            double[][] arr2_f = new double[v1][v2 + 2];
            for(int v3 = 0; v3 < v1; ++v3) {
                int v4 = this.mConstraintAttributeList.keyAt(v3);
                ConstraintAttribute constraintAttribute0 = (ConstraintAttribute)this.mConstraintAttributeList.valueAt(v3);
                float[] arr_f1 = (float[])this.mWaveProperties.valueAt(v3);
                arr_f[v3] = ((double)v4) * 0.01;
                constraintAttribute0.getValuesToInterpolate(this.mTempValues);
                for(int v5 = 0; true; ++v5) {
                    float[] arr_f2 = this.mTempValues;
                    if(v5 >= arr_f2.length) {
                        break;
                    }
                    arr2_f[v3][v5] = (double)arr_f2[v5];
                }
                double[] arr_f3 = arr2_f[v3];
                arr_f3[v2] = (double)arr_f1[0];
                arr_f3[v2 + 1] = (double)arr_f1[1];
            }
            this.mCurveFit = CurveFit.get(v, arr_f, arr2_f);
        }
    }

    static class ElevationSet extends ViewTimeCycle {
        @Override  // androidx.constraintlayout.motion.utils.ViewTimeCycle
        public boolean setProperty(View view0, float f, long v, KeyCache keyCache0) {
            view0.setElevation(this.get(f, v, view0, keyCache0));
            return this.mContinue;
        }
    }

    public static class PathRotate extends ViewTimeCycle {
        public boolean setPathRotate(View view0, KeyCache keyCache0, float f, long v, double f1, double f2) {
            view0.setRotation(this.get(f, v, view0, keyCache0) + ((float)Math.toDegrees(Math.atan2(f2, f1))));
            return this.mContinue;
        }

        @Override  // androidx.constraintlayout.motion.utils.ViewTimeCycle
        public boolean setProperty(View view0, float f, long v, KeyCache keyCache0) {
            return this.mContinue;
        }
    }

    static class ProgressSet extends ViewTimeCycle {
        boolean mNoMethod;

        ProgressSet() {
            this.mNoMethod = false;
        }

        @Override  // androidx.constraintlayout.motion.utils.ViewTimeCycle
        public boolean setProperty(View view0, float f, long v, KeyCache keyCache0) {
            Method method0;
            if(view0 instanceof MotionLayout) {
                ((MotionLayout)view0).setProgress(this.get(f, v, view0, keyCache0));
                return this.mContinue;
            }
            if(this.mNoMethod) {
                return false;
            }
            try {
                method0 = view0.getClass().getMethod("setProgress", Float.TYPE);
            }
            catch(NoSuchMethodException unused_ex) {
                this.mNoMethod = true;
                method0 = null;
            }
            if(method0 != null) {
                try {
                    method0.invoke(view0, this.get(f, v, view0, keyCache0));
                }
                catch(IllegalAccessException illegalAccessException0) {
                    Log.e("ViewTimeCycle", "unable to setProgress", illegalAccessException0);
                }
                catch(InvocationTargetException invocationTargetException0) {
                    Log.e("ViewTimeCycle", "unable to setProgress", invocationTargetException0);
                }
                return this.mContinue;
            }
            return this.mContinue;
        }
    }

    static class RotationSet extends ViewTimeCycle {
        @Override  // androidx.constraintlayout.motion.utils.ViewTimeCycle
        public boolean setProperty(View view0, float f, long v, KeyCache keyCache0) {
            view0.setRotation(this.get(f, v, view0, keyCache0));
            return this.mContinue;
        }
    }

    static class RotationXset extends ViewTimeCycle {
        @Override  // androidx.constraintlayout.motion.utils.ViewTimeCycle
        public boolean setProperty(View view0, float f, long v, KeyCache keyCache0) {
            view0.setRotationX(this.get(f, v, view0, keyCache0));
            return this.mContinue;
        }
    }

    static class RotationYset extends ViewTimeCycle {
        @Override  // androidx.constraintlayout.motion.utils.ViewTimeCycle
        public boolean setProperty(View view0, float f, long v, KeyCache keyCache0) {
            view0.setRotationY(this.get(f, v, view0, keyCache0));
            return this.mContinue;
        }
    }

    static class ScaleXset extends ViewTimeCycle {
        @Override  // androidx.constraintlayout.motion.utils.ViewTimeCycle
        public boolean setProperty(View view0, float f, long v, KeyCache keyCache0) {
            view0.setScaleX(this.get(f, v, view0, keyCache0));
            return this.mContinue;
        }
    }

    static class ScaleYset extends ViewTimeCycle {
        @Override  // androidx.constraintlayout.motion.utils.ViewTimeCycle
        public boolean setProperty(View view0, float f, long v, KeyCache keyCache0) {
            view0.setScaleY(this.get(f, v, view0, keyCache0));
            return this.mContinue;
        }
    }

    static class TranslationXset extends ViewTimeCycle {
        @Override  // androidx.constraintlayout.motion.utils.ViewTimeCycle
        public boolean setProperty(View view0, float f, long v, KeyCache keyCache0) {
            view0.setTranslationX(this.get(f, v, view0, keyCache0));
            return this.mContinue;
        }
    }

    static class TranslationYset extends ViewTimeCycle {
        @Override  // androidx.constraintlayout.motion.utils.ViewTimeCycle
        public boolean setProperty(View view0, float f, long v, KeyCache keyCache0) {
            view0.setTranslationY(this.get(f, v, view0, keyCache0));
            return this.mContinue;
        }
    }

    static class TranslationZset extends ViewTimeCycle {
        @Override  // androidx.constraintlayout.motion.utils.ViewTimeCycle
        public boolean setProperty(View view0, float f, long v, KeyCache keyCache0) {
            view0.setTranslationZ(this.get(f, v, view0, keyCache0));
            return this.mContinue;
        }
    }

    private static final String TAG = "ViewTimeCycle";

    public float get(float f, long v, View view0, KeyCache keyCache0) {
        this.mCurveFit.getPos(((double)f), this.mCache);
        float f1 = this.mCache[1];
        int v1 = Float.compare(f1, 0.0f);
        if(v1 == 0) {
            this.mContinue = false;
            return this.mCache[2];
        }
        if(Float.isNaN(this.mLastCycle)) {
            this.mLastCycle = keyCache0.getFloatValue(view0, this.mType, 0);
            if(Float.isNaN(this.mLastCycle)) {
                this.mLastCycle = 0.0f;
            }
        }
        this.mLastCycle = (float)((((double)this.mLastCycle) + ((double)(v - this.mLastTime)) * 1.000000E-09 * ((double)f1)) % 1.0);
        keyCache0.setFloatValue(view0, this.mType, 0, this.mLastCycle);
        this.mLastTime = v;
        float f2 = this.mCache[0];
        float f3 = this.calcWave(this.mLastCycle) * f2 + this.mCache[2];
        this.mContinue = f2 != 0.0f || v1 != 0;
        return f3;
    }

    public static ViewTimeCycle makeCustomSpline(String s, SparseArray sparseArray0) {
        return new CustomSet(s, sparseArray0);
    }

    public static ViewTimeCycle makeSpline(String s, long v) {
        ViewTimeCycle viewTimeCycle0;
        s.hashCode();
        switch(s) {
            case "alpha": {
                viewTimeCycle0 = new AlphaSet();
                break;
            }
            case "elevation": {
                viewTimeCycle0 = new ElevationSet();
                break;
            }
            case "progress": {
                viewTimeCycle0 = new ProgressSet();
                break;
            }
            case "rotation": {
                viewTimeCycle0 = new RotationSet();
                break;
            }
            case "rotationX": {
                viewTimeCycle0 = new RotationXset();
                break;
            }
            case "rotationY": {
                viewTimeCycle0 = new RotationYset();
                break;
            }
            case "scaleX": {
                viewTimeCycle0 = new ScaleXset();
                break;
            }
            case "scaleY": {
                viewTimeCycle0 = new ScaleYset();
                break;
            }
            case "transitionPathRotate": {
                viewTimeCycle0 = new PathRotate();
                break;
            }
            case "translationX": {
                viewTimeCycle0 = new TranslationXset();
                break;
            }
            case "translationY": {
                viewTimeCycle0 = new TranslationYset();
                break;
            }
            case "translationZ": {
                viewTimeCycle0 = new TranslationZset();
                break;
            }
            default: {
                return null;
            }
        }
        viewTimeCycle0.setStartTime(v);
        return viewTimeCycle0;
    }

    public abstract boolean setProperty(View arg1, float arg2, long arg3, KeyCache arg4);
}

