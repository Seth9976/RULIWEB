package androidx.core.os;

import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.LocaleList;
import java.util.Locale;

public final class ConfigurationCompat {
    static class Api24Impl {
        static LocaleList getLocales(Configuration configuration0) {
            return configuration0.getLocales();
        }

        static void setLocales(Configuration configuration0, LocaleListCompat localeListCompat0) {
            configuration0.setLocales(((LocaleList)localeListCompat0.unwrap()));
        }
    }

    public static LocaleListCompat getLocales(Configuration configuration0) {
        return Build.VERSION.SDK_INT < 24 ? LocaleListCompat.create(new Locale[]{configuration0.locale}) : LocaleListCompat.wrap(Api24Impl.getLocales(configuration0));
    }

    public static void setLocales(Configuration configuration0, LocaleListCompat localeListCompat0) {
        if(Build.VERSION.SDK_INT >= 24) {
            Api24Impl.setLocales(configuration0, localeListCompat0);
            return;
        }
        if(!localeListCompat0.isEmpty()) {
            configuration0.setLocale(localeListCompat0.get(0));
        }
    }
}

