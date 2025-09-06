package com.airbnb.lottie.model;

public class DocumentData {
    public static enum Justification {
        LEFT_ALIGN,
        RIGHT_ALIGN,
        CENTER;

    }

    public final double baselineShift;
    public final int color;
    public final String fontName;
    public final Justification justification;
    public final double lineHeight;
    public final double size;
    public final int strokeColor;
    public final boolean strokeOverFill;
    public final double strokeWidth;
    public final String text;
    public final int tracking;

    public DocumentData(String s, String s1, double f, Justification documentData$Justification0, int v, double f1, double f2, int v1, int v2, double f3, boolean z) {
        this.text = s;
        this.fontName = s1;
        this.size = f;
        this.justification = documentData$Justification0;
        this.tracking = v;
        this.lineHeight = f1;
        this.baselineShift = f2;
        this.color = v1;
        this.strokeColor = v2;
        this.strokeWidth = f3;
        this.strokeOverFill = z;
    }

    @Override
    public int hashCode() {
        long v = Double.doubleToLongBits(this.lineHeight);
        return (((((int)(((double)((this.text.hashCode() * 0x1F + this.fontName.hashCode()) * 0x1F)) + this.size)) * 0x1F + this.justification.ordinal()) * 0x1F + this.tracking) * 0x1F + ((int)(v ^ v >>> 0x20))) * 0x1F + this.color;
    }
}

