package androidx.transition;

import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.widget.ImageView;
import java.lang.reflect.Field;

class ImageViewUtils {
    static class Api29Impl {
        static void animateTransform(ImageView imageView0, Matrix matrix0) {
            imageView0.animateTransform(matrix0);
        }
    }

    private static Field sDrawMatrixField = null;
    private static boolean sDrawMatrixFieldFetched = false;
    private static boolean sTryHiddenAnimateTransform = true;

    static {
    }

    static void animateTransform(ImageView imageView0, Matrix matrix0) {
        if(Build.VERSION.SDK_INT >= 29) {
            Api29Impl.animateTransform(imageView0, matrix0);
            return;
        }
        if(matrix0 == null) {
            Drawable drawable0 = imageView0.getDrawable();
            if(drawable0 != null) {
                drawable0.setBounds(0, 0, imageView0.getWidth() - imageView0.getPaddingLeft() - imageView0.getPaddingRight(), imageView0.getHeight() - imageView0.getPaddingTop() - imageView0.getPaddingBottom());
                imageView0.invalidate();
            }
            return;
        }
        ImageViewUtils.hiddenAnimateTransform(imageView0, matrix0);
    }

    private static void fetchDrawMatrixField() {
        if(!ImageViewUtils.sDrawMatrixFieldFetched) {
            try {
                Field field0 = ImageView.class.getDeclaredField("mDrawMatrix");
                ImageViewUtils.sDrawMatrixField = field0;
                field0.setAccessible(true);
            }
            catch(NoSuchFieldException unused_ex) {
            }
            ImageViewUtils.sDrawMatrixFieldFetched = true;
        }
    }

    private static void hiddenAnimateTransform(ImageView imageView0, Matrix matrix0) {
        if(ImageViewUtils.sTryHiddenAnimateTransform) {
            try {
                Api29Impl.animateTransform(imageView0, matrix0);
            }
            catch(NoSuchMethodError unused_ex) {
                ImageViewUtils.sTryHiddenAnimateTransform = false;
            }
        }
    }
}

