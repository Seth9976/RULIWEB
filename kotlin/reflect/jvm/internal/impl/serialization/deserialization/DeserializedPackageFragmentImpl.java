package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.PackageFragment;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.StringTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolverImpl;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedPackageMemberScope;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;

public abstract class DeserializedPackageFragmentImpl extends DeserializedPackageFragment {
    private MemberScope _memberScope;
    private PackageFragment _proto;
    private final ProtoBasedClassDataFinder classDataFinder;
    private final DeserializedContainerSource containerSource;
    private final BinaryVersion metadataVersion;
    private final NameResolverImpl nameResolver;

    public DeserializedPackageFragmentImpl(FqName fqName0, StorageManager storageManager0, ModuleDescriptor moduleDescriptor0, PackageFragment protoBuf$PackageFragment0, BinaryVersion binaryVersion0, DeserializedContainerSource deserializedContainerSource0) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        Intrinsics.checkNotNullParameter(storageManager0, "storageManager");
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "module");
        Intrinsics.checkNotNullParameter(protoBuf$PackageFragment0, "proto");
        Intrinsics.checkNotNullParameter(binaryVersion0, "metadataVersion");
        super(fqName0, storageManager0, moduleDescriptor0);
        this.metadataVersion = binaryVersion0;
        this.containerSource = deserializedContainerSource0;
        StringTable protoBuf$StringTable0 = protoBuf$PackageFragment0.getStrings();
        Intrinsics.checkNotNullExpressionValue(protoBuf$StringTable0, "proto.strings");
        QualifiedNameTable protoBuf$QualifiedNameTable0 = protoBuf$PackageFragment0.getQualifiedNames();
        Intrinsics.checkNotNullExpressionValue(protoBuf$QualifiedNameTable0, "proto.qualifiedNames");
        NameResolverImpl nameResolverImpl0 = new NameResolverImpl(protoBuf$StringTable0, protoBuf$QualifiedNameTable0);
        this.nameResolver = nameResolverImpl0;
        this.classDataFinder = new ProtoBasedClassDataFinder(protoBuf$PackageFragment0, nameResolverImpl0, binaryVersion0, new Function1() {
            {
                DeserializedPackageFragmentImpl.this = deserializedPackageFragmentImpl0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((ClassId)object0));
            }

            public final SourceElement invoke(ClassId classId0) {
                Intrinsics.checkNotNullParameter(classId0, "it");
                DeserializedContainerSource deserializedContainerSource0 = DeserializedPackageFragmentImpl.this.containerSource;
                if(deserializedContainerSource0 != null) {
                    return deserializedContainerSource0;
                }
                Intrinsics.checkNotNullExpressionValue(SourceElement.NO_SOURCE, "NO_SOURCE");
                return SourceElement.NO_SOURCE;
            }
        });
        this._proto = protoBuf$PackageFragment0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializedPackageFragment
    public ClassDataFinder getClassDataFinder() {
        return this.getClassDataFinder();
    }

    public ProtoBasedClassDataFinder getClassDataFinder() {
        return this.classDataFinder;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor
    public MemberScope getMemberScope() {
        MemberScope memberScope0 = this._memberScope;
        if(memberScope0 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("_memberScope");
            return null;
        }
        return memberScope0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializedPackageFragment
    public void initialize(DeserializationComponents deserializationComponents0) {
        Intrinsics.checkNotNullParameter(deserializationComponents0, "components");
        PackageFragment protoBuf$PackageFragment0 = this._proto;
        if(protoBuf$PackageFragment0 == null) {
            throw new IllegalStateException("Repeated call to DeserializedPackageFragmentImpl::initialize");
        }
        this._proto = null;
        Package protoBuf$Package0 = protoBuf$PackageFragment0.getPackage();
        Intrinsics.checkNotNullExpressionValue(protoBuf$Package0, "proto.`package`");
        kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializedPackageFragmentImpl.initialize.1 deserializedPackageFragmentImpl$initialize$10 = new Function0() {
            {
                DeserializedPackageFragmentImpl.this = deserializedPackageFragmentImpl0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final Collection invoke() {
                Iterable iterable0 = DeserializedPackageFragmentImpl.this.getClassDataFinder().getAllClassIds();
                Collection collection0 = new ArrayList();
                for(Object object0: iterable0) {
                    if(!((ClassId)object0).isNestedClass() && !ClassDeserializer.Companion.getBLACK_LIST().contains(((ClassId)object0))) {
                        collection0.add(object0);
                    }
                }
                ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(((List)collection0), 10));
                for(Object object1: ((List)collection0)) {
                    arrayList0.add(((ClassId)object1).getShortClassName());
                }
                return arrayList0;
            }
        };
        this._memberScope = new DeserializedPackageMemberScope(this, protoBuf$Package0, this.nameResolver, this.metadataVersion, this.containerSource, deserializationComponents0, "scope of " + this, deserializedPackageFragmentImpl$initialize$10);
    }
}

