package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    private EntityManager entityManager;

    public UserDaoImpl(@Autowired EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        if (entityManager.find(User.class, user.getId()) != null)
            entityManager.merge(user);
    }

    @Override
    public User getUser(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        Query q = entityManager.createQuery("delete from User where id = :id");
        q.setParameter("id", id);
        q.executeUpdate();
    }

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("from User").getResultList();
    }
}