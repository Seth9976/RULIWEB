package kotlin.reflect.jvm.internal.impl.descriptors.runtime.components;

import java.io.InputStream;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinder.Result.KotlinClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinder.Result;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinder;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.builtins.BuiltInSerializerProtocol;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.builtins.BuiltInsResourceLoader;

public final class ReflectKotlinClassFinder implements KotlinClassFinder {
    private final BuiltInsResourceLoader builtInsResourceLoader;
    private final ClassLoader classLoader;

    public ReflectKotlinClassFinder(ClassLoader classLoader0) {
        Intrinsics.checkNotNullParameter(classLoader0, "classLoader");
        super();
        this.classLoader = classLoader0;
        this.builtInsResourceLoader = new BuiltInsResourceLoader();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.KotlinMetadataFinder
    public InputStream findBuiltInsData(FqName fqName0) {
        Intrinsics.checkNotNullParameter(fqName0, "packageFqName");
        if(!fqName0.startsWith(StandardNames.BUILT_INS_PACKAGE_NAME)) {
            return null;
        }
        String s = BuiltInSerializerProtocol.INSTANCE.getBuiltInsFilePath(fqName0);
        return this.builtInsResourceLoader.loadResource(s);
    }

    private final Result findKotlinClass(String s) {
        Class class0 = ReflectJavaClassFinderKt.tryLoadClass(this.classLoader, s);
        if(class0 != null) {
            ReflectKotlinClass reflectKotlinClass0 = ReflectKotlinClass.Factory.create(class0);
            return reflectKotlinClass0 == null ? null : new KotlinClass(reflectKotlinClass0, null, 2, null);
        }
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinder
    public Result findKotlinClassOrContent(JavaClass javaClass0, JvmMetadataVersion jvmMetadataVersion0) {
        Intrinsics.checkNotNullParameter(javaClass0, "javaClass");
        Intrinsics.checkNotNullParameter(jvmMetadataVersion0, "jvmMetadataVersion");
        FqName fqName0 = javaClass0.getFqName();
        if(fqName0 != null) {
            String s = fqName0.asString();
            return s == null ? null : this.findKotlinClass(s);
        }
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinder
    public Result findKotlinClassOrContent(ClassId classId0, JvmMetadataVersion jvmMetadataVersion0) {
        Intrinsics.checkNotNullParameter(classId0, "classId");
        Intrinsics.checkNotNullParameter(jvmMetadataVersion0, "jvmMetadataVersion");
        return this.findKotlinClass(ReflectKotlinClassFinderKt.access$toRuntimeFqName(classId0));
    }
}

