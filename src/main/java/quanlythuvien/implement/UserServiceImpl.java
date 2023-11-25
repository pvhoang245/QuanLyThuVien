package quanlythuvien.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import quanlythuvien.model.User;
import quanlythuvien.repository.UserRepository;
import quanlythuvien.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserById(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User findUserByAccountId(int id) {
        return userRepository.findByAccountId(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User deleteNgdung(int id) {
        User u = findUserById(id);
        userRepository.delete(u);
        return null;
    }


}
