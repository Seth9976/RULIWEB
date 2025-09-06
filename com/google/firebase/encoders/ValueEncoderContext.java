package com.google.firebase.encoders;

import java.io.IOException;

public interface ValueEncoderContext {
    ValueEncoderContext add(double arg1) throws IOException;

    ValueEncoderContext add(float arg1) throws IOException;

    ValueEncoderContext add(int arg1) throws IOException;

    ValueEncoderContext add(long arg1) throws IOException;

    ValueEncoderContext add(String arg1) throws IOException;

    ValueEncoderContext add(boolean arg1) throws IOException;

    ValueEncoderContext add(byte[] arg1) throws IOException;
}

