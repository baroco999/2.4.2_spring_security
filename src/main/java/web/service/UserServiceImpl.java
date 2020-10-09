package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.DAO.UserDAO;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional
    @Override
    public void dropUsersTable() {
        userDAO.dropUsersTable();
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    @Transactional
    @Override
    public void updateUser(User user){
        userDAO.updateUser(user);
    }

    @Transactional
    @Override
    public User getUserById(long id){
        return userDAO.getUserById(id);
    }

    @Transactional
    @Override
    public User getUserByUsername(String s) throws UsernameNotFoundException {
        List<User> list = userDAO.getAllUsers();
        list.removeIf(user -> !(user.getUsername().equals(s)));
        return list.get(0);
    }

    @Transactional
    @Override
    public void removeUserById(long id) {
        userDAO.removeUserById(id);
    }

    @Transactional
    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Transactional
    @Override
    public void cleanUsersTable() {
        userDAO.cleanUsersTable();
    }

}
