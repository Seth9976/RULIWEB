package androidx.browser.trusted;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class TokenContents {
    private final byte[] mContents;
    private List mFingerprints;
    private String mPackageName;

    private TokenContents(byte[] arr_b) {
        this.mContents = arr_b;
    }

    private TokenContents(byte[] arr_b, String s, List list0) {
        this.mContents = arr_b;
        this.mPackageName = s;
        this.mFingerprints = new ArrayList(list0.size());
        for(Object object0: list0) {
            this.mFingerprints.add(Arrays.copyOf(((byte[])object0), ((byte[])object0).length));
        }
    }

    // 检测为 Lambda 实现
    private static int compareByteArrays(byte[] arr_b, byte[] arr_b1) [...]

    static TokenContents create(String s, List list0) throws IOException {
        return new TokenContents(TokenContents.createToken(s, list0), s, list0);
    }

    private static byte[] createToken(String s, List list0) throws IOException {
        Collections.sort(list0, (byte[] arr_b, byte[] arr_b1) -> {
            if(arr_b == arr_b1) {
                return 0;
            }
            if(arr_b == null) {
                return -1;
            }
            if(arr_b1 == null) {
                return 1;
            }
            for(int v = 0; v < Math.min(arr_b.length, arr_b1.length); ++v) {
                int v1 = arr_b[v];
                int v2 = arr_b1[v];
                if(v1 != v2) {
                    return v1 - v2;
                }
            }
            return arr_b.length == arr_b1.length ? 0 : arr_b.length - arr_b1.length;
        });
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream0 = new DataOutputStream(byteArrayOutputStream0);
        dataOutputStream0.writeUTF(s);
        dataOutputStream0.writeInt(list0.size());
        for(Object object0: list0) {
            dataOutputStream0.writeInt(((byte[])object0).length);
            dataOutputStream0.write(((byte[])object0));
        }
        dataOutputStream0.flush();
        return byteArrayOutputStream0.toByteArray();
    }

    static TokenContents deserialize(byte[] arr_b) {
        return new TokenContents(arr_b);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 == null || this.getClass() != object0.getClass() ? false : Arrays.equals(this.mContents, ((TokenContents)object0).mContents);
    }

    public byte[] getFingerprint(int v) throws IOException {
        this.parseIfNeeded();
        List list0 = this.mFingerprints;
        if(list0 == null) {
            throw new IllegalStateException();
        }
        return Arrays.copyOf(((byte[])list0.get(v)), ((byte[])this.mFingerprints.get(v)).length);
    }

    public int getFingerprintCount() throws IOException {
        this.parseIfNeeded();
        List list0 = this.mFingerprints;
        if(list0 == null) {
            throw new IllegalStateException();
        }
        return list0.size();
    }

    public String getPackageName() throws IOException {
        this.parseIfNeeded();
        String s = this.mPackageName;
        if(s == null) {
            throw new IllegalStateException();
        }
        return s;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(this.mContents);
    }

    private void parseIfNeeded() throws IOException {
        if(this.mPackageName == null) {
            DataInputStream dataInputStream0 = new DataInputStream(new ByteArrayInputStream(this.mContents));
            this.mPackageName = dataInputStream0.readUTF();
            int v = dataInputStream0.readInt();
            this.mFingerprints = new ArrayList(v);
            for(int v1 = 0; v1 < v; ++v1) {
                int v2 = dataInputStream0.readInt();
                byte[] arr_b = new byte[v2];
                if(dataInputStream0.read(arr_b) != v2) {
                    throw new IllegalStateException("Could not read fingerprint");
                }
                this.mFingerprints.add(arr_b);
            }
        }
    }

    public byte[] serialize() {
        return Arrays.copyOf(this.mContents, this.mContents.length);
    }
}

