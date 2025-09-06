package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;

final class FieldSet {
    static final class Builder {
        private SmallSortedMap fields;
        private boolean hasLazyField;
        private boolean hasNestedBuilders;
        private boolean isMutable;

        private Builder() {
            this(SmallSortedMap.newFieldMap(16));
        }

        Builder(com.google.crypto.tink.shaded.protobuf.FieldSet.1 fieldSet$10) {
        }

        private Builder(SmallSortedMap smallSortedMap0) {
            this.fields = smallSortedMap0;
            this.isMutable = true;
        }

        public void addRepeatedField(FieldDescriptorLite fieldSet$FieldDescriptorLite0, Object object0) {
            List list0;
            this.ensureIsMutable();
            if(!fieldSet$FieldDescriptorLite0.isRepeated()) {
                throw new IllegalArgumentException("addRepeatedField() can only be called on repeated fields.");
            }
            this.hasNestedBuilders = this.hasNestedBuilders || object0 instanceof com.google.crypto.tink.shaded.protobuf.MessageLite.Builder;
            this.verifyType(fieldSet$FieldDescriptorLite0, object0);
            Object object1 = this.getFieldAllowBuilders(fieldSet$FieldDescriptorLite0);
            if(object1 == null) {
                list0 = new ArrayList();
                this.fields.put(fieldSet$FieldDescriptorLite0, list0);
            }
            else {
                list0 = (List)object1;
            }
            list0.add(object0);
        }

        public FieldSet build() {
            return this.buildImpl(false);
        }

        private FieldSet buildImpl(boolean z) {
            if(this.fields.isEmpty()) {
                return FieldSet.emptySet();
            }
            this.isMutable = false;
            SmallSortedMap smallSortedMap0 = this.fields;
            if(this.hasNestedBuilders) {
                smallSortedMap0 = FieldSet.cloneAllFieldsMap(smallSortedMap0, false);
                Builder.replaceBuilders(smallSortedMap0, z);
            }
            FieldSet fieldSet0 = new FieldSet(smallSortedMap0, null);
            fieldSet0.hasLazyField = this.hasLazyField;
            return fieldSet0;
        }

        public FieldSet buildPartial() {
            return this.buildImpl(true);
        }

        public void clearField(FieldDescriptorLite fieldSet$FieldDescriptorLite0) {
            this.ensureIsMutable();
            this.fields.remove(fieldSet$FieldDescriptorLite0);
            if(this.fields.isEmpty()) {
                this.hasLazyField = false;
            }
        }

        private void ensureIsMutable() {
            if(!this.isMutable) {
                this.fields = FieldSet.cloneAllFieldsMap(this.fields, true);
                this.isMutable = true;
            }
        }

        public static Builder fromFieldSet(FieldSet fieldSet0) {
            Builder fieldSet$Builder0 = new Builder(FieldSet.cloneAllFieldsMap(fieldSet0.fields, true));
            fieldSet$Builder0.hasLazyField = fieldSet0.hasLazyField;
            return fieldSet$Builder0;
        }

        public Map getAllFields() {
            if(this.hasLazyField) {
                Map map0 = FieldSet.cloneAllFieldsMap(this.fields, false);
                if(this.fields.isImmutable()) {
                    ((SmallSortedMap)map0).makeImmutable();
                    return map0;
                }
                Builder.replaceBuilders(((SmallSortedMap)map0), true);
                return map0;
            }
            return this.fields.isImmutable() ? this.fields : Collections.unmodifiableMap(this.fields);
        }

        public Object getField(FieldDescriptorLite fieldSet$FieldDescriptorLite0) {
            return Builder.replaceBuilders(fieldSet$FieldDescriptorLite0, this.getFieldAllowBuilders(fieldSet$FieldDescriptorLite0), true);
        }

        Object getFieldAllowBuilders(FieldDescriptorLite fieldSet$FieldDescriptorLite0) {
            MessageLite messageLite0 = this.fields.get(fieldSet$FieldDescriptorLite0);
            return messageLite0 instanceof LazyField ? ((LazyField)messageLite0).getValue() : messageLite0;
        }

