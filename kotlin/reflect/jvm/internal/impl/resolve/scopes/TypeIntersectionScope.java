package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.util.collectionUtils.ScopeUtilsKt;
import kotlin.reflect.jvm.internal.impl.utils.SmartList;

public final class TypeIntersectionScope extends AbstractScopeAdapter {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        @JvmStatic
        public final MemberScope create(String s, Collection collection0) {
            Intrinsics.checkNotNullParameter(s, "message");
            Intrinsics.checkNotNullParameter(collection0, "types");
            ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection0, 10));
            for(Object object0: collection0) {
                arrayList0.add(((KotlinType)object0).getMemberScope());
            }
            SmartList smartList0 = ScopeUtilsKt.listOfNonEmptyScopes(arrayList0);
            MemberScope memberScope0 = ChainedMemberScope.Companion.createOrSingle$descriptors(s, smartList0);
            return smartList0.size() <= 1 ? memberScope0 : new TypeIntersectionScope(s, memberScope0, null);
        }
    }

    public static final Companion Companion;
    private final String debugName;
    private final MemberScope workerScope;

    static {
        TypeIntersectionScope.Companion = new Companion(null);
    }

    private TypeIntersectionScope(String s, MemberScope memberScope0) {
        this.debugName = s;
        this.workerScope = memberScope0;
    }

    public TypeIntersectionScope(String s, MemberScope memberScope0, DefaultConstructorMarker defaultConstructorMarker0) {
        this(s, memberScope0);
    }

    @JvmStatic
    public static final MemberScope create(String s, Collection collection0) {
        return TypeIntersectionScope.Companion.create(s, collection0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.AbstractScopeAdapter
    public Collection getContributedDescriptors(DescriptorKindFilter descriptorKindFilter0, Function1 function10) {
        Intrinsics.checkNotNullParameter(descriptorKindFilter0, "kindFilter");
        Intrinsics.checkNotNullParameter(function10, "nameFilter");
        Iterable iterable0 = super.getContributedDescriptors(descriptorKindFilter0, function10);
        ArrayList arrayList0 = new ArrayList();
        ArrayList arrayList1 = new ArrayList();
        for(Object object0: iterable0) {
            if(((DeclarationDescriptor)object0) instanceof CallableDescriptor) {
                arrayList0.add(object0);
            }
            else {
                arrayList1.add(object0);
            }
        }
        Pair pair0 = new Pair(arrayList0, arrayList1);
        List list0 = (List)pair0.component1();
        Intrinsics.checkNotNull(list0, "null cannot be cast to non-null type kotlin.collections.Collection<org.jetbrains.kotlin.descriptors.CallableDescriptor>");
        return CollectionsKt.plus(OverridingUtilsKt.selectMostSpecificInEachOverridableGroup(list0, kotlin.reflect.jvm.internal.impl.resolve.scopes.TypeIntersectionScope.getContributedDescriptors.2.INSTANCE), ((List)pair0.component2()));

        final class kotlin.reflect.jvm.internal.impl.resolve.scopes.TypeIntersectionScope.getContributedDescriptors.2 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.resolve.scopes.TypeIntersectionScope.getContributedDescriptors.2 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.resolve.scopes.TypeIntersectionScope.getContributedDescriptors.2.INSTANCE = new kotlin.reflect.jvm.internal.impl.resolve.scopes.TypeIntersectionScope.getContributedDescriptors.2();
            }

            kotlin.reflect.jvm.internal.impl.resolve.scopes.TypeIntersectionScope.getContributedDescriptors.2() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((CallableDescriptor)object0));
            }

            public final CallableDescriptor invoke(CallableDescriptor callableDescriptor0) {
                Intrinsics.checkNotNullParameter(callableDescriptor0, "$this$selectMostSpecificInEachOverridableGroup");
                return callableDescriptor0;
            }
        }

    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.AbstractScopeAdapter
    public Collection getContributedFunctions(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        return OverridingUtilsKt.selectMostSpecificInEachOverridableGroup(super.getContributedFunctions(name0, lookupLocation0), kotlin.reflect.jvm.internal.impl.resolve.scopes.TypeIntersectionScope.getContributedFunctions.1.INSTANCE);

        final class kotlin.reflect.jvm.internal.impl.resolve.scopes.TypeIntersectionScope.getContributedFunctions.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.resolve.scopes.TypeIntersectionScope.getContributedFunctions.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.resolve.scopes.TypeIntersectionScope.getContributedFunctions.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.resolve.scopes.TypeIntersectionScope.getContributedFunctions.1();
            }

            kotlin.reflect.jvm.internal.impl.resolve.scopes.TypeIntersectionScope.getContributedFunctions.1() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((SimpleFunctionDescriptor)object0));
            }

            public final CallableDescriptor invoke(SimpleFunctionDescriptor simpleFunctionDescriptor0) {
                Intrinsics.checkNotNullParameter(simpleFunctionDescriptor0, "$this$selectMostSpecificInEachOverridableGroup");
                return simpleFunctionDescriptor0;
            }
        }

    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.AbstractScopeAdapter
    public Collection getContributedVariables(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        return OverridingUtilsKt.selectMostSpecificInEachOverridableGroup(super.getContributedVariables(name0, lookupLocation0), kotlin.reflect.jvm.internal.impl.resolve.scopes.TypeIntersectionScope.getContributedVariables.1.INSTANCE);

        final class kotlin.reflect.jvm.internal.impl.resolve.scopes.TypeIntersectionScope.getContributedVariables.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.resolve.scopes.TypeIntersectionScope.getContributedVariables.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.resolve.scopes.TypeIntersectionScope.getContributedVariables.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.resolve.scopes.TypeIntersectionScope.getContributedVariables.1();
            }

            kotlin.reflect.jvm.internal.impl.resolve.scopes.TypeIntersectionScope.getContributedVariables.1() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((PropertyDescriptor)object0));
            }

            public final CallableDescriptor invoke(PropertyDescriptor propertyDescriptor0) {
                Intrinsics.checkNotNullParameter(propertyDescriptor0, "$this$selectMostSpecificInEachOverridableGroup");
                return propertyDescriptor0;
            }
        }

    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.AbstractScopeAdapter
    protected MemberScope getWorkerScope() {
        return this.workerScope;
    }
}

