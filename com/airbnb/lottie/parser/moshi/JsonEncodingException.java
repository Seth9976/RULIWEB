package com.airbnb.lottie.parser.moshi;

import java.io.IOException;

final class JsonEncodingException extends IOException {
    JsonEncodingException(String s) {
        super(s);
    }
}

