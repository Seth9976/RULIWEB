package androidx.core.text.util;

import android.icu.number.NumberFormatter;
import android.icu.number.UnlocalizedNumberFormatter;
import android.icu.text.DateFormat.HourCycle;
import android.icu.text.DateTimePatternGenerator;
import android.icu.util.Calendar;
import android.icu.util.MeasureUnit;
import android.os.Build.VERSION;
import android.text.format.DateFormat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.Locale.Category;
import java.util.Locale;

public final class LocalePreferences {
    static class Api24Impl {
        static String getCalendarType(Locale locale0) {
            return Calendar.getInstance(locale0).getType();
        }

        static Locale getDefaultLocale() {
            return Locale.getDefault(Locale.Category.FORMAT);
        }
    }

    static class Api33Impl {
        static String getHourCycle(Locale locale0) {
            return Api33Impl.getHourCycleType(DateTimePatternGenerator.getInstance(locale0).getDefaultHourCycle());
        }

        private static String getHourCycleType(DateFormat.HourCycle dateFormat$HourCycle0) {
            switch(androidx.core.text.util.LocalePreferences.1.$SwitchMap$android$icu$text$DateFormat$HourCycle[dateFormat$HourCycle0.ordinal()]) {
                case 1: {
                    return "h11";
                }
                case 2: {
                    return "h12";
                }
                case 3: {
                    return "h23";
                }
                case 4: {
                    return "h24";
                }
                default: {
                    return "";
                }
            }
        }

        static String getResolvedTemperatureUnit(Locale locale0) {
            String s = ((UnlocalizedNumberFormatter)((UnlocalizedNumberFormatter)NumberFormatter.with().usage("weather")).unit(MeasureUnit.CELSIUS)).locale(locale0).format(1L).getOutputUnit().getIdentifier();
            return s.startsWith("fahrenhe") ? "fahrenhe" : s;
        }
    }

    public static class CalendarType {
        @Retention(RetentionPolicy.SOURCE)
        public @interface CalendarTypes {
        }

        public static final String CHINESE = "chinese";
        public static final String DANGI = "dangi";
        public static final String DEFAULT = "";
        public static final String GREGORIAN = "gregorian";
        public static final String HEBREW = "hebrew";
        public static final String INDIAN = "indian";
        public static final String ISLAMIC = "islamic";
        public static final String ISLAMIC_CIVIL = "islamic-civil";
        public static final String ISLAMIC_RGSA = "islamic-rgsa";
        public static final String ISLAMIC_TBLA = "islamic-tbla";
        public static final String ISLAMIC_UMALQURA = "islamic-umalqura";
        public static final String PERSIAN = "persian";
        private static final String U_EXTENSION_TAG = "ca";

    }

    public static class FirstDayOfWeek {
        @Retention(RetentionPolicy.SOURCE)
        public @interface Days {
        }

        public static final String DEFAULT = "";
        public static final String FRIDAY = "fri";
        public static final String MONDAY = "mon";
        public static final String SATURDAY = "sat";
        public static final String SUNDAY = "sun";
        public static final String THURSDAY = "thu";
        public static final String TUESDAY = "tue";
        private static final String U_EXTENSION_TAG = "fw";
        public static final String WEDNESDAY = "wed";

    }

    public static class HourCycle {
        @Retention(RetentionPolicy.SOURCE)
        public @interface HourCycleTypes {
        }

        public static final String DEFAULT = "";
        public static final String H11 = "h11";
        public static final String H12 = "h12";
        public static final String H23 = "h23";
        public static final String H24 = "h24";
        private static final String U_EXTENSION_TAG = "hc";

    }

    public static class TemperatureUnit {
        @Retention(RetentionPolicy.SOURCE)
        public @interface TemperatureUnits {
        }

        public static final String CELSIUS = "celsius";
        public static final String DEFAULT = "";
        public static final String FAHRENHEIT = "fahrenhe";
        public static final String KELVIN = "kelvin";
        private static final String U_EXTENSION_TAG = "mu";

    }

    private static final String TAG = "LocalePreferences";
    private static final String[] WEATHER_FAHRENHEIT_COUNTRIES;

    static {
        LocalePreferences.WEATHER_FAHRENHEIT_COUNTRIES = new String[]{"BS", "BZ", "KY", "PR", "PW", "US"};
    }

    private static String getBaseFirstDayOfWeek(Locale locale0) {
        return LocalePreferences.getStringOfFirstDayOfWeek(java.util.Calendar.getInstance(locale0).getFirstDayOfWeek());
    }

    // 去混淆评级： 低(20)
    private static String getBaseHourCycle(Locale locale0) {
        return DateFormat.getBestDateTimePattern(locale0, "jm").contains("H") ? "h23" : "h12";
    }

    public static String getCalendarType() {
        return LocalePreferences.getCalendarType(true);
    }

    public static String getCalendarType(Locale locale0) {
        return LocalePreferences.getCalendarType(locale0, true);
    }

