package com.google.firebase.encoders;

import java.io.IOException;

interface Encoder {
    void encode(Object arg1, Object arg2) throws IOException;
}

