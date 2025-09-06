package kotlin.reflect.jvm.internal.impl.name;

public final class Name implements Comparable {
    private final String name;
    private final boolean special;

    private static void $$$reportNull$$$0(int v) {
        Object[] arr_object = new Object[(v == 1 || v == 2 || v == 3 || v == 4 ? 2 : 3)];
        arr_object[0] = v == 1 || v == 2 || v == 3 || v == 4 ? "kotlin/reflect/jvm/internal/impl/name/Name" : "name";
        switch(v) {
            case 1: {
                arr_object[1] = "asString";
                break;
            }
            case 2: {
                arr_object[1] = "getIdentifier";
                break;
            }
            case 3: 
            case 4: {
                arr_object[1] = "asStringStripSpecialMarkers";
                break;
            }
            default: {
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/name/Name";
            }
        }
        switch(v) {
            case 1: 
            case 2: 
            case 3: 
            case 4: {
                break;
            }
            case 5: {
                arr_object[2] = "identifier";
                break;
            }
            case 6: {
                arr_object[2] = "isValidIdentifier";
                break;
            }
            case 7: {
                arr_object[2] = "special";
                break;
            }
            case 8: {
                arr_object[2] = "guessByFirstCharacter";
                break;
            }
            default: {
                arr_object[2] = "<init>";
            }
        }
        String s = String.format((v == 1 || v == 2 || v == 3 || v == 4 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null"), arr_object);
        IllegalStateException illegalStateException0 = v == 1 || v == 2 || v == 3 || v == 4 ? new IllegalStateException(s) : new IllegalArgumentException(s);
        throw illegalStateException0;
    }

    private Name(String s, boolean z) {
        if(s == null) {
            Name.$$$reportNull$$$0(0);
        }
        super();
        this.name = s;
        this.special = z;
    }

    public String asString() {
        String s = this.name;
        if(s == null) {
            Name.$$$reportNull$$$0(1);
        }
        return s;
    }

    @Override
    public int compareTo(Object object0) {
        return this.compareTo(((Name)object0));
    }

    public int compareTo(Name name0) {
        return this.name.compareTo(name0.name);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof Name)) {
            return false;
        }
        return this.special == ((Name)object0).special ? this.name.equals(((Name)object0).name) : false;
    }

    public String getIdentifier() {
        if(this.special) {
            throw new IllegalStateException("not identifier: " + this);
        }
        String s = this.asString();
        if(s == null) {
            Name.$$$reportNull$$$0(2);
        }
        return s;
    }

    public static Name guessByFirstCharacter(String s) {
        if(s == null) {
            Name.$$$reportNull$$$0(8);
        }
        return s.startsWith("<") ? Name.special(s) : Name.identifier(s);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() * 0x1F + this.special;
    }

    public static Name identifier(String s) {
        if(s == null) {
            Name.$$$reportNull$$$0(5);
        }
        return new Name(s, false);
    }

    public boolean isSpecial() {
        return this.special;
    }

    public static boolean isValidIdentifier(String s) {
        if(s == null) {
            Name.$$$reportNull$$$0(6);
        }
        if(!s.isEmpty() && !s.startsWith("<")) {
            int v = 0;
            while(v < s.length()) {
                if(s.charAt(v) != 46 && s.charAt(v) != 0x2F && s.charAt(v) != 92) {
                    ++v;
                    continue;
                }
                return false;
            }
            return true;
        }
        return false;
    }

    public static Name special(String s) {
        if(s == null) {
            Name.$$$reportNull$$$0(7);
        }
        if(!s.startsWith("<")) {
            throw new IllegalArgumentException("special name must start with \'<\': " + s);
        }
        return new Name(s, true);
    }

    @Override
    public String toString() {
        return this.name;
    }
}

