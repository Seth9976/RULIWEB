package okio;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import okio.internal._ByteStringKt;

@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000F\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000E\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 ]2\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002:\u0001]B\u000F\b\u0000\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u00A2\u0006\u0002\u0010\u0005J\b\u0010\u0015\u001A\u00020\u0016H\u0016J\b\u0010\u0017\u001A\u00020\u0010H\u0016J\b\u0010\u0018\u001A\u00020\u0010H\u0016J\u0011\u0010\u0019\u001A\u00020\t2\u0006\u0010\u001A\u001A\u00020\u0000H\u0096\u0002J,\u0010\u001B\u001A\u00020\u001C2\b\b\u0002\u0010\u001D\u001A\u00020\t2\u0006\u0010\u001E\u001A\u00020\u00042\b\b\u0002\u0010\u001F\u001A\u00020\t2\u0006\u0010 \u001A\u00020\tH\u0016J\u0015\u0010!\u001A\u00020\u00002\u0006\u0010\"\u001A\u00020\u0010H\u0010\u00A2\u0006\u0002\b#J\u000E\u0010$\u001A\u00020%2\u0006\u0010&\u001A\u00020\u0004J\u000E\u0010$\u001A\u00020%2\u0006\u0010&\u001A\u00020\u0000J\u0013\u0010\'\u001A\u00020%2\b\u0010\u001A\u001A\u0004\u0018\u00010(H\u0096\u0002J\u0016\u0010)\u001A\u00020*2\u0006\u0010+\u001A\u00020\tH\u0087\u0002\u00A2\u0006\u0002\b,J\u0015\u0010,\u001A\u00020*2\u0006\u0010+\u001A\u00020\tH\u0007\u00A2\u0006\u0002\b-J\r\u0010.\u001A\u00020\tH\u0010\u00A2\u0006\u0002\b/J\b\u0010\b\u001A\u00020\tH\u0016J\b\u00100\u001A\u00020\u0010H\u0016J\u001D\u00101\u001A\u00020\u00002\u0006\u0010\"\u001A\u00020\u00102\u0006\u00102\u001A\u00020\u0000H\u0010\u00A2\u0006\u0002\b3J\u0010\u00104\u001A\u00020\u00002\u0006\u00102\u001A\u00020\u0000H\u0016J\u0010\u00105\u001A\u00020\u00002\u0006\u00102\u001A\u00020\u0000H\u0016J\u0010\u00106\u001A\u00020\u00002\u0006\u00102\u001A\u00020\u0000H\u0016J\u001A\u00107\u001A\u00020\t2\u0006\u0010\u001A\u001A\u00020\u00042\b\b\u0002\u00108\u001A\u00020\tH\u0017J\u001A\u00107\u001A\u00020\t2\u0006\u0010\u001A\u001A\u00020\u00002\b\b\u0002\u00108\u001A\u00020\tH\u0007J\r\u00109\u001A\u00020\u0004H\u0010\u00A2\u0006\u0002\b:J\u0015\u0010;\u001A\u00020*2\u0006\u0010<\u001A\u00020\tH\u0010\u00A2\u0006\u0002\b=J\u001A\u0010>\u001A\u00020\t2\u0006\u0010\u001A\u001A\u00020\u00042\b\b\u0002\u00108\u001A\u00020\tH\u0017J\u001A\u0010>\u001A\u00020\t2\u0006\u0010\u001A\u001A\u00020\u00002\b\b\u0002\u00108\u001A\u00020\tH\u0007J\u0006\u0010?\u001A\u00020\u0000J(\u0010@\u001A\u00020%2\u0006\u0010\u001D\u001A\u00020\t2\u0006\u0010\u001A\u001A\u00020\u00042\u0006\u0010A\u001A\u00020\t2\u0006\u0010 \u001A\u00020\tH\u0016J(\u0010@\u001A\u00020%2\u0006\u0010\u001D\u001A\u00020\t2\u0006\u0010\u001A\u001A\u00020\u00002\u0006\u0010A\u001A\u00020\t2\u0006\u0010 \u001A\u00020\tH\u0016J\u0010\u0010B\u001A\u00020\u001C2\u0006\u0010C\u001A\u00020DH\u0002J\u0006\u0010E\u001A\u00020\u0000J\u0006\u0010F\u001A\u00020\u0000J\u0006\u0010G\u001A\u00020\u0000J\r\u0010\u000E\u001A\u00020\tH\u0007\u00A2\u0006\u0002\bHJ\u000E\u0010I\u001A\u00020%2\u0006\u0010J\u001A\u00020\u0004J\u000E\u0010I\u001A\u00020%2\u0006\u0010J\u001A\u00020\u0000J\u0010\u0010K\u001A\u00020\u00102\u0006\u0010L\u001A\u00020MH\u0016J\u001C\u0010N\u001A\u00020\u00002\b\b\u0002\u0010O\u001A\u00020\t2\b\b\u0002\u0010P\u001A\u00020\tH\u0017J\b\u0010Q\u001A\u00020\u0000H\u0016J\b\u0010R\u001A\u00020\u0000H\u0016J\b\u0010S\u001A\u00020\u0004H\u0016J\b\u0010T\u001A\u00020\u0010H\u0016J\b\u0010\u000F\u001A\u00020\u0010H\u0016J\u0010\u0010U\u001A\u00020\u001C2\u0006\u0010V\u001A\u00020WH\u0016J%\u0010U\u001A\u00020\u001C2\u0006\u0010X\u001A\u00020Y2\u0006\u0010\u001D\u001A\u00020\t2\u0006\u0010 \u001A\u00020\tH\u0010\u00A2\u0006\u0002\bZJ\u0010\u0010[\u001A\u00020\u001C2\u0006\u0010V\u001A\u00020\\H\u0002R\u0014\u0010\u0003\u001A\u00020\u0004X\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0006\u0010\u0007R\u001A\u0010\b\u001A\u00020\tX\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\n\u0010\u000B\"\u0004\b\f\u0010\rR\u0011\u0010\u000E\u001A\u00020\t8G\u00A2\u0006\u0006\u001A\u0004\b\u000E\u0010\u000BR\u001C\u0010\u000F\u001A\u0004\u0018\u00010\u0010X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014\u00A8\u0006^"}, d2 = {"Lokio/ByteString;", "Ljava/io/Serializable;", "", "data", "", "([B)V", "getData$okio", "()[B", "hashCode", "", "getHashCode$okio", "()I", "setHashCode$okio", "(I)V", "size", "utf8", "", "getUtf8$okio", "()Ljava/lang/String;", "setUtf8$okio", "(Ljava/lang/String;)V", "asByteBuffer", "Ljava/nio/ByteBuffer;", "base64", "base64Url", "compareTo", "other", "copyInto", "", "offset", "target", "targetOffset", "byteCount", "digest", "algorithm", "digest$okio", "endsWith", "", "suffix", "equals", "", "get", "", "index", "getByte", "-deprecated_getByte", "getSize", "getSize$okio", "hex", "hmac", "key", "hmac$okio", "hmacSha1", "hmacSha256", "hmacSha512", "indexOf", "fromIndex", "internalArray", "internalArray$okio", "internalGet", "pos", "internalGet$okio", "lastIndexOf", "md5", "rangeEquals", "otherOffset", "readObject", "in", "Ljava/io/ObjectInputStream;", "sha1", "sha256", "sha512", "-deprecated_size", "startsWith", "prefix", "string", "charset", "Ljava/nio/charset/Charset;", "substring", "beginIndex", "endIndex", "toAsciiLowercase", "toAsciiUppercase", "toByteArray", "toString", "write", "out", "Ljava/io/OutputStream;", "buffer", "Lokio/Buffer;", "write$okio", "writeObject", "Ljava/io/ObjectOutputStream;", "Companion", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public class ByteString implements Serializable, Comparable {
    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00A2\u0006\u0002\u0010\u0002J\u0017\u0010\u0007\u001A\u0004\u0018\u00010\u00042\u0006\u0010\b\u001A\u00020\tH\u0007\u00A2\u0006\u0002\b\nJ\u0015\u0010\u000B\u001A\u00020\u00042\u0006\u0010\b\u001A\u00020\tH\u0007\u00A2\u0006\u0002\b\fJ\u001D\u0010\r\u001A\u00020\u00042\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\u000E\u001A\u00020\u000FH\u0007\u00A2\u0006\u0002\b\u0010J\u0015\u0010\u0011\u001A\u00020\u00042\u0006\u0010\b\u001A\u00020\tH\u0007\u00A2\u0006\u0002\b\u0012J\u0015\u0010\u0013\u001A\u00020\u00042\u0006\u0010\u0014\u001A\u00020\u0015H\u0007\u00A2\u0006\u0002\b\u0016J\u0014\u0010\u0013\u001A\u00020\u00042\n\u0010\u0017\u001A\u00020\u0018\"\u00020\u0019H\u0007J%\u0010\u0013\u001A\u00020\u00042\u0006\u0010\u001A\u001A\u00020\u00182\u0006\u0010\u001B\u001A\u00020\u001C2\u0006\u0010\u001D\u001A\u00020\u001CH\u0007\u00A2\u0006\u0002\b\u0016J\u001D\u0010\u001E\u001A\u00020\u00042\u0006\u0010\u001F\u001A\u00020 2\u0006\u0010\u001D\u001A\u00020\u001CH\u0007\u00A2\u0006\u0002\b!J\u000E\u0010\u0007\u001A\u0004\u0018\u00010\u0004*\u00020\tH\u0007J\f\u0010\u000B\u001A\u00020\u0004*\u00020\tH\u0007J\u001B\u0010\"\u001A\u00020\u0004*\u00020\t2\b\b\u0002\u0010\u000E\u001A\u00020\u000FH\u0007\u00A2\u0006\u0002\b\rJ\f\u0010\u0011\u001A\u00020\u0004*\u00020\tH\u0007J\u0019\u0010#\u001A\u00020\u0004*\u00020 2\u0006\u0010\u001D\u001A\u00020\u001CH\u0007\u00A2\u0006\u0002\b\u001EJ\u0011\u0010$\u001A\u00020\u0004*\u00020\u0015H\u0007\u00A2\u0006\u0002\b\u0013J%\u0010$\u001A\u00020\u0004*\u00020\u00182\b\b\u0002\u0010\u001B\u001A\u00020\u001C2\b\b\u0002\u0010\u001D\u001A\u00020\u001CH\u0007\u00A2\u0006\u0002\b\u0013R\u0010\u0010\u0003\u001A\u00020\u00048\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082T\u00A2\u0006\u0002\n\u0000\u00A8\u0006%"}, d2 = {"Lokio/ByteString$Companion;", "", "()V", "EMPTY", "Lokio/ByteString;", "serialVersionUID", "", "decodeBase64", "string", "", "-deprecated_decodeBase64", "decodeHex", "-deprecated_decodeHex", "encodeString", "charset", "Ljava/nio/charset/Charset;", "-deprecated_encodeString", "encodeUtf8", "-deprecated_encodeUtf8", "of", "buffer", "Ljava/nio/ByteBuffer;", "-deprecated_of", "data", "", "", "array", "offset", "", "byteCount", "read", "inputstream", "Ljava/io/InputStream;", "-deprecated_read", "encode", "readByteString", "toByteString", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public static final class Companion {
        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "string.decodeBase64()", imports = {"okio.ByteString.Companion.decodeBase64"}))
        public final ByteString -deprecated_decodeBase64(String s) {
            Intrinsics.checkNotNullParameter(s, "string");
            return this.decodeBase64(s);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "string.decodeHex()", imports = {"okio.ByteString.Companion.decodeHex"}))
        public final ByteString -deprecated_decodeHex(String s) {
            Intrinsics.checkNotNullParameter(s, "string");
            return this.decodeHex(s);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "string.encode(charset)", imports = {"okio.ByteString.Companion.encode"}))
        public final ByteString -deprecated_encodeString(String s, Charset charset0) {
            Intrinsics.checkNotNullParameter(s, "string");
            Intrinsics.checkNotNullParameter(charset0, "charset");
            return this.encodeString(s, charset0);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "string.encodeUtf8()", imports = {"okio.ByteString.Companion.encodeUtf8"}))
        public final ByteString -deprecated_encodeUtf8(String s) {
            Intrinsics.checkNotNullParameter(s, "string");
            return this.encodeUtf8(s);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "buffer.toByteString()", imports = {"okio.ByteString.Companion.toByteString"}))
        public final ByteString -deprecated_of(ByteBuffer byteBuffer0) {
            Intrinsics.checkNotNullParameter(byteBuffer0, "buffer");
            return this.of(byteBuffer0);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "array.toByteString(offset, byteCount)", imports = {"okio.ByteString.Companion.toByteString"}))
        public final ByteString -deprecated_of(byte[] arr_b, int v, int v1) {
            Intrinsics.checkNotNullParameter(arr_b, "array");
            return this.of(arr_b, v, v1);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "moved to extension function", replaceWith = @ReplaceWith(expression = "inputstream.readByteString(byteCount)", imports = {"okio.ByteString.Companion.readByteString"}))
        public final ByteString -deprecated_read(InputStream inputStream0, int v) {
            Intrinsics.checkNotNullParameter(inputStream0, "inputstream");
            return this.read(inputStream0, v);
        }

        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        @JvmStatic
        public final ByteString decodeBase64(String s) {
            Intrinsics.checkNotNullParameter(s, "<this>");
            byte[] arr_b = _Base64Kt.decodeBase64ToArray(s);
            return arr_b == null ? null : new ByteString(arr_b);
        }

        @JvmStatic
        public final ByteString decodeHex(String s) {
            Intrinsics.checkNotNullParameter(s, "<this>");
            if(s.length() % 2 != 0) {
                throw new IllegalArgumentException(("Unexpected hex string: " + s).toString());
            }
            int v = s.length();
            byte[] arr_b = new byte[v / 2];
            for(int v1 = 0; v1 < v / 2; ++v1) {
                arr_b[v1] = (byte)((_ByteStringKt.access$decodeHexDigit(s.charAt(v1 * 2)) << 4) + _ByteStringKt.access$decodeHexDigit(s.charAt(v1 * 2 + 1)));
            }
            return new ByteString(arr_b);
        }

        @JvmStatic
        public final ByteString encodeString(String s, Charset charset0) {
            Intrinsics.checkNotNullParameter(s, "<this>");
            Intrinsics.checkNotNullParameter(charset0, "charset");
            byte[] arr_b = s.getBytes(charset0);
            Intrinsics.checkNotNullExpressionValue(arr_b, "this as java.lang.String).getBytes(charset)");
            return new ByteString(arr_b);
        }

        public static ByteString encodeString$default(Companion byteString$Companion0, String s, Charset charset0, int v, Object object0) {
            if((v & 1) != 0) {
                charset0 = Charsets.UTF_8;
            }
            return byteString$Companion0.encodeString(s, charset0);
        }

        @JvmStatic
        public final ByteString encodeUtf8(String s) {
            Intrinsics.checkNotNullParameter(s, "<this>");
            ByteString byteString0 = new ByteString(_JvmPlatformKt.asUtf8ToByteArray(s));
            byteString0.setUtf8$okio(s);
            return byteString0;
        }

        @JvmStatic
        public final ByteString of(ByteBuffer byteBuffer0) {
            Intrinsics.checkNotNullParameter(byteBuffer0, "<this>");
            byte[] arr_b = new byte[byteBuffer0.remaining()];
            byteBuffer0.get(arr_b);
            return new ByteString(arr_b);
        }

        @JvmStatic
        public final ByteString of(byte[] arr_b) {
            Intrinsics.checkNotNullParameter(arr_b, "data");
            byte[] arr_b1 = Arrays.copyOf(arr_b, arr_b.length);
            Intrinsics.checkNotNullExpressionValue(arr_b1, "copyOf(this, size)");
            return new ByteString(arr_b1);
        }

        @JvmStatic
        public final ByteString of(byte[] arr_b, int v, int v1) {
            Intrinsics.checkNotNullParameter(arr_b, "<this>");
            int v2 = _UtilKt.resolveDefaultParameter(arr_b, v1);
            _UtilKt.checkOffsetAndCount(arr_b.length, v, v2);
            return new ByteString(ArraysKt.copyOfRange(arr_b, v, v2 + v));
        }

        public static ByteString of$default(Companion byteString$Companion0, byte[] arr_b, int v, int v1, int v2, Object object0) {
            if((v2 & 1) != 0) {
                v = 0;
            }
            if((v2 & 2) != 0) {
                v1 = 0xB669FD2E;
            }
            return byteString$Companion0.of(arr_b, v, v1);
        }

        @JvmStatic
        public final ByteString read(InputStream inputStream0, int v) throws IOException {
            Intrinsics.checkNotNullParameter(inputStream0, "<this>");
            if(v < 0) {
                throw new IllegalArgumentException(("byteCount < 0: " + v).toString());
            }
            byte[] arr_b = new byte[v];
            for(int v1 = 0; v1 < v; v1 += v2) {
                int v2 = inputStream0.read(arr_b, v1, v - v1);
                if(v2 == -1) {
                    throw new EOFException();
                }
            }
            return new ByteString(arr_b);
        }
    }

    public static final Companion Companion = null;
    public static final ByteString EMPTY = null;
    private final byte[] data;
    private transient int hashCode;
    private static final long serialVersionUID = 1L;
    private transient String utf8;

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to operator function", replaceWith = @ReplaceWith(expression = "this[index]", imports = {}))
    public final byte -deprecated_getByte(int v) {
        return this.getByte(v);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "size", imports = {}))
    public final int -deprecated_size() {
        return this.size();
    }

    static {
        ByteString.Companion = new Companion(null);
        ByteString.EMPTY = new ByteString(new byte[0]);
    }

    public ByteString(byte[] arr_b) {
        Intrinsics.checkNotNullParameter(arr_b, "data");
        super();
        this.data = arr_b;
    }

    public ByteBuffer asByteBuffer() {
        ByteBuffer byteBuffer0 = ByteBuffer.wrap(this.data).asReadOnlyBuffer();
        Intrinsics.checkNotNullExpressionValue(byteBuffer0, "wrap(data).asReadOnlyBuffer()");
        return byteBuffer0;
    }

    public String base64() {
        return _Base64Kt.encodeBase64$default(this.getData$okio(), null, 1, null);
    }

    // 去混淆评级： 低(20)
    public String base64Url() {
        return _Base64Kt.encodeBase64(this.getData$okio(), new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 0x4F, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 0x6F, 0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 120, 0x79, 0x7A, 0x30, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 0x5F});
    }

    @Override
    public int compareTo(Object object0) {
        return this.compareTo(((ByteString)object0));
    }

    public int compareTo(ByteString byteString0) {
        Intrinsics.checkNotNullParameter(byteString0, "other");
        int v = this.size();
        int v1 = byteString0.size();
        int v2 = Math.min(v, v1);
        int v3 = 0;
        while(v3 < v2) {
            int v4 = this.getByte(v3) & 0xFF;
            int v5 = byteString0.getByte(v3) & 0xFF;
            if(v4 == v5) {
                ++v3;
                continue;
            }
            return v4 >= v5 ? 1 : -1;
        }
        if(v == v1) {
            return 0;
        }
        return v >= v1 ? 1 : -1;
    }

    public void copyInto(int v, byte[] arr_b, int v1, int v2) {
        Intrinsics.checkNotNullParameter(arr_b, "target");
        ArraysKt.copyInto(this.getData$okio(), arr_b, v1, v, v2 + v);
    }

    public static void copyInto$default(ByteString byteString0, int v, byte[] arr_b, int v1, int v2, int v3, Object object0) {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: copyInto");
        }
        if((v3 & 1) != 0) {
            v = 0;
        }
        if((v3 & 4) != 0) {
            v1 = 0;
        }
        byteString0.copyInto(v, arr_b, v1, v2);
    }

    @JvmStatic
    public static final ByteString decodeBase64(String s) {
        return ByteString.Companion.decodeBase64(s);
    }

    @JvmStatic
    public static final ByteString decodeHex(String s) {
        return ByteString.Companion.decodeHex(s);
    }

    public ByteString digest$okio(String s) {
        Intrinsics.checkNotNullParameter(s, "algorithm");
        MessageDigest messageDigest0 = MessageDigest.getInstance(s);
        messageDigest0.update(this.data, 0, this.size());
        byte[] arr_b = messageDigest0.digest();
        Intrinsics.checkNotNullExpressionValue(arr_b, "digestBytes");
        return new ByteString(arr_b);
    }

    @JvmStatic
    public static final ByteString encodeString(String s, Charset charset0) {
        return ByteString.Companion.encodeString(s, charset0);
    }

    @JvmStatic
    public static final ByteString encodeUtf8(String s) {
        return ByteString.Companion.encodeUtf8(s);
    }

    public final boolean endsWith(ByteString byteString0) {
        Intrinsics.checkNotNullParameter(byteString0, "suffix");
        return this.rangeEquals(this.size() - byteString0.size(), byteString0, 0, byteString0.size());
    }

    public final boolean endsWith(byte[] arr_b) {
        Intrinsics.checkNotNullParameter(arr_b, "suffix");
        return this.rangeEquals(this.size() - arr_b.length, arr_b, 0, arr_b.length);
    }

    // 去混淆评级： 低(30)
    @Override
    public boolean equals(Object object0) {
        return object0 == this ? true : object0 instanceof ByteString && ((ByteString)object0).size() == this.getData$okio().length && ((ByteString)object0).rangeEquals(0, this.getData$okio(), 0, this.getData$okio().length);
    }

    public final byte getByte(int v) {
        return this.internalGet$okio(v);
    }

    public final byte[] getData$okio() {
        return this.data;
    }

    public final int getHashCode$okio() {
        return this.hashCode;
    }

    public int getSize$okio() {
        return this.getData$okio().length;
    }

    public final String getUtf8$okio() {
        return this.utf8;
    }

    @Override
    public int hashCode() {
        int v = this.getHashCode$okio();
        if(v != 0) {
            return v;
        }
        int v1 = Arrays.hashCode(this.getData$okio());
        this.setHashCode$okio(v1);
        return v1;
    }

    public String hex() {
        char[] arr_c = new char[this.getData$okio().length * 2];
        byte[] arr_b = this.getData$okio();
        int v1 = 0;
        for(int v = 0; v < arr_b.length; ++v) {
            int v2 = arr_b[v];
            int v3 = v1 + 1;
            arr_c[v1] = _ByteStringKt.getHEX_DIGIT_CHARS()[v2 >> 4 & 15];
            v1 += 2;
            arr_c[v3] = _ByteStringKt.getHEX_DIGIT_CHARS()[v2 & 15];
        }
        return StringsKt.concatToString(arr_c);
    }

    public ByteString hmac$okio(String s, ByteString byteString0) {
        Intrinsics.checkNotNullParameter(s, "algorithm");
        Intrinsics.checkNotNullParameter(byteString0, "key");
        try {
            Mac mac0 = Mac.getInstance(s);
            mac0.init(new SecretKeySpec(byteString0.toByteArray(), s));
            byte[] arr_b = mac0.doFinal(this.data);
            Intrinsics.checkNotNullExpressionValue(arr_b, "mac.doFinal(data)");
            return new ByteString(arr_b);
        }
        catch(InvalidKeyException invalidKeyException0) {
            throw new IllegalArgumentException(invalidKeyException0);
        }
    }

    public ByteString hmacSha1(ByteString byteString0) {
        Intrinsics.checkNotNullParameter(byteString0, "key");
        return this.hmac$okio("HmacSHA1", byteString0);
    }

    public ByteString hmacSha256(ByteString byteString0) {
        Intrinsics.checkNotNullParameter(byteString0, "key");
        return this.hmac$okio("HmacSHA256", byteString0);
    }

    public ByteString hmacSha512(ByteString byteString0) {
        Intrinsics.checkNotNullParameter(byteString0, "key");
        return this.hmac$okio("HmacSHA512", byteString0);
    }

    public final int indexOf(ByteString byteString0) {
        Intrinsics.checkNotNullParameter(byteString0, "other");
        return ByteString.indexOf$default(this, byteString0, 0, 2, null);
    }

    public final int indexOf(ByteString byteString0, int v) {
        Intrinsics.checkNotNullParameter(byteString0, "other");
        return this.indexOf(byteString0.internalArray$okio(), v);
    }

    public final int indexOf(byte[] arr_b) {
        Intrinsics.checkNotNullParameter(arr_b, "other");
        return ByteString.indexOf$default(this, arr_b, 0, 2, null);
    }

    public int indexOf(byte[] arr_b, int v) {
        Intrinsics.checkNotNullParameter(arr_b, "other");
        int v1 = this.getData$okio().length - arr_b.length;
        int v2 = Math.max(v, 0);
        if(v2 <= v1) {
            while(true) {
                if(_UtilKt.arrayRangeEquals(this.getData$okio(), v2, arr_b, 0, arr_b.length)) {
                    return v2;
                }
                if(v2 == v1) {
                    break;
                }
                ++v2;
            }
        }
        return -1;
    }

    public static int indexOf$default(ByteString byteString0, ByteString byteString1, int v, int v1, Object object0) {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: indexOf");
        }
        if((v1 & 2) != 0) {
            v = 0;
        }
        return byteString0.indexOf(byteString1, v);
    }

    public static int indexOf$default(ByteString byteString0, byte[] arr_b, int v, int v1, Object object0) {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: indexOf");
        }
        if((v1 & 2) != 0) {
            v = 0;
        }
        return byteString0.indexOf(arr_b, v);
    }

    public byte[] internalArray$okio() {
        return this.getData$okio();
    }

    public byte internalGet$okio(int v) {
        return this.getData$okio()[v];
    }

    public final int lastIndexOf(ByteString byteString0) {
        Intrinsics.checkNotNullParameter(byteString0, "other");
        return ByteString.lastIndexOf$default(this, byteString0, 0, 2, null);
    }

    public final int lastIndexOf(ByteString byteString0, int v) {
        Intrinsics.checkNotNullParameter(byteString0, "other");
        return this.lastIndexOf(byteString0.internalArray$okio(), v);
    }

    public final int lastIndexOf(byte[] arr_b) {
        Intrinsics.checkNotNullParameter(arr_b, "other");
        return ByteString.lastIndexOf$default(this, arr_b, 0, 2, null);
    }

    public int lastIndexOf(byte[] arr_b, int v) {
        Intrinsics.checkNotNullParameter(arr_b, "other");
        for(int v1 = Math.min(_UtilKt.resolveDefaultParameter(this, v), this.getData$okio().length - arr_b.length); -1 < v1; --v1) {
            if(_UtilKt.arrayRangeEquals(this.getData$okio(), v1, arr_b, 0, arr_b.length)) {
                return v1;
            }
        }
        return -1;
    }

    public static int lastIndexOf$default(ByteString byteString0, ByteString byteString1, int v, int v1, Object object0) {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: lastIndexOf");
        }
        if((v1 & 2) != 0) {
            v = 0xB669FD2E;
        }
        return byteString0.lastIndexOf(byteString1, v);
    }

    public static int lastIndexOf$default(ByteString byteString0, byte[] arr_b, int v, int v1, Object object0) {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: lastIndexOf");
        }
        if((v1 & 2) != 0) {
            v = 0xB669FD2E;
        }
        return byteString0.lastIndexOf(arr_b, v);
    }

    public final ByteString md5() {
        return this.digest$okio("MD5");
    }

    @JvmStatic
    public static final ByteString of(ByteBuffer byteBuffer0) {
        return ByteString.Companion.of(byteBuffer0);
    }

    @JvmStatic
    public static final ByteString of(byte[] arr_b) {
        return ByteString.Companion.of(arr_b);
    }

    @JvmStatic
    public static final ByteString of(byte[] arr_b, int v, int v1) {
        return ByteString.Companion.of(arr_b, v, v1);
    }

    public boolean rangeEquals(int v, ByteString byteString0, int v1, int v2) {
        Intrinsics.checkNotNullParameter(byteString0, "other");
        return byteString0.rangeEquals(v1, this.getData$okio(), v, v2);
    }

    public boolean rangeEquals(int v, byte[] arr_b, int v1, int v2) {
        Intrinsics.checkNotNullParameter(arr_b, "other");
        return v >= 0 && v <= this.getData$okio().length - v2 && v1 >= 0 && v1 <= arr_b.length - v2 && _UtilKt.arrayRangeEquals(this.getData$okio(), v, arr_b, v1, v2);
    }

    @JvmStatic
    public static final ByteString read(InputStream inputStream0, int v) throws IOException {
        return ByteString.Companion.read(inputStream0, v);
    }

    private final void readObject(ObjectInputStream objectInputStream0) throws IOException {
        int v = objectInputStream0.readInt();
        ByteString byteString0 = ByteString.Companion.read(objectInputStream0, v);
        ByteString.class.getDeclaredField("data").setAccessible(true);
        this.data = byteString0.data;
    }

    public final void setHashCode$okio(int v) {
        this.hashCode = v;
    }

    public final void setUtf8$okio(String s) {
        this.utf8 = s;
    }

    public final ByteString sha1() {
        return this.digest$okio("SHA-1");
    }

    public final ByteString sha256() {
        return this.digest$okio("SHA-256");
    }

    public final ByteString sha512() {
        return this.digest$okio("SHA-512");
    }

    public final int size() {
        return this.getSize$okio();
    }

    public final boolean startsWith(ByteString byteString0) {
        Intrinsics.checkNotNullParameter(byteString0, "prefix");
        return this.rangeEquals(0, byteString0, 0, byteString0.size());
    }

    public final boolean startsWith(byte[] arr_b) {
        Intrinsics.checkNotNullParameter(arr_b, "prefix");
        return this.rangeEquals(0, arr_b, 0, arr_b.length);
    }

    public String string(Charset charset0) {
        Intrinsics.checkNotNullParameter(charset0, "charset");
        return new String(this.data, charset0);
    }

    public final ByteString substring() {
        return ByteString.substring$default(this, 0, 0, 3, null);
    }

    public final ByteString substring(int v) {
        return ByteString.substring$default(this, v, 0, 2, null);
    }

    public ByteString substring(int v, int v1) {
        int v2 = _UtilKt.resolveDefaultParameter(this, v1);
        if(v < 0) {
            throw new IllegalArgumentException("beginIndex < 0");
        }
        if(v2 > this.getData$okio().length) {
            throw new IllegalArgumentException(("endIndex > length(" + this.getData$okio().length + ')').toString());
        }
        if(v2 - v < 0) {
            throw new IllegalArgumentException("endIndex < beginIndex");
        }
        return v != 0 || v2 != this.getData$okio().length ? new ByteString(ArraysKt.copyOfRange(this.getData$okio(), v, v2)) : this;
    }

    public static ByteString substring$default(ByteString byteString0, int v, int v1, int v2, Object object0) {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: substring");
        }
        if((v2 & 1) != 0) {
            v = 0;
        }
        if((v2 & 2) != 0) {
            v1 = 0xB669FD2E;
        }
        return byteString0.substring(v, v1);
    }

    public ByteString toAsciiLowercase() {
        for(int v = 0; v < this.getData$okio().length; ++v) {
            int v1 = this.getData$okio()[v];
            if(v1 >= 65 && v1 <= 90) {
                byte[] arr_b = this.getData$okio();
                byte[] arr_b1 = Arrays.copyOf(arr_b, arr_b.length);
                Intrinsics.checkNotNullExpressionValue(arr_b1, "copyOf(this, size)");
                int v2 = v + 1;
                arr_b1[v] = (byte)(v1 + 0x20);
                while(v2 < arr_b1.length) {
                    int v3 = arr_b1[v2];
                    if(v3 >= 65 && v3 <= 90) {
                        arr_b1[v2] = (byte)(v3 + 0x20);
                    }
                    ++v2;
                }
                return new ByteString(arr_b1);
            }
        }
        return this;
    }

    public ByteString toAsciiUppercase() {
        for(int v = 0; v < this.getData$okio().length; ++v) {
            int v1 = this.getData$okio()[v];
            if(v1 >= 97 && v1 <= 0x7A) {
                byte[] arr_b = this.getData$okio();
                byte[] arr_b1 = Arrays.copyOf(arr_b, arr_b.length);
                Intrinsics.checkNotNullExpressionValue(arr_b1, "copyOf(this, size)");
                int v2 = v + 1;
                arr_b1[v] = (byte)(v1 - 0x20);
                while(v2 < arr_b1.length) {
                    int v3 = arr_b1[v2];
                    if(v3 >= 97 && v3 <= 0x7A) {
                        arr_b1[v2] = (byte)(v3 - 0x20);
                    }
                    ++v2;
                }
                return new ByteString(arr_b1);
            }
        }
        return this;
    }

    public byte[] toByteArray() {
        byte[] arr_b = this.getData$okio();
        byte[] arr_b1 = Arrays.copyOf(arr_b, arr_b.length);
        Intrinsics.checkNotNullExpressionValue(arr_b1, "copyOf(this, size)");
        return arr_b1;
    }

    @Override
    public String toString() {
        if(this.getData$okio().length == 0) {
            return "[size=0]";
        }
        int v = _ByteStringKt.access$codePointIndexToCharIndex(this.getData$okio(), 0x40);
        if(v == -1) {
            if(this.getData$okio().length <= 0x40) {
                return "[hex=" + this.hex() + ']';
            }
            StringBuilder stringBuilder0 = new StringBuilder("[size=");
            stringBuilder0.append(this.getData$okio().length);
            stringBuilder0.append(" hex=");
            int v1 = _UtilKt.resolveDefaultParameter(this, 0x40);
            if(v1 > this.getData$okio().length) {
                throw new IllegalArgumentException(("endIndex > length(" + this.getData$okio().length + ')').toString());
            }
            if(v1 < 0) {
                throw new IllegalArgumentException("endIndex < beginIndex");
            }
            stringBuilder0.append((v1 == this.getData$okio().length ? this : new ByteString(ArraysKt.copyOfRange(this.getData$okio(), 0, v1))).hex());
            stringBuilder0.append("…]");
            return stringBuilder0.toString();
        }
        String s = this.utf8();
        String s1 = s.substring(0, v);
        Intrinsics.checkNotNullExpressionValue(s1, "this as java.lang.String…ing(startIndex, endIndex)");
        String s2 = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(s1, "\\", "\\\\", false, 4, null), "\n", "\\n", false, 4, null), "\r", "\\r", false, 4, null);
        return v >= s.length() ? "[text=" + s2 + ']' : "[size=" + this.getData$okio().length + " text=" + s2 + "…]";
    }

    public String utf8() {
        String s = this.getUtf8$okio();
        if(s == null) {
            s = _JvmPlatformKt.toUtf8String(this.internalArray$okio());
            this.setUtf8$okio(s);
        }
        return s;
    }

    public void write(OutputStream outputStream0) throws IOException {
        Intrinsics.checkNotNullParameter(outputStream0, "out");
        outputStream0.write(this.data);
    }

    public void write$okio(Buffer buffer0, int v, int v1) {
        Intrinsics.checkNotNullParameter(buffer0, "buffer");
        _ByteStringKt.commonWrite(this, buffer0, v, v1);
    }

    private final void writeObject(ObjectOutputStream objectOutputStream0) throws IOException {
        objectOutputStream0.writeInt(this.data.length);
        objectOutputStream0.write(this.data);
    }
}

