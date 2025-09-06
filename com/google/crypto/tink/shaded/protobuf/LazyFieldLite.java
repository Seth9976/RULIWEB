package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;

public class LazyFieldLite {
    private static final ExtensionRegistryLite EMPTY_REGISTRY;
    private ByteString delayedBytes;
    private ExtensionRegistryLite extensionRegistry;
    private volatile ByteString memoizedBytes;
    protected volatile MessageLite value;

    static {
        LazyFieldLite.EMPTY_REGISTRY = ExtensionRegistryLite.getEmptyRegistry();
    }

    public LazyFieldLite() {
    }

    public LazyFieldLite(ExtensionRegistryLite extensionRegistryLite0, ByteString byteString0) {
        LazyFieldLite.checkArguments(extensionRegistryLite0, byteString0);
        this.extensionRegistry = extensionRegistryLite0;
        this.delayedBytes = byteString0;
    }

    private static void checkArguments(ExtensionRegistryLite extensionRegistryLite0, ByteString byteString0) {
        if(extensionRegistryLite0 == null) {
            throw new NullPointerException("found null ExtensionRegistry");
        }
        if(byteString0 == null) {
            throw new NullPointerException("found null ByteString");
        }
    }

    public void clear() {
        this.delayedBytes = null;
        this.value = null;
        this.memoizedBytes = null;
    }

    public boolean containsDefaultInstance() {
        return this.memoizedBytes == ByteString.EMPTY || this.value == null && (this.delayedBytes == null || this.delayedBytes == ByteString.EMPTY);
    }

