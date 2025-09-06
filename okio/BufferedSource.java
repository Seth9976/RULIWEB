package okio;

import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;

@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u00012\u00020\u0002J\b\u0010\u0003\u001A\u00020\u0004H\'J\b\u0010\u0007\u001A\u00020\bH&J\u0010\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\fH&J\u0018\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\nH&J \u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\n2\u0006\u0010\u000E\u001A\u00020\nH&J\u0010\u0010\t\u001A\u00020\n2\u0006\u0010\u000F\u001A\u00020\u0010H&J\u0018\u0010\t\u001A\u00020\n2\u0006\u0010\u000F\u001A\u00020\u00102\u0006\u0010\r\u001A\u00020\nH&J\u0010\u0010\u0011\u001A\u00020\n2\u0006\u0010\u0012\u001A\u00020\u0010H&J\u0018\u0010\u0011\u001A\u00020\n2\u0006\u0010\u0012\u001A\u00020\u00102\u0006\u0010\r\u001A\u00020\nH&J\b\u0010\u0013\u001A\u00020\u0014H&J\b\u0010\u0015\u001A\u00020\u0000H&J\u0018\u0010\u0016\u001A\u00020\b2\u0006\u0010\u0017\u001A\u00020\n2\u0006\u0010\u000F\u001A\u00020\u0010H&J(\u0010\u0016\u001A\u00020\b2\u0006\u0010\u0017\u001A\u00020\n2\u0006\u0010\u000F\u001A\u00020\u00102\u0006\u0010\u0018\u001A\u00020\u00192\u0006\u0010\u001A\u001A\u00020\u0019H&J\u0010\u0010\u001B\u001A\u00020\u00192\u0006\u0010\u001C\u001A\u00020\u001DH&J \u0010\u001B\u001A\u00020\u00192\u0006\u0010\u001C\u001A\u00020\u001D2\u0006\u0010\u0017\u001A\u00020\u00192\u0006\u0010\u001A\u001A\u00020\u0019H&J\u0010\u0010\u001E\u001A\u00020\n2\u0006\u0010\u001C\u001A\u00020\u001FH&J\b\u0010 \u001A\u00020\fH&J\b\u0010!\u001A\u00020\u001DH&J\u0010\u0010!\u001A\u00020\u001D2\u0006\u0010\u001A\u001A\u00020\nH&J\b\u0010\"\u001A\u00020\u0010H&J\u0010\u0010\"\u001A\u00020\u00102\u0006\u0010\u001A\u001A\u00020\nH&J\b\u0010#\u001A\u00020\nH&J\u0010\u0010$\u001A\u00020%2\u0006\u0010\u001C\u001A\u00020\u001DH&J\u0018\u0010$\u001A\u00020%2\u0006\u0010\u001C\u001A\u00020\u00042\u0006\u0010\u001A\u001A\u00020\nH&J\b\u0010&\u001A\u00020\nH&J\b\u0010\'\u001A\u00020\u0019H&J\b\u0010(\u001A\u00020\u0019H&J\b\u0010)\u001A\u00020\nH&J\b\u0010*\u001A\u00020\nH&J\b\u0010+\u001A\u00020,H&J\b\u0010-\u001A\u00020,H&J\u0010\u0010.\u001A\u00020/2\u0006\u00100\u001A\u000201H&J\u0018\u0010.\u001A\u00020/2\u0006\u0010\u001A\u001A\u00020\n2\u0006\u00100\u001A\u000201H&J\b\u00102\u001A\u00020/H&J\u0010\u00102\u001A\u00020/2\u0006\u0010\u001A\u001A\u00020\nH&J\b\u00103\u001A\u00020\u0019H&J\n\u00104\u001A\u0004\u0018\u00010/H&J\b\u00105\u001A\u00020/H&J\u0010\u00105\u001A\u00020/2\u0006\u00106\u001A\u00020\nH&J\u0010\u00107\u001A\u00020\b2\u0006\u0010\u001A\u001A\u00020\nH&J\u0010\u00108\u001A\u00020%2\u0006\u0010\u001A\u001A\u00020\nH&J\u0010\u00109\u001A\u00020\u00192\u0006\u0010:\u001A\u00020;H&J\u0010\u0010<\u001A\u00020%2\u0006\u0010\u001A\u001A\u00020\nH&R\u0012\u0010\u0003\u001A\u00020\u0004X\u00A6\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0005\u0010\u0006\u00F8\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001\u00A8\u0006=\u00C0\u0006\u0001"}, d2 = {"Lokio/BufferedSource;", "Lokio/Source;", "Ljava/nio/channels/ReadableByteChannel;", "buffer", "Lokio/Buffer;", "getBuffer", "()Lokio/Buffer;", "exhausted", "", "indexOf", "", "b", "", "fromIndex", "toIndex", "bytes", "Lokio/ByteString;", "indexOfElement", "targetBytes", "inputStream", "Ljava/io/InputStream;", "peek", "rangeEquals", "offset", "bytesOffset", "", "byteCount", "read", "sink", "", "readAll", "Lokio/Sink;", "readByte", "readByteArray", "readByteString", "readDecimalLong", "readFully", "", "readHexadecimalUnsignedLong", "readInt", "readIntLe", "readLong", "readLongLe", "readShort", "", "readShortLe", "readString", "", "charset", "Ljava/nio/charset/Charset;", "readUtf8", "readUtf8CodePoint", "readUtf8Line", "readUtf8LineStrict", "limit", "request", "require", "select", "options", "Lokio/Options;", "skip", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public interface BufferedSource extends ReadableByteChannel, Source {
    @Deprecated(level = DeprecationLevel.WARNING, message = "moved to val: use getBuffer() instead", replaceWith = @ReplaceWith(expression = "buffer", imports = {}))
    Buffer buffer();

    boolean exhausted() throws IOException;

    Buffer getBuffer();

    long indexOf(byte arg1) throws IOException;

    long indexOf(byte arg1, long arg2) throws IOException;

    long indexOf(byte arg1, long arg2, long arg3) throws IOException;

    long indexOf(ByteString arg1) throws IOException;

    long indexOf(ByteString arg1, long arg2) throws IOException;

    long indexOfElement(ByteString arg1) throws IOException;

    long indexOfElement(ByteString arg1, long arg2) throws IOException;

    InputStream inputStream();

    BufferedSource peek();

    boolean rangeEquals(long arg1, ByteString arg2) throws IOException;

    boolean rangeEquals(long arg1, ByteString arg2, int arg3, int arg4) throws IOException;

    int read(byte[] arg1) throws IOException;

    int read(byte[] arg1, int arg2, int arg3) throws IOException;

    long readAll(Sink arg1) throws IOException;

    byte readByte() throws IOException;

    byte[] readByteArray() throws IOException;

    byte[] readByteArray(long arg1) throws IOException;

    ByteString readByteString() throws IOException;

    ByteString readByteString(long arg1) throws IOException;

    long readDecimalLong() throws IOException;

    void readFully(Buffer arg1, long arg2) throws IOException;

    void readFully(byte[] arg1) throws IOException;

    long readHexadecimalUnsignedLong() throws IOException;

    int readInt() throws IOException;

    int readIntLe() throws IOException;

    long readLong() throws IOException;

    long readLongLe() throws IOException;

    short readShort() throws IOException;

    short readShortLe() throws IOException;

    String readString(long arg1, Charset arg2) throws IOException;

    String readString(Charset arg1) throws IOException;

    String readUtf8() throws IOException;

    String readUtf8(long arg1) throws IOException;

    int readUtf8CodePoint() throws IOException;

    String readUtf8Line() throws IOException;

    String readUtf8LineStrict() throws IOException;

    String readUtf8LineStrict(long arg1) throws IOException;

    boolean request(long arg1) throws IOException;

    void require(long arg1) throws IOException;

    int select(Options arg1) throws IOException;

    void skip(long arg1) throws IOException;
}

