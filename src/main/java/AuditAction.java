/**
 * Enum for the different audit actions .
 */
public enum AuditAction {

    CREATE("CREATE"),
    UPDATE("UPDATE"),
    DELETE("DELETE");

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

