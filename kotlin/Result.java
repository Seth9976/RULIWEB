package kotlin;

import java.io.Serializable;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u000B\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000E\n\u0002\b\u0005\b\u0087@\u0018\u0000 \"*\u0006\b\u0000\u0010\u0001 \u00012\u00060\u0002j\u0002`\u0003:\u0002\"#B\u0016\b\u0001\u0012\b\u0010\u0004\u001A\u0004\u0018\u00010\u0005ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007J\u001A\u0010\u0010\u001A\u00020\t2\b\u0010\u0011\u001A\u0004\u0018\u00010\u0005HÖ\u0003¢\u0006\u0004\b\u0012\u0010\u0013J\u000F\u0010\u0014\u001A\u0004\u0018\u00010\u0015¢\u0006\u0004\b\u0016\u0010\u0017J\u0012\u0010\u0018\u001A\u0004\u0018\u00018\u0000H\u0087\b¢\u0006\u0004\b\u0019\u0010\u0007J\u0010\u0010\u001A\u001A\u00020\u001BHÖ\u0001¢\u0006\u0004\b\u001C\u0010\u001DJ\u000F\u0010\u001E\u001A\u00020\u001FH\u0016¢\u0006\u0004\b \u0010!R\u0011\u0010\b\u001A\u00020\t8F¢\u0006\u0006\u001A\u0004\b\n\u0010\u000BR\u0011\u0010\f\u001A\u00020\t8F¢\u0006\u0006\u001A\u0004\b\r\u0010\u000BR\u0018\u0010\u0004\u001A\u0004\u0018\u00010\u00058\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u000E\u0010\u000F\u0088\u0001\u0004\u0092\u0001\u0004\u0018\u00010\u0005ø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006$"}, d2 = {"Lkotlin/Result;", "T", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "value", "", "constructor-impl", "(Ljava/lang/Object;)Ljava/lang/Object;", "isFailure", "", "isFailure-impl", "(Ljava/lang/Object;)Z", "isSuccess", "isSuccess-impl", "getValue$annotations", "()V", "equals", "other", "equals-impl", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "exceptionOrNull", "", "exceptionOrNull-impl", "(Ljava/lang/Object;)Ljava/lang/Throwable;", "getOrNull", "getOrNull-impl", "hashCode", "", "hashCode-impl", "(Ljava/lang/Object;)I", "toString", "", "toString-impl", "(Ljava/lang/Object;)Ljava/lang/String;", "Companion", "Failure", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
@JvmInline
public final class Result implements Serializable {
    @Metadata(d1 = {"\u0000\u001C\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J%\u0010\u0003\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\u0006\u0010\u0006\u001A\u00020\u0007H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\bJ%\u0010\t\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\u0006\u0010\n\u001A\u0002H\u0005H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u000B\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"Lkotlin/Result$Companion;", "", "()V", "failure", "Lkotlin/Result;", "T", "exception", "", "(Ljava/lang/Throwable;)Ljava/lang/Object;", "success", "value", "(Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        private final Object failure(Throwable throwable0) {
            Intrinsics.checkNotNullParameter(throwable0, "exception");
            return Result.constructor-impl(ResultKt.createFailure(throwable0));
        }

        private final Object success(Object object0) {
            return object0;
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0000\b\u0000\u0018\u00002\u00060\u0001j\u0002`\u0002B\r\u0012\u0006\u0010\u0003\u001A\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0013\u0010\u0006\u001A\u00020\u00072\b\u0010\b\u001A\u0004\u0018\u00010\tH\u0096\u0002J\b\u0010\n\u001A\u00020\u000BH\u0016J\b\u0010\f\u001A\u00020\rH\u0016R\u0010\u0010\u0003\u001A\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u000E"}, d2 = {"Lkotlin/Result$Failure;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "exception", "", "(Ljava/lang/Throwable;)V", "equals", "", "other", "", "hashCode", "", "toString", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class Failure implements Serializable {
        public final Throwable exception;

        public Failure(Throwable throwable0) {
            Intrinsics.checkNotNullParameter(throwable0, "exception");
            super();
            this.exception = throwable0;
        }

        // 去混淆评级： 低(20)
        @Override
        public boolean equals(Object object0) {
            return object0 instanceof Failure && Intrinsics.areEqual(this.exception, ((Failure)object0).exception);
        }

        @Override
        public int hashCode() {
            return this.exception.hashCode();
        }

        @Override
        public String toString() {
            return "Failure(" + this.exception + ')';
        }
    }

    public static final Companion Companion;
    private final Object value;

    static {
        Result.Companion = new Companion(null);
    }

    private Result(Object object0) {
        this.value = object0;
    }

    public static final Result box-impl(Object object0) {
        return new Result(object0);
    }

    public static Object constructor-impl(Object object0) [...] // Inlined contents

    @Override
    public boolean equals(Object object0) {
        return Result.equals-impl(this.value, object0);
    }

    // 去混淆评级： 低(20)
    public static boolean equals-impl(Object object0, Object object1) {
        return object1 instanceof Result ? Intrinsics.areEqual(object0, ((Result)object1).unbox-impl()) : false;
    }

    public static final boolean equals-impl0(Object object0, Object object1) {
        return Intrinsics.areEqual(object0, object1);
    }

    // 去混淆评级： 低(20)
    public static final Throwable exceptionOrNull-impl(Object object0) {
        return object0 instanceof Failure ? ((Failure)object0).exception : null;
    }

    // 去混淆评级： 低(20)
    private static final Object getOrNull-impl(Object object0) {
        return Result.isFailure-impl(object0) ? null : object0;
    }

    public static void getValue$annotations() {
    }

    @Override
    public int hashCode() {
        return Result.hashCode-impl(this.value);
    }

    public static int hashCode-impl(Object object0) {
        return object0 == null ? 0 : object0.hashCode();
    }

    public static final boolean isFailure-impl(Object object0) {
        return object0 instanceof Failure;
    }

    public static final boolean isSuccess-impl(Object object0) [...] // 潜在的解密器

    @Override
    public String toString() {
        return Result.toString-impl(this.value);
    }

    // 去混淆评级： 低(20)
    public static String toString-impl(Object object0) {
        return object0 instanceof Failure ? ((Failure)object0).toString() : "Success(" + object0 + ')';
    }

    public final Object unbox-impl() {
        return this.value;
    }
}

