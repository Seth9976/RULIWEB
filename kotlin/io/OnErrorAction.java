package kotlin.io;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesList;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lkotlin/io/OnErrorAction;", "", "(Ljava/lang/String;I)V", "SKIP", "TERMINATE", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public enum OnErrorAction {
    SKIP,
    TERMINATE;

    private static final EnumEntries $ENTRIES;
    private static final OnErrorAction[] $VALUES;

    private static final OnErrorAction[] $values() [...] // Inlined contents

    static {
        OnErrorAction.$VALUES = arr_onErrorAction;
        Intrinsics.checkNotNullParameter(arr_onErrorAction, "entries");
        OnErrorAction.$ENTRIES = new EnumEntriesList(arr_onErrorAction);
    }

    public static EnumEntries getEntries() {
        return OnErrorAction.$ENTRIES;
    }
}

