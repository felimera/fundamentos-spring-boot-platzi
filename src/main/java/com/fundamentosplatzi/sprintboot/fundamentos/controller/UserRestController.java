package com.fundamentosplatzi.sprintboot.fundamentos.controller;

import com.fundamentosplatzi.sprintboot.fundamentos.caseuse.CreateUser;
import com.fundamentosplatzi.sprintboot.fundamentos.caseuse.DeleteUser;
import com.fundamentosplatzi.sprintboot.fundamentos.caseuse.GetUser;
import com.fundamentosplatzi.sprintboot.fundamentos.caseuse.UpdateUser;
import com.fundamentosplatzi.sprintboot.fundamentos.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    //crear,get,delete,update
    private GetUser getUser;
    private CreateUser createUser;
    private DeleteUser deleteUser;
    private UpdateUser updateUser;

    public UserRestController(GetUser getUser, CreateUser createUser, DeleteUser deleteUser, UpdateUser updateUser) {
        this.getUser = getUser;
        this.createUser = createUser;
        this.deleteUser = deleteUser;
        this.updateUser = updateUser;
    }

    @GetMapping(path = "/")
    List<User> get() {
        return getUser.getAll();
    }

    @PostMapping("/")
    public ResponseEntity<User> newUser(@RequestBody User newUser) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createUser.save(newUser));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        deleteUser.remove(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<User> replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        return ResponseEntity.ok().body(updateUser.update(newUser, id));
    }

}
