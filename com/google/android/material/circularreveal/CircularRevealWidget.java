package com.google.android.material.circularreveal;

import android.animation.TypeEvaluator;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Property;

public interface CircularRevealWidget extends Delegate {
    public static class CircularRevealEvaluator implements TypeEvaluator {
        public static final TypeEvaluator CIRCULAR_REVEAL;
        private final RevealInfo revealInfo;

        static {
            CircularRevealEvaluator.CIRCULAR_REVEAL = new CircularRevealEvaluator();
        }

        public CircularRevealEvaluator() {
            this.revealInfo = new RevealInfo(null);
        }

        public RevealInfo evaluate(float f, RevealInfo circularRevealWidget$RevealInfo0, RevealInfo circularRevealWidget$RevealInfo1) {
            this.revealInfo.set((1.0f - f) * circularRevealWidget$RevealInfo0.centerX + f * circularRevealWidget$RevealInfo1.centerX, (1.0f - f) * circularRevealWidget$RevealInfo0.centerY + f * circularRevealWidget$RevealInfo1.centerY, (1.0f - f) * circularRevealWidget$RevealInfo0.radius + f * circularRevealWidget$RevealInfo1.radius);
            return this.revealInfo;
        }

        @Override  // android.animation.TypeEvaluator
        public Object evaluate(float f, Object object0, Object object1) {
            return this.evaluate(f, ((RevealInfo)object0), ((RevealInfo)object1));
        }
    }

    public static class CircularRevealProperty extends Property {
        public static final Property CIRCULAR_REVEAL;

        static {
            CircularRevealProperty.CIRCULAR_REVEAL = new CircularRevealProperty("circularReveal");
        }

        private CircularRevealProperty(String s) {
            super(RevealInfo.class, s);
        }

        public RevealInfo get(CircularRevealWidget circularRevealWidget0) {
            return circularRevealWidget0.getRevealInfo();
        }

        @Override  // android.util.Property
        public Object get(Object object0) {
            return this.get(((CircularRevealWidget)object0));
        }

        public void set(CircularRevealWidget circularRevealWidget0, RevealInfo circularRevealWidget$RevealInfo0) {
            circularRevealWidget0.setRevealInfo(circularRevealWidget$RevealInfo0);
        }

        @Override  // android.util.Property
        public void set(Object object0, Object object1) {
            this.set(((CircularRevealWidget)object0), ((RevealInfo)object1));
        }
    }

    public static class CircularRevealScrimColorProperty extends Property {
        public static final Property CIRCULAR_REVEAL_SCRIM_COLOR;

        static {
            CircularRevealScrimColorProperty.CIRCULAR_REVEAL_SCRIM_COLOR = new CircularRevealScrimColorProperty("circularRevealScrimColor");
        }

        private CircularRevealScrimColorProperty(String s) {
            super(Integer.class, s);
        }

        public Integer get(CircularRevealWidget circularRevealWidget0) {
            return circularRevealWidget0.getCircularRevealScrimColor();
        }

        @Override  // android.util.Property
        public Object get(Object object0) {
            return this.get(((CircularRevealWidget)object0));
        }

        public void set(CircularRevealWidget circularRevealWidget0, Integer integer0) {
            circularRevealWidget0.setCircularRevealScrimColor(((int)integer0));
        }

        @Override  // android.util.Property
        public void set(Object object0, Object object1) {
            this.set(((CircularRevealWidget)object0), ((Integer)object1));
        }
    }

    public static class RevealInfo {
        public static final float INVALID_RADIUS = 3.402823E+38f;
        public float centerX;
        public float centerY;
        public float radius;

        private RevealInfo() {
        }

        public RevealInfo(float f, float f1, float f2) {
            this.centerX = f;
            this.centerY = f1;
            this.radius = f2;
        }

        RevealInfo(com.google.android.material.circularreveal.CircularRevealWidget.1 circularRevealWidget$10) {
        }

        public RevealInfo(RevealInfo circularRevealWidget$RevealInfo0) {
            this(circularRevealWidget$RevealInfo0.centerX, circularRevealWidget$RevealInfo0.centerY, circularRevealWidget$RevealInfo0.radius);
        }

        public boolean isInvalid() {
            return this.radius == 3.402823E+38f;
        }

        public void set(float f, float f1, float f2) {
            this.centerX = f;
            this.centerY = f1;
            this.radius = f2;
        }

        public void set(RevealInfo circularRevealWidget$RevealInfo0) {
            this.set(circularRevealWidget$RevealInfo0.centerX, circularRevealWidget$RevealInfo0.centerY, circularRevealWidget$RevealInfo0.radius);
        }
    }

    void buildCircularRevealCache();

    void destroyCircularRevealCache();

    void draw(Canvas arg1);

    Drawable getCircularRevealOverlayDrawable();

    int getCircularRevealScrimColor();

    RevealInfo getRevealInfo();

    boolean isOpaque();

    void setCircularRevealOverlayDrawable(Drawable arg1);

    void setCircularRevealScrimColor(int arg1);

    void setRevealInfo(RevealInfo arg1);

    class com.google.android.material.circularreveal.CircularRevealWidget.1 {
    }

}

