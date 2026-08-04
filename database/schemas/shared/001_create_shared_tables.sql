-- EduBridge OS Shared Schema
-- Version: 1.0
-- Date: 2026-08-04

CREATE SCHEMA IF NOT EXISTS shared;

-- Countries table
CREATE TABLE shared.countries (
    code VARCHAR(10) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    region VARCHAR(100),
    sub_region VARCHAR(100),
    currency_code VARCHAR(3),
    phone_code VARCHAR(10),
    is_active BOOLEAN DEFAULT TRUE
);

-- Currencies table
CREATE TABLE shared.currencies (
    code VARCHAR(3) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    symbol VARCHAR(10),
    decimal_places INTEGER DEFAULT 2,
    is_active BOOLEAN DEFAULT TRUE
);

-- Languages table
CREATE TABLE shared.languages (
    code VARCHAR(10) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    native_name VARCHAR(100),
    is_active BOOLEAN DEFAULT TRUE
);

-- File storage table
CREATE TABLE shared.file_storage (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    filename VARCHAR(255) NOT NULL,
    original_filename VARCHAR(255),
    content_type VARCHAR(100),
    size_bytes BIGINT,
    storage_path VARCHAR(500) NOT NULL,
    uploaded_by UUID,
    is_public BOOLEAN DEFAULT FALSE,
    metadata JSONB,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Notifications table
CREATE TABLE shared.notifications (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    notification_type VARCHAR(100) NOT NULL,
    title VARCHAR(255) NOT NULL,
    message TEXT,
    is_read BOOLEAN DEFAULT FALSE,
    read_at TIMESTAMP,
    metadata JSONB,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Indexes
CREATE INDEX idx_countries_name ON shared.countries(name);
CREATE INDEX idx_countries_region ON shared.countries(region);
CREATE INDEX idx_file_storage_uploaded ON shared.file_storage(uploaded_by);
CREATE INDEX idx_notifications_user ON shared.notifications(user_id);
CREATE INDEX idx_notifications_type ON shared.notifications(notification_type);
CREATE INDEX idx_notifications_read ON shared.notifications(is_read);
