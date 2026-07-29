-- EduBridge OS: Initialize shared schema
-- Version: V1__initialize_shared_schema.sql

CREATE SCHEMA IF NOT EXISTS shared;

-- Audit table for tracking changes across all schemas
CREATE TABLE shared.audit_log (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    schema_name VARCHAR(64) NOT NULL,
    table_name VARCHAR(128) NOT NULL,
    record_id UUID NOT NULL,
    action VARCHAR(16) NOT NULL CHECK (action IN ('INSERT', 'UPDATE', 'DELETE')),
    old_values JSONB,
    new_values JSONB,
    performed_by UUID,
    performed_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    ip_address VARCHAR(45),
    user_agent TEXT
);

CREATE INDEX idx_audit_log_schema_table ON shared.audit_log(schema_name, table_name);
CREATE INDEX idx_audit_log_record_id ON shared.audit_log(record_id);
CREATE INDEX idx_audit_log_performed_at ON shared.audit_log(performed_at);
CREATE INDEX idx_audit_log_performed_by ON shared.audit_log(performed_by);

-- Countries reference table
CREATE TABLE shared.countries (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    code VARCHAR(2) NOT NULL UNIQUE,
    name VARCHAR(128) NOT NULL,
    is_active BOOLEAN DEFAULT true,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    deleted_at TIMESTAMP WITH TIME ZONE,
    version INTEGER DEFAULT 1
);

-- Currencies reference table
CREATE TABLE shared.currencies (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    code VARCHAR(3) NOT NULL UNIQUE,
    name VARCHAR(64) NOT NULL,
    symbol VARCHAR(8),
    decimal_places SMALLINT DEFAULT 2,
    is_active BOOLEAN DEFAULT true,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    deleted_at TIMESTAMP WITH TIME ZONE,
    version INTEGER DEFAULT 1
);

-- Configurations reference table
CREATE TABLE shared.configurations (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    config_key VARCHAR(128) NOT NULL UNIQUE,
    config_value TEXT NOT NULL,
    description TEXT,
    is_active BOOLEAN DEFAULT true,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    deleted_at TIMESTAMP WITH TIME ZONE,
    version INTEGER DEFAULT 1
);
