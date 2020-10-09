package web.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {


    private EntityManagerFactory entityManagerFactory;

    @Autowired
    public UserDAOImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public UserDAOImpl() {
    }

    @Transactional
    @Override
    public void dropUsersTable() {
        try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE users").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
            session.beginTransaction();
            session.merge(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    @Override
    public User getUserById(long id) {
        User user = null;
        try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
            user = session.get(User.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Transactional
    @Override
    public void removeUserById(long id) {
        try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
            session.beginTransaction();
            session.remove(session.get(User.class, id));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    @Override
    public List<User> getAllUsers() throws UsernameNotFoundException {
        List<User> list = null;

        try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
            list = (List<User>) session.createQuery("From User").list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Transactional
    @Override
    public void cleanUsersTable() {
        try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
            session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE roles").executeUpdate();
            session.getTransaction().commit();
            session.beginTransaction();
            session.createSQLQuery("DELETE FROM users").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
