package kotlin.reflect.jvm.internal.impl.resolve.constants;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;

public final class ByteValue extends IntegerValueConstant {
    public ByteValue(byte b) {
        super(b);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue
    public KotlinType getType(ModuleDescriptor moduleDescriptor0) {
        return this.getType(moduleDescriptor0);
    }

    public SimpleType getType(ModuleDescriptor moduleDescriptor0) {
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "module");
        SimpleType simpleType0 = moduleDescriptor0.getBuiltIns().getByteType();
        Intrinsics.checkNotNullExpressionValue(simpleType0, "module.builtIns.byteType");
        return simpleType0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue
    public String toString() {
        return ((Number)this.getValue()).intValue() + ".toByte()";
    }
}

