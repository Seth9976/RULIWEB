package androidx.core.os;

import android.os.LocaleList;
import java.util.Locale;

final class LocaleListPlatformWrapper implements LocaleListInterface {
    private final LocaleList mLocaleList;

    LocaleListPlatformWrapper(Object object0) {
        this.mLocaleList = (LocaleList)object0;
    }

    @Override
    public boolean equals(Object object0) {
        Object object1 = ((LocaleListInterface)object0).getLocaleList();
        return this.mLocaleList.equals(object1);
    }

    @Override  // androidx.core.os.LocaleListInterface
    public Locale get(int v) {
        return this.mLocaleList.get(v);
    }

    @Override  // androidx.core.os.LocaleListInterface
    public Locale getFirstMatch(String[] arr_s) {
        return this.mLocaleList.getFirstMatch(arr_s);
    }

    @Override  // androidx.core.os.LocaleListInterface
    public Object getLocaleList() {
        return this.mLocaleList;
    }

    @Override
    public int hashCode() {
        return this.mLocaleList.hashCode();
    }

    @Override  // androidx.core.os.LocaleListInterface
    public int indexOf(Locale locale0) {
        return this.mLocaleList.indexOf(locale0);
    }

    @Override  // androidx.core.os.LocaleListInterface
    public boolean isEmpty() {
        return this.mLocaleList.isEmpty();
    }

    @Override  // androidx.core.os.LocaleListInterface
    public int size() {
        return this.mLocaleList.size();
    }

    @Override  // androidx.core.os.LocaleListInterface
    public String toLanguageTags() {
        return this.mLocaleList.toLanguageTags();
    }

    @Override
    public String toString() {
        return this.mLocaleList.toString();
    }
}

