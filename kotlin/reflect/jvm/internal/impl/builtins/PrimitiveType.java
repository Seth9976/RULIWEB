package kotlin.reflect.jvm.internal.impl.builtins;

import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;

public enum PrimitiveType {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    BOOLEAN("Boolean"),
    CHAR("Char"),
    BYTE("Byte"),
    SHORT("Short"),
    INT("Int"),
    FLOAT("Float"),
    LONG("Long"),
    DOUBLE("Double");

    public static final Companion Companion;
    public static final Set NUMBER_TYPES;
    private final Lazy arrayTypeFqName$delegate;
    private final Name arrayTypeName;
    private final Lazy typeFqName$delegate;
    private final Name typeName;

    private static final PrimitiveType[] $values() [...] // Inlined contents

    static {
        PrimitiveType.Companion = new Companion(null);
        PrimitiveType.NUMBER_TYPES = SetsKt.setOf(new PrimitiveType[]{PrimitiveType.CHAR, PrimitiveType.BYTE, PrimitiveType.SHORT, PrimitiveType.INT, PrimitiveType.FLOAT, PrimitiveType.LONG, PrimitiveType.DOUBLE});
    }

    private PrimitiveType(String s1) {
        Name name0 = Name.identifier(s1);
        Intrinsics.checkNotNullExpressionValue(name0, "identifier(typeName)");
        this.typeName = name0;
        Name name1 = Name.identifier((s1 + "Array"));
        Intrinsics.checkNotNullExpressionValue(name1, "identifier(\"${typeName}Array\")");
        this.arrayTypeName = name1;
        Function0 function00 = new Function0() {
            {
                PrimitiveType.this = primitiveType0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final FqName invoke() {
                FqName fqName0 = StandardNames.BUILT_INS_PACKAGE_FQ_NAME.child(PrimitiveType.this.getTypeName());
                Intrinsics.checkNotNullExpressionValue(fqName0, "BUILT_INS_PACKAGE_FQ_NAME.child(this.typeName)");
                return fqName0;
            }
        };
        this.typeFqName$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, function00);
        Function0 function01 = new Function0() {
            {
                PrimitiveType.this = primitiveType0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final FqName invoke() {
                FqName fqName0 = StandardNames.BUILT_INS_PACKAGE_FQ_NAME.child(PrimitiveType.this.getArrayTypeName());
                Intrinsics.checkNotNullExpressionValue(fqName0, "BUILT_INS_PACKAGE_FQ_NAME.child(arrayTypeName)");
                return fqName0;
            }
        };
        this.arrayTypeFqName$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, function01);
    }

    public final FqName getArrayTypeFqName() {
        return (FqName)this.arrayTypeFqName$delegate.getValue();
    }

    public final Name getArrayTypeName() {
        return this.arrayTypeName;
    }

    public final FqName getTypeFqName() {
        return (FqName)this.typeFqName$delegate.getValue();
    }

    public final Name getTypeName() {
        return this.typeName;
    }
}

