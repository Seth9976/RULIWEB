package com.google.crypto.tink.shaded.protobuf;

interface MutabilityOracle {
    public static final MutabilityOracle IMMUTABLE;

    static {
        MutabilityOracle.IMMUTABLE = new MutabilityOracle() {
            @Override  // com.google.crypto.tink.shaded.protobuf.MutabilityOracle
            public void ensureMutable() {
                throw new UnsupportedOperationException();
            }
        };
    }

    void ensureMutable();
}

