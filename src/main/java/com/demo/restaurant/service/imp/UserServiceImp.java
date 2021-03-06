package com.demo.restaurant.service.imp;

import com.demo.restaurant.exception.NonUniqueResultException;
import com.demo.restaurant.exception.ResourceNotFoundException;
import com.demo.restaurant.exception.UnauthorizedAccessException;
import com.demo.restaurant.model.User;
import com.demo.restaurant.repo.UserRepository;
import com.demo.restaurant.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.demo.restaurant.enums.UserErrorMessage.*;
import static java.lang.String.format;

@Service
public class UserServiceImp implements UserService {

    //Object instance to persist in the database
    private final UserRepository userRepository;

    //Create instance of the object to encode the password
    private final BCryptPasswordEncoder passwordEncoder;

    //Class constructor
    public UserServiceImp(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(User user) {
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        if (optionalUser.isPresent()) throw new NonUniqueResultException(format(USER_DUPLICATE.getMessage(), user.getEmail()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User validateActiveUser(String email, String password) {
        //Create default admin user
        List<User> userList = userRepository.findAll();

        if (userList.isEmpty()) {
            User user = new User();
            user.setFirstName("ADMIN");
            user.setLastName("ADMIN");
            user.setEmail("admin@gmail.com");
            user.setPassword("1234");
            user.setIsAdmin(true);
            user.setDeprecated(false);
            this.save(user);
        }

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent() && passwordEncoder.matches(password, optionalUser.get().getPassword()) &&
                !optionalUser.get().getDeprecated()) return optionalUser.get();

        else throw new UnauthorizedAccessException(USER_AUTHENTICATION_ERROR.getMessage());
    }

    @Override
    public User updateUser(User updateUser) {
        Optional<User> optionalUser = userRepository.findById(updateUser.getId());
        if (!optionalUser.isPresent()) throw new ResourceNotFoundException(format(USER_NOT_FOUND.getMessage(), updateUser.getId()));

        User user = optionalUser.get();
        user.setFirstName(updateUser.getFirstName());
        user.setLastName(updateUser.getLastName());
        user.setIsAdmin(updateUser.getIsAdmin());
        user.setDeprecated(updateUser.getDeprecated());
        return userRepository.save(user);
    }

    @Override
    public User findById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) throw new ResourceNotFoundException(format(USER_NOT_FOUND.getMessage(), userId));
        return optionalUser.get();
    }
}
