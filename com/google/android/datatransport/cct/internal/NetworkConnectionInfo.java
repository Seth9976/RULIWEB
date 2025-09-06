package com.google.android.datatransport.cct.internal;

import android.util.SparseArray;

public abstract class NetworkConnectionInfo {
    public static abstract class Builder {
        public abstract NetworkConnectionInfo build();

        public abstract Builder setMobileSubtype(MobileSubtype arg1);

        public abstract Builder setNetworkType(NetworkType arg1);
    }

    public static enum MobileSubtype {
        UNKNOWN_MOBILE_SUBTYPE(0),
        GPRS(1),
        EDGE(2),
        UMTS(3),
        CDMA(4),
        EVDO_0(5),
        EVDO_A(6),
        RTT(7),
        HSDPA(8),
        HSUPA(9),
        HSPA(10),
        IDEN(11),
        EVDO_B(12),
        LTE(13),
        EHRPD(14),
        HSPAP(15),
        GSM(16),
        TD_SCDMA(17),
        IWLAN(18),
        LTE_CA(19),
        COMBINED(100);

        private final int value;
        private static final SparseArray valueMap;

        static {
            SparseArray sparseArray0 = new SparseArray();
            MobileSubtype.valueMap = sparseArray0;
            sparseArray0.put(0, MobileSubtype.UNKNOWN_MOBILE_SUBTYPE);
            sparseArray0.put(1, MobileSubtype.GPRS);
            sparseArray0.put(2, MobileSubtype.EDGE);
            sparseArray0.put(3, MobileSubtype.UMTS);
            sparseArray0.put(4, MobileSubtype.CDMA);
            sparseArray0.put(5, MobileSubtype.EVDO_0);
            sparseArray0.put(6, MobileSubtype.EVDO_A);
            sparseArray0.put(7, MobileSubtype.RTT);
            sparseArray0.put(8, MobileSubtype.HSDPA);
            sparseArray0.put(9, MobileSubtype.HSUPA);
            sparseArray0.put(10, MobileSubtype.HSPA);
            sparseArray0.put(11, MobileSubtype.IDEN);
            sparseArray0.put(12, MobileSubtype.EVDO_B);
            sparseArray0.put(13, MobileSubtype.LTE);
            sparseArray0.put(14, MobileSubtype.EHRPD);
            sparseArray0.put(15, MobileSubtype.HSPAP);
            sparseArray0.put(16, MobileSubtype.GSM);
            sparseArray0.put(17, MobileSubtype.TD_SCDMA);
            sparseArray0.put(18, MobileSubtype.IWLAN);
            sparseArray0.put(19, MobileSubtype.LTE_CA);
        }

        private MobileSubtype(int v1) {
            this.value = v1;
        }

        public static MobileSubtype forNumber(int v) {
            return (MobileSubtype)MobileSubtype.valueMap.get(v);
        }

        public int getValue() {
            return this.value;
        }
    }

    public static enum NetworkType {
        MOBILE(0),
        WIFI(1),
        MOBILE_MMS(2),
        MOBILE_SUPL(3),
        MOBILE_DUN(4),
        MOBILE_HIPRI(5),
        WIMAX(6),
        BLUETOOTH(7),
        DUMMY(8),
        ETHERNET(9),
        MOBILE_FOTA(10),
        MOBILE_IMS(11),
        MOBILE_CBS(12),
        WIFI_P2P(13),
        MOBILE_IA(14),
        MOBILE_EMERGENCY(15),
        PROXY(16),
        VPN(17),
        NONE(-1);

        private final int value;
        private static final SparseArray valueMap;

        static {
            SparseArray sparseArray0 = new SparseArray();
            NetworkType.valueMap = sparseArray0;
            sparseArray0.put(0, NetworkType.MOBILE);
            sparseArray0.put(1, NetworkType.WIFI);
            sparseArray0.put(2, NetworkType.MOBILE_MMS);
            sparseArray0.put(3, NetworkType.MOBILE_SUPL);
            sparseArray0.put(4, NetworkType.MOBILE_DUN);
            sparseArray0.put(5, NetworkType.MOBILE_HIPRI);
            sparseArray0.put(6, NetworkType.WIMAX);
            sparseArray0.put(7, NetworkType.BLUETOOTH);
            sparseArray0.put(8, NetworkType.DUMMY);
            sparseArray0.put(9, NetworkType.ETHERNET);
            sparseArray0.put(10, NetworkType.MOBILE_FOTA);
            sparseArray0.put(11, NetworkType.MOBILE_IMS);
            sparseArray0.put(12, NetworkType.MOBILE_CBS);
            sparseArray0.put(13, NetworkType.WIFI_P2P);
            sparseArray0.put(14, NetworkType.MOBILE_IA);
            sparseArray0.put(15, NetworkType.MOBILE_EMERGENCY);
            sparseArray0.put(16, NetworkType.PROXY);
            sparseArray0.put(17, NetworkType.VPN);
            sparseArray0.put(-1, NetworkType.NONE);
        }

        private NetworkType(int v1) {
            this.value = v1;
        }

        public static NetworkType forNumber(int v) {
            return (NetworkType)NetworkType.valueMap.get(v);
        }

        public int getValue() {
            return this.value;
        }
    }

    public static Builder builder() {
        return new com.google.android.datatransport.cct.internal.AutoValue_NetworkConnectionInfo.Builder();
    }

    public abstract MobileSubtype getMobileSubtype();

    public abstract NetworkType getNetworkType();
}

