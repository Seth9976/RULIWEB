package kotlin.reflect.jvm.internal.impl.builtins.functions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker.EMPTY;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueClassRepresentation;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.TypeParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope.Empty;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.AbstractClassTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.TypeAttributes;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;

public final class FunctionClassDescriptor extends AbstractClassDescriptor {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    final class FunctionTypeConstructor extends AbstractClassTypeConstructor {
        public final class WhenMappings {
            public static final int[] $EnumSwitchMapping$0;

            static {
                int[] arr_v = new int[FunctionClassKind.values().length];
                try {
                    arr_v[FunctionClassKind.Function.ordinal()] = 1;
                }
                catch(NoSuchFieldError unused_ex) {
                }
                try {
                    arr_v[FunctionClassKind.KFunction.ordinal()] = 2;
                }
                catch(NoSuchFieldError unused_ex) {
                }
                try {
                    arr_v[FunctionClassKind.SuspendFunction.ordinal()] = 3;
                }
                catch(NoSuchFieldError unused_ex) {
                }
                try {
                    arr_v[FunctionClassKind.KSuspendFunction.ordinal()] = 4;
                }
                catch(NoSuchFieldError unused_ex) {
                }
                WhenMappings.$EnumSwitchMapping$0 = arr_v;
            }
        }

