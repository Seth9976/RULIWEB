package com.google.android.datatransport;

public interface TransportFactory {
    Transport getTransport(String arg1, Class arg2, Encoding arg3, Transformer arg4);

    @Deprecated
    Transport getTransport(String arg1, Class arg2, Transformer arg3);
}

