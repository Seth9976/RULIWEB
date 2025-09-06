package androidx.constraintlayout.core.motion.utils;

import androidx.constraintlayout.core.motion.CustomAttribute;
import androidx.constraintlayout.core.motion.CustomVariable;
import androidx.constraintlayout.core.motion.MotionWidget;
import java.text.DecimalFormat;

public abstract class TimeCycleSplineSet {
    public static class CustomSet extends TimeCycleSplineSet {
        String mAttributeName;
        CustomArray mConstraintAttributeList;
        float[] mCustomCache;
        float[] mTempValues;
        FloatArray mWaveProperties;

        public CustomSet(String s, CustomArray keyFrameArray$CustomArray0) {
            this.mWaveProperties = new FloatArray();
            this.mAttributeName = s.split(",")[1];
            this.mConstraintAttributeList = keyFrameArray$CustomArray0;
        }

        @Override  // androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet
        public void setPoint(int v, float f, float f1, int v1, float f2) {
            throw new RuntimeException("don\'t call for custom attribute call setPoint(pos, ConstraintAttribute,...)");
        }

        public void setPoint(int v, CustomAttribute customAttribute0, float f, int v1, float f1) {
            this.mConstraintAttributeList.append(v, customAttribute0);
            this.mWaveProperties.append(v, new float[]{f, f1});
            this.mWaveShape = Math.max(this.mWaveShape, v1);
        }

        public boolean setProperty(MotionWidget motionWidget0, float f, long v, KeyCache keyCache0) {
            this.mCurveFit.getPos(((double)f), this.mTempValues);
            float[] arr_f = this.mTempValues;
            float f1 = arr_f[arr_f.length - 2];
            float f2 = arr_f[arr_f.length - 1];
            long v1 = v - this.mLastTime;
            if(Float.isNaN(this.mLastCycle)) {
                this.mLastCycle = keyCache0.getFloatValue(motionWidget0, this.mAttributeName, 0);
                if(Float.isNaN(this.mLastCycle)) {
                    this.mLastCycle = 0.0f;
                }
            }
            this.mLastCycle = (float)((((double)this.mLastCycle) + ((double)v1) * 1.000000E-09 * ((double)f1)) % 1.0);
            this.mLastTime = v;
            float f3 = this.calcWave(this.mLastCycle);
            this.mContinue = false;
            for(int v2 = 0; v2 < this.mCustomCache.length; ++v2) {
                this.mContinue |= ((double)this.mTempValues[v2]) != 0.0;
                this.mCustomCache[v2] = this.mTempValues[v2] * f3 + f2;
            }
            motionWidget0.setInterpolatedValue(this.mConstraintAttributeList.valueAt(0), this.mCustomCache);
            if(f1 != 0.0f) {
                this.mContinue = true;
            }
            return this.mContinue;
        }

