package kotlin.reflect.jvm.internal.impl.types;

import java.util.Collection;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefinerKt;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorUtils;

public abstract class AbstractTypeConstructor extends ClassifierBasedTypeConstructor {
    final class ModuleViewTypeConstructor implements TypeConstructor {
        private final KotlinTypeRefiner kotlinTypeRefiner;
        private final Lazy refinedSupertypes$delegate;

        public ModuleViewTypeConstructor(KotlinTypeRefiner kotlinTypeRefiner0) {
            Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "kotlinTypeRefiner");
            AbstractTypeConstructor.this = abstractTypeConstructor0;
            super();
            this.kotlinTypeRefiner = kotlinTypeRefiner0;
            Function0 function00 = new Function0(abstractTypeConstructor0) {
                {
                    ModuleViewTypeConstructor.this = abstractTypeConstructor$ModuleViewTypeConstructor0;
                    AbstractTypeConstructor.this = abstractTypeConstructor0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final List invoke() {
                    return KotlinTypeRefinerKt.refineTypes(ModuleViewTypeConstructor.access$getKotlinTypeRefiner$p(ModuleViewTypeConstructor.this), AbstractTypeConstructor.this.getSupertypes());
                }
            };
            this.refinedSupertypes$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, function00);
        }

        public static final KotlinTypeRefiner access$getKotlinTypeRefiner$p(ModuleViewTypeConstructor abstractTypeConstructor$ModuleViewTypeConstructor0) {
            return abstractTypeConstructor$ModuleViewTypeConstructor0.kotlinTypeRefiner;
        }

