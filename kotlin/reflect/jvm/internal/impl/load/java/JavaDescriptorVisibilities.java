package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.HashMap;
import java.util.Map;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.DelegatedDescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.java.JavaVisibilities.PackageVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.java.JavaVisibilities.ProtectedAndPackage;
import kotlin.reflect.jvm.internal.impl.descriptors.java.JavaVisibilities.ProtectedStaticVisibility;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ReceiverValue;

public class JavaDescriptorVisibilities {
    public static final DescriptorVisibility PACKAGE_VISIBILITY;
    public static final DescriptorVisibility PROTECTED_AND_PACKAGE;
    public static final DescriptorVisibility PROTECTED_STATIC_VISIBILITY;
    private static final Map visibilitiesMapping;

    private static void $$$reportNull$$$0(int v) {
        Object[] arr_object = new Object[(v == 5 || v == 6 ? 2 : 3)];
        switch(v) {
            case 1: {
                arr_object[0] = "from";
                break;
            }
            case 2: {
                arr_object[0] = "first";
                break;
            }
            case 3: {
                arr_object[0] = "second";
                break;
            }
            case 4: {
                arr_object[0] = "visibility";
                break;
            }
            case 5: 
            case 6: {
                arr_object[0] = "kotlin/reflect/jvm/internal/impl/load/java/JavaDescriptorVisibilities";
                break;
            }
            default: {
                arr_object[0] = "what";
            }
        }
        arr_object[1] = v == 5 || v == 6 ? "toDescriptorVisibility" : "kotlin/reflect/jvm/internal/impl/load/java/JavaDescriptorVisibilities";
        if(v == 2 || v == 3) {
            arr_object[2] = "areInSamePackage";
        }
        else {
            switch(v) {
                case 4: {
                    arr_object[2] = "toDescriptorVisibility";
                    break;
                }
                case 5: 
                case 6: {
                    break;
                }
                default: {
                    arr_object[2] = "isVisibleForProtectedAndPackage";
                }
            }
        }
        String s = String.format((v == 5 || v == 6 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null"), arr_object);
        IllegalStateException illegalStateException0 = v == 5 || v == 6 ? new IllegalStateException(s) : new IllegalArgumentException(s);
        throw illegalStateException0;
    }

    static {
        kotlin.reflect.jvm.internal.impl.load.java.JavaDescriptorVisibilities.1 javaDescriptorVisibilities$10 = new DelegatedDescriptorVisibility(PackageVisibility.INSTANCE) {
            private static void $$$reportNull$$$0(int v) {
                Object[] arr_object = new Object[3];
                arr_object[0] = v == 1 ? "from" : "what";
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/load/java/JavaDescriptorVisibilities$1";
                arr_object[2] = "isVisible";
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter \'%s\' of %s.%s must not be null", arr_object));
            }

            @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility
            public boolean isVisible(ReceiverValue receiverValue0, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility0, DeclarationDescriptor declarationDescriptor0, boolean z) {
                if(declarationDescriptorWithVisibility0 == null) {
                    kotlin.reflect.jvm.internal.impl.load.java.JavaDescriptorVisibilities.1.$$$reportNull$$$0(0);
                }
                if(declarationDescriptor0 == null) {
                    kotlin.reflect.jvm.internal.impl.load.java.JavaDescriptorVisibilities.1.$$$reportNull$$$0(1);
                }
                return JavaDescriptorVisibilities.areInSamePackage(declarationDescriptorWithVisibility0, declarationDescriptor0);
            }
        };
        JavaDescriptorVisibilities.PACKAGE_VISIBILITY = javaDescriptorVisibilities$10;
        kotlin.reflect.jvm.internal.impl.load.java.JavaDescriptorVisibilities.2 javaDescriptorVisibilities$20 = new DelegatedDescriptorVisibility(ProtectedStaticVisibility.INSTANCE) {
            private static void $$$reportNull$$$0(int v) {
                Object[] arr_object = new Object[3];
                arr_object[0] = v == 1 ? "from" : "what";
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/load/java/JavaDescriptorVisibilities$2";
                arr_object[2] = "isVisible";
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter \'%s\' of %s.%s must not be null", arr_object));
            }

            @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility
            public boolean isVisible(ReceiverValue receiverValue0, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility0, DeclarationDescriptor declarationDescriptor0, boolean z) {
                if(declarationDescriptorWithVisibility0 == null) {
                    kotlin.reflect.jvm.internal.impl.load.java.JavaDescriptorVisibilities.2.$$$reportNull$$$0(0);
                }
                if(declarationDescriptor0 == null) {
                    kotlin.reflect.jvm.internal.impl.load.java.JavaDescriptorVisibilities.2.$$$reportNull$$$0(1);
                }
                return JavaDescriptorVisibilities.isVisibleForProtectedAndPackage(receiverValue0, declarationDescriptorWithVisibility0, declarationDescriptor0);
            }
        };
        JavaDescriptorVisibilities.PROTECTED_STATIC_VISIBILITY = javaDescriptorVisibilities$20;
        kotlin.reflect.jvm.internal.impl.load.java.JavaDescriptorVisibilities.3 javaDescriptorVisibilities$30 = new DelegatedDescriptorVisibility(ProtectedAndPackage.INSTANCE) {
            private static void $$$reportNull$$$0(int v) {
                Object[] arr_object = new Object[3];
                arr_object[0] = v == 1 ? "from" : "what";
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/load/java/JavaDescriptorVisibilities$3";
                arr_object[2] = "isVisible";
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter \'%s\' of %s.%s must not be null", arr_object));
            }

            @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility
            public boolean isVisible(ReceiverValue receiverValue0, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility0, DeclarationDescriptor declarationDescriptor0, boolean z) {
                if(declarationDescriptorWithVisibility0 == null) {
                    kotlin.reflect.jvm.internal.impl.load.java.JavaDescriptorVisibilities.3.$$$reportNull$$$0(0);
                }
                if(declarationDescriptor0 == null) {
                    kotlin.reflect.jvm.internal.impl.load.java.JavaDescriptorVisibilities.3.$$$reportNull$$$0(1);
                }
                return JavaDescriptorVisibilities.isVisibleForProtectedAndPackage(receiverValue0, declarationDescriptorWithVisibility0, declarationDescriptor0);
            }
        };
        JavaDescriptorVisibilities.PROTECTED_AND_PACKAGE = javaDescriptorVisibilities$30;
        JavaDescriptorVisibilities.visibilitiesMapping = new HashMap();
        JavaDescriptorVisibilities.recordVisibilityMapping(javaDescriptorVisibilities$10);
        JavaDescriptorVisibilities.recordVisibilityMapping(javaDescriptorVisibilities$20);
        JavaDescriptorVisibilities.recordVisibilityMapping(javaDescriptorVisibilities$30);
    }

    private static boolean areInSamePackage(DeclarationDescriptor declarationDescriptor0, DeclarationDescriptor declarationDescriptor1) {
        if(declarationDescriptor0 == null) {
            JavaDescriptorVisibilities.$$$reportNull$$$0(2);
        }
        if(declarationDescriptor1 == null) {
            JavaDescriptorVisibilities.$$$reportNull$$$0(3);
        }
        PackageFragmentDescriptor packageFragmentDescriptor0 = (PackageFragmentDescriptor)DescriptorUtils.getParentOfType(declarationDescriptor0, PackageFragmentDescriptor.class, false);
        PackageFragmentDescriptor packageFragmentDescriptor1 = (PackageFragmentDescriptor)DescriptorUtils.getParentOfType(declarationDescriptor1, PackageFragmentDescriptor.class, false);
        return packageFragmentDescriptor1 != null && packageFragmentDescriptor0 != null && packageFragmentDescriptor0.getFqName().equals(packageFragmentDescriptor1.getFqName());
    }

    private static boolean isVisibleForProtectedAndPackage(ReceiverValue receiverValue0, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility0, DeclarationDescriptor declarationDescriptor0) {
        if(declarationDescriptorWithVisibility0 == null) {
            JavaDescriptorVisibilities.$$$reportNull$$$0(0);
        }
        if(declarationDescriptor0 == null) {
            JavaDescriptorVisibilities.$$$reportNull$$$0(1);
        }
        return JavaDescriptorVisibilities.areInSamePackage(DescriptorUtils.unwrapFakeOverrideToAnyDeclaration(declarationDescriptorWithVisibility0), declarationDescriptor0) ? true : DescriptorVisibilities.PROTECTED.isVisible(receiverValue0, declarationDescriptorWithVisibility0, declarationDescriptor0, false);
    }

    private static void recordVisibilityMapping(DescriptorVisibility descriptorVisibility0) {
        Visibility visibility0 = descriptorVisibility0.getDelegate();
        JavaDescriptorVisibilities.visibilitiesMapping.put(visibility0, descriptorVisibility0);
    }

    public static DescriptorVisibility toDescriptorVisibility(Visibility visibility0) {
        if(visibility0 == null) {
            JavaDescriptorVisibilities.$$$reportNull$$$0(4);
        }
        DescriptorVisibility descriptorVisibility0 = (DescriptorVisibility)JavaDescriptorVisibilities.visibilitiesMapping.get(visibility0);
        if(descriptorVisibility0 == null) {
            DescriptorVisibility descriptorVisibility1 = DescriptorVisibilities.toDescriptorVisibility(visibility0);
            if(descriptorVisibility1 == null) {
                JavaDescriptorVisibilities.$$$reportNull$$$0(5);
            }
            return descriptorVisibility1;
        }
        if(descriptorVisibility0 == null) {
            JavaDescriptorVisibilities.$$$reportNull$$$0(6);
        }
        return descriptorVisibility0;
    }
}

