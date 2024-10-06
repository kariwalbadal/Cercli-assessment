CREATE OR REPLACE FUNCTION audit_employee_changes()
RETURNS TRIGGER AS $$
BEGIN
    IF (TG_OP = 'INSERT') THEN
INSERT INTO employee_audit(employee_id, name, position, email, salary, country, currency, modified_at, modified_by, change_type)
VALUES (NEW.employee_id, NEW.name, NEW.position, NEW.email, NEW.salary, NEW.country, NEW.currency, NEW.created_at, NEW.created_by, 'INSERT');
RETURN NEW;
ELSIF (TG_OP = 'UPDATE') THEN
INSERT INTO employee_audit(employee_id, name, position, email, salary, country, currency, modified_at, modified_by, change_type)
VALUES (NEW.employee_id, NEW.name, NEW.position, NEW.email, NEW.salary, NEW.country, NEW.currency, NEW.modified_at, NEW.modified_by, 'UPDATE');
RETURN NEW;
ELSIF (TG_OP = 'DELETE') THEN
INSERT INTO employee_audit(employee_id, name, position, email, salary, country, currency, modified_at, modified_by, change_type)
VALUES (OLD.employee_id, OLD.name, OLD.position, OLD.email, OLD.salary, OLD.country, OLD.currency, NOW(), OLD.modified_by, 'DELETE');
RETURN OLD;
END IF;
END;
$$ LANGUAGE plpgsql;