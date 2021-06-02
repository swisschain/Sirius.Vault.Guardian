ALTER TABLE ${flyway:defaultSchema}.transfer_validation_requests
	ADD COLUMN vault_id bigint NOT NULL DEFAULT 0;

ALTER TABLE ${flyway:defaultSchema}.smart_contract_deployment_validation_requests
	ADD COLUMN vault_id bigint NOT NULL DEFAULT 0;

ALTER TABLE ${flyway:defaultSchema}.smart_contract_invocation_validation_requests
	ADD COLUMN vault_id bigint NOT NULL DEFAULT 0;