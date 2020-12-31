INSERT INTO role(role_id, type, description, created_date, last_updated, created_by, last_modified_by, version)
VALUES (uuid_generate_v4(), 'ROLE_USER', 'USER', now(), now(),'Saga', 'Saga', 1);

INSERT INTO role(role_id, type, description, created_date, last_updated, created_by, last_modified_by, version)
VALUES (uuid_generate_v4(), 'ROLE_ADMIN', 'ADMIN', now(), now(), 'Saga', 'Saga', 1);