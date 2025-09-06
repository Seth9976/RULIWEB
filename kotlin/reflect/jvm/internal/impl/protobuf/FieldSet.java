package kotlin.reflect.jvm.internal.impl.protobuf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

final class FieldSet {
    public interface FieldDescriptorLite extends Comparable {
        JavaType getLiteJavaType();

        FieldType getLiteType();

        int getNumber();

        Builder internalMergeFrom(Builder arg1, MessageLite arg2);

        boolean isPacked();

        boolean isRepeated();
    }

    private static final FieldSet DEFAULT_INSTANCE;
    private final SmallSortedMap fields;
    private boolean hasLazyField;
    private boolean isImmutable;

    static {
        FieldSet.DEFAULT_INSTANCE = new FieldSet(true);
    }

    private FieldSet() {
        this.hasLazyField = false;
        this.fields = SmallSortedMap.newFieldMap(16);
    }

    private FieldSet(boolean z) {
        this.hasLazyField = false;
        this.fields = SmallSortedMap.newFieldMap(0);
        this.makeImmutable();
    }

    public void addRepeatedField(FieldDescriptorLite fieldSet$FieldDescriptorLite0, Object object0) {
        List list0;
        if(!fieldSet$FieldDescriptorLite0.isRepeated()) {
            throw new IllegalArgumentException("addRepeatedField() can only be called on repeated fields.");
        }
        FieldSet.verifyType(fieldSet$FieldDescriptorLite0.getLiteType(), object0);
        Object object1 = this.getField(fieldSet$FieldDescriptorLite0);
        if(object1 == null) {
            list0 = new ArrayList();
            this.fields.put(fieldSet$FieldDescriptorLite0, list0);
        }
        else {
            list0 = (List)object1;
        }
        list0.add(object0);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return this.clone();
    }

    public FieldSet clone() {
        FieldSet fieldSet0 = FieldSet.newFieldSet();
        for(int v = 0; v < this.fields.getNumArrayEntries(); ++v) {
            Map.Entry map$Entry0 = this.fields.getArrayEntryAt(v);
            fieldSet0.setField(((FieldDescriptorLite)map$Entry0.getKey()), map$Entry0.getValue());
        }
        for(Object object0: this.fields.getOverflowEntries()) {
            fieldSet0.setField(((FieldDescriptorLite)((Map.Entry)object0).getKey()), ((Map.Entry)object0).getValue());
        }
        fieldSet0.hasLazyField = this.hasLazyField;
        return fieldSet0;
    }

    private Object cloneIfMutable(Object object0) {
        if(object0 instanceof byte[]) {
            byte[] arr_b = new byte[((byte[])object0).length];
            System.arraycopy(((byte[])object0), 0, arr_b, 0, ((byte[])object0).length);
            return arr_b;
        }
        return object0;
    }

    private static int computeElementSize(FieldType wireFormat$FieldType0, int v, Object object0) {
        return (wireFormat$FieldType0 == FieldType.GROUP ? CodedOutputStream.computeTagSize(v) * 2 : CodedOutputStream.computeTagSize(v)) + FieldSet.computeElementSizeNoTag(wireFormat$FieldType0, object0);
    }

