package com.google.android.gms.fido.fido2.api.common;

public final class zzax extends Exception {
    public zzax(String s) {
        super(String.format("User verification requirement %s not supported", s));
    }
}

