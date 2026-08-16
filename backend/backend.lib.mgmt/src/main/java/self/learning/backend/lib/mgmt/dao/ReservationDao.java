package self.learning.backend.lib.mgmt.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import self.learning.backend.lib.mgmt.dataobject.ReservationDO;

@Repository
public class ReservationDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public ReservationDO save(ReservationDO dataObject) {
        if (dataObject.getReservationId() == null) {
            entityManager.persist(dataObject);
            return dataObject;
        }
        return entityManager.merge(dataObject);
    }

    @Transactional(readOnly = true)
    public Optional<ReservationDO> findById(Long id) {
        return Optional.ofNullable(entityManager.find(ReservationDO.class, id));
    }

    @Transactional(readOnly = true)
    public Optional<ReservationDO> findCurrentById(Long id) {
        return entityManager.createQuery(
                "select d from ReservationDO d where d.reservationId = :id and d.status = 'ACTIVE'", ReservationDO.class)
                .setParameter("id", id)
                .getResultStream()
                .findFirst();
    }

    @Transactional(readOnly = true)
    public List<ReservationDO> findAll() {
        return entityManager.createQuery(
                "select d from ReservationDO d order by d.reservationId", ReservationDO.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public List<ReservationDO> findAllCurrent() {
        return entityManager.createQuery(
                "select d from ReservationDO d where d.status = 'ACTIVE' order by d.reservationId", ReservationDO.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Optional<ReservationDO> findByNormalizedBusinessKey(String businessKey) {
        return entityManager.createQuery(
                "select d from ReservationDO d where upper(trim(d.reservationNumber)) = upper(trim(:businessKey))", ReservationDO.class)
                .setParameter("businessKey", businessKey)
                .getResultStream()
                .findFirst();
    }

    @Transactional(readOnly = true)
    public List<ReservationDO> search(String text) {
        String pattern = "%" + text + "%";
        return entityManager.createQuery(
                "select d from ReservationDO d where (d.reservationNumber like :pattern) order by d.reservationId", ReservationDO.class)
                .setParameter("pattern", pattern)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public long countDependencies(Long id) {
        return entityManager.createQuery(
                "select count(i) from BookIssueDO i where i.reservationId = :id and i.status <> 'CANCELLED'", Long.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
