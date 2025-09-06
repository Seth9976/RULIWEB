package kotlin.reflect.jvm.internal.impl.incremental;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LocationInfo;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupTracker.DO_NOTHING;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupTracker;
import kotlin.reflect.jvm.internal.impl.incremental.components.Position;
import kotlin.reflect.jvm.internal.impl.incremental.components.ScopeKind;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;

public final class UtilsKt {
    public static final void record(LookupTracker lookupTracker0, LookupLocation lookupLocation0, ClassDescriptor classDescriptor0, Name name0) {
        Intrinsics.checkNotNullParameter(lookupTracker0, "<this>");
        Intrinsics.checkNotNullParameter(lookupLocation0, "from");
        Intrinsics.checkNotNullParameter(classDescriptor0, "scopeOwner");
        Intrinsics.checkNotNullParameter(name0, "name");
        if(lookupTracker0 != DO_NOTHING.INSTANCE) {
            LocationInfo locationInfo0 = lookupLocation0.getLocation();
            if(locationInfo0 != null) {
                Position position0 = lookupTracker0.getRequiresPosition() ? locationInfo0.getPosition() : Position.Companion.getNO_POSITION();
                String s = locationInfo0.getFilePath();
                String s1 = DescriptorUtils.getFqName(classDescriptor0).asString();
                Intrinsics.checkNotNullExpressionValue(s1, "getFqName(scopeOwner).asString()");
                String s2 = name0.asString();
                Intrinsics.checkNotNullExpressionValue(s2, "name.asString()");
                lookupTracker0.record(s, position0, s1, ScopeKind.CLASSIFIER, s2);
            }
        }
    }

    public static final void record(LookupTracker lookupTracker0, LookupLocation lookupLocation0, PackageFragmentDescriptor packageFragmentDescriptor0, Name name0) {
        Intrinsics.checkNotNullParameter(lookupTracker0, "<this>");
        Intrinsics.checkNotNullParameter(lookupLocation0, "from");
        Intrinsics.checkNotNullParameter(packageFragmentDescriptor0, "scopeOwner");
        Intrinsics.checkNotNullParameter(name0, "name");
        String s = packageFragmentDescriptor0.getFqName().asString();
        Intrinsics.checkNotNullExpressionValue(s, "scopeOwner.fqName.asString()");
        String s1 = name0.asString();
        Intrinsics.checkNotNullExpressionValue(s1, "name.asString()");
        UtilsKt.recordPackageLookup(lookupTracker0, lookupLocation0, s, s1);
    }

    public static final void recordPackageLookup(LookupTracker lookupTracker0, LookupLocation lookupLocation0, String s, String s1) {
        Intrinsics.checkNotNullParameter(lookupTracker0, "<this>");
        Intrinsics.checkNotNullParameter(lookupLocation0, "from");
        Intrinsics.checkNotNullParameter(s, "packageFqName");
        Intrinsics.checkNotNullParameter(s1, "name");
        if(lookupTracker0 != DO_NOTHING.INSTANCE) {
            LocationInfo locationInfo0 = lookupLocation0.getLocation();
            if(locationInfo0 != null) {
                Position position0 = lookupTracker0.getRequiresPosition() ? locationInfo0.getPosition() : Position.Companion.getNO_POSITION();
                lookupTracker0.record(locationInfo0.getFilePath(), position0, s, ScopeKind.PACKAGE, s1);
            }
        }
    }
}

