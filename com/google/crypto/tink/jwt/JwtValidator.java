package com.google.crypto.tink.jwt;

import com.google.errorprone.annotations.Immutable;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;

@Immutable
public final class JwtValidator {
    public static final class Builder {
        private boolean allowMissingExpiration;
        private Clock clock;
        private Duration clockSkew;
        private boolean expectIssuedInThePast;
        private Optional expectedAudience;
        private Optional expectedIssuer;
        private Optional expectedTypeHeader;
        private boolean ignoreAudiences;
        private boolean ignoreIssuer;
        private boolean ignoreTypeHeader;

        private Builder() {
            this.clock = LinkFollowing..ExternalSyntheticApiModelOutline0.m();
            this.clockSkew = LinkFollowing..ExternalSyntheticApiModelOutline0.m();
            this.expectedTypeHeader = LinkFollowing..ExternalSyntheticApiModelOutline0.m();
            this.ignoreTypeHeader = false;
            this.expectedIssuer = LinkFollowing..ExternalSyntheticApiModelOutline0.m();
            this.ignoreIssuer = false;
            this.expectedAudience = LinkFollowing..ExternalSyntheticApiModelOutline0.m();
            this.ignoreAudiences = false;
            this.allowMissingExpiration = false;
            this.expectIssuedInThePast = false;
        }

        Builder(com.google.crypto.tink.jwt.JwtValidator.1 jwtValidator$10) {
        }

        static Optional access$000(Builder jwtValidator$Builder0) {
            return jwtValidator$Builder0.expectedTypeHeader;
        }

        static boolean access$100(Builder jwtValidator$Builder0) {
            return jwtValidator$Builder0.ignoreTypeHeader;
        }

        static Optional access$200(Builder jwtValidator$Builder0) {
            return jwtValidator$Builder0.expectedIssuer;
        }

        static boolean access$300(Builder jwtValidator$Builder0) {
            return jwtValidator$Builder0.ignoreIssuer;
        }

        static Optional access$400(Builder jwtValidator$Builder0) {
            return jwtValidator$Builder0.expectedAudience;
        }

        static boolean access$500(Builder jwtValidator$Builder0) {
            return jwtValidator$Builder0.ignoreAudiences;
        }

        static boolean access$600(Builder jwtValidator$Builder0) {
            return jwtValidator$Builder0.allowMissingExpiration;
        }

        static boolean access$700(Builder jwtValidator$Builder0) {
            return jwtValidator$Builder0.expectIssuedInThePast;
        }

        static Clock access$800(Builder jwtValidator$Builder0) {
            return jwtValidator$Builder0.clock;
        }

        static Duration access$900(Builder jwtValidator$Builder0) {
            return jwtValidator$Builder0.clockSkew;
        }

        public Builder allowMissingExpiration() {
            this.allowMissingExpiration = true;
            return this;
        }

