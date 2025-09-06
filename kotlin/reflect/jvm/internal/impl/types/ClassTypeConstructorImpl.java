package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker.EMPTY;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;

public class ClassTypeConstructorImpl extends AbstractClassTypeConstructor implements TypeConstructor {
    private final ClassDescriptor classDescriptor;
    private final List parameters;
    private final Collection supertypes;

    private static void $$$reportNull$$$0(int v) {
        Object[] arr_object = new Object[(v == 4 || v == 5 || v == 6 || v == 7 ? 2 : 3)];
        switch(v) {
            case 1: {
                arr_object[0] = "parameters";
                break;
            }
            case 2: {
                arr_object[0] = "supertypes";
                break;
            }
            case 3: {
                arr_object[0] = "storageManager";
                break;
            }
            case 4: 
            case 5: 
            case 6: 
            case 7: {
                arr_object[0] = "kotlin/reflect/jvm/internal/impl/types/ClassTypeConstructorImpl";
                break;
            }
            default: {
                arr_object[0] = "classDescriptor";
            }
        }
        switch(v) {
            case 4: {
                arr_object[1] = "getParameters";
                break;
            }
            case 5: {
                arr_object[1] = "getDeclarationDescriptor";
                break;
            }
            case 6: {
                arr_object[1] = "computeSupertypes";
                break;
            }
            case 7: {
                arr_object[1] = "getSupertypeLoopChecker";
                break;
            }
            default: {
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/types/ClassTypeConstructorImpl";
            }
        }
        if(v != 4 && v != 5 && v != 6 && v != 7) {
            arr_object[2] = "<init>";
        }
        String s = String.format((v == 4 || v == 5 || v == 6 || v == 7 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null"), arr_object);
        IllegalStateException illegalStateException0 = v == 4 || v == 5 || v == 6 || v == 7 ? new IllegalStateException(s) : new IllegalArgumentException(s);
        throw illegalStateException0;
    }

    public ClassTypeConstructorImpl(ClassDescriptor classDescriptor0, List list0, Collection collection0, StorageManager storageManager0) {
        if(classDescriptor0 == null) {
            ClassTypeConstructorImpl.$$$reportNull$$$0(0);
        }
        if(list0 == null) {
            ClassTypeConstructorImpl.$$$reportNull$$$0(1);
        }
        if(collection0 == null) {
            ClassTypeConstructorImpl.$$$reportNull$$$0(2);
        }
        if(storageManager0 == null) {
            ClassTypeConstructorImpl.$$$reportNull$$$0(3);
        }
        super(storageManager0);
        this.classDescriptor = classDescriptor0;
        this.parameters = Collections.unmodifiableList(new ArrayList(list0));
        this.supertypes = Collections.unmodifiableCollection(collection0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor
    protected Collection computeSupertypes() {
        Collection collection0 = this.supertypes;
        if(collection0 == null) {
            ClassTypeConstructorImpl.$$$reportNull$$$0(6);
        }
        return collection0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.AbstractClassTypeConstructor
    public ClassDescriptor getDeclarationDescriptor() {
        ClassDescriptor classDescriptor0 = this.classDescriptor;
        if(classDescriptor0 == null) {
            ClassTypeConstructorImpl.$$$reportNull$$$0(5);
        }
        return classDescriptor0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.AbstractClassTypeConstructor, kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public ClassifierDescriptor getDeclarationDescriptor() {
        return this.getDeclarationDescriptor();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public List getParameters() {
        List list0 = this.parameters;
        if(list0 == null) {
            ClassTypeConstructorImpl.$$$reportNull$$$0(4);
        }
        return list0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor
    protected SupertypeLoopChecker getSupertypeLoopChecker() {
        SupertypeLoopChecker supertypeLoopChecker0 = EMPTY.INSTANCE;
        if(supertypeLoopChecker0 == null) {
            ClassTypeConstructorImpl.$$$reportNull$$$0(7);
        }
        return supertypeLoopChecker0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public boolean isDenotable() {
        return true;
    }

    @Override
    public String toString() {
        return DescriptorUtils.getFqName(this.classDescriptor).asString();
    }
}

