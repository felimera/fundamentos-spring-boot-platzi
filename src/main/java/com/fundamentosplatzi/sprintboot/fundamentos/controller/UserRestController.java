package com.fundamentosplatzi.sprintboot.fundamentos.controller;

import com.fundamentosplatzi.sprintboot.fundamentos.caseuse.CreateUser;
import com.fundamentosplatzi.sprintboot.fundamentos.caseuse.DeleteUser;
import com.fundamentosplatzi.sprintboot.fundamentos.caseuse.GetUser;
import com.fundamentosplatzi.sprintboot.fundamentos.caseuse.UpdateUser;
import com.fundamentosplatzi.sprintboot.fundamentos.entity.User;
import com.fundamentosplatzi.sprintboot.fundamentos.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
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
    private UserRepository userRepository;

    public UserRestController(GetUser getUser, CreateUser createUser, DeleteUser deleteUser, UpdateUser updateUser, UserRepository userRepository) {
        this.getUser = getUser;
        this.createUser = createUser;
        this.deleteUser = deleteUser;
        this.updateUser = updateUser;
        this.userRepository = userRepository;
    }

    @GetMapping(path = "/")
    List<User> get() {
        return getUser.getAll();
    }

    @PostMapping
    public ResponseEntity<User> newUser(@RequestBody User newUser) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createUser.save(newUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        deleteUser.remove(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        return ResponseEntity.ok().body(updateUser.update(newUser, id));
    }

    @GetMapping("/pageable")
    List<User> getUserPageable(@RequestParam int page, @RequestParam int size) {
        return userRepository.findAll(PageRequest.of(page, size)).getContent();
    }
}
