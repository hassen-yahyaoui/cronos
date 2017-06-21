package com.zakshya.mock.entities;

import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class User {

    private String identifier;
    private String login;
    private String password;
    private Date lastLogin;
    private Set<Permission> permissions;
}
