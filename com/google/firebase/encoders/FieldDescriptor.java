package com.google.firebase.encoders;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class FieldDescriptor {
    public static final class Builder {
        private final String name;
        private Map properties;

        Builder(String s) {
            this.properties = null;
            this.name = s;
        }

        public FieldDescriptor build() {
            return this.properties == null ? new FieldDescriptor(this.name, Collections.EMPTY_MAP, null) : new FieldDescriptor(this.name, Collections.unmodifiableMap(new HashMap(this.properties)), null);
        }

        public Builder withProperty(Annotation annotation0) {
            if(this.properties == null) {
                this.properties = new HashMap();
            }
            this.properties.put(annotation0.annotationType(), annotation0);
            return this;
        }
    }

    private final String name;
    private final Map properties;

    private FieldDescriptor(String s, Map map0) {
        this.name = s;
        this.properties = map0;
    }

    FieldDescriptor(String s, Map map0, com.google.firebase.encoders.FieldDescriptor.1 fieldDescriptor$10) {
        this(s, map0);
    }

    public static Builder builder(String s) {
        return new Builder(s);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof FieldDescriptor ? this.name.equals(((FieldDescriptor)object0).name) && this.properties.equals(((FieldDescriptor)object0).properties) : false;
    }

    public String getName() {
        return this.name;
    }

    public Annotation getProperty(Class class0) {
        return (Annotation)this.properties.get(class0);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() * 0x1F + this.properties.hashCode();
    }

    public static FieldDescriptor of(String s) {
        return new FieldDescriptor(s, Collections.EMPTY_MAP);
    }

    @Override
    public String toString() {
        return "FieldDescriptor{name=" + this.name + ", properties=" + this.properties.values() + "}";
    }

    class com.google.firebase.encoders.FieldDescriptor.1 {
    }

}

