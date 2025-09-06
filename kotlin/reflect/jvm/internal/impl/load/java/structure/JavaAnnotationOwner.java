package kotlin.reflect.jvm.internal.impl.load.java.structure;

import java.util.Collection;
import kotlin.reflect.jvm.internal.impl.name.FqName;

public interface JavaAnnotationOwner extends JavaElement {
    JavaAnnotation findAnnotation(FqName arg1);

    Collection getAnnotations();

    boolean isDeprecatedInJavaDoc();
}

