package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.Set;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames.FqNames;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderKt;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor;

public final class ClassDeserializer {
    static final class ClassKey {
        private final ClassData classData;
        private final ClassId classId;

        public ClassKey(ClassId classId0, ClassData classData0) {
            Intrinsics.checkNotNullParameter(classId0, "classId");
            super();
            this.classId = classId0;
            this.classData = classData0;
        }

        // 去混淆评级： 低(20)
        @Override
        public boolean equals(Object object0) {
            return object0 instanceof ClassKey && Intrinsics.areEqual(this.classId, ((ClassKey)object0).classId);
        }

        public final ClassData getClassData() {
            return this.classData;
        }

        public final ClassId getClassId() {
            return this.classId;
        }

        @Override
        public int hashCode() {
            return this.classId.hashCode();
        }
    }

    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final Set getBLACK_LIST() {
            return ClassDeserializer.BLACK_LIST;
        }
    }

    private static final Set BLACK_LIST;
    public static final Companion Companion;
    private final Function1 classes;
    private final DeserializationComponents components;

    static {
        ClassDeserializer.Companion = new Companion(null);
        ClassDeserializer.BLACK_LIST = SetsKt.setOf(ClassId.topLevel(FqNames.cloneable.toSafe()));
    }

    public ClassDeserializer(DeserializationComponents deserializationComponents0) {
        Intrinsics.checkNotNullParameter(deserializationComponents0, "components");
        super();
        this.components = deserializationComponents0;
        this.classes = deserializationComponents0.getStorageManager().createMemoizedFunctionWithNullableValues(new Function1() {
            {
                ClassDeserializer.this = classDeserializer0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((ClassKey)object0));
            }

            public final ClassDescriptor invoke(ClassKey classDeserializer$ClassKey0) {
                Intrinsics.checkNotNullParameter(classDeserializer$ClassKey0, "key");
                return ClassDeserializer.access$createClass(ClassDeserializer.this, classDeserializer$ClassKey0);
            }
        });
    }

    public static final ClassDescriptor access$createClass(ClassDeserializer classDeserializer0, ClassKey classDeserializer$ClassKey0) {
        return classDeserializer0.createClass(classDeserializer$ClassKey0);
    }

    private final ClassDescriptor createClass(ClassKey classDeserializer$ClassKey0) {
        ClassId classId0 = classDeserializer$ClassKey0.getClassId();
        for(Object object0: this.components.getFictitiousClassDescriptorFactories()) {
            ClassDescriptor classDescriptor0 = ((ClassDescriptorFactory)object0).createClass(classId0);
            if(classDescriptor0 != null) {
                return classDescriptor0;
            }
            if(false) {
                break;
            }
        }
        if(ClassDeserializer.BLACK_LIST.contains(classId0)) {
            return null;
        }
        ClassData classData0 = classDeserializer$ClassKey0.getClassData();
        if(classData0 == null) {
            classData0 = this.components.getClassDataFinder().findClassData(classId0);
            if(classData0 == null) {
                return null;
            }
        }
        NameResolver nameResolver0 = classData0.component1();
        Class protoBuf$Class0 = classData0.component2();
        BinaryVersion binaryVersion0 = classData0.component3();
        SourceElement sourceElement0 = classData0.component4();
        ClassId classId1 = classId0.getOuterClassId();
        if(classId1 != null) {
            ClassDescriptor classDescriptor1 = ClassDeserializer.deserializeClass$default(this, classId1, null, 2, null);
            DeserializedClassDescriptor deserializedClassDescriptor0 = classDescriptor1 instanceof DeserializedClassDescriptor ? ((DeserializedClassDescriptor)classDescriptor1) : null;
            if(deserializedClassDescriptor0 == null) {
                return null;
            }
            Name name0 = classId0.getShortClassName();
            Intrinsics.checkNotNullExpressionValue(name0, "classId.shortClassName");
            return !deserializedClassDescriptor0.hasNestedClass$deserialization(name0) ? null : new DeserializedClassDescriptor(deserializedClassDescriptor0.getC(), protoBuf$Class0, nameResolver0, binaryVersion0, sourceElement0);
        }
        FqName fqName0 = classId0.getPackageFqName();
        Intrinsics.checkNotNullExpressionValue(fqName0, "classId.packageFqName");
        for(Object object1: PackageFragmentProviderKt.packageFragments(this.components.getPackageFragmentProvider(), fqName0)) {
            if(!(((PackageFragmentDescriptor)object1) instanceof DeserializedPackageFragment)) {
                goto label_41;
            }
            Name name1 = classId0.getShortClassName();
            Intrinsics.checkNotNullExpressionValue(name1, "classId.shortClassName");
            if(!((DeserializedPackageFragment)(((PackageFragmentDescriptor)object1))).hasTopLevelClass(name1)) {
                continue;
            }
            goto label_41;
        }
        object1 = null;
    label_41:
        if(((PackageFragmentDescriptor)object1) == null) {
            return null;
        }
        TypeTable protoBuf$TypeTable0 = protoBuf$Class0.getTypeTable();
        Intrinsics.checkNotNullExpressionValue(protoBuf$TypeTable0, "classProto.typeTable");
        kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable typeTable0 = new kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable(protoBuf$TypeTable0);
        VersionRequirementTable protoBuf$VersionRequirementTable0 = protoBuf$Class0.getVersionRequirementTable();
        Intrinsics.checkNotNullExpressionValue(protoBuf$VersionRequirementTable0, "classProto.versionRequirementTable");
        kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable versionRequirementTable0 = kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable.Companion.create(protoBuf$VersionRequirementTable0);
        return new DeserializedClassDescriptor(this.components.createContext(((PackageFragmentDescriptor)object1), nameResolver0, typeTable0, versionRequirementTable0, binaryVersion0, null), protoBuf$Class0, nameResolver0, binaryVersion0, sourceElement0);
    }

    public final ClassDescriptor deserializeClass(ClassId classId0, ClassData classData0) {
        Intrinsics.checkNotNullParameter(classId0, "classId");
        ClassKey classDeserializer$ClassKey0 = new ClassKey(classId0, classData0);
        return (ClassDescriptor)this.classes.invoke(classDeserializer$ClassKey0);
    }

    public static ClassDescriptor deserializeClass$default(ClassDeserializer classDeserializer0, ClassId classId0, ClassData classData0, int v, Object object0) {
        if((v & 2) != 0) {
            classData0 = null;
        }
        return classDeserializer0.deserializeClass(classId0, classData0);
    }
}