        @Override
        public boolean equals(Object object0) {
            return AbstractTypeConstructor.this.equals(object0);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        public KotlinBuiltIns getBuiltIns() {
            KotlinBuiltIns kotlinBuiltIns0 = AbstractTypeConstructor.this.getBuiltIns();
            Intrinsics.checkNotNullExpressionValue(kotlinBuiltIns0, "this@AbstractTypeConstructor.builtIns");
            return kotlinBuiltIns0;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        public ClassifierDescriptor getDeclarationDescriptor() {
            return AbstractTypeConstructor.this.getDeclarationDescriptor();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        public List getParameters() {
            List list0 = AbstractTypeConstructor.this.getParameters();
            Intrinsics.checkNotNullExpressionValue(list0, "this@AbstractTypeConstructor.parameters");
            return list0;
        }

        private final List getRefinedSupertypes() {
            return (List)this.refinedSupertypes$delegate.getValue();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        public Collection getSupertypes() {
            return this.getSupertypes();
        }

        public List getSupertypes() {
            return this.getRefinedSupertypes();
        }

        @Override
        public int hashCode() {
            return AbstractTypeConstructor.this.hashCode();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        public boolean isDenotable() {
            return AbstractTypeConstructor.this.isDenotable();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        public TypeConstructor refine(KotlinTypeRefiner kotlinTypeRefiner0) {
            Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "kotlinTypeRefiner");
            return AbstractTypeConstructor.this.refine(kotlinTypeRefiner0);
        }

        @Override
        public String toString() {
            return AbstractTypeConstructor.this.toString();
        }
    }

    static final class Supertypes {
        private final Collection allSupertypes;
        private List supertypesWithoutCycles;

        public Supertypes(Collection collection0) {
            Intrinsics.checkNotNullParameter(collection0, "allSupertypes");
            super();
            this.allSupertypes = collection0;
            this.supertypesWithoutCycles = CollectionsKt.listOf(ErrorUtils.INSTANCE.getErrorTypeForLoopInSupertypes());
        }

        public final Collection getAllSupertypes() {
            return this.allSupertypes;
        }

        public final List getSupertypesWithoutCycles() {
            return this.supertypesWithoutCycles;
        }

        public final void setSupertypesWithoutCycles(List list0) {
            Intrinsics.checkNotNullParameter(list0, "<set-?>");
            this.supertypesWithoutCycles = list0;
        }
    }

    private final boolean shouldReportCyclicScopeWithCompanionWarning;
    private final NotNullLazyValue supertypes;

    public AbstractTypeConstructor(StorageManager storageManager0) {
        Intrinsics.checkNotNullParameter(storageManager0, "storageManager");
        super();
        Function0 function00 = new Function0() {
            {
                AbstractTypeConstructor.this = abstractTypeConstructor0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final Supertypes invoke() {
                return new Supertypes(AbstractTypeConstructor.this.computeSupertypes());
            }
        };
        Function1 function10 = new Function1() {
            {
                AbstractTypeConstructor.this = abstractTypeConstructor0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                this.invoke(((Supertypes)object0));
                return Unit.INSTANCE;
            }

            public final void invoke(Supertypes abstractTypeConstructor$Supertypes0) {
                Intrinsics.checkNotNullParameter(abstractTypeConstructor$Supertypes0, "supertypes");
                SupertypeLoopChecker supertypeLoopChecker0 = AbstractTypeConstructor.this.getSupertypeLoopChecker();
                kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor.supertypes.3.resultWithoutCycles.1 abstractTypeConstructor$supertypes$3$resultWithoutCycles$10 = new Function1() {
                    {
                        AbstractTypeConstructor.this = abstractTypeConstructor0;
                        super(1);
                    }

                    public final Iterable invoke(TypeConstructor typeConstructor0) {
                        Intrinsics.checkNotNullParameter(typeConstructor0, "it");
                        return AbstractTypeConstructor.access$computeNeighbours(AbstractTypeConstructor.this, typeConstructor0, false);
                    }

                    @Override  // kotlin.jvm.functions.Function1
                    public Object invoke(Object object0) {
                        return this.invoke(((TypeConstructor)object0));
                    }
                };
                kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor.supertypes.3.resultWithoutCycles.2 abstractTypeConstructor$supertypes$3$resultWithoutCycles$20 = new Function1() {
                    {
                        AbstractTypeConstructor.this = abstractTypeConstructor0;
                        super(1);
                    }

                    @Override  // kotlin.jvm.functions.Function1
                    public Object invoke(Object object0) {
                        this.invoke(((KotlinType)object0));
                        return Unit.INSTANCE;
                    }

                    public final void invoke(KotlinType kotlinType0) {
                        Intrinsics.checkNotNullParameter(kotlinType0, "it");
                        AbstractTypeConstructor.this.reportSupertypeLoopError(kotlinType0);
                    }
                };
                Collection collection0 = supertypeLoopChecker0.findLoopsInSupertypesAndDisconnect(AbstractTypeConstructor.this, abstractTypeConstructor$Supertypes0.getAllSupertypes(), abstractTypeConstructor$supertypes$3$resultWithoutCycles$10, abstractTypeConstructor$supertypes$3$resultWithoutCycles$20);
                List list0 = null;
                if(collection0.isEmpty()) {
                    KotlinType kotlinType0 = AbstractTypeConstructor.this.defaultSupertypeIfEmpty();
                    List list1 = kotlinType0 == null ? null : CollectionsKt.listOf(kotlinType0);
                    if(list1 == null) {
                        list1 = CollectionsKt.emptyList();
                    }
                    collection0 = list1;
                }
                if(AbstractTypeConstructor.this.getShouldReportCyclicScopeWithCompanionWarning()) {
                    SupertypeLoopChecker supertypeLoopChecker1 = AbstractTypeConstructor.this.getSupertypeLoopChecker();
                    kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor.supertypes.3.2 abstractTypeConstructor$supertypes$3$20 = new Function1() {
                        {
                            AbstractTypeConstructor.this = abstractTypeConstructor0;
                            super(1);
                        }

                        public final Iterable invoke(TypeConstructor typeConstructor0) {
                            Intrinsics.checkNotNullParameter(typeConstructor0, "it");
                            return AbstractTypeConstructor.access$computeNeighbours(AbstractTypeConstructor.this, typeConstructor0, true);
                        }

                        @Override  // kotlin.jvm.functions.Function1
                        public Object invoke(Object object0) {
                            return this.invoke(((TypeConstructor)object0));
                        }
                    };
                    kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor.supertypes.3.3 abstractTypeConstructor$supertypes$3$30 = new Function1() {
                        {
                            AbstractTypeConstructor.this = abstractTypeConstructor0;
                            super(1);
                        }

                        @Override  // kotlin.jvm.functions.Function1
                        public Object invoke(Object object0) {
                            this.invoke(((KotlinType)object0));
                            return Unit.INSTANCE;
                        }

                        public final void invoke(KotlinType kotlinType0) {
                            Intrinsics.checkNotNullParameter(kotlinType0, "it");
                            AbstractTypeConstructor.this.reportScopesLoopError(kotlinType0);
                        }
                    };
                    supertypeLoopChecker1.findLoopsInSupertypesAndDisconnect(AbstractTypeConstructor.this, collection0, abstractTypeConstructor$supertypes$3$20, abstractTypeConstructor$supertypes$3$30);
                }
                AbstractTypeConstructor abstractTypeConstructor0 = AbstractTypeConstructor.this;
                if(collection0 instanceof List) {
                    list0 = (List)collection0;
                }
                if(list0 == null) {
                    list0 = CollectionsKt.toList(collection0);
                }
                abstractTypeConstructor$Supertypes0.setSupertypesWithoutCycles(abstractTypeConstructor0.processSupertypesWithoutCycles(list0));
            }
        };
        this.supertypes = storageManager0.createLazyValueWithPostCompute(function00, kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor.supertypes.2.INSTANCE, function10);

        final class kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor.supertypes.2 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor.supertypes.2 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor.supertypes.2.INSTANCE = new kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor.supertypes.2();
            }

            kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor.supertypes.2() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((Boolean)object0).booleanValue());
            }

            public final Supertypes invoke(boolean z) {
                return new Supertypes(CollectionsKt.listOf(ErrorUtils.INSTANCE.getErrorTypeForLoopInSupertypes()));
            }
        }

    }

    public static final Collection access$computeNeighbours(AbstractTypeConstructor abstractTypeConstructor0, TypeConstructor typeConstructor0, boolean z) {
        return abstractTypeConstructor0.computeNeighbours(typeConstructor0, z);
    }

    private final Collection computeNeighbours(TypeConstructor typeConstructor0, boolean z) {
        AbstractTypeConstructor abstractTypeConstructor0 = typeConstructor0 instanceof AbstractTypeConstructor ? ((AbstractTypeConstructor)typeConstructor0) : null;
        if(abstractTypeConstructor0 != null) {
            List list0 = CollectionsKt.plus(((Supertypes)abstractTypeConstructor0.supertypes.invoke()).getAllSupertypes(), abstractTypeConstructor0.getAdditionalNeighboursInSupertypeGraph(z));
            if(list0 != null) {
                return list0;
            }
        }
        Collection collection0 = typeConstructor0.getSupertypes();
        Intrinsics.checkNotNullExpressionValue(collection0, "supertypes");
        return collection0;
    }

    protected abstract Collection computeSupertypes();

    protected KotlinType defaultSupertypeIfEmpty() {
        return null;
    }

    protected Collection getAdditionalNeighboursInSupertypeGraph(boolean z) {
        return CollectionsKt.emptyList();
    }

    protected boolean getShouldReportCyclicScopeWithCompanionWarning() {
        return this.shouldReportCyclicScopeWithCompanionWarning;
    }

    protected abstract SupertypeLoopChecker getSupertypeLoopChecker();

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public Collection getSupertypes() {
        return this.getSupertypes();
    }

    public List getSupertypes() {
        return ((Supertypes)this.supertypes.invoke()).getSupertypesWithoutCycles();
    }

    protected List processSupertypesWithoutCycles(List list0) {
        Intrinsics.checkNotNullParameter(list0, "supertypes");
        return list0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public TypeConstructor refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "kotlinTypeRefiner");
        return new ModuleViewTypeConstructor(this, kotlinTypeRefiner0);
    }

    protected void reportScopesLoopError(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "type");
    }

    protected void reportSupertypeLoopError(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "type");
    }
}

