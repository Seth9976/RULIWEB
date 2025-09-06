package com.google.android.material.drawable;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources.NotFoundException;
import android.content.res.XmlResourceParser;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.ColorStateListDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Xml;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.work.impl.utils.NetworkApi23..ExternalSyntheticApiModelOutline0;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;
import org.xmlpull.v1.XmlPullParserException;

public final class DrawableUtils {
    static class OutlineCompatL {
        static void setConvexPath(Outline outline0, Path path0) {
            outline0.setConvexPath(path0);
        }
    }

    static class OutlineCompatR {
        static void setPath(Outline outline0, Path path0) {
            LinkFollowing..ExternalSyntheticApiModelOutline0.m(outline0, path0);
        }
    }

    public static final int INTRINSIC_SIZE = -1;
    private static final int UNSPECIFIED_HEIGHT = -1;
    private static final int UNSPECIFIED_WIDTH = -1;

    public static Drawable compositeTwoLayeredDrawable(Drawable drawable0, Drawable drawable1) {
        return DrawableUtils.compositeTwoLayeredDrawable(drawable0, drawable1, -1, -1);
    }

    public static Drawable compositeTwoLayeredDrawable(Drawable drawable0, Drawable drawable1, int v, int v1) {
        Drawable drawable2 = drawable1;
        int v2 = v;
        int v3 = v1;
        if(drawable0 == null) {
            return drawable2;
        }
        if(drawable2 == null) {
            return drawable0;
        }
        boolean z = v2 != -1 && v3 != -1;
        if(v2 == -1) {
            v2 = DrawableUtils.getTopLayerIntrinsicWidth(drawable0, drawable1);
        }
        if(v3 == -1) {
            v3 = DrawableUtils.getTopLayerIntrinsicHeight(drawable0, drawable1);
        }
        if(v2 > drawable0.getIntrinsicWidth() || v3 > drawable0.getIntrinsicHeight()) {
            float f = ((float)v2) / ((float)v3);
            if(f >= ((float)drawable0.getIntrinsicWidth()) / ((float)drawable0.getIntrinsicHeight())) {
                int v4 = drawable0.getIntrinsicWidth();
                v3 = (int)(((float)v4) / f);
                v2 = v4;
            }
            else {
                v3 = drawable0.getIntrinsicHeight();
                v2 = (int)(f * ((float)v3));
            }
        }
        if(Build.VERSION.SDK_INT >= 23) {
            Drawable drawable3 = new LayerDrawable(new Drawable[]{drawable0, drawable2});
            ((LayerDrawable)drawable3).setLayerSize(1, v2, v3);
            ((LayerDrawable)drawable3).setLayerGravity(1, 17);
            return drawable3;
        }
        if(z) {
            drawable2 = new ScaledDrawableWrapper(drawable2, v2, v3);
        }
        Drawable drawable4 = new LayerDrawable(new Drawable[]{drawable0, drawable2});
        int v5 = Math.max((drawable0.getIntrinsicWidth() - v2) / 2, 0);
        int v6 = Math.max((drawable0.getIntrinsicHeight() - v3) / 2, 0);
        ((LayerDrawable)drawable4).setLayerInset(1, v5, v6, v5, v6);
        return drawable4;
    }

    public static Drawable createTintableDrawableIfNeeded(Drawable drawable0, ColorStateList colorStateList0, PorterDuff.Mode porterDuff$Mode0) {
        return DrawableUtils.createTintableMutatedDrawableIfNeeded(drawable0, colorStateList0, porterDuff$Mode0, false);
    }

    public static Drawable createTintableMutatedDrawableIfNeeded(Drawable drawable0, ColorStateList colorStateList0, PorterDuff.Mode porterDuff$Mode0) {
        return Build.VERSION.SDK_INT >= 23 ? DrawableUtils.createTintableMutatedDrawableIfNeeded(drawable0, colorStateList0, porterDuff$Mode0, false) : DrawableUtils.createTintableMutatedDrawableIfNeeded(drawable0, colorStateList0, porterDuff$Mode0, true);
    }

    private static Drawable createTintableMutatedDrawableIfNeeded(Drawable drawable0, ColorStateList colorStateList0, PorterDuff.Mode porterDuff$Mode0, boolean z) {
        if(drawable0 == null) {
            return null;
        }
        if(colorStateList0 != null) {
            Drawable drawable1 = DrawableCompat.wrap(drawable0).mutate();
            if(porterDuff$Mode0 != null) {
                DrawableCompat.setTintMode(drawable1, porterDuff$Mode0);
            }
            return drawable1;
        }
        if(z) {
            drawable0.mutate();
        }
        return drawable0;
    }

