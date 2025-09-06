package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.List;
import java.util.Set;

public interface ModuleDependencies {
    List getAllDependencies();

    List getDirectExpectedByDependencies();

    Set getModulesWhoseInternalsAreVisible();
}

