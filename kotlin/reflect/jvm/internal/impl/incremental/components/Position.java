package kotlin.reflect.jvm.internal.impl.incremental.components;

import java.io.Serializable;
import kotlin.jvm.internal.DefaultConstructorMarker;

public final class Position implements Serializable {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final Position getNO_POSITION() {
            return Position.NO_POSITION;
        }
    }

    public static final Companion Companion;
    private static final Position NO_POSITION;
    private final int column;
    private final int line;

    static {
        Position.Companion = new Companion(null);
        Position.NO_POSITION = new Position(-1, -1);
    }

    public Position(int v, int v1) {
        this.line = v;
        this.column = v1;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof Position)) {
            return false;
        }
        return this.line == ((Position)object0).line ? this.column == ((Position)object0).column : false;
    }

    @Override
    public int hashCode() {
        return this.line * 0x1F + this.column;
    }

    @Override
    public String toString() {
        return "Position(line=" + this.line + ", column=" + this.column + ')';
    }
}

