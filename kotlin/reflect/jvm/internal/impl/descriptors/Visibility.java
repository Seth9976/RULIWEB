package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.jvm.internal.Intrinsics;

public abstract class Visibility {
    private final boolean isPublicAPI;
    private final String name;

    protected Visibility(String s, boolean z) {
        Intrinsics.checkNotNullParameter(s, "name");
        super();
        this.name = s;
        this.isPublicAPI = z;
    }

    public Integer compareTo(Visibility visibility0) {
        Intrinsics.checkNotNullParameter(visibility0, "visibility");
        return Visibilities.INSTANCE.compareLocal$compiler_common(this, visibility0);
    }

    public String getInternalDisplayName() {
        return this.name;
    }

    public final boolean isPublicAPI() {
        return this.isPublicAPI;
    }

    public Visibility normalize() {
        return this;
    }

    @Override
    public final String toString() {
        return this.getInternalDisplayName();
    }
}

