package kotlin.reflect.jvm.internal.impl.util;

import java.util.Arrays;
import java.util.Collection;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.text.Regex;

public final class Checks {
    private final Function1 additionalCheck;
    private final Check[] checks;
    private final Name name;
    private final Collection nameList;
    private final Regex regex;

    public Checks(Collection collection0, Check[] arr_check, Function1 function10) {
        Intrinsics.checkNotNullParameter(collection0, "nameList");
        Intrinsics.checkNotNullParameter(arr_check, "checks");
        Intrinsics.checkNotNullParameter(function10, "additionalChecks");
        this(null, null, collection0, function10, ((Check[])Arrays.copyOf(arr_check, arr_check.length)));
    }

    public Checks(Collection collection0, Check[] arr_check, Function1 function10, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 4) != 0) {
            function10 = kotlin.reflect.jvm.internal.impl.util.Checks.4.INSTANCE;
        }
        this(collection0, arr_check, function10);

        final class kotlin.reflect.jvm.internal.impl.util.Checks.4 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.util.Checks.4 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.util.Checks.4.INSTANCE = new kotlin.reflect.jvm.internal.impl.util.Checks.4();
            }

            kotlin.reflect.jvm.internal.impl.util.Checks.4() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((FunctionDescriptor)object0));
            }

            public final Void invoke(FunctionDescriptor functionDescriptor0) {
                Intrinsics.checkNotNullParameter(functionDescriptor0, "$this$null");
                return null;
            }
        }

    }

    private Checks(Name name0, Regex regex0, Collection collection0, Function1 function10, Check[] arr_check) {
        this.name = name0;
        this.regex = regex0;
        this.nameList = collection0;
        this.additionalCheck = function10;
        this.checks = arr_check;
    }

    public Checks(Name name0, Check[] arr_check, Function1 function10) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(arr_check, "checks");
        Intrinsics.checkNotNullParameter(function10, "additionalChecks");
        this(name0, null, null, function10, ((Check[])Arrays.copyOf(arr_check, arr_check.length)));
    }

    public Checks(Name name0, Check[] arr_check, Function1 function10, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 4) != 0) {
            function10 = kotlin.reflect.jvm.internal.impl.util.Checks.2.INSTANCE;
        }
        this(name0, arr_check, function10);

        final class kotlin.reflect.jvm.internal.impl.util.Checks.2 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.util.Checks.2 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.util.Checks.2.INSTANCE = new kotlin.reflect.jvm.internal.impl.util.Checks.2();
            }

            kotlin.reflect.jvm.internal.impl.util.Checks.2() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((FunctionDescriptor)object0));
            }

            public final Void invoke(FunctionDescriptor functionDescriptor0) {
                Intrinsics.checkNotNullParameter(functionDescriptor0, "$this$null");
                return null;
            }
        }

    }

    public Checks(Regex regex0, Check[] arr_check, Function1 function10) {
        Intrinsics.checkNotNullParameter(regex0, "regex");
        Intrinsics.checkNotNullParameter(arr_check, "checks");
        Intrinsics.checkNotNullParameter(function10, "additionalChecks");
        this(null, regex0, null, function10, ((Check[])Arrays.copyOf(arr_check, arr_check.length)));
    }

    public Checks(Regex regex0, Check[] arr_check, Function1 function10, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 4) != 0) {
            function10 = kotlin.reflect.jvm.internal.impl.util.Checks.3.INSTANCE;
        }
        this(regex0, arr_check, function10);

        final class kotlin.reflect.jvm.internal.impl.util.Checks.3 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.util.Checks.3 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.util.Checks.3.INSTANCE = new kotlin.reflect.jvm.internal.impl.util.Checks.3();
            }

            kotlin.reflect.jvm.internal.impl.util.Checks.3() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((FunctionDescriptor)object0));
            }

            public final Void invoke(FunctionDescriptor functionDescriptor0) {
                Intrinsics.checkNotNullParameter(functionDescriptor0, "$this$null");
                return null;
            }
        }

    }

    public final CheckResult checkAll(FunctionDescriptor functionDescriptor0) {
        Intrinsics.checkNotNullParameter(functionDescriptor0, "functionDescriptor");
        Check[] arr_check = this.checks;
        for(int v = 0; v < arr_check.length; ++v) {
            String s = arr_check[v].invoke(functionDescriptor0);
            if(s != null) {
                return new IllegalSignature(s);
            }
        }
        String s1 = (String)this.additionalCheck.invoke(functionDescriptor0);
        return s1 != null ? new IllegalSignature(s1) : SuccessCheck.INSTANCE;
    }

    public final boolean isApplicable(FunctionDescriptor functionDescriptor0) {
        Intrinsics.checkNotNullParameter(functionDescriptor0, "functionDescriptor");
        if(this.name != null && !Intrinsics.areEqual(functionDescriptor0.getName(), this.name)) {
            return false;
        }
        if(this.regex != null) {
            String s = functionDescriptor0.getName().asString();
            Intrinsics.checkNotNullExpressionValue(s, "functionDescriptor.name.asString()");
            return this.regex.matches(s) ? this.nameList == null || this.nameList.contains(functionDescriptor0.getName()) : false;
        }
        return this.nameList == null || this.nameList.contains(functionDescriptor0.getName());
    }
}

