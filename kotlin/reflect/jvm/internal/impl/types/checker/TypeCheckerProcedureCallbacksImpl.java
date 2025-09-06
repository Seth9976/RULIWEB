package kotlin.reflect.jvm.internal.impl.types.checker;

import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;

class TypeCheckerProcedureCallbacksImpl implements TypeCheckingProcedureCallbacks {
    private static void $$$reportNull$$$0(int v) {
        Object[] arr_object = new Object[3];
        switch(v) {
            case 1: 
            case 4: {
                arr_object[0] = "b";
                break;
            }
            case 2: 
            case 7: {
                arr_object[0] = "typeCheckingProcedure";
                break;
            }
            case 8: {
                arr_object[0] = "type";
                break;
            }
            case 9: {
                arr_object[0] = "typeProjection";
                break;
            }
            case 5: 
            case 10: {
                arr_object[0] = "subtype";
                break;
            }
            case 6: 
            case 11: {
                arr_object[0] = "supertype";
                break;
            }
            default: {
                arr_object[0] = "a";
            }
        }
        arr_object[1] = "kotlin/reflect/jvm/internal/impl/types/checker/TypeCheckerProcedureCallbacksImpl";
        switch(v) {
            case 3: 
            case 4: {
                arr_object[2] = "assertEqualTypeConstructors";
                break;
            }
            case 5: 
            case 6: 
            case 7: {
                arr_object[2] = "assertSubtype";
                break;
            }
            case 8: 
            case 9: {
                arr_object[2] = "capture";
                break;
            }
            case 10: 
            case 11: {
                arr_object[2] = "noCorrespondingSupertype";
                break;
            }
            default: {
                arr_object[2] = "assertEqualTypes";
            }
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter \'%s\' of %s.%s must not be null", arr_object));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckingProcedureCallbacks
    public boolean assertEqualTypeConstructors(TypeConstructor typeConstructor0, TypeConstructor typeConstructor1) {
        if(typeConstructor0 == null) {
            TypeCheckerProcedureCallbacksImpl.$$$reportNull$$$0(3);
        }
        if(typeConstructor1 == null) {
            TypeCheckerProcedureCallbacksImpl.$$$reportNull$$$0(4);
        }
        return typeConstructor0.equals(typeConstructor1);
    }
}

