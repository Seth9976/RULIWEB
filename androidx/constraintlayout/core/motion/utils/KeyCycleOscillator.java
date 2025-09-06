package androidx.constraintlayout.core.motion.utils;

import androidx.constraintlayout.core.motion.MotionWidget;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public abstract class KeyCycleOscillator {
    static class CoreSpline extends KeyCycleOscillator {
        String mType;
        int mTypeId;

        CoreSpline(String s) {
            this.mType = s;
            this.mTypeId = TypedValues.CycleType.-CC.getId(s);
        }

        @Override  // androidx.constraintlayout.core.motion.utils.KeyCycleOscillator
        public void setProperty(MotionWidget motionWidget0, float f) {
            motionWidget0.setValue(this.mTypeId, this.get(f));
        }
    }

    static class CycleOscillator {
        private static final String TAG = "CycleOscillator";
        static final int UNSET = -1;
        CurveFit mCurveFit;
        float[] mOffsetArr;
        private final int mOffst;
        Oscillator mOscillator;
        float mPathLength;
        float[] mPeriod;
        private final int mPhase;
        float[] mPhaseArr;
        double[] mPosition;
        float[] mScale;
        double[] mSplineSlopeCache;
        double[] mSplineValueCache;
        private final int mValue;
        float[] mValues;
        private final int mVariesBy;
        int mWaveShape;

        CycleOscillator(int v, String s, int v1, int v2) {
            Oscillator oscillator0 = new Oscillator();
            this.mOscillator = oscillator0;
            this.mOffst = 0;
            this.mPhase = 1;
            this.mValue = 2;
            this.mWaveShape = v;
            this.mVariesBy = v1;
            oscillator0.setType(v, s);
            this.mValues = new float[v2];
            this.mPosition = new double[v2];
            this.mPeriod = new float[v2];
            this.mOffsetArr = new float[v2];
            this.mPhaseArr = new float[v2];
            this.mScale = new float[v2];
        }

        public double getLastPhase() {
            return this.mSplineValueCache[1];
        }

        public double getSlope(float f) {
            CurveFit curveFit0 = this.mCurveFit;
            if(curveFit0 == null) {
                double[] arr_f = this.mSplineSlopeCache;
                arr_f[0] = 0.0;
                arr_f[1] = 0.0;
                arr_f[2] = 0.0;
            }
            else {
                curveFit0.getSlope(((double)f), this.mSplineSlopeCache);
                this.mCurveFit.getPos(((double)f), this.mSplineValueCache);
            }
            double f1 = this.mOscillator.getValue(((double)f), this.mSplineValueCache[1]);
            double f2 = this.mOscillator.getSlope(((double)f), this.mSplineValueCache[1], this.mSplineSlopeCache[1]);
            return this.mSplineSlopeCache[0] + f1 * this.mSplineSlopeCache[2] + f2 * this.mSplineValueCache[2];
        }

        public double getValues(float f) {
            CurveFit curveFit0 = this.mCurveFit;
            if(curveFit0 != null) {
                curveFit0.getPos(((double)f), this.mSplineValueCache);
                return this.mSplineValueCache[0] + this.mOscillator.getValue(((double)f), this.mSplineValueCache[1]) * this.mSplineValueCache[2];
            }
            double[] arr_f = this.mSplineValueCache;
            arr_f[0] = (double)this.mOffsetArr[0];
            arr_f[1] = (double)this.mPhaseArr[0];
            arr_f[2] = (double)this.mValues[0];
            return this.mSplineValueCache[0] + this.mOscillator.getValue(((double)f), this.mSplineValueCache[1]) * this.mSplineValueCache[2];
        }

        public void setPoint(int v, int v1, float f, float f1, float f2, float f3) {
            this.mPosition[v] = ((double)v1) / 100.0;
            this.mPeriod[v] = f;
            this.mOffsetArr[v] = f1;
            this.mPhaseArr[v] = f2;
            this.mValues[v] = f3;
        }

        public void setup(float f) {
            this.mPathLength = f;
            double[][] arr2_f = new double[this.mPosition.length][3];
            float[] arr_f = this.mValues;
            this.mSplineValueCache = new double[arr_f.length + 2];
            this.mSplineSlopeCache = new double[arr_f.length + 2];
            if(this.mPosition[0] > 0.0) {
                this.mOscillator.addPoint(0.0, this.mPeriod[0]);
            }
            int v = this.mPosition.length - 1;
            if(this.mPosition[v] < 1.0) {
                this.mOscillator.addPoint(1.0, this.mPeriod[v]);
            }
            for(int v1 = 0; v1 < arr2_f.length; ++v1) {
                double[] arr_f1 = arr2_f[v1];
                arr_f1[0] = (double)this.mOffsetArr[v1];
                arr_f1[1] = (double)this.mPhaseArr[v1];
                arr_f1[2] = (double)this.mValues[v1];
                this.mOscillator.addPoint(this.mPosition[v1], this.mPeriod[v1]);
            }
            this.mOscillator.normalize();
            double[] arr_f2 = this.mPosition;
            if(arr_f2.length > 1) {
                this.mCurveFit = CurveFit.get(0, arr_f2, arr2_f);
                return;
            }
            this.mCurveFit = null;
        }
    }

    public static class PathRotateSet extends KeyCycleOscillator {
        String mType;
        int mTypeId;

        public PathRotateSet(String s) {
            this.mType = s;
            this.mTypeId = TypedValues.CycleType.-CC.getId(s);
        }

        public void setPathRotate(MotionWidget motionWidget0, float f, double f1, double f2) {
            motionWidget0.setRotationZ(this.get(f) + ((float)Math.toDegrees(Math.atan2(f2, f1))));
        }

        @Override  // androidx.constraintlayout.core.motion.utils.KeyCycleOscillator
        public void setProperty(MotionWidget motionWidget0, float f) {
            motionWidget0.setValue(this.mTypeId, this.get(f));
        }
    }

    static class WavePoint {
        float mOffset;
        float mPeriod;
        float mPhase;
        int mPosition;
        float mValue;

        WavePoint(int v, float f, float f1, float f2, float f3) {
            this.mPosition = v;
            this.mValue = f3;
            this.mOffset = f1;
            this.mPeriod = f;
            this.mPhase = f2;
        }
    }

    private static final String TAG = "KeyCycleOscillator";
    private CurveFit mCurveFit;
    private CycleOscillator mCycleOscillator;
    private String mType;
    public int mVariesBy;
    ArrayList mWavePoints;
    private int mWaveShape;
    private String mWaveString;

    public KeyCycleOscillator() {
        this.mWaveShape = 0;
        this.mWaveString = null;
        this.mVariesBy = 0;
        this.mWavePoints = new ArrayList();
    }

    public float get(float f) {
        return (float)this.mCycleOscillator.getValues(f);
    }

    public CurveFit getCurveFit() {
        return this.mCurveFit;
    }

    public float getSlope(float f) {
        return (float)this.mCycleOscillator.getSlope(f);
    }

    public static KeyCycleOscillator makeWidgetCycle(String s) {
        return s.equals("pathRotate") ? new PathRotateSet(s) : new CoreSpline(s);
    }

    protected void setCustom(Object object0) {
    }

    public void setPoint(int v, int v1, String s, int v2, float f, float f1, float f2, float f3) {
        this.mWavePoints.add(new WavePoint(v, f, f1, f2, f3));
        if(v2 != -1) {
            this.mVariesBy = v2;
        }
        this.mWaveShape = v1;
        this.mWaveString = s;
    }

    public void setPoint(int v, int v1, String s, int v2, float f, float f1, float f2, float f3, Object object0) {
        this.mWavePoints.add(new WavePoint(v, f, f1, f2, f3));
        if(v2 != -1) {
            this.mVariesBy = v2;
        }
        this.mWaveShape = v1;
        this.setCustom(object0);
        this.mWaveString = s;
    }

    public void setProperty(MotionWidget motionWidget0, float f) {
    }

    public void setType(String s) {
        this.mType = s;
    }

    public void setup(float f) {
        int v = this.mWavePoints.size();
        if(v == 0) {
            return;
        }
        Collections.sort(this.mWavePoints, new Comparator() {
            public int compare(WavePoint keyCycleOscillator$WavePoint0, WavePoint keyCycleOscillator$WavePoint1) {
                return Integer.compare(keyCycleOscillator$WavePoint0.mPosition, keyCycleOscillator$WavePoint1.mPosition);
            }

            @Override
            public int compare(Object object0, Object object1) {
                return this.compare(((WavePoint)object0), ((WavePoint)object1));
            }
        });
        double[] arr_f = new double[v];
        double[][] arr2_f = new double[v][3];
        this.mCycleOscillator = new CycleOscillator(this.mWaveShape, this.mWaveString, this.mVariesBy, v);
        int v1 = 0;
        for(Object object0: this.mWavePoints) {
            arr_f[v1] = ((double)((WavePoint)object0).mPeriod) * 0.01;
            arr2_f[v1][0] = (double)((WavePoint)object0).mValue;
            arr2_f[v1][1] = (double)((WavePoint)object0).mOffset;
            arr2_f[v1][2] = (double)((WavePoint)object0).mPhase;
            this.mCycleOscillator.setPoint(v1, ((WavePoint)object0).mPosition, ((WavePoint)object0).mPeriod, ((WavePoint)object0).mOffset, ((WavePoint)object0).mPhase, ((WavePoint)object0).mValue);
            ++v1;
        }
        this.mCycleOscillator.setup(f);
        this.mCurveFit = CurveFit.get(0, arr_f, arr2_f);
    }

    @Override
    public String toString() {
        String s = this.mType;
        DecimalFormat decimalFormat0 = new DecimalFormat("##.##");
        for(Object object0: this.mWavePoints) {
            s = s + "[" + ((WavePoint)object0).mPosition + " , " + decimalFormat0.format(((double)((WavePoint)object0).mValue)) + "] ";
        }
        return s;
    }

    public boolean variesByPath() {
        return this.mVariesBy == 1;
    }
}

