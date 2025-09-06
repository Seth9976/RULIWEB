package androidx.profileinstaller;

import java.util.Arrays;

public class ProfileVersion {
    static final byte[] METADATA_V001_N = null;
    static final byte[] METADATA_V002 = null;
    public static final int MIN_SUPPORTED_SDK = 24;
    static final byte[] V001_N;
    static final byte[] V005_O;
    static final byte[] V009_O_MR1;
    static final byte[] V010_P;
    static final byte[] V015_S;

    static {
        ProfileVersion.V015_S = new byte[]{0x30, 49, 53, 0};
        ProfileVersion.V010_P = new byte[]{0x30, 49, 0x30, 0};
        ProfileVersion.V009_O_MR1 = new byte[]{0x30, 0x30, 57, 0};
        ProfileVersion.V005_O = new byte[]{0x30, 0x30, 53, 0};
        ProfileVersion.V001_N = new byte[]{0x30, 0x30, 49, 0};
        ProfileVersion.METADATA_V001_N = new byte[]{0x30, 0x30, 49, 0};
        ProfileVersion.METADATA_V002 = new byte[]{0x30, 0x30, 50, 0};
    }

    static String dexKeySeparator(byte[] arr_b) {
        if(Arrays.equals(arr_b, ProfileVersion.V001_N)) {
            return ":";
        }
        return Arrays.equals(arr_b, ProfileVersion.V005_O) ? ":" : "!";
    }
}

