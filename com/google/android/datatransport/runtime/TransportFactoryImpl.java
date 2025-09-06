package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.TransportFactory;
import java.util.Set;

final class TransportFactoryImpl implements TransportFactory {
    private final Set supportedPayloadEncodings;
    private final TransportContext transportContext;
    private final TransportInternal transportInternal;

    TransportFactoryImpl(Set set0, TransportContext transportContext0, TransportInternal transportInternal0) {
        this.supportedPayloadEncodings = set0;
        this.transportContext = transportContext0;
        this.transportInternal = transportInternal0;
    }

    @Override  // com.google.android.datatransport.TransportFactory
    public Transport getTransport(String s, Class class0, Encoding encoding0, Transformer transformer0) {
        if(!this.supportedPayloadEncodings.contains(encoding0)) {
            throw new IllegalArgumentException(String.format("%s is not supported byt this factory. Supported encodings are: %s.", encoding0, this.supportedPayloadEncodings));
        }
        return new TransportImpl(this.transportContext, s, encoding0, transformer0, this.transportInternal);
    }

    @Override  // com.google.android.datatransport.TransportFactory
    public Transport getTransport(String s, Class class0, Transformer transformer0) {
        return this.getTransport(s, class0, Encoding.of("proto"), transformer0);
    }
}

