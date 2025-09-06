package kotlin.reflect.jvm.internal.impl.load.java.components;

import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

public interface SignaturePropagator {
    public static class PropagatedSignature {
        private final boolean hasStableParameterNames;
        private final KotlinType receiverType;
        private final KotlinType returnType;
        private final List signatureErrors;
        private final List typeParameters;
        private final List valueParameters;

        private static void $$$reportNull$$$0(int v) {
            Object[] arr_object = new Object[(v == 4 || v == 5 || v == 6 || v == 7 ? 2 : 3)];
            switch(v) {
                case 1: {
                    arr_object[0] = "valueParameters";
                    break;
                }
                case 2: {
                    arr_object[0] = "typeParameters";
                    break;
                }
                case 3: {
                    arr_object[0] = "signatureErrors";
                    break;
                }
                case 4: 
                case 5: 
                case 6: 
                case 7: {
                    arr_object[0] = "kotlin/reflect/jvm/internal/impl/load/java/components/SignaturePropagator$PropagatedSignature";
                    break;
                }
                default: {
                    arr_object[0] = "returnType";
                }
            }
            switch(v) {
                case 4: {
                    arr_object[1] = "getReturnType";
                    break;
                }
                case 5: {
                    arr_object[1] = "getValueParameters";
                    break;
                }
                case 6: {
                    arr_object[1] = "getTypeParameters";
                    break;
                }
                case 7: {
                    arr_object[1] = "getErrors";
                    break;
                }
                default: {
                    arr_object[1] = "kotlin/reflect/jvm/internal/impl/load/java/components/SignaturePropagator$PropagatedSignature";
                }
            }
            if(v != 4 && v != 5 && v != 6 && v != 7) {
                arr_object[2] = "<init>";
            }
            String s = String.format((v == 4 || v == 5 || v == 6 || v == 7 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null"), arr_object);
            IllegalStateException illegalStateException0 = v == 4 || v == 5 || v == 6 || v == 7 ? new IllegalStateException(s) : new IllegalArgumentException(s);
            throw illegalStateException0;
        }

        public PropagatedSignature(KotlinType kotlinType0, KotlinType kotlinType1, List list0, List list1, List list2, boolean z) {
            if(kotlinType0 == null) {
                PropagatedSignature.$$$reportNull$$$0(0);
            }
            if(list0 == null) {
                PropagatedSignature.$$$reportNull$$$0(1);
            }
            if(list1 == null) {
                PropagatedSignature.$$$reportNull$$$0(2);
            }
            if(list2 == null) {
                PropagatedSignature.$$$reportNull$$$0(3);
            }
            super();
            this.returnType = kotlinType0;
            this.receiverType = kotlinType1;
            this.valueParameters = list0;
            this.typeParameters = list1;
            this.signatureErrors = list2;
            this.hasStableParameterNames = z;
        }

        public List getErrors() {
            List list0 = this.signatureErrors;
            if(list0 == null) {
                PropagatedSignature.$$$reportNull$$$0(7);
            }
            return list0;
        }

        public KotlinType getReceiverType() {
            return this.receiverType;
        }

        public KotlinType getReturnType() {
            KotlinType kotlinType0 = this.returnType;
            if(kotlinType0 == null) {
                PropagatedSignature.$$$reportNull$$$0(4);
            }
            return kotlinType0;
        }

        public List getTypeParameters() {
            List list0 = this.typeParameters;
            if(list0 == null) {
                PropagatedSignature.$$$reportNull$$$0(6);
            }
            return list0;
        }

        public List getValueParameters() {
            List list0 = this.valueParameters;
            if(list0 == null) {
                PropagatedSignature.$$$reportNull$$$0(5);
            }
            return list0;
        }

        public boolean hasStableParameterNames() {
            return this.hasStableParameterNames;
        }
    }

    public static final SignaturePropagator DO_NOTHING;

    static {
        SignaturePropagator.DO_NOTHING = new SignaturePropagator() {
            private static void $$$reportNull$$$0(int v) {
                Object[] arr_object = new Object[3];
                switch(v) {
                    case 1: {
                        arr_object[0] = "owner";
                        break;
                    }
                    case 2: {
                        arr_object[0] = "returnType";
                        break;
                    }
                    case 3: {
                        arr_object[0] = "valueParameters";
                        break;
                    }
                    case 4: {
                        arr_object[0] = "typeParameters";
                        break;
                    }
                    case 5: {
                        arr_object[0] = "descriptor";
                        break;
                    }
                    case 6: {
                        arr_object[0] = "signatureErrors";
                        break;
                    }
                    default: {
                        arr_object[0] = "method";
                    }
                }
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/load/java/components/SignaturePropagator$1";
                arr_object[2] = v == 5 || v == 6 ? "reportSignatureErrors" : "resolvePropagatedSignature";
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter \'%s\' of %s.%s must not be null", arr_object));
            }

            @Override  // kotlin.reflect.jvm.internal.impl.load.java.components.SignaturePropagator
            public void reportSignatureErrors(CallableMemberDescriptor callableMemberDescriptor0, List list0) {
                if(callableMemberDescriptor0 == null) {
                    kotlin.reflect.jvm.internal.impl.load.java.components.SignaturePropagator.1.$$$reportNull$$$0(5);
                }
                if(list0 == null) {
                    kotlin.reflect.jvm.internal.impl.load.java.components.SignaturePropagator.1.$$$reportNull$$$0(6);
                }
                throw new UnsupportedOperationException("Should not be called");
            }

            @Override  // kotlin.reflect.jvm.internal.impl.load.java.components.SignaturePropagator
            public PropagatedSignature resolvePropagatedSignature(JavaMethod javaMethod0, ClassDescriptor classDescriptor0, KotlinType kotlinType0, KotlinType kotlinType1, List list0, List list1) {
                if(javaMethod0 == null) {
                    kotlin.reflect.jvm.internal.impl.load.java.components.SignaturePropagator.1.$$$reportNull$$$0(0);
                }
                if(classDescriptor0 == null) {
                    kotlin.reflect.jvm.internal.impl.load.java.components.SignaturePropagator.1.$$$reportNull$$$0(1);
                }
                if(kotlinType0 == null) {
                    kotlin.reflect.jvm.internal.impl.load.java.components.SignaturePropagator.1.$$$reportNull$$$0(2);
                }
                if(list0 == null) {
                    kotlin.reflect.jvm.internal.impl.load.java.components.SignaturePropagator.1.$$$reportNull$$$0(3);
                }
                if(list1 == null) {
                    kotlin.reflect.jvm.internal.impl.load.java.components.SignaturePropagator.1.$$$reportNull$$$0(4);
                }
                return new PropagatedSignature(kotlinType0, kotlinType1, list0, list1, Collections.EMPTY_LIST, false);
            }
        };
    }

    void reportSignatureErrors(CallableMemberDescriptor arg1, List arg2);

    PropagatedSignature resolvePropagatedSignature(JavaMethod arg1, ClassDescriptor arg2, KotlinType arg3, KotlinType arg4, List arg5, List arg6);
}

