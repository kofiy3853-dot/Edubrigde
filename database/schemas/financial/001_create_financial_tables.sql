-- EduBridge OS Financial Schema
-- Version: 1.0
-- Date: 2026-08-04

CREATE SCHEMA IF NOT EXISTS financial;

-- Scholarship catalog table
CREATE TABLE financial.scholarship_catalog (
    id VARCHAR(50) PRIMARY KEY,
    code VARCHAR(50) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    scholarship_type VARCHAR(100),
    funding_organization VARCHAR(255),
    country_code VARCHAR(10),
    currency_code VARCHAR(3),
    min_amount DECIMAL(15, 2),
    max_amount DECIMAL(15, 2),
    coverage_type VARCHAR(50),
    academic_requirement TEXT,
    financial_requirement TEXT,
    application_deadline TIMESTAMP,
    academic_year VARCHAR(20),
    is_renewable BOOLEAN DEFAULT FALSE,
    renewal_criteria TEXT,
    max_recipients INTEGER,
    current_recipients INTEGER DEFAULT 0,
    eligible_countries TEXT,
    eligible_programs TEXT,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Cost estimates table
CREATE TABLE financial.cost_estimates (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    university_id VARCHAR(50) NOT NULL,
    program_id UUID,
    academic_year VARCHAR(20),
    tuition_fee DECIMAL(15, 2),
    accommodation_cost DECIMAL(15, 2),
    living_expenses DECIMAL(15, 2),
    insurance_cost DECIMAL(15, 2),
    other_fees DECIMAL(15, 2),
    total_cost DECIMAL(15, 2),
    currency_code VARCHAR(3),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Financial profiles table
CREATE TABLE financial.financial_profiles (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    annual_income DECIMAL(15, 2),
    family_size INTEGER,
    dependents INTEGER DEFAULT 0,
    financial_need_level VARCHAR(50),
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Financial assessments table
CREATE TABLE financial.financial_assessments (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    assessment_type VARCHAR(100),
    result VARCHAR(50),
    score DECIMAL(5, 2),
    notes TEXT,
    assessed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Indexes
CREATE INDEX idx_scholarship_code ON financial.scholarship_catalog(code);
CREATE INDEX idx_scholarship_type ON financial.scholarship_catalog(scholarship_type);
CREATE INDEX idx_scholarship_country ON financial.scholarship_catalog(country_code);
CREATE INDEX idx_scholarship_active ON financial.scholarship_catalog(is_active);
CREATE INDEX idx_cost_estimates_university ON financial.cost_estimates(university_id);
CREATE INDEX idx_cost_estimates_program ON financial.cost_estimates(program_id);
CREATE INDEX idx_financial_profiles_user ON financial.financial_profiles(user_id);
CREATE INDEX idx_financial_assessments_user ON financial.financial_assessments(user_id);
