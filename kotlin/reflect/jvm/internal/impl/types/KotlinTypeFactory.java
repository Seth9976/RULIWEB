package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleAwareClassDescriptorKt;
import kotlin.reflect.jvm.internal.impl.resolve.constants.IntegerLiteralTypeConstructor;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorScopeKind;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorUtils;

public final class KotlinTypeFactory {
    static final class ExpandedTypeOrRefinedConstructor {
        private final SimpleType expandedType;
        private final TypeConstructor refinedConstructor;

        public ExpandedTypeOrRefinedConstructor(SimpleType simpleType0, TypeConstructor typeConstructor0) {
            this.expandedType = simpleType0;
            this.refinedConstructor = typeConstructor0;
        }

        public final SimpleType getExpandedType() {
            return this.expandedType;
        }

        public final TypeConstructor getRefinedConstructor() {
            return this.refinedConstructor;
        }
    }

    private static final Function1 EMPTY_REFINED_TYPE_FACTORY;
    public static final KotlinTypeFactory INSTANCE;

    static {
        KotlinTypeFactory.INSTANCE = new KotlinTypeFactory();
        KotlinTypeFactory.EMPTY_REFINED_TYPE_FACTORY = KotlinTypeFactory.EMPTY_REFINED_TYPE_FACTORY.1.INSTANCE;
    }

    @JvmStatic
    public static final SimpleType computeExpandedType(TypeAliasDescriptor typeAliasDescriptor0, List list0) {
        Intrinsics.checkNotNullParameter(typeAliasDescriptor0, "<this>");
        Intrinsics.checkNotNullParameter(list0, "arguments");
        return new TypeAliasExpander(DO_NOTHING.INSTANCE, false).expand(TypeAliasExpansion.Companion.create(null, typeAliasDescriptor0, list0), TypeAttributes.Companion.getEmpty());
    }

    private final MemberScope computeMemberScope(TypeConstructor typeConstructor0, List list0, KotlinTypeRefiner kotlinTypeRefiner0) {
        ClassifierDescriptor classifierDescriptor0 = typeConstructor0.getDeclarationDescriptor();
        if(classifierDescriptor0 instanceof TypeParameterDescriptor) {
            return ((TypeParameterDescriptor)classifierDescriptor0).getDefaultType().getMemberScope();
        }
        if(classifierDescriptor0 instanceof ClassDescriptor) {
            if(kotlinTypeRefiner0 == null) {
                kotlinTypeRefiner0 = DescriptorUtilsKt.getKotlinTypeRefiner(DescriptorUtilsKt.getModule(classifierDescriptor0));
            }
            return list0.isEmpty() ? ModuleAwareClassDescriptorKt.getRefinedUnsubstitutedMemberScopeIfPossible(((ClassDescriptor)classifierDescriptor0), kotlinTypeRefiner0) : ModuleAwareClassDescriptorKt.getRefinedMemberScopeIfPossible(((ClassDescriptor)classifierDescriptor0), TypeConstructorSubstitution.Companion.create(typeConstructor0, list0), kotlinTypeRefiner0);
        }
        if(classifierDescriptor0 instanceof TypeAliasDescriptor) {
            String s = ((TypeAliasDescriptor)classifierDescriptor0).getName().toString();
            Intrinsics.checkNotNullExpressionValue(s, "descriptor.name.toString()");
            return ErrorUtils.createErrorScope(ErrorScopeKind.SCOPE_FOR_ABBREVIATION_TYPE, true, new String[]{s});
        }
        if(!(typeConstructor0 instanceof IntersectionTypeConstructor)) {
            throw new IllegalStateException("Unsupported classifier: " + classifierDescriptor0 + " for constructor: " + typeConstructor0);
        }
        return ((IntersectionTypeConstructor)typeConstructor0).createScopeForKotlinType();
    }

    @JvmStatic
    public static final UnwrappedType flexibleType(SimpleType simpleType0, SimpleType simpleType1) {
        Intrinsics.checkNotNullParameter(simpleType0, "lowerBound");
        Intrinsics.checkNotNullParameter(simpleType1, "upperBound");
        return Intrinsics.areEqual(simpleType0, simpleType1) ? simpleType0 : new FlexibleTypeImpl(simpleType0, simpleType1);
    }

    @JvmStatic
    public static final SimpleType integerLiteralType(TypeAttributes typeAttributes0, IntegerLiteralTypeConstructor integerLiteralTypeConstructor0, boolean z) {
        Intrinsics.checkNotNullParameter(typeAttributes0, "attributes");
        Intrinsics.checkNotNullParameter(integerLiteralTypeConstructor0, "constructor");
        return KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(typeAttributes0, integerLiteralTypeConstructor0, CollectionsKt.emptyList(), z, ErrorUtils.createErrorScope(ErrorScopeKind.INTEGER_LITERAL_TYPE_SCOPE, true, new String[]{"unknown integer literal type"}));
    }

