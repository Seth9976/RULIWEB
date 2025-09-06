package com.airbnb.lottie.utils;

public class MeanCalculator {
    private int n;
    private float sum;

    public void add(float f) {
        float f1 = this.sum + f;
        this.sum = f1;
        int v = this.n + 1;
        this.n = v;
        if(v == 0x7FFFFFFF) {
            this.sum = f1 / 2.0f;
            this.n = 0x3FFFFFFF;
        }
    }

    public float getMean() {
        return this.n == 0 ? 0.0f : this.sum / ((float)this.n);
    }
}

