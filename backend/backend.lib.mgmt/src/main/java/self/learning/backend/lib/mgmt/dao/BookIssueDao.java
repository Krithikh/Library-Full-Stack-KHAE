package self.learning.backend.lib.mgmt.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import self.learning.backend.lib.mgmt.dataobject.BookIssueDO;

@Repository
public class BookIssueDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public BookIssueDO save(BookIssueDO dataObject) {
        if (dataObject.getBookIssueId() == null) {
            entityManager.persist(dataObject);
            return dataObject;
        }
        return entityManager.merge(dataObject);
    }

    @Transactional(readOnly = true)
    public Optional<BookIssueDO> findById(Long id) {
        return Optional.ofNullable(entityManager.find(BookIssueDO.class, id));
    }

    @Transactional(readOnly = true)
    public Optional<BookIssueDO> findCurrentById(Long id) {
        return entityManager.createQuery(
                "select d from BookIssueDO d where d.bookIssueId = :id and d.status = 'ACTIVE'", BookIssueDO.class)
                .setParameter("id", id)
                .getResultStream()
                .findFirst();
    }

    @Transactional(readOnly = true)
    public List<BookIssueDO> findAll() {
        return entityManager.createQuery(
                "select d from BookIssueDO d order by d.bookIssueId", BookIssueDO.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public List<BookIssueDO> findAllCurrent() {
        return entityManager.createQuery(
                "select d from BookIssueDO d where d.status = 'ACTIVE' order by d.bookIssueId", BookIssueDO.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Optional<BookIssueDO> findByNormalizedBusinessKey(String businessKey) {
        return entityManager.createQuery(
                "select d from BookIssueDO d where upper(trim(d.issueNumber)) = upper(trim(:businessKey))", BookIssueDO.class)
                .setParameter("businessKey", businessKey)
                .getResultStream()
                .findFirst();
    }

    @Transactional(readOnly = true)
    public List<BookIssueDO> search(String text) {
        String pattern = "%" + text + "%";
        return entityManager.createQuery(
                "select d from BookIssueDO d where (d.issueNumber like :pattern) order by d.bookIssueId", BookIssueDO.class)
                .setParameter("pattern", pattern)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public long countDependencies(Long id) {
        return entityManager.createQuery(
                "select count(r) from BookReturnDO r where r.bookIssueId = :id and r.status <> 'VOID'", Long.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
