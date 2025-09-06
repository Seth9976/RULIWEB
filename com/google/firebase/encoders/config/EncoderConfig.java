package com.google.firebase.encoders.config;

import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ValueEncoder;

public interface EncoderConfig {
    EncoderConfig registerEncoder(Class arg1, ObjectEncoder arg2);

    EncoderConfig registerEncoder(Class arg1, ValueEncoder arg2);
}

