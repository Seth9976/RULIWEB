package kotlin.reflect.jvm.internal.impl.protobuf;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public abstract class GeneratedMessageLite extends AbstractMessageLite implements Serializable {
    public static abstract class Builder extends kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder {
        private ByteString unknownFields;

        protected Builder() {
            this.unknownFields = ByteString.EMPTY;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
        public Object clone() throws CloneNotSupportedException {
            return this.clone();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite$Builder
        public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder clone() {
            return this.clone();
        }

        public Builder clone() {
            throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
        }

        public abstract GeneratedMessageLite getDefaultInstanceForType();

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLiteOrBuilder
        public MessageLite getDefaultInstanceForType() {
            return this.getDefaultInstanceForType();
        }

        public final ByteString getUnknownFields() {
            return this.unknownFields;
        }

        public abstract Builder mergeFrom(GeneratedMessageLite arg1);

        public final Builder setUnknownFields(ByteString byteString0) {
            this.unknownFields = byteString0;
            return this;
        }
    }

    public static abstract class ExtendableBuilder extends Builder implements ExtendableMessageOrBuilder {
        private FieldSet extensions;
        private boolean extensionsIsMutable;

        protected ExtendableBuilder() {
            this.extensions = FieldSet.emptySet();
        }

        private FieldSet buildExtensions() {
            this.extensions.makeImmutable();
            this.extensionsIsMutable = false;
            return this.extensions;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
        public Object clone() throws CloneNotSupportedException {
            return this.clone();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
        public kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder clone() {
            return this.clone();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite$Builder
        public Builder clone() {
            return this.clone();
        }

        public ExtendableBuilder clone() {
            throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
        }

        private void ensureExtensionsIsMutable() {
            if(!this.extensionsIsMutable) {
                this.extensions = this.extensions.clone();
                this.extensionsIsMutable = true;
            }
        }

        protected boolean extensionsAreInitialized() {
            return this.extensions.isInitialized();
        }

        protected final void mergeExtensionFields(ExtendableMessage generatedMessageLite$ExtendableMessage0) {
            this.ensureExtensionsIsMutable();
            this.extensions.mergeFrom(ExtendableMessage.access$200(generatedMessageLite$ExtendableMessage0));
        }
    }

    public static abstract class ExtendableMessage extends GeneratedMessageLite implements ExtendableMessageOrBuilder {
        public class ExtensionWriter {
            private final Iterator iter;
            private final boolean messageSetWireFormat;
            private Map.Entry next;

            private ExtensionWriter(boolean z) {
                Iterator iterator0 = generatedMessageLite$ExtendableMessage0.extensions.iterator();
                this.iter = iterator0;
                if(iterator0.hasNext()) {
                    Object object0 = iterator0.next();
                    this.next = (Map.Entry)object0;
                }
                this.messageSetWireFormat = z;
            }

            ExtensionWriter(boolean z, kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.1 generatedMessageLite$10) {
                this(z);
            }

            public void writeUntil(int v, CodedOutputStream codedOutputStream0) throws IOException {
                while(this.next != null && ((ExtensionDescriptor)this.next.getKey()).getNumber() < v) {
                    ExtensionDescriptor generatedMessageLite$ExtensionDescriptor0 = (ExtensionDescriptor)this.next.getKey();
                    if(!this.messageSetWireFormat || generatedMessageLite$ExtensionDescriptor0.getLiteJavaType() != JavaType.MESSAGE || generatedMessageLite$ExtensionDescriptor0.isRepeated()) {
                        FieldSet.writeField(generatedMessageLite$ExtensionDescriptor0, this.next.getValue(), codedOutputStream0);
                    }
                    else {
                        codedOutputStream0.writeMessageSetExtension(generatedMessageLite$ExtensionDescriptor0.getNumber(), ((MessageLite)this.next.getValue()));
                    }
                    if(this.iter.hasNext()) {
                        Object object0 = this.iter.next();
                        this.next = (Map.Entry)object0;
                    }
                    else {
                        this.next = null;
                    }
                }
            }
        }

        private final FieldSet extensions;

        protected ExtendableMessage() {
            this.extensions = FieldSet.newFieldSet();
        }

        protected ExtendableMessage(ExtendableBuilder generatedMessageLite$ExtendableBuilder0) {
            this.extensions = generatedMessageLite$ExtendableBuilder0.buildExtensions();
        }

        protected boolean extensionsAreInitialized() {
            return this.extensions.isInitialized();
        }

        protected int extensionsSerializedSize() {
            return this.extensions.getSerializedSize();
        }

        public final Object getExtension(GeneratedExtension generatedMessageLite$GeneratedExtension0) {
            this.verifyExtensionContainingType(generatedMessageLite$GeneratedExtension0);
            Object object0 = this.extensions.getField(generatedMessageLite$GeneratedExtension0.descriptor);
            return object0 == null ? generatedMessageLite$GeneratedExtension0.defaultValue : generatedMessageLite$GeneratedExtension0.fromFieldSetType(object0);
        }

        public final Object getExtension(GeneratedExtension generatedMessageLite$GeneratedExtension0, int v) {
            this.verifyExtensionContainingType(generatedMessageLite$GeneratedExtension0);
            return generatedMessageLite$GeneratedExtension0.singularFromFieldSetType(this.extensions.getRepeatedField(generatedMessageLite$GeneratedExtension0.descriptor, v));
        }

        public final int getExtensionCount(GeneratedExtension generatedMessageLite$GeneratedExtension0) {
            this.verifyExtensionContainingType(generatedMessageLite$GeneratedExtension0);
            return this.extensions.getRepeatedFieldCount(generatedMessageLite$GeneratedExtension0.descriptor);
        }

        public final boolean hasExtension(GeneratedExtension generatedMessageLite$GeneratedExtension0) {
            this.verifyExtensionContainingType(generatedMessageLite$GeneratedExtension0);
            return this.extensions.hasField(generatedMessageLite$GeneratedExtension0.descriptor);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite
        protected void makeExtensionsImmutable() {
            this.extensions.makeImmutable();
        }

        protected ExtensionWriter newExtensionWriter() {
            return new ExtensionWriter(this, false, null);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite
        protected boolean parseUnknownField(CodedInputStream codedInputStream0, CodedOutputStream codedOutputStream0, ExtensionRegistryLite extensionRegistryLite0, int v) throws IOException {
            MessageLite messageLite0 = this.getDefaultInstanceForType();
            return GeneratedMessageLite.parseUnknownField(this.extensions, messageLite0, codedInputStream0, codedOutputStream0, extensionRegistryLite0, v);
        }

        private void verifyExtensionContainingType(GeneratedExtension generatedMessageLite$GeneratedExtension0) {
            if(generatedMessageLite$GeneratedExtension0.getContainingTypeDefaultInstance() != this.getDefaultInstanceForType()) {
                throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
            }
        }
    }

    public interface ExtendableMessageOrBuilder extends MessageLiteOrBuilder {
    }

    static final class ExtensionDescriptor implements FieldDescriptorLite {
        final EnumLiteMap enumTypeMap;
        final boolean isPacked;
        final boolean isRepeated;
        final int number;
        final FieldType type;

        ExtensionDescriptor(EnumLiteMap internal$EnumLiteMap0, int v, FieldType wireFormat$FieldType0, boolean z, boolean z1) {
            this.enumTypeMap = internal$EnumLiteMap0;
            this.number = v;
            this.type = wireFormat$FieldType0;
            this.isRepeated = z;
            this.isPacked = z1;
        }

        @Override
        public int compareTo(Object object0) {
            return this.compareTo(((ExtensionDescriptor)object0));
        }

        public int compareTo(ExtensionDescriptor generatedMessageLite$ExtensionDescriptor0) {
            return this.number - generatedMessageLite$ExtensionDescriptor0.number;
        }

        public EnumLiteMap getEnumType() {
            return this.enumTypeMap;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.FieldSet$FieldDescriptorLite
        public JavaType getLiteJavaType() {
            return this.type.getJavaType();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.FieldSet$FieldDescriptorLite
        public FieldType getLiteType() {
            return this.type;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.FieldSet$FieldDescriptorLite
        public int getNumber() {
            return this.number;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.FieldSet$FieldDescriptorLite
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder internalMergeFrom(kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder messageLite$Builder0, MessageLite messageLite0) {
            return ((Builder)messageLite$Builder0).mergeFrom(((GeneratedMessageLite)messageLite0));
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.FieldSet$FieldDescriptorLite
        public boolean isPacked() {
            return this.isPacked;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.FieldSet$FieldDescriptorLite
        public boolean isRepeated() {
            return this.isRepeated;
        }
    }

    public static class GeneratedExtension {
        final MessageLite containingTypeDefaultInstance;
        final Object defaultValue;
        final ExtensionDescriptor descriptor;
        final Method enumValueOf;
        final MessageLite messageDefaultInstance;
        final Class singularType;

        GeneratedExtension(MessageLite messageLite0, Object object0, MessageLite messageLite1, ExtensionDescriptor generatedMessageLite$ExtensionDescriptor0, Class class0) {
            if(messageLite0 == null) {
                throw new IllegalArgumentException("Null containingTypeDefaultInstance");
            }
            if(generatedMessageLite$ExtensionDescriptor0.getLiteType() == FieldType.MESSAGE && messageLite1 == null) {
                throw new IllegalArgumentException("Null messageDefaultInstance");
            }
            this.containingTypeDefaultInstance = messageLite0;
            this.defaultValue = object0;
            this.messageDefaultInstance = messageLite1;
            this.descriptor = generatedMessageLite$ExtensionDescriptor0;
            this.singularType = class0;
            if(EnumLite.class.isAssignableFrom(class0)) {
                this.enumValueOf = GeneratedMessageLite.getMethodOrDie(class0, "valueOf", new Class[]{Integer.TYPE});
                return;
            }
            this.enumValueOf = null;
        }

        Object fromFieldSetType(Object object0) {
            if(this.descriptor.isRepeated()) {
                if(this.descriptor.getLiteJavaType() == JavaType.ENUM) {
                    ArrayList arrayList0 = new ArrayList();
                    for(Object object1: ((List)object0)) {
                        arrayList0.add(this.singularFromFieldSetType(object1));
                    }
                    return arrayList0;
                }
                return object0;
            }
            return this.singularFromFieldSetType(object0);
        }

        public MessageLite getContainingTypeDefaultInstance() {
            return this.containingTypeDefaultInstance;
        }

        public MessageLite getMessageDefaultInstance() {
            return this.messageDefaultInstance;
        }

        public int getNumber() {
            return this.descriptor.getNumber();
        }

        Object singularFromFieldSetType(Object object0) {
            return this.descriptor.getLiteJavaType() == JavaType.ENUM ? GeneratedMessageLite.invokeOrDie(this.enumValueOf, null, new Object[]{((Integer)object0)}) : object0;
        }

        Object singularToFieldSetType(Object object0) {
            return this.descriptor.getLiteJavaType() == JavaType.ENUM ? ((EnumLite)object0).getNumber() : object0;
        }
    }

    protected GeneratedMessageLite() {
    }

    protected GeneratedMessageLite(Builder generatedMessageLite$Builder0) {
    }

    static Method getMethodOrDie(Class class0, String s, Class[] arr_class) {
        try {
            return class0.getMethod(s, arr_class);
        }
        catch(NoSuchMethodException noSuchMethodException0) {
            throw new RuntimeException("Generated message class \"" + class0.getName() + "\" missing method \"" + s + "\".", noSuchMethodException0);
        }
    }

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
    public Parser getParserForType() {
        throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
    }

    static Object invokeOrDie(Method method0, Object object0, Object[] arr_object) {
        try {
            return method0.invoke(object0, arr_object);
        }
        catch(IllegalAccessException illegalAccessException0) {
            throw new RuntimeException("Couldn\'t use Java reflection to implement protocol message reflection.", illegalAccessException0);
        }
        catch(InvocationTargetException invocationTargetException0) {
            Throwable throwable0 = invocationTargetException0.getCause();
            if(throwable0 instanceof RuntimeException) {
                throw (RuntimeException)throwable0;
            }
            if(throwable0 instanceof Error) {
                throw (Error)throwable0;
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", throwable0);
        }
    }

    protected void makeExtensionsImmutable() {
    }

    public static GeneratedExtension newRepeatedGeneratedExtension(MessageLite messageLite0, MessageLite messageLite1, EnumLiteMap internal$EnumLiteMap0, int v, FieldType wireFormat$FieldType0, boolean z, Class class0) {
        return new GeneratedExtension(messageLite0, Collections.EMPTY_LIST, messageLite1, new ExtensionDescriptor(internal$EnumLiteMap0, v, wireFormat$FieldType0, true, z), class0);
    }

    public static GeneratedExtension newSingularGeneratedExtension(MessageLite messageLite0, Object object0, MessageLite messageLite1, EnumLiteMap internal$EnumLiteMap0, int v, FieldType wireFormat$FieldType0, Class class0) {
        return new GeneratedExtension(messageLite0, object0, messageLite1, new ExtensionDescriptor(internal$EnumLiteMap0, v, wireFormat$FieldType0, false, false), class0);
    }

    private static boolean parseUnknownField(FieldSet fieldSet0, MessageLite messageLite0, CodedInputStream codedInputStream0, CodedOutputStream codedOutputStream0, ExtensionRegistryLite extensionRegistryLite0, int v) throws IOException {
        Object object2;
        kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder messageLite$Builder0;
        boolean z1;
        boolean z;
        GeneratedExtension generatedMessageLite$GeneratedExtension0 = extensionRegistryLite0.findLiteExtensionByNumber(messageLite0, v >>> 3);
        if(generatedMessageLite$GeneratedExtension0 == null) {
            z = true;
            z1 = false;
        }
        else if((v & 7) == FieldSet.getWireFormatForFieldType(generatedMessageLite$GeneratedExtension0.descriptor.getLiteType(), false)) {
            z = false;
            z1 = false;
        }
        else if(!generatedMessageLite$GeneratedExtension0.descriptor.isRepeated || !generatedMessageLite$GeneratedExtension0.descriptor.type.isPackable() || (v & 7) != FieldSet.getWireFormatForFieldType(generatedMessageLite$GeneratedExtension0.descriptor.getLiteType(), true)) {
            z = true;
            z1 = false;
        }
        else {
            z1 = true;
            z = false;
        }
        if(z) {
            return codedInputStream0.skipField(v, codedOutputStream0);
        }
        if(z1) {
            int v1 = codedInputStream0.pushLimit(codedInputStream0.readRawVarint32());
            if(generatedMessageLite$GeneratedExtension0.descriptor.getLiteType() == FieldType.ENUM) {
                while(codedInputStream0.getBytesUntilLimit() > 0) {
                    int v2 = codedInputStream0.readEnum();
                    EnumLite internal$EnumLite0 = generatedMessageLite$GeneratedExtension0.descriptor.getEnumType().findValueByNumber(v2);
                    if(internal$EnumLite0 == null) {
                        return true;
                    }
                    Object object0 = generatedMessageLite$GeneratedExtension0.singularToFieldSetType(internal$EnumLite0);
                    fieldSet0.addRepeatedField(generatedMessageLite$GeneratedExtension0.descriptor, object0);
                }
            }
            else {
                while(codedInputStream0.getBytesUntilLimit() > 0) {
                    Object object1 = FieldSet.readPrimitiveField(codedInputStream0, generatedMessageLite$GeneratedExtension0.descriptor.getLiteType(), false);
                    fieldSet0.addRepeatedField(generatedMessageLite$GeneratedExtension0.descriptor, object1);
                }
            }
            codedInputStream0.popLimit(v1);
            return true;
        }
        switch(kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.1.$SwitchMap$com$google$protobuf$WireFormat$JavaType[generatedMessageLite$GeneratedExtension0.descriptor.getLiteJavaType().ordinal()]) {
            case 1: {
                if(generatedMessageLite$GeneratedExtension0.descriptor.isRepeated()) {
                    messageLite$Builder0 = null;
                }
                else {
                    MessageLite messageLite1 = (MessageLite)fieldSet0.getField(generatedMessageLite$GeneratedExtension0.descriptor);
                    messageLite$Builder0 = messageLite1 == null ? null : messageLite1.toBuilder();
                }
                if(messageLite$Builder0 == null) {
                    messageLite$Builder0 = generatedMessageLite$GeneratedExtension0.getMessageDefaultInstance().newBuilderForType();
                }
                if(generatedMessageLite$GeneratedExtension0.descriptor.getLiteType() == FieldType.GROUP) {
                    codedInputStream0.readGroup(generatedMessageLite$GeneratedExtension0.getNumber(), messageLite$Builder0, extensionRegistryLite0);
                }
                else {
                    codedInputStream0.readMessage(messageLite$Builder0, extensionRegistryLite0);
                }
                object2 = messageLite$Builder0.build();
                break;
            }
            case 2: {
                int v3 = codedInputStream0.readEnum();
                EnumLite internal$EnumLite1 = generatedMessageLite$GeneratedExtension0.descriptor.getEnumType().findValueByNumber(v3);
                if(internal$EnumLite1 == null) {
                    codedOutputStream0.writeRawVarint32(v);
                    codedOutputStream0.writeUInt32NoTag(v3);
                    return true;
                }
                object2 = internal$EnumLite1;
                break;
            }
            default: {
                object2 = FieldSet.readPrimitiveField(codedInputStream0, generatedMessageLite$GeneratedExtension0.descriptor.getLiteType(), false);
            }
        }
        if(generatedMessageLite$GeneratedExtension0.descriptor.isRepeated()) {
            Object object3 = generatedMessageLite$GeneratedExtension0.singularToFieldSetType(object2);
            fieldSet0.addRepeatedField(generatedMessageLite$GeneratedExtension0.descriptor, object3);
            return true;
        }
        Object object4 = generatedMessageLite$GeneratedExtension0.singularToFieldSetType(object2);
        fieldSet0.setField(generatedMessageLite$GeneratedExtension0.descriptor, object4);
        return true;
    }

    protected boolean parseUnknownField(CodedInputStream codedInputStream0, CodedOutputStream codedOutputStream0, ExtensionRegistryLite extensionRegistryLite0, int v) throws IOException {
        return codedInputStream0.skipField(v, codedOutputStream0);
    }

    class kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.1 {
        static final int[] $SwitchMap$com$google$protobuf$WireFormat$JavaType;

        static {
            int[] arr_v = new int[JavaType.values().length];
            kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.1.$SwitchMap$com$google$protobuf$WireFormat$JavaType = arr_v;
            try {
                arr_v[JavaType.MESSAGE.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.1.$SwitchMap$com$google$protobuf$WireFormat$JavaType[JavaType.ENUM.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

