package androidx.core.content.res;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\r\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A\u0016\u0010\u0000\u001A\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0003\u001A\u00020\u0004H\u0002\u001A\u0014\u0010\u0005\u001A\u00020\u0006*\u00020\u00022\b\b\u0001\u0010\u0003\u001A\u00020\u0004\u001A\u0016\u0010\u0007\u001A\u00020\u0004*\u00020\u00022\b\b\u0001\u0010\u0003\u001A\u00020\u0004H\u0007\u001A\u0014\u0010\b\u001A\u00020\t*\u00020\u00022\b\b\u0001\u0010\u0003\u001A\u00020\u0004\u001A\u0014\u0010\n\u001A\u00020\u000B*\u00020\u00022\b\b\u0001\u0010\u0003\u001A\u00020\u0004\u001A\u0016\u0010\f\u001A\u00020\u0004*\u00020\u00022\b\b\u0001\u0010\u0003\u001A\u00020\u0004H\u0007\u001A\u0016\u0010\r\u001A\u00020\u0004*\u00020\u00022\b\b\u0001\u0010\u0003\u001A\u00020\u0004H\u0007\u001A\u0014\u0010\u000E\u001A\u00020\u000F*\u00020\u00022\b\b\u0001\u0010\u0003\u001A\u00020\u0004\u001A\u0014\u0010\u0010\u001A\u00020\u000B*\u00020\u00022\b\b\u0001\u0010\u0003\u001A\u00020\u0004\u001A\u0016\u0010\u0011\u001A\u00020\u0012*\u00020\u00022\b\b\u0001\u0010\u0003\u001A\u00020\u0004H\u0007\u001A\u0014\u0010\u0013\u001A\u00020\u0004*\u00020\u00022\b\b\u0001\u0010\u0003\u001A\u00020\u0004\u001A\u0014\u0010\u0014\u001A\u00020\u0004*\u00020\u00022\b\b\u0001\u0010\u0003\u001A\u00020\u0004\u001A\u0016\u0010\u0015\u001A\u00020\u0004*\u00020\u00022\b\b\u0001\u0010\u0003\u001A\u00020\u0004H\u0007\u001A\u0014\u0010\u0016\u001A\u00020\u0017*\u00020\u00022\b\b\u0001\u0010\u0003\u001A\u00020\u0004\u001A\u001F\u0010\u0018\u001A\b\u0012\u0004\u0012\u00020\u001A0\u0019*\u00020\u00022\b\b\u0001\u0010\u0003\u001A\u00020\u0004\u00A2\u0006\u0002\u0010\u001B\u001A\u0014\u0010\u001C\u001A\u00020\u001A*\u00020\u00022\b\b\u0001\u0010\u0003\u001A\u00020\u0004\u001A,\u0010\u001D\u001A\u0002H\u001E\"\u0004\b\u0000\u0010\u001E*\u00020\u00022\u0012\u0010\u001F\u001A\u000E\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H\u001E0 H\u0086\b\u00A2\u0006\u0002\u0010!\u00A8\u0006\""}, d2 = {"checkAttribute", "", "Landroid/content/res/TypedArray;", "index", "", "getBooleanOrThrow", "", "getColorOrThrow", "getColorStateListOrThrow", "Landroid/content/res/ColorStateList;", "getDimensionOrThrow", "", "getDimensionPixelOffsetOrThrow", "getDimensionPixelSizeOrThrow", "getDrawableOrThrow", "Landroid/graphics/drawable/Drawable;", "getFloatOrThrow", "getFontOrThrow", "Landroid/graphics/Typeface;", "getIntOrThrow", "getIntegerOrThrow", "getResourceIdOrThrow", "getStringOrThrow", "", "getTextArrayOrThrow", "", "", "(Landroid/content/res/TypedArray;I)[Ljava/lang/CharSequence;", "getTextOrThrow", "use", "R", "block", "Lkotlin/Function1;", "(Landroid/content/res/TypedArray;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "core-ktx_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class TypedArrayKt {
    private static final void checkAttribute(TypedArray typedArray0, int v) {
        if(!typedArray0.hasValue(v)) {
            throw new IllegalArgumentException("Attribute not defined in set.");
        }
    }

    public static final boolean getBooleanOrThrow(TypedArray typedArray0, int v) {
        TypedArrayKt.checkAttribute(typedArray0, v);
        return typedArray0.getBoolean(v, false);
    }

    public static final int getColorOrThrow(TypedArray typedArray0, int v) {
        TypedArrayKt.checkAttribute(typedArray0, v);
        return typedArray0.getColor(v, 0);
    }

    public static final ColorStateList getColorStateListOrThrow(TypedArray typedArray0, int v) {
        TypedArrayKt.checkAttribute(typedArray0, v);
        ColorStateList colorStateList0 = typedArray0.getColorStateList(v);
        if(colorStateList0 == null) {
            throw new IllegalStateException("Attribute value was not a color or color state list.");
        }
        return colorStateList0;
    }

    public static final float getDimensionOrThrow(TypedArray typedArray0, int v) {
        TypedArrayKt.checkAttribute(typedArray0, v);
        return typedArray0.getDimension(v, 0.0f);
    }

    public static final int getDimensionPixelOffsetOrThrow(TypedArray typedArray0, int v) {
        TypedArrayKt.checkAttribute(typedArray0, v);
        return typedArray0.getDimensionPixelOffset(v, 0);
    }

    public static final int getDimensionPixelSizeOrThrow(TypedArray typedArray0, int v) {
        TypedArrayKt.checkAttribute(typedArray0, v);
        return typedArray0.getDimensionPixelSize(v, 0);
    }

    public static final Drawable getDrawableOrThrow(TypedArray typedArray0, int v) {
        TypedArrayKt.checkAttribute(typedArray0, v);
        Drawable drawable0 = typedArray0.getDrawable(v);
        Intrinsics.checkNotNull(drawable0);
        return drawable0;
    }

    public static final float getFloatOrThrow(TypedArray typedArray0, int v) {
        TypedArrayKt.checkAttribute(typedArray0, v);
        return typedArray0.getFloat(v, 0.0f);
    }

    public static final Typeface getFontOrThrow(TypedArray typedArray0, int v) {
        TypedArrayKt.checkAttribute(typedArray0, v);
        return TypedArrayApi26ImplKt.getFont(typedArray0, v);
    }

    public static final int getIntOrThrow(TypedArray typedArray0, int v) {
        TypedArrayKt.checkAttribute(typedArray0, v);
        return typedArray0.getInt(v, 0);
    }

    public static final int getIntegerOrThrow(TypedArray typedArray0, int v) {
        TypedArrayKt.checkAttribute(typedArray0, v);
        return typedArray0.getInteger(v, 0);
    }

    public static final int getResourceIdOrThrow(TypedArray typedArray0, int v) {
        TypedArrayKt.checkAttribute(typedArray0, v);
        return typedArray0.getResourceId(v, 0);
    }

    public static final String getStringOrThrow(TypedArray typedArray0, int v) {
        TypedArrayKt.checkAttribute(typedArray0, v);
        String s = typedArray0.getString(v);
        if(s == null) {
            throw new IllegalStateException("Attribute value could not be coerced to String.");
        }
        return s;
    }

    public static final CharSequence[] getTextArrayOrThrow(TypedArray typedArray0, int v) {
        TypedArrayKt.checkAttribute(typedArray0, v);
        return typedArray0.getTextArray(v);
    }

    public static final CharSequence getTextOrThrow(TypedArray typedArray0, int v) {
        TypedArrayKt.checkAttribute(typedArray0, v);
        CharSequence charSequence0 = typedArray0.getText(v);
        if(charSequence0 == null) {
            throw new IllegalStateException("Attribute value could not be coerced to CharSequence.");
        }
        return charSequence0;
    }

    public static final Object use(TypedArray typedArray0, Function1 function10) {
        Object object0 = function10.invoke(typedArray0);
        typedArray0.recycle();
        return object0;
    }
}

