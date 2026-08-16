package self.learning.backend.lib.mgmt.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import self.learning.backend.lib.mgmt.dataobject.MembershipDO;

@Repository
public class MembershipDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public MembershipDO save(MembershipDO dataObject) {
        if (dataObject.getMembershipId() == null) {
            entityManager.persist(dataObject);
            return dataObject;
        }
        return entityManager.merge(dataObject);
    }

    @Transactional(readOnly = true)
    public Optional<MembershipDO> findById(Long id) {
        return Optional.ofNullable(entityManager.find(MembershipDO.class, id));
    }

    @Transactional(readOnly = true)
    public Optional<MembershipDO> findCurrentById(Long id) {
        return entityManager.createQuery(
                "select d from MembershipDO d where d.membershipId = :id and d.status = 'ACTIVE'", MembershipDO.class)
                .setParameter("id", id)
                .getResultStream()
                .findFirst();
    }

    @Transactional(readOnly = true)
    public List<MembershipDO> findAll() {
        return entityManager.createQuery(
                "select d from MembershipDO d order by d.membershipId", MembershipDO.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public List<MembershipDO> findAllCurrent() {
        return entityManager.createQuery(
                "select d from MembershipDO d where d.status = 'ACTIVE' order by d.membershipId", MembershipDO.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Optional<MembershipDO> findByNormalizedBusinessKey(String businessKey) {
        return entityManager.createQuery(
                "select d from MembershipDO d where upper(trim(d.membershipNumber)) = upper(trim(:businessKey))", MembershipDO.class)
                .setParameter("businessKey", businessKey)
                .getResultStream()
                .findFirst();
    }

    @Transactional(readOnly = true)
    public List<MembershipDO> search(String text) {
        String pattern = "%" + text + "%";
        return entityManager.createQuery(
                "select d from MembershipDO d where (d.membershipNumber like :pattern or d.membershipType like :pattern) order by d.membershipId", MembershipDO.class)
                .setParameter("pattern", pattern)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public long countDependencies(Long id) {
        return entityManager.createQuery(
                "select count(i) from BookIssueDO i where i.membershipId = :id and i.status = 'ACTIVE'", Long.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
