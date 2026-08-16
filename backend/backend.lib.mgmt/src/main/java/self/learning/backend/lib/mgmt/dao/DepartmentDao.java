package self.learning.backend.lib.mgmt.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import self.learning.backend.lib.mgmt.dataobject.DepartmentDO;

@Repository
public class DepartmentDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public DepartmentDO save(DepartmentDO dataObject) {
        if (dataObject.getDepartmentId() == null) {
            entityManager.persist(dataObject);
            return dataObject;
        }
        return entityManager.merge(dataObject);
    }

    @Transactional(readOnly = true)
    public Optional<DepartmentDO> findById(Long id) {
        return Optional.ofNullable(entityManager.find(DepartmentDO.class, id));
    }

    @Transactional(readOnly = true)
    public Optional<DepartmentDO> findCurrentById(Long id) {
        return entityManager.createQuery(
                "select d from DepartmentDO d where d.departmentId = :id and d.active = true", DepartmentDO.class)
                .setParameter("id", id)
                .getResultStream()
                .findFirst();
    }

    @Transactional(readOnly = true)
    public List<DepartmentDO> findAll() {
        return entityManager.createQuery(
                "select d from DepartmentDO d order by d.departmentId", DepartmentDO.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public List<DepartmentDO> findAllCurrent() {
        return entityManager.createQuery(
                "select d from DepartmentDO d where d.active = true order by d.departmentId", DepartmentDO.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Optional<DepartmentDO> findByNormalizedBusinessKey(String businessKey) {
        return entityManager.createQuery(
                "select d from DepartmentDO d where upper(trim(d.departmentCode)) = upper(trim(:businessKey))", DepartmentDO.class)
                .setParameter("businessKey", businessKey)
                .getResultStream()
                .findFirst();
    }

    @Transactional(readOnly = true)
    public List<DepartmentDO> search(String text) {
        String pattern = "%" + text + "%";
        return entityManager.createQuery(
                "select d from DepartmentDO d where (d.departmentCode like :pattern or d.departmentName like :pattern) order by d.departmentId", DepartmentDO.class)
                .setParameter("pattern", pattern)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public long countDependencies(Long id) {
        return entityManager.createQuery(
                "select count(m) from MemberDO m where m.departmentId = :id and m.active = true", Long.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
