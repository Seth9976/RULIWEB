package com.google.crypto.tink.jwt;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.KeyStatus;
import com.google.crypto.tink.KeysetHandle.Entry;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.internal.LegacyProtoKey;
import com.google.crypto.tink.internal.ProtoKeySerialization;
import com.google.crypto.tink.proto.JwtEcdsaAlgorithm;
import com.google.crypto.tink.proto.JwtEcdsaPublicKey.Builder;
import com.google.crypto.tink.proto.JwtEcdsaPublicKey.CustomKid;
import com.google.crypto.tink.proto.JwtEcdsaPublicKey;
import com.google.crypto.tink.proto.JwtRsaSsaPkcs1Algorithm;
import com.google.crypto.tink.proto.JwtRsaSsaPkcs1PublicKey;
import com.google.crypto.tink.proto.JwtRsaSsaPssAlgorithm;
import com.google.crypto.tink.proto.JwtRsaSsaPssPublicKey;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.Base64;
import com.google.crypto.tink.tinkkey.KeyAccess;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Optional;
import javax.annotation.Nullable;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;

public final class JwkSetConverter {
    private static final String JWT_ECDSA_PUBLIC_KEY_URL = "type.googleapis.com/google.crypto.tink.JwtEcdsaPublicKey";
    private static final String JWT_RSA_SSA_PKCS1_PUBLIC_KEY_URL = "type.googleapis.com/google.crypto.tink.JwtRsaSsaPkcs1PublicKey";
    private static final String JWT_RSA_SSA_PSS_PUBLIC_KEY_URL = "type.googleapis.com/google.crypto.tink.JwtRsaSsaPssPublicKey";

