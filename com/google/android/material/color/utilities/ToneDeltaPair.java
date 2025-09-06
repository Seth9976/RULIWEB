package com.google.android.material.color.utilities;

public final class ToneDeltaPair {
    private final double delta;
    private final TonePolarity polarity;
    private final DynamicColor roleA;
    private final DynamicColor roleB;
    private final boolean stayTogether;

    public ToneDeltaPair(DynamicColor dynamicColor0, DynamicColor dynamicColor1, double f, TonePolarity tonePolarity0, boolean z) {
        this.roleA = dynamicColor0;
        this.roleB = dynamicColor1;
        this.delta = f;
        this.polarity = tonePolarity0;
        this.stayTogether = z;
    }

    public double getDelta() {
        return this.delta;
    }

    public TonePolarity getPolarity() {
        return this.polarity;
    }

    public DynamicColor getRoleA() {
        return this.roleA;
    }

    public DynamicColor getRoleB() {
        return this.roleB;
    }

    public boolean getStayTogether() {
        return this.stayTogether;
    }
}

