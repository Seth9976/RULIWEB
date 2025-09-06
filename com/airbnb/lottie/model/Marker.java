package com.airbnb.lottie.model;

public class Marker {
    private static String CARRIAGE_RETURN = "\r";
    public final float durationFrames;
    private final String name;
    public final float startFrame;

    static {
    }

    public Marker(String s, float f, float f1) {
        this.name = s;
        this.durationFrames = f1;
        this.startFrame = f;
    }

    // 去混淆评级： 中等(60)
    public boolean matchesName(String s) {
        return this.name.equalsIgnoreCase(s) ? true : this.name.endsWith("\r") && this.name.substring(0, this.name.length() - 1).equalsIgnoreCase(s);
    }
}

