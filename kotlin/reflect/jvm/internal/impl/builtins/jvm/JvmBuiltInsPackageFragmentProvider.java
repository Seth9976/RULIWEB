package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import java.io.InputStream;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.functions.BuiltInFictitiousFunctionClassFactory;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupTracker.DO_NOTHING;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinder;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite;
import kotlin.reflect.jvm.internal.impl.resolve.sam.SamConversionResolver;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.AbstractDeserializedPackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationAndConstantLoaderImpl;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ContractDeserializer;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationConfiguration;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializedClassDataFinder;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializedPackageFragment;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.FlexibleTypeDeserializer.ThrowException;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.LocalClassifierTypeSettings.Default;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.builtins.BuiltInSerializerProtocol;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.builtins.BuiltInsPackageFragmentImpl;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.checker.NewKotlinTypeChecker;

public final class JvmBuiltInsPackageFragmentProvider extends AbstractDeserializedPackageFragmentProvider {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    public static final Companion Companion;

    static {
        JvmBuiltInsPackageFragmentProvider.Companion = new Companion(null);
    }

    public JvmBuiltInsPackageFragmentProvider(StorageManager storageManager0, KotlinClassFinder kotlinClassFinder0, ModuleDescriptor moduleDescriptor0, NotFoundClasses notFoundClasses0, AdditionalClassPartsProvider additionalClassPartsProvider0, PlatformDependentDeclarationFilter platformDependentDeclarationFilter0, DeserializationConfiguration deserializationConfiguration0, NewKotlinTypeChecker newKotlinTypeChecker0, SamConversionResolver samConversionResolver0) {
        Intrinsics.checkNotNullParameter(storageManager0, "storageManager");
        Intrinsics.checkNotNullParameter(kotlinClassFinder0, "finder");
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "moduleDescriptor");
        Intrinsics.checkNotNullParameter(notFoundClasses0, "notFoundClasses");
        Intrinsics.checkNotNullParameter(additionalClassPartsProvider0, "additionalClassPartsProvider");
        Intrinsics.checkNotNullParameter(platformDependentDeclarationFilter0, "platformDependentDeclarationFilter");
        Intrinsics.checkNotNullParameter(deserializationConfiguration0, "deserializationConfiguration");
        Intrinsics.checkNotNullParameter(newKotlinTypeChecker0, "kotlinTypeChecker");
        Intrinsics.checkNotNullParameter(samConversionResolver0, "samConversionResolver");
        super(storageManager0, kotlinClassFinder0, moduleDescriptor0);
        DeserializedClassDataFinder deserializedClassDataFinder0 = new DeserializedClassDataFinder(this);
        AnnotationAndConstantLoaderImpl annotationAndConstantLoaderImpl0 = new AnnotationAndConstantLoaderImpl(moduleDescriptor0, notFoundClasses0, BuiltInSerializerProtocol.INSTANCE);
        Intrinsics.checkNotNullExpressionValue(ErrorReporter.DO_NOTHING, "DO_NOTHING");
        Iterable iterable0 = CollectionsKt.listOf(new ClassDescriptorFactory[]{new BuiltInFictitiousFunctionClassFactory(storageManager0, moduleDescriptor0), new JvmBuiltInClassDescriptorFactory(storageManager0, moduleDescriptor0, null, 4, null)});
        ExtensionRegistryLite extensionRegistryLite0 = BuiltInSerializerProtocol.INSTANCE.getExtensionRegistry();
        this.setComponents(new DeserializationComponents(storageManager0, moduleDescriptor0, deserializationConfiguration0, deserializedClassDataFinder0, annotationAndConstantLoaderImpl0, this, Default.INSTANCE, ErrorReporter.DO_NOTHING, DO_NOTHING.INSTANCE, ThrowException.INSTANCE, iterable0, notFoundClasses0, ContractDeserializer.Companion.getDEFAULT(), additionalClassPartsProvider0, platformDependentDeclarationFilter0, extensionRegistryLite0, newKotlinTypeChecker0, samConversionResolver0, null, null, 0xC0000, null));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AbstractDeserializedPackageFragmentProvider
    protected DeserializedPackageFragment findPackage(FqName fqName0) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        InputStream inputStream0 = this.getFinder().findBuiltInsData(fqName0);
        if(inputStream0 != null) {
            StorageManager storageManager0 = this.getStorageManager();
            ModuleDescriptor moduleDescriptor0 = this.getModuleDescriptor();
            return BuiltInsPackageFragmentImpl.Companion.create(fqName0, storageManager0, moduleDescriptor0, inputStream0, false);
        }
        return null;
    }
}

