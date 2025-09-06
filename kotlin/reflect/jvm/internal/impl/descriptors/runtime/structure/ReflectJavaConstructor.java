package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaConstructor;

public final class ReflectJavaConstructor extends ReflectJavaMember implements JavaConstructor {
    private final Constructor member;

    public ReflectJavaConstructor(Constructor constructor0) {
        Intrinsics.checkNotNullParameter(constructor0, "member");
        super();
        this.member = constructor0;
    }

    public Constructor getMember() {
        return this.member;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaMember
    public Member getMember() {
        return this.getMember();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameterListOwner
    public List getTypeParameters() {
        TypeVariable[] arr_typeVariable = this.getMember().getTypeParameters();
        Intrinsics.checkNotNullExpressionValue(arr_typeVariable, "member.typeParameters");
        ArrayList arrayList0 = new ArrayList(arr_typeVariable.length);
        for(int v = 0; v < arr_typeVariable.length; ++v) {
            arrayList0.add(new ReflectJavaTypeParameter(arr_typeVariable[v]));
        }
        return arrayList0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaConstructor
    public List getValueParameters() {
        Type[] arr_type = this.getMember().getGenericParameterTypes();
        Intrinsics.checkNotNullExpressionValue(arr_type, "types");
        if(arr_type.length == 0) {
            return CollectionsKt.emptyList();
        }
        Class class0 = this.getMember().getDeclaringClass();
        if(class0.getDeclaringClass() != null && !Modifier.isStatic(class0.getModifiers())) {
            arr_type = (Type[])ArraysKt.copyOfRange(arr_type, 1, arr_type.length);
        }
        Annotation[][] arr2_annotation = this.getMember().getParameterAnnotations();
        if(arr2_annotation.length < arr_type.length) {
            throw new IllegalStateException("Illegal generic signature: " + this.getMember());
        }
        if(arr2_annotation.length > arr_type.length) {
            Intrinsics.checkNotNullExpressionValue(arr2_annotation, "annotations");
            arr2_annotation = (Annotation[][])ArraysKt.copyOfRange(arr2_annotation, arr2_annotation.length - arr_type.length, arr2_annotation.length);
        }
        Intrinsics.checkNotNullExpressionValue(arr_type, "realTypes");
        Intrinsics.checkNotNullExpressionValue(arr2_annotation, "realAnnotations");
        return this.getValueParameters(arr_type, arr2_annotation, this.getMember().isVarArgs());
    }
}

