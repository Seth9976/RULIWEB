package kotlin.reflect.jvm.internal.impl.load.java.structure;

import java.util.List;

public interface JavaMethod extends JavaMember, JavaTypeParameterListOwner {
    JavaAnnotationArgument getAnnotationParameterDefaultValue();

    boolean getHasAnnotationParameterDefaultValue();

    JavaType getReturnType();

    List getValueParameters();
}

