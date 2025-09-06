package kotlin.reflect.jvm.internal.impl.builtins.functions;

import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.text.StringsKt;

public enum FunctionClassKind {
    public static final class Companion {
        public static final class KindWithArity {
            private final int arity;
            private final FunctionClassKind kind;

            public KindWithArity(FunctionClassKind functionClassKind0, int v) {
                Intrinsics.checkNotNullParameter(functionClassKind0, "kind");
                super();
                this.kind = functionClassKind0;
                this.arity = v;
            }

            public final FunctionClassKind component1() {
                return this.kind;
            }

            public final int component2() {
                return this.arity;
            }

            @Override
            public boolean equals(Object object0) {
                if(this == object0) {
                    return true;
                }
                if(!(object0 instanceof KindWithArity)) {
                    return false;
                }
                return this.kind == ((KindWithArity)object0).kind ? this.arity == ((KindWithArity)object0).arity : false;
            }

            public final FunctionClassKind getKind() {
                return this.kind;
            }

            @Override
            public int hashCode() {
                return this.kind.hashCode() * 0x1F + this.arity;
            }

            @Override
            public String toString() {
                return "KindWithArity(kind=" + this.kind + ", arity=" + this.arity + ')';
            }
        }

        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final FunctionClassKind byClassNamePrefix(FqName fqName0, String s) {
            Intrinsics.checkNotNullParameter(fqName0, "packageFqName");
            Intrinsics.checkNotNullParameter(s, "className");
            FunctionClassKind[] arr_functionClassKind = FunctionClassKind.values();
            for(int v = 0; v < arr_functionClassKind.length; ++v) {
                FunctionClassKind functionClassKind0 = arr_functionClassKind[v];
                if(Intrinsics.areEqual(functionClassKind0.getPackageFqName(), fqName0) && StringsKt.startsWith$default(s, functionClassKind0.getClassNamePrefix(), false, 2, null)) {
                    return functionClassKind0;
                }
            }
            return null;
        }

        @JvmStatic
        public final FunctionClassKind getFunctionalClassKind(String s, FqName fqName0) {
            Intrinsics.checkNotNullParameter(s, "className");
            Intrinsics.checkNotNullParameter(fqName0, "packageFqName");
            KindWithArity functionClassKind$Companion$KindWithArity0 = this.parseClassName(s, fqName0);
            return functionClassKind$Companion$KindWithArity0 == null ? null : functionClassKind$Companion$KindWithArity0.getKind();
        }

        public final KindWithArity parseClassName(String s, FqName fqName0) {
            Intrinsics.checkNotNullParameter(s, "className");
            Intrinsics.checkNotNullParameter(fqName0, "packageFqName");
            FunctionClassKind functionClassKind0 = this.byClassNamePrefix(fqName0, s);
            if(functionClassKind0 == null) {
                return null;
            }
            String s1 = s.substring(functionClassKind0.getClassNamePrefix().length());
            Intrinsics.checkNotNullExpressionValue(s1, "this as java.lang.String).substring(startIndex)");
            Integer integer0 = this.toInt(s1);
            return integer0 == null ? null : new KindWithArity(functionClassKind0, ((int)integer0));
        }

        private final Integer toInt(String s) {
            if(s.length() == 0) {
                return null;
            }
            int v = s.length();
            int v1 = 0;
            int v2 = 0;
            while(v1 < v) {
                int v3 = s.charAt(v1);
                if(v3 - 0x30 >= 0 && v3 - 0x30 < 10) {
                    v2 = v2 * 10 + (v3 - 0x30);
                    ++v1;
                    continue;
                }
                return null;
            }
            return v2;
        }
    }

    Function(StandardNames.BUILT_INS_PACKAGE_FQ_NAME, "Function", false, false),
    SuspendFunction(StandardNames.COROUTINES_PACKAGE_FQ_NAME, "SuspendFunction", true, false),
    KFunction(StandardNames.KOTLIN_REFLECT_FQ_NAME, "KFunction", false, true),
    KSuspendFunction(StandardNames.KOTLIN_REFLECT_FQ_NAME, "KSuspendFunction", true, true);

    public static final Companion Companion;
    private final String classNamePrefix;
    private final boolean isReflectType;
    private final boolean isSuspendType;
    private final FqName packageFqName;

    private static final FunctionClassKind[] $values() [...] // Inlined contents

    static {
        FunctionClassKind.Companion = new Companion(null);
    }

    private FunctionClassKind(FqName fqName0, String s1, boolean z, boolean z1) {
        this.packageFqName = fqName0;
        this.classNamePrefix = s1;
        this.isSuspendType = z;
        this.isReflectType = z1;
    }

    public final String getClassNamePrefix() {
        return this.classNamePrefix;
    }

    public final FqName getPackageFqName() {
        return this.packageFqName;
    }

    public final Name numberedClassName(int v) {
        Name name0 = Name.identifier((this.classNamePrefix + v));
        Intrinsics.checkNotNullExpressionValue(name0, "identifier(\"$classNamePrefix$arity\")");
        return name0;
    }
}

