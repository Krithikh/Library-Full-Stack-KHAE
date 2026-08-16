-- V002: deterministic presenter seed data used by the student guides and integration tests.
INSERT INTO tbl_department (department_id, department_code, department_name, is_active) VALUES
(1, 'CSE', 'Computer Science and Engineering', TRUE),
(2, 'ECE', 'Electronics Engineering', TRUE),
(3, 'CIVIL', 'Civil Engineering', FALSE),
(4, 'MECH', 'Mechanical Engineering', TRUE);
ALTER TABLE tbl_department ALTER COLUMN department_id RESTART WITH 100;

INSERT INTO tbl_member (member_id, registration_number, full_name, email, fk_department, is_active) VALUES
(1, 'REG-CSE-001', 'Arun Kumar', 'arun@example.edu', 1, TRUE),
(2, 'REG-ECE-001', 'Priya Devi', 'priya@example.edu', 2, TRUE),
(3, 'REG-CIV-001', 'Legacy Member', 'legacy@example.edu', 3, FALSE),
(4, 'REG-MECH-001', 'Meena S', 'meena@example.edu', 4, TRUE);
ALTER TABLE tbl_member ALTER COLUMN member_id RESTART WITH 100;

INSERT INTO tbl_author (author_id, author_code, author_name, is_active) VALUES
(1, 'AUT-001', 'Robert C. Martin', TRUE),
(2, 'AUT-002', 'Joshua Bloch', TRUE),
(3, 'AUT-OLD', 'Archived Author', FALSE),
(4, 'AUT-004', 'Unused Author', TRUE);
ALTER TABLE tbl_author ALTER COLUMN author_id RESTART WITH 100;

INSERT INTO tbl_category (category_id, category_code, category_name, is_active) VALUES
(1, 'PROGRAMMING', 'Programming', TRUE),
(2, 'DATABASE', 'Database Systems', TRUE),
(3, 'LEGACY', 'Archived Category', FALSE),
(4, 'NETWORK', 'Computer Networks', TRUE);
ALTER TABLE tbl_category ALTER COLUMN category_id RESTART WITH 100;

INSERT INTO tbl_publisher (publisher_id, publisher_code, publisher_name, is_active) VALUES
(1, 'PUB-PRENTICE', 'Prentice Hall', TRUE),
(2, 'PUB-ADDISON', 'Addison-Wesley', TRUE),
(3, 'PUB-OLD', 'Archived Publisher', FALSE),
(4, 'PUB-OREILLY', 'O''Reilly Media', TRUE);
ALTER TABLE tbl_publisher ALTER COLUMN publisher_id RESTART WITH 100;

INSERT INTO tbl_book (book_id, isbn, title, fk_author, fk_category, fk_publisher, is_active) VALUES
(1, '9780132350884', 'Clean Code', 1, 1, 1, TRUE),
(2, '9780134685991', 'Effective Java', 2, 1, 2, TRUE),
(3, '9780000000003', 'Archived Programming Book', 3, 3, 3, FALSE),
(4, '9780000000004', 'Database Fundamentals', 4, 2, 4, TRUE);
ALTER TABLE tbl_book ALTER COLUMN book_id RESTART WITH 100;

INSERT INTO tbl_book_copy (book_copy_id, accession_number, fk_book, status) VALUES
(1, 'ACC-0001', 1, 'AVAILABLE'),
(2, 'ACC-0002', 1, 'ISSUED'),
(3, 'ACC-0003', 2, 'WITHDRAWN'),
(4, 'ACC-0004', 4, 'AVAILABLE');
ALTER TABLE tbl_book_copy ALTER COLUMN book_copy_id RESTART WITH 100;

INSERT INTO tbl_membership (membership_id, membership_number, fk_member, membership_type, status) VALUES
(1, 'MEM-0001', 1, 'STUDENT', 'ACTIVE'),
(2, 'MEM-0002', 2, 'STUDENT', 'ACTIVE'),
(3, 'MEM-0003', 3, 'STUDENT', 'INACTIVE'),
(4, 'MEM-0004', 4, 'STAFF', 'ACTIVE');
ALTER TABLE tbl_membership ALTER COLUMN membership_id RESTART WITH 100;

INSERT INTO tbl_reservation (reservation_id, reservation_number, fk_membership, fk_book, reserved_date, status) VALUES
(1, 'RES-0001', 2, 1, DATE '2026-08-01', 'ACTIVE'),
(2, 'RES-0002', 4, 2, DATE '2026-08-02', 'CANCELLED'),
(3, 'RES-0003', 1, 1, DATE '2026-08-03', 'FULFILLED');
ALTER TABLE tbl_reservation ALTER COLUMN reservation_id RESTART WITH 100;

INSERT INTO tbl_book_issue (book_issue_id, issue_number, fk_membership, fk_book_copy, fk_reservation, issue_date, due_date, status) VALUES
(1, 'ISS-0001', 1, 2, NULL, DATE '2026-08-10', DATE '2026-08-24', 'ACTIVE'),
(2, 'ISS-0002', 2, 1, NULL, DATE '2026-07-01', DATE '2026-07-15', 'RETURNED'),
(3, 'ISS-0003', 1, 1, 3, DATE '2026-08-04', DATE '2026-08-18', 'RETURNED'),
(4, 'ISS-0004', 4, 4, NULL, DATE '2026-08-12', DATE '2026-08-26', 'CANCELLED');
ALTER TABLE tbl_book_issue ALTER COLUMN book_issue_id RESTART WITH 100;

INSERT INTO tbl_book_return (book_return_id, return_number, fk_book_issue, return_date, status) VALUES
(1, 'RET-0001', 2, DATE '2026-07-20', 'COMPLETED'),
(2, 'RET-0002', 3, DATE '2026-08-10', 'VOID');
ALTER TABLE tbl_book_return ALTER COLUMN book_return_id RESTART WITH 100;

INSERT INTO tbl_fine (fine_id, fine_number, fk_book_return, fk_membership, amount, outstanding_amount, status) VALUES
(1, 'FINE-0001', 1, 2, 50.00, 25.00, 'OUTSTANDING'),
(2, 'FINE-0002', 2, 1, 10.00, 10.00, 'VOID');
ALTER TABLE tbl_fine ALTER COLUMN fine_id RESTART WITH 100;

INSERT INTO tbl_fine_payment (fine_payment_id, payment_number, fk_fine, amount, paid_on, status) VALUES
(1, 'PAY-0001', 1, 25.00, DATE '2026-07-21', 'POSTED');
ALTER TABLE tbl_fine_payment ALTER COLUMN fine_payment_id RESTART WITH 100;
