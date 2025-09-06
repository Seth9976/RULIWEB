package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

public interface AnnotationAndConstantLoader extends AnnotationLoader {
    Object loadAnnotationDefaultValue(ProtoContainer arg1, Property arg2, KotlinType arg3);

    Object loadPropertyConstant(ProtoContainer arg1, Property arg2, KotlinType arg3);
}

