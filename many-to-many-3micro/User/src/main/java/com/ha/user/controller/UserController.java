package com.ha.testing.controller;

import com.ha.testing.dto.UserWithCourse;
import com.ha.testing.dto.UserWithRole;
import com.ha.testing.dto.UserWithRoles;
import com.ha.testing.entity.User;
import com.ha.testing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


//    @GetMapping("testing")
//    public ResponseEntity<List<Course>> getString(){
//        clientSide.accessApi();
//        return ResponseEntity.ok(clientSide.accessApi());
//
//    }

//    @GetMapping("testing")
//    public ResponseEntity<String> getString(){
//        return ResponseEntity.ok(clientSide.getString());
//
//    }

//    @GetMapping("getCourse")
//    public ResponseEntity<Course> getCourse(){
//
//        var role = clientSide.getCourse();
//        System.out.println(role);
//        return ResponseEntity.ok(role);
//
//    }

//    @GetMapping("school/{id}")
//    public UserWithRole getSchool(@PathVariable Long id){
//        return userService.getSchoolById(id);
//
//    }
//
//    @GetMapping("user/{id}/roles")
//    public UserWithRoles getUserWithRoles(@PathVariable Long id){
//        return userService.getUserWithRoles(id);
//
//    }

    //-----------------------------------------------------------------------------

    //write me CRUD methods in this controller class
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("{id}")
    public User getUser(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @GetMapping("name/{name}")
    public User getUserByName(@PathVariable String name) {
        return userService.getUserByName(name);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }



    //-----------------------------------------------------------------------------

    @GetMapping("{id}/courses")
    public UserWithCourse getUserWithCourse(@PathVariable long id){
        return userService.getUserWithCourses(id);
    }

}
