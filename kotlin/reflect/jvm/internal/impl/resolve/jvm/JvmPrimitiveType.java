package kotlin.reflect.jvm.internal.impl.resolve.jvm;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.name.FqName;

public enum JvmPrimitiveType {
    BOOLEAN(PrimitiveType.BOOLEAN, "boolean", "Z", "java.lang.Boolean"),
    CHAR(PrimitiveType.CHAR, "char", "C", "java.lang.Character"),
    BYTE(PrimitiveType.BYTE, "byte", "B", "java.lang.Byte"),
    SHORT(PrimitiveType.SHORT, "short", "S", "java.lang.Short"),
    INT(PrimitiveType.INT, "int", "I", "java.lang.Integer"),
    FLOAT(PrimitiveType.FLOAT, "float", "F", "java.lang.Float"),
    LONG(PrimitiveType.LONG, "long", "J", "java.lang.Long"),
    DOUBLE(PrimitiveType.DOUBLE, "double", "D", "java.lang.Double");

    private static final JvmPrimitiveType[] $VALUES;
    private static final Map TYPE_BY_DESC;
    private static final Map TYPE_BY_NAME;
    private static final Map TYPE_BY_PRIMITIVE_TYPE;
    private static final Set WRAPPERS_CLASS_NAMES;
    private final String desc;
    private final String name;
    private final PrimitiveType primitiveType;
    private final FqName wrapperFqName;

