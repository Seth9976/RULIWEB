package com.google.crypto.tink.shaded.protobuf;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;

public enum FieldType {
    static enum Collection {
        SCALAR(false),
        VECTOR(true),
        PACKED_VECTOR(true),
        MAP(false);

        private final boolean isList;

        private Collection(boolean z) {
            this.isList = z;
        }

        public boolean isList() {
            return this.isList;
        }
    }

    DOUBLE(0, Collection.SCALAR, JavaType.DOUBLE),
    FLOAT(1, Collection.SCALAR, JavaType.FLOAT),
    INT64(2, Collection.SCALAR, JavaType.LONG),
    UINT64(3, Collection.SCALAR, JavaType.LONG),
    INT32(4, Collection.SCALAR, JavaType.INT),
    FIXED64(5, Collection.SCALAR, JavaType.LONG),
    FIXED32(6, Collection.SCALAR, JavaType.INT),
    BOOL(7, Collection.SCALAR, JavaType.BOOLEAN),
    STRING(8, Collection.SCALAR, JavaType.STRING),
    MESSAGE(9, Collection.SCALAR, JavaType.MESSAGE),
    BYTES(10, Collection.SCALAR, JavaType.BYTE_STRING),
    UINT32(11, Collection.SCALAR, JavaType.INT),
    ENUM(12, Collection.SCALAR, JavaType.ENUM),
    SFIXED32(13, Collection.SCALAR, JavaType.INT),
    SFIXED64(14, Collection.SCALAR, JavaType.LONG),
    SINT32(15, Collection.SCALAR, JavaType.INT),
    SINT64(16, Collection.SCALAR, JavaType.LONG),
    GROUP(17, Collection.SCALAR, JavaType.MESSAGE),
    DOUBLE_LIST(18, Collection.VECTOR, JavaType.DOUBLE),
    FLOAT_LIST(19, Collection.VECTOR, JavaType.FLOAT),
    INT64_LIST(20, Collection.VECTOR, JavaType.LONG),
    UINT64_LIST(21, Collection.VECTOR, JavaType.LONG),
    INT32_LIST(22, Collection.VECTOR, JavaType.INT),
    FIXED64_LIST(23, Collection.VECTOR, JavaType.LONG),
    FIXED32_LIST(24, Collection.VECTOR, JavaType.INT),
    BOOL_LIST(25, Collection.VECTOR, JavaType.BOOLEAN),
    STRING_LIST(26, Collection.VECTOR, JavaType.STRING),
    MESSAGE_LIST(27, Collection.VECTOR, JavaType.MESSAGE),
    BYTES_LIST(28, Collection.VECTOR, JavaType.BYTE_STRING),
    UINT32_LIST(29, Collection.VECTOR, JavaType.INT),
    ENUM_LIST(30, Collection.VECTOR, JavaType.ENUM),
    SFIXED32_LIST(0x1F, Collection.VECTOR, JavaType.INT),
    SFIXED64_LIST(0x20, Collection.VECTOR, JavaType.LONG),
    SINT32_LIST(33, Collection.VECTOR, JavaType.INT),
    SINT64_LIST(34, Collection.VECTOR, JavaType.LONG),
    DOUBLE_LIST_PACKED(35, Collection.PACKED_VECTOR, JavaType.DOUBLE),
    FLOAT_LIST_PACKED(36, Collection.PACKED_VECTOR, JavaType.FLOAT),
    INT64_LIST_PACKED(37, Collection.PACKED_VECTOR, JavaType.LONG),
    UINT64_LIST_PACKED(38, Collection.PACKED_VECTOR, JavaType.LONG),
    INT32_LIST_PACKED(39, Collection.PACKED_VECTOR, JavaType.INT),
    FIXED64_LIST_PACKED(40, Collection.PACKED_VECTOR, JavaType.LONG),
    FIXED32_LIST_PACKED(41, Collection.PACKED_VECTOR, JavaType.INT),
    BOOL_LIST_PACKED(42, Collection.PACKED_VECTOR, JavaType.BOOLEAN),
    UINT32_LIST_PACKED(43, Collection.PACKED_VECTOR, JavaType.INT),
    ENUM_LIST_PACKED(44, Collection.PACKED_VECTOR, JavaType.ENUM),
    SFIXED32_LIST_PACKED(45, Collection.PACKED_VECTOR, JavaType.INT),
    SFIXED64_LIST_PACKED(46, Collection.PACKED_VECTOR, JavaType.LONG),
    SINT32_LIST_PACKED(0x2F, Collection.PACKED_VECTOR, JavaType.INT),
    SINT64_LIST_PACKED(0x30, Collection.PACKED_VECTOR, JavaType.LONG),
    GROUP_LIST(49, Collection.VECTOR, JavaType.MESSAGE),
    MAP(50, Collection.MAP, JavaType.VOID);

    private static final FieldType[] $VALUES;
    private static final Type[] EMPTY_TYPES;
    private static final FieldType[] VALUES;
    private final Collection collection;
    private final Class elementType;
    private final int id;
    private final JavaType javaType;
    private final boolean primitiveScalar;

