package androidx.core.app;

import android.app.LocaleManager;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.LocaleList;
import androidx.core.os.LocaleListCompat;
import java.util.Locale;

public final class LocaleManagerCompat {
    static class Api21Impl {
        static String toLanguageTag(Locale locale0) {
            return locale0.toLanguageTag();
        }
    }

    static class Api24Impl {
        static LocaleListCompat getLocales(Configuration configuration0) {
            return LocaleListCompat.forLanguageTags(configuration0.getLocales().toLanguageTags());
        }
    }

    static class Api33Impl {
        static LocaleList localeManagerGetApplicationLocales(Object object0) {
            return ((LocaleManager)object0).getApplicationLocales();
        }

        static LocaleList localeManagerGetSystemLocales(Object object0) {
            return ((LocaleManager)object0).getSystemLocales();
        }
    }

    public static LocaleListCompat getApplicationLocales(Context context0) {
        if(Build.VERSION.SDK_INT >= 33) {
            Object object0 = LocaleManagerCompat.getLocaleManagerForApplication(context0);
            return object0 == null ? LocaleListCompat.getEmptyLocaleList() : LocaleListCompat.wrap(Api33Impl.localeManagerGetApplicationLocales(object0));
        }
        return LocaleListCompat.forLanguageTags(AppLocalesStorageHelper.readLocales(context0));
    }

    static LocaleListCompat getConfigurationLocales(Configuration configuration0) {
        return Build.VERSION.SDK_INT < 24 ? LocaleListCompat.forLanguageTags(Api21Impl.toLanguageTag(configuration0.locale)) : Api24Impl.getLocales(configuration0);
    }

    private static Object getLocaleManagerForApplication(Context context0) {
        return context0.getSystemService("locale");
    }

    public static LocaleListCompat getSystemLocales(Context context0) {
        LocaleListCompat localeListCompat0 = LocaleListCompat.getEmptyLocaleList();
        if(Build.VERSION.SDK_INT >= 33) {
            Object object0 = LocaleManagerCompat.getLocaleManagerForApplication(context0);
            return object0 == null ? localeListCompat0 : LocaleListCompat.wrap(Api33Impl.localeManagerGetSystemLocales(object0));
        }
        return LocaleManagerCompat.getConfigurationLocales(Resources.getSystem().getConfiguration());
    }
}

