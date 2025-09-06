package kotlin.jvm.internal;

import kotlin.reflect.KClass;
import kotlin.reflect.KDeclarationContainer;

public class PropertyReference0Impl extends PropertyReference0 {
    public PropertyReference0Impl(Class class0, String s, String s1, int v) {
        super(PropertyReference0Impl.NO_RECEIVER, class0, s, s1, v);
    }

    public PropertyReference0Impl(Object object0, Class class0, String s, String s1, int v) {
        super(object0, class0, s, s1, v);
    }

    public PropertyReference0Impl(KDeclarationContainer kDeclarationContainer0, String s, String s1) {
        super(PropertyReference0Impl.NO_RECEIVER, ((ClassBasedDeclarationContainer)kDeclarationContainer0).getJClass(), s, s1, !(kDeclarationContainer0 instanceof KClass));
    }

    @Override  // kotlin.reflect.KProperty0
    public Object get() {
        return this.getGetter().call(new Object[0]);
    }
}

