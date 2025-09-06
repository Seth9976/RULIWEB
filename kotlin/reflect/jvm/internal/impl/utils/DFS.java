package kotlin.reflect.jvm.internal.impl.utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import kotlin.jvm.functions.Function1;

public class DFS {
    public static abstract class AbstractNodeHandler implements NodeHandler {
        @Override  // kotlin.reflect.jvm.internal.impl.utils.DFS$NodeHandler
        public void afterChildren(Object object0) {
        }

        @Override  // kotlin.reflect.jvm.internal.impl.utils.DFS$NodeHandler
        public boolean beforeChildren(Object object0) {
            return true;
        }
    }

    public static abstract class CollectingNodeHandler extends AbstractNodeHandler {
        protected final Iterable result;

        private static void $$$reportNull$$$0(int v) {
            Object[] arr_object = new Object[(v == 1 ? 2 : 3)];
            arr_object[0] = v == 1 ? "kotlin/reflect/jvm/internal/impl/utils/DFS$CollectingNodeHandler" : "result";
            arr_object[1] = v == 1 ? "result" : "kotlin/reflect/jvm/internal/impl/utils/DFS$CollectingNodeHandler";
            if(v != 1) {
                arr_object[2] = "<init>";
            }
            String s = String.format((v == 1 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null"), arr_object);
            IllegalArgumentException illegalArgumentException0 = v == 1 ? new IllegalStateException(s) : new IllegalArgumentException(s);
            throw illegalArgumentException0;
        }

        protected CollectingNodeHandler(Iterable iterable0) {
            if(iterable0 == null) {
                CollectingNodeHandler.$$$reportNull$$$0(0);
            }
            super();
            this.result = iterable0;
        }

        public Iterable result() {
            Iterable iterable0 = this.result;
            if(iterable0 == null) {
                CollectingNodeHandler.$$$reportNull$$$0(1);
            }
            return iterable0;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.utils.DFS$NodeHandler
        public Object result() {
            return this.result();
        }
    }

    public interface Neighbors {
        Iterable getNeighbors(Object arg1);
    }

    public interface NodeHandler {
        void afterChildren(Object arg1);

        boolean beforeChildren(Object arg1);

        Object result();
    }

    public static abstract class NodeHandlerWithListResult extends CollectingNodeHandler {
        protected NodeHandlerWithListResult() {
            super(new LinkedList());
        }
    }

    public interface Visited {
        boolean checkAndMarkVisited(Object arg1);
    }

    public static class VisitedWithSet implements Visited {
        private final Set visited;

        // 去混淆评级： 低(20)
        private static void $$$reportNull$$$0(int v) {
            throw new IllegalArgumentException("Argument for @NotNull parameter \'visited\' of kotlin/reflect/jvm/internal/impl/utils/DFS$VisitedWithSet.<init> must not be null");
        }

        public VisitedWithSet() {
            this(new HashSet());
        }

        public VisitedWithSet(Set set0) {
            if(set0 == null) {
                VisitedWithSet.$$$reportNull$$$0(0);
            }
            super();
            this.visited = set0;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.utils.DFS$Visited
        public boolean checkAndMarkVisited(Object object0) {
            return this.visited.add(object0);
        }
    }

    private static void $$$reportNull$$$0(int v) {
        Object[] arr_object = new Object[3];
        switch(v) {
            case 9: {
                arr_object[0] = "predicate";
                break;
            }
            case 10: 
            case 14: {
                arr_object[0] = "node";
                break;
            }
            case 22: {
                arr_object[0] = "current";
                break;
            }
            case 1: 
            case 5: 
            case 8: 
            case 11: 
            case 15: 
            case 18: 
            case 21: 
            case 23: {
                arr_object[0] = "neighbors";
                break;
            }
            case 2: 
            case 12: 
            case 16: 
            case 19: 
            case 24: {
                arr_object[0] = "visited";
                break;
            }
            case 3: 
            case 6: 
            case 13: 
            case 25: {
                arr_object[0] = "handler";
                break;
            }
            default: {
                arr_object[0] = "nodes";
            }
        }
        arr_object[1] = "kotlin/reflect/jvm/internal/impl/utils/DFS";
        switch(v) {
            case 7: 
            case 8: 
            case 9: {
                arr_object[2] = "ifAny";
                break;
            }
            case 10: 
            case 11: 
            case 12: 
            case 13: 
            case 14: 
            case 15: 
            case 16: {
                arr_object[2] = "dfsFromNode";
                break;
            }
            case 17: 
            case 18: 
            case 19: 
            case 20: 
            case 21: {
                arr_object[2] = "topologicalOrder";
                break;
            }
            case 22: 
            case 23: 
            case 24: 
            case 25: {
                arr_object[2] = "doDfs";
                break;
            }
            default: {
                arr_object[2] = "dfs";
            }
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter \'%s\' of %s.%s must not be null", arr_object));
    }

    public static Object dfs(Collection collection0, Neighbors dFS$Neighbors0, NodeHandler dFS$NodeHandler0) {
        if(collection0 == null) {
            DFS.$$$reportNull$$$0(4);
        }
        if(dFS$Neighbors0 == null) {
            DFS.$$$reportNull$$$0(5);
        }
        if(dFS$NodeHandler0 == null) {
            DFS.$$$reportNull$$$0(6);
        }
        return DFS.dfs(collection0, dFS$Neighbors0, new VisitedWithSet(), dFS$NodeHandler0);
    }

    public static Object dfs(Collection collection0, Neighbors dFS$Neighbors0, Visited dFS$Visited0, NodeHandler dFS$NodeHandler0) {
        if(collection0 == null) {
            DFS.$$$reportNull$$$0(0);
        }
        if(dFS$Neighbors0 == null) {
            DFS.$$$reportNull$$$0(1);
        }
        if(dFS$Visited0 == null) {
            DFS.$$$reportNull$$$0(2);
        }
        if(dFS$NodeHandler0 == null) {
            DFS.$$$reportNull$$$0(3);
        }
        for(Object object0: collection0) {
            DFS.doDfs(object0, dFS$Neighbors0, dFS$Visited0, dFS$NodeHandler0);
        }
        return dFS$NodeHandler0.result();
    }

    public static void doDfs(Object object0, Neighbors dFS$Neighbors0, Visited dFS$Visited0, NodeHandler dFS$NodeHandler0) {
        if(object0 == null) {
            DFS.$$$reportNull$$$0(22);
        }
        if(dFS$Neighbors0 == null) {
            DFS.$$$reportNull$$$0(23);
        }
        if(dFS$Visited0 == null) {
            DFS.$$$reportNull$$$0(24);
        }
        if(dFS$NodeHandler0 == null) {
            DFS.$$$reportNull$$$0(25);
        }
        if(!dFS$Visited0.checkAndMarkVisited(object0) || !dFS$NodeHandler0.beforeChildren(object0)) {
            return;
        }
        for(Object object1: dFS$Neighbors0.getNeighbors(object0)) {
            DFS.doDfs(object1, dFS$Neighbors0, dFS$Visited0, dFS$NodeHandler0);
        }
        dFS$NodeHandler0.afterChildren(object0);
    }

    public static Boolean ifAny(Collection collection0, Neighbors dFS$Neighbors0, Function1 function10) {
        if(collection0 == null) {
            DFS.$$$reportNull$$$0(7);
        }
        if(dFS$Neighbors0 == null) {
            DFS.$$$reportNull$$$0(8);
        }
        if(function10 == null) {
            DFS.$$$reportNull$$$0(9);
        }
        return (Boolean)DFS.dfs(collection0, dFS$Neighbors0, new AbstractNodeHandler() {
            @Override  // kotlin.reflect.jvm.internal.impl.utils.DFS$AbstractNodeHandler
            public boolean beforeChildren(Object object0) {
                if(((Boolean)function10.invoke(object0)).booleanValue()) {
                    new boolean[1][0] = true;
                }
                return !new boolean[1][0];
            }

            public Boolean result() {
                return Boolean.valueOf(new boolean[1][0]);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.utils.DFS$NodeHandler
            public Object result() {
                return this.result();
            }
        });
    }
}

