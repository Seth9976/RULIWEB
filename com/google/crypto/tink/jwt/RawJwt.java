package com.google.crypto.tink.jwt;

import com.google.errorprone.annotations.Immutable;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;

@Immutable
public final class RawJwt {
    public static final class Builder {
        private final JsonObject payload;
        private Optional typeHeader;
        private boolean withoutExpiration;

        private Builder() {
            this.typeHeader = LinkFollowing..ExternalSyntheticApiModelOutline0.m();
            this.withoutExpiration = false;
            this.payload = new JsonObject();
        }

        Builder(com.google.crypto.tink.jwt.RawJwt.1 rawJwt$10) {
        }

        static JsonObject access$000(Builder rawJwt$Builder0) {
            return rawJwt$Builder0.payload;
        }

        static boolean access$100(Builder rawJwt$Builder0) {
            return rawJwt$Builder0.withoutExpiration;
        }

        static Optional access$200(Builder rawJwt$Builder0) {
            return rawJwt$Builder0.typeHeader;
        }

        public Builder addAudience(String s) {
            JsonArray jsonArray0;
            if(!JsonUtil.isValidString(s)) {
                throw new IllegalArgumentException("invalid string");
            }
            if(this.payload.has("aud")) {
                JsonElement jsonElement0 = this.payload.get("aud");
                if(!jsonElement0.isJsonArray()) {
                    throw new IllegalArgumentException("addAudience can\'t be used together with setAudience");
                }
                jsonArray0 = jsonElement0.getAsJsonArray();
            }
            else {
                jsonArray0 = new JsonArray();
            }
            jsonArray0.add(s);
            this.payload.add("aud", jsonArray0);
            return this;
        }

        public Builder addBooleanClaim(String s, boolean z) {
            JwtNames.validate(s);
            JsonPrimitive jsonPrimitive0 = new JsonPrimitive(Boolean.valueOf(z));
            this.payload.add(s, jsonPrimitive0);
            return this;
        }

        public Builder addJsonArrayClaim(String s, String s1) throws JwtInvalidException {
            JwtNames.validate(s);
            JsonArray jsonArray0 = JsonUtil.parseJsonArray(s1);
            this.payload.add(s, jsonArray0);
            return this;
        }

        public Builder addJsonObjectClaim(String s, String s1) throws JwtInvalidException {
            JwtNames.validate(s);
            JsonObject jsonObject0 = JsonUtil.parseJson(s1);
            this.payload.add(s, jsonObject0);
            return this;
        }

        public Builder addNullClaim(String s) {
            JwtNames.validate(s);
            this.payload.add(s, JsonNull.INSTANCE);
            return this;
        }

        public Builder addNumberClaim(String s, double f) {
            JwtNames.validate(s);
            JsonPrimitive jsonPrimitive0 = new JsonPrimitive(f);
            this.payload.add(s, jsonPrimitive0);
            return this;
        }

        public Builder addStringClaim(String s, String s1) {
            if(!JsonUtil.isValidString(s1)) {
                throw new IllegalArgumentException();
            }
            JwtNames.validate(s);
            JsonPrimitive jsonPrimitive0 = new JsonPrimitive(s1);
            this.payload.add(s, jsonPrimitive0);
            return this;
        }

        public RawJwt build() {
            return new RawJwt(this, null);
        }

        public Builder setAudience(String s) {
            if(this.payload.has("aud") && this.payload.get("aud").isJsonArray()) {
                throw new IllegalArgumentException("setAudience can\'t be used together with setAudiences or addAudience");
            }
            if(!JsonUtil.isValidString(s)) {
                throw new IllegalArgumentException("invalid string");
            }
            JsonPrimitive jsonPrimitive0 = new JsonPrimitive(s);
            this.payload.add("aud", jsonPrimitive0);
            return this;
        }

        public Builder setAudiences(List list0) {
            if(this.payload.has("aud") && !this.payload.get("aud").isJsonArray()) {
                throw new IllegalArgumentException("setAudiences can\'t be used together with setAudience");
            }
            if(list0.isEmpty()) {
                throw new IllegalArgumentException("audiences must not be empty");
            }
            JsonArray jsonArray0 = new JsonArray();
            for(Object object0: list0) {
                if(!JsonUtil.isValidString(((String)object0))) {
                    throw new IllegalArgumentException("invalid string");
                }
                jsonArray0.add(((String)object0));
            }
            this.payload.add("aud", jsonArray0);
            return this;
        }

