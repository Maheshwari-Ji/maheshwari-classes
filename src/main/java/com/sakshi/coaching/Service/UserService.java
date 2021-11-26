package com.sakshi.coaching.Service;

import com.sakshi.coaching.Model.User;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    public void saveUser(User user);

    public List<User> getAllUsers();

    public User getUserByUsername(String username);

    public String findLoggedInUsername();

    public UserDetails loadUserByUsername(String username);

    public String getLoggedInRole();

    public User getUserByUserId(int userId);

}
