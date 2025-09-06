package kotlin.reflect.jvm.internal.impl.builtins;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;

// 部分失败：枚举糖化
// 枚举按原样呈现，而不是糖化为Java 5枚举。
public final class UnsignedType extends Enum {
    private static final UnsignedType[] $VALUES;
    public static final enum UnsignedType UBYTE;
    public static final enum UnsignedType UINT;
    public static final enum UnsignedType ULONG;
    public static final enum UnsignedType USHORT;
    private final ClassId arrayClassId;
    private final ClassId classId;
    private final Name typeName;

    private static final UnsignedType[] $values() [...] // Inlined contents

    static {
        ClassId classId0 = ClassId.fromString("kotlin/UByte", false);
        if(classId0 == null) {
            throw (NullPointerException)Intrinsics.sanitizeStackTrace(new NullPointerException("fromString(\"kotlin/UByte\") must not be null"));
        }
        UnsignedType.UBYTE = new UnsignedType("UBYTE", 0, classId0);
        ClassId classId1 = ClassId.fromString("kotlin/UShort", false);
        if(classId1 == null) {
            throw (NullPointerException)Intrinsics.sanitizeStackTrace(new NullPointerException("fromString(\"kotlin/UShort\") must not be null"));
        }
        UnsignedType.USHORT = new UnsignedType("USHORT", 1, classId1);
        ClassId classId2 = ClassId.fromString("kotlin/UInt", false);
        if(classId2 == null) {
            throw (NullPointerException)Intrinsics.sanitizeStackTrace(new NullPointerException("fromString(\"kotlin/UInt\") must not be null"));
        }
        UnsignedType.UINT = new UnsignedType("UINT", 2, classId2);
        ClassId classId3 = ClassId.fromString("kotlin/ULong", false);
        if(classId3 == null) {
            throw (NullPointerException)Intrinsics.sanitizeStackTrace(new NullPointerException("fromString(\"kotlin/ULong\") must not be null"));
        }
        UnsignedType.ULONG = new UnsignedType("ULONG", 3, classId3);
        UnsignedType.$VALUES = new UnsignedType[]{UnsignedType.UBYTE, UnsignedType.USHORT, UnsignedType.UINT, UnsignedType.ULONG};
    }

    private UnsignedType(String s, int v, ClassId classId0) {
        super(s, v);
        this.classId = classId0;
        Name name0 = classId0.getShortClassName();
        Intrinsics.checkNotNullExpressionValue(name0, "classId.shortClassName");
        this.typeName = name0;
        this.arrayClassId = new ClassId(classId0.getPackageFqName(), Name.identifier((name0.asString() + "Array")));
    }

    public final ClassId getArrayClassId() {
        return this.arrayClassId;
    }

    public final ClassId getClassId() {
        return this.classId;
    }

    public final Name getTypeName() {
        return this.typeName;
    }

    public static UnsignedType valueOf(String s) {
        return (UnsignedType)Enum.valueOf(UnsignedType.class, s);
    }

    public static UnsignedType[] values() {
        return (UnsignedType[])UnsignedType.$VALUES.clone();
    }
}

