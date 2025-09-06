package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.load.java.components.DescriptorResolverUtils;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaClassDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.UtilKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMember;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.utils.DFS.AbstractNodeHandler;
import kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler;
import kotlin.reflect.jvm.internal.impl.utils.DFS;
import kotlin.sequences.SequencesKt;

public final class LazyJavaStaticClassScope extends LazyJavaStaticScope {
    private final JavaClass jClass;
    private final JavaClassDescriptor ownerDescriptor;

    public LazyJavaStaticClassScope(LazyJavaResolverContext lazyJavaResolverContext0, JavaClass javaClass0, JavaClassDescriptor javaClassDescriptor0) {
        Intrinsics.checkNotNullParameter(lazyJavaResolverContext0, "c");
        Intrinsics.checkNotNullParameter(javaClass0, "jClass");
        Intrinsics.checkNotNullParameter(javaClassDescriptor0, "ownerDescriptor");
        super(lazyJavaResolverContext0);
        this.jClass = javaClass0;
        this.ownerDescriptor = javaClassDescriptor0;
    }

    // 检测为 Lambda 实现
    static Iterable accessor$LazyJavaStaticClassScope$lambda0(ClassDescriptor classDescriptor0) [...]

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected Set computeClassNames(DescriptorKindFilter descriptorKindFilter0, Function1 function10) {
        Intrinsics.checkNotNullParameter(descriptorKindFilter0, "kindFilter");
        return SetsKt.emptySet();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected Set computeFunctionNames(DescriptorKindFilter descriptorKindFilter0, Function1 function10) {
        Intrinsics.checkNotNullParameter(descriptorKindFilter0, "kindFilter");
        Set set0 = CollectionsKt.toMutableSet(((DeclaredMemberIndex)this.getDeclaredMemberIndex().invoke()).getMethodNames());
        LazyJavaStaticClassScope lazyJavaStaticClassScope0 = UtilKt.getParentJavaStaticClassScope(this.getOwnerDescriptor());
        Set set1 = lazyJavaStaticClassScope0 == null ? null : lazyJavaStaticClassScope0.getFunctionNames();
        if(set1 == null) {
            set1 = SetsKt.emptySet();
        }
        set0.addAll(set1);
        if(this.jClass.isEnum()) {
            set0.addAll(CollectionsKt.listOf(new Name[]{StandardNames.ENUM_VALUE_OF, StandardNames.ENUM_VALUES}));
        }
        LazyJavaResolverContext lazyJavaResolverContext0 = this.getC();
        set0.addAll(this.getC().getComponents().getSyntheticPartsProvider().getStaticFunctionNames(lazyJavaResolverContext0, this.getOwnerDescriptor()));
        return set0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected void computeImplicitlyDeclaredFunctions(Collection collection0, Name name0) {
        Intrinsics.checkNotNullParameter(collection0, "result");
        Intrinsics.checkNotNullParameter(name0, "name");
        LazyJavaResolverContext lazyJavaResolverContext0 = this.getC();
        this.getC().getComponents().getSyntheticPartsProvider().generateStaticFunctions(lazyJavaResolverContext0, this.getOwnerDescriptor(), name0, collection0);
    }

    protected ClassDeclaredMemberIndex computeMemberIndex() {
        return new ClassDeclaredMemberIndex(this.jClass, kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaStaticClassScope.computeMemberIndex.1.INSTANCE);

        final class kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaStaticClassScope.computeMemberIndex.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaStaticClassScope.computeMemberIndex.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaStaticClassScope.computeMemberIndex.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaStaticClassScope.computeMemberIndex.1();
            }

            kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaStaticClassScope.computeMemberIndex.1() {
                super(1);
            }

            public final Boolean invoke(JavaMember javaMember0) {
                Intrinsics.checkNotNullParameter(javaMember0, "it");
                return Boolean.valueOf(javaMember0.isStatic());
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((JavaMember)object0));
            }
        }

    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    public DeclaredMemberIndex computeMemberIndex() {
        return this.computeMemberIndex();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected void computeNonDeclaredFunctions(Collection collection0, Name name0) {
        Intrinsics.checkNotNullParameter(collection0, "result");
        Intrinsics.checkNotNullParameter(name0, "name");
        Collection collection1 = DescriptorResolverUtils.resolveOverridesForStaticMembers(name0, this.getStaticFunctionsFromJavaSuperClasses(name0, this.getOwnerDescriptor()), collection0, this.getOwnerDescriptor(), this.getC().getComponents().getErrorReporter(), this.getC().getComponents().getKotlinTypeChecker().getOverridingUtil());
        Intrinsics.checkNotNullExpressionValue(collection1, "resolveOverridesForStati…rridingUtil\n            )");
        collection0.addAll(collection1);
        if(this.jClass.isEnum()) {
            if(Intrinsics.areEqual(name0, StandardNames.ENUM_VALUE_OF)) {
                SimpleFunctionDescriptor simpleFunctionDescriptor0 = DescriptorFactory.createEnumValueOfMethod(this.getOwnerDescriptor());
                Intrinsics.checkNotNullExpressionValue(simpleFunctionDescriptor0, "createEnumValueOfMethod(ownerDescriptor)");
                collection0.add(simpleFunctionDescriptor0);
                return;
            }
            if(Intrinsics.areEqual(name0, StandardNames.ENUM_VALUES)) {
                SimpleFunctionDescriptor simpleFunctionDescriptor1 = DescriptorFactory.createEnumValuesMethod(this.getOwnerDescriptor());
                Intrinsics.checkNotNullExpressionValue(simpleFunctionDescriptor1, "createEnumValuesMethod(ownerDescriptor)");
                collection0.add(simpleFunctionDescriptor1);
            }
        }
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaStaticScope
    protected void computeNonDeclaredProperties(Name name0, Collection collection0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(collection0, "result");
        Set set0 = this.flatMapJavaStaticSupertypesScopes(this.getOwnerDescriptor(), new LinkedHashSet(), new Function1() {
            final Name $name;

            {
                this.$name = name0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((MemberScope)object0));
            }

            public final Collection invoke(MemberScope memberScope0) {
                Intrinsics.checkNotNullParameter(memberScope0, "it");
                return memberScope0.getContributedVariables(this.$name, NoLookupLocation.WHEN_GET_SUPER_MEMBERS);
            }
        });
        if(collection0.isEmpty()) {
            Map map0 = new LinkedHashMap();
            for(Object object0: set0) {
                PropertyDescriptor propertyDescriptor0 = this.getRealOriginal(((PropertyDescriptor)object0));
                ArrayList arrayList0 = map0.get(propertyDescriptor0);
                if(arrayList0 == null) {
                    arrayList0 = new ArrayList();
                    map0.put(propertyDescriptor0, arrayList0);
                }
                arrayList0.add(object0);
            }
            Collection collection2 = new ArrayList();
            for(Object object1: map0.entrySet()) {
                Collection collection3 = DescriptorResolverUtils.resolveOverridesForStaticMembers(name0, ((Collection)((Map.Entry)object1).getValue()), collection0, this.getOwnerDescriptor(), this.getC().getComponents().getErrorReporter(), this.getC().getComponents().getKotlinTypeChecker().getOverridingUtil());
                Intrinsics.checkNotNullExpressionValue(collection3, "resolveOverridesForStati…ingUtil\n                )");
                CollectionsKt.addAll(collection2, collection3);
            }
            collection0.addAll(((List)collection2));
        }
        else {
            Collection collection1 = DescriptorResolverUtils.resolveOverridesForStaticMembers(name0, set0, collection0, this.getOwnerDescriptor(), this.getC().getComponents().getErrorReporter(), this.getC().getComponents().getKotlinTypeChecker().getOverridingUtil());
            Intrinsics.checkNotNullExpressionValue(collection1, "resolveOverridesForStati…ingUtil\n                )");
            collection0.addAll(collection1);
        }
        if(this.jClass.isEnum() && Intrinsics.areEqual(name0, StandardNames.ENUM_ENTRIES)) {
            kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.addIfNotNull(collection0, DescriptorFactory.createEnumEntriesProperty(this.getOwnerDescriptor()));
        }
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected Set computePropertyNames(DescriptorKindFilter descriptorKindFilter0, Function1 function10) {
        Intrinsics.checkNotNullParameter(descriptorKindFilter0, "kindFilter");
        Set set0 = CollectionsKt.toMutableSet(((DeclaredMemberIndex)this.getDeclaredMemberIndex().invoke()).getFieldNames());
        this.flatMapJavaStaticSupertypesScopes(this.getOwnerDescriptor(), set0, kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaStaticClassScope.computePropertyNames.1.1.INSTANCE);
        if(this.jClass.isEnum()) {
            set0.add(StandardNames.ENUM_ENTRIES);
        }
        return set0;

        final class kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaStaticClassScope.computePropertyNames.1.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaStaticClassScope.computePropertyNames.1.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaStaticClassScope.computePropertyNames.1.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaStaticClassScope.computePropertyNames.1.1();
            }

            kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaStaticClassScope.computePropertyNames.1.1() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((MemberScope)object0));
            }

