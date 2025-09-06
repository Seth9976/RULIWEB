package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.Collection;
import java.util.Set;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader.Kind;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmNameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ClassData;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.IncompatibleVersionErrorData;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerAbiStability;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedPackageMemberScope;
import kotlin.reflect.jvm.internal.impl.utils.DeserializationHelpersKt;

public final class DeserializedDescriptorResolver {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final JvmMetadataVersion getKOTLIN_1_3_RC_METADATA_VERSION$descriptors_jvm() {
            return DeserializedDescriptorResolver.KOTLIN_1_3_RC_METADATA_VERSION;
        }
    }

    public static final Companion Companion;
    private static final JvmMetadataVersion KOTLIN_1_1_EAP_METADATA_VERSION;
    private static final JvmMetadataVersion KOTLIN_1_3_M1_METADATA_VERSION;
    private static final JvmMetadataVersion KOTLIN_1_3_RC_METADATA_VERSION;
    private static final Set KOTLIN_CLASS;
    private static final Set KOTLIN_FILE_FACADE_OR_MULTIFILE_CLASS_PART;
    public DeserializationComponents components;

    static {
        DeserializedDescriptorResolver.Companion = new Companion(null);
        DeserializedDescriptorResolver.KOTLIN_CLASS = SetsKt.setOf(Kind.CLASS);
        DeserializedDescriptorResolver.KOTLIN_FILE_FACADE_OR_MULTIFILE_CLASS_PART = SetsKt.setOf(new Kind[]{Kind.FILE_FACADE, Kind.MULTIFILE_CLASS_PART});
        DeserializedDescriptorResolver.KOTLIN_1_1_EAP_METADATA_VERSION = new JvmMetadataVersion(new int[]{1, 1, 2});
        DeserializedDescriptorResolver.KOTLIN_1_3_M1_METADATA_VERSION = new JvmMetadataVersion(new int[]{1, 1, 11});
        DeserializedDescriptorResolver.KOTLIN_1_3_RC_METADATA_VERSION = new JvmMetadataVersion(new int[]{1, 1, 13});
    }

    public final MemberScope createKotlinPackagePartScope(PackageFragmentDescriptor packageFragmentDescriptor0, KotlinJvmBinaryClass kotlinJvmBinaryClass0) {
        Pair pair0;
        Intrinsics.checkNotNullParameter(packageFragmentDescriptor0, "descriptor");
        Intrinsics.checkNotNullParameter(kotlinJvmBinaryClass0, "kotlinClass");
        String[] arr_s = this.readData(kotlinJvmBinaryClass0, DeserializedDescriptorResolver.KOTLIN_FILE_FACADE_OR_MULTIFILE_CLASS_PART);
        if(arr_s == null) {
            return null;
        }
        String[] arr_s1 = kotlinJvmBinaryClass0.getClassHeader().getStrings();
        if(arr_s1 == null) {
            return null;
        }
        try {
            try {
                pair0 = JvmProtoBufUtil.readPackageDataFrom(arr_s, arr_s1);
            }
            catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                throw new IllegalStateException("Could not read data from " + kotlinJvmBinaryClass0.getLocation(), invalidProtocolBufferException0);
            }
        }
        catch(Throwable throwable0) {
            if(this.getSkipMetadataVersionCheck() || kotlinJvmBinaryClass0.getClassHeader().getMetadataVersion().isCompatible(this.getOwnMetadataVersion())) {
                throw throwable0;
            }
            pair0 = null;
        }
        if(pair0 == null) {
            return null;
        }
        Object object0 = pair0.component2();
        NameResolver nameResolver0 = (JvmNameResolver)pair0.component1();
        JvmPackagePartSource jvmPackagePartSource0 = new JvmPackagePartSource(kotlinJvmBinaryClass0, ((Package)object0), nameResolver0, this.getIncompatibility(kotlinJvmBinaryClass0), this.isPreReleaseInvisible(kotlinJvmBinaryClass0), this.getAbiStability(kotlinJvmBinaryClass0));
        return new DeserializedPackageMemberScope(packageFragmentDescriptor0, ((Package)object0), nameResolver0, kotlinJvmBinaryClass0.getClassHeader().getMetadataVersion(), jvmPackagePartSource0, this.getComponents(), "scope for " + jvmPackagePartSource0 + " in " + packageFragmentDescriptor0, kotlin.reflect.jvm.internal.impl.load.kotlin.DeserializedDescriptorResolver.createKotlinPackagePartScope.2.INSTANCE);

        final class kotlin.reflect.jvm.internal.impl.load.kotlin.DeserializedDescriptorResolver.createKotlinPackagePartScope.2 extends Lambda implements Function0 {
            public static final kotlin.reflect.jvm.internal.impl.load.kotlin.DeserializedDescriptorResolver.createKotlinPackagePartScope.2 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.load.kotlin.DeserializedDescriptorResolver.createKotlinPackagePartScope.2.INSTANCE = new kotlin.reflect.jvm.internal.impl.load.kotlin.DeserializedDescriptorResolver.createKotlinPackagePartScope.2();
            }

            kotlin.reflect.jvm.internal.impl.load.kotlin.DeserializedDescriptorResolver.createKotlinPackagePartScope.2() {
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final Collection invoke() {
                return CollectionsKt.emptyList();
            }
        }

    }

    private final DeserializedContainerAbiStability getAbiStability(KotlinJvmBinaryClass kotlinJvmBinaryClass0) {
        if(this.getComponents().getConfiguration().getAllowUnstableDependencies()) {
            return DeserializedContainerAbiStability.STABLE;
        }
        if(kotlinJvmBinaryClass0.getClassHeader().isUnstableFirBinary()) {
            return DeserializedContainerAbiStability.FIR_UNSTABLE;
        }
        return kotlinJvmBinaryClass0.getClassHeader().isUnstableJvmIrBinary() ? DeserializedContainerAbiStability.IR_UNSTABLE : DeserializedContainerAbiStability.STABLE;
    }

    public final DeserializationComponents getComponents() {
        DeserializationComponents deserializationComponents0 = this.components;
        if(deserializationComponents0 != null) {
            return deserializationComponents0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("components");
        return null;
    }

    private final IncompatibleVersionErrorData getIncompatibility(KotlinJvmBinaryClass kotlinJvmBinaryClass0) {
        if(!this.getSkipMetadataVersionCheck() && !kotlinJvmBinaryClass0.getClassHeader().getMetadataVersion().isCompatible(this.getOwnMetadataVersion())) {
            JvmMetadataVersion jvmMetadataVersion0 = kotlinJvmBinaryClass0.getClassHeader().getMetadataVersion();
            JvmMetadataVersion jvmMetadataVersion1 = this.getOwnMetadataVersion();
            JvmMetadataVersion jvmMetadataVersion2 = this.getOwnMetadataVersion().lastSupportedVersionWithThisLanguageVersion(kotlinJvmBinaryClass0.getClassHeader().getMetadataVersion().isStrictSemantics());
            String s = kotlinJvmBinaryClass0.getLocation();
            ClassId classId0 = kotlinJvmBinaryClass0.getClassId();
            return new IncompatibleVersionErrorData(jvmMetadataVersion0, JvmMetadataVersion.INSTANCE, jvmMetadataVersion1, jvmMetadataVersion2, s, classId0);
        }
        return null;
    }

    private final JvmMetadataVersion getOwnMetadataVersion() {
        return DeserializationHelpersKt.jvmMetadataVersionOrDefault(this.getComponents().getConfiguration());
    }

    private final boolean getSkipMetadataVersionCheck() {
        return this.getComponents().getConfiguration().getSkipMetadataVersionCheck();
    }

    // 去混淆评级： 低(30)
    private final boolean isCompiledWith13M1(KotlinJvmBinaryClass kotlinJvmBinaryClass0) {
        return !this.getComponents().getConfiguration().getSkipPrereleaseCheck() && kotlinJvmBinaryClass0.getClassHeader().isPreRelease() && Intrinsics.areEqual(kotlinJvmBinaryClass0.getClassHeader().getMetadataVersion(), DeserializedDescriptorResolver.KOTLIN_1_3_M1_METADATA_VERSION);
    }

    // 去混淆评级： 低(40)
    private final boolean isPreReleaseInvisible(KotlinJvmBinaryClass kotlinJvmBinaryClass0) {
        return this.getComponents().getConfiguration().getReportErrorsOnPreReleaseDependencies() && (kotlinJvmBinaryClass0.getClassHeader().isPreRelease() || Intrinsics.areEqual(kotlinJvmBinaryClass0.getClassHeader().getMetadataVersion(), DeserializedDescriptorResolver.KOTLIN_1_1_EAP_METADATA_VERSION)) || this.isCompiledWith13M1(kotlinJvmBinaryClass0);
    }

    public final ClassData readClassData$descriptors_jvm(KotlinJvmBinaryClass kotlinJvmBinaryClass0) {
        Pair pair0;
        Intrinsics.checkNotNullParameter(kotlinJvmBinaryClass0, "kotlinClass");
        String[] arr_s = this.readData(kotlinJvmBinaryClass0, DeserializedDescriptorResolver.KOTLIN_CLASS);
        if(arr_s == null) {
            return null;
        }
        String[] arr_s1 = kotlinJvmBinaryClass0.getClassHeader().getStrings();
        if(arr_s1 == null) {
            return null;
        }
        try {
            try {
                pair0 = JvmProtoBufUtil.readClassDataFrom(arr_s, arr_s1);
            }
            catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                throw new IllegalStateException("Could not read data from " + kotlinJvmBinaryClass0.getLocation(), invalidProtocolBufferException0);
            }
        }
        catch(Throwable throwable0) {
            if(this.getSkipMetadataVersionCheck() || kotlinJvmBinaryClass0.getClassHeader().getMetadataVersion().isCompatible(this.getOwnMetadataVersion())) {
                throw throwable0;
            }
            pair0 = null;
        }
        if(pair0 == null) {
            return null;
        }
        KotlinJvmBinarySourceElement kotlinJvmBinarySourceElement0 = new KotlinJvmBinarySourceElement(kotlinJvmBinaryClass0, this.getIncompatibility(kotlinJvmBinaryClass0), this.isPreReleaseInvisible(kotlinJvmBinaryClass0), this.getAbiStability(kotlinJvmBinaryClass0));
        return new ClassData(((JvmNameResolver)pair0.component1()), ((Class)pair0.component2()), kotlinJvmBinaryClass0.getClassHeader().getMetadataVersion(), kotlinJvmBinarySourceElement0);
    }

    private final String[] readData(KotlinJvmBinaryClass kotlinJvmBinaryClass0, Set set0) {
        KotlinClassHeader kotlinClassHeader0 = kotlinJvmBinaryClass0.getClassHeader();
        String[] arr_s = kotlinClassHeader0.getData();
        if(arr_s == null) {
            arr_s = kotlinClassHeader0.getIncompatibleData();
        }
        return arr_s == null || !set0.contains(kotlinClassHeader0.getKind()) ? null : arr_s;
    }

    public final ClassDescriptor resolveClass(KotlinJvmBinaryClass kotlinJvmBinaryClass0) {
        Intrinsics.checkNotNullParameter(kotlinJvmBinaryClass0, "kotlinClass");
        ClassData classData0 = this.readClassData$descriptors_jvm(kotlinJvmBinaryClass0);
        return classData0 == null ? null : this.getComponents().getClassDeserializer().deserializeClass(kotlinJvmBinaryClass0.getClassId(), classData0);
    }

    public final void setComponents(DeserializationComponentsForJava deserializationComponentsForJava0) {
        Intrinsics.checkNotNullParameter(deserializationComponentsForJava0, "components");
        this.setComponents(deserializationComponentsForJava0.getComponents());
    }

    public final void setComponents(DeserializationComponents deserializationComponents0) {
        Intrinsics.checkNotNullParameter(deserializationComponents0, "<set-?>");
        this.components = deserializationComponents0;
    }
}

