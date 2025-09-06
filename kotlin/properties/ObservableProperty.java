package kotlin.properties;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000B\n\u0002\b\u0007\n\u0002\u0010\u000E\n\u0000\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0004\u001A\u00028\u0000¢\u0006\u0002\u0010\u0005J)\u0010\b\u001A\u00020\t2\n\u0010\n\u001A\u0006\u0012\u0002\b\u00030\u000B2\u0006\u0010\f\u001A\u00028\u00002\u0006\u0010\r\u001A\u00028\u0000H\u0014¢\u0006\u0002\u0010\u000EJ)\u0010\u000F\u001A\u00020\u00102\n\u0010\n\u001A\u0006\u0012\u0002\b\u00030\u000B2\u0006\u0010\f\u001A\u00028\u00002\u0006\u0010\r\u001A\u00028\u0000H\u0014¢\u0006\u0002\u0010\u0011J$\u0010\u0012\u001A\u00028\u00002\b\u0010\u0013\u001A\u0004\u0018\u00010\u00032\n\u0010\n\u001A\u0006\u0012\u0002\b\u00030\u000BH\u0096\u0002¢\u0006\u0002\u0010\u0014J,\u0010\u0015\u001A\u00020\t2\b\u0010\u0013\u001A\u0004\u0018\u00010\u00032\n\u0010\n\u001A\u0006\u0012\u0002\b\u00030\u000B2\u0006\u0010\u0006\u001A\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0016J\b\u0010\u0017\u001A\u00020\u0018H\u0016R\u0010\u0010\u0006\u001A\u00028\u0000X\u0082\u000E¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\u0019"}, d2 = {"Lkotlin/properties/ObservableProperty;", "V", "Lkotlin/properties/ReadWriteProperty;", "", "initialValue", "(Ljava/lang/Object;)V", "value", "Ljava/lang/Object;", "afterChange", "", "property", "Lkotlin/reflect/KProperty;", "oldValue", "newValue", "(Lkotlin/reflect/KProperty;Ljava/lang/Object;Ljava/lang/Object;)V", "beforeChange", "", "(Lkotlin/reflect/KProperty;Ljava/lang/Object;Ljava/lang/Object;)Z", "getValue", "thisRef", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "setValue", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "toString", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public abstract class ObservableProperty implements ReadWriteProperty {
    private Object value;

    public ObservableProperty(Object object0) {
        this.value = object0;
    }

    protected void afterChange(KProperty kProperty0, Object object0, Object object1) {
        Intrinsics.checkNotNullParameter(kProperty0, "property");
    }

    protected boolean beforeChange(KProperty kProperty0, Object object0, Object object1) {
        Intrinsics.checkNotNullParameter(kProperty0, "property");
        return true;
    }

    @Override  // kotlin.properties.ReadWriteProperty
    public Object getValue(Object object0, KProperty kProperty0) {
        Intrinsics.checkNotNullParameter(kProperty0, "property");
        return this.value;
    }

    @Override  // kotlin.properties.ReadWriteProperty
    public void setValue(Object object0, KProperty kProperty0, Object object1) {
        Intrinsics.checkNotNullParameter(kProperty0, "property");
        Object object2 = this.value;
        if(!this.beforeChange(kProperty0, object2, object1)) {
            return;
        }
        this.value = object1;
        this.afterChange(kProperty0, object2, object1);
    }

    @Override
    public String toString() {
        return "ObservableProperty(value=" + this.value + ')';
    }
}

