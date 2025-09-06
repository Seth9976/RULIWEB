package com.airbnb.lottie.model;

import java.util.List;

public class FontCharacter {
    private final char character;
    private final String fontFamily;
    private final List shapes;
    private final double size;
    private final String style;
    private final double width;

    public FontCharacter(List list0, char c, double f, double f1, String s, String s1) {
        this.shapes = list0;
        this.character = c;
        this.size = f;
        this.width = f1;
        this.style = s;
        this.fontFamily = s1;
    }

    public List getShapes() {
        return this.shapes;
    }

    double getSize() {
        return this.size;
    }

    String getStyle() {
        return this.style;
    }

    public double getWidth() {
        return this.width;
    }

    @Override
    public int hashCode() {
        return FontCharacter.hashFor(this.character, this.fontFamily, this.style);
    }

    public static int hashFor(char c, String s, String s1) {
        return (c * 0x1F + s.hashCode()) * 0x1F + s1.hashCode();
    }
}

