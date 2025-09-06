package kotlin.reflect.jvm.internal.impl.name;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;

public final class FqNameUnsafe {
    private static final Name ROOT_NAME;
    private static final Pattern SPLIT_BY_DOTS;
    private static final Function1 STRING_TO_NAME;
    private final String fqName;
    private transient FqNameUnsafe parent;
    private transient FqName safe;
    private transient Name shortName;

    private static void $$$reportNull$$$0(int v) {
        IllegalArgumentException illegalArgumentException0;
        int v1;
        String s;
        switch(v) {
            case 4: 
            case 5: 
            case 6: 
            case 7: 
            case 8: 
            case 10: 
            case 11: 
            case 12: 
            case 13: 
            case 14: 
            case 17: {
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
            case 8: 
            case 10: 
            case 11: 
            case 12: 
            case 13: 
            case 14: 
            case 17: {
                v1 = 2;
                break;
            }
            default: {
                v1 = 3;
            }
        }
        Object[] arr_object = new Object[v1];
        if(v == 1) {
            arr_object[0] = "safe";
        }
        else {
            switch(v) {
                case 9: {
                    arr_object[0] = "name";
                    break;
                }
                case 15: {
                    arr_object[0] = "segment";
                    break;
                }
                case 16: {
                    arr_object[0] = "shortName";
                    break;
                }
                case 4: 
                case 5: 
                case 6: 
                case 7: 
                case 8: 
                case 10: 
                case 11: 
                case 12: 
                case 13: 
                case 14: 
                case 17: {
                    arr_object[0] = "kotlin/reflect/jvm/internal/impl/name/FqNameUnsafe";
                    break;
                }
                default: {
                    arr_object[0] = "fqName";
                }
            }
        }
        switch(v) {
            case 4: {
                arr_object[1] = "asString";
                break;
            }
            case 5: 
            case 6: {
                arr_object[1] = "toSafe";
                break;
            }
            case 7: 
            case 8: {
                arr_object[1] = "parent";
                break;
            }
            case 10: 
            case 11: {
                arr_object[1] = "shortName";
                break;
            }
            case 12: 
            case 13: {
                arr_object[1] = "shortNameOrSpecial";
                break;
            }
            case 14: {
                arr_object[1] = "pathSegments";
                break;
            }
            case 17: {
                arr_object[1] = "toString";
                break;
            }
            default: {
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/name/FqNameUnsafe";
            }
        }
        switch(v) {
            case 9: {
                arr_object[2] = "child";
                break;
            }
            case 15: {
                arr_object[2] = "startsWith";
                break;
            }
            case 16: {
                arr_object[2] = "topLevel";
                break;
            }
            case 4: 
            case 5: 
            case 6: 
            case 7: 
            case 8: 
            case 10: 
            case 11: 
            case 12: 
            case 13: 
            case 14: 
            case 17: {
                break;
            }
            default: {
                arr_object[2] = "<init>";
            }
        }
        String s1 = String.format(s, arr_object);
        switch(v) {
            case 4: 
            case 5: 
            case 6: 
            case 7: 
            case 8: 
            case 10: 
            case 11: 
            case 12: 
            case 13: 
            case 14: 
            case 17: {
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
        FqNameUnsafe.ROOT_NAME = Name.special("<root>");
        FqNameUnsafe.SPLIT_BY_DOTS = Pattern.compile("\\.");
        FqNameUnsafe.STRING_TO_NAME = new Function1() {
            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((String)object0));
            }

            public Name invoke(String s) {
                return Name.guessByFirstCharacter(s);
            }
        };
    }

    public FqNameUnsafe(String s) {
        if(s == null) {
            FqNameUnsafe.$$$reportNull$$$0(2);
        }
        super();
        this.fqName = s;
    }

    FqNameUnsafe(String s, FqName fqName0) {
        if(s == null) {
            FqNameUnsafe.$$$reportNull$$$0(0);
        }
        if(fqName0 == null) {
            FqNameUnsafe.$$$reportNull$$$0(1);
        }
        super();
        this.fqName = s;
        this.safe = fqName0;
    }

    private FqNameUnsafe(String s, FqNameUnsafe fqNameUnsafe0, Name name0) {
        if(s == null) {
            FqNameUnsafe.$$$reportNull$$$0(3);
        }
        super();
        this.fqName = s;
        this.parent = fqNameUnsafe0;
        this.shortName = name0;
    }

    public String asString() {
        String s = this.fqName;
        if(s == null) {
            FqNameUnsafe.$$$reportNull$$$0(4);
        }
        return s;
    }

    public FqNameUnsafe child(Name name0) {
        if(name0 == null) {
            FqNameUnsafe.$$$reportNull$$$0(9);
        }
        return this.isRoot() ? new FqNameUnsafe(name0.asString(), this, name0) : new FqNameUnsafe(this.fqName + "." + name0.asString(), this, name0);
    }

    private void compute() {
        int v = this.fqName.lastIndexOf(46);
        if(v >= 0) {
            this.shortName = Name.guessByFirstCharacter(this.fqName.substring(v + 1));
            this.parent = new FqNameUnsafe(this.fqName.substring(0, v));
            return;
        }
        this.shortName = Name.guessByFirstCharacter(this.fqName);
        this.parent = FqName.ROOT.toUnsafe();
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof FqNameUnsafe ? this.fqName.equals(((FqNameUnsafe)object0).fqName) : false;
    }

    @Override
    public int hashCode() {
        return this.fqName.hashCode();
    }

    public boolean isRoot() {
        return this.fqName.isEmpty();
    }

    public boolean isSafe() {
        return this.safe != null || this.asString().indexOf(60) < 0;
    }

    public FqNameUnsafe parent() {
        FqNameUnsafe fqNameUnsafe0 = this.parent;
        if(fqNameUnsafe0 != null) {
            if(fqNameUnsafe0 == null) {
                FqNameUnsafe.$$$reportNull$$$0(7);
            }
            return fqNameUnsafe0;
        }
        if(this.isRoot()) {
            throw new IllegalStateException("root");
        }
        this.compute();
        FqNameUnsafe fqNameUnsafe1 = this.parent;
        if(fqNameUnsafe1 == null) {
            FqNameUnsafe.$$$reportNull$$$0(8);
        }
        return fqNameUnsafe1;
    }

    public List pathSegments() {
        List list0 = this.isRoot() ? Collections.EMPTY_LIST : ArraysKt.map(FqNameUnsafe.SPLIT_BY_DOTS.split(this.fqName), FqNameUnsafe.STRING_TO_NAME);
        if(list0 == null) {
            FqNameUnsafe.$$$reportNull$$$0(14);
        }
        return list0;
    }

    public Name shortName() {
        Name name0 = this.shortName;
        if(name0 != null) {
            if(name0 == null) {
                FqNameUnsafe.$$$reportNull$$$0(10);
            }
            return name0;
        }
        if(this.isRoot()) {
            throw new IllegalStateException("root");
        }
        this.compute();
        Name name1 = this.shortName;
        if(name1 == null) {
            FqNameUnsafe.$$$reportNull$$$0(11);
        }
        return name1;
    }

    public Name shortNameOrSpecial() {
        if(this.isRoot()) {
            Name name0 = FqNameUnsafe.ROOT_NAME;
            if(name0 == null) {
                FqNameUnsafe.$$$reportNull$$$0(12);
            }
            return name0;
        }
        Name name1 = this.shortName();
        if(name1 == null) {
            FqNameUnsafe.$$$reportNull$$$0(13);
        }
        return name1;
    }

    public boolean startsWith(Name name0) {
        if(name0 == null) {
            FqNameUnsafe.$$$reportNull$$$0(15);
        }
        if(this.isRoot()) {
            return false;
        }
        int v = this.fqName.indexOf(46);
        String s = name0.asString();
        String s1 = this.fqName;
        if(v == -1) {
            v = Math.max(s1.length(), s.length());
        }
        return s1.regionMatches(0, s, 0, v);
    }

    public FqName toSafe() {
        FqName fqName0 = this.safe;
        if(fqName0 != null) {
            if(fqName0 == null) {
                FqNameUnsafe.$$$reportNull$$$0(5);
            }
            return fqName0;
        }
        FqName fqName1 = new FqName(this);
        this.safe = fqName1;
        return fqName1;
    }

    @Override
    public String toString() {
        String s = this.isRoot() ? "<root>" : this.fqName;
        if(s == null) {
            FqNameUnsafe.$$$reportNull$$$0(17);
        }
        return s;
    }

    public static FqNameUnsafe topLevel(Name name0) {
        if(name0 == null) {
            FqNameUnsafe.$$$reportNull$$$0(16);
        }
        return new FqNameUnsafe(name0.asString(), FqName.ROOT.toUnsafe(), name0);
    }
}

