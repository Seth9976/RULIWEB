package kotlin.reflect.jvm.internal.calls;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.KotlinReflectionInternalError;
import kotlin.reflect.jvm.internal.UtilKt;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;

@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001A\u0018\u0010\u0005\u001A\u0004\u0018\u00010\u0006*\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001A\u00020\u0002H\u0000\u001A6\u0010\b\u001A\b\u0012\u0004\u0012\u0002H\n0\t\"\n\b\u0000\u0010\n*\u0004\u0018\u00010\u000B*\b\u0012\u0004\u0012\u0002H\n0\t2\u0006\u0010\u0007\u001A\u00020\u00022\b\b\u0002\u0010\f\u001A\u00020\rH\u0000\u001A\u0018\u0010\u000E\u001A\u00020\u000F*\u0006\u0012\u0002\b\u00030\u00102\u0006\u0010\u0007\u001A\u00020\u0002H\u0000\u001A\u0018\u0010\u0011\u001A\u00020\u000F*\u0006\u0012\u0002\b\u00030\u00102\u0006\u0010\u0007\u001A\u00020\u0002H\u0000\u001A\f\u0010\u0012\u001A\u00020\r*\u00020\u0002H\u0002\u001A\u0014\u0010\u0013\u001A\b\u0012\u0002\b\u0003\u0018\u00010\u0010*\u0004\u0018\u00010\u0014H\u0000\u001A\u0012\u0010\u0013\u001A\b\u0012\u0002\b\u0003\u0018\u00010\u0010*\u00020\u0001H\u0000\"\u001A\u0010\u0000\u001A\u0004\u0018\u00010\u0001*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001A\u0004\b\u0003\u0010\u0004¨\u0006\u0015"}, d2 = {"expectedReceiverType", "Lkotlin/reflect/jvm/internal/impl/types/KotlinType;", "Lkotlin/reflect/jvm/internal/impl/descriptors/CallableMemberDescriptor;", "getExpectedReceiverType", "(Lorg/jetbrains/kotlin/descriptors/CallableMemberDescriptor;)Lorg/jetbrains/kotlin/types/KotlinType;", "coerceToExpectedReceiverType", "", "descriptor", "createInlineClassAwareCallerIfNeeded", "Lkotlin/reflect/jvm/internal/calls/Caller;", "M", "Ljava/lang/reflect/Member;", "isDefault", "", "getBoxMethod", "Ljava/lang/reflect/Method;", "Ljava/lang/Class;", "getUnboxMethod", "hasInlineClassReceiver", "toInlineClass", "Lkotlin/reflect/jvm/internal/impl/descriptors/DeclarationDescriptor;", "kotlin-reflection"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class InlineClassAwareCallerKt {
    public static final Object coerceToExpectedReceiverType(Object object0, CallableMemberDescriptor callableMemberDescriptor0) {
        Intrinsics.checkNotNullParameter(callableMemberDescriptor0, "descriptor");
        if(!(callableMemberDescriptor0 instanceof PropertyDescriptor) || !InlineClassesUtilsKt.isUnderlyingPropertyOfInlineClass(((VariableDescriptor)callableMemberDescriptor0))) {
            KotlinType kotlinType0 = InlineClassAwareCallerKt.getExpectedReceiverType(callableMemberDescriptor0);
            if(kotlinType0 != null) {
                Class class0 = InlineClassAwareCallerKt.toInlineClass(kotlinType0);
                if(class0 != null) {
                    Method method0 = InlineClassAwareCallerKt.getUnboxMethod(class0, callableMemberDescriptor0);
                    return method0 == null ? object0 : method0.invoke(object0, null);
                }
            }
        }
        return object0;
    }

    public static final Caller createInlineClassAwareCallerIfNeeded(Caller caller0, CallableMemberDescriptor callableMemberDescriptor0, boolean z) {
        Intrinsics.checkNotNullParameter(caller0, "<this>");
        Intrinsics.checkNotNullParameter(callableMemberDescriptor0, "descriptor");
        if(!InlineClassesUtilsKt.isGetterOfUnderlyingPropertyOfInlineClass(callableMemberDescriptor0)) {
            List list0 = callableMemberDescriptor0.getValueParameters();
            Intrinsics.checkNotNullExpressionValue(list0, "descriptor.valueParameters");
            if(!(list0 instanceof Collection) || !list0.isEmpty()) {
                for(Object object0: list0) {
                    KotlinType kotlinType0 = ((ValueParameterDescriptor)object0).getType();
                    Intrinsics.checkNotNullExpressionValue(kotlinType0, "it.type");
                    if(InlineClassesUtilsKt.isInlineClassType(kotlinType0)) {
                        return new InlineClassAwareCaller(callableMemberDescriptor0, caller0, z);
                    }
                    if(false) {
                        break;
                    }
                }
            }
            KotlinType kotlinType1 = callableMemberDescriptor0.getReturnType();
            if((kotlinType1 == null || !InlineClassesUtilsKt.isInlineClassType(kotlinType1)) && (caller0 instanceof BoundCaller || !InlineClassAwareCallerKt.hasInlineClassReceiver(callableMemberDescriptor0))) {
                return caller0;
            }
        }
        return new InlineClassAwareCaller(callableMemberDescriptor0, caller0, z);
    }

    public static Caller createInlineClassAwareCallerIfNeeded$default(Caller caller0, CallableMemberDescriptor callableMemberDescriptor0, boolean z, int v, Object object0) {
        if((v & 2) != 0) {
            z = false;
        }
        return InlineClassAwareCallerKt.createInlineClassAwareCallerIfNeeded(caller0, callableMemberDescriptor0, z);
    }

    public static final Method getBoxMethod(Class class0, CallableMemberDescriptor callableMemberDescriptor0) {
        Intrinsics.checkNotNullParameter(class0, "<this>");
        Intrinsics.checkNotNullParameter(callableMemberDescriptor0, "descriptor");
        try {
            Method method0 = class0.getDeclaredMethod("box-impl", InlineClassAwareCallerKt.getUnboxMethod(class0, callableMemberDescriptor0).getReturnType());
            Intrinsics.checkNotNullExpressionValue(method0, "{\n        getDeclaredMet…riptor).returnType)\n    }");
            return method0;
        }
        catch(NoSuchMethodException unused_ex) {
            throw new KotlinReflectionInternalError("No box method found in inline class: " + class0 + " (calling " + callableMemberDescriptor0 + ')');
        }
    }

    private static final KotlinType getExpectedReceiverType(CallableMemberDescriptor callableMemberDescriptor0) {
        ReceiverParameterDescriptor receiverParameterDescriptor0 = callableMemberDescriptor0.getExtensionReceiverParameter();
        ReceiverParameterDescriptor receiverParameterDescriptor1 = callableMemberDescriptor0.getDispatchReceiverParameter();
        if(receiverParameterDescriptor0 != null) {
            return receiverParameterDescriptor0.getType();
        }
        if(receiverParameterDescriptor1 == null) {
            return null;
        }
        if(callableMemberDescriptor0 instanceof ConstructorDescriptor) {
            return receiverParameterDescriptor1.getType();
        }
        DeclarationDescriptor declarationDescriptor0 = callableMemberDescriptor0.getContainingDeclaration();
        ClassDescriptor classDescriptor0 = declarationDescriptor0 instanceof ClassDescriptor ? ((ClassDescriptor)declarationDescriptor0) : null;
        return classDescriptor0 == null ? null : classDescriptor0.getDefaultType();
    }

    public static final Method getUnboxMethod(Class class0, CallableMemberDescriptor callableMemberDescriptor0) {
        Intrinsics.checkNotNullParameter(class0, "<this>");
        Intrinsics.checkNotNullParameter(callableMemberDescriptor0, "descriptor");
        try {
            Method method0 = class0.getDeclaredMethod("unbox-impl", null);
            Intrinsics.checkNotNullExpressionValue(method0, "{\n        getDeclaredMet…LINE_CLASS_MEMBERS)\n    }");
            return method0;
        }
        catch(NoSuchMethodException unused_ex) {
            throw new KotlinReflectionInternalError("No unbox method found in inline class: " + class0 + " (calling " + callableMemberDescriptor0 + ')');
        }
    }

    private static final boolean hasInlineClassReceiver(CallableMemberDescriptor callableMemberDescriptor0) {
        KotlinType kotlinType0 = InlineClassAwareCallerKt.getExpectedReceiverType(callableMemberDescriptor0);
        return kotlinType0 != null && InlineClassesUtilsKt.isInlineClassType(kotlinType0);
    }

    public static final Class toInlineClass(DeclarationDescriptor declarationDescriptor0) {
        if(declarationDescriptor0 instanceof ClassDescriptor && InlineClassesUtilsKt.isInlineClass(declarationDescriptor0)) {
            Class class0 = UtilKt.toJavaClass(((ClassDescriptor)declarationDescriptor0));
            if(class0 == null) {
                throw new KotlinReflectionInternalError("Class object for the class " + ((ClassDescriptor)declarationDescriptor0).getName() + " cannot be found (classId=" + DescriptorUtilsKt.getClassId(((ClassifierDescriptor)declarationDescriptor0)) + ')');
            }
            return class0;
        }
        return null;
    }

    public static final Class toInlineClass(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        Class class0 = InlineClassAwareCallerKt.toInlineClass(kotlinType0.getConstructor().getDeclarationDescriptor());
        if(class0 == null) {
            return null;
        }
        if(TypeUtils.isNullableType(kotlinType0)) {
            KotlinType kotlinType1 = InlineClassesUtilsKt.unsubstitutedUnderlyingType(kotlinType0);
            if(kotlinType1 == null) {
                return null;
            }
            return TypeUtils.isNullableType(kotlinType1) || KotlinBuiltIns.isPrimitiveType(kotlinType1) ? null : class0;
        }
        return class0;
    }
}

