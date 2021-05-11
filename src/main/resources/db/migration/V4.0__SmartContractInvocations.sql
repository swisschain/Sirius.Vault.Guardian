CREATE TABLE ${flyway:defaultSchema}.smart_contract_invocation_validation_requests
(
    "id"                           bigint                   NOT NULL,
    "tenant_id"                    text                     NULL,
    "smart_contract_invocation"    text                     NOT NULL,
    "status"                       text                     NOT NULL,
    "document"                     text                     NULL,
    "signature"                    text                     NULL,
    "reject_reason_message"        text                     NULL,
    "created_at"                   timestamp with time zone NOT NULL,
    "updated_at"                   timestamp with time zone NOT NULL,
    CONSTRAINT "pk_smart_contract_invocation_validation_requests" PRIMARY KEY ("id")
);

ALTER TABLE ${flyway:defaultSchema}.smart_contract_invocation_validation_requests
    OWNER to ${flyway:user};
