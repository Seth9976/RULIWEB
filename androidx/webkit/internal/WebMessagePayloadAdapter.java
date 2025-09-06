package androidx.webkit.internal;

import java.util.Objects;
import org.chromium.support_lib_boundary.WebMessagePayloadBoundaryInterface;

public class WebMessagePayloadAdapter implements WebMessagePayloadBoundaryInterface {
    private final byte[] mArrayBuffer;
    private final String mString;
    private final int mType;

    public WebMessagePayloadAdapter(String s) {
        this.mType = 0;
        this.mString = s;
        this.mArrayBuffer = null;
    }

    public WebMessagePayloadAdapter(byte[] arr_b) {
        this.mType = 1;
        this.mString = null;
        this.mArrayBuffer = arr_b;
    }

    private void checkType(int v) {
        if(this.mType != v) {
            throw new IllegalStateException("Expected " + v + ", but type is " + this.mType);
        }
    }

    @Override  // org.chromium.support_lib_boundary.WebMessagePayloadBoundaryInterface
    public byte[] getAsArrayBuffer() {
        this.checkType(1);
        return (byte[])Objects.requireNonNull(this.mArrayBuffer);
    }

    @Override  // org.chromium.support_lib_boundary.WebMessagePayloadBoundaryInterface
    public String getAsString() {
        this.checkType(0);
        return this.mString;
    }

    @Override  // org.chromium.support_lib_boundary.FeatureFlagHolderBoundaryInterface
    public String[] getSupportedFeatures() {
        return new String[0];
    }

    @Override  // org.chromium.support_lib_boundary.WebMessagePayloadBoundaryInterface
    public int getType() {
        return this.mType;
    }
}

