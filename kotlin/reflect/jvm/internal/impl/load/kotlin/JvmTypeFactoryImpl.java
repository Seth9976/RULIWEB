package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import kotlin.text.StringsKt;

final class JvmTypeFactoryImpl implements JvmTypeFactory {
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] arr_v = new int[PrimitiveType.values().length];
            try {
                arr_v[PrimitiveType.BOOLEAN.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[PrimitiveType.CHAR.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[PrimitiveType.BYTE.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[PrimitiveType.SHORT.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[PrimitiveType.INT.ordinal()] = 5;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[PrimitiveType.FLOAT.ordinal()] = 6;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[PrimitiveType.LONG.ordinal()] = 7;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[PrimitiveType.DOUBLE.ordinal()] = 8;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$0 = arr_v;
        }
    }

    public static final JvmTypeFactoryImpl INSTANCE;

    static {
        JvmTypeFactoryImpl.INSTANCE = new JvmTypeFactoryImpl();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.JvmTypeFactory
    public Object boxType(Object object0) {
        return this.boxType(((JvmType)object0));
    }

    public JvmType boxType(JvmType jvmType0) {
        Intrinsics.checkNotNullParameter(jvmType0, "possiblyPrimitiveType");
        if(jvmType0 instanceof Primitive && ((Primitive)jvmType0).getJvmPrimitiveType() != null) {
            String s = JvmClassName.byFqNameWithoutInnerClasses(((Primitive)jvmType0).getJvmPrimitiveType().getWrapperFqName()).getInternalName();
            Intrinsics.checkNotNullExpressionValue(s, "byFqNameWithoutInnerClas…apperFqName).internalName");
            return this.createObjectType(s);
        }
        return jvmType0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.JvmTypeFactory
    public Object createFromString(String s) {
        return this.createFromString(s);
    }

    public JvmType createFromString(String s) {
        JvmPrimitiveType jvmPrimitiveType0;
        Intrinsics.checkNotNullParameter(s, "representation");
        s.length();
        int v = s.charAt(0);
        JvmPrimitiveType[] arr_jvmPrimitiveType = JvmPrimitiveType.values();
        for(int v1 = 0; true; ++v1) {
            jvmPrimitiveType0 = null;
            if(v1 >= arr_jvmPrimitiveType.length) {
                break;
            }
            JvmPrimitiveType jvmPrimitiveType1 = arr_jvmPrimitiveType[v1];
            if(jvmPrimitiveType1.getDesc().charAt(0) == v) {
                jvmPrimitiveType0 = jvmPrimitiveType1;
                break;
            }
        }
        if(jvmPrimitiveType0 != null) {
            return new Primitive(jvmPrimitiveType0);
        }
        switch(v) {
            case 76: {
                StringsKt.endsWith$default(s, ';', false, 2, null);
                break;
            }
            case 86: {
                return new Primitive(null);
            }
            case 91: {
                String s1 = s.substring(1);
                Intrinsics.checkNotNullExpressionValue(s1, "this as java.lang.String).substring(startIndex)");
                return new Array(this.createFromString(s1));
            }
        }
        String s2 = s.substring(1, s.length() - 1);
        Intrinsics.checkNotNullExpressionValue(s2, "this as java.lang.String…ing(startIndex, endIndex)");
        return new kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType.Object(s2);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.JvmTypeFactory
    public Object createObjectType(String s) {
        return this.createObjectType(s);
    }

    public kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType.Object createObjectType(String s) {
        Intrinsics.checkNotNullParameter(s, "internalName");
        return new kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType.Object(s);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.JvmTypeFactory
    public Object createPrimitiveType(PrimitiveType primitiveType0) {
        return this.createPrimitiveType(primitiveType0);
    }

    public JvmType createPrimitiveType(PrimitiveType primitiveType0) {
        Intrinsics.checkNotNullParameter(primitiveType0, "primitiveType");
        switch(WhenMappings.$EnumSwitchMapping$0[primitiveType0.ordinal()]) {
            case 1: {
                return JvmType.Companion.getBOOLEAN$descriptors_jvm();
            }
            case 2: {
                return JvmType.Companion.getCHAR$descriptors_jvm();
            }
            case 3: {
                return JvmType.Companion.getBYTE$descriptors_jvm();
            }
            case 4: {
                return JvmType.Companion.getSHORT$descriptors_jvm();
            }
            case 5: {
                return JvmType.Companion.getINT$descriptors_jvm();
            }
            case 6: {
                return JvmType.Companion.getFLOAT$descriptors_jvm();
            }
            case 7: {
                return JvmType.Companion.getLONG$descriptors_jvm();
            }
            case 8: {
                return JvmType.Companion.getDOUBLE$descriptors_jvm();
            }
            default: {
                throw new NoWhenBranchMatchedException();
            }
        }
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.JvmTypeFactory
    public Object getJavaLangClassType() {
        return this.getJavaLangClassType();
    }

    public JvmType getJavaLangClassType() {
        return this.createObjectType("java/lang/Class");
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.JvmTypeFactory
    public String toString(Object object0) {
        return this.toString(((JvmType)object0));
    }

    public String toString(JvmType jvmType0) {
        Intrinsics.checkNotNullParameter(jvmType0, "type");
        if(jvmType0 instanceof Array) {
            return "[" + this.toString(((Array)jvmType0).getElementType());
        }
        if(jvmType0 instanceof Primitive) {
            JvmPrimitiveType jvmPrimitiveType0 = ((Primitive)jvmType0).getJvmPrimitiveType();
            if(jvmPrimitiveType0 != null) {
                String s = jvmPrimitiveType0.getDesc();
                return s == null ? "V" : s;
            }
            return "V";
        }
        if(!(jvmType0 instanceof kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType.Object)) {
            throw new NoWhenBranchMatchedException();
        }
        return "L" + ((kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType.Object)jvmType0).getInternalName() + ';';
    }
}

