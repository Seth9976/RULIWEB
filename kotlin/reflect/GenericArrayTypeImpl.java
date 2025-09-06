package kotlin.reflect;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001A\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0013\u0010\u0006\u001A\u00020\u00072\b\u0010\b\u001A\u0004\u0018\u00010\tH\u0096\u0002J\b\u0010\n\u001A\u00020\u0004H\u0016J\b\u0010\u000B\u001A\u00020\fH\u0016J\b\u0010\r\u001A\u00020\u000EH\u0016J\b\u0010\u000F\u001A\u00020\fH\u0016R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lkotlin/reflect/GenericArrayTypeImpl;", "Ljava/lang/reflect/GenericArrayType;", "Lkotlin/reflect/TypeImpl;", "elementType", "Ljava/lang/reflect/Type;", "(Ljava/lang/reflect/Type;)V", "equals", "", "other", "", "getGenericComponentType", "getTypeName", "", "hashCode", "", "toString", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
final class GenericArrayTypeImpl implements GenericArrayType, TypeImpl {
    private final Type elementType;

    public GenericArrayTypeImpl(Type type0) {
        Intrinsics.checkNotNullParameter(type0, "elementType");
        super();
        this.elementType = type0;
    }

    // 去混淆评级： 低(20)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof GenericArrayType && Intrinsics.areEqual(this.getGenericComponentType(), ((GenericArrayType)object0).getGenericComponentType());
    }

    @Override
    public Type getGenericComponentType() {
        return this.elementType;
    }

    @Override  // kotlin.reflect.TypeImpl
    public String getTypeName() {
        return TypesJVMKt.access$typeToString(this.elementType) + "[]";
    }

    @Override
    public int hashCode() {
        return this.getGenericComponentType().hashCode();
    }

    @Override
    public String toString() {
        return this.getTypeName();
    }
}

