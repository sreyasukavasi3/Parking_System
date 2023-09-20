package com.example.helloworld.controller;
import com.example.helloworld.Exception.UserNotFoundException;
import com.example.helloworld.Repositoy.UserRepo;
import com.example.helloworld.Util.UserValidator;
import com.example.helloworld.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
//@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepo userRepo;

    //build create User REST API
    @PostMapping("/api/users")
    public ResponseEntity<User> saveUser(@RequestBody User user) throws Exception{
        if(UserValidator.isValidUser(user))
            return new ResponseEntity<>(userRepo.save(user), HttpStatus.CREATED);
        else throw new Exception();
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<>(userRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id){
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent()){
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        else{
            throw new UserNotFoundException(id);
        }

    }
//    public ResponseEntity<User> getUser(@PathVariable long id){
//        Optional<User> user = userRepo.findById(id);
//        if(user.isPresent()){
//            return new ResponseEntity<>(user.get(), HttpStatus.OK);
//        }else{
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id,@RequestBody User usr){
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent()){
            user.get().setFirstname(usr.getFirstname());
            user.get().setLastname(usr.getLastname());
            user.get().setEmail(usr.getEmail());
            return new ResponseEntity<>(userRepo.save(user.get()), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id){
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent()){
            userRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
