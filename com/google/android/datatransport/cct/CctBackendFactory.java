package com.google.android.datatransport.cct;

import com.google.android.datatransport.runtime.backends.BackendFactory;
import com.google.android.datatransport.runtime.backends.CreationContext;
import com.google.android.datatransport.runtime.backends.TransportBackend;

public class CctBackendFactory implements BackendFactory {
    @Override  // com.google.android.datatransport.runtime.backends.BackendFactory
    public TransportBackend create(CreationContext creationContext0) {
        return new CctTransportBackend(creationContext0.getApplicationContext(), creationContext0.getWallClock(), creationContext0.getMonotonicClock());
    }
}

