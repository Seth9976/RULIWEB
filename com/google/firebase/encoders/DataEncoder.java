package com.google.firebase.encoders;

import java.io.IOException;
import java.io.Writer;

public interface DataEncoder {
    String encode(Object arg1);

    void encode(Object arg1, Writer arg2) throws IOException;
}

