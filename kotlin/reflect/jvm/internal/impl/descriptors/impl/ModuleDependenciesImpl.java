package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.List;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;

public final class ModuleDependenciesImpl implements ModuleDependencies {
    private final List allDependencies;
    private final Set allExpectedByDependencies;
    private final List directExpectedByDependencies;
    private final Set modulesWhoseInternalsAreVisible;

    public ModuleDependenciesImpl(List list0, Set set0, List list1, Set set1) {
        Intrinsics.checkNotNullParameter(list0, "allDependencies");
        Intrinsics.checkNotNullParameter(set0, "modulesWhoseInternalsAreVisible");
        Intrinsics.checkNotNullParameter(list1, "directExpectedByDependencies");
        Intrinsics.checkNotNullParameter(set1, "allExpectedByDependencies");
        super();
        this.allDependencies = list0;
        this.modulesWhoseInternalsAreVisible = set0;
        this.directExpectedByDependencies = list1;
        this.allExpectedByDependencies = set1;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleDependencies
    public List getAllDependencies() {
        return this.allDependencies;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleDependencies
    public List getDirectExpectedByDependencies() {
        return this.directExpectedByDependencies;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleDependencies
    public Set getModulesWhoseInternalsAreVisible() {
        return this.modulesWhoseInternalsAreVisible;
    }
}

