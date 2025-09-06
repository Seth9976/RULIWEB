package kotlin.reflect.jvm.internal.impl.resolve.constants;

import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

public class ArrayValue extends ConstantValue {
    private final Function1 computeType;

    public ArrayValue(List list0, Function1 function10) {
        Intrinsics.checkNotNullParameter(list0, "value");
        Intrinsics.checkNotNullParameter(function10, "computeType");
        super(list0);
        this.computeType = function10;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue
    public KotlinType getType(ModuleDescriptor moduleDescriptor0) {
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "module");
        KotlinType kotlinType0 = (KotlinType)this.computeType.invoke(moduleDescriptor0);
        if(!KotlinBuiltIns.isArray(kotlinType0) && !KotlinBuiltIns.isPrimitiveArray(kotlinType0)) {
            KotlinBuiltIns.isUnsignedArrayType(kotlinType0);
        }
        return kotlinType0;
    }
}

