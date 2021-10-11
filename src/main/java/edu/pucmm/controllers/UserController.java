package edu.pucmm.controllers;

import edu.pucmm.entities.User;
import edu.pucmm.repositories.UserRepo;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserRepo UserRepo;

    public UserController(UserRepo UserRepo) {
        this.UserRepo = UserRepo;
    }

    @GetMapping("/users")
    List<User> all() {
        return UserRepo.findAll();
    }

    @PostMapping("/new-user")
    public void newUser(User estudiante) {
        UserRepo.save(estudiante);
    }

    @PostMapping("/edit-user")
    public void editUser(User estudiante){
        UserRepo.save(estudiante);
        //return "";
    }

    @GetMapping("/show-users")
    List<User> allUsers() {
        return UserRepo.findAll();
    }
        //modelo.addAttribute("user", UserRepo.findAll());
        //return "";


    @PostMapping("/delete-user/{id}")
    public void deleteUser(@PathVariable int id){
        User user = UserRepo.getOne(id);
        UserRepo.delete(user);
        //return "";
    }


}
