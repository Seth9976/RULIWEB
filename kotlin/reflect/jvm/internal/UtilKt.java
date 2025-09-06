package kotlin.reflect.jvm.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference;
import kotlin.jvm.internal.RepeatableContainer;
import kotlin.reflect.KCallable;
import kotlin.reflect.KType;
import kotlin.reflect.KVisibility;
import kotlin.reflect.jvm.internal.calls.AnnotationConstructorCallerKt;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.ReflectAnnotationSource;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.ReflectJavaClassFinderKt;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.ReflectKotlinClass;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.RuntimeModuleData;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.RuntimeSourceElementFactory.RuntimeSourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaAnnotation;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaElement;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinarySourceElement;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.constants.AnnotationValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ErrorValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.KClassValue.Value.LocalClass;
import kotlin.reflect.jvm.internal.impl.resolve.constants.KClassValue.Value.NormalClass;
import kotlin.reflect.jvm.internal.impl.resolve.constants.KClassValue.Value;
import kotlin.reflect.jvm.internal.impl.resolve.constants.KClassValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.NullValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.TypedArrayValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u00CA\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u001B\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A\u0012\u0010\r\u001A\u0004\u0018\u00010\u000E2\u0006\u0010\u000F\u001A\u00020\u0010H\u0000\u001Al\u0010\u0011\u001A\u0002H\u0012\"\b\b\u0000\u0010\u0013*\u00020\u0014\"\b\b\u0001\u0010\u0012*\u00020\u00062\n\u0010\u0015\u001A\u0006\u0012\u0002\b\u00030\u00162\u0006\u0010\u0017\u001A\u0002H\u00132\u0006\u0010\u0018\u001A\u00020\u00192\u0006\u0010\u001A\u001A\u00020\u001B2\u0006\u0010\u001C\u001A\u00020\u001D2\u001D\u0010\u001E\u001A\u0019\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u0002H\u00120\u001F\u00A2\u0006\u0002\b!H\u0000\u00A2\u0006\u0002\u0010\"\u001A.\u0010#\u001A\b\u0012\u0002\b\u0003\u0018\u00010\u00162\u0006\u0010$\u001A\u00020%2\u0006\u0010&\u001A\u00020\'2\u0006\u0010(\u001A\u00020\'2\u0006\u0010)\u001A\u00020*H\u0002\u001A(\u0010#\u001A\b\u0012\u0002\b\u0003\u0018\u00010\u00162\u0006\u0010$\u001A\u00020%2\u0006\u0010+\u001A\u00020,2\b\b\u0002\u0010)\u001A\u00020*H\u0002\u001A%\u0010-\u001A\u0002H.\"\u0004\b\u0000\u0010.2\f\u0010/\u001A\b\u0012\u0004\u0012\u0002H.00H\u0080\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u00101\u001A\u0016\u00102\u001A\u0004\u0018\u00010\u000E*\u0002032\u0006\u0010$\u001A\u00020%H\u0002\u001A\u0014\u00104\u001A\b\u0012\u0002\b\u0003\u0018\u000105*\u0004\u0018\u00010\u000EH\u0000\u001A\u0010\u00106\u001A\u0004\u0018\u000107*\u0004\u0018\u00010\u000EH\u0000\u001A\u0014\u00108\u001A\b\u0012\u0002\b\u0003\u0018\u000109*\u0004\u0018\u00010\u000EH\u0000\u001A\u0012\u0010:\u001A\b\u0012\u0004\u0012\u00020<0;*\u00020=H\u0000\u001A\u0014\u0010>\u001A\u0006\u0012\u0002\b\u00030\u0016*\u0006\u0012\u0002\b\u00030\u0016H\u0000\u001A\u000E\u0010?\u001A\u0004\u0018\u00010<*\u00020@H\u0002\u001A\u0012\u0010A\u001A\b\u0012\u0002\b\u0003\u0018\u00010\u0016*\u00020BH\u0000\u001A\u000E\u0010C\u001A\u0004\u0018\u00010D*\u00020EH\u0000\u001A\u001A\u0010F\u001A\u0004\u0018\u00010\u000E*\u0006\u0012\u0002\b\u00030G2\u0006\u0010$\u001A\u00020%H\u0002\u001A\u0018\u0010H\u001A\b\u0012\u0004\u0012\u00020<0;*\b\u0012\u0004\u0012\u00020<0;H\u0002\"\u0014\u0010\u0000\u001A\u00020\u0001X\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0002\u0010\u0003\"\u001A\u0010\u0004\u001A\u0004\u0018\u00010\u0005*\u00020\u00068@X\u0080\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0007\u0010\b\"\u0018\u0010\t\u001A\u00020\n*\u00020\u000B8@X\u0080\u0004\u00A2\u0006\u0006\u001A\u0004\b\t\u0010\f\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00A8\u0006I"}, d2 = {"JVM_STATIC", "Lkotlin/reflect/jvm/internal/impl/name/FqName;", "getJVM_STATIC", "()Lorg/jetbrains/kotlin/name/FqName;", "instanceReceiverParameter", "Lkotlin/reflect/jvm/internal/impl/descriptors/ReceiverParameterDescriptor;", "Lkotlin/reflect/jvm/internal/impl/descriptors/CallableDescriptor;", "getInstanceReceiverParameter", "(Lorg/jetbrains/kotlin/descriptors/CallableDescriptor;)Lorg/jetbrains/kotlin/descriptors/ReceiverParameterDescriptor;", "isInlineClassType", "", "Lkotlin/reflect/KType;", "(Lkotlin/reflect/KType;)Z", "defaultPrimitiveValue", "", "type", "Ljava/lang/reflect/Type;", "deserializeToDescriptor", "D", "M", "Lkotlin/reflect/jvm/internal/impl/protobuf/MessageLite;", "moduleAnchor", "Ljava/lang/Class;", "proto", "nameResolver", "Lkotlin/reflect/jvm/internal/impl/metadata/deserialization/NameResolver;", "typeTable", "Lkotlin/reflect/jvm/internal/impl/metadata/deserialization/TypeTable;", "metadataVersion", "Lkotlin/reflect/jvm/internal/impl/metadata/deserialization/BinaryVersion;", "createDescriptor", "Lkotlin/Function2;", "Lkotlin/reflect/jvm/internal/impl/serialization/deserialization/MemberDeserializer;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Class;Lorg/jetbrains/kotlin/protobuf/MessageLite;Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;Lorg/jetbrains/kotlin/metadata/deserialization/TypeTable;Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;Lkotlin/jvm/functions/Function2;)Lorg/jetbrains/kotlin/descriptors/CallableDescriptor;", "loadClass", "classLoader", "Ljava/lang/ClassLoader;", "packageName", "", "className", "arrayDimensions", "", "kotlinClassId", "Lkotlin/reflect/jvm/internal/impl/name/ClassId;", "reflectionCall", "R", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "arrayToRuntimeValue", "Lkotlin/reflect/jvm/internal/impl/resolve/constants/ArrayValue;", "asKCallableImpl", "Lkotlin/reflect/jvm/internal/KCallableImpl;", "asKFunctionImpl", "Lkotlin/reflect/jvm/internal/KFunctionImpl;", "asKPropertyImpl", "Lkotlin/reflect/jvm/internal/KPropertyImpl;", "computeAnnotations", "", "", "Lkotlin/reflect/jvm/internal/impl/descriptors/annotations/Annotated;", "createArrayType", "toAnnotationInstance", "Lkotlin/reflect/jvm/internal/impl/descriptors/annotations/AnnotationDescriptor;", "toJavaClass", "Lkotlin/reflect/jvm/internal/impl/descriptors/ClassDescriptor;", "toKVisibility", "Lkotlin/reflect/KVisibility;", "Lkotlin/reflect/jvm/internal/impl/descriptors/DescriptorVisibility;", "toRuntimeValue", "Lkotlin/reflect/jvm/internal/impl/resolve/constants/ConstantValue;", "unwrapRepeatableAnnotations", "kotlin-reflection"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class UtilKt {
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] arr_v = new int[PrimitiveType.values().length];
            try {
                arr_v[PrimitiveType.BOOLEAN.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[PrimitiveType.CHAR.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[PrimitiveType.BYTE.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[PrimitiveType.SHORT.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[PrimitiveType.INT.ordinal()] = 5;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[PrimitiveType.FLOAT.ordinal()] = 6;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[PrimitiveType.LONG.ordinal()] = 7;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[PrimitiveType.DOUBLE.ordinal()] = 8;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$0 = arr_v;
        }
    }

    private static final FqName JVM_STATIC;

    static {
        UtilKt.JVM_STATIC = new FqName("kotlin.jvm.JvmStatic");
    }

    private static final Object arrayToRuntimeValue(ArrayValue arrayValue0, ClassLoader classLoader0) {
        TypedArrayValue typedArrayValue0 = arrayValue0 instanceof TypedArrayValue ? ((TypedArrayValue)arrayValue0) : null;
        if(typedArrayValue0 != null) {
            KotlinType kotlinType0 = typedArrayValue0.getType();
            if(kotlinType0 != null) {
                Iterable iterable0 = (Iterable)arrayValue0.getValue();
                ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
                for(Object object0: iterable0) {
                    arrayList0.add(UtilKt.toRuntimeValue(((ConstantValue)object0), classLoader0));
                }
                PrimitiveType primitiveType0 = KotlinBuiltIns.getPrimitiveArrayElementType(kotlinType0);
                int v = 0;
                switch((primitiveType0 == null ? -1 : WhenMappings.$EnumSwitchMapping$0[primitiveType0.ordinal()])) {
                    case -1: {
                        if(!KotlinBuiltIns.isArray(kotlinType0)) {
                            throw new IllegalStateException(("Not an array type: " + kotlinType0).toString());
                        }
                        KotlinType kotlinType1 = ((TypeProjection)CollectionsKt.single(kotlinType0.getArguments())).getType();
                        Intrinsics.checkNotNullExpressionValue(kotlinType1, "type.arguments.single().type");
                        ClassifierDescriptor classifierDescriptor0 = kotlinType1.getConstructor().getDeclarationDescriptor();
                        ClassDescriptor classDescriptor0 = classifierDescriptor0 instanceof ClassDescriptor ? ((ClassDescriptor)classifierDescriptor0) : null;
                        if(classDescriptor0 == null) {
                            throw new IllegalStateException(("Not a class type: " + kotlinType1).toString());
                        }
                        if(KotlinBuiltIns.isString(kotlinType1)) {
                            int v1 = ((List)arrayValue0.getValue()).size();
                            String[] arr_s = new String[v1];
                            while(v < v1) {
                                Object object1 = arrayList0.get(v);
                                Intrinsics.checkNotNull(object1, "null cannot be cast to non-null type kotlin.String");
                                arr_s[v] = object1;
                                ++v;
                            }
                            return arr_s;
                        }
                        if(KotlinBuiltIns.isKClass(classDescriptor0)) {
                            int v2 = ((List)arrayValue0.getValue()).size();
                            Class[] arr_class = new Class[v2];
                            while(v < v2) {
                                Object object2 = arrayList0.get(v);
                                Intrinsics.checkNotNull(object2, "null cannot be cast to non-null type java.lang.Class<*>");
                                arr_class[v] = object2;
                                ++v;
                            }
                            return arr_class;
                        }
                        ClassId classId0 = DescriptorUtilsKt.getClassId(classDescriptor0);
                        if(classId0 != null) {
                            Class class0 = UtilKt.loadClass$default(classLoader0, classId0, 0, 4, null);
                            if(class0 != null) {
                                Object object3 = Array.newInstance(class0, ((List)arrayValue0.getValue()).size());
                                Intrinsics.checkNotNull(object3, "null cannot be cast to non-null type kotlin.Array<in kotlin.Any?>");
                                int v3 = arrayList0.size();
                                while(v < v3) {
                                    ((Object[])object3)[v] = arrayList0.get(v);
                                    ++v;
                                }
                                return (Object[])object3;
                            }
                        }
                        return null;
                    }
                    case 1: {
                        int v4 = ((List)arrayValue0.getValue()).size();
                        boolean[] arr_z = new boolean[v4];
                        while(v < v4) {
                            Object object4 = arrayList0.get(v);
                            Intrinsics.checkNotNull(object4, "null cannot be cast to non-null type kotlin.Boolean");
                            arr_z[v] = ((Boolean)object4).booleanValue();
                            ++v;
                        }
                        return arr_z;
                    }
                    case 2: {
                        int v5 = ((List)arrayValue0.getValue()).size();
                        char[] arr_c = new char[v5];
                        while(v < v5) {
                            Object object5 = arrayList0.get(v);
                            Intrinsics.checkNotNull(object5, "null cannot be cast to non-null type kotlin.Char");
                            arr_c[v] = ((Character)object5).charValue();
                            ++v;
                        }
                        return arr_c;
                    }
                    case 3: {
                        int v6 = ((List)arrayValue0.getValue()).size();
                        byte[] arr_b = new byte[v6];
                        while(v < v6) {
                            Object object6 = arrayList0.get(v);
                            Intrinsics.checkNotNull(object6, "null cannot be cast to non-null type kotlin.Byte");
                            arr_b[v] = (byte)(((Byte)object6));
                            ++v;
                        }
                        return arr_b;
                    }
                    case 4: {
                        int v7 = ((List)arrayValue0.getValue()).size();
                        short[] arr_v = new short[v7];
                        while(v < v7) {
                            Object object7 = arrayList0.get(v);
                            Intrinsics.checkNotNull(object7, "null cannot be cast to non-null type kotlin.Short");
                            arr_v[v] = (short)(((Short)object7));
                            ++v;
                        }
                        return arr_v;
                    }
                    case 5: {
                        int v8 = ((List)arrayValue0.getValue()).size();
                        int[] arr_v1 = new int[v8];
                        while(v < v8) {
                            Object object8 = arrayList0.get(v);
                            Intrinsics.checkNotNull(object8, "null cannot be cast to non-null type kotlin.Int");
                            arr_v1[v] = (int)(((Integer)object8));
                            ++v;
                        }
                        return arr_v1;
                    }
                    case 6: {
                        int v9 = ((List)arrayValue0.getValue()).size();
                        float[] arr_f = new float[v9];
                        while(v < v9) {
                            Object object9 = arrayList0.get(v);
                            Intrinsics.checkNotNull(object9, "null cannot be cast to non-null type kotlin.Float");
                            arr_f[v] = (float)(((Float)object9));
                            ++v;
                        }
                        return arr_f;
                    }
                    case 7: {
                        int v10 = ((List)arrayValue0.getValue()).size();
                        long[] arr_v2 = new long[v10];
                        while(v < v10) {
                            Object object10 = arrayList0.get(v);
                            Intrinsics.checkNotNull(object10, "null cannot be cast to non-null type kotlin.Long");
                            arr_v2[v] = (long)(((Long)object10));
                            ++v;
                        }
                        return arr_v2;
                    }
                    case 8: {
                        int v11 = ((List)arrayValue0.getValue()).size();
                        double[] arr_f1 = new double[v11];
                        while(v < v11) {
                            Object object11 = arrayList0.get(v);
                            Intrinsics.checkNotNull(object11, "null cannot be cast to non-null type kotlin.Double");
                            arr_f1[v] = (double)(((Double)object11));
                            ++v;
                        }
                        return arr_f1;
                    }
                    default: {
                        throw new NoWhenBranchMatchedException();
                    }
                }
            }
        }
        return null;
    }

    public static final KCallableImpl asKCallableImpl(Object object0) {
        KCallableImpl kCallableImpl0 = object0 instanceof KCallableImpl ? ((KCallableImpl)object0) : null;
        if(kCallableImpl0 == null) {
            KFunctionImpl kFunctionImpl0 = UtilKt.asKFunctionImpl(object0);
            return kFunctionImpl0 != null ? kFunctionImpl0 : UtilKt.asKPropertyImpl(object0);
        }
        return kCallableImpl0;
    }

    public static final KFunctionImpl asKFunctionImpl(Object object0) {
        KFunctionImpl kFunctionImpl0 = object0 instanceof KFunctionImpl ? ((KFunctionImpl)object0) : null;
        if(kFunctionImpl0 == null) {
            FunctionReference functionReference0 = object0 instanceof FunctionReference ? ((FunctionReference)object0) : null;
            KCallable kCallable0 = functionReference0 == null ? null : functionReference0.compute();
            return kCallable0 instanceof KFunctionImpl ? ((KFunctionImpl)kCallable0) : null;
        }
        return kFunctionImpl0;
    }

    public static final KPropertyImpl asKPropertyImpl(Object object0) {
        KPropertyImpl kPropertyImpl0 = object0 instanceof KPropertyImpl ? ((KPropertyImpl)object0) : null;
        if(kPropertyImpl0 == null) {
            PropertyReference propertyReference0 = object0 instanceof PropertyReference ? ((PropertyReference)object0) : null;
            KCallable kCallable0 = propertyReference0 == null ? null : propertyReference0.compute();
            return kCallable0 instanceof KPropertyImpl ? ((KPropertyImpl)kCallable0) : null;
        }
        return kPropertyImpl0;
    }

    public static final List computeAnnotations(Annotated annotated0) {
        Annotation annotation0;
        Intrinsics.checkNotNullParameter(annotated0, "<this>");
        Iterable iterable0 = annotated0.getAnnotations();
        Collection collection0 = new ArrayList();
        for(Object object0: iterable0) {
            AnnotationDescriptor annotationDescriptor0 = (AnnotationDescriptor)object0;
            SourceElement sourceElement0 = annotationDescriptor0.getSource();
            if(sourceElement0 instanceof ReflectAnnotationSource) {
                annotation0 = ((ReflectAnnotationSource)sourceElement0).getAnnotation();
            }
            else if(sourceElement0 instanceof RuntimeSourceElement) {
                ReflectJavaElement reflectJavaElement0 = ((RuntimeSourceElement)sourceElement0).getJavaElement();
                ReflectJavaAnnotation reflectJavaAnnotation0 = reflectJavaElement0 instanceof ReflectJavaAnnotation ? ((ReflectJavaAnnotation)reflectJavaElement0) : null;
                annotation0 = reflectJavaAnnotation0 == null ? null : reflectJavaAnnotation0.getAnnotation();
            }
            else {
                annotation0 = UtilKt.toAnnotationInstance(annotationDescriptor0);
            }
            if(annotation0 != null) {
                collection0.add(annotation0);
            }
        }
        return UtilKt.unwrapRepeatableAnnotations(((List)collection0));
    }

    public static final Class createArrayType(Class class0) {
        Intrinsics.checkNotNullParameter(class0, "<this>");
        return Array.newInstance(class0, 0).getClass();
    }

    public static final Object defaultPrimitiveValue(Type type0) {
        Intrinsics.checkNotNullParameter(type0, "type");
        if(type0 instanceof Class && ((Class)type0).isPrimitive()) {
            if(Intrinsics.areEqual(type0, Boolean.TYPE)) {
                return false;
            }
            if(Intrinsics.areEqual(type0, Character.TYPE)) {
                return Character.valueOf('\u0000');
            }
            if(Intrinsics.areEqual(type0, Byte.TYPE)) {
                return (byte)0;
            }
            if(Intrinsics.areEqual(type0, Short.TYPE)) {
                return (short)0;
            }
            if(Intrinsics.areEqual(type0, Integer.TYPE)) {
                return 0;
            }
            if(Intrinsics.areEqual(type0, Float.TYPE)) {
                return 0.0f;
            }
            if(Intrinsics.areEqual(type0, Long.TYPE)) {
                return 0L;
            }
            if(Intrinsics.areEqual(type0, Double.TYPE)) {
                return 0.0;
            }
            if(Intrinsics.areEqual(type0, Void.TYPE)) {
                throw new IllegalStateException("Parameter with void type is illegal");
            }
            throw new UnsupportedOperationException("Unknown primitive: " + type0);
        }
        return null;
    }

    public static final CallableDescriptor deserializeToDescriptor(Class class0, MessageLite messageLite0, NameResolver nameResolver0, TypeTable typeTable0, BinaryVersion binaryVersion0, Function2 function20) {
        List list0;
        Intrinsics.checkNotNullParameter(class0, "moduleAnchor");
        Intrinsics.checkNotNullParameter(messageLite0, "proto");
        Intrinsics.checkNotNullParameter(nameResolver0, "nameResolver");
        Intrinsics.checkNotNullParameter(typeTable0, "typeTable");
        Intrinsics.checkNotNullParameter(binaryVersion0, "metadataVersion");
        Intrinsics.checkNotNullParameter(function20, "createDescriptor");
        RuntimeModuleData runtimeModuleData0 = ModuleByClassLoaderKt.getOrCreateModule(class0);
        if(messageLite0 instanceof Function) {
            list0 = ((Function)messageLite0).getTypeParameterList();
        }
        else if(messageLite0 instanceof Property) {
            list0 = ((Property)messageLite0).getTypeParameterList();
        }
        else {
            throw new IllegalStateException(("Unsupported message: " + messageLite0).toString());
        }
        ModuleDescriptor moduleDescriptor0 = runtimeModuleData0.getModule();
        VersionRequirementTable versionRequirementTable0 = VersionRequirementTable.Companion.getEMPTY();
        Intrinsics.checkNotNullExpressionValue(list0, "typeParameters");
        return (CallableDescriptor)function20.invoke(new MemberDeserializer(new DeserializationContext(runtimeModuleData0.getDeserialization(), nameResolver0, moduleDescriptor0, typeTable0, versionRequirementTable0, binaryVersion0, null, null, list0)), messageLite0);
    }

    public static final ReceiverParameterDescriptor getInstanceReceiverParameter(CallableDescriptor callableDescriptor0) {
        Intrinsics.checkNotNullParameter(callableDescriptor0, "<this>");
        if(callableDescriptor0.getDispatchReceiverParameter() != null) {
            DeclarationDescriptor declarationDescriptor0 = callableDescriptor0.getContainingDeclaration();
            Intrinsics.checkNotNull(declarationDescriptor0, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
            return ((ClassDescriptor)declarationDescriptor0).getThisAsReceiverParameter();
        }
        return null;
    }

    public static final FqName getJVM_STATIC() {
        return UtilKt.JVM_STATIC;
    }

    public static final boolean isInlineClassType(KType kType0) {
        Intrinsics.checkNotNullParameter(kType0, "<this>");
        KTypeImpl kTypeImpl0 = kType0 instanceof KTypeImpl ? ((KTypeImpl)kType0) : null;
        if(kTypeImpl0 != null) {
            KotlinType kotlinType0 = kTypeImpl0.getType();
            return kotlinType0 != null && InlineClassesUtilsKt.isInlineClassType(kotlinType0);
        }
        return false;
    }

    private static final Class loadClass(ClassLoader classLoader0, String s, String s1, int v) {
        if(Intrinsics.areEqual(s, "kotlin")) {
            switch(s1) {
                case "Array": {
                    return Object[].class;
                }
                case "BooleanArray": {
                    return boolean[].class;
                }
                case "ByteArray": {
                    return byte[].class;
                }
                case "CharArray": {
                    return char[].class;
                }
                case "DoubleArray": {
                    return double[].class;
                }
                case "FloatArray": {
                    return float[].class;
                }
                case "IntArray": {
                    return int[].class;
                }
                case "LongArray": {
                    return long[].class;
                }
                case "ShortArray": {
                    return short[].class;
                }
            }
        }
        StringBuilder stringBuilder0 = new StringBuilder();
        if(v > 0) {
            for(int v1 = 0; v1 < v; ++v1) {
                stringBuilder0.append("[");
            }
            stringBuilder0.append("L");
        }
        if(s.length() > 0) {
            stringBuilder0.append(s + '.');
        }
        stringBuilder0.append(StringsKt.replace$default(s1, '.', '$', false, 4, null));
        if(v > 0) {
            stringBuilder0.append(";");
        }
        String s2 = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s2, "StringBuilder().apply(builderAction).toString()");
        return ReflectJavaClassFinderKt.tryLoadClass(classLoader0, s2);
    }

    private static final Class loadClass(ClassLoader classLoader0, ClassId classId0, int v) {
        FqNameUnsafe fqNameUnsafe0 = classId0.asSingleFqName().toUnsafe();
        Intrinsics.checkNotNullExpressionValue(fqNameUnsafe0, "kotlinClassId.asSingleFqName().toUnsafe()");
        ClassId classId1 = JavaToKotlinClassMap.INSTANCE.mapKotlinToJava(fqNameUnsafe0);
        if(classId1 != null) {
            classId0 = classId1;
        }
        String s = classId0.getPackageFqName().asString();
        Intrinsics.checkNotNullExpressionValue(s, "javaClassId.packageFqName.asString()");
        String s1 = classId0.getRelativeClassName().asString();
        Intrinsics.checkNotNullExpressionValue(s1, "javaClassId.relativeClassName.asString()");
        return UtilKt.loadClass(classLoader0, s, s1, v);
    }

    static Class loadClass$default(ClassLoader classLoader0, ClassId classId0, int v, int v1, Object object0) {
        if((v1 & 4) != 0) {
            v = 0;
        }
        return UtilKt.loadClass(classLoader0, classId0, v);
    }

    private static final Annotation toAnnotationInstance(AnnotationDescriptor annotationDescriptor0) {
        ClassDescriptor classDescriptor0 = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor0);
        Class class0 = classDescriptor0 == null ? null : UtilKt.toJavaClass(classDescriptor0);
        if(!(class0 instanceof Class)) {
            class0 = null;
        }
        if(class0 == null) {
            return null;
        }
        Iterable iterable0 = annotationDescriptor0.getAllValueArguments().entrySet();
        Collection collection0 = new ArrayList();
        for(Object object0: iterable0) {
            Name name0 = (Name)((Map.Entry)object0).getKey();
            ConstantValue constantValue0 = (ConstantValue)((Map.Entry)object0).getValue();
            ClassLoader classLoader0 = class0.getClassLoader();
            Intrinsics.checkNotNullExpressionValue(classLoader0, "annotationClass.classLoader");
            Object object1 = UtilKt.toRuntimeValue(constantValue0, classLoader0);
            Pair pair0 = object1 == null ? null : TuplesKt.to(name0.asString(), object1);
            if(pair0 != null) {
                collection0.add(pair0);
            }
        }
        return (Annotation)AnnotationConstructorCallerKt.createAnnotationInstance$default(class0, MapsKt.toMap(((List)collection0)), null, 4, null);
    }

    public static final Class toJavaClass(ClassDescriptor classDescriptor0) {
        Intrinsics.checkNotNullParameter(classDescriptor0, "<this>");
        SourceElement sourceElement0 = classDescriptor0.getSource();
        Intrinsics.checkNotNullExpressionValue(sourceElement0, "source");
        if(sourceElement0 instanceof KotlinJvmBinarySourceElement) {
            KotlinJvmBinaryClass kotlinJvmBinaryClass0 = ((KotlinJvmBinarySourceElement)sourceElement0).getBinaryClass();
            Intrinsics.checkNotNull(kotlinJvmBinaryClass0, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.runtime.components.ReflectKotlinClass");
            return ((ReflectKotlinClass)kotlinJvmBinaryClass0).getKlass();
        }
        if(sourceElement0 instanceof RuntimeSourceElement) {
            ReflectJavaElement reflectJavaElement0 = ((RuntimeSourceElement)sourceElement0).getJavaElement();
            Intrinsics.checkNotNull(reflectJavaElement0, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.runtime.structure.ReflectJavaClass");
            return ((ReflectJavaClass)reflectJavaElement0).getElement();
        }
        ClassId classId0 = DescriptorUtilsKt.getClassId(classDescriptor0);
        return classId0 == null ? null : UtilKt.loadClass(ReflectClassUtilKt.getSafeClassLoader(classDescriptor0.getClass()), classId0, 0);
    }

    public static final KVisibility toKVisibility(DescriptorVisibility descriptorVisibility0) {
        Intrinsics.checkNotNullParameter(descriptorVisibility0, "<this>");
        if(Intrinsics.areEqual(descriptorVisibility0, DescriptorVisibilities.PUBLIC)) {
            return KVisibility.PUBLIC;
        }
        if(Intrinsics.areEqual(descriptorVisibility0, DescriptorVisibilities.PROTECTED)) {
            return KVisibility.PROTECTED;
        }
        if(Intrinsics.areEqual(descriptorVisibility0, DescriptorVisibilities.INTERNAL)) {
            return KVisibility.INTERNAL;
        }
        return (Intrinsics.areEqual(descriptorVisibility0, DescriptorVisibilities.PRIVATE) ? true : Intrinsics.areEqual(descriptorVisibility0, DescriptorVisibilities.PRIVATE_TO_THIS)) ? KVisibility.PRIVATE : null;
    }

    private static final Object toRuntimeValue(ConstantValue constantValue0, ClassLoader classLoader0) {
        if(constantValue0 instanceof AnnotationValue) {
            return UtilKt.toAnnotationInstance(((AnnotationDescriptor)((AnnotationValue)constantValue0).getValue()));
        }
        if(constantValue0 instanceof ArrayValue) {
            return UtilKt.arrayToRuntimeValue(((ArrayValue)constantValue0), classLoader0);
        }
        if(constantValue0 instanceof EnumValue) {
            Pair pair0 = (Pair)((EnumValue)constantValue0).getValue();
            Class class0 = UtilKt.loadClass$default(classLoader0, ((ClassId)pair0.component1()), 0, 4, null);
            return class0 == null ? null : Util.getEnumConstantByName(class0, ((Name)pair0.component2()).asString());
        }
        if(constantValue0 instanceof KClassValue) {
            Value kClassValue$Value0 = (Value)((KClassValue)constantValue0).getValue();
            if(kClassValue$Value0 instanceof NormalClass) {
                return UtilKt.loadClass(classLoader0, ((NormalClass)kClassValue$Value0).getClassId(), ((NormalClass)kClassValue$Value0).getArrayDimensions());
            }
            if(!(kClassValue$Value0 instanceof LocalClass)) {
                throw new NoWhenBranchMatchedException();
            }
            ClassifierDescriptor classifierDescriptor0 = ((LocalClass)kClassValue$Value0).getType().getConstructor().getDeclarationDescriptor();
            ClassDescriptor classDescriptor0 = classifierDescriptor0 instanceof ClassDescriptor ? ((ClassDescriptor)classifierDescriptor0) : null;
            return classDescriptor0 != null ? UtilKt.toJavaClass(classDescriptor0) : null;
        }
        return (constantValue0 instanceof ErrorValue ? true : constantValue0 instanceof NullValue) ? null : constantValue0.getValue();
    }

    private static final List unwrapRepeatableAnnotations(List list0) {
        List list1;
        if(list0 instanceof Collection && list0.isEmpty()) {
            return list0;
        }
        for(Object object0: list0) {
            if(Intrinsics.areEqual(JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass(((Annotation)object0))).getSimpleName(), "Container")) {
                Collection collection0 = new ArrayList();
                for(Object object1: list0) {
                    Annotation annotation0 = (Annotation)object1;
                    Class class0 = JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass(annotation0));
                    if(!Intrinsics.areEqual(class0.getSimpleName(), "Container") || class0.getAnnotation(RepeatableContainer.class) == null) {
                        list1 = CollectionsKt.listOf(annotation0);
                    }
                    else {
                        Object object2 = class0.getDeclaredMethod("value", null).invoke(annotation0, null);
                        Intrinsics.checkNotNull(object2, "null cannot be cast to non-null type kotlin.Array<out kotlin.Annotation>");
                        list1 = ArraysKt.asList(((Annotation[])object2));
                    }
                    CollectionsKt.addAll(collection0, list1);
                }
                return (List)collection0;
            }
            if(false) {
                break;
            }
        }
        return list0;
    }
}

