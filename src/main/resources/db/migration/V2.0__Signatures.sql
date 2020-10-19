DROP TABLE ${flyway:defaultSchema}.transfer_validation_requests;

CREATE TABLE ${flyway:defaultSchema}.transfer_validation_requests
(
    "id"                           bigint                   NOT NULL,
    "tenant_id"                    text                     NULL,
    "transfer_details"             text                     NOT NULL,
    "status"                       text                     NOT NULL,
    "document"                     text                     NULL,
    "signature"                    text                     NULL,
    "reject_reason_message"        text                     NULL,
    "created_at"                   timestamp with time zone NOT NULL,
    "updated_at"                   timestamp with time zone NOT NULL,
    CONSTRAINT "pk_transfer_validation_requests" PRIMARY KEY ("id")
);

CREATE TABLE ${flyway:defaultSchema}.validator_responses
(
    "validator_id"                 text                     NOT NULL,
    "transfer_validation_request_id" bigint                   NOT NULL,
    "document"                     text                     NOT NULL,
    "signature"                    text                     NOT NULL,
    "resolution"                   text                     NOT NULL,
    "resolution_message"           text                     NULL,
    "device_info"                  text                     NULL,
    "ip"                           text                     NULL,
    "created_at"                   timestamp with time zone NOT NULL,
    CONSTRAINT "pk_validator_responses" PRIMARY KEY ("validator_id", "transfer_validation_request_id"),
    CONSTRAINT "fk_validator_responses_transfer_validation_requests" FOREIGN KEY ("transfer_validation_request_id") REFERENCES ${flyway:defaultSchema}.transfer_validation_requests ("id") ON DELETE CASCADE
);

CREATE TABLE ${flyway:defaultSchema}.validator_requests
(
    "validator_id"                 text                     NOT NULL,
    "transfer_validation_request_id" bigint                   NOT NULL,
    "encrypted_message"            text                     NOT NULL,
    "encrypted_key"                text                     NOT NULL,
    "key"                          text                     NOT NULL,
    "nonce"                        text                     NOT NULL,
    "created_at"                   timestamp with time zone NOT NULL,
    CONSTRAINT "pk_validator_requests" PRIMARY KEY ("validator_id", "transfer_validation_request_id"),
    CONSTRAINT "fk_validator_requests_transfer_validation_requests" FOREIGN KEY ("transfer_validation_request_id") REFERENCES ${flyway:defaultSchema}.transfer_validation_requests ("id") ON DELETE CASCADE
);


ALTER TABLE ${flyway:defaultSchema}.transfer_validation_requests
    OWNER to ${flyway:user};

ALTER TABLE ${flyway:defaultSchema}.validator_requests
    OWNER to ${flyway:user};

ALTER TABLE ${flyway:defaultSchema}.validator_responses
    OWNER to ${flyway:user};