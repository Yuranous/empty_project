package ru.bellintegrator.practice.dao.organization;

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
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.view.organization.OrganizationListFilter;

/**
 * {@inheritDoc}
 */
@Repository
public class OrganizationDaoImpl implements OrganizationDao {

    private final EntityManager em;

    @Autowired
    public OrganizationDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     * @param filter
     */
    @Override
    public List<Organization> findAllByFilter(OrganizationListFilter filter) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> query = builder.createQuery(Organization.class);
        Root<Organization> organization = query.from(Organization.class);

        if (filter.getName() != null) {
            Predicate nameFilter = builder.equal(organization.get("name"), filter.getName());
            query.where(nameFilter);
        }

        if (filter.getFullName() != null) {
            Predicate orgIdFilter = builder.equal(organization.get("fullName"), filter.getFullName());
            query.where(orgIdFilter);
        }

        if (filter.getInn() != null) {
            Predicate addressFilter = builder.equal(organization.get("inn"), filter.getInn());
            query.where(addressFilter);
        }
        if (filter.getKpp() != null) {
            Predicate phoneFilter = builder.equal(organization.get("kpp"), filter.getKpp());
            query.where(phoneFilter);
        }

        if (filter.getAddress() != null) {
            Predicate phoneFilter = builder.equal(organization.get("address"), filter.getAddress());
            query.where(phoneFilter);
        }

        if (filter.getPhone() != null) {
            Predicate phoneFilter = builder.equal(organization.get("phone"), filter.getPhone());
            query.where(phoneFilter);
        }

        if (filter.getIsActive() != null) {
            Predicate isActiveFilter = builder.equal(organization.get("isActive"), filter.getIsActive());
            query.where(isActiveFilter);
        }

        return em.createQuery(query).getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Organization> findById(Long id) {
        TypedQuery<Organization> typedQuery
                = em.createQuery("SELECT u FROM Organization u WHERE u.id=:id", Organization.class);
        typedQuery.setParameter("id", id);
        try {
            return Optional.of(typedQuery.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insert(Organization organization) {
        em.persist(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Organization organization) {
        em.merge(organization);
    }
}
