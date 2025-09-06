package com.google.gson;

import com.google.gson.internal..Gson.Preconditions;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;

public final class FieldAttributes {
    private final Field field;

    public FieldAttributes(Field field0) {
        .Gson.Preconditions.checkNotNull(field0);
        this.field = field0;
    }

    Object get(Object object0) throws IllegalAccessException {
        return this.field.get(object0);
    }

    public Annotation getAnnotation(Class class0) {
        return this.field.getAnnotation(class0);
    }

    public Collection getAnnotations() {
        return Arrays.asList(this.field.getAnnotations());
    }

    public Class getDeclaredClass() {
        return this.field.getType();
    }

    public Type getDeclaredType() {
        return this.field.getGenericType();
    }

    public Class getDeclaringClass() {
        return this.field.getDeclaringClass();
    }

    public String getName() {
        return this.field.getName();
    }

    public boolean hasModifier(int v) {
        return (v & this.field.getModifiers()) != 0;
    }

    boolean isSynthetic() {
        return this.field.isSynthetic();
    }
}

