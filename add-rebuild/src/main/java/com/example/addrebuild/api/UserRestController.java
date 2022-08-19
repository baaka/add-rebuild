package com.example.addrebuild.api;

import com.example.addrebuild.domain.User;
import com.example.addrebuild.model.UserRequestModel;
import com.example.addrebuild.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/v1/users")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @GetMapping
    public List<User> load(){
        return userService.load();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping("/username/{username}")
    public User getByUsername(@PathVariable String username) {
        return userService.getByUsername(username);
    }


    @PostMapping
    public User add(@RequestBody UserRequestModel user)  {
        return userService.add(user);
    }

    @PutMapping("/{id}")
    public User update(@RequestBody UserRequestModel user, @PathVariable Long id) {
        return userService.update(user, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
