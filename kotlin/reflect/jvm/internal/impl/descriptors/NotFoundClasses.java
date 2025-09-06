package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassDescriptorBase;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.EmptyPackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.TypeParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope.Empty;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNotNull;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.ClassTypeConstructorImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;

public final class NotFoundClasses {
    static final class ClassRequest {
        private final ClassId classId;
        private final List typeParametersCount;

        public ClassRequest(ClassId classId0, List list0) {
            Intrinsics.checkNotNullParameter(classId0, "classId");
            Intrinsics.checkNotNullParameter(list0, "typeParametersCount");
            super();
            this.classId = classId0;
            this.typeParametersCount = list0;
        }

        public final ClassId component1() {
            return this.classId;
        }

        public final List component2() {
            return this.typeParametersCount;
        }

        @Override
        public boolean equals(Object object0) {
            if(this == object0) {
                return true;
            }
            if(!(object0 instanceof ClassRequest)) {
                return false;
            }
            return Intrinsics.areEqual(this.classId, ((ClassRequest)object0).classId) ? Intrinsics.areEqual(this.typeParametersCount, ((ClassRequest)object0).typeParametersCount) : false;
        }

        @Override
        public int hashCode() {
            return this.classId.hashCode() * 0x1F + this.typeParametersCount.hashCode();
        }

        @Override
        public String toString() {
            return "ClassRequest(classId=" + this.classId + ", typeParametersCount=" + this.typeParametersCount + ')';
        }
    }

    public static final class MockClassDescriptor extends ClassDescriptorBase {
        private final List declaredTypeParameters;
        private final boolean isInner;
        private final ClassTypeConstructorImpl typeConstructor;

