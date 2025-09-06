package com.airbnb.lottie.parser;

import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

interface ValueParser {
    Object parse(JsonReader arg1, float arg2) throws IOException;
}

