package com.google.gson;

import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.MalformedJsonException;
import java.io.IOException;
import java.math.BigDecimal;

// 部分失败：枚举糖化
// 枚举按原样呈现，而不是糖化为Java 5枚举。
public abstract class ToNumberPolicy extends Enum implements ToNumberStrategy {
    private static final ToNumberPolicy[] $VALUES;
    public static final enum ToNumberPolicy BIG_DECIMAL;
    public static final enum ToNumberPolicy DOUBLE;
    public static final enum ToNumberPolicy LAZILY_PARSED_NUMBER;
    public static final enum ToNumberPolicy LONG_OR_DOUBLE;

    static {
        com.google.gson.ToNumberPolicy.1 toNumberPolicy$10 = new ToNumberPolicy("DOUBLE", 0) {
            public Double readNumber(JsonReader jsonReader0) throws IOException {
                return jsonReader0.nextDouble();
            }

            @Override  // com.google.gson.ToNumberStrategy
            public Number readNumber(JsonReader jsonReader0) throws IOException {
                return this.readNumber(jsonReader0);
            }
        };
        ToNumberPolicy.DOUBLE = toNumberPolicy$10;
        com.google.gson.ToNumberPolicy.2 toNumberPolicy$20 = new ToNumberPolicy("LAZILY_PARSED_NUMBER", 1) {
            @Override  // com.google.gson.ToNumberStrategy
            public Number readNumber(JsonReader jsonReader0) throws IOException {
                return new LazilyParsedNumber(jsonReader0.nextString());
            }
        };
        ToNumberPolicy.LAZILY_PARSED_NUMBER = toNumberPolicy$20;
        com.google.gson.ToNumberPolicy.3 toNumberPolicy$30 = new ToNumberPolicy("LONG_OR_DOUBLE", 2) {
            @Override  // com.google.gson.ToNumberStrategy
            public Number readNumber(JsonReader jsonReader0) throws IOException, JsonParseException {
                String s = jsonReader0.nextString();
                try {
                    return Long.parseLong(s);
                }
                catch(NumberFormatException unused_ex) {
                    try {
                        Number number0 = Double.valueOf(s);
                        if((((Double)number0).isInfinite() || ((Double)number0).isNaN()) && !jsonReader0.isLenient()) {
                            throw new MalformedJsonException("JSON forbids NaN and infinities: " + number0 + "; at path " + jsonReader0.getPath());
                        }
                        return number0;
                    }
                    catch(NumberFormatException numberFormatException0) {
                        throw new JsonParseException("Cannot parse " + s + "; at path " + jsonReader0.getPath(), numberFormatException0);
                    }
                }
            }
        };
        ToNumberPolicy.LONG_OR_DOUBLE = toNumberPolicy$30;
        com.google.gson.ToNumberPolicy.4 toNumberPolicy$40 = new ToNumberPolicy("BIG_DECIMAL", 3) {
            @Override  // com.google.gson.ToNumberStrategy
            public Number readNumber(JsonReader jsonReader0) throws IOException {
                return this.readNumber(jsonReader0);
            }

            public BigDecimal readNumber(JsonReader jsonReader0) throws IOException {
                String s = jsonReader0.nextString();
                try {
                    return new BigDecimal(s);
                }
                catch(NumberFormatException numberFormatException0) {
                    throw new JsonParseException("Cannot parse " + s + "; at path " + jsonReader0.getPath(), numberFormatException0);
                }
            }
        };
        ToNumberPolicy.BIG_DECIMAL = toNumberPolicy$40;
        ToNumberPolicy.$VALUES = new ToNumberPolicy[]{toNumberPolicy$10, toNumberPolicy$20, toNumberPolicy$30, toNumberPolicy$40};
    }

    private ToNumberPolicy(String s, int v) {
        super(s, v);
    }

    ToNumberPolicy(String s, int v, com.google.gson.ToNumberPolicy.1 toNumberPolicy$10) {
        this(s, v);
    }

    public static ToNumberPolicy valueOf(String s) {
        return (ToNumberPolicy)Enum.valueOf(ToNumberPolicy.class, s);
    }

    public static ToNumberPolicy[] values() {
        return (ToNumberPolicy[])ToNumberPolicy.$VALUES.clone();
    }
}

