package kotlin.jvm.internal;

import kotlin.reflect.KClass;
import kotlin.reflect.KDeclarationContainer;

public class PropertyReference2Impl extends PropertyReference2 {
    public PropertyReference2Impl(Class class0, String s, String s1, int v) {
        super(class0, s, s1, v);
    }

    public PropertyReference2Impl(KDeclarationContainer kDeclarationContainer0, String s, String s1) {
        super(((ClassBasedDeclarationContainer)kDeclarationContainer0).getJClass(), s, s1, !(kDeclarationContainer0 instanceof KClass));
    }

    @Override  // kotlin.reflect.KProperty2
    public Object get(Object object0, Object object1) {
        return this.getGetter().call(new Object[]{object0, object1});
    }
}

