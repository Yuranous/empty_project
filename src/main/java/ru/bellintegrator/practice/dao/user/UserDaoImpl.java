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
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.model.User;
import ru.bellintegrator.practice.view.user.UserListFilter;

/**
 * {@inheritDoc}
 */
@Repository
public class UserDaoImpl implements UserDao {
    private final EntityManager em;

    /**
     * {@inheritDoc}
     */
    @Autowired
    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     * @param filter
     */
    @Override
    public List<User> findAllByFilter(UserListFilter filter) {


        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> user = query.from(User.class);
        Join<User, Office> office = user.join("office");

        query.where(builder.equal(office.get("id"), filter.getOfficeId()));

        if (filter.getFirstName() != null) {
            Predicate firstNameFilter = builder.equal(user.get("firstName"), filter.getFirstName());
            query.where(firstNameFilter);
        }

        if (filter.getSecondName() != null) {
            Predicate secondNameFilter = builder.equal(user.get("secondName"), filter.getSecondName());
            query.where(secondNameFilter);
        }

        if (filter.getMiddleName() != null) {
            Predicate middleNameFilter = builder.equal(user.get("middleName"), filter.getMiddleName());
            query.where(middleNameFilter);
        }

        if (filter.getPosition() != null) {
            Predicate positionFilter = builder.equal(user.get("position"), filter.getPosition());
            query.where(positionFilter);
        }

        if (filter.getDocCode() != null) {
            Predicate documentFilter = builder.equal(user.get("docCode"), filter.getDocCode());
            query.where(documentFilter);
        }

        if (filter.getCitizenshipCode() != null) {
            Predicate citizenshipCodeFilter = builder.equal(user.get("citizenshipCode"), filter.getCitizenshipCode());
            query.where(citizenshipCodeFilter);
        }

        return em.createQuery(query).getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<User> findById(Long id) {
        TypedQuery<User> typedQuery
                = em.createQuery("SELECT u FROM User u WHERE u.id=:id", User.class);
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
    public void insert(User user) {
        em.persist(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(User user) {
        em.merge(user);
    }
}
