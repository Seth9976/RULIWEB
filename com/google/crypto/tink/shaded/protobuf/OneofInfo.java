package com.google.crypto.tink.shaded.protobuf;

import java.lang.reflect.Field;

@CheckReturnValue
final class OneofInfo {
    private final Field caseField;
    private final int id;
    private final Field valueField;

    public OneofInfo(int v, Field field0, Field field1) {
        this.id = v;
        this.caseField = field0;
        this.valueField = field1;
    }

    public Field getCaseField() {
        return this.caseField;
    }

    public int getId() {
        return this.id;
    }

    public Field getValueField() {
        return this.valueField;
    }
}

