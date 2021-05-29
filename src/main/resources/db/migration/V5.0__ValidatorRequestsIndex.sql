ALTER TABLE ${flyway:defaultSchema}.validator_requests
	ADD CONSTRAINT ux_validator_request UNIQUE ("validator_id", "validation_request_id");