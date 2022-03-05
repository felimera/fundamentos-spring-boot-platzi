package com.fundamentosplatzi.sprintboot.fundamentos.controller;

import com.fundamentosplatzi.sprintboot.fundamentos.caseuse.GetUser;
import com.fundamentosplatzi.sprintboot.fundamentos.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    //crear,get,delete,update
    private GetUser getUser;

    public UserRestController(GetUser getUser) {
        this.getUser = getUser;
    }

    @GetMapping(path = "/")
    List<User> get() {
        return getUser.getAll();
    }
}
