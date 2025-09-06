package kotlin.jvm.internal;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.KotlinReflectionNotSupportedError;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0004\n\u0002\u0010\u001E\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0019\u0012\n\u0010\u0002\u001A\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0013\u0010\u000E\u001A\u00020\u000F2\b\u0010\u0010\u001A\u0004\u0018\u00010\u0011H\u0096\u0002J\b\u0010\u0012\u001A\u00020\u0013H\u0016J\b\u0010\u0014\u001A\u00020\u0005H\u0016R\u0018\u0010\u0002\u001A\u0006\u0012\u0002\b\u00030\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0007\u0010\bR\u001E\u0010\t\u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000B0\n8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\f\u0010\rR\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lkotlin/jvm/internal/PackageReference;", "Lkotlin/jvm/internal/ClassBasedDeclarationContainer;", "jClass", "Ljava/lang/Class;", "moduleName", "", "(Ljava/lang/Class;Ljava/lang/String;)V", "getJClass", "()Ljava/lang/Class;", "members", "", "Lkotlin/reflect/KCallable;", "getMembers", "()Ljava/util/Collection;", "equals", "", "other", "", "hashCode", "", "toString", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class PackageReference implements ClassBasedDeclarationContainer {
    private final Class jClass;
    private final String moduleName;

    public PackageReference(Class class0, String s) {
        Intrinsics.checkNotNullParameter(class0, "jClass");
        Intrinsics.checkNotNullParameter(s, "moduleName");
        super();
        this.jClass = class0;
        this.moduleName = s;
    }

    // 去混淆评级： 低(20)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof PackageReference && Intrinsics.areEqual(this.getJClass(), ((PackageReference)object0).getJClass());
    }

    @Override  // kotlin.jvm.internal.ClassBasedDeclarationContainer
    public Class getJClass() {
        return this.jClass;
    }

    @Override  // kotlin.reflect.KDeclarationContainer
    public Collection getMembers() {
        throw new KotlinReflectionNotSupportedError();
    }

    @Override
    public int hashCode() {
        return this.getJClass().hashCode();
    }

    @Override
    public String toString() {
        return this.getJClass().toString() + " (Kotlin reflection is not available)";
    }
}

