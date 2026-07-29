-- EduBridge OS: Initialize institution schema
-- Version: V1__initialize_institution_schema.sql

CREATE SCHEMA IF NOT EXISTS institution;

-- Universities table
CREATE TABLE institution.universities (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    code VARCHAR(32) NOT NULL UNIQUE,
    description TEXT,
    website VARCHAR(512),
    email VARCHAR(255),
    phone VARCHAR(32),
    address TEXT,
    country_code VARCHAR(2),
    is_active BOOLEAN DEFAULT true,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    deleted_at TIMESTAMP WITH TIME ZONE,
    version INTEGER DEFAULT 1
);

CREATE INDEX idx_universities_name ON institution.universities(name);
CREATE INDEX idx_universities_code ON institution.universities(code);
CREATE INDEX idx_universities_country_code ON institution.universities(country_code);

-- Programs table
CREATE TABLE institution.programs (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    university_id UUID NOT NULL REFERENCES institution.universities(id),
    name VARCHAR(255) NOT NULL,
    code VARCHAR(32) NOT NULL,
    description TEXT,
    degree_level VARCHAR(32) CHECK (degree_level IN ('certificate', 'diploma', 'bachelors', 'masters', 'doctorate')),
    duration_months INTEGER,
    tuition_fee DECIMAL(12,2),
    currency_code VARCHAR(3),
    is_active BOOLEAN DEFAULT true,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    deleted_at TIMESTAMP WITH TIME ZONE,
    version INTEGER DEFAULT 1,
    UNIQUE(university_id, code)
);

CREATE INDEX idx_programs_university_id ON institution.programs(university_id);
CREATE INDEX idx_programs_degree_level ON institution.programs(degree_level);

-- Applications table
CREATE TABLE institution.applications (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    student_id UUID NOT NULL,
    program_id UUID NOT NULL REFERENCES institution.programs(id),
    status VARCHAR(32) NOT NULL DEFAULT 'submitted' CHECK (status IN ('submitted', 'under_review', 'accepted', 'rejected', 'waitlisted')),
    submitted_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    reviewed_at TIMESTAMP WITH TIME ZONE,
    decision_at TIMESTAMP WITH TIME ZONE,
    notes TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    deleted_at TIMESTAMP WITH TIME ZONE,
    version INTEGER DEFAULT 1
);

CREATE INDEX idx_applications_student_id ON institution.applications(student_id);
CREATE INDEX idx_applications_program_id ON institution.applications(program_id);
CREATE INDEX idx_applications_status ON institution.applications(status);
