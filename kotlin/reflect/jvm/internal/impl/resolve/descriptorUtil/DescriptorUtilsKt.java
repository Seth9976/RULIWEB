package kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil;

import java.util.ArrayList;
import java.util.Collection;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref.ObjectRef;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.InlineClassRepresentation;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueClassRepresentation;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner.Default;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefinerKt;
import kotlin.reflect.jvm.internal.impl.types.checker.Ref;
import kotlin.reflect.jvm.internal.impl.types.checker.TypeRefinementSupport.Enabled;
import kotlin.reflect.jvm.internal.impl.types.checker.TypeRefinementSupport;
import kotlin.reflect.jvm.internal.impl.utils.DFS.AbstractNodeHandler;
import kotlin.reflect.jvm.internal.impl.utils.DFS;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

public final class DescriptorUtilsKt {
    private static final Name RETENTION_PARAMETER_NAME;

    static {
        Name name0 = Name.identifier("value");
        Intrinsics.checkNotNullExpressionValue(name0, "identifier(\"value\")");
        DescriptorUtilsKt.RETENTION_PARAMETER_NAME = name0;
    }

    // 检测为 Lambda 实现
    static Iterable accessor$DescriptorUtilsKt$lambda0(ValueParameterDescriptor valueParameterDescriptor0) [...]

    // 检测为 Lambda 实现
    static Iterable accessor$DescriptorUtilsKt$lambda1(boolean z, CallableMemberDescriptor callableMemberDescriptor0) [...]

    public static final boolean declaresOrInheritsDefaultValue(ValueParameterDescriptor valueParameterDescriptor0) {
        Intrinsics.checkNotNullParameter(valueParameterDescriptor0, "<this>");
        Boolean boolean0 = DFS.ifAny(CollectionsKt.listOf(valueParameterDescriptor0), (ValueParameterDescriptor valueParameterDescriptor0) -> DescriptorUtilsKt.declaresOrInheritsDefaultValue$lambda$5(valueParameterDescriptor0), kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.declaresOrInheritsDefaultValue.2.INSTANCE);
        Intrinsics.checkNotNullExpressionValue(boolean0, "ifAny(\n        listOf(th…eclaresDefaultValue\n    )");
        return boolean0.booleanValue();

        final class kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.declaresOrInheritsDefaultValue.2 extends FunctionReference implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.declaresOrInheritsDefaultValue.2 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.declaresOrInheritsDefaultValue.2.INSTANCE = new kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.declaresOrInheritsDefaultValue.2();
            }

            kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.declaresOrInheritsDefaultValue.2() {
                super(1);
            }

