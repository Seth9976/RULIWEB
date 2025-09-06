package com.google.firebase.encoders.proto;

import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.ValueEncoder;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Map.Entry;
import java.util.Map;

final class ProtobufDataEncoderContext implements ObjectEncoderContext {
    private static final ObjectEncoder DEFAULT_MAP_ENCODER;
    private static final FieldDescriptor MAP_KEY_DESC;
    private static final FieldDescriptor MAP_VALUE_DESC;
    private static final Charset UTF_8;
    private final ObjectEncoder fallbackEncoder;
    private final Map objectEncoders;
    private OutputStream output;
    private final ProtobufValueEncoderContext valueEncoderContext;
    private final Map valueEncoders;

    static {
        ProtobufDataEncoderContext.UTF_8 = Charset.forName("UTF-8");
        ProtobufDataEncoderContext.MAP_KEY_DESC = FieldDescriptor.builder("key").withProperty(AtProtobuf.builder().tag(1).build()).build();
        ProtobufDataEncoderContext.MAP_VALUE_DESC = FieldDescriptor.builder("value").withProperty(AtProtobuf.builder().tag(2).build()).build();
        ProtobufDataEncoderContext.DEFAULT_MAP_ENCODER = (Map.Entry map$Entry0, ObjectEncoderContext objectEncoderContext0) -> {
            Object object0 = map$Entry0.getKey();
            objectEncoderContext0.add(ProtobufDataEncoderContext.MAP_KEY_DESC, object0);
            Object object1 = map$Entry0.getValue();
            objectEncoderContext0.add(ProtobufDataEncoderContext.MAP_VALUE_DESC, object1);
        };
    }

    ProtobufDataEncoderContext(OutputStream outputStream0, Map map0, Map map1, ObjectEncoder objectEncoder0) {
        this.valueEncoderContext = new ProtobufValueEncoderContext(this);
        this.output = outputStream0;
        this.objectEncoders = map0;
        this.valueEncoders = map1;
        this.fallbackEncoder = objectEncoder0;
    }