        @Override  // androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet
        public void setup(int v) {
            int v1 = this.mConstraintAttributeList.size();
            int v2 = this.mConstraintAttributeList.valueAt(0).numberOfInterpolatedValues();
            double[] arr_f = new double[v1];
            this.mTempValues = new float[v2 + 2];
            this.mCustomCache = new float[v2];
            double[][] arr2_f = new double[v1][v2 + 2];
            for(int v3 = 0; v3 < v1; ++v3) {
                int v4 = this.mConstraintAttributeList.keyAt(v3);
                CustomAttribute customAttribute0 = this.mConstraintAttributeList.valueAt(v3);
                float[] arr_f1 = this.mWaveProperties.valueAt(v3);
                arr_f[v3] = ((double)v4) * 0.01;
                customAttribute0.getValuesToInterpolate(this.mTempValues);
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

    public static class CustomVarSet extends TimeCycleSplineSet {
        String mAttributeName;
        CustomVar mConstraintAttributeList;
        float[] mCustomCache;
        float[] mTempValues;
        FloatArray mWaveProperties;

        public CustomVarSet(String s, CustomVar keyFrameArray$CustomVar0) {
            this.mWaveProperties = new FloatArray();
            this.mAttributeName = s.split(",")[1];
            this.mConstraintAttributeList = keyFrameArray$CustomVar0;
        }

        @Override  // androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet
        public void setPoint(int v, float f, float f1, int v1, float f2) {
            throw new RuntimeException("don\'t call for custom attribute call setPoint(pos, ConstraintAttribute,...)");
        }

        public void setPoint(int v, CustomVariable customVariable0, float f, int v1, float f1) {
            this.mConstraintAttributeList.append(v, customVariable0);
            this.mWaveProperties.append(v, new float[]{f, f1});
            this.mWaveShape = Math.max(this.mWaveShape, v1);
        }

        public boolean setProperty(MotionWidget motionWidget0, float f, long v, KeyCache keyCache0) {
            this.mCurveFit.getPos(((double)f), this.mTempValues);
            float[] arr_f = this.mTempValues;
            float f1 = arr_f[arr_f.length - 2];
            float f2 = arr_f[arr_f.length - 1];
            long v1 = v - this.mLastTime;
            if(Float.isNaN(this.mLastCycle)) {
                this.mLastCycle = keyCache0.getFloatValue(motionWidget0, this.mAttributeName, 0);
                if(Float.isNaN(this.mLastCycle)) {
                    this.mLastCycle = 0.0f;
                }
            }
            this.mLastCycle = (float)((((double)this.mLastCycle) + ((double)v1) * 1.000000E-09 * ((double)f1)) % 1.0);
            this.mLastTime = v;
            float f3 = this.calcWave(this.mLastCycle);
            this.mContinue = false;
            for(int v2 = 0; v2 < this.mCustomCache.length; ++v2) {
                this.mContinue |= ((double)this.mTempValues[v2]) != 0.0;
                this.mCustomCache[v2] = this.mTempValues[v2] * f3 + f2;
            }
            this.mConstraintAttributeList.valueAt(0).setInterpolatedValue(motionWidget0, this.mCustomCache);
            if(f1 != 0.0f) {
                this.mContinue = true;
            }
            return this.mContinue;
        }

        @Override  // androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet
        public void setup(int v) {
            int v1 = this.mConstraintAttributeList.size();
            int v2 = this.mConstraintAttributeList.valueAt(0).numberOfInterpolatedValues();
            double[] arr_f = new double[v1];
            this.mTempValues = new float[v2 + 2];
            this.mCustomCache = new float[v2];
            double[][] arr2_f = new double[v1][v2 + 2];
            for(int v3 = 0; v3 < v1; ++v3) {
                int v4 = this.mConstraintAttributeList.keyAt(v3);
                CustomVariable customVariable0 = this.mConstraintAttributeList.valueAt(v3);
                float[] arr_f1 = this.mWaveProperties.valueAt(v3);
                arr_f[v3] = ((double)v4) * 0.01;
                customVariable0.getValuesToInterpolate(this.mTempValues);
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

    public static class Sort {
        static void doubleQuickSort(int[] arr_v, float[][] arr2_f, int v, int v1) {
            int[] arr_v1 = new int[arr_v.length + 10];
            arr_v1[0] = v1;
            arr_v1[1] = v;
            int v2 = 2;
            while(v2 > 0) {
                int v3 = arr_v1[v2 - 1];
                int v4 = arr_v1[v2 - 2];
                if(v3 < v4) {
                    int v5 = Sort.partition(arr_v, arr2_f, v3, v4);
                    arr_v1[v2 - 2] = v5 - 1;
                    arr_v1[v2 - 1] = v3;
                    int v6 = v2 + 1;
                    arr_v1[v2] = v4;
                    v2 += 2;
                    arr_v1[v6] = v5 + 1;
                }
                else {
                    v2 -= 2;
                }
            }
        }

        private static int partition(int[] arr_v, float[][] arr2_f, int v, int v1) {
            int v2 = arr_v[v1];
            int v3 = v;
            while(v < v1) {
                if(arr_v[v] <= v2) {
                    Sort.swap(arr_v, arr2_f, v3, v);
                    ++v3;
                }
                ++v;
            }
            Sort.swap(arr_v, arr2_f, v3, v1);
            return v3;
        }

        private static void swap(int[] arr_v, float[][] arr2_f, int v, int v1) {
            int v2 = arr_v[v];
            arr_v[v] = arr_v[v1];
            arr_v[v1] = v2;
            float[] arr_f = arr2_f[v];
            arr2_f[v] = arr2_f[v1];
            arr2_f[v1] = arr_f;
        }
    }

    protected static final int CURVE_OFFSET = 2;
    protected static final int CURVE_PERIOD = 1;
    protected static final int CURVE_VALUE = 0;
    private static final String TAG = "SplineSet";
    protected float[] mCache;
    protected boolean mContinue;
    protected int mCount;
    protected CurveFit mCurveFit;
    protected float mLastCycle;
    protected long mLastTime;
    protected int[] mTimePoints;
    protected String mType;
    protected float[][] mValues;
    protected int mWaveShape;
    protected static float sVal2PI = 6.283185f;

    static {
    }

    public TimeCycleSplineSet() {
        this.mWaveShape = 0;
        this.mTimePoints = new int[10];
        this.mValues = new float[10][3];
        this.mCache = new float[3];
        this.mContinue = false;
        this.mLastCycle = NaNf;
    }

    protected float calcWave(float f) {
        switch(this.mWaveShape) {
            case 1: {
                return Math.signum(f * TimeCycleSplineSet.sVal2PI);
            }
            case 2: {
                return 1.0f - Math.abs(f);
            }
            case 3: {
                return (f * 2.0f + 1.0f) % 2.0f - 1.0f;
            }
            case 4: {
                return 1.0f - (f * 2.0f + 1.0f) % 2.0f;
            }
            case 5: {
                return (float)Math.cos(f * TimeCycleSplineSet.sVal2PI);
            }
            case 6: {
                float f1 = Math.abs(f * 4.0f % 4.0f - 2.0f);
                return 1.0f - (1.0f - f1) * (1.0f - f1);
            }
            default: {
                return (float)Math.sin(f * TimeCycleSplineSet.sVal2PI);
            }
        }
    }

    public CurveFit getCurveFit() {
        return this.mCurveFit;
    }

    public void setPoint(int v, float f, float f1, int v1, float f2) {
        int v2 = this.mCount;
        this.mTimePoints[v2] = v;
        float[] arr_f = this.mValues[v2];
        arr_f[0] = f;
        arr_f[1] = f1;
        arr_f[2] = f2;
        this.mWaveShape = Math.max(this.mWaveShape, v1);
        ++this.mCount;
    }

    protected void setStartTime(long v) {
        this.mLastTime = v;
    }

    public void setType(String s) {
        this.mType = s;
    }

    public void setup(int v) {
        int v1 = this.mCount;
        if(v1 == 0) {
            System.err.println("Error no points added to " + this.mType);
            return;
        }
        Sort.doubleQuickSort(this.mTimePoints, this.mValues, 0, v1 - 1);
        int v3 = 0;
        for(int v2 = 1; true; ++v2) {
            int[] arr_v = this.mTimePoints;
            if(v2 >= arr_v.length) {
                break;
            }
            if(arr_v[v2] != arr_v[v2 - 1]) {
                ++v3;
            }
        }
        if(v3 == 0) {
            v3 = 1;
        }
        double[] arr_f = new double[v3];
        double[][] arr2_f = new double[v3][3];
        int v5 = 0;
        for(int v4 = 0; v4 < this.mCount; ++v4) {
            if(v4 <= 0 || this.mTimePoints[v4] != this.mTimePoints[v4 - 1]) {
                arr_f[v5] = ((double)this.mTimePoints[v4]) * 0.01;
                double[] arr_f1 = arr2_f[v5];
                float[] arr_f2 = this.mValues[v4];
                arr_f1[0] = (double)arr_f2[0];
                arr_f1[1] = (double)arr_f2[1];
                arr_f1[2] = (double)arr_f2[2];
                ++v5;
            }
        }
        this.mCurveFit = CurveFit.get(v, arr_f, arr2_f);
    }

    @Override
    public String toString() {
        String s = this.mType;
        DecimalFormat decimalFormat0 = new DecimalFormat("##.##");
        for(int v = 0; v < this.mCount; ++v) {
            s = s + "[" + this.mTimePoints[v] + " , " + decimalFormat0.format(this.mValues[v]) + "] ";
        }
        return s;
    }
}

