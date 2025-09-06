package com.google.android.gms.common.images;

public final class Size {
    private final int zaa;
    private final int zab;

    public Size(int v, int v1) {
        this.zaa = v;
        this.zab = v1;
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == null) {
            return false;
        }
        return this == object0 ? true : object0 instanceof Size && this.zaa == ((Size)object0).zaa && this.zab == ((Size)object0).zab;
    }

    public int getHeight() {
        return this.zab;
    }

    public int getWidth() {
        return this.zaa;
    }

    @Override
    public int hashCode() {
        return (this.zaa >>> 16 | this.zaa << 16) ^ this.zab;
    }

    public static Size parseSize(String s) throws NumberFormatException {
        if(s == null) {
            throw new IllegalArgumentException("string must not be null");
        }
        int v = s.indexOf(42);
        if(v < 0) {
            v = s.indexOf(120);
        }
        if(v >= 0) {
            try {
                return new Size(Integer.parseInt(s.substring(0, v)), Integer.parseInt(s.substring(v + 1)));
            }
            catch(NumberFormatException unused_ex) {
                throw Size.zaa(s);
            }
        }
        throw Size.zaa(s);
    }

    @Override
    public String toString() {
        return this.zaa + "x" + this.zab;
    }

    private static NumberFormatException zaa(String s) {
        throw new NumberFormatException("Invalid Size: \"" + s + "\"");
    }
}

