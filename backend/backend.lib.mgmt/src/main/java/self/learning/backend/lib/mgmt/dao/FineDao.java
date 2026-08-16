package self.learning.backend.lib.mgmt.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import self.learning.backend.lib.mgmt.dataobject.FineDO;

@Repository
public class FineDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public FineDO save(FineDO dataObject) {
        if (dataObject.getFineId() == null) {
            entityManager.persist(dataObject);
            return dataObject;
        }
        return entityManager.merge(dataObject);
    }

    @Transactional(readOnly = true)
    public Optional<FineDO> findById(Long id) {
        return Optional.ofNullable(entityManager.find(FineDO.class, id));
    }

    @Transactional(readOnly = true)
    public Optional<FineDO> findCurrentById(Long id) {
        return entityManager.createQuery(
                "select d from FineDO d where d.fineId = :id and d.status <> 'VOID'", FineDO.class)
                .setParameter("id", id)
                .getResultStream()
                .findFirst();
    }

    @Transactional(readOnly = true)
    public List<FineDO> findAll() {
        return entityManager.createQuery(
                "select d from FineDO d order by d.fineId", FineDO.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public List<FineDO> findAllCurrent() {
        return entityManager.createQuery(
                "select d from FineDO d where d.status <> 'VOID' order by d.fineId", FineDO.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Optional<FineDO> findByNormalizedBusinessKey(String businessKey) {
        return entityManager.createQuery(
                "select d from FineDO d where upper(trim(d.fineNumber)) = upper(trim(:businessKey))", FineDO.class)
                .setParameter("businessKey", businessKey)
                .getResultStream()
                .findFirst();
    }

    @Transactional(readOnly = true)
    public List<FineDO> search(String text) {
        String pattern = "%" + text + "%";
        return entityManager.createQuery(
                "select d from FineDO d where (d.fineNumber like :pattern) order by d.fineId", FineDO.class)
                .setParameter("pattern", pattern)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public long countDependencies(Long id) {
        return entityManager.createQuery(
                "select count(p) from FinePaymentDO p where p.fineId = :id and p.status = 'POSTED'", Long.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
