package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorNonRoot;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractTypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeAlias;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutionKt;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.Variance;

public final class DeserializedTypeAliasDescriptor extends AbstractTypeAliasDescriptor implements DeserializedMemberDescriptor {
    private Collection constructors;
    private final DeserializedContainerSource containerSource;
    private SimpleType defaultTypeImpl;
    private SimpleType expandedType;
    private final NameResolver nameResolver;
    private final TypeAlias proto;
    private final StorageManager storageManager;
    private List typeConstructorParameters;
    private final TypeTable typeTable;
    private SimpleType underlyingType;
    private final VersionRequirementTable versionRequirementTable;

    public DeserializedTypeAliasDescriptor(StorageManager storageManager0, DeclarationDescriptor declarationDescriptor0, Annotations annotations0, Name name0, DescriptorVisibility descriptorVisibility0, TypeAlias protoBuf$TypeAlias0, NameResolver nameResolver0, TypeTable typeTable0, VersionRequirementTable versionRequirementTable0, DeserializedContainerSource deserializedContainerSource0) {
        Intrinsics.checkNotNullParameter(storageManager0, "storageManager");
        Intrinsics.checkNotNullParameter(declarationDescriptor0, "containingDeclaration");
        Intrinsics.checkNotNullParameter(annotations0, "annotations");
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(descriptorVisibility0, "visibility");
        Intrinsics.checkNotNullParameter(protoBuf$TypeAlias0, "proto");
        Intrinsics.checkNotNullParameter(nameResolver0, "nameResolver");
        Intrinsics.checkNotNullParameter(typeTable0, "typeTable");
        Intrinsics.checkNotNullParameter(versionRequirementTable0, "versionRequirementTable");
        Intrinsics.checkNotNullExpressionValue(SourceElement.NO_SOURCE, "NO_SOURCE");
        super(declarationDescriptor0, annotations0, name0, SourceElement.NO_SOURCE, descriptorVisibility0);
        this.storageManager = storageManager0;
        this.proto = protoBuf$TypeAlias0;
        this.nameResolver = nameResolver0;
        this.typeTable = typeTable0;
        this.versionRequirementTable = versionRequirementTable0;
        this.containerSource = deserializedContainerSource0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor
    public ClassDescriptor getClassDescriptor() {
        if(KotlinTypeKt.isError(this.getExpandedType())) {
            return null;
        }
        ClassifierDescriptor classifierDescriptor0 = this.getExpandedType().getConstructor().getDeclarationDescriptor();
        return classifierDescriptor0 instanceof ClassDescriptor ? ((ClassDescriptor)classifierDescriptor0) : null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor
    public DeserializedContainerSource getContainerSource() {
        return this.containerSource;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor
    public SimpleType getDefaultType() {
        SimpleType simpleType0 = this.defaultTypeImpl;
        if(simpleType0 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("defaultTypeImpl");
            return null;
        }
        return simpleType0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor
    public SimpleType getExpandedType() {
        SimpleType simpleType0 = this.expandedType;
        if(simpleType0 != null) {
            return simpleType0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("expandedType");
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor
    public NameResolver getNameResolver() {
        return this.nameResolver;
    }

    public TypeAlias getProto() {
        return this.proto;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor
    public MessageLite getProto() {
        return this.getProto();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractTypeAliasDescriptor
    protected StorageManager getStorageManager() {
        return this.storageManager;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractTypeAliasDescriptor
    protected List getTypeConstructorTypeParameters() {
        List list0 = this.typeConstructorParameters;
        if(list0 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("typeConstructorParameters");
            return null;
        }
        return list0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor
    public TypeTable getTypeTable() {
        return this.typeTable;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor
    public SimpleType getUnderlyingType() {
        SimpleType simpleType0 = this.underlyingType;
        if(simpleType0 != null) {
            return simpleType0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("underlyingType");
        return null;
    }

    public VersionRequirementTable getVersionRequirementTable() {
        return this.versionRequirementTable;
    }

    public final void initialize(List list0, SimpleType simpleType0, SimpleType simpleType1) {
        Intrinsics.checkNotNullParameter(list0, "declaredTypeParameters");
        Intrinsics.checkNotNullParameter(simpleType0, "underlyingType");
        Intrinsics.checkNotNullParameter(simpleType1, "expandedType");
        this.initialize(list0);
        this.underlyingType = simpleType0;
        this.expandedType = simpleType1;
        this.typeConstructorParameters = TypeParameterUtilsKt.computeConstructorTypeParameters(this);
        this.defaultTypeImpl = this.computeDefaultType();
        this.constructors = this.getTypeAliasConstructors();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.Substitutable
    public DeclarationDescriptorNonRoot substitute(TypeSubstitutor typeSubstitutor0) {
        return this.substitute(typeSubstitutor0);
    }

    public TypeAliasDescriptor substitute(TypeSubstitutor typeSubstitutor0) {
        Intrinsics.checkNotNullParameter(typeSubstitutor0, "substitutor");
        if(typeSubstitutor0.isEmpty()) {
            return this;
        }
        DeclarationDescriptor declarationDescriptor0 = this.getContainingDeclaration();
        Intrinsics.checkNotNullExpressionValue(declarationDescriptor0, "containingDeclaration");
        Annotations annotations0 = this.getAnnotations();
        Intrinsics.checkNotNullExpressionValue(annotations0, "annotations");
        Name name0 = this.getName();
        Intrinsics.checkNotNullExpressionValue(name0, "name");
        DeserializedTypeAliasDescriptor deserializedTypeAliasDescriptor0 = new DeserializedTypeAliasDescriptor(this.getStorageManager(), declarationDescriptor0, annotations0, name0, this.getVisibility(), this.getProto(), this.getNameResolver(), this.getTypeTable(), this.getVersionRequirementTable(), this.getContainerSource());
        List list0 = this.getDeclaredTypeParameters();
        KotlinType kotlinType0 = typeSubstitutor0.safeSubstitute(this.getUnderlyingType(), Variance.INVARIANT);
        Intrinsics.checkNotNullExpressionValue(kotlinType0, "substitutor.safeSubstitu…Type, Variance.INVARIANT)");
        SimpleType simpleType0 = TypeSubstitutionKt.asSimpleType(kotlinType0);
        KotlinType kotlinType1 = typeSubstitutor0.safeSubstitute(this.getExpandedType(), Variance.INVARIANT);
        Intrinsics.checkNotNullExpressionValue(kotlinType1, "substitutor.safeSubstitu…Type, Variance.INVARIANT)");
        deserializedTypeAliasDescriptor0.initialize(list0, simpleType0, TypeSubstitutionKt.asSimpleType(kotlinType1));
        return deserializedTypeAliasDescriptor0;
    }
}

