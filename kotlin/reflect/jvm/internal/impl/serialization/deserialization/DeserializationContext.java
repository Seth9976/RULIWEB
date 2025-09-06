package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionSpecificBehaviorKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;

public final class DeserializationContext {
    private final DeserializationComponents components;
    private final DeserializedContainerSource containerSource;
    private final DeclarationDescriptor containingDeclaration;
    private final MemberDeserializer memberDeserializer;
    private final BinaryVersion metadataVersion;
    private final NameResolver nameResolver;
    private final TypeDeserializer typeDeserializer;
    private final TypeTable typeTable;
    private final VersionRequirementTable versionRequirementTable;

    public DeserializationContext(DeserializationComponents deserializationComponents0, NameResolver nameResolver0, DeclarationDescriptor declarationDescriptor0, TypeTable typeTable0, VersionRequirementTable versionRequirementTable0, BinaryVersion binaryVersion0, DeserializedContainerSource deserializedContainerSource0, TypeDeserializer typeDeserializer0, List list0) {
        Intrinsics.checkNotNullParameter(deserializationComponents0, "components");
        String s1;
        Intrinsics.checkNotNullParameter(nameResolver0, "nameResolver");
        Intrinsics.checkNotNullParameter(declarationDescriptor0, "containingDeclaration");
        Intrinsics.checkNotNullParameter(typeTable0, "typeTable");
        Intrinsics.checkNotNullParameter(versionRequirementTable0, "versionRequirementTable");
        Intrinsics.checkNotNullParameter(binaryVersion0, "metadataVersion");
        Intrinsics.checkNotNullParameter(list0, "typeParameters");
        super();
        this.components = deserializationComponents0;
        this.nameResolver = nameResolver0;
        this.containingDeclaration = declarationDescriptor0;
        this.typeTable = typeTable0;
        this.versionRequirementTable = versionRequirementTable0;
        this.metadataVersion = binaryVersion0;
        this.containerSource = deserializedContainerSource0;
        String s = "Deserializer for \"" + declarationDescriptor0.getName() + '\"';
        if(deserializedContainerSource0 == null) {
            s1 = "[container not found]";
        }
        else {
            s1 = deserializedContainerSource0.getPresentableString();
            if(s1 == null) {
                s1 = "[container not found]";
            }
        }
        this.typeDeserializer = new TypeDeserializer(this, typeDeserializer0, list0, s, s1);
        this.memberDeserializer = new MemberDeserializer(this);
    }

    public final DeserializationContext childContext(DeclarationDescriptor declarationDescriptor0, List list0, NameResolver nameResolver0, TypeTable typeTable0, VersionRequirementTable versionRequirementTable0, BinaryVersion binaryVersion0) {
        Intrinsics.checkNotNullParameter(declarationDescriptor0, "descriptor");
        Intrinsics.checkNotNullParameter(list0, "typeParameterProtos");
        Intrinsics.checkNotNullParameter(nameResolver0, "nameResolver");
        Intrinsics.checkNotNullParameter(typeTable0, "typeTable");
        Intrinsics.checkNotNullParameter(versionRequirementTable0, "versionRequirementTable");
        Intrinsics.checkNotNullParameter(binaryVersion0, "metadataVersion");
        return VersionSpecificBehaviorKt.isVersionRequirementTableWrittenCorrectly(binaryVersion0) ? new DeserializationContext(this.components, nameResolver0, declarationDescriptor0, typeTable0, versionRequirementTable0, binaryVersion0, this.containerSource, this.typeDeserializer, list0) : new DeserializationContext(this.components, nameResolver0, declarationDescriptor0, typeTable0, this.versionRequirementTable, binaryVersion0, this.containerSource, this.typeDeserializer, list0);
    }

    public static DeserializationContext childContext$default(DeserializationContext deserializationContext0, DeclarationDescriptor declarationDescriptor0, List list0, NameResolver nameResolver0, TypeTable typeTable0, VersionRequirementTable versionRequirementTable0, BinaryVersion binaryVersion0, int v, Object object0) {
        if((v & 4) != 0) {
            nameResolver0 = deserializationContext0.nameResolver;
        }
        if((v & 8) != 0) {
            typeTable0 = deserializationContext0.typeTable;
        }
        if((v & 16) != 0) {
            versionRequirementTable0 = deserializationContext0.versionRequirementTable;
        }
        if((v & 0x20) != 0) {
            binaryVersion0 = deserializationContext0.metadataVersion;
        }
        return deserializationContext0.childContext(declarationDescriptor0, list0, nameResolver0, typeTable0, versionRequirementTable0, binaryVersion0);
    }

    public final DeserializationComponents getComponents() {
        return this.components;
    }

    public final DeserializedContainerSource getContainerSource() {
        return this.containerSource;
    }

    public final DeclarationDescriptor getContainingDeclaration() {
        return this.containingDeclaration;
    }

    public final MemberDeserializer getMemberDeserializer() {
        return this.memberDeserializer;
    }

    public final NameResolver getNameResolver() {
        return this.nameResolver;
    }

    public final StorageManager getStorageManager() {
        return this.components.getStorageManager();
    }

    public final TypeDeserializer getTypeDeserializer() {
        return this.typeDeserializer;
    }

    public final TypeTable getTypeTable() {
        return this.typeTable;
    }

    public final VersionRequirementTable getVersionRequirementTable() {
        return this.versionRequirementTable;
    }
}

