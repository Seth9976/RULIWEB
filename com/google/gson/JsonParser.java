package com.google.gson;

import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public final class JsonParser {
    @Deprecated
    public JsonElement parse(JsonReader jsonReader0) throws JsonIOException, JsonSyntaxException {
        return JsonParser.parseReader(jsonReader0);
    }

    @Deprecated
    public JsonElement parse(Reader reader0) throws JsonIOException, JsonSyntaxException {
        return JsonParser.parseReader(reader0);
    }

    @Deprecated
    public JsonElement parse(String s) throws JsonSyntaxException {
        return JsonParser.parseString(s);
    }

    public static JsonElement parseReader(JsonReader jsonReader0) throws JsonIOException, JsonSyntaxException {
        boolean z = jsonReader0.isLenient();
        jsonReader0.setLenient(true);
        try {
            return Streams.parse(jsonReader0);
        }
        catch(StackOverflowError stackOverflowError0) {
            throw new JsonParseException("Failed parsing JSON source: " + jsonReader0 + " to Json", stackOverflowError0);
        }
        catch(OutOfMemoryError outOfMemoryError0) {
            throw new JsonParseException("Failed parsing JSON source: " + jsonReader0 + " to Json", outOfMemoryError0);
        }
        finally {
            jsonReader0.setLenient(z);
        }
    }

    public static JsonElement parseReader(Reader reader0) throws JsonIOException, JsonSyntaxException {
        try {
            JsonReader jsonReader0 = new JsonReader(reader0);
            JsonElement jsonElement0 = JsonParser.parseReader(jsonReader0);
            if(jsonReader0.peek() != JsonToken.END_DOCUMENT) {
                throw new JsonSyntaxException("Did not consume the entire document.");
            }
            return jsonElement0;
        }
        catch(MalformedJsonException malformedJsonException0) {
            throw new JsonSyntaxException(malformedJsonException0);
        }
        catch(IOException iOException0) {
            throw new JsonIOException(iOException0);
        }
        catch(NumberFormatException numberFormatException0) {
            throw new JsonSyntaxException(numberFormatException0);
        }
    }

    public static JsonElement parseString(String s) throws JsonSyntaxException {
        return JsonParser.parseReader(new StringReader(s));
    }
}

