package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import java.util.Map;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

public class AnnotationDescriptorImpl implements AnnotationDescriptor {
    private final KotlinType annotationType;
    private final SourceElement source;
    private final Map valueArguments;

    private static void $$$reportNull$$$0(int v) {
        Object[] arr_object = new Object[(v == 3 || v == 4 || v == 5 ? 2 : 3)];
        switch(v) {
            case 1: {
                arr_object[0] = "valueArguments";
                break;
            }
            case 2: {
                arr_object[0] = "source";
                break;
            }
            case 3: 
            case 4: 
            case 5: {
                arr_object[0] = "kotlin/reflect/jvm/internal/impl/descriptors/annotations/AnnotationDescriptorImpl";
                break;
            }
            default: {
                arr_object[0] = "annotationType";
            }
        }
        switch(v) {
            case 3: {
                arr_object[1] = "getType";
                break;
            }
            case 4: {
                arr_object[1] = "getAllValueArguments";
                break;
            }
            case 5: {
                arr_object[1] = "getSource";
                break;
            }
            default: {
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/descriptors/annotations/AnnotationDescriptorImpl";
            }
        }
        if(v != 3 && v != 4 && v != 5) {
            arr_object[2] = "<init>";
        }
        String s = String.format((v == 3 || v == 4 || v == 5 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null"), arr_object);
        IllegalStateException illegalStateException0 = v == 3 || v == 4 || v == 5 ? new IllegalStateException(s) : new IllegalArgumentException(s);
        throw illegalStateException0;
    }

    public AnnotationDescriptorImpl(KotlinType kotlinType0, Map map0, SourceElement sourceElement0) {
        if(kotlinType0 == null) {
            AnnotationDescriptorImpl.$$$reportNull$$$0(0);
        }
        if(map0 == null) {
            AnnotationDescriptorImpl.$$$reportNull$$$0(1);
        }
        if(sourceElement0 == null) {
            AnnotationDescriptorImpl.$$$reportNull$$$0(2);
        }
        super();
        this.annotationType = kotlinType0;
        this.valueArguments = map0;
        this.source = sourceElement0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor
    public Map getAllValueArguments() {
        Map map0 = this.valueArguments;
        if(map0 == null) {
            AnnotationDescriptorImpl.$$$reportNull$$$0(4);
        }
        return map0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor
    public FqName getFqName() {
        return DefaultImpls.getFqName(this);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor
    public SourceElement getSource() {
        SourceElement sourceElement0 = this.source;
        if(sourceElement0 == null) {
            AnnotationDescriptorImpl.$$$reportNull$$$0(5);
        }
        return sourceElement0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor
    public KotlinType getType() {
        KotlinType kotlinType0 = this.annotationType;
        if(kotlinType0 == null) {
            AnnotationDescriptorImpl.$$$reportNull$$$0(3);
        }
        return kotlinType0;
    }

    @Override
    public String toString() {
        return DescriptorRenderer.FQ_NAMES_IN_TYPES.renderAnnotation(this, null);
    }
}

