package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.jvm.internal.Intrinsics;

public abstract class DelegatedDescriptorVisibility extends DescriptorVisibility {
    private final Visibility delegate;

    public DelegatedDescriptorVisibility(Visibility visibility0) {
        Intrinsics.checkNotNullParameter(visibility0, "delegate");
        super();
        this.delegate = visibility0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility
    public Visibility getDelegate() {
        return this.delegate;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility
    public String getInternalDisplayName() {
        return this.getDelegate().getInternalDisplayName();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility
    public DescriptorVisibility normalize() {
        DescriptorVisibility descriptorVisibility0 = DescriptorVisibilities.toDescriptorVisibility(this.getDelegate().normalize());
        Intrinsics.checkNotNullExpressionValue(descriptorVisibility0, "toDescriptorVisibility(delegate.normalize())");
        return descriptorVisibility0;
    }
}