    private final ExpandedTypeOrRefinedConstructor refineConstructor(TypeConstructor typeConstructor0, KotlinTypeRefiner kotlinTypeRefiner0, List list0) {
        ClassifierDescriptor classifierDescriptor0 = typeConstructor0.getDeclarationDescriptor();
        if(classifierDescriptor0 != null) {
            ClassifierDescriptor classifierDescriptor1 = kotlinTypeRefiner0.refineDescriptor(classifierDescriptor0);
            if(classifierDescriptor1 != null) {
                if(classifierDescriptor1 instanceof TypeAliasDescriptor) {
                    return new ExpandedTypeOrRefinedConstructor(KotlinTypeFactory.computeExpandedType(((TypeAliasDescriptor)classifierDescriptor1), list0), null);
                }
                TypeConstructor typeConstructor1 = classifierDescriptor1.getTypeConstructor().refine(kotlinTypeRefiner0);
                Intrinsics.checkNotNullExpressionValue(typeConstructor1, "descriptor.typeConstruct…refine(kotlinTypeRefiner)");
                return new ExpandedTypeOrRefinedConstructor(null, typeConstructor1);
            }
        }
        return null;
    }

    @JvmStatic
    public static final SimpleType simpleNotNullType(TypeAttributes typeAttributes0, ClassDescriptor classDescriptor0, List list0) {
        Intrinsics.checkNotNullParameter(typeAttributes0, "attributes");
        Intrinsics.checkNotNullParameter(classDescriptor0, "descriptor");
        Intrinsics.checkNotNullParameter(list0, "arguments");
        TypeConstructor typeConstructor0 = classDescriptor0.getTypeConstructor();
        Intrinsics.checkNotNullExpressionValue(typeConstructor0, "descriptor.typeConstructor");
        return KotlinTypeFactory.simpleType$default(typeAttributes0, typeConstructor0, list0, false, null, 16, null);
    }

    @JvmStatic
    public static final SimpleType simpleType(SimpleType simpleType0, TypeAttributes typeAttributes0, TypeConstructor typeConstructor0, List list0, boolean z) {
        Intrinsics.checkNotNullParameter(simpleType0, "baseType");
        Intrinsics.checkNotNullParameter(typeAttributes0, "annotations");
        Intrinsics.checkNotNullParameter(typeConstructor0, "constructor");
        Intrinsics.checkNotNullParameter(list0, "arguments");
        return KotlinTypeFactory.simpleType$default(typeAttributes0, typeConstructor0, list0, z, null, 16, null);
    }

    @JvmStatic
    public static final SimpleType simpleType(TypeAttributes typeAttributes0, TypeConstructor typeConstructor0, List list0, boolean z) {
        Intrinsics.checkNotNullParameter(typeAttributes0, "attributes");
        Intrinsics.checkNotNullParameter(typeConstructor0, "constructor");
        Intrinsics.checkNotNullParameter(list0, "arguments");
        return KotlinTypeFactory.simpleType$default(typeAttributes0, typeConstructor0, list0, z, null, 16, null);
    }

