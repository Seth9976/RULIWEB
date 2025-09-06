package com.google.android.datatransport;

public final class Encoding {
    private final String name;

    private Encoding(String s) {
        if(s == null) {
            throw new NullPointerException("name is null");
        }
        this.name = s;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof Encoding ? this.name.equals(((Encoding)object0).name) : false;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() ^ 1000003;
    }

    public static Encoding of(String s) {
        return new Encoding(s);
    }

    @Override
    public String toString() {
        return "Encoding{name=\"" + this.name + "\"}";
    }
}

