package self.learning.backend.lib.mgmt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import self.learning.backend.lib.mgmt.dao.AuthorDao;
import self.learning.backend.lib.mgmt.dao.BookCopyDao;
import self.learning.backend.lib.mgmt.dao.BookDao;
import self.learning.backend.lib.mgmt.dao.BookIssueDao;
import self.learning.backend.lib.mgmt.dao.BookReturnDao;
import self.learning.backend.lib.mgmt.dao.CategoryDao;
import self.learning.backend.lib.mgmt.dao.DepartmentDao;
import self.learning.backend.lib.mgmt.dao.FineDao;
import self.learning.backend.lib.mgmt.dao.MemberDao;
import self.learning.backend.lib.mgmt.dao.MembershipDao;
import self.learning.backend.lib.mgmt.dao.PublisherDao;
import self.learning.backend.lib.mgmt.dao.ReservationDao;

/**
 * Presenter-owned guard for the deterministic database scenarios referenced by
 * the T01-T60 student guides. This is intentionally a DAO/data contract test;
 * it does not implement any student service solution.
 */
@SpringBootTest
class TrainingDataContractTest {

    @Autowired private DepartmentDao departmentDao;
    @Autowired private MemberDao memberDao;
    @Autowired private AuthorDao authorDao;
    @Autowired private CategoryDao categoryDao;
    @Autowired private PublisherDao publisherDao;
    @Autowired private BookDao bookDao;
    @Autowired private BookCopyDao bookCopyDao;
    @Autowired private MembershipDao membershipDao;
    @Autowired private BookIssueDao bookIssueDao;
    @Autowired private BookReturnDao bookReturnDao;
    @Autowired private ReservationDao reservationDao;
    @Autowired private FineDao fineDao;

    @Test
    void readExercisesHavePhysicalButNonCurrentRows() {
        assertTrue(departmentDao.findById(3L).isPresent());
        assertFalse(departmentDao.findCurrentById(3L).isPresent());

        assertTrue(memberDao.findById(3L).isPresent());
        assertFalse(memberDao.findCurrentById(3L).isPresent());

        assertTrue(authorDao.findById(3L).isPresent());
        assertFalse(authorDao.findCurrentById(3L).isPresent());

        assertTrue(categoryDao.findById(3L).isPresent());
        assertFalse(categoryDao.findCurrentById(3L).isPresent());

        assertTrue(publisherDao.findById(3L).isPresent());
        assertFalse(publisherDao.findCurrentById(3L).isPresent());

        assertTrue(bookDao.findById(3L).isPresent());
        assertFalse(bookDao.findCurrentById(3L).isPresent());

        assertTrue(bookCopyDao.findById(3L).isPresent());
        assertFalse(bookCopyDao.findCurrentById(3L).isPresent());

        assertTrue(membershipDao.findById(3L).isPresent());
        assertFalse(membershipDao.findCurrentById(3L).isPresent());

        assertTrue(bookIssueDao.findById(2L).isPresent());
        assertFalse(bookIssueDao.findCurrentById(2L).isPresent());

        assertTrue(bookReturnDao.findById(2L).isPresent());
        assertFalse(bookReturnDao.findCurrentById(2L).isPresent());

        assertTrue(reservationDao.findById(2L).isPresent());
        assertFalse(reservationDao.findCurrentById(2L).isPresent());

        assertTrue(fineDao.findById(2L).isPresent());
        assertFalse(fineDao.findCurrentById(2L).isPresent());
    }

    @Test
    void logicalStateChangeExercisesHaveExpectedSafeAndBlockedRows() {
        // T04 Department: Department 5 / IT is safe; Department 1 / CSE has active Members.
        assertEquals(0L, departmentDao.countDependencies(5L));
        assertTrue(departmentDao.countDependencies(1L) > 0L);

        // T09 Member: Member 4 is safe; Member 1 is blocked by an ACTIVE Issue.
        assertEquals(0L, memberDao.countDependencies(4L));
        assertTrue(memberDao.countDependencies(1L) > 0L);

        // T14 Author: Author 4 is safe; Author 1 owns an active Book.
        assertEquals(0L, authorDao.countDependencies(4L));
        assertTrue(authorDao.countDependencies(1L) > 0L);

        // T19 Category: Category 4 is safe; Category 1 owns active Books.
        assertEquals(0L, categoryDao.countDependencies(4L));
        assertTrue(categoryDao.countDependencies(1L) > 0L);

        // T24 Publisher: supplemental Publisher 5 is safe; Publisher 1 is used.
        assertEquals(0L, publisherDao.countDependencies(5L));
        assertTrue(publisherDao.countDependencies(1L) > 0L);

        // T29 Book: Book 2 has only a withdrawn copy; Book 1 has current copies.
        assertEquals(0L, bookDao.countDependencies(2L));
        assertTrue(bookDao.countDependencies(1L) > 0L);

        // T34 Book Copy: Copy 4 is safe; Copy 2 is used by ISS-0001.
        assertEquals(0L, bookCopyDao.countDependencies(4L));
        assertTrue(bookCopyDao.countDependencies(2L) > 0L);

        // T39 Membership: Membership 2 is safe; Membership 1 has active Issues.
        assertEquals(0L, membershipDao.countDependencies(2L));
        assertTrue(membershipDao.countDependencies(1L) > 0L);

        // T44 Book Issue: Issue 1 has no Return; Issue 5 has RET-0003.
        assertEquals(0L, bookIssueDao.countDependencies(1L));
        assertTrue(bookIssueDao.countDependencies(5L) > 0L);

        // T49 Book Return: RET-0004 is safe; RET-0001 has FINE-0001.
        assertEquals(0L, bookReturnDao.countDependencies(4L));
        assertTrue(bookReturnDao.countDependencies(1L) > 0L);

        // T54 Reservation: RES-0001 is safe; RES-0004 has ISS-0006.
        assertEquals(0L, reservationDao.countDependencies(1L));
        assertTrue(reservationDao.countDependencies(4L) > 0L);

        // T59 Fine: FINE-0003 has no payment; FINE-0001 has PAY-0001 POSTED.
        assertEquals(0L, fineDao.countDependencies(3L));
        assertTrue(fineDao.countDependencies(1L) > 0L);
    }

    @Test
    void businessKeysUsedByDuplicateExercisesArePersisted() {
        assertTrue(departmentDao.findByNormalizedBusinessKey("CSE").isPresent());
        assertTrue(memberDao.findByNormalizedBusinessKey("REG-CSE-001").isPresent());
        assertTrue(authorDao.findByNormalizedBusinessKey("AUT-001").isPresent());
        assertTrue(categoryDao.findByNormalizedBusinessKey("PROGRAMMING").isPresent());
        assertTrue(publisherDao.findByNormalizedBusinessKey("PUB-PRENTICE").isPresent());
        assertTrue(bookDao.findByNormalizedBusinessKey("9780132350884").isPresent());
        assertTrue(bookCopyDao.findByNormalizedBusinessKey("ACC-0001").isPresent());
        assertTrue(membershipDao.findByNormalizedBusinessKey("MEM-0001").isPresent());
        assertTrue(bookIssueDao.findByNormalizedBusinessKey("ISS-0001").isPresent());
        assertTrue(bookReturnDao.findByNormalizedBusinessKey("RET-0001").isPresent());
        assertTrue(reservationDao.findByNormalizedBusinessKey("RES-0001").isPresent());
        assertTrue(fineDao.findByNormalizedBusinessKey("FINE-0001").isPresent());
    }
}
