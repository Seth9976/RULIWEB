package kotlin.reflect.jvm.internal.impl.resolve.constants;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;

public final class LongValue extends IntegerValueConstant {
    public LongValue(long v) {
        super(v);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue
    public KotlinType getType(ModuleDescriptor moduleDescriptor0) {
        return this.getType(moduleDescriptor0);
    }

    public SimpleType getType(ModuleDescriptor moduleDescriptor0) {
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "module");
        SimpleType simpleType0 = moduleDescriptor0.getBuiltIns().getLongType();
        Intrinsics.checkNotNullExpressionValue(simpleType0, "module.builtIns.longType");
        return simpleType0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue
    public String toString() {
        return ((Number)this.getValue()).longValue() + ".toLong()";
    }
}

