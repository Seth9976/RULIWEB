package kotlin.reflect;

import kotlin.Function;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\f\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003R\u001A\u0010\u0004\u001A\u00020\u00058&X§\u0004¢\u0006\f\u0012\u0004\b\u0006\u0010\u0007\u001A\u0004\b\u0004\u0010\bR\u001A\u0010\t\u001A\u00020\u00058&X§\u0004¢\u0006\f\u0012\u0004\b\n\u0010\u0007\u001A\u0004\b\t\u0010\bR\u001A\u0010\u000B\u001A\u00020\u00058&X§\u0004¢\u0006\f\u0012\u0004\b\f\u0010\u0007\u001A\u0004\b\u000B\u0010\bR\u001A\u0010\r\u001A\u00020\u00058&X§\u0004¢\u0006\f\u0012\u0004\b\u000E\u0010\u0007\u001A\u0004\b\r\u0010\bR\u001A\u0010\u000F\u001A\u00020\u00058&X§\u0004¢\u0006\f\u0012\u0004\b\u0010\u0010\u0007\u001A\u0004\b\u000F\u0010\b¨\u0006\u0011"}, d2 = {"Lkotlin/reflect/KFunction;", "R", "Lkotlin/reflect/KCallable;", "Lkotlin/Function;", "isExternal", "", "isExternal$annotations", "()V", "()Z", "isInfix", "isInfix$annotations", "isInline", "isInline$annotations", "isOperator", "isOperator$annotations", "isSuspend", "isSuspend$annotations", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public interface KFunction extends Function, KCallable {
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 0x30)
    public static final class DefaultImpls {
        public static void isExternal$annotations() {
        }

        public static void isInfix$annotations() {
        }

        public static void isInline$annotations() {
        }

        public static void isOperator$annotations() {
        }

        public static void isSuspend$annotations() {
        }
    }

    boolean isExternal();

    boolean isInfix();

    boolean isInline();

    boolean isOperator();

    @Override  // kotlin.reflect.KCallable
    boolean isSuspend();
}

