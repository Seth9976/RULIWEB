package com.google.android.material.carousel;

import androidx.core.math.MathUtils;

final class Arrangement {
    private static final float MEDIUM_ITEM_FLEX_PERCENTAGE = 0.1f;
    final float cost;
    final int largeCount;
    float largeSize;
    int mediumCount;
    float mediumSize;
    final int priority;
    int smallCount;
    float smallSize;

    Arrangement(int v, float f, float f1, float f2, int v1, float f3, int v2, float f4, int v3, float f5) {
        this.priority = v;
        this.smallSize = MathUtils.clamp(f, f1, f2);
        this.smallCount = v1;
        this.mediumSize = f3;
        this.mediumCount = v2;
        this.largeSize = f4;
        this.largeCount = v3;
        this.fit(f5, f1, f2, f4);
        this.cost = this.cost(f4);
    }

    private float calculateLargeSize(float f, int v, float f1, int v1, int v2) {
        if(v <= 0) {
            f1 = 0.0f;
        }
        return (f - (((float)v) + ((float)v1) / 2.0f) * f1) / (((float)v2) + ((float)v1) / 2.0f);
    }

    private float cost(float f) {
        return this.isValid() ? Math.abs(f - this.largeSize) * ((float)this.priority) : 3.402823E+38f;
    }

    static Arrangement findLowestCostArrangement(float f, float f1, float f2, float f3, int[] arr_v, float f4, int[] arr_v1, float f5, int[] arr_v2) {
        Arrangement arrangement0 = null;
        for(int v1 = 0; v1 < arr_v2.length; ++v1) {
            int v2 = arr_v2[v1];
            for(int v3 = 0; v3 < arr_v1.length; ++v3) {
                int v4 = arr_v1[v3];
                int v5 = 0;
                for(int v = 1; v5 < arr_v.length; ++v) {
                    Arrangement arrangement1 = new Arrangement(v, f1, f2, f3, arr_v[v5], f4, v4, f5, v2, f);
                    if(arrangement0 == null || arrangement1.cost < arrangement0.cost) {
                        if(arrangement1.cost == 0.0f) {
                            return arrangement1;
                        }
                        arrangement0 = arrangement1;
                    }
                    ++v5;
                }
            }
        }
        return arrangement0;
    }

    private void fit(float f, float f1, float f2, float f3) {
        float f4 = f - this.getSpace();
        int v = this.smallCount;
        if(v > 0 && f4 > 0.0f) {
            this.smallSize += Math.min(f4 / ((float)v), f2 - this.smallSize);
        }
        else if(v > 0 && f4 < 0.0f) {
            this.smallSize += Math.max(f4 / ((float)v), f1 - this.smallSize);
        }
        float f5 = this.smallCount <= 0 ? 0.0f : this.smallSize;
        this.smallSize = f5;
        float f6 = this.calculateLargeSize(f, this.smallCount, f5, this.mediumCount, this.largeCount);
        this.largeSize = f6;
        float f7 = (this.smallSize + f6) / 2.0f;
        this.mediumSize = f7;
        int v1 = this.mediumCount;
        if(v1 > 0 && f6 != f3) {
            float f8 = (f3 - f6) * ((float)this.largeCount);
            float f9 = Math.min(Math.abs(f8), f7 * 0.1f * ((float)v1));
            if(f8 > 0.0f) {
                this.mediumSize -= f9 / ((float)this.mediumCount);
                this.largeSize += f9 / ((float)this.largeCount);
                return;
            }
            this.mediumSize += f9 / ((float)this.mediumCount);
            this.largeSize -= f9 / ((float)this.largeCount);
        }
    }

    int getItemCount() {
        return this.smallCount + this.mediumCount + this.largeCount;
    }

    private float getSpace() {
        return this.largeSize * ((float)this.largeCount) + this.mediumSize * ((float)this.mediumCount) + this.smallSize * ((float)this.smallCount);
    }

    private boolean isValid() {
        return this.largeCount <= 0 || this.smallCount <= 0 || this.mediumCount <= 0 ? this.largeCount <= 0 || this.smallCount <= 0 || this.largeSize > this.smallSize : this.largeSize > this.mediumSize && this.mediumSize > this.smallSize;
    }

    @Override
    public String toString() {
        return "Arrangement [priority=" + this.priority + ", smallCount=" + this.smallCount + ", smallSize=" + this.smallSize + ", mediumCount=" + this.mediumCount + ", mediumSize=" + this.mediumSize + ", largeCount=" + this.largeCount + ", largeSize=" + this.largeSize + ", cost=" + this.cost + "]";
    }
}

