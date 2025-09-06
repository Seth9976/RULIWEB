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

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u0000 \u001A2\u00020\u00012\u00020\u0002:\u0001\u001AB\u0017\b\u0010\u0012\u0006\u0010\u0003\u001A\u00020\u0002\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0017\b\u0010\u0012\u0006\u0010\u0003\u001A\u00020\u0002\u0012\u0006\u0010\u0007\u001A\u00020\b¢\u0006\u0002\u0010\tB\u0017\b\u0010\u0012\u0006\u0010\u0003\u001A\u00020\u0002\u0012\u0006\u0010\n\u001A\u00020\u000B¢\u0006\u0002\u0010\fB\u001F\b\u0010\u0012\u0006\u0010\u0003\u001A\u00020\u0002\u0012\u0006\u0010\r\u001A\u00020\u000E\u0012\u0006\u0010\u0007\u001A\u00020\b¢\u0006\u0002\u0010\u000FJ\r\u0010\u0010\u001A\u00020\u000EH\u0007¢\u0006\u0002\b\u0013J\u0018\u0010\u0014\u001A\u00020\u00152\u0006\u0010\u0016\u001A\u00020\u00172\u0006\u0010\u0018\u001A\u00020\u0019H\u0016R\u0011\u0010\u0010\u001A\u00020\u000E8G¢\u0006\u0006\u001A\u0004\b\u0010\u0010\u0011R\u0010\u0010\n\u001A\u0004\u0018\u00010\u000BX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001A\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001B"}, d2 = {"Lokio/HashingSink;", "Lokio/ForwardingSink;", "Lokio/Sink;", "sink", "digest", "Ljava/security/MessageDigest;", "(Lokio/Sink;Ljava/security/MessageDigest;)V", "algorithm", "", "(Lokio/Sink;Ljava/lang/String;)V", "mac", "Ljavax/crypto/Mac;", "(Lokio/Sink;Ljavax/crypto/Mac;)V", "key", "Lokio/ByteString;", "(Lokio/Sink;Lokio/ByteString;Ljava/lang/String;)V", "hash", "()Lokio/ByteString;", "messageDigest", "-deprecated_hash", "write", "", "source", "Lokio/Buffer;", "byteCount", "", "Companion", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class HashingSink extends ForwardingSink implements Sink {
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH\u0007J\u0018\u0010\t\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH\u0007J\u0018\u0010\n\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH\u0007J\u0010\u0010\u000B\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0007J\u0010\u0010\f\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0007J\u0010\u0010\r\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0007J\u0010\u0010\u000E\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0007¨\u0006\u000F"}, d2 = {"Lokio/HashingSink$Companion;", "", "()V", "hmacSha1", "Lokio/HashingSink;", "sink", "Lokio/Sink;", "key", "Lokio/ByteString;", "hmacSha256", "hmacSha512", "md5", "sha1", "sha256", "sha512", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        @JvmStatic
        public final HashingSink hmacSha1(Sink sink0, ByteString byteString0) {
            Intrinsics.checkNotNullParameter(sink0, "sink");
            Intrinsics.checkNotNullParameter(byteString0, "key");
            return new HashingSink(sink0, byteString0, "HmacSHA1");
        }

        @JvmStatic
        public final HashingSink hmacSha256(Sink sink0, ByteString byteString0) {
            Intrinsics.checkNotNullParameter(sink0, "sink");
            Intrinsics.checkNotNullParameter(byteString0, "key");
            return new HashingSink(sink0, byteString0, "HmacSHA256");
        }

        @JvmStatic
        public final HashingSink hmacSha512(Sink sink0, ByteString byteString0) {
            Intrinsics.checkNotNullParameter(sink0, "sink");
            Intrinsics.checkNotNullParameter(byteString0, "key");
            return new HashingSink(sink0, byteString0, "HmacSHA512");
        }

        @JvmStatic
        public final HashingSink md5(Sink sink0) {
            Intrinsics.checkNotNullParameter(sink0, "sink");
            return new HashingSink(sink0, "MD5");
        }

        @JvmStatic
        public final HashingSink sha1(Sink sink0) {
            Intrinsics.checkNotNullParameter(sink0, "sink");
            return new HashingSink(sink0, "SHA-1");
        }

        @JvmStatic
        public final HashingSink sha256(Sink sink0) {
            Intrinsics.checkNotNullParameter(sink0, "sink");
            return new HashingSink(sink0, "SHA-256");
        }

        @JvmStatic
        public final HashingSink sha512(Sink sink0) {
            Intrinsics.checkNotNullParameter(sink0, "sink");
            return new HashingSink(sink0, "SHA-512");
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
        HashingSink.Companion = new Companion(null);
    }

    public HashingSink(Sink sink0, String s) {
        Intrinsics.checkNotNullParameter(sink0, "sink");
        Intrinsics.checkNotNullParameter(s, "algorithm");
        MessageDigest messageDigest0 = MessageDigest.getInstance(s);
        Intrinsics.checkNotNullExpressionValue(messageDigest0, "getInstance(algorithm)");
        this(sink0, messageDigest0);
    }

    public HashingSink(Sink sink0, MessageDigest messageDigest0) {
        Intrinsics.checkNotNullParameter(sink0, "sink");
        Intrinsics.checkNotNullParameter(messageDigest0, "digest");
        super(sink0);
        this.messageDigest = messageDigest0;
        this.mac = null;
    }

    public HashingSink(Sink sink0, Mac mac0) {
        Intrinsics.checkNotNullParameter(sink0, "sink");
        Intrinsics.checkNotNullParameter(mac0, "mac");
        super(sink0);
        this.mac = mac0;
        this.messageDigest = null;
    }

    public HashingSink(Sink sink0, ByteString byteString0, String s) {
        Intrinsics.checkNotNullParameter(sink0, "sink");
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
        this(sink0, mac0);
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
    public static final HashingSink hmacSha1(Sink sink0, ByteString byteString0) {
        return HashingSink.Companion.hmacSha1(sink0, byteString0);
    }

    @JvmStatic
    public static final HashingSink hmacSha256(Sink sink0, ByteString byteString0) {
        return HashingSink.Companion.hmacSha256(sink0, byteString0);
    }

    @JvmStatic
    public static final HashingSink hmacSha512(Sink sink0, ByteString byteString0) {
        return HashingSink.Companion.hmacSha512(sink0, byteString0);
    }

    @JvmStatic
    public static final HashingSink md5(Sink sink0) {
        return HashingSink.Companion.md5(sink0);
    }

    @JvmStatic
    public static final HashingSink sha1(Sink sink0) {
        return HashingSink.Companion.sha1(sink0);
    }

    @JvmStatic
    public static final HashingSink sha256(Sink sink0) {
        return HashingSink.Companion.sha256(sink0);
    }

    @JvmStatic
    public static final HashingSink sha512(Sink sink0) {
        return HashingSink.Companion.sha512(sink0);
    }

    @Override  // okio.ForwardingSink, okio.Sink
    public void write(Buffer buffer0, long v) throws IOException {
        Intrinsics.checkNotNullParameter(buffer0, "source");
        _UtilKt.checkOffsetAndCount(buffer0.size(), 0L, v);
        Segment segment0 = buffer0.head;
        Intrinsics.checkNotNull(segment0);
        long v1 = 0L;
        while(v1 < v) {
            int v2 = (int)Math.min(v - v1, segment0.limit - segment0.pos);
            MessageDigest messageDigest0 = this.messageDigest;
            if(messageDigest0 == null) {
                Intrinsics.checkNotNull(this.mac);
                this.mac.update(segment0.data, segment0.pos, v2);
            }
            else {
                messageDigest0.update(segment0.data, segment0.pos, v2);
            }
            v1 += (long)v2;
            segment0 = segment0.next;
            Intrinsics.checkNotNull(segment0);
        }
        super.write(buffer0, v);
    }
}

