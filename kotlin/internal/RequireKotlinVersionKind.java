package kotlin.internal;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesList;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0081\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lkotlin/internal/RequireKotlinVersionKind;", "", "(Ljava/lang/String;I)V", "LANGUAGE_VERSION", "COMPILER_VERSION", "API_VERSION", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public enum RequireKotlinVersionKind {
    LANGUAGE_VERSION,
    COMPILER_VERSION,
    API_VERSION;

    private static final EnumEntries $ENTRIES;
    private static final RequireKotlinVersionKind[] $VALUES;

    private static final RequireKotlinVersionKind[] $values() [...] // Inlined contents

    static {
        RequireKotlinVersionKind.$VALUES = arr_requireKotlinVersionKind;
        Intrinsics.checkNotNullParameter(arr_requireKotlinVersionKind, "entries");
        RequireKotlinVersionKind.$ENTRIES = new EnumEntriesList(arr_requireKotlinVersionKind);
    }

    public static EnumEntries getEntries() {
        return RequireKotlinVersionKind.$ENTRIES;
    }
}

