package kotlinx.coroutines.internal;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J!\u0010\u0005\u001A\u0004\u0018\u00010\u00062\f\u0010\u0007\u001A\b\u0012\u0004\u0012\u00020\u00060\b2\u0006\u0010\t\u001A\u00020\u0004H\u0082\bJ1\u0010\n\u001A\u0002H\u000B\"\u0004\b\u0000\u0010\u000B2\u0006\u0010\f\u001A\u00020\u00042\u0006\u0010\r\u001A\u00020\u000E2\f\u0010\u000F\u001A\b\u0012\u0004\u0012\u0002H\u000B0\bH\u0002¢\u0006\u0002\u0010\u0010J*\u0010\u0011\u001A\b\u0012\u0004\u0012\u0002H\u000B0\u0012\"\u0004\b\u0000\u0010\u000B2\f\u0010\u000F\u001A\b\u0012\u0004\u0012\u0002H\u000B0\b2\u0006\u0010\r\u001A\u00020\u000EH\u0002J\u0013\u0010\u0013\u001A\b\u0012\u0004\u0012\u00020\u00060\u0012H\u0000¢\u0006\u0002\b\u0014J/\u0010\u0015\u001A\b\u0012\u0004\u0012\u0002H\u000B0\u0012\"\u0004\b\u0000\u0010\u000B2\f\u0010\u000F\u001A\b\u0012\u0004\u0012\u0002H\u000B0\b2\u0006\u0010\r\u001A\u00020\u000EH\u0000¢\u0006\u0002\b\u0016J\u0016\u0010\u0017\u001A\b\u0012\u0004\u0012\u00020\u00040\u00122\u0006\u0010\u0018\u001A\u00020\u0019H\u0002J\u0016\u0010\u001A\u001A\b\u0012\u0004\u0012\u00020\u00040\u00122\u0006\u0010\u001B\u001A\u00020\u001CH\u0002J,\u0010\u001D\u001A\u0002H\u001E\"\u0004\b\u0000\u0010\u001E*\u00020\u001F2\u0012\u0010 \u001A\u000E\u0012\u0004\u0012\u00020\u001F\u0012\u0004\u0012\u0002H\u001E0!H\u0082\b¢\u0006\u0002\u0010\"R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lkotlinx/coroutines/internal/FastServiceLoader;", "", "()V", "PREFIX", "", "createInstanceOf", "Lkotlinx/coroutines/internal/MainDispatcherFactory;", "baseClass", "Ljava/lang/Class;", "serviceClass", "getProviderInstance", "S", "name", "loader", "Ljava/lang/ClassLoader;", "service", "(Ljava/lang/String;Ljava/lang/ClassLoader;Ljava/lang/Class;)Ljava/lang/Object;", "load", "", "loadMainDispatcherFactory", "loadMainDispatcherFactory$kotlinx_coroutines_core", "loadProviders", "loadProviders$kotlinx_coroutines_core", "parse", "url", "Ljava/net/URL;", "parseFile", "r", "Ljava/io/BufferedReader;", "use", "R", "Ljava/util/jar/JarFile;", "block", "Lkotlin/Function1;", "(Ljava/util/jar/JarFile;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class FastServiceLoader {
    public static final FastServiceLoader INSTANCE = null;
    private static final String PREFIX = "META-INF/services/";

    static {
        FastServiceLoader.INSTANCE = new FastServiceLoader();
    }

    private final MainDispatcherFactory createInstanceOf(Class class0, String s) {
        try {
            return (MainDispatcherFactory)class0.cast(Class.forName(s, true, class0.getClassLoader()).getDeclaredConstructor(null).newInstance(null));
        }
        catch(ClassNotFoundException unused_ex) {
            return null;
        }
    }

    private final Object getProviderInstance(String s, ClassLoader classLoader0, Class class0) {
        Class class1 = Class.forName(s, false, classLoader0);
        if(!class0.isAssignableFrom(class1)) {
            throw new IllegalArgumentException(("Expected service of class " + class0 + ", but found " + class1).toString());
        }
        return class0.cast(class1.getDeclaredConstructor(null).newInstance(null));
    }

    private final List load(Class class0, ClassLoader classLoader0) {
        try {
            return this.loadProviders$kotlinx_coroutines_core(class0, classLoader0);
        }
        catch(Throwable unused_ex) {
            return CollectionsKt.toList(ServiceLoader.load(class0, classLoader0));
        }
    }

    public final List loadMainDispatcherFactory$kotlinx_coroutines_core() {
        MainDispatcherFactory mainDispatcherFactory1;
        Class class0 = MainDispatcherFactory.class;
        if(!1) {
            return this.load(class0, class0.getClassLoader());
        }
        try {
            ArrayList arrayList0 = new ArrayList(2);
            MainDispatcherFactory mainDispatcherFactory0 = null;
            try {
                mainDispatcherFactory1 = null;
                mainDispatcherFactory1 = (MainDispatcherFactory)class0.cast(Class.forName("kotlinx.coroutines.android.AndroidDispatcherFactory", true, class0.getClassLoader()).getDeclaredConstructor(null).newInstance(null));
            }
            catch(ClassNotFoundException unused_ex) {
            }
            if(mainDispatcherFactory1 != null) {
                arrayList0.add(mainDispatcherFactory1);
            }
            try {
                mainDispatcherFactory0 = (MainDispatcherFactory)class0.cast(Class.forName("kotlinx.coroutines.test.internal.TestMainDispatcherFactory", true, class0.getClassLoader()).getDeclaredConstructor(null).newInstance(null));
            }
            catch(ClassNotFoundException unused_ex) {
            }
            if(mainDispatcherFactory0 != null) {
                arrayList0.add(mainDispatcherFactory0);
                return arrayList0;
            }
            return arrayList0;
        }
        catch(Throwable unused_ex) {
            return this.load(class0, class0.getClassLoader());
        }
    }

    public final List loadProviders$kotlinx_coroutines_core(Class class0, ClassLoader classLoader0) {
        ArrayList arrayList0 = Collections.list(classLoader0.getResources("META-INF/services/" + class0.getName()));
        Intrinsics.checkNotNullExpressionValue(arrayList0, "list(this)");
        Collection collection0 = new ArrayList();
        for(Object object0: arrayList0) {
            CollectionsKt.addAll(collection0, FastServiceLoader.INSTANCE.parse(((URL)object0)));
        }
        Set set0 = CollectionsKt.toSet(((List)collection0));
        if(set0.isEmpty()) {
            throw new IllegalArgumentException("No providers were loaded with FastServiceLoader");
        }
        ArrayList arrayList1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(set0, 10));
        for(Object object1: set0) {
            arrayList1.add(FastServiceLoader.INSTANCE.getProviderInstance(((String)object1), classLoader0, class0));
        }
        return arrayList1;
    }

    private final List parse(URL uRL0) {
        List list1;
        List list0;
        String s = uRL0.toString();
        if(StringsKt.startsWith$default(s, "jar", false, 2, null)) {
            String s1 = StringsKt.substringBefore$default(StringsKt.substringAfter$default(s, "jar:file:", null, 2, null), '!', null, 2, null);
            String s2 = StringsKt.substringAfter$default(s, "!/", null, 2, null);
            JarFile jarFile0 = new JarFile(s1, false);
            try {
                Closeable closeable0 = new BufferedReader(new InputStreamReader(jarFile0.getInputStream(new ZipEntry(s2)), "UTF-8"));
                try {
                    list0 = FastServiceLoader.INSTANCE.parseFile(((BufferedReader)closeable0));
                }
                catch(Throwable throwable1) {
                    CloseableKt.closeFinally(closeable0, throwable1);
                    throw throwable1;
                }
                CloseableKt.closeFinally(closeable0, null);
            }
            catch(Throwable throwable0) {
                try {
                    jarFile0.close();
                }
                catch(Throwable throwable2) {
                    ExceptionsKt.addSuppressed(throwable0, throwable2);
                    throw throwable0;
                }
                throw throwable0;
            }
            jarFile0.close();
            return list0;
        }
        Closeable closeable1 = new BufferedReader(new InputStreamReader(uRL0.openStream()));
        try {
            list1 = FastServiceLoader.INSTANCE.parseFile(((BufferedReader)closeable1));
        }
        catch(Throwable throwable3) {
            CloseableKt.closeFinally(closeable1, throwable3);
            throw throwable3;
        }
        CloseableKt.closeFinally(closeable1, null);
        return list1;
    }

    private final List parseFile(BufferedReader bufferedReader0) {
        Set set0 = new LinkedHashSet();
        while(true) {
            String s = bufferedReader0.readLine();
            if(s == null) {
                return CollectionsKt.toList(set0);
            }
            String s1 = StringsKt.trim(StringsKt.substringBefore$default(s, "#", null, 2, null)).toString();
            CharSequence charSequence0 = s1;
            for(int v = 0; v < charSequence0.length(); ++v) {
                int v1 = charSequence0.charAt(v);
                if(v1 != 46 && !Character.isJavaIdentifierPart(((char)v1))) {
                    throw new IllegalArgumentException(("Illegal service provider class name: " + s1).toString());
                }
            }
            if(charSequence0.length() > 0) {
                set0.add(s1);
            }
        }
    }

    private final Object use(JarFile jarFile0, Function1 function10) {
        Object object0;
        try {
            object0 = function10.invoke(jarFile0);
        }
        catch(Throwable throwable0) {
            try {
                jarFile0.close();
            }
            catch(Throwable throwable1) {
                ExceptionsKt.addSuppressed(throwable0, throwable1);
                throw throwable0;
            }
            throw throwable0;
        }
        jarFile0.close();
        return object0;
    }
}

