-- EduBridge OS Academic Schema
-- Version: 1.0
-- Date: 2026-08-04

CREATE SCHEMA IF NOT EXISTS academic;

-- Universities table
CREATE TABLE academic.universities (
    id VARCHAR(50) PRIMARY KEY,
    code VARCHAR(20) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    country_code VARCHAR(10),
    city VARCHAR(100),
    website VARCHAR(500),
    institution_type VARCHAR(50),
    accreditation_status VARCHAR(50),
    accreditation_body VARCHAR(255),
    accreditation_date DATE,
    logo_url VARCHAR(500),
    description TEXT,
    established_date DATE,
    phone VARCHAR(20),
    email VARCHAR(255),
    address TEXT,
    is_partner BOOLEAN DEFAULT FALSE,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Academic programs table
CREATE TABLE academic.programs (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    university_id VARCHAR(50) REFERENCES academic.universities(id),
    code VARCHAR(50) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    degree_level VARCHAR(50) NOT NULL,
    faculty VARCHAR(255),
    department VARCHAR(255),
    duration_months INTEGER,
    study_mode VARCHAR(50),
    language VARCHAR(50),
    tuition_fee DECIMAL(15, 2),
    currency_code VARCHAR(3),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Admission requirements table
CREATE TABLE academic.admission_requirements (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    program_id UUID REFERENCES academic.programs(id),
    requirement_type VARCHAR(100) NOT NULL,
    description TEXT,
    minimum_score DECIMAL(5, 2),
    maximum_score DECIMAL(5, 2),
    is_mandatory BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Accreditation table
CREATE TABLE academic.accreditation (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    university_id VARCHAR(50) REFERENCES academic.universities(id),
    accrediting_body VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    valid_from DATE,
    valid_until DATE,
    certificate_url VARCHAR(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Indexes
CREATE INDEX idx_universities_code ON academic.universities(code);
CREATE INDEX idx_universities_country ON academic.universities(country_code);
CREATE INDEX idx_universities_type ON academic.universities(institution_type);
CREATE INDEX idx_programs_university ON academic.programs(university_id);
CREATE INDEX idx_programs_degree ON academic.programs(degree_level);
CREATE INDEX idx_programs_faculty ON academic.programs(faculty);
CREATE INDEX idx_admission_requirements_program ON academic.admission_requirements(program_id);
CREATE INDEX idx_accreditation_university ON academic.accreditation(university_id);
