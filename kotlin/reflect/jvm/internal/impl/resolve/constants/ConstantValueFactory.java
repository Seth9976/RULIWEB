package kotlin.reflect.jvm.internal.impl.resolve.constants;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;

public final class ConstantValueFactory {
    public static final ConstantValueFactory INSTANCE;

    static {
        ConstantValueFactory.INSTANCE = new ConstantValueFactory();
    }

    private final ArrayValue createArrayValue(List list0, ModuleDescriptor moduleDescriptor0, PrimitiveType primitiveType0) {
        Iterable iterable0 = CollectionsKt.toList(list0);
        Collection collection0 = new ArrayList();
        for(Object object0: iterable0) {
            ConstantValue constantValue0 = ConstantValueFactory.createConstantValue$default(this, object0, null, 2, null);
            if(constantValue0 != null) {
                collection0.add(constantValue0);
            }
        }
        if(moduleDescriptor0 != null) {
            SimpleType simpleType0 = moduleDescriptor0.getBuiltIns().getPrimitiveArrayKotlinType(primitiveType0);
            Intrinsics.checkNotNullExpressionValue(simpleType0, "module.builtIns.getPrimi…KotlinType(componentType)");
            return new TypedArrayValue(((List)collection0), simpleType0);
        }
        return new ArrayValue(((List)collection0), new Function1() {
            final PrimitiveType $componentType;

            {
                this.$componentType = primitiveType0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((ModuleDescriptor)object0));
            }

            public final KotlinType invoke(ModuleDescriptor moduleDescriptor0) {
                Intrinsics.checkNotNullParameter(moduleDescriptor0, "it");
                SimpleType simpleType0 = moduleDescriptor0.getBuiltIns().getPrimitiveArrayKotlinType(this.$componentType);
                Intrinsics.checkNotNullExpressionValue(simpleType0, "it.builtIns.getPrimitive…KotlinType(componentType)");
                return simpleType0;
            }
        });
    }

    public final ArrayValue createArrayValue(List list0, KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(list0, "value");
        Intrinsics.checkNotNullParameter(kotlinType0, "type");
        return new TypedArrayValue(list0, kotlinType0);
    }

    public final ConstantValue createConstantValue(Object object0, ModuleDescriptor moduleDescriptor0) {
        if(object0 instanceof Byte) {
            return new ByteValue(((Number)object0).byteValue());
        }
        if(object0 instanceof Short) {
            return new ShortValue(((Number)object0).shortValue());
        }
        if(object0 instanceof Integer) {
            return new IntValue(((Number)object0).intValue());
        }
        if(object0 instanceof Long) {
            return new LongValue(((Number)object0).longValue());
        }
        if(object0 instanceof Character) {
            return new CharValue(((Character)object0).charValue());
        }
        if(object0 instanceof Float) {
            return new FloatValue(((Number)object0).floatValue());
        }
        if(object0 instanceof Double) {
            return new DoubleValue(((Number)object0).doubleValue());
        }
        if(object0 instanceof Boolean) {
            return new BooleanValue(((Boolean)object0).booleanValue());
        }
        if(object0 instanceof String) {
            return new StringValue(((String)object0));
        }
        if(object0 instanceof byte[]) {
            return this.createArrayValue(ArraysKt.toList(((byte[])object0)), moduleDescriptor0, PrimitiveType.BYTE);
        }
        if(object0 instanceof short[]) {
            return this.createArrayValue(ArraysKt.toList(((short[])object0)), moduleDescriptor0, PrimitiveType.SHORT);
        }
        if(object0 instanceof int[]) {
            return this.createArrayValue(ArraysKt.toList(((int[])object0)), moduleDescriptor0, PrimitiveType.INT);
        }
        if(object0 instanceof long[]) {
            return this.createArrayValue(ArraysKt.toList(((long[])object0)), moduleDescriptor0, PrimitiveType.LONG);
        }
        if(object0 instanceof char[]) {
            return this.createArrayValue(ArraysKt.toList(((char[])object0)), moduleDescriptor0, PrimitiveType.CHAR);
        }
        if(object0 instanceof float[]) {
            return this.createArrayValue(ArraysKt.toList(((float[])object0)), moduleDescriptor0, PrimitiveType.FLOAT);
        }
        if(object0 instanceof double[]) {
            return this.createArrayValue(ArraysKt.toList(((double[])object0)), moduleDescriptor0, PrimitiveType.DOUBLE);
        }
        if(object0 instanceof boolean[]) {
            return this.createArrayValue(ArraysKt.toList(((boolean[])object0)), moduleDescriptor0, PrimitiveType.BOOLEAN);
        }
        return object0 == null ? new NullValue() : null;
    }

    public static ConstantValue createConstantValue$default(ConstantValueFactory constantValueFactory0, Object object0, ModuleDescriptor moduleDescriptor0, int v, Object object1) {
        if((v & 2) != 0) {
            moduleDescriptor0 = null;
        }
        return constantValueFactory0.createConstantValue(object0, moduleDescriptor0);
    }
}

