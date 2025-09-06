package com.google.crypto.tink.shaded.protobuf;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

final class UnsafeUtil {
    static final class Android32MemoryAccessor extends MemoryAccessor {
        private static final long SMALL_ADDRESS_MASK = -1L;

        Android32MemoryAccessor(Unsafe unsafe0) {
            super(unsafe0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public void copyMemory(long v, byte[] arr_b, long v1, long v2) {
            throw new UnsupportedOperationException();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public void copyMemory(byte[] arr_b, long v, long v1, long v2) {
            throw new UnsupportedOperationException();
        }

        // 去混淆评级： 低(20)
        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public boolean getBoolean(Object object0, long v) {
            return UnsafeUtil.IS_BIG_ENDIAN ? UnsafeUtil.getBooleanBigEndian(object0, v) : UnsafeUtil.getBooleanLittleEndian(object0, v);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public byte getByte(long v) {
            throw new UnsupportedOperationException();
        }

        // 去混淆评级： 低(20)
        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public byte getByte(Object object0, long v) {
            return UnsafeUtil.IS_BIG_ENDIAN ? UnsafeUtil.getByteBigEndian(object0, v) : UnsafeUtil.getByteLittleEndian(object0, v);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public double getDouble(Object object0, long v) {
            return Double.longBitsToDouble(this.getLong(object0, v));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public float getFloat(Object object0, long v) {
            return Float.intBitsToFloat(this.getInt(object0, v));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public int getInt(long v) {
            throw new UnsupportedOperationException();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public long getLong(long v) {
            throw new UnsupportedOperationException();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public Object getStaticObject(Field field0) {
            try {
                return field0.get(null);
            }
            catch(IllegalAccessException unused_ex) {
                return null;
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public void putBoolean(Object object0, long v, boolean z) {
            if(UnsafeUtil.IS_BIG_ENDIAN) {
                UnsafeUtil.putBooleanBigEndian(object0, v, z);
                return;
            }
            UnsafeUtil.putBooleanLittleEndian(object0, v, z);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public void putByte(long v, byte b) {
            throw new UnsupportedOperationException();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public void putByte(Object object0, long v, byte b) {
            if(UnsafeUtil.IS_BIG_ENDIAN) {
                UnsafeUtil.putByteBigEndian(object0, v, b);
                return;
            }
            UnsafeUtil.putByteLittleEndian(object0, v, b);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public void putDouble(Object object0, long v, double f) {
            this.putLong(object0, v, Double.doubleToLongBits(f));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public void putFloat(Object object0, long v, float f) {
            this.putInt(object0, v, Float.floatToIntBits(f));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public void putInt(long v, int v1) {
            throw new UnsupportedOperationException();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public void putLong(long v, long v1) {
            throw new UnsupportedOperationException();
        }

        private static int smallAddress(long address) {
            return (int)address;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public boolean supportsUnsafeByteBufferOperations() {
            return false;
        }
    }

    static final class Android64MemoryAccessor extends MemoryAccessor {
        Android64MemoryAccessor(Unsafe unsafe0) {
            super(unsafe0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public void copyMemory(long v, byte[] arr_b, long v1, long v2) {
            throw new UnsupportedOperationException();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public void copyMemory(byte[] arr_b, long v, long v1, long v2) {
            throw new UnsupportedOperationException();
        }

        // 去混淆评级： 低(20)
        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public boolean getBoolean(Object object0, long v) {
            return UnsafeUtil.IS_BIG_ENDIAN ? UnsafeUtil.getBooleanBigEndian(object0, v) : UnsafeUtil.getBooleanLittleEndian(object0, v);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public byte getByte(long v) {
            throw new UnsupportedOperationException();
        }

        // 去混淆评级： 低(20)
        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public byte getByte(Object object0, long v) {
            return UnsafeUtil.IS_BIG_ENDIAN ? UnsafeUtil.getByteBigEndian(object0, v) : UnsafeUtil.getByteLittleEndian(object0, v);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public double getDouble(Object object0, long v) {
            return Double.longBitsToDouble(this.getLong(object0, v));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public float getFloat(Object object0, long v) {
            return Float.intBitsToFloat(this.getInt(object0, v));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public int getInt(long v) {
            throw new UnsupportedOperationException();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public long getLong(long v) {
            throw new UnsupportedOperationException();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public Object getStaticObject(Field field0) {
            try {
                return field0.get(null);
            }
            catch(IllegalAccessException unused_ex) {
                return null;
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public void putBoolean(Object object0, long v, boolean z) {
            if(UnsafeUtil.IS_BIG_ENDIAN) {
                UnsafeUtil.putBooleanBigEndian(object0, v, z);
                return;
            }
            UnsafeUtil.putBooleanLittleEndian(object0, v, z);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public void putByte(long v, byte b) {
            throw new UnsupportedOperationException();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public void putByte(Object object0, long v, byte b) {
            if(UnsafeUtil.IS_BIG_ENDIAN) {
                UnsafeUtil.putByteBigEndian(object0, v, b);
                return;
            }
            UnsafeUtil.putByteLittleEndian(object0, v, b);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public void putDouble(Object object0, long v, double f) {
            this.putLong(object0, v, Double.doubleToLongBits(f));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public void putFloat(Object object0, long v, float f) {
            this.putInt(object0, v, Float.floatToIntBits(f));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public void putInt(long v, int v1) {
            throw new UnsupportedOperationException();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public void putLong(long v, long v1) {
            throw new UnsupportedOperationException();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public boolean supportsUnsafeByteBufferOperations() {
            return false;
        }
    }

    static final class JvmMemoryAccessor extends MemoryAccessor {
        JvmMemoryAccessor(Unsafe unsafe0) {
            super(unsafe0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public void copyMemory(long v, byte[] arr_b, long v1, long v2) {
            this.unsafe.copyMemory(null, v, arr_b, UnsafeUtil.BYTE_ARRAY_BASE_OFFSET + v1, v2);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public void copyMemory(byte[] arr_b, long v, long v1, long v2) {
            this.unsafe.copyMemory(arr_b, UnsafeUtil.BYTE_ARRAY_BASE_OFFSET + v, null, v1, v2);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public boolean getBoolean(Object object0, long v) {
            return this.unsafe.getBoolean(object0, v);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public byte getByte(long v) {
            return this.unsafe.getByte(v);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public byte getByte(Object object0, long v) {
            return this.unsafe.getByte(object0, v);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public double getDouble(Object object0, long v) {
            return this.unsafe.getDouble(object0, v);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public float getFloat(Object object0, long v) {
            return this.unsafe.getFloat(object0, v);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public int getInt(long v) {
            return this.unsafe.getInt(v);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public long getLong(long v) {
            return this.unsafe.getLong(v);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public Object getStaticObject(Field field0) {
            return this.getObject(this.unsafe.staticFieldBase(field0), this.unsafe.staticFieldOffset(field0));
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public void putBoolean(Object object0, long v, boolean z) {
            this.unsafe.putBoolean(object0, v, z);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public void putByte(long v, byte b) {
            this.unsafe.putByte(v, b);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public void putByte(Object object0, long v, byte b) {
            this.unsafe.putByte(object0, v, b);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public void putDouble(Object object0, long v, double f) {
            this.unsafe.putDouble(object0, v, f);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public void putFloat(Object object0, long v, float f) {
            this.unsafe.putFloat(object0, v, f);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public void putInt(long v, int v1) {
            this.unsafe.putInt(v, v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public void putLong(long v, long v1) {
            this.unsafe.putLong(v, v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public boolean supportsUnsafeArrayOperations() {
            if(!super.supportsUnsafeArrayOperations()) {
                return false;
            }
            try {
                Class class0 = this.unsafe.getClass();
                class0.getMethod("getByte", Object.class, Long.TYPE);
                class0.getMethod("putByte", Object.class, Long.TYPE, Byte.TYPE);
                class0.getMethod("getBoolean", Object.class, Long.TYPE);
                class0.getMethod("putBoolean", Object.class, Long.TYPE, Boolean.TYPE);
                class0.getMethod("getFloat", Object.class, Long.TYPE);
                class0.getMethod("putFloat", Object.class, Long.TYPE, Float.TYPE);
                class0.getMethod("getDouble", Object.class, Long.TYPE);
                class0.getMethod("putDouble", Object.class, Long.TYPE, Double.TYPE);
                return true;
            }
            catch(Throwable throwable0) {
                UnsafeUtil.logMissingMethod(throwable0);
                return false;
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.UnsafeUtil$MemoryAccessor
        public boolean supportsUnsafeByteBufferOperations() {
            if(!super.supportsUnsafeByteBufferOperations()) {
                return false;
            }
            try {
                Class class0 = this.unsafe.getClass();
                class0.getMethod("getByte", Long.TYPE);
                class0.getMethod("putByte", Long.TYPE, Byte.TYPE);
                class0.getMethod("getInt", Long.TYPE);
                class0.getMethod("putInt", Long.TYPE, Integer.TYPE);
                class0.getMethod("getLong", Long.TYPE);
                class0.getMethod("putLong", Long.TYPE, Long.TYPE);
                class0.getMethod("copyMemory", Long.TYPE, Long.TYPE, Long.TYPE);
                class0.getMethod("copyMemory", Object.class, Long.TYPE, Object.class, Long.TYPE, Long.TYPE);
                return true;
            }
            catch(Throwable throwable0) {
                UnsafeUtil.logMissingMethod(throwable0);
                return false;
            }
        }
    }

    static abstract class MemoryAccessor {
        Unsafe unsafe;

        MemoryAccessor(Unsafe unsafe0) {
            this.unsafe = unsafe0;
        }

        public final int arrayBaseOffset(Class class0) {
            return this.unsafe.arrayBaseOffset(class0);
        }

        public final int arrayIndexScale(Class class0) {
            return this.unsafe.arrayIndexScale(class0);
        }

        public abstract void copyMemory(long arg1, byte[] arg2, long arg3, long arg4);

        public abstract void copyMemory(byte[] arg1, long arg2, long arg3, long arg4);

        public abstract boolean getBoolean(Object arg1, long arg2);

        public abstract byte getByte(long arg1);

        public abstract byte getByte(Object arg1, long arg2);

        public abstract double getDouble(Object arg1, long arg2);

        public abstract float getFloat(Object arg1, long arg2);

        public abstract int getInt(long arg1);

        public final int getInt(Object object0, long v) {
            return this.unsafe.getInt(object0, v);
        }

        public abstract long getLong(long arg1);

        public final long getLong(Object object0, long v) {
            return this.unsafe.getLong(object0, v);
        }

        public final Object getObject(Object object0, long v) {
            return this.unsafe.getObject(object0, v);
        }

        public abstract Object getStaticObject(Field arg1);

        public final long objectFieldOffset(Field field0) {
            return this.unsafe.objectFieldOffset(field0);
        }

        public abstract void putBoolean(Object arg1, long arg2, boolean arg3);

        public abstract void putByte(long arg1, byte arg2);

        public abstract void putByte(Object arg1, long arg2, byte arg3);

        public abstract void putDouble(Object arg1, long arg2, double arg3);

        public abstract void putFloat(Object arg1, long arg2, float arg3);

        public abstract void putInt(long arg1, int arg2);

        public final void putInt(Object object0, long v, int v1) {
            this.unsafe.putInt(object0, v, v1);
        }

        public abstract void putLong(long arg1, long arg2);

        public final void putLong(Object object0, long v, long v1) {
            this.unsafe.putLong(object0, v, v1);
        }

        public final void putObject(Object object0, long v, Object object1) {
            this.unsafe.putObject(object0, v, object1);
        }

        public boolean supportsUnsafeArrayOperations() {
            Unsafe unsafe0 = this.unsafe;
            if(unsafe0 == null) {
                return false;
            }
            try {
                Class class0 = unsafe0.getClass();
                class0.getMethod("objectFieldOffset", Field.class);
                class0.getMethod("arrayBaseOffset", Class.class);
                class0.getMethod("arrayIndexScale", Class.class);
                class0.getMethod("getInt", Object.class, Long.TYPE);
                class0.getMethod("putInt", Object.class, Long.TYPE, Integer.TYPE);
                class0.getMethod("getLong", Object.class, Long.TYPE);
                class0.getMethod("putLong", Object.class, Long.TYPE, Long.TYPE);
                class0.getMethod("getObject", Object.class, Long.TYPE);
                class0.getMethod("putObject", Object.class, Long.TYPE, Object.class);
                return true;
            }
            catch(Throwable throwable0) {
                UnsafeUtil.logMissingMethod(throwable0);
                return false;
            }
        }

        public boolean supportsUnsafeByteBufferOperations() {
            Unsafe unsafe0 = this.unsafe;
            if(unsafe0 == null) {
                return false;
            }
            try {
                Class class0 = unsafe0.getClass();
                class0.getMethod("objectFieldOffset", Field.class);
                class0.getMethod("getLong", Object.class, Long.TYPE);
                return UnsafeUtil.bufferAddressField() == null ? false : true;
            }
            catch(Throwable throwable0) {
                UnsafeUtil.logMissingMethod(throwable0);
                return false;
            }
        }
    }

    private static final long BOOLEAN_ARRAY_BASE_OFFSET = 0L;
    private static final long BOOLEAN_ARRAY_INDEX_SCALE = 0L;
    private static final long BUFFER_ADDRESS_OFFSET = 0L;
    private static final int BYTE_ARRAY_ALIGNMENT = 0;
    static final long BYTE_ARRAY_BASE_OFFSET = 0L;
    private static final long DOUBLE_ARRAY_BASE_OFFSET = 0L;
    private static final long DOUBLE_ARRAY_INDEX_SCALE = 0L;
    private static final long FLOAT_ARRAY_BASE_OFFSET = 0L;
    private static final long FLOAT_ARRAY_INDEX_SCALE = 0L;
    private static final boolean HAS_UNSAFE_ARRAY_OPERATIONS = false;
    private static final boolean HAS_UNSAFE_BYTEBUFFER_OPERATIONS = false;
    private static final long INT_ARRAY_BASE_OFFSET = 0L;
    private static final long INT_ARRAY_INDEX_SCALE = 0L;
    private static final boolean IS_ANDROID_32 = false;
    private static final boolean IS_ANDROID_64 = false;
    static final boolean IS_BIG_ENDIAN = false;
    private static final long LONG_ARRAY_BASE_OFFSET = 0L;
    private static final long LONG_ARRAY_INDEX_SCALE = 0L;
    private static final MemoryAccessor MEMORY_ACCESSOR = null;
    private static final Class MEMORY_CLASS = null;
    private static final long OBJECT_ARRAY_BASE_OFFSET = 0L;
    private static final long OBJECT_ARRAY_INDEX_SCALE = 0L;
    private static final int STRIDE = 8;
    private static final int STRIDE_ALIGNMENT_MASK = 7;
    private static final Unsafe UNSAFE;

    static {
        UnsafeUtil.UNSAFE = UnsafeUtil.getUnsafe();
        UnsafeUtil.MEMORY_CLASS = Android.getMemoryClass();
        UnsafeUtil.IS_ANDROID_64 = UnsafeUtil.determineAndroidSupportByAddressSize(Long.TYPE);
        UnsafeUtil.IS_ANDROID_32 = UnsafeUtil.determineAndroidSupportByAddressSize(Integer.TYPE);
        UnsafeUtil.MEMORY_ACCESSOR = UnsafeUtil.getMemoryAccessor();
        UnsafeUtil.HAS_UNSAFE_BYTEBUFFER_OPERATIONS = UnsafeUtil.supportsUnsafeByteBufferOperations();
        UnsafeUtil.HAS_UNSAFE_ARRAY_OPERATIONS = UnsafeUtil.supportsUnsafeArrayOperations();
        long v = (long)UnsafeUtil.arrayBaseOffset(byte[].class);
        UnsafeUtil.BYTE_ARRAY_BASE_OFFSET = v;
        UnsafeUtil.BOOLEAN_ARRAY_BASE_OFFSET = (long)UnsafeUtil.arrayBaseOffset(boolean[].class);
        UnsafeUtil.BOOLEAN_ARRAY_INDEX_SCALE = (long)UnsafeUtil.arrayIndexScale(boolean[].class);
        UnsafeUtil.INT_ARRAY_BASE_OFFSET = (long)UnsafeUtil.arrayBaseOffset(int[].class);
        UnsafeUtil.INT_ARRAY_INDEX_SCALE = (long)UnsafeUtil.arrayIndexScale(int[].class);
        UnsafeUtil.LONG_ARRAY_BASE_OFFSET = (long)UnsafeUtil.arrayBaseOffset(long[].class);
        UnsafeUtil.LONG_ARRAY_INDEX_SCALE = (long)UnsafeUtil.arrayIndexScale(long[].class);
        UnsafeUtil.FLOAT_ARRAY_BASE_OFFSET = (long)UnsafeUtil.arrayBaseOffset(float[].class);
        UnsafeUtil.FLOAT_ARRAY_INDEX_SCALE = (long)UnsafeUtil.arrayIndexScale(float[].class);
        UnsafeUtil.DOUBLE_ARRAY_BASE_OFFSET = (long)UnsafeUtil.arrayBaseOffset(double[].class);
        UnsafeUtil.DOUBLE_ARRAY_INDEX_SCALE = (long)UnsafeUtil.arrayIndexScale(double[].class);
        UnsafeUtil.OBJECT_ARRAY_BASE_OFFSET = (long)UnsafeUtil.arrayBaseOffset(Object[].class);
        UnsafeUtil.OBJECT_ARRAY_INDEX_SCALE = (long)UnsafeUtil.arrayIndexScale(Object[].class);
        UnsafeUtil.BUFFER_ADDRESS_OFFSET = UnsafeUtil.fieldOffset(UnsafeUtil.bufferAddressField());
        UnsafeUtil.BYTE_ARRAY_ALIGNMENT = (int)(v & 7L);
        UnsafeUtil.IS_BIG_ENDIAN = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
    }

    static long addressOffset(ByteBuffer byteBuffer0) {
        return UnsafeUtil.MEMORY_ACCESSOR.getLong(byteBuffer0, UnsafeUtil.BUFFER_ADDRESS_OFFSET);
    }

    static Object allocateInstance(Class class0) {
        try {
            return UnsafeUtil.UNSAFE.allocateInstance(class0);
        }
        catch(InstantiationException instantiationException0) {
            throw new IllegalStateException(instantiationException0);
        }
    }

    // 去混淆评级： 低(20)
    private static int arrayBaseOffset(Class class0) {
        return UnsafeUtil.HAS_UNSAFE_ARRAY_OPERATIONS ? UnsafeUtil.MEMORY_ACCESSOR.arrayBaseOffset(class0) : -1;
    }

    // 去混淆评级： 低(20)
    private static int arrayIndexScale(Class class0) {
        return UnsafeUtil.HAS_UNSAFE_ARRAY_OPERATIONS ? UnsafeUtil.MEMORY_ACCESSOR.arrayIndexScale(class0) : -1;
    }

    private static Field bufferAddressField() {
        Field field0 = UnsafeUtil.field(Buffer.class, "address");
        return field0 == null || field0.getType() != Long.TYPE ? null : field0;
    }

    static void copyMemory(long v, byte[] arr_b, long v1, long v2) {
        UnsafeUtil.MEMORY_ACCESSOR.copyMemory(v, arr_b, v1, v2);
    }

    static void copyMemory(byte[] arr_b, long v, long v1, long v2) {
        UnsafeUtil.MEMORY_ACCESSOR.copyMemory(arr_b, v, v1, v2);
    }

    static void copyMemory(byte[] arr_b, long v, byte[] arr_b1, long v1, long v2) {
        System.arraycopy(arr_b, ((int)v), arr_b1, ((int)v1), ((int)v2));
    }

    // 去混淆评级： 低(30)
    static boolean determineAndroidSupportByAddressSize(Class class0) {
        return false;
    }

    private static Field field(Class class0, String s) {
        try {
            return class0.getDeclaredField(s);
        }
        catch(Throwable unused_ex) {
            return null;
        }
    }

    private static long fieldOffset(Field field0) {
        if(field0 != null) {
            return UnsafeUtil.MEMORY_ACCESSOR == null ? -1L : UnsafeUtil.MEMORY_ACCESSOR.objectFieldOffset(field0);
        }
        return -1L;
    }

    // 去混淆评级： 低(20)
    private static int firstDifferingByteIndexNativeEndian(long v, long v1) {
        return UnsafeUtil.IS_BIG_ENDIAN ? Long.numberOfLeadingZeros(v ^ v1) >> 3 : Long.numberOfTrailingZeros(v ^ v1) >> 3;
    }

    static boolean getBoolean(Object object0, long v) {
        return UnsafeUtil.MEMORY_ACCESSOR.getBoolean(object0, v);
    }

    static boolean getBoolean(boolean[] arr_z, long v) {
        return UnsafeUtil.MEMORY_ACCESSOR.getBoolean(arr_z, UnsafeUtil.BOOLEAN_ARRAY_BASE_OFFSET + v * UnsafeUtil.BOOLEAN_ARRAY_INDEX_SCALE);
    }

    private static boolean getBooleanBigEndian(Object object0, long v) {
        return UnsafeUtil.getByteBigEndian(object0, v) != 0;
    }

    private static boolean getBooleanLittleEndian(Object object0, long v) {
        return UnsafeUtil.getByteLittleEndian(object0, v) != 0;
    }

    static byte getByte(long v) {
        return UnsafeUtil.MEMORY_ACCESSOR.getByte(v);
    }

    static byte getByte(Object object0, long v) {
        return UnsafeUtil.MEMORY_ACCESSOR.getByte(object0, v);
    }

    static byte getByte(byte[] arr_b, long v) {
        return UnsafeUtil.MEMORY_ACCESSOR.getByte(arr_b, UnsafeUtil.BYTE_ARRAY_BASE_OFFSET + v);
    }

    private static byte getByteBigEndian(Object object0, long v) {
        return (byte)(UnsafeUtil.getInt(object0, -4L & v) >>> ((int)((~v & 3L) << 3)) & 0xFF);
    }

    private static byte getByteLittleEndian(Object object0, long v) {
        return (byte)(UnsafeUtil.getInt(object0, -4L & v) >>> ((int)((v & 3L) << 3)) & 0xFF);
    }

    static double getDouble(Object object0, long v) {
        return UnsafeUtil.MEMORY_ACCESSOR.getDouble(object0, v);
    }

    static double getDouble(double[] arr_f, long v) {
        return UnsafeUtil.MEMORY_ACCESSOR.getDouble(arr_f, UnsafeUtil.DOUBLE_ARRAY_BASE_OFFSET + v * UnsafeUtil.DOUBLE_ARRAY_INDEX_SCALE);
    }

    static float getFloat(Object object0, long v) {
        return UnsafeUtil.MEMORY_ACCESSOR.getFloat(object0, v);
    }

    static float getFloat(float[] arr_f, long v) {
        return UnsafeUtil.MEMORY_ACCESSOR.getFloat(arr_f, UnsafeUtil.FLOAT_ARRAY_BASE_OFFSET + v * UnsafeUtil.FLOAT_ARRAY_INDEX_SCALE);
    }

    static int getInt(long v) {
        return UnsafeUtil.MEMORY_ACCESSOR.getInt(v);
    }

    static int getInt(Object object0, long v) {
        return UnsafeUtil.MEMORY_ACCESSOR.getInt(object0, v);
    }

    static int getInt(int[] arr_v, long v) {
        return UnsafeUtil.MEMORY_ACCESSOR.getInt(arr_v, UnsafeUtil.INT_ARRAY_BASE_OFFSET + v * UnsafeUtil.INT_ARRAY_INDEX_SCALE);
    }

    static long getLong(long v) {
        return UnsafeUtil.MEMORY_ACCESSOR.getLong(v);
    }

    static long getLong(Object object0, long v) {
        return UnsafeUtil.MEMORY_ACCESSOR.getLong(object0, v);
    }

    static long getLong(long[] arr_v, long v) {
        return UnsafeUtil.MEMORY_ACCESSOR.getLong(arr_v, UnsafeUtil.LONG_ARRAY_BASE_OFFSET + v * UnsafeUtil.LONG_ARRAY_INDEX_SCALE);
    }

    private static MemoryAccessor getMemoryAccessor() {
        Unsafe unsafe0 = UnsafeUtil.UNSAFE;
        return unsafe0 == null ? null : new JvmMemoryAccessor(unsafe0);
    }

    static Object getObject(Object object0, long v) {
        return UnsafeUtil.MEMORY_ACCESSOR.getObject(object0, v);
    }

    static Object getObject(Object[] arr_object, long v) {
        return UnsafeUtil.MEMORY_ACCESSOR.getObject(arr_object, UnsafeUtil.OBJECT_ARRAY_BASE_OFFSET + v * UnsafeUtil.OBJECT_ARRAY_INDEX_SCALE);
    }

    static Object getStaticObject(Field field0) {
        return UnsafeUtil.MEMORY_ACCESSOR.getStaticObject(field0);
    }

    static Unsafe getUnsafe() {
        try {
            return (Unsafe)AccessController.doPrivileged(new PrivilegedExceptionAction() {
                @Override
                public Object run() throws Exception {
                    return this.run();
                }

                public Unsafe run() throws Exception {
                    Class class0 = Unsafe.class;
                    Field[] arr_field = class0.getDeclaredFields();
                    for(int v = 0; v < arr_field.length; ++v) {
                        Field field0 = arr_field[v];
                        field0.setAccessible(true);
                        Object object0 = field0.get(null);
                        if(class0.isInstance(object0)) {
                            return (Unsafe)class0.cast(object0);
                        }
                    }
                    return null;
                }
            });
        }
        catch(Throwable unused_ex) {
            return null;
        }
    }

    static boolean hasUnsafeArrayOperations() [...] // 潜在的解密器

    static boolean hasUnsafeByteBufferOperations() [...] // 潜在的解密器

    static boolean isAndroid64() {
        return UnsafeUtil.IS_ANDROID_64;
    }

    private static void logMissingMethod(Throwable throwable0) {
        Logger.getLogger("com.google.crypto.tink.shaded.protobuf.UnsafeUtil").log(Level.WARNING, "platform method missing - proto runtime falling back to safer methods: " + throwable0);
    }

    static int mismatch(byte[] arr_b, int v, byte[] arr_b1, int v1, int v2) {
        int v3 = 0;
        if(v < 0 || v1 < 0 || v2 < 0 || v + v2 > arr_b.length || v1 + v2 > arr_b1.length) {
            throw new IndexOutOfBoundsException();
        }
        if(UnsafeUtil.HAS_UNSAFE_ARRAY_OPERATIONS) {
            for(int v4 = UnsafeUtil.BYTE_ARRAY_ALIGNMENT + v & 7; v3 < v2 && (v4 & 7) != 0; ++v4) {
                if(arr_b[v + v3] != arr_b1[v1 + v3]) {
                    return v3;
                }
                ++v3;
            }
            int v5 = (v2 - v3 & -8) + v3;
            while(v3 < v5) {
                long v6 = UnsafeUtil.getLong(arr_b, ((long)v) + UnsafeUtil.BYTE_ARRAY_BASE_OFFSET + ((long)v3));
                long v7 = UnsafeUtil.getLong(arr_b1, UnsafeUtil.BYTE_ARRAY_BASE_OFFSET + ((long)v1) + ((long)v3));
                if(v6 != v7) {
                    return v3 + UnsafeUtil.firstDifferingByteIndexNativeEndian(v6, v7);
                }
                v3 += 8;
            }
        }
        while(v3 < v2) {
            if(arr_b[v + v3] != arr_b1[v1 + v3]) {
                return v3;
            }
            ++v3;
        }
        return -1;
    }

    static long objectFieldOffset(Field field0) {
        return UnsafeUtil.MEMORY_ACCESSOR.objectFieldOffset(field0);
    }

    static void putBoolean(Object object0, long v, boolean z) {
        UnsafeUtil.MEMORY_ACCESSOR.putBoolean(object0, v, z);
    }

    static void putBoolean(boolean[] arr_z, long v, boolean z) {
        UnsafeUtil.MEMORY_ACCESSOR.putBoolean(arr_z, UnsafeUtil.BOOLEAN_ARRAY_BASE_OFFSET + v * UnsafeUtil.BOOLEAN_ARRAY_INDEX_SCALE, z);
    }

    private static void putBooleanBigEndian(Object object0, long v, boolean z) {
        UnsafeUtil.putByteBigEndian(object0, v, ((byte)z));
    }

    private static void putBooleanLittleEndian(Object object0, long v, boolean z) {
        UnsafeUtil.putByteLittleEndian(object0, v, ((byte)z));
    }

    static void putByte(long v, byte b) {
        UnsafeUtil.MEMORY_ACCESSOR.putByte(v, b);
    }

    static void putByte(Object object0, long v, byte b) {
        UnsafeUtil.MEMORY_ACCESSOR.putByte(object0, v, b);
    }

    static void putByte(byte[] arr_b, long v, byte b) {
        UnsafeUtil.MEMORY_ACCESSOR.putByte(arr_b, UnsafeUtil.BYTE_ARRAY_BASE_OFFSET + v, b);
    }

    private static void putByteBigEndian(Object object0, long v, byte b) {
        int v1 = (~((int)v) & 3) << 3;
        UnsafeUtil.putInt(object0, -4L & v, (0xFF & b) << v1 | UnsafeUtil.getInt(object0, -4L & v) & ~(0xFF << v1));
    }

    private static void putByteLittleEndian(Object object0, long v, byte b) {
        int v1 = (((int)v) & 3) << 3;
        UnsafeUtil.putInt(object0, -4L & v, (0xFF & b) << v1 | UnsafeUtil.getInt(object0, -4L & v) & ~(0xFF << v1));
    }

    static void putDouble(Object object0, long v, double f) {
        UnsafeUtil.MEMORY_ACCESSOR.putDouble(object0, v, f);
    }

    static void putDouble(double[] arr_f, long v, double f) {
        UnsafeUtil.MEMORY_ACCESSOR.putDouble(arr_f, UnsafeUtil.DOUBLE_ARRAY_BASE_OFFSET + v * UnsafeUtil.DOUBLE_ARRAY_INDEX_SCALE, f);
    }

    static void putFloat(Object object0, long v, float f) {
        UnsafeUtil.MEMORY_ACCESSOR.putFloat(object0, v, f);
    }

    static void putFloat(float[] arr_f, long v, float f) {
        UnsafeUtil.MEMORY_ACCESSOR.putFloat(arr_f, UnsafeUtil.FLOAT_ARRAY_BASE_OFFSET + v * UnsafeUtil.FLOAT_ARRAY_INDEX_SCALE, f);
    }

    static void putInt(long v, int v1) {
        UnsafeUtil.MEMORY_ACCESSOR.putInt(v, v1);
    }

    static void putInt(Object object0, long v, int v1) {
        UnsafeUtil.MEMORY_ACCESSOR.putInt(object0, v, v1);
    }

    static void putInt(int[] arr_v, long v, int v1) {
        UnsafeUtil.MEMORY_ACCESSOR.putInt(arr_v, UnsafeUtil.INT_ARRAY_BASE_OFFSET + v * UnsafeUtil.INT_ARRAY_INDEX_SCALE, v1);
    }

    static void putLong(long v, long v1) {
        UnsafeUtil.MEMORY_ACCESSOR.putLong(v, v1);
    }

    static void putLong(Object object0, long v, long v1) {
        UnsafeUtil.MEMORY_ACCESSOR.putLong(object0, v, v1);
    }

    static void putLong(long[] arr_v, long v, long v1) {
        UnsafeUtil.MEMORY_ACCESSOR.putLong(arr_v, UnsafeUtil.LONG_ARRAY_BASE_OFFSET + v * UnsafeUtil.LONG_ARRAY_INDEX_SCALE, v1);
    }

    static void putObject(Object object0, long v, Object object1) {
        UnsafeUtil.MEMORY_ACCESSOR.putObject(object0, v, object1);
    }

    static void putObject(Object[] arr_object, long v, Object object0) {
        UnsafeUtil.MEMORY_ACCESSOR.putObject(arr_object, UnsafeUtil.OBJECT_ARRAY_BASE_OFFSET + v * UnsafeUtil.OBJECT_ARRAY_INDEX_SCALE, object0);
    }

    private static boolean supportsUnsafeArrayOperations() {
        return UnsafeUtil.MEMORY_ACCESSOR == null ? false : UnsafeUtil.MEMORY_ACCESSOR.supportsUnsafeArrayOperations();
    }

    private static boolean supportsUnsafeByteBufferOperations() {
        return UnsafeUtil.MEMORY_ACCESSOR == null ? false : UnsafeUtil.MEMORY_ACCESSOR.supportsUnsafeByteBufferOperations();
    }
}

