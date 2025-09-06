package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.text.StringsKt;

public class JvmDescriptorTypeWriter {
    private Object jvmCurrentType;
    private int jvmCurrentTypeArrayLevel;
    private final JvmTypeFactory jvmTypeFactory;

    public void writeArrayEnd() {
    }

    public void writeArrayType() {
        if(this.jvmCurrentType == null) {
            ++this.jvmCurrentTypeArrayLevel;
        }
    }

    public void writeClass(Object object0) {
        Intrinsics.checkNotNullParameter(object0, "objectType");
        this.writeJvmTypeAsIs(object0);
    }

    protected final void writeJvmTypeAsIs(Object object0) {
        Intrinsics.checkNotNullParameter(object0, "type");
        if(this.jvmCurrentType == null) {
            if(this.jvmCurrentTypeArrayLevel > 0) {
                object0 = this.jvmTypeFactory.createFromString(StringsKt.repeat("[", this.jvmCurrentTypeArrayLevel) + this.jvmTypeFactory.toString(object0));
            }
            this.jvmCurrentType = object0;
        }
    }

    public void writeTypeVariable(Name name0, Object object0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(object0, "type");
        this.writeJvmTypeAsIs(object0);
    }
}

