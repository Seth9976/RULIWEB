package kotlin.properties;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u0002H\u00010\u0003B\u0005¢\u0006\u0002\u0010\u0004J$\u0010\u0007\u001A\u00028\u00002\b\u0010\b\u001A\u0004\u0018\u00010\u00022\n\u0010\t\u001A\u0006\u0012\u0002\b\u00030\nH\u0096\u0002¢\u0006\u0002\u0010\u000BJ,\u0010\f\u001A\u00020\r2\b\u0010\b\u001A\u0004\u0018\u00010\u00022\n\u0010\t\u001A\u0006\u0012\u0002\b\u00030\n2\u0006\u0010\u0005\u001A\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u000EJ\b\u0010\u000F\u001A\u00020\u0010H\u0016R\u0012\u0010\u0005\u001A\u0004\u0018\u00018\u0000X\u0082\u000E¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\u0011"}, d2 = {"Lkotlin/properties/NotNullVar;", "T", "", "Lkotlin/properties/ReadWriteProperty;", "()V", "value", "Ljava/lang/Object;", "getValue", "thisRef", "property", "Lkotlin/reflect/KProperty;", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "setValue", "", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "toString", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
final class NotNullVar implements ReadWriteProperty {
    private Object value;

    @Override  // kotlin.properties.ReadWriteProperty
    public Object getValue(Object object0, KProperty kProperty0) {
        Intrinsics.checkNotNullParameter(kProperty0, "property");
        Object object1 = this.value;
        if(object1 == null) {
            throw new IllegalStateException("Property " + kProperty0.getName() + " should be initialized before get.");
        }
        return object1;
    }

    @Override  // kotlin.properties.ReadWriteProperty
    public void setValue(Object object0, KProperty kProperty0, Object object1) {
        Intrinsics.checkNotNullParameter(kProperty0, "property");
        Intrinsics.checkNotNullParameter(object1, "value");
        this.value = object1;
    }

    @Override
    public String toString() {
        return "NotNullProperty(" + (this.value == null ? "value not initialized yet" : "value=" + this.value) + ')';
    }
}

