package kotlin.reflect.jvm.internal;

import java.lang.ref.SoftReference;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001C\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002B\u001D\u0012\u0016\u0010\u0004\u001A\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0002\u0010\u0007J\u001A\u0010\b\u001A\b\u0012\u0004\u0012\u00028\u00000\u00032\n\u0010\t\u001A\u0006\u0012\u0002\b\u00030\u0006H\u0014J\f\u0010\n\u001A\b\u0012\u0004\u0012\u00028\u00000\u0000R \u0010\u0004\u001A\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\u0004\u0012\u00028\u00000\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u000B"}, d2 = {"Lkotlin/reflect/jvm/internal/ComputableClassValue;", "V", "Ljava/lang/ClassValue;", "Ljava/lang/ref/SoftReference;", "compute", "Lkotlin/Function1;", "Ljava/lang/Class;", "(Lkotlin/jvm/functions/Function1;)V", "computeValue", "type", "createNewCopy", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class ComputableClassValue extends ClassValue {
    public final Function1 compute;

    public ComputableClassValue(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "compute");
        super();
        this.compute = function10;
    }

    @Override
    public Object computeValue(Class class0) {
        return this.computeValue(class0);
    }

    protected SoftReference computeValue(Class class0) {
        Intrinsics.checkNotNullParameter(class0, "type");
        return new SoftReference(this.compute.invoke(class0));
    }

    public final ComputableClassValue createNewCopy() {
        return new ComputableClassValue(this.compute);
    }
}

