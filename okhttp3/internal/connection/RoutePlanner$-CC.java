package okhttp3.internal.connection;

public final class RoutePlanner.-CC {
    public static boolean hasNext$default(RoutePlanner routePlanner0, RealConnection realConnection0, int v, Object object0) {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: hasNext");
        }
        if((v & 1) != 0) {
            realConnection0 = null;
        }
        return routePlanner0.hasNext(realConnection0);
    }
}

