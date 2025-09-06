package kotlin.reflect.jvm.internal.impl.storage;

class SingleThreadValue {
    private final Thread thread;
    private final Object value;

    SingleThreadValue(Object object0) {
        this.value = object0;
        this.thread = Thread.currentThread();
    }

    public Object getValue() {
        if(!this.hasValue()) {
            throw new IllegalStateException("No value in this thread (hasValue should be checked before)");
        }
        return this.value;
    }

    public boolean hasValue() {
        return this.thread == Thread.currentThread();
    }
}

