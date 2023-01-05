package com.example.demo.service;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    @Autowired
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User currentUser = userRepository.findByEmail(email);
        UserDetails user = new org.springframework.security.core
                .userdetails.User(email, currentUser.getPassword(), true, true, true, true,
                AuthorityUtils.createAuthorityList(currentUser.getRole()));

        return user;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User userById(long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User not found with id :" + userId));
    }
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(long userId, User user) {
        User existingUser = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User not found with id :" + userId));

        existingUser.setEmail(user.getEmail());
        existingUser.setRole(user.getRole());

        return userRepository.save(existingUser);
    }

    public void deleteUser(long userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User not found with id :" + userId));

        userRepository.delete(existingUser);
    }
}
