package kotlin.reflect.jvm.internal.impl.load.java.components;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.NonReportingOverrideStrategy;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter;

public final class DescriptorResolverUtils {
    private static void $$$reportNull$$$0(int v) {
        Object[] arr_object = new Object[(v == 18 ? 2 : 3)];
        switch(v) {
            case 1: 
            case 7: 
            case 13: {
                arr_object[0] = "membersFromSupertypes";
                break;
            }
            case 2: 
            case 8: 
            case 14: {
                arr_object[0] = "membersFromCurrent";
                break;
            }
            case 3: 
            case 9: 
            case 15: {
                arr_object[0] = "classDescriptor";
                break;
            }
            case 4: 
            case 10: 
            case 16: {
                arr_object[0] = "errorReporter";
                break;
            }
            case 5: 
            case 11: 
            case 17: {
                arr_object[0] = "overridingUtil";
                break;
            }
            case 18: {
                arr_object[0] = "kotlin/reflect/jvm/internal/impl/load/java/components/DescriptorResolverUtils";
                break;
            }
            case 20: {
                arr_object[0] = "annotationClass";
                break;
            }
            default: {
                arr_object[0] = "name";
            }
        }
        arr_object[1] = v == 18 ? "resolveOverrides" : "kotlin/reflect/jvm/internal/impl/load/java/components/DescriptorResolverUtils";
        switch(v) {
            case 6: 
            case 7: 
            case 8: 
            case 9: 
            case 10: 
            case 11: {
                arr_object[2] = "resolveOverridesForStaticMembers";
                break;
            }
            case 12: 
            case 13: 
            case 14: 
            case 15: 
            case 16: 
            case 17: {
                arr_object[2] = "resolveOverrides";
                break;
            }
            case 18: {
                break;
            }
            case 19: 
            case 20: {
                arr_object[2] = "getAnnotationParameterByName";
                break;
            }
            default: {
                arr_object[2] = "resolveOverridesForNonStaticMembers";
            }
        }
        String s = String.format((v == 18 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null"), arr_object);
        IllegalArgumentException illegalArgumentException0 = v == 18 ? new IllegalStateException(s) : new IllegalArgumentException(s);
        throw illegalArgumentException0;
    }

    public static ValueParameterDescriptor getAnnotationParameterByName(Name name0, ClassDescriptor classDescriptor0) {
        if(name0 == null) {
            DescriptorResolverUtils.$$$reportNull$$$0(19);
        }
        if(classDescriptor0 == null) {
            DescriptorResolverUtils.$$$reportNull$$$0(20);
        }
        Collection collection0 = classDescriptor0.getConstructors();
        if(collection0.size() != 1) {
            return null;
        }
        Object object0 = collection0.iterator().next();
        for(Object object1: ((ClassConstructorDescriptor)object0).getValueParameters()) {
            ValueParameterDescriptor valueParameterDescriptor0 = (ValueParameterDescriptor)object1;
            if(valueParameterDescriptor0.getName().equals(name0)) {
                return valueParameterDescriptor0;
            }
            if(false) {
                break;
            }
        }
        return null;
    }

    private static Collection resolveOverrides(Name name0, Collection collection0, Collection collection1, ClassDescriptor classDescriptor0, ErrorReporter errorReporter0, OverridingUtil overridingUtil0, boolean z) {
        if(name0 == null) {
            DescriptorResolverUtils.$$$reportNull$$$0(12);
        }
        if(collection0 == null) {
            DescriptorResolverUtils.$$$reportNull$$$0(13);
        }
        if(collection1 == null) {
            DescriptorResolverUtils.$$$reportNull$$$0(14);
        }
        if(classDescriptor0 == null) {
            DescriptorResolverUtils.$$$reportNull$$$0(15);
        }
        if(errorReporter0 == null) {
            DescriptorResolverUtils.$$$reportNull$$$0(16);
        }
        if(overridingUtil0 == null) {
            DescriptorResolverUtils.$$$reportNull$$$0(17);
        }
        Collection collection2 = new LinkedHashSet();
        overridingUtil0.generateOverridesInFunctionGroup(name0, collection0, collection1, classDescriptor0, new NonReportingOverrideStrategy() {
            private static void $$$reportNull$$$0(int v) {
                Object[] arr_object = new Object[3];
                switch(v) {
                    case 1: {
                        arr_object[0] = "fromSuper";
                        break;
                    }
                    case 2: {
                        arr_object[0] = "fromCurrent";
                        break;
                    }
                    case 3: {
                        arr_object[0] = "member";
                        break;
                    }
                    case 4: {
                        arr_object[0] = "overridden";
                        break;
                    }
                    default: {
                        arr_object[0] = "fakeOverride";
                    }
                }
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/load/java/components/DescriptorResolverUtils$1";
                switch(v) {
                    case 1: 
                    case 2: {
                        arr_object[2] = "conflict";
                        break;
                    }
                    case 3: 
                    case 4: {
                        arr_object[2] = "setOverriddenDescriptors";
                        break;
                    }
                    default: {
                        arr_object[2] = "addFakeOverride";
                    }
                }
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter \'%s\' of %s.%s must not be null", arr_object));
            }

            @Override  // kotlin.reflect.jvm.internal.impl.resolve.OverridingStrategy
            public void addFakeOverride(CallableMemberDescriptor callableMemberDescriptor0) {
                if(callableMemberDescriptor0 == null) {
                    kotlin.reflect.jvm.internal.impl.load.java.components.DescriptorResolverUtils.1.$$$reportNull$$$0(0);
                }
                OverridingUtil.resolveUnknownVisibilityForMember(callableMemberDescriptor0, new Function1() {
                    // 去混淆评级： 低(20)
                    private static void $$$reportNull$$$0(int v) {
                        throw new IllegalArgumentException("Argument for @NotNull parameter \'descriptor\' of kotlin/reflect/jvm/internal/impl/load/java/components/DescriptorResolverUtils$1$1.invoke must not be null");
                    }

                    @Override  // kotlin.jvm.functions.Function1
                    public Object invoke(Object object0) {
                        return this.invoke(((CallableMemberDescriptor)object0));
                    }

                    public Unit invoke(CallableMemberDescriptor callableMemberDescriptor0) {
                        if(callableMemberDescriptor0 == null) {
                            kotlin.reflect.jvm.internal.impl.load.java.components.DescriptorResolverUtils.1.1.$$$reportNull$$$0(0);
                        }
                        kotlin.reflect.jvm.internal.impl.load.java.components.DescriptorResolverUtils.1.this.val$errorReporter.reportCannotInferVisibility(callableMemberDescriptor0);
                        return Unit.INSTANCE;
                    }
                });
                ((Set)collection2).add(callableMemberDescriptor0);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.resolve.NonReportingOverrideStrategy
            public void conflict(CallableMemberDescriptor callableMemberDescriptor0, CallableMemberDescriptor callableMemberDescriptor1) {
                if(callableMemberDescriptor0 == null) {
                    kotlin.reflect.jvm.internal.impl.load.java.components.DescriptorResolverUtils.1.$$$reportNull$$$0(1);
                }
                if(callableMemberDescriptor1 == null) {
                    kotlin.reflect.jvm.internal.impl.load.java.components.DescriptorResolverUtils.1.$$$reportNull$$$0(2);
                }
            }

            @Override  // kotlin.reflect.jvm.internal.impl.resolve.OverridingStrategy
            public void setOverriddenDescriptors(CallableMemberDescriptor callableMemberDescriptor0, Collection collection0) {
                if(callableMemberDescriptor0 == null) {
                    kotlin.reflect.jvm.internal.impl.load.java.components.DescriptorResolverUtils.1.$$$reportNull$$$0(3);
                }
                if(collection0 == null) {
                    kotlin.reflect.jvm.internal.impl.load.java.components.DescriptorResolverUtils.1.$$$reportNull$$$0(4);
                }
                if(z && callableMemberDescriptor0.getKind() != Kind.FAKE_OVERRIDE) {
                    return;
                }
                super.setOverriddenDescriptors(callableMemberDescriptor0, collection0);
            }
        });
        return collection2;
    }

    public static Collection resolveOverridesForNonStaticMembers(Name name0, Collection collection0, Collection collection1, ClassDescriptor classDescriptor0, ErrorReporter errorReporter0, OverridingUtil overridingUtil0) {
        if(name0 == null) {
            DescriptorResolverUtils.$$$reportNull$$$0(0);
        }
        if(collection0 == null) {
            DescriptorResolverUtils.$$$reportNull$$$0(1);
        }
        if(collection1 == null) {
            DescriptorResolverUtils.$$$reportNull$$$0(2);
        }
        if(classDescriptor0 == null) {
            DescriptorResolverUtils.$$$reportNull$$$0(3);
        }
        if(errorReporter0 == null) {
            DescriptorResolverUtils.$$$reportNull$$$0(4);
        }
        if(overridingUtil0 == null) {
            DescriptorResolverUtils.$$$reportNull$$$0(5);
        }
        return DescriptorResolverUtils.resolveOverrides(name0, collection0, collection1, classDescriptor0, errorReporter0, overridingUtil0, false);
    }

    public static Collection resolveOverridesForStaticMembers(Name name0, Collection collection0, Collection collection1, ClassDescriptor classDescriptor0, ErrorReporter errorReporter0, OverridingUtil overridingUtil0) {
        if(name0 == null) {
            DescriptorResolverUtils.$$$reportNull$$$0(6);
        }
        if(collection0 == null) {
            DescriptorResolverUtils.$$$reportNull$$$0(7);
        }
        if(collection1 == null) {
            DescriptorResolverUtils.$$$reportNull$$$0(8);
        }
        if(classDescriptor0 == null) {
            DescriptorResolverUtils.$$$reportNull$$$0(9);
        }
        if(errorReporter0 == null) {
            DescriptorResolverUtils.$$$reportNull$$$0(10);
        }
        if(overridingUtil0 == null) {
            DescriptorResolverUtils.$$$reportNull$$$0(11);
        }
        return DescriptorResolverUtils.resolveOverrides(name0, collection0, collection1, classDescriptor0, errorReporter0, overridingUtil0, true);
    }
}

