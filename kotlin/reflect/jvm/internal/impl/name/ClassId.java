package kotlin.reflect.jvm.internal.impl.name;

public final class ClassId {
    static final boolean $assertionsDisabled;
    private final boolean local;
    private final FqName packageFqName;
    private final FqName relativeClassName;

    private static void $$$reportNull$$$0(int v) {
        Object[] arr_object = new Object[(v == 5 || v == 6 || v == 7 || v == 9 || (v == 13 || v == 14 || v == 15 || v == 16) ? 2 : 3)];
        switch(v) {
            case 2: {
                arr_object[0] = "relativeClassName";
                break;
            }
            case 1: 
            case 3: {
                arr_object[0] = "packageFqName";
                break;
            }
            case 4: {
                arr_object[0] = "topLevelName";
                break;
            }
            case 8: {
                arr_object[0] = "name";
                break;
            }
            case 10: {
                arr_object[0] = "segment";
                break;
            }
            case 11: 
            case 12: {
                arr_object[0] = "string";
                break;
            }
            case 5: 
            case 6: 
            case 7: 
            case 9: 
            case 13: 
            case 14: 
            case 15: 
            case 16: {
                arr_object[0] = "kotlin/reflect/jvm/internal/impl/name/ClassId";
                break;
            }
            default: {
                arr_object[0] = "topLevelFqName";
            }
        }
        switch(v) {
            case 5: {
                arr_object[1] = "getPackageFqName";
                break;
            }
            case 6: {
                arr_object[1] = "getRelativeClassName";
                break;
            }
            case 7: {
                arr_object[1] = "getShortClassName";
                break;
            }
            case 9: {
                arr_object[1] = "asSingleFqName";
                break;
            }
            case 13: 
            case 14: {
                arr_object[1] = "asString";
                break;
            }
            case 15: 
            case 16: {
                arr_object[1] = "asFqNameString";
                break;
            }
            default: {
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/name/ClassId";
            }
        }
        switch(v) {
            case 1: 
            case 2: 
            case 3: 
            case 4: {
                arr_object[2] = "<init>";
                break;
            }
            case 8: {
                arr_object[2] = "createNestedClassId";
                break;
            }
            case 10: {
                arr_object[2] = "startsWith";
                break;
            }
            case 11: 
            case 12: {
                arr_object[2] = "fromString";
                break;
            }
            case 5: 
            case 6: 
            case 7: 
            case 9: 
            case 13: 
            case 14: 
            case 15: 
            case 16: {
                break;
            }
            default: {
                arr_object[2] = "topLevel";
            }
        }
        String s = String.format((v == 5 || v == 6 || v == 7 || v == 9 || (v == 13 || v == 14 || v == 15 || v == 16) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null"), arr_object);
        IllegalStateException illegalStateException0 = v == 5 || v == 6 || v == 7 || v == 9 || (v == 13 || v == 14 || v == 15 || v == 16) ? new IllegalStateException(s) : new IllegalArgumentException(s);
        throw illegalStateException0;
    }

    static {
    }

    public ClassId(FqName fqName0, FqName fqName1, boolean z) {
        if(fqName0 == null) {
            ClassId.$$$reportNull$$$0(1);
        }
        if(fqName1 == null) {
            ClassId.$$$reportNull$$$0(2);
        }
        super();
        this.packageFqName = fqName0;
        this.relativeClassName = fqName1;
        this.local = z;
    }

    public ClassId(FqName fqName0, Name name0) {
        if(fqName0 == null) {
            ClassId.$$$reportNull$$$0(3);
        }
        if(name0 == null) {
            ClassId.$$$reportNull$$$0(4);
        }
        this(fqName0, FqName.topLevel(name0), false);
    }

    public FqName asSingleFqName() {
        if(this.packageFqName.isRoot()) {
            FqName fqName0 = this.relativeClassName;
            if(fqName0 == null) {
                ClassId.$$$reportNull$$$0(9);
            }
            return fqName0;
        }
        return new FqName(this.packageFqName.asString() + "." + this.relativeClassName.asString());
    }

    public String asString() {
        if(this.packageFqName.isRoot()) {
            String s = this.relativeClassName.asString();
            if(s == null) {
                ClassId.$$$reportNull$$$0(13);
            }
            return s;
        }
        String s1 = this.packageFqName.asString().replace('.', '/') + "/" + this.relativeClassName.asString();
        if(s1 == null) {
            ClassId.$$$reportNull$$$0(14);
        }
        return s1;
    }

    public ClassId createNestedClassId(Name name0) {
        if(name0 == null) {
            ClassId.$$$reportNull$$$0(8);
        }
        return new ClassId(this.getPackageFqName(), this.relativeClassName.child(name0), this.local);
    }

    // 去混淆评级： 低(30)
    @Override
    public boolean equals(Object object0) {
        return this == object0 ? true : object0 != null && this.getClass() == object0.getClass() && this.packageFqName.equals(((ClassId)object0).packageFqName) && this.relativeClassName.equals(((ClassId)object0).relativeClassName) && this.local == ((ClassId)object0).local;
    }

    public static ClassId fromString(String s) [...] // Inlined contents

    public static ClassId fromString(String s, boolean z) {
        if(s == null) {
            ClassId.$$$reportNull$$$0(12);
        }
        int v = s.lastIndexOf("/");
        return v == -1 ? new ClassId(new FqName(""), new FqName(s), z) : new ClassId(new FqName(s.substring(0, v).replace('/', '.')), new FqName(s.substring(v + 1)), z);
    }

    public ClassId getOuterClassId() {
        FqName fqName0 = this.relativeClassName.parent();
        return fqName0.isRoot() ? null : new ClassId(this.getPackageFqName(), fqName0, this.local);
    }

    public FqName getPackageFqName() {
        FqName fqName0 = this.packageFqName;
        if(fqName0 == null) {
            ClassId.$$$reportNull$$$0(5);
        }
        return fqName0;
    }

    public FqName getRelativeClassName() {
        FqName fqName0 = this.relativeClassName;
        if(fqName0 == null) {
            ClassId.$$$reportNull$$$0(6);
        }
        return fqName0;
    }

    public Name getShortClassName() {
        Name name0 = this.relativeClassName.shortName();
        if(name0 == null) {
            ClassId.$$$reportNull$$$0(7);
        }
        return name0;
    }

    @Override
    public int hashCode() {
        return (this.packageFqName.hashCode() * 0x1F + this.relativeClassName.hashCode()) * 0x1F + Boolean.valueOf(this.local).hashCode();
    }

    public boolean isLocal() {
        return this.local;
    }

    public boolean isNestedClass() {
        return !this.relativeClassName.parent().isRoot();
    }

    // 去混淆评级： 低(20)
    @Override
    public String toString() {
        return this.packageFqName.isRoot() ? "/" + this.asString() : this.asString();
    }

    public static ClassId topLevel(FqName fqName0) {
        if(fqName0 == null) {
            ClassId.$$$reportNull$$$0(0);
        }
        return new ClassId(fqName0.parent(), fqName0.shortName());
    }
}

