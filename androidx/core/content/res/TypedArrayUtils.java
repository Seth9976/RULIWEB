package androidx.core.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources.Theme;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import org.xmlpull.v1.XmlPullParser;

public class TypedArrayUtils {
    private static final String NAMESPACE = "http://schemas.android.com/apk/res/android";

    public static int getAttr(Context context0, int v, int v1) {
        TypedValue typedValue0 = new TypedValue();
        context0.getTheme().resolveAttribute(v, typedValue0, true);
        return typedValue0.resourceId == 0 ? v1 : v;
    }

    public static boolean getBoolean(TypedArray typedArray0, int v, int v1, boolean z) {
        return typedArray0.getBoolean(v, typedArray0.getBoolean(v1, z));
    }

    public static Drawable getDrawable(TypedArray typedArray0, int v, int v1) {
        Drawable drawable0 = typedArray0.getDrawable(v);
        return drawable0 == null ? typedArray0.getDrawable(v1) : drawable0;
    }

    public static int getInt(TypedArray typedArray0, int v, int v1, int v2) {
        return typedArray0.getInt(v, typedArray0.getInt(v1, v2));
    }

    public static boolean getNamedBoolean(TypedArray typedArray0, XmlPullParser xmlPullParser0, String s, int v, boolean z) {
        return TypedArrayUtils.hasAttribute(xmlPullParser0, s) ? typedArray0.getBoolean(v, z) : z;
    }

    public static int getNamedColor(TypedArray typedArray0, XmlPullParser xmlPullParser0, String s, int v, int v1) {
        return TypedArrayUtils.hasAttribute(xmlPullParser0, s) ? typedArray0.getColor(v, v1) : v1;
    }

    public static ColorStateList getNamedColorStateList(TypedArray typedArray0, XmlPullParser xmlPullParser0, Resources.Theme resources$Theme0, String s, int v) {
        if(TypedArrayUtils.hasAttribute(xmlPullParser0, s)) {
            TypedValue typedValue0 = new TypedValue();
            typedArray0.getValue(v, typedValue0);
            if(typedValue0.type == 2) {
                throw new UnsupportedOperationException("Failed to resolve attribute at index " + v + ": " + typedValue0);
            }
            return typedValue0.type < 28 || typedValue0.type > 0x1F ? ColorStateListInflaterCompat.inflate(typedArray0.getResources(), typedArray0.getResourceId(v, 0), resources$Theme0) : TypedArrayUtils.getNamedColorStateListFromInt(typedValue0);
        }
        return null;
    }

    private static ColorStateList getNamedColorStateListFromInt(TypedValue typedValue0) {
        return ColorStateList.valueOf(typedValue0.data);
    }

    public static ComplexColorCompat getNamedComplexColor(TypedArray typedArray0, XmlPullParser xmlPullParser0, Resources.Theme resources$Theme0, String s, int v, int v1) {
        if(TypedArrayUtils.hasAttribute(xmlPullParser0, s)) {
            TypedValue typedValue0 = new TypedValue();
            typedArray0.getValue(v, typedValue0);
            if(typedValue0.type >= 28 && typedValue0.type <= 0x1F) {
                return ComplexColorCompat.from(typedValue0.data);
            }
            ComplexColorCompat complexColorCompat0 = ComplexColorCompat.inflate(typedArray0.getResources(), typedArray0.getResourceId(v, 0), resources$Theme0);
            return complexColorCompat0 == null ? ComplexColorCompat.from(v1) : complexColorCompat0;
        }
        return ComplexColorCompat.from(v1);
    }

    public static float getNamedFloat(TypedArray typedArray0, XmlPullParser xmlPullParser0, String s, int v, float f) {
        return TypedArrayUtils.hasAttribute(xmlPullParser0, s) ? typedArray0.getFloat(v, f) : f;
    }

    public static int getNamedInt(TypedArray typedArray0, XmlPullParser xmlPullParser0, String s, int v, int v1) {
        return TypedArrayUtils.hasAttribute(xmlPullParser0, s) ? typedArray0.getInt(v, v1) : v1;
    }

    public static int getNamedResourceId(TypedArray typedArray0, XmlPullParser xmlPullParser0, String s, int v, int v1) {
        return TypedArrayUtils.hasAttribute(xmlPullParser0, s) ? typedArray0.getResourceId(v, v1) : v1;
    }

    public static String getNamedString(TypedArray typedArray0, XmlPullParser xmlPullParser0, String s, int v) {
        return TypedArrayUtils.hasAttribute(xmlPullParser0, s) ? typedArray0.getString(v) : null;
    }

    public static int getResourceId(TypedArray typedArray0, int v, int v1, int v2) {
        return typedArray0.getResourceId(v, typedArray0.getResourceId(v1, v2));
    }

    public static String getString(TypedArray typedArray0, int v, int v1) {
        String s = typedArray0.getString(v);
        return s == null ? typedArray0.getString(v1) : s;
    }

    public static CharSequence getText(TypedArray typedArray0, int v, int v1) {
        CharSequence charSequence0 = typedArray0.getText(v);
        return charSequence0 == null ? typedArray0.getText(v1) : charSequence0;
    }

    public static CharSequence[] getTextArray(TypedArray typedArray0, int v, int v1) {
        CharSequence[] arr_charSequence = typedArray0.getTextArray(v);
        return arr_charSequence == null ? typedArray0.getTextArray(v1) : arr_charSequence;
    }

    public static boolean hasAttribute(XmlPullParser xmlPullParser0, String s) {
        return xmlPullParser0.getAttributeValue("http://schemas.android.com/apk/res/android", s) != null;
    }

    public static TypedArray obtainAttributes(Resources resources0, Resources.Theme resources$Theme0, AttributeSet attributeSet0, int[] arr_v) {
        return resources$Theme0 == null ? resources0.obtainAttributes(attributeSet0, arr_v) : resources$Theme0.obtainStyledAttributes(attributeSet0, arr_v, 0, 0);
    }

    public static TypedValue peekNamedValue(TypedArray typedArray0, XmlPullParser xmlPullParser0, String s, int v) {
        return TypedArrayUtils.hasAttribute(xmlPullParser0, s) ? typedArray0.peekValue(v) : null;
    }
}

