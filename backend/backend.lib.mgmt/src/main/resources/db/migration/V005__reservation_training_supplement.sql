-- V005: Reservation training supplement for persisted dependency testing.
-- RES-0004 is still ACTIVE while a non-CANCELLED Book Issue already refers to it.
-- This represents a multi-step workflow in which the relationship is persisted before every lifecycle field is finalized.

INSERT INTO tbl_reservation
(reservation_id, reservation_number, fk_membership, fk_book, reserved_date, status)
VALUES
(4, 'RES-0004', 4, 1, DATE '2026-08-15', 'ACTIVE');

INSERT INTO tbl_book_issue
(book_issue_id, issue_number, fk_membership, fk_book_copy, fk_reservation, issue_date, due_date, status)
VALUES
(6, 'ISS-0006', 4, 1, 4, DATE '2026-08-16', DATE '2026-08-30', 'ACTIVE');

ALTER TABLE tbl_reservation ALTER COLUMN reservation_id RESTART WITH 100;
ALTER TABLE tbl_book_issue ALTER COLUMN book_issue_id RESTART WITH 100;
