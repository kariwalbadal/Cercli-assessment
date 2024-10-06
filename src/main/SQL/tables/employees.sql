CREATE TABLE employees (
                           employee_id UUID PRIMARY KEY,
                           name VARCHAR(100) NOT NULL,
                           position VARCHAR(100) NOT NULL,
                           email VARCHAR(100) NOT NULL UNIQUE,
                           salary NUMERIC(10, 2) NOT NULL,
                           country VARCHAR(50) NOT NULL,
                           currency VARCHAR(3) NOT NULL,
                           created_at TIMESTAMP WITH TIME ZONE NOT NULL,
                           created_by UUID NOT NULL,
                           modified_at TIMESTAMP WITH TIME ZONE,
                           modified_by UUID
);