package com.airbnb.lottie.model;

import android.graphics.PointF;

public class CubicCurveData {
    private final PointF controlPoint1;
    private final PointF controlPoint2;
    private final PointF vertex;

    public CubicCurveData() {
        this.controlPoint1 = new PointF();
        this.controlPoint2 = new PointF();
        this.vertex = new PointF();
    }

    public CubicCurveData(PointF pointF0, PointF pointF1, PointF pointF2) {
        this.controlPoint1 = pointF0;
        this.controlPoint2 = pointF1;
        this.vertex = pointF2;
    }

    public PointF getControlPoint1() {
        return this.controlPoint1;
    }

    public PointF getControlPoint2() {
        return this.controlPoint2;
    }

    public PointF getVertex() {
        return this.vertex;
    }

    public void setControlPoint1(float f, float f1) {
        this.controlPoint1.set(f, f1);
    }

    public void setControlPoint2(float f, float f1) {
        this.controlPoint2.set(f, f1);
    }

    public void setVertex(float f, float f1) {
        this.vertex.set(f, f1);
    }
}

