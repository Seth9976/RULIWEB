package com.airbnb.lottie.model.content;

import android.graphics.PointF;
import com.airbnb.lottie.model.CubicCurveData;
import com.airbnb.lottie.utils.Logger;
import java.util.ArrayList;
import java.util.List;

public class ShapeData {
    private boolean closed;
    private final List curves;
    private PointF initialPoint;

    public ShapeData() {
        this.curves = new ArrayList();
    }

    public ShapeData(PointF pointF0, boolean z, List list0) {
        this.initialPoint = pointF0;
        this.closed = z;
        this.curves = new ArrayList(list0);
    }

    public List getCurves() {
        return this.curves;
    }

    public PointF getInitialPoint() {
        return this.initialPoint;
    }

    public void interpolateBetween(ShapeData shapeData0, ShapeData shapeData1, float f) {
        if(this.initialPoint == null) {
            this.initialPoint = new PointF();
        }
        this.closed = shapeData0.isClosed() || shapeData1.isClosed();
        if(shapeData0.getCurves().size() != shapeData1.getCurves().size()) {
            Logger.warning(("Curves must have the same number of control points. Shape 1: " + shapeData0.getCurves().size() + "\tShape 2: " + shapeData1.getCurves().size()));
        }
        int v = Math.min(shapeData0.getCurves().size(), shapeData1.getCurves().size());
        if(this.curves.size() < v) {
            for(int v1 = this.curves.size(); v1 < v; ++v1) {
                CubicCurveData cubicCurveData0 = new CubicCurveData();
                this.curves.add(cubicCurveData0);
            }
        }
        else if(this.curves.size() > v) {
            for(int v2 = this.curves.size() - 1; v2 >= v; --v2) {
                this.curves.remove(this.curves.size() - 1);
            }
        }
        PointF pointF0 = shapeData0.getInitialPoint();
        PointF pointF1 = shapeData1.getInitialPoint();
        this.setInitialPoint(pointF0.x + f * (pointF1.x - pointF0.x), pointF0.y + f * (pointF1.y - pointF0.y));
        for(int v3 = this.curves.size() - 1; v3 >= 0; --v3) {
            CubicCurveData cubicCurveData1 = (CubicCurveData)shapeData0.getCurves().get(v3);
            CubicCurveData cubicCurveData2 = (CubicCurveData)shapeData1.getCurves().get(v3);
            PointF pointF2 = cubicCurveData1.getControlPoint1();
            PointF pointF3 = cubicCurveData1.getControlPoint2();
            PointF pointF4 = cubicCurveData1.getVertex();
            PointF pointF5 = cubicCurveData2.getControlPoint1();
            PointF pointF6 = cubicCurveData2.getControlPoint2();
            PointF pointF7 = cubicCurveData2.getVertex();
            ((CubicCurveData)this.curves.get(v3)).setControlPoint1(pointF2.x + f * (pointF5.x - pointF2.x), pointF2.y + f * (pointF5.y - pointF2.y));
            ((CubicCurveData)this.curves.get(v3)).setControlPoint2(pointF3.x + f * (pointF6.x - pointF3.x), pointF3.y + f * (pointF6.y - pointF3.y));
            ((CubicCurveData)this.curves.get(v3)).setVertex(pointF4.x + f * (pointF7.x - pointF4.x), pointF4.y + f * (pointF7.y - pointF4.y));
        }
    }

    public boolean isClosed() {
        return this.closed;
    }

    private void setInitialPoint(float f, float f1) {
        if(this.initialPoint == null) {
            this.initialPoint = new PointF();
        }
        this.initialPoint.set(f, f1);
    }

    @Override
    public String toString() {
        return "ShapeData{numCurves=" + this.curves.size() + "closed=" + this.closed + '}';
    }
}

