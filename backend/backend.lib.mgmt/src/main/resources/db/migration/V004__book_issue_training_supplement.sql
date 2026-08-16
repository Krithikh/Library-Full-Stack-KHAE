-- Additional Book Issue and Return records used by the library application baseline.
-- A Return may already be persisted while a related Issue status update is still pending.
-- Business operations must use the persisted relationships as well as the current status.

INSERT INTO tbl_book_issue
(book_issue_id, issue_number, fk_membership, fk_book_copy, fk_reservation, issue_date, due_date, status)
VALUES
(5, 'ISS-0005', 4, 4, NULL, DATE '2026-08-14', DATE '2026-08-28', 'ACTIVE');

INSERT INTO tbl_book_return
(book_return_id, return_number, fk_book_issue, return_date, status)
VALUES
(3, 'RET-0003', 5, DATE '2026-08-16', 'COMPLETED');

ALTER TABLE tbl_book_issue ALTER COLUMN book_issue_id RESTART WITH 100;
ALTER TABLE tbl_book_return ALTER COLUMN book_return_id RESTART WITH 100;
