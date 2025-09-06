package com.google.firebase.platforminfo;

import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.Dependency;
import java.util.Iterator;
import java.util.Set;

public class DefaultUserAgentPublisher implements UserAgentPublisher {
    private final GlobalLibraryVersionRegistrar gamesSDKRegistrar;
    private final String javaSDKVersionUserAgent;

    DefaultUserAgentPublisher(Set set0, GlobalLibraryVersionRegistrar globalLibraryVersionRegistrar0) {
        this.javaSDKVersionUserAgent = DefaultUserAgentPublisher.toUserAgent(set0);
        this.gamesSDKRegistrar = globalLibraryVersionRegistrar0;
    }

    public static Component component() {
        return Component.builder(UserAgentPublisher.class).add(Dependency.setOf(LibraryVersion.class)).factory((ComponentContainer componentContainer0) -> new DefaultUserAgentPublisher(componentContainer0.setOf(LibraryVersion.class), GlobalLibraryVersionRegistrar.getInstance())).build();
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.firebase.platforminfo.UserAgentPublisher
    public String getUserAgent() {
        return this.gamesSDKRegistrar.getRegisteredVersions().isEmpty() ? this.javaSDKVersionUserAgent : this.javaSDKVersionUserAgent + ' ' + DefaultUserAgentPublisher.toUserAgent(this.gamesSDKRegistrar.getRegisteredVersions());
    }

    // 检测为 Lambda 实现
    static UserAgentPublisher lambda$component$0(ComponentContainer componentContainer0) [...]

    private static String toUserAgent(Set set0) {
        StringBuilder stringBuilder0 = new StringBuilder();
        Iterator iterator0 = set0.iterator();
        while(iterator0.hasNext()) {
            Object object0 = iterator0.next();
            stringBuilder0.append(((LibraryVersion)object0).getLibraryName());
            stringBuilder0.append('/');
            stringBuilder0.append(((LibraryVersion)object0).getVersion());
            if(iterator0.hasNext()) {
                stringBuilder0.append(' ');
            }
        }
        return stringBuilder0.toString();
    }
}

