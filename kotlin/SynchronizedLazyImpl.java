package kotlin;

import java.io.Serializable;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00060\u0003j\u0002`\u0004B\u001F\u0012\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\n\b\u0002\u0010\u0007\u001A\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\b\u0010\u000E\u001A\u00020\u000FH\u0016J\b\u0010\u0010\u001A\u00020\u0011H\u0016J\b\u0010\u0012\u001A\u00020\bH\u0002R\u0010\u0010\n\u001A\u0004\u0018\u00010\bX\u0082\u000E¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001A\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006X\u0088\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000B\u001A\u00028\u00008VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\f\u0010\r¨\u0006\u0013"}, d2 = {"Lkotlin/SynchronizedLazyImpl;", "T", "Lkotlin/Lazy;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "initializer", "Lkotlin/Function0;", "lock", "", "(Lkotlin/jvm/functions/Function0;Ljava/lang/Object;)V", "_value", "value", "getValue", "()Ljava/lang/Object;", "isInitialized", "", "toString", "", "writeReplace", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
final class SynchronizedLazyImpl implements Serializable, Lazy {
    private volatile Object _value;
    private Function0 initializer;
    private final Object lock;

    public SynchronizedLazyImpl(Function0 function00, Object object0) {
        Intrinsics.checkNotNullParameter(function00, "initializer");
        super();
        this.initializer = function00;
        this._value = UNINITIALIZED_VALUE.INSTANCE;
        if(object0 == null) {
            object0 = this;
        }
        this.lock = object0;
    }

    public SynchronizedLazyImpl(Function0 function00, Object object0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 2) != 0) {
            object0 = null;
        }
        this(function00, object0);
    }

    @Override  // kotlin.Lazy
    public Object getValue() {
        Object object0 = this._value;
        if(object0 != UNINITIALIZED_VALUE.INSTANCE) {
            return object0;
        }
        synchronized(this.lock) {
            Object object2 = this._value;
            if(object2 == UNINITIALIZED_VALUE.INSTANCE) {
                Function0 function00 = this.initializer;
                Intrinsics.checkNotNull(function00);
                object2 = function00.invoke();
                this._value = object2;
                this.initializer = null;
            }
            return object2;
        }
    }

    @Override  // kotlin.Lazy
    public boolean isInitialized() {
        return this._value != UNINITIALIZED_VALUE.INSTANCE;
    }

    // 去混淆评级： 低(20)
    @Override
    public String toString() {
        return this.isInitialized() ? String.valueOf(this.getValue()) : "Lazy value not initialized yet.";
    }

    private final Object writeReplace() {
        return new InitializedLazyImpl(this.getValue());
    }
}

