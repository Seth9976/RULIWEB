package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters;
import kotlin.reflect.jvm.internal.impl.incremental.UtilsKt;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaPackage;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeKt;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.util.collectionUtils.ScopeUtilsKt;

public final class JvmPackageScope implements MemberScope {
    static final KProperty[] $$delegatedProperties;
    private final LazyJavaResolverContext c;
    private final LazyJavaPackageScope javaScope;
    private final NotNullLazyValue kotlinScopes$delegate;
    private final LazyJavaPackageFragment packageFragment;

    static {
        JvmPackageScope.$$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(JvmPackageScope.class), "kotlinScopes", "getKotlinScopes()[Lorg/jetbrains/kotlin/resolve/scopes/MemberScope;"))};
    }

    public JvmPackageScope(LazyJavaResolverContext lazyJavaResolverContext0, JavaPackage javaPackage0, LazyJavaPackageFragment lazyJavaPackageFragment0) {
        Intrinsics.checkNotNullParameter(lazyJavaResolverContext0, "c");
        Intrinsics.checkNotNullParameter(javaPackage0, "jPackage");
        Intrinsics.checkNotNullParameter(lazyJavaPackageFragment0, "packageFragment");
        super();
        this.c = lazyJavaResolverContext0;
        this.packageFragment = lazyJavaPackageFragment0;
        this.javaScope = new LazyJavaPackageScope(lazyJavaResolverContext0, javaPackage0, lazyJavaPackageFragment0);
        this.kotlinScopes$delegate = lazyJavaResolverContext0.getStorageManager().createLazyValue(new Function0() {
            {
                JvmPackageScope.this = jvmPackageScope0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final MemberScope[] invoke() {
                Iterable iterable0 = JvmPackageScope.this.packageFragment.getBinaryClasses$descriptors_jvm().values();
                JvmPackageScope jvmPackageScope0 = JvmPackageScope.this;
                Collection collection0 = new ArrayList();
                for(Object object0: iterable0) {
                    MemberScope memberScope0 = jvmPackageScope0.c.getComponents().getDeserializedDescriptorResolver().createKotlinPackagePartScope(jvmPackageScope0.packageFragment, ((KotlinJvmBinaryClass)object0));
                    if(memberScope0 != null) {
                        collection0.add(memberScope0);
                    }
                }
                return (MemberScope[])ScopeUtilsKt.listOfNonEmptyScopes(((List)collection0)).toArray(new MemberScope[0]);
            }
        });
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Set getClassifierNames() {
        Set set0 = MemberScopeKt.flatMapClassifierNamesOrNull(ArraysKt.asIterable(this.getKotlinScopes()));
        if(set0 != null) {
            set0.addAll(this.javaScope.getClassifierNames());
            return set0;
        }
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    public ClassifierDescriptor getContributedClassifier(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        this.recordLookup(name0, lookupLocation0);
        ClassDescriptor classDescriptor0 = this.javaScope.getContributedClassifier(name0, lookupLocation0);
        if(classDescriptor0 != null) {
            return classDescriptor0;
        }
        MemberScope[] arr_memberScope = this.getKotlinScopes();
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
        MemberScope[] arr_memberScope = this.getKotlinScopes();
        Collection collection0 = this.javaScope.getContributedDescriptors(descriptorKindFilter0, function10);
        for(int v = 0; v < arr_memberScope.length; ++v) {
            collection0 = ScopeUtilsKt.concat(collection0, arr_memberScope[v].getContributedDescriptors(descriptorKindFilter0, function10));
        }
        return collection0 == null ? SetsKt.emptySet() : collection0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Collection getContributedFunctions(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        this.recordLookup(name0, lookupLocation0);
        MemberScope[] arr_memberScope = this.getKotlinScopes();
        Collection collection0 = this.javaScope.getContributedFunctions(name0, lookupLocation0);
        for(int v = 0; v < arr_memberScope.length; ++v) {
            collection0 = ScopeUtilsKt.concat(collection0, arr_memberScope[v].getContributedFunctions(name0, lookupLocation0));
        }
        return collection0 == null ? SetsKt.emptySet() : collection0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Collection getContributedVariables(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        this.recordLookup(name0, lookupLocation0);
        MemberScope[] arr_memberScope = this.getKotlinScopes();
        Collection collection0 = this.javaScope.getContributedVariables(name0, lookupLocation0);
        for(int v = 0; v < arr_memberScope.length; ++v) {
            collection0 = ScopeUtilsKt.concat(collection0, arr_memberScope[v].getContributedVariables(name0, lookupLocation0));
        }
        return collection0 == null ? SetsKt.emptySet() : collection0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Set getFunctionNames() {
        MemberScope[] arr_memberScope = this.getKotlinScopes();
        Collection collection0 = new LinkedHashSet();
        for(int v = 0; v < arr_memberScope.length; ++v) {
            CollectionsKt.addAll(collection0, arr_memberScope[v].getFunctionNames());
        }
        ((Set)collection0).addAll(this.javaScope.getFunctionNames());
        return (Set)collection0;
    }

    public final LazyJavaPackageScope getJavaScope$descriptors_jvm() {
        return this.javaScope;
    }

    private final MemberScope[] getKotlinScopes() {
        return (MemberScope[])StorageKt.getValue(this.kotlinScopes$delegate, this, JvmPackageScope.$$delegatedProperties[0]);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Set getVariableNames() {
        MemberScope[] arr_memberScope = this.getKotlinScopes();
        Collection collection0 = new LinkedHashSet();
        for(int v = 0; v < arr_memberScope.length; ++v) {
            CollectionsKt.addAll(collection0, arr_memberScope[v].getVariableNames());
        }
        ((Set)collection0).addAll(this.javaScope.getVariableNames());
        return (Set)collection0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    public void recordLookup(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        UtilsKt.record(this.c.getComponents().getLookupTracker(), lookupLocation0, this.packageFragment, name0);
    }

    @Override
    public String toString() {
        return "scope for " + this.packageFragment;
    }
}

