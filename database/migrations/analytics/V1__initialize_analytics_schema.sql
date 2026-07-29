-- EduBridge OS: Initialize analytics schema
-- Version: V1__initialize_analytics_schema.sql

CREATE SCHEMA IF NOT EXISTS analytics;

-- Events table for tracking user actions
CREATE TABLE analytics.events (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID,
    event_type VARCHAR(64) NOT NULL,
    entity_type VARCHAR(64),
    entity_id UUID,
    metadata JSONB,
    ip_address VARCHAR(45),
    user_agent TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);

CREATE INDEX idx_events_user_id ON analytics.events(user_id);
CREATE INDEX idx_events_event_type ON analytics.events(event_type);
CREATE INDEX idx_events_entity ON analytics.events(entity_type, entity_id);
CREATE INDEX idx_events_created_at ON analytics.events(created_at);

-- Reports table
CREATE TABLE analytics.reports (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    report_type VARCHAR(64) NOT NULL,
    parameters JSONB,
    result JSONB,
    generated_by UUID,
    generated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    expires_at TIMESTAMP WITH TIME ZONE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    version INTEGER DEFAULT 1
);

CREATE INDEX idx_reports_name ON analytics.reports(name);
CREATE INDEX idx_reports_report_type ON analytics.reports(report_type);
