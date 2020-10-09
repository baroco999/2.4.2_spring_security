package web.service;

import web.model.User;
import java.util.List;

public interface UserService {

    void dropUsersTable();

    void saveUser(User user);

    void updateUser(User user);

    User getUserById(long id);

    User getUserByUsername(String s);

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();
}