    @JvmStatic
    public static final SimpleType simpleType(TypeAttributes typeAttributes0, TypeConstructor typeConstructor0, List list0, boolean z, KotlinTypeRefiner kotlinTypeRefiner0) {
        Intrinsics.checkNotNullParameter(typeAttributes0, "attributes");
        Intrinsics.checkNotNullParameter(typeConstructor0, "constructor");
        Intrinsics.checkNotNullParameter(list0, "arguments");
        if(typeAttributes0.isEmpty() && list0.isEmpty() && !z && typeConstructor0.getDeclarationDescriptor() != null) {
            ClassifierDescriptor classifierDescriptor0 = typeConstructor0.getDeclarationDescriptor();
            Intrinsics.checkNotNull(classifierDescriptor0);
            SimpleType simpleType0 = classifierDescriptor0.getDefaultType();
            Intrinsics.checkNotNullExpressionValue(simpleType0, "constructor.declarationDescriptor!!.defaultType");
            return simpleType0;
        }
        return KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(typeAttributes0, typeConstructor0, list0, z, KotlinTypeFactory.INSTANCE.computeMemberScope(typeConstructor0, list0, kotlinTypeRefiner0), new Function1(typeConstructor0, list0, typeAttributes0, z) {
            final List $arguments;
            final TypeAttributes $attributes;
            final TypeConstructor $constructor;
            final boolean $nullable;

            {
                this.$constructor = typeConstructor0;
                this.$arguments = list0;
                this.$attributes = typeAttributes0;
                this.$nullable = z;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((KotlinTypeRefiner)object0));
            }

            public final SimpleType invoke(KotlinTypeRefiner kotlinTypeRefiner0) {
                Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "refiner");
                ExpandedTypeOrRefinedConstructor kotlinTypeFactory$ExpandedTypeOrRefinedConstructor0 = KotlinTypeFactory.INSTANCE.refineConstructor(this.$constructor, kotlinTypeRefiner0, this.$arguments);
                if(kotlinTypeFactory$ExpandedTypeOrRefinedConstructor0 == null) {
                    return null;
                }
                SimpleType simpleType0 = kotlinTypeFactory$ExpandedTypeOrRefinedConstructor0.getExpandedType();
                if(simpleType0 != null) {
                    return simpleType0;
                }
                TypeConstructor typeConstructor0 = kotlinTypeFactory$ExpandedTypeOrRefinedConstructor0.getRefinedConstructor();
                Intrinsics.checkNotNull(typeConstructor0);
                return KotlinTypeFactory.simpleType(this.$attributes, typeConstructor0, this.$arguments, this.$nullable, kotlinTypeRefiner0);
            }
        });
    }

    public static SimpleType simpleType$default(SimpleType simpleType0, TypeAttributes typeAttributes0, TypeConstructor typeConstructor0, List list0, boolean z, int v, Object object0) {
        if((v & 2) != 0) {
            typeAttributes0 = simpleType0.getAttributes();
        }
        if((v & 4) != 0) {
            typeConstructor0 = simpleType0.getConstructor();
        }
        if((v & 8) != 0) {
            list0 = simpleType0.getArguments();
        }
        if((v & 16) != 0) {
            z = simpleType0.isMarkedNullable();
        }
        return KotlinTypeFactory.simpleType(simpleType0, typeAttributes0, typeConstructor0, list0, z);
    }

    public static SimpleType simpleType$default(TypeAttributes typeAttributes0, TypeConstructor typeConstructor0, List list0, boolean z, KotlinTypeRefiner kotlinTypeRefiner0, int v, Object object0) {
        if((v & 16) != 0) {
            kotlinTypeRefiner0 = null;
        }
        return KotlinTypeFactory.simpleType(typeAttributes0, typeConstructor0, list0, z, kotlinTypeRefiner0);
    }

    @JvmStatic
    public static final SimpleType simpleTypeWithNonTrivialMemberScope(TypeAttributes typeAttributes0, TypeConstructor typeConstructor0, List list0, boolean z, MemberScope memberScope0) {
        Intrinsics.checkNotNullParameter(typeAttributes0, "attributes");
        Intrinsics.checkNotNullParameter(typeConstructor0, "constructor");
        Intrinsics.checkNotNullParameter(list0, "arguments");
        Intrinsics.checkNotNullParameter(memberScope0, "memberScope");
        SimpleTypeImpl simpleTypeImpl0 = new SimpleTypeImpl(typeConstructor0, list0, z, memberScope0, new Function1(typeConstructor0, list0, typeAttributes0, z, memberScope0) {
            final List $arguments;
            final TypeAttributes $attributes;
            final TypeConstructor $constructor;
            final MemberScope $memberScope;
            final boolean $nullable;

            {
                this.$constructor = typeConstructor0;
                this.$arguments = list0;
                this.$attributes = typeAttributes0;
                this.$nullable = z;
                this.$memberScope = memberScope0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((KotlinTypeRefiner)object0));
            }

            public final SimpleType invoke(KotlinTypeRefiner kotlinTypeRefiner0) {
                Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "kotlinTypeRefiner");
                ExpandedTypeOrRefinedConstructor kotlinTypeFactory$ExpandedTypeOrRefinedConstructor0 = KotlinTypeFactory.INSTANCE.refineConstructor(this.$constructor, kotlinTypeRefiner0, this.$arguments);
                if(kotlinTypeFactory$ExpandedTypeOrRefinedConstructor0 == null) {
                    return null;
                }
                SimpleType simpleType0 = kotlinTypeFactory$ExpandedTypeOrRefinedConstructor0.getExpandedType();
                if(simpleType0 != null) {
                    return simpleType0;
                }
                TypeConstructor typeConstructor0 = kotlinTypeFactory$ExpandedTypeOrRefinedConstructor0.getRefinedConstructor();
                Intrinsics.checkNotNull(typeConstructor0);
                return KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(this.$attributes, typeConstructor0, this.$arguments, this.$nullable, this.$memberScope);
            }
        });
        return typeAttributes0.isEmpty() ? simpleTypeImpl0 : new SimpleTypeWithAttributes(simpleTypeImpl0, typeAttributes0);
    }

    @JvmStatic
    public static final SimpleType simpleTypeWithNonTrivialMemberScope(TypeAttributes typeAttributes0, TypeConstructor typeConstructor0, List list0, boolean z, MemberScope memberScope0, Function1 function10) {
        Intrinsics.checkNotNullParameter(typeAttributes0, "attributes");
        Intrinsics.checkNotNullParameter(typeConstructor0, "constructor");
        Intrinsics.checkNotNullParameter(list0, "arguments");
        Intrinsics.checkNotNullParameter(memberScope0, "memberScope");
        Intrinsics.checkNotNullParameter(function10, "refinedTypeFactory");
        SimpleTypeImpl simpleTypeImpl0 = new SimpleTypeImpl(typeConstructor0, list0, z, memberScope0, function10);
        return typeAttributes0.isEmpty() ? simpleTypeImpl0 : new SimpleTypeWithAttributes(simpleTypeImpl0, typeAttributes0);
    }
}

