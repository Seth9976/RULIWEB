package kotlin.reflect.jvm.internal.impl.serialization.deserialization.builtins;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import kotlin.jvm.internal.Intrinsics;

public final class BuiltInsResourceLoader {
    public final InputStream loadResource(String s) {
        Intrinsics.checkNotNullParameter(s, "path");
        ClassLoader classLoader0 = this.getClass().getClassLoader();
        if(classLoader0 == null) {
            return ClassLoader.getSystemResourceAsStream(s);
        }
        URL uRL0 = classLoader0.getResource(s);
        if(uRL0 == null) {
            return null;
        }
        URLConnection uRLConnection0 = uRL0.openConnection();
        uRLConnection0.setUseCaches(false);
        return uRLConnection0.getInputStream();
    }
}

