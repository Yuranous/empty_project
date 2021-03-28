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
import ru.bellintegrator.practice.dao.specification.BaseQueryCriteriaConsumer;
import ru.bellintegrator.practice.dao.specification.SearchCriteria;
import ru.bellintegrator.practice.model.Organization;

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
     */
    @Override
    public List<Organization> findAll(List<SearchCriteria> params) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> query = builder.createQuery(Organization.class);
        Root r = query.from(Organization.class);

        Predicate predicate = builder.conjunction();

        BaseQueryCriteriaConsumer searchConsumer =
                new BaseQueryCriteriaConsumer(predicate, builder, r);
        params.forEach(searchConsumer);
        predicate = searchConsumer.getPredicate();
        query.where(predicate);

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
