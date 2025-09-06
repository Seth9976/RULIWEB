package kotlin.reflect.jvm.internal.impl.metadata.jvm;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Constructor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter;
import kotlin.reflect.jvm.internal.impl.protobuf.AbstractParser;
import kotlin.reflect.jvm.internal.impl.protobuf.ByteString.Output;
import kotlin.reflect.jvm.internal.impl.protobuf.ByteString;
import kotlin.reflect.jvm.internal.impl.protobuf.CodedInputStream;
import kotlin.reflect.jvm.internal.impl.protobuf.CodedOutputStream;
import kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.GeneratedExtension;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite;
import kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLite;
import kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLiteMap;
import kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.protobuf.Parser;
import kotlin.reflect.jvm.internal.impl.protobuf.WireFormat.FieldType;

public final class JvmProtoBuf {
    public static final class JvmFieldSignature extends GeneratedMessageLite implements JvmProtoBuf.JvmFieldSignatureOrBuilder {
        public static final class Builder extends kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder implements JvmProtoBuf.JvmFieldSignatureOrBuilder {
            private int bitField0_;
            private int desc_;
            private int name_;

            static Builder access$2500() {
                return Builder.create();
            }

            public JvmFieldSignature build() {
                JvmFieldSignature jvmProtoBuf$JvmFieldSignature0 = this.buildPartial();
                if(!jvmProtoBuf$JvmFieldSignature0.isInitialized()) {
                    throw Builder.newUninitializedMessageException(jvmProtoBuf$JvmFieldSignature0);
                }
                return jvmProtoBuf$JvmFieldSignature0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite$Builder
            public MessageLite build() {
                return this.build();
            }

            public JvmFieldSignature buildPartial() {
                JvmFieldSignature jvmProtoBuf$JvmFieldSignature0 = new JvmFieldSignature(this, null);
                int v = this.bitField0_;
                int v1 = (v & 1) == 1 ? 1 : 0;
                jvmProtoBuf$JvmFieldSignature0.name_ = this.name_;
                if((v & 2) == 2) {
                    v1 |= 2;
                }
                jvmProtoBuf$JvmFieldSignature0.desc_ = this.desc_;
                jvmProtoBuf$JvmFieldSignature0.bitField0_ = v1;
                return jvmProtoBuf$JvmFieldSignature0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public Object clone() throws CloneNotSupportedException {
                return this.clone();
            }

            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder clone() {
                return this.clone();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder clone() {
                return this.clone();
            }

            private static Builder create() {
                return new Builder();
            }

            public JvmFieldSignature getDefaultInstanceForType() {
                return JvmFieldSignature.getDefaultInstance();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public GeneratedMessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            private void maybeForceBuilderInitialization() {
            }

            public Builder mergeFrom(JvmFieldSignature jvmProtoBuf$JvmFieldSignature0) {
                if(jvmProtoBuf$JvmFieldSignature0 == JvmFieldSignature.getDefaultInstance()) {
                    return this;
                }
                if(jvmProtoBuf$JvmFieldSignature0.hasName()) {
                    this.setName(jvmProtoBuf$JvmFieldSignature0.getName());
                }
                if(jvmProtoBuf$JvmFieldSignature0.hasDesc()) {
                    this.setDesc(jvmProtoBuf$JvmFieldSignature0.getDesc());
                }
                this.setUnknownFields(this.getUnknownFields().concat(jvmProtoBuf$JvmFieldSignature0.unknownFields));
                return this;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                JvmFieldSignature jvmProtoBuf$JvmFieldSignature1;
                JvmFieldSignature jvmProtoBuf$JvmFieldSignature0;
                try {
                    try {
                        jvmProtoBuf$JvmFieldSignature0 = null;
                        jvmProtoBuf$JvmFieldSignature1 = (JvmFieldSignature)JvmFieldSignature.PARSER.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                        goto label_13;
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        JvmFieldSignature jvmProtoBuf$JvmFieldSignature2 = (JvmFieldSignature)invalidProtocolBufferException0.getUnfinishedMessage();
                        try {
                            throw invalidProtocolBufferException0;
                        }
                        catch(Throwable throwable0) {
                            jvmProtoBuf$JvmFieldSignature0 = jvmProtoBuf$JvmFieldSignature2;
                        }
                    }
                }
                catch(Throwable throwable0) {
                }
                if(jvmProtoBuf$JvmFieldSignature0 != null) {
                    this.mergeFrom(jvmProtoBuf$JvmFieldSignature0);
                }
                throw throwable0;
            label_13:
                if(jvmProtoBuf$JvmFieldSignature1 != null) {
                    this.mergeFrom(jvmProtoBuf$JvmFieldSignature1);
                }
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedMessageLite0) {
                return this.mergeFrom(((JvmFieldSignature)generatedMessageLite0));
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            public Builder setDesc(int v) {
                this.bitField0_ |= 2;
                this.desc_ = v;
                return this;
            }

            public Builder setName(int v) {
                this.bitField0_ |= 1;
                this.name_ = v;
                return this;
            }
        }

        public static Parser PARSER;
        private int bitField0_;
        private static final JvmFieldSignature defaultInstance;
        private int desc_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private int name_;
        private final ByteString unknownFields;

        static {
            JvmFieldSignature.PARSER = new AbstractParser() {
                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Parser
                public Object parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return this.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                }

                public JvmFieldSignature parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return new JvmFieldSignature(codedInputStream0, extensionRegistryLite0, null);
                }
            };
            JvmFieldSignature jvmProtoBuf$JvmFieldSignature0 = new JvmFieldSignature(true);
            JvmFieldSignature.defaultInstance = jvmProtoBuf$JvmFieldSignature0;
            jvmProtoBuf$JvmFieldSignature0.initFields();
        }

        private JvmFieldSignature(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.initFields();
            Output byteString$Output0 = ByteString.newOutput();
            CodedOutputStream codedOutputStream0 = CodedOutputStream.newInstance(byteString$Output0, 1);
            try {
                boolean z = false;
                while(!z) {
                    try {
                        try {
                            int v1 = codedInputStream0.readTag();
                            switch(v1) {
                                case 0: {
                                    z = true;
                                    continue;
                                }
                                case 8: {
                                    this.bitField0_ |= 1;
                                    this.name_ = codedInputStream0.readInt32();
                                    continue;
                                }
                                case 16: {
                                    this.bitField0_ |= 2;
                                    this.desc_ = codedInputStream0.readInt32();
                                    continue;
                                }
                                default: {
                                    if(!this.parseUnknownField(codedInputStream0, codedOutputStream0, extensionRegistryLite0, v1)) {
                                        z = true;
                                        continue;
                                    }
                                    continue;
                                }
                            }
                        }
                        catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        }
                        catch(IOException iOException0) {
                            throw new InvalidProtocolBufferException(iOException0.getMessage()).setUnfinishedMessage(this);
                        }
                        throw invalidProtocolBufferException0.setUnfinishedMessage(this);
                    }
                    catch(Throwable throwable0) {
                        try {
                            codedOutputStream0.flush();
                        }
                        catch(IOException unused_ex) {
                        }
                        throw throwable0;
                    }
                }
                try {
                    codedOutputStream0.flush();
                }
                catch(IOException unused_ex) {
                }
            }
            finally {
                this.unknownFields = byteString$Output0.toByteString();
            }
        }

        JvmFieldSignature(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0, kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.1 jvmProtoBuf$10) throws InvalidProtocolBufferException {
            this(codedInputStream0, extensionRegistryLite0);
        }

        private JvmFieldSignature(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder generatedMessageLite$Builder0) {
            super(generatedMessageLite$Builder0);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = generatedMessageLite$Builder0.getUnknownFields();
        }

        JvmFieldSignature(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder generatedMessageLite$Builder0, kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.1 jvmProtoBuf$10) {
            this(generatedMessageLite$Builder0);
        }

        private JvmFieldSignature(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static JvmFieldSignature getDefaultInstance() {
            return JvmFieldSignature.defaultInstance;
        }

        public JvmFieldSignature getDefaultInstanceForType() {
            return JvmFieldSignature.defaultInstance;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return this.getDefaultInstanceForType();
        }

        public int getDesc() {
            return this.desc_;
        }

        public int getName() {
            return this.name_;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite
        public Parser getParserForType() {
            return JvmFieldSignature.PARSER;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int v = this.memoizedSerializedSize;
            if(v != -1) {
                return v;
            }
            int v1 = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeInt32Size(1, this.name_) : 0;
            if((this.bitField0_ & 2) == 2) {
                v1 += CodedOutputStream.computeInt32Size(2, this.desc_);
            }
            int v2 = v1 + this.unknownFields.size();
            this.memoizedSerializedSize = v2;
            return v2;
        }

        public boolean hasDesc() {
            return (this.bitField0_ & 2) == 2;
        }

        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        private void initFields() {
            this.name_ = 0;
            this.desc_ = 0;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            int v = this.memoizedIsInitialized;
            if(v == 1) {
                return true;
            }
            if(v == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public static Builder newBuilder() {
            return Builder.access$2500();
        }

        public static Builder newBuilder(JvmFieldSignature jvmProtoBuf$JvmFieldSignature0) {
            return JvmFieldSignature.newBuilder().mergeFrom(jvmProtoBuf$JvmFieldSignature0);
        }

        public Builder newBuilderForType() {
            return JvmFieldSignature.newBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder newBuilderForType() {
            return this.newBuilderForType();
        }

        public Builder toBuilder() {
            return JvmFieldSignature.newBuilder(this);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder toBuilder() {
            return this.toBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream0) throws IOException {
            this.getSerializedSize();
            if((this.bitField0_ & 1) == 1) {
                codedOutputStream0.writeInt32(1, this.name_);
            }
            if((this.bitField0_ & 2) == 2) {
                codedOutputStream0.writeInt32(2, this.desc_);
            }
            codedOutputStream0.writeRawBytes(this.unknownFields);
        }
    }

    public static final class JvmMethodSignature extends GeneratedMessageLite implements JvmProtoBuf.JvmMethodSignatureOrBuilder {
        public static final class kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmMethodSignature.Builder extends kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder implements JvmProtoBuf.JvmMethodSignatureOrBuilder {
            private int bitField0_;
            private int desc_;
            private int name_;

            static kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmMethodSignature.Builder access$1800() {
                return kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmMethodSignature.Builder.create();
            }

            public JvmMethodSignature build() {
                JvmMethodSignature jvmProtoBuf$JvmMethodSignature0 = this.buildPartial();
                if(!jvmProtoBuf$JvmMethodSignature0.isInitialized()) {
                    throw kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmMethodSignature.Builder.newUninitializedMessageException(jvmProtoBuf$JvmMethodSignature0);
                }
                return jvmProtoBuf$JvmMethodSignature0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite$Builder
            public MessageLite build() {
                return this.build();
            }

            public JvmMethodSignature buildPartial() {
                JvmMethodSignature jvmProtoBuf$JvmMethodSignature0 = new JvmMethodSignature(this, null);
                int v = this.bitField0_;
                int v1 = (v & 1) == 1 ? 1 : 0;
                jvmProtoBuf$JvmMethodSignature0.name_ = this.name_;
                if((v & 2) == 2) {
                    v1 |= 2;
                }
                jvmProtoBuf$JvmMethodSignature0.desc_ = this.desc_;
                jvmProtoBuf$JvmMethodSignature0.bitField0_ = v1;
                return jvmProtoBuf$JvmMethodSignature0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public Object clone() throws CloneNotSupportedException {
                return this.clone();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmMethodSignature.Builder clone() {
                return kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmMethodSignature.Builder.create().mergeFrom(this.buildPartial());
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder clone() {
                return this.clone();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder clone() {
                return this.clone();
            }

            private static kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmMethodSignature.Builder create() {
                return new kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmMethodSignature.Builder();
            }

            public JvmMethodSignature getDefaultInstanceForType() {
                return JvmMethodSignature.getDefaultInstance();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public GeneratedMessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            private void maybeForceBuilderInitialization() {
            }

            public kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmMethodSignature.Builder mergeFrom(JvmMethodSignature jvmProtoBuf$JvmMethodSignature0) {
                if(jvmProtoBuf$JvmMethodSignature0 == JvmMethodSignature.getDefaultInstance()) {
                    return this;
                }
                if(jvmProtoBuf$JvmMethodSignature0.hasName()) {
                    this.setName(jvmProtoBuf$JvmMethodSignature0.getName());
                }
                if(jvmProtoBuf$JvmMethodSignature0.hasDesc()) {
                    this.setDesc(jvmProtoBuf$JvmMethodSignature0.getDesc());
                }
                this.setUnknownFields(this.getUnknownFields().concat(jvmProtoBuf$JvmMethodSignature0.unknownFields));
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmMethodSignature.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                JvmMethodSignature jvmProtoBuf$JvmMethodSignature1;
                JvmMethodSignature jvmProtoBuf$JvmMethodSignature0;
                try {
                    try {
                        jvmProtoBuf$JvmMethodSignature0 = null;
                        jvmProtoBuf$JvmMethodSignature1 = (JvmMethodSignature)JvmMethodSignature.PARSER.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                        goto label_13;
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        JvmMethodSignature jvmProtoBuf$JvmMethodSignature2 = (JvmMethodSignature)invalidProtocolBufferException0.getUnfinishedMessage();
                        try {
                            throw invalidProtocolBufferException0;
                        }
                        catch(Throwable throwable0) {
                            jvmProtoBuf$JvmMethodSignature0 = jvmProtoBuf$JvmMethodSignature2;
                        }
                    }
                }
                catch(Throwable throwable0) {
                }
                if(jvmProtoBuf$JvmMethodSignature0 != null) {
                    this.mergeFrom(jvmProtoBuf$JvmMethodSignature0);
                }
                throw throwable0;
            label_13:
                if(jvmProtoBuf$JvmMethodSignature1 != null) {
                    this.mergeFrom(jvmProtoBuf$JvmMethodSignature1);
                }
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedMessageLite0) {
                return this.mergeFrom(((JvmMethodSignature)generatedMessageLite0));
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            public kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmMethodSignature.Builder setDesc(int v) {
                this.bitField0_ |= 2;
                this.desc_ = v;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmMethodSignature.Builder setName(int v) {
                this.bitField0_ |= 1;
                this.name_ = v;
                return this;
            }
        }

        public static Parser PARSER;
        private int bitField0_;
        private static final JvmMethodSignature defaultInstance;
        private int desc_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private int name_;
        private final ByteString unknownFields;

        static {
            JvmMethodSignature.PARSER = new AbstractParser() {
                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Parser
                public Object parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return this.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                }

                public JvmMethodSignature parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return new JvmMethodSignature(codedInputStream0, extensionRegistryLite0, null);
                }
            };
            JvmMethodSignature jvmProtoBuf$JvmMethodSignature0 = new JvmMethodSignature(true);
            JvmMethodSignature.defaultInstance = jvmProtoBuf$JvmMethodSignature0;
            jvmProtoBuf$JvmMethodSignature0.initFields();
        }

        private JvmMethodSignature(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.initFields();
            Output byteString$Output0 = ByteString.newOutput();
            CodedOutputStream codedOutputStream0 = CodedOutputStream.newInstance(byteString$Output0, 1);
            try {
                boolean z = false;
                while(!z) {
                    try {
                        try {
                            int v1 = codedInputStream0.readTag();
                            switch(v1) {
                                case 0: {
                                    z = true;
                                    continue;
                                }
                                case 8: {
                                    this.bitField0_ |= 1;
                                    this.name_ = codedInputStream0.readInt32();
                                    continue;
                                }
                                case 16: {
                                    this.bitField0_ |= 2;
                                    this.desc_ = codedInputStream0.readInt32();
                                    continue;
                                }
                                default: {
                                    if(!this.parseUnknownField(codedInputStream0, codedOutputStream0, extensionRegistryLite0, v1)) {
                                        z = true;
                                        continue;
                                    }
                                    continue;
                                }
                            }
                        }
                        catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        }
                        catch(IOException iOException0) {
                            throw new InvalidProtocolBufferException(iOException0.getMessage()).setUnfinishedMessage(this);
                        }
                        throw invalidProtocolBufferException0.setUnfinishedMessage(this);
                    }
                    catch(Throwable throwable0) {
                        try {
                            codedOutputStream0.flush();
                        }
                        catch(IOException unused_ex) {
                        }
                        throw throwable0;
                    }
                }
                try {
                    codedOutputStream0.flush();
                }
                catch(IOException unused_ex) {
                }
            }
            finally {
                this.unknownFields = byteString$Output0.toByteString();
            }
        }

        JvmMethodSignature(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0, kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.1 jvmProtoBuf$10) throws InvalidProtocolBufferException {
            this(codedInputStream0, extensionRegistryLite0);
        }

        private JvmMethodSignature(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder generatedMessageLite$Builder0) {
            super(generatedMessageLite$Builder0);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = generatedMessageLite$Builder0.getUnknownFields();
        }

        JvmMethodSignature(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder generatedMessageLite$Builder0, kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.1 jvmProtoBuf$10) {
            this(generatedMessageLite$Builder0);
        }

        private JvmMethodSignature(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static JvmMethodSignature getDefaultInstance() {
            return JvmMethodSignature.defaultInstance;
        }

        public JvmMethodSignature getDefaultInstanceForType() {
            return JvmMethodSignature.defaultInstance;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return this.getDefaultInstanceForType();
        }

        public int getDesc() {
            return this.desc_;
        }

        public int getName() {
            return this.name_;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite
        public Parser getParserForType() {
            return JvmMethodSignature.PARSER;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int v = this.memoizedSerializedSize;
            if(v != -1) {
                return v;
            }
            int v1 = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeInt32Size(1, this.name_) : 0;
            if((this.bitField0_ & 2) == 2) {
                v1 += CodedOutputStream.computeInt32Size(2, this.desc_);
            }
            int v2 = v1 + this.unknownFields.size();
            this.memoizedSerializedSize = v2;
            return v2;
        }

        public boolean hasDesc() {
            return (this.bitField0_ & 2) == 2;
        }

        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        private void initFields() {
            this.name_ = 0;
            this.desc_ = 0;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            int v = this.memoizedIsInitialized;
            if(v == 1) {
                return true;
            }
            if(v == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmMethodSignature.Builder newBuilder() {
            return kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmMethodSignature.Builder.access$1800();
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmMethodSignature.Builder newBuilder(JvmMethodSignature jvmProtoBuf$JvmMethodSignature0) {
            return JvmMethodSignature.newBuilder().mergeFrom(jvmProtoBuf$JvmMethodSignature0);
        }

        public kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmMethodSignature.Builder newBuilderForType() {
            return JvmMethodSignature.newBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder newBuilderForType() {
            return this.newBuilderForType();
        }

        public kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmMethodSignature.Builder toBuilder() {
            return JvmMethodSignature.newBuilder(this);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder toBuilder() {
            return this.toBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream0) throws IOException {
            this.getSerializedSize();
            if((this.bitField0_ & 1) == 1) {
                codedOutputStream0.writeInt32(1, this.name_);
            }
            if((this.bitField0_ & 2) == 2) {
                codedOutputStream0.writeInt32(2, this.desc_);
            }
            codedOutputStream0.writeRawBytes(this.unknownFields);
        }
    }

    public static final class JvmPropertySignature extends GeneratedMessageLite implements JvmProtoBuf.JvmPropertySignatureOrBuilder {
        public static final class kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmPropertySignature.Builder extends kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder implements JvmProtoBuf.JvmPropertySignatureOrBuilder {
            private int bitField0_;
            private JvmMethodSignature delegateMethod_;
            private JvmFieldSignature field_;
            private JvmMethodSignature getter_;
            private JvmMethodSignature setter_;
            private JvmMethodSignature syntheticMethod_;

            private kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmPropertySignature.Builder() {
                this.field_ = JvmFieldSignature.getDefaultInstance();
                this.syntheticMethod_ = JvmMethodSignature.getDefaultInstance();
                this.getter_ = JvmMethodSignature.getDefaultInstance();
                this.setter_ = JvmMethodSignature.getDefaultInstance();
                this.delegateMethod_ = JvmMethodSignature.getDefaultInstance();
            }

            static kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmPropertySignature.Builder access$3200() {
                return kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmPropertySignature.Builder.create();
            }

            public JvmPropertySignature build() {
                JvmPropertySignature jvmProtoBuf$JvmPropertySignature0 = this.buildPartial();
                if(!jvmProtoBuf$JvmPropertySignature0.isInitialized()) {
                    throw kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmPropertySignature.Builder.newUninitializedMessageException(jvmProtoBuf$JvmPropertySignature0);
                }
                return jvmProtoBuf$JvmPropertySignature0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite$Builder
            public MessageLite build() {
                return this.build();
            }

            public JvmPropertySignature buildPartial() {
                JvmPropertySignature jvmProtoBuf$JvmPropertySignature0 = new JvmPropertySignature(this, null);
                int v = this.bitField0_;
                int v1 = (v & 1) == 1 ? 1 : 0;
                jvmProtoBuf$JvmPropertySignature0.field_ = this.field_;
                if((v & 2) == 2) {
                    v1 |= 2;
                }
                jvmProtoBuf$JvmPropertySignature0.syntheticMethod_ = this.syntheticMethod_;
                if((v & 4) == 4) {
                    v1 |= 4;
                }
                jvmProtoBuf$JvmPropertySignature0.getter_ = this.getter_;
                if((v & 8) == 8) {
                    v1 |= 8;
                }
                jvmProtoBuf$JvmPropertySignature0.setter_ = this.setter_;
                if((v & 16) == 16) {
                    v1 |= 16;
                }
                jvmProtoBuf$JvmPropertySignature0.delegateMethod_ = this.delegateMethod_;
                jvmProtoBuf$JvmPropertySignature0.bitField0_ = v1;
                return jvmProtoBuf$JvmPropertySignature0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public Object clone() throws CloneNotSupportedException {
                return this.clone();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmPropertySignature.Builder clone() {
                return kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmPropertySignature.Builder.create().mergeFrom(this.buildPartial());
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder clone() {
                return this.clone();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder clone() {
                return this.clone();
            }

            private static kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmPropertySignature.Builder create() {
                return new kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmPropertySignature.Builder();
            }

            public JvmPropertySignature getDefaultInstanceForType() {
                return JvmPropertySignature.getDefaultInstance();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public GeneratedMessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            private void maybeForceBuilderInitialization() {
            }

            public kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmPropertySignature.Builder mergeDelegateMethod(JvmMethodSignature jvmProtoBuf$JvmMethodSignature0) {
                this.delegateMethod_ = (this.bitField0_ & 16) != 16 || this.delegateMethod_ == JvmMethodSignature.getDefaultInstance() ? jvmProtoBuf$JvmMethodSignature0 : JvmMethodSignature.newBuilder(this.delegateMethod_).mergeFrom(jvmProtoBuf$JvmMethodSignature0).buildPartial();
                this.bitField0_ |= 16;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmPropertySignature.Builder mergeField(JvmFieldSignature jvmProtoBuf$JvmFieldSignature0) {
                this.field_ = (this.bitField0_ & 1) != 1 || this.field_ == JvmFieldSignature.getDefaultInstance() ? jvmProtoBuf$JvmFieldSignature0 : JvmFieldSignature.newBuilder(this.field_).mergeFrom(jvmProtoBuf$JvmFieldSignature0).buildPartial();
                this.bitField0_ |= 1;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmPropertySignature.Builder mergeFrom(JvmPropertySignature jvmProtoBuf$JvmPropertySignature0) {
                if(jvmProtoBuf$JvmPropertySignature0 == JvmPropertySignature.getDefaultInstance()) {
                    return this;
                }
                if(jvmProtoBuf$JvmPropertySignature0.hasField()) {
                    this.mergeField(jvmProtoBuf$JvmPropertySignature0.getField());
                }
                if(jvmProtoBuf$JvmPropertySignature0.hasSyntheticMethod()) {
                    this.mergeSyntheticMethod(jvmProtoBuf$JvmPropertySignature0.getSyntheticMethod());
                }
                if(jvmProtoBuf$JvmPropertySignature0.hasGetter()) {
                    this.mergeGetter(jvmProtoBuf$JvmPropertySignature0.getGetter());
                }
                if(jvmProtoBuf$JvmPropertySignature0.hasSetter()) {
                    this.mergeSetter(jvmProtoBuf$JvmPropertySignature0.getSetter());
                }
                if(jvmProtoBuf$JvmPropertySignature0.hasDelegateMethod()) {
                    this.mergeDelegateMethod(jvmProtoBuf$JvmPropertySignature0.getDelegateMethod());
                }
                this.setUnknownFields(this.getUnknownFields().concat(jvmProtoBuf$JvmPropertySignature0.unknownFields));
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmPropertySignature.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                JvmPropertySignature jvmProtoBuf$JvmPropertySignature1;
                JvmPropertySignature jvmProtoBuf$JvmPropertySignature0;
                try {
                    try {
                        jvmProtoBuf$JvmPropertySignature0 = null;
                        jvmProtoBuf$JvmPropertySignature1 = (JvmPropertySignature)JvmPropertySignature.PARSER.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                        goto label_13;
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        JvmPropertySignature jvmProtoBuf$JvmPropertySignature2 = (JvmPropertySignature)invalidProtocolBufferException0.getUnfinishedMessage();
                        try {
                            throw invalidProtocolBufferException0;
                        }
                        catch(Throwable throwable0) {
                            jvmProtoBuf$JvmPropertySignature0 = jvmProtoBuf$JvmPropertySignature2;
                        }
                    }
                }
                catch(Throwable throwable0) {
                }
                if(jvmProtoBuf$JvmPropertySignature0 != null) {
                    this.mergeFrom(jvmProtoBuf$JvmPropertySignature0);
                }
                throw throwable0;
            label_13:
                if(jvmProtoBuf$JvmPropertySignature1 != null) {
                    this.mergeFrom(jvmProtoBuf$JvmPropertySignature1);
                }
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedMessageLite0) {
                return this.mergeFrom(((JvmPropertySignature)generatedMessageLite0));
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            public kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmPropertySignature.Builder mergeGetter(JvmMethodSignature jvmProtoBuf$JvmMethodSignature0) {
                this.getter_ = (this.bitField0_ & 4) != 4 || this.getter_ == JvmMethodSignature.getDefaultInstance() ? jvmProtoBuf$JvmMethodSignature0 : JvmMethodSignature.newBuilder(this.getter_).mergeFrom(jvmProtoBuf$JvmMethodSignature0).buildPartial();
                this.bitField0_ |= 4;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmPropertySignature.Builder mergeSetter(JvmMethodSignature jvmProtoBuf$JvmMethodSignature0) {
                this.setter_ = (this.bitField0_ & 8) != 8 || this.setter_ == JvmMethodSignature.getDefaultInstance() ? jvmProtoBuf$JvmMethodSignature0 : JvmMethodSignature.newBuilder(this.setter_).mergeFrom(jvmProtoBuf$JvmMethodSignature0).buildPartial();
                this.bitField0_ |= 8;
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmPropertySignature.Builder mergeSyntheticMethod(JvmMethodSignature jvmProtoBuf$JvmMethodSignature0) {
                this.syntheticMethod_ = (this.bitField0_ & 2) != 2 || this.syntheticMethod_ == JvmMethodSignature.getDefaultInstance() ? jvmProtoBuf$JvmMethodSignature0 : JvmMethodSignature.newBuilder(this.syntheticMethod_).mergeFrom(jvmProtoBuf$JvmMethodSignature0).buildPartial();
                this.bitField0_ |= 2;
                return this;
            }
        }

        public static Parser PARSER;
        private int bitField0_;
        private static final JvmPropertySignature defaultInstance;
        private JvmMethodSignature delegateMethod_;
        private JvmFieldSignature field_;
        private JvmMethodSignature getter_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private JvmMethodSignature setter_;
        private JvmMethodSignature syntheticMethod_;
        private final ByteString unknownFields;

        static {
            JvmPropertySignature.PARSER = new AbstractParser() {
                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Parser
                public Object parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return this.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                }

                public JvmPropertySignature parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return new JvmPropertySignature(codedInputStream0, extensionRegistryLite0, null);
                }
            };
            JvmPropertySignature jvmProtoBuf$JvmPropertySignature0 = new JvmPropertySignature(true);
            JvmPropertySignature.defaultInstance = jvmProtoBuf$JvmPropertySignature0;
            jvmProtoBuf$JvmPropertySignature0.initFields();
        }

        private JvmPropertySignature(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.initFields();
            Output byteString$Output0 = ByteString.newOutput();
            CodedOutputStream codedOutputStream0 = CodedOutputStream.newInstance(byteString$Output0, 1);
            try {
                boolean z = false;
                while(!z) {
                    try {
                        try {
                            int v1 = codedInputStream0.readTag();
                            if(v1 != 0) {
                                Builder jvmProtoBuf$JvmFieldSignature$Builder0 = null;
                                switch(v1) {
                                    case 10: {
                                        if((this.bitField0_ & 1) == 1) {
                                            jvmProtoBuf$JvmFieldSignature$Builder0 = this.field_.toBuilder();
                                        }
                                        JvmFieldSignature jvmProtoBuf$JvmFieldSignature0 = (JvmFieldSignature)codedInputStream0.readMessage(JvmFieldSignature.PARSER, extensionRegistryLite0);
                                        this.field_ = jvmProtoBuf$JvmFieldSignature0;
                                        if(jvmProtoBuf$JvmFieldSignature$Builder0 != null) {
                                            jvmProtoBuf$JvmFieldSignature$Builder0.mergeFrom(jvmProtoBuf$JvmFieldSignature0);
                                            this.field_ = jvmProtoBuf$JvmFieldSignature$Builder0.buildPartial();
                                        }
                                        this.bitField0_ |= 1;
                                        continue;
                                    }
                                    case 18: {
                                        if((this.bitField0_ & 2) == 2) {
                                            jvmProtoBuf$JvmFieldSignature$Builder0 = this.syntheticMethod_.toBuilder();
                                        }
                                        JvmMethodSignature jvmProtoBuf$JvmMethodSignature3 = (JvmMethodSignature)codedInputStream0.readMessage(JvmMethodSignature.PARSER, extensionRegistryLite0);
                                        this.syntheticMethod_ = jvmProtoBuf$JvmMethodSignature3;
                                        if(jvmProtoBuf$JvmFieldSignature$Builder0 != null) {
                                            ((kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmMethodSignature.Builder)jvmProtoBuf$JvmFieldSignature$Builder0).mergeFrom(jvmProtoBuf$JvmMethodSignature3);
                                            this.syntheticMethod_ = ((kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmMethodSignature.Builder)jvmProtoBuf$JvmFieldSignature$Builder0).buildPartial();
                                        }
                                        this.bitField0_ |= 2;
                                        continue;
                                    }
                                    case 26: {
                                        if((this.bitField0_ & 4) == 4) {
                                            jvmProtoBuf$JvmFieldSignature$Builder0 = this.getter_.toBuilder();
                                        }
                                        JvmMethodSignature jvmProtoBuf$JvmMethodSignature2 = (JvmMethodSignature)codedInputStream0.readMessage(JvmMethodSignature.PARSER, extensionRegistryLite0);
                                        this.getter_ = jvmProtoBuf$JvmMethodSignature2;
                                        if(jvmProtoBuf$JvmFieldSignature$Builder0 != null) {
                                            ((kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmMethodSignature.Builder)jvmProtoBuf$JvmFieldSignature$Builder0).mergeFrom(jvmProtoBuf$JvmMethodSignature2);
                                            this.getter_ = ((kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmMethodSignature.Builder)jvmProtoBuf$JvmFieldSignature$Builder0).buildPartial();
                                        }
                                        this.bitField0_ |= 4;
                                        continue;
                                    }
                                    case 34: {
                                        if((this.bitField0_ & 8) == 8) {
                                            jvmProtoBuf$JvmFieldSignature$Builder0 = this.setter_.toBuilder();
                                        }
                                        JvmMethodSignature jvmProtoBuf$JvmMethodSignature1 = (JvmMethodSignature)codedInputStream0.readMessage(JvmMethodSignature.PARSER, extensionRegistryLite0);
                                        this.setter_ = jvmProtoBuf$JvmMethodSignature1;
                                        if(jvmProtoBuf$JvmFieldSignature$Builder0 != null) {
                                            ((kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmMethodSignature.Builder)jvmProtoBuf$JvmFieldSignature$Builder0).mergeFrom(jvmProtoBuf$JvmMethodSignature1);
                                            this.setter_ = ((kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmMethodSignature.Builder)jvmProtoBuf$JvmFieldSignature$Builder0).buildPartial();
                                        }
                                        this.bitField0_ |= 8;
                                        continue;
                                    }
                                    case 42: {
                                        if((this.bitField0_ & 16) == 16) {
                                            jvmProtoBuf$JvmFieldSignature$Builder0 = this.delegateMethod_.toBuilder();
                                        }
                                        JvmMethodSignature jvmProtoBuf$JvmMethodSignature0 = (JvmMethodSignature)codedInputStream0.readMessage(JvmMethodSignature.PARSER, extensionRegistryLite0);
                                        this.delegateMethod_ = jvmProtoBuf$JvmMethodSignature0;
                                        if(jvmProtoBuf$JvmFieldSignature$Builder0 != null) {
                                            ((kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmMethodSignature.Builder)jvmProtoBuf$JvmFieldSignature$Builder0).mergeFrom(jvmProtoBuf$JvmMethodSignature0);
                                            this.delegateMethod_ = ((kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmMethodSignature.Builder)jvmProtoBuf$JvmFieldSignature$Builder0).buildPartial();
                                        }
                                        this.bitField0_ |= 16;
                                        continue;
                                    }
                                    default: {
                                        if(this.parseUnknownField(codedInputStream0, codedOutputStream0, extensionRegistryLite0, v1)) {
                                            continue;
                                        }
                                    }
                                }
                            }
                            z = true;
                            continue;
                        }
                        catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        }
                        catch(IOException iOException0) {
                            throw new InvalidProtocolBufferException(iOException0.getMessage()).setUnfinishedMessage(this);
                        }
                        throw invalidProtocolBufferException0.setUnfinishedMessage(this);
                    }
                    catch(Throwable throwable0) {
                        try {
                            codedOutputStream0.flush();
                        }
                        catch(IOException unused_ex) {
                        }
                        throw throwable0;
                    }
                }
                try {
                    codedOutputStream0.flush();
                }
                catch(IOException unused_ex) {
                }
            }
            finally {
                this.unknownFields = byteString$Output0.toByteString();
            }
        }

        JvmPropertySignature(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0, kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.1 jvmProtoBuf$10) throws InvalidProtocolBufferException {
            this(codedInputStream0, extensionRegistryLite0);
        }

        private JvmPropertySignature(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder generatedMessageLite$Builder0) {
            super(generatedMessageLite$Builder0);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = generatedMessageLite$Builder0.getUnknownFields();
        }

        JvmPropertySignature(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder generatedMessageLite$Builder0, kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.1 jvmProtoBuf$10) {
            this(generatedMessageLite$Builder0);
        }

        private JvmPropertySignature(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static JvmPropertySignature getDefaultInstance() {
            return JvmPropertySignature.defaultInstance;
        }

        public JvmPropertySignature getDefaultInstanceForType() {
            return JvmPropertySignature.defaultInstance;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return this.getDefaultInstanceForType();
        }

        public JvmMethodSignature getDelegateMethod() {
            return this.delegateMethod_;
        }

        public JvmFieldSignature getField() {
            return this.field_;
        }

        public JvmMethodSignature getGetter() {
            return this.getter_;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite
        public Parser getParserForType() {
            return JvmPropertySignature.PARSER;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int v = this.memoizedSerializedSize;
            if(v != -1) {
                return v;
            }
            int v1 = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeMessageSize(1, this.field_) : 0;
            if((this.bitField0_ & 2) == 2) {
                v1 += CodedOutputStream.computeMessageSize(2, this.syntheticMethod_);
            }
            if((this.bitField0_ & 4) == 4) {
                v1 += CodedOutputStream.computeMessageSize(3, this.getter_);
            }
            if((this.bitField0_ & 8) == 8) {
                v1 += CodedOutputStream.computeMessageSize(4, this.setter_);
            }
            if((this.bitField0_ & 16) == 16) {
                v1 += CodedOutputStream.computeMessageSize(5, this.delegateMethod_);
            }
            int v2 = v1 + this.unknownFields.size();
            this.memoizedSerializedSize = v2;
            return v2;
        }

        public JvmMethodSignature getSetter() {
            return this.setter_;
        }

        public JvmMethodSignature getSyntheticMethod() {
            return this.syntheticMethod_;
        }

        public boolean hasDelegateMethod() {
            return (this.bitField0_ & 16) == 16;
        }

        public boolean hasField() {
            return (this.bitField0_ & 1) == 1;
        }

        public boolean hasGetter() {
            return (this.bitField0_ & 4) == 4;
        }

        public boolean hasSetter() {
            return (this.bitField0_ & 8) == 8;
        }

        public boolean hasSyntheticMethod() {
            return (this.bitField0_ & 2) == 2;
        }

        private void initFields() {
            this.field_ = JvmFieldSignature.getDefaultInstance();
            this.syntheticMethod_ = JvmMethodSignature.getDefaultInstance();
            this.getter_ = JvmMethodSignature.getDefaultInstance();
            this.setter_ = JvmMethodSignature.getDefaultInstance();
            this.delegateMethod_ = JvmMethodSignature.getDefaultInstance();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            int v = this.memoizedIsInitialized;
            if(v == 1) {
                return true;
            }
            if(v == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmPropertySignature.Builder newBuilder() {
            return kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmPropertySignature.Builder.access$3200();
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmPropertySignature.Builder newBuilder(JvmPropertySignature jvmProtoBuf$JvmPropertySignature0) {
            return JvmPropertySignature.newBuilder().mergeFrom(jvmProtoBuf$JvmPropertySignature0);
        }

        public kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmPropertySignature.Builder newBuilderForType() {
            return JvmPropertySignature.newBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder newBuilderForType() {
            return this.newBuilderForType();
        }

        public kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmPropertySignature.Builder toBuilder() {
            return JvmPropertySignature.newBuilder(this);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder toBuilder() {
            return this.toBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream0) throws IOException {
            this.getSerializedSize();
            if((this.bitField0_ & 1) == 1) {
                codedOutputStream0.writeMessage(1, this.field_);
            }
            if((this.bitField0_ & 2) == 2) {
                codedOutputStream0.writeMessage(2, this.syntheticMethod_);
            }
            if((this.bitField0_ & 4) == 4) {
                codedOutputStream0.writeMessage(3, this.getter_);
            }
            if((this.bitField0_ & 8) == 8) {
                codedOutputStream0.writeMessage(4, this.setter_);
            }
            if((this.bitField0_ & 16) == 16) {
                codedOutputStream0.writeMessage(5, this.delegateMethod_);
            }
            codedOutputStream0.writeRawBytes(this.unknownFields);
        }
    }

    public static final class StringTableTypes extends GeneratedMessageLite implements JvmProtoBuf.StringTableTypesOrBuilder {
        public static final class kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Builder extends kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder implements JvmProtoBuf.StringTableTypesOrBuilder {
            private int bitField0_;
            private List localName_;
            private List record_;

            private kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Builder() {
                this.record_ = Collections.EMPTY_LIST;
                this.localName_ = Collections.EMPTY_LIST;
            }

            static kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Builder access$1200() {
                return kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Builder.create();
            }

            public StringTableTypes build() {
                StringTableTypes jvmProtoBuf$StringTableTypes0 = this.buildPartial();
                if(!jvmProtoBuf$StringTableTypes0.isInitialized()) {
                    throw kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Builder.newUninitializedMessageException(jvmProtoBuf$StringTableTypes0);
                }
                return jvmProtoBuf$StringTableTypes0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite$Builder
            public MessageLite build() {
                return this.build();
            }

            public StringTableTypes buildPartial() {
                StringTableTypes jvmProtoBuf$StringTableTypes0 = new StringTableTypes(this, null);
                if((this.bitField0_ & 1) == 1) {
                    this.record_ = Collections.unmodifiableList(this.record_);
                    this.bitField0_ &= -2;
                }
                jvmProtoBuf$StringTableTypes0.record_ = this.record_;
                if((this.bitField0_ & 2) == 2) {
                    this.localName_ = Collections.unmodifiableList(this.localName_);
                    this.bitField0_ &= -3;
                }
                jvmProtoBuf$StringTableTypes0.localName_ = this.localName_;
                return jvmProtoBuf$StringTableTypes0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public Object clone() throws CloneNotSupportedException {
                return this.clone();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Builder clone() {
                return kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Builder.create().mergeFrom(this.buildPartial());
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder clone() {
                return this.clone();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder clone() {
                return this.clone();
            }

            private static kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Builder create() {
                return new kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Builder();
            }

            private void ensureLocalNameIsMutable() {
                if((this.bitField0_ & 2) != 2) {
                    this.localName_ = new ArrayList(this.localName_);
                    this.bitField0_ |= 2;
                }
            }

            private void ensureRecordIsMutable() {
                if((this.bitField0_ & 1) != 1) {
                    this.record_ = new ArrayList(this.record_);
                    this.bitField0_ |= 1;
                }
            }

            public StringTableTypes getDefaultInstanceForType() {
                return StringTableTypes.getDefaultInstance();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public GeneratedMessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            private void maybeForceBuilderInitialization() {
            }

            public kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Builder mergeFrom(StringTableTypes jvmProtoBuf$StringTableTypes0) {
                if(jvmProtoBuf$StringTableTypes0 == StringTableTypes.getDefaultInstance()) {
                    return this;
                }
                if(!jvmProtoBuf$StringTableTypes0.record_.isEmpty()) {
                    if(this.record_.isEmpty()) {
                        this.record_ = jvmProtoBuf$StringTableTypes0.record_;
                        this.bitField0_ &= -2;
                    }
                    else {
                        this.ensureRecordIsMutable();
                        this.record_.addAll(jvmProtoBuf$StringTableTypes0.record_);
                    }
                }
                if(!jvmProtoBuf$StringTableTypes0.localName_.isEmpty()) {
                    if(this.localName_.isEmpty()) {
                        this.localName_ = jvmProtoBuf$StringTableTypes0.localName_;
                        this.bitField0_ &= -3;
                    }
                    else {
                        this.ensureLocalNameIsMutable();
                        this.localName_.addAll(jvmProtoBuf$StringTableTypes0.localName_);
                    }
                }
                this.setUnknownFields(this.getUnknownFields().concat(jvmProtoBuf$StringTableTypes0.unknownFields));
                return this;
            }

            public kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                StringTableTypes jvmProtoBuf$StringTableTypes1;
                StringTableTypes jvmProtoBuf$StringTableTypes0;
                try {
                    try {
                        jvmProtoBuf$StringTableTypes0 = null;
                        jvmProtoBuf$StringTableTypes1 = (StringTableTypes)StringTableTypes.PARSER.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                        goto label_13;
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        StringTableTypes jvmProtoBuf$StringTableTypes2 = (StringTableTypes)invalidProtocolBufferException0.getUnfinishedMessage();
                        try {
                            throw invalidProtocolBufferException0;
                        }
                        catch(Throwable throwable0) {
                            jvmProtoBuf$StringTableTypes0 = jvmProtoBuf$StringTableTypes2;
                        }
                    }
                }
                catch(Throwable throwable0) {
                }
                if(jvmProtoBuf$StringTableTypes0 != null) {
                    this.mergeFrom(jvmProtoBuf$StringTableTypes0);
                }
                throw throwable0;
            label_13:
                if(jvmProtoBuf$StringTableTypes1 != null) {
                    this.mergeFrom(jvmProtoBuf$StringTableTypes1);
                }
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedMessageLite0) {
                return this.mergeFrom(((StringTableTypes)generatedMessageLite0));
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
            public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            }
        }

        public static final class Record extends GeneratedMessageLite implements JvmProtoBuf.StringTableTypes.RecordOrBuilder {
            public static final class kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Record.Builder extends kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder implements JvmProtoBuf.StringTableTypes.RecordOrBuilder {
                private int bitField0_;
                private Operation operation_;
                private int predefinedIndex_;
                private int range_;
                private List replaceChar_;
                private Object string_;
                private List substringIndex_;

                private kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Record.Builder() {
                    this.range_ = 1;
                    this.string_ = "";
                    this.operation_ = Operation.NONE;
                    this.substringIndex_ = Collections.EMPTY_LIST;
                    this.replaceChar_ = Collections.EMPTY_LIST;
                }

                static kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Record.Builder access$200() {
                    return kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Record.Builder.create();
                }

                public Record build() {
                    Record jvmProtoBuf$StringTableTypes$Record0 = this.buildPartial();
                    if(!jvmProtoBuf$StringTableTypes$Record0.isInitialized()) {
                        throw kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Record.Builder.newUninitializedMessageException(jvmProtoBuf$StringTableTypes$Record0);
                    }
                    return jvmProtoBuf$StringTableTypes$Record0;
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite$Builder
                public MessageLite build() {
                    return this.build();
                }

                public Record buildPartial() {
                    Record jvmProtoBuf$StringTableTypes$Record0 = new Record(this, null);
                    int v = this.bitField0_;
                    int v1 = (v & 1) == 1 ? 1 : 0;
                    jvmProtoBuf$StringTableTypes$Record0.range_ = this.range_;
                    if((v & 2) == 2) {
                        v1 |= 2;
                    }
                    jvmProtoBuf$StringTableTypes$Record0.predefinedIndex_ = this.predefinedIndex_;
                    if((v & 4) == 4) {
                        v1 |= 4;
                    }
                    jvmProtoBuf$StringTableTypes$Record0.string_ = this.string_;
                    if((v & 8) == 8) {
                        v1 |= 8;
                    }
                    jvmProtoBuf$StringTableTypes$Record0.operation_ = this.operation_;
                    if((this.bitField0_ & 16) == 16) {
                        this.substringIndex_ = Collections.unmodifiableList(this.substringIndex_);
                        this.bitField0_ &= -17;
                    }
                    jvmProtoBuf$StringTableTypes$Record0.substringIndex_ = this.substringIndex_;
                    if((this.bitField0_ & 0x20) == 0x20) {
                        this.replaceChar_ = Collections.unmodifiableList(this.replaceChar_);
                        this.bitField0_ &= -33;
                    }
                    jvmProtoBuf$StringTableTypes$Record0.replaceChar_ = this.replaceChar_;
                    jvmProtoBuf$StringTableTypes$Record0.bitField0_ = v1;
                    return jvmProtoBuf$StringTableTypes$Record0;
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
                public Object clone() throws CloneNotSupportedException {
                    return this.clone();
                }

                public kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Record.Builder clone() {
                    return kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Record.Builder.create().mergeFrom(this.buildPartial());
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
                public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder clone() {
                    return this.clone();
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
                public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder clone() {
                    return this.clone();
                }

                private static kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Record.Builder create() {
                    return new kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Record.Builder();
                }

                private void ensureReplaceCharIsMutable() {
                    if((this.bitField0_ & 0x20) != 0x20) {
                        this.replaceChar_ = new ArrayList(this.replaceChar_);
                        this.bitField0_ |= 0x20;
                    }
                }

                private void ensureSubstringIndexIsMutable() {
                    if((this.bitField0_ & 16) != 16) {
                        this.substringIndex_ = new ArrayList(this.substringIndex_);
                        this.bitField0_ |= 16;
                    }
                }

                public Record getDefaultInstanceForType() {
                    return Record.getDefaultInstance();
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
                public GeneratedMessageLite getDefaultInstanceForType() {
                    return this.getDefaultInstanceForType();
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder, kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
                public MessageLite getDefaultInstanceForType() {
                    return this.getDefaultInstanceForType();
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
                public final boolean isInitialized() {
                    return true;
                }

                private void maybeForceBuilderInitialization() {
                }

                public kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Record.Builder mergeFrom(Record jvmProtoBuf$StringTableTypes$Record0) {
                    if(jvmProtoBuf$StringTableTypes$Record0 == Record.getDefaultInstance()) {
                        return this;
                    }
                    if(jvmProtoBuf$StringTableTypes$Record0.hasRange()) {
                        this.setRange(jvmProtoBuf$StringTableTypes$Record0.getRange());
                    }
                    if(jvmProtoBuf$StringTableTypes$Record0.hasPredefinedIndex()) {
                        this.setPredefinedIndex(jvmProtoBuf$StringTableTypes$Record0.getPredefinedIndex());
                    }
                    if(jvmProtoBuf$StringTableTypes$Record0.hasString()) {
                        this.bitField0_ |= 4;
                        this.string_ = jvmProtoBuf$StringTableTypes$Record0.string_;
                    }
                    if(jvmProtoBuf$StringTableTypes$Record0.hasOperation()) {
                        this.setOperation(jvmProtoBuf$StringTableTypes$Record0.getOperation());
                    }
                    if(!jvmProtoBuf$StringTableTypes$Record0.substringIndex_.isEmpty()) {
                        if(this.substringIndex_.isEmpty()) {
                            this.substringIndex_ = jvmProtoBuf$StringTableTypes$Record0.substringIndex_;
                            this.bitField0_ &= -17;
                        }
                        else {
                            this.ensureSubstringIndexIsMutable();
                            this.substringIndex_.addAll(jvmProtoBuf$StringTableTypes$Record0.substringIndex_);
                        }
                    }
                    if(!jvmProtoBuf$StringTableTypes$Record0.replaceChar_.isEmpty()) {
                        if(this.replaceChar_.isEmpty()) {
                            this.replaceChar_ = jvmProtoBuf$StringTableTypes$Record0.replaceChar_;
                            this.bitField0_ &= -33;
                        }
                        else {
                            this.ensureReplaceCharIsMutable();
                            this.replaceChar_.addAll(jvmProtoBuf$StringTableTypes$Record0.replaceChar_);
                        }
                    }
                    this.setUnknownFields(this.getUnknownFields().concat(jvmProtoBuf$StringTableTypes$Record0.unknownFields));
                    return this;
                }

                public kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Record.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                    Record jvmProtoBuf$StringTableTypes$Record1;
                    Record jvmProtoBuf$StringTableTypes$Record0;
                    try {
                        try {
                            jvmProtoBuf$StringTableTypes$Record0 = null;
                            jvmProtoBuf$StringTableTypes$Record1 = (Record)Record.PARSER.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                            goto label_13;
                        }
                        catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                            Record jvmProtoBuf$StringTableTypes$Record2 = (Record)invalidProtocolBufferException0.getUnfinishedMessage();
                            try {
                                throw invalidProtocolBufferException0;
                            }
                            catch(Throwable throwable0) {
                                jvmProtoBuf$StringTableTypes$Record0 = jvmProtoBuf$StringTableTypes$Record2;
                            }
                        }
                    }
                    catch(Throwable throwable0) {
                    }
                    if(jvmProtoBuf$StringTableTypes$Record0 != null) {
                        this.mergeFrom(jvmProtoBuf$StringTableTypes$Record0);
                    }
                    throw throwable0;
                label_13:
                    if(jvmProtoBuf$StringTableTypes$Record1 != null) {
                        this.mergeFrom(jvmProtoBuf$StringTableTypes$Record1);
                    }
                    return this;
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
                public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                    return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
                public kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedMessageLite0) {
                    return this.mergeFrom(((Record)generatedMessageLite0));
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
                public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
                    return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
                }

                public kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Record.Builder setOperation(Operation jvmProtoBuf$StringTableTypes$Record$Operation0) {
                    jvmProtoBuf$StringTableTypes$Record$Operation0.getClass();
                    this.bitField0_ |= 8;
                    this.operation_ = jvmProtoBuf$StringTableTypes$Record$Operation0;
                    return this;
                }

                public kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Record.Builder setPredefinedIndex(int v) {
                    this.bitField0_ |= 2;
                    this.predefinedIndex_ = v;
                    return this;
                }

                public kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Record.Builder setRange(int v) {
                    this.bitField0_ |= 1;
                    this.range_ = v;
                    return this;
                }
            }

            public static enum Operation implements EnumLite {
                NONE(0, 0),
                INTERNAL_TO_CLASS_ID(1, 1),
                DESC_TO_CLASS_ID(2, 2);

                private static EnumLiteMap internalValueMap;
                private final int value;

                static {
                    Operation.internalValueMap = new EnumLiteMap() {
                        public Operation findValueByNumber(int v) {
                            return Operation.valueOf(v);
                        }

                        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Internal$EnumLiteMap
                        public EnumLite findValueByNumber(int v) {
                            return this.findValueByNumber(v);
                        }
                    };
                }

                private Operation(int v1, int v2) {
                    this.value = v2;
                }

                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Internal$EnumLite
                public final int getNumber() {
                    return this.value;
                }

                public static Operation valueOf(int v) {
                    switch(v) {
                        case 0: {
                            return Operation.NONE;
                        }
                        case 1: {
                            return Operation.INTERNAL_TO_CLASS_ID;
                        }
                        case 2: {
                            return Operation.DESC_TO_CLASS_ID;
                        }
                        default: {
                            return null;
                        }
                    }
                }
            }

            public static Parser PARSER;
            private int bitField0_;
            private static final Record defaultInstance;
            private byte memoizedIsInitialized;
            private int memoizedSerializedSize;
            private Operation operation_;
            private int predefinedIndex_;
            private int range_;
            private int replaceCharMemoizedSerializedSize;
            private List replaceChar_;
            private Object string_;
            private int substringIndexMemoizedSerializedSize;
            private List substringIndex_;
            private final ByteString unknownFields;

            static {
                Record.PARSER = new AbstractParser() {
                    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Parser
                    public Object parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                        return this.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                    }

                    public Record parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                        return new Record(codedInputStream0, extensionRegistryLite0, null);
                    }
                };
                Record jvmProtoBuf$StringTableTypes$Record0 = new Record(true);
                Record.defaultInstance = jvmProtoBuf$StringTableTypes$Record0;
                jvmProtoBuf$StringTableTypes$Record0.initFields();
            }

            private Record(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                this.substringIndexMemoizedSerializedSize = -1;
                this.replaceCharMemoizedSerializedSize = -1;
                this.memoizedIsInitialized = -1;
                this.memoizedSerializedSize = -1;
                this.initFields();
                Output byteString$Output0 = ByteString.newOutput();
                CodedOutputStream codedOutputStream0 = CodedOutputStream.newInstance(byteString$Output0, 1);
                int v = 0;
                boolean z = false;
                while(!z) {
                    try {
                        try {
                            int v1 = codedInputStream0.readTag();
                            if(v1 != 0) {
                                switch(v1) {
                                    case 8: {
                                        this.bitField0_ |= 1;
                                        this.range_ = codedInputStream0.readInt32();
                                        continue;
                                    }
                                    case 16: {
                                        this.bitField0_ |= 2;
                                        this.predefinedIndex_ = codedInputStream0.readInt32();
                                        continue;
                                    }
                                    case 24: {
                                        int v2 = codedInputStream0.readEnum();
                                        Operation jvmProtoBuf$StringTableTypes$Record$Operation0 = Operation.valueOf(v2);
                                        if(jvmProtoBuf$StringTableTypes$Record$Operation0 == null) {
                                            codedOutputStream0.writeRawVarint32(24);
                                            codedOutputStream0.writeRawVarint32(v2);
                                        }
                                        else {
                                            this.bitField0_ |= 8;
                                            this.operation_ = jvmProtoBuf$StringTableTypes$Record$Operation0;
                                        }
                                        continue;
                                    }
                                    case 0x20: {
                                        if((v & 16) != 16) {
                                            this.substringIndex_ = new ArrayList();
                                            v |= 16;
                                        }
                                        this.substringIndex_.add(codedInputStream0.readInt32());
                                        continue;
                                    }
                                    case 34: {
                                        int v3 = codedInputStream0.pushLimit(codedInputStream0.readRawVarint32());
                                        if((v & 16) != 16 && codedInputStream0.getBytesUntilLimit() > 0) {
                                            this.substringIndex_ = new ArrayList();
                                            v |= 16;
                                        }
                                        while(codedInputStream0.getBytesUntilLimit() > 0) {
                                            this.substringIndex_.add(codedInputStream0.readInt32());
                                        }
                                        codedInputStream0.popLimit(v3);
                                        continue;
                                    }
                                    case 40: {
                                        if((v & 0x20) != 0x20) {
                                            this.replaceChar_ = new ArrayList();
                                            v |= 0x20;
                                        }
                                        this.replaceChar_.add(codedInputStream0.readInt32());
                                        continue;
                                    }
                                    case 42: {
                                        int v4 = codedInputStream0.pushLimit(codedInputStream0.readRawVarint32());
                                        if((v & 0x20) != 0x20 && codedInputStream0.getBytesUntilLimit() > 0) {
                                            this.replaceChar_ = new ArrayList();
                                            v |= 0x20;
                                        }
                                        while(codedInputStream0.getBytesUntilLimit() > 0) {
                                            this.replaceChar_.add(codedInputStream0.readInt32());
                                        }
                                        codedInputStream0.popLimit(v4);
                                        continue;
                                    }
                                    case 50: {
                                        ByteString byteString0 = codedInputStream0.readBytes();
                                        this.bitField0_ |= 4;
                                        this.string_ = byteString0;
                                        continue;
                                    }
                                    default: {
                                        if(this.parseUnknownField(codedInputStream0, codedOutputStream0, extensionRegistryLite0, v1)) {
                                            continue;
                                        }
                                    }
                                }
                            }
                            z = true;
                        }
                        catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                            throw invalidProtocolBufferException0.setUnfinishedMessage(this);
                        }
                        catch(IOException iOException0) {
                            throw new InvalidProtocolBufferException(iOException0.getMessage()).setUnfinishedMessage(this);
                        }
                    }
                    catch(Throwable throwable0) {
                        if((v & 16) == 16) {
                            this.substringIndex_ = Collections.unmodifiableList(this.substringIndex_);
                        }
                        if((v & 0x20) == 0x20) {
                            this.replaceChar_ = Collections.unmodifiableList(this.replaceChar_);
                        }
                        try {
                            codedOutputStream0.flush();
                        }
                        catch(IOException throwable1) {
                            this.unknownFields = byteString$Output0.toByteString();
                            throw throwable1;
                        }
                        catch(Throwable unused_ex) {
                        }
                        this.unknownFields = byteString$Output0.toByteString();
                        throw throwable0;
                    }
                }
                if((v & 16) == 16) {
                    this.substringIndex_ = Collections.unmodifiableList(this.substringIndex_);
                }
                if((v & 0x20) == 0x20) {
                    this.replaceChar_ = Collections.unmodifiableList(this.replaceChar_);
                }
                try {
                    codedOutputStream0.flush();
                }
                catch(IOException throwable2) {
                    this.unknownFields = byteString$Output0.toByteString();
                    throw throwable2;
                }
                catch(Throwable unused_ex) {
                }
                this.unknownFields = byteString$Output0.toByteString();
            }

            Record(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0, kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.1 jvmProtoBuf$10) throws InvalidProtocolBufferException {
                this(codedInputStream0, extensionRegistryLite0);
            }

            private Record(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder generatedMessageLite$Builder0) {
                super(generatedMessageLite$Builder0);
                this.substringIndexMemoizedSerializedSize = -1;
                this.replaceCharMemoizedSerializedSize = -1;
                this.memoizedIsInitialized = -1;
                this.memoizedSerializedSize = -1;
                this.unknownFields = generatedMessageLite$Builder0.getUnknownFields();
            }

            Record(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder generatedMessageLite$Builder0, kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.1 jvmProtoBuf$10) {
                this(generatedMessageLite$Builder0);
            }

            private Record(boolean z) {
                this.substringIndexMemoizedSerializedSize = -1;
                this.replaceCharMemoizedSerializedSize = -1;
                this.memoizedIsInitialized = -1;
                this.memoizedSerializedSize = -1;
                this.unknownFields = ByteString.EMPTY;
            }

            public static Record getDefaultInstance() {
                return Record.defaultInstance;
            }

            public Record getDefaultInstanceForType() {
                return Record.defaultInstance;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public MessageLite getDefaultInstanceForType() {
                return this.getDefaultInstanceForType();
            }

            public Operation getOperation() {
                return this.operation_;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite
            public Parser getParserForType() {
                return Record.PARSER;
            }

            public int getPredefinedIndex() {
                return this.predefinedIndex_;
            }

            public int getRange() {
                return this.range_;
            }

            public int getReplaceCharCount() {
                return this.replaceChar_.size();
            }

            public List getReplaceCharList() {
                return this.replaceChar_;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
            public int getSerializedSize() {
                int v1 = this.memoizedSerializedSize;
                if(v1 != -1) {
                    return v1;
                }
                int v2 = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeInt32Size(1, this.range_) : 0;
                if((this.bitField0_ & 2) == 2) {
                    v2 += CodedOutputStream.computeInt32Size(2, this.predefinedIndex_);
                }
                if((this.bitField0_ & 8) == 8) {
                    v2 += CodedOutputStream.computeEnumSize(3, this.operation_.getNumber());
                }
                int v4 = 0;
                for(int v3 = 0; v3 < this.substringIndex_.size(); ++v3) {
                    v4 += CodedOutputStream.computeInt32SizeNoTag(((int)(((Integer)this.substringIndex_.get(v3)))));
                }
                int v5 = this.getSubstringIndexList().isEmpty() ? v2 + v4 : v2 + v4 + 1 + CodedOutputStream.computeInt32SizeNoTag(v4);
                this.substringIndexMemoizedSerializedSize = v4;
                int v6 = 0;
                for(int v = 0; v < this.replaceChar_.size(); ++v) {
                    v6 += CodedOutputStream.computeInt32SizeNoTag(((int)(((Integer)this.replaceChar_.get(v)))));
                }
                int v7 = this.getReplaceCharList().isEmpty() ? v5 + v6 : v5 + v6 + 1 + CodedOutputStream.computeInt32SizeNoTag(v6);
                this.replaceCharMemoizedSerializedSize = v6;
                if((this.bitField0_ & 4) == 4) {
                    v7 += CodedOutputStream.computeBytesSize(6, this.getStringBytes());
                }
                int v8 = v7 + this.unknownFields.size();
                this.memoizedSerializedSize = v8;
                return v8;
            }

            public String getString() {
                Object object0 = this.string_;
                if(object0 instanceof String) {
                    return (String)object0;
                }
                String s = ((ByteString)object0).toStringUtf8();
                if(((ByteString)object0).isValidUtf8()) {
                    this.string_ = s;
                }
                return s;
            }

            public ByteString getStringBytes() {
                Object object0 = this.string_;
                if(object0 instanceof String) {
                    ByteString byteString0 = ByteString.copyFromUtf8(((String)object0));
                    this.string_ = byteString0;
                    return byteString0;
                }
                return (ByteString)object0;
            }

            public int getSubstringIndexCount() {
                return this.substringIndex_.size();
            }

            public List getSubstringIndexList() {
                return this.substringIndex_;
            }

            public boolean hasOperation() {
                return (this.bitField0_ & 8) == 8;
            }

            public boolean hasPredefinedIndex() {
                return (this.bitField0_ & 2) == 2;
            }

            public boolean hasRange() {
                return (this.bitField0_ & 1) == 1;
            }

            public boolean hasString() {
                return (this.bitField0_ & 4) == 4;
            }

            private void initFields() {
                this.range_ = 1;
                this.predefinedIndex_ = 0;
                this.string_ = "";
                this.operation_ = Operation.NONE;
                this.substringIndex_ = Collections.EMPTY_LIST;
                this.replaceChar_ = Collections.EMPTY_LIST;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                int v = this.memoizedIsInitialized;
                if(v == 1) {
                    return true;
                }
                if(v == 0) {
                    return false;
                }
                this.memoizedIsInitialized = 1;
                return true;
            }

            public static kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Record.Builder newBuilder() {
                return kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Record.Builder.access$200();
            }

            public static kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Record.Builder newBuilder(Record jvmProtoBuf$StringTableTypes$Record0) {
                return Record.newBuilder().mergeFrom(jvmProtoBuf$StringTableTypes$Record0);
            }

            public kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Record.Builder newBuilderForType() {
                return Record.newBuilder();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
            public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder newBuilderForType() {
                return this.newBuilderForType();
            }

            public kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Record.Builder toBuilder() {
                return Record.newBuilder(this);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
            public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder toBuilder() {
                return this.toBuilder();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
            public void writeTo(CodedOutputStream codedOutputStream0) throws IOException {
                this.getSerializedSize();
                if((this.bitField0_ & 1) == 1) {
                    codedOutputStream0.writeInt32(1, this.range_);
                }
                if((this.bitField0_ & 2) == 2) {
                    codedOutputStream0.writeInt32(2, this.predefinedIndex_);
                }
                if((this.bitField0_ & 8) == 8) {
                    codedOutputStream0.writeEnum(3, this.operation_.getNumber());
                }
                if(this.getSubstringIndexList().size() > 0) {
                    codedOutputStream0.writeRawVarint32(34);
                    codedOutputStream0.writeRawVarint32(this.substringIndexMemoizedSerializedSize);
                }
                for(int v1 = 0; v1 < this.substringIndex_.size(); ++v1) {
                    codedOutputStream0.writeInt32NoTag(((int)(((Integer)this.substringIndex_.get(v1)))));
                }
                if(this.getReplaceCharList().size() > 0) {
                    codedOutputStream0.writeRawVarint32(42);
                    codedOutputStream0.writeRawVarint32(this.replaceCharMemoizedSerializedSize);
                }
                for(int v = 0; v < this.replaceChar_.size(); ++v) {
                    codedOutputStream0.writeInt32NoTag(((int)(((Integer)this.replaceChar_.get(v)))));
                }
                if((this.bitField0_ & 4) == 4) {
                    codedOutputStream0.writeBytes(6, this.getStringBytes());
                }
                codedOutputStream0.writeRawBytes(this.unknownFields);
            }
        }

        public static Parser PARSER;
        private static final StringTableTypes defaultInstance;
        private int localNameMemoizedSerializedSize;
        private List localName_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private List record_;
        private final ByteString unknownFields;

        static {
            StringTableTypes.PARSER = new AbstractParser() {
                @Override  // kotlin.reflect.jvm.internal.impl.protobuf.Parser
                public Object parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return this.parsePartialFrom(codedInputStream0, extensionRegistryLite0);
                }

                public StringTableTypes parsePartialFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
                    return new StringTableTypes(codedInputStream0, extensionRegistryLite0, null);
                }
            };
            StringTableTypes jvmProtoBuf$StringTableTypes0 = new StringTableTypes(true);
            StringTableTypes.defaultInstance = jvmProtoBuf$StringTableTypes0;
            jvmProtoBuf$StringTableTypes0.initFields();
        }

        private StringTableTypes(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            this.localNameMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.initFields();
            Output byteString$Output0 = ByteString.newOutput();
            CodedOutputStream codedOutputStream0 = CodedOutputStream.newInstance(byteString$Output0, 1);
            int v = 0;
            boolean z = false;
            while(!z) {
                try {
                    try {
                        int v1 = codedInputStream0.readTag();
                        switch(v1) {
                            case 0: {
                            label_13:
                                z = true;
                                continue;
                            }
                            case 10: {
                                if((v & 1) != 1) {
                                    this.record_ = new ArrayList();
                                    v |= 1;
                                }
                                this.record_.add(codedInputStream0.readMessage(Record.PARSER, extensionRegistryLite0));
                                continue;
                            }
                            case 40: {
                                if((v & 2) != 2) {
                                    this.localName_ = new ArrayList();
                                    v |= 2;
                                }
                                this.localName_.add(codedInputStream0.readInt32());
                                continue;
                            }
                            case 42: {
                                int v2 = codedInputStream0.pushLimit(codedInputStream0.readRawVarint32());
                                if((v & 2) != 2 && codedInputStream0.getBytesUntilLimit() > 0) {
                                    this.localName_ = new ArrayList();
                                    v |= 2;
                                }
                                while(codedInputStream0.getBytesUntilLimit() > 0) {
                                    this.localName_.add(codedInputStream0.readInt32());
                                }
                                codedInputStream0.popLimit(v2);
                                break;
                            }
                            default: {
                                if(!this.parseUnknownField(codedInputStream0, codedOutputStream0, extensionRegistryLite0, v1)) {
                                    goto label_13;
                                }
                            }
                        }
                    }
                    catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                        throw invalidProtocolBufferException0.setUnfinishedMessage(this);
                    }
                    catch(IOException iOException0) {
                        throw new InvalidProtocolBufferException(iOException0.getMessage()).setUnfinishedMessage(this);
                    }
                }
                catch(Throwable throwable0) {
                    if((v & 1) == 1) {
                        this.record_ = Collections.unmodifiableList(this.record_);
                    }
                    if((v & 2) == 2) {
                        this.localName_ = Collections.unmodifiableList(this.localName_);
                    }
                    try {
                        codedOutputStream0.flush();
                    }
                    catch(IOException throwable1) {
                        this.unknownFields = byteString$Output0.toByteString();
                        throw throwable1;
                    }
                    catch(Throwable unused_ex) {
                    }
                    this.unknownFields = byteString$Output0.toByteString();
                    throw throwable0;
                }
            }
            if((v & 1) == 1) {
                this.record_ = Collections.unmodifiableList(this.record_);
            }
            if((v & 2) == 2) {
                this.localName_ = Collections.unmodifiableList(this.localName_);
            }
            try {
                codedOutputStream0.flush();
            }
            catch(IOException throwable2) {
                this.unknownFields = byteString$Output0.toByteString();
                throw throwable2;
            }
            catch(Throwable unused_ex) {
            }
            this.unknownFields = byteString$Output0.toByteString();
        }

        StringTableTypes(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0, kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.1 jvmProtoBuf$10) throws InvalidProtocolBufferException {
            this(codedInputStream0, extensionRegistryLite0);
        }

        private StringTableTypes(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder generatedMessageLite$Builder0) {
            super(generatedMessageLite$Builder0);
            this.localNameMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = generatedMessageLite$Builder0.getUnknownFields();
        }

        StringTableTypes(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.Builder generatedMessageLite$Builder0, kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.1 jvmProtoBuf$10) {
            this(generatedMessageLite$Builder0);
        }

        private StringTableTypes(boolean z) {
            this.localNameMemoizedSerializedSize = -1;
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = ByteString.EMPTY;
        }

        public static StringTableTypes getDefaultInstance() {
            return StringTableTypes.defaultInstance;
        }

        public StringTableTypes getDefaultInstanceForType() {
            return StringTableTypes.defaultInstance;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return this.getDefaultInstanceForType();
        }

        public List getLocalNameList() {
            return this.localName_;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite
        public Parser getParserForType() {
            return StringTableTypes.PARSER;
        }

        public List getRecordList() {
            return this.record_;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public int getSerializedSize() {
            int v = this.memoizedSerializedSize;
            if(v != -1) {
                return v;
            }
            int v3 = 0;
            for(int v2 = 0; v2 < this.record_.size(); ++v2) {
                v3 += CodedOutputStream.computeMessageSize(1, ((MessageLite)this.record_.get(v2)));
            }
            int v4 = 0;
            for(int v1 = 0; v1 < this.localName_.size(); ++v1) {
                v4 += CodedOutputStream.computeInt32SizeNoTag(((int)(((Integer)this.localName_.get(v1)))));
            }
            this.localNameMemoizedSerializedSize = v4;
            int v5 = (this.getLocalNameList().isEmpty() ? v3 + v4 : v3 + v4 + 1 + CodedOutputStream.computeInt32SizeNoTag(v4)) + this.unknownFields.size();
            this.memoizedSerializedSize = v5;
            return v5;
        }

        private void initFields() {
            this.record_ = Collections.EMPTY_LIST;
            this.localName_ = Collections.EMPTY_LIST;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            int v = this.memoizedIsInitialized;
            if(v == 1) {
                return true;
            }
            if(v == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Builder newBuilder() {
            return kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Builder.access$1200();
        }

        public static kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Builder newBuilder(StringTableTypes jvmProtoBuf$StringTableTypes0) {
            return StringTableTypes.newBuilder().mergeFrom(jvmProtoBuf$StringTableTypes0);
        }

        public kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Builder newBuilderForType() {
            return StringTableTypes.newBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder newBuilderForType() {
            return this.newBuilderForType();
        }

        public static StringTableTypes parseDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            return (StringTableTypes)StringTableTypes.PARSER.parseDelimitedFrom(inputStream0, extensionRegistryLite0);
        }

        public kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Builder toBuilder() {
            return StringTableTypes.newBuilder(this);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder toBuilder() {
            return this.toBuilder();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream0) throws IOException {
            this.getSerializedSize();
            for(int v1 = 0; v1 < this.record_.size(); ++v1) {
                codedOutputStream0.writeMessage(1, ((MessageLite)this.record_.get(v1)));
            }
            if(this.getLocalNameList().size() > 0) {
                codedOutputStream0.writeRawVarint32(42);
                codedOutputStream0.writeRawVarint32(this.localNameMemoizedSerializedSize);
            }
            for(int v = 0; v < this.localName_.size(); ++v) {
                codedOutputStream0.writeInt32NoTag(((int)(((Integer)this.localName_.get(v)))));
            }
            codedOutputStream0.writeRawBytes(this.unknownFields);
        }
    }

    public static final GeneratedExtension anonymousObjectOriginName;
    public static final GeneratedExtension classLocalVariable;
    public static final GeneratedExtension classModuleName;
    public static final GeneratedExtension constructorSignature;
    public static final GeneratedExtension flags;
    public static final GeneratedExtension isRaw;
    public static final GeneratedExtension jvmClassFlags;
    public static final GeneratedExtension lambdaClassOriginName;
    public static final GeneratedExtension methodSignature;
    public static final GeneratedExtension packageLocalVariable;
    public static final GeneratedExtension packageModuleName;
    public static final GeneratedExtension propertySignature;
    public static final GeneratedExtension typeAnnotation;
    public static final GeneratedExtension typeParameterAnnotation;

    static {
        JvmProtoBuf.constructorSignature = GeneratedMessageLite.newSingularGeneratedExtension(Constructor.getDefaultInstance(), JvmMethodSignature.getDefaultInstance(), JvmMethodSignature.getDefaultInstance(), null, 100, FieldType.MESSAGE, JvmMethodSignature.class);
        JvmProtoBuf.methodSignature = GeneratedMessageLite.newSingularGeneratedExtension(Function.getDefaultInstance(), JvmMethodSignature.getDefaultInstance(), JvmMethodSignature.getDefaultInstance(), null, 100, FieldType.MESSAGE, JvmMethodSignature.class);
        JvmProtoBuf.lambdaClassOriginName = GeneratedMessageLite.newSingularGeneratedExtension(Function.getDefaultInstance(), 0, null, null, 101, FieldType.INT32, Integer.class);
        JvmProtoBuf.propertySignature = GeneratedMessageLite.newSingularGeneratedExtension(Property.getDefaultInstance(), JvmPropertySignature.getDefaultInstance(), JvmPropertySignature.getDefaultInstance(), null, 100, FieldType.MESSAGE, JvmPropertySignature.class);
        JvmProtoBuf.flags = GeneratedMessageLite.newSingularGeneratedExtension(Property.getDefaultInstance(), 0, null, null, 101, FieldType.INT32, Integer.class);
        JvmProtoBuf.typeAnnotation = GeneratedMessageLite.newRepeatedGeneratedExtension(Type.getDefaultInstance(), Annotation.getDefaultInstance(), null, 100, FieldType.MESSAGE, false, Annotation.class);
        JvmProtoBuf.isRaw = GeneratedMessageLite.newSingularGeneratedExtension(Type.getDefaultInstance(), Boolean.FALSE, null, null, 101, FieldType.BOOL, Boolean.class);
        JvmProtoBuf.typeParameterAnnotation = GeneratedMessageLite.newRepeatedGeneratedExtension(TypeParameter.getDefaultInstance(), Annotation.getDefaultInstance(), null, 100, FieldType.MESSAGE, false, Annotation.class);
        JvmProtoBuf.classModuleName = GeneratedMessageLite.newSingularGeneratedExtension(Class.getDefaultInstance(), 0, null, null, 101, FieldType.INT32, Integer.class);
        JvmProtoBuf.classLocalVariable = GeneratedMessageLite.newRepeatedGeneratedExtension(Class.getDefaultInstance(), Property.getDefaultInstance(), null, 102, FieldType.MESSAGE, false, Property.class);
        JvmProtoBuf.anonymousObjectOriginName = GeneratedMessageLite.newSingularGeneratedExtension(Class.getDefaultInstance(), 0, null, null, 103, FieldType.INT32, Integer.class);
        JvmProtoBuf.jvmClassFlags = GeneratedMessageLite.newSingularGeneratedExtension(Class.getDefaultInstance(), 0, null, null, 104, FieldType.INT32, Integer.class);
        JvmProtoBuf.packageModuleName = GeneratedMessageLite.newSingularGeneratedExtension(Package.getDefaultInstance(), 0, null, null, 101, FieldType.INT32, Integer.class);
        JvmProtoBuf.packageLocalVariable = GeneratedMessageLite.newRepeatedGeneratedExtension(Package.getDefaultInstance(), Property.getDefaultInstance(), null, 102, FieldType.MESSAGE, false, Property.class);
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite0) {
        extensionRegistryLite0.add(JvmProtoBuf.constructorSignature);
        extensionRegistryLite0.add(JvmProtoBuf.methodSignature);
        extensionRegistryLite0.add(JvmProtoBuf.lambdaClassOriginName);
        extensionRegistryLite0.add(JvmProtoBuf.propertySignature);
        extensionRegistryLite0.add(JvmProtoBuf.flags);
        extensionRegistryLite0.add(JvmProtoBuf.typeAnnotation);
        extensionRegistryLite0.add(JvmProtoBuf.isRaw);
        extensionRegistryLite0.add(JvmProtoBuf.typeParameterAnnotation);
        extensionRegistryLite0.add(JvmProtoBuf.classModuleName);
        extensionRegistryLite0.add(JvmProtoBuf.classLocalVariable);
        extensionRegistryLite0.add(JvmProtoBuf.anonymousObjectOriginName);
        extensionRegistryLite0.add(JvmProtoBuf.jvmClassFlags);
        extensionRegistryLite0.add(JvmProtoBuf.packageModuleName);
        extensionRegistryLite0.add(JvmProtoBuf.packageLocalVariable);
    }

    class kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.1 {
    }

}

