package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzkg extends IOException {
    zzkg() {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.");
    }

    zzkg(String s, Throwable throwable0) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.: " + s, throwable0);
    }

    zzkg(Throwable throwable0) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.", throwable0);
    }
}