        public Object getRepeatedField(FieldDescriptorLite fieldSet$FieldDescriptorLite0, int v) {
            if(this.hasNestedBuilders) {
                this.ensureIsMutable();
            }
            return Builder.replaceBuilder(this.getRepeatedFieldAllowBuilders(fieldSet$FieldDescriptorLite0, v), true);
        }

        Object getRepeatedFieldAllowBuilders(FieldDescriptorLite fieldSet$FieldDescriptorLite0, int v) {
            if(!fieldSet$FieldDescriptorLite0.isRepeated()) {
                throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
            }
            Object object0 = this.getFieldAllowBuilders(fieldSet$FieldDescriptorLite0);
            if(object0 == null) {
                throw new IndexOutOfBoundsException();
            }
            return ((List)object0).get(v);
        }

        public int getRepeatedFieldCount(FieldDescriptorLite fieldSet$FieldDescriptorLite0) {
            if(!fieldSet$FieldDescriptorLite0.isRepeated()) {
                throw new IllegalArgumentException("getRepeatedFieldCount() can only be called on repeated fields.");
            }
            Object object0 = this.getFieldAllowBuilders(fieldSet$FieldDescriptorLite0);
            return object0 == null ? 0 : ((List)object0).size();
        }

        public boolean hasField(FieldDescriptorLite fieldSet$FieldDescriptorLite0) {
            if(fieldSet$FieldDescriptorLite0.isRepeated()) {
                throw new IllegalArgumentException("hasField() can only be called on non-repeated fields.");
            }
            return this.fields.get(fieldSet$FieldDescriptorLite0) != null;
        }

        public boolean isInitialized() {
            for(int v = 0; v < this.fields.getNumArrayEntries(); ++v) {
                if(!FieldSet.isInitialized(this.fields.getArrayEntryAt(v))) {
                    return false;
                }
            }
            for(Object object0: this.fields.getOverflowEntries()) {
                if(!FieldSet.isInitialized(((Map.Entry)object0))) {
                    return false;
                }
                if(false) {
                    break;
                }
            }
            return true;
        }

        public void mergeFrom(FieldSet fieldSet0) {
            this.ensureIsMutable();
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
                List list0 = (List)this.getFieldAllowBuilders(fieldSet$FieldDescriptorLite0);
                if(list0 == null) {
                    list0 = new ArrayList();
                    this.fields.put(fieldSet$FieldDescriptorLite0, list0);
                }
                for(Object object0: ((List)messageLite0)) {
                    list0.add(FieldSet.cloneIfMutable(object0));
                }
                return;
            }
            if(fieldSet$FieldDescriptorLite0.getLiteJavaType() == JavaType.MESSAGE) {
                Object object1 = this.getFieldAllowBuilders(fieldSet$FieldDescriptorLite0);
                if(object1 == null) {
                    this.fields.put(fieldSet$FieldDescriptorLite0, FieldSet.cloneIfMutable(messageLite0));
                    return;
                }
                if(object1 instanceof com.google.crypto.tink.shaded.protobuf.MessageLite.Builder) {
                    fieldSet$FieldDescriptorLite0.internalMergeFrom(((com.google.crypto.tink.shaded.protobuf.MessageLite.Builder)object1), messageLite0);
                    return;
                }
                MessageLite messageLite1 = fieldSet$FieldDescriptorLite0.internalMergeFrom(((MessageLite)object1).toBuilder(), messageLite0).build();
                this.fields.put(fieldSet$FieldDescriptorLite0, messageLite1);
                return;
            }
            this.fields.put(fieldSet$FieldDescriptorLite0, FieldSet.cloneIfMutable(messageLite0));
        }

        private static Object replaceBuilder(Object object0, boolean z) {
            if(!(object0 instanceof com.google.crypto.tink.shaded.protobuf.MessageLite.Builder)) {
                return object0;
            }
            return z ? ((com.google.crypto.tink.shaded.protobuf.MessageLite.Builder)object0).buildPartial() : ((com.google.crypto.tink.shaded.protobuf.MessageLite.Builder)object0).build();
        }

