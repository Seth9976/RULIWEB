package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value.Type;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument.Value;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation.Argument;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.constants.AnnotationValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.BooleanValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ByteValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.CharValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValueFactory;
import kotlin.reflect.jvm.internal.impl.resolve.constants.DoubleValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ErrorValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.FloatValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.IntValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.KClassValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.LongValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ShortValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.StringValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.UByteValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.UIntValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ULongValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.UShortValue;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorUtils;

public final class AnnotationDeserializer {
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] arr_v = new int[Type.values().length];
            try {
                arr_v[Type.BYTE.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[Type.CHAR.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[Type.SHORT.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[Type.INT.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[Type.LONG.ordinal()] = 5;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[Type.FLOAT.ordinal()] = 6;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[Type.DOUBLE.ordinal()] = 7;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[Type.BOOLEAN.ordinal()] = 8;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[Type.STRING.ordinal()] = 9;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[Type.CLASS.ordinal()] = 10;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[Type.ENUM.ordinal()] = 11;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[Type.ANNOTATION.ordinal()] = 12;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[Type.ARRAY.ordinal()] = 13;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$0 = arr_v;
        }
    }

    private final ModuleDescriptor module;
    private final NotFoundClasses notFoundClasses;

    public AnnotationDeserializer(ModuleDescriptor moduleDescriptor0, NotFoundClasses notFoundClasses0) {
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "module");
        Intrinsics.checkNotNullParameter(notFoundClasses0, "notFoundClasses");
        super();
        this.module = moduleDescriptor0;
        this.notFoundClasses = notFoundClasses0;
    }

    public final AnnotationDescriptor deserializeAnnotation(Annotation protoBuf$Annotation0, NameResolver nameResolver0) {
        Intrinsics.checkNotNullParameter(protoBuf$Annotation0, "proto");
        Intrinsics.checkNotNullParameter(nameResolver0, "nameResolver");
        ClassDescriptor classDescriptor0 = this.resolveClass(NameResolverUtilKt.getClassId(nameResolver0, protoBuf$Annotation0.getId()));
        Map map0 = MapsKt.emptyMap();
        if(protoBuf$Annotation0.getArgumentCount() != 0 && !ErrorUtils.isError(classDescriptor0) && DescriptorUtils.isAnnotationClass(classDescriptor0)) {
            Collection collection0 = classDescriptor0.getConstructors();
            Intrinsics.checkNotNullExpressionValue(collection0, "annotationClass.constructors");
            ClassConstructorDescriptor classConstructorDescriptor0 = (ClassConstructorDescriptor)CollectionsKt.singleOrNull(collection0);
            if(classConstructorDescriptor0 != null) {
                List list0 = classConstructorDescriptor0.getValueParameters();
                Intrinsics.checkNotNullExpressionValue(list0, "constructor.valueParameters");
                Map map1 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(list0, 10)), 16));
                for(Object object0: list0) {
                    map1.put(((ValueParameterDescriptor)object0).getName(), object0);
                }
                List list1 = protoBuf$Annotation0.getArgumentList();
                Intrinsics.checkNotNullExpressionValue(list1, "proto.argumentList");
                Collection collection1 = new ArrayList();
                for(Object object1: list1) {
                    Intrinsics.checkNotNullExpressionValue(((Argument)object1), "it");
                    Pair pair0 = this.resolveArgument(((Argument)object1), map1, nameResolver0);
                    if(pair0 != null) {
                        collection1.add(pair0);
                    }
                }
                map0 = MapsKt.toMap(((List)collection1));
            }
        }
        return new AnnotationDescriptorImpl(classDescriptor0.getDefaultType(), map0, SourceElement.NO_SOURCE);
    }

    private final boolean doesValueConformToExpectedType(ConstantValue constantValue0, KotlinType kotlinType0, Value protoBuf$Annotation$Argument$Value0) {
        Type protoBuf$Annotation$Argument$Value$Type0 = protoBuf$Annotation$Argument$Value0.getType();
        switch((protoBuf$Annotation$Argument$Value$Type0 == null ? -1 : WhenMappings.$EnumSwitchMapping$0[protoBuf$Annotation$Argument$Value$Type0.ordinal()])) {
            case 10: {
                ClassifierDescriptor classifierDescriptor0 = kotlinType0.getConstructor().getDeclarationDescriptor();
                ClassDescriptor classDescriptor0 = classifierDescriptor0 instanceof ClassDescriptor ? ((ClassDescriptor)classifierDescriptor0) : null;
                return classDescriptor0 == null || KotlinBuiltIns.isKClass(classDescriptor0);
            }
            case 13: {
                if(!(constantValue0 instanceof ArrayValue) || ((List)((ArrayValue)constantValue0).getValue()).size() != protoBuf$Annotation$Argument$Value0.getArrayElementList().size()) {
                    throw new IllegalStateException(("Deserialized ArrayValue should have the same number of elements as the original array value: " + constantValue0).toString());
                }
                KotlinType kotlinType1 = this.getBuiltIns().getArrayElementType(kotlinType0);
                Intrinsics.checkNotNullExpressionValue(kotlinType1, "builtIns.getArrayElementType(expectedType)");
                Iterable iterable0 = CollectionsKt.getIndices(((Collection)((ArrayValue)constantValue0).getValue()));
                if(iterable0 instanceof Collection && ((Collection)iterable0).isEmpty()) {
                    return true;
                }
                Iterator iterator0 = iterable0.iterator();
                while(iterator0.hasNext()) {
                    int v = ((IntIterator)iterator0).nextInt();
                    ConstantValue constantValue1 = (ConstantValue)((List)((ArrayValue)constantValue0).getValue()).get(v);
                    Value protoBuf$Annotation$Argument$Value1 = protoBuf$Annotation$Argument$Value0.getArrayElement(v);
                    Intrinsics.checkNotNullExpressionValue(protoBuf$Annotation$Argument$Value1, "value.getArrayElement(i)");
                    if(!this.doesValueConformToExpectedType(constantValue1, kotlinType1, protoBuf$Annotation$Argument$Value1)) {
                        return false;
                    }
                    if(false) {
                        break;
                    }
                }
                return true;
            }
            default: {
                return Intrinsics.areEqual(constantValue0.getType(this.module), kotlinType0);
            }
        }
    }

    private final KotlinBuiltIns getBuiltIns() {
        return this.module.getBuiltIns();
    }

    private final Pair resolveArgument(Argument protoBuf$Annotation$Argument0, Map map0, NameResolver nameResolver0) {
        ValueParameterDescriptor valueParameterDescriptor0 = (ValueParameterDescriptor)map0.get(NameResolverUtilKt.getName(nameResolver0, protoBuf$Annotation$Argument0.getNameId()));
        if(valueParameterDescriptor0 == null) {
            return null;
        }
        Name name0 = NameResolverUtilKt.getName(nameResolver0, protoBuf$Annotation$Argument0.getNameId());
        KotlinType kotlinType0 = valueParameterDescriptor0.getType();
        Intrinsics.checkNotNullExpressionValue(kotlinType0, "parameter.type");
        Value protoBuf$Annotation$Argument$Value0 = protoBuf$Annotation$Argument0.getValue();
        Intrinsics.checkNotNullExpressionValue(protoBuf$Annotation$Argument$Value0, "proto.value");
        return new Pair(name0, this.resolveValueAndCheckExpectedType(kotlinType0, protoBuf$Annotation$Argument$Value0, nameResolver0));
    }

    private final ClassDescriptor resolveClass(ClassId classId0) {
        return FindClassInModuleKt.findNonGenericClassAcrossDependencies(this.module, classId0, this.notFoundClasses);
    }

    public final ConstantValue resolveValue(KotlinType kotlinType0, Value protoBuf$Annotation$Argument$Value0, NameResolver nameResolver0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "expectedType");
        Intrinsics.checkNotNullParameter(protoBuf$Annotation$Argument$Value0, "value");
        Intrinsics.checkNotNullParameter(nameResolver0, "nameResolver");
        Boolean boolean0 = Flags.IS_UNSIGNED.get(protoBuf$Annotation$Argument$Value0.getFlags());
        Intrinsics.checkNotNullExpressionValue(boolean0, "IS_UNSIGNED.get(value.flags)");
        boolean z = boolean0.booleanValue();
        Type protoBuf$Annotation$Argument$Value$Type0 = protoBuf$Annotation$Argument$Value0.getType();
        switch((protoBuf$Annotation$Argument$Value$Type0 == null ? -1 : WhenMappings.$EnumSwitchMapping$0[protoBuf$Annotation$Argument$Value$Type0.ordinal()])) {
            case 1: {
                int v = (byte)(((int)protoBuf$Annotation$Argument$Value0.getIntValue()));
                return z ? new UByteValue(((byte)v)) : new ByteValue(((byte)v));
            }
            case 2: {
                return new CharValue(((char)(((int)protoBuf$Annotation$Argument$Value0.getIntValue()))));
            }
            case 3: {
                int v1 = (short)(((int)protoBuf$Annotation$Argument$Value0.getIntValue()));
                return z ? new UShortValue(((short)v1)) : new ShortValue(((short)v1));
            }
            case 4: {
                int v2 = (int)protoBuf$Annotation$Argument$Value0.getIntValue();
                return z ? new UIntValue(v2) : new IntValue(v2);
            }
            case 5: {
                long v3 = protoBuf$Annotation$Argument$Value0.getIntValue();
                return z ? new ULongValue(v3) : new LongValue(v3);
            }
            case 6: {
                return new FloatValue(protoBuf$Annotation$Argument$Value0.getFloatValue());
            }
            case 7: {
                return new DoubleValue(protoBuf$Annotation$Argument$Value0.getDoubleValue());
            }
            case 8: {
                return protoBuf$Annotation$Argument$Value0.getIntValue() == 0L ? new BooleanValue(false) : new BooleanValue(true);
            }
            case 9: {
                return new StringValue(nameResolver0.getString(protoBuf$Annotation$Argument$Value0.getStringValue()));
            }
            case 10: {
                return new KClassValue(NameResolverUtilKt.getClassId(nameResolver0, protoBuf$Annotation$Argument$Value0.getClassId()), protoBuf$Annotation$Argument$Value0.getArrayDimensionCount());
            }
            case 11: {
                return new EnumValue(NameResolverUtilKt.getClassId(nameResolver0, protoBuf$Annotation$Argument$Value0.getClassId()), NameResolverUtilKt.getName(nameResolver0, protoBuf$Annotation$Argument$Value0.getEnumValueId()));
            }
            case 12: {
                Annotation protoBuf$Annotation0 = protoBuf$Annotation$Argument$Value0.getAnnotation();
                Intrinsics.checkNotNullExpressionValue(protoBuf$Annotation0, "value.annotation");
                return new AnnotationValue(this.deserializeAnnotation(protoBuf$Annotation0, nameResolver0));
            }
            case 13: {
                ConstantValueFactory constantValueFactory0 = ConstantValueFactory.INSTANCE;
                List list0 = protoBuf$Annotation$Argument$Value0.getArrayElementList();
                Intrinsics.checkNotNullExpressionValue(list0, "value.arrayElementList");
                ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
                for(Object object0: list0) {
                    SimpleType simpleType0 = this.getBuiltIns().getAnyType();
                    Intrinsics.checkNotNullExpressionValue(simpleType0, "builtIns.anyType");
                    Intrinsics.checkNotNullExpressionValue(((Value)object0), "it");
                    arrayList0.add(this.resolveValue(simpleType0, ((Value)object0), nameResolver0));
                }
                return constantValueFactory0.createArrayValue(arrayList0, kotlinType0);
            }
            default: {
                throw new IllegalStateException(("Unsupported annotation argument type: " + protoBuf$Annotation$Argument$Value0.getType() + " (expected " + kotlinType0 + ')').toString());
            }
        }
    }

    private final ConstantValue resolveValueAndCheckExpectedType(KotlinType kotlinType0, Value protoBuf$Annotation$Argument$Value0, NameResolver nameResolver0) {
        ConstantValue constantValue0 = this.resolveValue(kotlinType0, protoBuf$Annotation$Argument$Value0, nameResolver0);
        if(!this.doesValueConformToExpectedType(constantValue0, kotlinType0, protoBuf$Annotation$Argument$Value0)) {
            constantValue0 = null;
        }
        return constantValue0 == null ? ErrorValue.Companion.create("Unexpected argument value: actual type " + protoBuf$Annotation$Argument$Value0.getType() + " != expected type " + kotlinType0) : constantValue0;
    }
}

