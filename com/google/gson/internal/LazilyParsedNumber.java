package com.google.gson.internal;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamException;
import java.math.BigDecimal;

public final class LazilyParsedNumber extends Number {
    private final String value;

    public LazilyParsedNumber(String s) {
        this.value = s;
    }

    @Override
    public double doubleValue() {
        return Double.parseDouble(this.value);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(object0 instanceof LazilyParsedNumber) {
            String s = ((LazilyParsedNumber)object0).value;
            return this.value == s || this.value.equals(s);
        }
        return false;
    }

    @Override
    public float floatValue() {
        return Float.parseFloat(this.value);
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

    @Override
    public int intValue() {
        try {
            return Integer.parseInt(this.value);
        }
        catch(NumberFormatException unused_ex) {
            try {
                return (int)Long.parseLong(this.value);
            }
            catch(NumberFormatException unused_ex) {
                return new BigDecimal(this.value).intValue();
            }
        }
    }

    @Override
    public long longValue() {
        try {
            return Long.parseLong(this.value);
        }
        catch(NumberFormatException unused_ex) {
            return new BigDecimal(this.value).longValue();
        }
    }

    private void readObject(ObjectInputStream objectInputStream0) throws IOException {
        throw new InvalidObjectException("Deserialization is unsupported");
    }

    @Override
    public String toString() {
        return this.value;
    }

    private Object writeReplace() throws ObjectStreamException {
        return new BigDecimal(this.value);
    }
}

