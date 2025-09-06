package com.google.gson;

import com.google.gson.internal.LinkedTreeMap;
import java.util.Map.Entry;
import java.util.Set;

public final class JsonObject extends JsonElement {
    private final LinkedTreeMap members;

    public JsonObject() {
        this.members = new LinkedTreeMap();
    }

    public void add(String s, JsonElement jsonElement0) {
        LinkedTreeMap linkedTreeMap0 = this.members;
        if(jsonElement0 == null) {
            jsonElement0 = JsonNull.INSTANCE;
        }
        linkedTreeMap0.put(s, jsonElement0);
    }

    public void addProperty(String s, Boolean boolean0) {
        JsonNull jsonNull0 = boolean0 == null ? JsonNull.INSTANCE : new JsonPrimitive(boolean0);
        this.add(s, jsonNull0);
    }

    public void addProperty(String s, Character character0) {
        JsonNull jsonNull0 = character0 == null ? JsonNull.INSTANCE : new JsonPrimitive(character0);
        this.add(s, jsonNull0);
    }

    public void addProperty(String s, Number number0) {
        JsonNull jsonNull0 = number0 == null ? JsonNull.INSTANCE : new JsonPrimitive(number0);
        this.add(s, jsonNull0);
    }

    public void addProperty(String s, String s1) {
        JsonNull jsonNull0 = s1 == null ? JsonNull.INSTANCE : new JsonPrimitive(s1);
        this.add(s, jsonNull0);
    }

    @Override  // com.google.gson.JsonElement
    public JsonElement deepCopy() {
        return this.deepCopy();
    }

    public JsonObject deepCopy() {
        JsonObject jsonObject0 = new JsonObject();
        for(Object object0: this.members.entrySet()) {
            jsonObject0.add(((String)((Map.Entry)object0).getKey()), ((JsonElement)((Map.Entry)object0).getValue()).deepCopy());
        }
        return jsonObject0;
    }

    public Set entrySet() {
        return this.members.entrySet();
    }

    // 去混淆评级： 低(20)
    @Override
    public boolean equals(Object object0) {
        return object0 == this || object0 instanceof JsonObject && ((JsonObject)object0).members.equals(this.members);
    }

    public JsonElement get(String s) {
        return (JsonElement)this.members.get(s);
    }

    public JsonArray getAsJsonArray(String s) {
        return (JsonArray)this.members.get(s);
    }

    public JsonObject getAsJsonObject(String s) {
        return (JsonObject)this.members.get(s);
    }

    public JsonPrimitive getAsJsonPrimitive(String s) {
        return (JsonPrimitive)this.members.get(s);
    }

    public boolean has(String s) {
        return this.members.containsKey(s);
    }

    @Override
    public int hashCode() {
        return this.members.hashCode();
    }

    public Set keySet() {
        return this.members.keySet();
    }

    public JsonElement remove(String s) {
        return (JsonElement)this.members.remove(s);
    }

    public int size() {
        return this.members.size();
    }
}

