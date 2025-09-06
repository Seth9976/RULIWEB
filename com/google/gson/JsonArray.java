package com.google.gson;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class JsonArray extends JsonElement implements Iterable {
    private final List elements;

    public JsonArray() {
        this.elements = new ArrayList();
    }

    public JsonArray(int v) {
        this.elements = new ArrayList(v);
    }

    public void add(JsonElement jsonElement0) {
        if(jsonElement0 == null) {
            jsonElement0 = JsonNull.INSTANCE;
        }
        this.elements.add(jsonElement0);
    }

    public void add(Boolean boolean0) {
        List list0 = this.elements;
        JsonNull jsonNull0 = boolean0 == null ? JsonNull.INSTANCE : new JsonPrimitive(boolean0);
        list0.add(jsonNull0);
    }

    public void add(Character character0) {
        List list0 = this.elements;
        JsonNull jsonNull0 = character0 == null ? JsonNull.INSTANCE : new JsonPrimitive(character0);
        list0.add(jsonNull0);
    }

    public void add(Number number0) {
        List list0 = this.elements;
        JsonNull jsonNull0 = number0 == null ? JsonNull.INSTANCE : new JsonPrimitive(number0);
        list0.add(jsonNull0);
    }

    public void add(String s) {
        List list0 = this.elements;
        JsonNull jsonNull0 = s == null ? JsonNull.INSTANCE : new JsonPrimitive(s);
        list0.add(jsonNull0);
    }

    public void addAll(JsonArray jsonArray0) {
        this.elements.addAll(jsonArray0.elements);
    }

    public boolean contains(JsonElement jsonElement0) {
        return this.elements.contains(jsonElement0);
    }

    public JsonArray deepCopy() {
        if(!this.elements.isEmpty()) {
            JsonArray jsonArray0 = new JsonArray(this.elements.size());
            for(Object object0: this.elements) {
                jsonArray0.add(((JsonElement)object0).deepCopy());
            }
            return jsonArray0;
        }
        return new JsonArray();
    }

    @Override  // com.google.gson.JsonElement
    public JsonElement deepCopy() {
        return this.deepCopy();
    }

    // 去混淆评级： 低(20)
    @Override
    public boolean equals(Object object0) {
        return object0 == this || object0 instanceof JsonArray && ((JsonArray)object0).elements.equals(this.elements);
    }

    public JsonElement get(int v) {
        return (JsonElement)this.elements.get(v);
    }

    @Override  // com.google.gson.JsonElement
    public BigDecimal getAsBigDecimal() {
        if(this.elements.size() != 1) {
            throw new IllegalStateException();
        }
        return ((JsonElement)this.elements.get(0)).getAsBigDecimal();
    }

    @Override  // com.google.gson.JsonElement
    public BigInteger getAsBigInteger() {
        if(this.elements.size() != 1) {
            throw new IllegalStateException();
        }
        return ((JsonElement)this.elements.get(0)).getAsBigInteger();
    }

    @Override  // com.google.gson.JsonElement
    public boolean getAsBoolean() {
        if(this.elements.size() != 1) {
            throw new IllegalStateException();
        }
        return ((JsonElement)this.elements.get(0)).getAsBoolean();
    }

    @Override  // com.google.gson.JsonElement
    public byte getAsByte() {
        if(this.elements.size() != 1) {
            throw new IllegalStateException();
        }
        return ((JsonElement)this.elements.get(0)).getAsByte();
    }

    @Override  // com.google.gson.JsonElement
    public char getAsCharacter() {
        if(this.elements.size() != 1) {
            throw new IllegalStateException();
        }
        return ((JsonElement)this.elements.get(0)).getAsCharacter();
    }

    @Override  // com.google.gson.JsonElement
    public double getAsDouble() {
        if(this.elements.size() != 1) {
            throw new IllegalStateException();
        }
        return ((JsonElement)this.elements.get(0)).getAsDouble();
    }

    @Override  // com.google.gson.JsonElement
    public float getAsFloat() {
        if(this.elements.size() != 1) {
            throw new IllegalStateException();
        }
        return ((JsonElement)this.elements.get(0)).getAsFloat();
    }

    @Override  // com.google.gson.JsonElement
    public int getAsInt() {
        if(this.elements.size() != 1) {
            throw new IllegalStateException();
        }
        return ((JsonElement)this.elements.get(0)).getAsInt();
    }

    @Override  // com.google.gson.JsonElement
    public long getAsLong() {
        if(this.elements.size() != 1) {
            throw new IllegalStateException();
        }
        return ((JsonElement)this.elements.get(0)).getAsLong();
    }

    @Override  // com.google.gson.JsonElement
    public Number getAsNumber() {
        if(this.elements.size() != 1) {
            throw new IllegalStateException();
        }
        return ((JsonElement)this.elements.get(0)).getAsNumber();
    }

    @Override  // com.google.gson.JsonElement
    public short getAsShort() {
        if(this.elements.size() != 1) {
            throw new IllegalStateException();
        }
        return ((JsonElement)this.elements.get(0)).getAsShort();
    }

    @Override  // com.google.gson.JsonElement
    public String getAsString() {
        if(this.elements.size() != 1) {
            throw new IllegalStateException();
        }
        return ((JsonElement)this.elements.get(0)).getAsString();
    }

    @Override
    public int hashCode() {
        return this.elements.hashCode();
    }

    public boolean isEmpty() {
        return this.elements.isEmpty();
    }

    @Override
    public Iterator iterator() {
        return this.elements.iterator();
    }

    public JsonElement remove(int v) {
        return (JsonElement)this.elements.remove(v);
    }

    public boolean remove(JsonElement jsonElement0) {
        return this.elements.remove(jsonElement0);
    }

    public JsonElement set(int v, JsonElement jsonElement0) {
        return (JsonElement)this.elements.set(v, jsonElement0);
    }

    public int size() {
        return this.elements.size();
    }
}

