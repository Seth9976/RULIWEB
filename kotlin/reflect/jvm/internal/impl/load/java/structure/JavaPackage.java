package kotlin.reflect.jvm.internal.impl.load.java.structure;

import java.util.Collection;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.jvm.internal.impl.name.FqName;

public interface JavaPackage extends JavaAnnotationOwner, JavaElement {
    Collection getClasses(Function1 arg1);

    FqName getFqName();

    Collection getSubPackages();
}

