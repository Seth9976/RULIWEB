package com.google.android.gms.fido.fido2.api.common;

public enum EC2Algorithm implements Algorithm {
    ED256(-260),
    ED512(0xFFFFFEFB),
    ED25519(-8),
    ES256(-7),
    ES384(-35),
    ES512(-36);

    private final int zzb;

    private EC2Algorithm(int v1) {
        this.zzb = v1;
    }

    @Override  // com.google.android.gms.fido.fido2.api.common.Algorithm
    public int getAlgoValue() {
        return this.zzb;
    }
}

