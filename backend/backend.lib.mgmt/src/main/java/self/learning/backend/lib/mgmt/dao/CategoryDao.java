package self.learning.backend.lib.mgmt.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import self.learning.backend.lib.mgmt.dataobject.CategoryDO;

@Repository
public class CategoryDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public CategoryDO save(CategoryDO dataObject) {
        if (dataObject.getCategoryId() == null) {
            entityManager.persist(dataObject);
            return dataObject;
        }
        return entityManager.merge(dataObject);
    }

    @Transactional(readOnly = true)
    public Optional<CategoryDO> findById(Long id) {
        return Optional.ofNullable(entityManager.find(CategoryDO.class, id));
    }

    @Transactional(readOnly = true)
    public Optional<CategoryDO> findCurrentById(Long id) {
        return entityManager.createQuery(
                "select d from CategoryDO d where d.categoryId = :id and d.active = true", CategoryDO.class)
                .setParameter("id", id)
                .getResultStream()
                .findFirst();
    }

    @Transactional(readOnly = true)
    public List<CategoryDO> findAll() {
        return entityManager.createQuery(
                "select d from CategoryDO d order by d.categoryId", CategoryDO.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public List<CategoryDO> findAllCurrent() {
        return entityManager.createQuery(
                "select d from CategoryDO d where d.active = true order by d.categoryId", CategoryDO.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Optional<CategoryDO> findByNormalizedBusinessKey(String businessKey) {
        return entityManager.createQuery(
                "select d from CategoryDO d where upper(trim(d.categoryCode)) = upper(trim(:businessKey))", CategoryDO.class)
                .setParameter("businessKey", businessKey)
                .getResultStream()
                .findFirst();
    }

    @Transactional(readOnly = true)
    public List<CategoryDO> search(String text) {
        String pattern = "%" + text + "%";
        return entityManager.createQuery(
                "select d from CategoryDO d where (d.categoryCode like :pattern or d.categoryName like :pattern) order by d.categoryId", CategoryDO.class)
                .setParameter("pattern", pattern)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public long countDependencies(Long id) {
        return entityManager.createQuery(
                "select count(b) from BookDO b where b.categoryId = :id and b.active = true", Long.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
