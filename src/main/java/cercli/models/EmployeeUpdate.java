package cercli.models;

import cercli.utils.OffsetDateTimeUtils;
import cercli.utils.UUIDUtils;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * Model to store information of an employee that is to be updated
 * We do not support updating timestamps and agent UUID of creation and modification
 */
public class EmployeeUpdate {
    private final UUID employeeId;  // Required
    private Optional<String> name = Optional.empty();
    private Optional<String> position = Optional.empty();
    private Optional<String> email = Optional.empty();
    private Optional<Double> salary = Optional.empty();
    private Optional<Country> country = Optional.empty();
    private Optional<String> currency = Optional.empty();
    private Optional<OffsetDateTime> modifiedAt = Optional.of(OffsetDateTimeUtils.getServerTime());
    private Optional<UUID> modifiedBy = Optional.of(UUIDUtils.getServiceUserUUID());

    public EmployeeUpdate(UUID employeeId) {
        this.employeeId = employeeId;
    }

    public UUID getEmployeeId() {
        return employeeId;
    }

    // Setters for optional fields
    public void setName(String name) {
        this.name = Optional.ofNullable(name);
    }

    public void setPosition(String position) {
        this.position = Optional.of(EmployeePosition.fromString(position).toString());
    }

    public void setEmail(String email) {
        this.email = Optional.ofNullable(email);
    }

    public void setSalary(Double salary) {
        this.salary = Optional.ofNullable(salary);
    }

    public void setCountry(Country country) {
        this.country = Optional.ofNullable(country);
    }

    public void setCurrency(String currency) {
        this.currency = Optional.ofNullable(currency);
    }

    // Getters for optional fields
    public Optional<String> getName() {
        return name;
    }

    public Optional<String> getPosition() {
        return position;
    }

    public Optional<String> getEmail() {
        return email;
    }

    public Optional<Double> getSalary() {
        return salary;
    }

    public Optional<Country> getCountry() {
        return country;
    }

    public Optional<String> getCurrency() {
        return currency;
    }

    public Optional<OffsetDateTime> getModifiedAt() {
        return modifiedAt;
    }

    public Optional<UUID> getModifiedBy() {
        return modifiedBy;
    }
}


