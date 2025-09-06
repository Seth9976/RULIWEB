package com.google.firebase.platforminfo;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class GlobalLibraryVersionRegistrar {
    private static volatile GlobalLibraryVersionRegistrar INSTANCE;
    private final Set infos;

    GlobalLibraryVersionRegistrar() {
        this.infos = new HashSet();
    }

    public static GlobalLibraryVersionRegistrar getInstance() {
        GlobalLibraryVersionRegistrar globalLibraryVersionRegistrar0 = GlobalLibraryVersionRegistrar.INSTANCE;
        if(globalLibraryVersionRegistrar0 == null) {
            synchronized(GlobalLibraryVersionRegistrar.class) {
                GlobalLibraryVersionRegistrar globalLibraryVersionRegistrar1 = GlobalLibraryVersionRegistrar.INSTANCE;
                if(globalLibraryVersionRegistrar1 == null) {
                    globalLibraryVersionRegistrar1 = new GlobalLibraryVersionRegistrar();
                    GlobalLibraryVersionRegistrar.INSTANCE = globalLibraryVersionRegistrar1;
                }
                return globalLibraryVersionRegistrar1;
            }
        }
        return globalLibraryVersionRegistrar0;
    }

    Set getRegisteredVersions() {
        synchronized(this.infos) {
        }
        return Collections.unmodifiableSet(this.infos);
    }

    public void registerVersion(String s, String s1) {
        synchronized(this.infos) {
            LibraryVersion libraryVersion0 = LibraryVersion.create(s, s1);
            this.infos.add(libraryVersion0);
        }
    }
}

