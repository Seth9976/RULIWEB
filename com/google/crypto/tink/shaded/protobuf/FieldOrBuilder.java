package com.google.crypto.tink.shaded.protobuf;

import java.util.List;

public interface FieldOrBuilder extends MessageLiteOrBuilder {
    Cardinality getCardinality();

    int getCardinalityValue();

    String getDefaultValue();

    ByteString getDefaultValueBytes();

    String getJsonName();

    ByteString getJsonNameBytes();

    Kind getKind();

    int getKindValue();

    String getName();

    ByteString getNameBytes();

    int getNumber();

    int getOneofIndex();

    Option getOptions(int arg1);

    int getOptionsCount();

    List getOptionsList();

    boolean getPacked();

    String getTypeUrl();

    ByteString getTypeUrlBytes();
}

