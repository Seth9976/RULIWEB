package androidx.core.text;

import android.icu.util.ULocale;
import android.os.Build.VERSION;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

public final class ICUCompat {
    static class Api21Impl {
        static String getScript(Locale locale0) {
            return locale0.getScript();
        }
    }

    static class Api24Impl {
        static ULocale addLikelySubtags(Object object0) {
            return ULocale.addLikelySubtags(((ULocale)object0));
        }

        static ULocale forLocale(Locale locale0) {
            return ULocale.forLocale(locale0);
        }

        static String getScript(Object object0) {
            return ((ULocale)object0).getScript();
        }
    }

    private static final String TAG = "ICUCompat";
    private static Method sAddLikelySubtagsMethod;
    private static Method sGetScriptMethod;

    static {
        if(Build.VERSION.SDK_INT < 24) {
            try {
                ICUCompat.sAddLikelySubtagsMethod = Class.forName("libcore.icu.ICU").getMethod("addLikelySubtags", Locale.class);
            }
            catch(Exception exception0) {
                throw new IllegalStateException(exception0);
            }
        }
    }

    private static String addLikelySubtagsBelowApi21(Locale locale0) {
        String s = locale0.toString();
        Method method0 = ICUCompat.sAddLikelySubtagsMethod;
        if(method0 != null) {
            try {
                return (String)method0.invoke(null, s);
            }
            catch(IllegalAccessException illegalAccessException0) {
                Log.w("ICUCompat", illegalAccessException0);
                return s;
            }
            catch(InvocationTargetException invocationTargetException0) {
                Log.w("ICUCompat", invocationTargetException0);
                return s;
            }
        }
        return s;
    }

    private static String getScriptBelowApi21(String s) {
        Method method0 = ICUCompat.sGetScriptMethod;
        if(method0 != null) {
            try {
                return (String)method0.invoke(null, s);
            }
            catch(IllegalAccessException illegalAccessException0) {
                Log.w("ICUCompat", illegalAccessException0);
                return null;
            }
            catch(InvocationTargetException invocationTargetException0) {
                Log.w("ICUCompat", invocationTargetException0);
                return null;
            }
        }
        return null;
    }

    public static String maximizeAndGetScript(Locale locale0) {
        if(Build.VERSION.SDK_INT >= 24) {
            return Api24Impl.getScript(Api24Impl.addLikelySubtags(Api24Impl.forLocale(locale0)));
        }
        try {
            return Api21Impl.getScript(((Locale)ICUCompat.sAddLikelySubtagsMethod.invoke(null, locale0)));
        }
        catch(InvocationTargetException invocationTargetException0) {
            Log.w("ICUCompat", invocationTargetException0);
            return Api21Impl.getScript(locale0);
        }
        catch(IllegalAccessException illegalAccessException0) {
            Log.w("ICUCompat", illegalAccessException0);
            return Api21Impl.getScript(locale0);
        }
    }
}