    public static int[] getCheckedState(int[] arr_v) {
        for(int v = 0; v < arr_v.length; ++v) {
            int v1 = arr_v[v];
            if(v1 == 0x10100A0) {
                return arr_v;
            }
            if(v1 == 0) {
                int[] arr_v1 = (int[])arr_v.clone();
                arr_v1[v] = 0x10100A0;
                return arr_v1;
            }
        }
        int[] arr_v2 = Arrays.copyOf(arr_v, arr_v.length + 1);
        arr_v2[arr_v.length] = 0x10100A0;
        return arr_v2;
    }

    public static ColorStateList getColorStateListOrNull(Drawable drawable0) {
        if(drawable0 instanceof ColorDrawable) {
            return ColorStateList.valueOf(((ColorDrawable)drawable0).getColor());
        }
        return Build.VERSION.SDK_INT < 29 || !NetworkApi23..ExternalSyntheticApiModelOutline0.m$1(drawable0) ? null : LinkFollowing..ExternalSyntheticApiModelOutline0.m(((ColorStateListDrawable)drawable0));
    }

    private static int getTopLayerIntrinsicHeight(Drawable drawable0, Drawable drawable1) {
        int v = drawable1.getIntrinsicHeight();
        return v == -1 ? drawable0.getIntrinsicHeight() : v;
    }

    private static int getTopLayerIntrinsicWidth(Drawable drawable0, Drawable drawable1) {
        int v = drawable1.getIntrinsicWidth();
        return v == -1 ? drawable0.getIntrinsicWidth() : v;
    }

    public static int[] getUncheckedState(int[] arr_v) {
        int[] arr_v1 = new int[arr_v.length];
        int v1 = 0;
        for(int v = 0; v < arr_v.length; ++v) {
            int v2 = arr_v[v];
            if(v2 != 0x10100A0) {
                arr_v1[v1] = v2;
                ++v1;
            }
        }
        return arr_v1;
    }

    public static AttributeSet parseDrawableXml(Context context0, int v, CharSequence charSequence0) {
        try {
            XmlResourceParser xmlResourceParser0 = context0.getResources().getXml(v);
            do {
                int v1 = xmlResourceParser0.next();
            }
            while(v1 != 1 && v1 != 2);
            if(v1 != 2) {
                throw new XmlPullParserException("No start tag found");
            }
            if(!TextUtils.equals(xmlResourceParser0.getName(), charSequence0)) {
                throw new XmlPullParserException("Must have a <" + charSequence0 + "> start tag");
            }
            return Xml.asAttributeSet(xmlResourceParser0);
        }
        catch(XmlPullParserException | IOException xmlPullParserException0) {
            Resources.NotFoundException resources$NotFoundException0 = new Resources.NotFoundException("Can\'t load badge resource ID #0x" + Integer.toHexString(v));
            resources$NotFoundException0.initCause(xmlPullParserException0);
            throw resources$NotFoundException0;
        }
    }

    public static void setOutlineToPath(Outline outline0, Path path0) {
        if(Build.VERSION.SDK_INT >= 30) {
            OutlineCompatR.setPath(outline0, path0);
            return;
        }
        if(Build.VERSION.SDK_INT >= 29) {
            try {
                OutlineCompatL.setConvexPath(outline0, path0);
            }
            catch(IllegalArgumentException unused_ex) {
            }
        }
        else if(path0.isConvex()) {
            OutlineCompatL.setConvexPath(outline0, path0);
        }
    }

    public static void setRippleDrawableRadius(RippleDrawable rippleDrawable0, int v) {
        if(Build.VERSION.SDK_INT >= 23) {
            LinkFollowing..ExternalSyntheticApiModelOutline0.m(rippleDrawable0, v);
            return;
        }
        try {
            RippleDrawable.class.getDeclaredMethod("setMaxRadius", Integer.TYPE).invoke(rippleDrawable0, v);
        }
        catch(NoSuchMethodException | InvocationTargetException | IllegalAccessException exception0) {
            throw new IllegalStateException("Couldn\'t set RippleDrawable radius", exception0);
        }
    }

    public static void setTint(Drawable drawable0, int v) {
        if(Build.VERSION.SDK_INT == 21) {
            if(v != 0) {
                drawable0.setColorFilter(v, PorterDuff.Mode.SRC_IN);
                return;
            }
            drawable0.setColorFilter(null);
            return;
        }
        if(v != 0) {
            DrawableCompat.setTint(drawable0, v);
            return;
        }
        DrawableCompat.setTintList(drawable0, null);
    }

    public static PorterDuffColorFilter updateTintFilter(Drawable drawable0, ColorStateList colorStateList0, PorterDuff.Mode porterDuff$Mode0) {
        return colorStateList0 == null || porterDuff$Mode0 == null ? null : new PorterDuffColorFilter(colorStateList0.getColorForState(drawable0.getState(), 0), porterDuff$Mode0);
    }
}

