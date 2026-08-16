package self.learning.backend.lib.mgmt.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import self.learning.backend.lib.mgmt.dataobject.BookReturnDO;

@Repository
public class BookReturnDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public BookReturnDO save(BookReturnDO dataObject) {
        if (dataObject.getBookReturnId() == null) {
            entityManager.persist(dataObject);
            return dataObject;
        }
        return entityManager.merge(dataObject);
    }

    @Transactional(readOnly = true)
    public Optional<BookReturnDO> findById(Long id) {
        return Optional.ofNullable(entityManager.find(BookReturnDO.class, id));
    }

    @Transactional(readOnly = true)
    public Optional<BookReturnDO> findCurrentById(Long id) {
        return entityManager.createQuery(
                "select d from BookReturnDO d where d.bookReturnId = :id and d.status <> 'VOID'", BookReturnDO.class)
                .setParameter("id", id)
                .getResultStream()
                .findFirst();
    }

    @Transactional(readOnly = true)
    public List<BookReturnDO> findAll() {
        return entityManager.createQuery(
                "select d from BookReturnDO d order by d.bookReturnId", BookReturnDO.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public List<BookReturnDO> findAllCurrent() {
        return entityManager.createQuery(
                "select d from BookReturnDO d where d.status <> 'VOID' order by d.bookReturnId", BookReturnDO.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Optional<BookReturnDO> findByNormalizedBusinessKey(String businessKey) {
        return entityManager.createQuery(
                "select d from BookReturnDO d where upper(trim(d.returnNumber)) = upper(trim(:businessKey))", BookReturnDO.class)
                .setParameter("businessKey", businessKey)
                .getResultStream()
                .findFirst();
    }

    @Transactional(readOnly = true)
    public List<BookReturnDO> search(String text) {
        String pattern = "%" + text + "%";
        return entityManager.createQuery(
                "select d from BookReturnDO d where (d.returnNumber like :pattern) order by d.bookReturnId", BookReturnDO.class)
                .setParameter("pattern", pattern)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public long countDependencies(Long id) {
        return entityManager.createQuery(
                "select count(f) from FineDO f where f.bookReturnId = :id and f.status <> 'VOID'", Long.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
