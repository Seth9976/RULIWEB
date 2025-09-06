package kotlin.jvm.internal;

import kotlin.reflect.KCallable;
import kotlin.reflect.KMutableProperty.Setter;
import kotlin.reflect.KMutableProperty0;
import kotlin.reflect.KProperty.Getter;

public abstract class MutablePropertyReference0 extends MutablePropertyReference implements KMutableProperty0 {
    public MutablePropertyReference0() {
    }

    public MutablePropertyReference0(Object object0) {
        super(object0);
    }

    public MutablePropertyReference0(Object object0, Class class0, String s, String s1, int v) {
        super(object0, class0, s, s1, v);
    }

    @Override  // kotlin.jvm.internal.CallableReference
    protected KCallable computeReflected() {
        return Reflection.mutableProperty0(this);
    }

    @Override  // kotlin.reflect.KProperty0
    public Object getDelegate() {
        return ((KMutableProperty0)this.getReflected()).getDelegate();
    }

    @Override  // kotlin.reflect.KProperty
    public Getter getGetter() {
        return this.getGetter();
    }

    @Override  // kotlin.reflect.KProperty0
    public kotlin.reflect.KProperty0.Getter getGetter() {
        return ((KMutableProperty0)this.getReflected()).getGetter();
    }

    @Override  // kotlin.reflect.KMutableProperty
    public Setter getSetter() {
        return this.getSetter();
    }

    @Override  // kotlin.reflect.KMutableProperty0
    public kotlin.reflect.KMutableProperty0.Setter getSetter() {
        return ((KMutableProperty0)this.getReflected()).getSetter();
    }

    @Override  // kotlin.jvm.functions.Function0
    public Object invoke() {
        return this.get();
    }
}