    @Override  // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(FieldDescriptor fieldDescriptor0, double f) throws IOException {
        return this.add(fieldDescriptor0, f, true);
    }

    ObjectEncoderContext add(FieldDescriptor fieldDescriptor0, double f, boolean z) throws IOException {
        if(z && f == 0.0) {
            return this;
        }
        this.writeVarInt32(ProtobufDataEncoderContext.getTag(fieldDescriptor0) << 3 | 1);
        this.output.write(ProtobufDataEncoderContext.allocateBuffer(8).putDouble(f).array());
        return this;
    }

    @Override  // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(FieldDescriptor fieldDescriptor0, float f) throws IOException {
        return this.add(fieldDescriptor0, f, true);
    }

    ObjectEncoderContext add(FieldDescriptor fieldDescriptor0, float f, boolean z) throws IOException {
        if(z && f == 0.0f) {
            return this;
        }
        this.writeVarInt32(ProtobufDataEncoderContext.getTag(fieldDescriptor0) << 3 | 5);
        this.output.write(ProtobufDataEncoderContext.allocateBuffer(4).putFloat(f).array());
        return this;
    }

    @Override  // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(FieldDescriptor fieldDescriptor0, int v) throws IOException {
        return this.add(fieldDescriptor0, v);
    }

    @Override  // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(FieldDescriptor fieldDescriptor0, long v) throws IOException {
        return this.add(fieldDescriptor0, v);
    }

    @Override  // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(FieldDescriptor fieldDescriptor0, Object object0) throws IOException {
        return this.add(fieldDescriptor0, object0, true);
    }

    ObjectEncoderContext add(FieldDescriptor fieldDescriptor0, Object object0, boolean z) throws IOException {
        if(object0 != null) {
            if(!(object0 instanceof CharSequence)) {
                if(object0 instanceof Collection) {
                    for(Object object1: ((Collection)object0)) {
                        this.add(fieldDescriptor0, object1, false);
                    }
                    return this;
                }
                if(object0 instanceof Map) {
                    for(Object object2: ((Map)object0).entrySet()) {
                        this.doEncode(ProtobufDataEncoderContext.DEFAULT_MAP_ENCODER, fieldDescriptor0, ((Map.Entry)object2), false);
                    }
                    return this;
                }
                if(object0 instanceof Double) {
                    return this.add(fieldDescriptor0, ((double)(((Double)object0))), z);
                }
                if(object0 instanceof Float) {
                    return this.add(fieldDescriptor0, ((float)(((Float)object0))), z);
                }
                if(object0 instanceof Number) {
                    return this.add(fieldDescriptor0, ((Number)object0).longValue(), z);
                }
                if(object0 instanceof Boolean) {
                    return this.add(fieldDescriptor0, ((Boolean)object0).booleanValue(), z);
                }
                if(!(object0 instanceof byte[])) {
                    Class class0 = object0.getClass();
                    ObjectEncoder objectEncoder0 = (ObjectEncoder)this.objectEncoders.get(class0);
                    if(objectEncoder0 != null) {
                        return this.doEncode(objectEncoder0, fieldDescriptor0, object0, z);
                    }
                    Class class1 = object0.getClass();
                    ValueEncoder valueEncoder0 = (ValueEncoder)this.valueEncoders.get(class1);
                    if(valueEncoder0 != null) {
                        return this.doEncode(valueEncoder0, fieldDescriptor0, object0, z);
                    }
                    if(object0 instanceof ProtoEnum) {
                        return this.add(fieldDescriptor0, ((ProtoEnum)object0).getNumber());
                    }
                    return object0 instanceof Enum ? this.add(fieldDescriptor0, ((Enum)object0).ordinal()) : this.doEncode(this.fallbackEncoder, fieldDescriptor0, object0, z);
                }
                else if(!z || ((byte[])object0).length != 0) {
                    this.writeVarInt32(ProtobufDataEncoderContext.getTag(fieldDescriptor0) << 3 | 2);
                    this.writeVarInt32(((byte[])object0).length);
                    this.output.write(((byte[])object0));
                    return this;
                }
            }
            else if(!z || ((CharSequence)object0).length() != 0) {
                this.writeVarInt32(ProtobufDataEncoderContext.getTag(fieldDescriptor0) << 3 | 2);
                byte[] arr_b = ((CharSequence)object0).toString().getBytes(ProtobufDataEncoderContext.UTF_8);
                this.writeVarInt32(arr_b.length);
                this.output.write(arr_b);
                return this;
            }
        }
        return this;
    }

    @Override  // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(FieldDescriptor fieldDescriptor0, boolean z) throws IOException {
        return this.add(fieldDescriptor0, z);
    }

    @Override  // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(String s, double f) throws IOException {
        return this.add(FieldDescriptor.of(s), f);
    }

    @Override  // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(String s, int v) throws IOException {
        return this.add(FieldDescriptor.of(s), v);
    }

    @Override  // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(String s, long v) throws IOException {
        return this.add(FieldDescriptor.of(s), v);
    }

    @Override  // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(String s, Object object0) throws IOException {
        return this.add(FieldDescriptor.of(s), object0);
    }

    @Override  // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(String s, boolean z) throws IOException {
        return this.add(FieldDescriptor.of(s), z);
    }

    public ProtobufDataEncoderContext add(FieldDescriptor fieldDescriptor0, int v) throws IOException {
        return this.add(fieldDescriptor0, v, true);
    }

    ProtobufDataEncoderContext add(FieldDescriptor fieldDescriptor0, int v, boolean z) throws IOException {
        if(!z || v != 0) {
            Protobuf protobuf0 = ProtobufDataEncoderContext.getProtobuf(fieldDescriptor0);
            int v1 = com.google.firebase.encoders.proto.ProtobufDataEncoderContext.1.$SwitchMap$com$google$firebase$encoders$proto$Protobuf$IntEncoding[protobuf0.intEncoding().ordinal()];
            if(v1 != 1) {
                switch(v1) {
                    case 2: {
                        this.writeVarInt32(protobuf0.tag() << 3);
                        this.writeVarInt32(v << 1 ^ v >> 0x1F);
                        return this;
                    }
                    case 3: {
                        this.writeVarInt32(protobuf0.tag() << 3 | 5);
                        this.output.write(ProtobufDataEncoderContext.allocateBuffer(4).putInt(v).array());
                        return this;
                    }
                    default: {
                        return this;
                    }
                }
            }
            this.writeVarInt32(protobuf0.tag() << 3);
            this.writeVarInt32(v);
            return this;
        }
        return this;
    }

    public ProtobufDataEncoderContext add(FieldDescriptor fieldDescriptor0, long v) throws IOException {
        return this.add(fieldDescriptor0, v, true);
    }

    ProtobufDataEncoderContext add(FieldDescriptor fieldDescriptor0, long v, boolean z) throws IOException {
        if(!z || v != 0L) {
            Protobuf protobuf0 = ProtobufDataEncoderContext.getProtobuf(fieldDescriptor0);
            int v1 = com.google.firebase.encoders.proto.ProtobufDataEncoderContext.1.$SwitchMap$com$google$firebase$encoders$proto$Protobuf$IntEncoding[protobuf0.intEncoding().ordinal()];
            if(v1 != 1) {
                switch(v1) {
                    case 2: {
                        this.writeVarInt32(protobuf0.tag() << 3);
                        this.writeVarInt64(v >> 0x3F ^ v << 1);
                        return this;
                    }
                    case 3: {
                        this.writeVarInt32(protobuf0.tag() << 3 | 1);
                        this.output.write(ProtobufDataEncoderContext.allocateBuffer(8).putLong(v).array());
                        return this;
                    }
                    default: {
                        return this;
                    }
                }
            }
            this.writeVarInt32(protobuf0.tag() << 3);
            this.writeVarInt64(v);
            return this;
        }
        return this;
    }

    public ProtobufDataEncoderContext add(FieldDescriptor fieldDescriptor0, boolean z) throws IOException {
        return this.add(fieldDescriptor0, z, true);
    }

    ProtobufDataEncoderContext add(FieldDescriptor fieldDescriptor0, boolean z, boolean z1) throws IOException {
        return this.add(fieldDescriptor0, ((int)z), z1);
    }

    private static ByteBuffer allocateBuffer(int v) {
        return ByteBuffer.allocate(v).order(ByteOrder.LITTLE_ENDIAN);
    }

    private long determineSize(ObjectEncoder objectEncoder0, Object object0) throws IOException {
        try(LengthCountingOutputStream lengthCountingOutputStream0 = new LengthCountingOutputStream()) {
            OutputStream outputStream0 = this.output;
            this.output = lengthCountingOutputStream0;
            try {
                objectEncoder0.encode(object0, this);
            }
            finally {
                this.output = outputStream0;
            }
            return lengthCountingOutputStream0.getLength();
        }
    }

    private ProtobufDataEncoderContext doEncode(ObjectEncoder objectEncoder0, FieldDescriptor fieldDescriptor0, Object object0, boolean z) throws IOException {
        long v = this.determineSize(objectEncoder0, object0);
        if(z && v == 0L) {
            return this;
        }
        this.writeVarInt32(ProtobufDataEncoderContext.getTag(fieldDescriptor0) << 3 | 2);
        this.writeVarInt64(v);
        objectEncoder0.encode(object0, this);
        return this;
    }

    private ProtobufDataEncoderContext doEncode(ValueEncoder valueEncoder0, FieldDescriptor fieldDescriptor0, Object object0, boolean z) throws IOException {
        this.valueEncoderContext.resetContext(fieldDescriptor0, z);
        valueEncoder0.encode(object0, this.valueEncoderContext);
        return this;
    }

    ProtobufDataEncoderContext encode(Object object0) throws IOException {
        if(object0 == null) {
            return this;
        }
        Class class0 = object0.getClass();
        ObjectEncoder objectEncoder0 = (ObjectEncoder)this.objectEncoders.get(class0);
        if(objectEncoder0 == null) {
            throw new EncodingException("No encoder for " + object0.getClass());
        }
        objectEncoder0.encode(object0, this);
        return this;
    }

    private static Protobuf getProtobuf(FieldDescriptor fieldDescriptor0) {
        Protobuf protobuf0 = (Protobuf)fieldDescriptor0.getProperty(Protobuf.class);
        if(protobuf0 == null) {
            throw new EncodingException("Field has no @Protobuf config");
        }
        return protobuf0;
    }

    private static int getTag(FieldDescriptor fieldDescriptor0) {
        Protobuf protobuf0 = (Protobuf)fieldDescriptor0.getProperty(Protobuf.class);
        if(protobuf0 == null) {
            throw new EncodingException("Field has no @Protobuf config");
        }
        return protobuf0.tag();
    }

    @Override  // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext inline(Object object0) throws IOException {
        return this.encode(object0);
    }

    // 检测为 Lambda 实现
    static void lambda$static$0(Map.Entry map$Entry0, ObjectEncoderContext objectEncoderContext0) throws IOException [...]

    @Override  // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext nested(FieldDescriptor fieldDescriptor0) throws IOException {
        throw new EncodingException("nested() is not implemented for protobuf encoding.");
    }

    @Override  // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext nested(String s) throws IOException {
        return this.nested(FieldDescriptor.of(s));
    }

    private void writeVarInt32(int v) throws IOException {
        while(((long)(v & 0xFFFFFF80)) != 0L) {
            this.output.write(v & 0x7F | 0x80);
            v >>>= 7;
        }
        this.output.write(v & 0x7F);
    }

    private void writeVarInt64(long v) throws IOException {
        while((0xFFFFFFFFFFFFFF80L & v) != 0L) {
            this.output.write(((int)v) & 0x7F | 0x80);
            v >>>= 7;
        }
        this.output.write(((int)v) & 0x7F);
    }
}

