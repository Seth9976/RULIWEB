package kotlin.jvm.internal;

import kotlin.reflect.KCallable;
import kotlin.reflect.KProperty.Getter;
import kotlin.reflect.KProperty1;

public abstract class PropertyReference1 extends PropertyReference implements KProperty1 {
    public PropertyReference1() {
    }

    public PropertyReference1(Object object0) {
        super(object0);
    }

    public PropertyReference1(Object object0, Class class0, String s, String s1, int v) {
        super(object0, class0, s, s1, v);
    }

    @Override  // kotlin.jvm.internal.CallableReference
    protected KCallable computeReflected() {
        return Reflection.property1(this);
    }

    @Override  // kotlin.reflect.KProperty1
    public Object getDelegate(Object object0) {
        return ((KProperty1)this.getReflected()).getDelegate(object0);
    }

    @Override  // kotlin.reflect.KProperty
    public Getter getGetter() {
        return this.getGetter();
    }

    @Override  // kotlin.reflect.KProperty1
    public kotlin.reflect.KProperty1.Getter getGetter() {
        return ((KProperty1)this.getReflected()).getGetter();
    }

    @Override  // kotlin.jvm.functions.Function1
    public Object invoke(Object object0) {
        return this.get(object0);
    }
}

