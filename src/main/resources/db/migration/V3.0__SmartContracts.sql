DROP TABLE ${flyway:defaultSchema}.transfer_validation_requests;
DROP TABLE ${flyway:defaultSchema}.validator_requests;
DROP TABLE ${flyway:defaultSchema}.validator_responses;

CREATE TABLE ${flyway:defaultSchema}.transfer_validation_requests
(
    "id"                           bigint                   NOT NULL,
    "tenant_id"                    text                     NOT NULL,
    "transfer"                     text                     NOT NULL,
    "status"                       text                     NOT NULL,
    "document"                     text                     NULL,
    "signature"                    text                     NULL,
    "reject_reason_message"        text                     NULL,
    "created_at"                   timestamp with time zone NOT NULL,
    "updated_at"                   timestamp with time zone NOT NULL,
    CONSTRAINT "pk_transfer_validation_requests" PRIMARY KEY ("id")
);

CREATE TABLE ${flyway:defaultSchema}.smart_contract_deployment_validation_requests
(
    "id"                           bigint                   NOT NULL,
    "tenant_id"                    text                     NULL,
    "smart_contract_deployment"    text                     NOT NULL,
    "status"                       text                     NOT NULL,
    "document"                     text                     NULL,
    "signature"                    text                     NULL,
    "reject_reason_message"        text                     NULL,
    "created_at"                   timestamp with time zone NOT NULL,
    "updated_at"                   timestamp with time zone NOT NULL,
    CONSTRAINT "pk_smart_contract_deployment_validation_requests" PRIMARY KEY ("id")
);

CREATE TABLE ${flyway:defaultSchema}.validator_requests
(
    "id"                           text                     NOT NULL,
    "validator_id"                 text                     NOT NULL,
    "tenant_id"                    text                     NOT NULL,
    "message"                      text                     NOT NULL,
    "key"                          text                     NOT NULL,
    "nonce"                        text                     NOT NULL,
    "status"                       text                     NOT NULL,
    "type"                         text                     NOT NULL,
    "validation_request_id"        bigint                   NOT NULL,
    "document"                     text                     NULL,
    "signature"                    text                     NULL,
    "resolution"                   text                     NULL,
    "resolution_message"           text                     NULL,
    "device_info"                  text                     NULL,
    "ip"                           text                     NULL,
    "created_at"                   timestamp with time zone NOT NULL,
    "updated_at"                   timestamp with time zone NOT NULL,
    CONSTRAINT "pk_validator_requests" PRIMARY KEY ("id")
);

ALTER TABLE ${flyway:defaultSchema}.transfer_validation_requests
    OWNER to ${flyway:user};

ALTER TABLE ${flyway:defaultSchema}.smart_contract_deployment_validation_requests
    OWNER to ${flyway:user};

ALTER TABLE ${flyway:defaultSchema}.validator_requests
    OWNER to ${flyway:user};