        private static Object replaceBuilders(FieldDescriptorLite fieldSet$FieldDescriptorLite0, Object object0, boolean z) {
            if(object0 != null && fieldSet$FieldDescriptorLite0.getLiteJavaType() == JavaType.MESSAGE) {
                if(fieldSet$FieldDescriptorLite0.isRepeated()) {
                    if(!(object0 instanceof List)) {
                        throw new IllegalStateException("Repeated field should contains a List but actually contains type: " + object0.getClass());
                    }
                    List list0 = (List)object0;
                    for(int v = 0; v < list0.size(); ++v) {
                        Object object1 = list0.get(v);
                        Object object2 = Builder.replaceBuilder(object1, z);
                        if(object2 != object1) {
                            if(list0 == object0) {
                                list0 = new ArrayList(list0);
                            }
                            list0.set(v, object2);
                        }
                    }
                    return list0;
                }
                return Builder.replaceBuilder(object0, z);
            }
            return object0;
        }

        private static void replaceBuilders(SmallSortedMap smallSortedMap0, boolean z) {
            for(int v = 0; v < smallSortedMap0.getNumArrayEntries(); ++v) {
                Builder.replaceBuilders(smallSortedMap0.getArrayEntryAt(v), z);
            }
            for(Object object0: smallSortedMap0.getOverflowEntries()) {
                Builder.replaceBuilders(((Map.Entry)object0), z);
            }
        }

        private static void replaceBuilders(Map.Entry map$Entry0, boolean z) {
            map$Entry0.setValue(Builder.replaceBuilders(((FieldDescriptorLite)map$Entry0.getKey()), map$Entry0.getValue(), z));
        }

        public void setField(FieldDescriptorLite fieldSet$FieldDescriptorLite0, Object object0) {
            this.ensureIsMutable();
            boolean z = false;
            if(fieldSet$FieldDescriptorLite0.isRepeated()) {
                if(!(object0 instanceof List)) {
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
                ArrayList arrayList0 = new ArrayList(((List)object0));
                for(Object object1: arrayList0) {
                    this.verifyType(fieldSet$FieldDescriptorLite0, object1);
                    this.hasNestedBuilders = this.hasNestedBuilders || object1 instanceof com.google.crypto.tink.shaded.protobuf.MessageLite.Builder;
                }
                object0 = arrayList0;
            }
            else {
                this.verifyType(fieldSet$FieldDescriptorLite0, object0);
            }
            if(object0 instanceof LazyField) {
                this.hasLazyField = true;
            }
            if(this.hasNestedBuilders || object0 instanceof com.google.crypto.tink.shaded.protobuf.MessageLite.Builder) {
                z = true;
            }
            this.hasNestedBuilders = z;
            this.fields.put(fieldSet$FieldDescriptorLite0, object0);
        }

        public void setRepeatedField(FieldDescriptorLite fieldSet$FieldDescriptorLite0, int v, Object object0) {
            this.ensureIsMutable();
            if(!fieldSet$FieldDescriptorLite0.isRepeated()) {
                throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
            }
            this.hasNestedBuilders = this.hasNestedBuilders || object0 instanceof com.google.crypto.tink.shaded.protobuf.MessageLite.Builder;
            Object object1 = this.getFieldAllowBuilders(fieldSet$FieldDescriptorLite0);
            if(object1 == null) {
                throw new IndexOutOfBoundsException();
            }
            this.verifyType(fieldSet$FieldDescriptorLite0, object0);
            ((List)object1).set(v, object0);
        }

        private void verifyType(FieldDescriptorLite fieldSet$FieldDescriptorLite0, Object object0) {
            if(!FieldSet.isValidType(fieldSet$FieldDescriptorLite0.getLiteType(), object0) && (fieldSet$FieldDescriptorLite0.getLiteType().getJavaType() != JavaType.MESSAGE || !(object0 instanceof com.google.crypto.tink.shaded.protobuf.MessageLite.Builder))) {
                throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", fieldSet$FieldDescriptorLite0.getNumber(), fieldSet$FieldDescriptorLite0.getLiteType().getJavaType(), object0.getClass().getName()));
            }
        }
    }

    public interface FieldDescriptorLite extends Comparable {
        EnumLiteMap getEnumType();

        JavaType getLiteJavaType();

        FieldType getLiteType();

        int getNumber();

