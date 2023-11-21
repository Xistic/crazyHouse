package Service;

import Model.User;
import Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public void deleteUser(User user){
        if(userRepository.existsById(user.getId())) userRepository.delete(user);
        else throw new EntityNotFoundException("Пользователь " + user.getName() + "не найден: ");
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new EntityNotFoundException("Пользователи не найдены");
        }
        return users;
    }
    public User getUser(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Пользователь не найден с id: " + userId));
    }
}
