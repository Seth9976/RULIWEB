package kotlin.jvm.internal;

import java.io.Serializable;
import kotlin.reflect.KCallable;
import kotlin.reflect.KFunction;

public class FunInterfaceConstructorReference extends FunctionReference implements Serializable {
    private final Class funInterface;

    public FunInterfaceConstructorReference(Class class0) {
        super(1);
        this.funInterface = class0;
    }

    @Override  // kotlin.jvm.internal.FunctionReference
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof FunInterfaceConstructorReference ? this.funInterface.equals(((FunInterfaceConstructorReference)object0).funInterface) : false;
    }

    @Override  // kotlin.jvm.internal.FunctionReference
    protected KCallable getReflected() {
        return this.getReflected();
    }

    @Override  // kotlin.jvm.internal.FunctionReference
    protected KFunction getReflected() {
        throw new UnsupportedOperationException("Functional interface constructor does not support reflection");
    }

    @Override  // kotlin.jvm.internal.FunctionReference
    public int hashCode() {
        return this.funInterface.hashCode();
    }

    @Override  // kotlin.jvm.internal.FunctionReference
    public String toString() {
        return "fun interface " + this.funInterface.getName();
    }
}

