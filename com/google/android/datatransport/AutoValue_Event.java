package com.google.android.datatransport;

final class AutoValue_Event extends Event {
    private final Integer code;
    private final Object payload;
    private final Priority priority;

    AutoValue_Event(Integer integer0, Object object0, Priority priority0) {
        this.code = integer0;
        if(object0 == null) {
            throw new NullPointerException("Null payload");
        }
        this.payload = object0;
        if(priority0 == null) {
            throw new NullPointerException("Null priority");
        }
        this.priority = priority0;
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(object0 instanceof Event) {
            Integer integer0 = this.code;
            if(integer0 != null) {
                if(integer0.equals(((Event)object0).getCode())) {
                label_8:
                    Object object1 = ((Event)object0).getPayload();
                    if(this.payload.equals(object1)) {
                        Priority priority0 = ((Event)object0).getPriority();
                        return this.priority.equals(priority0);
                    }
                }
            }
            else if(((Event)object0).getCode() == null) {
                goto label_8;
            }
        }
        return false;
    }

    @Override  // com.google.android.datatransport.Event
    public Integer getCode() {
        return this.code;
    }

    @Override  // com.google.android.datatransport.Event
    public Object getPayload() {
        return this.payload;
    }

    @Override  // com.google.android.datatransport.Event
    public Priority getPriority() {
        return this.priority;
    }

    @Override
    public int hashCode() {
        return this.code == null ? (0xD5009D89 ^ this.payload.hashCode()) * 1000003 ^ this.priority.hashCode() : ((this.code.hashCode() ^ 1000003) * 1000003 ^ this.payload.hashCode()) * 1000003 ^ this.priority.hashCode();
    }

    @Override
    public String toString() {
        return "Event{code=" + this.code + ", payload=" + this.payload + ", priority=" + this.priority + "}";
    }
}

