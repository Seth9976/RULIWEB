package kotlin.jvm.internal;

import kotlin.reflect.KCallable;
import kotlin.reflect.KFunction;

public class FunctionReference extends CallableReference implements FunctionBase, KFunction {
    private final int arity;
    private final int flags;

    public FunctionReference(int v) {
        this(v, FunctionReference.NO_RECEIVER, null, null, null, 0);
    }

    public FunctionReference(int v, Object object0) {
        this(v, object0, null, null, null, 0);
    }

    public FunctionReference(int v, Object object0, Class class0, String s, String s1, int v1) {
        super(object0, class0, s, s1, (v1 & 1) == 1);
        this.arity = v;
        this.flags = v1 >> 1;
    }

    @Override  // kotlin.jvm.internal.CallableReference
    protected KCallable computeReflected() {
        return Reflection.function(this);
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(object0 instanceof FunctionReference) {
            return this.getName().equals(((FunctionReference)object0).getName()) && this.getSignature().equals(((FunctionReference)object0).getSignature()) && this.flags == ((FunctionReference)object0).flags && this.arity == ((FunctionReference)object0).arity && Intrinsics.areEqual(this.getBoundReceiver(), ((FunctionReference)object0).getBoundReceiver()) && Intrinsics.areEqual(this.getOwner(), ((FunctionReference)object0).getOwner());
        }
        return object0 instanceof KFunction ? object0.equals(this.compute()) : false;
    }

    @Override  // kotlin.jvm.internal.FunctionBase
    public int getArity() {
        return this.arity;
    }

    @Override  // kotlin.jvm.internal.CallableReference
    protected KCallable getReflected() {
        return this.getReflected();
    }

    protected KFunction getReflected() {
        return (KFunction)super.getReflected();
    }

    @Override
    public int hashCode() {
        return this.getOwner() == null ? this.getName().hashCode() * 0x1F + this.getSignature().hashCode() : (this.getOwner().hashCode() * 0x1F + this.getName().hashCode()) * 0x1F + this.getSignature().hashCode();
    }

    @Override  // kotlin.reflect.KFunction
    public boolean isExternal() {
        return this.getReflected().isExternal();
    }

    @Override  // kotlin.reflect.KFunction
    public boolean isInfix() {
        return this.getReflected().isInfix();
    }

    @Override  // kotlin.reflect.KFunction
    public boolean isInline() {
        return this.getReflected().isInline();
    }

    @Override  // kotlin.reflect.KFunction
    public boolean isOperator() {
        return this.getReflected().isOperator();
    }

    @Override  // kotlin.jvm.internal.CallableReference, kotlin.reflect.KFunction
    public boolean isSuspend() {
        return this.getReflected().isSuspend();
    }

    @Override
    public String toString() {
        KCallable kCallable0 = this.compute();
        if(kCallable0 != this) {
            return kCallable0.toString();
        }
        return "<init>".equals(this.getName()) ? "constructor (Kotlin reflection is not available)" : "function " + this.getName() + " (Kotlin reflection is not available)";
    }
}

