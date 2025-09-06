package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.utils.SmartList;

public final class StaticScopeForKotlinEnum extends MemberScopeImpl {
    static final KProperty[] $$delegatedProperties;
    private final ClassDescriptor containingClass;
    private final NotNullLazyValue functions$delegate;
    private final NotNullLazyValue properties$delegate;

    static {
        StaticScopeForKotlinEnum.$$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(StaticScopeForKotlinEnum.class), "functions", "getFunctions()Ljava/util/List;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(StaticScopeForKotlinEnum.class), "properties", "getProperties()Ljava/util/List;"))};
    }

    public StaticScopeForKotlinEnum(StorageManager storageManager0, ClassDescriptor classDescriptor0) {
        Intrinsics.checkNotNullParameter(storageManager0, "storageManager");
        Intrinsics.checkNotNullParameter(classDescriptor0, "containingClass");
        super();
        this.containingClass = classDescriptor0;
        classDescriptor0.getKind();
        this.functions$delegate = storageManager0.createLazyValue(new Function0() {
            {
                StaticScopeForKotlinEnum.this = staticScopeForKotlinEnum0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final List invoke() {
                return CollectionsKt.listOf(new SimpleFunctionDescriptor[]{DescriptorFactory.createEnumValueOfMethod(StaticScopeForKotlinEnum.access$getContainingClass$p(StaticScopeForKotlinEnum.this)), DescriptorFactory.createEnumValuesMethod(StaticScopeForKotlinEnum.access$getContainingClass$p(StaticScopeForKotlinEnum.this))});
            }
        });
        this.properties$delegate = storageManager0.createLazyValue(new Function0() {
            {
                StaticScopeForKotlinEnum.this = staticScopeForKotlinEnum0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final List invoke() {
                return CollectionsKt.listOfNotNull(DescriptorFactory.createEnumEntriesProperty(StaticScopeForKotlinEnum.access$getContainingClass$p(StaticScopeForKotlinEnum.this)));
            }
        });
    }

    public static final ClassDescriptor access$getContainingClass$p(StaticScopeForKotlinEnum staticScopeForKotlinEnum0) {
        return staticScopeForKotlinEnum0.containingClass;
    }

    public Void getContributedClassifier(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
    public ClassifierDescriptor getContributedClassifier(Name name0, LookupLocation lookupLocation0) {
        return (ClassifierDescriptor)this.getContributedClassifier(name0, lookupLocation0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
    public Collection getContributedDescriptors(DescriptorKindFilter descriptorKindFilter0, Function1 function10) {
        return this.getContributedDescriptors(descriptorKindFilter0, function10);
    }

    public List getContributedDescriptors(DescriptorKindFilter descriptorKindFilter0, Function1 function10) {
        Intrinsics.checkNotNullParameter(descriptorKindFilter0, "kindFilter");
        Intrinsics.checkNotNullParameter(function10, "nameFilter");
        return CollectionsKt.plus(this.getFunctions(), this.getProperties());
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
    public Collection getContributedFunctions(Name name0, LookupLocation lookupLocation0) {
        return this.getContributedFunctions(name0, lookupLocation0);
    }

    public SmartList getContributedFunctions(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        Iterable iterable0 = this.getFunctions();
        Collection collection0 = new SmartList();
        for(Object object0: iterable0) {
            if(Intrinsics.areEqual(((SimpleFunctionDescriptor)object0).getName(), name0)) {
                collection0.add(object0);
            }
        }
        return (SmartList)collection0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
    public Collection getContributedVariables(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        Iterable iterable0 = this.getProperties();
        Collection collection0 = new SmartList();
        for(Object object0: iterable0) {
            if(Intrinsics.areEqual(((PropertyDescriptor)object0).getName(), name0)) {
                collection0.add(object0);
            }
        }
        return collection0;
    }

    private final List getFunctions() {
        return (List)StorageKt.getValue(this.functions$delegate, this, StaticScopeForKotlinEnum.$$delegatedProperties[0]);
    }

    private final List getProperties() {
        return (List)StorageKt.getValue(this.properties$delegate, this, StaticScopeForKotlinEnum.$$delegatedProperties[1]);
    }
}

