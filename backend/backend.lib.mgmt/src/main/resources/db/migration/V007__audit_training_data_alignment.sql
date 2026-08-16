-- V007: Final T01-T60 audit alignment.
--
-- The earlier supplements were added module-by-module.  Once all twelve
-- modules coexist, some later rows can accidentally consume a row that an
-- earlier student guide uses as its successful logical-state-change case.
-- This migration keeps the already-published guide IDs deterministic while
-- preserving every dependency-block scenario.

-- T14 Deactivate Author:
-- Author 4 (AUT-004) is the documented active, unused success row.
-- Book 4 does not need a unique Author for any Book exercise, so move it to
-- the already-used Author 2 and restore Author 4 as genuinely unused.
UPDATE tbl_book
SET fk_author = 2
WHERE book_id = 4
  AND fk_author = 4;

-- T09 Deactivate Member:
-- Member 4 is the documented active success row.  The later T44/T54
-- dependency supplements used Membership 4 only as convenient data.  Their
-- exercises depend on Issue/Return and Reservation/Issue relationships, not
-- on Member 4.  Repoint those supplemental relationships to Membership 1,
-- which is already the intentionally blocked Member/Membership case.
UPDATE tbl_book_issue
SET fk_membership = 1
WHERE book_issue_id = 5;

UPDATE tbl_reservation
SET fk_membership = 1
WHERE reservation_id = 4;

UPDATE tbl_book_issue
SET fk_membership = 1
WHERE book_issue_id = 6;

-- T34 Withdraw Book Copy:
-- Copy 4 (ACC-0004) is the documented AVAILABLE success row.  Give the T44
-- supplemental active Issue its own physical copy instead of consuming Copy 4.
INSERT INTO tbl_book_copy
(book_copy_id, accession_number, fk_book, status)
VALUES
(5, 'ACC-0005', 4, 'ISSUED');

UPDATE tbl_book_issue
SET fk_book_copy = 5
WHERE book_issue_id = 5;

-- T49 Void Book Return:
-- RET-0003 is now intentionally used by the Fine module (FINE-0003), so add
-- an independent completed Return with no Fine.  A historical RETURNED Issue
-- and its own physical copy keep this row internally consistent without
-- affecting any active dependency checks used by earlier tracks.
INSERT INTO tbl_book_copy
(book_copy_id, accession_number, fk_book, status)
VALUES
(6, 'ACC-0006', 1, 'AVAILABLE');

INSERT INTO tbl_book_issue
(book_issue_id, issue_number, fk_membership, fk_book_copy, fk_reservation,
 issue_date, due_date, status)
VALUES
(7, 'ISS-0007', 2, 6, NULL,
 DATE '2026-06-01', DATE '2026-06-15', 'RETURNED');

INSERT INTO tbl_book_return
(book_return_id, return_number, fk_book_issue, return_date, status)
VALUES
(4, 'RET-0004', 7, DATE '2026-06-10', 'COMPLETED');

ALTER TABLE tbl_book_copy ALTER COLUMN book_copy_id RESTART WITH 100;
ALTER TABLE tbl_book_issue ALTER COLUMN book_issue_id RESTART WITH 100;
ALTER TABLE tbl_book_return ALTER COLUMN book_return_id RESTART WITH 100;
