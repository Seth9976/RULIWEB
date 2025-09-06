package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;

public final class InnerClassesScopeWrapper extends MemberScopeImpl {
    private final MemberScope workerScope;

    public InnerClassesScopeWrapper(MemberScope memberScope0) {
        Intrinsics.checkNotNullParameter(memberScope0, "workerScope");
        super();
        this.workerScope = memberScope0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
    public Set getClassifierNames() {
        return this.workerScope.getClassifierNames();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
    public ClassifierDescriptor getContributedClassifier(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        ClassifierDescriptor classifierDescriptor0 = this.workerScope.getContributedClassifier(name0, lookupLocation0);
        if(classifierDescriptor0 != null) {
            ClassDescriptor classDescriptor0 = classifierDescriptor0 instanceof ClassDescriptor ? ((ClassDescriptor)classifierDescriptor0) : null;
            if(classDescriptor0 != null) {
                return classDescriptor0;
            }
            if(classifierDescriptor0 instanceof TypeAliasDescriptor) {
                return (TypeAliasDescriptor)classifierDescriptor0;
            }
        }
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
    public Collection getContributedDescriptors(DescriptorKindFilter descriptorKindFilter0, Function1 function10) {
        return this.getContributedDescriptors(descriptorKindFilter0, function10);
    }

    public List getContributedDescriptors(DescriptorKindFilter descriptorKindFilter0, Function1 function10) {
        Intrinsics.checkNotNullParameter(descriptorKindFilter0, "kindFilter");
        Intrinsics.checkNotNullParameter(function10, "nameFilter");
        DescriptorKindFilter descriptorKindFilter1 = descriptorKindFilter0.restrictedToKindsOrNull(DescriptorKindFilter.Companion.getCLASSIFIERS_MASK());
        if(descriptorKindFilter1 == null) {
            return CollectionsKt.emptyList();
        }
        Iterable iterable0 = this.workerScope.getContributedDescriptors(descriptorKindFilter1, function10);
        Collection collection0 = new ArrayList();
        for(Object object0: iterable0) {
            if(object0 instanceof ClassifierDescriptorWithTypeParameters) {
                collection0.add(object0);
            }
        }
        return (List)collection0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
    public Set getFunctionNames() {
        return this.workerScope.getFunctionNames();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
    public Set getVariableNames() {
        return this.workerScope.getVariableNames();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
    public void recordLookup(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        this.workerScope.recordLookup(name0, lookupLocation0);
    }

    @Override
    public String toString() {
        return "Classes from " + this.workerScope;
    }
}

