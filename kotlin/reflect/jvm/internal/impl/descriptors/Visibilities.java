package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.Map;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

public final class Visibilities {
    public static final class Inherited extends Visibility {
        public static final Inherited INSTANCE;

        static {
            Inherited.INSTANCE = new Inherited();
        }

        private Inherited() {
            super("inherited", false);
        }
    }

    public static final class Internal extends Visibility {
        public static final Internal INSTANCE;

        static {
            Internal.INSTANCE = new Internal();
        }

        private Internal() {
            super("internal", false);
        }
    }

    public static final class InvisibleFake extends Visibility {
        public static final InvisibleFake INSTANCE;

        static {
            InvisibleFake.INSTANCE = new InvisibleFake();
        }

        private InvisibleFake() {
            super("invisible_fake", false);
        }
    }

    public static final class Local extends Visibility {
        public static final Local INSTANCE;

        static {
            Local.INSTANCE = new Local();
        }

        private Local() {
            super("local", false);
        }
    }

    public static final class Private extends Visibility {
        public static final Private INSTANCE;

        static {
            Private.INSTANCE = new Private();
        }

        private Private() {
            super("private", false);
        }
    }

    public static final class PrivateToThis extends Visibility {
        public static final PrivateToThis INSTANCE;

        static {
            PrivateToThis.INSTANCE = new PrivateToThis();
        }

        private PrivateToThis() {
            super("private_to_this", false);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
        public String getInternalDisplayName() {
            return "private/*private to this*/";
        }
    }

    public static final class Protected extends Visibility {
        public static final Protected INSTANCE;

        static {
            Protected.INSTANCE = new Protected();
        }

        private Protected() {
            super("protected", true);
        }
    }

    public static final class Public extends Visibility {
        public static final Public INSTANCE;

        static {
            Public.INSTANCE = new Public();
        }

        private Public() {
            super("public", true);
        }
    }

    public static final class Unknown extends Visibility {
        public static final Unknown INSTANCE;

        static {
            Unknown.INSTANCE = new Unknown();
        }

        private Unknown() {
            super("unknown", false);
        }
    }

    private static final Public DEFAULT_VISIBILITY;
    public static final Visibilities INSTANCE;
    private static final Map ORDERED_VISIBILITIES;

    static {
        Visibilities.INSTANCE = new Visibilities();
        Map map0 = MapsKt.createMapBuilder();
        map0.put(PrivateToThis.INSTANCE, 0);
        map0.put(Private.INSTANCE, 0);
        map0.put(Internal.INSTANCE, 1);
        map0.put(Protected.INSTANCE, 1);
        map0.put(Public.INSTANCE, 2);
        Visibilities.ORDERED_VISIBILITIES = MapsKt.build(map0);
        Visibilities.DEFAULT_VISIBILITY = Public.INSTANCE;
    }

    public final Integer compareLocal$compiler_common(Visibility visibility0, Visibility visibility1) {
        Intrinsics.checkNotNullParameter(visibility0, "first");
        Intrinsics.checkNotNullParameter(visibility1, "second");
        if(visibility0 == visibility1) {
            return 0;
        }
        Integer integer0 = (Integer)Visibilities.ORDERED_VISIBILITIES.get(visibility0);
        Integer integer1 = (Integer)Visibilities.ORDERED_VISIBILITIES.get(visibility1);
        return integer0 == null || integer1 == null || Intrinsics.areEqual(integer0, integer1) ? null : ((int)(((int)integer0) - ((int)integer1)));
    }

    public final boolean isPrivate(Visibility visibility0) {
        Intrinsics.checkNotNullParameter(visibility0, "visibility");
        return visibility0 == Private.INSTANCE || visibility0 == PrivateToThis.INSTANCE;
    }
}

