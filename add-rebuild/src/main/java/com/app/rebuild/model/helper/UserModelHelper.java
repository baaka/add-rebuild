package com.app.rebuild.model.helper;

import com.app.rebuild.domain.auth.User;
import com.app.rebuild.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class UserModelHelper {

    public static User getEntity(UserModel userModel) {
        User user = new User();
        if (userModel == null) {
            return null;
        }
        user.setId(userModel.getId());
        user.setUsername(userModel.getUsername());
        user.setIdentificationNumber(userModel.getIdentificationNumber());
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setEmail(userModel.getEmail());
        user.setPhone(userModel.getPhone());
        user.setPermissions(PermissionModelHelper.toEntities(userModel.getPermissions()));

        return user;
    }

    public static List<UserModel> getModels(List<User> users) {
        List<UserModel> result = new ArrayList<>();
        if (users != null && !users.isEmpty()) {
            for (User user : users) {
                result.add(getModel(user));
            }
        }
        return result;
    }


    public static UserModel getModel(User user) {
        UserModel userModel = new UserModel();
        if (user == null) {
            return null;
        }

        userModel.setId(user.getId());
        userModel.setUsername(user.getUsername());
        userModel.setIdentificationNumber(user.getIdentificationNumber());
        userModel.setFirstName(user.getFirstName());
        userModel.setLastName(user.getLastName());
        userModel.setEmail(user.getEmail());
        userModel.setPhone(user.getPhone());
        userModel.setPermissions(PermissionModelHelper.toModels(user.getPermissions()));

        return userModel;
    }

}
