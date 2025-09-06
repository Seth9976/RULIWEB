package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.nio.ByteBuffer;

public abstract class ByteOutput {
    public abstract void write(byte arg1) throws IOException;

    public abstract void write(ByteBuffer arg1) throws IOException;

    public abstract void write(byte[] arg1, int arg2, int arg3) throws IOException;

    public abstract void writeLazy(ByteBuffer arg1) throws IOException;

    public abstract void writeLazy(byte[] arg1, int arg2, int arg3) throws IOException;
}

