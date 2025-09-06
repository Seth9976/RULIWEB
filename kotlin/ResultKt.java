package kotlin;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000F\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001A\u0010\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\u0001\u001A.\u0010\u0004\u001A\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u00062\f\u0010\u0007\u001A\b\u0012\u0004\u0012\u0002H\u00060\bH\u0087\b\u00F8\u0001\u0000\u00F8\u0001\u0001\u00A2\u0006\u0002\u0010\t\u001A\u0087\u0001\u0010\n\u001A\u0002H\u0006\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u000B*\b\u0012\u0004\u0012\u0002H\u000B0\u00052!\u0010\f\u001A\u001D\u0012\u0013\u0012\u0011H\u000B\u00A2\u0006\f\b\u000E\u0012\b\b\u000F\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u0002H\u00060\r2!\u0010\u0011\u001A\u001D\u0012\u0013\u0012\u00110\u0003\u00A2\u0006\f\b\u000E\u0012\b\b\u000F\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u0002H\u00060\rH\u0087\b\u00F8\u0001\u0000\u00F8\u0001\u0001\u0082\u0002\u0014\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0000\u00A2\u0006\u0002\u0010\u0012\u001A3\u0010\u0013\u001A\u0002H\u0006\"\u0004\b\u0000\u0010\u0006\"\b\b\u0001\u0010\u000B*\u0002H\u0006*\b\u0012\u0004\u0012\u0002H\u000B0\u00052\u0006\u0010\u0014\u001A\u0002H\u0006H\u0087\b\u00F8\u0001\u0001\u00A2\u0006\u0002\u0010\u0015\u001A^\u0010\u0016\u001A\u0002H\u0006\"\u0004\b\u0000\u0010\u0006\"\b\b\u0001\u0010\u000B*\u0002H\u0006*\b\u0012\u0004\u0012\u0002H\u000B0\u00052!\u0010\u0011\u001A\u001D\u0012\u0013\u0012\u00110\u0003\u00A2\u0006\f\b\u000E\u0012\b\b\u000F\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u0002H\u00060\rH\u0087\b\u00F8\u0001\u0000\u00F8\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000\u00A2\u0006\u0002\u0010\u0017\u001A!\u0010\u0018\u001A\u0002H\u000B\"\u0004\b\u0000\u0010\u000B*\b\u0012\u0004\u0012\u0002H\u000B0\u0005H\u0087\b\u00F8\u0001\u0001\u00A2\u0006\u0002\u0010\u0019\u001A`\u0010\u001A\u001A\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u000B*\b\u0012\u0004\u0012\u0002H\u000B0\u00052!\u0010\u001B\u001A\u001D\u0012\u0013\u0012\u0011H\u000B\u00A2\u0006\f\b\u000E\u0012\b\b\u000F\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u0002H\u00060\rH\u0087\b\u00F8\u0001\u0000\u00F8\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000\u00A2\u0006\u0002\u0010\u0017\u001AS\u0010\u001C\u001A\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u000B*\b\u0012\u0004\u0012\u0002H\u000B0\u00052!\u0010\u001B\u001A\u001D\u0012\u0013\u0012\u0011H\u000B\u00A2\u0006\f\b\u000E\u0012\b\b\u000F\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u0002H\u00060\rH\u0087\b\u00F8\u0001\u0000\u00F8\u0001\u0001\u00A2\u0006\u0002\u0010\u0017\u001AZ\u0010\u0011\u001A\b\u0012\u0004\u0012\u0002H\u000B0\u0005\"\u0004\b\u0000\u0010\u000B*\b\u0012\u0004\u0012\u0002H\u000B0\u00052!\u0010\u001D\u001A\u001D\u0012\u0013\u0012\u00110\u0003\u00A2\u0006\f\b\u000E\u0012\b\b\u000F\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u00020\u001E0\rH\u0087\b\u00F8\u0001\u0000\u00F8\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000\u00A2\u0006\u0002\u0010\u0017\u001AZ\u0010\f\u001A\b\u0012\u0004\u0012\u0002H\u000B0\u0005\"\u0004\b\u0000\u0010\u000B*\b\u0012\u0004\u0012\u0002H\u000B0\u00052!\u0010\u001D\u001A\u001D\u0012\u0013\u0012\u0011H\u000B\u00A2\u0006\f\b\u000E\u0012\b\b\u000F\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u001E0\rH\u0087\b\u00F8\u0001\u0000\u00F8\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000\u00A2\u0006\u0002\u0010\u0017\u001Ad\u0010\u001F\u001A\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u0006\"\b\b\u0001\u0010\u000B*\u0002H\u0006*\b\u0012\u0004\u0012\u0002H\u000B0\u00052!\u0010\u001B\u001A\u001D\u0012\u0013\u0012\u00110\u0003\u00A2\u0006\f\b\u000E\u0012\b\b\u000F\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u0002H\u00060\rH\u0087\b\u00F8\u0001\u0000\u00F8\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000\u00A2\u0006\u0002\u0010\u0017\u001AW\u0010 \u001A\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u0006\"\b\b\u0001\u0010\u000B*\u0002H\u0006*\b\u0012\u0004\u0012\u0002H\u000B0\u00052!\u0010\u001B\u001A\u001D\u0012\u0013\u0012\u00110\u0003\u00A2\u0006\f\b\u000E\u0012\b\b\u000F\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u0002H\u00060\rH\u0087\b\u00F8\u0001\u0000\u00F8\u0001\u0001\u00A2\u0006\u0002\u0010\u0017\u001AC\u0010\u0004\u001A\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u000B\"\u0004\b\u0001\u0010\u0006*\u0002H\u000B2\u0017\u0010\u0007\u001A\u0013\u0012\u0004\u0012\u0002H\u000B\u0012\u0004\u0012\u0002H\u00060\r\u00A2\u0006\u0002\b!H\u0087\b\u00F8\u0001\u0000\u00F8\u0001\u0001\u00A2\u0006\u0002\u0010\u0017\u001A\u0018\u0010\"\u001A\u00020\u001E*\u0006\u0012\u0002\b\u00030\u0005H\u0001\u00F8\u0001\u0001\u00A2\u0006\u0002\u0010#\u0082\u0002\u000B\n\u0005\b\u009920\u0001\n\u0002\b\u0019\u00A8\u0006$"}, d2 = {"createFailure", "", "exception", "", "runCatching", "Lkotlin/Result;", "R", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "fold", "T", "onSuccess", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "value", "onFailure", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "getOrDefault", "defaultValue", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "getOrElse", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "getOrThrow", "(Ljava/lang/Object;)Ljava/lang/Object;", "map", "transform", "mapCatching", "action", "", "recover", "recoverCatching", "Lkotlin/ExtensionFunctionType;", "throwOnFailure", "(Ljava/lang/Object;)V", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class ResultKt {
    public static final Object createFailure(Throwable throwable0) {
        Intrinsics.checkNotNullParameter(throwable0, "exception");
        return new Failure(throwable0);
    }

    private static final Object fold(Object object0, Function1 function10, Function1 function11) {
        Intrinsics.checkNotNullParameter(function10, "onSuccess");
        Intrinsics.checkNotNullParameter(function11, "onFailure");
        Throwable throwable0 = Result.exceptionOrNull-impl(object0);
        return throwable0 == null ? function10.invoke(object0) : function11.invoke(throwable0);
    }

    // 去混淆评级： 低(20)
    private static final Object getOrDefault(Object object0, Object object1) {
        return Result.isFailure-impl(object0) ? object1 : object0;
    }

    private static final Object getOrElse(Object object0, Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "onFailure");
        Throwable throwable0 = Result.exceptionOrNull-impl(object0);
        return throwable0 == null ? object0 : function10.invoke(throwable0);
    }

    private static final Object getOrThrow(Object object0) {
        ResultKt.throwOnFailure(object0);
        return object0;
    }

    private static final Object map(Object object0, Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "transform");
        return Result.isSuccess-impl(object0) ? Result.constructor-impl(function10.invoke(object0)) : object0;
    }

    private static final Object mapCatching(Object object0, Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "transform");
        if(Result.isSuccess-impl(object0)) {
            try {
                return Result.constructor-impl(function10.invoke(object0));
            }
            catch(Throwable throwable0) {
                return Result.constructor-impl(ResultKt.createFailure(throwable0));
            }
        }
        return object0;
    }

    private static final Object onFailure(Object object0, Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "action");
        Throwable throwable0 = Result.exceptionOrNull-impl(object0);
        if(throwable0 != null) {
            function10.invoke(throwable0);
        }
        return object0;
    }

    private static final Object onSuccess(Object object0, Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "action");
        if(Result.isSuccess-impl(object0)) {
            function10.invoke(object0);
        }
        return object0;
    }

    private static final Object recover(Object object0, Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "transform");
        Throwable throwable0 = Result.exceptionOrNull-impl(object0);
        return throwable0 == null ? object0 : Result.constructor-impl(function10.invoke(throwable0));
    }

    private static final Object recoverCatching(Object object0, Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "transform");
        Throwable throwable0 = Result.exceptionOrNull-impl(object0);
        if(throwable0 == null) {
            return object0;
        }
        try {
            return Result.constructor-impl(function10.invoke(throwable0));
        }
        catch(Throwable throwable1) {
            return Result.constructor-impl(ResultKt.createFailure(throwable1));
        }
    }

    private static final Object runCatching(Object object0, Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "block");
        try {
            return Result.constructor-impl(function10.invoke(object0));
        }
        catch(Throwable throwable0) {
            return Result.constructor-impl(ResultKt.createFailure(throwable0));
        }
    }

    private static final Object runCatching(Function0 function00) {
        Intrinsics.checkNotNullParameter(function00, "block");
        try {
            return Result.constructor-impl(function00.invoke());
        }
        catch(Throwable throwable0) {
            return Result.constructor-impl(ResultKt.createFailure(throwable0));
        }
    }

    public static final void throwOnFailure(Object object0) {
        if(object0 instanceof Failure) {
            throw ((Failure)object0).exception;
        }
    }
}

