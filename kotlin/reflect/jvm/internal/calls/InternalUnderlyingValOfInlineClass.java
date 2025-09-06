package kotlin.reflect.jvm.internal.calls;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001:\u0002\u0016\u0017B\u001D\b\u0004\u0012\u0006\u0010\u0003\u001A\u00020\u0002\u0012\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J%\u0010\u0010\u001A\u0004\u0018\u00010\u00112\b\u0010\u0012\u001A\u0004\u0018\u00010\u00112\n\u0010\u0013\u001A\u0006\u0012\u0002\b\u00030\u0014H\u0004¢\u0006\u0002\u0010\u0015R\u0013\u0010\b\u001A\u0004\u0018\u00010\u00028F¢\u0006\u0006\u001A\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\fR\u0011\u0010\r\u001A\u00020\u0006¢\u0006\b\n\u0000\u001A\u0004\b\u000E\u0010\u000FR\u000E\u0010\u0003\u001A\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0001\u0002\u0018\u0019¨\u0006\u001A"}, d2 = {"Lkotlin/reflect/jvm/internal/calls/InternalUnderlyingValOfInlineClass;", "Lkotlin/reflect/jvm/internal/calls/Caller;", "Ljava/lang/reflect/Method;", "unboxMethod", "parameterTypes", "", "Ljava/lang/reflect/Type;", "(Ljava/lang/reflect/Method;Ljava/util/List;)V", "member", "getMember", "()Ljava/lang/reflect/Method;", "getParameterTypes", "()Ljava/util/List;", "returnType", "getReturnType", "()Ljava/lang/reflect/Type;", "callMethod", "", "instance", "args", "", "(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;", "Bound", "Unbound", "Lkotlin/reflect/jvm/internal/calls/InternalUnderlyingValOfInlineClass$Bound;", "Lkotlin/reflect/jvm/internal/calls/InternalUnderlyingValOfInlineClass$Unbound;", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class InternalUnderlyingValOfInlineClass implements Caller {
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u0012\b\u0010\u0005\u001A\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u001B\u0010\b\u001A\u0004\u0018\u00010\u00062\n\u0010\t\u001A\u0006\u0012\u0002\b\u00030\nH\u0016¢\u0006\u0002\u0010\u000BR\u0010\u0010\u0005\u001A\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lkotlin/reflect/jvm/internal/calls/InternalUnderlyingValOfInlineClass$Bound;", "Lkotlin/reflect/jvm/internal/calls/InternalUnderlyingValOfInlineClass;", "Lkotlin/reflect/jvm/internal/calls/BoundCaller;", "unboxMethod", "Ljava/lang/reflect/Method;", "boundReceiver", "", "(Ljava/lang/reflect/Method;Ljava/lang/Object;)V", "call", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Bound extends InternalUnderlyingValOfInlineClass implements BoundCaller {
        private final Object boundReceiver;

        public Bound(Method method0, Object object0) {
            Intrinsics.checkNotNullParameter(method0, "unboxMethod");
            super(method0, CollectionsKt.emptyList(), null);
            this.boundReceiver = object0;
        }

        @Override  // kotlin.reflect.jvm.internal.calls.Caller
        public Object call(Object[] arr_object) {
            Intrinsics.checkNotNullParameter(arr_object, "args");
            this.checkArguments(arr_object);
            return this.callMethod(this.boundReceiver, arr_object);
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001B\u0010\u0005\u001A\u0004\u0018\u00010\u00062\n\u0010\u0007\u001A\u0006\u0012\u0002\b\u00030\bH\u0016¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lkotlin/reflect/jvm/internal/calls/InternalUnderlyingValOfInlineClass$Unbound;", "Lkotlin/reflect/jvm/internal/calls/InternalUnderlyingValOfInlineClass;", "unboxMethod", "Ljava/lang/reflect/Method;", "(Ljava/lang/reflect/Method;)V", "call", "", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Unbound extends InternalUnderlyingValOfInlineClass {
        public Unbound(Method method0) {
            Intrinsics.checkNotNullParameter(method0, "unboxMethod");
            super(method0, CollectionsKt.listOf(method0.getDeclaringClass()), null);
        }

        @Override  // kotlin.reflect.jvm.internal.calls.Caller
        public Object call(Object[] arr_object) {
            Intrinsics.checkNotNullParameter(arr_object, "args");
            this.checkArguments(arr_object);
            Object object0 = arr_object[0];
            return arr_object.length > 1 ? this.callMethod(object0, ArraysKt.copyOfRange(arr_object, 1, arr_object.length)) : this.callMethod(object0, new Object[0]);
        }
    }

    private final List parameterTypes;
    private final Type returnType;
    private final Method unboxMethod;

    private InternalUnderlyingValOfInlineClass(Method method0, List list0) {
        this.unboxMethod = method0;
        this.parameterTypes = list0;
        Class class0 = method0.getReturnType();
        Intrinsics.checkNotNullExpressionValue(class0, "unboxMethod.returnType");
        this.returnType = class0;
    }

    public InternalUnderlyingValOfInlineClass(Method method0, List list0, DefaultConstructorMarker defaultConstructorMarker0) {
        this(method0, list0);
    }

    protected final Object callMethod(Object object0, Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "args");
        Object[] arr_object1 = Arrays.copyOf(arr_object, arr_object.length);
        return this.unboxMethod.invoke(object0, arr_object1);
    }

    public void checkArguments(Object[] arr_object) {
        DefaultImpls.checkArguments(this, arr_object);
    }

    @Override  // kotlin.reflect.jvm.internal.calls.Caller
    public Member getMember() {
        return null;
    }

    public final Method getMember() [...] // Inlined contents

    @Override  // kotlin.reflect.jvm.internal.calls.Caller
    public final List getParameterTypes() {
        return this.parameterTypes;
    }

    @Override  // kotlin.reflect.jvm.internal.calls.Caller
    public final Type getReturnType() {
        return this.returnType;
    }
}

