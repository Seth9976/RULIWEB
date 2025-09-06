package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.TransportScheduleCallback;

final class TransportImpl implements Transport {
    private final String name;
    private final Encoding payloadEncoding;
    private final Transformer transformer;
    private final TransportContext transportContext;
    private final TransportInternal transportInternal;

    TransportImpl(TransportContext transportContext0, String s, Encoding encoding0, Transformer transformer0, TransportInternal transportInternal0) {
        this.transportContext = transportContext0;
        this.name = s;
        this.payloadEncoding = encoding0;
        this.transformer = transformer0;
        this.transportInternal = transportInternal0;
    }

    TransportContext getTransportContext() {
        return this.transportContext;
    }

    static void lambda$send$0(Exception exception0) {
    }

    @Override  // com.google.android.datatransport.Transport
    public void schedule(Event event0, TransportScheduleCallback transportScheduleCallback0) {
        SendRequest sendRequest0 = SendRequest.builder().setTransportContext(this.transportContext).setEvent(event0).setTransportName(this.name).setTransformer(this.transformer).setEncoding(this.payloadEncoding).build();
        this.transportInternal.send(sendRequest0, transportScheduleCallback0);
    }

    @Override  // com.google.android.datatransport.Transport
    public void send(Event event0) {
        this.schedule(event0, new TransportImpl..ExternalSyntheticLambda0());
    }
}

