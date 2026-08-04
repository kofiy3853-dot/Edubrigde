-- EduBridge OS Admissions Schema
-- Version: 1.0
-- Date: 2026-08-04

CREATE SCHEMA IF NOT EXISTS admissions;

-- Admission applications table
CREATE TABLE admissions.applications (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    application_reference VARCHAR(50) UNIQUE NOT NULL,
    applicant_id UUID NOT NULL,
    university_id VARCHAR(50) NOT NULL,
    program_id UUID NOT NULL,
    admission_cycle VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'DRAFT',
    submitted_at TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Eligibility assessments table
CREATE TABLE admissions.eligibility_assessments (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    application_id UUID REFERENCES admissions.applications(id),
    result VARCHAR(50) NOT NULL,
    missing_requirements TEXT,
    verification_notes TEXT,
    assessed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Admission reviews table
CREATE TABLE admissions.reviews (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    application_id UUID REFERENCES admissions.applications(id),
    reviewer_id UUID NOT NULL,
    review_type VARCHAR(50) NOT NULL,
    outcome VARCHAR(50),
    score DECIMAL(5, 2),
    comments TEXT,
    status VARCHAR(50) DEFAULT 'CREATED',
    started_at TIMESTAMP,
    completed_at TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Admission decisions table
CREATE TABLE admissions.decisions (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    application_id UUID REFERENCES admissions.applications(id),
    decision_type VARCHAR(50) NOT NULL,
    decision_date TIMESTAMP NOT NULL,
    approving_authority VARCHAR(255),
    reason TEXT,
    conditions TEXT,
    is_final BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Admission offers table
CREATE TABLE admissions.offers (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    application_id UUID REFERENCES admissions.applications(id),
    decision_id UUID REFERENCES admissions.decisions(id),
    offer_reference VARCHAR(50) UNIQUE NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'GENERATED',
    issued_at TIMESTAMP NOT NULL,
    expires_at TIMESTAMP NOT NULL,
    accepted_at TIMESTAMP,
    declined_at TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Waitlist entries table
CREATE TABLE admissions.waitlist_entries (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    application_id UUID REFERENCES admissions.applications(id),
    position INTEGER NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'ACTIVE',
    added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    promoted_at TIMESTAMP,
    removed_at TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Admission timeline table
CREATE TABLE admissions.timeline (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    application_id UUID REFERENCES admissions.applications(id),
    event_type VARCHAR(100) NOT NULL,
    event_description TEXT,
    actor_id UUID,
    event_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    metadata JSONB,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Indexes
CREATE INDEX idx_applications_reference ON admissions.applications(application_reference);
CREATE INDEX idx_applications_applicant ON admissions.applications(applicant_id);
CREATE INDEX idx_applications_university ON admissions.applications(university_id);
CREATE INDEX idx_applications_program ON admissions.applications(program_id);
CREATE INDEX idx_applications_status ON admissions.applications(status);
CREATE INDEX idx_applications_cycle ON admissions.applications(admission_cycle);
CREATE INDEX idx_eligibility_application ON admissions.eligibility_assessments(application_id);
CREATE INDEX idx_reviews_application ON admissions.reviews(application_id);
CREATE INDEX idx_reviews_reviewer ON admissions.reviews(reviewer_id);
CREATE INDEX idx_decisions_application ON admissions.decisions(application_id);
CREATE INDEX idx_offers_application ON admissions.offers(application_id);
CREATE INDEX idx_offers_reference ON admissions.offers(offer_reference);
CREATE INDEX idx_waitlist_application ON admissions.waitlist_entries(application_id);
CREATE INDEX idx_waitlist_position ON admissions.waitlist_entries(position);
CREATE INDEX idx_timeline_application ON admissions.timeline(application_id);
CREATE INDEX idx_timeline_event ON admissions.timeline(event_type);
