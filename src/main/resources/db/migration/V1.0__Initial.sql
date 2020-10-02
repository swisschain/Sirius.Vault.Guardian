CREATE TABLE ${flyway:defaultSchema}.transfer_validation_requests
(
    "id"                           bigint                   NOT NULL,
    "details"                      text                     NOT NULL,
    "approval_context"             text                     NOT NULL,
    "customer_signature"           text                     NOT NULL,
    "sirius_signature"             text                     NOT NULL,
    "status"                       text                     NOT NULL,
    "created_at"                   timestamp with time zone NOT NULL,
    "updated_at"                   timestamp with time zone NOT NULL,
    CONSTRAINT "pk_transfer_validation_requests" PRIMARY KEY ("id")
);

ALTER TABLE ${flyway:defaultSchema}.transfer_validation_requests
    OWNER to ${flyway:user};