    static {
        arr_fieldType[0] = FieldType.DOUBLE;
        arr_fieldType[1] = FieldType.FLOAT;
        arr_fieldType[2] = FieldType.INT64;
        arr_fieldType[3] = FieldType.UINT64;
        arr_fieldType[4] = FieldType.INT32;
        arr_fieldType[5] = FieldType.FIXED64;
        arr_fieldType[6] = FieldType.FIXED32;
        arr_fieldType[7] = FieldType.BOOL;
        arr_fieldType[8] = FieldType.STRING;
        arr_fieldType[9] = FieldType.MESSAGE;
        arr_fieldType[10] = FieldType.BYTES;
        arr_fieldType[11] = FieldType.UINT32;
        arr_fieldType[12] = FieldType.ENUM;
        arr_fieldType[13] = FieldType.SFIXED32;
        arr_fieldType[14] = FieldType.SFIXED64;
        arr_fieldType[15] = FieldType.SINT32;
        arr_fieldType[16] = FieldType.SINT64;
        arr_fieldType[17] = FieldType.GROUP;
        arr_fieldType[18] = FieldType.DOUBLE_LIST;
        arr_fieldType[19] = FieldType.FLOAT_LIST;
        arr_fieldType[20] = FieldType.INT64_LIST;
        arr_fieldType[21] = FieldType.UINT64_LIST;
        arr_fieldType[22] = FieldType.INT32_LIST;
        arr_fieldType[23] = FieldType.FIXED64_LIST;
        arr_fieldType[24] = FieldType.FIXED32_LIST;
        arr_fieldType[25] = FieldType.BOOL_LIST;
        arr_fieldType[26] = FieldType.STRING_LIST;
        arr_fieldType[27] = FieldType.MESSAGE_LIST;
        arr_fieldType[28] = FieldType.BYTES_LIST;
        arr_fieldType[29] = FieldType.UINT32_LIST;
        arr_fieldType[30] = FieldType.ENUM_LIST;
        arr_fieldType[0x1F] = FieldType.SFIXED32_LIST;
        arr_fieldType[0x20] = FieldType.SFIXED64_LIST;
        arr_fieldType[33] = FieldType.SINT32_LIST;
        arr_fieldType[34] = FieldType.SINT64_LIST;
        arr_fieldType[35] = FieldType.DOUBLE_LIST_PACKED;
        arr_fieldType[36] = FieldType.FLOAT_LIST_PACKED;
        arr_fieldType[37] = FieldType.INT64_LIST_PACKED;
        arr_fieldType[38] = FieldType.UINT64_LIST_PACKED;
        arr_fieldType[39] = FieldType.INT32_LIST_PACKED;
        arr_fieldType[40] = FieldType.FIXED64_LIST_PACKED;
        arr_fieldType[41] = FieldType.FIXED32_LIST_PACKED;
        arr_fieldType[42] = FieldType.BOOL_LIST_PACKED;
        arr_fieldType[43] = FieldType.UINT32_LIST_PACKED;
        arr_fieldType[44] = FieldType.ENUM_LIST_PACKED;
        arr_fieldType[45] = FieldType.SFIXED32_LIST_PACKED;
        arr_fieldType[46] = FieldType.SFIXED64_LIST_PACKED;
        arr_fieldType[0x2F] = FieldType.SINT32_LIST_PACKED;
        arr_fieldType[0x30] = FieldType.SINT64_LIST_PACKED;
        arr_fieldType[49] = FieldType.GROUP_LIST;
        arr_fieldType[50] = FieldType.MAP;
        FieldType.$VALUES = arr_fieldType;
        FieldType.EMPTY_TYPES = new Type[0];
        FieldType[] arr_fieldType1 = (FieldType[])FieldType.$VALUES.clone();
        FieldType.VALUES = new FieldType[arr_fieldType1.length];
        for(int v = 0; v < arr_fieldType1.length; ++v) {
            FieldType fieldType0 = arr_fieldType1[v];
            FieldType.VALUES[fieldType0.id] = fieldType0;
        }
    }

    private FieldType(int v1, Collection fieldType$Collection0, JavaType javaType0) {
        this.id = v1;
        this.collection = fieldType$Collection0;
        this.javaType = javaType0;
        boolean z = true;
        switch(com.google.crypto.tink.shaded.protobuf.FieldType.1.$SwitchMap$com$google$protobuf$FieldType$Collection[fieldType$Collection0.ordinal()]) {
            case 1: {
                this.elementType = javaType0.getBoxedType();
                break;
            }
            case 2: {
                this.elementType = javaType0.getBoxedType();
                break;
            }
            default: {
                this.elementType = null;
            }
        }
        if(fieldType$Collection0 == Collection.SCALAR) {
            int v2 = com.google.crypto.tink.shaded.protobuf.FieldType.1.$SwitchMap$com$google$protobuf$JavaType[javaType0.ordinal()];
            if(v2 == 1 || v2 == 2 || v2 == 3) {
                z = false;
            }
        }
        else {
            z = false;
        }
        this.primitiveScalar = z;
    }

