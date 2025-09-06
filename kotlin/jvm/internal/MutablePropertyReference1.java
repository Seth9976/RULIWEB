package kotlin.jvm.internal;

import kotlin.reflect.KCallable;
import kotlin.reflect.KMutableProperty.Setter;
import kotlin.reflect.KMutableProperty1;
import kotlin.reflect.KProperty.Getter;

public abstract class MutablePropertyReference1 extends MutablePropertyReference implements KMutableProperty1 {
    public MutablePropertyReference1() {
    }

    public MutablePropertyReference1(Object object0) {
        super(object0);
    }

    public MutablePropertyReference1(Object object0, Class class0, String s, String s1, int v) {
        super(object0, class0, s, s1, v);
    }

    @Override  // kotlin.jvm.internal.CallableReference
    protected KCallable computeReflected() {
        return Reflection.mutableProperty1(this);
    }

    @Override  // kotlin.reflect.KProperty1
    public Object getDelegate(Object object0) {
        return ((KMutableProperty1)this.getReflected()).getDelegate(object0);
    }

    @Override  // kotlin.reflect.KProperty
    public Getter getGetter() {
        return this.getGetter();
    }

    @Override  // kotlin.reflect.KProperty1
    public kotlin.reflect.KProperty1.Getter getGetter() {
        return ((KMutableProperty1)this.getReflected()).getGetter();
    }

    @Override  // kotlin.reflect.KMutableProperty
    public Setter getSetter() {
        return this.getSetter();
    }

    @Override  // kotlin.reflect.KMutableProperty1
    public kotlin.reflect.KMutableProperty1.Setter getSetter() {
        return ((KMutableProperty1)this.getReflected()).getSetter();
    }

    @Override  // kotlin.jvm.functions.Function1
    public Object invoke(Object object0) {
        return this.get(object0);
    }
}

