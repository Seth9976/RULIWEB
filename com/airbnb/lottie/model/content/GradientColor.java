package com.airbnb.lottie.model.content;

import com.airbnb.lottie.utils.GammaEvaluator;
import com.airbnb.lottie.utils.MiscUtils;

public class GradientColor {
    private final int[] colors;
    private final float[] positions;

    public GradientColor(float[] arr_f, int[] arr_v) {
        this.positions = arr_f;
        this.colors = arr_v;
    }

    public int[] getColors() {
        return this.colors;
    }

    public float[] getPositions() {
        return this.positions;
    }

    public int getSize() {
        return this.colors.length;
    }

    public void lerp(GradientColor gradientColor0, GradientColor gradientColor1, float f) {
        if(gradientColor0.colors.length != gradientColor1.colors.length) {
            throw new IllegalArgumentException("Cannot interpolate between gradients. Lengths vary (" + gradientColor0.colors.length + " vs " + gradientColor1.colors.length + ")");
        }
        for(int v = 0; v < gradientColor0.colors.length; ++v) {
            this.positions[v] = MiscUtils.lerp(gradientColor0.positions[v], gradientColor1.positions[v], f);
            this.colors[v] = GammaEvaluator.evaluate(f, gradientColor0.colors[v], gradientColor1.colors[v]);
        }
    }
}

