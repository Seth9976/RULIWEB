package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;

public final class ClassData {
    private final Class classProto;
    private final BinaryVersion metadataVersion;
    private final NameResolver nameResolver;
    private final SourceElement sourceElement;

    public ClassData(NameResolver nameResolver0, Class protoBuf$Class0, BinaryVersion binaryVersion0, SourceElement sourceElement0) {
        Intrinsics.checkNotNullParameter(nameResolver0, "nameResolver");
        Intrinsics.checkNotNullParameter(protoBuf$Class0, "classProto");
        Intrinsics.checkNotNullParameter(binaryVersion0, "metadataVersion");
        Intrinsics.checkNotNullParameter(sourceElement0, "sourceElement");
        super();
        this.nameResolver = nameResolver0;
        this.classProto = protoBuf$Class0;
        this.metadataVersion = binaryVersion0;
        this.sourceElement = sourceElement0;
    }

    public final NameResolver component1() {
        return this.nameResolver;
    }

    public final Class component2() {
        return this.classProto;
    }

    public final BinaryVersion component3() {
        return this.metadataVersion;
    }

    public final SourceElement component4() {
        return this.sourceElement;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof ClassData)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.nameResolver, ((ClassData)object0).nameResolver)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.classProto, ((ClassData)object0).classProto)) {
            return false;
        }
        return Intrinsics.areEqual(this.metadataVersion, ((ClassData)object0).metadataVersion) ? Intrinsics.areEqual(this.sourceElement, ((ClassData)object0).sourceElement) : false;
    }

    @Override
    public int hashCode() {
        return ((this.nameResolver.hashCode() * 0x1F + this.classProto.hashCode()) * 0x1F + this.metadataVersion.hashCode()) * 0x1F + this.sourceElement.hashCode();
    }

    @Override
    public String toString() {
        return "ClassData(nameResolver=" + this.nameResolver + ", classProto=" + this.classProto + ", metadataVersion=" + this.metadataVersion + ", sourceElement=" + this.sourceElement + ')';
    }
}

