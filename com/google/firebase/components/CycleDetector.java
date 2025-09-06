package com.google.firebase.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class CycleDetector {
    static class ComponentNode {
        private final Component component;
        private final Set dependencies;
        private final Set dependents;

        ComponentNode(Component component0) {
            this.dependencies = new HashSet();
            this.dependents = new HashSet();
            this.component = component0;
        }

        void addDependency(ComponentNode cycleDetector$ComponentNode0) {
            this.dependencies.add(cycleDetector$ComponentNode0);
        }

        void addDependent(ComponentNode cycleDetector$ComponentNode0) {
            this.dependents.add(cycleDetector$ComponentNode0);
        }

        Component getComponent() {
            return this.component;
        }

        Set getDependencies() {
            return this.dependencies;
        }

        boolean isLeaf() {
            return this.dependencies.isEmpty();
        }

        boolean isRoot() {
            return this.dependents.isEmpty();
        }

        void removeDependent(ComponentNode cycleDetector$ComponentNode0) {
            this.dependents.remove(cycleDetector$ComponentNode0);
        }
    }

    static class Dep {
        private final Qualified anInterface;
        private final boolean set;

        private Dep(Qualified qualified0, boolean z) {
            this.anInterface = qualified0;
            this.set = z;
        }

        Dep(Qualified qualified0, boolean z, com.google.firebase.components.CycleDetector.1 cycleDetector$10) {
            this(qualified0, z);
        }

        // 去混淆评级： 低(20)
        @Override
        public boolean equals(Object object0) {
            return object0 instanceof Dep && ((Dep)object0).anInterface.equals(this.anInterface) && ((Dep)object0).set == this.set;
        }

        @Override
        public int hashCode() {
            return (this.anInterface.hashCode() ^ 1000003) * 1000003 ^ Boolean.valueOf(this.set).hashCode();
        }
    }

    static void detect(List list0) {
        Set set0 = CycleDetector.toGraph(list0);
        Set set1 = CycleDetector.getRoots(set0);
        int v = 0;
        while(!set1.isEmpty()) {
            Object object0 = set1.iterator().next();
            ComponentNode cycleDetector$ComponentNode0 = (ComponentNode)object0;
            set1.remove(cycleDetector$ComponentNode0);
            ++v;
            for(Object object1: cycleDetector$ComponentNode0.getDependencies()) {
                ComponentNode cycleDetector$ComponentNode1 = (ComponentNode)object1;
                cycleDetector$ComponentNode1.removeDependent(cycleDetector$ComponentNode0);
                if(cycleDetector$ComponentNode1.isRoot()) {
                    set1.add(cycleDetector$ComponentNode1);
                }
            }
        }
        if(v == list0.size()) {
            return;
        }
        ArrayList arrayList0 = new ArrayList();
        for(Object object2: set0) {
            ComponentNode cycleDetector$ComponentNode2 = (ComponentNode)object2;
            if(!cycleDetector$ComponentNode2.isRoot() && !cycleDetector$ComponentNode2.isLeaf()) {
                arrayList0.add(cycleDetector$ComponentNode2.getComponent());
            }
        }
        throw new DependencyCycleException(arrayList0);
    }

    private static Set getRoots(Set set0) {
        Set set1 = new HashSet();
        for(Object object0: set0) {
            ComponentNode cycleDetector$ComponentNode0 = (ComponentNode)object0;
            if(cycleDetector$ComponentNode0.isRoot()) {
                set1.add(cycleDetector$ComponentNode0);
            }
        }
        return set1;
    }

    private static Set toGraph(List list0) {
        HashMap hashMap0 = new HashMap(list0.size());
        for(Object object0: list0) {
            Component component0 = (Component)object0;
            ComponentNode cycleDetector$ComponentNode0 = new ComponentNode(component0);
            for(Object object1: component0.getProvidedInterfaces()) {
                Qualified qualified0 = (Qualified)object1;
                Dep cycleDetector$Dep0 = new Dep(qualified0, !component0.isValue(), null);
                if(!hashMap0.containsKey(cycleDetector$Dep0)) {
                    hashMap0.put(cycleDetector$Dep0, new HashSet());
                }
                Set set0 = (Set)hashMap0.get(cycleDetector$Dep0);
                if(!set0.isEmpty() && !cycleDetector$Dep0.set) {
                    throw new IllegalArgumentException(String.format("Multiple components provide %s.", qualified0));
                }
                set0.add(cycleDetector$ComponentNode0);
            }
        }
        for(Object object2: hashMap0.values()) {
            for(Object object3: ((Set)object2)) {
                ComponentNode cycleDetector$ComponentNode1 = (ComponentNode)object3;
                for(Object object4: cycleDetector$ComponentNode1.getComponent().getDependencies()) {
                    Dependency dependency0 = (Dependency)object4;
                    if(dependency0.isDirectInjection()) {
                        Set set1 = (Set)hashMap0.get(new Dep(dependency0.getInterface(), dependency0.isSet(), null));
                        if(set1 != null) {
                            for(Object object5: set1) {
                                cycleDetector$ComponentNode1.addDependency(((ComponentNode)object5));
                                ((ComponentNode)object5).addDependent(cycleDetector$ComponentNode1);
                            }
                        }
                    }
                }
                if(false) {
                    break;
                }
            }
            if(false) {
                break;
            }
        }
        Set set2 = new HashSet();
        for(Object object6: hashMap0.values()) {
            ((HashSet)set2).addAll(((Set)object6));
        }
        return set2;
    }

    class com.google.firebase.components.CycleDetector.1 {
    }

}

