package kotlin.jvm.internal;

import kotlin.reflect.KClass;
import kotlin.reflect.KDeclarationContainer;

public class MutablePropertyReference2Impl extends MutablePropertyReference2 {
    public MutablePropertyReference2Impl(Class class0, String s, String s1, int v) {
        super(class0, s, s1, v);
    }

    public MutablePropertyReference2Impl(KDeclarationContainer kDeclarationContainer0, String s, String s1) {
        super(((ClassBasedDeclarationContainer)kDeclarationContainer0).getJClass(), s, s1, !(kDeclarationContainer0 instanceof KClass));
    }

    @Override  // kotlin.reflect.KProperty2
    public Object get(Object object0, Object object1) {
        return this.getGetter().call(new Object[]{object0, object1});
    }

    @Override  // kotlin.reflect.KMutableProperty2
    public void set(Object object0, Object object1, Object object2) {
        this.getSetter().call(new Object[]{object0, object1, object2});
    }
}

