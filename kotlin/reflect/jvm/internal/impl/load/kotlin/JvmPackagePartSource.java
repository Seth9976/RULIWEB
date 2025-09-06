package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceFile;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoBufUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.IncompatibleVersionErrorData;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerAbiStability;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource;
import kotlin.text.StringsKt;

public final class JvmPackagePartSource implements DeserializedContainerSource {
    private final DeserializedContainerAbiStability abiStability;
    private final JvmClassName className;
    private final JvmClassName facadeClassName;
    private final IncompatibleVersionErrorData incompatibility;
    private final boolean isPreReleaseInvisible;
    private final KotlinJvmBinaryClass knownJvmBinaryClass;
    private final String moduleName;

    public JvmPackagePartSource(KotlinJvmBinaryClass kotlinJvmBinaryClass0, Package protoBuf$Package0, NameResolver nameResolver0, IncompatibleVersionErrorData incompatibleVersionErrorData0, boolean z, DeserializedContainerAbiStability deserializedContainerAbiStability0) {
        Intrinsics.checkNotNullParameter(kotlinJvmBinaryClass0, "kotlinClass");
        Intrinsics.checkNotNullParameter(protoBuf$Package0, "packageProto");
        Intrinsics.checkNotNullParameter(nameResolver0, "nameResolver");
        Intrinsics.checkNotNullParameter(deserializedContainerAbiStability0, "abiStability");
        JvmClassName jvmClassName0 = JvmClassName.byClassId(kotlinJvmBinaryClass0.getClassId());
        Intrinsics.checkNotNullExpressionValue(jvmClassName0, "byClassId(kotlinClass.classId)");
        String s = kotlinJvmBinaryClass0.getClassHeader().getMultifileClassName();
        this(jvmClassName0, (s == null || s.length() <= 0 ? null : JvmClassName.byInternalName(s)), protoBuf$Package0, nameResolver0, incompatibleVersionErrorData0, z, deserializedContainerAbiStability0, kotlinJvmBinaryClass0);
    }

    public JvmPackagePartSource(JvmClassName jvmClassName0, JvmClassName jvmClassName1, Package protoBuf$Package0, NameResolver nameResolver0, IncompatibleVersionErrorData incompatibleVersionErrorData0, boolean z, DeserializedContainerAbiStability deserializedContainerAbiStability0, KotlinJvmBinaryClass kotlinJvmBinaryClass0) {
        Intrinsics.checkNotNullParameter(jvmClassName0, "className");
        String s;
        Intrinsics.checkNotNullParameter(protoBuf$Package0, "packageProto");
        Intrinsics.checkNotNullParameter(nameResolver0, "nameResolver");
        Intrinsics.checkNotNullParameter(deserializedContainerAbiStability0, "abiStability");
        super();
        this.className = jvmClassName0;
        this.facadeClassName = jvmClassName1;
        this.incompatibility = incompatibleVersionErrorData0;
        this.isPreReleaseInvisible = z;
        this.abiStability = deserializedContainerAbiStability0;
        this.knownJvmBinaryClass = kotlinJvmBinaryClass0;
        Intrinsics.checkNotNullExpressionValue(JvmProtoBuf.packageModuleName, "packageModuleName");
        Integer integer0 = (Integer)ProtoBufUtilKt.getExtensionOrNull(protoBuf$Package0, JvmProtoBuf.packageModuleName);
        if(integer0 == null) {
            s = "main";
        }
        else {
            s = nameResolver0.getString(integer0.intValue());
            if(s == null) {
                s = "main";
            }
        }
        this.moduleName = s;
    }

    public final ClassId getClassId() {
        return new ClassId(this.getClassName().getPackageFqName(), this.getSimpleName());
    }

    public JvmClassName getClassName() {
        return this.className;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.SourceElement
    public SourceFile getContainingFile() {
        Intrinsics.checkNotNullExpressionValue(SourceFile.NO_SOURCE_FILE, "NO_SOURCE_FILE");
        return SourceFile.NO_SOURCE_FILE;
    }

    public JvmClassName getFacadeClassName() {
        return this.facadeClassName;
    }

    public final KotlinJvmBinaryClass getKnownJvmBinaryClass() {
        return this.knownJvmBinaryClass;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource
    public String getPresentableString() {
        return "Class \'" + this.getClassId().asSingleFqName().asString() + '\'';
    }

    public final Name getSimpleName() {
        String s = this.getClassName().getInternalName();
        Intrinsics.checkNotNullExpressionValue(s, "className.internalName");
        Name name0 = Name.identifier(StringsKt.substringAfterLast$default(s, '/', null, 2, null));
        Intrinsics.checkNotNullExpressionValue(name0, "identifier(className.int….substringAfterLast(\'/\'))");
        return name0;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + this.getClassName();
    }
}

