package com.google.crypto.tink;

import com.google.crypto.tink.internal.JsonParser;
import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyStatusType;
import com.google.crypto.tink.proto.Keyset.Builder;
import com.google.crypto.tink.proto.Keyset.Key;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.proto.KeysetInfo.KeyInfo;
import com.google.crypto.tink.proto.KeysetInfo;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.subtle.Base64;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Path;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;

public final class JsonKeysetReader implements KeysetReader {
    private static final long MAX_KEY_ID = 0xFFFFFFFFL;
    private static final long MIN_KEY_ID = 0xFFFFFFFF80000000L;
    private static final Charset UTF_8;
    private final InputStream inputStream;
    private boolean urlSafeBase64;

    static {
        JsonKeysetReader.UTF_8 = Charset.forName("UTF-8");
    }

    private JsonKeysetReader(InputStream inputStream0) {
        this.urlSafeBase64 = false;
        this.inputStream = inputStream0;
    }

    private EncryptedKeyset encryptedKeysetFromJson(JsonObject jsonObject0) throws IOException {
        JsonKeysetReader.validateEncryptedKeyset(jsonObject0);
        byte[] arr_b = this.urlSafeBase64 ? Base64.urlSafeDecode(jsonObject0.get("encryptedKeyset").getAsString()) : Base64.decode(jsonObject0.get("encryptedKeyset").getAsString());
        return jsonObject0.has("keysetInfo") ? ((EncryptedKeyset)EncryptedKeyset.newBuilder().setEncryptedKeyset(ByteString.copyFrom(arr_b)).setKeysetInfo(JsonKeysetReader.keysetInfoFromJson(jsonObject0.getAsJsonObject("keysetInfo"))).build()) : ((EncryptedKeyset)EncryptedKeyset.newBuilder().setEncryptedKeyset(ByteString.copyFrom(arr_b)).build());
    }

    private static int getKeyId(JsonElement jsonElement0) throws IOException {
        try {
            long v = JsonParser.getParsedNumberAsLongOrThrow(jsonElement0);
        }
        catch(NumberFormatException numberFormatException0) {
            throw new IOException(numberFormatException0);
        }
        if(v > 0xFFFFFFFFL || v < 0xFFFFFFFF80000000L) {
            throw new IOException("invalid key id");
        }
        return (int)jsonElement0.getAsLong();
    }

    private static KeyMaterialType getKeyMaterialType(String s) {
        s.hashCode();
        switch(s) {
            case "ASYMMETRIC_PRIVATE": {
                return KeyMaterialType.ASYMMETRIC_PRIVATE;
            }
            case "ASYMMETRIC_PUBLIC": {
                return KeyMaterialType.ASYMMETRIC_PUBLIC;
            }
            case "REMOTE": {
                return KeyMaterialType.REMOTE;
            }
            case "SYMMETRIC": {
                return KeyMaterialType.SYMMETRIC;
            }
            default: {
                throw new JsonParseException("unknown key material type: " + s);
            }
        }
    }

    private static OutputPrefixType getOutputPrefixType(String s) {
        s.hashCode();
        switch(s) {
            case "CRUNCHY": {
                return OutputPrefixType.CRUNCHY;
            }
            case "LEGACY": {
                return OutputPrefixType.LEGACY;
            }
            case "RAW": {
                return OutputPrefixType.RAW;
            }
            case "TINK": {
                return OutputPrefixType.TINK;
            }
            default: {
                throw new JsonParseException("unknown output prefix type: " + s);
            }
        }
    }

    private static KeyStatusType getStatus(String s) {
        s.hashCode();
        switch(s) {
            case "DESTROYED": {
                return KeyStatusType.DESTROYED;
            }
            case "DISABLED": {
                return KeyStatusType.DISABLED;
            }
            case "ENABLED": {
                return KeyStatusType.ENABLED;
            }
            default: {
                throw new JsonParseException("unknown status: " + s);
            }
        }
    }

