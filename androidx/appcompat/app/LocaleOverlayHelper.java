package androidx.appcompat.app;

import android.os.LocaleList;
import androidx.core.os.LocaleListCompat;
import java.util.LinkedHashSet;
import java.util.Locale;

final class LocaleOverlayHelper {
    private static LocaleListCompat combineLocales(LocaleListCompat localeListCompat0, LocaleListCompat localeListCompat1) {
        LinkedHashSet linkedHashSet0 = new LinkedHashSet();
        for(int v = 0; v < localeListCompat0.size() + localeListCompat1.size(); ++v) {
            Locale locale0 = v >= localeListCompat0.size() ? localeListCompat1.get(v - localeListCompat0.size()) : localeListCompat0.get(v);
            if(locale0 != null) {
                linkedHashSet0.add(locale0);
            }
        }
        return LocaleListCompat.create(((Locale[])linkedHashSet0.toArray(new Locale[linkedHashSet0.size()])));
    }

    static LocaleListCompat combineLocalesIfOverlayExists(LocaleList localeList0, LocaleList localeList1) {
        return localeList0 == null || localeList0.isEmpty() ? LocaleListCompat.getEmptyLocaleList() : LocaleOverlayHelper.combineLocales(LocaleListCompat.wrap(localeList0), LocaleListCompat.wrap(localeList1));
    }

    static LocaleListCompat combineLocalesIfOverlayExists(LocaleListCompat localeListCompat0, LocaleListCompat localeListCompat1) {
        return localeListCompat0 == null || localeListCompat0.isEmpty() ? LocaleListCompat.getEmptyLocaleList() : LocaleOverlayHelper.combineLocales(localeListCompat0, localeListCompat1);
    }
}

