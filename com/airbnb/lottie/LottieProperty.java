package com.airbnb.lottie;

import android.graphics.ColorFilter;
import android.graphics.PointF;
import com.airbnb.lottie.value.ScaleXY;

public interface LottieProperty {
    public static final Integer COLOR;
    public static final ColorFilter COLOR_FILTER;
    public static final Float CORNER_RADIUS;
    public static final PointF ELLIPSE_SIZE;
    public static final Integer[] GRADIENT_COLOR;
    public static final Integer OPACITY;
    public static final Float POLYSTAR_INNER_RADIUS;
    public static final Float POLYSTAR_INNER_ROUNDEDNESS;
    public static final Float POLYSTAR_OUTER_RADIUS;
    public static final Float POLYSTAR_OUTER_ROUNDEDNESS;
    public static final Float POLYSTAR_POINTS;
    public static final Float POLYSTAR_ROTATION;
    public static final PointF POSITION;
    public static final PointF RECTANGLE_SIZE;
    public static final Float REPEATER_COPIES;
    public static final Float REPEATER_OFFSET;
    public static final Integer STROKE_COLOR;
    public static final Float STROKE_WIDTH;
    public static final Float TEXT_TRACKING;
    public static final Float TIME_REMAP;
    public static final PointF TRANSFORM_ANCHOR_POINT;
    public static final Float TRANSFORM_END_OPACITY;
    public static final Integer TRANSFORM_OPACITY;
    public static final PointF TRANSFORM_POSITION;
    public static final Float TRANSFORM_ROTATION;
    public static final ScaleXY TRANSFORM_SCALE;
    public static final Float TRANSFORM_SKEW;
    public static final Float TRANSFORM_SKEW_ANGLE;
    public static final Float TRANSFORM_START_OPACITY;

    static {
        LottieProperty.COLOR = 1;
        LottieProperty.STROKE_COLOR = 2;
        LottieProperty.TRANSFORM_OPACITY = 3;
        LottieProperty.OPACITY = 4;
        LottieProperty.TRANSFORM_ANCHOR_POINT = new PointF();
        LottieProperty.TRANSFORM_POSITION = new PointF();
        LottieProperty.ELLIPSE_SIZE = new PointF();
        LottieProperty.RECTANGLE_SIZE = new PointF();
        LottieProperty.CORNER_RADIUS = 0.0f;
        LottieProperty.POSITION = new PointF();
        LottieProperty.TRANSFORM_SCALE = new ScaleXY();
        LottieProperty.TRANSFORM_ROTATION = 1.0f;
        LottieProperty.TRANSFORM_SKEW = 0.0f;
        LottieProperty.TRANSFORM_SKEW_ANGLE = 0.0f;
        LottieProperty.STROKE_WIDTH = 2.0f;
        LottieProperty.TEXT_TRACKING = 3.0f;
        LottieProperty.REPEATER_COPIES = 4.0f;
        LottieProperty.REPEATER_OFFSET = 5.0f;
        LottieProperty.POLYSTAR_POINTS = 6.0f;
        LottieProperty.POLYSTAR_ROTATION = 7.0f;
        LottieProperty.POLYSTAR_INNER_RADIUS = 8.0f;
        LottieProperty.POLYSTAR_OUTER_RADIUS = 9.0f;
        LottieProperty.POLYSTAR_INNER_ROUNDEDNESS = 10.0f;
        LottieProperty.POLYSTAR_OUTER_ROUNDEDNESS = 11.0f;
        LottieProperty.TRANSFORM_START_OPACITY = 12.0f;
        LottieProperty.TRANSFORM_END_OPACITY = 12.0f;
        LottieProperty.TIME_REMAP = 13.0f;
        LottieProperty.COLOR_FILTER = new ColorFilter();
        LottieProperty.GRADIENT_COLOR = new Integer[0];
    }
}