    private KeyData keyDataFromJson(JsonObject jsonObject0) {
        byte[] arr_b;
        JsonKeysetReader.validateKeyData(jsonObject0);
        if(this.urlSafeBase64) {
            arr_b = Base64.urlSafeDecode(jsonObject0.get("value").getAsString());
            return (KeyData)KeyData.newBuilder().setTypeUrl(jsonObject0.get("typeUrl").getAsString()).setValue(ByteString.copyFrom(arr_b)).setKeyMaterialType(JsonKeysetReader.getKeyMaterialType(jsonObject0.get("keyMaterialType").getAsString())).build();
        }
        arr_b = Base64.decode(jsonObject0.get("value").getAsString());
        return (KeyData)KeyData.newBuilder().setTypeUrl(jsonObject0.get("typeUrl").getAsString()).setValue(ByteString.copyFrom(arr_b)).setKeyMaterialType(JsonKeysetReader.getKeyMaterialType(jsonObject0.get("keyMaterialType").getAsString())).build();
    }

    private Key keyFromJson(JsonObject jsonObject0) throws IOException {
        JsonKeysetReader.validateKey(jsonObject0);
        return (Key)Key.newBuilder().setStatus(JsonKeysetReader.getStatus(jsonObject0.get("status").getAsString())).setKeyId(JsonKeysetReader.getKeyId(jsonObject0.get("keyId"))).setOutputPrefixType(JsonKeysetReader.getOutputPrefixType(jsonObject0.get("outputPrefixType").getAsString())).setKeyData(this.keyDataFromJson(jsonObject0.getAsJsonObject("keyData"))).build();
    }

    private static KeyInfo keyInfoFromJson(JsonObject jsonObject0) throws IOException {
        return (KeyInfo)KeyInfo.newBuilder().setStatus(JsonKeysetReader.getStatus(jsonObject0.get("status").getAsString())).setKeyId(JsonKeysetReader.getKeyId(jsonObject0.get("keyId"))).setOutputPrefixType(JsonKeysetReader.getOutputPrefixType(jsonObject0.get("outputPrefixType").getAsString())).setTypeUrl(jsonObject0.get("typeUrl").getAsString()).build();
    }

    private Keyset keysetFromJson(JsonObject jsonObject0) throws IOException {
        JsonKeysetReader.validateKeyset(jsonObject0);
        Builder keyset$Builder0 = Keyset.newBuilder();
        if(jsonObject0.has("primaryKeyId")) {
            keyset$Builder0.setPrimaryKeyId(JsonKeysetReader.getKeyId(jsonObject0.get("primaryKeyId")));
        }
        JsonArray jsonArray0 = jsonObject0.getAsJsonArray("key");
        for(int v = 0; v < jsonArray0.size(); ++v) {
            keyset$Builder0.addKey(this.keyFromJson(jsonArray0.get(v).getAsJsonObject()));
        }
        return (Keyset)keyset$Builder0.build();
    }

    private static KeysetInfo keysetInfoFromJson(JsonObject jsonObject0) throws IOException {
        com.google.crypto.tink.proto.KeysetInfo.Builder keysetInfo$Builder0 = KeysetInfo.newBuilder();
        if(jsonObject0.has("primaryKeyId")) {
            keysetInfo$Builder0.setPrimaryKeyId(JsonKeysetReader.getKeyId(jsonObject0.get("primaryKeyId")));
        }
        if(jsonObject0.has("keyInfo")) {
            JsonArray jsonArray0 = jsonObject0.getAsJsonArray("keyInfo");
            for(int v = 0; v < jsonArray0.size(); ++v) {
                keysetInfo$Builder0.addKeyInfo(JsonKeysetReader.keyInfoFromJson(jsonArray0.get(v).getAsJsonObject()));
            }
        }
        return (KeysetInfo)keysetInfo$Builder0.build();
    }

