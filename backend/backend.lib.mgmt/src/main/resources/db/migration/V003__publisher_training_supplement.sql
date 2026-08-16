-- Additional deterministic Publisher row used by T24 Deactivate Publisher.
-- It is active and intentionally has no Book dependency, so the successful
-- logical-deactivation path can be tested before the dependency-block case.
INSERT INTO tbl_publisher (
    publisher_id,
    publisher_code,
    publisher_name,
    is_active
) VALUES (
    5,
    'PUB-SAGE',
    'Sage Publications',
    TRUE
);
