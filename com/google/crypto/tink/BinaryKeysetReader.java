package com.google.crypto.tink;

import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class BinaryKeysetReader implements KeysetReader {
    private final InputStream inputStream;

    private BinaryKeysetReader(InputStream inputStream0) {
        this.inputStream = inputStream0;
    }

    @Override  // com.google.crypto.tink.KeysetReader
    public Keyset read() throws IOException {
        try {
            ExtensionRegistryLite extensionRegistryLite0 = ExtensionRegistryLite.getEmptyRegistry();
            return Keyset.parseFrom(this.inputStream, extensionRegistryLite0);
        }
        finally {
            this.inputStream.close();
        }
    }

    @Override  // com.google.crypto.tink.KeysetReader
    public EncryptedKeyset readEncrypted() throws IOException {
        try {
            ExtensionRegistryLite extensionRegistryLite0 = ExtensionRegistryLite.getEmptyRegistry();
            return EncryptedKeyset.parseFrom(this.inputStream, extensionRegistryLite0);
        }
        finally {
            this.inputStream.close();
        }
    }

    public static KeysetReader withBytes(byte[] arr_b) {
        return new BinaryKeysetReader(new ByteArrayInputStream(arr_b));
    }

    @Deprecated
    public static KeysetReader withFile(File file0) throws IOException {
        return BinaryKeysetReader.withInputStream(new FileInputStream(file0));
    }

    public static KeysetReader withInputStream(InputStream inputStream0) {
        return new BinaryKeysetReader(inputStream0);
    }
}

