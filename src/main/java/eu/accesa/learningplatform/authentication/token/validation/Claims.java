package eu.accesa.learningplatform.authentication.token.validation;

public enum Claims {

    /**
     * The "iss" (issuer) claim identifies the principal that issued the JWT.
     */
    ISS("iss"),

    /**
     * The "sub" (subject) claim identifies the principal that is the subject of the JWT.
     */
    SUB("sub"),

    /**
     * The "aud" (audience) claim identifies the recipients that the JWT is intended for.
     */
    AUD("aud"),

    /**
     * The "exp" (expiration time) claim identifies the expiration time on or after which the JWT MUST
     * NOT be accepted for processing.
     */
    EXP("exp"),

    /**
     * The "nbf" (not before) claim identifies the time before which the JWT MUST NOT be accepted for
     * processing.
     */
    NBF("nbf"),

    /**
     * The "iat" (issued at) claim identifies the time at which the JWT was issued.
     */
    IAT("iat"),

    /**
     * The "jti" (JWT ID) claim provides a unique identifier for the JWT.
     */
    JTI("jti"),

    /**
     * The "domain" (domain) claim identifies the domain that the JWT is intended for.
     */
    DOMAIN("domain"),

    ROLES("roles");

    private final String value;

    Claims(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
