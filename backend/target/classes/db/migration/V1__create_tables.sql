CREATE TABLE membership_form
(
    id                 BIGSERIAL PRIMARY KEY,
    club_id            VARCHAR(64)              NOT NULL,
    form_id            VARCHAR(32)              NOT NULL,
    title              VARCHAR(255)             NOT NULL,
    description        TEXT,
    registration_opens TIMESTAMP WITH TIME ZONE NOT NULL,
    CONSTRAINT uq_membership_form_form_id UNIQUE (form_id)
);

CREATE TABLE member_type
(
    id   VARCHAR(32)  PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE membership_form_member_type
(
    membership_form_id BIGINT      NOT NULL,
    member_type_id     VARCHAR(32) NOT NULL,
    PRIMARY KEY (membership_form_id, member_type_id),
    CONSTRAINT fk_membership_form_member_type_form
        FOREIGN KEY (membership_form_id)
            REFERENCES membership_form (id)
            ON DELETE CASCADE,
    CONSTRAINT fk_membership_form_member_type_member_type
        FOREIGN KEY (member_type_id)
            REFERENCES member_type (id)
            ON DELETE CASCADE
);

CREATE TABLE registration
(
    id                 BIGSERIAL PRIMARY KEY,
    membership_form_id BIGINT                   NOT NULL,
    member_type_id     VARCHAR(32)              NOT NULL,
    full_name          VARCHAR(150)             NOT NULL,
    email              VARCHAR(255)             NOT NULL,
    phone_number       VARCHAR(50)              NOT NULL,
    birth_date         DATE                     NOT NULL,
    status             VARCHAR(32)              NOT NULL DEFAULT 'PENDING',
    submitted_at       TIMESTAMP WITH TIME ZONE NOT NULL,
    CONSTRAINT uq_registration_form_email UNIQUE (membership_form_id, email),
    CONSTRAINT fk_registration_form
        FOREIGN KEY (membership_form_id)
            REFERENCES membership_form (id)
            ON DELETE CASCADE,
    CONSTRAINT fk_registration_member_type
        FOREIGN KEY (member_type_id)
            REFERENCES member_type (id)
            ON DELETE RESTRICT
);

CREATE INDEX idx_membership_form_club_id ON membership_form (club_id);
CREATE INDEX idx_form_member_type_member_type_id ON membership_form_member_type (member_type_id);
CREATE INDEX idx_registration_form_id ON registration (membership_form_id);
CREATE INDEX idx_registration_member_type_id ON registration (member_type_id);
CREATE INDEX idx_registration_email ON registration (email);