    private static int computeElementSizeNoTag(FieldType wireFormat$FieldType0, Object object0) {
        switch(kotlin.reflect.jvm.internal.impl.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[wireFormat$FieldType0.ordinal()]) {
            case 1: {
                return CodedOutputStream.computeDoubleSizeNoTag(((double)(((Double)object0))));
            }
            case 2: {
                return CodedOutputStream.computeFloatSizeNoTag(((float)(((Float)object0))));
            }
            case 3: {
                return CodedOutputStream.computeInt64SizeNoTag(((long)(((Long)object0))));
            }
            case 4: {
                return CodedOutputStream.computeUInt64SizeNoTag(((long)(((Long)object0))));
            }
            case 5: {
                return CodedOutputStream.computeInt32SizeNoTag(((int)(((Integer)object0))));
            }
            case 6: {
                return CodedOutputStream.computeFixed64SizeNoTag(((long)(((Long)object0))));
            }
            case 7: {
                return CodedOutputStream.computeFixed32SizeNoTag(((int)(((Integer)object0))));
            }
            case 8: {
                return CodedOutputStream.computeBoolSizeNoTag(((Boolean)object0).booleanValue());
            }
            case 9: {
                return CodedOutputStream.computeStringSizeNoTag(((String)object0));
            }
            case 10: {
                return object0 instanceof ByteString ? CodedOutputStream.computeBytesSizeNoTag(((ByteString)object0)) : CodedOutputStream.computeByteArraySizeNoTag(((byte[])object0));
            }
            case 11: {
                return CodedOutputStream.computeUInt32SizeNoTag(((int)(((Integer)object0))));
            }
            case 12: {
                return CodedOutputStream.computeSFixed32SizeNoTag(((int)(((Integer)object0))));
            }
            case 13: {
                return CodedOutputStream.computeSFixed64SizeNoTag(((long)(((Long)object0))));
            }
            case 14: {
                return CodedOutputStream.computeSInt32SizeNoTag(((int)(((Integer)object0))));
            }
            case 15: {
                return CodedOutputStream.computeSInt64SizeNoTag(((long)(((Long)object0))));
            }
            case 16: {
                return CodedOutputStream.computeGroupSizeNoTag(((MessageLite)object0));
            }
            case 17: {
                return object0 instanceof LazyField ? CodedOutputStream.computeLazyFieldSizeNoTag(((LazyField)object0)) : CodedOutputStream.computeMessageSizeNoTag(((MessageLite)object0));
            }
            case 18: {
                return object0 instanceof EnumLite ? CodedOutputStream.computeEnumSizeNoTag(((EnumLite)object0).getNumber()) : CodedOutputStream.computeEnumSizeNoTag(((int)(((Integer)object0))));
            }
            default: {
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
            }
        }
    }

    public static int computeFieldSize(FieldDescriptorLite fieldSet$FieldDescriptorLite0, Object object0) {
        int v = 0;
        FieldType wireFormat$FieldType0 = fieldSet$FieldDescriptorLite0.getLiteType();
        int v1 = fieldSet$FieldDescriptorLite0.getNumber();
        if(fieldSet$FieldDescriptorLite0.isRepeated()) {
            if(fieldSet$FieldDescriptorLite0.isPacked()) {
                for(Object object1: ((List)object0)) {
                    v += FieldSet.computeElementSizeNoTag(wireFormat$FieldType0, object1);
                }
                return CodedOutputStream.computeTagSize(v1) + v + CodedOutputStream.computeRawVarint32Size(v);
            }
            for(Object object2: ((List)object0)) {
                v += FieldSet.computeElementSize(wireFormat$FieldType0, v1, object2);
            }
            return v;
        }
        return FieldSet.computeElementSize(wireFormat$FieldType0, v1, object0);
    }

    public static FieldSet emptySet() {
        return FieldSet.DEFAULT_INSTANCE;
    }

    public Object getField(FieldDescriptorLite fieldSet$FieldDescriptorLite0) {
        MessageLite messageLite0 = this.fields.get(fieldSet$FieldDescriptorLite0);
        return messageLite0 instanceof LazyField ? ((LazyField)messageLite0).getValue() : messageLite0;
    }

    public Object getRepeatedField(FieldDescriptorLite fieldSet$FieldDescriptorLite0, int v) {
        if(!fieldSet$FieldDescriptorLite0.isRepeated()) {
            throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
        }
        Object object0 = this.getField(fieldSet$FieldDescriptorLite0);
        if(object0 == null) {
            throw new IndexOutOfBoundsException();
        }
        return ((List)object0).get(v);
    }

    public int getRepeatedFieldCount(FieldDescriptorLite fieldSet$FieldDescriptorLite0) {
        if(!fieldSet$FieldDescriptorLite0.isRepeated()) {
            throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
        }
        Object object0 = this.getField(fieldSet$FieldDescriptorLite0);
        return object0 == null ? 0 : ((List)object0).size();
    }

