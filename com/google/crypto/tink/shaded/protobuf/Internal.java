package com.google.crypto.tink.shaded.protobuf;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.AbstractList;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.RandomAccess;
import java.util.Set;

public final class Internal {
    public interface BooleanList extends ProtobufList {
        void addBoolean(boolean arg1);

        boolean getBoolean(int arg1);

        BooleanList mutableCopyWithCapacity(int arg1);

        boolean setBoolean(int arg1, boolean arg2);
    }

    public interface DoubleList extends ProtobufList {
        void addDouble(double arg1);

        double getDouble(int arg1);

        DoubleList mutableCopyWithCapacity(int arg1);

        double setDouble(int arg1, double arg2);
    }

    public interface EnumLite {
        int getNumber();
    }

    public interface EnumLiteMap {
        EnumLite findValueByNumber(int arg1);
    }

    public interface EnumVerifier {
        boolean isInRange(int arg1);
    }

    public interface FloatList extends ProtobufList {
        void addFloat(float arg1);

        float getFloat(int arg1);

        FloatList mutableCopyWithCapacity(int arg1);

        float setFloat(int arg1, float arg2);
    }

    public interface IntList extends ProtobufList {
        void addInt(int arg1);

        int getInt(int arg1);

        IntList mutableCopyWithCapacity(int arg1);

        int setInt(int arg1, int arg2);
    }

    public static class ListAdapter extends AbstractList {
        public interface Converter {
            Object convert(Object arg1);
        }

        private final Converter converter;
        private final List fromList;

        public ListAdapter(List list0, Converter internal$ListAdapter$Converter0) {
            this.fromList = list0;
            this.converter = internal$ListAdapter$Converter0;
        }

        @Override
        public Object get(int v) {
            Object object0 = this.fromList.get(v);
            return this.converter.convert(object0);
        }

        @Override
        public int size() {
            return this.fromList.size();
        }
    }

    public interface LongList extends ProtobufList {
        void addLong(long arg1);

        long getLong(int arg1);

        LongList mutableCopyWithCapacity(int arg1);

        long setLong(int arg1, long arg2);
    }

    public static class MapAdapter extends AbstractMap {
        public interface com.google.crypto.tink.shaded.protobuf.Internal.MapAdapter.Converter {
            Object doBackward(Object arg1);

            Object doForward(Object arg1);
        }

        class EntryAdapter implements Map.Entry {
            private final Map.Entry realEntry;

            public EntryAdapter(Map.Entry map$Entry0) {
                this.realEntry = map$Entry0;
            }

            @Override
            public boolean equals(Object object0) {
                if(object0 == this) {
                    return true;
                }
                return object0 instanceof Map.Entry ? this.getKey().equals(((Map.Entry)object0).getKey()) && this.getValue().equals(this.getValue()) : false;
            }

            @Override
            public Object getKey() {
                return this.realEntry.getKey();
            }

            @Override
            public Object getValue() {
                return MapAdapter.this.valueConverter.doForward(this.realEntry.getValue());
            }

            @Override
            public int hashCode() {
                return this.realEntry.hashCode();
            }

            @Override
            public Object setValue(Object object0) {
                Object object1 = MapAdapter.this.valueConverter.doBackward(object0);
                Object object2 = this.realEntry.setValue(object1);
                return object2 == null ? null : MapAdapter.this.valueConverter.doForward(object2);
            }
        }

        class IteratorAdapter implements Iterator {
            private final Iterator realIterator;

            public IteratorAdapter(Iterator iterator0) {
                this.realIterator = iterator0;
            }

            @Override
            public boolean hasNext() {
                return this.realIterator.hasNext();
            }

            @Override
            public Object next() {
                return this.next();
            }

            public Map.Entry next() {
                Object object0 = this.realIterator.next();
                return new EntryAdapter(MapAdapter.this, ((Map.Entry)object0));
            }

            @Override
            public void remove() {
                this.realIterator.remove();
            }
        }

        class SetAdapter extends AbstractSet {
            private final Set realSet;

            public SetAdapter(Set set0) {
                this.realSet = set0;
            }

            @Override
            public Iterator iterator() {
                Iterator iterator0 = this.realSet.iterator();
                return new IteratorAdapter(MapAdapter.this, iterator0);
            }

            @Override
            public int size() {
                return this.realSet.size();
            }
        }

        private final Map realMap;
        private final com.google.crypto.tink.shaded.protobuf.Internal.MapAdapter.Converter valueConverter;

