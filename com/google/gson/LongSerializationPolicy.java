package com.google.gson;

// 部分失败：枚举糖化
// 枚举按原样呈现，而不是糖化为Java 5枚举。
public abstract class LongSerializationPolicy extends Enum {
    private static final LongSerializationPolicy[] $VALUES;
    public static final enum LongSerializationPolicy DEFAULT;
    public static final enum LongSerializationPolicy STRING;

    static {
        com.google.gson.LongSerializationPolicy.1 longSerializationPolicy$10 = new LongSerializationPolicy("DEFAULT", 0) {
            @Override  // com.google.gson.LongSerializationPolicy
            public JsonElement serialize(Long long0) {
                return long0 == null ? JsonNull.INSTANCE : new JsonPrimitive(long0);
            }
        };
        LongSerializationPolicy.DEFAULT = longSerializationPolicy$10;
        com.google.gson.LongSerializationPolicy.2 longSerializationPolicy$20 = new LongSerializationPolicy("STRING", 1) {
            @Override  // com.google.gson.LongSerializationPolicy
            public JsonElement serialize(Long long0) {
                return long0 == null ? JsonNull.INSTANCE : new JsonPrimitive(long0.toString());
            }
        };
        LongSerializationPolicy.STRING = longSerializationPolicy$20;
        LongSerializationPolicy.$VALUES = new LongSerializationPolicy[]{longSerializationPolicy$10, longSerializationPolicy$20};
    }

    private LongSerializationPolicy(String s, int v) {
        super(s, v);
    }

    LongSerializationPolicy(String s, int v, com.google.gson.LongSerializationPolicy.1 longSerializationPolicy$10) {
        this(s, v);
    }

    public abstract JsonElement serialize(Long arg1);

    public static LongSerializationPolicy valueOf(String s) {
        return (LongSerializationPolicy)Enum.valueOf(LongSerializationPolicy.class, s);
    }

    public static LongSerializationPolicy[] values() {
        return (LongSerializationPolicy[])LongSerializationPolicy.$VALUES.clone();
    }
}