    public static FieldType forId(int v) {
        if(v >= 0) {
            return v >= FieldType.VALUES.length ? null : FieldType.VALUES[v];
        }
        return null;
    }

    private static Type getGenericSuperList(Class class0) {
        Type[] arr_type = class0.getGenericInterfaces();
        for(int v = 0; v < arr_type.length; ++v) {
            Type type0 = arr_type[v];
            if(type0 instanceof ParameterizedType) {
                Class class1 = (Class)((ParameterizedType)type0).getRawType();
                if(List.class.isAssignableFrom(class1)) {
                    return type0;
                }
            }
        }
        Type type1 = class0.getGenericSuperclass();
        if(type1 instanceof ParameterizedType) {
            Class class2 = (Class)((ParameterizedType)type1).getRawType();
            return List.class.isAssignableFrom(class2) ? type1 : null;
        }
        return null;
    }

    public JavaType getJavaType() {
        return this.javaType;
    }

    private static Type getListParameter(Class class0, Type[] arr_type) {
    alab2:
        while(true) {
            int v = 0;
            if(class0 == List.class) {
                break;
            }
            Type type0 = FieldType.getGenericSuperList(class0);
            if(type0 instanceof ParameterizedType) {
                Type[] arr_type1 = ((ParameterizedType)type0).getActualTypeArguments();
            alab1:
                for(int v1 = 0; v1 < arr_type1.length; ++v1) {
                    Type type1 = arr_type1[v1];
                    if(type1 instanceof TypeVariable) {
                        TypeVariable[] arr_typeVariable = class0.getTypeParameters();
                        if(arr_type.length != arr_typeVariable.length) {
                            throw new RuntimeException("Type array mismatch");
                        }
                        int v2 = 0;
                        while(v2 < arr_typeVariable.length) {
                            if(type1 == arr_typeVariable[v2]) {
                                arr_type1[v1] = arr_type[v2];
                                continue alab1;
                            }
                            ++v2;
                        }
                        throw new RuntimeException("Unable to find replacement for " + type1);
                    }
                }
                class0 = (Class)((ParameterizedType)type0).getRawType();
                arr_type = arr_type1;
            }
            else {
                arr_type = FieldType.EMPTY_TYPES;
                Class[] arr_class = class0.getInterfaces();
                while(v < arr_class.length) {
                    Class class1 = arr_class[v];
                    if(List.class.isAssignableFrom(class1)) {
                        class0 = class1;
                        continue alab2;
                    }
                    ++v;
                }
                class0 = class0.getSuperclass();
            }
        }
        if(arr_type.length != 1) {
            throw new RuntimeException("Unable to identify parameter type for List<T>");
        }
        return arr_type[0];
    }

    public int id() {
        return this.id;
    }

    public boolean isList() {
        return this.collection.isList();
    }

    public boolean isMap() {
        return this.collection == Collection.MAP;
    }

    public boolean isPacked() {
        return Collection.PACKED_VECTOR.equals(this.collection);
    }

    public boolean isPrimitiveScalar() {
        return this.primitiveScalar;
    }

    public boolean isScalar() {
        return this.collection == Collection.SCALAR;
    }

    public boolean isValidForField(Field field0) {
        if(Collection.VECTOR.equals(this.collection)) {
            return this.isValidForList(field0);
        }
        Class class0 = field0.getType();
        return this.javaType.getType().isAssignableFrom(class0);
    }

    private boolean isValidForList(Field field0) {
        Class class0 = field0.getType();
        if(!this.javaType.getType().isAssignableFrom(class0)) {
            return false;
        }
        Type type0 = FieldType.getListParameter(class0, (field0.getGenericType() instanceof ParameterizedType ? ((ParameterizedType)field0.getGenericType()).getActualTypeArguments() : FieldType.EMPTY_TYPES));
        return type0 instanceof Class ? this.elementType.isAssignableFrom(((Class)type0)) : true;
    }

    class com.google.crypto.tink.shaded.protobuf.FieldType.1 {
        static final int[] $SwitchMap$com$google$protobuf$FieldType$Collection;
        static final int[] $SwitchMap$com$google$protobuf$JavaType;

        static {
            int[] arr_v = new int[JavaType.values().length];
            com.google.crypto.tink.shaded.protobuf.FieldType.1.$SwitchMap$com$google$protobuf$JavaType = arr_v;
            try {
                arr_v[JavaType.BYTE_STRING.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.FieldType.1.$SwitchMap$com$google$protobuf$JavaType[JavaType.MESSAGE.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.FieldType.1.$SwitchMap$com$google$protobuf$JavaType[JavaType.STRING.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            int[] arr_v1 = new int[Collection.values().length];
            com.google.crypto.tink.shaded.protobuf.FieldType.1.$SwitchMap$com$google$protobuf$FieldType$Collection = arr_v1;
            try {
                arr_v1[Collection.MAP.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.FieldType.1.$SwitchMap$com$google$protobuf$FieldType$Collection[Collection.VECTOR.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.FieldType.1.$SwitchMap$com$google$protobuf$FieldType$Collection[Collection.SCALAR.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

