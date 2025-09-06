package kotlin.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B)\u0012\n\u0010\u0003\u001A\u0006\u0012\u0002\b\u00030\u0004\u0012\b\u0010\u0005\u001A\u0004\u0018\u00010\u0006\u0012\f\u0010\u0007\u001A\b\u0012\u0004\u0012\u00020\u00060\b¢\u0006\u0002\u0010\tJ\u0013\u0010\f\u001A\u00020\r2\b\u0010\u000E\u001A\u0004\u0018\u00010\u000FH\u0096\u0002J\u0013\u0010\u0010\u001A\b\u0012\u0004\u0012\u00020\u00060\nH\u0016¢\u0006\u0002\u0010\u0011J\n\u0010\u0012\u001A\u0004\u0018\u00010\u0006H\u0016J\b\u0010\u0013\u001A\u00020\u0006H\u0016J\b\u0010\u0014\u001A\u00020\u0015H\u0016J\b\u0010\u0016\u001A\u00020\u0017H\u0016J\b\u0010\u0018\u001A\u00020\u0015H\u0016R\u0010\u0010\u0005\u001A\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0003\u001A\u0006\u0012\u0002\b\u00030\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001A\b\u0012\u0004\u0012\u00020\u00060\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000B¨\u0006\u0019"}, d2 = {"Lkotlin/reflect/ParameterizedTypeImpl;", "Ljava/lang/reflect/ParameterizedType;", "Lkotlin/reflect/TypeImpl;", "rawType", "Ljava/lang/Class;", "ownerType", "Ljava/lang/reflect/Type;", "typeArguments", "", "(Ljava/lang/Class;Ljava/lang/reflect/Type;Ljava/util/List;)V", "", "[Ljava/lang/reflect/Type;", "equals", "", "other", "", "getActualTypeArguments", "()[Ljava/lang/reflect/Type;", "getOwnerType", "getRawType", "getTypeName", "", "hashCode", "", "toString", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
final class ParameterizedTypeImpl implements ParameterizedType, TypeImpl {
    private final Type ownerType;
    private final Class rawType;
    private final Type[] typeArguments;

    public ParameterizedTypeImpl(Class class0, Type type0, List list0) {
        Intrinsics.checkNotNullParameter(class0, "rawType");
        Intrinsics.checkNotNullParameter(list0, "typeArguments");
        super();
        this.rawType = class0;
        this.ownerType = type0;
        this.typeArguments = (Type[])list0.toArray(new Type[0]);
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 instanceof ParameterizedType) {
            Type type0 = ((ParameterizedType)object0).getRawType();
            if(Intrinsics.areEqual(this.rawType, type0)) {
                Type type1 = ((ParameterizedType)object0).getOwnerType();
                return Intrinsics.areEqual(this.ownerType, type1) && Arrays.equals(this.getActualTypeArguments(), ((ParameterizedType)object0).getActualTypeArguments());
            }
        }
        return false;
    }

    @Override
    public Type[] getActualTypeArguments() {
        return this.typeArguments;
    }

    @Override
    public Type getOwnerType() {
        return this.ownerType;
    }

    @Override
    public Type getRawType() {
        return this.rawType;
    }

    @Override  // kotlin.reflect.TypeImpl
    public String getTypeName() {
        StringBuilder stringBuilder0 = new StringBuilder();
        Type type0 = this.ownerType;
        if(type0 == null) {
            stringBuilder0.append(TypesJVMKt.access$typeToString(this.rawType));
        }
        else {
            stringBuilder0.append(TypesJVMKt.access$typeToString(type0));
            stringBuilder0.append("$");
            stringBuilder0.append(this.rawType.getSimpleName());
        }
        Type[] arr_type = this.typeArguments;
        if(arr_type.length != 0) {
            ArraysKt.joinTo$default(arr_type, stringBuilder0, null, "<", ">", 0, null, kotlin.reflect.ParameterizedTypeImpl.getTypeName.1.1.INSTANCE, 50, null);
        }
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;

        @Metadata(k = 3, mv = {1, 9, 0}, xi = 0x30)
        final class kotlin.reflect.ParameterizedTypeImpl.getTypeName.1.1 extends FunctionReferenceImpl implements Function1 {
            public static final kotlin.reflect.ParameterizedTypeImpl.getTypeName.1.1 INSTANCE;

            static {
                kotlin.reflect.ParameterizedTypeImpl.getTypeName.1.1.INSTANCE = new kotlin.reflect.ParameterizedTypeImpl.getTypeName.1.1();
            }

            kotlin.reflect.ParameterizedTypeImpl.getTypeName.1.1() {
                super(1, TypesJVMKt.class, "typeToString", "typeToString(Ljava/lang/reflect/Type;)Ljava/lang/String;", 1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((Type)object0));
            }

            public final String invoke(Type type0) {
                Intrinsics.checkNotNullParameter(type0, "p0");
                return TypesJVMKt.access$typeToString(type0);
            }
        }

    }

    @Override
    public int hashCode() {
        int v = this.rawType.hashCode();
        return this.ownerType == null ? v ^ Arrays.hashCode(this.getActualTypeArguments()) : v ^ this.ownerType.hashCode() ^ Arrays.hashCode(this.getActualTypeArguments());
    }

    @Override
    public String toString() {
        return this.getTypeName();
    }
}

