package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.NonReportingOverrideStrategy;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.utils.SmartList;

public abstract class GivenFunctionsMemberScope extends MemberScopeImpl {
    static final KProperty[] $$delegatedProperties;
    private final NotNullLazyValue allDescriptors$delegate;
    private final ClassDescriptor containingClass;

    static {
        GivenFunctionsMemberScope.$$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(GivenFunctionsMemberScope.class), "allDescriptors", "getAllDescriptors()Ljava/util/List;"))};
    }

    public GivenFunctionsMemberScope(StorageManager storageManager0, ClassDescriptor classDescriptor0) {
        Intrinsics.checkNotNullParameter(storageManager0, "storageManager");
        Intrinsics.checkNotNullParameter(classDescriptor0, "containingClass");
        super();
        this.containingClass = classDescriptor0;
        this.allDescriptors$delegate = storageManager0.createLazyValue(new Function0() {
            {
                GivenFunctionsMemberScope.this = givenFunctionsMemberScope0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final List invoke() {
                List list0 = GivenFunctionsMemberScope.this.computeDeclaredFunctions();
                return CollectionsKt.plus(list0, GivenFunctionsMemberScope.access$createFakeOverrides(GivenFunctionsMemberScope.this, list0));
            }
        });
    }

    public static final List access$createFakeOverrides(GivenFunctionsMemberScope givenFunctionsMemberScope0, List list0) {
        return givenFunctionsMemberScope0.createFakeOverrides(list0);
    }

    protected abstract List computeDeclaredFunctions();

    private final List createFakeOverrides(List list0) {
        List list2;
        ArrayList arrayList0 = new ArrayList(3);
        Collection collection0 = this.containingClass.getTypeConstructor().getSupertypes();
        Intrinsics.checkNotNullExpressionValue(collection0, "containingClass.typeConstructor.supertypes");
        Collection collection1 = new ArrayList();
        for(Object object0: collection0) {
            CollectionsKt.addAll(collection1, DefaultImpls.getContributedDescriptors$default(((KotlinType)object0).getMemberScope(), null, null, 3, null));
        }
        Collection collection2 = new ArrayList();
        for(Object object1: ((List)collection1)) {
            if(object1 instanceof CallableMemberDescriptor) {
                collection2.add(object1);
            }
        }
        Map map0 = new LinkedHashMap();
        for(Object object2: ((List)collection2)) {
            Name name0 = ((CallableMemberDescriptor)object2).getName();
            ArrayList arrayList1 = map0.get(name0);
            if(arrayList1 == null) {
                arrayList1 = new ArrayList();
                map0.put(name0, arrayList1);
            }
            arrayList1.add(object2);
        }
        for(Object object3: map0.entrySet()) {
            Name name1 = (Name)((Map.Entry)object3).getKey();
            Iterable iterable0 = (List)((Map.Entry)object3).getValue();
            Map map1 = new LinkedHashMap();
            for(Object object4: iterable0) {
                Boolean boolean0 = Boolean.valueOf(((CallableMemberDescriptor)object4) instanceof FunctionDescriptor);
                ArrayList arrayList2 = map1.get(boolean0);
                if(arrayList2 == null) {
                    arrayList2 = new ArrayList();
                    map1.put(boolean0, arrayList2);
                }
                arrayList2.add(object4);
            }
            for(Object object5: map1.entrySet()) {
                boolean z = ((Boolean)((Map.Entry)object5).getKey()).booleanValue();
                List list1 = (List)((Map.Entry)object5).getValue();
                OverridingUtil overridingUtil0 = OverridingUtil.DEFAULT;
                if(z) {
                    Collection collection3 = new ArrayList();
                    for(Object object6: list0) {
                        if(Intrinsics.areEqual(((FunctionDescriptor)object6).getName(), name1)) {
                            collection3.add(object6);
                        }
                    }
                    list2 = (List)collection3;
                }
                else {
                    list2 = CollectionsKt.emptyList();
                }
                kotlin.reflect.jvm.internal.impl.resolve.scopes.GivenFunctionsMemberScope.createFakeOverrides.4 givenFunctionsMemberScope$createFakeOverrides$40 = new NonReportingOverrideStrategy() {
                    @Override  // kotlin.reflect.jvm.internal.impl.resolve.OverridingStrategy
                    public void addFakeOverride(CallableMemberDescriptor callableMemberDescriptor0) {
                        Intrinsics.checkNotNullParameter(callableMemberDescriptor0, "fakeOverride");
                        OverridingUtil.resolveUnknownVisibilityForMember(callableMemberDescriptor0, null);
                        this.add(callableMemberDescriptor0);
                    }

                    @Override  // kotlin.reflect.jvm.internal.impl.resolve.NonReportingOverrideStrategy
                    protected void conflict(CallableMemberDescriptor callableMemberDescriptor0, CallableMemberDescriptor callableMemberDescriptor1) {
                        Intrinsics.checkNotNullParameter(callableMemberDescriptor0, "fromSuper");
                        Intrinsics.checkNotNullParameter(callableMemberDescriptor1, "fromCurrent");
                        throw new IllegalStateException(("Conflict in scope of " + GivenFunctionsMemberScope.this.getContainingClass() + ": " + callableMemberDescriptor0 + " vs " + callableMemberDescriptor1).toString());
                    }
                };
                overridingUtil0.generateOverridesInFunctionGroup(name1, list1, list2, this.containingClass, givenFunctionsMemberScope$createFakeOverrides$40);
            }
        }
        return kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.compact(arrayList0);
    }

    private final List getAllDescriptors() {
        return (List)StorageKt.getValue(this.allDescriptors$delegate, this, GivenFunctionsMemberScope.$$delegatedProperties[0]);
    }

    protected final ClassDescriptor getContainingClass() {
        return this.containingClass;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
    public Collection getContributedDescriptors(DescriptorKindFilter descriptorKindFilter0, Function1 function10) {
        Intrinsics.checkNotNullParameter(descriptorKindFilter0, "kindFilter");
        Intrinsics.checkNotNullParameter(function10, "nameFilter");
        return descriptorKindFilter0.acceptsKinds(DescriptorKindFilter.CALLABLES.getKindMask()) ? this.getAllDescriptors() : CollectionsKt.emptyList();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
    public Collection getContributedFunctions(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        Iterable iterable0 = this.getAllDescriptors();
        Collection collection0 = new SmartList();
        for(Object object0: iterable0) {
            if(object0 instanceof SimpleFunctionDescriptor && Intrinsics.areEqual(((SimpleFunctionDescriptor)object0).getName(), name0)) {
                collection0.add(object0);
            }
        }
        return collection0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
    public Collection getContributedVariables(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        Iterable iterable0 = this.getAllDescriptors();
        Collection collection0 = new SmartList();
        for(Object object0: iterable0) {
            if(object0 instanceof PropertyDescriptor && Intrinsics.areEqual(((PropertyDescriptor)object0).getName(), name0)) {
                collection0.add(object0);
            }
        }
        return collection0;
    }
}

