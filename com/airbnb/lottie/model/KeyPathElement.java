package com.airbnb.lottie.model;

import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;

public interface KeyPathElement {
    void addValueCallback(Object arg1, LottieValueCallback arg2);

    void resolveKeyPath(KeyPath arg1, int arg2, List arg3, KeyPath arg4);
}

