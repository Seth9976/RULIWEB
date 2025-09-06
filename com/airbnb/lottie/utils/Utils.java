package com.airbnb.lottie.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap.Config;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.provider.Settings.Global;
import com.airbnb.lottie.L;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.content.TrimPathContent;
import com.airbnb.lottie.animation.keyframe.FloatKeyframeAnimation;
import java.io.Closeable;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.nio.channels.ClosedChannelException;
import javax.net.ssl.SSLException;

public final class Utils {
    public static final int SECOND_IN_NANOS = 1000000000;
    private static final float SQRT_2;
    private static float dpScale;
    private static final PathMeasure pathMeasure;
    private static final float[] points;
    private static final Path tempPath;
    private static final Path tempPath2;

    static {
        Utils.pathMeasure = new PathMeasure();
        Utils.tempPath = new Path();
        Utils.tempPath2 = new Path();
        Utils.points = new float[4];
        Utils.SQRT_2 = 1.414214f;
        Utils.dpScale = -1.0f;
    }

    public static void applyTrimPathIfNeeded(Path path0, float f, float f1, float f2) {
        L.beginSection("applyTrimPathIfNeeded");
        PathMeasure pathMeasure0 = Utils.pathMeasure;
        pathMeasure0.setPath(path0, false);
        float f3 = pathMeasure0.getLength();
        if(f == 1.0f && f1 == 0.0f) {
            L.endSection("applyTrimPathIfNeeded");
            return;
        }
        if(f3 >= 1.0f && ((double)Math.abs(f1 - f - 1.0f)) >= 0.01) {
            float f4 = f * f3;
            float f5 = f1 * f3;
            float f6 = f2 * f3;
            float f7 = Math.min(f4, f5) + f6;
            float f8 = Math.max(f4, f5) + f6;
            if(f7 >= f3 && f8 >= f3) {
                f7 = (float)MiscUtils.floorMod(f7, f3);
                f8 = (float)MiscUtils.floorMod(f8, f3);
            }
            if(f7 < 0.0f) {
                f7 = (float)MiscUtils.floorMod(f7, f3);
            }
            if(f8 < 0.0f) {
                f8 = (float)MiscUtils.floorMod(f8, f3);
            }
            int v = Float.compare(f7, f8);
            if(v == 0) {
                path0.reset();
                L.endSection("applyTrimPathIfNeeded");
                return;
            }
            if(v >= 0) {
                f7 -= f3;
            }
            Path path1 = Utils.tempPath;
            path1.reset();
            pathMeasure0.getSegment(f7, f8, path1, true);
            if(f8 > f3) {
                Utils.tempPath2.reset();
                pathMeasure0.getSegment(0.0f, f8 % f3, Utils.tempPath2, true);
                path1.addPath(Utils.tempPath2);
            }
            else if(f7 < 0.0f) {
                Utils.tempPath2.reset();
                pathMeasure0.getSegment(f7 + f3, f3, Utils.tempPath2, true);
                path1.addPath(Utils.tempPath2);
            }
            path0.set(path1);
            L.endSection("applyTrimPathIfNeeded");
            return;
        }
        L.endSection("applyTrimPathIfNeeded");
    }

    public static void applyTrimPathIfNeeded(Path path0, TrimPathContent trimPathContent0) {
        if(trimPathContent0 != null && !trimPathContent0.isHidden()) {
            Utils.applyTrimPathIfNeeded(path0, ((FloatKeyframeAnimation)trimPathContent0.getStart()).getFloatValue() / 100.0f, ((FloatKeyframeAnimation)trimPathContent0.getEnd()).getFloatValue() / 100.0f, ((FloatKeyframeAnimation)trimPathContent0.getOffset()).getFloatValue() / 360.0f);
        }
    }

    public static void closeQuietly(Closeable closeable0) {
        if(closeable0 != null) {
            try {
                closeable0.close();
                return;
            }
            catch(RuntimeException runtimeException0) {
            }
            catch(Exception unused_ex) {
                return;
            }
            throw runtimeException0;
        }
    }

