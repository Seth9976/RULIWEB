package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class.Kind;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.MemberKind;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Modality;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Visibility;
import kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLite;

public class Flags {
    public static class BooleanFlagField extends FlagField {
        // 去混淆评级： 低(20)
        private static void $$$reportNull$$$0(int v) {
            throw new IllegalStateException("@NotNull method kotlin/reflect/jvm/internal/impl/metadata/deserialization/Flags$BooleanFlagField.get must not return null");
        }

        public BooleanFlagField(int v) {
            super(v, 1, null);
        }

        public Boolean get(int v) {
            Boolean boolean0 = Boolean.valueOf((v & 1 << this.offset) != 0);
            if(boolean0 == null) {
                BooleanFlagField.$$$reportNull$$$0(0);
            }
            return boolean0;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$FlagField
        public Object get(int v) {
            return this.get(v);
        }

        // 去混淆评级： 低(20)
        public int toFlags(Boolean boolean0) {
            return boolean0.booleanValue() ? 1 << this.offset : 0;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$FlagField
        public int toFlags(Object object0) {
            return this.toFlags(((Boolean)object0));
        }
    }

    static class EnumLiteFlagField extends FlagField {
        private final EnumLite[] values;

        // 去混淆评级： 低(20)
        private static void $$$reportNull$$$0(int v) {
            throw new IllegalArgumentException("Argument for @NotNull parameter \'enumEntries\' of kotlin/reflect/jvm/internal/impl/metadata/deserialization/Flags$EnumLiteFlagField.bitWidth must not be null");
        }

        public EnumLiteFlagField(int v, EnumLite[] arr_internal$EnumLite) {
            super(v, EnumLiteFlagField.bitWidth(arr_internal$EnumLite), null);
            this.values = arr_internal$EnumLite;
        }

        private static int bitWidth(Object[] arr_object) {
            if(arr_object == null) {
                EnumLiteFlagField.$$$reportNull$$$0(0);
            }
            int v = arr_object.length - 1;
            if(v == 0) {
                return 1;
            }
            for(int v1 = 0x1F; v1 >= 0; --v1) {
                if((1 << v1 & v) != 0) {
                    return v1 + 1;
                }
            }
            throw new IllegalStateException("Empty enum: " + arr_object.getClass());
        }

        @Override  // kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$FlagField
        public Object get(int v) {
            return this.get(v);
        }

        public EnumLite get(int v) {
            int v1 = (v & (1 << this.bitWidth) - 1 << this.offset) >> this.offset;
            EnumLite[] arr_internal$EnumLite = this.values;
            for(int v2 = 0; v2 < arr_internal$EnumLite.length; ++v2) {
                EnumLite internal$EnumLite0 = arr_internal$EnumLite[v2];
                if(internal$EnumLite0.getNumber() == v1) {
                    return internal$EnumLite0;
                }
            }
            return null;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags$FlagField
        public int toFlags(Object object0) {
            return this.toFlags(((EnumLite)object0));
        }

        public int toFlags(EnumLite internal$EnumLite0) {
            return internal$EnumLite0.getNumber() << this.offset;
        }
    }

    public static abstract class FlagField {
        public final int bitWidth;
        public final int offset;

        private FlagField(int v, int v1) {
            this.offset = v;
            this.bitWidth = v1;
        }

        FlagField(int v, int v1, kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.1 flags$10) {
            this(v, v1);
        }

        public static FlagField after(FlagField flags$FlagField0, EnumLite[] arr_internal$EnumLite) {
            return new EnumLiteFlagField(flags$FlagField0.offset + flags$FlagField0.bitWidth, arr_internal$EnumLite);
        }

        public static BooleanFlagField booleanAfter(FlagField flags$FlagField0) {
            return new BooleanFlagField(flags$FlagField0.offset + flags$FlagField0.bitWidth);
        }

        public static BooleanFlagField booleanFirst() {
            return new BooleanFlagField(0);
        }

        public abstract Object get(int arg1);

        public abstract int toFlags(Object arg1);
    }

    public static final FlagField CLASS_KIND;
    public static final BooleanFlagField DECLARES_DEFAULT_VALUE;
    public static final BooleanFlagField DEFINITELY_NOT_NULL_TYPE;
    public static final BooleanFlagField HAS_ANNOTATIONS;
    public static final BooleanFlagField HAS_CONSTANT;
    public static final BooleanFlagField HAS_GETTER;
    public static final BooleanFlagField HAS_SETTER;
    public static final BooleanFlagField IS_CONST;
    public static final BooleanFlagField IS_CONSTRUCTOR_WITH_NON_STABLE_PARAMETER_NAMES;
    public static final BooleanFlagField IS_CROSSINLINE;
    public static final BooleanFlagField IS_DATA;
    public static final BooleanFlagField IS_DELEGATED;
    public static final BooleanFlagField IS_EXPECT_CLASS;
    public static final BooleanFlagField IS_EXPECT_FUNCTION;
    public static final BooleanFlagField IS_EXPECT_PROPERTY;
    public static final BooleanFlagField IS_EXTERNAL_ACCESSOR;
    public static final BooleanFlagField IS_EXTERNAL_CLASS;
    public static final BooleanFlagField IS_EXTERNAL_FUNCTION;
    public static final BooleanFlagField IS_EXTERNAL_PROPERTY;
    public static final BooleanFlagField IS_FUNCTION_WITH_NON_STABLE_PARAMETER_NAMES;
    public static final BooleanFlagField IS_FUN_INTERFACE;
    public static final BooleanFlagField IS_INFIX;
    public static final BooleanFlagField IS_INLINE;
    public static final BooleanFlagField IS_INLINE_ACCESSOR;
    public static final BooleanFlagField IS_INNER;
    public static final BooleanFlagField IS_LATEINIT;
    public static final BooleanFlagField IS_NEGATED;
    public static final BooleanFlagField IS_NOINLINE;
    public static final BooleanFlagField IS_NOT_DEFAULT;
    public static final BooleanFlagField IS_NULL_CHECK_PREDICATE;
    public static final BooleanFlagField IS_OPERATOR;
    public static final BooleanFlagField IS_SECONDARY;
    public static final BooleanFlagField IS_SUSPEND;
    public static final BooleanFlagField IS_TAILREC;
    public static final BooleanFlagField IS_UNSIGNED;
    public static final BooleanFlagField IS_VALUE_CLASS;
    public static final BooleanFlagField IS_VAR;
    public static final FlagField MEMBER_KIND;
    public static final FlagField MODALITY;
    public static final BooleanFlagField SUSPEND_TYPE;
    public static final FlagField VISIBILITY;

    private static void $$$reportNull$$$0(int v) {
        Object[] arr_object = new Object[3];
        switch(v) {
            case 2: {
                arr_object[0] = "kind";
                break;
            }
            case 6: 
            case 9: {
                arr_object[0] = "memberKind";
                break;
            }
            case 1: 
            case 5: 
            case 8: 
            case 11: {
                arr_object[0] = "modality";
                break;
            }
            default: {
                arr_object[0] = "visibility";
            }
        }
        arr_object[1] = "kotlin/reflect/jvm/internal/impl/metadata/deserialization/Flags";
        switch(v) {
            case 3: {
                arr_object[2] = "getConstructorFlags";
                break;
            }
            case 4: 
            case 5: 
            case 6: {
                arr_object[2] = "getFunctionFlags";
                break;
            }
            case 7: 
            case 8: 
            case 9: {
                arr_object[2] = "getPropertyFlags";
                break;
            }
            case 10: 
            case 11: {
                arr_object[2] = "getAccessorFlags";
                break;
            }
            default: {
                arr_object[2] = "getClassFlags";
            }
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter \'%s\' of %s.%s must not be null", arr_object));
    }

    static {
        BooleanFlagField flags$BooleanFlagField0 = FlagField.booleanFirst();
        Flags.SUSPEND_TYPE = flags$BooleanFlagField0;
        Flags.DEFINITELY_NOT_NULL_TYPE = FlagField.booleanAfter(flags$BooleanFlagField0);
        BooleanFlagField flags$BooleanFlagField1 = FlagField.booleanFirst();
        Flags.HAS_ANNOTATIONS = flags$BooleanFlagField1;
        FlagField flags$FlagField0 = FlagField.after(flags$BooleanFlagField1, Visibility.values());
        Flags.VISIBILITY = flags$FlagField0;
        FlagField flags$FlagField1 = FlagField.after(flags$FlagField0, Modality.values());
        Flags.MODALITY = flags$FlagField1;
        FlagField flags$FlagField2 = FlagField.after(flags$FlagField1, Kind.values());
        Flags.CLASS_KIND = flags$FlagField2;
        BooleanFlagField flags$BooleanFlagField2 = FlagField.booleanAfter(flags$FlagField2);
        Flags.IS_INNER = flags$BooleanFlagField2;
        BooleanFlagField flags$BooleanFlagField3 = FlagField.booleanAfter(flags$BooleanFlagField2);
        Flags.IS_DATA = flags$BooleanFlagField3;
        BooleanFlagField flags$BooleanFlagField4 = FlagField.booleanAfter(flags$BooleanFlagField3);
        Flags.IS_EXTERNAL_CLASS = flags$BooleanFlagField4;
        BooleanFlagField flags$BooleanFlagField5 = FlagField.booleanAfter(flags$BooleanFlagField4);
        Flags.IS_EXPECT_CLASS = flags$BooleanFlagField5;
        BooleanFlagField flags$BooleanFlagField6 = FlagField.booleanAfter(flags$BooleanFlagField5);
        Flags.IS_VALUE_CLASS = flags$BooleanFlagField6;
        Flags.IS_FUN_INTERFACE = FlagField.booleanAfter(flags$BooleanFlagField6);
        BooleanFlagField flags$BooleanFlagField7 = FlagField.booleanAfter(flags$FlagField0);
        Flags.IS_SECONDARY = flags$BooleanFlagField7;
        Flags.IS_CONSTRUCTOR_WITH_NON_STABLE_PARAMETER_NAMES = FlagField.booleanAfter(flags$BooleanFlagField7);
        FlagField flags$FlagField3 = FlagField.after(flags$FlagField1, MemberKind.values());
        Flags.MEMBER_KIND = flags$FlagField3;
        BooleanFlagField flags$BooleanFlagField8 = FlagField.booleanAfter(flags$FlagField3);
        Flags.IS_OPERATOR = flags$BooleanFlagField8;
        BooleanFlagField flags$BooleanFlagField9 = FlagField.booleanAfter(flags$BooleanFlagField8);
        Flags.IS_INFIX = flags$BooleanFlagField9;
        BooleanFlagField flags$BooleanFlagField10 = FlagField.booleanAfter(flags$BooleanFlagField9);
        Flags.IS_INLINE = flags$BooleanFlagField10;
        BooleanFlagField flags$BooleanFlagField11 = FlagField.booleanAfter(flags$BooleanFlagField10);
        Flags.IS_TAILREC = flags$BooleanFlagField11;
        BooleanFlagField flags$BooleanFlagField12 = FlagField.booleanAfter(flags$BooleanFlagField11);
        Flags.IS_EXTERNAL_FUNCTION = flags$BooleanFlagField12;
        BooleanFlagField flags$BooleanFlagField13 = FlagField.booleanAfter(flags$BooleanFlagField12);
        Flags.IS_SUSPEND = flags$BooleanFlagField13;
        BooleanFlagField flags$BooleanFlagField14 = FlagField.booleanAfter(flags$BooleanFlagField13);
        Flags.IS_EXPECT_FUNCTION = flags$BooleanFlagField14;
        Flags.IS_FUNCTION_WITH_NON_STABLE_PARAMETER_NAMES = FlagField.booleanAfter(flags$BooleanFlagField14);
        BooleanFlagField flags$BooleanFlagField15 = FlagField.booleanAfter(flags$FlagField3);
        Flags.IS_VAR = flags$BooleanFlagField15;
        BooleanFlagField flags$BooleanFlagField16 = FlagField.booleanAfter(flags$BooleanFlagField15);
        Flags.HAS_GETTER = flags$BooleanFlagField16;
        BooleanFlagField flags$BooleanFlagField17 = FlagField.booleanAfter(flags$BooleanFlagField16);
        Flags.HAS_SETTER = flags$BooleanFlagField17;
        BooleanFlagField flags$BooleanFlagField18 = FlagField.booleanAfter(flags$BooleanFlagField17);
        Flags.IS_CONST = flags$BooleanFlagField18;
        BooleanFlagField flags$BooleanFlagField19 = FlagField.booleanAfter(flags$BooleanFlagField18);
        Flags.IS_LATEINIT = flags$BooleanFlagField19;
        BooleanFlagField flags$BooleanFlagField20 = FlagField.booleanAfter(flags$BooleanFlagField19);
        Flags.HAS_CONSTANT = flags$BooleanFlagField20;
        BooleanFlagField flags$BooleanFlagField21 = FlagField.booleanAfter(flags$BooleanFlagField20);
        Flags.IS_EXTERNAL_PROPERTY = flags$BooleanFlagField21;
        BooleanFlagField flags$BooleanFlagField22 = FlagField.booleanAfter(flags$BooleanFlagField21);
        Flags.IS_DELEGATED = flags$BooleanFlagField22;
        Flags.IS_EXPECT_PROPERTY = FlagField.booleanAfter(flags$BooleanFlagField22);
        BooleanFlagField flags$BooleanFlagField23 = FlagField.booleanAfter(flags$BooleanFlagField1);
        Flags.DECLARES_DEFAULT_VALUE = flags$BooleanFlagField23;
        BooleanFlagField flags$BooleanFlagField24 = FlagField.booleanAfter(flags$BooleanFlagField23);
        Flags.IS_CROSSINLINE = flags$BooleanFlagField24;
        Flags.IS_NOINLINE = FlagField.booleanAfter(flags$BooleanFlagField24);
        BooleanFlagField flags$BooleanFlagField25 = FlagField.booleanAfter(flags$FlagField1);
        Flags.IS_NOT_DEFAULT = flags$BooleanFlagField25;
        BooleanFlagField flags$BooleanFlagField26 = FlagField.booleanAfter(flags$BooleanFlagField25);
        Flags.IS_EXTERNAL_ACCESSOR = flags$BooleanFlagField26;
        Flags.IS_INLINE_ACCESSOR = FlagField.booleanAfter(flags$BooleanFlagField26);
        BooleanFlagField flags$BooleanFlagField27 = FlagField.booleanFirst();
        Flags.IS_NEGATED = flags$BooleanFlagField27;
        Flags.IS_NULL_CHECK_PREDICATE = FlagField.booleanAfter(flags$BooleanFlagField27);
        Flags.IS_UNSIGNED = FlagField.booleanFirst();
    }

    public static int getAccessorFlags(boolean z, Visibility protoBuf$Visibility0, Modality protoBuf$Modality0, boolean z1, boolean z2, boolean z3) {
        if(protoBuf$Visibility0 == null) {
            Flags.$$$reportNull$$$0(10);
        }
        if(protoBuf$Modality0 == null) {
            Flags.$$$reportNull$$$0(11);
        }
        return Flags.HAS_ANNOTATIONS.toFlags(Boolean.valueOf(z)) | Flags.MODALITY.toFlags(protoBuf$Modality0) | Flags.VISIBILITY.toFlags(protoBuf$Visibility0) | Flags.IS_NOT_DEFAULT.toFlags(Boolean.valueOf(z1)) | Flags.IS_EXTERNAL_ACCESSOR.toFlags(Boolean.valueOf(z2)) | Flags.IS_INLINE_ACCESSOR.toFlags(Boolean.valueOf(z3));
    }

    class kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.1 {
    }

}

