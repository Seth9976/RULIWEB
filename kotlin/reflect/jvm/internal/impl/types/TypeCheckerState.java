package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayDeque;
import java.util.Set;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.model.CapturedTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext;
import kotlin.reflect.jvm.internal.impl.utils.SmartSet;

public class TypeCheckerState {
    public interface ForkPointContext {
        public static final class Default implements ForkPointContext {
            private boolean result;

            @Override  // kotlin.reflect.jvm.internal.impl.types.TypeCheckerState$ForkPointContext
            public void fork(Function0 function00) {
                Intrinsics.checkNotNullParameter(function00, "block");
                if(this.result) {
                    return;
                }
                this.result = ((Boolean)function00.invoke()).booleanValue();
            }

            public final boolean getResult() {
                return this.result;
            }
        }

        void fork(Function0 arg1);
    }

    public static enum LowerCapturedTypePolicy {
        CHECK_ONLY_LOWER,
        CHECK_SUBTYPE_AND_LOWER,
        SKIP_LOWER;

        private static final LowerCapturedTypePolicy[] $values() [...] // Inlined contents
    }

    public static abstract class SupertypesPolicy {
        public static abstract class DoCustomTransform extends SupertypesPolicy {
            public DoCustomTransform() {
                super(null);
            }
        }

        public static final class LowerIfFlexible extends SupertypesPolicy {
            public static final LowerIfFlexible INSTANCE;

            static {
                LowerIfFlexible.INSTANCE = new LowerIfFlexible();
            }

            private LowerIfFlexible() {
                super(null);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.types.TypeCheckerState$SupertypesPolicy
            public SimpleTypeMarker transformType(TypeCheckerState typeCheckerState0, KotlinTypeMarker kotlinTypeMarker0) {
                Intrinsics.checkNotNullParameter(typeCheckerState0, "state");
                Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "type");
                return typeCheckerState0.getTypeSystemContext().lowerBoundIfFlexible(kotlinTypeMarker0);
            }
        }

        public static final class None extends SupertypesPolicy {
            public static final None INSTANCE;

            static {
                None.INSTANCE = new None();
            }

            private None() {
                super(null);
            }

            public Void transformType(TypeCheckerState typeCheckerState0, KotlinTypeMarker kotlinTypeMarker0) {
                Intrinsics.checkNotNullParameter(typeCheckerState0, "state");
                Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "type");
                throw new UnsupportedOperationException("Should not be called");
            }

            @Override  // kotlin.reflect.jvm.internal.impl.types.TypeCheckerState$SupertypesPolicy
            public SimpleTypeMarker transformType(TypeCheckerState typeCheckerState0, KotlinTypeMarker kotlinTypeMarker0) {
                return (SimpleTypeMarker)this.transformType(typeCheckerState0, kotlinTypeMarker0);
            }
        }

        public static final class UpperIfFlexible extends SupertypesPolicy {
            public static final UpperIfFlexible INSTANCE;

            static {
                UpperIfFlexible.INSTANCE = new UpperIfFlexible();
            }

            private UpperIfFlexible() {
                super(null);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.types.TypeCheckerState$SupertypesPolicy
            public SimpleTypeMarker transformType(TypeCheckerState typeCheckerState0, KotlinTypeMarker kotlinTypeMarker0) {
                Intrinsics.checkNotNullParameter(typeCheckerState0, "state");
                Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "type");
                return typeCheckerState0.getTypeSystemContext().upperBoundIfFlexible(kotlinTypeMarker0);
            }
        }

        private SupertypesPolicy() {
        }

        public SupertypesPolicy(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public abstract SimpleTypeMarker transformType(TypeCheckerState arg1, KotlinTypeMarker arg2);
    }

    private final boolean allowedTypeVariable;
    private int argumentsDepth;
    private final boolean isErrorTypeEqualsToAnything;
    private final boolean isStubTypeEqualsToAnything;
    private final AbstractTypePreparator kotlinTypePreparator;
    private final AbstractTypeRefiner kotlinTypeRefiner;
    private ArrayDeque supertypesDeque;
    private boolean supertypesLocked;
    private Set supertypesSet;
    private final TypeSystemContext typeSystemContext;

