package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider.None;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter.All;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentTypeTransformer;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupTracker;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite;
import kotlin.reflect.jvm.internal.impl.resolve.sam.SamConversionResolver;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.DefaultTypeAttributeTranslator;
import kotlin.reflect.jvm.internal.impl.types.checker.NewKotlinTypeChecker;

public final class DeserializationComponents {
    private final AdditionalClassPartsProvider additionalClassPartsProvider;
    private final AnnotationAndConstantLoader annotationAndConstantLoader;
    private final ClassDataFinder classDataFinder;
    private final ClassDeserializer classDeserializer;
    private final DeserializationConfiguration configuration;
    private final ContractDeserializer contractDeserializer;
    private final ErrorReporter errorReporter;
    private final ExtensionRegistryLite extensionRegistryLite;
    private final Iterable fictitiousClassDescriptorFactories;
    private final FlexibleTypeDeserializer flexibleTypeDeserializer;
    private final NewKotlinTypeChecker kotlinTypeChecker;
    private final LocalClassifierTypeSettings localClassifierTypeSettings;
    private final LookupTracker lookupTracker;
    private final ModuleDescriptor moduleDescriptor;
    private final NotFoundClasses notFoundClasses;
    private final PackageFragmentProvider packageFragmentProvider;
    private final PlatformDependentDeclarationFilter platformDependentDeclarationFilter;
    private final PlatformDependentTypeTransformer platformDependentTypeTransformer;
    private final SamConversionResolver samConversionResolver;
    private final StorageManager storageManager;
    private final List typeAttributeTranslators;

