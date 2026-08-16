-- Additional Fine row used by the Fine Update and Void integration exercises.
-- FINE-0003 is current and has no POSTED Fine Payment.

INSERT INTO tbl_fine
(fine_id, fine_number, fk_book_return, fk_membership, amount, outstanding_amount, status)
VALUES
(3, 'FINE-0003', 3, 4, 20.00, 20.00, 'OUTSTANDING');

ALTER TABLE tbl_fine ALTER COLUMN fine_id RESTART WITH 100;
