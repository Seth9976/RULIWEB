package kotlin.reflect.jvm.internal.impl.serialization.deserialization.builtins;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.jvm.internal.impl.builtins.BuiltInsLoader;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider.None;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupTracker.DO_NOTHING;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite;
import kotlin.reflect.jvm.internal.impl.resolve.sam.SamConversionResolverImpl;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationAndConstantLoaderImpl;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ContractDeserializer;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationConfiguration.Default;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializedClassDataFinder;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.FlexibleTypeDeserializer.ThrowException;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;

public final class BuiltInsLoaderImpl implements BuiltInsLoader {
    private final BuiltInsResourceLoader resourceLoader;

    public BuiltInsLoaderImpl() {
        this.resourceLoader = new BuiltInsResourceLoader();
    }

    public final PackageFragmentProvider createBuiltInPackageFragmentProvider(StorageManager storageManager0, ModuleDescriptor moduleDescriptor0, Set set0, Iterable iterable0, PlatformDependentDeclarationFilter platformDependentDeclarationFilter0, AdditionalClassPartsProvider additionalClassPartsProvider0, boolean z, Function1 function10) {
        Intrinsics.checkNotNullParameter(storageManager0, "storageManager");
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "module");
        Intrinsics.checkNotNullParameter(set0, "packageFqNames");
        Intrinsics.checkNotNullParameter(iterable0, "classDescriptorFactories");
        Intrinsics.checkNotNullParameter(platformDependentDeclarationFilter0, "platformDependentDeclarationFilter");
        Intrinsics.checkNotNullParameter(additionalClassPartsProvider0, "additionalClassPartsProvider");
        Intrinsics.checkNotNullParameter(function10, "loadResource");
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(set0, 10));
        for(Object object0: set0) {
            String s = BuiltInSerializerProtocol.INSTANCE.getBuiltInsFilePath(((FqName)object0));
            InputStream inputStream0 = (InputStream)function10.invoke(s);
            if(inputStream0 == null) {
                throw new IllegalStateException("Resource not found in classpath: " + s);
            }
            arrayList0.add(BuiltInsPackageFragmentImpl.Companion.create(((FqName)object0), storageManager0, moduleDescriptor0, inputStream0, z));
        }
        PackageFragmentProviderImpl packageFragmentProviderImpl0 = new PackageFragmentProviderImpl(arrayList0);
        NotFoundClasses notFoundClasses0 = new NotFoundClasses(storageManager0, moduleDescriptor0);
        DeserializedClassDataFinder deserializedClassDataFinder0 = new DeserializedClassDataFinder(packageFragmentProviderImpl0);
        AnnotationAndConstantLoaderImpl annotationAndConstantLoaderImpl0 = new AnnotationAndConstantLoaderImpl(moduleDescriptor0, notFoundClasses0, BuiltInSerializerProtocol.INSTANCE);
        Intrinsics.checkNotNullExpressionValue(ErrorReporter.DO_NOTHING, "DO_NOTHING");
        ExtensionRegistryLite extensionRegistryLite0 = BuiltInSerializerProtocol.INSTANCE.getExtensionRegistry();
        SamConversionResolverImpl samConversionResolverImpl0 = new SamConversionResolverImpl(storageManager0, CollectionsKt.emptyList());
        DeserializationComponents deserializationComponents0 = new DeserializationComponents(storageManager0, moduleDescriptor0, Default.INSTANCE, deserializedClassDataFinder0, annotationAndConstantLoaderImpl0, packageFragmentProviderImpl0, () -> null, ErrorReporter.DO_NOTHING, DO_NOTHING.INSTANCE, ThrowException.INSTANCE, iterable0, notFoundClasses0, ContractDeserializer.Companion.getDEFAULT(), additionalClassPartsProvider0, platformDependentDeclarationFilter0, extensionRegistryLite0, null, samConversionResolverImpl0, null, null, 0xD0000, null);
        for(Object object1: arrayList0) {
            ((BuiltInsPackageFragmentImpl)object1).initialize(deserializationComponents0);
        }
        return packageFragmentProviderImpl0;
    }

    public static PackageFragmentProvider createBuiltInPackageFragmentProvider$default(BuiltInsLoaderImpl builtInsLoaderImpl0, StorageManager storageManager0, ModuleDescriptor moduleDescriptor0, Set set0, Iterable iterable0, PlatformDependentDeclarationFilter platformDependentDeclarationFilter0, AdditionalClassPartsProvider additionalClassPartsProvider0, boolean z, Function1 function10, int v, Object object0) {
        return (v & 0x20) == 0 ? builtInsLoaderImpl0.createBuiltInPackageFragmentProvider(storageManager0, moduleDescriptor0, set0, iterable0, platformDependentDeclarationFilter0, additionalClassPartsProvider0, z, function10) : builtInsLoaderImpl0.createBuiltInPackageFragmentProvider(storageManager0, moduleDescriptor0, set0, iterable0, platformDependentDeclarationFilter0, None.INSTANCE, z, function10);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.builtins.BuiltInsLoader
    public PackageFragmentProvider createPackageFragmentProvider(StorageManager storageManager0, ModuleDescriptor moduleDescriptor0, Iterable iterable0, PlatformDependentDeclarationFilter platformDependentDeclarationFilter0, AdditionalClassPartsProvider additionalClassPartsProvider0, boolean z) {
        Intrinsics.checkNotNullParameter(storageManager0, "storageManager");
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "builtInsModule");
        Intrinsics.checkNotNullParameter(iterable0, "classDescriptorFactories");
        Intrinsics.checkNotNullParameter(platformDependentDeclarationFilter0, "platformDependentDeclarationFilter");
        Intrinsics.checkNotNullParameter(additionalClassPartsProvider0, "additionalClassPartsProvider");
        kotlin.reflect.jvm.internal.impl.serialization.deserialization.builtins.BuiltInsLoaderImpl.createPackageFragmentProvider.1 builtInsLoaderImpl$createPackageFragmentProvider$10 = new Function1() {
            {
                super(1, object0);
            }

            @Override  // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
            public final String getName() {
                return "loadResource";
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public final KDeclarationContainer getOwner() {
                return Reflection.getOrCreateKotlinClass(BuiltInsResourceLoader.class);
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public final String getSignature() {
                return "loadResource(Ljava/lang/String;)Ljava/io/InputStream;";
            }

            public final InputStream invoke(String s) {
                Intrinsics.checkNotNullParameter(s, "p0");
                return ((BuiltInsResourceLoader)this.receiver).loadResource(s);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((String)object0));
            }
        };
        return this.createBuiltInPackageFragmentProvider(storageManager0, moduleDescriptor0, StandardNames.BUILT_INS_PACKAGE_FQ_NAMES, iterable0, platformDependentDeclarationFilter0, additionalClassPartsProvider0, z, builtInsLoaderImpl$createPackageFragmentProvider$10);
    }
}

