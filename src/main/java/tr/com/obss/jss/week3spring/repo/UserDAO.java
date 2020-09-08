package tr.com.obss.jss.week3spring.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tr.com.obss.jss.week3spring.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDAO {
    @Autowired
    private EntityManager entityManager;

    public List<User> get(int pageSize, int pageNumber) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> from = criteriaQuery.from(User.class);
        CriteriaQuery<User> select = criteriaQuery.select(from);
        select.where(criteriaBuilder.isNotNull((from.get("operationType"))));
        TypedQuery typedQuery = entityManager.createQuery(select);
        typedQuery.setFirstResult(pageNumber * pageSize);
        typedQuery.setMaxResults(pageSize);
        List resultList = typedQuery.getResultList();
        return resultList;
    }

}
