package com.kakao.sdk.common.util;

import android.util.Base64;
import com.kakao.sdk.common.KakaoSdk;
import com.kakao.sdk.common.model.ContextInfo;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.util.Arrays;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 \u001A2\u00020\u0001:\u0001\u001AB\u000F\u0012\b\b\u0002\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0011\u001A\u0004\u0018\u00010\b2\u0006\u0010\u0012\u001A\u00020\bH\u0002J\u0010\u0010\u0013\u001A\u00020\b2\u0006\u0010\u0014\u001A\u00020\bH\u0016J\u0010\u0010\u0015\u001A\u00020\b2\u0006\u0010\u0016\u001A\u00020\bH\u0016J\u0012\u0010\u0017\u001A\u0004\u0018\u00010\b2\u0006\u0010\u0018\u001A\u00020\bH\u0002J\u001E\u0010\u0017\u001A\u0004\u0018\u00010\b2\b\u0010\u0018\u001A\u0004\u0018\u00010\b2\b\u0010\u0019\u001A\u0004\u0018\u00010\bH\u0002R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001A\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u000B\u001A\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\f\u001A\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u000E\u001A\u00020\u000FX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001A\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001B"}, d2 = {"Lcom/kakao/sdk/common/util/AESCipher;", "Lcom/kakao/sdk/common/util/Cipher;", "contextInfo", "Lcom/kakao/sdk/common/model/ContextInfo;", "(Lcom/kakao/sdk/common/model/ContextInfo;)V", "charSet", "Ljava/nio/charset/Charset;", "cipherAlgorithm", "", "decryptor", "Ljavax/crypto/Cipher;", "encryptor", "initVector", "", "ivParameterSpec", "Ljavax/crypto/spec/IvParameterSpec;", "keyGenAlgorithm", "base64DecodeAndXor", "source", "decrypt", "encrypted", "encrypt", "value", "xorMessage", "message", "key", "Companion", "common_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class AESCipher implements Cipher {
    @Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/kakao/sdk/common/util/AESCipher$Companion;", "", "()V", "ALGORITHM", "", "ITEM_COUNT", "", "KEY_LENGTH", "common_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    private static final String ALGORITHM = "AES";
    public static final Companion Companion = null;
    private static final int ITEM_COUNT = 2;
    private static final int KEY_LENGTH = 0x100;
    private final Charset charSet;
    private final String cipherAlgorithm;
    private final javax.crypto.Cipher decryptor;
    private final javax.crypto.Cipher encryptor;
    private final byte[] initVector;
    private final IvParameterSpec ivParameterSpec;
    private final String keyGenAlgorithm;

    static {
        AESCipher.Companion = new Companion(null);
    }

    public AESCipher() {
        this(null, 1, null);
    }

    public AESCipher(ContextInfo contextInfo0) {
        Intrinsics.checkNotNullParameter(contextInfo0, "contextInfo");
        super();
        this.keyGenAlgorithm = "PBEWITHSHAAND256BITAES-CBC-BC";
        this.cipherAlgorithm = "AES/CBC/PKCS5Padding";
        this.charSet = Charsets.UTF_8;
        byte[] arr_b = {0x70, 78, 75, 55, -54, -30, -10, 44, 102, (byte)0x82, (byte)0x82, 92, (byte)0x8C, (byte)0xD0, (byte)0x85, -55};
        this.initVector = arr_b;
        IvParameterSpec ivParameterSpec0 = new IvParameterSpec(arr_b);
        this.ivParameterSpec = ivParameterSpec0;
        String s = contextInfo0.getSigningKeyHash();
        SecretKeyFactory secretKeyFactory0 = SecretKeyFactory.getInstance("PBEWITHSHAAND256BITAES-CBC-BC");
        String s1 = s.substring(0, Math.min(s.length(), 16));
        Intrinsics.checkNotNullExpressionValue(s1, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        if(s1 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        char[] arr_c = s1.toCharArray();
        Intrinsics.checkNotNullExpressionValue(arr_c, "(this as java.lang.String).toCharArray()");
        SecretKey secretKey0 = secretKeyFactory0.generateSecret(new PBEKeySpec(arr_c, contextInfo0.getSalt(), 2, 0x100));
        SecretKeySpec secretKeySpec0 = new SecretKeySpec(secretKey0.getEncoded(), "AES");
        javax.crypto.Cipher cipher0 = javax.crypto.Cipher.getInstance("AES/CBC/PKCS5Padding");
        Intrinsics.checkNotNullExpressionValue(cipher0, "getInstance(cipherAlgorithm)");
        this.encryptor = cipher0;
        javax.crypto.Cipher cipher1 = javax.crypto.Cipher.getInstance("AES/CBC/PKCS5Padding");
        Intrinsics.checkNotNullExpressionValue(cipher1, "getInstance(cipherAlgorithm)");
        this.decryptor = cipher1;
        try {
            cipher0.init(1, secretKeySpec0, ivParameterSpec0);
            cipher1.init(2, secretKeySpec0, ivParameterSpec0);
        }
        catch(InvalidKeyException unused_ex) {
            SecretKeySpec secretKeySpec1 = new SecretKeySpec(Arrays.copyOfRange(secretKey0.getEncoded(), 0, secretKey0.getEncoded().length / 2), "AES");
            this.encryptor.init(1, secretKeySpec1, this.ivParameterSpec);
            this.decryptor.init(2, secretKeySpec1, this.ivParameterSpec);
        }
    }

    public AESCipher(ContextInfo contextInfo0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 1) != 0) {
            contextInfo0 = KakaoSdk.INSTANCE.getApplicationContextInfo();
        }
        this(contextInfo0);
    }

    private final String base64DecodeAndXor(String s) [...] // 潜在的解密器

    @Override  // com.kakao.sdk.common.util.Cipher
    public String decrypt(String s) {
        Intrinsics.checkNotNullParameter(s, "encrypted");
        byte[] arr_b = Base64.decode(s, 2);
        byte[] arr_b1 = this.decryptor.doFinal(arr_b);
        Intrinsics.checkNotNullExpressionValue(arr_b1, "decryptor.doFinal(Base64.decode(encrypted, Base64.NO_WRAP))");
        return new String(arr_b1, this.charSet);
    }

    @Override  // com.kakao.sdk.common.util.Cipher
    public String encrypt(String s) {
        Intrinsics.checkNotNullParameter(s, "value");
        byte[] arr_b = s.getBytes(this.charSet);
        Intrinsics.checkNotNullExpressionValue(arr_b, "(this as java.lang.String).getBytes(charset)");
        String s1 = Base64.encodeToString(this.encryptor.doFinal(arr_b), 2);
        Intrinsics.checkNotNullExpressionValue(s1, "encodeToString(encryptor.doFinal(value.toByteArray(charSet)), Base64.NO_WRAP)");
        return s1;
    }

    private final String xorMessage(String s) {
        return this.xorMessage(s, "com.kakao.api");
    }

    private final String xorMessage(String s, String s1) {
        if(s != null && s1 != null) {
            try {
                char[] arr_c = s1.toCharArray();
                Intrinsics.checkNotNullExpressionValue(arr_c, "(this as java.lang.String).toCharArray()");
                char[] arr_c1 = s.toCharArray();
                Intrinsics.checkNotNullExpressionValue(arr_c1, "(this as java.lang.String).toCharArray()");
                char[] arr_c2 = new char[arr_c1.length];
                if(arr_c1.length > 0) {
                    for(int v = 0; true; ++v) {
                        arr_c2[v] = (char)(arr_c1[v] ^ arr_c[v % arr_c.length]);
                        if(v + 1 >= arr_c1.length) {
                            break;
                        }
                    }
                }
                return new String(arr_c2);
            }
            catch(Exception unused_ex) {
            }
        }
        return null;
    }
}

