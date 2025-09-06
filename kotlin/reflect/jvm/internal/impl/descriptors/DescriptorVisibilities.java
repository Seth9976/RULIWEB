package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;
import kotlin.collections.SetsKt;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.TypeAliasConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ReceiverValue;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.SuperCallReceiverValue;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ThisClassReceiver;
import kotlin.reflect.jvm.internal.impl.types.DynamicTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.util.ModuleVisibilityHelper.EMPTY;
import kotlin.reflect.jvm.internal.impl.util.ModuleVisibilityHelper;
import kotlin.reflect.jvm.internal.impl.utils.CollectionsKt;

public class DescriptorVisibilities {
    public static final ReceiverValue ALWAYS_SUITABLE_RECEIVER;
    public static final DescriptorVisibility DEFAULT_VISIBILITY;
    @Deprecated
    public static final ReceiverValue FALSE_IF_PROTECTED;
    public static final DescriptorVisibility INHERITED;
    public static final DescriptorVisibility INTERNAL;
    public static final DescriptorVisibility INVISIBLE_FAKE;
    public static final Set INVISIBLE_FROM_OTHER_MODULES;
    private static final ReceiverValue IRRELEVANT_RECEIVER;
    public static final DescriptorVisibility LOCAL;
    private static final ModuleVisibilityHelper MODULE_VISIBILITY_HELPER;
    private static final Map ORDERED_VISIBILITIES;
    public static final DescriptorVisibility PRIVATE;
    public static final DescriptorVisibility PRIVATE_TO_THIS;
    public static final DescriptorVisibility PROTECTED;
    public static final DescriptorVisibility PUBLIC;
    public static final DescriptorVisibility UNKNOWN;
    private static final Map visibilitiesMapping;