            public final Collection invoke(MemberScope memberScope0) {
                Intrinsics.checkNotNullParameter(memberScope0, "it");
                return memberScope0.getVariableNames();
            }
        }

    }

    private final Set flatMapJavaStaticSupertypesScopes(ClassDescriptor classDescriptor0, Set set0, Function1 function10) {
        Collection collection0 = CollectionsKt.listOf(classDescriptor0);
        NodeHandler dFS$NodeHandler0 = new AbstractNodeHandler() {
            @Override  // kotlin.reflect.jvm.internal.impl.utils.DFS$AbstractNodeHandler
            public boolean beforeChildren(Object object0) {
                return this.beforeChildren(((ClassDescriptor)object0));
            }

            public boolean beforeChildren(ClassDescriptor classDescriptor0) {
                Intrinsics.checkNotNullParameter(classDescriptor0, "current");
                if(classDescriptor0 == set0) {
                    return true;
                }
                MemberScope memberScope0 = classDescriptor0.getStaticScope();
                Intrinsics.checkNotNullExpressionValue(memberScope0, "current.staticScope");
                if(memberScope0 instanceof LazyJavaStaticScope) {
                    Collection collection0 = (Collection)this.$onJavaStaticScope.invoke(memberScope0);
                    function10.addAll(collection0);
                    return false;
                }
                return true;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.utils.DFS$NodeHandler
            public Object result() {
                return Unit.INSTANCE;
            }

            public void result() {
            }
        };
        DFS.dfs(collection0, (ClassDescriptor classDescriptor0) -> LazyJavaStaticClassScope.flatMapJavaStaticSupertypesScopes$lambda$6(classDescriptor0), dFS$NodeHandler0);
        return set0;

        final class kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaStaticClassScope.flatMapJavaStaticSupertypesScopes.1.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaStaticClassScope.flatMapJavaStaticSupertypesScopes.1.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaStaticClassScope.flatMapJavaStaticSupertypesScopes.1.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaStaticClassScope.flatMapJavaStaticSupertypesScopes.1.1();
            }

            kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaStaticClassScope.flatMapJavaStaticSupertypesScopes.1.1() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((KotlinType)object0));
            }

            public final ClassDescriptor invoke(KotlinType kotlinType0) {
                ClassifierDescriptor classifierDescriptor0 = kotlinType0.getConstructor().getDeclarationDescriptor();
                return classifierDescriptor0 instanceof ClassDescriptor ? ((ClassDescriptor)classifierDescriptor0) : null;
            }
        }

    }

    private static final Iterable flatMapJavaStaticSupertypesScopes$lambda$6(ClassDescriptor classDescriptor0) {
        Collection collection0 = classDescriptor0.getTypeConstructor().getSupertypes();
        Intrinsics.checkNotNullExpressionValue(collection0, "it.typeConstructor.supertypes");
        return SequencesKt.asIterable(SequencesKt.mapNotNull(CollectionsKt.asSequence(collection0), kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaStaticClassScope.flatMapJavaStaticSupertypesScopes.1.1.INSTANCE));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
    public ClassifierDescriptor getContributedClassifier(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    public DeclarationDescriptor getOwnerDescriptor() {
        return this.getOwnerDescriptor();
    }

    protected JavaClassDescriptor getOwnerDescriptor() {
        return this.ownerDescriptor;
    }

    private final PropertyDescriptor getRealOriginal(PropertyDescriptor propertyDescriptor0) {
        if(propertyDescriptor0.getKind().isReal()) {
            return propertyDescriptor0;
        }
        Collection collection0 = propertyDescriptor0.getOverriddenDescriptors();
        Intrinsics.checkNotNullExpressionValue(collection0, "this.overriddenDescriptors");
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection0, 10));
        for(Object object0: collection0) {
            Intrinsics.checkNotNullExpressionValue(((PropertyDescriptor)object0), "it");
            arrayList0.add(this.getRealOriginal(((PropertyDescriptor)object0)));
        }
        return (PropertyDescriptor)CollectionsKt.single(CollectionsKt.distinct(arrayList0));
    }

    private final Set getStaticFunctionsFromJavaSuperClasses(Name name0, ClassDescriptor classDescriptor0) {
        LazyJavaStaticClassScope lazyJavaStaticClassScope0 = UtilKt.getParentJavaStaticClassScope(classDescriptor0);
        return lazyJavaStaticClassScope0 == null ? SetsKt.emptySet() : CollectionsKt.toSet(lazyJavaStaticClassScope0.getContributedFunctions(name0, NoLookupLocation.WHEN_GET_SUPER_MEMBERS));
    }
}