        public MockClassDescriptor(StorageManager storageManager0, DeclarationDescriptor declarationDescriptor0, Name name0, boolean z, int v) {
            Intrinsics.checkNotNullParameter(storageManager0, "storageManager");
            Intrinsics.checkNotNullParameter(declarationDescriptor0, "container");
            Intrinsics.checkNotNullParameter(name0, "name");
            super(storageManager0, declarationDescriptor0, name0, SourceElement.NO_SOURCE, false);
            this.isInner = z;
            Iterable iterable0 = RangesKt.until(0, v);
            ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
            Iterator iterator0 = iterable0.iterator();
            while(iterator0.hasNext()) {
                int v1 = ((IntIterator)iterator0).nextInt();
                Name name1 = Name.identifier(("T" + v1));
                arrayList0.add(TypeParameterDescriptorImpl.createWithDefaultBound(this, Annotations.Companion.getEMPTY(), false, Variance.INVARIANT, name1, v1, storageManager0));
            }
            this.declaredTypeParameters = arrayList0;
            this.typeConstructor = new ClassTypeConstructorImpl(this, TypeParameterUtilsKt.computeConstructorTypeParameters(this), SetsKt.setOf(DescriptorUtilsKt.getModule(this).getBuiltIns().getAnyType()), storageManager0);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated
        public Annotations getAnnotations() {
            return Annotations.Companion.getEMPTY();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
        public ClassDescriptor getCompanionObjectDescriptor() {
            return null;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
        public Collection getConstructors() {
            return SetsKt.emptySet();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
        public List getDeclaredTypeParameters() {
            return this.declaredTypeParameters;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
        public ClassKind getKind() {
            return ClassKind.CLASS;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
        public Modality getModality() {
            return Modality.FINAL;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
        public Collection getSealedSubclasses() {
            return CollectionsKt.emptyList();
        }

        public Empty getStaticScope() {
            return Empty.INSTANCE;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
        public MemberScope getStaticScope() {
            return this.getStaticScope();
        }

        public ClassTypeConstructorImpl getTypeConstructor() {
            return this.typeConstructor;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor
        public TypeConstructor getTypeConstructor() {
            return this.getTypeConstructor();
        }

        protected Empty getUnsubstitutedMemberScope(KotlinTypeRefiner kotlinTypeRefiner0) {
            Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "kotlinTypeRefiner");
            return Empty.INSTANCE;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleAwareClassDescriptor
        public MemberScope getUnsubstitutedMemberScope(KotlinTypeRefiner kotlinTypeRefiner0) {
            return this.getUnsubstitutedMemberScope(kotlinTypeRefiner0);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
        public ClassConstructorDescriptor getUnsubstitutedPrimaryConstructor() {
            return null;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
        public ValueClassRepresentation getValueClassRepresentation() {
            return null;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
        public DescriptorVisibility getVisibility() {
            Intrinsics.checkNotNullExpressionValue(DescriptorVisibilities.PUBLIC, "PUBLIC");
            return DescriptorVisibilities.PUBLIC;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
        public boolean isActual() {
            return false;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
        public boolean isCompanionObject() {
            return false;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
        public boolean isData() {
            return false;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
        public boolean isExpect() {
            return false;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassDescriptorBase
        public boolean isExternal() {
            return false;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
        public boolean isFun() {
            return false;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
        public boolean isInline() {
            return false;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters
        public boolean isInner() {
            return this.isInner;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
        public boolean isValue() {
            return false;
        }

        @Override
        public String toString() {
            return "class " + this.getName() + " (not found)";
        }
    }

    private final MemoizedFunctionToNotNull classes;
    private final ModuleDescriptor module;
    private final MemoizedFunctionToNotNull packageFragments;
    private final StorageManager storageManager;

    public NotFoundClasses(StorageManager storageManager0, ModuleDescriptor moduleDescriptor0) {
        Intrinsics.checkNotNullParameter(storageManager0, "storageManager");
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "module");
        super();
        this.storageManager = storageManager0;
        this.module = moduleDescriptor0;
        this.packageFragments = storageManager0.createMemoizedFunction(new Function1() {
            {
                NotFoundClasses.this = notFoundClasses0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((FqName)object0));
            }

            public final PackageFragmentDescriptor invoke(FqName fqName0) {
                Intrinsics.checkNotNullParameter(fqName0, "fqName");
                return new EmptyPackageFragmentDescriptor(NotFoundClasses.this.module, fqName0);
            }
        });
        this.classes = storageManager0.createMemoizedFunction(new Function1() {
            {
                NotFoundClasses.this = notFoundClasses0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((ClassRequest)object0));
            }

            public final ClassDescriptor invoke(ClassRequest notFoundClasses$ClassRequest0) {
                ClassOrPackageFragmentDescriptor classOrPackageFragmentDescriptor0;
                Intrinsics.checkNotNullParameter(notFoundClasses$ClassRequest0, "<name for destructuring parameter 0>");
                ClassId classId0 = notFoundClasses$ClassRequest0.component1();
                List list0 = notFoundClasses$ClassRequest0.component2();
                if(classId0.isLocal()) {
                    throw new UnsupportedOperationException("Unresolved local class: " + classId0);
                }
                ClassId classId1 = classId0.getOuterClassId();
                if(classId1 == null) {
                label_11:
                    FqName fqName0 = classId0.getPackageFqName();
                    Intrinsics.checkNotNullExpressionValue(fqName0, "classId.packageFqName");
                    classOrPackageFragmentDescriptor0 = (ClassOrPackageFragmentDescriptor)NotFoundClasses.this.packageFragments.invoke(fqName0);
                }
                else {
                    List list1 = CollectionsKt.drop(list0, 1);
                    ClassDescriptor classDescriptor0 = NotFoundClasses.this.getClass(classId1, list1);
                    if(classDescriptor0 != null) {
                        classOrPackageFragmentDescriptor0 = classDescriptor0;
                        goto label_14;
                    }
                    goto label_11;
                }
            label_14:
                boolean z = classId0.isNestedClass();
                StorageManager storageManager0 = NotFoundClasses.this.storageManager;
                Name name0 = classId0.getShortClassName();
                Intrinsics.checkNotNullExpressionValue(name0, "classId.shortClassName");
                Integer integer0 = (Integer)CollectionsKt.firstOrNull(list0);
                return integer0 == null ? new MockClassDescriptor(storageManager0, classOrPackageFragmentDescriptor0, name0, z, 0) : new MockClassDescriptor(storageManager0, classOrPackageFragmentDescriptor0, name0, z, ((int)integer0));
            }
        });
    }

    public final ClassDescriptor getClass(ClassId classId0, List list0) {
        Intrinsics.checkNotNullParameter(classId0, "classId");
        Intrinsics.checkNotNullParameter(list0, "typeParametersCount");
        ClassRequest notFoundClasses$ClassRequest0 = new ClassRequest(classId0, list0);
        return (ClassDescriptor)this.classes.invoke(notFoundClasses$ClassRequest0);
    }
}

