package kotlin.reflect.jvm.internal.impl.resolve;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleCapability;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;

public final class ResolutionAnchorProviderKt {
    private static final ModuleCapability RESOLUTION_ANCHOR_PROVIDER_CAPABILITY;

    static {
        ResolutionAnchorProviderKt.RESOLUTION_ANCHOR_PROVIDER_CAPABILITY = new ModuleCapability("ResolutionAnchorProvider");
    }

    public static final ModuleDescriptor getResolutionAnchorIfAny(ModuleDescriptor moduleDescriptor0) {
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "<this>");
        ResolutionAnchorProvider resolutionAnchorProvider0 = (ResolutionAnchorProvider)moduleDescriptor0.getCapability(ResolutionAnchorProviderKt.RESOLUTION_ANCHOR_PROVIDER_CAPABILITY);
        return resolutionAnchorProvider0 == null ? null : resolutionAnchorProvider0.getResolutionAnchor(moduleDescriptor0);
    }
}

