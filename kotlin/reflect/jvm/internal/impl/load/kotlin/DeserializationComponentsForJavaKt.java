package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.ReflectionTypes;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker.EMPTY;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupTracker.DO_NOTHING;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver;
import kotlin.reflect.jvm.internal.impl.load.java.JavaClassFinder;
import kotlin.reflect.jvm.internal.impl.load.java.JavaModuleAnnotationsProvider;
import kotlin.reflect.jvm.internal.impl.load.java.JavaTypeEnhancementState;
import kotlin.reflect.jvm.internal.impl.load.java.components.JavaPropertyInitializerEvaluator.DoNothing;
import kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache;
import kotlin.reflect.jvm.internal.impl.load.java.components.SignaturePropagator;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaResolverComponents;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaPackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.ModuleClassResolver;
import kotlin.reflect.jvm.internal.impl.load.java.sources.JavaSourceElementFactory;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.JavaTypeEnhancement;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.resolve.sam.SamConversionResolverImpl;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ContractDeserializer;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationConfiguration.Default;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.DefaultTypeAttributeTranslator;
import kotlin.reflect.jvm.internal.impl.types.checker.NewKotlinTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.extensions.TypeAttributeTranslators;

public final class DeserializationComponentsForJavaKt {
    public static final DeserializationComponentsForJava makeDeserializationComponentsForJava(ModuleDescriptor moduleDescriptor0, StorageManager storageManager0, NotFoundClasses notFoundClasses0, LazyJavaPackageFragmentProvider lazyJavaPackageFragmentProvider0, KotlinClassFinder kotlinClassFinder0, DeserializedDescriptorResolver deserializedDescriptorResolver0, ErrorReporter errorReporter0, JvmMetadataVersion jvmMetadataVersion0) {
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "module");
        Intrinsics.checkNotNullParameter(storageManager0, "storageManager");
        Intrinsics.checkNotNullParameter(notFoundClasses0, "notFoundClasses");
        Intrinsics.checkNotNullParameter(lazyJavaPackageFragmentProvider0, "lazyJavaPackageFragmentProvider");
        Intrinsics.checkNotNullParameter(kotlinClassFinder0, "reflectKotlinClassFinder");
        Intrinsics.checkNotNullParameter(deserializedDescriptorResolver0, "deserializedDescriptorResolver");
        Intrinsics.checkNotNullParameter(errorReporter0, "errorReporter");
        Intrinsics.checkNotNullParameter(jvmMetadataVersion0, "jvmMetadataVersion");
        JavaClassDataFinder javaClassDataFinder0 = new JavaClassDataFinder(kotlinClassFinder0, deserializedDescriptorResolver0);
        BinaryClassAnnotationAndConstantLoaderImpl binaryClassAnnotationAndConstantLoaderImpl0 = BinaryClassAnnotationAndConstantLoaderImplKt.createBinaryClassAnnotationAndConstantLoader(moduleDescriptor0, notFoundClasses0, storageManager0, kotlinClassFinder0, jvmMetadataVersion0);
        TypeAttributeTranslators typeAttributeTranslators0 = new TypeAttributeTranslators(CollectionsKt.listOf(DefaultTypeAttributeTranslator.INSTANCE));
        return new DeserializationComponentsForJava(storageManager0, moduleDescriptor0, Default.INSTANCE, javaClassDataFinder0, binaryClassAnnotationAndConstantLoaderImpl0, lazyJavaPackageFragmentProvider0, notFoundClasses0, errorReporter0, DO_NOTHING.INSTANCE, ContractDeserializer.Companion.getDEFAULT(), NewKotlinTypeChecker.Companion.getDefault(), typeAttributeTranslators0);
    }

    public static final LazyJavaPackageFragmentProvider makeLazyJavaPackageFragmentProvider(JavaClassFinder javaClassFinder0, ModuleDescriptor moduleDescriptor0, StorageManager storageManager0, NotFoundClasses notFoundClasses0, KotlinClassFinder kotlinClassFinder0, DeserializedDescriptorResolver deserializedDescriptorResolver0, ErrorReporter errorReporter0, JavaSourceElementFactory javaSourceElementFactory0, ModuleClassResolver moduleClassResolver0, PackagePartProvider packagePartProvider0) {
        Intrinsics.checkNotNullParameter(javaClassFinder0, "javaClassFinder");
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "module");
        Intrinsics.checkNotNullParameter(storageManager0, "storageManager");
        Intrinsics.checkNotNullParameter(notFoundClasses0, "notFoundClasses");
        Intrinsics.checkNotNullParameter(kotlinClassFinder0, "reflectKotlinClassFinder");
        Intrinsics.checkNotNullParameter(deserializedDescriptorResolver0, "deserializedDescriptorResolver");
        Intrinsics.checkNotNullParameter(errorReporter0, "errorReporter");
        Intrinsics.checkNotNullParameter(javaSourceElementFactory0, "javaSourceElementFactory");
        Intrinsics.checkNotNullParameter(moduleClassResolver0, "singleModuleClassResolver");
        Intrinsics.checkNotNullParameter(packagePartProvider0, "packagePartProvider");
        Intrinsics.checkNotNullExpressionValue(SignaturePropagator.DO_NOTHING, "DO_NOTHING");
        Intrinsics.checkNotNullExpressionValue(JavaResolverCache.EMPTY, "EMPTY");
        SamConversionResolverImpl samConversionResolverImpl0 = new SamConversionResolverImpl(storageManager0, CollectionsKt.emptyList());
        ReflectionTypes reflectionTypes0 = new ReflectionTypes(moduleDescriptor0, notFoundClasses0);
        AnnotationTypeQualifierResolver annotationTypeQualifierResolver0 = new AnnotationTypeQualifierResolver(JavaTypeEnhancementState.Companion.getDEFAULT());
        SignatureEnhancement signatureEnhancement0 = new SignatureEnhancement(new JavaTypeEnhancement(kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaResolverSettings.Default.INSTANCE));
        kotlin.reflect.jvm.internal.impl.load.kotlin.DeserializationComponentsForJavaKt.makeLazyJavaPackageFragmentProvider.javaResolverComponents.1 deserializationComponentsForJavaKt$makeLazyJavaPackageFragmentProvider$javaResolverComponents$10 = new JavaModuleAnnotationsProvider() {
            @Override  // kotlin.reflect.jvm.internal.impl.load.java.JavaModuleAnnotationsProvider
            public List getAnnotationsForModuleOwnerOfClass(ClassId classId0) {
                Intrinsics.checkNotNullParameter(classId0, "classId");
                return null;
            }
        };
        return new LazyJavaPackageFragmentProvider(new JavaResolverComponents(storageManager0, javaClassFinder0, kotlinClassFinder0, deserializedDescriptorResolver0, SignaturePropagator.DO_NOTHING, errorReporter0, JavaResolverCache.EMPTY, DoNothing.INSTANCE, samConversionResolverImpl0, javaSourceElementFactory0, moduleClassResolver0, packagePartProvider0, EMPTY.INSTANCE, DO_NOTHING.INSTANCE, moduleDescriptor0, reflectionTypes0, annotationTypeQualifierResolver0, signatureEnhancement0, (kotlin.reflect.jvm.internal.impl.load.java.JavaClassesTracker.Default this) -> Intrinsics.checkNotNullParameter(javaClassDescriptor0, "classDescriptor"), kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaResolverSettings.Default.INSTANCE, NewKotlinTypeChecker.Companion.getDefault(), JavaTypeEnhancementState.Companion.getDEFAULT(), deserializationComponentsForJavaKt$makeLazyJavaPackageFragmentProvider$javaResolverComponents$10, null, 0x800000, null));
    }

    public static LazyJavaPackageFragmentProvider makeLazyJavaPackageFragmentProvider$default(JavaClassFinder javaClassFinder0, ModuleDescriptor moduleDescriptor0, StorageManager storageManager0, NotFoundClasses notFoundClasses0, KotlinClassFinder kotlinClassFinder0, DeserializedDescriptorResolver deserializedDescriptorResolver0, ErrorReporter errorReporter0, JavaSourceElementFactory javaSourceElementFactory0, ModuleClassResolver moduleClassResolver0, PackagePartProvider packagePartProvider0, int v, Object object0) {
        return (v & 0x200) == 0 ? DeserializationComponentsForJavaKt.makeLazyJavaPackageFragmentProvider(javaClassFinder0, moduleDescriptor0, storageManager0, notFoundClasses0, kotlinClassFinder0, deserializedDescriptorResolver0, errorReporter0, javaSourceElementFactory0, moduleClassResolver0, packagePartProvider0) : DeserializationComponentsForJavaKt.makeLazyJavaPackageFragmentProvider(javaClassFinder0, moduleDescriptor0, storageManager0, notFoundClasses0, kotlinClassFinder0, deserializedDescriptorResolver0, errorReporter0, javaSourceElementFactory0, moduleClassResolver0, Empty.INSTANCE);
    }
}

