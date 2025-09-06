package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class SignatureBuildingComponents {
    public static final SignatureBuildingComponents INSTANCE;

    static {
        SignatureBuildingComponents.INSTANCE = new SignatureBuildingComponents();
    }

    public final String[] constructors(String[] arr_s) {
        Intrinsics.checkNotNullParameter(arr_s, "signatures");
        ArrayList arrayList0 = new ArrayList(arr_s.length);
        for(int v = 0; v < arr_s.length; ++v) {
            arrayList0.add("<init>(" + arr_s[v] + ")V");
        }
        return (String[])arrayList0.toArray(new String[0]);
    }

    private final String escapeClassName(String s) {
        return s.length() <= 1 ? s : "L" + s + ';';
    }

    public final Set inClass(String s, String[] arr_s) {
        Intrinsics.checkNotNullParameter(s, "internalName");
        Intrinsics.checkNotNullParameter(arr_s, "signatures");
        Collection collection0 = new LinkedHashSet();
        for(int v = 0; v < arr_s.length; ++v) {
            collection0.add(s + '.' + arr_s[v]);
        }
        return (Set)collection0;
    }

    public final Set inJavaLang(String s, String[] arr_s) {
        Intrinsics.checkNotNullParameter(s, "name");
        Intrinsics.checkNotNullParameter(arr_s, "signatures");
        return this.inClass(this.javaLang(s), ((String[])Arrays.copyOf(arr_s, arr_s.length)));
    }

    public final Set inJavaUtil(String s, String[] arr_s) {
        Intrinsics.checkNotNullParameter(s, "name");
        Intrinsics.checkNotNullParameter(arr_s, "signatures");
        return this.inClass(this.javaUtil(s), ((String[])Arrays.copyOf(arr_s, arr_s.length)));
    }

    public final String javaFunction(String s) [...] // 潜在的解密器

    public final String javaLang(String s) [...] // 潜在的解密器

    public final String javaUtil(String s) [...] // 潜在的解密器

    public final String jvmDescriptor(String s, List list0, String s1) {
        Intrinsics.checkNotNullParameter(s, "name");
        Intrinsics.checkNotNullParameter(list0, "parameters");
        Intrinsics.checkNotNullParameter(s1, "ret");
        return s + '(' + CollectionsKt.joinToString$default(list0, "", null, null, 0, null, kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents.jvmDescriptor.1.INSTANCE, 30, null) + ')' + this.escapeClassName(s1);

        final class kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents.jvmDescriptor.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents.jvmDescriptor.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents.jvmDescriptor.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents.jvmDescriptor.1();
            }

            kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents.jvmDescriptor.1() {
                super(1);
            }

            public final CharSequence invoke(String s) {
                Intrinsics.checkNotNullParameter(s, "it");
                return SignatureBuildingComponents.INSTANCE.escapeClassName(s);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((String)object0));
            }
        }

    }

    public final String signature(String s, String s1) {
        Intrinsics.checkNotNullParameter(s, "internalName");
        Intrinsics.checkNotNullParameter(s1, "jvmDescriptor");
        return s + '.' + s1;
    }
}

