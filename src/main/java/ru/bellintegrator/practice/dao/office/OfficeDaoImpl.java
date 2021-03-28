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
import ru.bellintegrator.practice.dao.specification.BaseQueryCriteriaConsumer;
import ru.bellintegrator.practice.dao.specification.SearchCriteria;
import ru.bellintegrator.practice.model.Office;

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
     */
    @Override
    public List<Office> findAll(List<SearchCriteria> params) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Office> query = builder.createQuery(Office.class);
        Root r = query.from(Office.class);

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
