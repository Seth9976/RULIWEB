package kotlin.reflect;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\n\n\u0002\u0010\u000E\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002J%\u0010%\u001A\u00028\u00002\u0016\u0010&\u001A\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010(0\'\"\u0004\u0018\u00010(H&¢\u0006\u0002\u0010)J#\u0010*\u001A\u00028\u00002\u0014\u0010&\u001A\u0010\u0012\u0004\u0012\u00020\u0015\u0012\u0006\u0012\u0004\u0018\u00010(0+H&¢\u0006\u0002\u0010,R\u001A\u0010\u0003\u001A\u00020\u00048&X§\u0004¢\u0006\f\u0012\u0004\b\u0005\u0010\u0006\u001A\u0004\b\u0003\u0010\u0007R\u001A\u0010\b\u001A\u00020\u00048&X§\u0004¢\u0006\f\u0012\u0004\b\t\u0010\u0006\u001A\u0004\b\b\u0010\u0007R\u001A\u0010\n\u001A\u00020\u00048&X§\u0004¢\u0006\f\u0012\u0004\b\u000B\u0010\u0006\u001A\u0004\b\n\u0010\u0007R\u001A\u0010\f\u001A\u00020\u00048&X§\u0004¢\u0006\f\u0012\u0004\b\r\u0010\u0006\u001A\u0004\b\f\u0010\u0007R\u001A\u0010\u000E\u001A\u00020\u000F8&X§\u0004¢\u0006\f\u0012\u0004\b\u0010\u0010\u0006\u001A\u0004\b\u0011\u0010\u0012R\u0018\u0010\u0013\u001A\b\u0012\u0004\u0012\u00020\u00150\u0014X¦\u0004¢\u0006\u0006\u001A\u0004\b\u0016\u0010\u0017R\u0012\u0010\u0018\u001A\u00020\u0019X¦\u0004¢\u0006\u0006\u001A\u0004\b\u001A\u0010\u001BR \u0010\u001C\u001A\b\u0012\u0004\u0012\u00020\u001D0\u00148&X§\u0004¢\u0006\f\u0012\u0004\b\u001E\u0010\u0006\u001A\u0004\b\u001F\u0010\u0017R\u001C\u0010 \u001A\u0004\u0018\u00010!8&X§\u0004¢\u0006\f\u0012\u0004\b\"\u0010\u0006\u001A\u0004\b#\u0010$¨\u0006-"}, d2 = {"Lkotlin/reflect/KCallable;", "R", "Lkotlin/reflect/KAnnotatedElement;", "isAbstract", "", "isAbstract$annotations", "()V", "()Z", "isFinal", "isFinal$annotations", "isOpen", "isOpen$annotations", "isSuspend", "isSuspend$annotations", "name", "", "getName$annotations", "getName", "()Ljava/lang/String;", "parameters", "", "Lkotlin/reflect/KParameter;", "getParameters", "()Ljava/util/List;", "returnType", "Lkotlin/reflect/KType;", "getReturnType", "()Lkotlin/reflect/KType;", "typeParameters", "Lkotlin/reflect/KTypeParameter;", "getTypeParameters$annotations", "getTypeParameters", "visibility", "Lkotlin/reflect/KVisibility;", "getVisibility$annotations", "getVisibility", "()Lkotlin/reflect/KVisibility;", "call", "args", "", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "callBy", "", "(Ljava/util/Map;)Ljava/lang/Object;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public interface KCallable extends KAnnotatedElement {
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 0x30)
    public static final class DefaultImpls {
        public static void getName$annotations() {
        }

        public static void getTypeParameters$annotations() {
        }

        public static void getVisibility$annotations() {
        }

        public static void isAbstract$annotations() {
        }

        public static void isFinal$annotations() {
        }

        public static void isOpen$annotations() {
        }

        public static void isSuspend$annotations() {
        }
    }

    Object call(Object[] arg1);

    Object callBy(Map arg1);

    String getName();

    List getParameters();

    KType getReturnType();

    List getTypeParameters();

    KVisibility getVisibility();

    boolean isAbstract();

    boolean isFinal();

    boolean isOpen();

    boolean isSuspend();
}

