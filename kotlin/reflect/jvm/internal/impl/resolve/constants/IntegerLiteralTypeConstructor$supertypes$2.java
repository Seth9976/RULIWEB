package kotlin.reflect.jvm.internal.impl.resolve.constants;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutionKt;
import kotlin.reflect.jvm.internal.impl.types.Variance;

final class IntegerLiteralTypeConstructor.supertypes.2 extends Lambda implements Function0 {
    IntegerLiteralTypeConstructor.supertypes.2(IntegerLiteralTypeConstructor integerLiteralTypeConstructor0) {
        IntegerLiteralTypeConstructor.this = integerLiteralTypeConstructor0;
        super(0);
    }

    @Override  // kotlin.jvm.functions.Function0
    public Object invoke() {
        return this.invoke();
    }

    public final List invoke() {
        SimpleType[] arr_simpleType = new SimpleType[1];
        SimpleType simpleType0 = IntegerLiteralTypeConstructor.this.getBuiltIns().getComparable().getDefaultType();
        Intrinsics.checkNotNullExpressionValue(simpleType0, "builtIns.comparable.defaultType");
        arr_simpleType[0] = TypeSubstitutionKt.replace$default(simpleType0, CollectionsKt.listOf(new TypeProjectionImpl(Variance.IN_VARIANCE, IntegerLiteralTypeConstructor.access$getType$p(IntegerLiteralTypeConstructor.this))), null, 2, null);
        List list0 = CollectionsKt.mutableListOf(arr_simpleType);
        if(!IntegerLiteralTypeConstructor.access$isContainsOnlyUnsignedTypes(IntegerLiteralTypeConstructor.this)) {
            list0.add(IntegerLiteralTypeConstructor.this.getBuiltIns().getNumberType());
        }
        return list0;
    }
}

