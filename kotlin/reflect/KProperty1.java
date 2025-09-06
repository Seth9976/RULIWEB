package kotlin.reflect;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0002\bf\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0006\b\u0001\u0010\u0002 \u00012\b\u0012\u0004\u0012\u0002H\u00020\u00032\u000E\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0004:\u0001\u000EJ\u0015\u0010\t\u001A\u00028\u00012\u0006\u0010\n\u001A\u00028\u0000H&¢\u0006\u0002\u0010\u000BJ\u0017\u0010\f\u001A\u0004\u0018\u00010\r2\u0006\u0010\n\u001A\u00028\u0000H\'¢\u0006\u0002\u0010\u000BR\u001E\u0010\u0005\u001A\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006X¦\u0004¢\u0006\u0006\u001A\u0004\b\u0007\u0010\b¨\u0006\u000F"}, d2 = {"Lkotlin/reflect/KProperty1;", "T", "V", "Lkotlin/reflect/KProperty;", "Lkotlin/Function1;", "getter", "Lkotlin/reflect/KProperty1$Getter;", "getGetter", "()Lkotlin/reflect/KProperty1$Getter;", "get", "receiver", "(Ljava/lang/Object;)Ljava/lang/Object;", "getDelegate", "", "Getter", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public interface KProperty1 extends Function1, KProperty {
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0006\b\u0003\u0010\u0002 \u00012\b\u0012\u0004\u0012\u0002H\u00020\u00032\u000E\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0004¨\u0006\u0005"}, d2 = {"Lkotlin/reflect/KProperty1$Getter;", "T", "V", "Lkotlin/reflect/KProperty$Getter;", "Lkotlin/Function1;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public interface Getter extends Function1, kotlin.reflect.KProperty.Getter {
    }

    Object get(Object arg1);

    Object getDelegate(Object arg1);

    Getter getGetter();
}