    public int getSerializedSize() {
        int v1 = 0;
        for(int v = 0; v < this.fields.getNumArrayEntries(); ++v) {
            Map.Entry map$Entry0 = this.fields.getArrayEntryAt(v);
            v1 += FieldSet.computeFieldSize(((FieldDescriptorLite)map$Entry0.getKey()), map$Entry0.getValue());
        }
        for(Object object0: this.fields.getOverflowEntries()) {
            v1 += FieldSet.computeFieldSize(((FieldDescriptorLite)((Map.Entry)object0).getKey()), ((Map.Entry)object0).getValue());
        }
        return v1;
    }

    // 去混淆评级： 低(20)
    static int getWireFormatForFieldType(FieldType wireFormat$FieldType0, boolean z) {
        return z ? 2 : wireFormat$FieldType0.getWireType();
    }

    public boolean hasField(FieldDescriptorLite fieldSet$FieldDescriptorLite0) {
        if(fieldSet$FieldDescriptorLite0.isRepeated()) {
            throw new IllegalArgumentException("hasField() can only be called on non-repeated fields.");
        }
        return this.fields.get(fieldSet$FieldDescriptorLite0) != null;
    }

    private boolean isInitialized(Map.Entry map$Entry0) {
        FieldDescriptorLite fieldSet$FieldDescriptorLite0 = (FieldDescriptorLite)map$Entry0.getKey();
        if(fieldSet$FieldDescriptorLite0.getLiteJavaType() == JavaType.MESSAGE) {
            if(fieldSet$FieldDescriptorLite0.isRepeated()) {
                Iterator iterator0 = ((List)map$Entry0.getValue()).iterator();
                while(true) {
                    if(!iterator0.hasNext()) {
                        return true;
                    }
                    Object object0 = iterator0.next();
                    if(((MessageLite)object0).isInitialized()) {
                        continue;
                    }
                    return false;
                }
            }
            Object object1 = map$Entry0.getValue();
            if(object1 instanceof MessageLite) {
                return ((MessageLite)object1).isInitialized();
            }
            if(!(object1 instanceof LazyField)) {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
            return true;
        }
        return true;
    }

    public boolean isInitialized() {
        for(int v = 0; v < this.fields.getNumArrayEntries(); ++v) {
            if(!this.isInitialized(this.fields.getArrayEntryAt(v))) {
                return false;
            }
        }
        for(Object object0: this.fields.getOverflowEntries()) {
            if(!this.isInitialized(((Map.Entry)object0))) {
                return false;
            }
            if(false) {
                break;
            }
        }
        return true;
    }

    public Iterator iterator() {
        return this.hasLazyField ? new LazyIterator(this.fields.entrySet().iterator()) : this.fields.entrySet().iterator();
    }

    public void makeImmutable() {
        if(this.isImmutable) {
            return;
        }
        this.fields.makeImmutable();
        this.isImmutable = true;
    }

    public void mergeFrom(FieldSet fieldSet0) {
        for(int v = 0; v < fieldSet0.fields.getNumArrayEntries(); ++v) {
            this.mergeFromField(fieldSet0.fields.getArrayEntryAt(v));
        }
        for(Object object0: fieldSet0.fields.getOverflowEntries()) {
            this.mergeFromField(((Map.Entry)object0));
        }
    }

    private void mergeFromField(Map.Entry map$Entry0) {
        FieldDescriptorLite fieldSet$FieldDescriptorLite0 = (FieldDescriptorLite)map$Entry0.getKey();
        MessageLite messageLite0 = map$Entry0.getValue();
        if(messageLite0 instanceof LazyField) {
            messageLite0 = ((LazyField)messageLite0).getValue();
        }
        if(fieldSet$FieldDescriptorLite0.isRepeated()) {
            ArrayList arrayList0 = this.getField(fieldSet$FieldDescriptorLite0);
            if(arrayList0 == null) {
                arrayList0 = new ArrayList();
            }
            for(Object object0: ((List)messageLite0)) {
                arrayList0.add(this.cloneIfMutable(object0));
            }
            this.fields.put(fieldSet$FieldDescriptorLite0, arrayList0);
            return;
        }
        if(fieldSet$FieldDescriptorLite0.getLiteJavaType() == JavaType.MESSAGE) {
            Object object1 = this.getField(fieldSet$FieldDescriptorLite0);
            if(object1 == null) {
                Object object2 = this.cloneIfMutable(messageLite0);
                this.fields.put(fieldSet$FieldDescriptorLite0, object2);
                return;
            }
            MessageLite messageLite1 = fieldSet$FieldDescriptorLite0.internalMergeFrom(((MessageLite)object1).toBuilder(), messageLite0).build();
            this.fields.put(fieldSet$FieldDescriptorLite0, messageLite1);
            return;
        }
        Object object3 = this.cloneIfMutable(messageLite0);
        this.fields.put(fieldSet$FieldDescriptorLite0, object3);
    }

    public static FieldSet newFieldSet() {
        return new FieldSet();
    }

    public static Object readPrimitiveField(CodedInputStream codedInputStream0, FieldType wireFormat$FieldType0, boolean z) throws IOException {
        switch(kotlin.reflect.jvm.internal.impl.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[wireFormat$FieldType0.ordinal()]) {
            case 1: {
                return codedInputStream0.readDouble();
            }
            case 2: {
                return codedInputStream0.readFloat();
            }
            case 3: {
                return codedInputStream0.readInt64();
            }
            case 4: {
                return codedInputStream0.readUInt64();
            }
            case 5: {
                return codedInputStream0.readInt32();
            }
            case 6: {
                return codedInputStream0.readFixed64();
            }
            case 7: {
                return codedInputStream0.readFixed32();
            }
            case 8: {
                return Boolean.valueOf(codedInputStream0.readBool());
            }
            case 9: {
                return z ? codedInputStream0.readStringRequireUtf8() : codedInputStream0.readString();
            }
            case 10: {
                return codedInputStream0.readBytes();
            }
            case 11: {
                return codedInputStream0.readUInt32();
            }
            case 12: {
                return codedInputStream0.readSFixed32();
            }
            case 13: {
                return codedInputStream0.readSFixed64();
            }
            case 14: {
                return codedInputStream0.readSInt32();
            }
            case 15: {
                return codedInputStream0.readSInt64();
            }
            case 16: {
                throw new IllegalArgumentException("readPrimitiveField() cannot handle nested groups.");
            }
            case 17: {
                throw new IllegalArgumentException("readPrimitiveField() cannot handle embedded messages.");
            }
            case 18: {
                throw new IllegalArgumentException("readPrimitiveField() cannot handle enums.");
            }
            default: {
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
            }
        }
    }

    public void setField(FieldDescriptorLite fieldSet$FieldDescriptorLite0, Object object0) {
        if(fieldSet$FieldDescriptorLite0.isRepeated()) {
            if(!(object0 instanceof List)) {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
            ArrayList arrayList0 = new ArrayList();
            arrayList0.addAll(((List)object0));
            for(Object object1: arrayList0) {
                FieldSet.verifyType(fieldSet$FieldDescriptorLite0.getLiteType(), object1);
            }
            object0 = arrayList0;
        }
        else {
            FieldSet.verifyType(fieldSet$FieldDescriptorLite0.getLiteType(), object0);
        }
        if(object0 instanceof LazyField) {
            this.hasLazyField = true;
        }
        this.fields.put(fieldSet$FieldDescriptorLite0, object0);
    }

    private static void verifyType(FieldType wireFormat$FieldType0, Object object0) {
        object0.getClass();
        boolean z = true;
        boolean z1 = false;
        switch(kotlin.reflect.jvm.internal.impl.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$JavaType[wireFormat$FieldType0.getJavaType().ordinal()]) {
            case 1: {
                z1 = object0 instanceof Integer;
                break;
            }
            case 2: {
                z1 = object0 instanceof Long;
                break;
            }
            case 3: {
                z1 = object0 instanceof Float;
                break;
            }
            case 4: {
                z1 = object0 instanceof Double;
                break;
            }
            case 5: {
                z1 = object0 instanceof Boolean;
                break;
            }
            case 6: {
                z1 = object0 instanceof String;
                break;
            }
            case 7: {
                if(!(object0 instanceof ByteString) && !(object0 instanceof byte[])) {
                    z = false;
                }
                z1 = z;
                break;
            }
            case 8: {
                if(!(object0 instanceof Integer) && !(object0 instanceof EnumLite)) {
                    z = false;
                }
                z1 = z;
                break;
            }
            case 9: {
                if(!(object0 instanceof MessageLite) && !(object0 instanceof LazyField)) {
                    z = false;
                }
                z1 = z;
            }
        }
        if(!z1) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
    }

    private static void writeElement(CodedOutputStream codedOutputStream0, FieldType wireFormat$FieldType0, int v, Object object0) throws IOException {
        if(wireFormat$FieldType0 == FieldType.GROUP) {
            codedOutputStream0.writeGroup(v, ((MessageLite)object0));
            return;
        }
        codedOutputStream0.writeTag(v, FieldSet.getWireFormatForFieldType(wireFormat$FieldType0, false));
        FieldSet.writeElementNoTag(codedOutputStream0, wireFormat$FieldType0, object0);
    }

    private static void writeElementNoTag(CodedOutputStream codedOutputStream0, FieldType wireFormat$FieldType0, Object object0) throws IOException {
        switch(kotlin.reflect.jvm.internal.impl.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[wireFormat$FieldType0.ordinal()]) {
            case 1: {
                codedOutputStream0.writeDoubleNoTag(((double)(((Double)object0))));
                return;
            }
            case 2: {
                codedOutputStream0.writeFloatNoTag(((float)(((Float)object0))));
                return;
            }
            case 3: {
                codedOutputStream0.writeInt64NoTag(((long)(((Long)object0))));
                return;
            }
            case 4: {
                codedOutputStream0.writeUInt64NoTag(((long)(((Long)object0))));
                return;
            }
            case 5: {
                codedOutputStream0.writeInt32NoTag(((int)(((Integer)object0))));
                return;
            }
            case 6: {
                codedOutputStream0.writeFixed64NoTag(((long)(((Long)object0))));
                return;
            }
            case 7: {
                codedOutputStream0.writeFixed32NoTag(((int)(((Integer)object0))));
                return;
            }
            case 8: {
                codedOutputStream0.writeBoolNoTag(((Boolean)object0).booleanValue());
                return;
            }
            case 9: {
                codedOutputStream0.writeStringNoTag(((String)object0));
                return;
            }
            case 10: {
                if(object0 instanceof ByteString) {
                    codedOutputStream0.writeBytesNoTag(((ByteString)object0));
                    return;
                }
                codedOutputStream0.writeByteArrayNoTag(((byte[])object0));
                return;
            }
            case 11: {
                codedOutputStream0.writeUInt32NoTag(((int)(((Integer)object0))));
                return;
            }
            case 12: {
                codedOutputStream0.writeSFixed32NoTag(((int)(((Integer)object0))));
                return;
            }
            case 13: {
                codedOutputStream0.writeSFixed64NoTag(((long)(((Long)object0))));
                return;
            }
            case 14: {
                codedOutputStream0.writeSInt32NoTag(((int)(((Integer)object0))));
                return;
            }
            case 15: {
                codedOutputStream0.writeSInt64NoTag(((long)(((Long)object0))));
                return;
            }
            case 16: {
                codedOutputStream0.writeGroupNoTag(((MessageLite)object0));
                return;
            }
            case 17: {
                codedOutputStream0.writeMessageNoTag(((MessageLite)object0));
                return;
            }
            case 18: {
                if(object0 instanceof EnumLite) {
                    codedOutputStream0.writeEnumNoTag(((EnumLite)object0).getNumber());
                    return;
                }
                codedOutputStream0.writeEnumNoTag(((int)(((Integer)object0))));
            }
        }
    }

    public static void writeField(FieldDescriptorLite fieldSet$FieldDescriptorLite0, Object object0, CodedOutputStream codedOutputStream0) throws IOException {
        FieldType wireFormat$FieldType0 = fieldSet$FieldDescriptorLite0.getLiteType();
        int v = fieldSet$FieldDescriptorLite0.getNumber();
        if(fieldSet$FieldDescriptorLite0.isRepeated()) {
            if(fieldSet$FieldDescriptorLite0.isPacked()) {
                codedOutputStream0.writeTag(v, 2);
                int v1 = 0;
                for(Object object1: ((List)object0)) {
                    v1 += FieldSet.computeElementSizeNoTag(wireFormat$FieldType0, object1);
                }
                codedOutputStream0.writeRawVarint32(v1);
                for(Object object2: ((List)object0)) {
                    FieldSet.writeElementNoTag(codedOutputStream0, wireFormat$FieldType0, object2);
                }
                return;
            }
            for(Object object3: ((List)object0)) {
                FieldSet.writeElement(codedOutputStream0, wireFormat$FieldType0, v, object3);
            }
            return;
        }
        if(object0 instanceof LazyField) {
            FieldSet.writeElement(codedOutputStream0, wireFormat$FieldType0, v, ((LazyField)object0).getValue());
            return;
        }
        FieldSet.writeElement(codedOutputStream0, wireFormat$FieldType0, v, object0);
    }

    class kotlin.reflect.jvm.internal.impl.protobuf.FieldSet.1 {
        static final int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType;
        static final int[] $SwitchMap$com$google$protobuf$WireFormat$JavaType;

        static {
            int[] arr_v = new int[FieldType.values().length];
            kotlin.reflect.jvm.internal.impl.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType = arr_v;
            try {
                arr_v[FieldType.DOUBLE.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                kotlin.reflect.jvm.internal.impl.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.FLOAT.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                kotlin.reflect.jvm.internal.impl.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.INT64.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                kotlin.reflect.jvm.internal.impl.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.UINT64.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                kotlin.reflect.jvm.internal.impl.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.INT32.ordinal()] = 5;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                kotlin.reflect.jvm.internal.impl.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.FIXED64.ordinal()] = 6;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                kotlin.reflect.jvm.internal.impl.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.FIXED32.ordinal()] = 7;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                kotlin.reflect.jvm.internal.impl.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.BOOL.ordinal()] = 8;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                kotlin.reflect.jvm.internal.impl.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.STRING.ordinal()] = 9;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                kotlin.reflect.jvm.internal.impl.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.BYTES.ordinal()] = 10;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                kotlin.reflect.jvm.internal.impl.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.UINT32.ordinal()] = 11;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                kotlin.reflect.jvm.internal.impl.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.SFIXED32.ordinal()] = 12;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                kotlin.reflect.jvm.internal.impl.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.SFIXED64.ordinal()] = 13;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                kotlin.reflect.jvm.internal.impl.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.SINT32.ordinal()] = 14;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                kotlin.reflect.jvm.internal.impl.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.SINT64.ordinal()] = 15;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                kotlin.reflect.jvm.internal.impl.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.GROUP.ordinal()] = 16;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                kotlin.reflect.jvm.internal.impl.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.MESSAGE.ordinal()] = 17;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                kotlin.reflect.jvm.internal.impl.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.ENUM.ordinal()] = 18;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            int[] arr_v1 = new int[JavaType.values().length];
            kotlin.reflect.jvm.internal.impl.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$JavaType = arr_v1;
            try {
                arr_v1[JavaType.INT.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                kotlin.reflect.jvm.internal.impl.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$JavaType[JavaType.LONG.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                kotlin.reflect.jvm.internal.impl.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$JavaType[JavaType.FLOAT.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                kotlin.reflect.jvm.internal.impl.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$JavaType[JavaType.DOUBLE.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                kotlin.reflect.jvm.internal.impl.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$JavaType[JavaType.BOOLEAN.ordinal()] = 5;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                kotlin.reflect.jvm.internal.impl.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$JavaType[JavaType.STRING.ordinal()] = 6;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                kotlin.reflect.jvm.internal.impl.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$JavaType[JavaType.BYTE_STRING.ordinal()] = 7;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                kotlin.reflect.jvm.internal.impl.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$JavaType[JavaType.ENUM.ordinal()] = 8;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                kotlin.reflect.jvm.internal.impl.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$JavaType[JavaType.MESSAGE.ordinal()] = 9;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

