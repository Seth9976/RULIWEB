package com.google.android.gms.fido.fido2.api.common;

public enum RSAAlgorithm implements Algorithm {
    RS256(0xFFFFFEFF),
    RS384(0xFFFFFEFE),
    RS512(0xFFFFFEFD),
    @Deprecated
    LEGACY_RS1(0xFFFFFEFA),
    PS256(-37),
    PS384(-38),
    PS512(-39),
    RS1(0xFFFF0001);

    private final int zzb;

    private RSAAlgorithm(int v1) {
        this.zzb = v1;
    }

    @Override  // com.google.android.gms.fido.fido2.api.common.Algorithm
    public int getAlgoValue() {
        return this.zzb;
    }
}

