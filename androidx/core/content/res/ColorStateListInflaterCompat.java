package androidx.core.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources.Theme;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.util.StateSet;
import android.util.TypedValue;
import android.util.Xml;
import androidx.core.R.attr;
import androidx.core.R.styleable;
import androidx.core.math.MathUtils;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class ColorStateListInflaterCompat {
    private static final ThreadLocal sTempTypedValue;

    static {
        ColorStateListInflaterCompat.sTempTypedValue = new ThreadLocal();
    }

    public static ColorStateList createFromXml(Resources resources0, XmlPullParser xmlPullParser0, Resources.Theme resources$Theme0) throws XmlPullParserException, IOException {
        AttributeSet attributeSet0 = Xml.asAttributeSet(xmlPullParser0);
        do {
            int v = xmlPullParser0.next();
        }
        while(v != 1 && v != 2);
        if(v != 2) {
            throw new XmlPullParserException("No start tag found");
        }
        return ColorStateListInflaterCompat.createFromXmlInner(resources0, xmlPullParser0, attributeSet0, resources$Theme0);
    }

    public static ColorStateList createFromXmlInner(Resources resources0, XmlPullParser xmlPullParser0, AttributeSet attributeSet0, Resources.Theme resources$Theme0) throws XmlPullParserException, IOException {
        String s = xmlPullParser0.getName();
        if(!s.equals("selector")) {
            throw new XmlPullParserException(xmlPullParser0.getPositionDescription() + ": invalid color state list tag " + s);
        }
        return ColorStateListInflaterCompat.inflate(resources0, xmlPullParser0, attributeSet0, resources$Theme0);
    }

    private static TypedValue getTypedValue() {
        ThreadLocal threadLocal0 = ColorStateListInflaterCompat.sTempTypedValue;
        TypedValue typedValue0 = (TypedValue)threadLocal0.get();
        if(typedValue0 == null) {
            typedValue0 = new TypedValue();
            threadLocal0.set(typedValue0);
        }
        return typedValue0;
    }

    public static ColorStateList inflate(Resources resources0, int v, Resources.Theme resources$Theme0) {
        try {
            return ColorStateListInflaterCompat.createFromXml(resources0, resources0.getXml(v), resources$Theme0);
        }
        catch(Exception exception0) {
            Log.e("CSLCompat", "Failed to inflate ColorStateList.", exception0);
            return null;
        }
    }

    private static ColorStateList inflate(Resources resources0, XmlPullParser xmlPullParser0, AttributeSet attributeSet0, Resources.Theme resources$Theme0) throws XmlPullParserException, IOException {
        int v5;
        int v = xmlPullParser0.getDepth();
        int[][] arr2_v = new int[20][];
        int[] arr_v = new int[20];
        int v1 = 0;
        int v2;
        while((v2 = xmlPullParser0.next()) != 1) {
            int v3 = xmlPullParser0.getDepth();
            if(v3 < v + 1 && v2 == 3) {
                break;
            }
            if(v2 == 2 && v3 <= v + 1 && xmlPullParser0.getName().equals("item")) {
                TypedArray typedArray0 = ColorStateListInflaterCompat.obtainAttributes(resources0, resources$Theme0, attributeSet0, styleable.ColorStateListItem);
                int v4 = typedArray0.getResourceId(styleable.ColorStateListItem_android_color, -1);
                if(v4 == -1 || ColorStateListInflaterCompat.isColorInt(resources0, v4)) {
                    v5 = typedArray0.getColor(styleable.ColorStateListItem_android_color, 0xFFFF00FF);
                }
                else {
                    try {
                        v5 = ColorStateListInflaterCompat.createFromXml(resources0, resources0.getXml(v4), resources$Theme0).getDefaultColor();
                    }
                    catch(Exception unused_ex) {
                        v5 = typedArray0.getColor(styleable.ColorStateListItem_android_color, 0xFFFF00FF);
                    }
                }
                float f = 1.0f;
                if(typedArray0.hasValue(styleable.ColorStateListItem_android_alpha)) {
                    f = typedArray0.getFloat(styleable.ColorStateListItem_android_alpha, 1.0f);
                }
                else if(typedArray0.hasValue(styleable.ColorStateListItem_alpha)) {
                    f = typedArray0.getFloat(styleable.ColorStateListItem_alpha, 1.0f);
                }
                float f1 = Build.VERSION.SDK_INT < 0x1F || !typedArray0.hasValue(styleable.ColorStateListItem_android_lStar) ? typedArray0.getFloat(styleable.ColorStateListItem_lStar, -1.0f) : typedArray0.getFloat(styleable.ColorStateListItem_android_lStar, -1.0f);
                typedArray0.recycle();
                int v6 = attributeSet0.getAttributeCount();
                int[] arr_v1 = new int[v6];
                int v8 = 0;
                for(int v7 = 0; v7 < v6; ++v7) {
                    int v9 = attributeSet0.getAttributeNameResource(v7);
                    if(v9 != 0x10101A5 && v9 != 0x101031F && v9 != attr.alpha && v9 != attr.lStar) {
                        if(!attributeSet0.getAttributeBooleanValue(v7, false)) {
                            v9 = -v9;
                        }
                        arr_v1[v8] = v9;
                        ++v8;
                    }
                }
                int[] arr_v2 = StateSet.trimStateSet(arr_v1, v8);
                arr_v = GrowingArrayUtils.append(arr_v, v1, ColorStateListInflaterCompat.modulateColorAlpha(v5, f, f1));
                arr2_v = (int[][])GrowingArrayUtils.append(arr2_v, v1, arr_v2);
                ++v1;
            }
        }
        int[] arr_v3 = new int[v1];
        int[][] arr2_v1 = new int[v1][];
        System.arraycopy(arr_v, 0, arr_v3, 0, v1);
        System.arraycopy(arr2_v, 0, arr2_v1, 0, v1);
        return new ColorStateList(arr2_v1, arr_v3);
    }

    private static boolean isColorInt(Resources resources0, int v) {
        TypedValue typedValue0 = ColorStateListInflaterCompat.getTypedValue();
        resources0.getValue(v, typedValue0, true);
        return typedValue0.type >= 28 && typedValue0.type <= 0x1F;
    }

    private static int modulateColorAlpha(int v, float f, float f1) {
        boolean z = f1 >= 0.0f && f1 <= 100.0f;
        if(f == 1.0f && !z) {
            return v;
        }
        int v1 = MathUtils.clamp(((int)(((float)Color.alpha(v)) * f + 0.5f)), 0, 0xFF);
        if(z) {
            CamColor camColor0 = CamColor.fromColor(v);
            v = CamColor.toColor(camColor0.getHue(), camColor0.getChroma(), f1);
        }
        return v & 0xFFFFFF | v1 << 24;
    }

    private static TypedArray obtainAttributes(Resources resources0, Resources.Theme resources$Theme0, AttributeSet attributeSet0, int[] arr_v) {
        return resources$Theme0 == null ? resources0.obtainAttributes(attributeSet0, arr_v) : resources$Theme0.obtainStyledAttributes(attributeSet0, arr_v, 0, 0);
    }
}

