package com.ruliweb.www.ruliapp;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesList;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0011\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000F\b\u0002\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000Bj\u0002\b\fj\u0002\b\rj\u0002\b\u000Ej\u0002\b\u000Fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013¨\u0006\u0014"}, d2 = {"Lcom/ruliweb/www/ruliapp/RuliAppFunction;", "", "functionId", "", "(Ljava/lang/String;II)V", "getFunctionId", "()I", "BACK", "FRONT", "REFRESH", "GOBOTTOM", "GOTOP", "COMMENT", "MORE", "SETTINGS", "FIND", "URLCOPY", "SHARE", "NOTIFICATION", "HOME", "app_release"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public enum RuliAppFunction {
    BACK(1),
    FRONT(2),
    REFRESH(3),
    GOBOTTOM(4),
    GOTOP(5),
    COMMENT(6),
    MORE(7),
    SETTINGS(8),
    FIND(9),
    URLCOPY(10),
    SHARE(11),
    NOTIFICATION(12),
    HOME(13);

    private static final EnumEntries $ENTRIES;
    private static final RuliAppFunction[] $VALUES;
    private final int functionId;

    private static final RuliAppFunction[] $values() [...] // Inlined contents

    static {
        RuliAppFunction.$VALUES = arr_ruliAppFunction;
        Intrinsics.checkNotNullParameter(arr_ruliAppFunction, "entries");
        RuliAppFunction.$ENTRIES = new EnumEntriesList(arr_ruliAppFunction);
    }

    private RuliAppFunction(int v1) {
        this.functionId = v1;
    }

    public static EnumEntries getEntries() {
        return RuliAppFunction.$ENTRIES;
    }

    public final int getFunctionId() {
        return this.functionId;
    }
}

