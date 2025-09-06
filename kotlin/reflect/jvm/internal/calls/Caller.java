package kotlin.reflect.jvm.internal.calls;

import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b`\u0018\u0000*\f\b\u0000\u0010\u0001 \u0001*\u0004\u0018\u00010\u00022\u00020\u0003J\u001B\u0010\u000F\u001A\u0004\u0018\u00010\u00032\n\u0010\u0010\u001A\u0006\u0012\u0002\b\u00030\u0011H&¢\u0006\u0002\u0010\u0012J\u0019\u0010\u0013\u001A\u00020\u00142\n\u0010\u0010\u001A\u0006\u0012\u0002\b\u00030\u0011H\u0016¢\u0006\u0002\u0010\u0015R\u0012\u0010\u0004\u001A\u00028\u0000X¦\u0004¢\u0006\u0006\u001A\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0007\u001A\b\u0012\u0004\u0012\u00020\t0\bX¦\u0004¢\u0006\u0006\u001A\u0004\b\n\u0010\u000BR\u0012\u0010\f\u001A\u00020\tX¦\u0004¢\u0006\u0006\u001A\u0004\b\r\u0010\u000E¨\u0006\u0016"}, d2 = {"Lkotlin/reflect/jvm/internal/calls/Caller;", "M", "Ljava/lang/reflect/Member;", "", "member", "getMember", "()Ljava/lang/reflect/Member;", "parameterTypes", "", "Ljava/lang/reflect/Type;", "getParameterTypes", "()Ljava/util/List;", "returnType", "getReturnType", "()Ljava/lang/reflect/Type;", "call", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "checkArguments", "", "([Ljava/lang/Object;)V", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public interface Caller {
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
    public static final class DefaultImpls {
        public static void checkArguments(Caller caller0, Object[] arr_object) {
            Intrinsics.checkNotNullParameter(arr_object, "args");
            if(CallerKt.getArity(caller0) != arr_object.length) {
                throw new IllegalArgumentException("Callable expects " + CallerKt.getArity(caller0) + " arguments, but " + arr_object.length + " were provided.");
            }
        }
    }

    Object call(Object[] arg1);

    Member getMember();

    List getParameterTypes();

    Type getReturnType();
}

