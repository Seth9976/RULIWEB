package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.List;
import kotlin.reflect.jvm.internal.impl.name.ClassId;

public interface JavaModuleAnnotationsProvider {
    List getAnnotationsForModuleOwnerOfClass(ClassId arg1);
}