    private static JsonObject convertJwtEcdsaKey(ProtoKeySerialization protoKeySerialization0) throws GeneralSecurityException {
        String s1;
        String s;
        JwtEcdsaPublicKey jwtEcdsaPublicKey0;
        try {
            jwtEcdsaPublicKey0 = JwtEcdsaPublicKey.parseFrom(protoKeySerialization0.getValue(), ExtensionRegistryLite.getEmptyRegistry());
        }
        catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
            throw new GeneralSecurityException("failed to parse value as JwtEcdsaPublicKey proto", invalidProtocolBufferException0);
        }
        switch(com.google.crypto.tink.jwt.JwkSetConverter.1.$SwitchMap$com$google$crypto$tink$proto$JwtEcdsaAlgorithm[jwtEcdsaPublicKey0.getAlgorithm().ordinal()]) {
            case 1: {
                s = "ES256";
                s1 = "P-256";
                break;
            }
            case 2: {
                s = "ES384";
                s1 = "P-384";
                break;
            }
            case 3: {
                s = "ES512";
                s1 = "P-521";
                break;
            }
            default: {
                throw new GeneralSecurityException("unknown algorithm");
            }
        }
        JsonObject jsonObject0 = new JsonObject();
        jsonObject0.addProperty("kty", "EC");
        jsonObject0.addProperty("crv", s1);
        jsonObject0.addProperty("x", Base64.urlSafeEncode(jwtEcdsaPublicKey0.getX().toByteArray()));
        jsonObject0.addProperty("y", Base64.urlSafeEncode(jwtEcdsaPublicKey0.getY().toByteArray()));
        jsonObject0.addProperty("use", "sig");
        jsonObject0.addProperty("alg", s);
        JsonArray jsonArray0 = new JsonArray();
        jsonArray0.add("verify");
        jsonObject0.add("key_ops", jsonArray0);
        Optional optional0 = JwkSetConverter.getKid(protoKeySerialization0.getIdRequirementOrNull());
        if(LinkFollowing..ExternalSyntheticApiModelOutline0.m(optional0)) {
            jsonObject0.addProperty("kid", ((String)LinkFollowing..ExternalSyntheticApiModelOutline0.m(optional0)));
            return jsonObject0;
        }
        if(jwtEcdsaPublicKey0.hasCustomKid()) {
            jsonObject0.addProperty("kid", "");
        }
        return jsonObject0;
    }

    private static JsonObject convertJwtRsaSsaPkcs1(ProtoKeySerialization protoKeySerialization0) throws GeneralSecurityException {
        String s;
        JwtRsaSsaPkcs1PublicKey jwtRsaSsaPkcs1PublicKey0;
        try {
            jwtRsaSsaPkcs1PublicKey0 = JwtRsaSsaPkcs1PublicKey.parseFrom(protoKeySerialization0.getValue(), ExtensionRegistryLite.getEmptyRegistry());
        }
        catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
            throw new GeneralSecurityException("failed to parse value as JwtRsaSsaPkcs1PublicKey proto", invalidProtocolBufferException0);
        }
        switch(com.google.crypto.tink.jwt.JwkSetConverter.1.$SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPkcs1Algorithm[jwtRsaSsaPkcs1PublicKey0.getAlgorithm().ordinal()]) {
            case 1: {
                s = "RS256";
                break;
            }
            case 2: {
                s = "RS384";
                break;
            }
            case 3: {
                s = "RS512";
                break;
            }
            default: {
                throw new GeneralSecurityException("unknown algorithm");
            }
        }
        JsonObject jsonObject0 = new JsonObject();
        jsonObject0.addProperty("kty", "RSA");
        jsonObject0.addProperty("n", Base64.urlSafeEncode(jwtRsaSsaPkcs1PublicKey0.getN().toByteArray()));
        jsonObject0.addProperty("e", Base64.urlSafeEncode(jwtRsaSsaPkcs1PublicKey0.getE().toByteArray()));
        jsonObject0.addProperty("use", "sig");
        jsonObject0.addProperty("alg", s);
        JsonArray jsonArray0 = new JsonArray();
        jsonArray0.add("verify");
        jsonObject0.add("key_ops", jsonArray0);
        Optional optional0 = JwkSetConverter.getKid(protoKeySerialization0.getIdRequirementOrNull());
        if(LinkFollowing..ExternalSyntheticApiModelOutline0.m(optional0)) {
            jsonObject0.addProperty("kid", ((String)LinkFollowing..ExternalSyntheticApiModelOutline0.m(optional0)));
            return jsonObject0;
        }
        if(jwtRsaSsaPkcs1PublicKey0.hasCustomKid()) {
            jsonObject0.addProperty("kid", "");
        }
        return jsonObject0;
    }

    private static JsonObject convertJwtRsaSsaPss(ProtoKeySerialization protoKeySerialization0) throws GeneralSecurityException {
        String s;
        JwtRsaSsaPssPublicKey jwtRsaSsaPssPublicKey0;
        try {
            jwtRsaSsaPssPublicKey0 = JwtRsaSsaPssPublicKey.parseFrom(protoKeySerialization0.getValue(), ExtensionRegistryLite.getEmptyRegistry());
        }
        catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
            throw new GeneralSecurityException("failed to parse value as JwtRsaSsaPssPublicKey proto", invalidProtocolBufferException0);
        }
        switch(com.google.crypto.tink.jwt.JwkSetConverter.1.$SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPssAlgorithm[jwtRsaSsaPssPublicKey0.getAlgorithm().ordinal()]) {
            case 1: {
                s = "PS256";
                break;
            }
            case 2: {
                s = "PS384";
                break;
            }
            case 3: {
                s = "PS512";
                break;
            }
            default: {
                throw new GeneralSecurityException("unknown algorithm");
            }
        }
        JsonObject jsonObject0 = new JsonObject();
        jsonObject0.addProperty("kty", "RSA");
        jsonObject0.addProperty("n", Base64.urlSafeEncode(jwtRsaSsaPssPublicKey0.getN().toByteArray()));
        jsonObject0.addProperty("e", Base64.urlSafeEncode(jwtRsaSsaPssPublicKey0.getE().toByteArray()));
        jsonObject0.addProperty("use", "sig");
        jsonObject0.addProperty("alg", s);
        JsonArray jsonArray0 = new JsonArray();
        jsonArray0.add("verify");
        jsonObject0.add("key_ops", jsonArray0);
        Optional optional0 = JwkSetConverter.getKid(protoKeySerialization0.getIdRequirementOrNull());
        if(LinkFollowing..ExternalSyntheticApiModelOutline0.m(optional0)) {
            jsonObject0.addProperty("kid", ((String)LinkFollowing..ExternalSyntheticApiModelOutline0.m(optional0)));
            return jsonObject0;
        }
        if(jwtRsaSsaPssPublicKey0.hasCustomKid()) {
            jsonObject0.addProperty("kid", "");
        }
        return jsonObject0;
    }

    private static ProtoKeySerialization convertToEcdsaKey(JsonObject jsonObject0) throws GeneralSecurityException {
        JwtEcdsaAlgorithm jwtEcdsaAlgorithm0;
        String s = JwkSetConverter.getStringItem(jsonObject0, "alg");
        s.hashCode();
        switch(s) {
            case "ES256": {
                JwkSetConverter.expectStringItem(jsonObject0, "crv", "P-256");
                jwtEcdsaAlgorithm0 = JwtEcdsaAlgorithm.ES256;
                break;
            }
            case "ES384": {
                JwkSetConverter.expectStringItem(jsonObject0, "crv", "P-384");
                jwtEcdsaAlgorithm0 = JwtEcdsaAlgorithm.ES384;
                break;
            }
            case "ES512": {
                JwkSetConverter.expectStringItem(jsonObject0, "crv", "P-521");
                jwtEcdsaAlgorithm0 = JwtEcdsaAlgorithm.ES512;
                break;
            }
            default: {
                throw new GeneralSecurityException("Unknown Ecdsa Algorithm: " + JwkSetConverter.getStringItem(jsonObject0, "alg"));
            }
        }
        if(jsonObject0.has("d")) {
            throw new UnsupportedOperationException("importing ECDSA private keys is not implemented");
        }
        JwkSetConverter.expectStringItem(jsonObject0, "kty", "EC");
        JwkSetConverter.validateUseIsSig(jsonObject0);
        JwkSetConverter.validateKeyOpsIsVerify(jsonObject0);
        Builder jwtEcdsaPublicKey$Builder0 = JwtEcdsaPublicKey.newBuilder().setVersion(0).setAlgorithm(jwtEcdsaAlgorithm0).setX(ByteString.copyFrom(Base64.urlSafeDecode(JwkSetConverter.getStringItem(jsonObject0, "x")))).setY(ByteString.copyFrom(Base64.urlSafeDecode(JwkSetConverter.getStringItem(jsonObject0, "y"))));
        if(jsonObject0.has("kid")) {
            jwtEcdsaPublicKey$Builder0.setCustomKid(((CustomKid)CustomKid.newBuilder().setValue(JwkSetConverter.getStringItem(jsonObject0, "kid")).build()));
        }
        return ProtoKeySerialization.create("type.googleapis.com/google.crypto.tink.JwtEcdsaPublicKey", ((JwtEcdsaPublicKey)jwtEcdsaPublicKey$Builder0.build()).toByteString(), KeyMaterialType.ASYMMETRIC_PUBLIC, OutputPrefixType.RAW, null);
    }

    private static ProtoKeySerialization convertToRsaSsaPkcs1Key(JsonObject jsonObject0) throws GeneralSecurityException {
        JwtRsaSsaPkcs1Algorithm jwtRsaSsaPkcs1Algorithm0;
        String s = JwkSetConverter.getStringItem(jsonObject0, "alg");
        s.hashCode();
        switch(s) {
            case "RS256": {
                jwtRsaSsaPkcs1Algorithm0 = JwtRsaSsaPkcs1Algorithm.RS256;
                break;
            }
            case "RS384": {
                jwtRsaSsaPkcs1Algorithm0 = JwtRsaSsaPkcs1Algorithm.RS384;
                break;
            }
            case "RS512": {
                jwtRsaSsaPkcs1Algorithm0 = JwtRsaSsaPkcs1Algorithm.RS512;
                break;
            }
            default: {
                throw new GeneralSecurityException("Unknown Rsa Algorithm: " + JwkSetConverter.getStringItem(jsonObject0, "alg"));
            }
        }
        if(jsonObject0.has("p") || jsonObject0.has("q") || jsonObject0.has("dp") || jsonObject0.has("dq") || jsonObject0.has("d") || jsonObject0.has("qi")) {
            throw new UnsupportedOperationException("importing RSA private keys is not implemented");
        }
        JwkSetConverter.expectStringItem(jsonObject0, "kty", "RSA");
        JwkSetConverter.validateUseIsSig(jsonObject0);
        JwkSetConverter.validateKeyOpsIsVerify(jsonObject0);
        com.google.crypto.tink.proto.JwtRsaSsaPkcs1PublicKey.Builder jwtRsaSsaPkcs1PublicKey$Builder0 = JwtRsaSsaPkcs1PublicKey.newBuilder().setVersion(0).setAlgorithm(jwtRsaSsaPkcs1Algorithm0).setE(ByteString.copyFrom(Base64.urlSafeDecode(JwkSetConverter.getStringItem(jsonObject0, "e")))).setN(ByteString.copyFrom(Base64.urlSafeDecode(JwkSetConverter.getStringItem(jsonObject0, "n"))));
        if(jsonObject0.has("kid")) {
            jwtRsaSsaPkcs1PublicKey$Builder0.setCustomKid(((com.google.crypto.tink.proto.JwtRsaSsaPkcs1PublicKey.CustomKid)com.google.crypto.tink.proto.JwtRsaSsaPkcs1PublicKey.CustomKid.newBuilder().setValue(JwkSetConverter.getStringItem(jsonObject0, "kid")).build()));
        }
        return ProtoKeySerialization.create("type.googleapis.com/google.crypto.tink.JwtRsaSsaPkcs1PublicKey", ((JwtRsaSsaPkcs1PublicKey)jwtRsaSsaPkcs1PublicKey$Builder0.build()).toByteString(), KeyMaterialType.ASYMMETRIC_PUBLIC, OutputPrefixType.RAW, null);
    }

    private static ProtoKeySerialization convertToRsaSsaPssKey(JsonObject jsonObject0) throws GeneralSecurityException {
        JwtRsaSsaPssAlgorithm jwtRsaSsaPssAlgorithm0;
        String s = JwkSetConverter.getStringItem(jsonObject0, "alg");
        s.hashCode();
        switch(s) {
            case "PS256": {
                jwtRsaSsaPssAlgorithm0 = JwtRsaSsaPssAlgorithm.PS256;
                break;
            }
            case "PS384": {
                jwtRsaSsaPssAlgorithm0 = JwtRsaSsaPssAlgorithm.PS384;
                break;
            }
            case "PS512": {
                jwtRsaSsaPssAlgorithm0 = JwtRsaSsaPssAlgorithm.PS512;
                break;
            }
            default: {
                throw new GeneralSecurityException("Unknown Rsa Algorithm: " + JwkSetConverter.getStringItem(jsonObject0, "alg"));
            }
        }
        if(jsonObject0.has("p") || jsonObject0.has("q") || jsonObject0.has("dq") || jsonObject0.has("dq") || jsonObject0.has("d") || jsonObject0.has("qi")) {
            throw new UnsupportedOperationException("importing RSA private keys is not implemented");
        }
        JwkSetConverter.expectStringItem(jsonObject0, "kty", "RSA");
        JwkSetConverter.validateUseIsSig(jsonObject0);
        JwkSetConverter.validateKeyOpsIsVerify(jsonObject0);
        com.google.crypto.tink.proto.JwtRsaSsaPssPublicKey.Builder jwtRsaSsaPssPublicKey$Builder0 = JwtRsaSsaPssPublicKey.newBuilder().setVersion(0).setAlgorithm(jwtRsaSsaPssAlgorithm0).setE(ByteString.copyFrom(Base64.urlSafeDecode(JwkSetConverter.getStringItem(jsonObject0, "e")))).setN(ByteString.copyFrom(Base64.urlSafeDecode(JwkSetConverter.getStringItem(jsonObject0, "n"))));
        if(jsonObject0.has("kid")) {
            jwtRsaSsaPssPublicKey$Builder0.setCustomKid(((com.google.crypto.tink.proto.JwtRsaSsaPssPublicKey.CustomKid)com.google.crypto.tink.proto.JwtRsaSsaPssPublicKey.CustomKid.newBuilder().setValue(JwkSetConverter.getStringItem(jsonObject0, "kid")).build()));
        }
        return ProtoKeySerialization.create("type.googleapis.com/google.crypto.tink.JwtRsaSsaPssPublicKey", ((JwtRsaSsaPssPublicKey)jwtRsaSsaPssPublicKey$Builder0.build()).toByteString(), KeyMaterialType.ASYMMETRIC_PUBLIC, OutputPrefixType.RAW, null);
    }

    private static void expectStringItem(JsonObject jsonObject0, String s, String s1) throws GeneralSecurityException {
        String s2 = JwkSetConverter.getStringItem(jsonObject0, s);
        if(!s2.equals(s1)) {
            throw new GeneralSecurityException("unexpected " + s + " value: " + s2);
        }
    }

    @Deprecated
    public static String fromKeysetHandle(KeysetHandle keysetHandle0, KeyAccess keyAccess0) throws IOException, GeneralSecurityException {
        return JwkSetConverter.fromPublicKeysetHandle(keysetHandle0);
    }

    public static String fromPublicKeysetHandle(KeysetHandle keysetHandle0) throws IOException, GeneralSecurityException {
        JsonArray jsonArray0 = new JsonArray();
        for(int v = 0; v < keysetHandle0.size(); ++v) {
            Entry keysetHandle$Entry0 = keysetHandle0.getAt(v);
            if(keysetHandle$Entry0.getStatus() == KeyStatus.ENABLED) {
                Key key0 = keysetHandle$Entry0.getKey();
                if(!(key0 instanceof LegacyProtoKey)) {
                    throw new GeneralSecurityException("only LegacyProtoKey is currently supported");
                }
                ProtoKeySerialization protoKeySerialization0 = ((LegacyProtoKey)key0).getSerialization(null);
                if(protoKeySerialization0.getOutputPrefixType() != OutputPrefixType.RAW && protoKeySerialization0.getOutputPrefixType() != OutputPrefixType.TINK) {
                    throw new GeneralSecurityException("only OutputPrefixType RAW and TINK are supported");
                }
                if(protoKeySerialization0.getKeyMaterialType() != KeyMaterialType.ASYMMETRIC_PUBLIC) {
                    throw new GeneralSecurityException("only public keys can be converted");
                }
                String s = protoKeySerialization0.getTypeUrl();
                s.hashCode();
                switch(s) {
                    case "type.googleapis.com/google.crypto.tink.JwtEcdsaPublicKey": {
                        jsonArray0.add(JwkSetConverter.convertJwtEcdsaKey(protoKeySerialization0));
                        break;
                    }
                    case "type.googleapis.com/google.crypto.tink.JwtRsaSsaPkcs1PublicKey": {
                        jsonArray0.add(JwkSetConverter.convertJwtRsaSsaPkcs1(protoKeySerialization0));
                        break;
                    }
                    case "type.googleapis.com/google.crypto.tink.JwtRsaSsaPssPublicKey": {
                        jsonArray0.add(JwkSetConverter.convertJwtRsaSsaPss(protoKeySerialization0));
                        break;
                    }
                    default: {
                        throw new GeneralSecurityException(String.format("key type %s is not supported", protoKeySerialization0.getTypeUrl()));
                    }
                }
                continue;
            }
        }
        JsonObject jsonObject0 = new JsonObject();
        jsonObject0.add("keys", jsonArray0);
        return jsonObject0.toString();
    }

    private static Optional getKid(@Nullable Integer integer0) {
        return integer0 == null ? LinkFollowing..ExternalSyntheticApiModelOutline0.m() : LinkFollowing..ExternalSyntheticApiModelOutline0.m(Base64.urlSafeEncode(ByteBuffer.allocate(4).putInt(((int)integer0)).array()));
    }

    private static String getStringItem(JsonObject jsonObject0, String s) throws GeneralSecurityException {
        if(!jsonObject0.has(s)) {
            throw new GeneralSecurityException(s + " not found");
        }
        if(!jsonObject0.get(s).isJsonPrimitive() || !jsonObject0.get(s).getAsJsonPrimitive().isString()) {
            throw new GeneralSecurityException(s + " is not a string");
        }
        return jsonObject0.get(s).getAsString();
    }

    @Deprecated
    public static KeysetHandle toKeysetHandle(String s, KeyAccess keyAccess0) throws IOException, GeneralSecurityException {
        return JwkSetConverter.toPublicKeysetHandle(s);
    }

    public static KeysetHandle toPublicKeysetHandle(String s) throws IOException, GeneralSecurityException {
        ProtoKeySerialization protoKeySerialization0;
        try {
            JsonReader jsonReader0 = new JsonReader(new StringReader(s));
            jsonReader0.setLenient(false);
            JsonObject jsonObject0 = Streams.parse(jsonReader0).getAsJsonObject();
        }
        catch(IllegalStateException | JsonParseException | StackOverflowError throwable0) {
            throw new GeneralSecurityException("JWK set is invalid JSON", throwable0);
        }
        com.google.crypto.tink.KeysetHandle.Builder keysetHandle$Builder0 = KeysetHandle.newBuilder();
        for(Object object0: jsonObject0.get("keys").getAsJsonArray()) {
            JsonObject jsonObject1 = ((JsonElement)object0).getAsJsonObject();
            String s1 = JwkSetConverter.getStringItem(jsonObject1, "alg").substring(0, 2);
            s1.hashCode();
            switch(s1) {
                case "ES": {
                    protoKeySerialization0 = JwkSetConverter.convertToEcdsaKey(jsonObject1);
                    break;
                }
                case "PS": {
                    protoKeySerialization0 = JwkSetConverter.convertToRsaSsaPssKey(jsonObject1);
                    break;
                }
                case "RS": {
                    protoKeySerialization0 = JwkSetConverter.convertToRsaSsaPkcs1Key(jsonObject1);
                    break;
                }
                default: {
                    throw new GeneralSecurityException("unexpected alg value: " + JwkSetConverter.getStringItem(jsonObject1, "alg"));
                }
            }
            keysetHandle$Builder0.addEntry(KeysetHandle.importKey(new LegacyProtoKey(protoKeySerialization0, null)).withRandomId());
        }
        if(keysetHandle$Builder0.size() <= 0) {
            throw new GeneralSecurityException("empty keyset");
        }
        keysetHandle$Builder0.getAt(0).makePrimary();
        return keysetHandle$Builder0.build();
    }

    private static void validateKeyOpsIsVerify(JsonObject jsonObject0) throws GeneralSecurityException {
        if(jsonObject0.has("key_ops")) {
            if(!jsonObject0.get("key_ops").isJsonArray()) {
                throw new GeneralSecurityException("key_ops is not an array");
            }
            JsonArray jsonArray0 = jsonObject0.get("key_ops").getAsJsonArray();
            if(jsonArray0.size() != 1) {
                throw new GeneralSecurityException("key_ops must contain exactly one element");
            }
            if(!jsonArray0.get(0).isJsonPrimitive() || !jsonArray0.get(0).getAsJsonPrimitive().isString()) {
                throw new GeneralSecurityException("key_ops is not a string");
            }
            if(!jsonArray0.get(0).getAsString().equals("verify")) {
                throw new GeneralSecurityException("unexpected keyOps value: " + jsonArray0.get(0).getAsString());
            }
        }
    }

    private static void validateUseIsSig(JsonObject jsonObject0) throws GeneralSecurityException {
        if(!jsonObject0.has("use")) {
            return;
        }
        JwkSetConverter.expectStringItem(jsonObject0, "use", "sig");
    }

    class com.google.crypto.tink.jwt.JwkSetConverter.1 {
        static final int[] $SwitchMap$com$google$crypto$tink$proto$JwtEcdsaAlgorithm;
        static final int[] $SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPkcs1Algorithm;
        static final int[] $SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPssAlgorithm;

        static {
            int[] arr_v = new int[JwtRsaSsaPssAlgorithm.values().length];
            com.google.crypto.tink.jwt.JwkSetConverter.1.$SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPssAlgorithm = arr_v;
            try {
                arr_v[JwtRsaSsaPssAlgorithm.PS256.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.jwt.JwkSetConverter.1.$SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPssAlgorithm[JwtRsaSsaPssAlgorithm.PS384.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.jwt.JwkSetConverter.1.$SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPssAlgorithm[JwtRsaSsaPssAlgorithm.PS512.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            int[] arr_v1 = new int[JwtRsaSsaPkcs1Algorithm.values().length];
            com.google.crypto.tink.jwt.JwkSetConverter.1.$SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPkcs1Algorithm = arr_v1;
            try {
                arr_v1[JwtRsaSsaPkcs1Algorithm.RS256.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.jwt.JwkSetConverter.1.$SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPkcs1Algorithm[JwtRsaSsaPkcs1Algorithm.RS384.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.jwt.JwkSetConverter.1.$SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPkcs1Algorithm[JwtRsaSsaPkcs1Algorithm.RS512.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            int[] arr_v2 = new int[JwtEcdsaAlgorithm.values().length];
            com.google.crypto.tink.jwt.JwkSetConverter.1.$SwitchMap$com$google$crypto$tink$proto$JwtEcdsaAlgorithm = arr_v2;
            try {
                arr_v2[JwtEcdsaAlgorithm.ES256.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.jwt.JwkSetConverter.1.$SwitchMap$com$google$crypto$tink$proto$JwtEcdsaAlgorithm[JwtEcdsaAlgorithm.ES384.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.jwt.JwkSetConverter.1.$SwitchMap$com$google$crypto$tink$proto$JwtEcdsaAlgorithm[JwtEcdsaAlgorithm.ES512.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

