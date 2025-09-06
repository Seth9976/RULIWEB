package kotlin.reflect.jvm.internal.impl.protobuf;

public class LazyFieldLite {
    private ByteString bytes;
    private ExtensionRegistryLite extensionRegistry;
    private volatile boolean isDirty;
    protected volatile MessageLite value;

    protected void ensureInitialized(MessageLite messageLite0) {
        if(this.value == null) {
            synchronized(this) {
                if(this.value != null) {
                    return;
                }
                this.value = this.bytes == null ? messageLite0 : ((MessageLite)messageLite0.getParserForType().parseFrom(this.bytes, this.extensionRegistry));
            }
        }
    }

    // 去混淆评级： 低(20)
    public int getSerializedSize() {
        return this.isDirty ? this.value.getSerializedSize() : this.bytes.size();
    }

    public MessageLite getValue(MessageLite messageLite0) {
        this.ensureInitialized(messageLite0);
        return this.value;
    }

    public MessageLite setValue(MessageLite messageLite0) {
        MessageLite messageLite1 = this.value;
        this.value = messageLite0;
        this.bytes = null;
        this.isDirty = true;
        return messageLite1;
    }
}

