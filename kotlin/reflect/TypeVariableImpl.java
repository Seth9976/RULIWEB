package kotlin.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u001B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0013\u0010\u0007\u001A\u00020\b2\b\u0010\t\u001A\u0004\u0018\u00010\nH\u0096\u0002J%\u0010\u000B\u001A\u0004\u0018\u0001H\f\"\b\b\u0000\u0010\f*\u00020\r2\f\u0010\u000E\u001A\b\u0012\u0004\u0012\u0002H\f0\u000F¢\u0006\u0002\u0010\u0010J\u0011\u0010\u0011\u001A\b\u0012\u0004\u0012\u00020\r0\u0012¢\u0006\u0002\u0010\u0013J\u0013\u0010\u0014\u001A\b\u0012\u0004\u0012\u00020\u00150\u0012H\u0016¢\u0006\u0002\u0010\u0016J\u0011\u0010\u0017\u001A\b\u0012\u0004\u0012\u00020\r0\u0012¢\u0006\u0002\u0010\u0013J\b\u0010\u0018\u001A\u00020\u0002H\u0016J\b\u0010\u0019\u001A\u00020\u001AH\u0016J\b\u0010\u001B\u001A\u00020\u001AH\u0016J\b\u0010\u001C\u001A\u00020\u001DH\u0016J\b\u0010\u001E\u001A\u00020\u001AH\u0016R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001F"}, d2 = {"Lkotlin/reflect/TypeVariableImpl;", "Ljava/lang/reflect/TypeVariable;", "Ljava/lang/reflect/GenericDeclaration;", "Lkotlin/reflect/TypeImpl;", "typeParameter", "Lkotlin/reflect/KTypeParameter;", "(Lkotlin/reflect/KTypeParameter;)V", "equals", "", "other", "", "getAnnotation", "T", "", "annotationClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Ljava/lang/annotation/Annotation;", "getAnnotations", "", "()[Ljava/lang/annotation/Annotation;", "getBounds", "Ljava/lang/reflect/Type;", "()[Ljava/lang/reflect/Type;", "getDeclaredAnnotations", "getGenericDeclaration", "getName", "", "getTypeName", "hashCode", "", "toString", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
final class TypeVariableImpl implements TypeVariable, TypeImpl {
    private final KTypeParameter typeParameter;

    public TypeVariableImpl(KTypeParameter kTypeParameter0) {
        Intrinsics.checkNotNullParameter(kTypeParameter0, "typeParameter");
        super();
        this.typeParameter = kTypeParameter0;
    }

    // 去混淆评级： 低(30)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof TypeVariable && Intrinsics.areEqual(this.getName(), ((TypeVariable)object0).getName()) && Intrinsics.areEqual(this.getGenericDeclaration(), ((TypeVariable)object0).getGenericDeclaration());
    }

    public final Annotation getAnnotation(Class class0) {
        Intrinsics.checkNotNullParameter(class0, "annotationClass");
        return null;
    }

    public final Annotation[] getAnnotations() {
        return new Annotation[0];
    }

    @Override
    public Type[] getBounds() {
        Iterable iterable0 = this.typeParameter.getUpperBounds();
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
        for(Object object0: iterable0) {
            arrayList0.add(TypesJVMKt.computeJavaType(((KType)object0), true));
        }
        return (Type[])arrayList0.toArray(new Type[0]);
    }

    public final Annotation[] getDeclaredAnnotations() {
        return new Annotation[0];
    }

    @Override
    public GenericDeclaration getGenericDeclaration() {
        throw new NotImplementedError("An operation is not implemented: " + ("getGenericDeclaration() is not yet supported for type variables created from KType: " + this.typeParameter));
    }

    @Override
    public String getName() {
        return this.typeParameter.getName();
    }

    @Override  // kotlin.reflect.TypeImpl
    public String getTypeName() {
        return this.getName();
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode() ^ this.getGenericDeclaration().hashCode();
    }

    @Override
    public String toString() {
        return this.getTypeName();
    }
}

