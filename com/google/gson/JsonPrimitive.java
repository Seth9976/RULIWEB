package com.google.gson;

import com.google.gson.internal..Gson.Preconditions;
import com.google.gson.internal.LazilyParsedNumber;
import java.math.BigDecimal;
import java.math.BigInteger;

public final class JsonPrimitive extends JsonElement {
    private final Object value;

    public JsonPrimitive(Boolean boolean0) {
        this.value = .Gson.Preconditions.checkNotNull(boolean0);
    }

    public JsonPrimitive(Character character0) {
        this.value = ((Character).Gson.Preconditions.checkNotNull(character0)).toString();
    }

    public JsonPrimitive(Number number0) {
        this.value = .Gson.Preconditions.checkNotNull(number0);
    }

    public JsonPrimitive(String s) {
        this.value = .Gson.Preconditions.checkNotNull(s);
    }

    @Override  // com.google.gson.JsonElement
    public JsonElement deepCopy() {
        return this;
    }

    public JsonPrimitive deepCopy() [...] // Inlined contents

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(object0 != null && this.getClass() == object0.getClass()) {
            if(this.value == null) {
                return ((JsonPrimitive)object0).value == null;
            }
            if(JsonPrimitive.isIntegral(this) && JsonPrimitive.isIntegral(((JsonPrimitive)object0))) {
                return this.getAsNumber().longValue() == ((JsonPrimitive)object0).getAsNumber().longValue();
            }
            Object object1 = this.value;
            if(object1 instanceof Number && ((JsonPrimitive)object0).value instanceof Number) {
                double f = this.getAsNumber().doubleValue();
                double f1 = ((JsonPrimitive)object0).getAsNumber().doubleValue();
                return f == f1 || Double.isNaN(f) && Double.isNaN(f1);
            }
            return object1.equals(((JsonPrimitive)object0).value);
        }
        return false;
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.gson.JsonElement
    public BigDecimal getAsBigDecimal() {
        return this.value instanceof BigDecimal ? ((BigDecimal)this.value) : new BigDecimal(this.value.toString());
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.gson.JsonElement
    public BigInteger getAsBigInteger() {
        return this.value instanceof BigInteger ? ((BigInteger)this.value) : new BigInteger(this.value.toString());
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.gson.JsonElement
    public boolean getAsBoolean() {
        return this.isBoolean() ? ((Boolean)this.value).booleanValue() : Boolean.parseBoolean(this.getAsString());
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.gson.JsonElement
    public byte getAsByte() {
        return this.isNumber() ? this.getAsNumber().byteValue() : Byte.parseByte(this.getAsString());
    }

    @Override  // com.google.gson.JsonElement
    public char getAsCharacter() {
        return this.getAsString().charAt(0);
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.gson.JsonElement
    public double getAsDouble() {
        return this.isNumber() ? this.getAsNumber().doubleValue() : Double.parseDouble(this.getAsString());
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.gson.JsonElement
    public float getAsFloat() {
        return this.isNumber() ? this.getAsNumber().floatValue() : Float.parseFloat(this.getAsString());
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.gson.JsonElement
    public int getAsInt() {
        return this.isNumber() ? this.getAsNumber().intValue() : Integer.parseInt(this.getAsString());
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.gson.JsonElement
    public long getAsLong() {
        return this.isNumber() ? this.getAsNumber().longValue() : Long.parseLong(this.getAsString());
    }

    @Override  // com.google.gson.JsonElement
    public Number getAsNumber() {
        Object object0 = this.value;
        return object0 instanceof String ? new LazilyParsedNumber(((String)this.value)) : ((Number)object0);
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.gson.JsonElement
    public short getAsShort() {
        return this.isNumber() ? this.getAsNumber().shortValue() : Short.parseShort(this.getAsString());
    }

    @Override  // com.google.gson.JsonElement
    public String getAsString() {
        if(this.isNumber()) {
            return this.getAsNumber().toString();
        }
        return this.isBoolean() ? ((Boolean)this.value).toString() : ((String)this.value);
    }

    @Override
    public int hashCode() {
        long v;
        if(this.value == null) {
            return 0x1F;
        }
        if(JsonPrimitive.isIntegral(this)) {
            v = this.getAsNumber().longValue();
            return (int)(v >>> 0x20 ^ v);
        }
        Object object0 = this.value;
        if(object0 instanceof Number) {
            v = Double.doubleToLongBits(this.getAsNumber().doubleValue());
            return (int)(v >>> 0x20 ^ v);
        }
        return object0.hashCode();
    }

    public boolean isBoolean() {
        return this.value instanceof Boolean;
    }

    // 去混淆评级： 中等(60)
    private static boolean isIntegral(JsonPrimitive jsonPrimitive0) {
        return jsonPrimitive0.value instanceof Number && (((Number)jsonPrimitive0.value) instanceof BigInteger || ((Number)jsonPrimitive0.value) instanceof Long || ((Number)jsonPrimitive0.value) instanceof Integer || ((Number)jsonPrimitive0.value) instanceof Short || ((Number)jsonPrimitive0.value) instanceof Byte);
    }

    public boolean isNumber() {
        return this.value instanceof Number;
    }

    public boolean isString() {
        return this.value instanceof String;
    }
}