    public static Path createPath(PointF pointF0, PointF pointF1, PointF pointF2, PointF pointF3) {
        Path path0 = new Path();
        path0.moveTo(pointF0.x, pointF0.y);
        if(pointF2 != null && pointF3 != null && (pointF2.length() != 0.0f || pointF3.length() != 0.0f)) {
            path0.cubicTo(pointF0.x + pointF2.x, pointF0.y + pointF2.y, pointF1.x + pointF3.x, pointF1.y + pointF3.y, pointF1.x, pointF1.y);
            return path0;
        }
        path0.lineTo(pointF1.x, pointF1.y);
        return path0;
    }

    public static float dpScale() {
        if(Utils.dpScale == -1.0f) {
            Utils.dpScale = Resources.getSystem().getDisplayMetrics().density;
        }
        return Utils.dpScale;
    }

    public static float getAnimationScale(Context context0) {
        return Settings.Global.getFloat(context0.getContentResolver(), "animator_duration_scale", 1.0f);
    }

    public static float getScale(Matrix matrix0) {
        Utils.points[0] = 0.0f;
        Utils.points[1] = 0.0f;
        Utils.points[2] = Utils.SQRT_2;
        Utils.points[3] = Utils.SQRT_2;
        matrix0.mapPoints(Utils.points);
        return ((float)Math.hypot(Utils.points[2] - Utils.points[0], Utils.points[3] - Utils.points[1])) / 2.0f;
    }

    public static boolean hasZeroScaleAxis(Matrix matrix0) {
        Utils.points[0] = 0.0f;
        Utils.points[1] = 0.0f;
        Utils.points[2] = 37394.730469f;
        Utils.points[3] = 39575.234375f;
        matrix0.mapPoints(Utils.points);
        return Utils.points[0] == Utils.points[2] || Utils.points[1] == Utils.points[3];
    }

    public static int hashFor(float f, float f1, float f2, float f3) {
        int v = f == 0.0f ? 17 : ((int)(527.0f * f));
        if(f1 != 0.0f) {
            v = (int)(((float)(v * 0x1F)) * f1);
        }
        if(f2 != 0.0f) {
            v = (int)(((float)(v * 0x1F)) * f2);
        }
        return f3 == 0.0f ? v : ((int)(((float)(v * 0x1F)) * f3));
    }

    public static boolean isAtLeastVersion(int v, int v1, int v2, int v3, int v4, int v5) {
        if(v < v3) {
            return false;
        }
        if(v > v3) {
            return true;
        }
        if(v1 < v4) {
            return false;
        }
        return v1 <= v4 ? v2 >= v5 : true;
    }

    // 去混淆评级： 中等(70)
    public static boolean isNetworkException(Throwable throwable0) {
        return throwable0 instanceof SocketException || throwable0 instanceof ClosedChannelException || throwable0 instanceof InterruptedIOException || throwable0 instanceof ProtocolException || throwable0 instanceof SSLException || throwable0 instanceof UnknownHostException || throwable0 instanceof UnknownServiceException;
    }

    public static Bitmap renderPath(Path path0) {
        RectF rectF0 = new RectF();
        path0.computeBounds(rectF0, false);
        Bitmap bitmap0 = Bitmap.createBitmap(((int)rectF0.right), ((int)rectF0.bottom), Bitmap.Config.ARGB_8888);
        Canvas canvas0 = new Canvas(bitmap0);
        LPaint lPaint0 = new LPaint();
        lPaint0.setAntiAlias(true);
        lPaint0.setColor(0xFF0000FF);
        canvas0.drawPath(path0, lPaint0);
        return bitmap0;
    }

    public static Bitmap resizeBitmapIfNeeded(Bitmap bitmap0, int v, int v1) {
        if(bitmap0.getWidth() == v && bitmap0.getHeight() == v1) {
            return bitmap0;
        }
        Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap0, v, v1, true);
        bitmap0.recycle();
        return bitmap1;
    }

    public static void saveLayerCompat(Canvas canvas0, RectF rectF0, Paint paint0) {
        Utils.saveLayerCompat(canvas0, rectF0, paint0, 0x1F);
    }

    public static void saveLayerCompat(Canvas canvas0, RectF rectF0, Paint paint0, int v) {
        L.beginSection("Utils#saveLayer");
        if(Build.VERSION.SDK_INT < 23) {
            canvas0.saveLayer(rectF0, paint0, v);
        }
        else {
            canvas0.saveLayer(rectF0, paint0);
        }
        L.endSection("Utils#saveLayer");
    }
}

