package kotlin.text;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\r\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u0019\b\u0002\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001A\u00020\u0004¢\u0006\u0002\u0010\u0006R\u0014\u0010\u0005\u001A\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0007\u0010\bR\u0014\u0010\u0003\u001A\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\t\u0010\bj\u0002\b\nj\u0002\b\u000Bj\u0002\b\fj\u0002\b\rj\u0002\b\u000Ej\u0002\b\u000Fj\u0002\b\u0010¨\u0006\u0011"}, d2 = {"Lkotlin/text/RegexOption;", "", "Lkotlin/text/FlagEnum;", "value", "", "mask", "(Ljava/lang/String;III)V", "getMask", "()I", "getValue", "IGNORE_CASE", "MULTILINE", "LITERAL", "UNIX_LINES", "COMMENTS", "DOT_MATCHES_ALL", "CANON_EQ", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public enum RegexOption implements FlagEnum {
    IGNORE_CASE(2, 0, 2, null),
    MULTILINE(8, 0, 2, null),
    LITERAL(16, 0, 2, null),
    UNIX_LINES(1, 0, 2, null),
    COMMENTS(4, 0, 2, null),
    DOT_MATCHES_ALL(0x20, 0, 2, null),
    CANON_EQ(0x80, 0, 2, null);

    private static final EnumEntries $ENTRIES;
    private static final RegexOption[] $VALUES;
    private final int mask;
    private final int value;

    private static final RegexOption[] $values() [...] // Inlined contents

    static {
        RegexOption.$VALUES = arr_regexOption;
        Intrinsics.checkNotNullParameter(arr_regexOption, "entries");
        RegexOption.$ENTRIES = new EnumEntriesList(arr_regexOption);
    }

    private RegexOption(int v1, int v2) {
        this.value = v1;
        this.mask = v2;
    }

    RegexOption(int v1, int v2, int v3, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v3 & 2) != 0) {
            v2 = v1;
        }
        this(v1, v2);
    }

    public static EnumEntries getEntries() {
        return RegexOption.$ENTRIES;
    }

    @Override  // kotlin.text.FlagEnum
    public int getMask() {
        return this.mask;
    }

    @Override  // kotlin.text.FlagEnum
    public int getValue() {
        return this.value;
    }
}

