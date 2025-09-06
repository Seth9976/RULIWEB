package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory;
import kotlin.reflect.jvm.internal.impl.incremental.UtilsKt;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext;

public class DeserializedPackageMemberScope extends DeserializedMemberScope {
    private final String debugName;
    private final PackageFragmentDescriptor packageDescriptor;
    private final FqName packageFqName;

    public DeserializedPackageMemberScope(PackageFragmentDescriptor packageFragmentDescriptor0, Package protoBuf$Package0, NameResolver nameResolver0, BinaryVersion binaryVersion0, DeserializedContainerSource deserializedContainerSource0, DeserializationComponents deserializationComponents0, String s, Function0 function00) {
        Intrinsics.checkNotNullParameter(packageFragmentDescriptor0, "packageDescriptor");
        Intrinsics.checkNotNullParameter(protoBuf$Package0, "proto");
        Intrinsics.checkNotNullParameter(nameResolver0, "nameResolver");
        Intrinsics.checkNotNullParameter(binaryVersion0, "metadataVersion");
        Intrinsics.checkNotNullParameter(deserializationComponents0, "components");
        Intrinsics.checkNotNullParameter(s, "debugName");
        Intrinsics.checkNotNullParameter(function00, "classNames");
        TypeTable protoBuf$TypeTable0 = protoBuf$Package0.getTypeTable();
        Intrinsics.checkNotNullExpressionValue(protoBuf$TypeTable0, "proto.typeTable");
        kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable typeTable0 = new kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable(protoBuf$TypeTable0);
        VersionRequirementTable protoBuf$VersionRequirementTable0 = protoBuf$Package0.getVersionRequirementTable();
        Intrinsics.checkNotNullExpressionValue(protoBuf$VersionRequirementTable0, "proto.versionRequirementTable");
        DeserializationContext deserializationContext0 = deserializationComponents0.createContext(packageFragmentDescriptor0, nameResolver0, typeTable0, kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable.Companion.create(protoBuf$VersionRequirementTable0), binaryVersion0, deserializedContainerSource0);
        List list0 = protoBuf$Package0.getFunctionList();
        Intrinsics.checkNotNullExpressionValue(list0, "proto.functionList");
        List list1 = protoBuf$Package0.getPropertyList();
        Intrinsics.checkNotNullExpressionValue(list1, "proto.propertyList");
        List list2 = protoBuf$Package0.getTypeAliasList();
        Intrinsics.checkNotNullExpressionValue(list2, "proto.typeAliasList");
        super(deserializationContext0, list0, list1, list2, function00);
        this.packageDescriptor = packageFragmentDescriptor0;
        this.debugName = s;
        this.packageFqName = packageFragmentDescriptor0.getFqName();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
    protected void addEnumEntryDescriptors(Collection collection0, Function1 function10) {
        Intrinsics.checkNotNullParameter(collection0, "result");
        Intrinsics.checkNotNullParameter(function10, "nameFilter");
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
    protected ClassId createClassId(Name name0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        return new ClassId(this.packageFqName, name0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
    public ClassifierDescriptor getContributedClassifier(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        this.recordLookup(name0, lookupLocation0);
        return super.getContributedClassifier(name0, lookupLocation0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
    public Collection getContributedDescriptors(DescriptorKindFilter descriptorKindFilter0, Function1 function10) {
        return this.getContributedDescriptors(descriptorKindFilter0, function10);
    }

    public List getContributedDescriptors(DescriptorKindFilter descriptorKindFilter0, Function1 function10) {
        Intrinsics.checkNotNullParameter(descriptorKindFilter0, "kindFilter");
        Intrinsics.checkNotNullParameter(function10, "nameFilter");
        Collection collection0 = this.computeDescriptors(descriptorKindFilter0, function10, NoLookupLocation.WHEN_GET_ALL_DESCRIPTORS);
        Iterable iterable0 = this.getC().getComponents().getFictitiousClassDescriptorFactories();
        Collection collection1 = new ArrayList();
        for(Object object0: iterable0) {
            CollectionsKt.addAll(collection1, ((ClassDescriptorFactory)object0).getAllContributedClassesIfPossible(this.packageFqName));
        }
        return CollectionsKt.plus(collection0, ((List)collection1));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
    protected Set getNonDeclaredClassifierNames() {
        return SetsKt.emptySet();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
    protected Set getNonDeclaredFunctionNames() {
        return SetsKt.emptySet();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
    protected Set getNonDeclaredVariableNames() {
        return SetsKt.emptySet();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
    protected boolean hasClass(Name name0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        if(!super.hasClass(name0)) {
            Iterable iterable0 = this.getC().getComponents().getFictitiousClassDescriptorFactories();
            if(!(iterable0 instanceof Collection) || !((Collection)iterable0).isEmpty()) {
                for(Object object0: iterable0) {
                    if(((ClassDescriptorFactory)object0).shouldCreateClass(this.packageFqName, name0)) {
                        return true;
                    }
                    if(false) {
                        break;
                    }
                }
            }
            return false;
        }
        return true;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
    public void recordLookup(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        UtilsKt.record(this.getC().getComponents().getLookupTracker(), lookupLocation0, this.packageDescriptor, name0);
    }

    @Override
    public String toString() {
        return this.debugName;
    }
}

