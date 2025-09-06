package kotlin.reflect.jvm.internal.impl.load.java.lazy;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.ReflectionTypes;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupTracker;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver;
import kotlin.reflect.jvm.internal.impl.load.java.JavaClassFinder;
import kotlin.reflect.jvm.internal.impl.load.java.JavaClassesTracker;
import kotlin.reflect.jvm.internal.impl.load.java.JavaModuleAnnotationsProvider;
import kotlin.reflect.jvm.internal.impl.load.java.JavaTypeEnhancementState;
import kotlin.reflect.jvm.internal.impl.load.java.components.JavaPropertyInitializerEvaluator;
import kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache;
import kotlin.reflect.jvm.internal.impl.load.java.components.SignaturePropagator;
import kotlin.reflect.jvm.internal.impl.load.java.sources.JavaSourceElementFactory;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement;
import kotlin.reflect.jvm.internal.impl.load.kotlin.DeserializedDescriptorResolver;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinder;
import kotlin.reflect.jvm.internal.impl.load.kotlin.PackagePartProvider;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.SyntheticJavaPartsProvider;
import kotlin.reflect.jvm.internal.impl.resolve.sam.SamConversionResolver;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.checker.NewKotlinTypeChecker;

public final class JavaResolverComponents {
    private final AnnotationTypeQualifierResolver annotationTypeQualifierResolver;
    private final DeserializedDescriptorResolver deserializedDescriptorResolver;
    private final ErrorReporter errorReporter;
    private final JavaClassFinder finder;
    private final JavaClassesTracker javaClassesTracker;
    private final JavaModuleAnnotationsProvider javaModuleResolver;
    private final JavaPropertyInitializerEvaluator javaPropertyInitializerEvaluator;
    private final JavaResolverCache javaResolverCache;
    private final JavaTypeEnhancementState javaTypeEnhancementState;
    private final KotlinClassFinder kotlinClassFinder;
    private final NewKotlinTypeChecker kotlinTypeChecker;
    private final LookupTracker lookupTracker;
    private final ModuleDescriptor module;
    private final ModuleClassResolver moduleClassResolver;
    private final PackagePartProvider packagePartProvider;
    private final ReflectionTypes reflectionTypes;
    private final SamConversionResolver samConversionResolver;
    private final JavaResolverSettings settings;
    private final SignatureEnhancement signatureEnhancement;
    private final SignaturePropagator signaturePropagator;
    private final JavaSourceElementFactory sourceElementFactory;
    private final StorageManager storageManager;
    private final SupertypeLoopChecker supertypeLoopChecker;
    private final SyntheticJavaPartsProvider syntheticPartsProvider;

