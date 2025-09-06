package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Priority;
import com.google.android.datatransport.Transport;

public final class ForcedSender {
    private static TransportContext getTransportContextOrThrow(Transport transport0) {
        if(!(transport0 instanceof TransportImpl)) {
            throw new IllegalArgumentException("Expected instance of TransportImpl.");
        }
        return ((TransportImpl)transport0).getTransportContext();
    }

    public static void sendBlocking(Transport transport0, Priority priority0) {
        TransportContext transportContext0 = ForcedSender.getTransportContextOrThrow(transport0).withPriority(priority0);
        TransportRuntime.getInstance().getUploader().logAndUpdateState(transportContext0, 1);
    }
}

