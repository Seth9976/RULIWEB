package com.google.android.material.color;

import com.google.android.material.R.attr;

public class HarmonizedColorsOptions {
    public static class Builder {
        private int colorAttributeToHarmonizeWith;
        private HarmonizedColorAttributes colorAttributes;
        private int[] colorResourceIds;

        public Builder() {
            this.colorResourceIds = new int[0];
            this.colorAttributeToHarmonizeWith = attr.colorPrimary;
        }

        static int[] access$000(Builder harmonizedColorsOptions$Builder0) {
            return harmonizedColorsOptions$Builder0.colorResourceIds;
        }

        static HarmonizedColorAttributes access$100(Builder harmonizedColorsOptions$Builder0) {
            return harmonizedColorsOptions$Builder0.colorAttributes;
        }

        static int access$200(Builder harmonizedColorsOptions$Builder0) {
            return harmonizedColorsOptions$Builder0.colorAttributeToHarmonizeWith;
        }

        public HarmonizedColorsOptions build() {
            return new HarmonizedColorsOptions(this, null);
        }

        public Builder setColorAttributeToHarmonizeWith(int v) {
            this.colorAttributeToHarmonizeWith = v;
            return this;
        }

        public Builder setColorAttributes(HarmonizedColorAttributes harmonizedColorAttributes0) {
            this.colorAttributes = harmonizedColorAttributes0;
            return this;
        }

        public Builder setColorResourceIds(int[] arr_v) {
            this.colorResourceIds = arr_v;
            return this;
        }
    }

    private final int colorAttributeToHarmonizeWith;
    private final HarmonizedColorAttributes colorAttributes;
    private final int[] colorResourceIds;

    private HarmonizedColorsOptions(Builder harmonizedColorsOptions$Builder0) {
        this.colorResourceIds = Builder.access$000(harmonizedColorsOptions$Builder0);
        this.colorAttributes = Builder.access$100(harmonizedColorsOptions$Builder0);
        this.colorAttributeToHarmonizeWith = Builder.access$200(harmonizedColorsOptions$Builder0);
    }

    HarmonizedColorsOptions(Builder harmonizedColorsOptions$Builder0, com.google.android.material.color.HarmonizedColorsOptions.1 harmonizedColorsOptions$10) {
        this(harmonizedColorsOptions$Builder0);
    }

    public static HarmonizedColorsOptions createMaterialDefaults() {
        return new Builder().setColorAttributes(HarmonizedColorAttributes.createMaterialDefaults()).build();
    }

    public int getColorAttributeToHarmonizeWith() {
        return this.colorAttributeToHarmonizeWith;
    }

    public HarmonizedColorAttributes getColorAttributes() {
        return this.colorAttributes;
    }

    public int[] getColorResourceIds() {
        return this.colorResourceIds;
    }

    int getThemeOverlayResourceId(int v) {
        return this.colorAttributes == null || this.colorAttributes.getThemeOverlay() == 0 ? v : this.colorAttributes.getThemeOverlay();
    }

    class com.google.android.material.color.HarmonizedColorsOptions.1 {
    }

}