    @Override  // com.google.crypto.tink.KeysetReader
    public Keyset read() throws IOException {
        InputStream inputStream0;
        Keyset keyset0;
        try {
            try {
                keyset0 = this.keysetFromJson(JsonParser.parse(new String(Util.readAll(this.inputStream), JsonKeysetReader.UTF_8)).getAsJsonObject());
                inputStream0 = this.inputStream;
            }
            catch(JsonParseException | IllegalStateException jsonParseException0) {
                throw new IOException(jsonParseException0);
            }
        }
        catch(Throwable throwable0) {
            InputStream inputStream1 = this.inputStream;
            if(inputStream1 != null) {
                inputStream1.close();
            }
            throw throwable0;
        }
        if(inputStream0 != null) {
            inputStream0.close();
        }
        return keyset0;
    }

    @Override  // com.google.crypto.tink.KeysetReader
    public EncryptedKeyset readEncrypted() throws IOException {
        InputStream inputStream0;
        EncryptedKeyset encryptedKeyset0;
        try {
            try {
                encryptedKeyset0 = this.encryptedKeysetFromJson(JsonParser.parse(new String(Util.readAll(this.inputStream), JsonKeysetReader.UTF_8)).getAsJsonObject());
                inputStream0 = this.inputStream;
            }
            catch(JsonParseException | IllegalStateException jsonParseException0) {
                throw new IOException(jsonParseException0);
            }
        }
        catch(Throwable throwable0) {
            InputStream inputStream1 = this.inputStream;
            if(inputStream1 != null) {
                inputStream1.close();
            }
            throw throwable0;
        }
        if(inputStream0 != null) {
            inputStream0.close();
        }
        return encryptedKeyset0;
    }

    private static void validateEncryptedKeyset(JsonObject jsonObject0) {
        if(!jsonObject0.has("encryptedKeyset")) {
            throw new JsonParseException("invalid encrypted keyset");
        }
    }

    private static void validateKey(JsonObject jsonObject0) {
        if(!jsonObject0.has("keyData") || !jsonObject0.has("status") || !jsonObject0.has("keyId") || !jsonObject0.has("outputPrefixType")) {
            throw new JsonParseException("invalid key");
        }
    }

    private static void validateKeyData(JsonObject jsonObject0) {
        if(!jsonObject0.has("typeUrl") || !jsonObject0.has("value") || !jsonObject0.has("keyMaterialType")) {
            throw new JsonParseException("invalid keyData");
        }
    }

    private static void validateKeyset(JsonObject jsonObject0) {
        if(!jsonObject0.has("key") || jsonObject0.getAsJsonArray("key").size() == 0) {
            throw new JsonParseException("invalid keyset");
        }
    }

    public static JsonKeysetReader withBytes(byte[] arr_b) {
        return new JsonKeysetReader(new ByteArrayInputStream(arr_b));
    }

    @Deprecated
    public static JsonKeysetReader withFile(File file0) throws IOException {
        return JsonKeysetReader.withInputStream(new FileInputStream(file0));
    }

    public static JsonKeysetReader withInputStream(InputStream inputStream0) throws IOException {
        return new JsonKeysetReader(inputStream0);
    }

    @Deprecated
    public static JsonKeysetReader withJsonObject(Object object0) {
        return JsonKeysetReader.withString(object0.toString());
    }

    @Deprecated
    public static JsonKeysetReader withPath(String s) throws IOException {
        return JsonKeysetReader.withInputStream(new FileInputStream(new File(s)));
    }

    @Deprecated
    public static JsonKeysetReader withPath(Path path0) throws IOException {
        return JsonKeysetReader.withInputStream(new FileInputStream(LinkFollowing..ExternalSyntheticApiModelOutline0.m(path0)));
    }

    public static JsonKeysetReader withString(String s) {
        return new JsonKeysetReader(new ByteArrayInputStream(s.getBytes(JsonKeysetReader.UTF_8)));
    }

    public JsonKeysetReader withUrlSafeBase64() {
        this.urlSafeBase64 = true;
        return this;
    }
}

