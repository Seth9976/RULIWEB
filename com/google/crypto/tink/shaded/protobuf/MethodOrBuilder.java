package com.google.crypto.tink.shaded.protobuf;

import java.util.List;

public interface MethodOrBuilder extends MessageLiteOrBuilder {
    String getName();

    ByteString getNameBytes();

    Option getOptions(int arg1);

    int getOptionsCount();

    List getOptionsList();

    boolean getRequestStreaming();

    String getRequestTypeUrl();

    ByteString getRequestTypeUrlBytes();

    boolean getResponseStreaming();

    String getResponseTypeUrl();

    ByteString getResponseTypeUrlBytes();

    Syntax getSyntax();

    int getSyntaxValue();
}

