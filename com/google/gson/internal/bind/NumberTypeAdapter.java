package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.ToNumberPolicy;
import com.google.gson.ToNumberStrategy;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

public final class NumberTypeAdapter extends TypeAdapter {
    private static final TypeAdapterFactory LAZILY_PARSED_NUMBER_FACTORY;
    private final ToNumberStrategy toNumberStrategy;

    static {
        NumberTypeAdapter.LAZILY_PARSED_NUMBER_FACTORY = NumberTypeAdapter.newFactory(ToNumberPolicy.LAZILY_PARSED_NUMBER);
    }

    private NumberTypeAdapter(ToNumberStrategy toNumberStrategy0) {
        this.toNumberStrategy = toNumberStrategy0;
    }

    public static TypeAdapterFactory getFactory(ToNumberStrategy toNumberStrategy0) {
        return toNumberStrategy0 == ToNumberPolicy.LAZILY_PARSED_NUMBER ? NumberTypeAdapter.LAZILY_PARSED_NUMBER_FACTORY : NumberTypeAdapter.newFactory(toNumberStrategy0);
    }

    private static TypeAdapterFactory newFactory(ToNumberStrategy toNumberStrategy0) {
        return new TypeAdapterFactory() {
            final NumberTypeAdapter val$adapter;

            @Override  // com.google.gson.TypeAdapterFactory
            public TypeAdapter create(Gson gson0, TypeToken typeToken0) {
                return typeToken0.getRawType() == Number.class ? NumberTypeAdapter.this : null;
            }
        };
    }

    public Number read(JsonReader jsonReader0) throws IOException {
        JsonToken jsonToken0 = jsonReader0.peek();
        switch(com.google.gson.internal.bind.NumberTypeAdapter.2.$SwitchMap$com$google$gson$stream$JsonToken[jsonToken0.ordinal()]) {
            case 1: {
                jsonReader0.nextNull();
                return null;
            }
            case 2: 
            case 3: {
                return this.toNumberStrategy.readNumber(jsonReader0);
            }
            default: {
                throw new JsonSyntaxException("Expecting number, got: " + jsonToken0);
            }
        }
    }

    @Override  // com.google.gson.TypeAdapter
    public Object read(JsonReader jsonReader0) throws IOException {
        return this.read(jsonReader0);
    }

    public void write(JsonWriter jsonWriter0, Number number0) throws IOException {
        jsonWriter0.value(number0);
    }

    @Override  // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
        this.write(jsonWriter0, ((Number)object0));
    }

    class com.google.gson.internal.bind.NumberTypeAdapter.2 {
        static final int[] $SwitchMap$com$google$gson$stream$JsonToken;

        static {
            int[] arr_v = new int[JsonToken.values().length];
            com.google.gson.internal.bind.NumberTypeAdapter.2.$SwitchMap$com$google$gson$stream$JsonToken = arr_v;
            try {
                arr_v[JsonToken.NULL.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.gson.internal.bind.NumberTypeAdapter.2.$SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NUMBER.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.gson.internal.bind.NumberTypeAdapter.2.$SwitchMap$com$google$gson$stream$JsonToken[JsonToken.STRING.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

