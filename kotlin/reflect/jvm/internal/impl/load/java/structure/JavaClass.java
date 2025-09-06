package kotlin.reflect.jvm.internal.impl.load.java.structure;

import java.util.Collection;
import kotlin.reflect.jvm.internal.impl.name.FqName;

public interface JavaClass extends JavaClassifier, JavaModifierListOwner, JavaTypeParameterListOwner {
    Collection getConstructors();

    Collection getFields();

    FqName getFqName();

    Collection getInnerClassNames();

    LightClassOriginKind getLightClassOriginKind();

    Collection getMethods();

    JavaClass getOuterClass();

    Collection getPermittedTypes();

    Collection getRecordComponents();

    Collection getSupertypes();

    boolean hasDefaultConstructor();

    boolean isAnnotationType();

    boolean isEnum();

    boolean isInterface();

    boolean isRecord();

    boolean isSealed();
}