        public JwtValidator build() {
            if(this.ignoreTypeHeader && LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.expectedTypeHeader)) {
                throw new IllegalArgumentException("ignoreTypeHeader() and expectedTypeHeader() cannot be used together.");
            }
            if(this.ignoreIssuer && LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.expectedIssuer)) {
                throw new IllegalArgumentException("ignoreIssuer() and expectedIssuer() cannot be used together.");
            }
            if(this.ignoreAudiences && LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.expectedAudience)) {
                throw new IllegalArgumentException("ignoreAudiences() and expectedAudience() cannot be used together.");
            }
            return new JwtValidator(this, null);
        }

        public Builder expectAudience(String s) {
            if(s == null) {
                throw new NullPointerException("audience cannot be null");
            }
            this.expectedAudience = LinkFollowing..ExternalSyntheticApiModelOutline0.m(s);
            return this;
        }

        public Builder expectIssuedInThePast() {
            this.expectIssuedInThePast = true;
            return this;
        }

        public Builder expectIssuer(String s) {
            if(s == null) {
                throw new NullPointerException("issuer cannot be null");
            }
            this.expectedIssuer = LinkFollowing..ExternalSyntheticApiModelOutline0.m(s);
            return this;
        }

        public Builder expectTypeHeader(String s) {
            if(s == null) {
                throw new NullPointerException("typ header cannot be null");
            }
            this.expectedTypeHeader = LinkFollowing..ExternalSyntheticApiModelOutline0.m(s);
            return this;
        }

        public Builder ignoreAudiences() {
            this.ignoreAudiences = true;
            return this;
        }

        public Builder ignoreIssuer() {
            this.ignoreIssuer = true;
            return this;
        }

        public Builder ignoreTypeHeader() {
            this.ignoreTypeHeader = true;
            return this;
        }

        public Builder setClock(Clock clock0) {
            if(clock0 == null) {
                throw new NullPointerException("clock cannot be null");
            }
            this.clock = clock0;
            return this;
        }

        public Builder setClockSkew(Duration duration0) {
            if(LinkFollowing..ExternalSyntheticApiModelOutline0.m(duration0, JwtValidator.MAX_CLOCK_SKEW) > 0) {
                throw new IllegalArgumentException("Clock skew too large, max is 10 minutes");
            }
            this.clockSkew = duration0;
            return this;
        }
    }

    private static final Duration MAX_CLOCK_SKEW;
    private final boolean allowMissingExpiration;
    private final Clock clock;
    private final Duration clockSkew;
    private final boolean expectIssuedInThePast;
    private final Optional expectedAudience;
    private final Optional expectedIssuer;
    private final Optional expectedTypeHeader;
    private final boolean ignoreAudiences;
    private final boolean ignoreIssuer;
    private final boolean ignoreTypeHeader;

    static {
        JwtValidator.MAX_CLOCK_SKEW = LinkFollowing..ExternalSyntheticApiModelOutline0.m(10L);
    }

    private JwtValidator(Builder jwtValidator$Builder0) {
        this.expectedTypeHeader = Builder.access$000(jwtValidator$Builder0);
        this.ignoreTypeHeader = Builder.access$100(jwtValidator$Builder0);
        this.expectedIssuer = Builder.access$200(jwtValidator$Builder0);
        this.ignoreIssuer = Builder.access$300(jwtValidator$Builder0);
        this.expectedAudience = Builder.access$400(jwtValidator$Builder0);
        this.ignoreAudiences = Builder.access$500(jwtValidator$Builder0);
        this.allowMissingExpiration = Builder.access$600(jwtValidator$Builder0);
        this.expectIssuedInThePast = Builder.access$700(jwtValidator$Builder0);
        this.clock = Builder.access$800(jwtValidator$Builder0);
        this.clockSkew = Builder.access$900(jwtValidator$Builder0);
    }

    JwtValidator(Builder jwtValidator$Builder0, com.google.crypto.tink.jwt.JwtValidator.1 jwtValidator$10) {
        this(jwtValidator$Builder0);
    }

    public static Builder newBuilder() {
        return new Builder(null);
    }

    @Override
    public String toString() {
        ArrayList arrayList0 = new ArrayList();
        if(LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.expectedTypeHeader)) {
            arrayList0.add("expectedTypeHeader=" + ((String)LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.expectedTypeHeader)));
        }
        if(this.ignoreTypeHeader) {
            arrayList0.add("ignoreTypeHeader");
        }
        if(LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.expectedIssuer)) {
            arrayList0.add("expectedIssuer=" + ((String)LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.expectedIssuer)));
        }
        if(this.ignoreIssuer) {
            arrayList0.add("ignoreIssuer");
        }
        if(LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.expectedAudience)) {
            arrayList0.add("expectedAudience=" + ((String)LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.expectedAudience)));
        }
        if(this.ignoreAudiences) {
            arrayList0.add("ignoreAudiences");
        }
        if(this.allowMissingExpiration) {
            arrayList0.add("allowMissingExpiration");
        }
        if(this.expectIssuedInThePast) {
            arrayList0.add("expectIssuedInThePast");
        }
        if(!LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.clockSkew)) {
            arrayList0.add("clockSkew=" + this.clockSkew);
        }
        StringBuilder stringBuilder0 = new StringBuilder("JwtValidator{");
        String s = "";
        for(Object object0: arrayList0) {
            stringBuilder0.append(s);
            stringBuilder0.append(((String)object0));
            s = ",";
        }
        stringBuilder0.append("}");
        return stringBuilder0.toString();
    }

    VerifiedJwt validate(RawJwt rawJwt0) throws JwtInvalidException {
        this.validateTimestampClaims(rawJwt0);
        this.validateTypeHeader(rawJwt0);
        this.validateIssuer(rawJwt0);
        this.validateAudiences(rawJwt0);
        return new VerifiedJwt(rawJwt0);
    }

    private void validateAudiences(RawJwt rawJwt0) throws JwtInvalidException {
        if(LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.expectedAudience)) {
            if(!rawJwt0.hasAudiences() || !rawJwt0.getAudiences().contains(LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.expectedAudience))) {
                throw new JwtInvalidException(String.format("invalid JWT; missing expected audience %s.", LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.expectedAudience)));
            }
        }
        else if(rawJwt0.hasAudiences() && !this.ignoreAudiences) {
            throw new JwtInvalidException("invalid JWT; token has audience set, but validator not.");
        }
    }

    private void validateIssuer(RawJwt rawJwt0) throws JwtInvalidException {
        if(LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.expectedIssuer)) {
            if(!rawJwt0.hasIssuer()) {
                throw new JwtInvalidException(String.format("invalid JWT; missing expected issuer %s.", LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.expectedIssuer)));
            }
            if(!rawJwt0.getIssuer().equals(LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.expectedIssuer))) {
                throw new JwtInvalidException(String.format("invalid JWT; expected issuer %s, but got %s", LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.expectedIssuer), rawJwt0.getIssuer()));
            }
        }
        else if(rawJwt0.hasIssuer() && !this.ignoreIssuer) {
            throw new JwtInvalidException("invalid JWT; token has issuer set, but validator not.");
        }
    }

    private void validateTimestampClaims(RawJwt rawJwt0) throws JwtInvalidException {
        Instant instant0 = LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.clock);
        if(!rawJwt0.hasExpiration() && !this.allowMissingExpiration) {
            throw new JwtInvalidException("token does not have an expiration set");
        }
        if(rawJwt0.hasExpiration() && !LinkFollowing..ExternalSyntheticApiModelOutline0.m(rawJwt0.getExpiration(), LinkFollowing..ExternalSyntheticApiModelOutline0.m(instant0, this.clockSkew))) {
            throw new JwtInvalidException("token has expired since " + rawJwt0.getExpiration());
        }
        if(rawJwt0.hasNotBefore() && LinkFollowing..ExternalSyntheticApiModelOutline0.m(rawJwt0.getNotBefore(), LinkFollowing..ExternalSyntheticApiModelOutline0.m$1(instant0, this.clockSkew))) {
            throw new JwtInvalidException("token cannot be used before " + rawJwt0.getNotBefore());
        }
        if(this.expectIssuedInThePast) {
            if(!rawJwt0.hasIssuedAt()) {
                throw new JwtInvalidException("token does not have an iat claim");
            }
            if(LinkFollowing..ExternalSyntheticApiModelOutline0.m(rawJwt0.getIssuedAt(), LinkFollowing..ExternalSyntheticApiModelOutline0.m$1(instant0, this.clockSkew))) {
                throw new JwtInvalidException("token has a invalid iat claim in the future: " + rawJwt0.getIssuedAt());
            }
        }
    }

    private void validateTypeHeader(RawJwt rawJwt0) throws JwtInvalidException {
        if(LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.expectedTypeHeader)) {
            if(!rawJwt0.hasTypeHeader()) {
                throw new JwtInvalidException(String.format("invalid JWT; missing expected type header %s.", LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.expectedTypeHeader)));
            }
            if(!rawJwt0.getTypeHeader().equals(LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.expectedTypeHeader))) {
                throw new JwtInvalidException(String.format("invalid JWT; expected type header %s, but got %s", LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.expectedTypeHeader), rawJwt0.getTypeHeader()));
            }
        }
        else if(rawJwt0.hasTypeHeader() && !this.ignoreTypeHeader) {
            throw new JwtInvalidException("invalid JWT; token has type header set, but validator not.");
        }
    }

    class com.google.crypto.tink.jwt.JwtValidator.1 {
    }

}

