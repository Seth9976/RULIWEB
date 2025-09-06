package com.google.firebase.datatransport;

import android.content.Context;
import com.google.android.datatransport.TransportFactory;
import com.google.android.datatransport.cct.CCTDestination;
import com.google.android.datatransport.runtime.TransportRuntime;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import java.util.Arrays;
import java.util.List;

public class TransportRegistrar implements ComponentRegistrar {
    private static final String LIBRARY_NAME = "fire-transport";

    @Override  // com.google.firebase.components.ComponentRegistrar
    public List getComponents() {
        return Arrays.asList(new Component[]{Component.builder(TransportFactory.class).name("fire-transport").add(Dependency.required(Context.class)).factory((ComponentContainer componentContainer0) -> {
            TransportRuntime.initialize(((Context)componentContainer0.get(Context.class)));
            return TransportRuntime.getInstance().newFactory(CCTDestination.LEGACY_INSTANCE);
        }).build(), LibraryVersionComponent.create("fire-transport", "18.1.7")});
    }

    // 检测为 Lambda 实现
    static TransportFactory lambda$getComponents$0(ComponentContainer componentContainer0) [...]
}

