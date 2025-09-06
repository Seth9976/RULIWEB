package com.airbnb.lottie;

import java.util.Arrays;

public final class LottieResult {
    private final Throwable exception;
    private final Object value;

    public LottieResult(Object object0) {
        this.value = object0;
        this.exception = null;
    }

    public LottieResult(Throwable throwable0) {
        this.exception = throwable0;
        this.value = null;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof LottieResult)) {
            return false;
        }
        if(this.getValue() != null && this.getValue().equals(((LottieResult)object0).getValue())) {
            return true;
        }
        return this.getException() == null || ((LottieResult)object0).getException() == null ? false : this.getException().toString().equals(this.getException().toString());
    }

    public Throwable getException() {
        return this.exception;
    }

    public Object getValue() {
        return this.value;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.getValue(), this.getException()});
    }
}

