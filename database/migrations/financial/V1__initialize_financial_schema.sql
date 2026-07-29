-- EduBridge OS: Initialize financial schema
-- Version: V1__initialize_financial_schema.sql

CREATE SCHEMA IF NOT EXISTS financial;

-- Payments table
CREATE TABLE financial.payments (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    student_id UUID NOT NULL,
    amount DECIMAL(12,2) NOT NULL CHECK (amount > 0),
    currency_code VARCHAR(3) NOT NULL,
    status VARCHAR(32) NOT NULL DEFAULT 'pending' CHECK (status IN ('pending', 'processing', 'completed', 'failed', 'refunded')),
    payment_method VARCHAR(32),
    reference_number VARCHAR(128) UNIQUE,
    description TEXT,
    paid_at TIMESTAMP WITH TIME ZONE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    deleted_at TIMESTAMP WITH TIME ZONE,
    version INTEGER DEFAULT 1
);

CREATE INDEX idx_payments_student_id ON financial.payments(student_id);
CREATE INDEX idx_payments_status ON financial.payments(status);
CREATE INDEX idx_payments_reference_number ON financial.payments(reference_number);
CREATE INDEX idx_payments_paid_at ON financial.payments(paid_at);

-- Budgets table
CREATE TABLE financial.budgets (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    student_id UUID NOT NULL,
    name VARCHAR(128) NOT NULL,
    total_amount DECIMAL(12,2) NOT NULL CHECK (total_amount > 0),
    spent_amount DECIMAL(12,2) DEFAULT 0,
    currency_code VARCHAR(3) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    is_active BOOLEAN DEFAULT true,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    deleted_at TIMESTAMP WITH TIME ZONE,
    version INTEGER DEFAULT 1
);

CREATE INDEX idx_budgets_student_id ON financial.budgets(student_id);
CREATE INDEX idx_budgets_is_active ON financial.budgets(is_active);

-- Scholarships table
CREATE TABLE financial.scholarships (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    description TEXT,
    amount DECIMAL(12,2) NOT NULL CHECK (amount > 0),
    currency_code VARCHAR(3) NOT NULL,
    eligibility_criteria JSONB,
    application_deadline DATE,
    is_active BOOLEAN DEFAULT true,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    deleted_at TIMESTAMP WITH TIME ZONE,
    version INTEGER DEFAULT 1
);

CREATE INDEX idx_scholarships_name ON financial.scholarships(name);
CREATE INDEX idx_scholarships_is_active ON financial.scholarships(is_active);
