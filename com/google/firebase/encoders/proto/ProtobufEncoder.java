package com.google.firebase.encoders.proto;

import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.ValueEncoder;
import com.google.firebase.encoders.config.Configurator;
import com.google.firebase.encoders.config.EncoderConfig;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class ProtobufEncoder {
    public static final class Builder implements EncoderConfig {
        private static final ObjectEncoder DEFAULT_FALLBACK_ENCODER;
        private ObjectEncoder fallbackEncoder;
        private final Map objectEncoders;
        private final Map valueEncoders;

        static {
            Builder.DEFAULT_FALLBACK_ENCODER = (Object object0, ObjectEncoderContext objectEncoderContext0) -> throw new EncodingException("Couldn\'t find encoder for type " + object0.getClass().getCanonicalName());
        }

        public Builder() {
            this.objectEncoders = new HashMap();
            this.valueEncoders = new HashMap();
            this.fallbackEncoder = Builder.DEFAULT_FALLBACK_ENCODER;
        }

        public ProtobufEncoder build() {
            return new ProtobufEncoder(new HashMap(this.objectEncoders), new HashMap(this.valueEncoders), this.fallbackEncoder);
        }

        public Builder configureWith(Configurator configurator0) {
            configurator0.configure(this);
            return this;
        }

        // 检测为 Lambda 实现
        static void lambda$static$0(Object object0, ObjectEncoderContext objectEncoderContext0) throws IOException [...]

        @Override  // com.google.firebase.encoders.config.EncoderConfig
        public EncoderConfig registerEncoder(Class class0, ObjectEncoder objectEncoder0) {
            return this.registerEncoder(class0, objectEncoder0);
        }

        @Override  // com.google.firebase.encoders.config.EncoderConfig
        public EncoderConfig registerEncoder(Class class0, ValueEncoder valueEncoder0) {
            return this.registerEncoder(class0, valueEncoder0);
        }

        public Builder registerEncoder(Class class0, ObjectEncoder objectEncoder0) {
            this.objectEncoders.put(class0, objectEncoder0);
            this.valueEncoders.remove(class0);
            return this;
        }

        public Builder registerEncoder(Class class0, ValueEncoder valueEncoder0) {
            this.valueEncoders.put(class0, valueEncoder0);
            this.objectEncoders.remove(class0);
            return this;
        }

        public Builder registerFallbackEncoder(ObjectEncoder objectEncoder0) {
            this.fallbackEncoder = objectEncoder0;
            return this;
        }
    }

    private final ObjectEncoder fallbackEncoder;
    private final Map objectEncoders;
    private final Map valueEncoders;

    ProtobufEncoder(Map map0, Map map1, ObjectEncoder objectEncoder0) {
        this.objectEncoders = map0;
        this.valueEncoders = map1;
        this.fallbackEncoder = objectEncoder0;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void encode(Object object0, OutputStream outputStream0) throws IOException {
        new ProtobufDataEncoderContext(outputStream0, this.objectEncoders, this.valueEncoders, this.fallbackEncoder).encode(object0);
    }

    public byte[] encode(Object object0) {
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
        try {
            this.encode(object0, byteArrayOutputStream0);
        }
        catch(IOException unused_ex) {
        }
        return byteArrayOutputStream0.toByteArray();
    }
}

