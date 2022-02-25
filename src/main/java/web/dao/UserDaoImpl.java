package web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;

    public UserDaoImpl(@Autowired SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addUser(User user) {
        currentSession().persist(user);
    }

    @Override
    public void updateUser(User user) {
        if (currentSession().find(User.class, user.getId()) != null)
            currentSession().merge(user);
    }

    @Override
    public User getUser(int id) {
        return currentSession().find(User.class, id);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        Query q = currentSession().createQuery("delete from User where id = :id");
        q.setParameter("id", id);
        q.executeUpdate();
    }

    @Override
    public List<User> getUsers() {
        return currentSession().createQuery("from User").getResultList();
    }
}