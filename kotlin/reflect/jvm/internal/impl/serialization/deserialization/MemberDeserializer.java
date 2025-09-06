package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor.UserDataKey;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations.Companion;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.FieldDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyGetterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertySetterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ValueParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Constructor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.MemberKind;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeAlias;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Visibility;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedAnnotations;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedPropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedSimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedTypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.NonEmptyDeserializedAnnotations;
import kotlin.reflect.jvm.internal.impl.storage.NullableLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

public final class MemberDeserializer {
    private final AnnotationDeserializer annotationDeserializer;
    private final DeserializationContext c;

    public MemberDeserializer(DeserializationContext deserializationContext0) {
        Intrinsics.checkNotNullParameter(deserializationContext0, "c");
        super();
        this.c = deserializationContext0;
        this.annotationDeserializer = new AnnotationDeserializer(deserializationContext0.getComponents().getModuleDescriptor(), deserializationContext0.getComponents().getNotFoundClasses());
    }

    private final ProtoContainer asProtoContainer(DeclarationDescriptor declarationDescriptor0) {
        if(declarationDescriptor0 instanceof PackageFragmentDescriptor) {
            return new Package(((PackageFragmentDescriptor)declarationDescriptor0).getFqName(), this.c.getNameResolver(), this.c.getTypeTable(), this.c.getContainerSource());
        }
        return declarationDescriptor0 instanceof DeserializedClassDescriptor ? ((DeserializedClassDescriptor)declarationDescriptor0).getThisAsProtoContainer$deserialization() : null;
    }

