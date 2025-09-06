package kotlin.collections;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesList;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lkotlin/collections/State;", "", "(Ljava/lang/String;I)V", "Ready", "NotReady", "Done", "Failed", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
enum State {
    Ready,
    NotReady,
    Done,
    Failed;

    private static final EnumEntries $ENTRIES;
    private static final State[] $VALUES;

    private static final State[] $values() [...] // Inlined contents

    static {
        State.$VALUES = arr_state;
        Intrinsics.checkNotNullParameter(arr_state, "entries");
        State.$ENTRIES = new EnumEntriesList(arr_state);
    }

    public static EnumEntries getEntries() {
        return State.$ENTRIES;
    }
}

