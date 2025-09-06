package androidx.appcompat.widget;

import android.graphics.Insets;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import androidx.core.graphics.drawable.DrawableCompat;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DrawableUtils {
    static class Api18Impl {
        private static final Field sBottom;
        private static final Method sGetOpticalInsets;
        private static final Field sLeft;
        private static final boolean sReflectionSuccessful;
        private static final Field sRight;
        private static final Field sTop;

        static {
            Field field1;
            boolean z;
            Field field3;
            Field field0;
            Method method0;
            Class class0;
            try {
                class0 = Insets.class;
                method0 = null;
                method0 = Drawable.class.getMethod("getOpticalInsets", null);
            }
            catch(NoSuchMethodException unused_ex) {
                field0 = null;
                field1 = null;
                goto label_18;
            }
            catch(ClassNotFoundException unused_ex) {
                field0 = null;
                field1 = null;
                goto label_18;
            }
            catch(NoSuchFieldException unused_ex) {
                field0 = null;
                field1 = null;
                goto label_18;
            }
            try {
                field0 = null;
                field0 = class0.getField("left");
                field1 = null;
                field1 = class0.getField("top");
                goto label_20;
            }
            catch(NoSuchMethodException | ClassNotFoundException | NoSuchFieldException unused_ex) {
            }
            field1 = null;
            goto label_18;
            try {
                field1 = null;
                field1 = class0.getField("top");
                goto label_20;
            }
            catch(NoSuchMethodException | ClassNotFoundException | NoSuchFieldException unused_ex) {
            }
        label_18:
            Field field2 = null;
            goto label_27;
            try {
            label_20:
                field2 = class0.getField("right");
            }
            catch(NoSuchMethodException | ClassNotFoundException | NoSuchFieldException unused_ex) {
                field2 = null;
                goto label_27;
            }
            try {
                field3 = class0.getField("bottom");
                z = true;
                goto label_29;
            }
            catch(NoSuchMethodException | ClassNotFoundException | NoSuchFieldException unused_ex) {
            }
        label_27:
            field3 = null;
            z = false;
        label_29:
            if(z) {
                Api18Impl.sGetOpticalInsets = method0;
                Api18Impl.sLeft = field0;
                Api18Impl.sTop = field1;
                Api18Impl.sRight = field2;
                Api18Impl.sBottom = field3;
                Api18Impl.sReflectionSuccessful = true;
                return;
            }
            Api18Impl.sGetOpticalInsets = null;
            Api18Impl.sLeft = null;
            Api18Impl.sTop = null;
            Api18Impl.sRight = null;
            Api18Impl.sBottom = null;
            Api18Impl.sReflectionSuccessful = false;
        }

        static Rect getOpticalInsets(Drawable drawable0) {
            if(Build.VERSION.SDK_INT < 29 && Api18Impl.sReflectionSuccessful) {
                try {
                    Object object0 = Api18Impl.sGetOpticalInsets.invoke(drawable0, null);
                    return object0 == null ? DrawableUtils.INSETS_NONE : new Rect(Api18Impl.sLeft.getInt(object0), Api18Impl.sTop.getInt(object0), Api18Impl.sRight.getInt(object0), Api18Impl.sBottom.getInt(object0));
                }
                catch(IllegalAccessException | InvocationTargetException unused_ex) {
                }
            }
            return DrawableUtils.INSETS_NONE;
        }
    }

    static class Api29Impl {
        static Insets getOpticalInsets(Drawable drawable0) {
            return drawable0.getOpticalInsets();
        }
    }

    private static final int[] CHECKED_STATE_SET;
    private static final int[] EMPTY_STATE_SET;
    public static final Rect INSETS_NONE;

    static {
        DrawableUtils.CHECKED_STATE_SET = new int[]{0x10100A0};
        DrawableUtils.EMPTY_STATE_SET = new int[0];
        DrawableUtils.INSETS_NONE = new Rect();
    }

    @Deprecated
    public static boolean canSafelyMutateDrawable(Drawable drawable0) [...] // Inlined contents

    static void fixDrawable(Drawable drawable0) {
        String s = drawable0.getClass().getName();
        if(Build.VERSION.SDK_INT == 21 && "android.graphics.drawable.VectorDrawable".equals(s)) {
            DrawableUtils.forceDrawableStateChange(drawable0);
            return;
        }
        if(Build.VERSION.SDK_INT >= 29 && Build.VERSION.SDK_INT < 0x1F && "android.graphics.drawable.ColorStateListDrawable".equals(s)) {
            DrawableUtils.forceDrawableStateChange(drawable0);
        }
    }

    private static void forceDrawableStateChange(Drawable drawable0) {
        int[] arr_v = drawable0.getState();
        if(arr_v == null || arr_v.length == 0) {
            drawable0.setState(DrawableUtils.CHECKED_STATE_SET);
        }
        else {
            drawable0.setState(DrawableUtils.EMPTY_STATE_SET);
        }
        drawable0.setState(arr_v);
    }

    public static Rect getOpticalBounds(Drawable drawable0) {
        if(Build.VERSION.SDK_INT >= 29) {
            Insets insets0 = Api29Impl.getOpticalInsets(drawable0);
            return new Rect(insets0.left, insets0.top, insets0.right, insets0.bottom);
        }
        return Api18Impl.getOpticalInsets(DrawableCompat.unwrap(drawable0));
    }

    public static PorterDuff.Mode parseTintMode(int v, PorterDuff.Mode porterDuff$Mode0) {
        switch(v) {
            case 3: {
                return PorterDuff.Mode.SRC_OVER;
            }
            case 5: {
                return PorterDuff.Mode.SRC_IN;
            }
            case 9: {
                return PorterDuff.Mode.SRC_ATOP;
            }
            case 14: {
                return PorterDuff.Mode.MULTIPLY;
            }
            case 15: {
                return PorterDuff.Mode.SCREEN;
            }
            case 16: {
                return PorterDuff.Mode.ADD;
            }
            default: {
                return porterDuff$Mode0;
            }
        }
    }
}

