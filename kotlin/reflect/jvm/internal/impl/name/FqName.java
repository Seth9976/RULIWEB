package kotlin.reflect.jvm.internal.impl.name;

import java.util.List;

public final class FqName {
    public static final FqName ROOT;
    private final FqNameUnsafe fqName;
    private transient FqName parent;

    private static void $$$reportNull$$$0(int v) {
        IllegalArgumentException illegalArgumentException0;
        int v1;
        String s;
        switch(v) {
            case 4: 
            case 5: 
            case 6: 
            case 7: 
            case 9: 
            case 10: 
            case 11: {
                s = "@NotNull method %s.%s must not return null";
                break;
            }
            default: {
                s = "Argument for @NotNull parameter \'%s\' of %s.%s must not be null";
            }
        }
        switch(v) {
            case 4: 
            case 5: 
            case 6: 
            case 7: 
            case 9: 
            case 10: 
            case 11: {
                v1 = 2;
                break;
            }
            default: {
                v1 = 3;
            }
        }
        Object[] arr_object = new Object[v1];
        switch(v) {
            case 1: 
            case 2: 
            case 3: {
                arr_object[0] = "fqName";
                break;
            }
            case 8: {
                arr_object[0] = "name";
                break;
            }
            case 4: 
            case 5: 
            case 6: 
            case 7: 
            case 9: 
            case 10: 
            case 11: {
                arr_object[0] = "kotlin/reflect/jvm/internal/impl/name/FqName";
                break;
            }
            case 12: {
                arr_object[0] = "segment";
                break;
            }
            case 13: {
                arr_object[0] = "shortName";
                break;
            }
            default: {
                arr_object[0] = "names";
            }
        }
        switch(v) {
            case 4: {
                arr_object[1] = "asString";
                break;
            }
            case 5: {
                arr_object[1] = "toUnsafe";
                break;
            }
            case 6: 
            case 7: {
                arr_object[1] = "parent";
                break;
            }
            case 9: {
                arr_object[1] = "shortName";
                break;
            }
            case 10: {
                arr_object[1] = "shortNameOrSpecial";
                break;
            }
            case 11: {
                arr_object[1] = "pathSegments";
                break;
            }
            default: {
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/name/FqName";
            }
        }
        switch(v) {
            case 1: 
            case 2: 
            case 3: {
                arr_object[2] = "<init>";
                break;
            }
            case 8: {
                arr_object[2] = "child";
                break;
            }
            case 4: 
            case 5: 
            case 6: 
            case 7: 
            case 9: 
            case 10: 
            case 11: {
                break;
            }
            case 12: {
                arr_object[2] = "startsWith";
                break;
            }
            case 13: {
                arr_object[2] = "topLevel";
                break;
            }
            default: {
                arr_object[2] = "fromSegments";
            }
        }
        String s1 = String.format(s, arr_object);
        switch(v) {
            case 4: 
            case 5: 
            case 6: 
            case 7: 
            case 9: 
            case 10: 
            case 11: {
                illegalArgumentException0 = new IllegalStateException(s1);
                break;
            }
            default: {
                illegalArgumentException0 = new IllegalArgumentException(s1);
            }
        }
        throw illegalArgumentException0;
    }

    static {
        FqName.ROOT = new FqName("");
    }

    public FqName(String s) {
        if(s == null) {
            FqName.$$$reportNull$$$0(1);
        }
        super();
        this.fqName = new FqNameUnsafe(s, this);
    }

    public FqName(FqNameUnsafe fqNameUnsafe0) {
        if(fqNameUnsafe0 == null) {
            FqName.$$$reportNull$$$0(2);
        }
        super();
        this.fqName = fqNameUnsafe0;
    }

    private FqName(FqNameUnsafe fqNameUnsafe0, FqName fqName0) {
        if(fqNameUnsafe0 == null) {
            FqName.$$$reportNull$$$0(3);
        }
        super();
        this.fqName = fqNameUnsafe0;
        this.parent = fqName0;
    }

    public String asString() {
        String s = this.fqName.asString();
        if(s == null) {
            FqName.$$$reportNull$$$0(4);
        }
        return s;
    }

    public FqName child(Name name0) {
        if(name0 == null) {
            FqName.$$$reportNull$$$0(8);
        }
        return new FqName(this.fqName.child(name0), this);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof FqName ? this.fqName.equals(((FqName)object0).fqName) : false;
    }

    @Override
    public int hashCode() {
        return this.fqName.hashCode();
    }

    public boolean isRoot() {
        return this.fqName.isRoot();
    }

    public FqName parent() {
        FqName fqName0 = this.parent;
        if(fqName0 != null) {
            if(fqName0 == null) {
                FqName.$$$reportNull$$$0(6);
            }
            return fqName0;
        }
        if(this.isRoot()) {
            throw new IllegalStateException("root");
        }
        FqName fqName1 = new FqName(this.fqName.parent());
        this.parent = fqName1;
        return fqName1;
    }

    public List pathSegments() {
        List list0 = this.fqName.pathSegments();
        if(list0 == null) {
            FqName.$$$reportNull$$$0(11);
        }
        return list0;
    }

    public Name shortName() {
        Name name0 = this.fqName.shortName();
        if(name0 == null) {
            FqName.$$$reportNull$$$0(9);
        }
        return name0;
    }

    public Name shortNameOrSpecial() {
        Name name0 = this.fqName.shortNameOrSpecial();
        if(name0 == null) {
            FqName.$$$reportNull$$$0(10);
        }
        return name0;
    }

    public boolean startsWith(Name name0) {
        if(name0 == null) {
            FqName.$$$reportNull$$$0(12);
        }
        return this.fqName.startsWith(name0);
    }

    @Override
    public String toString() {
        return this.fqName.toString();
    }

    public FqNameUnsafe toUnsafe() {
        FqNameUnsafe fqNameUnsafe0 = this.fqName;
        if(fqNameUnsafe0 == null) {
            FqName.$$$reportNull$$$0(5);
        }
        return fqNameUnsafe0;
    }

    public static FqName topLevel(Name name0) {
        if(name0 == null) {
            FqName.$$$reportNull$$$0(13);
        }
        return new FqName(FqNameUnsafe.topLevel(name0));
    }
}

