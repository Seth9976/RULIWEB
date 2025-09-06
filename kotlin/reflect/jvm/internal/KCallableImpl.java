package kotlin.reflect.jvm.internal;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KCallable;
import kotlin.reflect.KParameter.Kind;
import kotlin.reflect.KParameter;
import kotlin.reflect.KType;
import kotlin.reflect.KVisibility;
import kotlin.reflect.full.IllegalCallableAccessException;
import kotlin.reflect.jvm.KTypesJvm;
import kotlin.reflect.jvm.ReflectJvmMapping;
import kotlin.reflect.jvm.internal.calls.Caller;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaCallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

@Metadata(d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u001B\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b \u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003B\u0005\u00A2\u0006\u0002\u0010\u0004J%\u00109\u001A\u00028\u00002\u0016\u0010:\u001A\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\b0\u0007\"\u0004\u0018\u00010\bH\u0016\u00A2\u0006\u0002\u0010;J#\u0010<\u001A\u00028\u00002\u0014\u0010:\u001A\u0010\u0012\u0004\u0012\u00020\u000F\u0012\u0006\u0012\u0004\u0018\u00010\b0=H\u0002\u00A2\u0006\u0002\u0010>J#\u0010?\u001A\u00028\u00002\u0014\u0010:\u001A\u0010\u0012\u0004\u0012\u00020\u000F\u0012\u0006\u0012\u0004\u0018\u00010\b0=H\u0016\u00A2\u0006\u0002\u0010>J3\u0010@\u001A\u00028\u00002\u0014\u0010:\u001A\u0010\u0012\u0004\u0012\u00020\u000F\u0012\u0006\u0012\u0004\u0018\u00010\b0=2\f\u0010A\u001A\b\u0012\u0002\b\u0003\u0018\u00010BH\u0000\u00A2\u0006\u0004\bC\u0010DJ\u0010\u0010E\u001A\u00020\b2\u0006\u0010F\u001A\u00020/H\u0002J\n\u0010G\u001A\u0004\u0018\u00010HH\u0002J\u0015\u0010I\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007H\u0002\u00A2\u0006\u0002\u0010JR,\u0010\u0005\u001A \u0012\u001C\u0012\u001A\u0012\u0006\u0012\u0004\u0018\u00010\b \t*\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u00070\u00070\u0006X\u0082\u0004\u00A2\u0006\u0002\n\u0000R(\u0010\n\u001A\u001C\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\f \t*\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000B0\u000B0\u0006X\u0082\u0004\u00A2\u0006\u0002\n\u0000R(\u0010\r\u001A\u001C\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u000F \t*\n\u0012\u0004\u0012\u00020\u000F\u0018\u00010\u000E0\u000E0\u0006X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u001C\u0010\u0010\u001A\u0010\u0012\f\u0012\n \t*\u0004\u0018\u00010\u00110\u00110\u0006X\u0082\u0004\u00A2\u0006\u0002\n\u0000R(\u0010\u0012\u001A\u001C\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0013 \t*\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u000B0\u000B0\u0006X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u001A\u0010\u0014\u001A\b\u0012\u0004\u0012\u00020\f0\u000B8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0017\u001A\u0006\u0012\u0002\b\u00030\u0018X\u00A6\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0019\u0010\u001AR\u0012\u0010\u001B\u001A\u00020\u001CX\u00A6\u0004\u00A2\u0006\u0006\u001A\u0004\b\u001D\u0010\u001ER\u0018\u0010\u001F\u001A\b\u0012\u0002\b\u0003\u0018\u00010\u0018X\u00A6\u0004\u00A2\u0006\u0006\u001A\u0004\b \u0010\u001AR\u0012\u0010!\u001A\u00020\"X\u00A6\u0004\u00A2\u0006\u0006\u001A\u0004\b#\u0010$R\u0014\u0010%\u001A\u00020&8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b%\u0010\'R\u0014\u0010(\u001A\u00020&8DX\u0084\u0004\u00A2\u0006\u0006\u001A\u0004\b(\u0010\'R\u0012\u0010)\u001A\u00020&X\u00A6\u0004\u00A2\u0006\u0006\u001A\u0004\b)\u0010\'R\u0014\u0010*\u001A\u00020&8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b*\u0010\'R\u0014\u0010+\u001A\u00020&8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b+\u0010\'R\u001A\u0010,\u001A\b\u0012\u0004\u0012\u00020\u000F0\u000B8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b-\u0010\u0016R\u0014\u0010.\u001A\u00020/8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b0\u00101R\u001A\u00102\u001A\b\u0012\u0004\u0012\u0002030\u000B8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b4\u0010\u0016R\u0016\u00105\u001A\u0004\u0018\u0001068VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b7\u00108\u00A8\u0006K"}, d2 = {"Lkotlin/reflect/jvm/internal/KCallableImpl;", "R", "Lkotlin/reflect/KCallable;", "Lkotlin/reflect/jvm/internal/KTypeParameterOwnerImpl;", "()V", "_absentArguments", "Lkotlin/reflect/jvm/internal/ReflectProperties$LazySoftVal;", "", "", "kotlin.jvm.PlatformType", "_annotations", "", "", "_parameters", "Ljava/util/ArrayList;", "Lkotlin/reflect/KParameter;", "_returnType", "Lkotlin/reflect/jvm/internal/KTypeImpl;", "_typeParameters", "Lkotlin/reflect/jvm/internal/KTypeParameterImpl;", "annotations", "getAnnotations", "()Ljava/util/List;", "caller", "Lkotlin/reflect/jvm/internal/calls/Caller;", "getCaller", "()Lkotlin/reflect/jvm/internal/calls/Caller;", "container", "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "getContainer", "()Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "defaultCaller", "getDefaultCaller", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/CallableMemberDescriptor;", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/CallableMemberDescriptor;", "isAbstract", "", "()Z", "isAnnotationConstructor", "isBound", "isFinal", "isOpen", "parameters", "getParameters", "returnType", "Lkotlin/reflect/KType;", "getReturnType", "()Lkotlin/reflect/KType;", "typeParameters", "Lkotlin/reflect/KTypeParameter;", "getTypeParameters", "visibility", "Lkotlin/reflect/KVisibility;", "getVisibility", "()Lkotlin/reflect/KVisibility;", "call", "args", "([Ljava/lang/Object;)Ljava/lang/Object;", "callAnnotationConstructor", "", "(Ljava/util/Map;)Ljava/lang/Object;", "callBy", "callDefaultMethod", "continuationArgument", "Lkotlin/coroutines/Continuation;", "callDefaultMethod$kotlin_reflection", "(Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "defaultEmptyArray", "type", "extractContinuationArgument", "Ljava/lang/reflect/Type;", "getAbsentArguments", "()[Ljava/lang/Object;", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class KCallableImpl implements KCallable, KTypeParameterOwnerImpl {
    private final LazySoftVal _absentArguments;
    private final LazySoftVal _annotations;
    private final LazySoftVal _parameters;
    private final LazySoftVal _returnType;
    private final LazySoftVal _typeParameters;

    public KCallableImpl() {
        LazySoftVal reflectProperties$LazySoftVal0 = ReflectProperties.lazySoft(new Function0() {
            {
                KCallableImpl.this = kCallableImpl0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final List invoke() {
                return UtilKt.computeAnnotations(KCallableImpl.this.getDescriptor());
            }
        });
        Intrinsics.checkNotNullExpressionValue(reflectProperties$LazySoftVal0, "lazySoft { descriptor.computeAnnotations() }");
        this._annotations = reflectProperties$LazySoftVal0;
        LazySoftVal reflectProperties$LazySoftVal1 = ReflectProperties.lazySoft(new Function0() {
            {
                KCallableImpl.this = kCallableImpl0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final ArrayList invoke() {
                int v1;
                CallableMemberDescriptor callableMemberDescriptor0 = KCallableImpl.this.getDescriptor();
                ArrayList arrayList0 = new ArrayList();
                int v = 0;
                if(KCallableImpl.this.isBound()) {
                    v1 = 0;
                }
                else {
                    ReceiverParameterDescriptor receiverParameterDescriptor0 = UtilKt.getInstanceReceiverParameter(callableMemberDescriptor0);
                    if(receiverParameterDescriptor0 == null) {
                        v1 = 0;
                    }
                    else {
                        Function0 function00 = new Function0() {
                            final ReceiverParameterDescriptor $instanceReceiver;

                            {
                                this.$instanceReceiver = receiverParameterDescriptor0;
                                super(0);
                            }

                            @Override  // kotlin.jvm.functions.Function0
                            public Object invoke() {
                                return this.invoke();
                            }

                            public final ParameterDescriptor invoke() {
                                return this.$instanceReceiver;
                            }
                        };
                        arrayList0.add(new KParameterImpl(KCallableImpl.this, 0, Kind.INSTANCE, function00));
                        v1 = 1;
                    }
                    ReceiverParameterDescriptor receiverParameterDescriptor1 = callableMemberDescriptor0.getExtensionReceiverParameter();
                    if(receiverParameterDescriptor1 != null) {
                        Function0 function01 = new Function0() {
                            final ReceiverParameterDescriptor $extensionReceiver;

                            {
                                this.$extensionReceiver = receiverParameterDescriptor0;
                                super(0);
                            }

                            @Override  // kotlin.jvm.functions.Function0
                            public Object invoke() {
                                return this.invoke();
                            }

                            public final ParameterDescriptor invoke() {
                                return this.$extensionReceiver;
                            }
                        };
                        arrayList0.add(new KParameterImpl(KCallableImpl.this, v1, Kind.EXTENSION_RECEIVER, function01));
                        ++v1;
                    }
                }
                int v2 = callableMemberDescriptor0.getValueParameters().size();
                while(v < v2) {
                    Function0 function02 = new Function0(v) {
                        final CallableMemberDescriptor $descriptor;
                        final int $i;

                        {
                            this.$descriptor = callableMemberDescriptor0;
                            this.$i = v;
                            super(0);
                        }

                        @Override  // kotlin.jvm.functions.Function0
                        public Object invoke() {
                            return this.invoke();
                        }

                        public final ParameterDescriptor invoke() {
                            Object object0 = this.$descriptor.getValueParameters().get(this.$i);
                            Intrinsics.checkNotNullExpressionValue(object0, "descriptor.valueParameters[i]");
                            return (ParameterDescriptor)object0;
                        }
                    };
                    arrayList0.add(new KParameterImpl(KCallableImpl.this, v1, Kind.VALUE, function02));
                    ++v;
                    ++v1;
                }
                if(KCallableImpl.this.isAnnotationConstructor() && callableMemberDescriptor0 instanceof JavaCallableMemberDescriptor && arrayList0.size() > 1) {
                    CollectionsKt.sortWith(arrayList0, new kotlin.reflect.jvm.internal.KCallableImpl._parameters.1.invoke..inlined.sortBy.1());
                }
                arrayList0.trimToSize();
                return arrayList0;

                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\u0010\u0000\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u000E\u0010\u0003\u001A\n \u0004*\u0004\u0018\u0001H\u0002H\u00022\u000E\u0010\u0005\u001A\n \u0004*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", "T", "a", "kotlin.jvm.PlatformType", "b", "compare", "(Ljava/lang/Object;Ljava/lang/Object;)I", "kotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$2"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
                public final class kotlin.reflect.jvm.internal.KCallableImpl._parameters.1.invoke..inlined.sortBy.1 implements Comparator {
                    @Override
                    public final int compare(Object object0, Object object1) {
                        return ComparisonsKt.compareValues(((KParameter)object0).getName(), ((KParameter)object1).getName());
                    }
                }

            }
        });
        Intrinsics.checkNotNullExpressionValue(reflectProperties$LazySoftVal1, "lazySoft {\n        val d…ze()\n        result\n    }");
        this._parameters = reflectProperties$LazySoftVal1;
        LazySoftVal reflectProperties$LazySoftVal2 = ReflectProperties.lazySoft(new Function0() {
            {
                KCallableImpl.this = kCallableImpl0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final KTypeImpl invoke() {
                KotlinType kotlinType0 = KCallableImpl.this.getDescriptor().getReturnType();
                Intrinsics.checkNotNull(kotlinType0);
                return new KTypeImpl(kotlinType0, new Function0() {
                    {
                        KCallableImpl.this = kCallableImpl0;
                        super(0);
                    }

                    @Override  // kotlin.jvm.functions.Function0
                    public Object invoke() {
                        return this.invoke();
                    }

                    public final Type invoke() {
                        Type type0 = KCallableImpl.access$extractContinuationArgument(KCallableImpl.this);
                        return type0 == null ? KCallableImpl.this.getCaller().getReturnType() : type0;
                    }
                });
            }
        });
        Intrinsics.checkNotNullExpressionValue(reflectProperties$LazySoftVal2, "lazySoft {\n        KType…eturnType\n        }\n    }");
        this._returnType = reflectProperties$LazySoftVal2;
        LazySoftVal reflectProperties$LazySoftVal3 = ReflectProperties.lazySoft(new Function0() {
            {
                KCallableImpl.this = kCallableImpl0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final List invoke() {
                List list0 = KCallableImpl.this.getDescriptor().getTypeParameters();
                Intrinsics.checkNotNullExpressionValue(list0, "descriptor.typeParameters");
                KCallableImpl kCallableImpl0 = KCallableImpl.this;
                ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
                for(Object object0: list0) {
                    Intrinsics.checkNotNullExpressionValue(((TypeParameterDescriptor)object0), "descriptor");
                    arrayList0.add(new KTypeParameterImpl(kCallableImpl0, ((TypeParameterDescriptor)object0)));
                }
                return arrayList0;
            }
        });
        Intrinsics.checkNotNullExpressionValue(reflectProperties$LazySoftVal3, "lazySoft {\n        descr…this, descriptor) }\n    }");
        this._typeParameters = reflectProperties$LazySoftVal3;
        LazySoftVal reflectProperties$LazySoftVal4 = ReflectProperties.lazySoft(new Function0() {
            {
                KCallableImpl.this = kCallableImpl0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final Object[] invoke() {
                int v = KCallableImpl.this.getParameters().size() + KCallableImpl.this.isSuspend();
                int v1 = (KCallableImpl.this.getParameters().size() + 0x1F) / 0x20;
                Object[] arr_object = new Object[v + v1 + 1];
                Iterable iterable0 = KCallableImpl.this.getParameters();
                KCallableImpl kCallableImpl0 = KCallableImpl.this;
                for(Object object0: iterable0) {
                    KParameter kParameter0 = (KParameter)object0;
                    if(kParameter0.isOptional() && !UtilKt.isInlineClassType(kParameter0.getType())) {
                        int v2 = kParameter0.getIndex();
                        arr_object[v2] = UtilKt.defaultPrimitiveValue(ReflectJvmMapping.getJavaType(kParameter0.getType()));
                    }
                    else if(kParameter0.isVararg()) {
                        int v3 = kParameter0.getIndex();
                        arr_object[v3] = KCallableImpl.access$defaultEmptyArray(kCallableImpl0, kParameter0.getType());
                    }
                }
                for(int v4 = 0; v4 < v1; ++v4) {
                    arr_object[v + v4] = 0;
                }
                return arr_object;
            }
        });
        Intrinsics.checkNotNullExpressionValue(reflectProperties$LazySoftVal4, "lazySoft {\n        val p…\n\n        arguments\n    }");
        this._absentArguments = reflectProperties$LazySoftVal4;
    }

    public static final Object access$defaultEmptyArray(KCallableImpl kCallableImpl0, KType kType0) {
        return kCallableImpl0.defaultEmptyArray(kType0);
    }

    public static final Type access$extractContinuationArgument(KCallableImpl kCallableImpl0) {
        return kCallableImpl0.extractContinuationArgument();
    }

    @Override  // kotlin.reflect.KCallable
    public Object call(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "args");
        try {
            return this.getCaller().call(arr_object);
        }
        catch(IllegalAccessException illegalAccessException0) {
            throw new IllegalCallableAccessException(illegalAccessException0);
        }
    }

    private final Object callAnnotationConstructor(Map map0) {
        Object object1;
        Iterable iterable0 = this.getParameters();
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
        for(Object object0: iterable0) {
            KParameter kParameter0 = (KParameter)object0;
            if(map0.containsKey(kParameter0)) {
                object1 = map0.get(kParameter0);
                if(object1 == null) {
                    throw new IllegalArgumentException("Annotation argument value cannot be null (" + kParameter0 + ')');
                }
            }
            else if(kParameter0.isOptional()) {
                object1 = null;
            }
            else {
                if(!kParameter0.isVararg()) {
                    throw new IllegalArgumentException("No argument provided for a required parameter: " + kParameter0);
                }
                object1 = this.defaultEmptyArray(kParameter0.getType());
            }
            arrayList0.add(object1);
        }
        Caller caller0 = this.getDefaultCaller();
        if(caller0 != null) {
            try {
                return caller0.call(arrayList0.toArray(new Object[0]));
            }
            catch(IllegalAccessException illegalAccessException0) {
                throw new IllegalCallableAccessException(illegalAccessException0);
            }
        }
        throw new KotlinReflectionInternalError("This callable does not support a default call: " + this.getDescriptor());
    }

    @Override  // kotlin.reflect.KCallable
    public Object callBy(Map map0) {
        Intrinsics.checkNotNullParameter(map0, "args");
        return this.isAnnotationConstructor() ? this.callAnnotationConstructor(map0) : this.callDefaultMethod$kotlin_reflection(map0, null);
    }

    public final Object callDefaultMethod$kotlin_reflection(Map map0, Continuation continuation0) {
        Intrinsics.checkNotNullParameter(map0, "args");
        List list0 = this.getParameters();
        boolean z = false;
        if(list0.isEmpty()) {
            try {
                return this.getCaller().call((this.isSuspend() ? new Continuation[]{continuation0} : new Continuation[0]));
            }
            catch(IllegalAccessException illegalAccessException0) {
                throw new IllegalCallableAccessException(illegalAccessException0);
            }
        }
        int v = list0.size() + this.isSuspend();
        Object[] arr_object = this.getAbsentArguments();
        if(this.isSuspend()) {
            arr_object[list0.size()] = continuation0;
        }
        int v1 = 0;
        for(Object object0: list0) {
            KParameter kParameter0 = (KParameter)object0;
            if(map0.containsKey(kParameter0)) {
                int v2 = kParameter0.getIndex();
                arr_object[v2] = map0.get(kParameter0);
            }
            else if(kParameter0.isOptional()) {
                int v3 = v1 / 0x20 + v;
                Object object1 = arr_object[v3];
                Intrinsics.checkNotNull(object1, "null cannot be cast to non-null type kotlin.Int");
                arr_object[v3] = (int)(((int)(((Integer)object1))) | 1 << v1 % 0x20);
                z = true;
            }
            else if(!kParameter0.isVararg()) {
                throw new IllegalArgumentException("No argument provided for a required parameter: " + kParameter0);
            }
            if(kParameter0.getKind() == Kind.VALUE) {
                ++v1;
            }
        }
        if(!z) {
            try {
                Caller caller0 = this.getCaller();
                Object[] arr_object1 = Arrays.copyOf(arr_object, v);
                Intrinsics.checkNotNullExpressionValue(arr_object1, "copyOf(this, newSize)");
                return caller0.call(arr_object1);
            }
            catch(IllegalAccessException illegalAccessException1) {
                throw new IllegalCallableAccessException(illegalAccessException1);
            }
        }
        Caller caller1 = this.getDefaultCaller();
        if(caller1 != null) {
            try {
                return caller1.call(arr_object);
            }
            catch(IllegalAccessException illegalAccessException2) {
                throw new IllegalCallableAccessException(illegalAccessException2);
            }
        }
        throw new KotlinReflectionInternalError("This callable does not support a default call: " + this.getDescriptor());
    }

    private final Object defaultEmptyArray(KType kType0) {
        Class class0 = JvmClassMappingKt.getJavaClass(KTypesJvm.getJvmErasure(kType0));
        if(!class0.isArray()) {
            throw new KotlinReflectionInternalError("Cannot instantiate the default empty array of type " + class0.getSimpleName() + ", because it is not an array type");
        }
        Object object0 = Array.newInstance(class0.getComponentType(), 0);
        Intrinsics.checkNotNullExpressionValue(object0, "type.jvmErasure.java.run…\"\n            )\n        }");
        return object0;
    }

    private final Type extractContinuationArgument() {
        if(this.isSuspend()) {
            Object object0 = CollectionsKt.lastOrNull(this.getCaller().getParameterTypes());
            ParameterizedType parameterizedType0 = object0 instanceof ParameterizedType ? ((ParameterizedType)object0) : null;
            if(Intrinsics.areEqual((parameterizedType0 == null ? null : parameterizedType0.getRawType()), Continuation.class)) {
                Type[] arr_type = parameterizedType0.getActualTypeArguments();
                Intrinsics.checkNotNullExpressionValue(arr_type, "continuationType.actualTypeArguments");
                Object object1 = ArraysKt.single(arr_type);
                WildcardType wildcardType0 = object1 instanceof WildcardType ? ((WildcardType)object1) : null;
                if(wildcardType0 != null) {
                    Type[] arr_type1 = wildcardType0.getLowerBounds();
                    return arr_type1 == null ? null : ((Type)ArraysKt.first(arr_type1));
                }
            }
        }
        return null;
    }

    private final Object[] getAbsentArguments() {
        return (Object[])((Object[])this._absentArguments.invoke()).clone();
    }

    @Override  // kotlin.reflect.KAnnotatedElement
    public List getAnnotations() {
        Object object0 = this._annotations.invoke();
        Intrinsics.checkNotNullExpressionValue(object0, "_annotations()");
        return (List)object0;
    }

    public abstract Caller getCaller();

    public abstract KDeclarationContainerImpl getContainer();

    public abstract Caller getDefaultCaller();

    public abstract CallableMemberDescriptor getDescriptor();

    @Override  // kotlin.reflect.KCallable
    public List getParameters() {
        Object object0 = this._parameters.invoke();
        Intrinsics.checkNotNullExpressionValue(object0, "_parameters()");
        return (List)object0;
    }

    @Override  // kotlin.reflect.KCallable
    public KType getReturnType() {
        Object object0 = this._returnType.invoke();
        Intrinsics.checkNotNullExpressionValue(object0, "_returnType()");
        return (KType)object0;
    }

    @Override  // kotlin.reflect.KCallable
    public List getTypeParameters() {
        Object object0 = this._typeParameters.invoke();
        Intrinsics.checkNotNullExpressionValue(object0, "_typeParameters()");
        return (List)object0;
    }

    @Override  // kotlin.reflect.KCallable
    public KVisibility getVisibility() {
        DescriptorVisibility descriptorVisibility0 = this.getDescriptor().getVisibility();
        Intrinsics.checkNotNullExpressionValue(descriptorVisibility0, "descriptor.visibility");
        return UtilKt.toKVisibility(descriptorVisibility0);
    }

    @Override  // kotlin.reflect.KCallable
    public boolean isAbstract() {
        return this.getDescriptor().getModality() == Modality.ABSTRACT;
    }

    // 去混淆评级： 低(20)
    protected final boolean isAnnotationConstructor() {
        return Intrinsics.areEqual(this.getName(), "<init>") && this.getContainer().getJClass().isAnnotation();
    }

    public abstract boolean isBound();

    @Override  // kotlin.reflect.KCallable
    public boolean isFinal() {
        return this.getDescriptor().getModality() == Modality.FINAL;
    }

    @Override  // kotlin.reflect.KCallable
    public boolean isOpen() {
        return this.getDescriptor().getModality() == Modality.OPEN;
    }
}

