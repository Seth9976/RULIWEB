package com.google.android.material.color;

public class ColorContrastOptions {
    public static class Builder {
        private int highContrastThemeOverlayResourceId;
        private int mediumContrastThemeOverlayResourceId;

        static int access$100(Builder colorContrastOptions$Builder0) {
            return colorContrastOptions$Builder0.highContrastThemeOverlayResourceId;
        }

        public ColorContrastOptions build() {
            return new ColorContrastOptions(this, null);
        }

        public Builder setHighContrastThemeOverlay(int v) {
            this.highContrastThemeOverlayResourceId = v;
            return this;
        }

        public Builder setMediumContrastThemeOverlay(int v) {
            this.mediumContrastThemeOverlayResourceId = v;
            return this;
        }
    }

    private final int highContrastThemeOverlayResourceId;
    private final int mediumContrastThemeOverlayResourceId;

    private ColorContrastOptions(Builder colorContrastOptions$Builder0) {
        this.mediumContrastThemeOverlayResourceId = colorContrastOptions$Builder0.mediumContrastThemeOverlayResourceId;
        this.highContrastThemeOverlayResourceId = Builder.access$100(colorContrastOptions$Builder0);
    }

    ColorContrastOptions(Builder colorContrastOptions$Builder0, com.google.android.material.color.ColorContrastOptions.1 colorContrastOptions$10) {
        this(colorContrastOptions$Builder0);
    }

    public int getHighContrastThemeOverlay() {
        return this.highContrastThemeOverlayResourceId;
    }

    public int getMediumContrastThemeOverlay() {
        return this.mediumContrastThemeOverlayResourceId;
    }

    class com.google.android.material.color.ColorContrastOptions.1 {
    }

}

