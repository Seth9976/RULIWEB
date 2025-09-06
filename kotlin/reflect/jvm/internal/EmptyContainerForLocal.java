package kotlin.reflect.jvm.internal;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001E\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u000F\u001A\u00020\u0010H\u0002J\u0016\u0010\u0011\u001A\b\u0012\u0004\u0012\u00020\u00120\u00042\u0006\u0010\u0013\u001A\u00020\u0014H\u0016J\u0012\u0010\u0015\u001A\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001A\u00020\u0018H\u0016J\u0016\u0010\u0019\u001A\b\u0012\u0004\u0012\u00020\u00160\u00042\u0006\u0010\u0013\u001A\u00020\u0014H\u0016R\u001A\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00050\u00048VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001A\u0006\u0012\u0002\b\u00030\t8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\n\u0010\u000BR\u001E\u0010\f\u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r0\u00048VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u000E\u0010\u0007¨\u0006\u001A"}, d2 = {"Lkotlin/reflect/jvm/internal/EmptyContainerForLocal;", "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "()V", "constructorDescriptors", "", "Lkotlin/reflect/jvm/internal/impl/descriptors/ConstructorDescriptor;", "getConstructorDescriptors", "()Ljava/util/Collection;", "jClass", "Ljava/lang/Class;", "getJClass", "()Ljava/lang/Class;", "members", "Lkotlin/reflect/KCallable;", "getMembers", "fail", "", "getFunctions", "Lkotlin/reflect/jvm/internal/impl/descriptors/FunctionDescriptor;", "name", "Lkotlin/reflect/jvm/internal/impl/name/Name;", "getLocalProperty", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "index", "", "getProperties", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class EmptyContainerForLocal extends KDeclarationContainerImpl {
    public static final EmptyContainerForLocal INSTANCE;

    static {
        EmptyContainerForLocal.INSTANCE = new EmptyContainerForLocal();
    }

    private final Void fail() {
        throw new KotlinReflectionInternalError("Introspecting local functions, lambdas, anonymous functions, local variables and typealiases is not yet fully supported in Kotlin reflection");
    }

    @Override  // kotlin.reflect.jvm.internal.KDeclarationContainerImpl
    public Collection getConstructorDescriptors() {
        this.fail();
        throw null;
    }

    @Override  // kotlin.reflect.jvm.internal.KDeclarationContainerImpl
    public Collection getFunctions(Name name0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        this.fail();
        throw null;
    }

    @Override  // kotlin.jvm.internal.ClassBasedDeclarationContainer
    public Class getJClass() {
        this.fail();
        throw null;
    }

    @Override  // kotlin.reflect.jvm.internal.KDeclarationContainerImpl
    public PropertyDescriptor getLocalProperty(int v) {
        return null;
    }

    @Override  // kotlin.reflect.KDeclarationContainer
    public Collection getMembers() {
        this.fail();
        throw null;
    }

    @Override  // kotlin.reflect.jvm.internal.KDeclarationContainerImpl
    public Collection getProperties(Name name0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        this.fail();
        throw null;
    }
}

