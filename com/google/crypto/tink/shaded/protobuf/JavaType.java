package com.google.crypto.tink.shaded.protobuf;

public enum JavaType {
    VOID(Void.class, Void.class, null),
    INT(Integer.TYPE, Integer.class, 0),
    LONG(Long.TYPE, Long.class, 0L),
    FLOAT(Float.TYPE, Float.class, 0.0f),
    DOUBLE(Double.TYPE, Double.class, 0.0),
    BOOLEAN(Boolean.TYPE, Boolean.class, Boolean.FALSE),
    STRING(String.class, String.class, ""),
    BYTE_STRING(ByteString.class, ByteString.class, ByteString.EMPTY),
    ENUM(Integer.TYPE, Integer.class, null),
    MESSAGE(Object.class, Object.class, null);

    private final Class boxedType;
    private final Object defaultDefault;
    private final Class type;

    private JavaType(Class class0, Class class1, Object object0) {
        this.type = class0;
        this.boxedType = class1;
        this.defaultDefault = object0;
    }

    public Class getBoxedType() {
        return this.boxedType;
    }

    public Object getDefaultDefault() {
        return this.defaultDefault;
    }

    public Class getType() {
        return this.type;
    }

    public boolean isValidType(Class class0) {
        return this.type.isAssignableFrom(class0);
    }
}

