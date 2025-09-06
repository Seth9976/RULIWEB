package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.List;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.EnumEntry;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;

public interface AnnotationLoader {
    List loadCallableAnnotations(ProtoContainer arg1, MessageLite arg2, AnnotatedCallableKind arg3);

    List loadClassAnnotations(Class arg1);

    List loadEnumEntryAnnotations(ProtoContainer arg1, EnumEntry arg2);

    List loadExtensionReceiverParameterAnnotations(ProtoContainer arg1, MessageLite arg2, AnnotatedCallableKind arg3);

    List loadPropertyBackingFieldAnnotations(ProtoContainer arg1, Property arg2);

    List loadPropertyDelegateFieldAnnotations(ProtoContainer arg1, Property arg2);

    List loadTypeAnnotations(Type arg1, NameResolver arg2);

    List loadTypeParameterAnnotations(TypeParameter arg1, NameResolver arg2);

    List loadValueParameterAnnotations(ProtoContainer arg1, MessageLite arg2, AnnotatedCallableKind arg3, int arg4, ValueParameter arg5);
}

