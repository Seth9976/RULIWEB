package okio;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00192\u00020\u00012\u00020\u0002:\u0001\u0019B\u0017\b\u0010\u0012\u0006\u0010\u0003\u001A\u00020\u0002\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0017\b\u0010\u0012\u0006\u0010\u0003\u001A\u00020\u0002\u0012\u0006\u0010\u0007\u001A\u00020\b¢\u0006\u0002\u0010\tB\u0017\b\u0010\u0012\u0006\u0010\u0003\u001A\u00020\u0002\u0012\u0006\u0010\n\u001A\u00020\u000B¢\u0006\u0002\u0010\fB\u001F\b\u0010\u0012\u0006\u0010\u0003\u001A\u00020\u0002\u0012\u0006\u0010\r\u001A\u00020\u000E\u0012\u0006\u0010\u0007\u001A\u00020\b¢\u0006\u0002\u0010\u000FJ\r\u0010\u0010\u001A\u00020\u000EH\u0007¢\u0006\u0002\b\u0013J\u0018\u0010\u0014\u001A\u00020\u00152\u0006\u0010\u0016\u001A\u00020\u00172\u0006\u0010\u0018\u001A\u00020\u0015H\u0016R\u0011\u0010\u0010\u001A\u00020\u000E8G¢\u0006\u0006\u001A\u0004\b\u0010\u0010\u0011R\u0010\u0010\n\u001A\u0004\u0018\u00010\u000BX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001A\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001A"}, d2 = {"Lokio/HashingSource;", "Lokio/ForwardingSource;", "Lokio/Source;", "source", "digest", "Ljava/security/MessageDigest;", "(Lokio/Source;Ljava/security/MessageDigest;)V", "algorithm", "", "(Lokio/Source;Ljava/lang/String;)V", "mac", "Ljavax/crypto/Mac;", "(Lokio/Source;Ljavax/crypto/Mac;)V", "key", "Lokio/ByteString;", "(Lokio/Source;Lokio/ByteString;Ljava/lang/String;)V", "hash", "()Lokio/ByteString;", "messageDigest", "-deprecated_hash", "read", "", "sink", "Lokio/Buffer;", "byteCount", "Companion", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class HashingSource extends ForwardingSource implements Source {
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH\u0007J\u0018\u0010\t\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH\u0007J\u0018\u0010\n\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH\u0007J\u0010\u0010\u000B\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0007J\u0010\u0010\f\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0007J\u0010\u0010\r\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0007J\u0010\u0010\u000E\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0007¨\u0006\u000F"}, d2 = {"Lokio/HashingSource$Companion;", "", "()V", "hmacSha1", "Lokio/HashingSource;", "source", "Lokio/Source;", "key", "Lokio/ByteString;", "hmacSha256", "hmacSha512", "md5", "sha1", "sha256", "sha512", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        @JvmStatic
        public final HashingSource hmacSha1(Source source0, ByteString byteString0) {
            Intrinsics.checkNotNullParameter(source0, "source");
            Intrinsics.checkNotNullParameter(byteString0, "key");
            return new HashingSource(source0, byteString0, "HmacSHA1");
        }

        @JvmStatic
        public final HashingSource hmacSha256(Source source0, ByteString byteString0) {
            Intrinsics.checkNotNullParameter(source0, "source");
            Intrinsics.checkNotNullParameter(byteString0, "key");
            return new HashingSource(source0, byteString0, "HmacSHA256");
        }

        @JvmStatic
        public final HashingSource hmacSha512(Source source0, ByteString byteString0) {
            Intrinsics.checkNotNullParameter(source0, "source");
            Intrinsics.checkNotNullParameter(byteString0, "key");
            return new HashingSource(source0, byteString0, "HmacSHA512");
        }

        @JvmStatic
        public final HashingSource md5(Source source0) {
            Intrinsics.checkNotNullParameter(source0, "source");
            return new HashingSource(source0, "MD5");
        }

        @JvmStatic
        public final HashingSource sha1(Source source0) {
            Intrinsics.checkNotNullParameter(source0, "source");
            return new HashingSource(source0, "SHA-1");
        }

        @JvmStatic
        public final HashingSource sha256(Source source0) {
            Intrinsics.checkNotNullParameter(source0, "source");
            return new HashingSource(source0, "SHA-256");
        }

        @JvmStatic
        public final HashingSource sha512(Source source0) {
            Intrinsics.checkNotNullParameter(source0, "source");
            return new HashingSource(source0, "SHA-512");
        }
    }

    public static final Companion Companion;
    private final Mac mac;
    private final MessageDigest messageDigest;

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "hash", imports = {}))
    public final ByteString -deprecated_hash() {
        return this.hash();
    }

    static {
        HashingSource.Companion = new Companion(null);
    }

    public HashingSource(Source source0, String s) {
        Intrinsics.checkNotNullParameter(source0, "source");
        Intrinsics.checkNotNullParameter(s, "algorithm");
        MessageDigest messageDigest0 = MessageDigest.getInstance(s);
        Intrinsics.checkNotNullExpressionValue(messageDigest0, "getInstance(algorithm)");
        this(source0, messageDigest0);
    }

    public HashingSource(Source source0, MessageDigest messageDigest0) {
        Intrinsics.checkNotNullParameter(source0, "source");
        Intrinsics.checkNotNullParameter(messageDigest0, "digest");
        super(source0);
        this.messageDigest = messageDigest0;
        this.mac = null;
    }

    public HashingSource(Source source0, Mac mac0) {
        Intrinsics.checkNotNullParameter(source0, "source");
        Intrinsics.checkNotNullParameter(mac0, "mac");
        super(source0);
        this.mac = mac0;
        this.messageDigest = null;
    }

    public HashingSource(Source source0, ByteString byteString0, String s) {
        Intrinsics.checkNotNullParameter(source0, "source");
        Mac mac0;
        Intrinsics.checkNotNullParameter(byteString0, "key");
        Intrinsics.checkNotNullParameter(s, "algorithm");
        try {
            mac0 = Mac.getInstance(s);
            mac0.init(new SecretKeySpec(byteString0.toByteArray(), s));
        }
        catch(InvalidKeyException invalidKeyException0) {
            throw new IllegalArgumentException(invalidKeyException0);
        }
        Intrinsics.checkNotNullExpressionValue(mac0, "try {\n      Mac.getInsta…rgumentException(e)\n    }");
        this(source0, mac0);
    }

    public final ByteString hash() {
        byte[] arr_b;
        MessageDigest messageDigest0 = this.messageDigest;
        if(messageDigest0 == null) {
            Intrinsics.checkNotNull(this.mac);
            arr_b = this.mac.doFinal();
        }
        else {
            arr_b = messageDigest0.digest();
        }
        Intrinsics.checkNotNullExpressionValue(arr_b, "result");
        return new ByteString(arr_b);
    }

    @JvmStatic
    public static final HashingSource hmacSha1(Source source0, ByteString byteString0) {
        return HashingSource.Companion.hmacSha1(source0, byteString0);
    }

    @JvmStatic
    public static final HashingSource hmacSha256(Source source0, ByteString byteString0) {
        return HashingSource.Companion.hmacSha256(source0, byteString0);
    }

    @JvmStatic
    public static final HashingSource hmacSha512(Source source0, ByteString byteString0) {
        return HashingSource.Companion.hmacSha512(source0, byteString0);
    }

    @JvmStatic
    public static final HashingSource md5(Source source0) {
        return HashingSource.Companion.md5(source0);
    }

    @Override  // okio.ForwardingSource, okio.Source
    public long read(Buffer buffer0, long v) throws IOException {
        Intrinsics.checkNotNullParameter(buffer0, "sink");
        long v1 = super.read(buffer0, v);
        if(v1 != -1L) {
            long v2 = buffer0.size() - v1;
            long v3 = buffer0.size();
            Segment segment0 = buffer0.head;
            Intrinsics.checkNotNull(segment0);
            while(v3 > v2) {
                segment0 = segment0.prev;
                Intrinsics.checkNotNull(segment0);
                v3 -= (long)(segment0.limit - segment0.pos);
            }
            while(v3 < buffer0.size()) {
                int v4 = (int)(((long)segment0.pos) + v2 - v3);
                MessageDigest messageDigest0 = this.messageDigest;
                if(messageDigest0 == null) {
                    Intrinsics.checkNotNull(this.mac);
                    this.mac.update(segment0.data, v4, segment0.limit - v4);
                }
                else {
                    messageDigest0.update(segment0.data, v4, segment0.limit - v4);
                }
                v3 += (long)(segment0.limit - segment0.pos);
                segment0 = segment0.next;
                Intrinsics.checkNotNull(segment0);
                v2 = v3;
            }
        }
        return v1;
    }

    @JvmStatic
    public static final HashingSource sha1(Source source0) {
        return HashingSource.Companion.sha1(source0);
    }

    @JvmStatic
    public static final HashingSource sha256(Source source0) {
        return HashingSource.Companion.sha256(source0);
    }

    @JvmStatic
    public static final HashingSource sha512(Source source0) {
        return HashingSource.Companion.sha512(source0);
    }
}

