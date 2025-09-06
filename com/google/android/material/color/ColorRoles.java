package com.google.android.material.color;

public final class ColorRoles {
    private final int accent;
    private final int accentContainer;
    private final int onAccent;
    private final int onAccentContainer;

    ColorRoles(int v, int v1, int v2, int v3) {
        this.accent = v;
        this.onAccent = v1;
        this.accentContainer = v2;
        this.onAccentContainer = v3;
    }

    public int getAccent() {
        return this.accent;
    }

    public int getAccentContainer() {
        return this.accentContainer;
    }

    public int getOnAccent() {
        return this.onAccent;
    }

    public int getOnAccentContainer() {
        return this.onAccentContainer;
    }
}

