package okio;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import okio.internal._BufferKt;

@Metadata(d1 = {"\u0000\u00AA\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001A\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000F\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0017\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0002\u0090\u0001B\u0005\u00A2\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001A\u00020\u0000H\u0016J\u0006\u0010\u0011\u001A\u00020\u0012J\b\u0010\u0013\u001A\u00020\u0000H\u0016J\b\u0010\u0014\u001A\u00020\u0012H\u0016J\u0006\u0010\u0015\u001A\u00020\fJ\u0006\u0010\u0016\u001A\u00020\u0000J$\u0010\u0017\u001A\u00020\u00002\u0006\u0010\u0018\u001A\u00020\u00192\b\b\u0002\u0010\u001A\u001A\u00020\f2\b\b\u0002\u0010\u001B\u001A\u00020\fH\u0007J\u0018\u0010\u0017\u001A\u00020\u00002\u0006\u0010\u0018\u001A\u00020\u00002\b\b\u0002\u0010\u001A\u001A\u00020\fJ \u0010\u0017\u001A\u00020\u00002\u0006\u0010\u0018\u001A\u00020\u00002\b\b\u0002\u0010\u001A\u001A\u00020\f2\u0006\u0010\u001B\u001A\u00020\fJ\u0010\u0010\u001C\u001A\u00020\u001D2\u0006\u0010\u001E\u001A\u00020\u001FH\u0002J\b\u0010 \u001A\u00020\u0000H\u0016J\b\u0010!\u001A\u00020\u0000H\u0016J\u0013\u0010\"\u001A\u00020#2\b\u0010$\u001A\u0004\u0018\u00010%H\u0096\u0002J\b\u0010&\u001A\u00020#H\u0016J\b\u0010\'\u001A\u00020\u0012H\u0016J\u0016\u0010(\u001A\u00020)2\u0006\u0010*\u001A\u00020\fH\u0087\u0002\u00A2\u0006\u0002\b+J\u0015\u0010+\u001A\u00020)2\u0006\u0010,\u001A\u00020\fH\u0007\u00A2\u0006\u0002\b-J\b\u0010.\u001A\u00020/H\u0016J\u0018\u00100\u001A\u00020\u001D2\u0006\u0010\u001E\u001A\u00020\u001F2\u0006\u00101\u001A\u00020\u001DH\u0002J\u000E\u00102\u001A\u00020\u001D2\u0006\u00101\u001A\u00020\u001DJ\u000E\u00103\u001A\u00020\u001D2\u0006\u00101\u001A\u00020\u001DJ\u000E\u00104\u001A\u00020\u001D2\u0006\u00101\u001A\u00020\u001DJ\u0010\u00105\u001A\u00020\f2\u0006\u00106\u001A\u00020)H\u0016J\u0018\u00105\u001A\u00020\f2\u0006\u00106\u001A\u00020)2\u0006\u00107\u001A\u00020\fH\u0016J \u00105\u001A\u00020\f2\u0006\u00106\u001A\u00020)2\u0006\u00107\u001A\u00020\f2\u0006\u00108\u001A\u00020\fH\u0016J\u0010\u00105\u001A\u00020\f2\u0006\u00109\u001A\u00020\u001DH\u0016J\u0018\u00105\u001A\u00020\f2\u0006\u00109\u001A\u00020\u001D2\u0006\u00107\u001A\u00020\fH\u0016J\u0010\u0010:\u001A\u00020\f2\u0006\u0010;\u001A\u00020\u001DH\u0016J\u0018\u0010:\u001A\u00020\f2\u0006\u0010;\u001A\u00020\u001D2\u0006\u00107\u001A\u00020\fH\u0016J\b\u0010<\u001A\u00020=H\u0016J\b\u0010>\u001A\u00020#H\u0016J\u0006\u0010?\u001A\u00020\u001DJ\b\u0010@\u001A\u00020\u0019H\u0016J\b\u0010A\u001A\u00020\u0001H\u0016J\u0018\u0010B\u001A\u00020#2\u0006\u0010\u001A\u001A\u00020\f2\u0006\u00109\u001A\u00020\u001DH\u0016J(\u0010B\u001A\u00020#2\u0006\u0010\u001A\u001A\u00020\f2\u0006\u00109\u001A\u00020\u001D2\u0006\u0010C\u001A\u00020/2\u0006\u0010\u001B\u001A\u00020/H\u0016J\u0010\u0010D\u001A\u00020/2\u0006\u0010E\u001A\u00020FH\u0016J\u0010\u0010D\u001A\u00020/2\u0006\u0010E\u001A\u00020GH\u0016J \u0010D\u001A\u00020/2\u0006\u0010E\u001A\u00020G2\u0006\u0010\u001A\u001A\u00020/2\u0006\u0010\u001B\u001A\u00020/H\u0016J\u0018\u0010D\u001A\u00020\f2\u0006\u0010E\u001A\u00020\u00002\u0006\u0010\u001B\u001A\u00020\fH\u0016J\u0010\u0010H\u001A\u00020\f2\u0006\u0010E\u001A\u00020IH\u0016J\u0012\u0010J\u001A\u00020K2\b\b\u0002\u0010L\u001A\u00020KH\u0007J\b\u0010M\u001A\u00020)H\u0016J\b\u0010N\u001A\u00020GH\u0016J\u0010\u0010N\u001A\u00020G2\u0006\u0010\u001B\u001A\u00020\fH\u0016J\b\u0010O\u001A\u00020\u001DH\u0016J\u0010\u0010O\u001A\u00020\u001D2\u0006\u0010\u001B\u001A\u00020\fH\u0016J\b\u0010P\u001A\u00020\fH\u0016J\u000E\u0010Q\u001A\u00020\u00002\u0006\u0010R\u001A\u00020=J\u0016\u0010Q\u001A\u00020\u00002\u0006\u0010R\u001A\u00020=2\u0006\u0010\u001B\u001A\u00020\fJ \u0010Q\u001A\u00020\u00122\u0006\u0010R\u001A\u00020=2\u0006\u0010\u001B\u001A\u00020\f2\u0006\u0010S\u001A\u00020#H\u0002J\u0010\u0010T\u001A\u00020\u00122\u0006\u0010E\u001A\u00020GH\u0016J\u0018\u0010T\u001A\u00020\u00122\u0006\u0010E\u001A\u00020\u00002\u0006\u0010\u001B\u001A\u00020\fH\u0016J\b\u0010U\u001A\u00020\fH\u0016J\b\u0010V\u001A\u00020/H\u0016J\b\u0010W\u001A\u00020/H\u0016J\b\u0010X\u001A\u00020\fH\u0016J\b\u0010Y\u001A\u00020\fH\u0016J\b\u0010Z\u001A\u00020[H\u0016J\b\u0010\\\u001A\u00020[H\u0016J\u0010\u0010]\u001A\u00020\u001F2\u0006\u0010^\u001A\u00020_H\u0016J\u0018\u0010]\u001A\u00020\u001F2\u0006\u0010\u001B\u001A\u00020\f2\u0006\u0010^\u001A\u00020_H\u0016J\u0012\u0010`\u001A\u00020K2\b\b\u0002\u0010L\u001A\u00020KH\u0007J\b\u0010a\u001A\u00020\u001FH\u0016J\u0010\u0010a\u001A\u00020\u001F2\u0006\u0010\u001B\u001A\u00020\fH\u0016J\b\u0010b\u001A\u00020/H\u0016J\n\u0010c\u001A\u0004\u0018\u00010\u001FH\u0016J\b\u0010d\u001A\u00020\u001FH\u0016J\u0010\u0010d\u001A\u00020\u001F2\u0006\u0010e\u001A\u00020\fH\u0016J\u0010\u0010f\u001A\u00020#2\u0006\u0010\u001B\u001A\u00020\fH\u0016J\u0010\u0010g\u001A\u00020\u00122\u0006\u0010\u001B\u001A\u00020\fH\u0016J\u0010\u0010h\u001A\u00020/2\u0006\u0010i\u001A\u00020jH\u0016J\u0006\u0010k\u001A\u00020\u001DJ\u0006\u0010l\u001A\u00020\u001DJ\u0006\u0010m\u001A\u00020\u001DJ\r\u0010\r\u001A\u00020\fH\u0007\u00A2\u0006\u0002\bnJ\u0010\u0010o\u001A\u00020\u00122\u0006\u0010\u001B\u001A\u00020\fH\u0016J\u0006\u0010p\u001A\u00020\u001DJ\u000E\u0010p\u001A\u00020\u001D2\u0006\u0010\u001B\u001A\u00020/J\b\u0010q\u001A\u00020rH\u0016J\b\u0010s\u001A\u00020\u001FH\u0016J\u0015\u0010t\u001A\u00020\n2\u0006\u0010u\u001A\u00020/H\u0000\u00A2\u0006\u0002\bvJ\u0010\u0010w\u001A\u00020/2\u0006\u0010x\u001A\u00020FH\u0016J\u0010\u0010w\u001A\u00020\u00002\u0006\u0010x\u001A\u00020GH\u0016J \u0010w\u001A\u00020\u00002\u0006\u0010x\u001A\u00020G2\u0006\u0010\u001A\u001A\u00020/2\u0006\u0010\u001B\u001A\u00020/H\u0016J\u0018\u0010w\u001A\u00020\u00122\u0006\u0010x\u001A\u00020\u00002\u0006\u0010\u001B\u001A\u00020\fH\u0016J\u0010\u0010w\u001A\u00020\u00002\u0006\u0010y\u001A\u00020\u001DH\u0016J \u0010w\u001A\u00020\u00002\u0006\u0010y\u001A\u00020\u001D2\u0006\u0010\u001A\u001A\u00020/2\u0006\u0010\u001B\u001A\u00020/H\u0016J\u0018\u0010w\u001A\u00020\u00002\u0006\u0010x\u001A\u00020z2\u0006\u0010\u001B\u001A\u00020\fH\u0016J\u0010\u0010{\u001A\u00020\f2\u0006\u0010x\u001A\u00020zH\u0016J\u0010\u0010|\u001A\u00020\u00002\u0006\u00106\u001A\u00020/H\u0016J\u0010\u0010}\u001A\u00020\u00002\u0006\u0010~\u001A\u00020\fH\u0016J\u0010\u0010\u007F\u001A\u00020\u00002\u0006\u0010~\u001A\u00020\fH\u0016J\u0012\u0010\u0080\u0001\u001A\u00020\u00002\u0007\u0010\u0081\u0001\u001A\u00020/H\u0016J\u0012\u0010\u0082\u0001\u001A\u00020\u00002\u0007\u0010\u0081\u0001\u001A\u00020/H\u0016J\u0011\u0010\u0083\u0001\u001A\u00020\u00002\u0006\u0010~\u001A\u00020\fH\u0016J\u0011\u0010\u0084\u0001\u001A\u00020\u00002\u0006\u0010~\u001A\u00020\fH\u0016J\u0012\u0010\u0085\u0001\u001A\u00020\u00002\u0007\u0010\u0086\u0001\u001A\u00020/H\u0016J\u0012\u0010\u0087\u0001\u001A\u00020\u00002\u0007\u0010\u0086\u0001\u001A\u00020/H\u0016J\u001A\u0010\u0088\u0001\u001A\u00020\u00002\u0007\u0010\u0089\u0001\u001A\u00020\u001F2\u0006\u0010^\u001A\u00020_H\u0016J,\u0010\u0088\u0001\u001A\u00020\u00002\u0007\u0010\u0089\u0001\u001A\u00020\u001F2\u0007\u0010\u008A\u0001\u001A\u00020/2\u0007\u0010\u008B\u0001\u001A\u00020/2\u0006\u0010^\u001A\u00020_H\u0016J\u001B\u0010\u008C\u0001\u001A\u00020\u00002\u0006\u0010\u0018\u001A\u00020\u00192\b\b\u0002\u0010\u001B\u001A\u00020\fH\u0007J\u0012\u0010\u008D\u0001\u001A\u00020\u00002\u0007\u0010\u0089\u0001\u001A\u00020\u001FH\u0016J$\u0010\u008D\u0001\u001A\u00020\u00002\u0007\u0010\u0089\u0001\u001A\u00020\u001F2\u0007\u0010\u008A\u0001\u001A\u00020/2\u0007\u0010\u008B\u0001\u001A\u00020/H\u0016J\u0012\u0010\u008E\u0001\u001A\u00020\u00002\u0007\u0010\u008F\u0001\u001A\u00020/H\u0016R\u0014\u0010\u0006\u001A\u00020\u00008VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001A\u0004\u0018\u00010\n8\u0000@\u0000X\u0081\u000E\u00A2\u0006\u0002\n\u0000R&\u0010\r\u001A\u00020\f2\u0006\u0010\u000B\u001A\u00020\f8G@@X\u0086\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\r\u0010\u000E\"\u0004\b\u000F\u0010\u0010\u00A8\u0006\u0091\u0001"}, d2 = {"Lokio/Buffer;", "Lokio/BufferedSource;", "Lokio/BufferedSink;", "", "Ljava/nio/channels/ByteChannel;", "()V", "buffer", "getBuffer", "()Lokio/Buffer;", "head", "Lokio/Segment;", "<set-?>", "", "size", "()J", "setSize$okio", "(J)V", "clear", "", "clone", "close", "completeSegmentByteCount", "copy", "copyTo", "out", "Ljava/io/OutputStream;", "offset", "byteCount", "digest", "Lokio/ByteString;", "algorithm", "", "emit", "emitCompleteSegments", "equals", "", "other", "", "exhausted", "flush", "get", "", "pos", "getByte", "index", "-deprecated_getByte", "hashCode", "", "hmac", "key", "hmacSha1", "hmacSha256", "hmacSha512", "indexOf", "b", "fromIndex", "toIndex", "bytes", "indexOfElement", "targetBytes", "inputStream", "Ljava/io/InputStream;", "isOpen", "md5", "outputStream", "peek", "rangeEquals", "bytesOffset", "read", "sink", "Ljava/nio/ByteBuffer;", "", "readAll", "Lokio/Sink;", "readAndWriteUnsafe", "Lokio/Buffer$UnsafeCursor;", "unsafeCursor", "readByte", "readByteArray", "readByteString", "readDecimalLong", "readFrom", "input", "forever", "readFully", "readHexadecimalUnsignedLong", "readInt", "readIntLe", "readLong", "readLongLe", "readShort", "", "readShortLe", "readString", "charset", "Ljava/nio/charset/Charset;", "readUnsafe", "readUtf8", "readUtf8CodePoint", "readUtf8Line", "readUtf8LineStrict", "limit", "request", "require", "select", "options", "Lokio/Options;", "sha1", "sha256", "sha512", "-deprecated_size", "skip", "snapshot", "timeout", "Lokio/Timeout;", "toString", "writableSegment", "minimumCapacity", "writableSegment$okio", "write", "source", "byteString", "Lokio/Source;", "writeAll", "writeByte", "writeDecimalLong", "v", "writeHexadecimalUnsignedLong", "writeInt", "i", "writeIntLe", "writeLong", "writeLongLe", "writeShort", "s", "writeShortLe", "writeString", "string", "beginIndex", "endIndex", "writeTo", "writeUtf8", "writeUtf8CodePoint", "codePoint", "UnsafeCursor", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class Buffer implements AutoCloseable, Cloneable, ByteChannel, BufferedSink, BufferedSource {
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0014\u001A\u00020\u0015H\u0016J\u000E\u0010\u0016\u001A\u00020\n2\u0006\u0010\u0017\u001A\u00020\bJ\u0006\u0010\u0018\u001A\u00020\bJ\u000E\u0010\u0019\u001A\u00020\n2\u0006\u0010\u001A\u001A\u00020\nJ\u000E\u0010\u001B\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\nR\u0014\u0010\u0003\u001A\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000E¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001A\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000E¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001A\u00020\b8\u0006@\u0006X\u0087\u000E¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001A\u00020\n8\u0006@\u0006X\u0087\u000E¢\u0006\u0002\n\u0000R\u0012\u0010\u000B\u001A\u00020\f8\u0006@\u0006X\u0087\u000E¢\u0006\u0002\n\u0000R\u001C\u0010\r\u001A\u0004\u0018\u00010\u000EX\u0080\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u000F\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0012\u0010\u0013\u001A\u00020\b8\u0006@\u0006X\u0087\u000E¢\u0006\u0002\n\u0000¨\u0006\u001C"}, d2 = {"Lokio/Buffer$UnsafeCursor;", "Ljava/io/Closeable;", "()V", "buffer", "Lokio/Buffer;", "data", "", "end", "", "offset", "", "readWrite", "", "segment", "Lokio/Segment;", "getSegment$okio", "()Lokio/Segment;", "setSegment$okio", "(Lokio/Segment;)V", "start", "close", "", "expandBuffer", "minByteCount", "next", "resizeBuffer", "newSize", "seek", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public static final class UnsafeCursor implements Closeable, AutoCloseable {
        public Buffer buffer;
        public byte[] data;
        public int end;
        public long offset;
        public boolean readWrite;
        private Segment segment;
        public int start;

        public UnsafeCursor() {
            this.offset = -1L;
            this.start = -1;
            this.end = -1;
        }

        @Override
        public void close() {
            if(this.buffer == null) {
                throw new IllegalStateException("not attached to a buffer");
            }
            this.buffer = null;
            this.setSegment$okio(null);
            this.offset = -1L;
            this.data = null;
            this.start = -1;
            this.end = -1;
        }

        public final long expandBuffer(int v) {
            if(v <= 0) {
                throw new IllegalArgumentException(("minByteCount <= 0: " + v).toString());
            }
            if(v > 0x2000) {
                throw new IllegalArgumentException(("minByteCount > Segment.SIZE: " + v).toString());
            }
            Buffer buffer0 = this.buffer;
            if(buffer0 == null) {
                throw new IllegalStateException("not attached to a buffer");
            }
            if(!this.readWrite) {
                throw new IllegalStateException("expandBuffer() only permitted for read/write buffers");
            }
            long v1 = buffer0.size();
            Segment segment0 = buffer0.writableSegment$okio(v);
            int v2 = 0x2000 - segment0.limit;
            segment0.limit = 0x2000;
            buffer0.setSize$okio(v1 + ((long)v2));
            this.setSegment$okio(segment0);
            this.offset = v1;
            this.data = segment0.data;
            this.start = 0x2000 - v2;
            this.end = 0x2000;
            return (long)v2;
        }

        public final Segment getSegment$okio() {
            return this.segment;
        }

        public final int next() {
            long v = this.offset;
            Buffer buffer0 = this.buffer;
            Intrinsics.checkNotNull(buffer0);
            if(v == buffer0.size()) {
                throw new IllegalStateException("no more bytes");
            }
            return this.offset == -1L ? this.seek(0L) : this.seek(this.offset + ((long)(this.end - this.start)));
        }

        public final long resizeBuffer(long v) {
            Buffer buffer0 = this.buffer;
            if(buffer0 == null) {
                throw new IllegalStateException("not attached to a buffer");
            }
            if(!this.readWrite) {
                throw new IllegalStateException("resizeBuffer() only permitted for read/write buffers");
            }
            long v1 = buffer0.size();
            if(Long.compare(v, v1) <= 0) {
                if(v < 0L) {
                    throw new IllegalArgumentException(("newSize < 0: " + v).toString());
                }
                long v2 = v1 - v;
                while(v2 > 0L) {
                    Segment segment0 = buffer0.head;
                    Intrinsics.checkNotNull(segment0);
                    Segment segment1 = segment0.prev;
                    Intrinsics.checkNotNull(segment1);
                    long v3 = (long)(segment1.limit - segment1.pos);
                    if(v3 <= v2) {
                        buffer0.head = segment1.pop();
                        SegmentPool.recycle(segment1);
                        v2 -= v3;
                    }
                    else {
                        segment1.limit -= (int)v2;
                        if(true) {
                            break;
                        }
                    }
                }
                this.setSegment$okio(null);
                this.offset = v;
                this.data = null;
                this.start = -1;
                this.end = -1;
            }
            else {
                long v4 = v - v1;
                boolean z = true;
                while(v4 > 0L) {
                    Segment segment2 = buffer0.writableSegment$okio(1);
                    int v5 = (int)Math.min(v4, 0x2000 - segment2.limit);
                    segment2.limit += v5;
                    v4 -= (long)v5;
                    if(z) {
                        this.setSegment$okio(segment2);
                        this.offset = v1;
                        this.data = segment2.data;
                        this.start = segment2.limit - v5;
                        this.end = segment2.limit;
                        z = false;
                    }
                }
            }
            buffer0.setSize$okio(v);
            return v1;
        }

        public final int seek(long v) {
            Buffer buffer0 = this.buffer;
            if(buffer0 == null) {
                throw new IllegalStateException("not attached to a buffer");
            }
            int v1 = Long.compare(v, -1L);
            if(v1 < 0 || v > buffer0.size()) {
                throw new ArrayIndexOutOfBoundsException("offset=" + v + " > size=" + buffer0.size());
            }
            if(v1 != 0 && v != buffer0.size()) {
                long v2 = buffer0.size();
                Segment segment0 = buffer0.head;
                Segment segment1 = buffer0.head;
                long v3 = 0L;
                if(this.getSegment$okio() != null) {
                    long v4 = this.offset;
                    int v5 = this.start;
                    Segment segment2 = this.getSegment$okio();
                    Intrinsics.checkNotNull(segment2);
                    long v6 = v4 - ((long)(v5 - segment2.pos));
                    if(v6 > v) {
                        segment1 = this.getSegment$okio();
                        v2 = v6;
                    }
                    else {
                        segment0 = this.getSegment$okio();
                        v3 = v6;
                    }
                }
                if(v2 - v > v - v3) {
                    while(true) {
                        Intrinsics.checkNotNull(segment0);
                        if(v < ((long)(segment0.limit - segment0.pos)) + v3) {
                            break;
                        }
                        v3 += (long)(segment0.limit - segment0.pos);
                        segment0 = segment0.next;
                    }
                }
                else {
                    while(v2 > v) {
                        Intrinsics.checkNotNull(segment1);
                        segment1 = segment1.prev;
                        Intrinsics.checkNotNull(segment1);
                        v2 -= (long)(segment1.limit - segment1.pos);
                    }
                    v3 = v2;
                    segment0 = segment1;
                }
                if(this.readWrite) {
                    Intrinsics.checkNotNull(segment0);
                    if(segment0.shared) {
                        Segment segment3 = segment0.unsharedCopy();
                        if(buffer0.head == segment0) {
                            buffer0.head = segment3;
                        }
                        segment0 = segment0.push(segment3);
                        Segment segment4 = segment0.prev;
                        Intrinsics.checkNotNull(segment4);
                        segment4.pop();
                    }
                }
                this.setSegment$okio(segment0);
                this.offset = v;
                Intrinsics.checkNotNull(segment0);
                this.data = segment0.data;
                this.start = segment0.pos + ((int)(v - v3));
                this.end = segment0.limit;
                return segment0.limit - this.start;
            }
            this.setSegment$okio(null);
            this.offset = v;
            this.data = null;
            this.start = -1;
            this.end = -1;
            return -1;
        }

        public final void setSegment$okio(Segment segment0) {
            this.segment = segment0;
        }
    }

    public Segment head;
    private long size;

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to operator function", replaceWith = @ReplaceWith(expression = "this[index]", imports = {}))
    public final byte -deprecated_getByte(long v) {
        return this.getByte(v);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "size", imports = {}))
    public final long -deprecated_size() {
        return this.size;
    }

    @Override  // okio.BufferedSource, okio.BufferedSink
    public Buffer buffer() {
        return this;
    }

    public final void clear() {
        this.skip(this.size());
    }

    @Override
    public Object clone() {
        return this.clone();
    }

    public Buffer clone() {
        return this.copy();
    }

    @Override  // okio.Source, okio.Sink
    public void close() {
    }

    public final long completeSegmentByteCount() {
        long v = this.size();
        if(v == 0L) {
            return 0L;
        }
        Segment segment0 = this.head;
        Intrinsics.checkNotNull(segment0);
        Segment segment1 = segment0.prev;
        Intrinsics.checkNotNull(segment1);
        return segment1.limit >= 0x2000 || !segment1.owner ? v : v - ((long)(segment1.limit - segment1.pos));
    }

    public final Buffer copy() {
        Buffer buffer0 = new Buffer();
        if(this.size() == 0L) {
            return buffer0;
        }
        Segment segment0 = this.head;
        Intrinsics.checkNotNull(segment0);
        Segment segment1 = segment0.sharedCopy();
        buffer0.head = segment1;
        segment1.prev = segment1;
        segment1.next = segment1.prev;
        for(Segment segment2 = segment0.next; segment2 != segment0; segment2 = segment2.next) {
            Segment segment3 = segment1.prev;
            Intrinsics.checkNotNull(segment3);
            Intrinsics.checkNotNull(segment2);
            segment3.push(segment2.sharedCopy());
        }
        buffer0.setSize$okio(this.size());
        return buffer0;
    }

    public final Buffer copyTo(OutputStream outputStream0) throws IOException {
        Intrinsics.checkNotNullParameter(outputStream0, "out");
        return Buffer.copyTo$default(this, outputStream0, 0L, 0L, 6, null);
    }

    public final Buffer copyTo(OutputStream outputStream0, long v) throws IOException {
        Intrinsics.checkNotNullParameter(outputStream0, "out");
        return Buffer.copyTo$default(this, outputStream0, v, 0L, 4, null);
    }

    public final Buffer copyTo(OutputStream outputStream0, long v, long v1) throws IOException {
        Intrinsics.checkNotNullParameter(outputStream0, "out");
        long v2 = v;
        _UtilKt.checkOffsetAndCount(this.size, v2, v1);
        if(v1 != 0L) {
            Segment segment0;
            for(segment0 = this.head; true; segment0 = segment0.next) {
                Intrinsics.checkNotNull(segment0);
                if(v2 < ((long)(segment0.limit - segment0.pos))) {
                    break;
                }
                v2 -= (long)(segment0.limit - segment0.pos);
            }
            Segment segment1 = segment0;
            long v3 = v1;
            while(v3 > 0L) {
                Intrinsics.checkNotNull(segment1);
                int v4 = (int)(((long)segment1.pos) + v2);
                int v5 = (int)Math.min(segment1.limit - v4, v3);
                outputStream0.write(segment1.data, v4, v5);
                v3 -= (long)v5;
                segment1 = segment1.next;
                v2 = 0L;
            }
        }
        return this;
    }

    public final Buffer copyTo(Buffer buffer0, long v) {
        Intrinsics.checkNotNullParameter(buffer0, "out");
        return this.copyTo(buffer0, v, this.size - v);
    }

    public final Buffer copyTo(Buffer buffer0, long v, long v1) {
        Intrinsics.checkNotNullParameter(buffer0, "out");
        long v2 = v;
        _UtilKt.checkOffsetAndCount(this.size(), v2, v1);
        if(v1 != 0L) {
            buffer0.setSize$okio(buffer0.size() + v1);
            Segment segment0;
            for(segment0 = this.head; true; segment0 = segment0.next) {
                Intrinsics.checkNotNull(segment0);
                if(v2 < ((long)(segment0.limit - segment0.pos))) {
                    break;
                }
                v2 -= (long)(segment0.limit - segment0.pos);
            }
            Segment segment1 = segment0;
            long v3 = v1;
            while(v3 > 0L) {
                Intrinsics.checkNotNull(segment1);
                Segment segment2 = segment1.sharedCopy();
                segment2.pos += (int)v2;
                segment2.limit = Math.min(segment2.pos + ((int)v3), segment2.limit);
                Segment segment3 = buffer0.head;
                if(segment3 == null) {
                    segment2.prev = segment2;
                    segment2.next = segment2.prev;
                    buffer0.head = segment2.next;
                }
                else {
                    Intrinsics.checkNotNull(segment3);
                    Segment segment4 = segment3.prev;
                    Intrinsics.checkNotNull(segment4);
                    segment4.push(segment2);
                }
                v3 -= (long)(segment2.limit - segment2.pos);
                segment1 = segment1.next;
                v2 = 0L;
            }
        }
        return this;
    }

    public static Buffer copyTo$default(Buffer buffer0, OutputStream outputStream0, long v, long v1, int v2, Object object0) throws IOException {
        if((v2 & 2) != 0) {
            v = 0L;
        }
        if((v2 & 4) != 0) {
            v1 = buffer0.size - v;
        }
        return buffer0.copyTo(outputStream0, v, v1);
    }

    public static Buffer copyTo$default(Buffer buffer0, Buffer buffer1, long v, int v1, Object object0) {
        if((v1 & 2) != 0) {
            v = 0L;
        }
        return buffer0.copyTo(buffer1, v);
    }

    public static Buffer copyTo$default(Buffer buffer0, Buffer buffer1, long v, long v1, int v2, Object object0) {
        if((v2 & 2) != 0) {
            v = 0L;
        }
        return buffer0.copyTo(buffer1, v, v1);
    }

    private final ByteString digest(String s) {
        MessageDigest messageDigest0 = MessageDigest.getInstance(s);
        Segment segment0 = this.head;
        if(segment0 != null) {
            messageDigest0.update(segment0.data, segment0.pos, segment0.limit - segment0.pos);
            Segment segment1 = segment0.next;
            Intrinsics.checkNotNull(segment1);
            while(segment1 != segment0) {
                messageDigest0.update(segment1.data, segment1.pos, segment1.limit - segment1.pos);
                segment1 = segment1.next;
                Intrinsics.checkNotNull(segment1);
            }
        }
        byte[] arr_b = messageDigest0.digest();
        Intrinsics.checkNotNullExpressionValue(arr_b, "messageDigest.digest()");
        return new ByteString(arr_b);
    }

    public Buffer emit() [...] // Inlined contents

    @Override  // okio.BufferedSink
    public BufferedSink emit() {
        return this;
    }

    public Buffer emitCompleteSegments() [...] // Inlined contents

    @Override  // okio.BufferedSink
    public BufferedSink emitCompleteSegments() {
        return this;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof Buffer)) {
            return false;
        }
        if(this.size() != ((Buffer)object0).size()) {
            return false;
        }
        if(this.size() == 0L) {
            return true;
        }
        Segment segment0 = this.head;
        Intrinsics.checkNotNull(segment0);
        Segment segment1 = ((Buffer)object0).head;
        Intrinsics.checkNotNull(segment1);
        int v = segment0.pos;
        int v1 = segment1.pos;
        for(long v2 = 0L; v2 < this.size(); v2 += v3) {
            long v3 = (long)Math.min(segment0.limit - v, segment1.limit - v1);
            long v4 = 0L;
            while(v4 < v3) {
                if(segment0.data[v] != segment1.data[v1]) {
                    return false;
                }
                ++v4;
                ++v;
                ++v1;
            }
            if(v == segment0.limit) {
                segment0 = segment0.next;
                Intrinsics.checkNotNull(segment0);
                v = segment0.pos;
            }
            if(v1 == segment1.limit) {
                segment1 = segment1.next;
                Intrinsics.checkNotNull(segment1);
                v1 = segment1.pos;
            }
        }
        return true;
    }

    @Override  // okio.BufferedSource
    public boolean exhausted() {
        return this.size == 0L;
    }

    @Override  // okio.BufferedSink
    public void flush() {
    }

    @Override  // okio.BufferedSource, okio.BufferedSink
    public Buffer getBuffer() {
        return this;
    }

    public final byte getByte(long v) {
        _UtilKt.checkOffsetAndCount(this.size(), v, 1L);
        Segment segment0 = this.head;
        if(segment0 != null) {
            if(this.size() - v < v) {
                long v1;
                for(v1 = this.size(); v1 > v; v1 -= (long)(segment0.limit - segment0.pos)) {
                    segment0 = segment0.prev;
                    Intrinsics.checkNotNull(segment0);
                }
                Intrinsics.checkNotNull(segment0);
                return segment0.data[((int)(((long)segment0.pos) + v - v1))];
            }
            long v2 = 0L;
            long v3;
            while((v3 = ((long)(segment0.limit - segment0.pos)) + v2) <= v) {
                segment0 = segment0.next;
                Intrinsics.checkNotNull(segment0);
                v2 = v3;
            }
            Intrinsics.checkNotNull(segment0);
            return segment0.data[((int)(((long)segment0.pos) + v - v2))];
        }
        Intrinsics.checkNotNull(null);
        throw null;
    }

    @Override
    public int hashCode() {
        Segment segment0 = this.head;
        if(segment0 == null) {
            return 0;
        }
        int v = 1;
        do {
            int v1 = segment0.pos;
            int v2 = segment0.limit;
            while(v1 < v2) {
                v = v * 0x1F + segment0.data[v1];
                ++v1;
            }
            segment0 = segment0.next;
            Intrinsics.checkNotNull(segment0);
        }
        while(segment0 != this.head);
        return v;
    }

    private final ByteString hmac(String s, ByteString byteString0) {
        try {
            Mac mac0 = Mac.getInstance(s);
            mac0.init(new SecretKeySpec(byteString0.internalArray$okio(), s));
            Segment segment0 = this.head;
            if(segment0 != null) {
                mac0.update(segment0.data, segment0.pos, segment0.limit - segment0.pos);
                Segment segment1 = segment0.next;
                Intrinsics.checkNotNull(segment1);
                while(segment1 != segment0) {
                    mac0.update(segment1.data, segment1.pos, segment1.limit - segment1.pos);
                    segment1 = segment1.next;
                    Intrinsics.checkNotNull(segment1);
                }
            }
            byte[] arr_b = mac0.doFinal();
            Intrinsics.checkNotNullExpressionValue(arr_b, "mac.doFinal()");
            return new ByteString(arr_b);
        }
        catch(InvalidKeyException invalidKeyException0) {
            throw new IllegalArgumentException(invalidKeyException0);
        }
    }

    public final ByteString hmacSha1(ByteString byteString0) {
        Intrinsics.checkNotNullParameter(byteString0, "key");
        return this.hmac("HmacSHA1", byteString0);
    }

    public final ByteString hmacSha256(ByteString byteString0) {
        Intrinsics.checkNotNullParameter(byteString0, "key");
        return this.hmac("HmacSHA256", byteString0);
    }

    public final ByteString hmacSha512(ByteString byteString0) {
        Intrinsics.checkNotNullParameter(byteString0, "key");
        return this.hmac("HmacSHA512", byteString0);
    }

    @Override  // okio.BufferedSource
    public long indexOf(byte b) {
        return this.indexOf(b, 0L, 0x7FFFFFFFFFFFFFFFL);
    }

    @Override  // okio.BufferedSource
    public long indexOf(byte b, long v) {
        return this.indexOf(b, v, 0x7FFFFFFFFFFFFFFFL);
    }

    @Override  // okio.BufferedSource
    public long indexOf(byte b, long v, long v1) {
        int v4;
        long v2 = 0L;
        if(0L > v || v > v1) {
            throw new IllegalArgumentException(("size=" + this.size() + " fromIndex=" + v + " toIndex=" + v1).toString());
        }
        if(v1 > this.size()) {
            v1 = this.size();
        }
        if(v == v1) {
            return -1L;
        }
        Segment segment0 = this.head;
        if(segment0 == null) {
            return -1L;
        }
        if(this.size() - v < v) {
            for(v2 = this.size(); v2 > v; v2 -= (long)(segment0.limit - segment0.pos)) {
                segment0 = segment0.prev;
                Intrinsics.checkNotNull(segment0);
            }
            if(segment0 == null) {
                return -1L;
            }
            while(v2 < v1) {
                byte[] arr_b = segment0.data;
                int v3 = (int)Math.min(segment0.limit, ((long)segment0.pos) + v1 - v2);
                for(v4 = (int)(((long)segment0.pos) + v - v2); v4 < v3; ++v4) {
                    if(arr_b[v4] == b) {
                        return ((long)(v4 - segment0.pos)) + v2;
                    }
                }
                v2 += (long)(segment0.limit - segment0.pos);
                segment0 = segment0.next;
                Intrinsics.checkNotNull(segment0);
                v = v2;
            }
            return -1L;
        }
        long v5;
        while((v5 = ((long)(segment0.limit - segment0.pos)) + v2) <= v) {
            segment0 = segment0.next;
            Intrinsics.checkNotNull(segment0);
            v2 = v5;
        }
        if(segment0 == null) {
            return -1L;
        }
        while(v2 < v1) {
            byte[] arr_b1 = segment0.data;
            int v6 = (int)Math.min(segment0.limit, ((long)segment0.pos) + v1 - v2);
            v4 = (int)(((long)segment0.pos) + v - v2);
            while(v4 < v6) {
                if(arr_b1[v4] != b) {
                    ++v4;
                    continue;
                }
                return ((long)(v4 - segment0.pos)) + v2;
            }
            v2 += (long)(segment0.limit - segment0.pos);
            segment0 = segment0.next;
            Intrinsics.checkNotNull(segment0);
            v = v2;
        }
        return -1L;
    }

    @Override  // okio.BufferedSource
    public long indexOf(ByteString byteString0) throws IOException {
        Intrinsics.checkNotNullParameter(byteString0, "bytes");
        return this.indexOf(byteString0, 0L);
    }

    @Override  // okio.BufferedSource
    public long indexOf(ByteString byteString0, long v) throws IOException {
        int v6;
        long v1 = 0L;
        Intrinsics.checkNotNullParameter(byteString0, "bytes");
        if(byteString0.size() <= 0) {
            throw new IllegalArgumentException("bytes is empty");
        }
        if(v < 0L) {
            throw new IllegalArgumentException(("fromIndex < 0: " + v).toString());
        }
        Segment segment0 = this.head;
        if(segment0 == null) {
            return -1L;
        }
        if(this.size() - v < v) {
            for(v1 = this.size(); v1 > v; v1 -= (long)(segment0.limit - segment0.pos)) {
                segment0 = segment0.prev;
                Intrinsics.checkNotNull(segment0);
            }
            if(segment0 == null) {
                return -1L;
            }
            byte[] arr_b = byteString0.internalArray$okio();
            int v2 = arr_b[0];
            int v3 = byteString0.size();
            long v4 = this.size() - ((long)v3) + 1L;
            while(v1 < v4) {
                byte[] arr_b1 = segment0.data;
                int v5 = (int)Math.min(segment0.limit, ((long)segment0.pos) + v4 - v1);
                for(v6 = (int)(((long)segment0.pos) + v - v1); v6 < v5; ++v6) {
                    if(arr_b1[v6] == v2 && _BufferKt.rangeEquals(segment0, v6 + 1, arr_b, 1, v3)) {
                        return ((long)(v6 - segment0.pos)) + v1;
                    }
                }
                v1 += (long)(segment0.limit - segment0.pos);
                segment0 = segment0.next;
                Intrinsics.checkNotNull(segment0);
                v = v1;
            }
            return -1L;
        }
        long v7;
        while((v7 = ((long)(segment0.limit - segment0.pos)) + v1) <= v) {
            segment0 = segment0.next;
            Intrinsics.checkNotNull(segment0);
            v1 = v7;
        }
        if(segment0 == null) {
            return -1L;
        }
        byte[] arr_b2 = byteString0.internalArray$okio();
        int v8 = arr_b2[0];
        int v9 = byteString0.size();
        long v10 = this.size() - ((long)v9) + 1L;
        while(v1 < v10) {
            byte[] arr_b3 = segment0.data;
            int v11 = (int)Math.min(segment0.limit, ((long)segment0.pos) + v10 - v1);
            v6 = (int)(((long)segment0.pos) + v - v1);
            while(v6 < v11) {
                if(arr_b3[v6] != v8 || !_BufferKt.rangeEquals(segment0, v6 + 1, arr_b2, 1, v9)) {
                    ++v6;
                    continue;
                }
                return ((long)(v6 - segment0.pos)) + v1;
            }
            v1 += (long)(segment0.limit - segment0.pos);
            segment0 = segment0.next;
            Intrinsics.checkNotNull(segment0);
            v = v1;
        }
        return -1L;
    }

    @Override  // okio.BufferedSource
    public long indexOfElement(ByteString byteString0) {
        Intrinsics.checkNotNullParameter(byteString0, "targetBytes");
        return this.indexOfElement(byteString0, 0L);
    }

    @Override  // okio.BufferedSource
    public long indexOfElement(ByteString byteString0, long v) {
        int v4;
        Intrinsics.checkNotNullParameter(byteString0, "targetBytes");
        long v1 = 0L;
        if(v < 0L) {
            throw new IllegalArgumentException(("fromIndex < 0: " + v).toString());
        }
        Segment segment0 = this.head;
        if(segment0 == null) {
            return -1L;
        }
        if(this.size() - v < v) {
            for(v1 = this.size(); v1 > v; v1 -= (long)(segment0.limit - segment0.pos)) {
                segment0 = segment0.prev;
                Intrinsics.checkNotNull(segment0);
            }
            if(segment0 == null) {
                return -1L;
            }
            if(byteString0.size() == 2) {
                int v2 = byteString0.getByte(0);
                int v3 = byteString0.getByte(1);
                while(v1 < this.size()) {
                    byte[] arr_b = segment0.data;
                    v4 = (int)(((long)segment0.pos) + v - v1);
                    int v5 = segment0.limit;
                    while(v4 < v5) {
                        int v6 = arr_b[v4];
                        if(v6 == v2 || v6 == v3) {
                            return ((long)(v4 - segment0.pos)) + v1;
                        }
                        ++v4;
                    }
                    v1 += (long)(segment0.limit - segment0.pos);
                    segment0 = segment0.next;
                    Intrinsics.checkNotNull(segment0);
                    v = v1;
                }
                return -1L;
            }
            byte[] arr_b1 = byteString0.internalArray$okio();
            while(v1 < this.size()) {
                byte[] arr_b2 = segment0.data;
                v4 = (int)(((long)segment0.pos) + v - v1);
                int v7 = segment0.limit;
                while(v4 < v7) {
                    int v8 = arr_b2[v4];
                    for(int v9 = 0; v9 < arr_b1.length; ++v9) {
                        if(v8 == arr_b1[v9]) {
                            return ((long)(v4 - segment0.pos)) + v1;
                        }
                    }
                    ++v4;
                }
                v1 += (long)(segment0.limit - segment0.pos);
                segment0 = segment0.next;
                Intrinsics.checkNotNull(segment0);
                v = v1;
            }
            return -1L;
        }
        long v10;
        while((v10 = ((long)(segment0.limit - segment0.pos)) + v1) <= v) {
            segment0 = segment0.next;
            Intrinsics.checkNotNull(segment0);
            v1 = v10;
        }
        if(segment0 == null) {
            return -1L;
        }
        if(byteString0.size() == 2) {
            int v11 = byteString0.getByte(0);
            int v12 = byteString0.getByte(1);
            while(v1 < this.size()) {
                byte[] arr_b3 = segment0.data;
                v4 = (int)(((long)segment0.pos) + v - v1);
                int v13 = segment0.limit;
                while(v4 < v13) {
                    int v14 = arr_b3[v4];
                    if(v14 != v11 && v14 != v12) {
                        ++v4;
                        continue;
                    }
                    return ((long)(v4 - segment0.pos)) + v1;
                }
                v1 += (long)(segment0.limit - segment0.pos);
                segment0 = segment0.next;
                Intrinsics.checkNotNull(segment0);
                v = v1;
            }
            return -1L;
        }
        byte[] arr_b4 = byteString0.internalArray$okio();
        while(v1 < this.size()) {
            byte[] arr_b5 = segment0.data;
            v4 = (int)(((long)segment0.pos) + v - v1);
            int v15 = segment0.limit;
            while(v4 < v15) {
                int v16 = arr_b5[v4];
                int v17 = 0;
                while(v17 < arr_b4.length) {
                    if(v16 != arr_b4[v17]) {
                        ++v17;
                        continue;
                    }
                    return ((long)(v4 - segment0.pos)) + v1;
                }
                ++v4;
            }
            v1 += (long)(segment0.limit - segment0.pos);
            segment0 = segment0.next;
            Intrinsics.checkNotNull(segment0);
            v = v1;
        }
        return -1L;
    }

    @Override  // okio.BufferedSource
    public InputStream inputStream() {
        return new AutoCloseable() {
            @Override
            public int available() {
                return (int)Math.min(Buffer.this.size(), 0x7FFFFFFFL);
            }

            @Override
            public void close() {
            }

            @Override
            public int read() {
                return Buffer.this.size() <= 0L ? -1 : Buffer.this.readByte() & 0xFF;
            }

            @Override
            public int read(byte[] arr_b, int v, int v1) {
                Intrinsics.checkNotNullParameter(arr_b, "sink");
                return Buffer.this.read(arr_b, v, v1);
            }

            @Override
            public String toString() {
                return Buffer.this + ".inputStream()";
            }
        };
    }

    @Override
    public boolean isOpen() {
        return true;
    }

    public final ByteString md5() {
        return this.digest("MD5");
    }

    @Override  // okio.BufferedSink
    public OutputStream outputStream() {
        return new AutoCloseable() {
            @Override
            public void close() {
            }

            @Override
            public void flush() {
            }

            @Override
            public String toString() {
                return Buffer.this + ".outputStream()";
            }

            @Override
            public void write(int v) {
                Buffer.this.writeByte(v);
            }

            @Override
            public void write(byte[] arr_b, int v, int v1) {
                Intrinsics.checkNotNullParameter(arr_b, "data");
                Buffer.this.write(arr_b, v, v1);
            }
        };
    }

    @Override  // okio.BufferedSource
    public BufferedSource peek() {
        return Okio.buffer(new PeekSource(this));
    }

    @Override  // okio.BufferedSource
    public boolean rangeEquals(long v, ByteString byteString0) {
        Intrinsics.checkNotNullParameter(byteString0, "bytes");
        return this.rangeEquals(v, byteString0, 0, byteString0.size());
    }

    @Override  // okio.BufferedSource
    public boolean rangeEquals(long v, ByteString byteString0, int v1, int v2) {
        Intrinsics.checkNotNullParameter(byteString0, "bytes");
        if(v >= 0L && v1 >= 0 && v2 >= 0 && this.size() - v >= ((long)v2) && byteString0.size() - v1 >= v2) {
            for(int v3 = 0; v3 < v2; ++v3) {
                if(this.getByte(((long)v3) + v) != byteString0.getByte(v1 + v3)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int read(ByteBuffer byteBuffer0) throws IOException {
        Intrinsics.checkNotNullParameter(byteBuffer0, "sink");
        Segment segment0 = this.head;
        if(segment0 == null) {
            return -1;
        }
        int v = Math.min(byteBuffer0.remaining(), segment0.limit - segment0.pos);
        byteBuffer0.put(segment0.data, segment0.pos, v);
        segment0.pos += v;
        this.size -= (long)v;
        if(segment0.pos == segment0.limit) {
            this.head = segment0.pop();
            SegmentPool.recycle(segment0);
        }
        return v;
    }

    @Override  // okio.BufferedSource
    public int read(byte[] arr_b) {
        Intrinsics.checkNotNullParameter(arr_b, "sink");
        return this.read(arr_b, 0, arr_b.length);
    }

    @Override  // okio.BufferedSource
    public int read(byte[] arr_b, int v, int v1) {
        Intrinsics.checkNotNullParameter(arr_b, "sink");
        _UtilKt.checkOffsetAndCount(arr_b.length, v, v1);
        Segment segment0 = this.head;
        if(segment0 == null) {
            return -1;
        }
        int v2 = Math.min(v1, segment0.limit - segment0.pos);
        ArraysKt.copyInto(segment0.data, arr_b, v, segment0.pos, segment0.pos + v2);
        segment0.pos += v2;
        this.setSize$okio(this.size() - ((long)v2));
        if(segment0.pos == segment0.limit) {
            this.head = segment0.pop();
            SegmentPool.recycle(segment0);
        }
        return v2;
    }

    @Override  // okio.Source
    public long read(Buffer buffer0, long v) {
        Intrinsics.checkNotNullParameter(buffer0, "sink");
        if(v < 0L) {
            throw new IllegalArgumentException(("byteCount < 0: " + v).toString());
        }
        if(this.size() == 0L) {
            return -1L;
        }
        if(v > this.size()) {
            v = this.size();
        }
        buffer0.write(this, v);
        return v;
    }

    @Override  // okio.BufferedSource
    public long readAll(Sink sink0) throws IOException {
        Intrinsics.checkNotNullParameter(sink0, "sink");
        long v = this.size();
        if(v > 0L) {
            sink0.write(this, v);
        }
        return v;
    }

    public final UnsafeCursor readAndWriteUnsafe() {
        return Buffer.readAndWriteUnsafe$default(this, null, 1, null);
    }

    public final UnsafeCursor readAndWriteUnsafe(UnsafeCursor buffer$UnsafeCursor0) {
        Intrinsics.checkNotNullParameter(buffer$UnsafeCursor0, "unsafeCursor");
        return _BufferKt.commonReadAndWriteUnsafe(this, buffer$UnsafeCursor0);
    }

    public static UnsafeCursor readAndWriteUnsafe$default(Buffer buffer0, UnsafeCursor buffer$UnsafeCursor0, int v, Object object0) {
        if((v & 1) != 0) {
            buffer$UnsafeCursor0 = _UtilKt.getDEFAULT__new_UnsafeCursor();
        }
        return buffer0.readAndWriteUnsafe(buffer$UnsafeCursor0);
    }

    @Override  // okio.BufferedSource
    public byte readByte() throws EOFException {
        if(this.size() == 0L) {
            throw new EOFException();
        }
        Segment segment0 = this.head;
        Intrinsics.checkNotNull(segment0);
        int v = segment0.limit;
        int v1 = segment0.pos + 1;
        byte b = segment0.data[segment0.pos];
        this.setSize$okio(this.size() - 1L);
        if(v1 == v) {
            this.head = segment0.pop();
            SegmentPool.recycle(segment0);
            return b;
        }
        segment0.pos = v1;
        return b;
    }

    @Override  // okio.BufferedSource
    public byte[] readByteArray() [...] // 潜在的解密器

    @Override  // okio.BufferedSource
    public byte[] readByteArray(long v) throws EOFException {
        if(v < 0L || v > 0x7FFFFFFFL) {
            throw new IllegalArgumentException(("byteCount: " + v).toString());
        }
        if(this.size() < v) {
            throw new EOFException();
        }
        byte[] arr_b = new byte[((int)v)];
        this.readFully(arr_b);
        return arr_b;
    }

    @Override  // okio.BufferedSource
    public ByteString readByteString() {
        return this.readByteString(this.size());
    }

    @Override  // okio.BufferedSource
    public ByteString readByteString(long v) throws EOFException {
        if(v < 0L || v > 0x7FFFFFFFL) {
            throw new IllegalArgumentException(("byteCount: " + v).toString());
        }
        if(this.size() < v) {
            throw new EOFException();
        }
        if(v >= 0x1000L) {
            ByteString byteString0 = this.snapshot(((int)v));
            this.skip(v);
            return byteString0;
        }
        return new ByteString(this.readByteArray(v));
    }

    @Override  // okio.BufferedSource
    public long readDecimalLong() throws EOFException {
        if(this.size() == 0L) {
            throw new EOFException();
        }
        int v = 0;
        long v1 = 0L;
        long v2 = -7L;
        boolean z = false;
        boolean z1 = false;
        do {
            Segment segment0 = this.head;
            Intrinsics.checkNotNull(segment0);
            byte[] arr_b = segment0.data;
            int v3 = segment0.pos;
            int v4 = segment0.limit;
            while(v3 < v4) {
                int v5 = arr_b[v3];
                if(v5 >= 0x30 && v5 <= 57) {
                    int v6 = Long.compare(v1, -922337203685477580L);
                    if(v6 >= 0 && (v6 != 0 || ((long)(0x30 - v5)) >= v2)) {
                        v1 = v1 * 10L + ((long)(0x30 - v5));
                        goto label_25;
                    }
                    Buffer buffer0 = new Buffer().writeDecimalLong(v1).writeByte(v5);
                    if(!z) {
                        buffer0.readByte();
                    }
                    throw new NumberFormatException("Number too large: ");
                }
                else if(v5 == 45 && v == 0) {
                    --v2;
                    z = true;
                }
                else {
                    goto label_28;
                }
            label_25:
                ++v3;
                ++v;
                continue;
            label_28:
                z1 = true;
                if(true) {
                    break;
                }
            }
            if(v3 == v4) {
                this.head = segment0.pop();
                SegmentPool.recycle(segment0);
            }
            else {
                segment0.pos = v3;
            }
        }
        while(!z1 && this.head != null);
        this.setSize$okio(this.size() - ((long)v));
        if(v < (z ? 2 : 1)) {
            if(this.size() == 0L) {
                throw new EOFException();
            }
            throw new NumberFormatException((z ? "Expected a digit" : "Expected a digit or \'-\'") + " but was 0x" + _UtilKt.toHexString(this.getByte(0L)));
        }
        return z ? v1 : -v1;
    }

    private final void readFrom(InputStream inputStream0, long v, boolean z) throws IOException {
        while(v > 0L || z) {
            Segment segment0 = this.writableSegment$okio(1);
            int v1 = inputStream0.read(segment0.data, segment0.limit, ((int)Math.min(v, 0x2000 - segment0.limit)));
            if(v1 == -1) {
                if(segment0.pos == segment0.limit) {
                    this.head = segment0.pop();
                    SegmentPool.recycle(segment0);
                }
                if(!z) {
                    throw new EOFException();
                }
                break;
            }
            segment0.limit += v1;
            this.size += (long)v1;
            v -= (long)v1;
        }
    }

    public final Buffer readFrom(InputStream inputStream0) throws IOException {
        Intrinsics.checkNotNullParameter(inputStream0, "input");
        this.readFrom(inputStream0, 0x7FFFFFFFFFFFFFFFL, true);
        return this;
    }

    public final Buffer readFrom(InputStream inputStream0, long v) throws IOException {
        Intrinsics.checkNotNullParameter(inputStream0, "input");
        if(v < 0L) {
            throw new IllegalArgumentException(("byteCount < 0: " + v).toString());
        }
        this.readFrom(inputStream0, v, false);
        return this;
    }

    @Override  // okio.BufferedSource
    public void readFully(Buffer buffer0, long v) throws EOFException {
        Intrinsics.checkNotNullParameter(buffer0, "sink");
        if(this.size() >= v) {
            buffer0.write(this, v);
            return;
        }
        buffer0.write(this, this.size());
        throw new EOFException();
    }

    @Override  // okio.BufferedSource
    public void readFully(byte[] arr_b) throws EOFException {
        Intrinsics.checkNotNullParameter(arr_b, "sink");
        for(int v = 0; v < arr_b.length; v += v1) {
            int v1 = this.read(arr_b, v, arr_b.length - v);
            if(v1 == -1) {
                throw new EOFException();
            }
        }
    }

    @Override  // okio.BufferedSource
    public long readHexadecimalUnsignedLong() throws EOFException {
        int v6;
        if(this.size() == 0L) {
            throw new EOFException();
        }
        int v = 0;
        long v1 = 0L;
        boolean z = false;
        do {
            Segment segment0 = this.head;
            Intrinsics.checkNotNull(segment0);
            byte[] arr_b = segment0.data;
            int v2 = segment0.pos;
            int v3 = segment0.limit;
            while(v2 < v3) {
                int v4 = 97;
                int v5 = arr_b[v2];
                if(v5 < 0x30 || v5 > 57) {
                    if(v5 < 97 || v5 > 102) {
                        v4 = 65;
                        if(v5 < 65 || v5 > 70) {
                            if(v == 0) {
                                throw new NumberFormatException("Expected leading [0-9a-fA-F] character but was 0x" + _UtilKt.toHexString(((byte)v5)));
                            }
                            z = true;
                            break;
                        }
                    }
                    v6 = v5 - v4 + 10;
                }
                else {
                    v6 = v5 - 0x30;
                }
                if((0xF000000000000000L & v1) != 0L) {
                    throw new NumberFormatException("Number too large: ");
                }
                v1 = v1 << 4 | ((long)v6);
                ++v2;
                ++v;
            }
            if(v2 == v3) {
                this.head = segment0.pop();
                SegmentPool.recycle(segment0);
            }
            else {
                segment0.pos = v2;
            }
        }
        while(!z && this.head != null);
        this.setSize$okio(this.size() - ((long)v));
        return v1;
    }

    @Override  // okio.BufferedSource
    public int readInt() throws EOFException {
        if(this.size() < 4L) {
            throw new EOFException();
        }
        Segment segment0 = this.head;
        Intrinsics.checkNotNull(segment0);
        int v = segment0.pos;
        int v1 = segment0.limit;
        if(((long)(v1 - v)) < 4L) {
            return (this.readByte() & 0xFF) << 24 | (this.readByte() & 0xFF) << 16 | (this.readByte() & 0xFF) << 8 | this.readByte() & 0xFF;
        }
        int v2 = segment0.data[v + 3] & 0xFF | ((segment0.data[v + 1] & 0xFF) << 16 | (segment0.data[v] & 0xFF) << 24 | (segment0.data[v + 2] & 0xFF) << 8);
        this.setSize$okio(this.size() - 4L);
        if(v + 4 == v1) {
            this.head = segment0.pop();
            SegmentPool.recycle(segment0);
            return v2;
        }
        segment0.pos = v + 4;
        return v2;
    }

    @Override  // okio.BufferedSource
    public int readIntLe() throws EOFException {
        return _UtilKt.reverseBytes(this.readInt());
    }

    @Override  // okio.BufferedSource
    public long readLong() throws EOFException {
        if(this.size() < 8L) {
            throw new EOFException();
        }
        Segment segment0 = this.head;
        Intrinsics.checkNotNull(segment0);
        int v = segment0.pos;
        int v1 = segment0.limit;
        if(((long)(v1 - v)) < 8L) {
            return (((long)this.readInt()) & 0xFFFFFFFFL) << 0x20 | 0xFFFFFFFFL & ((long)this.readInt());
        }
        long v2 = (((long)segment0.data[v + 3]) & 0xFFL) << 0x20 | ((((long)segment0.data[v]) & 0xFFL) << 56 | (((long)segment0.data[v + 1]) & 0xFFL) << 0x30 | (((long)segment0.data[v + 2]) & 0xFFL) << 40) | (((long)segment0.data[v + 4]) & 0xFFL) << 24 | (((long)segment0.data[v + 5]) & 0xFFL) << 16 | (((long)segment0.data[v + 6]) & 0xFFL) << 8 | ((long)segment0.data[v + 7]) & 0xFFL;
        this.setSize$okio(this.size() - 8L);
        if(v + 8 == v1) {
            this.head = segment0.pop();
            SegmentPool.recycle(segment0);
            return v2;
        }
        segment0.pos = v + 8;
        return v2;
    }

    @Override  // okio.BufferedSource
    public long readLongLe() throws EOFException {
        return _UtilKt.reverseBytes(this.readLong());
    }

    @Override  // okio.BufferedSource
    public short readShort() throws EOFException {
        if(this.size() < 2L) {
            throw new EOFException();
        }
        Segment segment0 = this.head;
        Intrinsics.checkNotNull(segment0);
        int v = segment0.pos;
        int v1 = segment0.limit;
        if(v1 - v < 2) {
            return (short)((this.readByte() & 0xFF) << 8 | this.readByte() & 0xFF);
        }
        int v2 = segment0.data[v + 1] & 0xFF | (segment0.data[v] & 0xFF) << 8;
        this.setSize$okio(this.size() - 2L);
        if(v + 2 == v1) {
            this.head = segment0.pop();
            SegmentPool.recycle(segment0);
            return (short)v2;
        }
        segment0.pos = v + 2;
        return (short)v2;
    }

    @Override  // okio.BufferedSource
    public short readShortLe() throws EOFException {
        return _UtilKt.reverseBytes(this.readShort());
    }

    @Override  // okio.BufferedSource
    public String readString(long v, Charset charset0) throws EOFException {
        Intrinsics.checkNotNullParameter(charset0, "charset");
        int v1 = Long.compare(v, 0L);
        if(v1 < 0 || v > 0x7FFFFFFFL) {
            throw new IllegalArgumentException(("byteCount: " + v).toString());
        }
        if(this.size < v) {
            throw new EOFException();
        }
        if(v1 == 0) {
            return "";
        }
        Segment segment0 = this.head;
        Intrinsics.checkNotNull(segment0);
        if(((long)segment0.pos) + v > ((long)segment0.limit)) {
            return new String(this.readByteArray(v), charset0);
        }
        String s = new String(segment0.data, segment0.pos, ((int)v), charset0);
        segment0.pos += (int)v;
        this.size -= v;
        if(segment0.pos == segment0.limit) {
            this.head = segment0.pop();
            SegmentPool.recycle(segment0);
        }
        return s;
    }

    @Override  // okio.BufferedSource
    public String readString(Charset charset0) {
        Intrinsics.checkNotNullParameter(charset0, "charset");
        return this.readString(this.size, charset0);
    }

    public final UnsafeCursor readUnsafe() {
        return Buffer.readUnsafe$default(this, null, 1, null);
    }

    public final UnsafeCursor readUnsafe(UnsafeCursor buffer$UnsafeCursor0) {
        Intrinsics.checkNotNullParameter(buffer$UnsafeCursor0, "unsafeCursor");
        return _BufferKt.commonReadUnsafe(this, buffer$UnsafeCursor0);
    }

    public static UnsafeCursor readUnsafe$default(Buffer buffer0, UnsafeCursor buffer$UnsafeCursor0, int v, Object object0) {
        if((v & 1) != 0) {
            buffer$UnsafeCursor0 = _UtilKt.getDEFAULT__new_UnsafeCursor();
        }
        return buffer0.readUnsafe(buffer$UnsafeCursor0);
    }

    @Override  // okio.BufferedSource
    public String readUtf8() [...] // 潜在的解密器

    @Override  // okio.BufferedSource
    public String readUtf8(long v) throws EOFException {
        return this.readString(v, Charsets.UTF_8);
    }

    @Override  // okio.BufferedSource
    public int readUtf8CodePoint() throws EOFException {
        int v4;
        int v3;
        int v2;
        if(this.size() == 0L) {
            throw new EOFException();
        }
        int v = this.getByte(0L);
        int v1 = 1;
        if((v & 0x80) == 0) {
            v2 = v & 0x7F;
            v3 = 1;
            v4 = 0;
            goto label_22;
        }
        if((v & 0xE0) == 0xC0) {
            v2 = v & 0x1F;
            v3 = 2;
            v4 = 0x80;
            goto label_22;
        }
        boolean z = false;
        if((v & 0xF0) == 0xE0) {
            z = true;
            v2 = v & 15;
            v3 = 3;
            v4 = 0x800;
        }
        else if((v & 0xF8) == 0xF0) {
            z = true;
            v2 = v & 7;
            v3 = 4;
            v4 = 0x10000;
        }
        if(z) {
        label_22:
            if(this.size() < ((long)v3)) {
                throw new EOFException("size < " + v3 + ": " + this.size() + " (to read code point prefixed 0x" + _UtilKt.toHexString(((byte)v)) + ')');
            }
            while(v1 < v3) {
                int v5 = this.getByte(((long)v1));
                if((v5 & 0xC0) == 0x80) {
                    v2 = v2 << 6 | v5 & 0x3F;
                    ++v1;
                    continue;
                }
                this.skip(((long)v1));
                return 0xFFFD;
            }
            this.skip(((long)v3));
            if(v2 > 0x10FFFF) {
                return 0xFFFD;
            }
            if(0xD800 <= v2 && v2 < 0xE000) {
                return 0xFFFD;
            }
            return v2 >= v4 ? v2 : 0xFFFD;
        }
        this.skip(1L);
        return 0xFFFD;
    }

    @Override  // okio.BufferedSource
    public String readUtf8Line() throws EOFException {
        long v = this.indexOf(10);
        if(v != -1L) {
            return _BufferKt.readUtf8Line(this, v);
        }
        return this.size() == 0L ? null : this.readUtf8(this.size());
    }

    @Override  // okio.BufferedSource
    public String readUtf8LineStrict() throws EOFException {
        return this.readUtf8LineStrict(0x7FFFFFFFFFFFFFFFL);
    }

    @Override  // okio.BufferedSource
    public String readUtf8LineStrict(long v) throws EOFException {
        long v1 = 0x7FFFFFFFFFFFFFFFL;
        if(v < 0L) {
            throw new IllegalArgumentException(("limit < 0: " + v).toString());
        }
        if(v != 0x7FFFFFFFFFFFFFFFL) {
            v1 = v + 1L;
        }
        long v2 = this.indexOf(10, 0L, v1);
        if(v2 != -1L) {
            return _BufferKt.readUtf8Line(this, v2);
        }
        if(v1 < this.size() && this.getByte(v1 - 1L) == 13 && this.getByte(v1) == 10) {
            return _BufferKt.readUtf8Line(this, v1);
        }
        Buffer buffer0 = new Buffer();
        this.copyTo(buffer0, 0L, Math.min(0x20L, this.size()));
        throw new EOFException("\\n not found: limit=" + Math.min(this.size(), v) + " content=" + buffer0.readByteString().hex() + '…');
    }

    @Override  // okio.BufferedSource
    public boolean request(long v) {
        return this.size >= v;
    }

    @Override  // okio.BufferedSource
    public void require(long v) throws EOFException {
        if(this.size < v) {
            throw new EOFException();
        }
    }

    @Override  // okio.BufferedSource
    public int select(Options options0) {
        Intrinsics.checkNotNullParameter(options0, "options");
        int v = _BufferKt.selectPrefix$default(this, options0, false, 2, null);
        if(v == -1) {
            return -1;
        }
        this.skip(((long)options0.getByteStrings$okio()[v].size()));
        return v;
    }

    public final void setSize$okio(long v) {
        this.size = v;
    }

    public final ByteString sha1() {
        return this.digest("SHA-1");
    }

    public final ByteString sha256() {
        return this.digest("SHA-256");
    }

    public final ByteString sha512() {
        return this.digest("SHA-512");
    }

    public final long size() {
        return this.size;
    }

    @Override  // okio.BufferedSource
    public void skip(long v) throws EOFException {
        while(v > 0L) {
            Segment segment0 = this.head;
            if(segment0 == null) {
                throw new EOFException();
            }
            int v1 = (int)Math.min(v, segment0.limit - segment0.pos);
            this.setSize$okio(this.size() - ((long)v1));
            v -= (long)v1;
            segment0.pos += v1;
            if(segment0.pos == segment0.limit) {
                this.head = segment0.pop();
                SegmentPool.recycle(segment0);
            }
        }
    }

    public final ByteString snapshot() {
        if(this.size() > 0x7FFFFFFFL) {
            throw new IllegalStateException(("size > Int.MAX_VALUE: " + this.size()).toString());
        }
        return this.snapshot(((int)this.size()));
    }

    public final ByteString snapshot(int v) {
        if(v == 0) {
            return ByteString.EMPTY;
        }
        _UtilKt.checkOffsetAndCount(this.size(), 0L, v);
        Segment segment0 = this.head;
        int v1 = 0;
        int v2 = 0;
        int v3 = 0;
        while(v2 < v) {
            Intrinsics.checkNotNull(segment0);
            if(segment0.limit == segment0.pos) {
                throw new AssertionError("s.limit == s.pos");
            }
            v2 += segment0.limit - segment0.pos;
            ++v3;
            segment0 = segment0.next;
        }
        byte[][] arr2_b = new byte[v3][];
        int[] arr_v = new int[v3 * 2];
        Segment segment1 = this.head;
        int v4 = 0;
        while(v1 < v) {
            Intrinsics.checkNotNull(segment1);
            arr2_b[v4] = segment1.data;
            v1 += segment1.limit - segment1.pos;
            arr_v[v4] = Math.min(v1, v);
            arr_v[arr2_b.length + v4] = segment1.pos;
            segment1.shared = true;
            ++v4;
            segment1 = segment1.next;
        }
        return new SegmentedByteString(arr2_b, arr_v);
    }

    @Override  // okio.Source, okio.Sink
    public Timeout timeout() {
        return Timeout.NONE;
    }

    @Override
    public String toString() {
        return this.snapshot().toString();
    }

    public final Segment writableSegment$okio(int v) {
        if(v < 1 || v > 0x2000) {
            throw new IllegalArgumentException("unexpected capacity");
        }
        Segment segment0 = this.head;
        if(segment0 == null) {
            Segment segment1 = SegmentPool.take();
            this.head = segment1;
            segment1.prev = segment1;
            segment1.next = segment1;
            return segment1;
        }
        Intrinsics.checkNotNull(segment0);
        Segment segment2 = segment0.prev;
        Intrinsics.checkNotNull(segment2);
        return segment2.limit + v > 0x2000 || !segment2.owner ? segment2.push(SegmentPool.take()) : segment2;
    }

    @Override
    public int write(ByteBuffer byteBuffer0) throws IOException {
        Intrinsics.checkNotNullParameter(byteBuffer0, "source");
        int v = byteBuffer0.remaining();
        int v1 = v;
        while(v1 > 0) {
            Segment segment0 = this.writableSegment$okio(1);
            int v2 = Math.min(v1, 0x2000 - segment0.limit);
            byteBuffer0.get(segment0.data, segment0.limit, v2);
            v1 -= v2;
            segment0.limit += v2;
        }
        this.size += (long)v;
        return v;
    }

    public Buffer write(ByteString byteString0) {
        Intrinsics.checkNotNullParameter(byteString0, "byteString");
        byteString0.write$okio(this, 0, byteString0.size());
        return this;
    }

    public Buffer write(ByteString byteString0, int v, int v1) {
        Intrinsics.checkNotNullParameter(byteString0, "byteString");
        byteString0.write$okio(this, v, v1);
        return this;
    }

    public Buffer write(Source source0, long v) throws IOException {
        Intrinsics.checkNotNullParameter(source0, "source");
        while(v > 0L) {
            long v1 = source0.read(this, v);
            if(v1 == -1L) {
                throw new EOFException();
            }
            v -= v1;
        }
        return this;
    }

    public Buffer write(byte[] arr_b) {
        Intrinsics.checkNotNullParameter(arr_b, "source");
        return this.write(arr_b, 0, arr_b.length);
    }

    public Buffer write(byte[] arr_b, int v, int v1) {
        Intrinsics.checkNotNullParameter(arr_b, "source");
        _UtilKt.checkOffsetAndCount(arr_b.length, v, v1);
        int v2 = v1 + v;
        while(v < v2) {
            Segment segment0 = this.writableSegment$okio(1);
            int v3 = Math.min(v2 - v, 0x2000 - segment0.limit);
            int v4 = v + v3;
            ArraysKt.copyInto(arr_b, segment0.data, segment0.limit, v, v4);
            segment0.limit += v3;
            v = v4;
        }
        this.setSize$okio(this.size() + ((long)v1));
        return this;
    }

    @Override  // okio.BufferedSink
    public BufferedSink write(ByteString byteString0) {
        return this.write(byteString0);
    }

    @Override  // okio.BufferedSink
    public BufferedSink write(ByteString byteString0, int v, int v1) {
        return this.write(byteString0, v, v1);
    }

    @Override  // okio.BufferedSink
    public BufferedSink write(Source source0, long v) {
        return this.write(source0, v);
    }

    @Override  // okio.BufferedSink
    public BufferedSink write(byte[] arr_b) {
        return this.write(arr_b);
    }

    @Override  // okio.BufferedSink
    public BufferedSink write(byte[] arr_b, int v, int v1) {
        return this.write(arr_b, v, v1);
    }

    @Override  // okio.Sink
    public void write(Buffer buffer0, long v) {
        Segment segment3;
        Intrinsics.checkNotNullParameter(buffer0, "source");
        if(buffer0 == this) {
            throw new IllegalArgumentException("source == this");
        }
        _UtilKt.checkOffsetAndCount(buffer0.size(), 0L, v);
        while(v > 0L) {
            Segment segment0 = buffer0.head;
            Intrinsics.checkNotNull(segment0);
            int v1 = segment0.limit;
            Segment segment1 = buffer0.head;
            Intrinsics.checkNotNull(segment1);
            if(v < ((long)(v1 - segment1.pos))) {
                Segment segment2 = this.head;
                if(segment2 == null) {
                    segment3 = null;
                }
                else {
                    Intrinsics.checkNotNull(segment2);
                    segment3 = segment2.prev;
                }
                if(segment3 != null && segment3.owner && ((long)segment3.limit) + v - ((long)(segment3.shared ? 0 : segment3.pos)) <= 0x2000L) {
                    Segment segment4 = buffer0.head;
                    Intrinsics.checkNotNull(segment4);
                    segment4.writeTo(segment3, ((int)v));
                    buffer0.setSize$okio(buffer0.size() - v);
                    this.setSize$okio(this.size() + v);
                    return;
                }
                Segment segment5 = buffer0.head;
                Intrinsics.checkNotNull(segment5);
                buffer0.head = segment5.split(((int)v));
            }
            Segment segment6 = buffer0.head;
            Intrinsics.checkNotNull(segment6);
            long v2 = (long)(segment6.limit - segment6.pos);
            buffer0.head = segment6.pop();
            Segment segment7 = this.head;
            if(segment7 == null) {
                this.head = segment6;
                segment6.prev = segment6;
                segment6.next = segment6.prev;
            }
            else {
                Intrinsics.checkNotNull(segment7);
                Segment segment8 = segment7.prev;
                Intrinsics.checkNotNull(segment8);
                segment8.push(segment6).compact();
            }
            buffer0.setSize$okio(buffer0.size() - v2);
            this.setSize$okio(this.size() + v2);
            v -= v2;
        }
    }

    @Override  // okio.BufferedSink
    public long writeAll(Source source0) throws IOException {
        Intrinsics.checkNotNullParameter(source0, "source");
        long v = 0L;
        long v1;
        while((v1 = source0.read(this, 0x2000L)) != -1L) {
            v += v1;
        }
        return v;
    }

    public Buffer writeByte(int v) {
        Segment segment0 = this.writableSegment$okio(1);
        int v1 = segment0.limit;
        segment0.limit = v1 + 1;
        segment0.data[v1] = (byte)v;
        this.setSize$okio(this.size() + 1L);
        return this;
    }

    @Override  // okio.BufferedSink
    public BufferedSink writeByte(int v) {
        return this.writeByte(v);
    }

    public Buffer writeDecimalLong(long v) {
        boolean z;
        int v1 = 1;
        int v2 = Long.compare(v, 0L);
        if(v2 == 0) {
            return this.writeByte(0x30);
        }
        if(v2 < 0) {
            v = -v;
            if(v < 0L) {
                return this.writeUtf8("-9223372036854775808");
            }
            z = true;
        }
        else {
            z = false;
        }
        if(v >= 100000000L) {
            if(v >= 1000000000000L) {
                if(v >= 1000000000000000L) {
                    if(v >= 100000000000000000L) {
                        v1 = v >= 1000000000000000000L ? 19 : 18;
                    }
                    else if(v < 10000000000000000L) {
                        v1 = 16;
                    }
                    else {
                        v1 = 17;
                    }
                }
                else if(v < 10000000000000L) {
                    v1 = 13;
                }
                else if(v < 100000000000000L) {
                    v1 = 14;
                }
                else {
                    v1 = 15;
                }
            }
            else if(v >= 10000000000L) {
                v1 = v < 100000000000L ? 11 : 12;
            }
            else if(v < 1000000000L) {
                v1 = 9;
            }
            else {
                v1 = 10;
            }
        }
        else if(v >= 10000L) {
            if(v >= 1000000L) {
                v1 = v < 10000000L ? 7 : 8;
            }
            else if(v < 100000L) {
                v1 = 5;
            }
            else {
                v1 = 6;
            }
        }
        else if(v >= 100L) {
            v1 = v < 1000L ? 3 : 4;
        }
        else if(v >= 10L) {
            v1 = 2;
        }
        if(z) {
            ++v1;
        }
        Segment segment0 = this.writableSegment$okio(v1);
        byte[] arr_b = segment0.data;
        int v3 = segment0.limit + v1;
        while(v != 0L) {
            --v3;
            arr_b[v3] = new byte[]{0x30, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102}[((int)(v % 10L))];
            v /= 10L;
        }
        if(z) {
            arr_b[v3 - 1] = 45;
        }
        segment0.limit += v1;
        this.setSize$okio(this.size() + ((long)v1));
        return this;
    }

    @Override  // okio.BufferedSink
    public BufferedSink writeDecimalLong(long v) {
        return this.writeDecimalLong(v);
    }

    public Buffer writeHexadecimalUnsignedLong(long v) {
        if(v == 0L) {
            return this.writeByte(0x30);
        }
        long v1 = v >>> 1 | v;
        long v2 = v1 | v1 >>> 2;
        long v3 = v2 | v2 >>> 4;
        long v4 = v3 | v3 >>> 8;
        long v5 = v4 | v4 >>> 16;
        long v6 = v5 | v5 >>> 0x20;
        long v7 = v6 - (v6 >>> 1 & 0x5555555555555555L);
        long v8 = (v7 >>> 2 & 0x3333333333333333L) + (v7 & 0x3333333333333333L);
        long v9 = (v8 >>> 4) + v8 & 0xF0F0F0F0F0F0F0FL;
        long v10 = v9 + (v9 >>> 8);
        long v11 = v10 + (v10 >>> 16);
        int v12 = (int)(((v11 & 0x3FL) + (v11 >>> 0x20 & 0x3FL) + 3L) / 4L);
        Segment segment0 = this.writableSegment$okio(v12);
        byte[] arr_b = segment0.data;
        int v13 = segment0.limit + v12 - 1;
        int v14 = segment0.limit;
        while(v13 >= v14) {
            arr_b[v13] = new byte[]{0x30, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102}[((int)(15L & v))];
            v >>>= 4;
            --v13;
        }
        segment0.limit += v12;
        this.setSize$okio(this.size() + ((long)v12));
        return this;
    }

    @Override  // okio.BufferedSink
    public BufferedSink writeHexadecimalUnsignedLong(long v) {
        return this.writeHexadecimalUnsignedLong(v);
    }

    public Buffer writeInt(int v) {
        Segment segment0 = this.writableSegment$okio(4);
        int v1 = segment0.limit;
        segment0.data[v1] = (byte)(v >>> 24 & 0xFF);
        segment0.data[v1 + 1] = (byte)(v >>> 16 & 0xFF);
        segment0.data[v1 + 2] = (byte)(v >>> 8 & 0xFF);
        segment0.data[v1 + 3] = (byte)(v & 0xFF);
        segment0.limit = v1 + 4;
        this.setSize$okio(this.size() + 4L);
        return this;
    }

    @Override  // okio.BufferedSink
    public BufferedSink writeInt(int v) {
        return this.writeInt(v);
    }

    public Buffer writeIntLe(int v) {
        return this.writeInt((v & 0xFF) << 24 | ((0xFF000000 & v) >>> 24 | (0xFF0000 & v) >>> 8 | (0xFF00 & v) << 8));
    }

    @Override  // okio.BufferedSink
    public BufferedSink writeIntLe(int v) {
        return this.writeIntLe(v);
    }

    public Buffer writeLong(long v) {
        Segment segment0 = this.writableSegment$okio(8);
        int v1 = segment0.limit;
        segment0.data[v1] = (byte)(((int)(v >>> 56 & 0xFFL)));
        segment0.data[v1 + 1] = (byte)(((int)(v >>> 0x30 & 0xFFL)));
        segment0.data[v1 + 2] = (byte)(((int)(v >>> 40 & 0xFFL)));
        segment0.data[v1 + 3] = (byte)(((int)(v >>> 0x20 & 0xFFL)));
        segment0.data[v1 + 4] = (byte)(((int)(v >>> 24 & 0xFFL)));
        segment0.data[v1 + 5] = (byte)(((int)(v >>> 16 & 0xFFL)));
        segment0.data[v1 + 6] = (byte)(((int)(v >>> 8 & 0xFFL)));
        segment0.data[v1 + 7] = (byte)(((int)(v & 0xFFL)));
        segment0.limit = v1 + 8;
        this.setSize$okio(this.size() + 8L);
        return this;
    }

    @Override  // okio.BufferedSink
    public BufferedSink writeLong(long v) {
        return this.writeLong(v);
    }

    public Buffer writeLongLe(long v) {
        return this.writeLong(_UtilKt.reverseBytes(v));
    }

    @Override  // okio.BufferedSink
    public BufferedSink writeLongLe(long v) {
        return this.writeLongLe(v);
    }

    public Buffer writeShort(int v) {
        Segment segment0 = this.writableSegment$okio(2);
        int v1 = segment0.limit;
        segment0.data[v1] = (byte)(v >>> 8 & 0xFF);
        segment0.data[v1 + 1] = (byte)(v & 0xFF);
        segment0.limit = v1 + 2;
        this.setSize$okio(this.size() + 2L);
        return this;
    }

    @Override  // okio.BufferedSink
    public BufferedSink writeShort(int v) {
        return this.writeShort(v);
    }

    public Buffer writeShortLe(int v) {
        return this.writeShort(((int)(((short)((((short)v) & 0xFF) << 8 | (0xFF00 & ((short)v)) >>> 8)))));
    }

    @Override  // okio.BufferedSink
    public BufferedSink writeShortLe(int v) {
        return this.writeShortLe(v);
    }

    public Buffer writeString(String s, int v, int v1, Charset charset0) {
        Intrinsics.checkNotNullParameter(s, "string");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        if(v < 0) {
            throw new IllegalArgumentException(("beginIndex < 0: " + v).toString());
        }
        if(v1 < v) {
            throw new IllegalArgumentException(("endIndex < beginIndex: " + v1 + " < " + v).toString());
        }
        if(v1 > s.length()) {
            throw new IllegalArgumentException(("endIndex > string.length: " + v1 + " > " + s.length()).toString());
        }
        if(Intrinsics.areEqual(charset0, Charsets.UTF_8)) {
            return this.writeUtf8(s, v, v1);
        }
        String s1 = s.substring(v, v1);
        Intrinsics.checkNotNullExpressionValue(s1, "this as java.lang.String…ing(startIndex, endIndex)");
        byte[] arr_b = s1.getBytes(charset0);
        Intrinsics.checkNotNullExpressionValue(arr_b, "this as java.lang.String).getBytes(charset)");
        return this.write(arr_b, 0, arr_b.length);
    }

    public Buffer writeString(String s, Charset charset0) {
        Intrinsics.checkNotNullParameter(s, "string");
        Intrinsics.checkNotNullParameter(charset0, "charset");
        return this.writeString(s, 0, s.length(), charset0);
    }

    @Override  // okio.BufferedSink
    public BufferedSink writeString(String s, int v, int v1, Charset charset0) {
        return this.writeString(s, v, v1, charset0);
    }

    @Override  // okio.BufferedSink
    public BufferedSink writeString(String s, Charset charset0) {
        return this.writeString(s, charset0);
    }

    public final Buffer writeTo(OutputStream outputStream0) throws IOException {
        Intrinsics.checkNotNullParameter(outputStream0, "out");
        return Buffer.writeTo$default(this, outputStream0, 0L, 2, null);
    }

    public final Buffer writeTo(OutputStream outputStream0, long v) throws IOException {
        Intrinsics.checkNotNullParameter(outputStream0, "out");
        _UtilKt.checkOffsetAndCount(this.size, 0L, v);
        Segment segment0 = this.head;
        long v1 = v;
        while(v1 > 0L) {
            Intrinsics.checkNotNull(segment0);
            int v2 = (int)Math.min(v1, segment0.limit - segment0.pos);
            outputStream0.write(segment0.data, segment0.pos, v2);
            segment0.pos += v2;
            this.size -= (long)v2;
            v1 -= (long)v2;
            if(segment0.pos == segment0.limit) {
                Segment segment1 = segment0.pop();
                this.head = segment1;
                SegmentPool.recycle(segment0);
                segment0 = segment1;
            }
        }
        return this;
    }

    public static Buffer writeTo$default(Buffer buffer0, OutputStream outputStream0, long v, int v1, Object object0) throws IOException {
        if((v1 & 2) != 0) {
            v = buffer0.size;
        }
        return buffer0.writeTo(outputStream0, v);
    }

    public Buffer writeUtf8(String s) {
        Intrinsics.checkNotNullParameter(s, "string");
        return this.writeUtf8(s, 0, s.length());
    }

    public Buffer writeUtf8(String s, int v, int v1) {
        Intrinsics.checkNotNullParameter(s, "string");
        if(v < 0) {
            throw new IllegalArgumentException(("beginIndex < 0: " + v).toString());
        }
        if(v1 < v) {
            throw new IllegalArgumentException(("endIndex < beginIndex: " + v1 + " < " + v).toString());
        }
        if(v1 > s.length()) {
            throw new IllegalArgumentException(("endIndex > string.length: " + v1 + " > " + s.length()).toString());
        }
        while(v < v1) {
            int v2 = s.charAt(v);
            if(v2 < 0x80) {
                Segment segment0 = this.writableSegment$okio(1);
                byte[] arr_b = segment0.data;
                int v3 = segment0.limit - v;
                int v4 = Math.min(v1, 0x2000 - v3);
                int v5 = v + 1;
                arr_b[v + v3] = (byte)v2;
                while(true) {
                    v = v5;
                    if(v >= v4) {
                        break;
                    }
                    int v6 = s.charAt(v);
                    if(v6 >= 0x80) {
                        break;
                    }
                    v5 = v + 1;
                    arr_b[v + v3] = (byte)v6;
                }
                int v7 = v3 + v - segment0.limit;
                segment0.limit += v7;
                this.setSize$okio(this.size() + ((long)v7));
            }
            else {
                if(v2 < 0x800) {
                    Segment segment1 = this.writableSegment$okio(2);
                    segment1.data[segment1.limit] = (byte)(v2 >> 6 | 0xC0);
                    segment1.data[segment1.limit + 1] = (byte)(v2 & 0x3F | 0x80);
                    segment1.limit += 2;
                    this.setSize$okio(this.size() + 2L);
                }
                else if(v2 < 0xD800 || v2 > 0xDFFF) {
                    Segment segment3 = this.writableSegment$okio(3);
                    segment3.data[segment3.limit] = (byte)(v2 >> 12 | 0xE0);
                    segment3.data[segment3.limit + 1] = (byte)(v2 >> 6 & 0x3F | 0x80);
                    segment3.data[segment3.limit + 2] = (byte)(v2 & 0x3F | 0x80);
                    segment3.limit += 3;
                    this.setSize$okio(this.size() + 3L);
                }
                else {
                    int v8 = v + 1 >= v1 ? 0 : s.charAt(v + 1);
                    if(v2 > 0xDBFF || 0xDC00 > v8 || v8 >= 0xE000) {
                        this.writeByte(0x3F);
                        ++v;
                    }
                    else {
                        int v9 = ((v2 & 0x3FF) << 10 | v8 & 0x3FF) + 0x10000;
                        Segment segment2 = this.writableSegment$okio(4);
                        segment2.data[segment2.limit] = (byte)(v9 >> 18 | 0xF0);
                        segment2.data[segment2.limit + 1] = (byte)(v9 >> 12 & 0x3F | 0x80);
                        segment2.data[segment2.limit + 2] = (byte)(v9 >> 6 & 0x3F | 0x80);
                        segment2.data[segment2.limit + 3] = (byte)(v9 & 0x3F | 0x80);
                        segment2.limit += 4;
                        this.setSize$okio(this.size() + 4L);
                        v += 2;
                    }
                    continue;
                }
                ++v;
            }
        }
        return this;
    }

    @Override  // okio.BufferedSink
    public BufferedSink writeUtf8(String s) {
        return this.writeUtf8(s);
    }

    @Override  // okio.BufferedSink
    public BufferedSink writeUtf8(String s, int v, int v1) {
        return this.writeUtf8(s, v, v1);
    }

    public Buffer writeUtf8CodePoint(int v) {
        if(v < 0x80) {
            this.writeByte(v);
            return this;
        }
        if(v < 0x800) {
            Segment segment0 = this.writableSegment$okio(2);
            segment0.data[segment0.limit] = (byte)(v >> 6 | 0xC0);
            segment0.data[segment0.limit + 1] = (byte)(v & 0x3F | 0x80);
            segment0.limit += 2;
            this.setSize$okio(this.size() + 2L);
            return this;
        }
        if(0xD800 <= v && v < 0xE000) {
            this.writeByte(0x3F);
            return this;
        }
        if(v < 0x10000) {
            Segment segment1 = this.writableSegment$okio(3);
            segment1.data[segment1.limit] = (byte)(v >> 12 | 0xE0);
            segment1.data[segment1.limit + 1] = (byte)(v >> 6 & 0x3F | 0x80);
            segment1.data[segment1.limit + 2] = (byte)(v & 0x3F | 0x80);
            segment1.limit += 3;
            this.setSize$okio(this.size() + 3L);
            return this;
        }
        if(v > 0x10FFFF) {
            throw new IllegalArgumentException("Unexpected code point: 0x" + _UtilKt.toHexString(v));
        }
        Segment segment2 = this.writableSegment$okio(4);
        segment2.data[segment2.limit] = (byte)(v >> 18 | 0xF0);
        segment2.data[segment2.limit + 1] = (byte)(v >> 12 & 0x3F | 0x80);
        segment2.data[segment2.limit + 2] = (byte)(v >> 6 & 0x3F | 0x80);
        segment2.data[segment2.limit + 3] = (byte)(v & 0x3F | 0x80);
        segment2.limit += 4;
        this.setSize$okio(this.size() + 4L);
        return this;
    }

    @Override  // okio.BufferedSink
    public BufferedSink writeUtf8CodePoint(int v) {
        return this.writeUtf8CodePoint(v);
    }
}

