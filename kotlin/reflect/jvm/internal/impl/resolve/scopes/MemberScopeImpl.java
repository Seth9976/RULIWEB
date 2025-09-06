package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.utils.FunctionsKt;

public abstract class MemberScopeImpl implements MemberScope {
    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Set getClassifierNames() {
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    public ClassifierDescriptor getContributedClassifier(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    public Collection getContributedDescriptors(DescriptorKindFilter descriptorKindFilter0, Function1 function10) {
        Intrinsics.checkNotNullParameter(descriptorKindFilter0, "kindFilter");
        Intrinsics.checkNotNullParameter(function10, "nameFilter");
        return CollectionsKt.emptyList();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Collection getContributedFunctions(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        return CollectionsKt.emptyList();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Collection getContributedVariables(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        return CollectionsKt.emptyList();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Set getFunctionNames() {
        Iterable iterable0 = this.getContributedDescriptors(DescriptorKindFilter.FUNCTIONS, FunctionsKt.alwaysTrue());
        Collection collection0 = new LinkedHashSet();
        for(Object object0: iterable0) {
            if(object0 instanceof SimpleFunctionDescriptor) {
                Name name0 = ((SimpleFunctionDescriptor)object0).getName();
                Intrinsics.checkNotNullExpressionValue(name0, "it.name");
                collection0.add(name0);
            }
        }
        return (Set)collection0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Set getVariableNames() {
        Iterable iterable0 = this.getContributedDescriptors(DescriptorKindFilter.VARIABLES, FunctionsKt.alwaysTrue());
        Collection collection0 = new LinkedHashSet();
        for(Object object0: iterable0) {
            if(object0 instanceof SimpleFunctionDescriptor) {
                Name name0 = ((SimpleFunctionDescriptor)object0).getName();
                Intrinsics.checkNotNullExpressionValue(name0, "it.name");
                collection0.add(name0);
            }
        }
        return (Set)collection0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    public void recordLookup(Name name0, LookupLocation lookupLocation0) {
        DefaultImpls.recordLookup(this, name0, lookupLocation0);
    }
}

