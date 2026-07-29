-- EduBridge OS: Initialize communication schema
-- Version: V1__initialize_communication_schema.sql

CREATE SCHEMA IF NOT EXISTS communication;

-- Notifications table
CREATE TABLE communication.notifications (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    title VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,
    type VARCHAR(32) NOT NULL CHECK (type IN ('info', 'warning', 'success', 'error')),
    is_read BOOLEAN DEFAULT false,
    read_at TIMESTAMP WITH TIME ZONE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    deleted_at TIMESTAMP WITH TIME ZONE,
    version INTEGER DEFAULT 1
);

CREATE INDEX idx_notifications_user_id ON communication.notifications(user_id);
CREATE INDEX idx_notifications_is_read ON communication.notifications(is_read);
CREATE INDEX idx_notifications_created_at ON communication.notifications(created_at);

-- Messages table
CREATE TABLE communication.messages (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    sender_id UUID NOT NULL,
    recipient_id UUID NOT NULL,
    subject VARCHAR(255),
    body TEXT NOT NULL,
    is_read BOOLEAN DEFAULT false,
    read_at TIMESTAMP WITH TIME ZONE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    deleted_at TIMESTAMP WITH TIME ZONE,
    version INTEGER DEFAULT 1
);

CREATE INDEX idx_messages_sender_id ON communication.messages(sender_id);
CREATE INDEX idx_messages_recipient_id ON communication.messages(recipient_id);
CREATE INDEX idx_messages_is_read ON communication.messages(is_read);
