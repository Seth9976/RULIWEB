package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.util.capitalizeDecapitalize.CapitalizeDecapitalizeKt;
import kotlin.text.StringsKt;

public final class PropertiesConventionUtilKt {
    public static final List getPropertyNamesCandidatesByAccessorName(Name name0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        String s = name0.asString();
        Intrinsics.checkNotNullExpressionValue(s, "name.asString()");
        if(JvmAbi.isGetterName(s)) {
            return CollectionsKt.listOfNotNull(PropertiesConventionUtilKt.propertyNameByGetMethodName(name0));
        }
        return JvmAbi.isSetterName(s) ? PropertiesConventionUtilKt.propertyNamesBySetMethodName(name0) : BuiltinSpecialProperties.INSTANCE.getPropertyNameCandidatesBySpecialGetterName(name0);
    }

    public static final Name propertyNameByGetMethodName(Name name0) {
        Intrinsics.checkNotNullParameter(name0, "methodName");
        Name name1 = PropertiesConventionUtilKt.propertyNameFromAccessorMethodName$default(name0, "get", false, null, 12, null);
        return name1 == null ? PropertiesConventionUtilKt.propertyNameFromAccessorMethodName$default(name0, "is", false, null, 8, null) : name1;
    }

    public static final Name propertyNameBySetMethodName(Name name0, boolean z) {
        Intrinsics.checkNotNullParameter(name0, "methodName");
        return z ? PropertiesConventionUtilKt.propertyNameFromAccessorMethodName$default(name0, "set", false, "is", 4, null) : PropertiesConventionUtilKt.propertyNameFromAccessorMethodName$default(name0, "set", false, null, 4, null);
    }

    private static final Name propertyNameFromAccessorMethodName(Name name0, String s, boolean z, String s1) {
        if(name0.isSpecial()) {
            return null;
        }
        String s2 = name0.getIdentifier();
        Intrinsics.checkNotNullExpressionValue(s2, "methodName.identifier");
        if(!StringsKt.startsWith$default(s2, s, false, 2, null)) {
            return null;
        }
        if(s2.length() == s.length()) {
            return null;
        }
        int v = s2.charAt(s.length());
        if(97 <= v && v < 0x7B) {
            return null;
        }
        if(s1 != null) {
            return Name.identifier((s1 + StringsKt.removePrefix(s2, s)));
        }
        if(!z) {
            return name0;
        }
        String s3 = CapitalizeDecapitalizeKt.decapitalizeSmartForCompiler(StringsKt.removePrefix(s2, s), true);
        return Name.isValidIdentifier(s3) ? Name.identifier(s3) : null;
    }

    static Name propertyNameFromAccessorMethodName$default(Name name0, String s, boolean z, String s1, int v, Object object0) {
        if((v & 4) != 0) {
            z = true;
        }
        if((v & 8) != 0) {
            s1 = null;
        }
        return PropertiesConventionUtilKt.propertyNameFromAccessorMethodName(name0, s, z, s1);
    }

    public static final List propertyNamesBySetMethodName(Name name0) {
        Intrinsics.checkNotNullParameter(name0, "methodName");
        return CollectionsKt.listOfNotNull(new Name[]{PropertiesConventionUtilKt.propertyNameBySetMethodName(name0, false), PropertiesConventionUtilKt.propertyNameBySetMethodName(name0, true)});
    }
}

