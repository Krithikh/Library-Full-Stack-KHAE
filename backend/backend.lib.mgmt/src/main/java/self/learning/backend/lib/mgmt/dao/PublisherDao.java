package self.learning.backend.lib.mgmt.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import self.learning.backend.lib.mgmt.dataobject.PublisherDO;

@Repository
public class PublisherDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public PublisherDO save(PublisherDO dataObject) {
        if (dataObject.getPublisherId() == null) {
            entityManager.persist(dataObject);
            return dataObject;
        }
        return entityManager.merge(dataObject);
    }

    @Transactional(readOnly = true)
    public Optional<PublisherDO> findById(Long id) {
        return Optional.ofNullable(entityManager.find(PublisherDO.class, id));
    }

    @Transactional(readOnly = true)
    public Optional<PublisherDO> findCurrentById(Long id) {
        return entityManager.createQuery(
                "select d from PublisherDO d where d.publisherId = :id and d.active = true", PublisherDO.class)
                .setParameter("id", id)
                .getResultStream()
                .findFirst();
    }

    @Transactional(readOnly = true)
    public List<PublisherDO> findAll() {
        return entityManager.createQuery(
                "select d from PublisherDO d order by d.publisherId", PublisherDO.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public List<PublisherDO> findAllCurrent() {
        return entityManager.createQuery(
                "select d from PublisherDO d where d.active = true order by d.publisherId", PublisherDO.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Optional<PublisherDO> findByNormalizedBusinessKey(String businessKey) {
        return entityManager.createQuery(
                "select d from PublisherDO d where upper(trim(d.publisherCode)) = upper(trim(:businessKey))", PublisherDO.class)
                .setParameter("businessKey", businessKey)
                .getResultStream()
                .findFirst();
    }

    @Transactional(readOnly = true)
    public List<PublisherDO> search(String text) {
        String pattern = "%" + text + "%";
        return entityManager.createQuery(
                "select d from PublisherDO d where (d.publisherCode like :pattern or d.publisherName like :pattern) order by d.publisherId", PublisherDO.class)
                .setParameter("pattern", pattern)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public long countDependencies(Long id) {
        return entityManager.createQuery(
                "select count(b) from BookDO b where b.publisherId = :id and b.active = true", Long.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
