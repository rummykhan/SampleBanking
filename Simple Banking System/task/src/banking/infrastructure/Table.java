package banking.infrastructure;

public enum Table {
    CARD("card");

    private final String tableName;

    Table(String tableName) {
        this.tableName = tableName;
    }

    public String value() {
        return tableName;
    }
}
