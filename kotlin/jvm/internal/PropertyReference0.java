package kotlin.jvm.internal;

import kotlin.reflect.KCallable;
import kotlin.reflect.KProperty.Getter;
import kotlin.reflect.KProperty0;

public abstract class PropertyReference0 extends PropertyReference implements KProperty0 {
    public PropertyReference0() {
    }

    public PropertyReference0(Object object0) {
        super(object0);
    }

    public PropertyReference0(Object object0, Class class0, String s, String s1, int v) {
        super(object0, class0, s, s1, v);
    }

    @Override  // kotlin.jvm.internal.CallableReference
    protected KCallable computeReflected() {
        return Reflection.property0(this);
    }

    @Override  // kotlin.reflect.KProperty0
    public Object getDelegate() {
        return ((KProperty0)this.getReflected()).getDelegate();
    }

    @Override  // kotlin.reflect.KProperty
    public Getter getGetter() {
        return this.getGetter();
    }

    @Override  // kotlin.reflect.KProperty0
    public kotlin.reflect.KProperty0.Getter getGetter() {
        return ((KProperty0)this.getReflected()).getGetter();
    }

    @Override  // kotlin.jvm.functions.Function0
    public Object invoke() {
        return this.get();
    }
}

