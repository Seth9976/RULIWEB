package androidx.core.graphics;

import android.graphics.Bitmap.Config;
import android.graphics.Bitmap;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.ColorSpace.Named;
import android.graphics.ColorSpace;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Build.VERSION;
import androidx.annotation.ReplaceWith;

public final class BitmapCompat {
    static class Api27Impl {
        static Bitmap copyBitmapIfHardware(Bitmap bitmap0) {
            return bitmap0.getConfig() == Bitmap.Config.HARDWARE ? bitmap0.copy((Build.VERSION.SDK_INT < 0x1F ? Bitmap.Config.ARGB_8888 : Api31Impl.getHardwareBitmapConfig(bitmap0)), true) : bitmap0;
        }

        static Bitmap createBitmapWithSourceColorspace(int v, int v1, Bitmap bitmap0, boolean z) {
            Bitmap.Config bitmap$Config0 = bitmap0.getConfig();
            ColorSpace colorSpace0 = bitmap0.getColorSpace();
            ColorSpace colorSpace1 = ColorSpace.get(ColorSpace.Named.LINEAR_EXTENDED_SRGB);
            if(z && !bitmap0.getColorSpace().equals(colorSpace1)) {
                return Bitmap.createBitmap(v, v1, Bitmap.Config.RGBA_F16, bitmap0.hasAlpha(), colorSpace1);
            }
            if(bitmap0.getConfig() == Bitmap.Config.HARDWARE) {
                bitmap$Config0 = Bitmap.Config.ARGB_8888;
                if(Build.VERSION.SDK_INT >= 0x1F) {
                    bitmap$Config0 = Api31Impl.getHardwareBitmapConfig(bitmap0);
                }
            }
            return Bitmap.createBitmap(v, v1, bitmap$Config0, bitmap0.hasAlpha(), colorSpace0);
        }

        static boolean isAlreadyF16AndLinear(Bitmap bitmap0) {
            ColorSpace colorSpace0 = ColorSpace.get(ColorSpace.Named.LINEAR_EXTENDED_SRGB);
            return bitmap0.getConfig() == Bitmap.Config.RGBA_F16 && bitmap0.getColorSpace().equals(colorSpace0);
        }
    }

    static class Api29Impl {
        static void setPaintBlendMode(Paint paint0) {
            paint0.setBlendMode(BlendMode.SRC);
        }
    }

    static class Api31Impl {
        static Bitmap.Config getHardwareBitmapConfig(Bitmap bitmap0) {
            return bitmap0.getHardwareBuffer().getFormat() == 22 ? Bitmap.Config.RGBA_F16 : Bitmap.Config.ARGB_8888;
        }
    }

