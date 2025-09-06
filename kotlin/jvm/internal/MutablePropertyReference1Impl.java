package kotlin.jvm.internal;

import kotlin.reflect.KClass;
import kotlin.reflect.KDeclarationContainer;

public class MutablePropertyReference1Impl extends MutablePropertyReference1 {
    public MutablePropertyReference1Impl(Class class0, String s, String s1, int v) {
        super(MutablePropertyReference1Impl.NO_RECEIVER, class0, s, s1, v);
    }

    public MutablePropertyReference1Impl(Object object0, Class class0, String s, String s1, int v) {
        super(object0, class0, s, s1, v);
    }

    public MutablePropertyReference1Impl(KDeclarationContainer kDeclarationContainer0, String s, String s1) {
        super(MutablePropertyReference1Impl.NO_RECEIVER, ((ClassBasedDeclarationContainer)kDeclarationContainer0).getJClass(), s, s1, !(kDeclarationContainer0 instanceof KClass));
    }

    @Override  // kotlin.reflect.KProperty1
    public Object get(Object object0) {
        return this.getGetter().call(new Object[]{object0});
    }

    @Override  // kotlin.reflect.KMutableProperty1
    public void set(Object object0, Object object1) {
        this.getSetter().call(new Object[]{object0, object1});
    }
}

