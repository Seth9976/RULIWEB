package com.google.crypto.tink;

import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.Keyset;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public final class BinaryKeysetWriter implements KeysetWriter {
    private final OutputStream outputStream;

    private BinaryKeysetWriter(OutputStream outputStream0) {
        this.outputStream = outputStream0;
    }

    @Deprecated
    public static KeysetWriter withFile(File file0) throws IOException {
        return BinaryKeysetWriter.withOutputStream(new FileOutputStream(file0));
    }

    public static KeysetWriter withOutputStream(OutputStream outputStream0) {
        return new BinaryKeysetWriter(outputStream0);
    }

    @Override  // com.google.crypto.tink.KeysetWriter
    public void write(EncryptedKeyset encryptedKeyset0) throws IOException {
        try {
            encryptedKeyset0.writeTo(this.outputStream);
        }
        finally {
            this.outputStream.close();
        }
    }

    @Override  // com.google.crypto.tink.KeysetWriter
    public void write(Keyset keyset0) throws IOException {
        try {
            keyset0.writeTo(this.outputStream);
        }
        finally {
            this.outputStream.close();
        }
    }
}

