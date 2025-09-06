package com.google.android.material.color.utilities;

public final class Hct {
    private int argb;
    private double chroma;
    private double hue;
    private double tone;

    private Hct(int v) {
        this.setInternalState(v);
    }

    public static Hct from(double f, double f1, double f2) {
        return new Hct(HctSolver.solveToInt(f, f1, f2));
    }

    public static Hct fromInt(int v) {
        return new Hct(v);
    }

    public double getChroma() {
        return this.chroma;
    }

    public double getHue() {
        return this.hue;
    }

    public double getTone() {
        return this.tone;
    }

    public Hct inViewingConditions(ViewingConditions viewingConditions0) {
        double[] arr_f = Cam16.fromInt(this.toInt()).xyzInViewingConditions(viewingConditions0, null);
        Cam16 cam160 = Cam16.fromXyzInViewingConditions(arr_f[0], arr_f[1], arr_f[2], ViewingConditions.DEFAULT);
        return Hct.from(cam160.getHue(), cam160.getChroma(), ColorUtils.lstarFromY(arr_f[1]));
    }

    public void setChroma(double f) {
        this.setInternalState(HctSolver.solveToInt(this.hue, f, this.tone));
    }

    public void setHue(double f) {
        this.setInternalState(HctSolver.solveToInt(f, this.chroma, this.tone));
    }

    private void setInternalState(int v) {
        this.argb = v;
        Cam16 cam160 = Cam16.fromInt(v);
        this.hue = cam160.getHue();
        this.chroma = cam160.getChroma();
        this.tone = ColorUtils.lstarFromArgb(v);
    }

    public void setTone(double f) {
        this.setInternalState(HctSolver.solveToInt(this.hue, this.chroma, f));
    }

    public int toInt() {
        return this.argb;
    }
}

