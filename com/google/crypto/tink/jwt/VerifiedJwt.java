package com.google.crypto.tink.jwt;

import com.google.errorprone.annotations.Immutable;
import java.time.Instant;
import java.util.List;
import java.util.Set;

@Immutable
public final class VerifiedJwt {
    private final RawJwt rawJwt;

    VerifiedJwt(RawJwt rawJwt0) {
        this.rawJwt = rawJwt0;
    }

    public Set customClaimNames() {
        return this.rawJwt.customClaimNames();
    }

    public List getAudiences() throws JwtInvalidException {
        return this.rawJwt.getAudiences();
    }

    public Boolean getBooleanClaim(String s) throws JwtInvalidException {
        return this.rawJwt.getBooleanClaim(s);
    }

    public Instant getExpiration() throws JwtInvalidException {
        return this.rawJwt.getExpiration();
    }

    public Instant getIssuedAt() throws JwtInvalidException {
        return this.rawJwt.getIssuedAt();
    }

    public String getIssuer() throws JwtInvalidException {
        return this.rawJwt.getIssuer();
    }

    public String getJsonArrayClaim(String s) throws JwtInvalidException {
        return this.rawJwt.getJsonArrayClaim(s);
    }

    public String getJsonObjectClaim(String s) throws JwtInvalidException {
        return this.rawJwt.getJsonObjectClaim(s);
    }

    public String getJwtId() throws JwtInvalidException {
        return this.rawJwt.getJwtId();
    }

    public Instant getNotBefore() throws JwtInvalidException {
        return this.rawJwt.getNotBefore();
    }

    public Double getNumberClaim(String s) throws JwtInvalidException {
        return this.rawJwt.getNumberClaim(s);
    }

    public String getStringClaim(String s) throws JwtInvalidException {
        return this.rawJwt.getStringClaim(s);
    }

    public String getSubject() throws JwtInvalidException {
        return this.rawJwt.getSubject();
    }

    public String getTypeHeader() throws JwtInvalidException {
        return this.rawJwt.getTypeHeader();
    }

    public boolean hasAudiences() {
        return this.rawJwt.hasAudiences();
    }

    public boolean hasBooleanClaim(String s) {
        return this.rawJwt.hasBooleanClaim(s);
    }

    public boolean hasExpiration() {
        return this.rawJwt.hasExpiration();
    }

    public boolean hasIssuedAt() {
        return this.rawJwt.hasIssuedAt();
    }

    public boolean hasIssuer() {
        return this.rawJwt.hasIssuer();
    }

    public boolean hasJsonArrayClaim(String s) {
        return this.rawJwt.hasJsonArrayClaim(s);
    }

    public boolean hasJsonObjectClaim(String s) {
        return this.rawJwt.hasJsonObjectClaim(s);
    }

    public boolean hasJwtId() {
        return this.rawJwt.hasJwtId();
    }

    public boolean hasNotBefore() {
        return this.rawJwt.hasNotBefore();
    }

    public boolean hasNumberClaim(String s) {
        return this.rawJwt.hasNumberClaim(s);
    }

    public boolean hasStringClaim(String s) {
        return this.rawJwt.hasStringClaim(s);
    }

    public boolean hasSubject() {
        return this.rawJwt.hasSubject();
    }

    public boolean hasTypeHeader() {
        return this.rawJwt.hasTypeHeader();
    }

    public boolean isNullClaim(String s) {
        return this.rawJwt.isNullClaim(s);
    }

    @Override
    public String toString() {
        return "verified{" + this.rawJwt + "}";
    }
}

