package com.google.gson.internal;

import com.google.gson.InstanceCreator;
import com.google.gson.JsonIOException;
import com.google.gson.internal.reflect.ReflectionAccessor;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public final class ConstructorConstructor {
    private final ReflectionAccessor accessor;
    private final Map instanceCreators;

    public ConstructorConstructor(Map map0) {
        this.accessor = ReflectionAccessor.getInstance();
        this.instanceCreators = map0;
    }

    public ObjectConstructor get(TypeToken typeToken0) {
        Type type0 = typeToken0.getType();
        Class class0 = typeToken0.getRawType();
        InstanceCreator instanceCreator0 = (InstanceCreator)this.instanceCreators.get(type0);
        if(instanceCreator0 != null) {
            return new ObjectConstructor() {
                @Override  // com.google.gson.internal.ObjectConstructor
                public Object construct() {
                    return instanceCreator0.createInstance(type0);
                }
            };
        }
        InstanceCreator instanceCreator1 = (InstanceCreator)this.instanceCreators.get(class0);
        if(instanceCreator1 != null) {
            return new ObjectConstructor() {
                @Override  // com.google.gson.internal.ObjectConstructor
                public Object construct() {
                    return instanceCreator1.createInstance(type0);
                }
            };
        }
        ObjectConstructor objectConstructor0 = this.newDefaultConstructor(class0);
        if(objectConstructor0 != null) {
            return objectConstructor0;
        }
        ObjectConstructor objectConstructor1 = this.newDefaultImplementationConstructor(type0, class0);
        return objectConstructor1 == null ? this.newUnsafeAllocator(type0, class0) : objectConstructor1;
    }

    private ObjectConstructor newDefaultConstructor(Class class0) {
        try {
            Constructor constructor0 = class0.getDeclaredConstructor(null);
            if(!constructor0.isAccessible()) {
                this.accessor.makeAccessible(constructor0);
            }
            return new ObjectConstructor() {
                @Override  // com.google.gson.internal.ObjectConstructor
                public Object construct() {
                    try {
                        return constructor0.newInstance(null);
                    }
                    catch(InstantiationException instantiationException0) {
                        throw new RuntimeException("Failed to invoke " + constructor0 + " with no args", instantiationException0);
                    }
                    catch(InvocationTargetException invocationTargetException0) {
                        throw new RuntimeException("Failed to invoke " + constructor0 + " with no args", invocationTargetException0.getTargetException());
                    }
                    catch(IllegalAccessException illegalAccessException0) {
                        throw new AssertionError(illegalAccessException0);
                    }
                }
            };
        }
        catch(NoSuchMethodException unused_ex) {
            return null;
        }
    }

    private ObjectConstructor newDefaultImplementationConstructor(Type type0, Class class0) {
        if(Collection.class.isAssignableFrom(class0)) {
            if(SortedSet.class.isAssignableFrom(class0)) {
                return new ObjectConstructor() {
                    @Override  // com.google.gson.internal.ObjectConstructor
                    public Object construct() {
                        return new TreeSet();
                    }
                };
            }
            if(EnumSet.class.isAssignableFrom(class0)) {
                return new ObjectConstructor() {
                    @Override  // com.google.gson.internal.ObjectConstructor
                    public Object construct() {
                        Type type0 = type0;
                        if(!(type0 instanceof ParameterizedType)) {
                            throw new JsonIOException("Invalid EnumSet type: " + type0.toString());
                        }
                        Type type1 = ((ParameterizedType)type0).getActualTypeArguments()[0];
                        if(!(type1 instanceof Class)) {
                            throw new JsonIOException("Invalid EnumSet type: " + type0.toString());
                        }
                        return EnumSet.noneOf(((Class)type1));
                    }
                };
            }
            if(Set.class.isAssignableFrom(class0)) {
                return new ObjectConstructor() {
                    @Override  // com.google.gson.internal.ObjectConstructor
                    public Object construct() {
                        return new LinkedHashSet();
                    }
                };
            }
            return Queue.class.isAssignableFrom(class0) ? new ObjectConstructor() {
                @Override  // com.google.gson.internal.ObjectConstructor
                public Object construct() {
                    return new ArrayDeque();
                }
            } : new ObjectConstructor() {
                @Override  // com.google.gson.internal.ObjectConstructor
                public Object construct() {
                    return new ArrayList();
                }
            };
        }
        if(Map.class.isAssignableFrom(class0)) {
            if(ConcurrentNavigableMap.class.isAssignableFrom(class0)) {
                return new ObjectConstructor() {
                    @Override  // com.google.gson.internal.ObjectConstructor
                    public Object construct() {
                        return new ConcurrentSkipListMap();
                    }
                };
            }
            if(ConcurrentMap.class.isAssignableFrom(class0)) {
                return new ObjectConstructor() {
                    @Override  // com.google.gson.internal.ObjectConstructor
                    public Object construct() {
                        return new ConcurrentHashMap();
                    }
                };
            }
            if(SortedMap.class.isAssignableFrom(class0)) {
                return new ObjectConstructor() {
                    @Override  // com.google.gson.internal.ObjectConstructor
                    public Object construct() {
                        return new TreeMap();
                    }
                };
            }
            if(type0 instanceof ParameterizedType) {
                Class class1 = TypeToken.get(((ParameterizedType)type0).getActualTypeArguments()[0]).getRawType();
                if(!String.class.isAssignableFrom(class1)) {
                    return new ObjectConstructor() {
                        @Override  // com.google.gson.internal.ObjectConstructor
                        public Object construct() {
                            return new LinkedHashMap();
                        }
                    };
                }
            }
            return new ObjectConstructor() {
                @Override  // com.google.gson.internal.ObjectConstructor
                public Object construct() {
                    return new LinkedTreeMap();
                }
            };
        }
        return null;
    }

    private ObjectConstructor newUnsafeAllocator(Type type0, Class class0) {
        return new ObjectConstructor() {
            private final UnsafeAllocator unsafeAllocator;

            {
                Class class0 = class0;  // 捕获的参数 （可能与外部方法变量命名冲突；考虑手动重命名）
                Type type0 = type0;  // 捕获的参数 （可能与外部方法变量命名冲突；考虑手动重命名）
                this.unsafeAllocator = UnsafeAllocator.create();
            }

            @Override  // com.google.gson.internal.ObjectConstructor
            public Object construct() {
                try {
                    return this.unsafeAllocator.newInstance(class0);
                }
                catch(Exception exception0) {
                    throw new RuntimeException("Unable to invoke no-args constructor for " + type0 + ". Registering an InstanceCreator with Gson for this type may fix this problem.", exception0);
                }
            }
        };
    }

    @Override
    public String toString() {
        return this.instanceCreators.toString();
    }
}

