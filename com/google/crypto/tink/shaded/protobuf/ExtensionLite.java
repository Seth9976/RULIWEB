package com.google.crypto.tink.shaded.protobuf;

public abstract class ExtensionLite {
    public abstract Object getDefaultValue();

    public abstract FieldType getLiteType();

    public abstract MessageLite getMessageDefaultInstance();

    public abstract int getNumber();

    boolean isLite() [...] // Inlined contents

    public abstract boolean isRepeated();
}

