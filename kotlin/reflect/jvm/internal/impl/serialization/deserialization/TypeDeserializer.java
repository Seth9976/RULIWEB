package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.jvm.internal.impl.builtins.FunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument.Projection;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedAnnotations;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedTypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.DefinitelyNotNullType.Companion;
import kotlin.reflect.jvm.internal.impl.types.DefinitelyNotNullType;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.SpecialTypesKt;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionForAbsentTypeParameter;
import kotlin.reflect.jvm.internal.impl.types.StarProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeAttributeTranslator;
import kotlin.reflect.jvm.internal.impl.types.TypeAttributes;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorTypeKind;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.sequences.SequencesKt;

public final class TypeDeserializer {
    private final DeserializationContext c;
    private final Function1 classifierDescriptors;
    private final String containerPresentableName;
    private final String debugName;
    private final TypeDeserializer parent;
    private final Function1 typeAliasDescriptors;
    private final Map typeParameterDescriptors;

    public TypeDeserializer(DeserializationContext deserializationContext0, TypeDeserializer typeDeserializer0, List list0, String s, String s1) {
        Intrinsics.checkNotNullParameter(deserializationContext0, "c");
        Map map0;
        Intrinsics.checkNotNullParameter(list0, "typeParameterProtos");
        Intrinsics.checkNotNullParameter(s, "debugName");
        Intrinsics.checkNotNullParameter(s1, "containerPresentableName");
        super();
        this.c = deserializationContext0;
        this.parent = typeDeserializer0;
        this.debugName = s;
        this.containerPresentableName = s1;
        this.classifierDescriptors = deserializationContext0.getStorageManager().createMemoizedFunctionWithNullableValues(new Function1() {
            {
                TypeDeserializer.this = typeDeserializer0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((Number)object0).intValue());
            }

            public final ClassifierDescriptor invoke(int v) {
                return TypeDeserializer.access$computeClassifierDescriptor(TypeDeserializer.this, v);
            }
        });
        this.typeAliasDescriptors = deserializationContext0.getStorageManager().createMemoizedFunctionWithNullableValues(new Function1() {
            {
                TypeDeserializer.this = typeDeserializer0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((Number)object0).intValue());
            }

            public final ClassifierDescriptor invoke(int v) {
                return TypeDeserializer.access$computeTypeAliasDescriptor(TypeDeserializer.this, v);
            }
        });
        if(list0.isEmpty()) {
            map0 = MapsKt.emptyMap();
        }
        else {
            LinkedHashMap linkedHashMap0 = new LinkedHashMap();
            int v = 0;
            for(Object object0: list0) {
                linkedHashMap0.put(((TypeParameter)object0).getId(), new DeserializedTypeParameterDescriptor(this.c, ((TypeParameter)object0), v));
                ++v;
            }
            map0 = linkedHashMap0;
        }
        this.typeParameterDescriptors = map0;
    }

    public static final ClassifierDescriptor access$computeClassifierDescriptor(TypeDeserializer typeDeserializer0, int v) {
        return typeDeserializer0.computeClassifierDescriptor(v);
    }

    public static final ClassifierDescriptor access$computeTypeAliasDescriptor(TypeDeserializer typeDeserializer0, int v) {
        return typeDeserializer0.computeTypeAliasDescriptor(v);
    }

    private final ClassifierDescriptor computeClassifierDescriptor(int v) {
        ClassId classId0 = NameResolverUtilKt.getClassId(this.c.getNameResolver(), v);
        return classId0.isLocal() ? this.c.getComponents().deserializeClass(classId0) : FindClassInModuleKt.findClassifierAcrossModuleDependencies(this.c.getComponents().getModuleDescriptor(), classId0);
    }

    // 去混淆评级： 低(20)
    private final SimpleType computeLocalClassifierReplacementType(int v) {
        return NameResolverUtilKt.getClassId(this.c.getNameResolver(), v).isLocal() ? this.c.getComponents().getLocalClassifierTypeSettings().getReplacementTypeForLocalClassifiers() : null;
    }

    private final ClassifierDescriptor computeTypeAliasDescriptor(int v) {
        ClassId classId0 = NameResolverUtilKt.getClassId(this.c.getNameResolver(), v);
        return classId0.isLocal() ? null : FindClassInModuleKt.findTypeAliasAcrossModuleDependencies(this.c.getComponents().getModuleDescriptor(), classId0);
    }

    private final SimpleType createSimpleSuspendFunctionType(KotlinType kotlinType0, KotlinType kotlinType1) {
        KotlinBuiltIns kotlinBuiltIns0 = TypeUtilsKt.getBuiltIns(kotlinType0);
        Annotations annotations0 = kotlinType0.getAnnotations();
        KotlinType kotlinType2 = FunctionTypesKt.getReceiverTypeFromFunctionType(kotlinType0);
        List list0 = FunctionTypesKt.getContextReceiverTypesFromFunctionType(kotlinType0);
        Iterable iterable0 = CollectionsKt.dropLast(FunctionTypesKt.getValueParameterTypesFromFunctionType(kotlinType0), 1);
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
        for(Object object0: iterable0) {
            arrayList0.add(((TypeProjection)object0).getType());
        }
        return FunctionTypesKt.createFunctionType(kotlinBuiltIns0, annotations0, kotlinType2, list0, arrayList0, null, kotlinType1, true).makeNullableAsSpecified(kotlinType0.isMarkedNullable());
    }

    private final SimpleType createSuspendFunctionType(TypeAttributes typeAttributes0, TypeConstructor typeConstructor0, List list0, boolean z) {
        SimpleType simpleType0 = null;
        switch(typeConstructor0.getParameters().size() - list0.size()) {
            case 0: {
                simpleType0 = this.createSuspendFunctionTypeForBasicCase(typeAttributes0, typeConstructor0, list0, z);
                break;
            }
            case 1: {
                int v = list0.size();
                if(v - 1 >= 0) {
                    TypeConstructor typeConstructor1 = typeConstructor0.getBuiltIns().getSuspendFunction(v - 1).getTypeConstructor();
                    Intrinsics.checkNotNullExpressionValue(typeConstructor1, "functionTypeConstructor.…on(arity).typeConstructor");
                    simpleType0 = KotlinTypeFactory.simpleType$default(typeAttributes0, typeConstructor1, list0, z, null, 16, null);
                }
            }
        }
        return simpleType0 == null ? ErrorUtils.INSTANCE.createErrorTypeWithArguments(ErrorTypeKind.INCONSISTENT_SUSPEND_FUNCTION, list0, typeConstructor0, new String[0]) : simpleType0;
    }

    private final SimpleType createSuspendFunctionTypeForBasicCase(TypeAttributes typeAttributes0, TypeConstructor typeConstructor0, List list0, boolean z) {
        KotlinType kotlinType0 = KotlinTypeFactory.simpleType$default(typeAttributes0, typeConstructor0, list0, z, null, 16, null);
        return FunctionTypesKt.isFunctionType(kotlinType0) ? this.transformRuntimeFunctionTypeToSuspendFunction(kotlinType0) : null;
    }

    public final List getOwnTypeParameters() {
        return CollectionsKt.toList(this.typeParameterDescriptors.values());
    }

    private final TypeParameterDescriptor loadTypeParameter(int v) {
        TypeParameterDescriptor typeParameterDescriptor0 = (TypeParameterDescriptor)this.typeParameterDescriptors.get(v);
        if(typeParameterDescriptor0 == null) {
            return this.parent == null ? null : this.parent.loadTypeParameter(v);
        }
        return typeParameterDescriptor0;
    }

    public final SimpleType simpleType(Type protoBuf$Type0, boolean z) {
        SimpleType simpleType2;
        SimpleType simpleType0;
        Intrinsics.checkNotNullParameter(protoBuf$Type0, "proto");
        if(protoBuf$Type0.hasClassName()) {
            simpleType0 = this.computeLocalClassifierReplacementType(protoBuf$Type0.getClassName());
        }
        else {
            simpleType0 = protoBuf$Type0.hasTypeAliasName() ? this.computeLocalClassifierReplacementType(protoBuf$Type0.getTypeAliasName()) : null;
        }
        if(simpleType0 != null) {
            return simpleType0;
        }
        TypeConstructor typeConstructor0 = this.typeConstructor(protoBuf$Type0);
        if(ErrorUtils.isError(typeConstructor0.getDeclarationDescriptor())) {
            return ErrorUtils.INSTANCE.createErrorType(ErrorTypeKind.TYPE_FOR_ERROR_TYPE_CONSTRUCTOR, typeConstructor0, new String[]{typeConstructor0.toString()});
        }
        DeserializedAnnotations deserializedAnnotations0 = new DeserializedAnnotations(this.c.getStorageManager(), new Function0(protoBuf$Type0) {
            final Type $proto;

            {
                TypeDeserializer.this = typeDeserializer0;
                this.$proto = protoBuf$Type0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final List invoke() {
                return TypeDeserializer.this.c.getComponents().getAnnotationAndConstantLoader().loadTypeAnnotations(this.$proto, TypeDeserializer.this.c.getNameResolver());
            }
        });
        TypeAttributes typeAttributes0 = this.toAttributes(this.c.getComponents().getTypeAttributeTranslators(), deserializedAnnotations0, typeConstructor0, this.c.getContainingDeclaration());
        Iterable iterable0 = TypeDeserializer.simpleType$collectAllArguments(protoBuf$Type0, this);
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
        int v = 0;
        for(Object object0: iterable0) {
            if(v < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            List list0 = typeConstructor0.getParameters();
            Intrinsics.checkNotNullExpressionValue(list0, "constructor.parameters");
            arrayList0.add(this.typeArgument(((TypeParameterDescriptor)CollectionsKt.getOrNull(list0, v)), ((Argument)object0)));
            ++v;
        }
        List list1 = CollectionsKt.toList(arrayList0);
        ClassifierDescriptor classifierDescriptor0 = typeConstructor0.getDeclarationDescriptor();
        if(!z || !(classifierDescriptor0 instanceof TypeAliasDescriptor)) {
            Boolean boolean0 = Flags.SUSPEND_TYPE.get(protoBuf$Type0.getFlags());
            Intrinsics.checkNotNullExpressionValue(boolean0, "SUSPEND_TYPE.get(proto.flags)");
            if(boolean0.booleanValue()) {
                simpleType2 = this.createSuspendFunctionType(typeAttributes0, typeConstructor0, list1, protoBuf$Type0.getNullable());
            }
            else {
                simpleType2 = KotlinTypeFactory.simpleType$default(typeAttributes0, typeConstructor0, list1, protoBuf$Type0.getNullable(), null, 16, null);
                Boolean boolean1 = Flags.DEFINITELY_NOT_NULL_TYPE.get(protoBuf$Type0.getFlags());
                Intrinsics.checkNotNullExpressionValue(boolean1, "DEFINITELY_NOT_NULL_TYPE.get(proto.flags)");
                if(boolean1.booleanValue()) {
                    DefinitelyNotNullType definitelyNotNullType0 = Companion.makeDefinitelyNotNull$default(DefinitelyNotNullType.Companion, simpleType2, true, false, 4, null);
                    if(definitelyNotNullType0 == null) {
                        throw new IllegalStateException(("null DefinitelyNotNullType for \'" + simpleType2 + '\'').toString());
                    }
                    simpleType2 = definitelyNotNullType0;
                }
            }
        }
        else {
            SimpleType simpleType1 = KotlinTypeFactory.computeExpandedType(((TypeAliasDescriptor)classifierDescriptor0), list1);
            List list2 = this.c.getComponents().getTypeAttributeTranslators();
            List list3 = CollectionsKt.plus(deserializedAnnotations0, simpleType1.getAnnotations());
            TypeAttributes typeAttributes1 = this.toAttributes(list2, Annotations.Companion.create(list3), typeConstructor0, this.c.getContainingDeclaration());
            simpleType2 = simpleType1.makeNullableAsSpecified(KotlinTypeKt.isNullable(simpleType1) || protoBuf$Type0.getNullable()).replaceAttributes(typeAttributes1);
        }
        Type protoBuf$Type1 = ProtoTypeTableUtilKt.abbreviatedType(protoBuf$Type0, this.c.getTypeTable());
        if(protoBuf$Type1 != null) {
            SimpleType simpleType3 = SpecialTypesKt.withAbbreviation(simpleType2, this.simpleType(protoBuf$Type1, false));
            if(simpleType3 != null) {
                simpleType2 = simpleType3;
            }
        }
        if(protoBuf$Type0.hasClassName()) {
            ClassId classId0 = NameResolverUtilKt.getClassId(this.c.getNameResolver(), protoBuf$Type0.getClassName());
            return this.c.getComponents().getPlatformDependentTypeTransformer().transformPlatformType(classId0, simpleType2);
        }
        return simpleType2;
    }

    private static final List simpleType$collectAllArguments(Type protoBuf$Type0, TypeDeserializer typeDeserializer0) {
        List list0 = protoBuf$Type0.getArgumentList();
        Intrinsics.checkNotNullExpressionValue(list0, "argumentList");
        Type protoBuf$Type1 = ProtoTypeTableUtilKt.outerType(protoBuf$Type0, typeDeserializer0.c.getTypeTable());
        List list1 = protoBuf$Type1 == null ? null : TypeDeserializer.simpleType$collectAllArguments(protoBuf$Type1, typeDeserializer0);
        if(list1 == null) {
            list1 = CollectionsKt.emptyList();
        }
        return CollectionsKt.plus(list0, list1);
    }

    public static SimpleType simpleType$default(TypeDeserializer typeDeserializer0, Type protoBuf$Type0, boolean z, int v, Object object0) {
        if((v & 2) != 0) {
            z = true;
        }
        return typeDeserializer0.simpleType(protoBuf$Type0, z);
    }

    private final TypeAttributes toAttributes(List list0, Annotations annotations0, TypeConstructor typeConstructor0, DeclarationDescriptor declarationDescriptor0) {
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
        for(Object object0: list0) {
            arrayList0.add(((TypeAttributeTranslator)object0).toAttributes(annotations0, typeConstructor0, declarationDescriptor0));
        }
        List list1 = CollectionsKt.flatten(arrayList0);
        return TypeAttributes.Companion.create(list1);
    }

    @Override
    public String toString() {
        return this.debugName + (this.parent == null ? "" : ". Child of " + this.parent.debugName);
    }

    private final SimpleType transformRuntimeFunctionTypeToSuspendFunction(KotlinType kotlinType0) {
        TypeProjection typeProjection0 = (TypeProjection)CollectionsKt.lastOrNull(FunctionTypesKt.getValueParameterTypesFromFunctionType(kotlinType0));
        FqName fqName0 = null;
        if(typeProjection0 != null) {
            KotlinType kotlinType1 = typeProjection0.getType();
            if(kotlinType1 != null) {
                ClassifierDescriptor classifierDescriptor0 = kotlinType1.getConstructor().getDeclarationDescriptor();
                FqName fqName1 = classifierDescriptor0 == null ? null : DescriptorUtilsKt.getFqNameSafe(classifierDescriptor0);
                if(kotlinType1.getArguments().size() == 1 && (Intrinsics.areEqual(fqName1, StandardNames.CONTINUATION_INTERFACE_FQ_NAME) || Intrinsics.areEqual(fqName1, TypeDeserializerKt.EXPERIMENTAL_CONTINUATION_FQ_NAME))) {
                    KotlinType kotlinType2 = ((TypeProjection)CollectionsKt.single(kotlinType1.getArguments())).getType();
                    Intrinsics.checkNotNullExpressionValue(kotlinType2, "continuationArgumentType.arguments.single().type");
                    DeclarationDescriptor declarationDescriptor0 = this.c.getContainingDeclaration();
                    CallableDescriptor callableDescriptor0 = declarationDescriptor0 instanceof CallableDescriptor ? ((CallableDescriptor)declarationDescriptor0) : null;
                    if(callableDescriptor0 != null) {
                        fqName0 = DescriptorUtilsKt.fqNameOrNull(callableDescriptor0);
                    }
                    return Intrinsics.areEqual(fqName0, SuspendFunctionTypeUtilKt.KOTLIN_SUSPEND_BUILT_IN_FUNCTION_FQ_NAME) ? this.createSimpleSuspendFunctionType(kotlinType0, kotlinType2) : this.createSimpleSuspendFunctionType(kotlinType0, kotlinType2);
                }
                return (SimpleType)kotlinType0;
            }
        }
        return null;
    }

    public final KotlinType type(Type protoBuf$Type0) {
        Intrinsics.checkNotNullParameter(protoBuf$Type0, "proto");
        if(protoBuf$Type0.hasFlexibleTypeCapabilitiesId()) {
            String s = this.c.getNameResolver().getString(protoBuf$Type0.getFlexibleTypeCapabilitiesId());
            SimpleType simpleType0 = TypeDeserializer.simpleType$default(this, protoBuf$Type0, false, 2, null);
            Type protoBuf$Type1 = ProtoTypeTableUtilKt.flexibleUpperBound(protoBuf$Type0, this.c.getTypeTable());
            Intrinsics.checkNotNull(protoBuf$Type1);
            SimpleType simpleType1 = TypeDeserializer.simpleType$default(this, protoBuf$Type1, false, 2, null);
            return this.c.getComponents().getFlexibleTypeDeserializer().create(protoBuf$Type0, s, simpleType0, simpleType1);
        }
        return this.simpleType(protoBuf$Type0, true);
    }

    private final TypeProjection typeArgument(TypeParameterDescriptor typeParameterDescriptor0, Argument protoBuf$Type$Argument0) {
        if(protoBuf$Type$Argument0.getProjection() == Projection.STAR) {
            return typeParameterDescriptor0 == null ? new StarProjectionForAbsentTypeParameter(this.c.getComponents().getModuleDescriptor().getBuiltIns()) : new StarProjectionImpl(typeParameterDescriptor0);
        }
        Projection protoBuf$Type$Argument$Projection0 = protoBuf$Type$Argument0.getProjection();
        Intrinsics.checkNotNullExpressionValue(protoBuf$Type$Argument$Projection0, "typeArgumentProto.projection");
        Variance variance0 = ProtoEnumFlags.INSTANCE.variance(protoBuf$Type$Argument$Projection0);
        Type protoBuf$Type0 = ProtoTypeTableUtilKt.type(protoBuf$Type$Argument0, this.c.getTypeTable());
        return protoBuf$Type0 == null ? new TypeProjectionImpl(ErrorUtils.createErrorType(ErrorTypeKind.NO_RECORDED_TYPE, new String[]{protoBuf$Type$Argument0.toString()})) : new TypeProjectionImpl(variance0, this.type(protoBuf$Type0));
    }

    private final TypeConstructor typeConstructor(Type protoBuf$Type0) {
        ClassifierDescriptor classifierDescriptor0;
        if(protoBuf$Type0.hasClassName()) {
            classifierDescriptor0 = (ClassifierDescriptor)this.classifierDescriptors.invoke(protoBuf$Type0.getClassName());
            if(classifierDescriptor0 != null) {
                goto label_27;
            }
            classifierDescriptor0 = TypeDeserializer.typeConstructor$notFoundClass(this, protoBuf$Type0, protoBuf$Type0.getClassName());
        }
        else {
            if(protoBuf$Type0.hasTypeParameter()) {
                TypeParameterDescriptor typeParameterDescriptor0 = this.loadTypeParameter(protoBuf$Type0.getTypeParameter());
                if(typeParameterDescriptor0 == null) {
                    return ErrorUtils.INSTANCE.createErrorTypeConstructor(ErrorTypeKind.CANNOT_LOAD_DESERIALIZE_TYPE_PARAMETER, new String[]{String.valueOf(protoBuf$Type0.getTypeParameter()), this.containerPresentableName});
                }
                classifierDescriptor0 = typeParameterDescriptor0;
                goto label_27;
            }
            if(protoBuf$Type0.hasTypeParameterName()) {
                Object object0 = null;
                String s = this.c.getNameResolver().getString(protoBuf$Type0.getTypeParameterName());
                for(Object object1: this.getOwnTypeParameters()) {
                    if(Intrinsics.areEqual(((TypeParameterDescriptor)object1).getName().asString(), s)) {
                        object0 = object1;
                        break;
                    }
                }
                if(((TypeParameterDescriptor)object0) == null) {
                    return ErrorUtils.INSTANCE.createErrorTypeConstructor(ErrorTypeKind.CANNOT_LOAD_DESERIALIZE_TYPE_PARAMETER_BY_NAME, new String[]{s, this.c.getContainingDeclaration().toString()});
                }
                classifierDescriptor0 = (TypeParameterDescriptor)object0;
            }
            else if(protoBuf$Type0.hasTypeAliasName()) {
                classifierDescriptor0 = (ClassifierDescriptor)this.typeAliasDescriptors.invoke(protoBuf$Type0.getTypeAliasName());
                if(classifierDescriptor0 == null) {
                    classifierDescriptor0 = TypeDeserializer.typeConstructor$notFoundClass(this, protoBuf$Type0, protoBuf$Type0.getTypeAliasName());
                }
            }
            else {
                return ErrorUtils.INSTANCE.createErrorTypeConstructor(ErrorTypeKind.UNKNOWN_TYPE, new String[0]);
            }
        }
    label_27:
        TypeConstructor typeConstructor0 = classifierDescriptor0.getTypeConstructor();
        Intrinsics.checkNotNullExpressionValue(typeConstructor0, "classifier.typeConstructor");
        return typeConstructor0;
    }

    private static final ClassDescriptor typeConstructor$notFoundClass(TypeDeserializer typeDeserializer0, Type protoBuf$Type0, int v) {
        ClassId classId0 = NameResolverUtilKt.getClassId(typeDeserializer0.c.getNameResolver(), v);
        List list0 = SequencesKt.toMutableList(SequencesKt.map(SequencesKt.generateSequence(protoBuf$Type0, new Function1(typeDeserializer0) {
            {
                TypeDeserializer.this = typeDeserializer0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((Type)object0));
            }

            public final Type invoke(Type protoBuf$Type0) {
                Intrinsics.checkNotNullParameter(protoBuf$Type0, "it");
                return ProtoTypeTableUtilKt.outerType(protoBuf$Type0, TypeDeserializer.this.c.getTypeTable());
            }
        }), kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer.typeConstructor.notFoundClass.typeParametersCount.2.INSTANCE));
        int v1 = SequencesKt.count(SequencesKt.generateSequence(classId0, kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer.typeConstructor.notFoundClass.classNestingLevel.1.INSTANCE));
        while(list0.size() < v1) {
            list0.add(0);
        }
        return typeDeserializer0.c.getComponents().getNotFoundClasses().getClass(classId0, list0);

        final class kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer.typeConstructor.notFoundClass.classNestingLevel.1 extends FunctionReference implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer.typeConstructor.notFoundClass.classNestingLevel.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer.typeConstructor.notFoundClass.classNestingLevel.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer.typeConstructor.notFoundClass.classNestingLevel.1();
            }

            kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer.typeConstructor.notFoundClass.classNestingLevel.1() {
                super(1);
            }

            @Override  // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
            public final String getName() {
                return "getOuterClassId";
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public final KDeclarationContainer getOwner() {
                return Reflection.getOrCreateKotlinClass(ClassId.class);
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public final String getSignature() {
                return "getOuterClassId()Lorg/jetbrains/kotlin/name/ClassId;";
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((ClassId)object0));
            }

            public final ClassId invoke(ClassId classId0) {
                Intrinsics.checkNotNullParameter(classId0, "p0");
                return classId0.getOuterClassId();
            }
        }


        final class kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer.typeConstructor.notFoundClass.typeParametersCount.2 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer.typeConstructor.notFoundClass.typeParametersCount.2 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer.typeConstructor.notFoundClass.typeParametersCount.2.INSTANCE = new kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer.typeConstructor.notFoundClass.typeParametersCount.2();
            }

            kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer.typeConstructor.notFoundClass.typeParametersCount.2() {
                super(1);
            }

            public final Integer invoke(Type protoBuf$Type0) {
                Intrinsics.checkNotNullParameter(protoBuf$Type0, "it");
                return protoBuf$Type0.getArgumentCount();
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((Type)object0));
            }
        }

    }
}

