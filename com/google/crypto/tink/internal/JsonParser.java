package com.google.crypto.tink.internal;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayDeque;
import javax.annotation.Nullable;

public final class JsonParser {
    static final class JsonElementTypeAdapter extends TypeAdapter {
        private static final int RECURSION_LIMIT = 100;

        private JsonElementTypeAdapter() {
        }

        JsonElementTypeAdapter(com.google.crypto.tink.internal.JsonParser.1 jsonParser$10) {
        }

        public JsonElement read(JsonReader jsonReader0) throws IOException {
            String s;
            JsonToken jsonToken0 = jsonReader0.peek();
            JsonElement jsonElement0 = this.tryBeginNesting(jsonReader0, jsonToken0);
            if(jsonElement0 == null) {
                return this.readTerminal(jsonReader0, jsonToken0);
            }
            ArrayDeque arrayDeque0 = new ArrayDeque();
            while(true) {
                if(jsonReader0.hasNext()) {
                    if(jsonElement0 instanceof JsonObject) {
                        s = jsonReader0.nextName();
                        if(!JsonParser.isValidString(s)) {
                            throw new IOException("illegal characters in string");
                        }
                    }
                    else {
                        s = null;
                    }
                    JsonToken jsonToken1 = jsonReader0.peek();
                    JsonElement jsonElement1 = this.tryBeginNesting(jsonReader0, jsonToken1);
                    boolean z = jsonElement1 != null;
                    if(jsonElement1 == null) {
                        jsonElement1 = this.readTerminal(jsonReader0, jsonToken1);
                    }
                    if(jsonElement0 instanceof JsonArray) {
                        ((JsonArray)jsonElement0).add(jsonElement1);
                        goto label_21;
                    }
                    else {
                        if(((JsonObject)jsonElement0).has(s)) {
                            throw new IOException("duplicate key: " + s);
                        }
                        ((JsonObject)jsonElement0).add(s, jsonElement1);
                    label_21:
                        if(!z) {
                            continue;
                        }
                        arrayDeque0.addLast(jsonElement0);
                        if(arrayDeque0.size() > 100) {
                            throw new IOException("too many recursions");
                        }
                        jsonElement0 = jsonElement1;
                        continue;
                    }
                }
                if(jsonElement0 instanceof JsonArray) {
                    jsonReader0.endArray();
                }
                else {
                    jsonReader0.endObject();
                }
                if(arrayDeque0.isEmpty()) {
                    return jsonElement0;
                }
                jsonElement0 = (JsonElement)arrayDeque0.removeLast();
            }
        }

        @Override  // com.google.gson.TypeAdapter
        public Object read(JsonReader jsonReader0) throws IOException {
            return this.read(jsonReader0);
        }

        private JsonElement readTerminal(JsonReader jsonReader0, JsonToken jsonToken0) throws IOException {
            switch(com.google.crypto.tink.internal.JsonParser.1.$SwitchMap$com$google$gson$stream$JsonToken[jsonToken0.ordinal()]) {
                case 3: {
                    String s = jsonReader0.nextString();
                    if(!JsonParser.isValidString(s)) {
                        throw new IOException("illegal characters in string");
                    }
                    return new JsonPrimitive(s);
                }
                case 4: {
                    return new JsonPrimitive(new LazilyParsedNumber(jsonReader0.nextString()));
                }
                case 5: {
                    return new JsonPrimitive(Boolean.valueOf(jsonReader0.nextBoolean()));
                }
                case 6: {
                    jsonReader0.nextNull();
                    return JsonNull.INSTANCE;
                }
                default: {
                    throw new IllegalStateException("Unexpected token: " + jsonToken0);
                }
            }
        }

        @Nullable
        private JsonElement tryBeginNesting(JsonReader jsonReader0, JsonToken jsonToken0) throws IOException {
            switch(com.google.crypto.tink.internal.JsonParser.1.$SwitchMap$com$google$gson$stream$JsonToken[jsonToken0.ordinal()]) {
                case 1: {
                    jsonReader0.beginArray();
                    return new JsonArray();
                }
                case 2: {
                    jsonReader0.beginObject();
                    return new JsonObject();
                }
                default: {
                    return null;
                }
            }
        }