    private final Annotations getAnnotations(MessageLite messageLite0, int v, AnnotatedCallableKind annotatedCallableKind0) {
        return !Flags.HAS_ANNOTATIONS.get(v).booleanValue() ? Annotations.Companion.getEMPTY() : new NonEmptyDeserializedAnnotations(this.c.getStorageManager(), new Function0(messageLite0, annotatedCallableKind0) {
            final AnnotatedCallableKind $kind;
            final MessageLite $proto;

            {
                MemberDeserializer.this = memberDeserializer0;
                this.$proto = messageLite0;
                this.$kind = annotatedCallableKind0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final List invoke() {
                ProtoContainer protoContainer0 = MemberDeserializer.this.asProtoContainer(MemberDeserializer.this.c.getContainingDeclaration());
                List list0 = protoContainer0 == null ? null : CollectionsKt.toList(MemberDeserializer.this.c.getComponents().getAnnotationAndConstantLoader().loadCallableAnnotations(protoContainer0, this.$proto, this.$kind));
                return list0 == null ? CollectionsKt.emptyList() : list0;
            }
        });
    }

    private final ReceiverParameterDescriptor getDispatchReceiverParameter() {
        DeclarationDescriptor declarationDescriptor0 = this.c.getContainingDeclaration();
        ClassDescriptor classDescriptor0 = declarationDescriptor0 instanceof ClassDescriptor ? ((ClassDescriptor)declarationDescriptor0) : null;
        return classDescriptor0 == null ? null : classDescriptor0.getThisAsReceiverParameter();
    }

    private final Annotations getPropertyFieldAnnotations(Property protoBuf$Property0, boolean z) {
        return !Flags.HAS_ANNOTATIONS.get(protoBuf$Property0.getFlags()).booleanValue() ? Annotations.Companion.getEMPTY() : new NonEmptyDeserializedAnnotations(this.c.getStorageManager(), new Function0(z, protoBuf$Property0) {
            final boolean $isDelegate;
            final Property $proto;

            {
                MemberDeserializer.this = memberDeserializer0;
                this.$isDelegate = z;
                this.$proto = protoBuf$Property0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final List invoke() {
                List list0;
                ProtoContainer protoContainer0 = MemberDeserializer.this.asProtoContainer(MemberDeserializer.this.c.getContainingDeclaration());
                if(protoContainer0 != null) {
                    MemberDeserializer memberDeserializer0 = MemberDeserializer.this;
                    Property protoBuf$Property0 = this.$proto;
                    if(this.$isDelegate) {
                        list0 = CollectionsKt.toList(memberDeserializer0.c.getComponents().getAnnotationAndConstantLoader().loadPropertyDelegateFieldAnnotations(protoContainer0, protoBuf$Property0));
                        return list0 == null ? CollectionsKt.emptyList() : list0;
                    }
                    list0 = CollectionsKt.toList(memberDeserializer0.c.getComponents().getAnnotationAndConstantLoader().loadPropertyBackingFieldAnnotations(protoContainer0, protoBuf$Property0));
                    return list0 == null ? CollectionsKt.emptyList() : list0;
                }
                return CollectionsKt.emptyList();
            }
        });
    }

    private final Annotations getReceiverParameterAnnotations(MessageLite messageLite0, AnnotatedCallableKind annotatedCallableKind0) {
        return new DeserializedAnnotations(this.c.getStorageManager(), new Function0(messageLite0, annotatedCallableKind0) {
            final AnnotatedCallableKind $kind;
            final MessageLite $proto;

            {
                MemberDeserializer.this = memberDeserializer0;
                this.$proto = messageLite0;
                this.$kind = annotatedCallableKind0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final List invoke() {
                ProtoContainer protoContainer0 = MemberDeserializer.this.asProtoContainer(MemberDeserializer.this.c.getContainingDeclaration());
                List list0 = protoContainer0 == null ? null : MemberDeserializer.this.c.getComponents().getAnnotationAndConstantLoader().loadExtensionReceiverParameterAnnotations(protoContainer0, this.$proto, this.$kind);
                return list0 == null ? CollectionsKt.emptyList() : list0;
            }
        });
    }

    private final void initializeWithCoroutinesExperimentalityStatus(DeserializedSimpleFunctionDescriptor deserializedSimpleFunctionDescriptor0, ReceiverParameterDescriptor receiverParameterDescriptor0, ReceiverParameterDescriptor receiverParameterDescriptor1, List list0, List list1, List list2, KotlinType kotlinType0, Modality modality0, DescriptorVisibility descriptorVisibility0, Map map0) {
        deserializedSimpleFunctionDescriptor0.initialize(receiverParameterDescriptor0, receiverParameterDescriptor1, list0, list1, list2, kotlinType0, modality0, descriptorVisibility0, map0);
    }

    public final ClassConstructorDescriptor loadConstructor(Constructor protoBuf$Constructor0, boolean z) {
        Intrinsics.checkNotNullParameter(protoBuf$Constructor0, "proto");
        DeclarationDescriptor declarationDescriptor0 = this.c.getContainingDeclaration();
        Intrinsics.checkNotNull(declarationDescriptor0, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
        DeserializedClassConstructorDescriptor deserializedClassConstructorDescriptor0 = new DeserializedClassConstructorDescriptor(((ClassDescriptor)declarationDescriptor0), null, this.getAnnotations(protoBuf$Constructor0, protoBuf$Constructor0.getFlags(), AnnotatedCallableKind.FUNCTION), z, Kind.DECLARATION, protoBuf$Constructor0, this.c.getNameResolver(), this.c.getTypeTable(), this.c.getVersionRequirementTable(), this.c.getContainerSource(), null, 0x400, null);
        List list0 = CollectionsKt.emptyList();
        MemberDeserializer memberDeserializer0 = DeserializationContext.childContext$default(this.c, deserializedClassConstructorDescriptor0, list0, null, null, null, null, 60, null).getMemberDeserializer();
        List list1 = protoBuf$Constructor0.getValueParameterList();
        Intrinsics.checkNotNullExpressionValue(list1, "proto.valueParameterList");
        List list2 = memberDeserializer0.valueParameters(list1, protoBuf$Constructor0, AnnotatedCallableKind.FUNCTION);
        Visibility protoBuf$Visibility0 = (Visibility)Flags.VISIBILITY.get(protoBuf$Constructor0.getFlags());
        deserializedClassConstructorDescriptor0.initialize(list2, ProtoEnumFlagsUtilsKt.descriptorVisibility(ProtoEnumFlags.INSTANCE, protoBuf$Visibility0));
        deserializedClassConstructorDescriptor0.setReturnType(((ClassDescriptor)declarationDescriptor0).getDefaultType());
        deserializedClassConstructorDescriptor0.setExpect(((ClassDescriptor)declarationDescriptor0).isExpect());
        deserializedClassConstructorDescriptor0.setHasStableParameterNames(!Flags.IS_CONSTRUCTOR_WITH_NON_STABLE_PARAMETER_NAMES.get(protoBuf$Constructor0.getFlags()).booleanValue());
        return deserializedClassConstructorDescriptor0;
    }

    public final SimpleFunctionDescriptor loadFunction(Function protoBuf$Function0) {
        ReceiverParameterDescriptor receiverParameterDescriptor0;
        Intrinsics.checkNotNullParameter(protoBuf$Function0, "proto");
        int v = protoBuf$Function0.hasFlags() ? protoBuf$Function0.getFlags() : (protoBuf$Function0.getOldFlags() & 0x3F) + (protoBuf$Function0.getOldFlags() >> 8 << 6);
        Annotations annotations0 = this.getAnnotations(protoBuf$Function0, v, AnnotatedCallableKind.FUNCTION);
        Annotations annotations1 = ProtoTypeTableUtilKt.hasReceiver(protoBuf$Function0) ? this.getReceiverParameterAnnotations(protoBuf$Function0, AnnotatedCallableKind.FUNCTION) : Annotations.Companion.getEMPTY();
        VersionRequirementTable versionRequirementTable0 = Intrinsics.areEqual(DescriptorUtilsKt.getFqNameSafe(this.c.getContainingDeclaration()).child(NameResolverUtilKt.getName(this.c.getNameResolver(), protoBuf$Function0.getName())), SuspendFunctionTypeUtilKt.KOTLIN_SUSPEND_BUILT_IN_FUNCTION_FQ_NAME) ? VersionRequirementTable.Companion.getEMPTY() : this.c.getVersionRequirementTable();
        Name name0 = NameResolverUtilKt.getName(this.c.getNameResolver(), protoBuf$Function0.getName());
        MemberKind protoBuf$MemberKind0 = (MemberKind)Flags.MEMBER_KIND.get(v);
        Kind callableMemberDescriptor$Kind0 = ProtoEnumFlagsUtilsKt.memberKind(ProtoEnumFlags.INSTANCE, protoBuf$MemberKind0);
        DeserializedSimpleFunctionDescriptor deserializedSimpleFunctionDescriptor0 = new DeserializedSimpleFunctionDescriptor(this.c.getContainingDeclaration(), null, annotations0, name0, callableMemberDescriptor$Kind0, protoBuf$Function0, this.c.getNameResolver(), this.c.getTypeTable(), versionRequirementTable0, this.c.getContainerSource(), null, 0x400, null);
        List list0 = protoBuf$Function0.getTypeParameterList();
        Intrinsics.checkNotNullExpressionValue(list0, "proto.typeParameterList");
        DeserializationContext deserializationContext0 = DeserializationContext.childContext$default(this.c, deserializedSimpleFunctionDescriptor0, list0, null, null, null, null, 60, null);
        Type protoBuf$Type0 = ProtoTypeTableUtilKt.receiverType(protoBuf$Function0, this.c.getTypeTable());
        if(protoBuf$Type0 == null) {
            receiverParameterDescriptor0 = null;
        }
        else {
            KotlinType kotlinType0 = deserializationContext0.getTypeDeserializer().type(protoBuf$Type0);
            receiverParameterDescriptor0 = kotlinType0 == null ? null : DescriptorFactory.createExtensionReceiverParameterForCallable(deserializedSimpleFunctionDescriptor0, kotlinType0, annotations1);
        }
        ReceiverParameterDescriptor receiverParameterDescriptor1 = this.getDispatchReceiverParameter();
        Iterable iterable0 = ProtoTypeTableUtilKt.contextReceiverTypes(protoBuf$Function0, this.c.getTypeTable());
        Collection collection0 = new ArrayList();
        int v1 = 0;
        for(Object object0: iterable0) {
            if(v1 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            ReceiverParameterDescriptor receiverParameterDescriptor2 = this.toContextReceiver(((Type)object0), deserializationContext0, deserializedSimpleFunctionDescriptor0, v1);
            if(receiverParameterDescriptor2 != null) {
                collection0.add(receiverParameterDescriptor2);
            }
            ++v1;
        }
        List list1 = deserializationContext0.getTypeDeserializer().getOwnTypeParameters();
        List list2 = protoBuf$Function0.getValueParameterList();
        Intrinsics.checkNotNullExpressionValue(list2, "proto.valueParameterList");
        List list3 = deserializationContext0.getMemberDeserializer().valueParameters(list2, protoBuf$Function0, AnnotatedCallableKind.FUNCTION);
        KotlinType kotlinType1 = deserializationContext0.getTypeDeserializer().type(ProtoTypeTableUtilKt.returnType(protoBuf$Function0, this.c.getTypeTable()));
        kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Modality protoBuf$Modality0 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Modality)Flags.MODALITY.get(v);
        Modality modality0 = ProtoEnumFlags.INSTANCE.modality(protoBuf$Modality0);
        Visibility protoBuf$Visibility0 = (Visibility)Flags.VISIBILITY.get(v);
        this.initializeWithCoroutinesExperimentalityStatus(deserializedSimpleFunctionDescriptor0, receiverParameterDescriptor0, receiverParameterDescriptor1, ((List)collection0), list1, list3, kotlinType1, modality0, ProtoEnumFlagsUtilsKt.descriptorVisibility(ProtoEnumFlags.INSTANCE, protoBuf$Visibility0), MapsKt.emptyMap());
        Boolean boolean0 = Flags.IS_OPERATOR.get(v);
        Intrinsics.checkNotNullExpressionValue(boolean0, "IS_OPERATOR.get(flags)");
        deserializedSimpleFunctionDescriptor0.setOperator(boolean0.booleanValue());
        Boolean boolean1 = Flags.IS_INFIX.get(v);
        Intrinsics.checkNotNullExpressionValue(boolean1, "IS_INFIX.get(flags)");
        deserializedSimpleFunctionDescriptor0.setInfix(boolean1.booleanValue());
        Boolean boolean2 = Flags.IS_EXTERNAL_FUNCTION.get(v);
        Intrinsics.checkNotNullExpressionValue(boolean2, "IS_EXTERNAL_FUNCTION.get(flags)");
        deserializedSimpleFunctionDescriptor0.setExternal(boolean2.booleanValue());
        Boolean boolean3 = Flags.IS_INLINE.get(v);
        Intrinsics.checkNotNullExpressionValue(boolean3, "IS_INLINE.get(flags)");
        deserializedSimpleFunctionDescriptor0.setInline(boolean3.booleanValue());
        Boolean boolean4 = Flags.IS_TAILREC.get(v);
        Intrinsics.checkNotNullExpressionValue(boolean4, "IS_TAILREC.get(flags)");
        deserializedSimpleFunctionDescriptor0.setTailrec(boolean4.booleanValue());
        Boolean boolean5 = Flags.IS_SUSPEND.get(v);
        Intrinsics.checkNotNullExpressionValue(boolean5, "IS_SUSPEND.get(flags)");
        deserializedSimpleFunctionDescriptor0.setSuspend(boolean5.booleanValue());
        Boolean boolean6 = Flags.IS_EXPECT_FUNCTION.get(v);
        Intrinsics.checkNotNullExpressionValue(boolean6, "IS_EXPECT_FUNCTION.get(flags)");
        deserializedSimpleFunctionDescriptor0.setExpect(boolean6.booleanValue());
        deserializedSimpleFunctionDescriptor0.setHasStableParameterNames(!Flags.IS_FUNCTION_WITH_NON_STABLE_PARAMETER_NAMES.get(v).booleanValue());
        Pair pair0 = this.c.getComponents().getContractDeserializer().deserializeContractFromFunction(protoBuf$Function0, deserializedSimpleFunctionDescriptor0, this.c.getTypeTable(), deserializationContext0.getTypeDeserializer());
        if(pair0 != null) {
            deserializedSimpleFunctionDescriptor0.putInUserDataMap(((UserDataKey)pair0.getFirst()), pair0.getSecond());
        }
        return deserializedSimpleFunctionDescriptor0;
    }

    private final int loadOldFlags(int v) [...] // Inlined contents

    public final PropertyDescriptor loadProperty(Property protoBuf$Property0) {
        PropertySetterDescriptorImpl propertySetterDescriptorImpl0;
        PropertyGetterDescriptorImpl propertyGetterDescriptorImpl0;
        ReceiverParameterDescriptor receiverParameterDescriptor1;
        Intrinsics.checkNotNullParameter(protoBuf$Property0, "proto");
        int v = protoBuf$Property0.hasFlags() ? protoBuf$Property0.getFlags() : (protoBuf$Property0.getOldFlags() & 0x3F) + (protoBuf$Property0.getOldFlags() >> 8 << 6);
        Annotations annotations0 = this.getAnnotations(protoBuf$Property0, v, AnnotatedCallableKind.PROPERTY);
        kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Modality protoBuf$Modality0 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Modality)Flags.MODALITY.get(v);
        Modality modality0 = ProtoEnumFlags.INSTANCE.modality(protoBuf$Modality0);
        Visibility protoBuf$Visibility0 = (Visibility)Flags.VISIBILITY.get(v);
        DescriptorVisibility descriptorVisibility0 = ProtoEnumFlagsUtilsKt.descriptorVisibility(ProtoEnumFlags.INSTANCE, protoBuf$Visibility0);
        Boolean boolean0 = Flags.IS_VAR.get(v);
        Intrinsics.checkNotNullExpressionValue(boolean0, "IS_VAR.get(flags)");
        Name name0 = NameResolverUtilKt.getName(this.c.getNameResolver(), protoBuf$Property0.getName());
        MemberKind protoBuf$MemberKind0 = (MemberKind)Flags.MEMBER_KIND.get(v);
        Kind callableMemberDescriptor$Kind0 = ProtoEnumFlagsUtilsKt.memberKind(ProtoEnumFlags.INSTANCE, protoBuf$MemberKind0);
        Boolean boolean1 = Flags.IS_LATEINIT.get(v);
        Intrinsics.checkNotNullExpressionValue(boolean1, "IS_LATEINIT.get(flags)");
        Boolean boolean2 = Flags.IS_CONST.get(v);
        Intrinsics.checkNotNullExpressionValue(boolean2, "IS_CONST.get(flags)");
        Boolean boolean3 = Flags.IS_EXTERNAL_PROPERTY.get(v);
        Intrinsics.checkNotNullExpressionValue(boolean3, "IS_EXTERNAL_PROPERTY.get(flags)");
        Boolean boolean4 = Flags.IS_DELEGATED.get(v);
        Intrinsics.checkNotNullExpressionValue(boolean4, "IS_DELEGATED.get(flags)");
        Boolean boolean5 = Flags.IS_EXPECT_PROPERTY.get(v);
        Intrinsics.checkNotNullExpressionValue(boolean5, "IS_EXPECT_PROPERTY.get(flags)");
        DeserializedPropertyDescriptor deserializedPropertyDescriptor0 = new DeserializedPropertyDescriptor(this.c.getContainingDeclaration(), null, annotations0, modality0, descriptorVisibility0, boolean0.booleanValue(), name0, callableMemberDescriptor$Kind0, boolean1.booleanValue(), boolean2.booleanValue(), boolean3.booleanValue(), boolean4.booleanValue(), boolean5.booleanValue(), protoBuf$Property0, this.c.getNameResolver(), this.c.getTypeTable(), this.c.getVersionRequirementTable(), this.c.getContainerSource());
        List list0 = protoBuf$Property0.getTypeParameterList();
        Intrinsics.checkNotNullExpressionValue(list0, "proto.typeParameterList");
        DeserializationContext deserializationContext0 = DeserializationContext.childContext$default(this.c, deserializedPropertyDescriptor0, list0, null, null, null, null, 60, null);
        Boolean boolean6 = Flags.HAS_GETTER.get(v);
        Intrinsics.checkNotNullExpressionValue(boolean6, "HAS_GETTER.get(flags)");
        boolean z = boolean6.booleanValue();
        Annotations annotations1 = !z || !ProtoTypeTableUtilKt.hasReceiver(protoBuf$Property0) ? Annotations.Companion.getEMPTY() : this.getReceiverParameterAnnotations(protoBuf$Property0, AnnotatedCallableKind.PROPERTY_GETTER);
        KotlinType kotlinType0 = deserializationContext0.getTypeDeserializer().type(ProtoTypeTableUtilKt.returnType(protoBuf$Property0, this.c.getTypeTable()));
        List list1 = deserializationContext0.getTypeDeserializer().getOwnTypeParameters();
        ReceiverParameterDescriptor receiverParameterDescriptor0 = this.getDispatchReceiverParameter();
        Type protoBuf$Type0 = ProtoTypeTableUtilKt.receiverType(protoBuf$Property0, this.c.getTypeTable());
        if(protoBuf$Type0 == null) {
            receiverParameterDescriptor1 = null;
        }
        else {
            KotlinType kotlinType1 = deserializationContext0.getTypeDeserializer().type(protoBuf$Type0);
            receiverParameterDescriptor1 = kotlinType1 == null ? null : DescriptorFactory.createExtensionReceiverParameterForCallable(deserializedPropertyDescriptor0, kotlinType1, annotations1);
        }
        Iterable iterable0 = ProtoTypeTableUtilKt.contextReceiverTypes(protoBuf$Property0, this.c.getTypeTable());
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
        int v1 = 0;
        for(Object object0: iterable0) {
            if(v1 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            arrayList0.add(this.toContextReceiver(((Type)object0), deserializationContext0, deserializedPropertyDescriptor0, v1));
            ++v1;
        }
        deserializedPropertyDescriptor0.setType(kotlinType0, list1, receiverParameterDescriptor0, receiverParameterDescriptor1, arrayList0);
        Boolean boolean7 = Flags.HAS_ANNOTATIONS.get(v);
        Intrinsics.checkNotNullExpressionValue(boolean7, "HAS_ANNOTATIONS.get(flags)");
        int v2 = Flags.getAccessorFlags(boolean7.booleanValue(), ((Visibility)Flags.VISIBILITY.get(v)), ((kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Modality)Flags.MODALITY.get(v)), false, false, false);
        if(z) {
            int v3 = protoBuf$Property0.hasGetterFlags() ? protoBuf$Property0.getGetterFlags() : v2;
            Boolean boolean8 = Flags.IS_NOT_DEFAULT.get(v3);
            Intrinsics.checkNotNullExpressionValue(boolean8, "IS_NOT_DEFAULT.get(getterFlags)");
            Boolean boolean9 = Flags.IS_EXTERNAL_ACCESSOR.get(v3);
            Intrinsics.checkNotNullExpressionValue(boolean9, "IS_EXTERNAL_ACCESSOR.get(getterFlags)");
            boolean z1 = boolean9.booleanValue();
            Boolean boolean10 = Flags.IS_INLINE_ACCESSOR.get(v3);
            Intrinsics.checkNotNullExpressionValue(boolean10, "IS_INLINE_ACCESSOR.get(getterFlags)");
            boolean z2 = boolean10.booleanValue();
            Annotations annotations2 = this.getAnnotations(protoBuf$Property0, v3, AnnotatedCallableKind.PROPERTY_GETTER);
            if(boolean8.booleanValue()) {
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Modality protoBuf$Modality1 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Modality)Flags.MODALITY.get(v3);
                Modality modality1 = ProtoEnumFlags.INSTANCE.modality(protoBuf$Modality1);
                Visibility protoBuf$Visibility1 = (Visibility)Flags.VISIBILITY.get(v3);
                propertyGetterDescriptorImpl0 = new PropertyGetterDescriptorImpl(deserializedPropertyDescriptor0, annotations2, modality1, ProtoEnumFlagsUtilsKt.descriptorVisibility(ProtoEnumFlags.INSTANCE, protoBuf$Visibility1), false, z1, z2, deserializedPropertyDescriptor0.getKind(), null, SourceElement.NO_SOURCE);
            }
            else {
                propertyGetterDescriptorImpl0 = DescriptorFactory.createDefaultGetter(deserializedPropertyDescriptor0, annotations2);
                Intrinsics.checkNotNullExpressionValue(propertyGetterDescriptorImpl0, "{\n                Descri…nnotations)\n            }");
            }
            propertyGetterDescriptorImpl0.initialize(deserializedPropertyDescriptor0.getReturnType());
        }
        else {
            propertyGetterDescriptorImpl0 = null;
        }
        Boolean boolean11 = Flags.HAS_SETTER.get(v);
        Intrinsics.checkNotNullExpressionValue(boolean11, "HAS_SETTER.get(flags)");
        if(boolean11.booleanValue()) {
            if(protoBuf$Property0.hasSetterFlags()) {
                v2 = protoBuf$Property0.getSetterFlags();
            }
            Boolean boolean12 = Flags.IS_NOT_DEFAULT.get(v2);
            Intrinsics.checkNotNullExpressionValue(boolean12, "IS_NOT_DEFAULT.get(setterFlags)");
            Boolean boolean13 = Flags.IS_EXTERNAL_ACCESSOR.get(v2);
            Intrinsics.checkNotNullExpressionValue(boolean13, "IS_EXTERNAL_ACCESSOR.get(setterFlags)");
            boolean z3 = boolean13.booleanValue();
            Boolean boolean14 = Flags.IS_INLINE_ACCESSOR.get(v2);
            Intrinsics.checkNotNullExpressionValue(boolean14, "IS_INLINE_ACCESSOR.get(setterFlags)");
            boolean z4 = boolean14.booleanValue();
            Annotations annotations3 = this.getAnnotations(protoBuf$Property0, v2, AnnotatedCallableKind.PROPERTY_SETTER);
            if(boolean12.booleanValue()) {
                kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Modality protoBuf$Modality2 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Modality)Flags.MODALITY.get(v2);
                Modality modality2 = ProtoEnumFlags.INSTANCE.modality(protoBuf$Modality2);
                Visibility protoBuf$Visibility2 = (Visibility)Flags.VISIBILITY.get(v2);
                propertySetterDescriptorImpl0 = new PropertySetterDescriptorImpl(deserializedPropertyDescriptor0, annotations3, modality2, ProtoEnumFlagsUtilsKt.descriptorVisibility(ProtoEnumFlags.INSTANCE, protoBuf$Visibility2), false, z3, z4, deserializedPropertyDescriptor0.getKind(), null, SourceElement.NO_SOURCE);
                propertySetterDescriptorImpl0.initialize(((ValueParameterDescriptor)CollectionsKt.single(DeserializationContext.childContext$default(deserializationContext0, propertySetterDescriptorImpl0, CollectionsKt.emptyList(), null, null, null, null, 60, null).getMemberDeserializer().valueParameters(CollectionsKt.listOf(protoBuf$Property0.getSetterValueParameter()), protoBuf$Property0, AnnotatedCallableKind.PROPERTY_SETTER))));
            }
            else {
                PropertySetterDescriptorImpl propertySetterDescriptorImpl1 = DescriptorFactory.createDefaultSetter(deserializedPropertyDescriptor0, annotations3, Annotations.Companion.getEMPTY());
                Intrinsics.checkNotNullExpressionValue(propertySetterDescriptorImpl1, "{\n                Descri…          )\n            }");
                propertySetterDescriptorImpl0 = propertySetterDescriptorImpl1;
            }
        }
        else {
            propertySetterDescriptorImpl0 = null;
        }
        Boolean boolean15 = Flags.HAS_CONSTANT.get(v);
        Intrinsics.checkNotNullExpressionValue(boolean15, "HAS_CONSTANT.get(flags)");
        if(boolean15.booleanValue()) {
            deserializedPropertyDescriptor0.setCompileTimeInitializerFactory(new Function0(protoBuf$Property0, deserializedPropertyDescriptor0) {
                final DeserializedPropertyDescriptor $property;
                final Property $proto;

                {
                    MemberDeserializer.this = memberDeserializer0;
                    this.$proto = protoBuf$Property0;
                    this.$property = deserializedPropertyDescriptor0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final NullableLazyValue invoke() {
                    return MemberDeserializer.this.c.getStorageManager().createNullableLazyValue(new Function0(this.$proto, this.$property) {
                        final DeserializedPropertyDescriptor $property;
                        final Property $proto;

                        {
                            MemberDeserializer.this = memberDeserializer0;
                            this.$proto = protoBuf$Property0;
                            this.$property = deserializedPropertyDescriptor0;
                            super(0);
                        }

                        @Override  // kotlin.jvm.functions.Function0
                        public Object invoke() {
                            return this.invoke();
                        }

                        public final ConstantValue invoke() {
                            ProtoContainer protoContainer0 = MemberDeserializer.this.asProtoContainer(MemberDeserializer.this.c.getContainingDeclaration());
                            Intrinsics.checkNotNull(protoContainer0);
                            AnnotationAndConstantLoader annotationAndConstantLoader0 = MemberDeserializer.this.c.getComponents().getAnnotationAndConstantLoader();
                            KotlinType kotlinType0 = this.$property.getReturnType();
                            Intrinsics.checkNotNullExpressionValue(kotlinType0, "property.returnType");
                            return (ConstantValue)annotationAndConstantLoader0.loadPropertyConstant(protoContainer0, this.$proto, kotlinType0);
                        }
                    });
                }
            });
        }
        DeclarationDescriptor declarationDescriptor0 = this.c.getContainingDeclaration();
        ClassDescriptor classDescriptor0 = declarationDescriptor0 instanceof ClassDescriptor ? ((ClassDescriptor)declarationDescriptor0) : null;
        if((classDescriptor0 == null ? null : classDescriptor0.getKind()) == ClassKind.ANNOTATION_CLASS) {
            deserializedPropertyDescriptor0.setCompileTimeInitializerFactory(new Function0(protoBuf$Property0, deserializedPropertyDescriptor0) {
                final DeserializedPropertyDescriptor $property;
                final Property $proto;

                {
                    MemberDeserializer.this = memberDeserializer0;
                    this.$proto = protoBuf$Property0;
                    this.$property = deserializedPropertyDescriptor0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final NullableLazyValue invoke() {
                    return MemberDeserializer.this.c.getStorageManager().createNullableLazyValue(new Function0(this.$proto, this.$property) {
                        final DeserializedPropertyDescriptor $property;
                        final Property $proto;

                        {
                            MemberDeserializer.this = memberDeserializer0;
                            this.$proto = protoBuf$Property0;
                            this.$property = deserializedPropertyDescriptor0;
                            super(0);
                        }

                        @Override  // kotlin.jvm.functions.Function0
                        public Object invoke() {
                            return this.invoke();
                        }

                        public final ConstantValue invoke() {
                            ProtoContainer protoContainer0 = MemberDeserializer.this.asProtoContainer(MemberDeserializer.this.c.getContainingDeclaration());
                            Intrinsics.checkNotNull(protoContainer0);
                            AnnotationAndConstantLoader annotationAndConstantLoader0 = MemberDeserializer.this.c.getComponents().getAnnotationAndConstantLoader();
                            KotlinType kotlinType0 = this.$property.getReturnType();
                            Intrinsics.checkNotNullExpressionValue(kotlinType0, "property.returnType");
                            return (ConstantValue)annotationAndConstantLoader0.loadAnnotationDefaultValue(protoContainer0, this.$proto, kotlinType0);
                        }
                    });
                }
            });
        }
        deserializedPropertyDescriptor0.initialize(propertyGetterDescriptorImpl0, propertySetterDescriptorImpl0, new FieldDescriptorImpl(this.getPropertyFieldAnnotations(protoBuf$Property0, false), deserializedPropertyDescriptor0), new FieldDescriptorImpl(this.getPropertyFieldAnnotations(protoBuf$Property0, true), deserializedPropertyDescriptor0));
        return deserializedPropertyDescriptor0;
    }

    public final TypeAliasDescriptor loadTypeAlias(TypeAlias protoBuf$TypeAlias0) {
        Intrinsics.checkNotNullParameter(protoBuf$TypeAlias0, "proto");
        Companion annotations$Companion0 = Annotations.Companion;
        List list0 = protoBuf$TypeAlias0.getAnnotationList();
        Intrinsics.checkNotNullExpressionValue(list0, "proto.annotationList");
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
        for(Object object0: list0) {
            Intrinsics.checkNotNullExpressionValue(((Annotation)object0), "it");
            arrayList0.add(this.annotationDeserializer.deserializeAnnotation(((Annotation)object0), this.c.getNameResolver()));
        }
        Annotations annotations0 = annotations$Companion0.create(arrayList0);
        Visibility protoBuf$Visibility0 = (Visibility)Flags.VISIBILITY.get(protoBuf$TypeAlias0.getFlags());
        DescriptorVisibility descriptorVisibility0 = ProtoEnumFlagsUtilsKt.descriptorVisibility(ProtoEnumFlags.INSTANCE, protoBuf$Visibility0);
        StorageManager storageManager0 = this.c.getStorageManager();
        Name name0 = NameResolverUtilKt.getName(this.c.getNameResolver(), protoBuf$TypeAlias0.getName());
        DeserializedTypeAliasDescriptor deserializedTypeAliasDescriptor0 = new DeserializedTypeAliasDescriptor(storageManager0, this.c.getContainingDeclaration(), annotations0, name0, descriptorVisibility0, protoBuf$TypeAlias0, this.c.getNameResolver(), this.c.getTypeTable(), this.c.getVersionRequirementTable(), this.c.getContainerSource());
        List list1 = protoBuf$TypeAlias0.getTypeParameterList();
        Intrinsics.checkNotNullExpressionValue(list1, "proto.typeParameterList");
        DeserializationContext deserializationContext0 = DeserializationContext.childContext$default(this.c, deserializedTypeAliasDescriptor0, list1, null, null, null, null, 60, null);
        deserializedTypeAliasDescriptor0.initialize(deserializationContext0.getTypeDeserializer().getOwnTypeParameters(), deserializationContext0.getTypeDeserializer().simpleType(ProtoTypeTableUtilKt.underlyingType(protoBuf$TypeAlias0, this.c.getTypeTable()), false), deserializationContext0.getTypeDeserializer().simpleType(ProtoTypeTableUtilKt.expandedType(protoBuf$TypeAlias0, this.c.getTypeTable()), false));
        return deserializedTypeAliasDescriptor0;
    }

    private final ReceiverParameterDescriptor toContextReceiver(Type protoBuf$Type0, DeserializationContext deserializationContext0, CallableDescriptor callableDescriptor0, int v) {
        return DescriptorFactory.createContextReceiverParameterForCallable(callableDescriptor0, deserializationContext0.getTypeDeserializer().type(protoBuf$Type0), null, Annotations.Companion.getEMPTY(), v);
    }

    private final List valueParameters(List list0, MessageLite messageLite0, AnnotatedCallableKind annotatedCallableKind0) {
        Annotations annotations0;
        DeclarationDescriptor declarationDescriptor0 = this.c.getContainingDeclaration();
        Intrinsics.checkNotNull(declarationDescriptor0, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.CallableDescriptor");
        DeclarationDescriptor declarationDescriptor1 = ((CallableDescriptor)declarationDescriptor0).getContainingDeclaration();
        Intrinsics.checkNotNullExpressionValue(declarationDescriptor1, "callableDescriptor.containingDeclaration");
        ProtoContainer protoContainer0 = this.asProtoContainer(declarationDescriptor1);
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
        int v = 0;
        for(Object object0: list0) {
            if(v < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            int v1 = ((ValueParameter)object0).hasFlags() ? ((ValueParameter)object0).getFlags() : 0;
            if(protoContainer0 == null) {
                annotations0 = Annotations.Companion.getEMPTY();
            }
            else {
                Boolean boolean0 = Flags.HAS_ANNOTATIONS.get(v1);
                Intrinsics.checkNotNullExpressionValue(boolean0, "HAS_ANNOTATIONS.get(flags)");
                if(boolean0.booleanValue()) {
                    annotations0 = new NonEmptyDeserializedAnnotations(this.c.getStorageManager(), new Function0(protoContainer0, messageLite0, annotatedCallableKind0, v, ((ValueParameter)object0)) {
                        final MessageLite $callable;
                        final ProtoContainer $containerOfCallable;
                        final int $i;
                        final AnnotatedCallableKind $kind;
                        final ValueParameter $proto;

                        {
                            MemberDeserializer.this = memberDeserializer0;
                            this.$containerOfCallable = protoContainer0;
                            this.$callable = messageLite0;
                            this.$kind = annotatedCallableKind0;
                            this.$i = v;
                            this.$proto = protoBuf$ValueParameter0;
                            super(0);
                        }

                        @Override  // kotlin.jvm.functions.Function0
                        public Object invoke() {
                            return this.invoke();
                        }

                        public final List invoke() {
                            return CollectionsKt.toList(MemberDeserializer.this.c.getComponents().getAnnotationAndConstantLoader().loadValueParameterAnnotations(this.$containerOfCallable, this.$callable, this.$kind, this.$i, this.$proto));
                        }
                    });
                }
            }
            int v2 = ((ValueParameter)object0).getName();
            Name name0 = NameResolverUtilKt.getName(this.c.getNameResolver(), v2);
            Type protoBuf$Type0 = ProtoTypeTableUtilKt.type(((ValueParameter)object0), this.c.getTypeTable());
            KotlinType kotlinType0 = this.c.getTypeDeserializer().type(protoBuf$Type0);
            Boolean boolean1 = Flags.DECLARES_DEFAULT_VALUE.get(v1);
            Intrinsics.checkNotNullExpressionValue(boolean1, "DECLARES_DEFAULT_VALUE.get(flags)");
            Boolean boolean2 = Flags.IS_CROSSINLINE.get(v1);
            Intrinsics.checkNotNullExpressionValue(boolean2, "IS_CROSSINLINE.get(flags)");
            Boolean boolean3 = Flags.IS_NOINLINE.get(v1);
            Intrinsics.checkNotNullExpressionValue(boolean3, "IS_NOINLINE.get(flags)");
            Type protoBuf$Type1 = ProtoTypeTableUtilKt.varargElementType(((ValueParameter)object0), this.c.getTypeTable());
            KotlinType kotlinType1 = protoBuf$Type1 == null ? null : this.c.getTypeDeserializer().type(protoBuf$Type1);
            Intrinsics.checkNotNullExpressionValue(SourceElement.NO_SOURCE, "NO_SOURCE");
            arrayList0.add(new ValueParameterDescriptorImpl(((CallableDescriptor)declarationDescriptor0), null, v, annotations0, name0, kotlinType0, boolean1.booleanValue(), boolean2.booleanValue(), boolean3.booleanValue(), kotlinType1, SourceElement.NO_SOURCE));
            ++v;
        }
        return CollectionsKt.toList(arrayList0);
    }
}

