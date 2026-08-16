package self.learning.backend.lib.mgmt.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import self.learning.backend.lib.mgmt.dataobject.AuthorDO;

@Repository
public class AuthorDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public AuthorDO save(AuthorDO dataObject) {
        if (dataObject.getAuthorId() == null) {
            entityManager.persist(dataObject);
            return dataObject;
        }
        return entityManager.merge(dataObject);
    }

    @Transactional(readOnly = true)
    public Optional<AuthorDO> findById(Long id) {
        return Optional.ofNullable(entityManager.find(AuthorDO.class, id));
    }

    @Transactional(readOnly = true)
    public Optional<AuthorDO> findCurrentById(Long id) {
        return entityManager.createQuery(
                "select d from AuthorDO d where d.authorId = :id and d.active = true", AuthorDO.class)
                .setParameter("id", id)
                .getResultStream()
                .findFirst();
    }

    @Transactional(readOnly = true)
    public List<AuthorDO> findAll() {
        return entityManager.createQuery(
                "select d from AuthorDO d order by d.authorId", AuthorDO.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public List<AuthorDO> findAllCurrent() {
        return entityManager.createQuery(
                "select d from AuthorDO d where d.active = true order by d.authorId", AuthorDO.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Optional<AuthorDO> findByNormalizedBusinessKey(String businessKey) {
        return entityManager.createQuery(
                "select d from AuthorDO d where upper(trim(d.authorCode)) = upper(trim(:businessKey))", AuthorDO.class)
                .setParameter("businessKey", businessKey)
                .getResultStream()
                .findFirst();
    }

    @Transactional(readOnly = true)
    public List<AuthorDO> search(String text) {
        String pattern = "%" + text + "%";
        return entityManager.createQuery(
                "select d from AuthorDO d where (d.authorCode like :pattern or d.authorName like :pattern) order by d.authorId", AuthorDO.class)
                .setParameter("pattern", pattern)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public long countDependencies(Long id) {
        return entityManager.createQuery(
                "select count(b) from BookDO b where b.authorId = :id and b.active = true", Long.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
