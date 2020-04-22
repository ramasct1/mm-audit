package com.github.ramasct1;
/**
 * Enum for the different audit actions .
 */
public enum AuditAction {

    CREATE("CREATE"),
    UPDATE("UPDATE"),
    DELETE("DELETE"),
    LOGIN("LOGIN"),
    LOGOUT("LOGOUT"),
    RESET_PASSWORD("RESETPASSWORD"),
    FORGET_PASSWORD("FORGOTPASSWORD"),
    CHANGE_USER_INFO("CHANGEUSERINFO");



    private String value;

    /**
     * Add audit action .
     * @param value .
     */
    AuditAction(final String value) {
        this.value = value;
    }

    /**
     * @return value.
     */
    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return this.value();
    }
}

