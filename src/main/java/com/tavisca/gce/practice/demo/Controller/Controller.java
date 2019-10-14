package com.tavisca.gce.practice.demo.Controller;

import com.tavisca.gce.practice.demo.dao.UserRepo;
import com.tavisca.gce.practice.demo.Model.User;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class Controller {

    @Autowired
    UserRepo repo;

    @GetMapping("/users")
    public ResponseEntity getAllUsers(@RequestParam("name") String name){
        Optional<User> user=repo.findByName(name);
        if(user.get().getRole().equals("admin"))
            return ResponseEntity.status(HttpStatus.OK).body(repo.findAll());
        return  ResponseEntity.status(HttpStatus.FORBIDDEN).body("You're not an admin");
    }
    @GetMapping("/users/{id}")
    public ResponseEntity getOneUser(@RequestParam("name")String name,@PathVariable("id") Integer id){

        Optional<User> user=repo.findByName(name);
        if(user.get().getRole().equals("admin"))
            return ResponseEntity.status(HttpStatus.OK).body(repo.findById(id));
        return  ResponseEntity.status(HttpStatus.FORBIDDEN).body("You're not an admin");
    }

    @PostMapping("/users")
    public ResponseEntity addUser(@RequestParam("name")String name,@RequestBody User userdata){

        Optional<User> user=repo.findByName(name);
        if(user.get().getRole().equals("admin"))
            return ResponseEntity.status(HttpStatus.OK).body(repo.save(userdata));
        return  ResponseEntity.status(HttpStatus.FORBIDDEN).body("You're not an admin");
    }

    @GetMapping("/users/login")
    public ResponseEntity login(@RequestParam("name")String name,@RequestParam("password")String password) throws NotFoundException {
        Optional<User> expectedUser=repo.findByName(name);
        if( expectedUser.get().getPassword().equals(password)){
            return ResponseEntity.status(HttpStatus.OK).body(expectedUser);
        }
        else
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Wrong Password");
    }
}
