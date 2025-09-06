package com.google.crypto.tink.shaded.protobuf;

import java.lang.reflect.Field;

@CheckReturnValue
final class FieldInfo implements Comparable {
    public static final class Builder {
        private Field cachedSizeField;
        private boolean enforceUtf8;
        private EnumVerifier enumVerifier;
        private Field field;
        private int fieldNumber;
        private Object mapDefaultEntry;
        private OneofInfo oneof;
        private Class oneofStoredType;
        private Field presenceField;
        private int presenceMask;
        private boolean required;
        private FieldType type;

        private Builder() {
        }

        Builder(com.google.crypto.tink.shaded.protobuf.FieldInfo.1 fieldInfo$10) {
        }

        public FieldInfo build() {
            OneofInfo oneofInfo0 = this.oneof;
            if(oneofInfo0 != null) {
                return FieldInfo.forOneofMemberField(this.fieldNumber, this.type, oneofInfo0, this.oneofStoredType, this.enforceUtf8, this.enumVerifier);
            }
            Object object0 = this.mapDefaultEntry;
            if(object0 != null) {
                return FieldInfo.forMapField(this.field, this.fieldNumber, object0, this.enumVerifier);
            }
            Field field0 = this.presenceField;
            if(field0 != null) {
                return this.required ? FieldInfo.forProto2RequiredField(this.field, this.fieldNumber, this.type, field0, this.presenceMask, this.enforceUtf8, this.enumVerifier) : FieldInfo.forProto2OptionalField(this.field, this.fieldNumber, this.type, field0, this.presenceMask, this.enforceUtf8, this.enumVerifier);
            }
            EnumVerifier internal$EnumVerifier0 = this.enumVerifier;
            if(internal$EnumVerifier0 != null) {
                return this.cachedSizeField == null ? FieldInfo.forFieldWithEnumVerifier(this.field, this.fieldNumber, this.type, internal$EnumVerifier0) : FieldInfo.forPackedFieldWithEnumVerifier(this.field, this.fieldNumber, this.type, internal$EnumVerifier0, this.cachedSizeField);
            }
            return this.cachedSizeField == null ? FieldInfo.forField(this.field, this.fieldNumber, this.type, this.enforceUtf8) : FieldInfo.forPackedField(this.field, this.fieldNumber, this.type, this.cachedSizeField);
        }

        public Builder withCachedSizeField(Field field0) {
            this.cachedSizeField = field0;
            return this;
        }

        public Builder withEnforceUtf8(boolean z) {
            this.enforceUtf8 = z;
            return this;
        }

        public Builder withEnumVerifier(EnumVerifier internal$EnumVerifier0) {
            this.enumVerifier = internal$EnumVerifier0;
            return this;
        }

        public Builder withField(Field field0) {
            if(this.oneof != null) {
                throw new IllegalStateException("Cannot set field when building a oneof.");
            }
            this.field = field0;
            return this;
        }

        public Builder withFieldNumber(int v) {
            this.fieldNumber = v;
            return this;
        }

        public Builder withMapDefaultEntry(Object object0) {
            this.mapDefaultEntry = object0;
            return this;
        }

        public Builder withOneof(OneofInfo oneofInfo0, Class class0) {
            if(this.field != null || this.presenceField != null) {
                throw new IllegalStateException("Cannot set oneof when field or presenceField have been provided");
            }
            this.oneof = oneofInfo0;
            this.oneofStoredType = class0;
            return this;
        }

        public Builder withPresence(Field field0, int v) {
            this.presenceField = (Field)Internal.checkNotNull(field0, "presenceField");
            this.presenceMask = v;
            return this;
        }

        public Builder withRequired(boolean z) {
            this.required = z;
            return this;
        }

        public Builder withType(FieldType fieldType0) {
            this.type = fieldType0;
            return this;
        }
    }

    private final Field cachedSizeField;
    private final boolean enforceUtf8;
    private final EnumVerifier enumVerifier;
    private final Field field;
    private final int fieldNumber;
    private final Object mapDefaultEntry;
    private final Class messageClass;
    private final OneofInfo oneof;
    private final Class oneofStoredType;
    private final Field presenceField;
    private final int presenceMask;
    private final boolean required;
    private final FieldType type;

