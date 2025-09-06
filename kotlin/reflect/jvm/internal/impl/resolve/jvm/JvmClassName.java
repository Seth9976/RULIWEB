package kotlin.reflect.jvm.internal.impl.resolve.jvm;

import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;

public class JvmClassName {
    private FqName fqName;
    private final String internalName;

    private static void $$$reportNull$$$0(int v) {
        Object[] arr_object = new Object[(v == 3 || v == 6 || v == 7 || v == 8 ? 2 : 3)];
        switch(v) {
            case 1: {
                arr_object[0] = "classId";
                break;
            }
            case 2: 
            case 4: {
                arr_object[0] = "fqName";
                break;
            }
            case 3: 
            case 6: 
            case 7: 
            case 8: {
                arr_object[0] = "kotlin/reflect/jvm/internal/impl/resolve/jvm/JvmClassName";
                break;
            }
            default: {
                arr_object[0] = "internalName";
            }
        }
        switch(v) {
            case 3: {
                arr_object[1] = "byFqNameWithoutInnerClasses";
                break;
            }
            case 6: {
                arr_object[1] = "getFqNameForClassNameWithoutDollars";
                break;
            }
            case 7: {
                arr_object[1] = "getPackageFqName";
                break;
            }
            case 8: {
                arr_object[1] = "getInternalName";
                break;
            }
            default: {
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/resolve/jvm/JvmClassName";
            }
        }
        switch(v) {
            case 1: {
                arr_object[2] = "byClassId";
                break;
            }
            case 2: 
            case 4: {
                arr_object[2] = "byFqNameWithoutInnerClasses";
                break;
            }
            case 5: {
                arr_object[2] = "<init>";
                break;
            }
            case 3: 
            case 6: 
            case 7: 
            case 8: {
                break;
            }
            default: {
                arr_object[2] = "byInternalName";
            }
        }
        String s = String.format((v == 3 || v == 6 || v == 7 || v == 8 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null"), arr_object);
        IllegalStateException illegalStateException0 = v == 3 || v == 6 || v == 7 || v == 8 ? new IllegalStateException(s) : new IllegalArgumentException(s);
        throw illegalStateException0;
    }

    private JvmClassName(String s) {
        if(s == null) {
            JvmClassName.$$$reportNull$$$0(5);
        }
        super();
        this.internalName = s;
    }

    public static JvmClassName byClassId(ClassId classId0) {
        if(classId0 == null) {
            JvmClassName.$$$reportNull$$$0(1);
        }
        FqName fqName0 = classId0.getPackageFqName();
        String s = classId0.getRelativeClassName().asString().replace('.', '$');
        return fqName0.isRoot() ? new JvmClassName(s) : new JvmClassName(fqName0.asString().replace('.', '/') + "/" + s);
    }

    public static JvmClassName byFqNameWithoutInnerClasses(FqName fqName0) {
        if(fqName0 == null) {
            JvmClassName.$$$reportNull$$$0(2);
        }
        JvmClassName jvmClassName0 = new JvmClassName(fqName0.asString().replace('.', '/'));
        jvmClassName0.fqName = fqName0;
        return jvmClassName0;
    }

    public static JvmClassName byInternalName(String s) {
        if(s == null) {
            JvmClassName.$$$reportNull$$$0(0);
        }
        return new JvmClassName(s);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 == null || this.getClass() != object0.getClass() ? false : this.internalName.equals(((JvmClassName)object0).internalName);
    }

    public FqName getFqNameForTopLevelClassMaybeWithDollars() {
        return new FqName(this.internalName.replace('/', '.'));
    }

    public String getInternalName() {
        String s = this.internalName;
        if(s == null) {
            JvmClassName.$$$reportNull$$$0(8);
        }
        return s;
    }

    public FqName getPackageFqName() {
        int v = this.internalName.lastIndexOf("/");
        if(v == -1) {
            FqName fqName0 = FqName.ROOT;
            if(fqName0 == null) {
                JvmClassName.$$$reportNull$$$0(7);
            }
            return fqName0;
        }
        return new FqName(this.internalName.substring(0, v).replace('/', '.'));
    }

    @Override
    public int hashCode() {
        return this.internalName.hashCode();
    }

    @Override
    public String toString() {
        return this.internalName;
    }
}

