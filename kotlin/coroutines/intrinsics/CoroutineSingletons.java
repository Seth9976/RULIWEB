package kotlin.coroutines.intrinsics;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesList;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0081\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lkotlin/coroutines/intrinsics/CoroutineSingletons;", "", "(Ljava/lang/String;I)V", "COROUTINE_SUSPENDED", "UNDECIDED", "RESUMED", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public enum CoroutineSingletons {
    COROUTINE_SUSPENDED,
    UNDECIDED,
    RESUMED;

    private static final EnumEntries $ENTRIES;
    private static final CoroutineSingletons[] $VALUES;

    private static final CoroutineSingletons[] $values() [...] // Inlined contents

    static {
        CoroutineSingletons.$VALUES = arr_coroutineSingletons;
        Intrinsics.checkNotNullParameter(arr_coroutineSingletons, "entries");
        CoroutineSingletons.$ENTRIES = new EnumEntriesList(arr_coroutineSingletons);
    }

    public static EnumEntries getEntries() {
        return CoroutineSingletons.$ENTRIES;
    }
}

