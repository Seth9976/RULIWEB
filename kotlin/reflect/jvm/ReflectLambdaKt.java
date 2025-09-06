package kotlin.reflect.jvm;

import kotlin.Function;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KFunction;
import kotlin.reflect.jvm.internal.EmptyContainerForLocal;
import kotlin.reflect.jvm.internal.KFunctionImpl;
import kotlin.reflect.jvm.internal.UtilKt;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmNameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer;

@Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001A \u0010\u0000\u001A\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0007¨\u0006\u0004"}, d2 = {"reflect", "Lkotlin/reflect/KFunction;", "R", "Lkotlin/Function;", "kotlin-reflection"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class ReflectLambdaKt {
    public static final KFunction reflect(Function function0) {
        Intrinsics.checkNotNullParameter(function0, "<this>");
        Metadata metadata0 = (Metadata)function0.getClass().getAnnotation(Metadata.class);
        if(metadata0 == null) {
            return null;
        }
        String[] arr_s = metadata0.d1();
        if(arr_s.length == 0) {
            arr_s = null;
        }
        if(arr_s == null) {
            return null;
        }
        Pair pair0 = JvmProtoBufUtil.readFunctionDataFrom(arr_s, metadata0.d2());
        kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function protoBuf$Function0 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function)pair0.component2();
        JvmMetadataVersion jvmMetadataVersion0 = new JvmMetadataVersion(metadata0.mv(), (metadata0.xi() & 8) != 0);
        Class class0 = function0.getClass();
        TypeTable protoBuf$TypeTable0 = protoBuf$Function0.getTypeTable();
        Intrinsics.checkNotNullExpressionValue(protoBuf$TypeTable0, "proto.typeTable");
        SimpleFunctionDescriptor simpleFunctionDescriptor0 = (SimpleFunctionDescriptor)UtilKt.deserializeToDescriptor(class0, protoBuf$Function0, ((JvmNameResolver)pair0.component1()), new kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable(protoBuf$TypeTable0), jvmMetadataVersion0, kotlin.reflect.jvm.ReflectLambdaKt.reflect.descriptor.1.INSTANCE);
        return new KFunctionImpl(EmptyContainerForLocal.INSTANCE, simpleFunctionDescriptor0);

        @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlin.reflect.jvm.ReflectLambdaKt.reflect.descriptor.1 extends FunctionReference implements Function2 {
            public static final kotlin.reflect.jvm.ReflectLambdaKt.reflect.descriptor.1 INSTANCE;

            static {
                kotlin.reflect.jvm.ReflectLambdaKt.reflect.descriptor.1.INSTANCE = new kotlin.reflect.jvm.ReflectLambdaKt.reflect.descriptor.1();
            }

            kotlin.reflect.jvm.ReflectLambdaKt.reflect.descriptor.1() {
                super(2);
            }

            @Override  // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
            public final String getName() {
                return "loadFunction";
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public final KDeclarationContainer getOwner() {
                return Reflection.getOrCreateKotlinClass(MemberDeserializer.class);
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public final String getSignature() {
                return "loadFunction(Lorg/jetbrains/kotlin/metadata/ProtoBuf$Function;)Lorg/jetbrains/kotlin/descriptors/SimpleFunctionDescriptor;";
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((MemberDeserializer)object0), ((kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function)object1));
            }

            public final SimpleFunctionDescriptor invoke(MemberDeserializer memberDeserializer0, kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function protoBuf$Function0) {
                Intrinsics.checkNotNullParameter(memberDeserializer0, "p0");
                Intrinsics.checkNotNullParameter(protoBuf$Function0, "p1");
                return memberDeserializer0.loadFunction(protoBuf$Function0);
            }
        }

    }
}

