package com.google.crypto.tink.jwt;

import com.google.crypto.tink.internal.Util;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.subtle.Base64;
import com.google.gson.JsonObject;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.security.InvalidAlgorithmParameterException;
import java.util.Optional;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;

final class JwtFormat {
    static class Parts {
        String header;
        String payload;
        byte[] signatureOrMac;
        String unsignedCompact;

        Parts(String s, byte[] arr_b, String s1, String s2) {
            this.unsignedCompact = s;
            this.signatureOrMac = arr_b;
            this.header = s1;
            this.payload = s2;
        }
    }

    static String createHeader(String s, Optional optional0, Optional optional1) throws InvalidAlgorithmParameterException {
        JwtFormat.validateAlgorithm(s);
        JsonObject jsonObject0 = new JsonObject();
        if(LinkFollowing..ExternalSyntheticApiModelOutline0.m(optional1)) {
            jsonObject0.addProperty("kid", ((String)LinkFollowing..ExternalSyntheticApiModelOutline0.m(optional1)));
        }
        jsonObject0.addProperty("alg", s);
        if(LinkFollowing..ExternalSyntheticApiModelOutline0.m(optional0)) {
            jsonObject0.addProperty("typ", ((String)LinkFollowing..ExternalSyntheticApiModelOutline0.m(optional0)));
        }
        return Base64.urlSafeEncode(jsonObject0.toString().getBytes(Util.UTF_8));
    }

    static String createSignedCompact(String s, byte[] arr_b) {
        return s + "." + JwtFormat.encodeSignature(arr_b);
    }

    static String createUnsignedCompact(String s, Optional optional0, RawJwt rawJwt0) throws InvalidAlgorithmParameterException, JwtInvalidException {
        String s1 = rawJwt0.getJsonPayload();
        return rawJwt0.hasTypeHeader() ? JwtFormat.createHeader(s, LinkFollowing..ExternalSyntheticApiModelOutline0.m(rawJwt0.getTypeHeader()), optional0) + "." + JwtFormat.encodePayload(s1) : JwtFormat.createHeader(s, LinkFollowing..ExternalSyntheticApiModelOutline0.m(), optional0) + "." + JwtFormat.encodePayload(s1);
    }

    static String decodeHeader(String s) throws JwtInvalidException {
        byte[] arr_b = JwtFormat.strictUrlSafeDecode(s);
        JwtFormat.validateUtf8(arr_b);
        return new String(arr_b, Util.UTF_8);
    }

    static String decodePayload(String s) throws JwtInvalidException {
        byte[] arr_b = JwtFormat.strictUrlSafeDecode(s);
        JwtFormat.validateUtf8(arr_b);
        return new String(arr_b, Util.UTF_8);
    }

    static byte[] decodeSignature(String s) throws JwtInvalidException {
        return JwtFormat.strictUrlSafeDecode(s);
    }

    static String encodePayload(String s) {
        return Base64.urlSafeEncode(s.getBytes(Util.UTF_8));
    }

    static String encodeSignature(byte[] arr_b) {
        return Base64.urlSafeEncode(arr_b);
    }

    static Optional getKeyId(String s) {
        byte[] arr_b = Base64.urlSafeDecode(s);
        return arr_b.length == 4 ? LinkFollowing..ExternalSyntheticApiModelOutline0.m(ByteBuffer.wrap(arr_b).getInt()) : LinkFollowing..ExternalSyntheticApiModelOutline0.m();
    }

    static Optional getKid(int v, OutputPrefixType outputPrefixType0) throws JwtInvalidException {
        if(outputPrefixType0 == OutputPrefixType.RAW) {
            return LinkFollowing..ExternalSyntheticApiModelOutline0.m();
        }
        if(outputPrefixType0 != OutputPrefixType.TINK) {
            throw new JwtInvalidException("unsupported output prefix type");
        }
        return LinkFollowing..ExternalSyntheticApiModelOutline0.m(Base64.urlSafeEncode(ByteBuffer.allocate(4).putInt(v).array()));
    }

    private static String getStringHeader(JsonObject jsonObject0, String s) throws JwtInvalidException {
        if(!jsonObject0.has(s)) {
            throw new JwtInvalidException("header " + s + " does not exist");
        }
        if(!jsonObject0.get(s).isJsonPrimitive() || !jsonObject0.get(s).getAsJsonPrimitive().isString()) {
            throw new JwtInvalidException("header " + s + " is not a string");
        }
        return jsonObject0.get(s).getAsString();
    }

    // 去混淆评级： 低(20)
    static Optional getTypeHeader(JsonObject jsonObject0) throws JwtInvalidException {
        return jsonObject0.has("typ") ? LinkFollowing..ExternalSyntheticApiModelOutline0.m(JwtFormat.getStringHeader(jsonObject0, "typ")) : LinkFollowing..ExternalSyntheticApiModelOutline0.m();
    }

