package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;

@CheckReturnValue
final class CodedOutputStreamWriter implements Writer {
    private final CodedOutputStream output;

    private CodedOutputStreamWriter(CodedOutputStream codedOutputStream0) {
        CodedOutputStream codedOutputStream1 = (CodedOutputStream)Internal.checkNotNull(codedOutputStream0, "output");
        this.output = codedOutputStream1;
        codedOutputStream1.wrapper = this;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public FieldOrder fieldOrder() {
        return FieldOrder.ASCENDING;
    }

    public static CodedOutputStreamWriter forCodedOutput(CodedOutputStream codedOutputStream0) {
        return codedOutputStream0.wrapper == null ? new CodedOutputStreamWriter(codedOutputStream0) : codedOutputStream0.wrapper;
    }

    public int getTotalBytesWritten() {
        return this.output.getTotalBytesWritten();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeBool(int v, boolean z) throws IOException {
        this.output.writeBool(v, z);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeBoolList(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.output.writeTag(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                v3 += CodedOutputStream.computeBoolSizeNoTag(((Boolean)list0.get(v2)).booleanValue());
            }
            this.output.writeUInt32NoTag(v3);
            while(v1 < list0.size()) {
                boolean z1 = ((Boolean)list0.get(v1)).booleanValue();
                this.output.writeBoolNoTag(z1);
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            boolean z2 = ((Boolean)list0.get(v1)).booleanValue();
            this.output.writeBool(v, z2);
            ++v1;
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeBytes(int v, ByteString byteString0) throws IOException {
        this.output.writeBytes(v, byteString0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeBytesList(int v, List list0) throws IOException {
        for(int v1 = 0; v1 < list0.size(); ++v1) {
            ByteString byteString0 = (ByteString)list0.get(v1);
            this.output.writeBytes(v, byteString0);
        }
    }

    private void writeDeterministicBooleanMapEntry(int v, boolean z, Object object0, Metadata mapEntryLite$Metadata0) throws IOException {
        this.output.writeTag(v, 2);
        int v1 = MapEntryLite.computeSerializedSize(mapEntryLite$Metadata0, Boolean.valueOf(z), object0);
        this.output.writeUInt32NoTag(v1);
        MapEntryLite.writeTo(this.output, mapEntryLite$Metadata0, Boolean.valueOf(z), object0);
    }

    private void writeDeterministicIntegerMap(int v, Metadata mapEntryLite$Metadata0, Map map0) throws IOException {
        int v1 = map0.size();
        int[] arr_v = new int[v1];
        int v3 = 0;
        for(Object object0: map0.keySet()) {
            arr_v[v3] = (int)(((Integer)object0));
            ++v3;
        }
        Arrays.sort(arr_v);
        for(int v2 = 0; v2 < v1; ++v2) {
            int v4 = arr_v[v2];
            Object object1 = map0.get(v4);
            this.output.writeTag(v, 2);
            int v5 = MapEntryLite.computeSerializedSize(mapEntryLite$Metadata0, v4, object1);
            this.output.writeUInt32NoTag(v5);
            MapEntryLite.writeTo(this.output, mapEntryLite$Metadata0, v4, object1);
        }
    }

    private void writeDeterministicLongMap(int v, Metadata mapEntryLite$Metadata0, Map map0) throws IOException {
        int v1 = map0.size();
        long[] arr_v = new long[v1];
        int v3 = 0;
        for(Object object0: map0.keySet()) {
            arr_v[v3] = (long)(((Long)object0));
            ++v3;
        }
        Arrays.sort(arr_v);
        for(int v2 = 0; v2 < v1; ++v2) {
            long v4 = arr_v[v2];
            Object object1 = map0.get(v4);
            this.output.writeTag(v, 2);
            int v5 = MapEntryLite.computeSerializedSize(mapEntryLite$Metadata0, v4, object1);
            this.output.writeUInt32NoTag(v5);
            MapEntryLite.writeTo(this.output, mapEntryLite$Metadata0, v4, object1);
        }
    }

    private void writeDeterministicMap(int v, Metadata mapEntryLite$Metadata0, Map map0) throws IOException {
        switch(com.google.crypto.tink.shaded.protobuf.CodedOutputStreamWriter.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[mapEntryLite$Metadata0.keyType.ordinal()]) {
            case 1: {
                Object object0 = map0.get(Boolean.FALSE);
                if(object0 != null) {
                    this.writeDeterministicBooleanMapEntry(v, false, object0, mapEntryLite$Metadata0);
                }
                Object object1 = map0.get(Boolean.TRUE);
                if(object1 != null) {
                    this.writeDeterministicBooleanMapEntry(v, true, object1, mapEntryLite$Metadata0);
                }
                return;
            }
            case 2: 
            case 3: 
            case 4: 
            case 5: 
            case 6: {
                this.writeDeterministicIntegerMap(v, mapEntryLite$Metadata0, map0);
                return;
            }
            case 7: 
            case 8: 
            case 9: 
            case 10: 
            case 11: {
                this.writeDeterministicLongMap(v, mapEntryLite$Metadata0, map0);
                return;
            }
            case 12: {
                this.writeDeterministicStringMap(v, mapEntryLite$Metadata0, map0);
                return;
            }
            default: {
                throw new IllegalArgumentException("does not support key type: " + mapEntryLite$Metadata0.keyType);
            }
        }
    }

    private void writeDeterministicStringMap(int v, Metadata mapEntryLite$Metadata0, Map map0) throws IOException {
        int v1 = map0.size();
        String[] arr_s = new String[v1];
        int v3 = 0;
        for(Object object0: map0.keySet()) {
            arr_s[v3] = (String)object0;
            ++v3;
        }
        Arrays.sort(arr_s);
        for(int v2 = 0; v2 < v1; ++v2) {
            String s = arr_s[v2];
            Object object1 = map0.get(s);
            this.output.writeTag(v, 2);
            int v4 = MapEntryLite.computeSerializedSize(mapEntryLite$Metadata0, s, object1);
            this.output.writeUInt32NoTag(v4);
            MapEntryLite.writeTo(this.output, mapEntryLite$Metadata0, s, object1);
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeDouble(int v, double f) throws IOException {
        this.output.writeDouble(v, f);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeDoubleList(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.output.writeTag(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                v3 += CodedOutputStream.computeDoubleSizeNoTag(((double)(((Double)list0.get(v2)))));
            }
            this.output.writeUInt32NoTag(v3);
            while(v1 < list0.size()) {
                double f = (double)(((Double)list0.get(v1)));
                this.output.writeDoubleNoTag(f);
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            double f1 = (double)(((Double)list0.get(v1)));
            this.output.writeDouble(v, f1);
            ++v1;
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    @Deprecated
    public void writeEndGroup(int v) throws IOException {
        this.output.writeTag(v, 4);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeEnum(int v, int v1) throws IOException {
        this.output.writeEnum(v, v1);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeEnumList(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.output.writeTag(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                v3 += CodedOutputStream.computeEnumSizeNoTag(((int)(((Integer)list0.get(v2)))));
            }
            this.output.writeUInt32NoTag(v3);
            while(v1 < list0.size()) {
                int v4 = (int)(((Integer)list0.get(v1)));
                this.output.writeEnumNoTag(v4);
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            int v5 = (int)(((Integer)list0.get(v1)));
            this.output.writeEnum(v, v5);
            ++v1;
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeFixed32(int v, int v1) throws IOException {
        this.output.writeFixed32(v, v1);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeFixed32List(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.output.writeTag(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                v3 += CodedOutputStream.computeFixed32SizeNoTag(((int)(((Integer)list0.get(v2)))));
            }
            this.output.writeUInt32NoTag(v3);
            while(v1 < list0.size()) {
                int v4 = (int)(((Integer)list0.get(v1)));
                this.output.writeFixed32NoTag(v4);
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            int v5 = (int)(((Integer)list0.get(v1)));
            this.output.writeFixed32(v, v5);
            ++v1;
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeFixed64(int v, long v1) throws IOException {
        this.output.writeFixed64(v, v1);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeFixed64List(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.output.writeTag(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                v3 += CodedOutputStream.computeFixed64SizeNoTag(((long)(((Long)list0.get(v2)))));
            }
            this.output.writeUInt32NoTag(v3);
            while(v1 < list0.size()) {
                long v4 = (long)(((Long)list0.get(v1)));
                this.output.writeFixed64NoTag(v4);
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            long v5 = (long)(((Long)list0.get(v1)));
            this.output.writeFixed64(v, v5);
            ++v1;
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeFloat(int v, float f) throws IOException {
        this.output.writeFloat(v, f);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeFloatList(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.output.writeTag(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                v3 += CodedOutputStream.computeFloatSizeNoTag(((float)(((Float)list0.get(v2)))));
            }
            this.output.writeUInt32NoTag(v3);
            while(v1 < list0.size()) {
                float f = (float)(((Float)list0.get(v1)));
                this.output.writeFloatNoTag(f);
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            float f1 = (float)(((Float)list0.get(v1)));
            this.output.writeFloat(v, f1);
            ++v1;
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    @Deprecated
    public void writeGroup(int v, Object object0) throws IOException {
        this.output.writeGroup(v, ((MessageLite)object0));
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeGroup(int v, Object object0, Schema schema0) throws IOException {
        this.output.writeGroup(v, ((MessageLite)object0), schema0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    @Deprecated
    public void writeGroupList(int v, List list0) throws IOException {
        for(int v1 = 0; v1 < list0.size(); ++v1) {
            this.writeGroup(v, list0.get(v1));
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeGroupList(int v, List list0, Schema schema0) throws IOException {
        for(int v1 = 0; v1 < list0.size(); ++v1) {
            this.writeGroup(v, list0.get(v1), schema0);
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeInt32(int v, int v1) throws IOException {
        this.output.writeInt32(v, v1);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeInt32List(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.output.writeTag(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                v3 += CodedOutputStream.computeInt32SizeNoTag(((int)(((Integer)list0.get(v2)))));
            }
            this.output.writeUInt32NoTag(v3);
            while(v1 < list0.size()) {
                int v4 = (int)(((Integer)list0.get(v1)));
                this.output.writeInt32NoTag(v4);
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            int v5 = (int)(((Integer)list0.get(v1)));
            this.output.writeInt32(v, v5);
            ++v1;
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeInt64(int v, long v1) throws IOException {
        this.output.writeInt64(v, v1);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeInt64List(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.output.writeTag(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                v3 += CodedOutputStream.computeInt64SizeNoTag(((long)(((Long)list0.get(v2)))));
            }
            this.output.writeUInt32NoTag(v3);
            while(v1 < list0.size()) {
                long v4 = (long)(((Long)list0.get(v1)));
                this.output.writeInt64NoTag(v4);
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            long v5 = (long)(((Long)list0.get(v1)));
            this.output.writeInt64(v, v5);
            ++v1;
        }
    }

    private void writeLazyString(int v, Object object0) throws IOException {
        if(object0 instanceof String) {
            this.output.writeString(v, ((String)object0));
            return;
        }
        this.output.writeBytes(v, ((ByteString)object0));
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeMap(int v, Metadata mapEntryLite$Metadata0, Map map0) throws IOException {
        if(this.output.isSerializationDeterministic()) {
            this.writeDeterministicMap(v, mapEntryLite$Metadata0, map0);
            return;
        }
        for(Object object0: map0.entrySet()) {
            this.output.writeTag(v, 2);
            int v1 = MapEntryLite.computeSerializedSize(mapEntryLite$Metadata0, ((Map.Entry)object0).getKey(), ((Map.Entry)object0).getValue());
            this.output.writeUInt32NoTag(v1);
            Object object1 = ((Map.Entry)object0).getKey();
            Object object2 = ((Map.Entry)object0).getValue();
            MapEntryLite.writeTo(this.output, mapEntryLite$Metadata0, object1, object2);
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeMessage(int v, Object object0) throws IOException {
        this.output.writeMessage(v, ((MessageLite)object0));
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeMessage(int v, Object object0, Schema schema0) throws IOException {
        this.output.writeMessage(v, ((MessageLite)object0), schema0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeMessageList(int v, List list0) throws IOException {
        for(int v1 = 0; v1 < list0.size(); ++v1) {
            this.writeMessage(v, list0.get(v1));
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeMessageList(int v, List list0, Schema schema0) throws IOException {
        for(int v1 = 0; v1 < list0.size(); ++v1) {
            this.writeMessage(v, list0.get(v1), schema0);
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public final void writeMessageSetItem(int v, Object object0) throws IOException {
        if(object0 instanceof ByteString) {
            this.output.writeRawMessageSetExtension(v, ((ByteString)object0));
            return;
        }
        this.output.writeMessageSetExtension(v, ((MessageLite)object0));
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeSFixed32(int v, int v1) throws IOException {
        this.output.writeSFixed32(v, v1);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeSFixed32List(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.output.writeTag(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                v3 += CodedOutputStream.computeSFixed32SizeNoTag(((int)(((Integer)list0.get(v2)))));
            }
            this.output.writeUInt32NoTag(v3);
            while(v1 < list0.size()) {
                int v4 = (int)(((Integer)list0.get(v1)));
                this.output.writeSFixed32NoTag(v4);
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            int v5 = (int)(((Integer)list0.get(v1)));
            this.output.writeSFixed32(v, v5);
            ++v1;
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeSFixed64(int v, long v1) throws IOException {
        this.output.writeSFixed64(v, v1);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeSFixed64List(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.output.writeTag(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                v3 += CodedOutputStream.computeSFixed64SizeNoTag(((long)(((Long)list0.get(v2)))));
            }
            this.output.writeUInt32NoTag(v3);
            while(v1 < list0.size()) {
                long v4 = (long)(((Long)list0.get(v1)));
                this.output.writeSFixed64NoTag(v4);
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            long v5 = (long)(((Long)list0.get(v1)));
            this.output.writeSFixed64(v, v5);
            ++v1;
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeSInt32(int v, int v1) throws IOException {
        this.output.writeSInt32(v, v1);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeSInt32List(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.output.writeTag(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                v3 += CodedOutputStream.computeSInt32SizeNoTag(((int)(((Integer)list0.get(v2)))));
            }
            this.output.writeUInt32NoTag(v3);
            while(v1 < list0.size()) {
                int v4 = (int)(((Integer)list0.get(v1)));
                this.output.writeSInt32NoTag(v4);
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            int v5 = (int)(((Integer)list0.get(v1)));
            this.output.writeSInt32(v, v5);
            ++v1;
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeSInt64(int v, long v1) throws IOException {
        this.output.writeSInt64(v, v1);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeSInt64List(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.output.writeTag(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                v3 += CodedOutputStream.computeSInt64SizeNoTag(((long)(((Long)list0.get(v2)))));
            }
            this.output.writeUInt32NoTag(v3);
            while(v1 < list0.size()) {
                long v4 = (long)(((Long)list0.get(v1)));
                this.output.writeSInt64NoTag(v4);
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            long v5 = (long)(((Long)list0.get(v1)));
            this.output.writeSInt64(v, v5);
            ++v1;
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    @Deprecated
    public void writeStartGroup(int v) throws IOException {
        this.output.writeTag(v, 3);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeString(int v, String s) throws IOException {
        this.output.writeString(v, s);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeStringList(int v, List list0) throws IOException {
        int v1 = 0;
        if(list0 instanceof LazyStringList) {
            while(v1 < list0.size()) {
                this.writeLazyString(v, ((LazyStringList)list0).getRaw(v1));
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            String s = (String)list0.get(v1);
            this.output.writeString(v, s);
            ++v1;
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeUInt32(int v, int v1) throws IOException {
        this.output.writeUInt32(v, v1);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeUInt32List(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.output.writeTag(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                v3 += CodedOutputStream.computeUInt32SizeNoTag(((int)(((Integer)list0.get(v2)))));
            }
            this.output.writeUInt32NoTag(v3);
            while(v1 < list0.size()) {
                int v4 = (int)(((Integer)list0.get(v1)));
                this.output.writeUInt32NoTag(v4);
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            int v5 = (int)(((Integer)list0.get(v1)));
            this.output.writeUInt32(v, v5);
            ++v1;
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeUInt64(int v, long v1) throws IOException {
        this.output.writeUInt64(v, v1);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Writer
    public void writeUInt64List(int v, List list0, boolean z) throws IOException {
        int v1 = 0;
        if(z) {
            this.output.writeTag(v, 2);
            int v3 = 0;
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                v3 += CodedOutputStream.computeUInt64SizeNoTag(((long)(((Long)list0.get(v2)))));
            }
            this.output.writeUInt32NoTag(v3);
            while(v1 < list0.size()) {
                long v4 = (long)(((Long)list0.get(v1)));
                this.output.writeUInt64NoTag(v4);
                ++v1;
            }
            return;
        }
        while(v1 < list0.size()) {
            long v5 = (long)(((Long)list0.get(v1)));
            this.output.writeUInt64(v, v5);
            ++v1;
        }
    }

    class com.google.crypto.tink.shaded.protobuf.CodedOutputStreamWriter.1 {
        static final int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType;

        static {
            int[] arr_v = new int[FieldType.values().length];
            com.google.crypto.tink.shaded.protobuf.CodedOutputStreamWriter.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType = arr_v;
            try {
                arr_v[FieldType.BOOL.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.CodedOutputStreamWriter.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.FIXED32.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.CodedOutputStreamWriter.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.INT32.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.CodedOutputStreamWriter.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.SFIXED32.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.CodedOutputStreamWriter.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.SINT32.ordinal()] = 5;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.CodedOutputStreamWriter.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.UINT32.ordinal()] = 6;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.CodedOutputStreamWriter.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.FIXED64.ordinal()] = 7;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.CodedOutputStreamWriter.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.INT64.ordinal()] = 8;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.CodedOutputStreamWriter.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.SFIXED64.ordinal()] = 9;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.CodedOutputStreamWriter.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.SINT64.ordinal()] = 10;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.CodedOutputStreamWriter.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.UINT64.ordinal()] = 11;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.CodedOutputStreamWriter.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.STRING.ordinal()] = 12;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

