package quanlythuvien.service;

import quanlythuvien.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    User findUserById(int id);
    User findUserByAccountId(int id);
    List<User> getAllUsers();
    User deleteUser(int id);
}
