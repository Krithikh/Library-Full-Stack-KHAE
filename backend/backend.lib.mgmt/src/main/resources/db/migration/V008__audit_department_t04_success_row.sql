-- T04 final audit alignment.
-- Department 1/2/4 all have active Members in the deterministic training data.
-- Add one explicit active Department with no Member so the successful logical
-- Deactivate path and the dependency-block path can both be demonstrated.
INSERT INTO tbl_department
(department_id, department_code, department_name, is_active)
VALUES
(5, 'IT', 'Information Technology', TRUE);

ALTER TABLE tbl_department ALTER COLUMN department_id RESTART WITH 100;
