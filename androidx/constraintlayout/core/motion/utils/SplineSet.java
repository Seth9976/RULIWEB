package androidx.constraintlayout.core.motion.utils;

import androidx.constraintlayout.core.motion.CustomAttribute;
import androidx.constraintlayout.core.motion.CustomVariable;
import androidx.constraintlayout.core.motion.MotionWidget;
import androidx.constraintlayout.core.state.WidgetFrame;
import java.text.DecimalFormat;
import java.util.Arrays;

public abstract class SplineSet {
    static class CoreSpline extends SplineSet {
        long mStart;
        String mType;

        CoreSpline(String s, long v) {
            this.mType = s;
            this.mStart = v;
        }

        @Override  // androidx.constraintlayout.core.motion.utils.SplineSet
        public void setProperty(TypedValues typedValues0, float f) {
            typedValues0.setValue(typedValues0.getId(this.mType), this.get(f));
        }
    }

    public static class CustomSet extends SplineSet {
        String mAttributeName;
        CustomArray mConstraintAttributeList;
        float[] mTempValues;

        public CustomSet(String s, CustomArray keyFrameArray$CustomArray0) {
            this.mAttributeName = s.split(",")[1];
            this.mConstraintAttributeList = keyFrameArray$CustomArray0;
        }

        @Override  // androidx.constraintlayout.core.motion.utils.SplineSet
        public void setPoint(int v, float f) {
            throw new RuntimeException("don\'t call for custom attribute call setPoint(pos, ConstraintAttribute)");
        }

        public void setPoint(int v, CustomAttribute customAttribute0) {
            this.mConstraintAttributeList.append(v, customAttribute0);
        }

        public void setProperty(WidgetFrame widgetFrame0, float f) {
            this.mCurveFit.getPos(((double)f), this.mTempValues);
            widgetFrame0.setCustomValue(this.mConstraintAttributeList.valueAt(0), this.mTempValues);
        }

        @Override  // androidx.constraintlayout.core.motion.utils.SplineSet
        public void setup(int v) {
            int v1 = this.mConstraintAttributeList.size();
            int v2 = this.mConstraintAttributeList.valueAt(0).numberOfInterpolatedValues();
            double[] arr_f = new double[v1];
            this.mTempValues = new float[v2];
            double[][] arr2_f = new double[v1][v2];
            for(int v3 = 0; v3 < v1; ++v3) {
                int v4 = this.mConstraintAttributeList.keyAt(v3);
                CustomAttribute customAttribute0 = this.mConstraintAttributeList.valueAt(v3);
                arr_f[v3] = ((double)v4) * 0.01;
                customAttribute0.getValuesToInterpolate(this.mTempValues);
                for(int v5 = 0; true; ++v5) {
                    float[] arr_f1 = this.mTempValues;
                    if(v5 >= arr_f1.length) {
                        break;
                    }
                    arr2_f[v3][v5] = (double)arr_f1[v5];
                }
            }
            this.mCurveFit = CurveFit.get(v, arr_f, arr2_f);
        }
    }

    public static class CustomSpline extends SplineSet {
        String mAttributeName;
        CustomVar mConstraintAttributeList;
        float[] mTempValues;

        public CustomSpline(String s, CustomVar keyFrameArray$CustomVar0) {
            this.mAttributeName = s.split(",")[1];
            this.mConstraintAttributeList = keyFrameArray$CustomVar0;
        }

        @Override  // androidx.constraintlayout.core.motion.utils.SplineSet
        public void setPoint(int v, float f) {
            throw new RuntimeException("don\'t call for custom attribute call setPoint(pos, ConstraintAttribute)");
        }

        public void setPoint(int v, CustomVariable customVariable0) {
            this.mConstraintAttributeList.append(v, customVariable0);
        }

        public void setProperty(MotionWidget motionWidget0, float f) {
            this.mCurveFit.getPos(((double)f), this.mTempValues);
            this.mConstraintAttributeList.valueAt(0).setInterpolatedValue(motionWidget0, this.mTempValues);
        }

        @Override  // androidx.constraintlayout.core.motion.utils.SplineSet
        public void setProperty(TypedValues typedValues0, float f) {
            this.setProperty(((MotionWidget)typedValues0), f);
        }