        public FunctionTypeConstructor() {
            super(functionClassDescriptor0.storageManager);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor
        protected Collection computeSupertypes() {
            List list0;
            switch(WhenMappings.$EnumSwitchMapping$0[FunctionClassDescriptor.this.getFunctionKind().ordinal()]) {
                case 1: {
                    list0 = CollectionsKt.listOf(FunctionClassDescriptor.functionClassId);
                    break;
                }
                case 2: {
                    ClassId[] arr_classId = new ClassId[2];
                    arr_classId[0] = FunctionClassDescriptor.kFunctionClassId;
                    Name name0 = FunctionClassKind.Function.numberedClassName(FunctionClassDescriptor.this.getArity());
                    arr_classId[1] = new ClassId(StandardNames.BUILT_INS_PACKAGE_FQ_NAME, name0);
                    list0 = CollectionsKt.listOf(arr_classId);
                    break;
                }
                case 3: {
                    list0 = CollectionsKt.listOf(FunctionClassDescriptor.functionClassId);
                    break;
                }
                case 4: {
                    ClassId[] arr_classId1 = new ClassId[2];
                    arr_classId1[0] = FunctionClassDescriptor.kFunctionClassId;
                    Name name1 = FunctionClassKind.SuspendFunction.numberedClassName(FunctionClassDescriptor.this.getArity());
                    arr_classId1[1] = new ClassId(StandardNames.COROUTINES_PACKAGE_FQ_NAME, name1);
                    list0 = CollectionsKt.listOf(arr_classId1);
                    break;
                }
                default: {
                    throw new NoWhenBranchMatchedException();
                }
            }
            ModuleDescriptor moduleDescriptor0 = FunctionClassDescriptor.this.containingDeclaration.getContainingDeclaration();
            ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
            for(Object object0: list0) {
                ClassId classId0 = (ClassId)object0;
                ClassDescriptor classDescriptor0 = FindClassInModuleKt.findClassAcrossModuleDependencies(moduleDescriptor0, classId0);
                if(classDescriptor0 == null) {
                    throw new IllegalStateException(("Built-in class " + classId0 + " not found").toString());
                }
                Iterable iterable0 = CollectionsKt.takeLast(this.getParameters(), classDescriptor0.getTypeConstructor().getParameters().size());
                Collection collection0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
                for(Object object1: iterable0) {
                    collection0.add(new TypeProjectionImpl(((TypeParameterDescriptor)object1).getDefaultType()));
                }
                arrayList0.add(KotlinTypeFactory.simpleNotNullType(TypeAttributes.Companion.getEmpty(), classDescriptor0, ((List)collection0)));
            }
            return CollectionsKt.toList(arrayList0);
        }

        public FunctionClassDescriptor getDeclarationDescriptor() {
            return FunctionClassDescriptor.this;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.AbstractClassTypeConstructor
        public ClassDescriptor getDeclarationDescriptor() {
            return this.getDeclarationDescriptor();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.AbstractClassTypeConstructor
        public ClassifierDescriptor getDeclarationDescriptor() {
            return this.getDeclarationDescriptor();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        public List getParameters() {
            return FunctionClassDescriptor.this.parameters;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor
        protected SupertypeLoopChecker getSupertypeLoopChecker() {
            return EMPTY.INSTANCE;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        public boolean isDenotable() {
            return true;
        }

        @Override
        public String toString() {
            return this.getDeclarationDescriptor().toString();
        }
    }

    public static final Companion Companion;
    private final int arity;
    private final PackageFragmentDescriptor containingDeclaration;
    private static final ClassId functionClassId;
    private final FunctionClassKind functionKind;
    private static final ClassId kFunctionClassId;
    private final FunctionClassScope memberScope;
    private final List parameters;
    private final StorageManager storageManager;
    private final FunctionTypeConstructor typeConstructor;

    static {
        FunctionClassDescriptor.Companion = new Companion(null);
        Name name0 = Name.identifier("Function");
        FunctionClassDescriptor.functionClassId = new ClassId(StandardNames.BUILT_INS_PACKAGE_FQ_NAME, name0);
        Name name1 = Name.identifier("KFunction");
        FunctionClassDescriptor.kFunctionClassId = new ClassId(StandardNames.KOTLIN_REFLECT_FQ_NAME, name1);
    }

    public FunctionClassDescriptor(StorageManager storageManager0, PackageFragmentDescriptor packageFragmentDescriptor0, FunctionClassKind functionClassKind0, int v) {
        Intrinsics.checkNotNullParameter(storageManager0, "storageManager");
        Intrinsics.checkNotNullParameter(packageFragmentDescriptor0, "containingDeclaration");
        Intrinsics.checkNotNullParameter(functionClassKind0, "functionKind");
        super(storageManager0, functionClassKind0.numberedClassName(v));
        this.storageManager = storageManager0;
        this.containingDeclaration = packageFragmentDescriptor0;
        this.functionKind = functionClassKind0;
        this.arity = v;
        this.typeConstructor = new FunctionTypeConstructor(this);
        this.memberScope = new FunctionClassScope(storageManager0, this);
        ArrayList arrayList0 = new ArrayList();
        IntRange intRange0 = new IntRange(1, v);
        ArrayList arrayList1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRange0, 10));
        Iterator iterator0 = intRange0.iterator();
        while(iterator0.hasNext()) {
            int v1 = ((IntIterator)iterator0).nextInt();
            FunctionClassDescriptor._init_$typeParameter(arrayList0, this, Variance.IN_VARIANCE, "P" + v1);
            arrayList1.add(Unit.INSTANCE);
        }
        FunctionClassDescriptor._init_$typeParameter(arrayList0, this, Variance.OUT_VARIANCE, "R");
        this.parameters = CollectionsKt.toList(arrayList0);
    }

    private static final void _init_$typeParameter(ArrayList arrayList0, FunctionClassDescriptor functionClassDescriptor0, Variance variance0, String s) {
        Name name0 = Name.identifier(s);
        arrayList0.add(TypeParameterDescriptorImpl.createWithDefaultBound(functionClassDescriptor0, Annotations.Companion.getEMPTY(), false, variance0, name0, arrayList0.size(), functionClassDescriptor0.storageManager));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated
    public Annotations getAnnotations() {
        return Annotations.Companion.getEMPTY();
    }

    public final int getArity() {
        return this.arity;
    }

    public Void getCompanionObjectDescriptor() [...] // Inlined contents

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public ClassDescriptor getCompanionObjectDescriptor() {
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public Collection getConstructors() {
        return this.getConstructors();
    }

    public List getConstructors() {
        return CollectionsKt.emptyList();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public DeclarationDescriptor getContainingDeclaration() {
        return this.getContainingDeclaration();
    }

    public PackageFragmentDescriptor getContainingDeclaration() {
        return this.containingDeclaration;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public List getDeclaredTypeParameters() {
        return this.parameters;
    }

    public final FunctionClassKind getFunctionKind() {
        return this.functionKind;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public ClassKind getKind() {
        return ClassKind.INTERFACE;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public Modality getModality() {
        return Modality.ABSTRACT;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public Collection getSealedSubclasses() {
        return this.getSealedSubclasses();
    }

    public List getSealedSubclasses() {
        return CollectionsKt.emptyList();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithSource
    public SourceElement getSource() {
        Intrinsics.checkNotNullExpressionValue(SourceElement.NO_SOURCE, "NO_SOURCE");
        return SourceElement.NO_SOURCE;
    }

    public Empty getStaticScope() {
        return Empty.INSTANCE;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public MemberScope getStaticScope() {
        return this.getStaticScope();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor
    public TypeConstructor getTypeConstructor() {
        return this.typeConstructor;
    }

    protected FunctionClassScope getUnsubstitutedMemberScope(KotlinTypeRefiner kotlinTypeRefiner0) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "kotlinTypeRefiner");
        return this.memberScope;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleAwareClassDescriptor
    public MemberScope getUnsubstitutedMemberScope(KotlinTypeRefiner kotlinTypeRefiner0) {
        return this.getUnsubstitutedMemberScope(kotlinTypeRefiner0);
    }

    public Void getUnsubstitutedPrimaryConstructor() [...] // Inlined contents

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

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
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
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isValue() {
        return false;
    }

    @Override
    public String toString() {
        String s = this.getName().asString();
        Intrinsics.checkNotNullExpressionValue(s, "name.asString()");
        return s;
    }
}

