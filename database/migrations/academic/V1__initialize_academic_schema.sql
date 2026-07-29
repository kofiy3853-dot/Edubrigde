-- EduBridge OS: Initialize academic schema
-- Version: V1__initialize_academic_schema.sql

CREATE SCHEMA IF NOT EXISTS academic;

-- Students table
CREATE TABLE academic.students (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL UNIQUE,
    student_number VARCHAR(32) NOT NULL UNIQUE,
    date_of_birth DATE,
    nationality VARCHAR(2),
    address TEXT,
    is_active BOOLEAN DEFAULT true,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    deleted_at TIMESTAMP WITH TIME ZONE,
    version INTEGER DEFAULT 1
);

CREATE INDEX idx_students_user_id ON academic.students(user_id);
CREATE INDEX idx_students_student_number ON academic.students(student_number);

-- Courses table
CREATE TABLE academic.courses (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    code VARCHAR(32) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    credits INTEGER NOT NULL CHECK (credits > 0),
    is_active BOOLEAN DEFAULT true,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    deleted_at TIMESTAMP WITH TIME ZONE,
    version INTEGER DEFAULT 1
);

CREATE INDEX idx_courses_code ON academic.courses(code);

-- Learning progress table
CREATE TABLE academic.learning_progress (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    student_id UUID NOT NULL REFERENCES academic.students(id),
    course_id UUID NOT NULL REFERENCES academic.courses(id),
    status VARCHAR(32) NOT NULL DEFAULT 'enrolled' CHECK (status IN ('enrolled', 'in_progress', 'completed', 'dropped')),
    progress_percentage DECIMAL(5,2) DEFAULT 0 CHECK (progress_percentage >= 0 AND progress_percentage <= 100),
    enrolled_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    completed_at TIMESTAMP WITH TIME ZONE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    deleted_at TIMESTAMP WITH TIME ZONE,
    version INTEGER DEFAULT 1,
    UNIQUE(student_id, course_id)
);

CREATE INDEX idx_learning_progress_student_id ON academic.learning_progress(student_id);
CREATE INDEX idx_learning_progress_course_id ON academic.learning_progress(course_id);
CREATE INDEX idx_learning_progress_status ON academic.learning_progress(status);