        com.google.crypto.tink.shaded.protobuf.MessageLite.Builder internalMergeFrom(com.google.crypto.tink.shaded.protobuf.MessageLite.Builder arg1, MessageLite arg2);

        boolean isPacked();

        boolean isRepeated();
    }

    private static final int DEFAULT_FIELD_MAP_ARRAY_SIZE = 16;
    private static final FieldSet DEFAULT_INSTANCE;
    private final SmallSortedMap fields;
    private boolean hasLazyField;
    private boolean isImmutable;

    static {
        FieldSet.DEFAULT_INSTANCE = new FieldSet(true);
    }

    private FieldSet() {
        this.fields = SmallSortedMap.newFieldMap(16);
    }

    private FieldSet(SmallSortedMap smallSortedMap0) {
        this.fields = smallSortedMap0;
        this.makeImmutable();
    }

    FieldSet(SmallSortedMap smallSortedMap0, com.google.crypto.tink.shaded.protobuf.FieldSet.1 fieldSet$10) {
        this(smallSortedMap0);
    }

    private FieldSet(boolean z) {
        this(SmallSortedMap.newFieldMap(0));
        this.makeImmutable();
    }

    public void addRepeatedField(FieldDescriptorLite fieldSet$FieldDescriptorLite0, Object object0) {
        List list0;
        if(!fieldSet$FieldDescriptorLite0.isRepeated()) {
            throw new IllegalArgumentException("addRepeatedField() can only be called on repeated fields.");
        }
        this.verifyType(fieldSet$FieldDescriptorLite0, object0);
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

    public void clear() {
        this.fields.clear();
        this.hasLazyField = false;
    }

    public void clearField(FieldDescriptorLite fieldSet$FieldDescriptorLite0) {
        this.fields.remove(fieldSet$FieldDescriptorLite0);
        if(this.fields.isEmpty()) {
            this.hasLazyField = false;
        }
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

    @Override
    public Object clone() throws CloneNotSupportedException {
        return this.clone();
    }

    private static SmallSortedMap cloneAllFieldsMap(SmallSortedMap smallSortedMap0, boolean z) {
        SmallSortedMap smallSortedMap1 = SmallSortedMap.newFieldMap(16);
        for(int v = 0; v < smallSortedMap0.getNumArrayEntries(); ++v) {
            FieldSet.cloneFieldEntry(smallSortedMap1, smallSortedMap0.getArrayEntryAt(v), z);
        }
        for(Object object0: smallSortedMap0.getOverflowEntries()) {
            FieldSet.cloneFieldEntry(smallSortedMap1, ((Map.Entry)object0), z);
        }
        return smallSortedMap1;
    }

    private static void cloneFieldEntry(Map map0, Map.Entry map$Entry0, boolean z) {
        FieldDescriptorLite fieldSet$FieldDescriptorLite0 = (FieldDescriptorLite)map$Entry0.getKey();
        Object object0 = map$Entry0.getValue();
        if(object0 instanceof LazyField) {
            map0.put(fieldSet$FieldDescriptorLite0, ((LazyField)object0).getValue());
            return;
        }
        if(z && object0 instanceof List) {
            map0.put(fieldSet$FieldDescriptorLite0, new ArrayList(((List)object0)));
            return;
        }
        map0.put(fieldSet$FieldDescriptorLite0, object0);
    }

    private static Object cloneIfMutable(Object object0) {
        if(object0 instanceof byte[]) {
            byte[] arr_b = new byte[((byte[])object0).length];
            System.arraycopy(((byte[])object0), 0, arr_b, 0, ((byte[])object0).length);
            return arr_b;
        }
        return object0;
    }

    static int computeElementSize(FieldType wireFormat$FieldType0, int v, Object object0) {
        return (wireFormat$FieldType0 == FieldType.GROUP ? CodedOutputStream.computeTagSize(v) * 2 : CodedOutputStream.computeTagSize(v)) + FieldSet.computeElementSizeNoTag(wireFormat$FieldType0, object0);
    }

    static int computeElementSizeNoTag(FieldType wireFormat$FieldType0, Object object0) {
        switch(com.google.crypto.tink.shaded.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[wireFormat$FieldType0.ordinal()]) {
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
                return CodedOutputStream.computeGroupSizeNoTag(((MessageLite)object0));
            }
            case 10: {
                return object0 instanceof LazyField ? CodedOutputStream.computeLazyFieldSizeNoTag(((LazyField)object0)) : CodedOutputStream.computeMessageSizeNoTag(((MessageLite)object0));
            }
            case 11: {
                return object0 instanceof ByteString ? CodedOutputStream.computeBytesSizeNoTag(((ByteString)object0)) : CodedOutputStream.computeStringSizeNoTag(((String)object0));
            }
            case 12: {
                return object0 instanceof ByteString ? CodedOutputStream.computeBytesSizeNoTag(((ByteString)object0)) : CodedOutputStream.computeByteArraySizeNoTag(((byte[])object0));
            }
            case 13: {
                return CodedOutputStream.computeUInt32SizeNoTag(((int)(((Integer)object0))));
            }
            case 14: {
                return CodedOutputStream.computeSFixed32SizeNoTag(((int)(((Integer)object0))));
            }
            case 15: {
                return CodedOutputStream.computeSFixed64SizeNoTag(((long)(((Long)object0))));
            }
            case 16: {
                return CodedOutputStream.computeSInt32SizeNoTag(((int)(((Integer)object0))));
            }
            case 17: {
                return CodedOutputStream.computeSInt64SizeNoTag(((long)(((Long)object0))));
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
                return CodedOutputStream.computeTagSize(v1) + v + CodedOutputStream.computeUInt32SizeNoTag(v);
            }
            for(Object object2: ((List)object0)) {
                v += FieldSet.computeElementSize(wireFormat$FieldType0, v1, object2);
            }
            return v;
        }
        return FieldSet.computeElementSize(wireFormat$FieldType0, v1, object0);
    }

    Iterator descendingIterator() {
        return this.hasLazyField ? new LazyIterator(this.fields.descendingEntrySet().iterator()) : this.fields.descendingEntrySet().iterator();
    }

    public static FieldSet emptySet() {
        return FieldSet.DEFAULT_INSTANCE;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof FieldSet ? this.fields.equals(((FieldSet)object0).fields) : false;
    }

    public Map getAllFields() {
        if(this.hasLazyField) {
            Map map0 = FieldSet.cloneAllFieldsMap(this.fields, false);
            if(this.fields.isImmutable()) {
                ((SmallSortedMap)map0).makeImmutable();
            }
            return map0;
        }
        return this.fields.isImmutable() ? this.fields : Collections.unmodifiableMap(this.fields);
    }

    public Object getField(FieldDescriptorLite fieldSet$FieldDescriptorLite0) {
        MessageLite messageLite0 = this.fields.get(fieldSet$FieldDescriptorLite0);
        return messageLite0 instanceof LazyField ? ((LazyField)messageLite0).getValue() : messageLite0;
    }

    private int getMessageSetSerializedSize(Map.Entry map$Entry0) {
        FieldDescriptorLite fieldSet$FieldDescriptorLite0 = (FieldDescriptorLite)map$Entry0.getKey();
        Object object0 = map$Entry0.getValue();
        if(fieldSet$FieldDescriptorLite0.getLiteJavaType() == JavaType.MESSAGE && !fieldSet$FieldDescriptorLite0.isRepeated() && !fieldSet$FieldDescriptorLite0.isPacked()) {
            return object0 instanceof LazyField ? CodedOutputStream.computeLazyFieldMessageSetExtensionSize(((FieldDescriptorLite)map$Entry0.getKey()).getNumber(), ((LazyField)object0)) : CodedOutputStream.computeMessageSetExtensionSize(((FieldDescriptorLite)map$Entry0.getKey()).getNumber(), ((MessageLite)object0));
        }
        return FieldSet.computeFieldSize(fieldSet$FieldDescriptorLite0, object0);
    }

    public int getMessageSetSerializedSize() {
        int v1 = 0;
        for(int v = 0; v < this.fields.getNumArrayEntries(); ++v) {
            v1 += this.getMessageSetSerializedSize(this.fields.getArrayEntryAt(v));
        }
        for(Object object0: this.fields.getOverflowEntries()) {
            v1 += this.getMessageSetSerializedSize(((Map.Entry)object0));
        }
        return v1;
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

    @Override
    public int hashCode() {
        return this.fields.hashCode();
    }

    boolean isEmpty() {
        return this.fields.isEmpty();
    }

    public boolean isImmutable() {
        return this.isImmutable;
    }

    private static boolean isInitialized(Map.Entry map$Entry0) {
        FieldDescriptorLite fieldSet$FieldDescriptorLite0 = (FieldDescriptorLite)map$Entry0.getKey();
        if(fieldSet$FieldDescriptorLite0.getLiteJavaType() == JavaType.MESSAGE) {
            if(fieldSet$FieldDescriptorLite0.isRepeated()) {
                Iterator iterator0 = ((List)map$Entry0.getValue()).iterator();
                while(true) {
                    if(!iterator0.hasNext()) {
                        return true;
                    }
                    Object object0 = iterator0.next();
                    if(FieldSet.isMessageFieldValueInitialized(object0)) {
                        continue;
                    }
                    return false;
                }
            }
            return FieldSet.isMessageFieldValueInitialized(map$Entry0.getValue());
        }
        return true;
    }

    public boolean isInitialized() {
        for(int v = 0; v < this.fields.getNumArrayEntries(); ++v) {
            if(!FieldSet.isInitialized(this.fields.getArrayEntryAt(v))) {
                return false;
            }
        }
        for(Object object0: this.fields.getOverflowEntries()) {
            if(!FieldSet.isInitialized(((Map.Entry)object0))) {
                return false;
            }
            if(false) {
                break;
            }
        }
        return true;
    }

    private static boolean isMessageFieldValueInitialized(Object object0) {
        if(object0 instanceof MessageLiteOrBuilder) {
            return ((MessageLiteOrBuilder)object0).isInitialized();
        }
        if(!(object0 instanceof LazyField)) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        return true;
    }

    private static boolean isValidType(FieldType wireFormat$FieldType0, Object object0) {
        Internal.checkNotNull(object0);
        switch(com.google.crypto.tink.shaded.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$JavaType[wireFormat$FieldType0.getJavaType().ordinal()]) {
            case 1: {
                return object0 instanceof Integer;
            }
            case 2: {
                return object0 instanceof Long;
            }
            case 3: {
                return object0 instanceof Float;
            }
            case 4: {
                return object0 instanceof Double;
            }
            case 5: {
                return object0 instanceof Boolean;
            }
            case 6: {
                return object0 instanceof String;
            }
            case 7: {
                return object0 instanceof ByteString || object0 instanceof byte[];
            }
            case 8: {
                return object0 instanceof Integer || object0 instanceof EnumLite;
            }
            case 9: {
                return object0 instanceof MessageLite || object0 instanceof LazyField;
            }
            default: {
                return false;
            }
        }
    }

    public Iterator iterator() {
        return this.hasLazyField ? new LazyIterator(this.fields.entrySet().iterator()) : this.fields.entrySet().iterator();
    }

    public void makeImmutable() {
        if(this.isImmutable) {
            return;
        }
        for(int v = 0; v < this.fields.getNumArrayEntries(); ++v) {
            Map.Entry map$Entry0 = this.fields.getArrayEntryAt(v);
            if(map$Entry0.getValue() instanceof GeneratedMessageLite) {
                ((GeneratedMessageLite)map$Entry0.getValue()).makeImmutable();
            }
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
                arrayList0.add(FieldSet.cloneIfMutable(object0));
            }
            this.fields.put(fieldSet$FieldDescriptorLite0, arrayList0);
            return;
        }
        if(fieldSet$FieldDescriptorLite0.getLiteJavaType() == JavaType.MESSAGE) {
            Object object1 = this.getField(fieldSet$FieldDescriptorLite0);
            if(object1 == null) {
                Object object2 = FieldSet.cloneIfMutable(messageLite0);
                this.fields.put(fieldSet$FieldDescriptorLite0, object2);
                return;
            }
            MessageLite messageLite1 = fieldSet$FieldDescriptorLite0.internalMergeFrom(((MessageLite)object1).toBuilder(), messageLite0).build();
            this.fields.put(fieldSet$FieldDescriptorLite0, messageLite1);
            return;
        }
        Object object3 = FieldSet.cloneIfMutable(messageLite0);
        this.fields.put(fieldSet$FieldDescriptorLite0, object3);
    }

    public static Builder newBuilder() {
        return new Builder(null);
    }

    public static FieldSet newFieldSet() {
        return new FieldSet();
    }

    // 去混淆评级： 低(20)
    public static Object readPrimitiveField(CodedInputStream codedInputStream0, FieldType wireFormat$FieldType0, boolean z) throws IOException {
        return z ? WireFormat.readPrimitiveField(codedInputStream0, wireFormat$FieldType0, Utf8Validation.STRICT) : WireFormat.readPrimitiveField(codedInputStream0, wireFormat$FieldType0, Utf8Validation.LOOSE);
    }

    public void setField(FieldDescriptorLite fieldSet$FieldDescriptorLite0, Object object0) {
        if(fieldSet$FieldDescriptorLite0.isRepeated()) {
            if(!(object0 instanceof List)) {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
            ArrayList arrayList0 = new ArrayList();
            arrayList0.addAll(((List)object0));
            for(Object object1: arrayList0) {
                this.verifyType(fieldSet$FieldDescriptorLite0, object1);
            }
            object0 = arrayList0;
        }
        else {
            this.verifyType(fieldSet$FieldDescriptorLite0, object0);
        }
        if(object0 instanceof LazyField) {
            this.hasLazyField = true;
        }
        this.fields.put(fieldSet$FieldDescriptorLite0, object0);
    }

    public void setRepeatedField(FieldDescriptorLite fieldSet$FieldDescriptorLite0, int v, Object object0) {
        if(!fieldSet$FieldDescriptorLite0.isRepeated()) {
            throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
        }
        Object object1 = this.getField(fieldSet$FieldDescriptorLite0);
        if(object1 == null) {
            throw new IndexOutOfBoundsException();
        }
        this.verifyType(fieldSet$FieldDescriptorLite0, object0);
        ((List)object1).set(v, object0);
    }

    private void verifyType(FieldDescriptorLite fieldSet$FieldDescriptorLite0, Object object0) {
        if(!FieldSet.isValidType(fieldSet$FieldDescriptorLite0.getLiteType(), object0)) {
            throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", fieldSet$FieldDescriptorLite0.getNumber(), fieldSet$FieldDescriptorLite0.getLiteType().getJavaType(), object0.getClass().getName()));
        }
    }

    static void writeElement(CodedOutputStream codedOutputStream0, FieldType wireFormat$FieldType0, int v, Object object0) throws IOException {
        if(wireFormat$FieldType0 == FieldType.GROUP) {
            codedOutputStream0.writeGroup(v, ((MessageLite)object0));
            return;
        }
        codedOutputStream0.writeTag(v, FieldSet.getWireFormatForFieldType(wireFormat$FieldType0, false));
        FieldSet.writeElementNoTag(codedOutputStream0, wireFormat$FieldType0, object0);
    }

    static void writeElementNoTag(CodedOutputStream codedOutputStream0, FieldType wireFormat$FieldType0, Object object0) throws IOException {
        switch(com.google.crypto.tink.shaded.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[wireFormat$FieldType0.ordinal()]) {
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
                codedOutputStream0.writeGroupNoTag(((MessageLite)object0));
                return;
            }
            case 10: {
                codedOutputStream0.writeMessageNoTag(((MessageLite)object0));
                return;
            }
            case 11: {
                if(object0 instanceof ByteString) {
                    codedOutputStream0.writeBytesNoTag(((ByteString)object0));
                    return;
                }
                codedOutputStream0.writeStringNoTag(((String)object0));
                return;
            }
            case 12: {
                if(object0 instanceof ByteString) {
                    codedOutputStream0.writeBytesNoTag(((ByteString)object0));
                    return;
                }
                codedOutputStream0.writeByteArrayNoTag(((byte[])object0));
                return;
            }
            case 13: {
                codedOutputStream0.writeUInt32NoTag(((int)(((Integer)object0))));
                return;
            }
            case 14: {
                codedOutputStream0.writeSFixed32NoTag(((int)(((Integer)object0))));
                return;
            }
            case 15: {
                codedOutputStream0.writeSFixed64NoTag(((long)(((Long)object0))));
                return;
            }
            case 16: {
                codedOutputStream0.writeSInt32NoTag(((int)(((Integer)object0))));
                return;
            }
            case 17: {
                codedOutputStream0.writeSInt64NoTag(((long)(((Long)object0))));
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
                codedOutputStream0.writeUInt32NoTag(v1);
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

    private void writeMessageSetTo(Map.Entry map$Entry0, CodedOutputStream codedOutputStream0) throws IOException {
        FieldDescriptorLite fieldSet$FieldDescriptorLite0 = (FieldDescriptorLite)map$Entry0.getKey();
        if(fieldSet$FieldDescriptorLite0.getLiteJavaType() == JavaType.MESSAGE && !fieldSet$FieldDescriptorLite0.isRepeated() && !fieldSet$FieldDescriptorLite0.isPacked()) {
            MessageLite messageLite0 = map$Entry0.getValue();
            if(messageLite0 instanceof LazyField) {
                messageLite0 = ((LazyField)messageLite0).getValue();
            }
            codedOutputStream0.writeMessageSetExtension(((FieldDescriptorLite)map$Entry0.getKey()).getNumber(), messageLite0);
            return;
        }
        FieldSet.writeField(fieldSet$FieldDescriptorLite0, map$Entry0.getValue(), codedOutputStream0);
    }

    public void writeMessageSetTo(CodedOutputStream codedOutputStream0) throws IOException {
        for(int v = 0; v < this.fields.getNumArrayEntries(); ++v) {
            this.writeMessageSetTo(this.fields.getArrayEntryAt(v), codedOutputStream0);
        }
        for(Object object0: this.fields.getOverflowEntries()) {
            this.writeMessageSetTo(((Map.Entry)object0), codedOutputStream0);
        }
    }

    public void writeTo(CodedOutputStream codedOutputStream0) throws IOException {
        for(int v = 0; v < this.fields.getNumArrayEntries(); ++v) {
            Map.Entry map$Entry0 = this.fields.getArrayEntryAt(v);
            FieldSet.writeField(((FieldDescriptorLite)map$Entry0.getKey()), map$Entry0.getValue(), codedOutputStream0);
        }
        for(Object object0: this.fields.getOverflowEntries()) {
            FieldSet.writeField(((FieldDescriptorLite)((Map.Entry)object0).getKey()), ((Map.Entry)object0).getValue(), codedOutputStream0);
        }
    }

    class com.google.crypto.tink.shaded.protobuf.FieldSet.1 {
        static final int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType;
        static final int[] $SwitchMap$com$google$protobuf$WireFormat$JavaType;

        static {
            int[] arr_v = new int[FieldType.values().length];
            com.google.crypto.tink.shaded.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType = arr_v;
            try {
                arr_v[FieldType.DOUBLE.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.FLOAT.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.INT64.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.UINT64.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.INT32.ordinal()] = 5;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.FIXED64.ordinal()] = 6;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.FIXED32.ordinal()] = 7;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.BOOL.ordinal()] = 8;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.GROUP.ordinal()] = 9;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.MESSAGE.ordinal()] = 10;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.STRING.ordinal()] = 11;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.BYTES.ordinal()] = 12;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.UINT32.ordinal()] = 13;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.SFIXED32.ordinal()] = 14;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.SFIXED64.ordinal()] = 15;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.SINT32.ordinal()] = 16;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.SINT64.ordinal()] = 17;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.ENUM.ordinal()] = 18;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            int[] arr_v1 = new int[JavaType.values().length];
            com.google.crypto.tink.shaded.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$JavaType = arr_v1;
            try {
                arr_v1[JavaType.INT.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$JavaType[JavaType.LONG.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$JavaType[JavaType.FLOAT.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$JavaType[JavaType.DOUBLE.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$JavaType[JavaType.BOOLEAN.ordinal()] = 5;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$JavaType[JavaType.STRING.ordinal()] = 6;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$JavaType[JavaType.BYTE_STRING.ordinal()] = 7;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$JavaType[JavaType.ENUM.ordinal()] = 8;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.FieldSet.1.$SwitchMap$com$google$protobuf$WireFormat$JavaType[JavaType.MESSAGE.ordinal()] = 9;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

