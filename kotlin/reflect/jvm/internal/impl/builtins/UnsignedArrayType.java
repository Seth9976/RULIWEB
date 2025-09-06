package kotlin.reflect.jvm.internal.impl.builtins;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;

// 部分失败：枚举糖化
// 枚举按原样呈现，而不是糖化为Java 5枚举。
public final class UnsignedArrayType extends Enum {
    private static final UnsignedArrayType[] $VALUES;
    public static final enum UnsignedArrayType UBYTEARRAY;
    public static final enum UnsignedArrayType UINTARRAY;
    public static final enum UnsignedArrayType ULONGARRAY;
    public static final enum UnsignedArrayType USHORTARRAY;
    private final ClassId classId;
    private final Name typeName;

    private static final UnsignedArrayType[] $values() [...] // Inlined contents

    static {
        ClassId classId0 = ClassId.fromString("kotlin/UByteArray", false);
        if(classId0 == null) {
            throw (NullPointerException)Intrinsics.sanitizeStackTrace(new NullPointerException("fromString(\"kotlin/UByteArray\") must not be null"));
        }
        UnsignedArrayType.UBYTEARRAY = new UnsignedArrayType("UBYTEARRAY", 0, classId0);
        ClassId classId1 = ClassId.fromString("kotlin/UShortArray", false);
        if(classId1 == null) {
            throw (NullPointerException)Intrinsics.sanitizeStackTrace(new NullPointerException("fromString(\"kotlin/UShortArray\") must not be null"));
        }
        UnsignedArrayType.USHORTARRAY = new UnsignedArrayType("USHORTARRAY", 1, classId1);
        ClassId classId2 = ClassId.fromString("kotlin/UIntArray", false);
        if(classId2 == null) {
            throw (NullPointerException)Intrinsics.sanitizeStackTrace(new NullPointerException("fromString(\"kotlin/UIntArray\") must not be null"));
        }
        UnsignedArrayType.UINTARRAY = new UnsignedArrayType("UINTARRAY", 2, classId2);
        ClassId classId3 = ClassId.fromString("kotlin/ULongArray", false);
        if(classId3 == null) {
            throw (NullPointerException)Intrinsics.sanitizeStackTrace(new NullPointerException("fromString(\"kotlin/ULongArray\") must not be null"));
        }
        UnsignedArrayType.ULONGARRAY = new UnsignedArrayType("ULONGARRAY", 3, classId3);
        UnsignedArrayType.$VALUES = new UnsignedArrayType[]{UnsignedArrayType.UBYTEARRAY, UnsignedArrayType.USHORTARRAY, UnsignedArrayType.UINTARRAY, UnsignedArrayType.ULONGARRAY};
    }

    private UnsignedArrayType(String s, int v, ClassId classId0) {
        super(s, v);
        this.classId = classId0;
        Name name0 = classId0.getShortClassName();
        Intrinsics.checkNotNullExpressionValue(name0, "classId.shortClassName");
        this.typeName = name0;
    }

    public final Name getTypeName() {
        return this.typeName;
    }

    public static UnsignedArrayType valueOf(String s) {
        return (UnsignedArrayType)Enum.valueOf(UnsignedArrayType.class, s);
    }

    public static UnsignedArrayType[] values() {
        return (UnsignedArrayType[])UnsignedArrayType.$VALUES.clone();
    }
}

