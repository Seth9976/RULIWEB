package com.airbnb.lottie.model;

public class Font {
    private final float ascent;
    private final String family;
    private final String name;
    private final String style;

    public Font(String s, String s1, String s2, float f) {
        this.family = s;
        this.name = s1;
        this.style = s2;
        this.ascent = f;
    }

    float getAscent() {
        return this.ascent;
    }

    public String getFamily() {
        return this.family;
    }

    public String getName() {
        return this.name;
    }

    public String getStyle() {
        return this.style;
    }
}

