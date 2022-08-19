package com.app.rebuild.api;

import com.app.rebuild.AppConstants;
import com.app.rebuild.exception.AppException;
import com.app.rebuild.model.PermissionModel;
import com.app.rebuild.model.UserModel;
import com.app.rebuild.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AppConstants.APP_REST_CONTEXT_PATH + "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserModel addUser(@RequestBody UserModel userModel) throws AppException {
        return userService.createUser(userModel);
    }

    @PutMapping
    public UserModel updateUser(@RequestBody UserModel userModel) {
        return userService.updateUser(userModel);
    }

    @GetMapping
    public List<UserModel> getUsers() {
        return userService.getUsers();
    }

    @PutMapping("/permissions/{userId}")
    public void updateUserPermissions(@PathVariable("userId") long userId, @RequestBody List<PermissionModel> permissionModels) {
        userService.updateUserPermissions(userId, permissionModels);
    }

    @GetMapping("/current")
    public UserModel getCurrentUser() {
        return userService.getCurrentUserModel();
    }
}