    private FieldInfo(Field field0, int v, FieldType fieldType0, Class class0, Field field1, int v1, boolean z, boolean z1, OneofInfo oneofInfo0, Class class1, Object object0, EnumVerifier internal$EnumVerifier0, Field field2) {
        this.field = field0;
        this.type = fieldType0;
        this.messageClass = class0;
        this.fieldNumber = v;
        this.presenceField = field1;
        this.presenceMask = v1;
        this.required = z;
        this.enforceUtf8 = z1;
        this.oneof = oneofInfo0;
        this.oneofStoredType = class1;
        this.mapDefaultEntry = object0;
        this.enumVerifier = internal$EnumVerifier0;
        this.cachedSizeField = field2;
    }

    private static void checkFieldNumber(int v) {
        if(v <= 0) {
            throw new IllegalArgumentException("fieldNumber must be positive: " + v);
        }
    }

    public int compareTo(FieldInfo fieldInfo0) {
        return this.fieldNumber - fieldInfo0.fieldNumber;
    }

    @Override
    public int compareTo(Object object0) {
        return this.compareTo(((FieldInfo)object0));
    }

    public static FieldInfo forField(Field field0, int v, FieldType fieldType0, boolean z) {
        FieldInfo.checkFieldNumber(v);
        Internal.checkNotNull(field0, "field");
        Internal.checkNotNull(fieldType0, "fieldType");
        if(fieldType0 == FieldType.MESSAGE_LIST || fieldType0 == FieldType.GROUP_LIST) {
            throw new IllegalStateException("Shouldn\'t be called for repeated message fields.");
        }
        return new FieldInfo(field0, v, fieldType0, null, null, 0, false, z, null, null, null, null, null);
    }

    public static FieldInfo forFieldWithEnumVerifier(Field field0, int v, FieldType fieldType0, EnumVerifier internal$EnumVerifier0) {
        FieldInfo.checkFieldNumber(v);
        Internal.checkNotNull(field0, "field");
        return new FieldInfo(field0, v, fieldType0, null, null, 0, false, false, null, null, null, internal$EnumVerifier0, null);
    }

    public static FieldInfo forMapField(Field field0, int v, Object object0, EnumVerifier internal$EnumVerifier0) {
        Internal.checkNotNull(object0, "mapDefaultEntry");
        FieldInfo.checkFieldNumber(v);
        Internal.checkNotNull(field0, "field");
        return new FieldInfo(field0, v, FieldType.MAP, null, null, 0, false, true, null, null, object0, internal$EnumVerifier0, null);
    }

    public static FieldInfo forOneofMemberField(int v, FieldType fieldType0, OneofInfo oneofInfo0, Class class0, boolean z, EnumVerifier internal$EnumVerifier0) {
        FieldInfo.checkFieldNumber(v);
        Internal.checkNotNull(fieldType0, "fieldType");
        Internal.checkNotNull(oneofInfo0, "oneof");
        Internal.checkNotNull(class0, "oneofStoredType");
        if(!fieldType0.isScalar()) {
            throw new IllegalArgumentException("Oneof is only supported for scalar fields. Field " + v + " is of type " + fieldType0);
        }
        return new FieldInfo(null, v, fieldType0, null, null, 0, false, z, oneofInfo0, class0, null, internal$EnumVerifier0, null);
    }

    public static FieldInfo forPackedField(Field field0, int v, FieldType fieldType0, Field field1) {
        FieldInfo.checkFieldNumber(v);
        Internal.checkNotNull(field0, "field");
        Internal.checkNotNull(fieldType0, "fieldType");
        if(fieldType0 == FieldType.MESSAGE_LIST || fieldType0 == FieldType.GROUP_LIST) {
            throw new IllegalStateException("Shouldn\'t be called for repeated message fields.");
        }
        return new FieldInfo(field0, v, fieldType0, null, null, 0, false, false, null, null, null, null, field1);
    }

    public static FieldInfo forPackedFieldWithEnumVerifier(Field field0, int v, FieldType fieldType0, EnumVerifier internal$EnumVerifier0, Field field1) {
        FieldInfo.checkFieldNumber(v);
        Internal.checkNotNull(field0, "field");
        return new FieldInfo(field0, v, fieldType0, null, null, 0, false, false, null, null, null, internal$EnumVerifier0, field1);
    }

