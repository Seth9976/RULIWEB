package com.airbnb.lottie.utils;

import android.graphics.Path;
import android.graphics.PointF;
import com.airbnb.lottie.animation.content.KeyPathElementContent;
import com.airbnb.lottie.model.CubicCurveData;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.ShapeData;
import java.util.List;

public class MiscUtils {
    private static PointF pathFromDataCurrentPoint;

    static {
        MiscUtils.pathFromDataCurrentPoint = new PointF();
    }

    public static PointF addPoints(PointF pointF0, PointF pointF1) {
        return new PointF(pointF0.x + pointF1.x, pointF0.y + pointF1.y);
    }

    public static float clamp(float f, float f1, float f2) {
        return Math.max(f1, Math.min(f2, f));
    }

    public static int clamp(int v, int v1, int v2) {
        return Math.max(v1, Math.min(v2, v));
    }

    public static boolean contains(float f, float f1, float f2) {
        return f >= f1 && f <= f2;
    }

    private static int floorDiv(int v, int v1) {
        int v2 = v / v1;
        return (v ^ v1) >= 0 || v % v1 == 0 ? v2 : v2 - 1;
    }

    static int floorMod(float f, float f1) {
        return MiscUtils.floorMod(((int)f), ((int)f1));
    }

    private static int floorMod(int v, int v1) {
        return v - v1 * MiscUtils.floorDiv(v, v1);
    }

    public static void getPathFromData(ShapeData shapeData0, Path path0) {
        path0.reset();
        PointF pointF0 = shapeData0.getInitialPoint();
        path0.moveTo(pointF0.x, pointF0.y);
        MiscUtils.pathFromDataCurrentPoint.set(pointF0.x, pointF0.y);
        for(int v = 0; v < shapeData0.getCurves().size(); ++v) {
            CubicCurveData cubicCurveData0 = (CubicCurveData)shapeData0.getCurves().get(v);
            PointF pointF1 = cubicCurveData0.getControlPoint1();
            PointF pointF2 = cubicCurveData0.getControlPoint2();
            PointF pointF3 = cubicCurveData0.getVertex();
            if(!pointF1.equals(MiscUtils.pathFromDataCurrentPoint) || !pointF2.equals(pointF3)) {
                path0.cubicTo(pointF1.x, pointF1.y, pointF2.x, pointF2.y, pointF3.x, pointF3.y);
            }
            else {
                path0.lineTo(pointF3.x, pointF3.y);
            }
            MiscUtils.pathFromDataCurrentPoint.set(pointF3.x, pointF3.y);
        }
        if(shapeData0.isClosed()) {
            path0.close();
        }
    }

    public static double lerp(double f, double f1, double f2) {
        return f + f2 * (f1 - f);
    }

    public static float lerp(float f, float f1, float f2) [...] // Inlined contents

    public static int lerp(int v, int v1, float f) {
        return (int)(((float)v) + f * ((float)(v1 - v)));
    }

    public static void resolveKeyPath(KeyPath keyPath0, int v, List list0, KeyPath keyPath1, KeyPathElementContent keyPathElementContent0) {
        if(keyPath0.fullyResolvesTo(keyPathElementContent0.getName(), v)) {
            list0.add(keyPath1.addKey(keyPathElementContent0.getName()).resolve(keyPathElementContent0));
        }
    }
}

