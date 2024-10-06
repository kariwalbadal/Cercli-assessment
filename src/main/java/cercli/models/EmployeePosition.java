package cercli.models;

public enum EmployeePosition {
    SALES_ASSOCIATE,
    MANAGER,
    DIRECTOR,
    VICE_PRESIDENT,
    SOFTWARE_ENGINEER,
    PRODUCT_MANAGER,
    HUMAN_RESOURCES,
    CUSTOMER_SERVICE,
    MARKETING_SPECIALIST,
    FINANCE_ANALYST,
    OPERATIONS_MANAGER,
    CEO,
    CTO,
    CFO;

    @Override
    public String toString() {
        return name().replace('_', ' ');
    }

    public static EmployeePosition fromString(String position) {
        String normalized = position.toUpperCase().replace(' ', '_');

        // Try to match the normalized string to an enum constant
        try {
            return EmployeePosition.valueOf(normalized);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid position: " + position);
        }
    }
}