    public static FieldInfo forProto2OptionalField(Field field0, int v, FieldType fieldType0, Field field1, int v1, boolean z, EnumVerifier internal$EnumVerifier0) {
        FieldInfo.checkFieldNumber(v);
        Internal.checkNotNull(field0, "field");
        Internal.checkNotNull(fieldType0, "fieldType");
        Internal.checkNotNull(field1, "presenceField");
        if(field1 != null && !FieldInfo.isExactlyOneBitSet(v1)) {
            throw new IllegalArgumentException("presenceMask must have exactly one bit set: " + v1);
        }
        return new FieldInfo(field0, v, fieldType0, null, field1, v1, false, z, null, null, null, internal$EnumVerifier0, null);
    }

    public static FieldInfo forProto2RequiredField(Field field0, int v, FieldType fieldType0, Field field1, int v1, boolean z, EnumVerifier internal$EnumVerifier0) {
        FieldInfo.checkFieldNumber(v);
        Internal.checkNotNull(field0, "field");
        Internal.checkNotNull(fieldType0, "fieldType");
        Internal.checkNotNull(field1, "presenceField");
        if(field1 != null && !FieldInfo.isExactlyOneBitSet(v1)) {
            throw new IllegalArgumentException("presenceMask must have exactly one bit set: " + v1);
        }
        return new FieldInfo(field0, v, fieldType0, null, field1, v1, true, z, null, null, null, internal$EnumVerifier0, null);
    }

    public static FieldInfo forRepeatedMessageField(Field field0, int v, FieldType fieldType0, Class class0) {
        FieldInfo.checkFieldNumber(v);
        Internal.checkNotNull(field0, "field");
        Internal.checkNotNull(fieldType0, "fieldType");
        Internal.checkNotNull(class0, "messageClass");
        return new FieldInfo(field0, v, fieldType0, class0, null, 0, false, false, null, null, null, null, null);
    }

    public Field getCachedSizeField() {
        return this.cachedSizeField;
    }

    public EnumVerifier getEnumVerifier() {
        return this.enumVerifier;
    }

    public Field getField() {
        return this.field;
    }

    public int getFieldNumber() {
        return this.fieldNumber;
    }

    public Class getListElementType() {
        return this.messageClass;
    }

    public Object getMapDefaultEntry() {
        return this.mapDefaultEntry;
    }

    public Class getMessageFieldClass() {
        switch(com.google.crypto.tink.shaded.protobuf.FieldInfo.1.$SwitchMap$com$google$protobuf$FieldType[this.type.ordinal()]) {
            case 1: 
            case 2: {
                return this.field == null ? this.oneofStoredType : this.field.getType();
            }
            case 3: 
            case 4: {
                return this.messageClass;
            }
            default: {
                return null;
            }
        }
    }

    public OneofInfo getOneof() {
        return this.oneof;
    }

    public Class getOneofStoredType() {
        return this.oneofStoredType;
    }

    public Field getPresenceField() {
        return this.presenceField;
    }

    public int getPresenceMask() {
        return this.presenceMask;
    }

    public FieldType getType() {
        return this.type;
    }

    public boolean isEnforceUtf8() {
        return this.enforceUtf8;
    }

    private static boolean isExactlyOneBitSet(int value) {
        return value != 0 && (value & value - 1) == 0;
    }

    public boolean isRequired() {
        return this.required;
    }

    public static Builder newBuilder() {
        return new Builder(null);
    }

    class com.google.crypto.tink.shaded.protobuf.FieldInfo.1 {
        static final int[] $SwitchMap$com$google$protobuf$FieldType;

        static {
            int[] arr_v = new int[FieldType.values().length];
            com.google.crypto.tink.shaded.protobuf.FieldInfo.1.$SwitchMap$com$google$protobuf$FieldType = arr_v;
            try {
                arr_v[FieldType.MESSAGE.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.FieldInfo.1.$SwitchMap$com$google$protobuf$FieldType[FieldType.GROUP.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.FieldInfo.1.$SwitchMap$com$google$protobuf$FieldType[FieldType.MESSAGE_LIST.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.FieldInfo.1.$SwitchMap$com$google$protobuf$FieldType[FieldType.GROUP_LIST.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

