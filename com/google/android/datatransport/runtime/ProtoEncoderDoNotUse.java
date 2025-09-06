package com.google.android.datatransport.runtime;

import com.google.android.datatransport.runtime.firebase.transport.ClientMetrics;
import com.google.firebase.encoders.annotations.Encodable;
import com.google.firebase.encoders.proto.ProtobufEncoder;
import java.io.IOException;
import java.io.OutputStream;

@Encodable
public abstract class ProtoEncoderDoNotUse {
    private static final ProtobufEncoder ENCODER;

    static {
        ProtoEncoderDoNotUse.ENCODER = ProtobufEncoder.builder().configureWith(AutoProtoEncoderDoNotUseEncoder.CONFIG).build();
    }

    public static void encode(Object object0, OutputStream outputStream0) throws IOException {
        ProtoEncoderDoNotUse.ENCODER.encode(object0, outputStream0);
    }

    public static byte[] encode(Object object0) {
        return ProtoEncoderDoNotUse.ENCODER.encode(object0);
    }

    public abstract ClientMetrics getClientMetrics();
}

