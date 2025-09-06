package kotlin.reflect.jvm.internal.calls;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.jvm.internal.KotlinReflectionInternalError;
import kotlin.reflect.jvm.internal.UtilKt;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\b\u0000\u0018\u0000*\f\b\u0000\u0010\u0001 \u0001*\u0004\u0018\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001\u001CB#\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\f\u0010\u0006\u001A\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\u0006\u0010\u0007\u001A\u00020\b¢\u0006\u0002\u0010\tJ\u001B\u0010\u0017\u001A\u0004\u0018\u00010\u00182\n\u0010\u0019\u001A\u0006\u0012\u0002\b\u00030\u001AH\u0016¢\u0006\u0002\u0010\u001BR\u0014\u0010\u0006\u001A\b\u0012\u0004\u0012\u00028\u00000\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\n\u001A\u00020\u000BX\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001A\u00028\u00008VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\r\u0010\u000ER\u001A\u0010\u000F\u001A\b\u0012\u0004\u0012\u00020\u00110\u00108VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001A\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0015\u0010\u0016¨\u0006\u001D"}, d2 = {"Lkotlin/reflect/jvm/internal/calls/InlineClassAwareCaller;", "M", "Ljava/lang/reflect/Member;", "Lkotlin/reflect/jvm/internal/calls/Caller;", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/CallableMemberDescriptor;", "caller", "isDefault", "", "(Lorg/jetbrains/kotlin/descriptors/CallableMemberDescriptor;Lkotlin/reflect/jvm/internal/calls/Caller;Z)V", "data", "Lkotlin/reflect/jvm/internal/calls/InlineClassAwareCaller$BoxUnboxData;", "member", "getMember", "()Ljava/lang/reflect/Member;", "parameterTypes", "", "Ljava/lang/reflect/Type;", "getParameterTypes", "()Ljava/util/List;", "returnType", "getReturnType", "()Ljava/lang/reflect/Type;", "call", "", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "BoxUnboxData", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class InlineClassAwareCaller implements Caller {
    @Metadata(d1 = {"\u0000\u001C\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\r\b\u0002\u0018\u00002\u00020\u0001B\'\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u000E\u0010\u0004\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005\u0012\b\u0010\u0007\u001A\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\bJ\t\u0010\u0010\u001A\u00020\u0003H\u0086\u0002J\u0016\u0010\u0011\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005H\u0086\u0002¢\u0006\u0002\u0010\u000EJ\u000B\u0010\u0012\u001A\u0004\u0018\u00010\u0006H\u0086\u0002R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\t\u0010\nR\u0013\u0010\u0007\u001A\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\fR\u001B\u0010\u0004\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005¢\u0006\n\n\u0002\u0010\u000F\u001A\u0004\b\r\u0010\u000E¨\u0006\u0013"}, d2 = {"Lkotlin/reflect/jvm/internal/calls/InlineClassAwareCaller$BoxUnboxData;", "", "argumentRange", "Lkotlin/ranges/IntRange;", "unbox", "", "Ljava/lang/reflect/Method;", "box", "(Lkotlin/ranges/IntRange;[Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;)V", "getArgumentRange", "()Lkotlin/ranges/IntRange;", "getBox", "()Ljava/lang/reflect/Method;", "getUnbox", "()[Ljava/lang/reflect/Method;", "[Ljava/lang/reflect/Method;", "component1", "component2", "component3", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    static final class BoxUnboxData {
        private final IntRange argumentRange;
        private final Method box;
        private final Method[] unbox;

        public BoxUnboxData(IntRange intRange0, Method[] arr_method, Method method0) {
            Intrinsics.checkNotNullParameter(intRange0, "argumentRange");
            Intrinsics.checkNotNullParameter(arr_method, "unbox");
            super();
            this.argumentRange = intRange0;
            this.unbox = arr_method;
            this.box = method0;
        }

        public final IntRange component1() {
            return this.argumentRange;
        }

        public final Method[] component2() {
            return this.unbox;
        }

        public final Method component3() {
            return this.box;
        }
    }

    private final Caller caller;
    private final BoxUnboxData data;
    private final boolean isDefault;

    public InlineClassAwareCaller(CallableMemberDescriptor callableMemberDescriptor0, Caller caller0, boolean z) {
        Intrinsics.checkNotNullParameter(callableMemberDescriptor0, "descriptor");
        Method method1;
        BoxUnboxData inlineClassAwareCaller$BoxUnboxData0;
        Intrinsics.checkNotNullParameter(caller0, "caller");
        super();
        this.caller = caller0;
        this.isDefault = z;
        KotlinType kotlinType0 = callableMemberDescriptor0.getReturnType();
        Intrinsics.checkNotNull(kotlinType0);
        Class class0 = InlineClassAwareCallerKt.toInlineClass(kotlinType0);
        Method method0 = class0 == null ? null : InlineClassAwareCallerKt.getBoxMethod(class0, callableMemberDescriptor0);
        if(InlineClassesUtilsKt.isGetterOfUnderlyingPropertyOfInlineClass(callableMemberDescriptor0)) {
            inlineClassAwareCaller$BoxUnboxData0 = new BoxUnboxData(IntRange.Companion.getEMPTY(), new Method[0], method0);
        }
        else {
            Caller caller1 = this.caller;
            int v1 = -1;
            int v2 = 1;
            if(!(caller1 instanceof BoundStatic)) {
                if(!(callableMemberDescriptor0 instanceof ConstructorDescriptor)) {
                    if(callableMemberDescriptor0.getDispatchReceiverParameter() == null || this.caller instanceof BoundCaller) {
                        v1 = 0;
                    }
                    else {
                        DeclarationDescriptor declarationDescriptor0 = callableMemberDescriptor0.getContainingDeclaration();
                        Intrinsics.checkNotNullExpressionValue(declarationDescriptor0, "descriptor.containingDeclaration");
                        v1 = InlineClassesUtilsKt.isInlineClass(declarationDescriptor0) ? 0 : 1;
                    }
                }
                else if(!(caller1 instanceof BoundCaller)) {
                    v1 = 0;
                }
            }
            ArrayList arrayList0 = new ArrayList();
            ReceiverParameterDescriptor receiverParameterDescriptor0 = callableMemberDescriptor0.getExtensionReceiverParameter();
            KotlinType kotlinType1 = receiverParameterDescriptor0 == null ? null : receiverParameterDescriptor0.getType();
            if(kotlinType1 != null) {
                arrayList0.add(kotlinType1);
            }
            else if(callableMemberDescriptor0 instanceof ConstructorDescriptor) {
                ClassDescriptor classDescriptor0 = ((ConstructorDescriptor)callableMemberDescriptor0).getConstructedClass();
                Intrinsics.checkNotNullExpressionValue(classDescriptor0, "descriptor.constructedClass");
                if(classDescriptor0.isInner()) {
                    DeclarationDescriptor declarationDescriptor1 = classDescriptor0.getContainingDeclaration();
                    Intrinsics.checkNotNull(declarationDescriptor1, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                    arrayList0.add(((ClassDescriptor)declarationDescriptor1).getDefaultType());
                }
            }
            else {
                DeclarationDescriptor declarationDescriptor2 = callableMemberDescriptor0.getContainingDeclaration();
                Intrinsics.checkNotNullExpressionValue(declarationDescriptor2, "descriptor.containingDeclaration");
                if(declarationDescriptor2 instanceof ClassDescriptor && InlineClassesUtilsKt.isInlineClass(declarationDescriptor2)) {
                    arrayList0.add(((ClassDescriptor)declarationDescriptor2).getDefaultType());
                }
            }
            List list0 = callableMemberDescriptor0.getValueParameters();
            Intrinsics.checkNotNullExpressionValue(list0, "descriptor.valueParameters");
            for(Object object0: list0) {
                arrayList0.add(((ValueParameterDescriptor)object0).getType());
            }
            int v3 = this.isDefault ? (arrayList0.size() + 0x1F) / 0x20 + 1 : 0;
            if(!(callableMemberDescriptor0 instanceof FunctionDescriptor) || !((FunctionDescriptor)callableMemberDescriptor0).isSuspend()) {
                v2 = 0;
            }
            int v4 = arrayList0.size() + v1 + (v3 + v2);
            if(CallerKt.getArity(this) != v4) {
                throw new KotlinReflectionInternalError("Inconsistent number of parameters in the descriptor and Java reflection object: " + CallerKt.getArity(this) + " != " + v4 + "\nCalling: " + callableMemberDescriptor0 + "\nParameter types: " + this.getParameterTypes() + ")\nDefault: " + this.isDefault);
            }
            IntRange intRange0 = RangesKt.until(Math.max(v1, 0), arrayList0.size() + v1);
            Method[] arr_method = new Method[v4];
            for(int v = 0; v < v4; ++v) {
                int v5 = intRange0.getFirst();
                if(v > intRange0.getLast() || v5 > v) {
                    method1 = null;
                }
                else {
                    Class class1 = InlineClassAwareCallerKt.toInlineClass(((KotlinType)arrayList0.get(v - v1)));
                    if(class1 != null) {
                        method1 = InlineClassAwareCallerKt.getUnboxMethod(class1, callableMemberDescriptor0);
                    }
                }
                arr_method[v] = method1;
            }
            inlineClassAwareCaller$BoxUnboxData0 = new BoxUnboxData(intRange0, arr_method, method0);
        }
        this.data = inlineClassAwareCaller$BoxUnboxData0;
    }

    @Override  // kotlin.reflect.jvm.internal.calls.Caller
    public Object call(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "args");
        IntRange intRange0 = this.data.component1();
        Method[] arr_method = this.data.component2();
        Method method0 = this.data.component3();
        Object[] arr_object1 = Arrays.copyOf(arr_object, arr_object.length);
        Intrinsics.checkNotNullExpressionValue(arr_object1, "copyOf(this, size)");
        int v = intRange0.getFirst();
        int v1 = intRange0.getLast();
        if(v <= v1) {
            while(true) {
                Method method1 = arr_method[v];
                Object object0 = arr_object[v];
                if(method1 != null) {
                    if(object0 == null) {
                        Class class0 = method1.getReturnType();
                        Intrinsics.checkNotNullExpressionValue(class0, "method.returnType");
                        object0 = UtilKt.defaultPrimitiveValue(class0);
                    }
                    else {
                        object0 = method1.invoke(object0, null);
                    }
                }
                arr_object1[v] = object0;
                if(v == v1) {
                    break;
                }
                ++v;
            }
        }
        Object object1 = this.caller.call(arr_object1);
        if(method0 != null) {
            Object object2 = method0.invoke(null, object1);
            return object2 == null ? object1 : object2;
        }
        return object1;
    }

    @Override  // kotlin.reflect.jvm.internal.calls.Caller
    public Member getMember() {
        return this.caller.getMember();
    }

    @Override  // kotlin.reflect.jvm.internal.calls.Caller
    public List getParameterTypes() {
        return this.caller.getParameterTypes();
    }

    @Override  // kotlin.reflect.jvm.internal.calls.Caller
    public Type getReturnType() {
        return this.caller.getReturnType();
    }
}

