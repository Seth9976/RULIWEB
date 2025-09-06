package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker.EMPTY;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractLazyTypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter.Variance;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.NameResolverUtilKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

public final class DeserializedTypeParameterDescriptor extends AbstractLazyTypeParameterDescriptor {
    private final DeserializedAnnotations annotations;
    private final DeserializationContext c;
    private final TypeParameter proto;

    public DeserializedTypeParameterDescriptor(DeserializationContext deserializationContext0, TypeParameter protoBuf$TypeParameter0, int v) {
        Intrinsics.checkNotNullParameter(deserializationContext0, "c");
        Intrinsics.checkNotNullParameter(protoBuf$TypeParameter0, "proto");
        StorageManager storageManager0 = deserializationContext0.getStorageManager();
        Name name0 = NameResolverUtilKt.getName(deserializationContext0.getNameResolver(), protoBuf$TypeParameter0.getName());
        Variance protoBuf$TypeParameter$Variance0 = protoBuf$TypeParameter0.getVariance();
        Intrinsics.checkNotNullExpressionValue(protoBuf$TypeParameter$Variance0, "proto.variance");
        kotlin.reflect.jvm.internal.impl.types.Variance variance0 = ProtoEnumFlags.INSTANCE.variance(protoBuf$TypeParameter$Variance0);
        super(storageManager0, deserializationContext0.getContainingDeclaration(), Annotations.Companion.getEMPTY(), name0, variance0, protoBuf$TypeParameter0.getReified(), v, SourceElement.NO_SOURCE, EMPTY.INSTANCE);
        this.c = deserializationContext0;
        this.proto = protoBuf$TypeParameter0;
        this.annotations = new DeserializedAnnotations(deserializationContext0.getStorageManager(), new Function0() {
            {
                DeserializedTypeParameterDescriptor.this = deserializedTypeParameterDescriptor0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final List invoke() {
                return CollectionsKt.toList(DeserializedTypeParameterDescriptor.this.c.getComponents().getAnnotationAndConstantLoader().loadTypeParameterAnnotations(DeserializedTypeParameterDescriptor.this.getProto(), DeserializedTypeParameterDescriptor.this.c.getNameResolver()));
            }
        });
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotatedImpl, kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated
    public Annotations getAnnotations() {
        return this.getAnnotations();
    }

    public DeserializedAnnotations getAnnotations() {
        return this.annotations;
    }

    public final TypeParameter getProto() {
        return this.proto;
    }

    protected Void reportSupertypeLoopError(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "type");
        throw new IllegalStateException("There should be no cycles for deserialized type parameters, but found for: " + this);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractTypeParameterDescriptor
    public void reportSupertypeLoopError(KotlinType kotlinType0) {
        this.reportSupertypeLoopError(kotlinType0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractTypeParameterDescriptor
    protected List resolveUpperBounds() {
        List list0 = ProtoTypeTableUtilKt.upperBounds(this.proto, this.c.getTypeTable());
        if(list0.isEmpty()) {
            return CollectionsKt.listOf(DescriptorUtilsKt.getBuiltIns(this).getDefaultBound());
        }
        TypeDeserializer typeDeserializer0 = this.c.getTypeDeserializer();
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
        for(Object object0: list0) {
            arrayList0.add(typeDeserializer0.type(((Type)object0)));
        }
        return arrayList0;
    }
}

