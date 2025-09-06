package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import java.util.Collection;
import java.util.Set;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;

public abstract class AbstractScopeAdapter implements MemberScope {
    public final MemberScope getActualScope() {
        if(this.getWorkerScope() instanceof AbstractScopeAdapter) {
            MemberScope memberScope0 = this.getWorkerScope();
            Intrinsics.checkNotNull(memberScope0, "null cannot be cast to non-null type org.jetbrains.kotlin.resolve.scopes.AbstractScopeAdapter");
            return ((AbstractScopeAdapter)memberScope0).getActualScope();
        }
        return this.getWorkerScope();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Set getClassifierNames() {
        return this.getWorkerScope().getClassifierNames();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    public ClassifierDescriptor getContributedClassifier(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        return this.getWorkerScope().getContributedClassifier(name0, lookupLocation0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    public Collection getContributedDescriptors(DescriptorKindFilter descriptorKindFilter0, Function1 function10) {
        Intrinsics.checkNotNullParameter(descriptorKindFilter0, "kindFilter");
        Intrinsics.checkNotNullParameter(function10, "nameFilter");
        return this.getWorkerScope().getContributedDescriptors(descriptorKindFilter0, function10);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Collection getContributedFunctions(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        return this.getWorkerScope().getContributedFunctions(name0, lookupLocation0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Collection getContributedVariables(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        return this.getWorkerScope().getContributedVariables(name0, lookupLocation0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Set getFunctionNames() {
        return this.getWorkerScope().getFunctionNames();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Set getVariableNames() {
        return this.getWorkerScope().getVariableNames();
    }

    protected abstract MemberScope getWorkerScope();

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    public void recordLookup(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        this.getWorkerScope().recordLookup(name0, lookupLocation0);
    }
}

