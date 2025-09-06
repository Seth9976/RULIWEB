package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.util.collectionUtils.ScopeUtilsKt;
import kotlin.reflect.jvm.internal.impl.utils.SmartList;

public final class ChainedMemberScope implements MemberScope {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final MemberScope create(String s, Iterable iterable0) {
            Intrinsics.checkNotNullParameter(s, "debugName");
            Intrinsics.checkNotNullParameter(iterable0, "scopes");
            SmartList smartList0 = new SmartList();
            for(Object object0: iterable0) {
                MemberScope memberScope0 = (MemberScope)object0;
                if(memberScope0 == Empty.INSTANCE) {
                }
                else if(memberScope0 instanceof ChainedMemberScope) {
                    CollectionsKt.addAll(smartList0, ((ChainedMemberScope)memberScope0).scopes);
                }
                else {
                    smartList0.add(memberScope0);
                }
            }
            return this.createOrSingle$descriptors(s, smartList0);
        }

        public final MemberScope createOrSingle$descriptors(String s, List list0) {
            Intrinsics.checkNotNullParameter(s, "debugName");
            Intrinsics.checkNotNullParameter(list0, "scopes");
            switch(list0.size()) {
                case 0: {
                    return Empty.INSTANCE;
                }
                case 1: {
                    return (MemberScope)list0.get(0);
                }
                default: {
                    return new ChainedMemberScope(s, ((MemberScope[])list0.toArray(new MemberScope[0])), null);
                }
            }
        }
    }

    public static final Companion Companion;
    private final String debugName;
    private final MemberScope[] scopes;

    static {
        ChainedMemberScope.Companion = new Companion(null);
    }

    private ChainedMemberScope(String s, MemberScope[] arr_memberScope) {
        this.debugName = s;
        this.scopes = arr_memberScope;
    }

    public ChainedMemberScope(String s, MemberScope[] arr_memberScope, DefaultConstructorMarker defaultConstructorMarker0) {
        this(s, arr_memberScope);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Set getClassifierNames() {
        return MemberScopeKt.flatMapClassifierNamesOrNull(ArraysKt.asIterable(this.scopes));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    public ClassifierDescriptor getContributedClassifier(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        MemberScope[] arr_memberScope = this.scopes;
        ClassifierDescriptor classifierDescriptor0 = null;
        for(int v = 0; v < arr_memberScope.length; ++v) {
            ClassifierDescriptor classifierDescriptor1 = arr_memberScope[v].getContributedClassifier(name0, lookupLocation0);
            if(classifierDescriptor1 != null) {
                if(!(classifierDescriptor1 instanceof ClassifierDescriptorWithTypeParameters) || !((ClassifierDescriptorWithTypeParameters)classifierDescriptor1).isExpect()) {
                    return classifierDescriptor1;
                }
                if(classifierDescriptor0 == null) {
                    classifierDescriptor0 = classifierDescriptor1;
                }
            }
        }
        return classifierDescriptor0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    public Collection getContributedDescriptors(DescriptorKindFilter descriptorKindFilter0, Function1 function10) {
        Intrinsics.checkNotNullParameter(descriptorKindFilter0, "kindFilter");
        Intrinsics.checkNotNullParameter(function10, "nameFilter");
        MemberScope[] arr_memberScope = this.scopes;
        switch(arr_memberScope.length) {
            case 0: {
                return CollectionsKt.emptyList();
            }
            case 1: {
                return arr_memberScope[0].getContributedDescriptors(descriptorKindFilter0, function10);
            }
            default: {
                Collection collection0 = null;
                for(int v = 0; v < arr_memberScope.length; ++v) {
                    collection0 = ScopeUtilsKt.concat(collection0, arr_memberScope[v].getContributedDescriptors(descriptorKindFilter0, function10));
                }
                return collection0 == null ? SetsKt.emptySet() : collection0;
            }
        }
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Collection getContributedFunctions(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        MemberScope[] arr_memberScope = this.scopes;
        switch(arr_memberScope.length) {
            case 0: {
                return CollectionsKt.emptyList();
            }
            case 1: {
                return arr_memberScope[0].getContributedFunctions(name0, lookupLocation0);
            }
            default: {
                Collection collection0 = null;
                for(int v = 0; v < arr_memberScope.length; ++v) {
                    collection0 = ScopeUtilsKt.concat(collection0, arr_memberScope[v].getContributedFunctions(name0, lookupLocation0));
                }
                return collection0 == null ? SetsKt.emptySet() : collection0;
            }
        }
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Collection getContributedVariables(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        MemberScope[] arr_memberScope = this.scopes;
        switch(arr_memberScope.length) {
            case 0: {
                return CollectionsKt.emptyList();
            }
            case 1: {
                return arr_memberScope[0].getContributedVariables(name0, lookupLocation0);
            }
            default: {
                Collection collection0 = null;
                for(int v = 0; v < arr_memberScope.length; ++v) {
                    collection0 = ScopeUtilsKt.concat(collection0, arr_memberScope[v].getContributedVariables(name0, lookupLocation0));
                }
                return collection0 == null ? SetsKt.emptySet() : collection0;
            }
        }
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Set getFunctionNames() {
        MemberScope[] arr_memberScope = this.scopes;
        Collection collection0 = new LinkedHashSet();
        for(int v = 0; v < arr_memberScope.length; ++v) {
            CollectionsKt.addAll(collection0, arr_memberScope[v].getFunctionNames());
        }
        return (Set)collection0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Set getVariableNames() {
        MemberScope[] arr_memberScope = this.scopes;
        Collection collection0 = new LinkedHashSet();
        for(int v = 0; v < arr_memberScope.length; ++v) {
            CollectionsKt.addAll(collection0, arr_memberScope[v].getVariableNames());
        }
        return (Set)collection0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    public void recordLookup(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        MemberScope[] arr_memberScope = this.scopes;
        for(int v = 0; v < arr_memberScope.length; ++v) {
            arr_memberScope[v].recordLookup(name0, lookupLocation0);
        }
    }

    @Override
    public String toString() {
        return this.debugName;
    }
}

