package com.google.firebase.encoders.proto;

import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ValueEncoderContext;
import java.io.IOException;

class ProtobufValueEncoderContext implements ValueEncoderContext {
    private boolean encoded;
    private FieldDescriptor field;
    private final ProtobufDataEncoderContext objEncoderCtx;
    private boolean skipDefault;

    ProtobufValueEncoderContext(ProtobufDataEncoderContext protobufDataEncoderContext0) {
        this.encoded = false;
        this.skipDefault = false;
        this.objEncoderCtx = protobufDataEncoderContext0;
    }

    @Override  // com.google.firebase.encoders.ValueEncoderContext
    public ValueEncoderContext add(double f) throws IOException {
        this.checkNotUsed();
        this.objEncoderCtx.add(this.field, f, this.skipDefault);
        return this;
    }

    @Override  // com.google.firebase.encoders.ValueEncoderContext
    public ValueEncoderContext add(float f) throws IOException {
        this.checkNotUsed();
        this.objEncoderCtx.add(this.field, f, this.skipDefault);
        return this;
    }

    @Override  // com.google.firebase.encoders.ValueEncoderContext
    public ValueEncoderContext add(int v) throws IOException {
        this.checkNotUsed();
        this.objEncoderCtx.add(this.field, v, this.skipDefault);
        return this;
    }

    @Override  // com.google.firebase.encoders.ValueEncoderContext
    public ValueEncoderContext add(long v) throws IOException {
        this.checkNotUsed();
        this.objEncoderCtx.add(this.field, v, this.skipDefault);
        return this;
    }

    @Override  // com.google.firebase.encoders.ValueEncoderContext
    public ValueEncoderContext add(String s) throws IOException {
        this.checkNotUsed();
        this.objEncoderCtx.add(this.field, s, this.skipDefault);
        return this;
    }

    @Override  // com.google.firebase.encoders.ValueEncoderContext
    public ValueEncoderContext add(boolean z) throws IOException {
        this.checkNotUsed();
        this.objEncoderCtx.add(this.field, z, this.skipDefault);
        return this;
    }

    @Override  // com.google.firebase.encoders.ValueEncoderContext
    public ValueEncoderContext add(byte[] arr_b) throws IOException {
        this.checkNotUsed();
        this.objEncoderCtx.add(this.field, arr_b, this.skipDefault);
        return this;
    }

    private void checkNotUsed() {
        if(this.encoded) {
            throw new EncodingException("Cannot encode a second value in the ValueEncoderContext");
        }
        this.encoded = true;
    }

    void resetContext(FieldDescriptor fieldDescriptor0, boolean z) {
        this.encoded = false;
        this.field = fieldDescriptor0;
        this.skipDefault = z;
    }
}