        public MapAdapter(Map map0, com.google.crypto.tink.shaded.protobuf.Internal.MapAdapter.Converter internal$MapAdapter$Converter0) {
            this.realMap = map0;
            this.valueConverter = internal$MapAdapter$Converter0;
        }

        @Override
        public Set entrySet() {
            return new SetAdapter(this, this.realMap.entrySet());
        }

        @Override
        public Object get(Object object0) {
            Object object1 = this.realMap.get(object0);
            return object1 == null ? null : this.valueConverter.doForward(object1);
        }

        public static com.google.crypto.tink.shaded.protobuf.Internal.MapAdapter.Converter newEnumConverter(EnumLiteMap internal$EnumLiteMap0, EnumLite internal$EnumLite0) {
            return new com.google.crypto.tink.shaded.protobuf.Internal.MapAdapter.Converter() {
                public Integer doBackward(EnumLite internal$EnumLite0) {
                    return internal$EnumLite0.getNumber();
                }

                @Override  // com.google.crypto.tink.shaded.protobuf.Internal$MapAdapter$Converter
                public Object doBackward(Object object0) {
                    return this.doBackward(((EnumLite)object0));
                }

                public EnumLite doForward(Integer integer0) {
                    EnumLite internal$EnumLite0 = internal$EnumLiteMap0.findValueByNumber(((int)integer0));
                    return internal$EnumLite0 == null ? internal$EnumLite0 : internal$EnumLite0;
                }

                @Override  // com.google.crypto.tink.shaded.protobuf.Internal$MapAdapter$Converter
                public Object doForward(Object object0) {
                    return this.doForward(((Integer)object0));
                }
            };
        }

        @Override
        public Object put(Object object0, Object object1) {
            Object object2 = this.valueConverter.doBackward(object1);
            Object object3 = this.realMap.put(object0, object2);
            return object3 == null ? null : this.valueConverter.doForward(object3);
        }
    }

    public interface ProtobufList extends List, RandomAccess {
        boolean isModifiable();

        void makeImmutable();

        ProtobufList mutableCopyWithCapacity(int arg1);
    }

    private static final int DEFAULT_BUFFER_SIZE = 0x1000;
    public static final byte[] EMPTY_BYTE_ARRAY;
    public static final ByteBuffer EMPTY_BYTE_BUFFER;
    public static final CodedInputStream EMPTY_CODED_INPUT_STREAM;
    static final Charset ISO_8859_1;
    static final Charset US_ASCII;
    static final Charset UTF_8;

    static {
        Internal.US_ASCII = Charset.forName("US-ASCII");
        Internal.UTF_8 = Charset.forName("UTF-8");
        Internal.ISO_8859_1 = Charset.forName("ISO-8859-1");
        byte[] arr_b = new byte[0];
        Internal.EMPTY_BYTE_ARRAY = arr_b;
        Internal.EMPTY_BYTE_BUFFER = ByteBuffer.wrap(arr_b);
        Internal.EMPTY_CODED_INPUT_STREAM = CodedInputStream.newInstance(arr_b);
    }

    public static byte[] byteArrayDefaultValue(String s) {
        return s.getBytes(Internal.ISO_8859_1);
    }

    public static ByteBuffer byteBufferDefaultValue(String s) {
        return ByteBuffer.wrap(Internal.byteArrayDefaultValue(s));
    }

    public static ByteString bytesDefaultValue(String s) {
        return ByteString.copyFrom(s.getBytes(Internal.ISO_8859_1));
    }

    static Object checkNotNull(Object object0) {
        object0.getClass();
        return object0;
    }

    static Object checkNotNull(Object object0, String s) {
        if(object0 == null) {
            throw new NullPointerException(s);
        }
        return object0;
    }

    public static ByteBuffer copyByteBuffer(ByteBuffer byteBuffer0) {
        ByteBuffer byteBuffer1 = byteBuffer0.duplicate();
        byteBuffer1.clear();
        ByteBuffer byteBuffer2 = ByteBuffer.allocate(byteBuffer1.capacity());
        byteBuffer2.put(byteBuffer1);
        byteBuffer2.clear();
        return byteBuffer2;
    }

    public static boolean equals(List list0, List list1) {
        if(list0.size() != list1.size()) {
            return false;
        }
        for(int v = 0; v < list0.size(); ++v) {
            if(!Arrays.equals(((byte[])list0.get(v)), ((byte[])list1.get(v)))) {
                return false;
            }
        }
        return true;
    }

