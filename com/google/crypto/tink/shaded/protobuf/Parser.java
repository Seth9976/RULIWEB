package com.google.crypto.tink.shaded.protobuf;

import java.io.InputStream;
import java.nio.ByteBuffer;

public interface Parser {
    Object parseDelimitedFrom(InputStream arg1) throws InvalidProtocolBufferException;

    Object parseDelimitedFrom(InputStream arg1, ExtensionRegistryLite arg2) throws InvalidProtocolBufferException;

    Object parseFrom(ByteString arg1) throws InvalidProtocolBufferException;

    Object parseFrom(ByteString arg1, ExtensionRegistryLite arg2) throws InvalidProtocolBufferException;

    Object parseFrom(CodedInputStream arg1) throws InvalidProtocolBufferException;

    Object parseFrom(CodedInputStream arg1, ExtensionRegistryLite arg2) throws InvalidProtocolBufferException;

    Object parseFrom(InputStream arg1) throws InvalidProtocolBufferException;

    Object parseFrom(InputStream arg1, ExtensionRegistryLite arg2) throws InvalidProtocolBufferException;

    Object parseFrom(ByteBuffer arg1) throws InvalidProtocolBufferException;

    Object parseFrom(ByteBuffer arg1, ExtensionRegistryLite arg2) throws InvalidProtocolBufferException;

    Object parseFrom(byte[] arg1) throws InvalidProtocolBufferException;

    Object parseFrom(byte[] arg1, int arg2, int arg3) throws InvalidProtocolBufferException;

    Object parseFrom(byte[] arg1, int arg2, int arg3, ExtensionRegistryLite arg4) throws InvalidProtocolBufferException;

    Object parseFrom(byte[] arg1, ExtensionRegistryLite arg2) throws InvalidProtocolBufferException;

    Object parsePartialDelimitedFrom(InputStream arg1) throws InvalidProtocolBufferException;

    Object parsePartialDelimitedFrom(InputStream arg1, ExtensionRegistryLite arg2) throws InvalidProtocolBufferException;

    Object parsePartialFrom(ByteString arg1) throws InvalidProtocolBufferException;

    Object parsePartialFrom(ByteString arg1, ExtensionRegistryLite arg2) throws InvalidProtocolBufferException;

    Object parsePartialFrom(CodedInputStream arg1) throws InvalidProtocolBufferException;

    Object parsePartialFrom(CodedInputStream arg1, ExtensionRegistryLite arg2) throws InvalidProtocolBufferException;

    Object parsePartialFrom(InputStream arg1) throws InvalidProtocolBufferException;

    Object parsePartialFrom(InputStream arg1, ExtensionRegistryLite arg2) throws InvalidProtocolBufferException;

    Object parsePartialFrom(byte[] arg1) throws InvalidProtocolBufferException;

    Object parsePartialFrom(byte[] arg1, int arg2, int arg3) throws InvalidProtocolBufferException;

    Object parsePartialFrom(byte[] arg1, int arg2, int arg3, ExtensionRegistryLite arg4) throws InvalidProtocolBufferException;

    Object parsePartialFrom(byte[] arg1, ExtensionRegistryLite arg2) throws InvalidProtocolBufferException;
}

