package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import java.lang.annotation.Annotation;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaType;

public final class ReflectJavaMethod extends ReflectJavaMember implements JavaMethod {
    private final Method member;

    public ReflectJavaMethod(Method method0) {
        Intrinsics.checkNotNullParameter(method0, "member");
        super();
        this.member = method0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod
    public JavaAnnotationArgument getAnnotationParameterDefaultValue() {
        Object object0 = this.getMember().getDefaultValue();
        return object0 == null ? null : ReflectJavaAnnotationArgument.Factory.create(object0, null);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod
    public boolean getHasAnnotationParameterDefaultValue() {
        return this.getAnnotationParameterDefaultValue() != null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaMember
    public Member getMember() {
        return this.getMember();
    }

    public Method getMember() {
        return this.member;
    }

    public ReflectJavaType getReturnType() {
        Type type0 = this.getMember().getGenericReturnType();
        Intrinsics.checkNotNullExpressionValue(type0, "member.genericReturnType");
        return ReflectJavaType.Factory.create(type0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod
    public JavaType getReturnType() {
        return this.getReturnType();
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

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod
    public List getValueParameters() {
        Type[] arr_type = this.getMember().getGenericParameterTypes();
        Intrinsics.checkNotNullExpressionValue(arr_type, "member.genericParameterTypes");
        Annotation[][] arr2_annotation = this.getMember().getParameterAnnotations();
        Intrinsics.checkNotNullExpressionValue(arr2_annotation, "member.parameterAnnotations");
        return this.getValueParameters(arr_type, arr2_annotation, this.getMember().isVarArgs());
    }
}

