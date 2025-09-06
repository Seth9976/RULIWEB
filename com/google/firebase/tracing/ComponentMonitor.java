package com.google.firebase.tracing;

import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.ComponentRegistrarProcessor;
import java.util.ArrayList;
import java.util.List;

public class ComponentMonitor implements ComponentRegistrarProcessor {
    // 检测为 Lambda 实现
    static Object lambda$processRegistrar$0(String s, Component component0, ComponentContainer componentContainer0) [...]

    @Override  // com.google.firebase.components.ComponentRegistrarProcessor
    public List processRegistrar(ComponentRegistrar componentRegistrar0) {
        List list0 = new ArrayList();
        for(Object object0: componentRegistrar0.getComponents()) {
            Component component0 = (Component)object0;
            String s = component0.getName();
            if(s != null) {
                component0 = component0.withFactory((ComponentContainer componentContainer0) -> try {
                    FirebaseTrace.pushTrace(s);
                    return component0.getFactory().create(componentContainer0);
                }
                finally {
                    FirebaseTrace.popTrace();
                });
            }
            list0.add(component0);
        }
        return list0;
    }
}

