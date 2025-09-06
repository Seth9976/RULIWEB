package com.pairip.licensecheck;

import android.os.Bundle;
import android.util.Base64;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import org.json.JSONException;
import org.json.JSONObject;

public class ResponseValidator {
    private static final String KEY_FACTORY_ALGORITHM = "RSA";
    private static final String PAYLOAD_LICENSE_DATA = "LICENSE_DATA";
    private static final String PAYLOAD_PACKAGE_NAME = "packageName";
    private static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
    private static final Charset UTF_8;

    static {
        ResponseValidator.UTF_8 = Charset.forName("UTF-8");
    }

    private static JSONObject base64ToJson(String s) throws LicenseCheckException {
        try {
            return new JSONObject(new String(Base64.decode(s, 8), ResponseValidator.UTF_8));
        }
        catch(IllegalArgumentException | JSONException exception0) {
            throw new LicenseCheckException("Invalid response", exception0);
        }
    }

    private static PublicKey getPublicKey() throws LicenseCheckException {
        try {
            byte[] arr_b = Base64.decode("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgCeJoZ40GO2pMtiNCQuGvPJGJsFTeLdwz8hgT5jVdkQ1r3L9i1YppjjNssjregCL5YfVGPuYW+FosbGafizawbFXEoJUO6zs9mwobh69yl91ivnYCePFeXmWjexiJemnFm+VGsTWi0UuUgh+i2FXCCXk0qQQv9hIdlO7p1h0VqLsjyipscMaEEFd2x3NHozpcYGHBtC2GTVBdYtdMuj85j7B5S8shrjDdZwpkiqNiSzjTSNEczLbiyMqp+QRgm5hdyYE+hF8jst9RcZzolqWTdm/dylfm0qIp++Q0mjS1Qi5mztOv1+Wup0sidLzA/8mjWrfax9DbWspZZrDX+W4XwIDAQAB", 0);
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(arr_b));
        }
        catch(NoSuchAlgorithmException noSuchAlgorithmException0) {
            throw new LicenseCheckException("RSA algorithm not found on device", noSuchAlgorithmException0);
        }
        catch(IllegalArgumentException illegalArgumentException0) {
            throw new LicenseCheckException("Could not decode public key", illegalArgumentException0);
        }
        catch(InvalidKeySpecException invalidKeySpecException0) {
            throw new LicenseCheckException("Could not create key specification from the public key", invalidKeySpecException0);
        }
    }

    public static void validateResponse(Bundle bundle0, String s) throws LicenseCheckException {
        try {
            String s1 = bundle0.getString("LICENSE_DATA");
            if(s1 == null) {
                throw new LicenseCheckException("Invalid response");
            }
            String[] arr_s = s1.split("\\.", -1);
            if(arr_s.length != 3) {
                throw new LicenseCheckException("Invalid response");
            }
            JSONObject jSONObject0 = ResponseValidator.base64ToJson(arr_s[0]);
            JSONObject jSONObject1 = ResponseValidator.base64ToJson(arr_s[1]);
            String s2 = arr_s[2];
            String s3 = arr_s[0] + "." + arr_s[1];
            if(!jSONObject0.getString("alg").equals("RS256")) {
                throw new LicenseCheckException("Response must be signed with RS256 algorithm.");
            }
            ResponseValidator.verifySignature(s3, s2, "SHA256withRSA", ResponseValidator.getPublicKey());
            if(!jSONObject1.getString("packageName").equals(s)) {
                throw new LicenseCheckException("Package name doesn\'t match.");
            }
        }
        catch(JSONException jSONException0) {
            throw new LicenseCheckException("Could not decode json", jSONException0);
        }
    }

    private static void verifySignature(String s, String s1, String s2, PublicKey publicKey0) throws LicenseCheckException {
        try {
            Signature signature0 = Signature.getInstance(s2);
            signature0.initVerify(publicKey0);
            signature0.update(s.getBytes(ResponseValidator.UTF_8));
            if(!signature0.verify(Base64.decode(s1, 8))) {
                throw new LicenseCheckException("Signature verification failed.");
            }
        }
        catch(NoSuchAlgorithmException noSuchAlgorithmException0) {
            throw new LicenseCheckException(String.format("Could not find %s algorithm on the device", s2), noSuchAlgorithmException0);
        }
        catch(InvalidKeyException invalidKeyException0) {
            throw new LicenseCheckException("Could not sign data with the public key", invalidKeyException0);
        }
        catch(SignatureException signatureException0) {
            throw new LicenseCheckException("Could not parse returned signature.", signatureException0);
        }
        catch(IllegalArgumentException illegalArgumentException0) {
            throw new LicenseCheckException("Could not base64 decode returned signature", illegalArgumentException0);
        }
    }
}

