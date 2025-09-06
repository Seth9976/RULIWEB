package kotlin.jvm.internal;

import kotlin.reflect.KCallable;
import kotlin.reflect.KMutableProperty.Setter;
import kotlin.reflect.KMutableProperty2;
import kotlin.reflect.KProperty.Getter;

public abstract class MutablePropertyReference2 extends MutablePropertyReference implements KMutableProperty2 {
    public MutablePropertyReference2() {
    }

    public MutablePropertyReference2(Class class0, String s, String s1, int v) {
        super(MutablePropertyReference2.NO_RECEIVER, class0, s, s1, v);
    }

    @Override  // kotlin.jvm.internal.CallableReference
    protected KCallable computeReflected() {
        return Reflection.mutableProperty2(this);
    }

    @Override  // kotlin.reflect.KProperty2
    public Object getDelegate(Object object0, Object object1) {
        return ((KMutableProperty2)this.getReflected()).getDelegate(object0, object1);
    }

    @Override  // kotlin.reflect.KProperty
    public Getter getGetter() {
        return this.getGetter();
    }

    @Override  // kotlin.reflect.KProperty2
    public kotlin.reflect.KProperty2.Getter getGetter() {
        return ((KMutableProperty2)this.getReflected()).getGetter();
    }

    @Override  // kotlin.reflect.KMutableProperty
    public Setter getSetter() {
        return this.getSetter();
    }

    @Override  // kotlin.reflect.KMutableProperty2
    public kotlin.reflect.KMutableProperty2.Setter getSetter() {
        return ((KMutableProperty2)this.getReflected()).getSetter();
    }

    @Override  // kotlin.jvm.functions.Function2
    public Object invoke(Object object0, Object object1) {
        return this.get(object0, object1);
    }
}

