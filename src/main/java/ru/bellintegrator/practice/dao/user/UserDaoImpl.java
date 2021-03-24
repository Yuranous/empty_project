package ru.bellintegrator.practice.dao.user;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.dao.specification.BaseQueryCriteriaConsumer;
import ru.bellintegrator.practice.dao.specification.SearchCriteria;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.model.User;

@Repository
public class UserDaoImpl implements UserDao {
    private final EntityManager em;

    @Autowired
    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<User> findAll(List<SearchCriteria> params, Long officeId) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> userOfficeCriteria = builder.createQuery(User.class);
        Root<User> userOfficeRoot = userOfficeCriteria.from(User.class);
        Join<User, Office> office = userOfficeRoot.join("office");
        userOfficeCriteria.select(userOfficeRoot);
        userOfficeCriteria.where(builder.equal(office.get("id"), officeId));

        Predicate predicate = builder.conjunction();

        BaseQueryCriteriaConsumer searchConsumer =
                new BaseQueryCriteriaConsumer(predicate, builder, userOfficeRoot);
        params.forEach(searchConsumer);
        predicate = searchConsumer.getPredicate();

        userOfficeCriteria.where(builder.and(builder.equal(office.get("id"), officeId), predicate));

        return em.createQuery(userOfficeCriteria).getResultList();
    }

    @Override
    public Optional<User> findById(Long id) {
        TypedQuery<User> typedQuery
                = em.createQuery("SELECT u FROM User u WHERE u.id=:id", User.class);
        typedQuery.setParameter("id", id);
        try {
            return Optional.of(typedQuery.getSingleResult());
        } catch(NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public void insert(User user) {
        em.persist(user);
    }


    @Override
    public void update(User user) {
        em.merge(user);
    }
}