    public TypeCheckerState(boolean z, boolean z1, boolean z2, TypeSystemContext typeSystemContext0, AbstractTypePreparator abstractTypePreparator0, AbstractTypeRefiner abstractTypeRefiner0) {
        Intrinsics.checkNotNullParameter(typeSystemContext0, "typeSystemContext");
        Intrinsics.checkNotNullParameter(abstractTypePreparator0, "kotlinTypePreparator");
        Intrinsics.checkNotNullParameter(abstractTypeRefiner0, "kotlinTypeRefiner");
        super();
        this.isErrorTypeEqualsToAnything = z;
        this.isStubTypeEqualsToAnything = z1;
        this.allowedTypeVariable = z2;
        this.typeSystemContext = typeSystemContext0;
        this.kotlinTypePreparator = abstractTypePreparator0;
        this.kotlinTypeRefiner = abstractTypeRefiner0;
    }

    public Boolean addSubtypeConstraint(KotlinTypeMarker kotlinTypeMarker0, KotlinTypeMarker kotlinTypeMarker1, boolean z) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "subType");
        Intrinsics.checkNotNullParameter(kotlinTypeMarker1, "superType");
        return null;
    }

    public static Boolean addSubtypeConstraint$default(TypeCheckerState typeCheckerState0, KotlinTypeMarker kotlinTypeMarker0, KotlinTypeMarker kotlinTypeMarker1, boolean z, int v, Object object0) {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addSubtypeConstraint");
        }
        if((v & 4) != 0) {
            z = false;
        }
        return typeCheckerState0.addSubtypeConstraint(kotlinTypeMarker0, kotlinTypeMarker1, z);
    }

    public final void clear() {
        ArrayDeque arrayDeque0 = this.supertypesDeque;
        Intrinsics.checkNotNull(arrayDeque0);
        arrayDeque0.clear();
        Set set0 = this.supertypesSet;
        Intrinsics.checkNotNull(set0);
        set0.clear();
        this.supertypesLocked = false;
    }

    public boolean customIsSubtypeOf(KotlinTypeMarker kotlinTypeMarker0, KotlinTypeMarker kotlinTypeMarker1) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "subType");
        Intrinsics.checkNotNullParameter(kotlinTypeMarker1, "superType");
        return true;
    }

    public LowerCapturedTypePolicy getLowerCapturedTypePolicy(SimpleTypeMarker simpleTypeMarker0, CapturedTypeMarker capturedTypeMarker0) {
        Intrinsics.checkNotNullParameter(simpleTypeMarker0, "subType");
        Intrinsics.checkNotNullParameter(capturedTypeMarker0, "superType");
        return LowerCapturedTypePolicy.CHECK_SUBTYPE_AND_LOWER;
    }

    public final ArrayDeque getSupertypesDeque() {
        return this.supertypesDeque;
    }

    public final Set getSupertypesSet() {
        return this.supertypesSet;
    }

    public final TypeSystemContext getTypeSystemContext() {
        return this.typeSystemContext;
    }

    public final void initialize() {
        this.supertypesLocked = true;
        if(this.supertypesDeque == null) {
            this.supertypesDeque = new ArrayDeque(4);
        }
        if(this.supertypesSet == null) {
            this.supertypesSet = SmartSet.Companion.create();
        }
    }

    public final boolean isAllowedTypeVariable(KotlinTypeMarker kotlinTypeMarker0) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "type");
        return this.allowedTypeVariable && this.typeSystemContext.isTypeVariableType(kotlinTypeMarker0);
    }

    public final boolean isErrorTypeEqualsToAnything() {
        return this.isErrorTypeEqualsToAnything;
    }

    public final boolean isStubTypeEqualsToAnything() {
        return this.isStubTypeEqualsToAnything;
    }

    public final KotlinTypeMarker prepareType(KotlinTypeMarker kotlinTypeMarker0) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "type");
        return this.kotlinTypePreparator.prepareType(kotlinTypeMarker0);
    }

    public final KotlinTypeMarker refineType(KotlinTypeMarker kotlinTypeMarker0) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "type");
        return this.kotlinTypeRefiner.refineType(kotlinTypeMarker0);
    }

    public boolean runForkingPoint(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "block");
        Default typeCheckerState$ForkPointContext$Default0 = new Default();
        function10.invoke(typeCheckerState$ForkPointContext$Default0);
        return typeCheckerState$ForkPointContext$Default0.getResult();
    }
}