        @Override  // androidx.constraintlayout.core.motion.utils.SplineSet
        public void setup(int v) {
            int v1 = this.mConstraintAttributeList.size();
            int v2 = this.mConstraintAttributeList.valueAt(0).numberOfInterpolatedValues();
            double[] arr_f = new double[v1];
            this.mTempValues = new float[v2];
            double[][] arr2_f = new double[v1][v2];
            for(int v3 = 0; v3 < v1; ++v3) {
                int v4 = this.mConstraintAttributeList.keyAt(v3);
                CustomVariable customVariable0 = this.mConstraintAttributeList.valueAt(v3);
                arr_f[v3] = ((double)v4) * 0.01;
                customVariable0.getValuesToInterpolate(this.mTempValues);
                for(int v5 = 0; true; ++v5) {
                    float[] arr_f1 = this.mTempValues;
                    if(v5 >= arr_f1.length) {
                        break;
                    }
                    arr2_f[v3][v5] = (double)arr_f1[v5];
                }
            }
            this.mCurveFit = CurveFit.get(v, arr_f, arr2_f);
        }
    }

    static class Sort {
        static void doubleQuickSort(int[] arr_v, float[] arr_f, int v, int v1) {
            int[] arr_v1 = new int[arr_v.length + 10];
            arr_v1[0] = v1;
            arr_v1[1] = v;
            int v2 = 2;
            while(v2 > 0) {
                int v3 = arr_v1[v2 - 1];
                int v4 = arr_v1[v2 - 2];
                if(v3 < v4) {
                    int v5 = Sort.partition(arr_v, arr_f, v3, v4);
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

        private static int partition(int[] arr_v, float[] arr_f, int v, int v1) {
            int v2 = arr_v[v1];
            int v3 = v;
            while(v < v1) {
                if(arr_v[v] <= v2) {
                    Sort.swap(arr_v, arr_f, v3, v);
                    ++v3;
                }
                ++v;
            }
            Sort.swap(arr_v, arr_f, v3, v1);
            return v3;
        }

        private static void swap(int[] arr_v, float[] arr_f, int v, int v1) {
            int v2 = arr_v[v];
            arr_v[v] = arr_v[v1];
            arr_v[v1] = v2;
            float f = arr_f[v];
            arr_f[v] = arr_f[v1];
            arr_f[v1] = f;
        }
    }

    private static final String TAG = "SplineSet";
    private int mCount;
    protected CurveFit mCurveFit;
    protected int[] mTimePoints;
    private String mType;
    protected float[] mValues;

    public SplineSet() {
        this.mTimePoints = new int[10];
        this.mValues = new float[10];
    }

    public float get(float f) {
        return (float)this.mCurveFit.getPos(((double)f), 0);
    }

    public CurveFit getCurveFit() {
        return this.mCurveFit;
    }

    public float getSlope(float f) {
        return (float)this.mCurveFit.getSlope(((double)f), 0);
    }

    public static SplineSet makeCustomSpline(String s, CustomArray keyFrameArray$CustomArray0) {
        return new CustomSet(s, keyFrameArray$CustomArray0);
    }

    public static SplineSet makeCustomSplineSet(String s, CustomVar keyFrameArray$CustomVar0) {
        return new CustomSpline(s, keyFrameArray$CustomVar0);
    }

    public static SplineSet makeSpline(String s, long v) {
        return new CoreSpline(s, v);
    }

    public void setPoint(int v, float f) {
        int[] arr_v = this.mTimePoints;
        if(arr_v.length < this.mCount + 1) {
            this.mTimePoints = Arrays.copyOf(arr_v, arr_v.length * 2);
            this.mValues = Arrays.copyOf(this.mValues, this.mValues.length * 2);
        }
        int v1 = this.mCount;
        this.mTimePoints[v1] = v;
        this.mValues[v1] = f;
        this.mCount = v1 + 1;
    }

    public void setProperty(TypedValues typedValues0, float f) {
        typedValues0.setValue(TypedValues.AttributesType.-CC.getId(this.mType), this.get(f));
    }

    public void setType(String s) {
        this.mType = s;
    }

    public void setup(int v) {
        int v1 = this.mCount;
        if(v1 == 0) {
            return;
        }
        Sort.doubleQuickSort(this.mTimePoints, this.mValues, 0, v1 - 1);
        int v3 = 1;
        for(int v2 = 1; v2 < this.mCount; ++v2) {
            if(this.mTimePoints[v2 - 1] != this.mTimePoints[v2]) {
                ++v3;
            }
        }
        double[] arr_f = new double[v3];
        double[][] arr2_f = new double[v3][1];
        int v5 = 0;
        for(int v4 = 0; v4 < this.mCount; ++v4) {
            if(v4 <= 0 || this.mTimePoints[v4] != this.mTimePoints[v4 - 1]) {
                arr_f[v5] = ((double)this.mTimePoints[v4]) * 0.01;
                arr2_f[v5][0] = (double)this.mValues[v4];
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
            s = s + "[" + this.mTimePoints[v] + " , " + decimalFormat0.format(((double)this.mValues[v])) + "] ";
        }
        return s;
    }
}

