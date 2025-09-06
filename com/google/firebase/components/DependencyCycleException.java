package com.google.firebase.components;

import java.util.Arrays;
import java.util.List;

public class DependencyCycleException extends DependencyException {
    private final List componentsInCycle;

    public DependencyCycleException(List list0) {
        super("Dependency cycle detected: " + Arrays.toString(list0.toArray()));
        this.componentsInCycle = list0;
    }

    public List getComponentsInCycle() {
        return this.componentsInCycle;
    }
}

