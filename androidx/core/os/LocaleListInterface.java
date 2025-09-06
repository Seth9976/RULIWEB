package androidx.core.os;

import java.util.Locale;

interface LocaleListInterface {
    Locale get(int arg1);

    Locale getFirstMatch(String[] arg1);

    Object getLocaleList();

    int indexOf(Locale arg1);

    boolean isEmpty();

    int size();

    String toLanguageTags();
}