    public static String getCalendarType(Locale locale0, boolean z) {
        String s = LocalePreferences.getUnicodeLocaleType("ca", "", locale0, z);
        if(s != null) {
            return s;
        }
        if(Build.VERSION.SDK_INT >= 24) {
            return Api24Impl.getCalendarType(locale0);
        }
        return z ? "gregorian" : "";
    }

    public static String getCalendarType(boolean z) {
        return Build.VERSION.SDK_INT < 24 ? LocalePreferences.getCalendarType(LocalePreferences.getDefaultLocale(), z) : LocalePreferences.getCalendarType(Api24Impl.getDefaultLocale(), z);
    }

    private static Locale getDefaultLocale() {
        return Locale.getDefault();
    }

    public static String getFirstDayOfWeek() {
        return LocalePreferences.getFirstDayOfWeek(true);
    }

    public static String getFirstDayOfWeek(Locale locale0) {
        return LocalePreferences.getFirstDayOfWeek(locale0, true);
    }

    public static String getFirstDayOfWeek(Locale locale0, boolean z) {
        String s = LocalePreferences.getUnicodeLocaleType("fw", "", locale0, z);
        return s == null ? LocalePreferences.getBaseFirstDayOfWeek(locale0) : s;
    }

    public static String getFirstDayOfWeek(boolean z) {
        return Build.VERSION.SDK_INT < 24 ? LocalePreferences.getFirstDayOfWeek(LocalePreferences.getDefaultLocale(), z) : LocalePreferences.getFirstDayOfWeek(Api24Impl.getDefaultLocale(), z);
    }

    public static String getHourCycle() {
        return LocalePreferences.getHourCycle(true);
    }

    public static String getHourCycle(Locale locale0) {
        return LocalePreferences.getHourCycle(locale0, true);
    }

    public static String getHourCycle(Locale locale0, boolean z) {
        String s = LocalePreferences.getUnicodeLocaleType("hc", "", locale0, z);
        if(s != null) {
            return s;
        }
        return Build.VERSION.SDK_INT < 33 ? LocalePreferences.getBaseHourCycle(locale0) : Api33Impl.getHourCycle(locale0);
    }

    public static String getHourCycle(boolean z) {
        return Build.VERSION.SDK_INT < 24 ? LocalePreferences.getHourCycle(LocalePreferences.getDefaultLocale(), z) : LocalePreferences.getHourCycle(Api24Impl.getDefaultLocale(), z);
    }

    private static String getStringOfFirstDayOfWeek(int v) {
        return v < 1 || v > 7 ? "" : new String[]{"sun", "mon", "tue", "wed", "thu", "fri", "sat"}[v - 1];
    }

    private static String getTemperatureHardCoded(Locale locale0) {
        String s = locale0.getCountry();
        return Arrays.binarySearch(LocalePreferences.WEATHER_FAHRENHEIT_COUNTRIES, s) < 0 ? "celsius" : "fahrenhe";
    }

    public static String getTemperatureUnit() {
        return LocalePreferences.getTemperatureUnit(true);
    }

    public static String getTemperatureUnit(Locale locale0) {
        return LocalePreferences.getTemperatureUnit(locale0, true);
    }

    public static String getTemperatureUnit(Locale locale0, boolean z) {
        String s = LocalePreferences.getUnicodeLocaleType("mu", "", locale0, z);
        if(s != null) {
            return s;
        }
        return Build.VERSION.SDK_INT < 33 ? LocalePreferences.getTemperatureHardCoded(locale0) : Api33Impl.getResolvedTemperatureUnit(locale0);
    }

    public static String getTemperatureUnit(boolean z) {
        return Build.VERSION.SDK_INT < 24 ? LocalePreferences.getTemperatureUnit(LocalePreferences.getDefaultLocale(), z) : LocalePreferences.getTemperatureUnit(Api24Impl.getDefaultLocale(), z);
    }

    private static String getUnicodeLocaleType(String s, String s1, Locale locale0, boolean z) {
        String s2 = locale0.getUnicodeLocaleType(s);
        if(s2 != null) {
            return s2;
        }
        return z ? null : s1;
    }

    class androidx.core.text.util.LocalePreferences.1 {
        static final int[] $SwitchMap$android$icu$text$DateFormat$HourCycle;

        static {
            int[] arr_v = new int[DateFormat.HourCycle.values().length];
            androidx.core.text.util.LocalePreferences.1.$SwitchMap$android$icu$text$DateFormat$HourCycle = arr_v;
            try {
                arr_v[0] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                androidx.core.text.util.LocalePreferences.1.$SwitchMap$android$icu$text$DateFormat$HourCycle[1] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                androidx.core.text.util.LocalePreferences.1.$SwitchMap$android$icu$text$DateFormat$HourCycle[2] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                androidx.core.text.util.LocalePreferences.1.$SwitchMap$android$icu$text$DateFormat$HourCycle[3] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

