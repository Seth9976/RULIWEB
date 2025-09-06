package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class.Kind;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;

public abstract class ProtoContainer {
    public static final class Class extends ProtoContainer {
        private final ClassId classId;
        private final kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class classProto;
        private final boolean isInner;
        private final Kind kind;
        private final Class outerClass;

        public Class(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class protoBuf$Class0, NameResolver nameResolver0, TypeTable typeTable0, SourceElement sourceElement0, Class protoContainer$Class0) {
            Intrinsics.checkNotNullParameter(protoBuf$Class0, "classProto");
            Intrinsics.checkNotNullParameter(nameResolver0, "nameResolver");
            Intrinsics.checkNotNullParameter(typeTable0, "typeTable");
            super(nameResolver0, typeTable0, sourceElement0, null);
            this.classProto = protoBuf$Class0;
            this.outerClass = protoContainer$Class0;
            this.classId = NameResolverUtilKt.getClassId(nameResolver0, protoBuf$Class0.getFqName());
            Kind protoBuf$Class$Kind0 = (Kind)Flags.CLASS_KIND.get(protoBuf$Class0.getFlags());
            if(protoBuf$Class$Kind0 == null) {
                protoBuf$Class$Kind0 = Kind.CLASS;
            }
            this.kind = protoBuf$Class$Kind0;
            Boolean boolean0 = Flags.IS_INNER.get(protoBuf$Class0.getFlags());
            Intrinsics.checkNotNullExpressionValue(boolean0, "IS_INNER.get(classProto.flags)");
            this.isInner = boolean0.booleanValue();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer
        public FqName debugFqName() {
            FqName fqName0 = this.classId.asSingleFqName();
            Intrinsics.checkNotNullExpressionValue(fqName0, "classId.asSingleFqName()");
            return fqName0;
        }

        public final ClassId getClassId() {
            return this.classId;
        }

        public final kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class getClassProto() {
            return this.classProto;
        }

        public final Kind getKind() {
            return this.kind;
        }

        public final Class getOuterClass() {
            return this.outerClass;
        }

        public final boolean isInner() {
            return this.isInner;
        }
    }

    public static final class Package extends ProtoContainer {
        private final FqName fqName;

        public Package(FqName fqName0, NameResolver nameResolver0, TypeTable typeTable0, SourceElement sourceElement0) {
            Intrinsics.checkNotNullParameter(fqName0, "fqName");
            Intrinsics.checkNotNullParameter(nameResolver0, "nameResolver");
            Intrinsics.checkNotNullParameter(typeTable0, "typeTable");
            super(nameResolver0, typeTable0, sourceElement0, null);
            this.fqName = fqName0;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer
        public FqName debugFqName() {
            return this.fqName;
        }
    }

    private final NameResolver nameResolver;
    private final SourceElement source;
    private final TypeTable typeTable;

    private ProtoContainer(NameResolver nameResolver0, TypeTable typeTable0, SourceElement sourceElement0) {
        this.nameResolver = nameResolver0;
        this.typeTable = typeTable0;
        this.source = sourceElement0;
    }

    public ProtoContainer(NameResolver nameResolver0, TypeTable typeTable0, SourceElement sourceElement0, DefaultConstructorMarker defaultConstructorMarker0) {
        this(nameResolver0, typeTable0, sourceElement0);
    }

    public abstract FqName debugFqName();

    public final NameResolver getNameResolver() {
        return this.nameResolver;
    }

    public final SourceElement getSource() {
        return this.source;
    }

    public final TypeTable getTypeTable() {
        return this.typeTable;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + this.debugFqName();
    }
}

