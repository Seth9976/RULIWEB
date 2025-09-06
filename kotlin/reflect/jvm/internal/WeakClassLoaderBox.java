package kotlin.reflect.jvm.internal;

import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0010\u000E\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0011\u001A\u00020\u00122\b\u0010\u0013\u001A\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0014\u001A\u00020\u0006H\u0016J\b\u0010\u0015\u001A\u00020\u0016H\u0016R\u0011\u0010\u0005\u001A\u00020\u0006¢\u0006\b\n\u0000\u001A\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001A\b\u0012\u0004\u0012\u00020\u00030\n¢\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\fR\u001C\u0010\r\u001A\u0004\u0018\u00010\u0003X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u000E\u0010\u000F\"\u0004\b\u0010\u0010\u0004¨\u0006\u0017"}, d2 = {"Lkotlin/reflect/jvm/internal/WeakClassLoaderBox;", "", "classLoader", "Ljava/lang/ClassLoader;", "(Ljava/lang/ClassLoader;)V", "identityHashCode", "", "getIdentityHashCode", "()I", "ref", "Ljava/lang/ref/WeakReference;", "getRef", "()Ljava/lang/ref/WeakReference;", "temporaryStrongRef", "getTemporaryStrongRef", "()Ljava/lang/ClassLoader;", "setTemporaryStrongRef", "equals", "", "other", "hashCode", "toString", "", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class WeakClassLoaderBox {
    private final int identityHashCode;
    private final WeakReference ref;
    private ClassLoader temporaryStrongRef;

    public WeakClassLoaderBox(ClassLoader classLoader0) {
        Intrinsics.checkNotNullParameter(classLoader0, "classLoader");
        super();
        this.ref = new WeakReference(classLoader0);
        this.identityHashCode = System.identityHashCode(classLoader0);
        this.temporaryStrongRef = classLoader0;
    }

    @Override
    public boolean equals(Object object0) {
        return object0 instanceof WeakClassLoaderBox && this.ref.get() == ((WeakClassLoaderBox)object0).ref.get();
    }

    @Override
    public int hashCode() {
        return this.identityHashCode;
    }

    public final void setTemporaryStrongRef(ClassLoader classLoader0) {
        this.temporaryStrongRef = classLoader0;
    }

    @Override
    public String toString() {
        ClassLoader classLoader0 = (ClassLoader)this.ref.get();
        if(classLoader0 != null) {
            String s = classLoader0.toString();
            return s == null ? "<null>" : s;
        }
        return "<null>";
    }
}

