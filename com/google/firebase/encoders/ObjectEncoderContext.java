package com.google.firebase.encoders;

import java.io.IOException;

public interface ObjectEncoderContext {
    ObjectEncoderContext add(FieldDescriptor arg1, double arg2) throws IOException;

    ObjectEncoderContext add(FieldDescriptor arg1, float arg2) throws IOException;

    ObjectEncoderContext add(FieldDescriptor arg1, int arg2) throws IOException;

    ObjectEncoderContext add(FieldDescriptor arg1, long arg2) throws IOException;

    ObjectEncoderContext add(FieldDescriptor arg1, Object arg2) throws IOException;

    ObjectEncoderContext add(FieldDescriptor arg1, boolean arg2) throws IOException;

    @Deprecated
    ObjectEncoderContext add(String arg1, double arg2) throws IOException;

    @Deprecated
    ObjectEncoderContext add(String arg1, int arg2) throws IOException;

    @Deprecated
    ObjectEncoderContext add(String arg1, long arg2) throws IOException;

    @Deprecated
    ObjectEncoderContext add(String arg1, Object arg2) throws IOException;

    @Deprecated
    ObjectEncoderContext add(String arg1, boolean arg2) throws IOException;

    ObjectEncoderContext inline(Object arg1) throws IOException;

    ObjectEncoderContext nested(FieldDescriptor arg1) throws IOException;

    ObjectEncoderContext nested(String arg1) throws IOException;
}

