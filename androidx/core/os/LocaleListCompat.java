package androidx.core.os;

import android.os.Build.VERSION;
import android.os.LocaleList;
import androidx.core.text.ICUCompat;
import java.util.Locale;

public final class LocaleListCompat {
    static class Api21Impl {
        private static final Locale[] PSEUDO_LOCALE;

        static {
            Api21Impl.PSEUDO_LOCALE = new Locale[]{new Locale("en", "XA"), new Locale("ar", "XB")};
        }

        static Locale forLanguageTag(String s) {
            return Locale.forLanguageTag(s);
        }

        private static boolean isPseudoLocale(Locale locale0) {
            Locale[] arr_locale = Api21Impl.PSEUDO_LOCALE;
            for(int v = 0; v < arr_locale.length; ++v) {
                if(arr_locale[v].equals(locale0)) {
                    return true;
                }
            }
            return false;
        }

        static boolean matchesLanguageAndScript(Locale locale0, Locale locale1) {
            if(locale0.equals(locale1)) {
                return true;
            }
            if(!locale0.getLanguage().equals(locale1.getLanguage())) {
                return false;
            }
            if(!Api21Impl.isPseudoLocale(locale0) && !Api21Impl.isPseudoLocale(locale1)) {
                String s = ICUCompat.maximizeAndGetScript(locale0);
                if(s.isEmpty()) {
                    String s1 = locale0.getCountry();
                    return s1.isEmpty() || s1.equals(locale1.getCountry());
                }
                return s.equals(ICUCompat.maximizeAndGetScript(locale1));
            }
            return false;
        }
    }

    static class Api24Impl {
        static LocaleList createLocaleList(Locale[] arr_locale) {
            return new LocaleList(arr_locale);
        }

        static LocaleList getAdjustedDefault() {
            return LocaleList.getAdjustedDefault();
        }

        static LocaleList getDefault() {
            return LocaleList.getDefault();
        }
    }

    private final LocaleListInterface mImpl;
    private static final LocaleListCompat sEmptyLocaleList;

    static {
        LocaleListCompat.sEmptyLocaleList = LocaleListCompat.create(new Locale[0]);
    }

    private LocaleListCompat(LocaleListInterface localeListInterface0) {
        this.mImpl = localeListInterface0;
    }

    public static LocaleListCompat create(Locale[] arr_locale) {
        return Build.VERSION.SDK_INT < 24 ? new LocaleListCompat(new LocaleListCompatWrapper(arr_locale)) : LocaleListCompat.wrap(Api24Impl.createLocaleList(arr_locale));
    }

    // 去混淆评级： 低(20)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof LocaleListCompat && this.mImpl.equals(((LocaleListCompat)object0).mImpl);
    }

    static Locale forLanguageTagCompat(String s) {
        if(s.contains("-")) {
            String[] arr_s = s.split("-", -1);
            if(arr_s.length > 2) {
                return new Locale(arr_s[0], arr_s[1], arr_s[2]);
            }
            if(arr_s.length > 1) {
                return new Locale(arr_s[0], arr_s[1]);
            }
            if(arr_s.length != 1) {
                throw new IllegalArgumentException("Can not parse language tag: [" + s + "]");
            }
            return new Locale(arr_s[0]);
        }
        if(s.contains("_")) {
            String[] arr_s1 = s.split("_", -1);
            if(arr_s1.length > 2) {
                return new Locale(arr_s1[0], arr_s1[1], arr_s1[2]);
            }
            if(arr_s1.length > 1) {
                return new Locale(arr_s1[0], arr_s1[1]);
            }
            if(arr_s1.length != 1) {
                throw new IllegalArgumentException("Can not parse language tag: [" + s + "]");
            }
            return new Locale(arr_s1[0]);
        }
        return new Locale(s);
    }

    public static LocaleListCompat forLanguageTags(String s) {
        if(s != null && !s.isEmpty()) {
            String[] arr_s = s.split(",", -1);
            Locale[] arr_locale = new Locale[arr_s.length];
            for(int v = 0; v < arr_s.length; ++v) {
                arr_locale[v] = Api21Impl.forLanguageTag(arr_s[v]);
            }
            return LocaleListCompat.create(arr_locale);
        }
        return LocaleListCompat.getEmptyLocaleList();
    }

    public Locale get(int v) {
        return this.mImpl.get(v);
    }

    public static LocaleListCompat getAdjustedDefault() {
        return Build.VERSION.SDK_INT < 24 ? LocaleListCompat.create(new Locale[]{Locale.getDefault()}) : LocaleListCompat.wrap(Api24Impl.getAdjustedDefault());
    }

    public static LocaleListCompat getDefault() {
        return Build.VERSION.SDK_INT < 24 ? LocaleListCompat.create(new Locale[]{Locale.getDefault()}) : LocaleListCompat.wrap(Api24Impl.getDefault());
    }

    public static LocaleListCompat getEmptyLocaleList() {
        return LocaleListCompat.sEmptyLocaleList;
    }

    public Locale getFirstMatch(String[] arr_s) {
        return this.mImpl.getFirstMatch(arr_s);
    }

    @Override
    public int hashCode() {
        return this.mImpl.hashCode();
    }

    public int indexOf(Locale locale0) {
        return this.mImpl.indexOf(locale0);
    }

    public boolean isEmpty() {
        return this.mImpl.isEmpty();
    }

    public static boolean matchesLanguageAndScript(Locale locale0, Locale locale1) {
        return Build.VERSION.SDK_INT < 33 ? Api21Impl.matchesLanguageAndScript(locale0, locale1) : LocaleList.matchesLanguageAndScript(locale0, locale1);
    }

    public int size() {
        return this.mImpl.size();
    }

    public String toLanguageTags() {
        return this.mImpl.toLanguageTags();
    }

    @Override
    public String toString() {
        return this.mImpl.toString();
    }

    public Object unwrap() {
        return this.mImpl.getLocaleList();
    }

    public static LocaleListCompat wrap(LocaleList localeList0) {
        return new LocaleListCompat(new LocaleListPlatformWrapper(localeList0));
    }

    @Deprecated
    public static LocaleListCompat wrap(Object object0) {
        return LocaleListCompat.wrap(((LocaleList)object0));
    }
}

