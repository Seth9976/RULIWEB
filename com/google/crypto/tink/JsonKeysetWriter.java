package com.google.crypto.tink;

import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.Keyset.Key;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.proto.KeysetInfo.KeyInfo;
import com.google.crypto.tink.proto.KeysetInfo;
import com.google.crypto.tink.subtle.Base64;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Path;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;

public final class JsonKeysetWriter implements KeysetWriter {
    private static final Charset UTF_8;
    private final OutputStream outputStream;

    static {
        JsonKeysetWriter.UTF_8 = Charset.forName("UTF-8");
    }

    private JsonKeysetWriter(OutputStream outputStream0) {
        this.outputStream = outputStream0;
    }

    private JsonObject toJson(EncryptedKeyset encryptedKeyset0) {
        JsonObject jsonObject0 = new JsonObject();
        jsonObject0.addProperty("encryptedKeyset", Base64.encode(encryptedKeyset0.getEncryptedKeyset().toByteArray()));
        jsonObject0.add("keysetInfo", this.toJson(encryptedKeyset0.getKeysetInfo()));
        return jsonObject0;
    }

    private JsonObject toJson(KeyData keyData0) {
        JsonObject jsonObject0 = new JsonObject();
        jsonObject0.addProperty("typeUrl", "");
        jsonObject0.addProperty("value", Base64.encode(keyData0.getValue().toByteArray()));
        jsonObject0.addProperty("keyMaterialType", keyData0.getKeyMaterialType().name());
        return jsonObject0;
    }

    private JsonObject toJson(Key keyset$Key0) {
        JsonObject jsonObject0 = new JsonObject();
        jsonObject0.add("keyData", this.toJson(keyset$Key0.getKeyData()));
        jsonObject0.addProperty("status", keyset$Key0.getStatus().name());
        jsonObject0.addProperty("keyId", ((long)(((long)keyset$Key0.getKeyId()) & 0xFFFFFFFFL)));
        jsonObject0.addProperty("outputPrefixType", keyset$Key0.getOutputPrefixType().name());
        return jsonObject0;
    }

    private JsonObject toJson(Keyset keyset0) {
        JsonObject jsonObject0 = new JsonObject();
        jsonObject0.addProperty("primaryKeyId", this.toUnsignedLong(keyset0.getPrimaryKeyId()));
        JsonArray jsonArray0 = new JsonArray();
        for(Object object0: keyset0.getKeyList()) {
            jsonArray0.add(this.toJson(((Key)object0)));
        }
        jsonObject0.add("key", jsonArray0);
        return jsonObject0;
    }

    private JsonObject toJson(KeyInfo keysetInfo$KeyInfo0) {
        JsonObject jsonObject0 = new JsonObject();
        jsonObject0.addProperty("typeUrl", "");
        jsonObject0.addProperty("status", keysetInfo$KeyInfo0.getStatus().name());
        jsonObject0.addProperty("keyId", ((long)(((long)keysetInfo$KeyInfo0.getKeyId()) & 0xFFFFFFFFL)));
        jsonObject0.addProperty("outputPrefixType", keysetInfo$KeyInfo0.getOutputPrefixType().name());
        return jsonObject0;
    }

    private JsonObject toJson(KeysetInfo keysetInfo0) {
        JsonObject jsonObject0 = new JsonObject();
        jsonObject0.addProperty("primaryKeyId", this.toUnsignedLong(keysetInfo0.getPrimaryKeyId()));
        JsonArray jsonArray0 = new JsonArray();
        for(Object object0: keysetInfo0.getKeyInfoList()) {
            jsonArray0.add(this.toJson(((KeyInfo)object0)));
        }
        jsonObject0.add("keyInfo", jsonArray0);
        return jsonObject0;
    }

    private long toUnsignedLong(int x) [...] // Inlined contents

    @Deprecated
    public static KeysetWriter withFile(File file0) throws IOException {
        return JsonKeysetWriter.withOutputStream(new FileOutputStream(file0));
    }

    public static KeysetWriter withOutputStream(OutputStream outputStream0) {
        return new JsonKeysetWriter(outputStream0);
    }

    @Deprecated
    public static KeysetWriter withPath(String s) throws IOException {
        return JsonKeysetWriter.withOutputStream(new FileOutputStream(new File(s)));
    }

    @Deprecated
    public static KeysetWriter withPath(Path path0) throws IOException {
        return JsonKeysetWriter.withOutputStream(new FileOutputStream(LinkFollowing..ExternalSyntheticApiModelOutline0.m(path0)));
    }

    @Override  // com.google.crypto.tink.KeysetWriter
    public void write(EncryptedKeyset encryptedKeyset0) throws IOException {
        byte[] arr_b = this.toJson(encryptedKeyset0).toString().getBytes(JsonKeysetWriter.UTF_8);
        this.outputStream.write(arr_b);
        this.outputStream.write("\r\n".getBytes(JsonKeysetWriter.UTF_8));
        this.outputStream.close();
    }

    @Override  // com.google.crypto.tink.KeysetWriter
    public void write(Keyset keyset0) throws IOException {
        try {
            byte[] arr_b = this.toJson(keyset0).toString().getBytes(JsonKeysetWriter.UTF_8);
            this.outputStream.write(arr_b);
            this.outputStream.write("\r\n".getBytes(JsonKeysetWriter.UTF_8));
        }
        catch(JsonParseException jsonParseException0) {
            throw new IOException(jsonParseException0);
        }
        finally {
            this.outputStream.close();
        }
    }
}

