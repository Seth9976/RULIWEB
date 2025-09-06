package com.google.firebase.installations;

import com.google.firebase.FirebaseApp;
import com.google.firebase.annotations.concurrent.Background;
import com.google.firebase.annotations.concurrent.Blocking;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.components.Qualified;
import com.google.firebase.concurrent.FirebaseExecutors;
import com.google.firebase.heartbeatinfo.HeartBeatConsumerComponent;
import com.google.firebase.heartbeatinfo.HeartBeatController;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

public class FirebaseInstallationsRegistrar implements ComponentRegistrar {
    private static final String LIBRARY_NAME = "fire-installations";

    @Override  // com.google.firebase.components.ComponentRegistrar
    public List getComponents() {
        return Arrays.asList(new Component[]{Component.builder(FirebaseInstallationsApi.class).name("fire-installations").add(Dependency.required(FirebaseApp.class)).add(Dependency.optionalProvider(HeartBeatController.class)).add(Dependency.required(Qualified.qualified(Background.class, ExecutorService.class))).add(Dependency.required(Qualified.qualified(Blocking.class, Executor.class))).factory((ComponentContainer componentContainer0) -> new FirebaseInstallations(((FirebaseApp)componentContainer0.get(FirebaseApp.class)), componentContainer0.getProvider(HeartBeatController.class), ((ExecutorService)componentContainer0.get(Qualified.qualified(Background.class, ExecutorService.class))), FirebaseExecutors.newSequentialExecutor(((Executor)componentContainer0.get(Qualified.qualified(Blocking.class, Executor.class)))))).build(), HeartBeatConsumerComponent.create(), LibraryVersionComponent.create("fire-installations", "17.1.4")});
    }

    // 检测为 Lambda 实现
    static FirebaseInstallationsApi lambda$getComponents$0(ComponentContainer componentContainer0) [...]
}

