package kotlinx.coroutines.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CopyableThrowable;

@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000B\u001A2\u0010\u0004\u001A\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005j\u0002`\u0007\"\b\b\u0000\u0010\b*\u00020\u00062\f\u0010\t\u001A\b\u0012\u0004\u0012\u0002H\b0\nH\u0002\u001A.\u0010\u000B\u001A\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005j\u0002`\u00072\u0012\u0010\f\u001A\u000E\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005H\u0002\u001A!\u0010\r\u001A\u0004\u0018\u0001H\b\"\b\b\u0000\u0010\b*\u00020\u00062\u0006\u0010\u000E\u001A\u0002H\bH\u0000¢\u0006\u0002\u0010\u000F\u001A\u001B\u0010\u0010\u001A\u00020\u0003*\u0006\u0012\u0002\b\u00030\n2\b\b\u0002\u0010\u0011\u001A\u00020\u0003H\u0082\u0010\u001A\u0018\u0010\u0012\u001A\u00020\u0003*\u0006\u0012\u0002\b\u00030\n2\u0006\u0010\u0013\u001A\u00020\u0003H\u0002\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000*(\b\u0002\u0010\u0014\"\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00052\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005¨\u0006\u0015"}, d2 = {"ctorCache", "Lkotlinx/coroutines/internal/CtorCache;", "throwableFields", "", "createConstructor", "Lkotlin/Function1;", "", "Lkotlinx/coroutines/internal/Ctor;", "E", "clz", "Ljava/lang/Class;", "safeCtor", "block", "tryCopyException", "exception", "(Ljava/lang/Throwable;)Ljava/lang/Throwable;", "fieldsCount", "accumulator", "fieldsCountOrDefault", "defaultValue", "Ctor", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class ExceptionsConstructorKt {
    private static final CtorCache ctorCache;
    private static final int throwableFields;

    static {
        ExceptionsConstructorKt.throwableFields = ExceptionsConstructorKt.fieldsCountOrDefault(Throwable.class, -1);
        ExceptionsConstructorKt.ctorCache = WeakMapCtorCache.INSTANCE;
    }

    private static final Function1 createConstructor(Class class0) {
        Pair pair0;
        Object object0;
        Function1 function10 = kotlinx.coroutines.internal.ExceptionsConstructorKt.createConstructor.nullResult.1.INSTANCE;
        int v = ExceptionsConstructorKt.fieldsCountOrDefault(class0, 0);
        if(ExceptionsConstructorKt.throwableFields == v) {
            Constructor[] arr_constructor = class0.getConstructors();
            ArrayList arrayList0 = new ArrayList(arr_constructor.length);
            for(int v1 = 0; true; ++v1) {
                object0 = null;
                if(v1 >= arr_constructor.length) {
                    break;
                }
                Constructor constructor0 = arr_constructor[v1];
                Class[] arr_class = constructor0.getParameterTypes();
                int v2 = arr_class.length;
                if(v2 == 0) {
                    pair0 = TuplesKt.to(ExceptionsConstructorKt.safeCtor(new Function1(constructor0) {
                        final Constructor $constructor;

                        {
                            this.$constructor = constructor0;
                            super(1);
                        }

                        @Override  // kotlin.jvm.functions.Function1
                        public Object invoke(Object object0) {
                            return this.invoke(((Throwable)object0));
                        }

                        public final Throwable invoke(Throwable throwable0) {
                            Object object0 = this.$constructor.newInstance(null);
                            Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlin.Throwable");
                            ((Throwable)object0).initCause(throwable0);
                            return (Throwable)object0;
                        }
                    }), 0);
                }
                else {
                    switch(v2) {
                        case 1: {
                            Class class1 = arr_class[0];
                            if(Intrinsics.areEqual(class1, String.class)) {
                                pair0 = TuplesKt.to(ExceptionsConstructorKt.safeCtor(new Function1(constructor0) {
                                    final Constructor $constructor;

                                    {
                                        this.$constructor = constructor0;
                                        super(1);
                                    }

                                    @Override  // kotlin.jvm.functions.Function1
                                    public Object invoke(Object object0) {
                                        return this.invoke(((Throwable)object0));
                                    }

                                    public final Throwable invoke(Throwable throwable0) {
                                        Object object0 = this.$constructor.newInstance(throwable0.getMessage());
                                        Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlin.Throwable");
                                        ((Throwable)object0).initCause(throwable0);
                                        return (Throwable)object0;
                                    }
                                }), 2);
                            }
                            else if(Intrinsics.areEqual(class1, Throwable.class)) {
                                pair0 = TuplesKt.to(ExceptionsConstructorKt.safeCtor(new Function1(constructor0) {
                                    final Constructor $constructor;

                                    {
                                        this.$constructor = constructor0;
                                        super(1);
                                    }

                                    @Override  // kotlin.jvm.functions.Function1
                                    public Object invoke(Object object0) {
                                        return this.invoke(((Throwable)object0));
                                    }

                                    public final Throwable invoke(Throwable throwable0) {
                                        Object object0 = this.$constructor.newInstance(throwable0);
                                        Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlin.Throwable");
                                        return (Throwable)object0;
                                    }
                                }), 1);
                            }
                            else {
                                pair0 = TuplesKt.to(null, -1);
                            }
                            break;
                        }
                        case 2: {
                            pair0 = !Intrinsics.areEqual(arr_class[0], String.class) || !Intrinsics.areEqual(arr_class[1], Throwable.class) ? TuplesKt.to(null, -1) : TuplesKt.to(ExceptionsConstructorKt.safeCtor(new Function1(constructor0) {
                                final Constructor $constructor;

                                {
                                    this.$constructor = constructor0;
                                    super(1);
                                }

                                @Override  // kotlin.jvm.functions.Function1
                                public Object invoke(Object object0) {
                                    return this.invoke(((Throwable)object0));
                                }

                                public final Throwable invoke(Throwable throwable0) {
                                    Object object0 = this.$constructor.newInstance(throwable0.getMessage(), throwable0);
                                    Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlin.Throwable");
                                    return (Throwable)object0;
                                }
                            }), 3);
                            break;
                        }
                        default: {
                            pair0 = TuplesKt.to(null, -1);
                        }
                    }
                }
                arrayList0.add(pair0);
            }
            Iterator iterator0 = arrayList0.iterator();
            if(iterator0.hasNext()) {
                object0 = iterator0.next();
                if(iterator0.hasNext()) {
                    int v3 = ((Number)((Pair)object0).getSecond()).intValue();
                    while(true) {
                        Object object1 = iterator0.next();
                        int v4 = ((Number)((Pair)object1).getSecond()).intValue();
                        if(v3 < v4) {
                            object0 = object1;
                            v3 = v4;
                        }
                        if(!iterator0.hasNext()) {
                            break;
                        }
                    }
                }
            }
            if(((Pair)object0) != null) {
                Function1 function11 = (Function1)((Pair)object0).getFirst();
                return function11 == null ? function10 : function11;
            }
        }
        return function10;

        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001A\u0004\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "E", "", "it", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlinx.coroutines.internal.ExceptionsConstructorKt.createConstructor.nullResult.1 extends Lambda implements Function1 {
            public static final kotlinx.coroutines.internal.ExceptionsConstructorKt.createConstructor.nullResult.1 INSTANCE;

            static {
                kotlinx.coroutines.internal.ExceptionsConstructorKt.createConstructor.nullResult.1.INSTANCE = new kotlinx.coroutines.internal.ExceptionsConstructorKt.createConstructor.nullResult.1();
            }

            kotlinx.coroutines.internal.ExceptionsConstructorKt.createConstructor.nullResult.1() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((Throwable)object0));
            }

            public final Void invoke(Throwable throwable0) {
                return null;
            }
        }

    }

    private static final int fieldsCount(Class class0, int v) {
        do {
            Field[] arr_field = class0.getDeclaredFields();
            int v2 = 0;
            for(int v1 = 0; v1 < arr_field.length; ++v1) {
                if(!Modifier.isStatic(arr_field[v1].getModifiers())) {
                    ++v2;
                }
            }
            v += v2;
            class0 = class0.getSuperclass();
        }
        while(class0 != null);
        return v;
    }

    static int fieldsCount$default(Class class0, int v, int v1, Object object0) {
        if((v1 & 1) != 0) {
            v = 0;
        }
        return ExceptionsConstructorKt.fieldsCount(class0, v);
    }

    private static final int fieldsCountOrDefault(Class class0, int v) {
        Integer integer0;
        JvmClassMappingKt.getKotlinClass(class0);
        try {
            integer0 = Result.constructor-impl(ExceptionsConstructorKt.fieldsCount$default(class0, 0, 1, null));
        }
        catch(Throwable throwable0) {
            integer0 = Result.constructor-impl(ResultKt.createFailure(throwable0));
        }
        Integer integer1 = v;
        if(Result.isFailure-impl(integer0)) {
            integer0 = integer1;
        }
        return integer0.intValue();
    }

    private static final Function1 safeCtor(Function1 function10) {
        return new Function1(function10) {
            final Function1 $block;

            {
                this.$block = function10;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((Throwable)object0));
            }

            public final Throwable invoke(Throwable throwable0) {
                try {
                    Throwable throwable2 = (Throwable)this.$block.invoke(throwable0);
                    if(!Intrinsics.areEqual(throwable0.getMessage(), throwable2.getMessage()) && !Intrinsics.areEqual(throwable2.getMessage(), throwable0.toString())) {
                        throwable2 = null;
                    }
                    return Result.isFailure-impl(throwable2) ? null : throwable2;
                }
                catch(Throwable throwable1) {
                    Throwable throwable3 = Result.constructor-impl(ResultKt.createFailure(throwable1));
                    return Result.isFailure-impl(throwable3) ? null : throwable3;
                }
            }
        };
    }

    public static final Throwable tryCopyException(Throwable throwable0) {
        Object object0;
        if(throwable0 instanceof CopyableThrowable) {
            try {
                object0 = Result.constructor-impl(((CopyableThrowable)throwable0).createCopy());
            }
            catch(Throwable throwable1) {
                object0 = Result.constructor-impl(ResultKt.createFailure(throwable1));
            }
            if(Result.isFailure-impl(object0)) {
                object0 = null;
            }
            return (Throwable)object0;
        }
        Class class0 = throwable0.getClass();
        return (Throwable)ExceptionsConstructorKt.ctorCache.get(class0).invoke(throwable0);
    }
}

