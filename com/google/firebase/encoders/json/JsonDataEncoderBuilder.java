package com.google.firebase.encoders.json;

import com.google.firebase.encoders.DataEncoder;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.ValueEncoder;
import com.google.firebase.encoders.ValueEncoderContext;
import com.google.firebase.encoders.config.Configurator;
import com.google.firebase.encoders.config.EncoderConfig;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public final class JsonDataEncoderBuilder implements EncoderConfig {
    static final class TimestampEncoder implements ValueEncoder {
        private static final DateFormat rfc339;

        static {
            SimpleDateFormat simpleDateFormat0 = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss.SSS\'Z\'", Locale.US);
            TimestampEncoder.rfc339 = simpleDateFormat0;
            simpleDateFormat0.setTimeZone(TimeZone.getTimeZone("UTC"));
        }

        private TimestampEncoder() {
        }

        TimestampEncoder(com.google.firebase.encoders.json.JsonDataEncoderBuilder.1 jsonDataEncoderBuilder$10) {
        }

        @Override  // com.google.firebase.encoders.Encoder
        public void encode(Object object0, Object object1) throws IOException {
            this.encode(((Date)object0), ((ValueEncoderContext)object1));
        }

        public void encode(Date date0, ValueEncoderContext valueEncoderContext0) throws IOException {
            valueEncoderContext0.add(TimestampEncoder.rfc339.format(date0));
        }
    }

    private static final ValueEncoder BOOLEAN_ENCODER;
    private static final ObjectEncoder DEFAULT_FALLBACK_ENCODER;
    private static final ValueEncoder STRING_ENCODER;
    private static final TimestampEncoder TIMESTAMP_ENCODER;
    private ObjectEncoder fallbackEncoder;
    private boolean ignoreNullValues;
    private final Map objectEncoders;
    private final Map valueEncoders;

    static {
        JsonDataEncoderBuilder.DEFAULT_FALLBACK_ENCODER = (Object object0, ObjectEncoderContext objectEncoderContext0) -> throw new EncodingException("Couldn\'t find encoder for type " + object0.getClass().getCanonicalName());
        JsonDataEncoderBuilder.STRING_ENCODER = (String s, ValueEncoderContext valueEncoderContext0) -> valueEncoderContext0.add(s);
        JsonDataEncoderBuilder.BOOLEAN_ENCODER = (Boolean boolean0, ValueEncoderContext valueEncoderContext0) -> valueEncoderContext0.add(boolean0.booleanValue());
        JsonDataEncoderBuilder.TIMESTAMP_ENCODER = new TimestampEncoder(null);
    }

    public JsonDataEncoderBuilder() {
        this.objectEncoders = new HashMap();
        this.valueEncoders = new HashMap();
        this.fallbackEncoder = JsonDataEncoderBuilder.DEFAULT_FALLBACK_ENCODER;
        this.ignoreNullValues = false;
        this.registerEncoder(String.class, JsonDataEncoderBuilder.STRING_ENCODER);
        this.registerEncoder(Boolean.class, JsonDataEncoderBuilder.BOOLEAN_ENCODER);
        this.registerEncoder(Date.class, JsonDataEncoderBuilder.TIMESTAMP_ENCODER);
    }

    public DataEncoder build() {
        return new DataEncoder() {
            @Override  // com.google.firebase.encoders.DataEncoder
            public String encode(Object object0) {
                StringWriter stringWriter0 = new StringWriter();
                try {
                    this.encode(object0, stringWriter0);
                }
                catch(IOException unused_ex) {
                }
                return stringWriter0.toString();
            }

            @Override  // com.google.firebase.encoders.DataEncoder
            public void encode(Object object0, Writer writer0) throws IOException {
                JsonValueObjectEncoderContext jsonValueObjectEncoderContext0 = new JsonValueObjectEncoderContext(writer0, JsonDataEncoderBuilder.this.objectEncoders, JsonDataEncoderBuilder.this.valueEncoders, JsonDataEncoderBuilder.this.fallbackEncoder, JsonDataEncoderBuilder.this.ignoreNullValues);
                jsonValueObjectEncoderContext0.add(object0, false);
                jsonValueObjectEncoderContext0.close();
            }
        };
    }

    public JsonDataEncoderBuilder configureWith(Configurator configurator0) {
        configurator0.configure(this);
        return this;
    }

    public JsonDataEncoderBuilder ignoreNullValues(boolean z) {
        this.ignoreNullValues = z;
        return this;
    }

    // 检测为 Lambda 实现
    static void lambda$static$0(Object object0, ObjectEncoderContext objectEncoderContext0) throws IOException [...]

    // 检测为 Lambda 实现
    static void lambda$static$1(String s, ValueEncoderContext valueEncoderContext0) throws IOException [...]

    // 检测为 Lambda 实现
    static void lambda$static$2(Boolean boolean0, ValueEncoderContext valueEncoderContext0) throws IOException [...]

    @Override  // com.google.firebase.encoders.config.EncoderConfig
    public EncoderConfig registerEncoder(Class class0, ObjectEncoder objectEncoder0) {
        return this.registerEncoder(class0, objectEncoder0);
    }

    @Override  // com.google.firebase.encoders.config.EncoderConfig
    public EncoderConfig registerEncoder(Class class0, ValueEncoder valueEncoder0) {
        return this.registerEncoder(class0, valueEncoder0);
    }

    public JsonDataEncoderBuilder registerEncoder(Class class0, ObjectEncoder objectEncoder0) {
        this.objectEncoders.put(class0, objectEncoder0);
        this.valueEncoders.remove(class0);
        return this;
    }

    public JsonDataEncoderBuilder registerEncoder(Class class0, ValueEncoder valueEncoder0) {
        this.valueEncoders.put(class0, valueEncoder0);
        this.objectEncoders.remove(class0);
        return this;
    }

    public JsonDataEncoderBuilder registerFallbackEncoder(ObjectEncoder objectEncoder0) {
        this.fallbackEncoder = objectEncoder0;
        return this;
    }
}

