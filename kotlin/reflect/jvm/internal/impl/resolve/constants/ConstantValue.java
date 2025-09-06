package kotlin.reflect.jvm.internal.impl.resolve.constants;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

public abstract class ConstantValue {
    private final Object value;

    public ConstantValue(Object object0) {
        this.value = object0;
    }

    @Override
    public boolean equals(Object object0) {
        if(this != object0) {
            Object object1 = this.getValue();
            Object object2 = null;
            ConstantValue constantValue0 = object0 instanceof ConstantValue ? ((ConstantValue)object0) : null;
            if(constantValue0 != null) {
                object2 = constantValue0.getValue();
            }
            return Intrinsics.areEqual(object1, object2);
        }
        return true;
    }

    public abstract KotlinType getType(ModuleDescriptor arg1);

    public Object getValue() {
        return this.value;
    }

    @Override
    public int hashCode() {
        Object object0 = this.getValue();
        return object0 == null ? 0 : object0.hashCode();
    }

    @Override
    public String toString() {
        return String.valueOf(this.getValue());
    }
}

