package io.github.willianlds.apiWithTest.services;


import io.github.willianlds.apiWithTest.domain.User;
import io.github.willianlds.apiWithTest.domain.dto.UserDTO;

import java.util.List;

public interface UserService {

    User findById(Integer id);
    List<User> findAll();
    User create(UserDTO obj);
}
