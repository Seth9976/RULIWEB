package com.google.firebase.messaging;

import com.google.android.datatransport.TransportFactory;
import com.google.firebase.FirebaseApp;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.events.Subscriber;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import com.google.firebase.platforminfo.UserAgentPublisher;
import java.util.Arrays;
import java.util.List;

public class FirebaseMessagingRegistrar implements ComponentRegistrar {
    private static final String LIBRARY_NAME = "fire-fcm";

    @Override  // com.google.firebase.components.ComponentRegistrar
    public List getComponents() {
        return Arrays.asList(new Component[]{Component.builder(FirebaseMessaging.class).name("fire-fcm").add(Dependency.required(FirebaseApp.class)).add(Dependency.optional(FirebaseInstanceIdInternal.class)).add(Dependency.optionalProvider(UserAgentPublisher.class)).add(Dependency.optionalProvider(HeartBeatInfo.class)).add(Dependency.optional(TransportFactory.class)).add(Dependency.required(FirebaseInstallationsApi.class)).add(Dependency.required(Subscriber.class)).factory((ComponentContainer componentContainer0) -> new FirebaseMessaging(((FirebaseApp)componentContainer0.get(FirebaseApp.class)), ((FirebaseInstanceIdInternal)componentContainer0.get(FirebaseInstanceIdInternal.class)), componentContainer0.getProvider(UserAgentPublisher.class), componentContainer0.getProvider(HeartBeatInfo.class), ((FirebaseInstallationsApi)componentContainer0.get(FirebaseInstallationsApi.class)), ((TransportFactory)componentContainer0.get(TransportFactory.class)), ((Subscriber)componentContainer0.get(Subscriber.class)))).alwaysEager().build(), LibraryVersionComponent.create("fire-fcm", "23.2.1")});
    }

    // 检测为 Lambda 实现
    static FirebaseMessaging lambda$getComponents$0(ComponentContainer componentContainer0) [...]
}

