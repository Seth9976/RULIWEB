package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class Mixin extends GeneratedMessageLite implements MixinOrBuilder {
    public static final class Builder extends com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder implements MixinOrBuilder {
        private Builder() {
            super(Mixin.DEFAULT_INSTANCE);
        }

        Builder(com.google.crypto.tink.shaded.protobuf.Mixin.1 mixin$10) {
        }

        public Builder clearName() {
            this.copyOnWrite();
            ((Mixin)this.instance).clearName();
            return this;
        }

        public Builder clearRoot() {
            this.copyOnWrite();
            ((Mixin)this.instance).clearRoot();
            return this;
        }

        // 去混淆评级： 低(20)
        @Override  // com.google.crypto.tink.shaded.protobuf.MixinOrBuilder
        public String getName() {
            return "";
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.MixinOrBuilder
        public ByteString getNameBytes() {
            return ((Mixin)this.instance).getNameBytes();
        }

        // 去混淆评级： 低(20)
        @Override  // com.google.crypto.tink.shaded.protobuf.MixinOrBuilder
        public String getRoot() {
            return "";
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.MixinOrBuilder
        public ByteString getRootBytes() {
            return ((Mixin)this.instance).getRootBytes();
        }

        public Builder setName(String s) {
            this.copyOnWrite();
            ((Mixin)this.instance).setName(s);
            return this;
        }

        public Builder setNameBytes(ByteString byteString0) {
            this.copyOnWrite();
            ((Mixin)this.instance).setNameBytes(byteString0);
            return this;
        }

        public Builder setRoot(String s) {
            this.copyOnWrite();
            ((Mixin)this.instance).setRoot(s);
            return this;
        }

        public Builder setRootBytes(ByteString byteString0) {
            this.copyOnWrite();
            ((Mixin)this.instance).setRootBytes(byteString0);
            return this;
        }
    }

    private static final Mixin DEFAULT_INSTANCE = null;
    public static final int NAME_FIELD_NUMBER = 1;
    private static volatile Parser PARSER = null;
    public static final int ROOT_FIELD_NUMBER = 2;
    private String name_;
    private String root_;

    static {
        Mixin mixin0 = new Mixin();
        Mixin.DEFAULT_INSTANCE = mixin0;
        GeneratedMessageLite.registerDefaultInstance(Mixin.class, mixin0);
    }

    private Mixin() {
        this.name_ = "";
        this.root_ = "";
    }

    private void clearName() {
        this.name_ = "";
    }

    private void clearRoot() {
        this.root_ = "";
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(MethodToInvoke generatedMessageLite$MethodToInvoke0, Object object0, Object object1) {
        switch(com.google.crypto.tink.shaded.protobuf.Mixin.1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[generatedMessageLite$MethodToInvoke0.ordinal()]) {
            case 1: {
                return new Mixin();
            }
            case 2: {
                return new Builder(null);
            }
            case 3: {
                return Mixin.newMessageInfo(Mixin.DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ", new Object[]{"name_", "root_"});
            }
            case 4: {
                return Mixin.DEFAULT_INSTANCE;
            }
            case 5: {
                Parser parser0 = Mixin.PARSER;
                if(parser0 == null) {
                    Class class0 = Mixin.class;
                    synchronized(class0) {
                        Parser parser1 = Mixin.PARSER;
                        if(parser1 == null) {
                            parser1 = new DefaultInstanceBasedParser(Mixin.DEFAULT_INSTANCE);
                            Mixin.PARSER = parser1;
                        }
                        return parser1;
                    }
                }
                return parser0;
            }
            case 6: {
                return (byte)1;
            }
            case 7: {
                return null;
            }
            default: {
                throw new UnsupportedOperationException();
            }
        }
    }

    public static Mixin getDefaultInstance() {
        return Mixin.DEFAULT_INSTANCE;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.MixinOrBuilder
    public String getName() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.shaded.protobuf.MixinOrBuilder
    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.MixinOrBuilder
    public String getRoot() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.shaded.protobuf.MixinOrBuilder
    public ByteString getRootBytes() {
        return ByteString.copyFromUtf8(this.root_);
    }

    public static Builder newBuilder() {
        return (Builder)Mixin.DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Mixin mixin0) {
        return (Builder)Mixin.DEFAULT_INSTANCE.createBuilder(mixin0);
    }

    public static Mixin parseDelimitedFrom(InputStream inputStream0) throws IOException {
        return (Mixin)Mixin.parseDelimitedFrom(Mixin.DEFAULT_INSTANCE, inputStream0);
    }

    public static Mixin parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (Mixin)Mixin.parseDelimitedFrom(Mixin.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static Mixin parseFrom(ByteString byteString0) throws InvalidProtocolBufferException {
        return (Mixin)GeneratedMessageLite.parseFrom(Mixin.DEFAULT_INSTANCE, byteString0);
    }

    public static Mixin parseFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (Mixin)GeneratedMessageLite.parseFrom(Mixin.DEFAULT_INSTANCE, byteString0, extensionRegistryLite0);
    }

    public static Mixin parseFrom(CodedInputStream codedInputStream0) throws IOException {
        return (Mixin)GeneratedMessageLite.parseFrom(Mixin.DEFAULT_INSTANCE, codedInputStream0);
    }

    public static Mixin parseFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (Mixin)GeneratedMessageLite.parseFrom(Mixin.DEFAULT_INSTANCE, codedInputStream0, extensionRegistryLite0);
    }

    public static Mixin parseFrom(InputStream inputStream0) throws IOException {
        return (Mixin)GeneratedMessageLite.parseFrom(Mixin.DEFAULT_INSTANCE, inputStream0);
    }

    public static Mixin parseFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return (Mixin)GeneratedMessageLite.parseFrom(Mixin.DEFAULT_INSTANCE, inputStream0, extensionRegistryLite0);
    }

    public static Mixin parseFrom(ByteBuffer byteBuffer0) throws InvalidProtocolBufferException {
        return (Mixin)GeneratedMessageLite.parseFrom(Mixin.DEFAULT_INSTANCE, byteBuffer0);
    }

    public static Mixin parseFrom(ByteBuffer byteBuffer0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (Mixin)GeneratedMessageLite.parseFrom(Mixin.DEFAULT_INSTANCE, byteBuffer0, extensionRegistryLite0);
    }

    public static Mixin parseFrom(byte[] arr_b) throws InvalidProtocolBufferException {
        return (Mixin)GeneratedMessageLite.parseFrom(Mixin.DEFAULT_INSTANCE, arr_b);
    }

    public static Mixin parseFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
        return (Mixin)GeneratedMessageLite.parseFrom(Mixin.DEFAULT_INSTANCE, arr_b, extensionRegistryLite0);
    }

    public static Parser parser() {
        return Mixin.DEFAULT_INSTANCE.getParserForType();
    }

    private void setName(String s) {
        s.getClass();
        this.name_ = s;
    }

    private void setNameBytes(ByteString byteString0) {
        Mixin.checkByteStringIsUtf8(byteString0);
        this.name_ = byteString0.toStringUtf8();
    }

    private void setRoot(String s) {
        s.getClass();
        this.root_ = s;
    }

    private void setRootBytes(ByteString byteString0) {
        Mixin.checkByteStringIsUtf8(byteString0);
        this.root_ = byteString0.toStringUtf8();
    }
}

