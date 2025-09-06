package com.google.gson;

public final class JsonNull extends JsonElement {
    public static final JsonNull INSTANCE;

    static {
        JsonNull.INSTANCE = new JsonNull();
    }

    @Override  // com.google.gson.JsonElement
    public JsonElement deepCopy() {
        return this.deepCopy();
    }

    public JsonNull deepCopy() {
        return JsonNull.INSTANCE;
    }

    @Override
    public boolean equals(Object object0) {
        return this == object0 || object0 instanceof JsonNull;
    }

    @Override
    public int hashCode() {
        return JsonNull.class.hashCode();
    }
}

