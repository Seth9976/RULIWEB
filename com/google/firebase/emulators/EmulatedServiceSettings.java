package com.google.firebase.emulators;

public final class EmulatedServiceSettings {
    private final String host;
    private final int port;

    public EmulatedServiceSettings(String s, int v) {
        this.host = s;
        this.port = v;
    }

    public String getHost() {
        return this.host;
    }

    public int getPort() {
        return this.port;
    }
}

