package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;

public abstract class UnwrappedType extends KotlinType {
    private UnwrappedType() {
        super(null);
    }

    public UnwrappedType(DefaultConstructorMarker defaultConstructorMarker0) {
    }

    public abstract UnwrappedType makeNullableAsSpecified(boolean arg1);

    public abstract UnwrappedType refine(KotlinTypeRefiner arg1);

    public abstract UnwrappedType replaceAttributes(TypeAttributes arg1);

    @Override  // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public final UnwrappedType unwrap() {
        return this;
    }
}

