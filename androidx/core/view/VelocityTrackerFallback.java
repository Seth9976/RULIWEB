package androidx.core.view;

import android.view.MotionEvent;

class VelocityTrackerFallback {
    private static final long ASSUME_POINTER_STOPPED_MS = 40L;
    private static final int HISTORY_SIZE = 20;
    private static final long RANGE_MS = 100L;
    private int mDataPointsBufferLastUsedIndex;
    private int mDataPointsBufferSize;
    private final long[] mEventTimes;
    private float mLastComputedVelocity;
    private final float[] mMovements;

    VelocityTrackerFallback() {
        this.mMovements = new float[20];
        this.mEventTimes = new long[20];
        this.mLastComputedVelocity = 0.0f;
        this.mDataPointsBufferSize = 0;
        this.mDataPointsBufferLastUsedIndex = 0;
    }

    void addMovement(MotionEvent motionEvent0) {
        long v = motionEvent0.getEventTime();
        if(this.mDataPointsBufferSize != 0 && v - this.mEventTimes[this.mDataPointsBufferLastUsedIndex] > 40L) {
            this.clear();
        }
        int v1 = (this.mDataPointsBufferLastUsedIndex + 1) % 20;
        this.mDataPointsBufferLastUsedIndex = v1;
        int v2 = this.mDataPointsBufferSize;
        if(v2 != 20) {
            this.mDataPointsBufferSize = v2 + 1;
        }
        this.mMovements[v1] = motionEvent0.getAxisValue(26);
        this.mEventTimes[this.mDataPointsBufferLastUsedIndex] = v;
    }

    private void clear() {
        this.mDataPointsBufferSize = 0;
        this.mLastComputedVelocity = 0.0f;
    }

    void computeCurrentVelocity(int v) {
        this.computeCurrentVelocity(v, 3.402823E+38f);
    }

    void computeCurrentVelocity(int v, float f) {
        float f1 = this.getCurrentVelocity() * ((float)v);
        this.mLastComputedVelocity = f1;
        if(f1 < -Math.abs(f)) {
            this.mLastComputedVelocity = -Math.abs(f);
            return;
        }
        if(this.mLastComputedVelocity > Math.abs(f)) {
            this.mLastComputedVelocity = Math.abs(f);
        }
    }

    float getAxisVelocity(int v) {
        return v == 26 ? this.mLastComputedVelocity : 0.0f;
    }

    private float getCurrentVelocity() {
        long v3;
        long[] arr_v;
        int v = this.mDataPointsBufferSize;
        if(v < 2) {
            return 0.0f;
        }
        int v1 = (this.mDataPointsBufferLastUsedIndex + 20 - (v - 1)) % 20;
        long v2 = this.mEventTimes[this.mDataPointsBufferLastUsedIndex];
        while(true) {
            arr_v = this.mEventTimes;
            v3 = arr_v[v1];
            if(v2 - v3 <= 100L) {
                break;
            }
            --this.mDataPointsBufferSize;
            v1 = (v1 + 1) % 20;
        }
        int v4 = this.mDataPointsBufferSize;
        if(v4 < 2) {
            return 0.0f;
        }
        if(v4 == 2) {
            int v5 = (v1 + 1) % 20;
            long v6 = arr_v[v5];
            return v3 == v6 ? 0.0f : this.mMovements[v5] / ((float)(v6 - v3));
        }
        int v8 = 0;
        float f = 0.0f;
        for(int v7 = 0; v7 < this.mDataPointsBufferSize - 1; ++v7) {
            int v9 = v7 + v1;
            long v10 = this.mEventTimes[v9 % 20];
            int v11 = (v9 + 1) % 20;
            if(this.mEventTimes[v11] != v10) {
                ++v8;
                float f1 = this.mMovements[v11] / ((float)(this.mEventTimes[v11] - v10));
                f += (f1 - VelocityTrackerFallback.kineticEnergyToVelocity(f)) * Math.abs(f1);
                if(v8 == 1) {
                    f *= 0.5f;
                }
            }
        }
        return VelocityTrackerFallback.kineticEnergyToVelocity(f);
    }

    private static float kineticEnergyToVelocity(float f) {
        return f < 0.0f ? -1.0f * ((float)Math.sqrt(Math.abs(f) * 2.0f)) : 1.0f * ((float)Math.sqrt(Math.abs(f) * 2.0f));
    }
}

