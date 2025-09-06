package kotlin.reflect.jvm.internal.impl.types.error;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;

public class ErrorScope implements MemberScope {
    private final String debugMessage;
    private final ErrorScopeKind kind;

    public ErrorScope(ErrorScopeKind errorScopeKind0, String[] arr_s) {
        Intrinsics.checkNotNullParameter(errorScopeKind0, "kind");
        Intrinsics.checkNotNullParameter(arr_s, "formatParams");
        super();
        this.kind = errorScopeKind0;
        Object[] arr_object = Arrays.copyOf(arr_s, arr_s.length);
        String s = String.format(errorScopeKind0.getDebugMessage(), Arrays.copyOf(arr_object, arr_object.length));
        Intrinsics.checkNotNullExpressionValue(s, "format(this, *args)");
        this.debugMessage = s;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Set getClassifierNames() {
        return SetsKt.emptySet();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    public ClassifierDescriptor getContributedClassifier(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        String s = String.format("<Error class: %s>", Arrays.copyOf(new Object[]{name0}, 1));
        Intrinsics.checkNotNullExpressionValue(s, "format(this, *args)");
        Name name1 = Name.special(s);
        Intrinsics.checkNotNullExpressionValue(name1, "special(ErrorEntity.ERRO…S.debugText.format(name))");
        return new ErrorClassDescriptor(name1);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    public Collection getContributedDescriptors(DescriptorKindFilter descriptorKindFilter0, Function1 function10) {
        Intrinsics.checkNotNullParameter(descriptorKindFilter0, "kindFilter");
        Intrinsics.checkNotNullParameter(function10, "nameFilter");
        return CollectionsKt.emptyList();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Collection getContributedFunctions(Name name0, LookupLocation lookupLocation0) {
        return this.getContributedFunctions(name0, lookupLocation0);
    }

    public Set getContributedFunctions(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        return SetsKt.setOf(new ErrorFunctionDescriptor(ErrorUtils.INSTANCE.getErrorClass()));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Collection getContributedVariables(Name name0, LookupLocation lookupLocation0) {
        return this.getContributedVariables(name0, lookupLocation0);
    }

    public Set getContributedVariables(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        return ErrorUtils.INSTANCE.getErrorPropertyGroup();
    }

    protected final String getDebugMessage() {
        return this.debugMessage;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Set getFunctionNames() {
        return SetsKt.emptySet();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Set getVariableNames() {
        return SetsKt.emptySet();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    public void recordLookup(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
    }

    @Override
    public String toString() {
        return "ErrorScope{" + this.debugMessage + '}';
    }
}

