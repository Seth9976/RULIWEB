package kotlin.reflect.jvm.internal.impl.renderer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

public enum DescriptorRendererModifier {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    VISIBILITY(true),
    MODALITY(true),
    OVERRIDE(true),
    ANNOTATIONS(false),
    INNER(true),
    MEMBER_KIND(true),
    DATA(true),
    INLINE(true),
    EXPECT(true),
    ACTUAL(true),
    CONST(true),
    LATEINIT(true),
    FUN(true),
    VALUE(true);

    public static final Set ALL;
    public static final Set ALL_EXCEPT_ANNOTATIONS;
    public static final Companion Companion;
    private final boolean includeByDefault;

    private static final DescriptorRendererModifier[] $values() [...] // Inlined contents

    static {
        DescriptorRendererModifier.Companion = new Companion(null);
        DescriptorRendererModifier[] arr_descriptorRendererModifier = (DescriptorRendererModifier[])DescriptorRendererModifier.$VALUES.clone();
        Collection collection0 = new ArrayList();
        for(int v = 0; v < arr_descriptorRendererModifier.length; ++v) {
            DescriptorRendererModifier descriptorRendererModifier0 = arr_descriptorRendererModifier[v];
            if(descriptorRendererModifier0.includeByDefault) {
                collection0.add(descriptorRendererModifier0);
            }
        }
        DescriptorRendererModifier.ALL_EXCEPT_ANNOTATIONS = CollectionsKt.toSet(((List)collection0));
        DescriptorRendererModifier.ALL = ArraysKt.toSet(((DescriptorRendererModifier[])DescriptorRendererModifier.$VALUES.clone()));
    }

    private DescriptorRendererModifier(boolean z) {
        this.includeByDefault = z;
    }
}