            @Override  // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
            public final String getName() {
                return "declaresDefaultValue";
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public final KDeclarationContainer getOwner() {
                return Reflection.getOrCreateKotlinClass(ValueParameterDescriptor.class);
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public final String getSignature() {
                return "declaresDefaultValue()Z";
            }

            public final Boolean invoke(ValueParameterDescriptor valueParameterDescriptor0) {
                Intrinsics.checkNotNullParameter(valueParameterDescriptor0, "p0");
                return Boolean.valueOf(valueParameterDescriptor0.declaresDefaultValue());
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((ValueParameterDescriptor)object0));
            }
        }

    }

    private static final Iterable declaresOrInheritsDefaultValue$lambda$5(ValueParameterDescriptor valueParameterDescriptor0) {
        Iterable iterable0 = valueParameterDescriptor0.getOverriddenDescriptors();
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
        for(Object object0: iterable0) {
            arrayList0.add(((ValueParameterDescriptor)object0).getOriginal());
        }
        return arrayList0;
    }

    public static final CallableMemberDescriptor firstOverridden(CallableMemberDescriptor callableMemberDescriptor0, boolean z, Function1 function10) {
        Intrinsics.checkNotNullParameter(callableMemberDescriptor0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "predicate");
        ObjectRef ref$ObjectRef0 = new ObjectRef();
        return (CallableMemberDescriptor)DFS.dfs(CollectionsKt.listOf(callableMemberDescriptor0), (CallableMemberDescriptor callableMemberDescriptor0) -> DescriptorUtilsKt.firstOverridden$lambda$9(z, callableMemberDescriptor0), new AbstractNodeHandler() {
            @Override  // kotlin.reflect.jvm.internal.impl.utils.DFS$AbstractNodeHandler
            public void afterChildren(Object object0) {
                this.afterChildren(((CallableMemberDescriptor)object0));
            }

            public void afterChildren(CallableMemberDescriptor callableMemberDescriptor0) {
                Intrinsics.checkNotNullParameter(callableMemberDescriptor0, "current");
                if(ref$ObjectRef0.element == null && ((Boolean)function10.invoke(callableMemberDescriptor0)).booleanValue()) {
                    ref$ObjectRef0.element = callableMemberDescriptor0;
                }
            }

            @Override  // kotlin.reflect.jvm.internal.impl.utils.DFS$AbstractNodeHandler
            public boolean beforeChildren(Object object0) {
                return this.beforeChildren(((CallableMemberDescriptor)object0));
            }

            public boolean beforeChildren(CallableMemberDescriptor callableMemberDescriptor0) {
                Intrinsics.checkNotNullParameter(callableMemberDescriptor0, "current");
                return ref$ObjectRef0.element == null;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.utils.DFS$NodeHandler
            public Object result() {
                return this.result();
            }

            public CallableMemberDescriptor result() {
                return (CallableMemberDescriptor)ref$ObjectRef0.element;
            }
        });
    }

    public static CallableMemberDescriptor firstOverridden$default(CallableMemberDescriptor callableMemberDescriptor0, boolean z, Function1 function10, int v, Object object0) {
        if((v & 1) != 0) {
            z = false;
        }
        return DescriptorUtilsKt.firstOverridden(callableMemberDescriptor0, z, function10);
    }

    private static final Iterable firstOverridden$lambda$9(boolean z, CallableMemberDescriptor callableMemberDescriptor0) {
        Collection collection0 = null;
        if(z) {
            callableMemberDescriptor0 = callableMemberDescriptor0 == null ? null : callableMemberDescriptor0.getOriginal();
        }
        if(callableMemberDescriptor0 != null) {
            collection0 = callableMemberDescriptor0.getOverriddenDescriptors();
        }
        return collection0 == null ? CollectionsKt.emptyList() : collection0;
    }

    public static final FqName fqNameOrNull(DeclarationDescriptor declarationDescriptor0) {
        Intrinsics.checkNotNullParameter(declarationDescriptor0, "<this>");
        FqNameUnsafe fqNameUnsafe0 = DescriptorUtilsKt.getFqNameUnsafe(declarationDescriptor0);
        if(!fqNameUnsafe0.isSafe()) {
            fqNameUnsafe0 = null;
        }
        return fqNameUnsafe0 == null ? null : fqNameUnsafe0.toSafe();
    }

    public static final ClassDescriptor getAnnotationClass(AnnotationDescriptor annotationDescriptor0) {
        Intrinsics.checkNotNullParameter(annotationDescriptor0, "<this>");
        ClassifierDescriptor classifierDescriptor0 = annotationDescriptor0.getType().getConstructor().getDeclarationDescriptor();
        return classifierDescriptor0 instanceof ClassDescriptor ? ((ClassDescriptor)classifierDescriptor0) : null;
    }

    public static final KotlinBuiltIns getBuiltIns(DeclarationDescriptor declarationDescriptor0) {
        Intrinsics.checkNotNullParameter(declarationDescriptor0, "<this>");
        return DescriptorUtilsKt.getModule(declarationDescriptor0).getBuiltIns();
    }

    public static final ClassId getClassId(ClassifierDescriptor classifierDescriptor0) {
        if(classifierDescriptor0 != null) {
            DeclarationDescriptor declarationDescriptor0 = classifierDescriptor0.getContainingDeclaration();
            if(declarationDescriptor0 != null) {
                if(declarationDescriptor0 instanceof PackageFragmentDescriptor) {
                    return new ClassId(((PackageFragmentDescriptor)declarationDescriptor0).getFqName(), classifierDescriptor0.getName());
                }
                if(declarationDescriptor0 instanceof ClassifierDescriptorWithTypeParameters) {
                    ClassId classId0 = DescriptorUtilsKt.getClassId(((ClassifierDescriptor)declarationDescriptor0));
                    return classId0 == null ? null : classId0.createNestedClassId(classifierDescriptor0.getName());
                }
            }
        }
        return null;
    }

    public static final FqName getFqNameSafe(DeclarationDescriptor declarationDescriptor0) {
        Intrinsics.checkNotNullParameter(declarationDescriptor0, "<this>");
        FqName fqName0 = DescriptorUtils.getFqNameSafe(declarationDescriptor0);
        Intrinsics.checkNotNullExpressionValue(fqName0, "getFqNameSafe(this)");
        return fqName0;
    }

    public static final FqNameUnsafe getFqNameUnsafe(DeclarationDescriptor declarationDescriptor0) {
        Intrinsics.checkNotNullParameter(declarationDescriptor0, "<this>");
        FqNameUnsafe fqNameUnsafe0 = DescriptorUtils.getFqName(declarationDescriptor0);
        Intrinsics.checkNotNullExpressionValue(fqNameUnsafe0, "getFqName(this)");
        return fqNameUnsafe0;
    }

    public static final InlineClassRepresentation getInlineClassRepresentation(ClassDescriptor classDescriptor0) {
        ValueClassRepresentation valueClassRepresentation0 = classDescriptor0 == null ? null : classDescriptor0.getValueClassRepresentation();
        return valueClassRepresentation0 instanceof InlineClassRepresentation ? ((InlineClassRepresentation)valueClassRepresentation0) : null;
    }

    public static final KotlinTypeRefiner getKotlinTypeRefiner(ModuleDescriptor moduleDescriptor0) {
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "<this>");
        Ref ref0 = (Ref)moduleDescriptor0.getCapability(KotlinTypeRefinerKt.getREFINER_CAPABILITY());
        TypeRefinementSupport typeRefinementSupport0 = ref0 == null ? null : ((TypeRefinementSupport)ref0.getValue());
        return typeRefinementSupport0 instanceof Enabled ? ((Enabled)typeRefinementSupport0).getTypeRefiner() : Default.INSTANCE;
    }

    public static final ModuleDescriptor getModule(DeclarationDescriptor declarationDescriptor0) {
        Intrinsics.checkNotNullParameter(declarationDescriptor0, "<this>");
        ModuleDescriptor moduleDescriptor0 = DescriptorUtils.getContainingModule(declarationDescriptor0);
        Intrinsics.checkNotNullExpressionValue(moduleDescriptor0, "getContainingModule(this)");
        return moduleDescriptor0;
    }

    public static final Sequence getParents(DeclarationDescriptor declarationDescriptor0) {
        Intrinsics.checkNotNullParameter(declarationDescriptor0, "<this>");
        return SequencesKt.drop(DescriptorUtilsKt.getParentsWithSelf(declarationDescriptor0), 1);
    }

    public static final Sequence getParentsWithSelf(DeclarationDescriptor declarationDescriptor0) {
        Intrinsics.checkNotNullParameter(declarationDescriptor0, "<this>");
        return SequencesKt.generateSequence(declarationDescriptor0, kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.parentsWithSelf.1.INSTANCE);

        final class kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.parentsWithSelf.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.parentsWithSelf.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.parentsWithSelf.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.parentsWithSelf.1();
            }

            kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt.parentsWithSelf.1() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((DeclarationDescriptor)object0));
            }

            public final DeclarationDescriptor invoke(DeclarationDescriptor declarationDescriptor0) {
                Intrinsics.checkNotNullParameter(declarationDescriptor0, "it");
                return declarationDescriptor0.getContainingDeclaration();
            }
        }

    }

    public static final CallableMemberDescriptor getPropertyIfAccessor(CallableMemberDescriptor callableMemberDescriptor0) {
        Intrinsics.checkNotNullParameter(callableMemberDescriptor0, "<this>");
        if(callableMemberDescriptor0 instanceof PropertyAccessorDescriptor) {
            PropertyDescriptor propertyDescriptor0 = ((PropertyAccessorDescriptor)callableMemberDescriptor0).getCorrespondingProperty();
            Intrinsics.checkNotNullExpressionValue(propertyDescriptor0, "correspondingProperty");
            return propertyDescriptor0;
        }
        return callableMemberDescriptor0;
    }

    public static final ClassDescriptor getSuperClassNotAny(ClassDescriptor classDescriptor0) {
        Intrinsics.checkNotNullParameter(classDescriptor0, "<this>");
        for(Object object0: classDescriptor0.getDefaultType().getConstructor().getSupertypes()) {
            KotlinType kotlinType0 = (KotlinType)object0;
            if(!KotlinBuiltIns.isAnyOrNullableAny(kotlinType0)) {
                ClassifierDescriptor classifierDescriptor0 = kotlinType0.getConstructor().getDeclarationDescriptor();
                if(DescriptorUtils.isClassOrEnumClass(classifierDescriptor0)) {
                    Intrinsics.checkNotNull(classifierDescriptor0, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                    return (ClassDescriptor)classifierDescriptor0;
                }
                if(false) {
                    break;
                }
            }
        }
        return null;
    }

    public static final boolean isTypeRefinementEnabled(ModuleDescriptor moduleDescriptor0) {
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "<this>");
        Ref ref0 = (Ref)moduleDescriptor0.getCapability(KotlinTypeRefinerKt.getREFINER_CAPABILITY());
        if(ref0 != null) {
            TypeRefinementSupport typeRefinementSupport0 = (TypeRefinementSupport)ref0.getValue();
            return typeRefinementSupport0 != null && typeRefinementSupport0.isEnabled();
        }
        return false;
    }

    public static final ClassDescriptor resolveTopLevelClass(ModuleDescriptor moduleDescriptor0, FqName fqName0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "<this>");
        Intrinsics.checkNotNullParameter(fqName0, "topLevelClassFqName");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        FqName fqName1 = fqName0.parent();
        Intrinsics.checkNotNullExpressionValue(fqName1, "topLevelClassFqName.parent()");
        MemberScope memberScope0 = moduleDescriptor0.getPackage(fqName1).getMemberScope();
        Name name0 = fqName0.shortName();
        Intrinsics.checkNotNullExpressionValue(name0, "topLevelClassFqName.shortName()");
        ClassifierDescriptor classifierDescriptor0 = memberScope0.getContributedClassifier(name0, lookupLocation0);
        return classifierDescriptor0 instanceof ClassDescriptor ? ((ClassDescriptor)classifierDescriptor0) : null;
    }
}

