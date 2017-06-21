package com.zakshya.mock.services;

import com.zakshya.mock.entities.Permission;
import com.zakshya.mock.entities.User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    public Boolean create(User user) {
        return true;
    }

    public Boolean update(User user) {
        return  true;
    }

    public Boolean delete(String identifier) {
        return true;
    }

    public void addPermission(Permission permission, String userIdentifier) {

    }

    public void addPermission(List<Permission> permissions, String userIdentifier) {

    }


    private Set<User> findAll(){
        return  new HashSet<>();
    }

    private void check() {

    }
}
