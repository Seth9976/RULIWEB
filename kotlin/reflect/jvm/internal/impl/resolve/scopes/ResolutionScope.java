package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import java.util.Collection;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;

public interface ResolutionScope {
    public static final class DefaultImpls {
        public static Collection getContributedDescriptors$default(ResolutionScope resolutionScope0, DescriptorKindFilter descriptorKindFilter0, Function1 function10, int v, Object object0) {
            if(object0 != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getContributedDescriptors");
            }
            if((v & 1) != 0) {
                descriptorKindFilter0 = DescriptorKindFilter.ALL;
            }
            if((v & 2) != 0) {
                function10 = MemberScope.Companion.getALL_NAME_FILTER();
            }
            return resolutionScope0.getContributedDescriptors(descriptorKindFilter0, function10);
        }

        public static void recordLookup(ResolutionScope resolutionScope0, Name name0, LookupLocation lookupLocation0) {
            Intrinsics.checkNotNullParameter(name0, "name");
            Intrinsics.checkNotNullParameter(lookupLocation0, "location");
            resolutionScope0.getContributedFunctions(name0, lookupLocation0);
        }
    }

    ClassifierDescriptor getContributedClassifier(Name arg1, LookupLocation arg2);

    Collection getContributedDescriptors(DescriptorKindFilter arg1, Function1 arg2);

    Collection getContributedFunctions(Name arg1, LookupLocation arg2);

    void recordLookup(Name arg1, LookupLocation arg2);
}

