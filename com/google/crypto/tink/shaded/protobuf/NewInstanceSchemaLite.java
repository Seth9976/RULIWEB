package com.google.crypto.tink.shaded.protobuf;

@CheckReturnValue
final class NewInstanceSchemaLite implements NewInstanceSchema {
    @Override  // com.google.crypto.tink.shaded.protobuf.NewInstanceSchema
    public Object newInstance(Object object0) {
        return ((GeneratedMessageLite)object0).newMutableInstance();
    }
}

