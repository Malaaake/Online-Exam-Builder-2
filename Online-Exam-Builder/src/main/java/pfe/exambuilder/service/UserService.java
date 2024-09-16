package pfe.exambuilder.service;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pfe.exambuilder.dto.UserDto;
import pfe.exambuilder.repository.UserRepository;

import org.springframework.security.config.http.SessionCreationPolicy;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Service
public class UserService {

	private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder1;

    public BCryptPasswordEncoder getPasswordEncoder() {
        return passwordEncoder1;
    }


    @Autowired
    private PasswordEncoder passwordEncoder;
    
    
    public List<UserDto> getAllUsers() {
		return null;
	}
    public UserDto createUser(UserDto userDto) {
		return null;
	}
    public UserDto getUserById(Long id) {
		return null;
	}
    public UserDto updateUser(Long id, UserDto userDto) {
		return null;
	}
    public void deleteUser(Long id) {
	}

 

    

    public User register(User user) {
        user.setPassword(passwordEncoder1.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    
    
    public void save(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        userRepository.save(user);
    }

    // This method is likely redundant and can be removed if it's identical to the above method
    public void save(pfe.exambuilder.model.User user) {
        save((User) user);
    }

    public boolean existsByUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return userRepository.existsByEmail(email);
    }
}
