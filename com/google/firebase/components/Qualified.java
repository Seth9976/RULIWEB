package com.google.firebase.components;

public final class Qualified {
    @interface Unqualified {
    }

    private final Class qualifier;
    private final Class type;

    public Qualified(Class class0, Class class1) {
        this.qualifier = class0;
        this.type = class1;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 != null && this.getClass() == object0.getClass() && this.type.equals(((Qualified)object0).type) ? this.qualifier.equals(((Qualified)object0).qualifier) : false;
    }

    @Override
    public int hashCode() {
        return this.type.hashCode() * 0x1F + this.qualifier.hashCode();
    }

    public static Qualified qualified(Class class0, Class class1) {
        return new Qualified(class0, class1);
    }

    @Override
    public String toString() {
        return this.qualifier == Unqualified.class ? this.type.getName() : "@" + this.qualifier.getName() + " " + this.type.getName();
    }

    public static Qualified unqualified(Class class0) {
        return new Qualified(Unqualified.class, class0);
    }
}

