package kotlin.reflect.jvm.internal.impl.resolve.constants;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;

public final class CharValue extends IntegerValueConstant {
    public CharValue(char c) {
        super(Character.valueOf(c));
    }

    private final String getPrintablePart(char c) {
        switch(c) {
            case 8: {
                return "\\b";
            }
            case 9: {
                return "\\t";
            }
            case 10: {
                return "\\n";
            }
            case 12: {
                return "\\f";
            }
            case 13: {
                return "\\r";
            }
            default: {
                return this.isPrintableUnicode(c) ? String.valueOf(c) : "?";
            }
        }
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue
    public KotlinType getType(ModuleDescriptor moduleDescriptor0) {
        return this.getType(moduleDescriptor0);
    }

    public SimpleType getType(ModuleDescriptor moduleDescriptor0) {
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "module");
        SimpleType simpleType0 = moduleDescriptor0.getBuiltIns().getCharType();
        Intrinsics.checkNotNullExpressionValue(simpleType0, "module.builtIns.charType");
        return simpleType0;
    }

    private final boolean isPrintableUnicode(char c) {
        switch(((byte)Character.getType(c))) {
            case 0: 
            case 13: 
            case 14: 
            case 15: 
            case 16: 
            case 18: 
            case 19: {
                return false;
            }
            default: {
                return true;
            }
        }
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue
    public String toString() {
        String s = String.format("\\u%04X (\'%s\')", Arrays.copyOf(new Object[]{((int)((Character)this.getValue()).charValue()), this.getPrintablePart(((Character)this.getValue()).charValue())}, 2));
        Intrinsics.checkNotNullExpressionValue(s, "format(this, *args)");
        return s;
    }
}