        public void write(JsonWriter jsonWriter0, JsonElement jsonElement0) {
            throw new UnsupportedOperationException("write is not supported");
        }

        @Override  // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
            this.write(jsonWriter0, ((JsonElement)object0));
        }
    }

    static final class LazilyParsedNumber extends Number {
        private final String value;

        public LazilyParsedNumber(String s) {
            this.value = s;
        }

        @Override
        public double doubleValue() {
            return Double.parseDouble(this.value);
        }

        @Override
        public boolean equals(Object object0) {
            if(this == object0) {
                return true;
            }
            return object0 instanceof LazilyParsedNumber ? this.value.equals(((LazilyParsedNumber)object0).value) : false;
        }

        @Override
        public float floatValue() {
            return Float.parseFloat(this.value);
        }

        @Override
        public int hashCode() {
            return this.value.hashCode();
        }

        @Override
        public int intValue() {
            try {
                return Integer.parseInt(this.value);
            }
            catch(NumberFormatException unused_ex) {
                try {
                    return (int)Long.parseLong(this.value);
                }
                catch(NumberFormatException unused_ex) {
                    return new BigDecimal(this.value).intValue();
                }
            }
        }

        @Override
        public long longValue() {
            try {
                return Long.parseLong(this.value);
            }
            catch(NumberFormatException unused_ex) {
                return new BigDecimal(this.value).longValue();
            }
        }

        private void readObject(ObjectInputStream objectInputStream0) throws NotSerializableException {
            throw new NotSerializableException("serialization is not supported");
        }

        @Override
        public String toString() {
            return this.value;
        }

        private Object writeReplace() throws NotSerializableException {
            throw new NotSerializableException("serialization is not supported");
        }
    }

    private static final JsonElementTypeAdapter JSON_ELEMENT;

    static {
        JsonParser.JSON_ELEMENT = new JsonElementTypeAdapter(null);
    }

    public static long getParsedNumberAsLongOrThrow(JsonElement jsonElement0) {
        if(!(jsonElement0.getAsNumber() instanceof LazilyParsedNumber)) {
            throw new IllegalArgumentException("does not contain a parsed number.");
        }
        return Long.parseLong(jsonElement0.getAsNumber().toString());
    }

    public static boolean isValidString(String s) {
        int v = s.length();
        int v1 = 0;
        while(true) {
            if(v1 == v) {
                return true;
            }
            int v2 = s.charAt(v1);
            if(Character.isSurrogate(((char)v2))) {
                if(!Character.isLowSurrogate(((char)v2)) && v1 + 1 != v && Character.isLowSurrogate(s.charAt(v1 + 1))) {
                    v1 += 2;
                    continue;
                }
                return false;
            }
            ++v1;
        }
    }

    public static JsonElement parse(String s) throws IOException {
        try {
            JsonReader jsonReader0 = new JsonReader(new StringReader(s));
            jsonReader0.setLenient(false);
            return JsonParser.JSON_ELEMENT.read(jsonReader0);
        }
        catch(NumberFormatException numberFormatException0) {
            throw new IOException(numberFormatException0);
        }
    }

    class com.google.crypto.tink.internal.JsonParser.1 {
        static final int[] $SwitchMap$com$google$gson$stream$JsonToken;

        static {
            int[] arr_v = new int[JsonToken.values().length];
            com.google.crypto.tink.internal.JsonParser.1.$SwitchMap$com$google$gson$stream$JsonToken = arr_v;
            try {
                arr_v[JsonToken.BEGIN_ARRAY.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.internal.JsonParser.1.$SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BEGIN_OBJECT.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.internal.JsonParser.1.$SwitchMap$com$google$gson$stream$JsonToken[JsonToken.STRING.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.internal.JsonParser.1.$SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NUMBER.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.internal.JsonParser.1.$SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BOOLEAN.ordinal()] = 5;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.internal.JsonParser.1.$SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NULL.ordinal()] = 6;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

