package cercli.models;

import cercli.utils.OffsetDateTimeUtils;
import cercli.utils.UUIDUtils;

import java.time.OffsetDateTime;
import java.util.UUID;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "employees")
/**
 * Employee model
 */
public class Employee {

    @Id
    @Column(name = "employee_id", nullable = false)
    private final UUID employeeId;

    @Column(name = "name", nullable = false)
    private final String name;

    @Column(name = "position", nullable = false)
    private final String position;

    @Column(name = "email", nullable = false)
    private final String email;

    @Column(name = "salary", nullable = false)
    private final double salary;

    @Column(name = "country", nullable = false)
    @Enumerated(EnumType.STRING)
    private final Country country;

    @Column(name = "currency", nullable = false)
    private final String currency;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "created_by", nullable = false)
    private final UUID createdBy;

    @Column(name = "modified_at")
    private OffsetDateTime modifiedAt;

    @Column(name = "modified_by")
    private final UUID modifiedBy;

    public Employee(String name, String position, String email, double salary, String currency, Country country) {

        this.employeeId = UUIDUtils.getNewUUID();
        this.name = Objects.requireNonNull(name);
        this.position = Objects.requireNonNull(EmployeePosition.fromString(position).toString());
        this.email = Objects.requireNonNull(email);
        this.salary = salary;
        this.country = country;
        this.currency = currency;
        this.createdAt = OffsetDateTimeUtils.getServerTime();
        this.createdBy = UUIDUtils.getServiceUserUUID();
        this.modifiedAt = OffsetDateTimeUtils.getServerTime();
        this.modifiedBy = UUIDUtils.getServiceUserUUID();
    }

    public Employee(UUID employeeId, String name, String position, String email, double salary, String currency, Country country, OffsetDateTime createdAt, UUID createdBy, OffsetDateTime modifiedAt, UUID modifiedBy) {
        this.employeeId = Objects.requireNonNull(employeeId);
        this.name = Objects.requireNonNull(name);
        this.position = Objects.requireNonNull(EmployeePosition.fromString(position).toString());
        this.email = Objects.requireNonNull(email);
        this.salary = salary;
        this.country = country;
        this.currency = currency;
        this.createdAt = OffsetDateTimeUtils.convertToServerTime(Objects.requireNonNull(createdAt));
        this.createdBy = Objects.requireNonNull(createdBy);
        this.modifiedAt = OffsetDateTimeUtils.convertToServerTime(Objects.requireNonNull(modifiedAt));
        this.modifiedBy = Objects.requireNonNull(modifiedBy);
    }

    public Employee() {
        employeeId = null;
        name = "";
        position = "";
        email = "";
        salary = 0.0d;
        country = null;
        currency = "";
        createdAt = null;
        createdBy = null;
        modifiedAt = null;
        modifiedBy = null;
    }

    /**
     * Method to override timestamps to server timezone
     */
    @PostLoad
    private void convertTimestamps() {
        this.createdAt = OffsetDateTimeUtils.convertToServerTime(this.createdAt);
        this.modifiedAt = OffsetDateTimeUtils.convertToServerTime(this.createdAt);
    }

    public UUID getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getEmail() {
        return email;
    }

    public double getSalary() {
        return salary;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getModifiedAt() {
        return modifiedAt;
    }

    public Country getCountry() {
        return country;
    }

    public String getCurrency() {
        return currency;
    }

    public UUID getCreatedBy() {
        return createdBy;
    }

    public UUID getModifiedBy() {
        return modifiedBy;
    }

    @Override
    public String toString() {
        return String.format("ID=%s,\nName=%s,\nPosition=%s,\nEmail=%s,\nCountry=%s,\nSalary=%.2f,\nCurrency=%s,\nCreatedAt=%s,\nModifiedAt=%s,\nRecord Created By=%s,\nRecord Updated By=%s",
                employeeId, name, position, email, country, salary, currency, createdAt, modifiedAt, createdBy, modifiedBy);
    }
}
