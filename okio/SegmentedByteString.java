package okio;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import okio.internal._SegmentedByteStringKt;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0002\b\f\n\u0002\u0010\u0005\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u001D\b\u0000\u0012\f\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u00A2\u0006\u0002\u0010\u0007J\b\u0010\r\u001A\u00020\u000EH\u0016J\b\u0010\u000F\u001A\u00020\u0010H\u0016J\b\u0010\u0011\u001A\u00020\u0010H\u0016J(\u0010\u0012\u001A\u00020\u00132\u0006\u0010\u0014\u001A\u00020\u00152\u0006\u0010\u0016\u001A\u00020\u00042\u0006\u0010\u0017\u001A\u00020\u00152\u0006\u0010\u0018\u001A\u00020\u0015H\u0016J\u0015\u0010\u0019\u001A\u00020\u00012\u0006\u0010\u001A\u001A\u00020\u0010H\u0010\u00A2\u0006\u0002\b\u001BJ\u0013\u0010\u001C\u001A\u00020\u001D2\b\u0010\u001E\u001A\u0004\u0018\u00010\u001FH\u0096\u0002J\r\u0010 \u001A\u00020\u0015H\u0010\u00A2\u0006\u0002\b!J\b\u0010\"\u001A\u00020\u0015H\u0016J\b\u0010#\u001A\u00020\u0010H\u0016J\u001D\u0010$\u001A\u00020\u00012\u0006\u0010\u001A\u001A\u00020\u00102\u0006\u0010%\u001A\u00020\u0001H\u0010\u00A2\u0006\u0002\b&J\u0018\u0010\'\u001A\u00020\u00152\u0006\u0010\u001E\u001A\u00020\u00042\u0006\u0010(\u001A\u00020\u0015H\u0016J\r\u0010)\u001A\u00020\u0004H\u0010\u00A2\u0006\u0002\b*J\u0015\u0010+\u001A\u00020,2\u0006\u0010-\u001A\u00020\u0015H\u0010\u00A2\u0006\u0002\b.J\u0018\u0010/\u001A\u00020\u00152\u0006\u0010\u001E\u001A\u00020\u00042\u0006\u0010(\u001A\u00020\u0015H\u0016J(\u00100\u001A\u00020\u001D2\u0006\u0010\u0014\u001A\u00020\u00152\u0006\u0010\u001E\u001A\u00020\u00042\u0006\u00101\u001A\u00020\u00152\u0006\u0010\u0018\u001A\u00020\u0015H\u0016J(\u00100\u001A\u00020\u001D2\u0006\u0010\u0014\u001A\u00020\u00152\u0006\u0010\u001E\u001A\u00020\u00012\u0006\u00101\u001A\u00020\u00152\u0006\u0010\u0018\u001A\u00020\u0015H\u0016J\u0010\u00102\u001A\u00020\u00102\u0006\u00103\u001A\u000204H\u0016J\u0018\u00105\u001A\u00020\u00012\u0006\u00106\u001A\u00020\u00152\u0006\u00107\u001A\u00020\u0015H\u0016J\b\u00108\u001A\u00020\u0001H\u0016J\b\u00109\u001A\u00020\u0001H\u0016J\b\u0010:\u001A\u00020\u0004H\u0016J\b\u0010;\u001A\u00020\u0001H\u0002J\b\u0010<\u001A\u00020\u0010H\u0016J\u0010\u0010=\u001A\u00020\u00132\u0006\u0010>\u001A\u00020?H\u0016J%\u0010=\u001A\u00020\u00132\u0006\u0010@\u001A\u00020A2\u0006\u0010\u0014\u001A\u00020\u00152\u0006\u0010\u0018\u001A\u00020\u0015H\u0010\u00A2\u0006\u0002\bBJ\b\u0010C\u001A\u00020DH\u0002R\u0014\u0010\u0005\u001A\u00020\u0006X\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\b\u0010\tR\u001C\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0080\u0004\u00A2\u0006\n\n\u0002\u0010\f\u001A\u0004\b\n\u0010\u000B\u00A8\u0006E"}, d2 = {"Lokio/SegmentedByteString;", "Lokio/ByteString;", "segments", "", "", "directory", "", "([[B[I)V", "getDirectory$okio", "()[I", "getSegments$okio", "()[[B", "[[B", "asByteBuffer", "Ljava/nio/ByteBuffer;", "base64", "", "base64Url", "copyInto", "", "offset", "", "target", "targetOffset", "byteCount", "digest", "algorithm", "digest$okio", "equals", "", "other", "", "getSize", "getSize$okio", "hashCode", "hex", "hmac", "key", "hmac$okio", "indexOf", "fromIndex", "internalArray", "internalArray$okio", "internalGet", "", "pos", "internalGet$okio", "lastIndexOf", "rangeEquals", "otherOffset", "string", "charset", "Ljava/nio/charset/Charset;", "substring", "beginIndex", "endIndex", "toAsciiLowercase", "toAsciiUppercase", "toByteArray", "toByteString", "toString", "write", "out", "Ljava/io/OutputStream;", "buffer", "Lokio/Buffer;", "write$okio", "writeReplace", "Ljava/lang/Object;", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class SegmentedByteString extends ByteString {
    private final transient int[] directory;
    private final transient byte[][] segments;

    public SegmentedByteString(byte[][] arr2_b, int[] arr_v) {
        Intrinsics.checkNotNullParameter(arr2_b, "segments");
        Intrinsics.checkNotNullParameter(arr_v, "directory");
        super(ByteString.EMPTY.getData$okio());
        this.segments = arr2_b;
        this.directory = arr_v;
    }

    @Override  // okio.ByteString
    public ByteBuffer asByteBuffer() {
        ByteBuffer byteBuffer0 = ByteBuffer.wrap(this.toByteArray()).asReadOnlyBuffer();
        Intrinsics.checkNotNullExpressionValue(byteBuffer0, "wrap(toByteArray()).asReadOnlyBuffer()");
        return byteBuffer0;
    }

    @Override  // okio.ByteString
    public String base64() {
        return this.toByteString().base64();
    }

    @Override  // okio.ByteString
    public String base64Url() {
        return this.toByteString().base64Url();
    }

    @Override  // okio.ByteString
    public void copyInto(int v, byte[] arr_b, int v1, int v2) {
        Intrinsics.checkNotNullParameter(arr_b, "target");
        _UtilKt.checkOffsetAndCount(this.size(), v, v2);
        _UtilKt.checkOffsetAndCount(arr_b.length, v1, v2);
        int v3 = v2 + v;
        for(int v4 = _SegmentedByteStringKt.segment(this, v); v < v3; ++v4) {
            int v5 = v4 == 0 ? 0 : this.getDirectory$okio()[v4 - 1];
            int v6 = Math.min(v3, this.getDirectory$okio()[v4]) - v;
            int v7 = this.getDirectory$okio()[this.getSegments$okio().length + v4] + (v - v5);
            ArraysKt.copyInto(this.getSegments$okio()[v4], arr_b, v1, v7, v7 + v6);
            v1 += v6;
            v += v6;
        }
    }

    @Override  // okio.ByteString
    public ByteString digest$okio(String s) {
        Intrinsics.checkNotNullParameter(s, "algorithm");
        MessageDigest messageDigest0 = MessageDigest.getInstance(s);
        int v = this.getSegments$okio().length;
        int v1 = 0;
        for(int v2 = 0; v1 < v; v2 = v4) {
            int v3 = this.getDirectory$okio()[v + v1];
            int v4 = this.getDirectory$okio()[v1];
            messageDigest0.update(this.getSegments$okio()[v1], v3, v4 - v2);
            ++v1;
        }
        byte[] arr_b = messageDigest0.digest();
        Intrinsics.checkNotNullExpressionValue(arr_b, "digestBytes");
        return new ByteString(arr_b);
    }

    // 去混淆评级： 低(30)
    @Override  // okio.ByteString
    public boolean equals(Object object0) {
        return object0 == this ? true : object0 instanceof ByteString && ((ByteString)object0).size() == this.size() && this.rangeEquals(0, ((ByteString)object0), 0, this.size());
    }

    public final int[] getDirectory$okio() {
        return this.directory;
    }

    public final byte[][] getSegments$okio() {
        return this.segments;
    }

    @Override  // okio.ByteString
    public int getSize$okio() {
        return this.getDirectory$okio()[this.getSegments$okio().length - 1];
    }

    @Override  // okio.ByteString
    public int hashCode() {
        int v = this.getHashCode$okio();
        if(v != 0) {
            return v;
        }
        int v1 = this.getSegments$okio().length;
        int v2 = 0;
        int v4 = 1;
        for(int v3 = 0; v2 < v1; v3 = v6) {
            int v5 = this.getDirectory$okio()[v1 + v2];
            int v6 = this.getDirectory$okio()[v2];
            byte[] arr_b = this.getSegments$okio()[v2];
            int v7 = v6 - v3 + v5;
            while(v5 < v7) {
                v4 = v4 * 0x1F + arr_b[v5];
                ++v5;
            }
            ++v2;
        }
        this.setHashCode$okio(v4);
        return v4;
    }

    @Override  // okio.ByteString
    public String hex() {
        return this.toByteString().hex();
    }

    @Override  // okio.ByteString
    public ByteString hmac$okio(String s, ByteString byteString0) {
        Intrinsics.checkNotNullParameter(s, "algorithm");
        Intrinsics.checkNotNullParameter(byteString0, "key");
        try {
            Mac mac0 = Mac.getInstance(s);
            mac0.init(new SecretKeySpec(byteString0.toByteArray(), s));
            int v = this.getSegments$okio().length;
            int v1 = 0;
            for(int v2 = 0; v1 < v; v2 = v4) {
                int v3 = this.getDirectory$okio()[v + v1];
                int v4 = this.getDirectory$okio()[v1];
                mac0.update(this.getSegments$okio()[v1], v3, v4 - v2);
                ++v1;
            }
            byte[] arr_b = mac0.doFinal();
            Intrinsics.checkNotNullExpressionValue(arr_b, "mac.doFinal()");
            return new ByteString(arr_b);
        }
        catch(InvalidKeyException invalidKeyException0) {
            throw new IllegalArgumentException(invalidKeyException0);
        }
    }

    @Override  // okio.ByteString
    public int indexOf(byte[] arr_b, int v) {
        Intrinsics.checkNotNullParameter(arr_b, "other");
        return this.toByteString().indexOf(arr_b, v);
    }

    @Override  // okio.ByteString
    public byte[] internalArray$okio() {
        return this.toByteArray();
    }

    @Override  // okio.ByteString
    public byte internalGet$okio(int v) {
        _UtilKt.checkOffsetAndCount(this.getDirectory$okio()[this.getSegments$okio().length - 1], v, 1L);
        int v1 = _SegmentedByteStringKt.segment(this, v);
        return v1 == 0 ? this.getSegments$okio()[0][v + this.getDirectory$okio()[this.getSegments$okio().length]] : this.getSegments$okio()[v1][v - this.getDirectory$okio()[v1 - 1] + this.getDirectory$okio()[this.getSegments$okio().length + v1]];
    }

    @Override  // okio.ByteString
    public int lastIndexOf(byte[] arr_b, int v) {
        Intrinsics.checkNotNullParameter(arr_b, "other");
        return this.toByteString().lastIndexOf(arr_b, v);
    }

    @Override  // okio.ByteString
    public boolean rangeEquals(int v, ByteString byteString0, int v1, int v2) {
        Intrinsics.checkNotNullParameter(byteString0, "other");
        if(v >= 0 && v <= this.size() - v2) {
            int v3 = v2 + v;
            for(int v4 = _SegmentedByteStringKt.segment(this, v); v < v3; ++v4) {
                int v5 = v4 == 0 ? 0 : this.getDirectory$okio()[v4 - 1];
                int v6 = Math.min(v3, this.getDirectory$okio()[v4]) - v;
                if(!byteString0.rangeEquals(v1, this.getSegments$okio()[v4], this.getDirectory$okio()[this.getSegments$okio().length + v4] + (v - v5), v6)) {
                    return false;
                }
                v1 += v6;
                v += v6;
            }
            return true;
        }
        return false;
    }

    @Override  // okio.ByteString
    public boolean rangeEquals(int v, byte[] arr_b, int v1, int v2) {
        Intrinsics.checkNotNullParameter(arr_b, "other");
        if(v >= 0 && v <= this.size() - v2 && v1 >= 0 && v1 <= arr_b.length - v2) {
            int v3 = v2 + v;
            for(int v4 = _SegmentedByteStringKt.segment(this, v); v < v3; ++v4) {
                int v5 = v4 == 0 ? 0 : this.getDirectory$okio()[v4 - 1];
                int v6 = Math.min(v3, this.getDirectory$okio()[v4]) - v;
                if(!_UtilKt.arrayRangeEquals(this.getSegments$okio()[v4], this.getDirectory$okio()[this.getSegments$okio().length + v4] + (v - v5), arr_b, v1, v6)) {
                    return false;
                }
                v1 += v6;
                v += v6;
            }
            return true;
        }
        return false;
    }

    @Override  // okio.ByteString
    public String string(Charset charset0) {
        Intrinsics.checkNotNullParameter(charset0, "charset");
        return this.toByteString().string(charset0);
    }

    @Override  // okio.ByteString
    public ByteString substring(int v, int v1) {
        int v2 = _UtilKt.resolveDefaultParameter(this, v1);
        if(v < 0) {
            throw new IllegalArgumentException(("beginIndex=" + v + " < 0").toString());
        }
        if(v2 > this.size()) {
            throw new IllegalArgumentException(("endIndex=" + v2 + " > length(" + this.size() + ')').toString());
        }
        int v3 = v2 - v;
        if(v3 < 0) {
            throw new IllegalArgumentException(("endIndex=" + v2 + " < beginIndex=" + v).toString());
        }
        if(v == 0 && v2 == this.size()) {
            return this;
        }
        if(v == v2) {
            return ByteString.EMPTY;
        }
        int v4 = _SegmentedByteStringKt.segment(this, v);
        int v5 = _SegmentedByteStringKt.segment(this, v2 - 1);
        byte[][] arr2_b = (byte[][])ArraysKt.copyOfRange(this.getSegments$okio(), v4, v5 + 1);
        int[] arr_v = new int[arr2_b.length * 2];
        int v6 = 0;
        if(v4 <= v5) {
            int v7 = v4;
            for(int v8 = 0; true; ++v8) {
                arr_v[v8] = Math.min(this.getDirectory$okio()[v7] - v, v3);
                arr_v[v8 + arr2_b.length] = this.getDirectory$okio()[this.getSegments$okio().length + v7];
                if(v7 == v5) {
                    break;
                }
                ++v7;
            }
        }
        if(v4 != 0) {
            v6 = this.getDirectory$okio()[v4 - 1];
        }
        arr_v[arr2_b.length] += v - v6;
        return new SegmentedByteString(arr2_b, arr_v);
    }

    @Override  // okio.ByteString
    public ByteString toAsciiLowercase() {
        return this.toByteString().toAsciiLowercase();
    }

    @Override  // okio.ByteString
    public ByteString toAsciiUppercase() {
        return this.toByteString().toAsciiUppercase();
    }

    @Override  // okio.ByteString
    public byte[] toByteArray() {
        byte[] arr_b = new byte[this.size()];
        int v = this.getSegments$okio().length;
        int v1 = 0;
        int v3 = 0;
        for(int v2 = 0; v1 < v; v2 = v5) {
            int v4 = this.getDirectory$okio()[v + v1];
            int v5 = this.getDirectory$okio()[v1];
            int v6 = v5 - v2;
            ArraysKt.copyInto(this.getSegments$okio()[v1], arr_b, v3, v4, v4 + v6);
            v3 += v6;
            ++v1;
        }
        return arr_b;
    }

    private final ByteString toByteString() {
        return new ByteString(this.toByteArray());
    }

    @Override  // okio.ByteString
    public String toString() {
        return this.toByteString().toString();
    }

    @Override  // okio.ByteString
    public void write(OutputStream outputStream0) throws IOException {
        Intrinsics.checkNotNullParameter(outputStream0, "out");
        int v = this.getSegments$okio().length;
        int v1 = 0;
        for(int v2 = 0; v1 < v; v2 = v4) {
            int v3 = this.getDirectory$okio()[v + v1];
            int v4 = this.getDirectory$okio()[v1];
            outputStream0.write(this.getSegments$okio()[v1], v3, v4 - v2);
            ++v1;
        }
    }

    @Override  // okio.ByteString
    public void write$okio(Buffer buffer0, int v, int v1) {
        Intrinsics.checkNotNullParameter(buffer0, "buffer");
        int v2 = v + v1;
        for(int v3 = _SegmentedByteStringKt.segment(this, v); v < v2; ++v3) {
            int v4 = v3 == 0 ? 0 : this.getDirectory$okio()[v3 - 1];
            int v5 = Math.min(v2, this.getDirectory$okio()[v3]) - v;
            int v6 = this.getDirectory$okio()[this.getSegments$okio().length + v3] + (v - v4);
            Segment segment0 = new Segment(this.getSegments$okio()[v3], v6, v6 + v5, true, false);
            if(buffer0.head == null) {
                segment0.prev = segment0;
                segment0.next = segment0.prev;
                buffer0.head = segment0.next;
            }
            else {
                Segment segment1 = buffer0.head;
                Intrinsics.checkNotNull(segment1);
                Segment segment2 = segment1.prev;
                Intrinsics.checkNotNull(segment2);
                segment2.push(segment0);
            }
            v += v5;
        }
        buffer0.setSize$okio(buffer0.size() + ((long)v1));
    }

    private final Object writeReplace() {
        return this.toByteString();
    }
}

