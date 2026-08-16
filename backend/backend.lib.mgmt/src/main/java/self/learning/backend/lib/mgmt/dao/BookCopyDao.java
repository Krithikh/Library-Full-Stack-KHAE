package self.learning.backend.lib.mgmt.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import self.learning.backend.lib.mgmt.dataobject.BookCopyDO;

@Repository
public class BookCopyDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public BookCopyDO save(BookCopyDO dataObject) {
        if (dataObject.getBookCopyId() == null) {
            entityManager.persist(dataObject);
            return dataObject;
        }
        return entityManager.merge(dataObject);
    }

    @Transactional(readOnly = true)
    public Optional<BookCopyDO> findById(Long id) {
        return Optional.ofNullable(entityManager.find(BookCopyDO.class, id));
    }

    @Transactional(readOnly = true)
    public Optional<BookCopyDO> findCurrentById(Long id) {
        return entityManager.createQuery(
                "select d from BookCopyDO d where d.bookCopyId = :id and d.status <> 'WITHDRAWN'", BookCopyDO.class)
                .setParameter("id", id)
                .getResultStream()
                .findFirst();
    }

    @Transactional(readOnly = true)
    public List<BookCopyDO> findAll() {
        return entityManager.createQuery(
                "select d from BookCopyDO d order by d.bookCopyId", BookCopyDO.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public List<BookCopyDO> findAllCurrent() {
        return entityManager.createQuery(
                "select d from BookCopyDO d where d.status <> 'WITHDRAWN' order by d.bookCopyId", BookCopyDO.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Optional<BookCopyDO> findByNormalizedBusinessKey(String businessKey) {
        return entityManager.createQuery(
                "select d from BookCopyDO d where upper(trim(d.accessionNumber)) = upper(trim(:businessKey))", BookCopyDO.class)
                .setParameter("businessKey", businessKey)
                .getResultStream()
                .findFirst();
    }

    @Transactional(readOnly = true)
    public List<BookCopyDO> search(String text) {
        String pattern = "%" + text + "%";
        return entityManager.createQuery(
                "select d from BookCopyDO d where (d.accessionNumber like :pattern) order by d.bookCopyId", BookCopyDO.class)
                .setParameter("pattern", pattern)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public long countDependencies(Long id) {
        return entityManager.createQuery(
                "select count(i) from BookIssueDO i where i.bookCopyId = :id and i.status = 'ACTIVE'", Long.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
