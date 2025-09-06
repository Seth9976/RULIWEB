package kotlin.io.path;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesList;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0087\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lkotlin/io/path/CopyActionResult;", "", "(Ljava/lang/String;I)V", "CONTINUE", "SKIP_SUBTREE", "TERMINATE", "kotlin-stdlib-jdk7"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public enum CopyActionResult {
    CONTINUE,
    SKIP_SUBTREE,
    TERMINATE;

    private static final EnumEntries $ENTRIES;
    private static final CopyActionResult[] $VALUES;

    private static final CopyActionResult[] $values() [...] // Inlined contents

    static {
        CopyActionResult.$VALUES = arr_copyActionResult;
        Intrinsics.checkNotNullParameter(arr_copyActionResult, "entries");
        CopyActionResult.$ENTRIES = new EnumEntriesList(arr_copyActionResult);
    }

    public static EnumEntries getEntries() {
        return CopyActionResult.$ENTRIES;
    }
}