    public JavaResolverComponents(StorageManager storageManager0, JavaClassFinder javaClassFinder0, KotlinClassFinder kotlinClassFinder0, DeserializedDescriptorResolver deserializedDescriptorResolver0, SignaturePropagator signaturePropagator0, ErrorReporter errorReporter0, JavaResolverCache javaResolverCache0, JavaPropertyInitializerEvaluator javaPropertyInitializerEvaluator0, SamConversionResolver samConversionResolver0, JavaSourceElementFactory javaSourceElementFactory0, ModuleClassResolver moduleClassResolver0, PackagePartProvider packagePartProvider0, SupertypeLoopChecker supertypeLoopChecker0, LookupTracker lookupTracker0, ModuleDescriptor moduleDescriptor0, ReflectionTypes reflectionTypes0, AnnotationTypeQualifierResolver annotationTypeQualifierResolver0, SignatureEnhancement signatureEnhancement0, JavaClassesTracker javaClassesTracker0, JavaResolverSettings javaResolverSettings0, NewKotlinTypeChecker newKotlinTypeChecker0, JavaTypeEnhancementState javaTypeEnhancementState0, JavaModuleAnnotationsProvider javaModuleAnnotationsProvider0, SyntheticJavaPartsProvider syntheticJavaPartsProvider0) {
        Intrinsics.checkNotNullParameter(storageManager0, "storageManager");
        Intrinsics.checkNotNullParameter(javaClassFinder0, "finder");
        Intrinsics.checkNotNullParameter(kotlinClassFinder0, "kotlinClassFinder");
        Intrinsics.checkNotNullParameter(deserializedDescriptorResolver0, "deserializedDescriptorResolver");
        Intrinsics.checkNotNullParameter(signaturePropagator0, "signaturePropagator");
        Intrinsics.checkNotNullParameter(errorReporter0, "errorReporter");
        Intrinsics.checkNotNullParameter(javaResolverCache0, "javaResolverCache");
        Intrinsics.checkNotNullParameter(javaPropertyInitializerEvaluator0, "javaPropertyInitializerEvaluator");
        Intrinsics.checkNotNullParameter(samConversionResolver0, "samConversionResolver");
        Intrinsics.checkNotNullParameter(javaSourceElementFactory0, "sourceElementFactory");
        Intrinsics.checkNotNullParameter(moduleClassResolver0, "moduleClassResolver");
        Intrinsics.checkNotNullParameter(packagePartProvider0, "packagePartProvider");
        Intrinsics.checkNotNullParameter(supertypeLoopChecker0, "supertypeLoopChecker");
        Intrinsics.checkNotNullParameter(lookupTracker0, "lookupTracker");
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "module");
        Intrinsics.checkNotNullParameter(reflectionTypes0, "reflectionTypes");
        Intrinsics.checkNotNullParameter(annotationTypeQualifierResolver0, "annotationTypeQualifierResolver");
        Intrinsics.checkNotNullParameter(signatureEnhancement0, "signatureEnhancement");
        Intrinsics.checkNotNullParameter(javaClassesTracker0, "javaClassesTracker");
        Intrinsics.checkNotNullParameter(javaResolverSettings0, "settings");
        Intrinsics.checkNotNullParameter(newKotlinTypeChecker0, "kotlinTypeChecker");
        Intrinsics.checkNotNullParameter(javaTypeEnhancementState0, "javaTypeEnhancementState");
        Intrinsics.checkNotNullParameter(javaModuleAnnotationsProvider0, "javaModuleResolver");
        Intrinsics.checkNotNullParameter(syntheticJavaPartsProvider0, "syntheticPartsProvider");
        super();
        this.storageManager = storageManager0;
        this.finder = javaClassFinder0;
        this.kotlinClassFinder = kotlinClassFinder0;
        this.deserializedDescriptorResolver = deserializedDescriptorResolver0;
        this.signaturePropagator = signaturePropagator0;
        this.errorReporter = errorReporter0;
        this.javaResolverCache = javaResolverCache0;
        this.javaPropertyInitializerEvaluator = javaPropertyInitializerEvaluator0;
        this.samConversionResolver = samConversionResolver0;
        this.sourceElementFactory = javaSourceElementFactory0;
        this.moduleClassResolver = moduleClassResolver0;
        this.packagePartProvider = packagePartProvider0;
        this.supertypeLoopChecker = supertypeLoopChecker0;
        this.lookupTracker = lookupTracker0;
        this.module = moduleDescriptor0;
        this.reflectionTypes = reflectionTypes0;
        this.annotationTypeQualifierResolver = annotationTypeQualifierResolver0;
        this.signatureEnhancement = signatureEnhancement0;
        this.javaClassesTracker = javaClassesTracker0;
        this.settings = javaResolverSettings0;
        this.kotlinTypeChecker = newKotlinTypeChecker0;
        this.javaTypeEnhancementState = javaTypeEnhancementState0;
        this.javaModuleResolver = javaModuleAnnotationsProvider0;
        this.syntheticPartsProvider = syntheticJavaPartsProvider0;
    }

    public JavaResolverComponents(StorageManager storageManager0, JavaClassFinder javaClassFinder0, KotlinClassFinder kotlinClassFinder0, DeserializedDescriptorResolver deserializedDescriptorResolver0, SignaturePropagator signaturePropagator0, ErrorReporter errorReporter0, JavaResolverCache javaResolverCache0, JavaPropertyInitializerEvaluator javaPropertyInitializerEvaluator0, SamConversionResolver samConversionResolver0, JavaSourceElementFactory javaSourceElementFactory0, ModuleClassResolver moduleClassResolver0, PackagePartProvider packagePartProvider0, SupertypeLoopChecker supertypeLoopChecker0, LookupTracker lookupTracker0, ModuleDescriptor moduleDescriptor0, ReflectionTypes reflectionTypes0, AnnotationTypeQualifierResolver annotationTypeQualifierResolver0, SignatureEnhancement signatureEnhancement0, JavaClassesTracker javaClassesTracker0, JavaResolverSettings javaResolverSettings0, NewKotlinTypeChecker newKotlinTypeChecker0, JavaTypeEnhancementState javaTypeEnhancementState0, JavaModuleAnnotationsProvider javaModuleAnnotationsProvider0, SyntheticJavaPartsProvider syntheticJavaPartsProvider0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        SyntheticJavaPartsProvider syntheticJavaPartsProvider1 = (v & 0x800000) == 0 ? syntheticJavaPartsProvider0 : SyntheticJavaPartsProvider.Companion.getEMPTY();
        this(storageManager0, javaClassFinder0, kotlinClassFinder0, deserializedDescriptorResolver0, signaturePropagator0, errorReporter0, javaResolverCache0, javaPropertyInitializerEvaluator0, samConversionResolver0, javaSourceElementFactory0, moduleClassResolver0, packagePartProvider0, supertypeLoopChecker0, lookupTracker0, moduleDescriptor0, reflectionTypes0, annotationTypeQualifierResolver0, signatureEnhancement0, javaClassesTracker0, javaResolverSettings0, newKotlinTypeChecker0, javaTypeEnhancementState0, javaModuleAnnotationsProvider0, syntheticJavaPartsProvider1);
    }

    public final AnnotationTypeQualifierResolver getAnnotationTypeQualifierResolver() {
        return this.annotationTypeQualifierResolver;
    }

    public final DeserializedDescriptorResolver getDeserializedDescriptorResolver() {
        return this.deserializedDescriptorResolver;
    }

    public final ErrorReporter getErrorReporter() {
        return this.errorReporter;
    }

    public final JavaClassFinder getFinder() {
        return this.finder;
    }

    public final JavaClassesTracker getJavaClassesTracker() {
        return this.javaClassesTracker;
    }

    public final JavaModuleAnnotationsProvider getJavaModuleResolver() {
        return this.javaModuleResolver;
    }

    public final JavaPropertyInitializerEvaluator getJavaPropertyInitializerEvaluator() {
        return this.javaPropertyInitializerEvaluator;
    }

    public final JavaResolverCache getJavaResolverCache() {
        return this.javaResolverCache;
    }

    public final JavaTypeEnhancementState getJavaTypeEnhancementState() {
        return this.javaTypeEnhancementState;
    }

    public final KotlinClassFinder getKotlinClassFinder() {
        return this.kotlinClassFinder;
    }

    public final NewKotlinTypeChecker getKotlinTypeChecker() {
        return this.kotlinTypeChecker;
    }

    public final LookupTracker getLookupTracker() {
        return this.lookupTracker;
    }

    public final ModuleDescriptor getModule() {
        return this.module;
    }

    public final ModuleClassResolver getModuleClassResolver() {
        return this.moduleClassResolver;
    }

    public final PackagePartProvider getPackagePartProvider() {
        return this.packagePartProvider;
    }

    public final ReflectionTypes getReflectionTypes() {
        return this.reflectionTypes;
    }

    public final JavaResolverSettings getSettings() {
        return this.settings;
    }

    public final SignatureEnhancement getSignatureEnhancement() {
        return this.signatureEnhancement;
    }

    public final SignaturePropagator getSignaturePropagator() {
        return this.signaturePropagator;
    }

    public final JavaSourceElementFactory getSourceElementFactory() {
        return this.sourceElementFactory;
    }

    public final StorageManager getStorageManager() {
        return this.storageManager;
    }

    public final SupertypeLoopChecker getSupertypeLoopChecker() {
        return this.supertypeLoopChecker;
    }

    public final SyntheticJavaPartsProvider getSyntheticPartsProvider() {
        return this.syntheticPartsProvider;
    }

    public final JavaResolverComponents replace(JavaResolverCache javaResolverCache0) {
        Intrinsics.checkNotNullParameter(javaResolverCache0, "javaResolverCache");
        return new JavaResolverComponents(this.storageManager, this.finder, this.kotlinClassFinder, this.deserializedDescriptorResolver, this.signaturePropagator, this.errorReporter, javaResolverCache0, this.javaPropertyInitializerEvaluator, this.samConversionResolver, this.sourceElementFactory, this.moduleClassResolver, this.packagePartProvider, this.supertypeLoopChecker, this.lookupTracker, this.module, this.reflectionTypes, this.annotationTypeQualifierResolver, this.signatureEnhancement, this.javaClassesTracker, this.settings, this.kotlinTypeChecker, this.javaTypeEnhancementState, this.javaModuleResolver, null, 0x800000, null);
    }
}

