package com.piter.cloud.service;

import com.piter.cloud.domain.User;

import java.util.List;

/**
 * Created by piter on 2019/8/29.
 */
public interface UserService {
    void create(User user);

    User getUser(Long id);

    void update(User user);

    void delete(Long id);

    User getByUsername(String username);

    List<User> getUserByIds(List<Long> ids);
}
