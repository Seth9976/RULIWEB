package kotlin.reflect.jvm.internal.impl.name;

import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

public final class SpecialNames {
    public static final Name ANONYMOUS;
    public static final Name ARRAY;
    public static final Name DEFAULT_NAME_FOR_COMPANION_OBJECT;
    public static final Name DESTRUCT;
    public static final Name ENUM_GET_ENTRIES;
    public static final Name IMPLICIT_SET_PARAMETER;
    public static final Name INIT;
    public static final SpecialNames INSTANCE;
    public static final Name ITERATOR;
    public static final Name LOCAL;
    public static final Name NO_NAME_PROVIDED;
    public static final Name RECEIVER;
    public static final Name ROOT_PACKAGE;
    public static final Name SAFE_IDENTIFIER_FOR_NO_NAME;
    public static final Name THIS;
    public static final Name UNARY;
    public static final Name UNARY_RESULT;
    public static final Name UNDERSCORE_FOR_UNUSED_VAR;

    static {
        SpecialNames.INSTANCE = new SpecialNames();
        Name name0 = Name.special("<no name provided>");
        Intrinsics.checkNotNullExpressionValue(name0, "special(\"<no name provided>\")");
        SpecialNames.NO_NAME_PROVIDED = name0;
        Name name1 = Name.special("<root package>");
        Intrinsics.checkNotNullExpressionValue(name1, "special(\"<root package>\")");
        SpecialNames.ROOT_PACKAGE = name1;
        Name name2 = Name.identifier("Companion");
        Intrinsics.checkNotNullExpressionValue(name2, "identifier(\"Companion\")");
        SpecialNames.DEFAULT_NAME_FOR_COMPANION_OBJECT = name2;
        Name name3 = Name.identifier("no_name_in_PSI_3d19d79d_1ba9_4cd0_b7f5_b46aa3cd5d40");
        Intrinsics.checkNotNullExpressionValue(name3, "identifier(\"no_name_in_P…_4cd0_b7f5_b46aa3cd5d40\")");
        SpecialNames.SAFE_IDENTIFIER_FOR_NO_NAME = name3;
        Name name4 = Name.special("<anonymous>");
        Intrinsics.checkNotNullExpressionValue(name4, "special(ANONYMOUS_STRING)");
        SpecialNames.ANONYMOUS = name4;
        Name name5 = Name.special("<unary>");
        Intrinsics.checkNotNullExpressionValue(name5, "special(\"<unary>\")");
        SpecialNames.UNARY = name5;
        Name name6 = Name.special("<unary-result>");
        Intrinsics.checkNotNullExpressionValue(name6, "special(\"<unary-result>\")");
        SpecialNames.UNARY_RESULT = name6;
        Name name7 = Name.special("<this>");
        Intrinsics.checkNotNullExpressionValue(name7, "special(\"<this>\")");
        SpecialNames.THIS = name7;
        Name name8 = Name.special("<init>");
        Intrinsics.checkNotNullExpressionValue(name8, "special(\"<init>\")");
        SpecialNames.INIT = name8;
        Name name9 = Name.special("<iterator>");
        Intrinsics.checkNotNullExpressionValue(name9, "special(\"<iterator>\")");
        SpecialNames.ITERATOR = name9;
        Name name10 = Name.special("<destruct>");
        Intrinsics.checkNotNullExpressionValue(name10, "special(\"<destruct>\")");
        SpecialNames.DESTRUCT = name10;
        Name name11 = Name.special("<local>");
        Intrinsics.checkNotNullExpressionValue(name11, "special(\"<local>\")");
        SpecialNames.LOCAL = name11;
        Name name12 = Name.special("<unused var>");
        Intrinsics.checkNotNullExpressionValue(name12, "special(\"<unused var>\")");
        SpecialNames.UNDERSCORE_FOR_UNUSED_VAR = name12;
        Name name13 = Name.special("<set-?>");
        Intrinsics.checkNotNullExpressionValue(name13, "special(\"<set-?>\")");
        SpecialNames.IMPLICIT_SET_PARAMETER = name13;
        Name name14 = Name.special("<array>");
        Intrinsics.checkNotNullExpressionValue(name14, "special(\"<array>\")");
        SpecialNames.ARRAY = name14;
        Name name15 = Name.special("<receiver>");
        Intrinsics.checkNotNullExpressionValue(name15, "special(\"<receiver>\")");
        SpecialNames.RECEIVER = name15;
        Name name16 = Name.special("<get-entries>");
        Intrinsics.checkNotNullExpressionValue(name16, "special(\"<get-entries>\")");
        SpecialNames.ENUM_GET_ENTRIES = name16;
    }

    public final boolean isSafeIdentifier(Name name0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        String s = name0.asString();
        Intrinsics.checkNotNullExpressionValue(s, "name.asString()");
        return s.length() > 0 && !name0.isSpecial();
    }

    @JvmStatic
    public static final Name safeIdentifier(Name name0) {
        return name0 == null || name0.isSpecial() ? SpecialNames.SAFE_IDENTIFIER_FOR_NO_NAME : name0;
    }
}

