package self.learning.backend.lib.mgmt.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import self.learning.backend.lib.mgmt.dataobject.MemberDO;

@Repository
public class MemberDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public MemberDO save(MemberDO dataObject) {
        if (dataObject.getMemberId() == null) {
            entityManager.persist(dataObject);
            return dataObject;
        }
        return entityManager.merge(dataObject);
    }

    @Transactional(readOnly = true)
    public Optional<MemberDO> findById(Long id) {
        return Optional.ofNullable(entityManager.find(MemberDO.class, id));
    }

    @Transactional(readOnly = true)
    public Optional<MemberDO> findCurrentById(Long id) {
        return entityManager.createQuery(
                "select d from MemberDO d where d.memberId = :id and d.active = true", MemberDO.class)
                .setParameter("id", id)
                .getResultStream()
                .findFirst();
    }

    @Transactional(readOnly = true)
    public List<MemberDO> findAll() {
        return entityManager.createQuery(
                "select d from MemberDO d order by d.memberId", MemberDO.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public List<MemberDO> findAllCurrent() {
        return entityManager.createQuery(
                "select d from MemberDO d where d.active = true order by d.memberId", MemberDO.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Optional<MemberDO> findByNormalizedBusinessKey(String businessKey) {
        return entityManager.createQuery(
                "select d from MemberDO d where upper(trim(d.registrationNumber)) = upper(trim(:businessKey))", MemberDO.class)
                .setParameter("businessKey", businessKey)
                .getResultStream()
                .findFirst();
    }

    @Transactional(readOnly = true)
    public List<MemberDO> search(String text) {
        String pattern = "%" + text + "%";
        return entityManager.createQuery(
                "select d from MemberDO d where (d.registrationNumber like :pattern or d.fullName like :pattern) order by d.memberId", MemberDO.class)
                .setParameter("pattern", pattern)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public long countDependencies(Long id) {
        return entityManager.createQuery(
                "select count(i) from BookIssueDO i, MembershipDO ms where i.membershipId = ms.membershipId and ms.memberId = :id and i.status = 'ACTIVE'", Long.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
