package com.google.android.material.resources;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.TypedValue;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.TintTypedArray;
import com.google.android.material.R.styleable;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;

public class MaterialResources {
    private static final float FONT_SCALE_1_3 = 1.3f;
    private static final float FONT_SCALE_2_0 = 2.0f;

    public static ColorStateList getColorStateList(Context context0, TypedArray typedArray0, int v) {
        if(typedArray0.hasValue(v)) {
            int v1 = typedArray0.getResourceId(v, 0);
            if(v1 != 0) {
                ColorStateList colorStateList0 = AppCompatResources.getColorStateList(context0, v1);
                return colorStateList0 == null ? typedArray0.getColorStateList(v) : colorStateList0;
            }
        }
        return typedArray0.getColorStateList(v);
    }

    public static ColorStateList getColorStateList(Context context0, TintTypedArray tintTypedArray0, int v) {
        if(tintTypedArray0.hasValue(v)) {
            int v1 = tintTypedArray0.getResourceId(v, 0);
            if(v1 != 0) {
                ColorStateList colorStateList0 = AppCompatResources.getColorStateList(context0, v1);
                return colorStateList0 == null ? tintTypedArray0.getColorStateList(v) : colorStateList0;
            }
        }
        return tintTypedArray0.getColorStateList(v);
    }

    private static int getComplexUnit(TypedValue typedValue0) {
        return Build.VERSION.SDK_INT < 22 ? typedValue0.data & 15 : LinkFollowing..ExternalSyntheticApiModelOutline0.m(typedValue0);
    }

    public static int getDimensionPixelSize(Context context0, TypedArray typedArray0, int v, int v1) {
        TypedValue typedValue0 = new TypedValue();
        if(typedArray0.getValue(v, typedValue0) && typedValue0.type == 2) {
            TypedArray typedArray1 = context0.getTheme().obtainStyledAttributes(new int[]{typedValue0.data});
            int v2 = typedArray1.getDimensionPixelSize(0, v1);
            typedArray1.recycle();
            return v2;
        }
        return typedArray0.getDimensionPixelSize(v, v1);
    }

    public static Drawable getDrawable(Context context0, TypedArray typedArray0, int v) {
        if(typedArray0.hasValue(v)) {
            int v1 = typedArray0.getResourceId(v, 0);
            if(v1 != 0) {
                Drawable drawable0 = AppCompatResources.getDrawable(context0, v1);
                return drawable0 == null ? typedArray0.getDrawable(v) : drawable0;
            }
        }
        return typedArray0.getDrawable(v);
    }

    public static float getFontScale(Context context0) {
        return context0.getResources().getConfiguration().fontScale;
    }

    // 去混淆评级： 低(20)
    static int getIndexWithValue(TypedArray typedArray0, int v, int v1) {
        return typedArray0.hasValue(v) ? v : v1;
    }

    public static TextAppearance getTextAppearance(Context context0, TypedArray typedArray0, int v) {
        if(typedArray0.hasValue(v)) {
            int v1 = typedArray0.getResourceId(v, 0);
            return v1 == 0 ? null : new TextAppearance(context0, v1);
        }
        return null;
    }

    public static int getUnscaledTextSize(Context context0, int v, int v1) {
        if(v != 0) {
            TypedArray typedArray0 = context0.obtainStyledAttributes(v, styleable.TextAppearance);
            TypedValue typedValue0 = new TypedValue();
            boolean z = typedArray0.getValue(styleable.TextAppearance_android_textSize, typedValue0);
            typedArray0.recycle();
            if(z) {
                return MaterialResources.getComplexUnit(typedValue0) == 2 ? Math.round(TypedValue.complexToFloat(typedValue0.data) * context0.getResources().getDisplayMetrics().density) : TypedValue.complexToDimensionPixelSize(typedValue0.data, context0.getResources().getDisplayMetrics());
            }
        }
        return v1;
    }

    public static boolean isFontScaleAtLeast1_3(Context context0) {
        return context0.getResources().getConfiguration().fontScale >= 1.3f;
    }

    public static boolean isFontScaleAtLeast2_0(Context context0) {
        return context0.getResources().getConfiguration().fontScale >= 2.0f;
    }
}

