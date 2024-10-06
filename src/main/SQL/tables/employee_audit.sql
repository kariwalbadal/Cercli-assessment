CREATE TABLE employee_audit (
                                audit_id SERIAL PRIMARY KEY,
                                employee_id UUID NOT NULL,
                                name VARCHAR(100),
                                position VARCHAR(100),
                                email VARCHAR(100),
                                salary NUMERIC(10, 2),
                                country VARCHAR(50),
                                currency VARCHAR(3),
                                modified_at TIMESTAMP WITH TIME ZONE NOT NULL,
                                modified_by UUID NOT NULL,
                                change_type VARCHAR(10) NOT NULL,  -- 'INSERT', 'UPDATE', or 'DELETE'
                                CONSTRAINT fk_employee
                                    FOREIGN KEY (employee_id)
                                        REFERENCES employees (employee_id)
);
