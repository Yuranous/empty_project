package ru.bellintegrator.practice.dao.office;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.view.office.OfficeListFilter;

/**
 * {@inheritDoc}
 */
@Repository
public class OfficeDaoImpl implements OfficeDao {

    private final EntityManager em;

    @Autowired
    public OfficeDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     * @param filter
     */
    @Override
    public List<Office> finAllByFilter(OfficeListFilter filter) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Office> query = builder.createQuery(Office.class);
        Root<Office> office = query.from(Office.class);

        if (filter.getName() != null) {
            Predicate nameFilter = builder.equal(office.get("name"), filter.getName());
            query.where(nameFilter);
        }

        if (filter.getOrgId() != null) {
            Predicate orgIdFilter = builder.equal(office.get("orgId"), filter.getOrgId());
            query.where(orgIdFilter);
        }

        if (filter.getAddress() != null) {
            Predicate addressFilter = builder.equal(office.get("address"), filter.getAddress());
            query.where(addressFilter);
        }
        if (filter.getPhone() != null) {
            Predicate phoneFilter = builder.equal(office.get("phone"), filter.getPhone());
            query.where(phoneFilter);
        }

        if (filter.getIsActive() != null) {
            Predicate isActiveFilter = builder.equal(office.get("isActive"), filter.getIsActive());
            query.where(isActiveFilter);
        }

        return em.createQuery(query).getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Office> findById(Long id) {
        TypedQuery<Office> typedQuery
                = em.createQuery("SELECT u FROM Office u WHERE u.id=:id", Office.class);
        typedQuery.setParameter("id", id);
        try {
            return Optional.of(typedQuery.getSingleResult());
        } catch(NoResultException e) {
            return Optional.empty();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insert(Office office) {
        em.persist(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Office office) {
        em.merge(office);
    }
}
