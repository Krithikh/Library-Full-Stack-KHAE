package self.learning.backend.lib.mgmt.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import self.learning.backend.lib.mgmt.dataobject.BookDO;

@Repository
public class BookDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public BookDO save(BookDO dataObject) {
        if (dataObject.getBookId() == null) {
            entityManager.persist(dataObject);
            return dataObject;
        }
        return entityManager.merge(dataObject);
    }

    @Transactional(readOnly = true)
    public Optional<BookDO> findById(Long id) {
        return Optional.ofNullable(entityManager.find(BookDO.class, id));
    }

    @Transactional(readOnly = true)
    public Optional<BookDO> findCurrentById(Long id) {
        return entityManager.createQuery(
                "select d from BookDO d where d.bookId = :id and d.active = true", BookDO.class)
                .setParameter("id", id)
                .getResultStream()
                .findFirst();
    }

    @Transactional(readOnly = true)
    public List<BookDO> findAll() {
        return entityManager.createQuery(
                "select d from BookDO d order by d.bookId", BookDO.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public List<BookDO> findAllCurrent() {
        return entityManager.createQuery(
                "select d from BookDO d where d.active = true order by d.bookId", BookDO.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Optional<BookDO> findByNormalizedBusinessKey(String businessKey) {
        return entityManager.createQuery(
                "select d from BookDO d where upper(trim(d.isbn)) = upper(trim(:businessKey))", BookDO.class)
                .setParameter("businessKey", businessKey)
                .getResultStream()
                .findFirst();
    }

    @Transactional(readOnly = true)
    public List<BookDO> search(String text) {
        String pattern = "%" + text + "%";
        return entityManager.createQuery(
                "select d from BookDO d where (d.isbn like :pattern or d.title like :pattern) order by d.bookId", BookDO.class)
                .setParameter("pattern", pattern)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public long countDependencies(Long id) {
        return entityManager.createQuery(
                "select count(c) from BookCopyDO c where c.bookId = :id and c.status <> 'WITHDRAWN'", Long.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