        public Builder setExpiration(Instant instant0) {
            this.setTimestampClaim("exp", instant0);
            return this;
        }

        public Builder setIssuedAt(Instant instant0) {
            this.setTimestampClaim("iat", instant0);
            return this;
        }

        public Builder setIssuer(String s) {
            if(!JsonUtil.isValidString(s)) {
                throw new IllegalArgumentException();
            }
            JsonPrimitive jsonPrimitive0 = new JsonPrimitive(s);
            this.payload.add("iss", jsonPrimitive0);
            return this;
        }

        public Builder setJwtId(String s) {
            if(!JsonUtil.isValidString(s)) {
                throw new IllegalArgumentException();
            }
            JsonPrimitive jsonPrimitive0 = new JsonPrimitive(s);
            this.payload.add("jti", jsonPrimitive0);
            return this;
        }

        public Builder setNotBefore(Instant instant0) {
            this.setTimestampClaim("nbf", instant0);
            return this;
        }

        public Builder setSubject(String s) {
            if(!JsonUtil.isValidString(s)) {
                throw new IllegalArgumentException();
            }
            JsonPrimitive jsonPrimitive0 = new JsonPrimitive(s);
            this.payload.add("sub", jsonPrimitive0);
            return this;
        }

        private void setTimestampClaim(String s, Instant instant0) {
            long v = LinkFollowing..ExternalSyntheticApiModelOutline0.m(instant0);
            if(v > 0x3AFFF4417FL || v < 0L) {
                throw new IllegalArgumentException("timestamp of claim " + s + " is out of range");
            }
            JsonPrimitive jsonPrimitive0 = new JsonPrimitive(v);
            this.payload.add(s, jsonPrimitive0);
        }

        public Builder setTypeHeader(String s) {
            this.typeHeader = LinkFollowing..ExternalSyntheticApiModelOutline0.m(s);
            return this;
        }

        public Builder withoutExpiration() {
            this.withoutExpiration = true;
            return this;
        }
    }

    private static final long MAX_TIMESTAMP_VALUE = 0x3AFFF4417FL;
    private final JsonObject payload;
    private final Optional typeHeader;

    private RawJwt(Builder rawJwt$Builder0) {
        if(!Builder.access$000(rawJwt$Builder0).has("exp") && !Builder.access$100(rawJwt$Builder0)) {
            throw new IllegalArgumentException("neither setExpiration() nor withoutExpiration() was called");
        }
        if(Builder.access$000(rawJwt$Builder0).has("exp") && Builder.access$100(rawJwt$Builder0)) {
            throw new IllegalArgumentException("setExpiration() and withoutExpiration() must not be called together");
        }
        this.typeHeader = Builder.access$200(rawJwt$Builder0);
        this.payload = Builder.access$000(rawJwt$Builder0).deepCopy();
    }

    RawJwt(Builder rawJwt$Builder0, com.google.crypto.tink.jwt.RawJwt.1 rawJwt$10) {
        this(rawJwt$Builder0);
    }

    private RawJwt(Optional optional0, String s) throws JwtInvalidException {
        this.typeHeader = optional0;
        this.payload = JsonUtil.parseJson(s);
        this.validateStringClaim("iss");
        this.validateStringClaim("sub");
        this.validateStringClaim("jti");
        this.validateTimestampClaim("exp");
        this.validateTimestampClaim("nbf");
        this.validateTimestampClaim("iat");
        this.validateAudienceClaim();
    }

    Set customClaimNames() {
        HashSet hashSet0 = new HashSet();
        for(Object object0: this.payload.keySet()) {
            String s = (String)object0;
            if(!JwtNames.isRegisteredName(s)) {
                hashSet0.add(s);
            }
        }
        return Collections.unmodifiableSet(hashSet0);
    }

    static RawJwt fromJsonPayload(Optional optional0, String s) throws JwtInvalidException {
        return new RawJwt(optional0, s);
    }

