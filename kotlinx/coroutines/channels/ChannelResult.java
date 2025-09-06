package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u000B\n\u0002\b\u000B\n\u0002\u0010\u0003\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000E\n\u0002\b\u0006\b\u0087@\u0018\u0000 %*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002:\u0003$%&B\u0016\b\u0001\u0012\b\u0010\u0003\u001A\u0004\u0018\u00010\u0002ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001A\u0010\u0010\u001A\u00020\t2\b\u0010\u0011\u001A\u0004\u0018\u00010\u0002HÖ\u0003¢\u0006\u0004\b\u0012\u0010\u0013J\u000F\u0010\u0014\u001A\u0004\u0018\u00010\u0015¢\u0006\u0004\b\u0016\u0010\u0017J\u000F\u0010\u0018\u001A\u0004\u0018\u00018\u0000¢\u0006\u0004\b\u0019\u0010\u0005J\r\u0010\u001A\u001A\u00028\u0000¢\u0006\u0004\b\u001B\u0010\u0005J\u0010\u0010\u001C\u001A\u00020\u001DHÖ\u0001¢\u0006\u0004\b\u001E\u0010\u001FJ\u000F\u0010 \u001A\u00020!H\u0016¢\u0006\u0004\b\"\u0010#R\u0018\u0010\u0003\u001A\u0004\u0018\u00010\u00028\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001A\u00020\t8F¢\u0006\u0006\u001A\u0004\b\n\u0010\u000BR\u0011\u0010\f\u001A\u00020\t8F¢\u0006\u0006\u001A\u0004\b\r\u0010\u000BR\u0011\u0010\u000E\u001A\u00020\t8F¢\u0006\u0006\u001A\u0004\b\u000F\u0010\u000B\u0088\u0001\u0003\u0092\u0001\u0004\u0018\u00010\u0002ø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\'"}, d2 = {"Lkotlinx/coroutines/channels/ChannelResult;", "T", "", "holder", "constructor-impl", "(Ljava/lang/Object;)Ljava/lang/Object;", "getHolder$annotations", "()V", "isClosed", "", "isClosed-impl", "(Ljava/lang/Object;)Z", "isFailure", "isFailure-impl", "isSuccess", "isSuccess-impl", "equals", "other", "equals-impl", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "exceptionOrNull", "", "exceptionOrNull-impl", "(Ljava/lang/Object;)Ljava/lang/Throwable;", "getOrNull", "getOrNull-impl", "getOrThrow", "getOrThrow-impl", "hashCode", "", "hashCode-impl", "(Ljava/lang/Object;)I", "toString", "", "toString-impl", "(Ljava/lang/Object;)Ljava/lang/String;", "Closed", "Companion", "Failed", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
@JvmInline
public final class ChannelResult {
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000F\u0012\b\u0010\u0002\u001A\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0005\u001A\u00020\u00062\b\u0010\u0007\u001A\u0004\u0018\u00010\bH\u0096\u0002J\b\u0010\t\u001A\u00020\nH\u0016J\b\u0010\u000B\u001A\u00020\fH\u0016R\u0012\u0010\u0002\u001A\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lkotlinx/coroutines/channels/ChannelResult$Closed;", "Lkotlinx/coroutines/channels/ChannelResult$Failed;", "cause", "", "(Ljava/lang/Throwable;)V", "equals", "", "other", "", "hashCode", "", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Closed extends Failed {
        public final Throwable cause;

        public Closed(Throwable throwable0) {
            this.cause = throwable0;
        }

        // 去混淆评级： 低(20)
        @Override
        public boolean equals(Object object0) {
            return object0 instanceof Closed && Intrinsics.areEqual(this.cause, ((Closed)object0).cause);
        }

        @Override
        public int hashCode() {
            return this.cause == null ? 0 : this.cause.hashCode();
        }

        @Override  // kotlinx.coroutines.channels.ChannelResult$Failed
        public String toString() {
            return "Closed(" + this.cause + ')';
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\n\b\u0087\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J.\u0010\u0005\u001A\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\u0004\b\u0001\u0010\u00072\b\u0010\b\u001A\u0004\u0018\u00010\tH\u0007ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\n\u0010\u000BJ$\u0010\f\u001A\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\u0004\b\u0001\u0010\u0007H\u0007ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\r\u0010\u000EJ,\u0010\u000F\u001A\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\u0004\b\u0001\u0010\u00072\u0006\u0010\u0010\u001A\u0002H\u0007H\u0007ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u0011\u0010\u0012R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000F\n\u0002\b!\n\u0005\b¡\u001E0\u0001\n\u0002\b\u0019¨\u0006\u0013"}, d2 = {"Lkotlinx/coroutines/channels/ChannelResult$Companion;", "", "()V", "failed", "Lkotlinx/coroutines/channels/ChannelResult$Failed;", "closed", "Lkotlinx/coroutines/channels/ChannelResult;", "E", "cause", "", "closed-JP2dKIU", "(Ljava/lang/Throwable;)Ljava/lang/Object;", "failure", "failure-PtdJZtk", "()Ljava/lang/Object;", "success", "value", "success-JP2dKIU", "(Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final Object closed-JP2dKIU(Throwable throwable0) {
            return ChannelResult.constructor-impl(new Closed(throwable0));
        }

        public final Object failure-PtdJZtk() {
            return ChannelResult.failed;
        }

        public final Object success-JP2dKIU(Object object0) {
            return object0;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\b\u0010\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001A\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lkotlinx/coroutines/channels/ChannelResult$Failed;", "", "()V", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static class Failed {
        @Override
        public String toString() {
            return "Failed";
        }
    }

    public static final Companion Companion;
    private static final Failed failed;
    private final Object holder;

    static {
        ChannelResult.Companion = new Companion(null);
        ChannelResult.failed = new Failed();
    }

    private ChannelResult(Object object0) {
        this.holder = object0;
    }

    public static final ChannelResult box-impl(Object object0) {
        return new ChannelResult(object0);
    }

    public static Object constructor-impl(Object object0) [...] // Inlined contents

    @Override
    public boolean equals(Object object0) {
        return ChannelResult.equals-impl(this.holder, object0);
    }

    // 去混淆评级： 低(20)
    public static boolean equals-impl(Object object0, Object object1) {
        return object1 instanceof ChannelResult ? Intrinsics.areEqual(object0, ((ChannelResult)object1).unbox-impl()) : false;
    }

    public static final boolean equals-impl0(Object object0, Object object1) {
        return Intrinsics.areEqual(object0, object1);
    }

    public static final Throwable exceptionOrNull-impl(Object object0) {
        Closed channelResult$Closed0 = object0 instanceof Closed ? ((Closed)object0) : null;
        return channelResult$Closed0 == null ? null : channelResult$Closed0.cause;
    }

    public static void getHolder$annotations() {
    }

    public static final Object getOrNull-impl(Object object0) {
        return object0 instanceof Failed ? null : object0;
    }

    public static final Object getOrThrow-impl(Object object0) {
        if(!(object0 instanceof Failed)) {
            return object0;
        }
        if(!(object0 instanceof Closed) || ((Closed)object0).cause == null) {
            throw new IllegalStateException(("Trying to call \'getOrThrow\' on a failed channel result: " + object0).toString());
        }
        throw ((Closed)object0).cause;
    }

    @Override
    public int hashCode() {
        return ChannelResult.hashCode-impl(this.holder);
    }

    public static int hashCode-impl(Object object0) {
        return object0 == null ? 0 : object0.hashCode();
    }

    public static final boolean isClosed-impl(Object object0) {
        return object0 instanceof Closed;
    }

    public static final boolean isFailure-impl(Object object0) {
        return object0 instanceof Failed;
    }

    public static final boolean isSuccess-impl(Object object0) {
        return !(object0 instanceof Failed);
    }

    @Override
    public String toString() {
        return ChannelResult.toString-impl(this.holder);
    }

    // 去混淆评级： 低(20)
    public static String toString-impl(Object object0) {
        return object0 instanceof Closed ? ((Closed)object0).toString() : "Value(" + object0 + ')';
    }

    public final Object unbox-impl() {
        return this.holder;
    }
}