    public static boolean equalsByteBuffer(ByteBuffer byteBuffer0, ByteBuffer byteBuffer1) {
        return byteBuffer0.capacity() == byteBuffer1.capacity() ? byteBuffer0.duplicate().clear().equals(byteBuffer1.duplicate().clear()) : false;
    }

    public static boolean equalsByteBuffer(List list0, List list1) {
        if(list0.size() != list1.size()) {
            return false;
        }
        for(int v = 0; v < list0.size(); ++v) {
            if(!Internal.equalsByteBuffer(((ByteBuffer)list0.get(v)), ((ByteBuffer)list1.get(v)))) {
                return false;
            }
        }
        return true;
    }

    public static MessageLite getDefaultInstance(Class class0) {
        try {
            Method method0 = class0.getMethod("getDefaultInstance", null);
            return (MessageLite)method0.invoke(method0, null);
        }
        catch(Exception exception0) {
            throw new RuntimeException("Failed to get default instance for " + class0, exception0);
        }
    }

    // 去混淆评级： 低(20)
    public static int hashBoolean(boolean b) {
        return b ? 0x4CF : 0x4D5;
    }

    public static int hashCode(List list0) {
        int v = 1;
        for(Object object0: list0) {
            v = v * 0x1F + Internal.hashCode(((byte[])object0));
        }
        return v;
    }

    public static int hashCode(byte[] arr_b) {
        return Internal.hashCode(arr_b, 0, arr_b.length);
    }

    static int hashCode(byte[] arr_b, int v, int v1) {
        int v2 = Internal.partialHash(v1, arr_b, v, v1);
        return v2 == 0 ? 1 : v2;
    }

    public static int hashCodeByteBuffer(ByteBuffer byteBuffer0) {
        int v = 0x1000;
        if(byteBuffer0.hasArray()) {
            int v1 = Internal.partialHash(byteBuffer0.capacity(), byteBuffer0.array(), byteBuffer0.arrayOffset(), byteBuffer0.capacity());
            return v1 == 0 ? 1 : v1;
        }
        if(byteBuffer0.capacity() <= 0x1000) {
            v = byteBuffer0.capacity();
        }
        byte[] arr_b = new byte[v];
        ByteBuffer byteBuffer1 = byteBuffer0.duplicate();
        byteBuffer1.clear();
        int v2;
        for(v2 = byteBuffer0.capacity(); byteBuffer1.remaining() > 0; v2 = Internal.partialHash(v2, arr_b, 0, v3)) {
            int v3 = byteBuffer1.remaining() > v ? v : byteBuffer1.remaining();
            byteBuffer1.get(arr_b, 0, v3);
        }
        return v2 == 0 ? 1 : v2;
    }

    public static int hashCodeByteBuffer(List list0) {
        int v = 1;
        for(Object object0: list0) {
            v = v * 0x1F + Internal.hashCodeByteBuffer(((ByteBuffer)object0));
        }
        return v;
    }

    public static int hashEnum(EnumLite internal$EnumLite0) {
        return internal$EnumLite0.getNumber();
    }

    public static int hashEnumList(List list0) {
        int v = 1;
        for(Object object0: list0) {
            v = v * 0x1F + Internal.hashEnum(((EnumLite)object0));
        }
        return v;
    }

    public static int hashLong(long n) {
        return (int)(n ^ n >>> 0x20);
    }

    public static boolean isValidUtf8(ByteString byteString0) {
        return byteString0.isValidUtf8();
    }

    public static boolean isValidUtf8(byte[] arr_b) {
        return Utf8.isValidUtf8(arr_b);
    }

    static Object mergeMessage(Object object0, Object object1) {
        return ((MessageLite)object0).toBuilder().mergeFrom(((MessageLite)object1)).buildPartial();
    }

    static int partialHash(int v, byte[] arr_b, int v1, int v2) {
        for(int v3 = v1; v3 < v1 + v2; ++v3) {
            v = v * 0x1F + arr_b[v3];
        }
        return v;
    }

    public static String stringDefaultValue(String s) {
        return new String(s.getBytes(Internal.ISO_8859_1), Internal.UTF_8);
    }

    public static byte[] toByteArray(String s) {
        return s.getBytes(Internal.UTF_8);
    }

    public static String toStringUtf8(byte[] arr_b) {
        return new String(arr_b, Internal.UTF_8);
    }
}

