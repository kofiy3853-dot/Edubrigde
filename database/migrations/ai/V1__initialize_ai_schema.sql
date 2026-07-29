-- EduBridge OS: Initialize AI schema
-- Version: V1__initialize_ai_schema.sql

CREATE SCHEMA IF NOT EXISTS ai;

-- Recommendations table
CREATE TABLE ai.recommendations (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    recommendation_type VARCHAR(64) NOT NULL,
    entity_type VARCHAR(64) NOT NULL,
    entity_id UUID NOT NULL,
    score DECIMAL(5,4) CHECK (score >= 0 AND score <= 1),
    reason TEXT,
    is_dismissed BOOLEAN DEFAULT false,
    dismissed_at TIMESTAMP WITH TIME ZONE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    version INTEGER DEFAULT 1
);

CREATE INDEX idx_recommendations_user_id ON ai.recommendations(user_id);
CREATE INDEX idx_recommendations_type ON ai.recommendations(recommendation_type);
CREATE INDEX idx_recommendations_entity ON ai.recommendations(entity_type, entity_id);
CREATE INDEX idx_recommendations_score ON ai.recommendations(score DESC);

-- Model versions table
CREATE TABLE ai.model_versions (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    model_name VARCHAR(128) NOT NULL,
    version VARCHAR(32) NOT NULL,
    description TEXT,
    parameters JSONB,
    metrics JSONB,
    is_active BOOLEAN DEFAULT true,
    trained_at TIMESTAMP WITH TIME ZONE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    UNIQUE(model_name, version)
);

CREATE INDEX idx_model_versions_model_name ON ai.model_versions(model_name);
CREATE INDEX idx_model_versions_is_active ON ai.model_versions(is_active);
