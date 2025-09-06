package com.google.crypto.tink.jwt;

import com.google.crypto.tink.internal.JsonParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.io.IOException;

final class JsonUtil {
    static boolean isValidString(String s) {
        return JsonParser.isValidString(s);
    }

    static JsonObject parseJson(String s) throws JwtInvalidException {
        try {
            return JsonParser.parse(s).getAsJsonObject();
        }
        catch(IllegalStateException | JsonParseException | IOException exception0) {
            throw new JwtInvalidException("invalid JSON: " + exception0);
        }
    }

    static JsonArray parseJsonArray(String s) throws JwtInvalidException {
        try {
            return JsonParser.parse(s).getAsJsonArray();
        }
        catch(IllegalStateException | JsonParseException | IOException exception0) {
            throw new JwtInvalidException("invalid JSON: " + exception0);
        }
    }
}

