package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorNonRoot;
import kotlin.reflect.jvm.internal.impl.descriptors.Substitutable;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructorKt;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.utils.CollectionsKt;

public final class SubstitutingScope implements MemberScope {
    private final Lazy _allDescriptors$delegate;
    private final TypeSubstitutor capturingSubstitutor;
    private Map substitutedDescriptors;
    private final Lazy substitutor$delegate;
    private final MemberScope workerScope;

    public SubstitutingScope(MemberScope memberScope0, TypeSubstitutor typeSubstitutor0) {
        Intrinsics.checkNotNullParameter(memberScope0, "workerScope");
        Intrinsics.checkNotNullParameter(typeSubstitutor0, "givenSubstitutor");
        super();
        this.workerScope = memberScope0;
        this.substitutor$delegate = LazyKt.lazy(new Function0() {
            final TypeSubstitutor $givenSubstitutor;

            {
                this.$givenSubstitutor = typeSubstitutor0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final TypeSubstitutor invoke() {
                return this.$givenSubstitutor.getSubstitution().buildSubstitutor();
            }
        });
        TypeSubstitution typeSubstitution0 = typeSubstitutor0.getSubstitution();
        Intrinsics.checkNotNullExpressionValue(typeSubstitution0, "givenSubstitutor.substitution");
        this.capturingSubstitutor = CapturedTypeConstructorKt.wrapWithCapturingSubstitution$default(typeSubstitution0, false, 1, null).buildSubstitutor();
        this._allDescriptors$delegate = LazyKt.lazy(new Function0() {
            {
                SubstitutingScope.this = substitutingScope0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final Collection invoke() {
                Collection collection0 = DefaultImpls.getContributedDescriptors$default(SubstitutingScope.access$getWorkerScope$p(SubstitutingScope.this), null, null, 3, null);
                return SubstitutingScope.access$substitute(SubstitutingScope.this, collection0);
            }
        });
    }

    public static final MemberScope access$getWorkerScope$p(SubstitutingScope substitutingScope0) {
        return substitutingScope0.workerScope;
    }

    public static final Collection access$substitute(SubstitutingScope substitutingScope0, Collection collection0) {
        return substitutingScope0.substitute(collection0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Set getClassifierNames() {
        return this.workerScope.getClassifierNames();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    public ClassifierDescriptor getContributedClassifier(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        ClassifierDescriptor classifierDescriptor0 = this.workerScope.getContributedClassifier(name0, lookupLocation0);
        return classifierDescriptor0 == null ? null : ((ClassifierDescriptor)this.substitute(classifierDescriptor0));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    public Collection getContributedDescriptors(DescriptorKindFilter descriptorKindFilter0, Function1 function10) {
        Intrinsics.checkNotNullParameter(descriptorKindFilter0, "kindFilter");
        Intrinsics.checkNotNullParameter(function10, "nameFilter");
        return this.get_allDescriptors();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Collection getContributedFunctions(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        return this.substitute(this.workerScope.getContributedFunctions(name0, lookupLocation0));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Collection getContributedVariables(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        return this.substitute(this.workerScope.getContributedVariables(name0, lookupLocation0));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Set getFunctionNames() {
        return this.workerScope.getFunctionNames();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope
    public Set getVariableNames() {
        return this.workerScope.getVariableNames();
    }

    private final Collection get_allDescriptors() {
        return (Collection)this._allDescriptors$delegate.getValue();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope
    public void recordLookup(Name name0, LookupLocation lookupLocation0) {
        kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope.DefaultImpls.recordLookup(this, name0, lookupLocation0);
    }

    private final Collection substitute(Collection collection0) {
        if(this.capturingSubstitutor.isEmpty() || collection0.isEmpty()) {
            return collection0;
        }
        LinkedHashSet linkedHashSet0 = CollectionsKt.newLinkedHashSetWithExpectedSize(collection0.size());
        for(Object object0: collection0) {
            linkedHashSet0.add(this.substitute(((DeclarationDescriptor)object0)));
        }
        return linkedHashSet0;
    }

    private final DeclarationDescriptor substitute(DeclarationDescriptor declarationDescriptor0) {
        if(this.capturingSubstitutor.isEmpty()) {
            return declarationDescriptor0;
        }
        if(this.substitutedDescriptors == null) {
            this.substitutedDescriptors = new HashMap();
        }
        Map map0 = this.substitutedDescriptors;
        Intrinsics.checkNotNull(map0);
        DeclarationDescriptorNonRoot declarationDescriptorNonRoot0 = map0.get(declarationDescriptor0);
        if(declarationDescriptorNonRoot0 == null) {
            if(!(declarationDescriptor0 instanceof Substitutable)) {
                throw new IllegalStateException(("Unknown descriptor in scope: " + declarationDescriptor0).toString());
            }
            DeclarationDescriptorNonRoot declarationDescriptorNonRoot1 = ((Substitutable)declarationDescriptor0).substitute(this.capturingSubstitutor);
            if(declarationDescriptorNonRoot1 == null) {
                throw new AssertionError("We expect that no conflict should happen while substitution is guaranteed to generate invariant projection, but " + declarationDescriptor0 + " substitution fails");
            }
            declarationDescriptorNonRoot0 = declarationDescriptorNonRoot1;
            map0.put(declarationDescriptor0, declarationDescriptorNonRoot0);
        }
        Intrinsics.checkNotNull(declarationDescriptorNonRoot0, "null cannot be cast to non-null type D of org.jetbrains.kotlin.resolve.scopes.SubstitutingScope.substitute");
        return declarationDescriptorNonRoot0;
    }
}

