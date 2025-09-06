package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.checker.StrictEqualityTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;

public abstract class KotlinType implements Annotated, KotlinTypeMarker {
    private int cachedHashCode;

    private KotlinType() {
    }

    public KotlinType(DefaultConstructorMarker defaultConstructorMarker0) {
    }

    // 去混淆评级： 低(20)
    private final int computeHashCode() {
        return KotlinTypeKt.isError(this) ? super.hashCode() : (this.getConstructor().hashCode() * 0x1F + this.getArguments().hashCode()) * 0x1F + this.isMarkedNullable();
    }

    @Override
    public final boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof KotlinType)) {
            return false;
        }
        if(this.isMarkedNullable() == ((KotlinType)object0).isMarkedNullable()) {
            UnwrappedType unwrappedType0 = this.unwrap();
            UnwrappedType unwrappedType1 = ((KotlinType)object0).unwrap();
            return StrictEqualityTypeChecker.INSTANCE.strictEqualTypes(unwrappedType0, unwrappedType1);
        }
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated
    public Annotations getAnnotations() {
        return AnnotationsTypeAttributeKt.getAnnotations(this.getAttributes());
    }

    public abstract List getArguments();

    public abstract TypeAttributes getAttributes();

    public abstract TypeConstructor getConstructor();

    public abstract MemberScope getMemberScope();

    @Override
    public final int hashCode() {
        int v = this.cachedHashCode;
        if(v != 0) {
            return v;
        }
        int v1 = this.computeHashCode();
        this.cachedHashCode = v1;
        return v1;
    }

    public abstract boolean isMarkedNullable();

    public abstract KotlinType refine(KotlinTypeRefiner arg1);

    public abstract UnwrappedType unwrap();
}

