package androidx.lifecycle.viewmodel;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001C\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000F\u0012\b\b\u0002\u0010\u0002\u001A\u00020\u0001¢\u0006\u0002\u0010\u0003J$\u0010\u0004\u001A\u0004\u0018\u0001H\u0005\"\u0004\b\u0000\u0010\u00052\f\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0007H\u0096\u0002¢\u0006\u0002\u0010\bJ*\u0010\t\u001A\u00020\n\"\u0004\b\u0000\u0010\u00052\f\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00050\u00072\u0006\u0010\u000B\u001A\u0002H\u0005H\u0086\u0002¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Landroidx/lifecycle/viewmodel/MutableCreationExtras;", "Landroidx/lifecycle/viewmodel/CreationExtras;", "initialExtras", "(Landroidx/lifecycle/viewmodel/CreationExtras;)V", "get", "T", "key", "Landroidx/lifecycle/viewmodel/CreationExtras$Key;", "(Landroidx/lifecycle/viewmodel/CreationExtras$Key;)Ljava/lang/Object;", "set", "", "t", "(Landroidx/lifecycle/viewmodel/CreationExtras$Key;Ljava/lang/Object;)V", "lifecycle-viewmodel_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class MutableCreationExtras extends CreationExtras {
    public MutableCreationExtras() {
        this(null, 1, null);
    }

    public MutableCreationExtras(CreationExtras creationExtras0) {
        Intrinsics.checkNotNullParameter(creationExtras0, "initialExtras");
        super();
        this.getMap$lifecycle_viewmodel_release().putAll(creationExtras0.getMap$lifecycle_viewmodel_release());
    }

    public MutableCreationExtras(CreationExtras creationExtras0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 1) != 0) {
            creationExtras0 = Empty.INSTANCE;
        }
        this(creationExtras0);
    }

    @Override  // androidx.lifecycle.viewmodel.CreationExtras
    public Object get(Key creationExtras$Key0) {
        Intrinsics.checkNotNullParameter(creationExtras$Key0, "key");
        return this.getMap$lifecycle_viewmodel_release().get(creationExtras$Key0);
    }

    public final void set(Key creationExtras$Key0, Object object0) {
        Intrinsics.checkNotNullParameter(creationExtras$Key0, "key");
        this.getMap$lifecycle_viewmodel_release().put(creationExtras$Key0, object0);
    }
}

