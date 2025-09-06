package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources.Theme;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer.DrawableContainerState;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.InsetDrawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import androidx.annotation.ReplaceWith;
import java.io.IOException;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class DrawableCompat {
    static class Api21Impl {
        static void applyTheme(Drawable drawable0, Resources.Theme resources$Theme0) {
            drawable0.applyTheme(resources$Theme0);
        }

        static boolean canApplyTheme(Drawable drawable0) {
            return drawable0.canApplyTheme();
        }

        static ColorFilter getColorFilter(Drawable drawable0) {
            return drawable0.getColorFilter();
        }

        static void inflate(Drawable drawable0, Resources resources0, XmlPullParser xmlPullParser0, AttributeSet attributeSet0, Resources.Theme resources$Theme0) throws XmlPullParserException, IOException {
            drawable0.inflate(resources0, xmlPullParser0, attributeSet0, resources$Theme0);
        }

        static void setHotspot(Drawable drawable0, float f, float f1) {
            drawable0.setHotspot(f, f1);
        }

        static void setHotspotBounds(Drawable drawable0, int v, int v1, int v2, int v3) {
            drawable0.setHotspotBounds(v, v1, v2, v3);
        }

        static void setTint(Drawable drawable0, int v) {
            drawable0.setTint(v);
        }

        static void setTintList(Drawable drawable0, ColorStateList colorStateList0) {
            drawable0.setTintList(colorStateList0);
        }

        static void setTintMode(Drawable drawable0, PorterDuff.Mode porterDuff$Mode0) {
            drawable0.setTintMode(porterDuff$Mode0);
        }
    }

    static class Api23Impl {
        static int getLayoutDirection(Drawable drawable0) {
            return drawable0.getLayoutDirection();
        }

        static boolean setLayoutDirection(Drawable drawable0, int v) {
            return drawable0.setLayoutDirection(v);
        }
    }

    private static final String TAG = "DrawableCompat";
    private static Method sGetLayoutDirectionMethod;
    private static boolean sGetLayoutDirectionMethodFetched;
    private static Method sSetLayoutDirectionMethod;
    private static boolean sSetLayoutDirectionMethodFetched;

    public static void applyTheme(Drawable drawable0, Resources.Theme resources$Theme0) {
        Api21Impl.applyTheme(drawable0, resources$Theme0);
    }

    public static boolean canApplyTheme(Drawable drawable0) {
        return Api21Impl.canApplyTheme(drawable0);
    }

    public static void clearColorFilter(Drawable drawable0) {
        if(Build.VERSION.SDK_INT >= 23) {
            drawable0.clearColorFilter();
            return;
        }
        drawable0.clearColorFilter();
        if(drawable0 instanceof InsetDrawable) {
            DrawableCompat.clearColorFilter(((InsetDrawable)drawable0).getDrawable());
            return;
        }
        if(drawable0 instanceof WrappedDrawable) {
            DrawableCompat.clearColorFilter(((WrappedDrawable)drawable0).getWrappedDrawable());
            return;
        }
        if(drawable0 instanceof DrawableContainer) {
            DrawableContainer.DrawableContainerState drawableContainer$DrawableContainerState0 = (DrawableContainer.DrawableContainerState)((DrawableContainer)drawable0).getConstantState();
            if(drawableContainer$DrawableContainerState0 != null) {
                int v = drawableContainer$DrawableContainerState0.getChildCount();
                for(int v1 = 0; v1 < v; ++v1) {
                    Drawable drawable1 = drawableContainer$DrawableContainerState0.getChild(v1);
                    if(drawable1 != null) {
                        DrawableCompat.clearColorFilter(drawable1);
                    }
                }
            }
        }
    }

    @ReplaceWith(expression = "drawable.getAlpha()")
    @Deprecated
    public static int getAlpha(Drawable drawable0) {
        return drawable0.getAlpha();
    }

    public static ColorFilter getColorFilter(Drawable drawable0) {
        return Api21Impl.getColorFilter(drawable0);
    }

    public static int getLayoutDirection(Drawable drawable0) {
        if(Build.VERSION.SDK_INT >= 23) {
            return Api23Impl.getLayoutDirection(drawable0);
        }
        if(!DrawableCompat.sGetLayoutDirectionMethodFetched) {
            try {
                Method method0 = Drawable.class.getDeclaredMethod("getLayoutDirection", null);
                DrawableCompat.sGetLayoutDirectionMethod = method0;
                method0.setAccessible(true);
            }
            catch(NoSuchMethodException noSuchMethodException0) {
                Log.i("DrawableCompat", "Failed to retrieve getLayoutDirection() method", noSuchMethodException0);
            }
            DrawableCompat.sGetLayoutDirectionMethodFetched = true;
        }
        Method method1 = DrawableCompat.sGetLayoutDirectionMethod;
        if(method1 != null) {
            try {
                return (int)(((Integer)method1.invoke(drawable0, null)));
            }
            catch(Exception exception0) {
                Log.i("DrawableCompat", "Failed to invoke getLayoutDirection() via reflection", exception0);
                DrawableCompat.sGetLayoutDirectionMethod = null;
            }
        }
        return 0;
    }

    public static void inflate(Drawable drawable0, Resources resources0, XmlPullParser xmlPullParser0, AttributeSet attributeSet0, Resources.Theme resources$Theme0) throws XmlPullParserException, IOException {
        Api21Impl.inflate(drawable0, resources0, xmlPullParser0, attributeSet0, resources$Theme0);
    }

    @ReplaceWith(expression = "drawable.isAutoMirrored()")
    @Deprecated
    public static boolean isAutoMirrored(Drawable drawable0) {
        return drawable0.isAutoMirrored();
    }

    @ReplaceWith(expression = "drawable.jumpToCurrentState()")
    @Deprecated
    public static void jumpToCurrentState(Drawable drawable0) {
        drawable0.jumpToCurrentState();
    }

    @ReplaceWith(expression = "drawable.setAutoMirrored(mirrored)")
    @Deprecated
    public static void setAutoMirrored(Drawable drawable0, boolean z) {
        drawable0.setAutoMirrored(z);
    }

    public static void setHotspot(Drawable drawable0, float f, float f1) {
        Api21Impl.setHotspot(drawable0, f, f1);
    }

    public static void setHotspotBounds(Drawable drawable0, int v, int v1, int v2, int v3) {
        Api21Impl.setHotspotBounds(drawable0, v, v1, v2, v3);
    }

    public static boolean setLayoutDirection(Drawable drawable0, int v) {
        if(Build.VERSION.SDK_INT >= 23) {
            return Api23Impl.setLayoutDirection(drawable0, v);
        }
        if(!DrawableCompat.sSetLayoutDirectionMethodFetched) {
            try {
                Method method0 = Drawable.class.getDeclaredMethod("setLayoutDirection", Integer.TYPE);
                DrawableCompat.sSetLayoutDirectionMethod = method0;
                method0.setAccessible(true);
            }
            catch(NoSuchMethodException noSuchMethodException0) {
                Log.i("DrawableCompat", "Failed to retrieve setLayoutDirection(int) method", noSuchMethodException0);
            }
            DrawableCompat.sSetLayoutDirectionMethodFetched = true;
        }
        Method method1 = DrawableCompat.sSetLayoutDirectionMethod;
        if(method1 != null) {
            try {
                method1.invoke(drawable0, v);
                return true;
            }
            catch(Exception exception0) {
                Log.i("DrawableCompat", "Failed to invoke setLayoutDirection(int) via reflection", exception0);
                DrawableCompat.sSetLayoutDirectionMethod = null;
            }
        }
        return false;
    }

    public static void setTint(Drawable drawable0, int v) {
        Api21Impl.setTint(drawable0, v);
    }

    public static void setTintList(Drawable drawable0, ColorStateList colorStateList0) {
        Api21Impl.setTintList(drawable0, colorStateList0);
    }

    public static void setTintMode(Drawable drawable0, PorterDuff.Mode porterDuff$Mode0) {
        Api21Impl.setTintMode(drawable0, porterDuff$Mode0);
    }

    // 去混淆评级： 低(20)
    public static Drawable unwrap(Drawable drawable0) {
        return drawable0 instanceof WrappedDrawable ? ((WrappedDrawable)drawable0).getWrappedDrawable() : drawable0;
    }

    public static Drawable wrap(Drawable drawable0) {
        return Build.VERSION.SDK_INT < 23 && !(drawable0 instanceof TintAwareDrawable) ? new WrappedDrawableApi21(drawable0) : drawable0;
    }
}

