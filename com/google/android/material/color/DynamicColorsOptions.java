package com.google.android.material.color;

import android.app.Activity;
import android.graphics.Bitmap;
import com.google.android.material.color.utilities.QuantizerCelebi;
import com.google.android.material.color.utilities.Score;

public class DynamicColorsOptions {
    public static class Builder {
        private Bitmap contentBasedSourceBitmap;
        private Integer contentBasedSourceColor;
        private OnAppliedCallback onAppliedCallback;
        private Precondition precondition;
        private int themeOverlay;

        public Builder() {
            this.precondition = DynamicColorsOptions.ALWAYS_ALLOW;
            this.onAppliedCallback = DynamicColorsOptions.NO_OP_CALLBACK;
        }

        static Precondition access$100(Builder dynamicColorsOptions$Builder0) {
            return dynamicColorsOptions$Builder0.precondition;
        }

        static OnAppliedCallback access$200(Builder dynamicColorsOptions$Builder0) {
            return dynamicColorsOptions$Builder0.onAppliedCallback;
        }

        static Integer access$300(Builder dynamicColorsOptions$Builder0) {
            return dynamicColorsOptions$Builder0.contentBasedSourceColor;
        }

        static Bitmap access$400(Builder dynamicColorsOptions$Builder0) {
            return dynamicColorsOptions$Builder0.contentBasedSourceBitmap;
        }

        public DynamicColorsOptions build() {
            return new DynamicColorsOptions(this, null);
        }

        public Builder setContentBasedSource(int v) {
            this.contentBasedSourceBitmap = null;
            this.contentBasedSourceColor = v;
            return this;
        }

        public Builder setContentBasedSource(Bitmap bitmap0) {
            this.contentBasedSourceBitmap = bitmap0;
            this.contentBasedSourceColor = null;
            return this;
        }

        public Builder setOnAppliedCallback(OnAppliedCallback dynamicColors$OnAppliedCallback0) {
            this.onAppliedCallback = dynamicColors$OnAppliedCallback0;
            return this;
        }

        public Builder setPrecondition(Precondition dynamicColors$Precondition0) {
            this.precondition = dynamicColors$Precondition0;
            return this;
        }

        public Builder setThemeOverlay(int v) {
            this.themeOverlay = v;
            return this;
        }
    }

    private static final Precondition ALWAYS_ALLOW;
    private static final OnAppliedCallback NO_OP_CALLBACK;
    private Integer contentBasedSeedColor;
    private final OnAppliedCallback onAppliedCallback;
    private final Precondition precondition;
    private final int themeOverlay;

    static {
        DynamicColorsOptions.ALWAYS_ALLOW = new Precondition() {
            @Override  // com.google.android.material.color.DynamicColors$Precondition
            public boolean shouldApplyDynamicColors(Activity activity0, int v) {
                return true;
            }
        };
        DynamicColorsOptions.NO_OP_CALLBACK = new OnAppliedCallback() {
            @Override  // com.google.android.material.color.DynamicColors$OnAppliedCallback
            public void onApplied(Activity activity0) {
            }
        };
    }

    private DynamicColorsOptions(Builder dynamicColorsOptions$Builder0) {
        this.themeOverlay = dynamicColorsOptions$Builder0.themeOverlay;
        this.precondition = Builder.access$100(dynamicColorsOptions$Builder0);
        this.onAppliedCallback = Builder.access$200(dynamicColorsOptions$Builder0);
        if(Builder.access$300(dynamicColorsOptions$Builder0) != null) {
            this.contentBasedSeedColor = Builder.access$300(dynamicColorsOptions$Builder0);
            return;
        }
        if(Builder.access$400(dynamicColorsOptions$Builder0) != null) {
            this.contentBasedSeedColor = DynamicColorsOptions.extractSeedColorFromImage(Builder.access$400(dynamicColorsOptions$Builder0));
        }
    }

    DynamicColorsOptions(Builder dynamicColorsOptions$Builder0, com.google.android.material.color.DynamicColorsOptions.1 dynamicColorsOptions$10) {
        this(dynamicColorsOptions$Builder0);
    }

    private static int extractSeedColorFromImage(Bitmap bitmap0) {
        int v = bitmap0.getWidth();
        int v1 = bitmap0.getHeight();
        int[] arr_v = new int[v * v1];
        bitmap0.getPixels(arr_v, 0, v, 0, 0, v, v1);
        return (int)(((Integer)Score.score(QuantizerCelebi.quantize(arr_v, 0x80)).get(0)));
    }

    public Integer getContentBasedSeedColor() {
        return this.contentBasedSeedColor;
    }

    public OnAppliedCallback getOnAppliedCallback() {
        return this.onAppliedCallback;
    }

    public Precondition getPrecondition() {
        return this.precondition;
    }

    public int getThemeOverlay() {
        return this.themeOverlay;
    }
}

