package com.google.crypto.tink;

import com.google.crypto.tink.monitoring.MonitoringAnnotations;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;

public final class CleartextKeysetHandle {
    public static KeysetHandle fromKeyset(Keyset keyset0) throws GeneralSecurityException {
        return KeysetHandle.fromKeyset(keyset0);
    }

    public static Keyset getKeyset(KeysetHandle keysetHandle0) {
        return keysetHandle0.getKeyset();
    }

    @Deprecated
    public static final KeysetHandle parseFrom(byte[] arr_b) throws GeneralSecurityException {
        try {
            return KeysetHandle.fromKeyset(Keyset.parseFrom(arr_b, ExtensionRegistryLite.getEmptyRegistry()));
        }
        catch(InvalidProtocolBufferException unused_ex) {
            throw new GeneralSecurityException("invalid keyset");
        }
    }

    public static KeysetHandle read(KeysetReader keysetReader0) throws GeneralSecurityException, IOException {
        return KeysetHandle.fromKeyset(keysetReader0.read());
    }

    public static KeysetHandle read(KeysetReader keysetReader0, Map map0) throws GeneralSecurityException, IOException {
        return KeysetHandle.fromKeysetAndAnnotations(keysetReader0.read(), MonitoringAnnotations.newBuilder().addAll(map0).build());
    }

    public static void write(KeysetHandle keysetHandle0, KeysetWriter keysetWriter0) throws IOException {
        keysetWriter0.write(keysetHandle0.getKeyset());
    }
}

