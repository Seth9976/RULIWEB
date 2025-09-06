package com.google.android.datatransport.runtime.dagger.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class SetBuilder {
    private static final String SET_CONTRIBUTIONS_CANNOT_BE_NULL = "Set contributions cannot be null";
    private final List contributions;

    private SetBuilder(int v) {
        this.contributions = new ArrayList(v);
    }

    public SetBuilder add(Object object0) {
        Object object1 = Preconditions.checkNotNull(object0, "Set contributions cannot be null");
        this.contributions.add(object1);
        return this;
    }

    public SetBuilder addAll(Collection collection0) {
        for(Object object0: collection0) {
            Preconditions.checkNotNull(object0, "Set contributions cannot be null");
        }
        this.contributions.addAll(collection0);
        return this;
    }

    public Set build() {
        switch(this.contributions.size()) {
            case 0: {
                return Collections.EMPTY_SET;
            }
            case 1: {
                return Collections.singleton(this.contributions.get(0));
            }
            default: {
                return Collections.unmodifiableSet(new HashSet(this.contributions));
            }
        }
    }

    public static SetBuilder newSetBuilder(int v) {
        return new SetBuilder(v);
    }
}