    List getAudiences() throws JwtInvalidException {
        if(!this.hasAudiences()) {
            throw new JwtInvalidException("claim aud does not exist");
        }
        JsonElement jsonElement0 = this.payload.get("aud");
        if(jsonElement0.isJsonPrimitive()) {
            if(!jsonElement0.getAsJsonPrimitive().isString()) {
                throw new JwtInvalidException(String.format("invalid audience: got %s; want a string", jsonElement0));
            }
            return Collections.unmodifiableList(Arrays.asList(new String[]{jsonElement0.getAsString()}));
        }
        if(!jsonElement0.isJsonArray()) {
            throw new JwtInvalidException("claim aud is not a string or a JSON array");
        }
        JsonArray jsonArray0 = jsonElement0.getAsJsonArray();
        ArrayList arrayList0 = new ArrayList(jsonArray0.size());
        for(int v = 0; v < jsonArray0.size(); ++v) {
            if(!jsonArray0.get(v).isJsonPrimitive() || !jsonArray0.get(v).getAsJsonPrimitive().isString()) {
                throw new JwtInvalidException(String.format("invalid audience: got %s; want a string", jsonArray0.get(v)));
            }
            arrayList0.add(jsonArray0.get(v).getAsString());
        }
        return Collections.unmodifiableList(arrayList0);
    }

    Boolean getBooleanClaim(String s) throws JwtInvalidException {
        JwtNames.validate(s);
        if(!this.payload.has(s)) {
            throw new JwtInvalidException("claim " + s + " does not exist");
        }
        if(!this.payload.get(s).isJsonPrimitive() || !this.payload.get(s).getAsJsonPrimitive().isBoolean()) {
            throw new JwtInvalidException("claim " + s + " is not a boolean");
        }
        return Boolean.valueOf(this.payload.get(s).getAsBoolean());
    }

    Instant getExpiration() throws JwtInvalidException {
        return this.getInstant("exp");
    }

    private Instant getInstant(String s) throws JwtInvalidException {
        if(!this.payload.has(s)) {
            throw new JwtInvalidException("claim " + s + " does not exist");
        }
        if(this.payload.get(s).isJsonPrimitive() && this.payload.get(s).getAsJsonPrimitive().isNumber()) {
            try {
                return LinkFollowing..ExternalSyntheticApiModelOutline0.m(((long)(this.payload.get(s).getAsJsonPrimitive().getAsDouble() * 1000.0)));
            }
            catch(NumberFormatException numberFormatException0) {
                throw new JwtInvalidException("claim " + s + " is not a timestamp: " + numberFormatException0);
            }
        }
        throw new JwtInvalidException("claim " + s + " is not a timestamp");
    }

    Instant getIssuedAt() throws JwtInvalidException {
        return this.getInstant("iat");
    }

    String getIssuer() throws JwtInvalidException {
        return this.getStringClaimInternal("iss");
    }

    String getJsonArrayClaim(String s) throws JwtInvalidException {
        JwtNames.validate(s);
        if(!this.payload.has(s)) {
            throw new JwtInvalidException("claim " + s + " does not exist");
        }
        if(!this.payload.get(s).isJsonArray()) {
            throw new JwtInvalidException("claim " + s + " is not a JSON array");
        }
        return this.payload.get(s).getAsJsonArray().toString();
    }

    String getJsonObjectClaim(String s) throws JwtInvalidException {
        JwtNames.validate(s);
        if(!this.payload.has(s)) {
            throw new JwtInvalidException("claim " + s + " does not exist");
        }
        if(!this.payload.get(s).isJsonObject()) {
            throw new JwtInvalidException("claim " + s + " is not a JSON object");
        }
        return this.payload.get(s).getAsJsonObject().toString();
    }

    String getJsonPayload() {
        return this.payload.toString();
    }

    String getJwtId() throws JwtInvalidException {
        return this.getStringClaimInternal("jti");
    }

    Instant getNotBefore() throws JwtInvalidException {
        return this.getInstant("nbf");
    }

    Double getNumberClaim(String s) throws JwtInvalidException {
        JwtNames.validate(s);
        if(!this.payload.has(s)) {
            throw new JwtInvalidException("claim " + s + " does not exist");
        }
        if(!this.payload.get(s).isJsonPrimitive() || !this.payload.get(s).getAsJsonPrimitive().isNumber()) {
            throw new JwtInvalidException("claim " + s + " is not a number");
        }
        return this.payload.get(s).getAsDouble();
    }

    String getStringClaim(String s) throws JwtInvalidException {
        JwtNames.validate(s);
        return this.getStringClaimInternal(s);
    }