    protected void ensureInitialized(MessageLite messageLite0) {
        if(this.value == null) {
            synchronized(this) {
                if(this.value != null) {
                    return;
                }
                goto label_5;
            }
            return;
            try {
            label_5:
                if(this.delayedBytes == null) {
                    this.value = messageLite0;
                    this.memoizedBytes = ByteString.EMPTY;
                }
                else {
                    this.value = (MessageLite)messageLite0.getParserForType().parseFrom(this.delayedBytes, this.extensionRegistry);
                    this.memoizedBytes = this.delayedBytes;
                }
            }
            catch(InvalidProtocolBufferException unused_ex) {
                this.value = messageLite0;
                this.memoizedBytes = ByteString.EMPTY;
            }
        }
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof LazyFieldLite)) {
            return false;
        }
        MessageLite messageLite0 = this.value;
        MessageLite messageLite1 = ((LazyFieldLite)object0).value;
        if(messageLite0 == null && messageLite1 == null) {
            return this.toByteString().equals(((LazyFieldLite)object0).toByteString());
        }
        if(messageLite0 != null && messageLite1 != null) {
            return messageLite0.equals(messageLite1);
        }
        return messageLite0 == null ? this.getValue(messageLite1.getDefaultInstanceForType()).equals(messageLite1) : messageLite0.equals(((LazyFieldLite)object0).getValue(messageLite0.getDefaultInstanceForType()));
    }

    public static LazyFieldLite fromValue(MessageLite messageLite0) {
        LazyFieldLite lazyFieldLite0 = new LazyFieldLite();
        lazyFieldLite0.setValue(messageLite0);
        return lazyFieldLite0;
    }

    public int getSerializedSize() {
        if(this.memoizedBytes != null) {
            return this.memoizedBytes.size();
        }
        ByteString byteString0 = this.delayedBytes;
        if(byteString0 != null) {
            return byteString0.size();
        }
        return this.value == null ? 0 : this.value.getSerializedSize();
    }

    public MessageLite getValue(MessageLite messageLite0) {
        this.ensureInitialized(messageLite0);
        return this.value;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    public void merge(LazyFieldLite lazyFieldLite0) {
        if(lazyFieldLite0.containsDefaultInstance()) {
            return;
        }
        if(this.containsDefaultInstance()) {
            this.set(lazyFieldLite0);
            return;
        }
        if(this.extensionRegistry == null) {
            this.extensionRegistry = lazyFieldLite0.extensionRegistry;
        }
        ByteString byteString0 = this.delayedBytes;
        if(byteString0 != null) {
            ByteString byteString1 = lazyFieldLite0.delayedBytes;
            if(byteString1 != null) {
                this.delayedBytes = byteString0.concat(byteString1);
                return;
            }
        }
        if(this.value == null && lazyFieldLite0.value != null) {
            this.setValue(LazyFieldLite.mergeValueAndBytes(lazyFieldLite0.value, this.delayedBytes, this.extensionRegistry));
            return;
        }
        if(this.value != null && lazyFieldLite0.value == null) {
            this.setValue(LazyFieldLite.mergeValueAndBytes(this.value, lazyFieldLite0.delayedBytes, lazyFieldLite0.extensionRegistry));
            return;
        }
        this.setValue(this.value.toBuilder().mergeFrom(lazyFieldLite0.value).build());
    }

    public void mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        if(this.containsDefaultInstance()) {
            this.setByteString(codedInputStream0.readBytes(), extensionRegistryLite0);
            return;
        }
        if(this.extensionRegistry == null) {
            this.extensionRegistry = extensionRegistryLite0;
        }
        ByteString byteString0 = this.delayedBytes;
        if(byteString0 != null) {
            this.setByteString(byteString0.concat(codedInputStream0.readBytes()), this.extensionRegistry);
            return;
        }
        try {
            this.setValue(this.value.toBuilder().mergeFrom(codedInputStream0, extensionRegistryLite0).build());
        }
        catch(InvalidProtocolBufferException unused_ex) {
        }
    }

    private static MessageLite mergeValueAndBytes(MessageLite messageLite0, ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) {
        try {
            return messageLite0.toBuilder().mergeFrom(byteString0, extensionRegistryLite0).build();
        }
        catch(InvalidProtocolBufferException unused_ex) {
            return messageLite0;
        }
    }

    public void set(LazyFieldLite lazyFieldLite0) {
        this.delayedBytes = lazyFieldLite0.delayedBytes;
        this.value = lazyFieldLite0.value;
        this.memoizedBytes = lazyFieldLite0.memoizedBytes;
        ExtensionRegistryLite extensionRegistryLite0 = lazyFieldLite0.extensionRegistry;
        if(extensionRegistryLite0 != null) {
            this.extensionRegistry = extensionRegistryLite0;
        }
    }

    public void setByteString(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) {
        LazyFieldLite.checkArguments(extensionRegistryLite0, byteString0);
        this.delayedBytes = byteString0;
        this.extensionRegistry = extensionRegistryLite0;
        this.value = null;
        this.memoizedBytes = null;
    }

    public MessageLite setValue(MessageLite messageLite0) {
        MessageLite messageLite1 = this.value;
        this.delayedBytes = null;
        this.memoizedBytes = null;
        this.value = messageLite0;
        return messageLite1;
    }

    public ByteString toByteString() {
        if(this.memoizedBytes != null) {
            return this.memoizedBytes;
        }
        ByteString byteString0 = this.delayedBytes;
        if(byteString0 != null) {
            return byteString0;
        }
        synchronized(this) {
            if(this.memoizedBytes != null) {
                return this.memoizedBytes;
            }
            this.memoizedBytes = this.value == null ? ByteString.EMPTY : this.value.toByteString();
            return this.memoizedBytes;
        }
    }

    void writeTo(Writer writer0, int v) throws IOException {
        if(this.memoizedBytes != null) {
            writer0.writeBytes(v, this.memoizedBytes);
            return;
        }
        ByteString byteString0 = this.delayedBytes;
        if(byteString0 != null) {
            writer0.writeBytes(v, byteString0);
            return;
        }
        if(this.value != null) {
            writer0.writeMessage(v, this.value);
            return;
        }
        writer0.writeBytes(v, ByteString.EMPTY);
    }
}

