package androidx.core.os;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;

final class LocaleListCompatWrapper implements LocaleListInterface {
    static class Api21Impl {
        static String getScript(Locale locale0) {
            return locale0.getScript();
        }
    }

    private static final Locale EN_LATN;
    private static final Locale LOCALE_AR_XB;
    private static final Locale LOCALE_EN_XA;
    private final Locale[] mList;
    private final String mStringRepresentation;
    private static final Locale[] sEmptyList;

    static {
        LocaleListCompatWrapper.sEmptyList = new Locale[0];
        LocaleListCompatWrapper.LOCALE_EN_XA = new Locale("en", "XA");
        LocaleListCompatWrapper.LOCALE_AR_XB = new Locale("ar", "XB");
        LocaleListCompatWrapper.EN_LATN = LocaleListCompat.forLanguageTagCompat("en-Latn");
    }

    LocaleListCompatWrapper(Locale[] arr_locale) {
        if(arr_locale.length == 0) {
            this.mList = LocaleListCompatWrapper.sEmptyList;
            this.mStringRepresentation = "";
            return;
        }
        ArrayList arrayList0 = new ArrayList();
        HashSet hashSet0 = new HashSet();
        StringBuilder stringBuilder0 = new StringBuilder();
        for(int v = 0; v < arr_locale.length; ++v) {
            Locale locale0 = arr_locale[v];
            if(locale0 == null) {
                throw new NullPointerException("list[" + v + "] is null");
            }
            if(!hashSet0.contains(locale0)) {
                Locale locale1 = (Locale)locale0.clone();
                arrayList0.add(locale1);
                LocaleListCompatWrapper.toLanguageTag(stringBuilder0, locale1);
                if(v < arr_locale.length - 1) {
                    stringBuilder0.append(',');
                }
                hashSet0.add(locale1);
            }
        }
        this.mList = (Locale[])arrayList0.toArray(new Locale[0]);
        this.mStringRepresentation = stringBuilder0.toString();
    }

    private Locale computeFirstMatch(Collection collection0, boolean z) {
        int v = this.computeFirstMatchIndex(collection0, z);
        return v == -1 ? null : this.mList[v];
    }

    private int computeFirstMatchIndex(Collection collection0, boolean z) {
        int v;
        Locale[] arr_locale = this.mList;
        if(arr_locale.length == 1) {
            return 0;
        }
        if(arr_locale.length == 0) {
            return -1;
        }
        if(z) {
            v = this.findFirstMatchIndex(LocaleListCompatWrapper.EN_LATN);
            if(v == 0) {
                return 0;
            }
            if(v >= 0x7FFFFFFF) {
                v = 0x7FFFFFFF;
            }
        }
        else {
            v = 0x7FFFFFFF;
        }
        for(Object object0: collection0) {
            int v1 = this.findFirstMatchIndex(LocaleListCompat.forLanguageTagCompat(((String)object0)));
            if(v1 == 0) {
                return 0;
            }
            if(v1 < v) {
                v = v1;
            }
        }
        return v == 0x7FFFFFFF ? 0 : v;
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(!(object0 instanceof LocaleListCompatWrapper)) {
            return false;
        }
        Locale[] arr_locale = ((LocaleListCompatWrapper)object0).mList;
        if(this.mList.length != arr_locale.length) {
            return false;
        }
        for(int v = 0; true; ++v) {
            Locale[] arr_locale1 = this.mList;
            if(v >= arr_locale1.length) {
                break;
            }
            if(!arr_locale1[v].equals(arr_locale[v])) {
                return false;
            }
        }
        return true;
    }

    private int findFirstMatchIndex(Locale locale0) {
        for(int v = 0; true; ++v) {
            Locale[] arr_locale = this.mList;
            if(v >= arr_locale.length) {
                break;
            }
            if(LocaleListCompatWrapper.matchScore(locale0, arr_locale[v]) > 0) {
                return v;
            }
        }
        return 0x7FFFFFFF;
    }

    @Override  // androidx.core.os.LocaleListInterface
    public Locale get(int v) {
        if(v >= 0) {
            return v >= this.mList.length ? null : this.mList[v];
        }
        return null;
    }

    @Override  // androidx.core.os.LocaleListInterface
    public Locale getFirstMatch(String[] arr_s) {
        return this.computeFirstMatch(Arrays.asList(arr_s), false);
    }

    private static String getLikelyScript(Locale locale0) {
        String s = Api21Impl.getScript(locale0);
        return s.isEmpty() ? "" : s;
    }

    @Override  // androidx.core.os.LocaleListInterface
    public Object getLocaleList() {
        return null;
    }

    @Override
    public int hashCode() {
        Locale[] arr_locale = this.mList;
        int v = 1;
        for(int v1 = 0; v1 < arr_locale.length; ++v1) {
            v = v * 0x1F + arr_locale[v1].hashCode();
        }
        return v;
    }

    @Override  // androidx.core.os.LocaleListInterface
    public int indexOf(Locale locale0) {
        for(int v = 0; true; ++v) {
            Locale[] arr_locale = this.mList;
            if(v >= arr_locale.length) {
                break;
            }
            if(arr_locale[v].equals(locale0)) {
                return v;
            }
        }
        return -1;
    }

    @Override  // androidx.core.os.LocaleListInterface
    public boolean isEmpty() {
        return this.mList.length == 0;
    }

    // 去混淆评级： 低(20)
    private static boolean isPseudoLocale(Locale locale0) {
        return LocaleListCompatWrapper.LOCALE_EN_XA.equals(locale0) || LocaleListCompatWrapper.LOCALE_AR_XB.equals(locale0);
    }

    private static int matchScore(Locale locale0, Locale locale1) {
        if(locale0.equals(locale1)) {
            return 1;
        }
        if(!locale0.getLanguage().equals(locale1.getLanguage())) {
            return 0;
        }
        if(!LocaleListCompatWrapper.isPseudoLocale(locale0) && !LocaleListCompatWrapper.isPseudoLocale(locale1)) {
            String s = LocaleListCompatWrapper.getLikelyScript(locale0);
            if(s.isEmpty()) {
                String s1 = locale0.getCountry();
                return s1.isEmpty() || s1.equals(locale1.getCountry()) ? 1 : 0;
            }
            return s.equals(LocaleListCompatWrapper.getLikelyScript(locale1));
        }
        return 0;
    }

    @Override  // androidx.core.os.LocaleListInterface
    public int size() {
        return this.mList.length;
    }

    static void toLanguageTag(StringBuilder stringBuilder0, Locale locale0) {
        stringBuilder0.append(locale0.getLanguage());
        String s = locale0.getCountry();
        if(s != null && !s.isEmpty()) {
            stringBuilder0.append('-');
            stringBuilder0.append(locale0.getCountry());
        }
    }

    @Override  // androidx.core.os.LocaleListInterface
    public String toLanguageTags() {
        return this.mStringRepresentation;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder0 = new StringBuilder("[");
        for(int v = 0; true; ++v) {
            Locale[] arr_locale = this.mList;
            if(v >= arr_locale.length) {
                break;
            }
            stringBuilder0.append(arr_locale[v]);
            if(v < this.mList.length - 1) {
                stringBuilder0.append(',');
            }
        }
        stringBuilder0.append("]");
        return stringBuilder0.toString();
    }
}

