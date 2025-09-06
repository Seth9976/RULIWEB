package kotlin.reflect.jvm.internal.impl.types;

import kotlin.reflect.KClass;

public abstract class TypeAttribute {
    public abstract TypeAttribute add(TypeAttribute arg1);

    public abstract KClass getKey();

    public abstract TypeAttribute intersect(TypeAttribute arg1);
}

