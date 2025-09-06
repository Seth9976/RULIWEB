package com.google.firebase.platforminfo;

import android.content.Context;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.Dependency;

public class LibraryVersionComponent {
    public interface VersionExtractor {
        String extract(Object arg1);
    }

    public static Component create(String s, String s1) {
        return Component.intoSet(LibraryVersion.create(s, s1), LibraryVersion.class);
    }

    public static Component fromContext(String s, VersionExtractor libraryVersionComponent$VersionExtractor0) {
        return Component.intoSetBuilder(LibraryVersion.class).add(Dependency.required(Context.class)).factory((ComponentContainer componentContainer0) -> LibraryVersion.create(s, libraryVersionComponent$VersionExtractor0.extract(((Context)componentContainer0.get(Context.class))))).build();
    }

    // 检测为 Lambda 实现
    static LibraryVersion lambda$fromContext$0(String s, VersionExtractor libraryVersionComponent$VersionExtractor0, ComponentContainer componentContainer0) [...]
}

