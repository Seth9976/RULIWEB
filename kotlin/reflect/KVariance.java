package kotlin.reflect;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesList;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0087\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lkotlin/reflect/KVariance;", "", "(Ljava/lang/String;I)V", "INVARIANT", "IN", "OUT", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public enum KVariance {
    INVARIANT,
    IN,
    OUT;

    private static final EnumEntries $ENTRIES;
    private static final KVariance[] $VALUES;

    private static final KVariance[] $values() [...] // Inlined contents

    static {
        KVariance.$VALUES = arr_kVariance;
        Intrinsics.checkNotNullParameter(arr_kVariance, "entries");
        KVariance.$ENTRIES = new EnumEntriesList(arr_kVariance);
    }

    public static EnumEntries getEntries() {
        return KVariance.$ENTRIES;
    }
}

