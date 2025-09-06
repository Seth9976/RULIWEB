package com.google.crypto.tink.shaded.protobuf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@CheckReturnValue
final class StructuralMessageInfo implements MessageInfo {
    public static final class Builder {
        private int[] checkInitialized;
        private Object defaultInstance;
        private final List fields;
        private boolean messageSetWireFormat;
        private ProtoSyntax syntax;
        private boolean wasBuilt;

        public Builder() {
            this.checkInitialized = null;
            this.fields = new ArrayList();
        }

        public Builder(int v) {
            this.checkInitialized = null;
            this.fields = new ArrayList(v);
        }

        public StructuralMessageInfo build() {
            if(this.wasBuilt) {
                throw new IllegalStateException("Builder can only build once");
            }
            if(this.syntax == null) {
                throw new IllegalStateException("Must specify a proto syntax");
            }
            this.wasBuilt = true;
            Collections.sort(this.fields);
            return new StructuralMessageInfo(this.syntax, this.messageSetWireFormat, this.checkInitialized, ((FieldInfo[])this.fields.toArray(new FieldInfo[0])), this.defaultInstance);
        }

        public void withCheckInitialized(int[] arr_v) {
            this.checkInitialized = arr_v;
        }

        public void withDefaultInstance(Object object0) {
            this.defaultInstance = object0;
        }

        public void withField(FieldInfo fieldInfo0) {
            if(this.wasBuilt) {
                throw new IllegalStateException("Builder can only build once");
            }
            this.fields.add(fieldInfo0);
        }

        public void withMessageSetWireFormat(boolean z) {
            this.messageSetWireFormat = z;
        }

        public void withSyntax(ProtoSyntax protoSyntax0) {
            this.syntax = (ProtoSyntax)Internal.checkNotNull(protoSyntax0, "syntax");
        }
    }

    private final int[] checkInitialized;
    private final MessageLite defaultInstance;
    private final FieldInfo[] fields;
    private final boolean messageSetWireFormat;
    private final ProtoSyntax syntax;

    StructuralMessageInfo(ProtoSyntax protoSyntax0, boolean z, int[] arr_v, FieldInfo[] arr_fieldInfo, Object object0) {
        this.syntax = protoSyntax0;
        this.messageSetWireFormat = z;
        this.checkInitialized = arr_v;
        this.fields = arr_fieldInfo;
        this.defaultInstance = (MessageLite)Internal.checkNotNull(object0, "defaultInstance");
    }

    public int[] getCheckInitialized() {
        return this.checkInitialized;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.MessageInfo
    public MessageLite getDefaultInstance() {
        return this.defaultInstance;
    }

    public FieldInfo[] getFields() {
        return this.fields;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.MessageInfo
    public ProtoSyntax getSyntax() {
        return this.syntax;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.MessageInfo
    public boolean isMessageSetWireFormat() {
        return this.messageSetWireFormat;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(int v) {
        return new Builder(v);
    }
}

