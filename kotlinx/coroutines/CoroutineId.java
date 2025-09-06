package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000E\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0081\b\u0018\u0000 \u00182\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\u0018B\r\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\t\u001A\u00020\u0005HÆ\u0003J\u0013\u0010\n\u001A\u00020\u00002\b\b\u0002\u0010\u0004\u001A\u00020\u0005HÆ\u0001J\u0013\u0010\u000B\u001A\u00020\f2\b\u0010\r\u001A\u0004\u0018\u00010\u000EHÖ\u0003J\t\u0010\u000F\u001A\u00020\u0010HÖ\u0001J\u0018\u0010\u0011\u001A\u00020\u00122\u0006\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0015\u001A\u00020\u0002H\u0016J\b\u0010\u0016\u001A\u00020\u0002H\u0016J\u0010\u0010\u0017\u001A\u00020\u00022\u0006\u0010\u0013\u001A\u00020\u0014H\u0016R\u0011\u0010\u0004\u001A\u00020\u0005¢\u0006\b\n\u0000\u001A\u0004\b\u0007\u0010\b¨\u0006\u0019"}, d2 = {"Lkotlinx/coroutines/CoroutineId;", "Lkotlinx/coroutines/ThreadContextElement;", "", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "id", "", "(J)V", "getId", "()J", "component1", "copy", "equals", "", "other", "", "hashCode", "", "restoreThreadContext", "", "context", "Lkotlin/coroutines/CoroutineContext;", "oldState", "toString", "updateThreadContext", "Key", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class CoroutineId extends AbstractCoroutineContextElement implements ThreadContextElement {
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lkotlinx/coroutines/CoroutineId$Key;", "Lkotlin/coroutines/CoroutineContext$Key;", "Lkotlinx/coroutines/CoroutineId;", "()V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Key implements kotlin.coroutines.CoroutineContext.Key {
        private Key() {
        }

        public Key(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    public static final Key Key;
    private final long id;

    static {
        CoroutineId.Key = new Key(null);
    }

    public CoroutineId(long v) {
        super(CoroutineId.Key);
        this.id = v;
    }

    public final long component1() {
        return this.id;
    }

    public final CoroutineId copy(long v) {
        return new CoroutineId(v);
    }

    public static CoroutineId copy$default(CoroutineId coroutineId0, long v, int v1, Object object0) {
        if((v1 & 1) != 0) {
            v = coroutineId0.id;
        }
        return coroutineId0.copy(v);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof CoroutineId ? this.id == ((CoroutineId)object0).id : false;
    }

    public final long getId() {
        return this.id;
    }

    @Override
    public int hashCode() {
        return (int)(this.id ^ this.id >>> 0x20);
    }

    @Override  // kotlinx.coroutines.ThreadContextElement
    public void restoreThreadContext(CoroutineContext coroutineContext0, Object object0) {
        this.restoreThreadContext(coroutineContext0, ((String)object0));
    }

    public void restoreThreadContext(CoroutineContext coroutineContext0, String s) {
        Thread.currentThread().setName(s);
    }

    @Override
    public String toString() {
        return "CoroutineId(" + this.id + ')';
    }

    @Override  // kotlinx.coroutines.ThreadContextElement
    public Object updateThreadContext(CoroutineContext coroutineContext0) {
        return this.updateThreadContext(coroutineContext0);
    }

    public String updateThreadContext(CoroutineContext coroutineContext0) {
        String s;
        CoroutineName coroutineName0 = (CoroutineName)coroutineContext0.get(CoroutineName.Key);
        if(coroutineName0 == null) {
            s = "coroutine";
        }
        else {
            s = coroutineName0.getName();
            if(s == null) {
                s = "coroutine";
            }
        }
        boolean z = StringsKt.lastIndexOf$default("jeb-dexdec-sb-st-13280", " @", 0, false, 6, null) >= 0;
        Intrinsics.checkNotNullExpressionValue("jeb-dexdec-sb-st-13280", "this as java.lang.String…ing(startIndex, endIndex)");
        String s1 = "jeb-dexdec-sb-st-13280 @" + s + '#' + this.id;
        Intrinsics.checkNotNullExpressionValue(s1, "StringBuilder(capacity).…builderAction).toString()");
        Thread.currentThread().setName(s1);
        return "jeb-dexdec-sb-st-13280";
    }
}