    public DeserializationComponents(StorageManager storageManager0, ModuleDescriptor moduleDescriptor0, DeserializationConfiguration deserializationConfiguration0, ClassDataFinder classDataFinder0, AnnotationAndConstantLoader annotationAndConstantLoader0, PackageFragmentProvider packageFragmentProvider0, LocalClassifierTypeSettings localClassifierTypeSettings0, ErrorReporter errorReporter0, LookupTracker lookupTracker0, FlexibleTypeDeserializer flexibleTypeDeserializer0, Iterable iterable0, NotFoundClasses notFoundClasses0, ContractDeserializer contractDeserializer0, AdditionalClassPartsProvider additionalClassPartsProvider0, PlatformDependentDeclarationFilter platformDependentDeclarationFilter0, ExtensionRegistryLite extensionRegistryLite0, NewKotlinTypeChecker newKotlinTypeChecker0, SamConversionResolver samConversionResolver0, PlatformDependentTypeTransformer platformDependentTypeTransformer0, List list0) {
        Intrinsics.checkNotNullParameter(storageManager0, "storageManager");
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "moduleDescriptor");
        Intrinsics.checkNotNullParameter(deserializationConfiguration0, "configuration");
        Intrinsics.checkNotNullParameter(classDataFinder0, "classDataFinder");
        Intrinsics.checkNotNullParameter(annotationAndConstantLoader0, "annotationAndConstantLoader");
        Intrinsics.checkNotNullParameter(packageFragmentProvider0, "packageFragmentProvider");
        Intrinsics.checkNotNullParameter(localClassifierTypeSettings0, "localClassifierTypeSettings");
        Intrinsics.checkNotNullParameter(errorReporter0, "errorReporter");
        Intrinsics.checkNotNullParameter(lookupTracker0, "lookupTracker");
        Intrinsics.checkNotNullParameter(flexibleTypeDeserializer0, "flexibleTypeDeserializer");
        Intrinsics.checkNotNullParameter(iterable0, "fictitiousClassDescriptorFactories");
        Intrinsics.checkNotNullParameter(notFoundClasses0, "notFoundClasses");
        Intrinsics.checkNotNullParameter(contractDeserializer0, "contractDeserializer");
        Intrinsics.checkNotNullParameter(additionalClassPartsProvider0, "additionalClassPartsProvider");
        Intrinsics.checkNotNullParameter(platformDependentDeclarationFilter0, "platformDependentDeclarationFilter");
        Intrinsics.checkNotNullParameter(extensionRegistryLite0, "extensionRegistryLite");
        Intrinsics.checkNotNullParameter(newKotlinTypeChecker0, "kotlinTypeChecker");
        Intrinsics.checkNotNullParameter(samConversionResolver0, "samConversionResolver");
        Intrinsics.checkNotNullParameter(platformDependentTypeTransformer0, "platformDependentTypeTransformer");
        Intrinsics.checkNotNullParameter(list0, "typeAttributeTranslators");
        super();
        this.storageManager = storageManager0;
        this.moduleDescriptor = moduleDescriptor0;
        this.configuration = deserializationConfiguration0;
        this.classDataFinder = classDataFinder0;
        this.annotationAndConstantLoader = annotationAndConstantLoader0;
        this.packageFragmentProvider = packageFragmentProvider0;
        this.localClassifierTypeSettings = localClassifierTypeSettings0;
        this.errorReporter = errorReporter0;
        this.lookupTracker = lookupTracker0;
        this.flexibleTypeDeserializer = flexibleTypeDeserializer0;
        this.fictitiousClassDescriptorFactories = iterable0;
        this.notFoundClasses = notFoundClasses0;
        this.contractDeserializer = contractDeserializer0;
        this.additionalClassPartsProvider = additionalClassPartsProvider0;
        this.platformDependentDeclarationFilter = platformDependentDeclarationFilter0;
        this.extensionRegistryLite = extensionRegistryLite0;
        this.kotlinTypeChecker = newKotlinTypeChecker0;
        this.samConversionResolver = samConversionResolver0;
        this.platformDependentTypeTransformer = platformDependentTypeTransformer0;
        this.typeAttributeTranslators = list0;
        this.classDeserializer = new ClassDeserializer(this);
    }

    public DeserializationComponents(StorageManager storageManager0, ModuleDescriptor moduleDescriptor0, DeserializationConfiguration deserializationConfiguration0, ClassDataFinder classDataFinder0, AnnotationAndConstantLoader annotationAndConstantLoader0, PackageFragmentProvider packageFragmentProvider0, LocalClassifierTypeSettings localClassifierTypeSettings0, ErrorReporter errorReporter0, LookupTracker lookupTracker0, FlexibleTypeDeserializer flexibleTypeDeserializer0, Iterable iterable0, NotFoundClasses notFoundClasses0, ContractDeserializer contractDeserializer0, AdditionalClassPartsProvider additionalClassPartsProvider0, PlatformDependentDeclarationFilter platformDependentDeclarationFilter0, ExtensionRegistryLite extensionRegistryLite0, NewKotlinTypeChecker newKotlinTypeChecker0, SamConversionResolver samConversionResolver0, PlatformDependentTypeTransformer platformDependentTypeTransformer0, List list0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        AdditionalClassPartsProvider additionalClassPartsProvider1 = (v & 0x2000) == 0 ? additionalClassPartsProvider0 : None.INSTANCE;
        PlatformDependentDeclarationFilter platformDependentDeclarationFilter1 = (v & 0x4000) == 0 ? platformDependentDeclarationFilter0 : All.INSTANCE;
        NewKotlinTypeChecker newKotlinTypeChecker1 = (0x10000 & v) == 0 ? newKotlinTypeChecker0 : NewKotlinTypeChecker.Companion.getDefault();
        PlatformDependentTypeTransformer platformDependentTypeTransformer1 = (0x40000 & v) == 0 ? platformDependentTypeTransformer0 : kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentTypeTransformer.None.INSTANCE;
        this(storageManager0, moduleDescriptor0, deserializationConfiguration0, classDataFinder0, annotationAndConstantLoader0, packageFragmentProvider0, localClassifierTypeSettings0, errorReporter0, lookupTracker0, flexibleTypeDeserializer0, iterable0, notFoundClasses0, contractDeserializer0, additionalClassPartsProvider1, platformDependentDeclarationFilter1, extensionRegistryLite0, newKotlinTypeChecker1, samConversionResolver0, platformDependentTypeTransformer1, ((v & 0x80000) == 0 ? list0 : CollectionsKt.listOf(DefaultTypeAttributeTranslator.INSTANCE)));
    }

    public final DeserializationContext createContext(PackageFragmentDescriptor packageFragmentDescriptor0, NameResolver nameResolver0, TypeTable typeTable0, VersionRequirementTable versionRequirementTable0, BinaryVersion binaryVersion0, DeserializedContainerSource deserializedContainerSource0) {
        Intrinsics.checkNotNullParameter(packageFragmentDescriptor0, "descriptor");
        Intrinsics.checkNotNullParameter(nameResolver0, "nameResolver");
        Intrinsics.checkNotNullParameter(typeTable0, "typeTable");
        Intrinsics.checkNotNullParameter(versionRequirementTable0, "versionRequirementTable");
        Intrinsics.checkNotNullParameter(binaryVersion0, "metadataVersion");
        return new DeserializationContext(this, nameResolver0, packageFragmentDescriptor0, typeTable0, versionRequirementTable0, binaryVersion0, deserializedContainerSource0, null, CollectionsKt.emptyList());
    }

    public final ClassDescriptor deserializeClass(ClassId classId0) {
        Intrinsics.checkNotNullParameter(classId0, "classId");
        return ClassDeserializer.deserializeClass$default(this.classDeserializer, classId0, null, 2, null);
    }

    public final AdditionalClassPartsProvider getAdditionalClassPartsProvider() {
        return this.additionalClassPartsProvider;
    }

    public final AnnotationAndConstantLoader getAnnotationAndConstantLoader() {
        return this.annotationAndConstantLoader;
    }

    public final ClassDataFinder getClassDataFinder() {
        return this.classDataFinder;
    }

    public final ClassDeserializer getClassDeserializer() {
        return this.classDeserializer;
    }

    public final DeserializationConfiguration getConfiguration() {
        return this.configuration;
    }

    public final ContractDeserializer getContractDeserializer() {
        return this.contractDeserializer;
    }

    public final ErrorReporter getErrorReporter() {
        return this.errorReporter;
    }

    public final ExtensionRegistryLite getExtensionRegistryLite() {
        return this.extensionRegistryLite;
    }

    public final Iterable getFictitiousClassDescriptorFactories() {
        return this.fictitiousClassDescriptorFactories;
    }

    public final FlexibleTypeDeserializer getFlexibleTypeDeserializer() {
        return this.flexibleTypeDeserializer;
    }

    public final NewKotlinTypeChecker getKotlinTypeChecker() {
        return this.kotlinTypeChecker;
    }

    public final LocalClassifierTypeSettings getLocalClassifierTypeSettings() {
        return this.localClassifierTypeSettings;
    }

    public final LookupTracker getLookupTracker() {
        return this.lookupTracker;
    }

    public final ModuleDescriptor getModuleDescriptor() {
        return this.moduleDescriptor;
    }

    public final NotFoundClasses getNotFoundClasses() {
        return this.notFoundClasses;
    }

    public final PackageFragmentProvider getPackageFragmentProvider() {
        return this.packageFragmentProvider;
    }

    public final PlatformDependentDeclarationFilter getPlatformDependentDeclarationFilter() {
        return this.platformDependentDeclarationFilter;
    }

    public final PlatformDependentTypeTransformer getPlatformDependentTypeTransformer() {
        return this.platformDependentTypeTransformer;
    }

    public final StorageManager getStorageManager() {
        return this.storageManager;
    }

    public final List getTypeAttributeTranslators() {
        return this.typeAttributeTranslators;
    }
}

