package com.google.crypto.tink.shaded.protobuf;

import java.util.List;

public interface ApiOrBuilder extends MessageLiteOrBuilder {
    Method getMethods(int arg1);

    int getMethodsCount();

    List getMethodsList();

    Mixin getMixins(int arg1);

    int getMixinsCount();

    List getMixinsList();

    String getName();

    ByteString getNameBytes();

    Option getOptions(int arg1);

    int getOptionsCount();

    List getOptionsList();

    SourceContext getSourceContext();

    Syntax getSyntax();

    int getSyntaxValue();

    String getVersion();

    ByteString getVersionBytes();

    boolean hasSourceContext();
}

