package kotlin.contracts;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesList;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0087\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lkotlin/contracts/InvocationKind;", "", "(Ljava/lang/String;I)V", "AT_MOST_ONCE", "AT_LEAST_ONCE", "EXACTLY_ONCE", "UNKNOWN", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public enum InvocationKind {
    AT_MOST_ONCE,
    AT_LEAST_ONCE,
    EXACTLY_ONCE,
    UNKNOWN;

    private static final EnumEntries $ENTRIES;
    private static final InvocationKind[] $VALUES;

    private static final InvocationKind[] $values() [...] // Inlined contents

    static {
        InvocationKind.$VALUES = arr_invocationKind;
        Intrinsics.checkNotNullParameter(arr_invocationKind, "entries");
        InvocationKind.$ENTRIES = new EnumEntriesList(arr_invocationKind);
    }

    public static EnumEntries getEntries() {
        return InvocationKind.$ENTRIES;
    }
}

