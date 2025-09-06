package com.squareup.moshi.internal;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader.Token;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import java.io.IOException;
import javax.annotation.Nullable;

public final class NonNullJsonAdapter extends JsonAdapter {
    private final JsonAdapter delegate;

    public NonNullJsonAdapter(JsonAdapter jsonAdapter0) {
        this.delegate = jsonAdapter0;
    }

    public JsonAdapter delegate() {
        return this.delegate;
    }

    @Override  // com.squareup.moshi.JsonAdapter
    @Nullable
    public Object fromJson(JsonReader jsonReader0) throws IOException {
        if(jsonReader0.peek() == Token.NULL) {
            throw new JsonDataException("Unexpected null at $");
        }
        return this.delegate.fromJson(jsonReader0);
    }

    @Override  // com.squareup.moshi.JsonAdapter
    public void toJson(JsonWriter jsonWriter0, @Nullable Object object0) throws IOException {
        if(object0 == null) {
            throw new JsonDataException("Unexpected null at $");
        }
        this.delegate.toJson(jsonWriter0, object0);
    }

    @Override
    public String toString() {
        return this.delegate + ".nonNull()";
    }
}

