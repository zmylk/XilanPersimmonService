package com.xilan.bengin.service;

import com.xilan.bengin.pojo.User;

import java.util.List;

public interface UserService {

    public User findById(int id);

    public List<User> findAll();

    public void insert(User user);

    public void update(User user);

    public void delete(User user);

}