    private static void $$$reportNull$$$0(int v) {
        Object[] arr_object = new Object[(v == 16 ? 2 : 3)];
        if(v == 1 || v == 3 || (v == 5 || v == 7)) {
            arr_object[0] = "from";
        }
        else {
            switch(v) {
                case 9: {
                    arr_object[0] = "from";
                    break;
                }
                case 10: 
                case 12: {
                    arr_object[0] = "first";
                    break;
                }
                case 11: 
                case 13: {
                    arr_object[0] = "second";
                    break;
                }
                case 14: 
                case 15: {
                    arr_object[0] = "visibility";
                    break;
                }
                case 16: {
                    arr_object[0] = "kotlin/reflect/jvm/internal/impl/descriptors/DescriptorVisibilities";
                    break;
                }
                default: {
                    arr_object[0] = "what";
                }
            }
        }
        arr_object[1] = v == 16 ? "toDescriptorVisibility" : "kotlin/reflect/jvm/internal/impl/descriptors/DescriptorVisibilities";
        switch(v) {
            case 2: 
            case 3: {
                arr_object[2] = "isVisibleIgnoringReceiver";
                break;
            }
            case 4: 
            case 5: {
                arr_object[2] = "isVisibleWithAnyReceiver";
                break;
            }
            case 6: 
            case 7: {
                arr_object[2] = "inSameFile";
                break;
            }
            case 8: 
            case 9: {
                arr_object[2] = "findInvisibleMember";
                break;
            }
            case 10: 
            case 11: {
                arr_object[2] = "compareLocal";
                break;
            }
            case 12: 
            case 13: {
                arr_object[2] = "compare";
                break;
            }
            case 14: {
                arr_object[2] = "isPrivate";
                break;
            }
            case 15: {
                arr_object[2] = "toDescriptorVisibility";
                break;
            }
            case 16: {
                break;
            }
            default: {
                arr_object[2] = "isVisible";
            }
        }
        String s = String.format((v == 16 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null"), arr_object);
        IllegalArgumentException illegalArgumentException0 = v == 16 ? new IllegalStateException(s) : new IllegalArgumentException(s);
        throw illegalArgumentException0;
    }

    static {
        ModuleVisibilityHelper moduleVisibilityHelper0;
        kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.1 descriptorVisibilities$10 = new DelegatedDescriptorVisibility(Private.INSTANCE) {
            private static void $$$reportNull$$$0(int v) {
                Object[] arr_object = new Object[3];
                switch(v) {
                    case 1: {
                        arr_object[0] = "what";
                        break;
                    }
                    case 2: {
                        arr_object[0] = "from";
                        break;
                    }
                    default: {
                        arr_object[0] = "descriptor";
                    }
                }
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/descriptors/DescriptorVisibilities$1";
                arr_object[2] = v == 1 || v == 2 ? "isVisible" : "hasContainingSourceFile";
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter \'%s\' of %s.%s must not be null", arr_object));
            }

            private boolean hasContainingSourceFile(DeclarationDescriptor declarationDescriptor0) {
                if(declarationDescriptor0 == null) {
                    kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.1.$$$reportNull$$$0(0);
                }
                return DescriptorUtils.getContainingSourceFile(declarationDescriptor0) != SourceFile.NO_SOURCE_FILE;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility
            public boolean isVisible(ReceiverValue receiverValue0, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility0, DeclarationDescriptor declarationDescriptor0, boolean z) {
                if(declarationDescriptorWithVisibility0 == null) {
                    kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.1.$$$reportNull$$$0(1);
                }
                if(declarationDescriptor0 == null) {
                    kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.1.$$$reportNull$$$0(2);
                }
                if(DescriptorUtils.isTopLevelDeclaration(declarationDescriptorWithVisibility0) && this.hasContainingSourceFile(declarationDescriptor0)) {
                    return DescriptorVisibilities.inSameFile(declarationDescriptorWithVisibility0, declarationDescriptor0);
                }
                if(declarationDescriptorWithVisibility0 instanceof ConstructorDescriptor) {
                    ClassifierDescriptorWithTypeParameters classifierDescriptorWithTypeParameters0 = ((ConstructorDescriptor)declarationDescriptorWithVisibility0).getContainingDeclaration();
                    if(z && DescriptorUtils.isSealedClass(classifierDescriptorWithTypeParameters0) && DescriptorUtils.isTopLevelDeclaration(classifierDescriptorWithTypeParameters0) && declarationDescriptor0 instanceof ConstructorDescriptor && DescriptorUtils.isTopLevelDeclaration(declarationDescriptor0.getContainingDeclaration()) && DescriptorVisibilities.inSameFile(declarationDescriptorWithVisibility0, declarationDescriptor0)) {
                        return true;
                    }
                }
                while(declarationDescriptorWithVisibility0 != null) {
                    declarationDescriptorWithVisibility0 = declarationDescriptorWithVisibility0.getContainingDeclaration();
                    if(declarationDescriptorWithVisibility0 instanceof ClassDescriptor && !DescriptorUtils.isCompanionObject(declarationDescriptorWithVisibility0) || declarationDescriptorWithVisibility0 instanceof PackageFragmentDescriptor) {
                        break;
                    }
                }
                if(declarationDescriptorWithVisibility0 == null) {
                    return false;
                }
                while(declarationDescriptor0 != null) {
                    if(declarationDescriptorWithVisibility0 == declarationDescriptor0) {
                        return true;
                    }
                    if(declarationDescriptor0 instanceof PackageFragmentDescriptor) {
                        return declarationDescriptorWithVisibility0 instanceof PackageFragmentDescriptor && ((PackageFragmentDescriptor)declarationDescriptorWithVisibility0).getFqName().equals(((PackageFragmentDescriptor)declarationDescriptor0).getFqName()) && DescriptorUtils.areInSameModule(declarationDescriptor0, declarationDescriptorWithVisibility0);
                    }
                    declarationDescriptor0 = declarationDescriptor0.getContainingDeclaration();
                }
                return false;
            }
        };
        DescriptorVisibilities.PRIVATE = descriptorVisibilities$10;
        kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.2 descriptorVisibilities$20 = new DelegatedDescriptorVisibility(PrivateToThis.INSTANCE) {
            private static void $$$reportNull$$$0(int v) {
                Object[] arr_object = new Object[3];
                arr_object[0] = v == 1 ? "from" : "what";
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/descriptors/DescriptorVisibilities$2";
                arr_object[2] = "isVisible";
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter \'%s\' of %s.%s must not be null", arr_object));
            }

            @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility
            public boolean isVisible(ReceiverValue receiverValue0, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility0, DeclarationDescriptor declarationDescriptor0, boolean z) {
                if(declarationDescriptorWithVisibility0 == null) {
                    kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.2.$$$reportNull$$$0(0);
                }
                if(declarationDescriptor0 == null) {
                    kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.2.$$$reportNull$$$0(1);
                }
                if(DescriptorVisibilities.PRIVATE.isVisible(receiverValue0, declarationDescriptorWithVisibility0, declarationDescriptor0, z)) {
                    if(receiverValue0 == DescriptorVisibilities.ALWAYS_SUITABLE_RECEIVER) {
                        return true;
                    }
                    if(receiverValue0 == DescriptorVisibilities.IRRELEVANT_RECEIVER) {
                        return false;
                    }
                    DeclarationDescriptor declarationDescriptor1 = DescriptorUtils.getParentOfType(declarationDescriptorWithVisibility0, ClassDescriptor.class);
                    return declarationDescriptor1 == null || !(receiverValue0 instanceof ThisClassReceiver) ? false : ((ThisClassReceiver)receiverValue0).getClassDescriptor().getOriginal().equals(declarationDescriptor1.getOriginal());
                }
                return false;
            }
        };
        DescriptorVisibilities.PRIVATE_TO_THIS = descriptorVisibilities$20;
        kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.3 descriptorVisibilities$30 = new DelegatedDescriptorVisibility(Protected.INSTANCE) {
            private static void $$$reportNull$$$0(int v) {
                Object[] arr_object = new Object[3];
                switch(v) {
                    case 1: {
                        arr_object[0] = "from";
                        break;
                    }
                    case 2: {
                        arr_object[0] = "whatDeclaration";
                        break;
                    }
                    case 3: {
                        arr_object[0] = "fromClass";
                        break;
                    }
                    default: {
                        arr_object[0] = "what";
                    }
                }
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/descriptors/DescriptorVisibilities$3";
                arr_object[2] = v == 2 || v == 3 ? "doesReceiverFitForProtectedVisibility" : "isVisible";
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter \'%s\' of %s.%s must not be null", arr_object));
            }

            private boolean doesReceiverFitForProtectedVisibility(ReceiverValue receiverValue0, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility0, ClassDescriptor classDescriptor0) {
                if(declarationDescriptorWithVisibility0 == null) {
                    kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.3.$$$reportNull$$$0(2);
                }
                if(classDescriptor0 == null) {
                    kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.3.$$$reportNull$$$0(3);
                }
                if(receiverValue0 == DescriptorVisibilities.FALSE_IF_PROTECTED) {
                    return false;
                }
                if(!(declarationDescriptorWithVisibility0 instanceof CallableMemberDescriptor)) {
                    return true;
                }
                if(declarationDescriptorWithVisibility0 instanceof ConstructorDescriptor) {
                    return true;
                }
                if(receiverValue0 == DescriptorVisibilities.ALWAYS_SUITABLE_RECEIVER) {
                    return true;
                }
                if(receiverValue0 != DescriptorVisibilities.IRRELEVANT_RECEIVER && receiverValue0 != null) {
                    KotlinType kotlinType0 = receiverValue0 instanceof SuperCallReceiverValue ? ((SuperCallReceiverValue)receiverValue0).getThisType() : receiverValue0.getType();
                    return DescriptorUtils.isSubtypeOfClass(kotlinType0, classDescriptor0) || DynamicTypesKt.isDynamic(kotlinType0);
                }
                return false;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility
            public boolean isVisible(ReceiverValue receiverValue0, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility0, DeclarationDescriptor declarationDescriptor0, boolean z) {
                if(declarationDescriptorWithVisibility0 == null) {
                    kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.3.$$$reportNull$$$0(0);
                }
                if(declarationDescriptor0 == null) {
                    kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.3.$$$reportNull$$$0(1);
                }
                ClassDescriptor classDescriptor0 = (ClassDescriptor)DescriptorUtils.getParentOfType(declarationDescriptorWithVisibility0, ClassDescriptor.class);
                ClassDescriptor classDescriptor1 = (ClassDescriptor)DescriptorUtils.getParentOfType(declarationDescriptor0, ClassDescriptor.class, false);
                if(classDescriptor1 == null) {
                    return false;
                }
                if(classDescriptor0 != null && DescriptorUtils.isCompanionObject(classDescriptor0)) {
                    ClassDescriptor classDescriptor2 = (ClassDescriptor)DescriptorUtils.getParentOfType(classDescriptor0, ClassDescriptor.class);
                    if(classDescriptor2 != null && DescriptorUtils.isSubclass(classDescriptor1, classDescriptor2)) {
                        return true;
                    }
                }
                DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility1 = DescriptorUtils.unwrapFakeOverrideToAnyDeclaration(declarationDescriptorWithVisibility0);
                ClassDescriptor classDescriptor3 = (ClassDescriptor)DescriptorUtils.getParentOfType(declarationDescriptorWithVisibility1, ClassDescriptor.class);
                if(classDescriptor3 == null) {
                    return false;
                }
                return !DescriptorUtils.isSubclass(classDescriptor1, classDescriptor3) || !this.doesReceiverFitForProtectedVisibility(receiverValue0, declarationDescriptorWithVisibility1, classDescriptor1) ? this.isVisible(receiverValue0, declarationDescriptorWithVisibility0, classDescriptor1.getContainingDeclaration(), z) : true;
            }
        };
        DescriptorVisibilities.PROTECTED = descriptorVisibilities$30;
        kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.4 descriptorVisibilities$40 = new DelegatedDescriptorVisibility(Internal.INSTANCE) {
            private static void $$$reportNull$$$0(int v) {
                Object[] arr_object = new Object[3];
                arr_object[0] = v == 1 ? "from" : "what";
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/descriptors/DescriptorVisibilities$4";
                arr_object[2] = "isVisible";
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter \'%s\' of %s.%s must not be null", arr_object));
            }

            @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility
            public boolean isVisible(ReceiverValue receiverValue0, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility0, DeclarationDescriptor declarationDescriptor0, boolean z) {
                if(declarationDescriptorWithVisibility0 == null) {
                    kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.4.$$$reportNull$$$0(0);
                }
                if(declarationDescriptor0 == null) {
                    kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.4.$$$reportNull$$$0(1);
                }
                ModuleDescriptor moduleDescriptor0 = DescriptorUtils.getContainingModule(declarationDescriptorWithVisibility0);
                return DescriptorUtils.getContainingModule(declarationDescriptor0).shouldSeeInternalsOf(moduleDescriptor0) ? DescriptorVisibilities.MODULE_VISIBILITY_HELPER.isInFriendModule(declarationDescriptorWithVisibility0, declarationDescriptor0) : false;
            }
        };
        DescriptorVisibilities.INTERNAL = descriptorVisibilities$40;
        kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.5 descriptorVisibilities$50 = new DelegatedDescriptorVisibility(Public.INSTANCE) {
            private static void $$$reportNull$$$0(int v) {
                Object[] arr_object = new Object[3];
                arr_object[0] = v == 1 ? "from" : "what";
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/descriptors/DescriptorVisibilities$5";
                arr_object[2] = "isVisible";
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter \'%s\' of %s.%s must not be null", arr_object));
            }

            @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility
            public boolean isVisible(ReceiverValue receiverValue0, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility0, DeclarationDescriptor declarationDescriptor0, boolean z) {
                if(declarationDescriptorWithVisibility0 == null) {
                    kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.5.$$$reportNull$$$0(0);
                }
                if(declarationDescriptor0 == null) {
                    kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.5.$$$reportNull$$$0(1);
                }
                return true;
            }
        };
        DescriptorVisibilities.PUBLIC = descriptorVisibilities$50;
        kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.6 descriptorVisibilities$60 = new DelegatedDescriptorVisibility(Local.INSTANCE) {
            private static void $$$reportNull$$$0(int v) {
                Object[] arr_object = new Object[3];
                arr_object[0] = v == 1 ? "from" : "what";
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/descriptors/DescriptorVisibilities$6";
                arr_object[2] = "isVisible";
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter \'%s\' of %s.%s must not be null", arr_object));
            }

            @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility
            public boolean isVisible(ReceiverValue receiverValue0, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility0, DeclarationDescriptor declarationDescriptor0, boolean z) {
                if(declarationDescriptorWithVisibility0 == null) {
                    kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.6.$$$reportNull$$$0(0);
                }
                if(declarationDescriptor0 == null) {
                    kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.6.$$$reportNull$$$0(1);
                }
                throw new IllegalStateException("This method shouldn\'t be invoked for LOCAL visibility");
            }
        };
        DescriptorVisibilities.LOCAL = descriptorVisibilities$60;
        kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.7 descriptorVisibilities$70 = new DelegatedDescriptorVisibility(Inherited.INSTANCE) {
            private static void $$$reportNull$$$0(int v) {
                Object[] arr_object = new Object[3];
                arr_object[0] = v == 1 ? "from" : "what";
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/descriptors/DescriptorVisibilities$7";
                arr_object[2] = "isVisible";
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter \'%s\' of %s.%s must not be null", arr_object));
            }

            @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility
            public boolean isVisible(ReceiverValue receiverValue0, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility0, DeclarationDescriptor declarationDescriptor0, boolean z) {
                if(declarationDescriptorWithVisibility0 == null) {
                    kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.7.$$$reportNull$$$0(0);
                }
                if(declarationDescriptor0 == null) {
                    kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.7.$$$reportNull$$$0(1);
                }
                throw new IllegalStateException("Visibility is unknown yet");
            }
        };
        DescriptorVisibilities.INHERITED = descriptorVisibilities$70;
        kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.8 descriptorVisibilities$80 = new DelegatedDescriptorVisibility(InvisibleFake.INSTANCE) {
            private static void $$$reportNull$$$0(int v) {
                Object[] arr_object = new Object[3];
                arr_object[0] = v == 1 ? "from" : "what";
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/descriptors/DescriptorVisibilities$8";
                arr_object[2] = "isVisible";
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter \'%s\' of %s.%s must not be null", arr_object));
            }

            @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility
            public boolean isVisible(ReceiverValue receiverValue0, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility0, DeclarationDescriptor declarationDescriptor0, boolean z) {
                if(declarationDescriptorWithVisibility0 == null) {
                    kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.8.$$$reportNull$$$0(0);
                }
                if(declarationDescriptor0 == null) {
                    kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.8.$$$reportNull$$$0(1);
                }
                return false;
            }
        };
        DescriptorVisibilities.INVISIBLE_FAKE = descriptorVisibilities$80;
        kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.9 descriptorVisibilities$90 = new DelegatedDescriptorVisibility(Unknown.INSTANCE) {
            private static void $$$reportNull$$$0(int v) {
                Object[] arr_object = new Object[3];
                arr_object[0] = v == 1 ? "from" : "what";
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/descriptors/DescriptorVisibilities$9";
                arr_object[2] = "isVisible";
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter \'%s\' of %s.%s must not be null", arr_object));
            }

            @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility
            public boolean isVisible(ReceiverValue receiverValue0, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility0, DeclarationDescriptor declarationDescriptor0, boolean z) {
                if(declarationDescriptorWithVisibility0 == null) {
                    kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.9.$$$reportNull$$$0(0);
                }
                if(declarationDescriptor0 == null) {
                    kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.9.$$$reportNull$$$0(1);
                }
                return false;
            }
        };
        DescriptorVisibilities.UNKNOWN = descriptorVisibilities$90;
        DescriptorVisibilities.INVISIBLE_FROM_OTHER_MODULES = Collections.unmodifiableSet(SetsKt.setOf(new DescriptorVisibility[]{descriptorVisibilities$10, descriptorVisibilities$20, descriptorVisibilities$40, descriptorVisibilities$60}));
        HashMap hashMap0 = CollectionsKt.newHashMapWithExpectedSize(4);
        hashMap0.put(descriptorVisibilities$20, 0);
        hashMap0.put(descriptorVisibilities$10, 0);
        hashMap0.put(descriptorVisibilities$40, 1);
        hashMap0.put(descriptorVisibilities$30, 1);
        hashMap0.put(descriptorVisibilities$50, 2);
        DescriptorVisibilities.ORDERED_VISIBILITIES = Collections.unmodifiableMap(hashMap0);
        DescriptorVisibilities.DEFAULT_VISIBILITY = descriptorVisibilities$50;
        DescriptorVisibilities.IRRELEVANT_RECEIVER = new ReceiverValue() {
            @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ReceiverValue
            public KotlinType getType() {
                throw new IllegalStateException("This method should not be called");
            }
        };
        DescriptorVisibilities.ALWAYS_SUITABLE_RECEIVER = new ReceiverValue() {
            @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ReceiverValue
            public KotlinType getType() {
                throw new IllegalStateException("This method should not be called");
            }
        };
        DescriptorVisibilities.FALSE_IF_PROTECTED = new ReceiverValue() {
            @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ReceiverValue
            public KotlinType getType() {
                throw new IllegalStateException("This method should not be called");
            }
        };
        Iterator iterator0 = ServiceLoader.load(ModuleVisibilityHelper.class, ModuleVisibilityHelper.class.getClassLoader()).iterator();
        if(iterator0.hasNext()) {
            Object object0 = iterator0.next();
            moduleVisibilityHelper0 = (ModuleVisibilityHelper)object0;
        }
        else {
            moduleVisibilityHelper0 = EMPTY.INSTANCE;
        }
        DescriptorVisibilities.MODULE_VISIBILITY_HELPER = moduleVisibilityHelper0;
        DescriptorVisibilities.visibilitiesMapping = new HashMap();
        DescriptorVisibilities.recordVisibilityMapping(descriptorVisibilities$10);
        DescriptorVisibilities.recordVisibilityMapping(descriptorVisibilities$20);
        DescriptorVisibilities.recordVisibilityMapping(descriptorVisibilities$30);
        DescriptorVisibilities.recordVisibilityMapping(descriptorVisibilities$40);
        DescriptorVisibilities.recordVisibilityMapping(descriptorVisibilities$50);
        DescriptorVisibilities.recordVisibilityMapping(descriptorVisibilities$60);
        DescriptorVisibilities.recordVisibilityMapping(descriptorVisibilities$70);
        DescriptorVisibilities.recordVisibilityMapping(descriptorVisibilities$80);
        DescriptorVisibilities.recordVisibilityMapping(descriptorVisibilities$90);
    }

    public static Integer compare(DescriptorVisibility descriptorVisibility0, DescriptorVisibility descriptorVisibility1) {
        if(descriptorVisibility0 == null) {
            DescriptorVisibilities.$$$reportNull$$$0(12);
        }
        if(descriptorVisibility1 == null) {
            DescriptorVisibilities.$$$reportNull$$$0(13);
        }
        Integer integer0 = descriptorVisibility0.compareTo(descriptorVisibility1);
        if(integer0 != null) {
            return integer0;
        }
        Integer integer1 = descriptorVisibility1.compareTo(descriptorVisibility0);
        return integer1 == null ? null : ((int)(-((int)integer1)));
    }

    public static DeclarationDescriptorWithVisibility findInvisibleMember(ReceiverValue receiverValue0, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility0, DeclarationDescriptor declarationDescriptor0, boolean z) {
        if(declarationDescriptorWithVisibility0 == null) {
            DescriptorVisibilities.$$$reportNull$$$0(8);
        }
        if(declarationDescriptor0 == null) {
            DescriptorVisibilities.$$$reportNull$$$0(9);
        }
        for(DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility1 = (DeclarationDescriptorWithVisibility)declarationDescriptorWithVisibility0.getOriginal(); declarationDescriptorWithVisibility1 != null && declarationDescriptorWithVisibility1.getVisibility() != DescriptorVisibilities.LOCAL; declarationDescriptorWithVisibility1 = (DeclarationDescriptorWithVisibility)DescriptorUtils.getParentOfType(declarationDescriptorWithVisibility1, DeclarationDescriptorWithVisibility.class)) {
            if(!declarationDescriptorWithVisibility1.getVisibility().isVisible(receiverValue0, declarationDescriptorWithVisibility1, declarationDescriptor0, z)) {
                return declarationDescriptorWithVisibility1;
            }
        }
        if(declarationDescriptorWithVisibility0 instanceof TypeAliasConstructorDescriptor) {
            DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility2 = DescriptorVisibilities.findInvisibleMember(receiverValue0, ((TypeAliasConstructorDescriptor)declarationDescriptorWithVisibility0).getUnderlyingConstructorDescriptor(), declarationDescriptor0, z);
            return declarationDescriptorWithVisibility2 == null ? null : declarationDescriptorWithVisibility2;
        }
        return null;
    }

    public static boolean inSameFile(DeclarationDescriptor declarationDescriptor0, DeclarationDescriptor declarationDescriptor1) {
        if(declarationDescriptor0 == null) {
            DescriptorVisibilities.$$$reportNull$$$0(6);
        }
        if(declarationDescriptor1 == null) {
            DescriptorVisibilities.$$$reportNull$$$0(7);
        }
        SourceFile sourceFile0 = DescriptorUtils.getContainingSourceFile(declarationDescriptor1);
        return sourceFile0 == SourceFile.NO_SOURCE_FILE ? false : sourceFile0.equals(DescriptorUtils.getContainingSourceFile(declarationDescriptor0));
    }

    public static boolean isPrivate(DescriptorVisibility descriptorVisibility0) {
        if(descriptorVisibility0 == null) {
            DescriptorVisibilities.$$$reportNull$$$0(14);
        }
        return descriptorVisibility0 == DescriptorVisibilities.PRIVATE || descriptorVisibility0 == DescriptorVisibilities.PRIVATE_TO_THIS;
    }

    public static boolean isVisibleIgnoringReceiver(DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility0, DeclarationDescriptor declarationDescriptor0, boolean z) {
        if(declarationDescriptorWithVisibility0 == null) {
            DescriptorVisibilities.$$$reportNull$$$0(2);
        }
        if(declarationDescriptor0 == null) {
            DescriptorVisibilities.$$$reportNull$$$0(3);
        }
        return DescriptorVisibilities.findInvisibleMember(DescriptorVisibilities.ALWAYS_SUITABLE_RECEIVER, declarationDescriptorWithVisibility0, declarationDescriptor0, z) == null;
    }

    private static void recordVisibilityMapping(DescriptorVisibility descriptorVisibility0) {
        Visibility visibility0 = descriptorVisibility0.getDelegate();
        DescriptorVisibilities.visibilitiesMapping.put(visibility0, descriptorVisibility0);
    }

    public static DescriptorVisibility toDescriptorVisibility(Visibility visibility0) {
        if(visibility0 == null) {
            DescriptorVisibilities.$$$reportNull$$$0(15);
        }
        DescriptorVisibility descriptorVisibility0 = (DescriptorVisibility)DescriptorVisibilities.visibilitiesMapping.get(visibility0);
        if(descriptorVisibility0 == null) {
            throw new IllegalArgumentException("Inapplicable visibility: " + visibility0);
        }
        if(descriptorVisibility0 == null) {
            DescriptorVisibilities.$$$reportNull$$$0(16);
        }
        return descriptorVisibility0;
    }
}

