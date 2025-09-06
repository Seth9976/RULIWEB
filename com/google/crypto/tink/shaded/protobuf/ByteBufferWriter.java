package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.SoftReference;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

final class ByteBufferWriter {
    private static final ThreadLocal BUFFER = null;
    private static final float BUFFER_REALLOCATION_THRESHOLD = 0.5f;
    private static final long CHANNEL_FIELD_OFFSET = 0L;
    private static final Class FILE_OUTPUT_STREAM_CLASS = null;
    private static final int MAX_CACHED_BUFFER_SIZE = 0x4000;
    private static final int MIN_CACHED_BUFFER_SIZE = 0x400;

    static {
        ByteBufferWriter.BUFFER = new ThreadLocal();
        Class class0 = ByteBufferWriter.safeGetClass("java.io.FileOutputStream");
        ByteBufferWriter.FILE_OUTPUT_STREAM_CLASS = class0;
        ByteBufferWriter.CHANNEL_FIELD_OFFSET = ByteBufferWriter.getChannelFieldOffset(class0);
    }

    static void clearCachedBuffer() {
        ByteBufferWriter.BUFFER.set(null);
    }

    private static byte[] getBuffer() {
        SoftReference softReference0 = (SoftReference)ByteBufferWriter.BUFFER.get();
        return softReference0 == null ? null : ((byte[])softReference0.get());
    }

    private static long getChannelFieldOffset(Class class0) {
        if(class0 != null) {
            try {
                return UnsafeUtil.objectFieldOffset(class0.getDeclaredField("channel"));
            }
            catch(Throwable unused_ex) {
            }
        }
        return -1L;
    }

    private static byte[] getOrCreateBuffer(int v) {
        int v1 = Math.max(v, 0x400);
        byte[] arr_b = ByteBufferWriter.getBuffer();
        if(arr_b != null && !ByteBufferWriter.needToReallocate(v1, arr_b.length)) {
            return arr_b;
        }
        byte[] arr_b1 = new byte[v1];
        if(v1 <= 0x4000) {
            ByteBufferWriter.setBuffer(arr_b1);
        }
        return arr_b1;
    }

    private static boolean needToReallocate(int requestedSize, int bufferLength) {
        return bufferLength < requestedSize && ((float)bufferLength) < ((float)requestedSize) * 0.5f;
    }

    private static Class safeGetClass(String s) {
        try {
            return Class.forName(s);
        }
        catch(ClassNotFoundException unused_ex) {
            return null;
        }
    }

    private static void setBuffer(byte[] arr_b) {
        SoftReference softReference0 = new SoftReference(arr_b);
        ByteBufferWriter.BUFFER.set(softReference0);
    }

    static void write(ByteBuffer byteBuffer0, OutputStream outputStream0) throws IOException {
        int v = byteBuffer0.position();
        try {
            if(byteBuffer0.hasArray()) {
                outputStream0.write(byteBuffer0.array(), byteBuffer0.arrayOffset() + byteBuffer0.position(), byteBuffer0.remaining());
            }
            else if(!ByteBufferWriter.writeToChannel(byteBuffer0, outputStream0)) {
                byte[] arr_b = ByteBufferWriter.getOrCreateBuffer(byteBuffer0.remaining());
                while(byteBuffer0.hasRemaining()) {
                    int v2 = Math.min(byteBuffer0.remaining(), arr_b.length);
                    byteBuffer0.get(arr_b, 0, v2);
                    outputStream0.write(arr_b, 0, v2);
                }
            }
        }
        finally {
            byteBuffer0.position(v);
        }
    }

    private static boolean writeToChannel(ByteBuffer byteBuffer0, OutputStream outputStream0) throws IOException {
        WritableByteChannel writableByteChannel0;
        long v = ByteBufferWriter.CHANNEL_FIELD_OFFSET;
        if(v >= 0L && ByteBufferWriter.FILE_OUTPUT_STREAM_CLASS.isInstance(outputStream0)) {
            try {
                writableByteChannel0 = null;
                writableByteChannel0 = (WritableByteChannel)UnsafeUtil.getObject(outputStream0, v);
            }
            catch(ClassCastException unused_ex) {
            }
            if(writableByteChannel0 != null) {
                writableByteChannel0.write(byteBuffer0);
                return true;
            }
        }
        return false;
    }
}

