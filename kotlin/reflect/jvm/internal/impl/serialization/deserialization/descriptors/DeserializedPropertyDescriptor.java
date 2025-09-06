package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;

public final class DeserializedPropertyDescriptor extends PropertyDescriptorImpl implements DeserializedCallableMemberDescriptor {
    private final DeserializedContainerSource containerSource;
    private final NameResolver nameResolver;
    private final Property proto;
    private final TypeTable typeTable;
    private final VersionRequirementTable versionRequirementTable;

    public DeserializedPropertyDescriptor(DeclarationDescriptor declarationDescriptor0, PropertyDescriptor propertyDescriptor0, Annotations annotations0, Modality modality0, DescriptorVisibility descriptorVisibility0, boolean z, Name name0, Kind callableMemberDescriptor$Kind0, boolean z1, boolean z2, boolean z3, boolean z4, boolean z5, Property protoBuf$Property0, NameResolver nameResolver0, TypeTable typeTable0, VersionRequirementTable versionRequirementTable0, DeserializedContainerSource deserializedContainerSource0) {
        Intrinsics.checkNotNullParameter(declarationDescriptor0, "containingDeclaration");
        Intrinsics.checkNotNullParameter(annotations0, "annotations");
        Intrinsics.checkNotNullParameter(modality0, "modality");
        Intrinsics.checkNotNullParameter(descriptorVisibility0, "visibility");
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(callableMemberDescriptor$Kind0, "kind");
        Intrinsics.checkNotNullParameter(protoBuf$Property0, "proto");
        Intrinsics.checkNotNullParameter(nameResolver0, "nameResolver");
        Intrinsics.checkNotNullParameter(typeTable0, "typeTable");
        Intrinsics.checkNotNullParameter(versionRequirementTable0, "versionRequirementTable");
        super(declarationDescriptor0, propertyDescriptor0, annotations0, modality0, descriptorVisibility0, z, name0, callableMemberDescriptor$Kind0, SourceElement.NO_SOURCE, z1, z2, z5, false, z3, z4);
        this.proto = protoBuf$Property0;
        this.nameResolver = nameResolver0;
        this.typeTable = typeTable0;
        this.versionRequirementTable = versionRequirementTable0;
        this.containerSource = deserializedContainerSource0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyDescriptorImpl
    protected PropertyDescriptorImpl createSubstitutedCopy(DeclarationDescriptor declarationDescriptor0, Modality modality0, DescriptorVisibility descriptorVisibility0, PropertyDescriptor propertyDescriptor0, Kind callableMemberDescriptor$Kind0, Name name0, SourceElement sourceElement0) {
        Intrinsics.checkNotNullParameter(declarationDescriptor0, "newOwner");
        Intrinsics.checkNotNullParameter(modality0, "newModality");
        Intrinsics.checkNotNullParameter(descriptorVisibility0, "newVisibility");
        Intrinsics.checkNotNullParameter(callableMemberDescriptor$Kind0, "kind");
        Intrinsics.checkNotNullParameter(name0, "newName");
        Intrinsics.checkNotNullParameter(sourceElement0, "source");
        return new DeserializedPropertyDescriptor(declarationDescriptor0, propertyDescriptor0, this.getAnnotations(), modality0, descriptorVisibility0, this.isVar(), name0, callableMemberDescriptor$Kind0, this.isLateInit(), this.isConst(), this.isExternal(), this.isDelegated(), this.isExpect(), this.getProto(), this.getNameResolver(), this.getTypeTable(), this.getVersionRequirementTable(), this.getContainerSource());
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor
    public DeserializedContainerSource getContainerSource() {
        return this.containerSource;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor
    public NameResolver getNameResolver() {
        return this.nameResolver;
    }

    public Property getProto() {
        return this.proto;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor
    public MessageLite getProto() {
        return this.getProto();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor
    public TypeTable getTypeTable() {
        return this.typeTable;
    }

    public VersionRequirementTable getVersionRequirementTable() {
        return this.versionRequirementTable;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isExternal() {
        Boolean boolean0 = Flags.IS_EXTERNAL_PROPERTY.get(this.getProto().getFlags());
        Intrinsics.checkNotNullExpressionValue(boolean0, "IS_EXTERNAL_PROPERTY.get(proto.flags)");
        return boolean0.booleanValue();
    }
}

