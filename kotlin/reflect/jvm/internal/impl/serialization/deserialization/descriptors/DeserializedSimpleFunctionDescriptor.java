package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;

public final class DeserializedSimpleFunctionDescriptor extends SimpleFunctionDescriptorImpl implements DeserializedCallableMemberDescriptor {
    private final DeserializedContainerSource containerSource;
    private final NameResolver nameResolver;
    private final Function proto;
    private final TypeTable typeTable;
    private final VersionRequirementTable versionRequirementTable;

    public DeserializedSimpleFunctionDescriptor(DeclarationDescriptor declarationDescriptor0, SimpleFunctionDescriptor simpleFunctionDescriptor0, Annotations annotations0, Name name0, Kind callableMemberDescriptor$Kind0, Function protoBuf$Function0, NameResolver nameResolver0, TypeTable typeTable0, VersionRequirementTable versionRequirementTable0, DeserializedContainerSource deserializedContainerSource0, SourceElement sourceElement0) {
        Intrinsics.checkNotNullParameter(declarationDescriptor0, "containingDeclaration");
        Intrinsics.checkNotNullParameter(annotations0, "annotations");
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(callableMemberDescriptor$Kind0, "kind");
        Intrinsics.checkNotNullParameter(protoBuf$Function0, "proto");
        Intrinsics.checkNotNullParameter(nameResolver0, "nameResolver");
        Intrinsics.checkNotNullParameter(typeTable0, "typeTable");
        Intrinsics.checkNotNullParameter(versionRequirementTable0, "versionRequirementTable");
        super(declarationDescriptor0, simpleFunctionDescriptor0, annotations0, name0, callableMemberDescriptor$Kind0, (sourceElement0 == null ? SourceElement.NO_SOURCE : sourceElement0));
        this.proto = protoBuf$Function0;
        this.nameResolver = nameResolver0;
        this.typeTable = typeTable0;
        this.versionRequirementTable = versionRequirementTable0;
        this.containerSource = deserializedContainerSource0;
    }

    public DeserializedSimpleFunctionDescriptor(DeclarationDescriptor declarationDescriptor0, SimpleFunctionDescriptor simpleFunctionDescriptor0, Annotations annotations0, Name name0, Kind callableMemberDescriptor$Kind0, Function protoBuf$Function0, NameResolver nameResolver0, TypeTable typeTable0, VersionRequirementTable versionRequirementTable0, DeserializedContainerSource deserializedContainerSource0, SourceElement sourceElement0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        this(declarationDescriptor0, simpleFunctionDescriptor0, annotations0, name0, callableMemberDescriptor$Kind0, protoBuf$Function0, nameResolver0, typeTable0, versionRequirementTable0, deserializedContainerSource0, ((v & 0x400) == 0 ? sourceElement0 : null));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl
    protected FunctionDescriptorImpl createSubstitutedCopy(DeclarationDescriptor declarationDescriptor0, FunctionDescriptor functionDescriptor0, Kind callableMemberDescriptor$Kind0, Name name0, Annotations annotations0, SourceElement sourceElement0) {
        Name name2;
        Intrinsics.checkNotNullParameter(declarationDescriptor0, "newOwner");
        Intrinsics.checkNotNullParameter(callableMemberDescriptor$Kind0, "kind");
        Intrinsics.checkNotNullParameter(annotations0, "annotations");
        Intrinsics.checkNotNullParameter(sourceElement0, "source");
        if(name0 == null) {
            Name name1 = this.getName();
            Intrinsics.checkNotNullExpressionValue(name1, "name");
            name2 = name1;
        }
        else {
            name2 = name0;
        }
        DeserializedSimpleFunctionDescriptor deserializedSimpleFunctionDescriptor0 = new DeserializedSimpleFunctionDescriptor(declarationDescriptor0, ((SimpleFunctionDescriptor)functionDescriptor0), annotations0, name2, callableMemberDescriptor$Kind0, this.getProto(), this.getNameResolver(), this.getTypeTable(), this.getVersionRequirementTable(), this.getContainerSource(), sourceElement0);
        deserializedSimpleFunctionDescriptor0.setHasStableParameterNames(this.hasStableParameterNames());
        return deserializedSimpleFunctionDescriptor0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor
    public DeserializedContainerSource getContainerSource() {
        return this.containerSource;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor
    public NameResolver getNameResolver() {
        return this.nameResolver;
    }

    public Function getProto() {
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
}

