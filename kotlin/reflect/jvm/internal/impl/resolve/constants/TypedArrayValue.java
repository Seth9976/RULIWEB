package kotlin.reflect.jvm.internal.impl.resolve.constants;

import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

public final class TypedArrayValue extends ArrayValue {
    private final KotlinType type;

    public TypedArrayValue(List list0, KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(list0, "value");
        Intrinsics.checkNotNullParameter(kotlinType0, "type");
        super(list0, new Function1() {
            final KotlinType $type;

            {
                this.$type = kotlinType0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((ModuleDescriptor)object0));
            }

            public final KotlinType invoke(ModuleDescriptor moduleDescriptor0) {
                Intrinsics.checkNotNullParameter(moduleDescriptor0, "it");
                return this.$type;
            }
        });
        this.type = kotlinType0;
    }

    public final KotlinType getType() {
        return this.type;
    }
}