    // 去混淆评级： 低(30)
    static boolean isValidUrlsafeBase64Char(char c) {
        return c >= 97 && c <= 0x7A || c >= 65 && c <= 90 || c >= 0x30 && c <= 57 || (c == 45 || c == 0x5F);
    }

    static Parts splitSignedCompact(String s) throws JwtInvalidException {
        JwtFormat.validateASCII(s);
        int v = s.lastIndexOf(46);
        if(v < 0) {
            throw new JwtInvalidException("only tokens in JWS compact serialization format are supported");
        }
        String s1 = s.substring(0, v);
        byte[] arr_b = JwtFormat.decodeSignature(s.substring(v + 1));
        int v1 = s1.indexOf(46);
        if(v1 < 0) {
            throw new JwtInvalidException("only tokens in JWS compact serialization format are supported");
        }
        String s2 = s1.substring(0, v1);
        String s3 = s1.substring(v1 + 1);
        if(s3.indexOf(46) > 0) {
            throw new JwtInvalidException("only tokens in JWS compact serialization format are supported");
        }
        return new Parts(s1, arr_b, JwtFormat.decodeHeader(s2), JwtFormat.decodePayload(s3));
    }

    static byte[] strictUrlSafeDecode(String s) throws JwtInvalidException {
        for(int v = 0; v < s.length(); ++v) {
            if(!JwtFormat.isValidUrlsafeBase64Char(s.charAt(v))) {
                throw new JwtInvalidException("invalid encoding");
            }
        }
        try {
            return Base64.urlSafeDecode(s);
        }
        catch(IllegalArgumentException illegalArgumentException0) {
            throw new JwtInvalidException("invalid encoding: " + illegalArgumentException0);
        }
    }

    static void validateASCII(String s) throws JwtInvalidException {
        for(int v = 0; v < s.length(); ++v) {
            if((s.charAt(v) & 0x80) > 0) {
                throw new JwtInvalidException("Non ascii character");
            }
        }
    }

    // 去混淆评级： 低(30)
    private static void validateAlgorithm(String s) throws InvalidAlgorithmParameterException {
        s.hashCode();
        switch(s) {
            case "ES256": 
            case "ES384": 
            case "ES512": 
            case "HS256": 
            case "HS384": 
            case "HS512": 
            case "PS256": 
            case "PS384": 
            case "PS512": 
            case "RS256": 
            case "RS384": 
            case "RS512": {
                return;
            }
            default: {
                throw new InvalidAlgorithmParameterException("invalid algorithm: " + s);
            }
        }
    }

    static void validateHeader(String s, Optional optional0, Optional optional1, JsonObject jsonObject0) throws InvalidAlgorithmParameterException, JwtInvalidException {
        JwtFormat.validateAlgorithm(s);
        String s1 = JwtFormat.getStringHeader(jsonObject0, "alg");
        if(!s1.equals(s)) {
            throw new InvalidAlgorithmParameterException(String.format("invalid algorithm; expected %s, got %s", s, s1));
        }
        if(jsonObject0.has("crit")) {
            throw new JwtInvalidException("all tokens with crit headers are rejected");
        }
        if(LinkFollowing..ExternalSyntheticApiModelOutline0.m(optional0) && LinkFollowing..ExternalSyntheticApiModelOutline0.m(optional1)) {
            throw new JwtInvalidException("custom_kid can only be set for RAW keys.");
        }
        boolean z = jsonObject0.has("kid");
        if(LinkFollowing..ExternalSyntheticApiModelOutline0.m(optional0)) {
            if(!z) {
                throw new JwtInvalidException("missing kid in header");
            }
            JwtFormat.validateKidInHeader(((String)LinkFollowing..ExternalSyntheticApiModelOutline0.m(optional0)), jsonObject0);
        }
        if(LinkFollowing..ExternalSyntheticApiModelOutline0.m(optional1) && z) {
            JwtFormat.validateKidInHeader(((String)LinkFollowing..ExternalSyntheticApiModelOutline0.m(optional1)), jsonObject0);
        }
    }

    private static void validateKidInHeader(String s, JsonObject jsonObject0) throws JwtInvalidException {
        if(!JwtFormat.getStringHeader(jsonObject0, "kid").equals(s)) {
            throw new JwtInvalidException("invalid kid in header");
        }
    }

    static void validateUtf8(byte[] arr_b) throws JwtInvalidException {
        CharsetDecoder charsetDecoder0 = Util.UTF_8.newDecoder();
        try {
            charsetDecoder0.decode(ByteBuffer.wrap(arr_b));
        }
        catch(CharacterCodingException characterCodingException0) {
            throw new JwtInvalidException(characterCodingException0.getMessage());
        }
    }
}

