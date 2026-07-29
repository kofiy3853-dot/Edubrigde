-- EduBridge OS: Initialize career schema
-- Version: V1__initialize_career_schema.sql

CREATE SCHEMA IF NOT EXISTS career;

-- Job listings table
CREATE TABLE career.job_listings (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    company_name VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    employment_type VARCHAR(32) CHECK (employment_type IN ('full_time', 'part_time', 'contract', 'internship')),
    salary_min DECIMAL(12,2),
    salary_max DECIMAL(12,2),
    currency_code VARCHAR(3),
    is_remote BOOLEAN DEFAULT false,
    is_active BOOLEAN DEFAULT true,
    posted_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    expires_at TIMESTAMP WITH TIME ZONE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    deleted_at TIMESTAMP WITH TIME ZONE,
    version INTEGER DEFAULT 1
);

CREATE INDEX idx_job_listings_title ON career.job_listings(title);
CREATE INDEX idx_job_listings_company ON career.job_listings(company_name);
CREATE INDEX idx_job_listings_is_active ON career.job_listings(is_active);
CREATE INDEX idx_job_listings_posted_at ON career.job_listings(posted_at);

-- Job applications table
CREATE TABLE career.job_applications (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    student_id UUID NOT NULL,
    job_listing_id UUID NOT NULL REFERENCES career.job_listings(id),
    status VARCHAR(32) NOT NULL DEFAULT 'submitted' CHECK (status IN ('submitted', 'reviewed', 'interview', 'accepted', 'rejected')),
    cover_letter TEXT,
    resume_url VARCHAR(512),
    applied_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    reviewed_at TIMESTAMP WITH TIME ZONE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    deleted_at TIMESTAMP WITH TIME ZONE,
    version INTEGER DEFAULT 1,
    UNIQUE(student_id, job_listing_id)
);

CREATE INDEX idx_job_applications_student_id ON career.job_applications(student_id);
CREATE INDEX idx_job_applications_job_listing_id ON career.job_applications(job_listing_id);
CREATE INDEX idx_job_applications_status ON career.job_applications(status);
