package kotlin.reflect.jvm.internal.impl.load.java.structure;

import java.util.List;

public interface JavaConstructor extends JavaMember, JavaTypeParameterListOwner {
    List getValueParameters();
}

