package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.Log;
import android.widget.CompoundButton;
import java.lang.reflect.Field;

public final class CompoundButtonCompat {
    static class Api21Impl {
        static ColorStateList getButtonTintList(CompoundButton compoundButton0) {
            return compoundButton0.getButtonTintList();
        }

        static PorterDuff.Mode getButtonTintMode(CompoundButton compoundButton0) {
            return compoundButton0.getButtonTintMode();
        }

        static void setButtonTintList(CompoundButton compoundButton0, ColorStateList colorStateList0) {
            compoundButton0.setButtonTintList(colorStateList0);
        }

        static void setButtonTintMode(CompoundButton compoundButton0, PorterDuff.Mode porterDuff$Mode0) {
            compoundButton0.setButtonTintMode(porterDuff$Mode0);
        }
    }

    static class Api23Impl {
        static Drawable getButtonDrawable(CompoundButton compoundButton0) {
            return compoundButton0.getButtonDrawable();
        }
    }

    private static final String TAG = "CompoundButtonCompat";
    private static Field sButtonDrawableField;
    private static boolean sButtonDrawableFieldFetched;

    public static Drawable getButtonDrawable(CompoundButton compoundButton0) {
        if(Build.VERSION.SDK_INT >= 23) {
            return Api23Impl.getButtonDrawable(compoundButton0);
        }
        if(!CompoundButtonCompat.sButtonDrawableFieldFetched) {
            try {
                Field field0 = CompoundButton.class.getDeclaredField("mButtonDrawable");
                CompoundButtonCompat.sButtonDrawableField = field0;
                field0.setAccessible(true);
            }
            catch(NoSuchFieldException noSuchFieldException0) {
                Log.i("CompoundButtonCompat", "Failed to retrieve mButtonDrawable field", noSuchFieldException0);
            }
            CompoundButtonCompat.sButtonDrawableFieldFetched = true;
        }
        Field field1 = CompoundButtonCompat.sButtonDrawableField;
        if(field1 != null) {
            try {
                return (Drawable)field1.get(compoundButton0);
            }
            catch(IllegalAccessException illegalAccessException0) {
                Log.i("CompoundButtonCompat", "Failed to get button drawable via reflection", illegalAccessException0);
                CompoundButtonCompat.sButtonDrawableField = null;
            }
        }
        return null;
    }

    public static ColorStateList getButtonTintList(CompoundButton compoundButton0) {
        return Api21Impl.getButtonTintList(compoundButton0);
    }

    public static PorterDuff.Mode getButtonTintMode(CompoundButton compoundButton0) {
        return Api21Impl.getButtonTintMode(compoundButton0);
    }

    public static void setButtonTintList(CompoundButton compoundButton0, ColorStateList colorStateList0) {
        Api21Impl.setButtonTintList(compoundButton0, colorStateList0);
    }

    public static void setButtonTintMode(CompoundButton compoundButton0, PorterDuff.Mode porterDuff$Mode0) {
        Api21Impl.setButtonTintMode(compoundButton0, porterDuff$Mode0);
    }
}

