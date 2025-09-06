package kotlin.jvm.internal;

import kotlin.reflect.KCallable;
import kotlin.reflect.KProperty.Getter;
import kotlin.reflect.KProperty2;

public abstract class PropertyReference2 extends PropertyReference implements KProperty2 {
    public PropertyReference2() {
    }

    public PropertyReference2(Class class0, String s, String s1, int v) {
        super(PropertyReference2.NO_RECEIVER, class0, s, s1, v);
    }

    @Override  // kotlin.jvm.internal.CallableReference
    protected KCallable computeReflected() {
        return Reflection.property2(this);
    }

    @Override  // kotlin.reflect.KProperty2
    public Object getDelegate(Object object0, Object object1) {
        return ((KProperty2)this.getReflected()).getDelegate(object0, object1);
    }

    @Override  // kotlin.reflect.KProperty
    public Getter getGetter() {
        return this.getGetter();
    }

    @Override  // kotlin.reflect.KProperty2
    public kotlin.reflect.KProperty2.Getter getGetter() {
        return ((KProperty2)this.getReflected()).getGetter();
    }

    @Override  // kotlin.jvm.functions.Function2
    public Object invoke(Object object0, Object object1) {
        return this.get(object0, object1);
    }
}

