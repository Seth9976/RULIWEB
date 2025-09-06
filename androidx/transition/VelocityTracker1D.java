package androidx.transition;

import java.util.Arrays;

class VelocityTracker1D {
    private static final int ASSUME_POINTER_MOVE_STOPPED_MILLIS = 40;
    private static final int HISTORY_SIZE = 20;
    private static final int HORIZON_MILLIS = 100;
    private float[] mDataSamples;
    private int mIndex;
    private long[] mTimeSamples;

    VelocityTracker1D() {
        long[] arr_v = new long[20];
        this.mTimeSamples = arr_v;
        this.mDataSamples = new float[20];
        this.mIndex = 0;
        Arrays.fill(arr_v, 0x8000000000000000L);
    }

    public void addDataPoint(long v, float f) {
        int v1 = (this.mIndex + 1) % 20;
        this.mIndex = v1;
        this.mTimeSamples[v1] = v;
        this.mDataSamples[v1] = f;
    }

    float calculateVelocity() {
        int v = this.mIndex;
        if(v == 0 && this.mTimeSamples[0] == 0x8000000000000000L) {
            return 0.0f;
        }
        long v1 = this.mTimeSamples[v];
        int v2 = 0;
        for(long v3 = v1; true; v3 = v4) {
            long v4 = this.mTimeSamples[v];
            if(v4 == 0x8000000000000000L || (((float)(v1 - v4)) > 100.0f || ((float)Math.abs(v4 - v3)) > 40.0f)) {
                break;
            }
            if(v == 0) {
                v = 20;
            }
            --v;
            ++v2;
            if(v2 >= 20) {
                break;
            }
        }
        if(v2 < 2) {
            return 0.0f;
        }
        if(v2 == 2) {
            int v5 = this.mIndex;
            int v6 = v5 == 0 ? 19 : v5 - 1;
            float f = (float)(this.mTimeSamples[v5] - this.mTimeSamples[v6]);
            return f == 0.0f ? 0.0f : (this.mDataSamples[v5] - this.mDataSamples[v6]) / f * 1000.0f;
        }
        int v7 = (this.mIndex - v2 + 21) % 20;
        int v8 = (this.mIndex + 21) % 20;
        long v9 = this.mTimeSamples[v7];
        float f1 = this.mDataSamples[v7];
        int v10 = (v7 + 1) % 20;
        float f2 = 0.0f;
        while(v10 != v8) {
            long v11 = this.mTimeSamples[v10];
            float f3 = (float)(v11 - v9);
            if(f3 != 0.0f) {
                float f4 = this.mDataSamples[v10];
                float f5 = (f4 - f1) / f3;
                f2 += (f5 - this.kineticEnergyToVelocity(f2)) * Math.abs(f5);
                f2 = v10 == v7 + 1 ? f2 * 0.5f : f2 + (f5 - this.kineticEnergyToVelocity(f2)) * Math.abs(f5);
                f1 = f4;
                v9 = v11;
            }
            v10 = (v10 + 1) % 20;
        }
        return this.kineticEnergyToVelocity(f2) * 1000.0f;
    }

    private float kineticEnergyToVelocity(float f) {
        return (float)(((double)Math.signum(f)) * Math.sqrt(Math.abs(f) * 2.0f));
    }

    public void resetTracking() {
        this.mIndex = 0;
        Arrays.fill(this.mTimeSamples, 0x8000000000000000L);
        Arrays.fill(this.mDataSamples, 0.0f);
    }
}

