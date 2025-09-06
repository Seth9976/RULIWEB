package kotlin.reflect.jvm.internal.impl.descriptors.deserialization;

import java.util.Collection;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;

public interface AdditionalClassPartsProvider {
    public static final class None implements AdditionalClassPartsProvider {
        public static final None INSTANCE;

        static {
            None.INSTANCE = new None();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider
        public Collection getConstructors(ClassDescriptor classDescriptor0) {
            Intrinsics.checkNotNullParameter(classDescriptor0, "classDescriptor");
            return CollectionsKt.emptyList();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider
        public Collection getFunctions(Name name0, ClassDescriptor classDescriptor0) {
            Intrinsics.checkNotNullParameter(name0, "name");
            Intrinsics.checkNotNullParameter(classDescriptor0, "classDescriptor");
            return CollectionsKt.emptyList();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider
        public Collection getFunctionsNames(ClassDescriptor classDescriptor0) {
            Intrinsics.checkNotNullParameter(classDescriptor0, "classDescriptor");
            return CollectionsKt.emptyList();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider
        public Collection getSupertypes(ClassDescriptor classDescriptor0) {
            Intrinsics.checkNotNullParameter(classDescriptor0, "classDescriptor");
            return CollectionsKt.emptyList();
        }
    }

    Collection getConstructors(ClassDescriptor arg1);

    Collection getFunctions(Name arg1, ClassDescriptor arg2);

    Collection getFunctionsNames(ClassDescriptor arg1);

    Collection getSupertypes(ClassDescriptor arg1);
}

