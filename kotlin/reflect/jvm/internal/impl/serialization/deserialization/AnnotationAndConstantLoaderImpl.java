package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Constructor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.EnumEntry;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoBufUtilKt;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.GeneratedExtension;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.serialization.SerializerExtensionProtocol;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

public final class AnnotationAndConstantLoaderImpl implements AnnotationAndConstantLoader {
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] arr_v = new int[AnnotatedCallableKind.values().length];
            try {
                arr_v[AnnotatedCallableKind.PROPERTY.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[AnnotatedCallableKind.PROPERTY_GETTER.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[AnnotatedCallableKind.PROPERTY_SETTER.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$0 = arr_v;
        }
    }

    private final AnnotationDeserializer deserializer;
    private final SerializerExtensionProtocol protocol;

    public AnnotationAndConstantLoaderImpl(ModuleDescriptor moduleDescriptor0, NotFoundClasses notFoundClasses0, SerializerExtensionProtocol serializerExtensionProtocol0) {
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "module");
        Intrinsics.checkNotNullParameter(notFoundClasses0, "notFoundClasses");
        Intrinsics.checkNotNullParameter(serializerExtensionProtocol0, "protocol");
        super();
        this.protocol = serializerExtensionProtocol0;
        this.deserializer = new AnnotationDeserializer(moduleDescriptor0, notFoundClasses0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationAndConstantLoader
    public Object loadAnnotationDefaultValue(ProtoContainer protoContainer0, Property protoBuf$Property0, KotlinType kotlinType0) {
        return this.loadAnnotationDefaultValue(protoContainer0, protoBuf$Property0, kotlinType0);
    }

    public ConstantValue loadAnnotationDefaultValue(ProtoContainer protoContainer0, Property protoBuf$Property0, KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(protoContainer0, "container");
        Intrinsics.checkNotNullParameter(protoBuf$Property0, "proto");
        Intrinsics.checkNotNullParameter(kotlinType0, "expectedType");
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader
    public List loadCallableAnnotations(ProtoContainer protoContainer0, MessageLite messageLite0, AnnotatedCallableKind annotatedCallableKind0) {
        List list0;
        Intrinsics.checkNotNullParameter(protoContainer0, "container");
        Intrinsics.checkNotNullParameter(messageLite0, "proto");
        Intrinsics.checkNotNullParameter(annotatedCallableKind0, "kind");
        if(messageLite0 instanceof Constructor) {
            list0 = (List)((Constructor)messageLite0).getExtension(this.protocol.getConstructorAnnotation());
        }
        else if(messageLite0 instanceof Function) {
            list0 = (List)((Function)messageLite0).getExtension(this.protocol.getFunctionAnnotation());
        }
        else if(messageLite0 instanceof Property) {
            switch(WhenMappings.$EnumSwitchMapping$0[annotatedCallableKind0.ordinal()]) {
                case 1: {
                    list0 = (List)((Property)messageLite0).getExtension(this.protocol.getPropertyAnnotation());
                    break;
                }
                case 2: {
                    list0 = (List)((Property)messageLite0).getExtension(this.protocol.getPropertyGetterAnnotation());
                    break;
                }
                case 3: {
                    list0 = (List)((Property)messageLite0).getExtension(this.protocol.getPropertySetterAnnotation());
                    break;
                }
                default: {
                    throw new IllegalStateException("Unsupported callable kind with property proto");
                }
            }
        }
        else {
            throw new IllegalStateException(("Unknown message: " + messageLite0).toString());
        }
        if(list0 == null) {
            list0 = CollectionsKt.emptyList();
        }
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
        for(Object object0: list0) {
            arrayList0.add(this.deserializer.deserializeAnnotation(((Annotation)object0), protoContainer0.getNameResolver()));
        }
        return arrayList0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader
    public List loadClassAnnotations(Class protoContainer$Class0) {
        Intrinsics.checkNotNullParameter(protoContainer$Class0, "container");
        List list0 = (List)protoContainer$Class0.getClassProto().getExtension(this.protocol.getClassAnnotation());
        if(list0 == null) {
            list0 = CollectionsKt.emptyList();
        }
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
        for(Object object0: list0) {
            NameResolver nameResolver0 = protoContainer$Class0.getNameResolver();
            arrayList0.add(this.deserializer.deserializeAnnotation(((Annotation)object0), nameResolver0));
        }
        return arrayList0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader
    public List loadEnumEntryAnnotations(ProtoContainer protoContainer0, EnumEntry protoBuf$EnumEntry0) {
        Intrinsics.checkNotNullParameter(protoContainer0, "container");
        Intrinsics.checkNotNullParameter(protoBuf$EnumEntry0, "proto");
        List list0 = (List)protoBuf$EnumEntry0.getExtension(this.protocol.getEnumEntryAnnotation());
        if(list0 == null) {
            list0 = CollectionsKt.emptyList();
        }
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
        for(Object object0: list0) {
            arrayList0.add(this.deserializer.deserializeAnnotation(((Annotation)object0), protoContainer0.getNameResolver()));
        }
        return arrayList0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader
    public List loadExtensionReceiverParameterAnnotations(ProtoContainer protoContainer0, MessageLite messageLite0, AnnotatedCallableKind annotatedCallableKind0) {
        Intrinsics.checkNotNullParameter(protoContainer0, "container");
        Intrinsics.checkNotNullParameter(messageLite0, "proto");
        Intrinsics.checkNotNullParameter(annotatedCallableKind0, "kind");
        List list0 = null;
        if(messageLite0 instanceof Function) {
            GeneratedExtension generatedMessageLite$GeneratedExtension0 = this.protocol.getFunctionExtensionReceiverAnnotation();
            if(generatedMessageLite$GeneratedExtension0 == null) {
                goto label_15;
            }
            list0 = (List)((Function)messageLite0).getExtension(generatedMessageLite$GeneratedExtension0);
        }
        else if(messageLite0 instanceof Property) {
            switch(WhenMappings.$EnumSwitchMapping$0[annotatedCallableKind0.ordinal()]) {
                case 1: 
                case 2: 
                case 3: {
                    GeneratedExtension generatedMessageLite$GeneratedExtension1 = this.protocol.getPropertyExtensionReceiverAnnotation();
                    if(generatedMessageLite$GeneratedExtension1 != null) {
                        list0 = (List)((Property)messageLite0).getExtension(generatedMessageLite$GeneratedExtension1);
                    }
                    break;
                }
                default: {
                    throw new IllegalStateException(("Unsupported callable kind with property proto for receiver annotations: " + annotatedCallableKind0).toString());
                }
            }
        }
        else {
            throw new IllegalStateException(("Unknown message: " + messageLite0).toString());
        }
    label_15:
        if(list0 == null) {
            list0 = CollectionsKt.emptyList();
        }
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
        for(Object object0: list0) {
            arrayList0.add(this.deserializer.deserializeAnnotation(((Annotation)object0), protoContainer0.getNameResolver()));
        }
        return arrayList0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader
    public List loadPropertyBackingFieldAnnotations(ProtoContainer protoContainer0, Property protoBuf$Property0) {
        Intrinsics.checkNotNullParameter(protoContainer0, "container");
        Intrinsics.checkNotNullParameter(protoBuf$Property0, "proto");
        GeneratedExtension generatedMessageLite$GeneratedExtension0 = this.protocol.getPropertyBackingFieldAnnotation();
        List list0 = generatedMessageLite$GeneratedExtension0 == null ? null : ((List)protoBuf$Property0.getExtension(generatedMessageLite$GeneratedExtension0));
        if(list0 == null) {
            list0 = CollectionsKt.emptyList();
        }
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
        for(Object object0: list0) {
            arrayList0.add(this.deserializer.deserializeAnnotation(((Annotation)object0), protoContainer0.getNameResolver()));
        }
        return arrayList0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationAndConstantLoader
    public Object loadPropertyConstant(ProtoContainer protoContainer0, Property protoBuf$Property0, KotlinType kotlinType0) {
        return this.loadPropertyConstant(protoContainer0, protoBuf$Property0, kotlinType0);
    }

    public ConstantValue loadPropertyConstant(ProtoContainer protoContainer0, Property protoBuf$Property0, KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(protoContainer0, "container");
        Intrinsics.checkNotNullParameter(protoBuf$Property0, "proto");
        Intrinsics.checkNotNullParameter(kotlinType0, "expectedType");
        Value protoBuf$Annotation$Argument$Value0 = (Value)ProtoBufUtilKt.getExtensionOrNull(protoBuf$Property0, this.protocol.getCompileTimeValue());
        return protoBuf$Annotation$Argument$Value0 == null ? null : this.deserializer.resolveValue(kotlinType0, protoBuf$Annotation$Argument$Value0, protoContainer0.getNameResolver());
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader
    public List loadPropertyDelegateFieldAnnotations(ProtoContainer protoContainer0, Property protoBuf$Property0) {
        Intrinsics.checkNotNullParameter(protoContainer0, "container");
        Intrinsics.checkNotNullParameter(protoBuf$Property0, "proto");
        GeneratedExtension generatedMessageLite$GeneratedExtension0 = this.protocol.getPropertyDelegatedFieldAnnotation();
        List list0 = generatedMessageLite$GeneratedExtension0 == null ? null : ((List)protoBuf$Property0.getExtension(generatedMessageLite$GeneratedExtension0));
        if(list0 == null) {
            list0 = CollectionsKt.emptyList();
        }
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
        for(Object object0: list0) {
            arrayList0.add(this.deserializer.deserializeAnnotation(((Annotation)object0), protoContainer0.getNameResolver()));
        }
        return arrayList0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader
    public List loadTypeAnnotations(Type protoBuf$Type0, NameResolver nameResolver0) {
        Intrinsics.checkNotNullParameter(protoBuf$Type0, "proto");
        Intrinsics.checkNotNullParameter(nameResolver0, "nameResolver");
        List list0 = (List)protoBuf$Type0.getExtension(this.protocol.getTypeAnnotation());
        if(list0 == null) {
            list0 = CollectionsKt.emptyList();
        }
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
        for(Object object0: list0) {
            arrayList0.add(this.deserializer.deserializeAnnotation(((Annotation)object0), nameResolver0));
        }
        return arrayList0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader
    public List loadTypeParameterAnnotations(TypeParameter protoBuf$TypeParameter0, NameResolver nameResolver0) {
        Intrinsics.checkNotNullParameter(protoBuf$TypeParameter0, "proto");
        Intrinsics.checkNotNullParameter(nameResolver0, "nameResolver");
        List list0 = (List)protoBuf$TypeParameter0.getExtension(this.protocol.getTypeParameterAnnotation());
        if(list0 == null) {
            list0 = CollectionsKt.emptyList();
        }
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
        for(Object object0: list0) {
            arrayList0.add(this.deserializer.deserializeAnnotation(((Annotation)object0), nameResolver0));
        }
        return arrayList0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader
    public List loadValueParameterAnnotations(ProtoContainer protoContainer0, MessageLite messageLite0, AnnotatedCallableKind annotatedCallableKind0, int v, ValueParameter protoBuf$ValueParameter0) {
        Intrinsics.checkNotNullParameter(protoContainer0, "container");
        Intrinsics.checkNotNullParameter(messageLite0, "callableProto");
        Intrinsics.checkNotNullParameter(annotatedCallableKind0, "kind");
        Intrinsics.checkNotNullParameter(protoBuf$ValueParameter0, "proto");
        List list0 = (List)protoBuf$ValueParameter0.getExtension(this.protocol.getParameterAnnotation());
        if(list0 == null) {
            list0 = CollectionsKt.emptyList();
        }
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
        for(Object object0: list0) {
            arrayList0.add(this.deserializer.deserializeAnnotation(((Annotation)object0), protoContainer0.getNameResolver()));
        }
        return arrayList0;
    }
}