    private static void $$$reportNull$$$0(int v) {
        Object[] arr_object = new Object[(v == 2 || v == 4 || (v == 10 || v == 11 || v == 12 || v == 13) ? 2 : 3)];
        switch(v) {
            case 3: {
                arr_object[0] = "type";
                break;
            }
            case 6: {
                arr_object[0] = "primitiveType";
                break;
            }
            case 1: 
            case 7: {
                arr_object[0] = "name";
                break;
            }
            case 5: 
            case 8: {
                arr_object[0] = "desc";
                break;
            }
            case 9: {
                arr_object[0] = "wrapperClassName";
                break;
            }
            case 2: 
            case 4: 
            case 10: 
            case 11: 
            case 12: 
            case 13: {
                arr_object[0] = "kotlin/reflect/jvm/internal/impl/resolve/jvm/JvmPrimitiveType";
                break;
            }
            default: {
                arr_object[0] = "className";
            }
        }
        if(v == 2 || v == 4) {
            arr_object[1] = "get";
        }
        else {
            switch(v) {
                case 10: {
                    arr_object[1] = "getPrimitiveType";
                    break;
                }
                case 11: {
                    arr_object[1] = "getJavaKeywordName";
                    break;
                }
                case 12: {
                    arr_object[1] = "getDesc";
                    break;
                }
                case 13: {
                    arr_object[1] = "getWrapperFqName";
                    break;
                }
                default: {
                    arr_object[1] = "kotlin/reflect/jvm/internal/impl/resolve/jvm/JvmPrimitiveType";
                }
            }
        }
        switch(v) {
            case 1: 
            case 3: {
                arr_object[2] = "get";
                break;
            }
            case 5: {
                arr_object[2] = "getByDesc";
                break;
            }
            case 6: 
            case 7: 
            case 8: 
            case 9: {
                arr_object[2] = "<init>";
                break;
            }
            case 2: 
            case 4: 
            case 10: 
            case 11: 
            case 12: 
            case 13: {
                break;
            }
            default: {
                arr_object[2] = "isWrapperClassName";
            }
        }
        String s = String.format((v == 2 || v == 4 || (v == 10 || v == 11 || v == 12 || v == 13) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null"), arr_object);
        IllegalStateException illegalStateException0 = v == 2 || v == 4 || (v == 10 || v == 11 || v == 12 || v == 13) ? new IllegalStateException(s) : new IllegalArgumentException(s);
        throw illegalStateException0;
    }

    static {
        arr_jvmPrimitiveType[0] = JvmPrimitiveType.BOOLEAN;
        arr_jvmPrimitiveType[1] = JvmPrimitiveType.CHAR;
        arr_jvmPrimitiveType[2] = JvmPrimitiveType.BYTE;
        arr_jvmPrimitiveType[3] = JvmPrimitiveType.SHORT;
        arr_jvmPrimitiveType[4] = JvmPrimitiveType.INT;
        arr_jvmPrimitiveType[5] = JvmPrimitiveType.FLOAT;
        arr_jvmPrimitiveType[6] = JvmPrimitiveType.LONG;
        arr_jvmPrimitiveType[7] = JvmPrimitiveType.DOUBLE;
        JvmPrimitiveType.$VALUES = arr_jvmPrimitiveType;
        JvmPrimitiveType.WRAPPERS_CLASS_NAMES = new HashSet();
        JvmPrimitiveType.TYPE_BY_NAME = new HashMap();
        JvmPrimitiveType.TYPE_BY_PRIMITIVE_TYPE = new EnumMap(PrimitiveType.class);
        JvmPrimitiveType.TYPE_BY_DESC = new HashMap();
        JvmPrimitiveType[] arr_jvmPrimitiveType1 = (JvmPrimitiveType[])JvmPrimitiveType.$VALUES.clone();
        for(int v = 0; v < arr_jvmPrimitiveType1.length; ++v) {
            JvmPrimitiveType jvmPrimitiveType0 = arr_jvmPrimitiveType1[v];
            FqName fqName0 = jvmPrimitiveType0.getWrapperFqName();
            JvmPrimitiveType.WRAPPERS_CLASS_NAMES.add(fqName0);
            String s = jvmPrimitiveType0.getJavaKeywordName();
            JvmPrimitiveType.TYPE_BY_NAME.put(s, jvmPrimitiveType0);
            PrimitiveType primitiveType0 = jvmPrimitiveType0.getPrimitiveType();
            JvmPrimitiveType.TYPE_BY_PRIMITIVE_TYPE.put(primitiveType0, jvmPrimitiveType0);
            String s1 = jvmPrimitiveType0.getDesc();
            JvmPrimitiveType.TYPE_BY_DESC.put(s1, jvmPrimitiveType0);
        }
    }

    private JvmPrimitiveType(PrimitiveType primitiveType0, String s1, String s2, String s3) {
        if(primitiveType0 == null) {
            JvmPrimitiveType.$$$reportNull$$$0(6);
        }
        if(s1 == null) {
            JvmPrimitiveType.$$$reportNull$$$0(7);
        }
        if(s2 == null) {
            JvmPrimitiveType.$$$reportNull$$$0(8);
        }
        if(s3 == null) {
            JvmPrimitiveType.$$$reportNull$$$0(9);
        }
        super(s, v);
        this.primitiveType = primitiveType0;
        this.name = s1;
        this.desc = s2;
        this.wrapperFqName = new FqName(s3);
    }

    public static JvmPrimitiveType get(String s) {
        if(s == null) {
            JvmPrimitiveType.$$$reportNull$$$0(1);
        }
        JvmPrimitiveType jvmPrimitiveType0 = (JvmPrimitiveType)JvmPrimitiveType.TYPE_BY_NAME.get(s);
        if(jvmPrimitiveType0 == null) {
            throw new AssertionError("Non-primitive type name passed: " + s);
        }
        if(jvmPrimitiveType0 == null) {
            JvmPrimitiveType.$$$reportNull$$$0(2);
        }
        return jvmPrimitiveType0;
    }

    public static JvmPrimitiveType get(PrimitiveType primitiveType0) {
        if(primitiveType0 == null) {
            JvmPrimitiveType.$$$reportNull$$$0(3);
        }
        JvmPrimitiveType jvmPrimitiveType0 = (JvmPrimitiveType)JvmPrimitiveType.TYPE_BY_PRIMITIVE_TYPE.get(primitiveType0);
        if(jvmPrimitiveType0 == null) {
            JvmPrimitiveType.$$$reportNull$$$0(4);
        }
        return jvmPrimitiveType0;
    }

    public String getDesc() {
        String s = this.desc;
        if(s == null) {
            JvmPrimitiveType.$$$reportNull$$$0(12);
        }
        return s;
    }

    public String getJavaKeywordName() {
        String s = this.name;
        if(s == null) {
            JvmPrimitiveType.$$$reportNull$$$0(11);
        }
        return s;
    }

    public PrimitiveType getPrimitiveType() {
        PrimitiveType primitiveType0 = this.primitiveType;
        if(primitiveType0 == null) {
            JvmPrimitiveType.$$$reportNull$$$0(10);
        }
        return primitiveType0;
    }

    public FqName getWrapperFqName() {
        FqName fqName0 = this.wrapperFqName;
        if(fqName0 == null) {
            JvmPrimitiveType.$$$reportNull$$$0(13);
        }
        return fqName0;
    }
}