    public static Bitmap createScaledBitmap(Bitmap bitmap0, int v, int v1, Rect rect0, boolean z) {
        Bitmap bitmap5;
        int v12;
        int v8;
        int v7;
        Bitmap bitmap4;
        double f2;
        int v6;
        if(v <= 0 || v1 <= 0) {
            throw new IllegalArgumentException("dstW and dstH must be > 0!");
        }
        if(rect0 != null && (rect0.isEmpty() || rect0.left < 0 || rect0.right > bitmap0.getWidth() || rect0.top < 0 || rect0.bottom > bitmap0.getHeight())) {
            throw new IllegalArgumentException("srcRect must be contained by srcBm!");
        }
        Bitmap bitmap1 = Build.VERSION.SDK_INT < 27 ? bitmap0 : Api27Impl.copyBitmapIfHardware(bitmap0);
        int v2 = rect0 == null ? bitmap0.getWidth() : rect0.width();
        int v3 = rect0 == null ? bitmap0.getHeight() : rect0.height();
        float f = ((float)v) / ((float)v2);
        float f1 = ((float)v1) / ((float)v3);
        int v4 = rect0 == null ? 0 : rect0.left;
        int v5 = rect0 == null ? 0 : rect0.top;
        if(v4 == 0 && v5 == 0 && v == bitmap0.getWidth() && v1 == bitmap0.getHeight()) {
            return !bitmap0.isMutable() || bitmap0 != bitmap1 ? bitmap1 : bitmap0.copy(bitmap0.getConfig(), true);
        }
        Paint paint0 = new Paint(1);
        paint0.setFilterBitmap(true);
        if(Build.VERSION.SDK_INT >= 29) {
            Api29Impl.setPaintBlendMode(paint0);
        }
        else {
            paint0.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        }
        if(v2 == v && v3 == v1) {
            Bitmap bitmap2 = Bitmap.createBitmap(v, v1, bitmap1.getConfig());
            new Canvas(bitmap2).drawBitmap(bitmap1, ((float)(-v4)), ((float)(-v5)), paint0);
            return bitmap2;
        }
        if(f > 1.0f) {
            v6 = v4;
            f2 = Math.ceil(Math.log(f) / 0.693147);
        }
        else {
            v6 = v4;
            f2 = Math.floor(Math.log(f) / 0.693147);
        }
        double f3 = f1 > 1.0f ? Math.ceil(Math.log(f1) / 0.693147) : Math.floor(Math.log(f1) / 0.693147);
        if(!z || Build.VERSION.SDK_INT < 27 || Api27Impl.isAlreadyF16AndLinear(bitmap0)) {
            bitmap4 = null;
            v7 = v6;
            v8 = 0;
        }
        else {
            Bitmap bitmap3 = Api27Impl.createBitmapWithSourceColorspace((((int)f2) <= 0 ? v2 : BitmapCompat.sizeAtStep(v2, v, 1, ((int)f2))), (((int)f3) <= 0 ? v3 : BitmapCompat.sizeAtStep(v3, v1, 1, ((int)f3))), bitmap0, true);
            new Canvas(bitmap3).drawBitmap(bitmap1, ((float)(-v6)), ((float)(-v5)), paint0);
            bitmap4 = bitmap1;
            bitmap1 = bitmap3;
            v5 = 0;
            v7 = 0;
            v8 = 1;
        }
        Rect rect1 = new Rect(v7, v5, v2, v3);
        Rect rect2 = new Rect();
        int v9 = (int)f2;
        int v10 = (int)f3;
        while(v9 != 0 || v10 != 0) {
            if(v9 < 0) {
                ++v9;
            }
            else if(v9 > 0) {
                --v9;
            }
            if(v10 < 0) {
                ++v10;
            }
            else if(v10 > 0) {
                --v10;
            }
            rect2.set(0, 0, BitmapCompat.sizeAtStep(v2, v, v9, ((int)f2)), BitmapCompat.sizeAtStep(v3, v1, v10, ((int)f3)));
            int v11 = v9 != 0 || v10 != 0 ? 0 : 1;
            boolean z1 = bitmap4 != null && bitmap4.getWidth() == v && bitmap4.getHeight() == v1;
            if(bitmap4 == null || bitmap4 == bitmap0) {
                v12 = v11;
            }
            else if(z) {
                v12 = v11;
                if(Build.VERSION.SDK_INT < 27 || Api27Impl.isAlreadyF16AndLinear(bitmap4)) {
                    goto label_65;
                }
            }
            else {
                v12 = v11;
            label_65:
                if(v12 == 0 || z1 && v8 == 0) {
                    bitmap5 = bitmap4;
                    goto label_74;
                }
            }
            if(bitmap4 != bitmap0 && bitmap4 != null) {
                bitmap4.recycle();
            }
            int v13 = BitmapCompat.sizeAtStep(v2, v, (v9 <= 0 ? v9 : v8), ((int)f2));
            int v14 = BitmapCompat.sizeAtStep(v3, v1, (v10 <= 0 ? v10 : v8), ((int)f3));
            bitmap5 = Build.VERSION.SDK_INT < 27 ? Bitmap.createBitmap(v13, v14, bitmap1.getConfig()) : Api27Impl.createBitmapWithSourceColorspace(v13, v14, bitmap0, z && v12 == 0);
        label_74:
            new Canvas(bitmap5).drawBitmap(bitmap1, rect1, rect2, paint0);
            rect1.set(rect2);
            bitmap4 = bitmap1;
            bitmap1 = bitmap5;
        }
        if(bitmap4 != bitmap0 && bitmap4 != null) {
            bitmap4.recycle();
        }
        return bitmap1;
    }

    @ReplaceWith(expression = "bitmap.getAllocationByteCount()")
    @Deprecated
    public static int getAllocationByteCount(Bitmap bitmap0) {
        return bitmap0.getAllocationByteCount();
    }

    @ReplaceWith(expression = "bitmap.hasMipMap()")
    @Deprecated
    public static boolean hasMipMap(Bitmap bitmap0) {
        return bitmap0.hasMipMap();
    }

    @ReplaceWith(expression = "bitmap.setHasMipMap(hasMipMap)")
    @Deprecated
    public static void setHasMipMap(Bitmap bitmap0, boolean z) {
        bitmap0.setHasMipMap(z);
    }

    static int sizeAtStep(int v, int v1, int v2, int v3) {
        if(v2 == 0) {
            return v1;
        }
        return v2 <= 0 ? v1 << -v2 - 1 : v * (1 << v3 - v2);
    }
}