    private String getStringClaimInternal(String s) throws JwtInvalidException {
        if(!this.payload.has(s)) {
            throw new JwtInvalidException("claim " + s + " does not exist");
        }
        if(!this.payload.get(s).isJsonPrimitive() || !this.payload.get(s).getAsJsonPrimitive().isString()) {
            throw new JwtInvalidException("claim " + s + " is not a string");
        }
        return this.payload.get(s).getAsString();
    }

    String getSubject() throws JwtInvalidException {
        return this.getStringClaimInternal("sub");
    }

    String getTypeHeader() throws JwtInvalidException {
        if(!LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.typeHeader)) {
            throw new JwtInvalidException("type header is not set");
        }
        return (String)LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.typeHeader);
    }

    boolean hasAudiences() {
        return this.payload.has("aud");
    }

    boolean hasBooleanClaim(String s) {
        JwtNames.validate(s);
        return this.payload.has(s) && this.payload.get(s).isJsonPrimitive() && this.payload.get(s).getAsJsonPrimitive().isBoolean();
    }

    boolean hasExpiration() {
        return this.payload.has("exp");
    }

    boolean hasIssuedAt() {
        return this.payload.has("iat");
    }

    boolean hasIssuer() {
        return this.payload.has("iss");
    }

    boolean hasJsonArrayClaim(String s) {
        JwtNames.validate(s);
        return this.payload.has(s) && this.payload.get(s).isJsonArray();
    }

    boolean hasJsonObjectClaim(String s) {
        JwtNames.validate(s);
        return this.payload.has(s) && this.payload.get(s).isJsonObject();
    }

    boolean hasJwtId() {
        return this.payload.has("jti");
    }

    boolean hasNotBefore() {
        return this.payload.has("nbf");
    }

    boolean hasNumberClaim(String s) {
        JwtNames.validate(s);
        return this.payload.has(s) && this.payload.get(s).isJsonPrimitive() && this.payload.get(s).getAsJsonPrimitive().isNumber();
    }

    boolean hasStringClaim(String s) {
        JwtNames.validate(s);
        return this.payload.has(s) && this.payload.get(s).isJsonPrimitive() && this.payload.get(s).getAsJsonPrimitive().isString();
    }

    boolean hasSubject() {
        return this.payload.has("sub");
    }

    boolean hasTypeHeader() {
        return LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.typeHeader);
    }

    boolean isNullClaim(String s) {
        JwtNames.validate(s);
        try {
            JsonElement jsonElement0 = this.payload.get(s);
            return JsonNull.INSTANCE.equals(jsonElement0);
        }
        catch(JsonParseException unused_ex) {
            return false;
        }
    }

    public static Builder newBuilder() {
        return new Builder(null);
    }

    @Override
    public String toString() {
        JsonObject jsonObject0 = new JsonObject();
        if(LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.typeHeader)) {
            jsonObject0.add("typ", new JsonPrimitive(((String)LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.typeHeader))));
        }
        return jsonObject0 + "." + this.payload;
    }

    private void validateAudienceClaim() throws JwtInvalidException {
        if(this.payload.has("aud") && (!this.payload.get("aud").isJsonPrimitive() || !this.payload.get("aud").getAsJsonPrimitive().isString()) && this.getAudiences().size() < 1) {
            throw new JwtInvalidException("invalid JWT payload: claim aud is present but empty.");
        }
    }

    private void validateStringClaim(String s) throws JwtInvalidException {
        if(this.payload.has(s) && (!this.payload.get(s).isJsonPrimitive() || !this.payload.get(s).getAsJsonPrimitive().isString())) {
            throw new JwtInvalidException("invalid JWT payload: claim " + s + " is not a string.");
        }
    }

    private void validateTimestampClaim(String s) throws JwtInvalidException {
        if(this.payload.has(s)) {
            if(!this.payload.get(s).isJsonPrimitive() || !this.payload.get(s).getAsJsonPrimitive().isNumber()) {
                throw new JwtInvalidException("invalid JWT payload: claim " + s + " is not a number.");
            }
            double f = this.payload.get(s).getAsJsonPrimitive().getAsDouble();
            if(f > 253402300799.0 || f < 0.0) {
                throw new JwtInvalidException("invalid JWT payload: claim " + s + " has an invalid timestamp");
            }
        }
    }

    class com.google.crypto.tink.jwt.RawJwt.1 {
    }

}

