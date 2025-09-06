package kotlin.reflect.jvm.internal.impl.descriptors.java;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.Internal;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.Protected;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;

public final class JavaVisibilities {
    public static final class PackageVisibility extends Visibility {
        public static final PackageVisibility INSTANCE;

        static {
            PackageVisibility.INSTANCE = new PackageVisibility();
        }

        private PackageVisibility() {
            super("package", false);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
        public Integer compareTo(Visibility visibility0) {
            Intrinsics.checkNotNullParameter(visibility0, "visibility");
            if(this == visibility0) {
                return 0;
            }
            return Visibilities.INSTANCE.isPrivate(visibility0) ? 1 : -1;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
        public String getInternalDisplayName() {
            return "public/*package*/";
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
        public Visibility normalize() {
            return Protected.INSTANCE;
        }
    }

    public static final class ProtectedAndPackage extends Visibility {
        public static final ProtectedAndPackage INSTANCE;

        static {
            ProtectedAndPackage.INSTANCE = new ProtectedAndPackage();
        }

        private ProtectedAndPackage() {
            super("protected_and_package", true);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
        public Integer compareTo(Visibility visibility0) {
            Intrinsics.checkNotNullParameter(visibility0, "visibility");
            if(Intrinsics.areEqual(this, visibility0)) {
                return 0;
            }
            if(visibility0 == Internal.INSTANCE) {
                return null;
            }
            return Visibilities.INSTANCE.isPrivate(visibility0) ? 1 : -1;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
        public String getInternalDisplayName() {
            return "protected/*protected and package*/";
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
        public Visibility normalize() {
            return Protected.INSTANCE;
        }
    }

    public static final class ProtectedStaticVisibility extends Visibility {
        public static final ProtectedStaticVisibility INSTANCE;

        static {
            ProtectedStaticVisibility.INSTANCE = new ProtectedStaticVisibility();
        }

        private ProtectedStaticVisibility() {
            super("protected_static", true);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
        public String getInternalDisplayName() {
            return "protected/*protected static*/";
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
        public Visibility normalize() {
            return Protected.INSTANCE;
        }
    }

    public static final JavaVisibilities INSTANCE;

    static {
        JavaVisibilities.INSTANCE = new JavaVisibilities();
    }
}

