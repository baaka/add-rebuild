package com.app.rebuild.service;

import com.app.rebuild.domain.auth.Permission;
import com.app.rebuild.domain.auth.User;
import com.app.rebuild.exception.AppException;
import com.app.rebuild.model.PermissionModel;
import com.app.rebuild.model.UserModel;
import com.app.rebuild.model.helper.UserModelHelper;
import com.app.rebuild.repo.auth.UserRepository;
import com.app.rebuild.security.SecurityUtil;
import com.app.rebuild.security.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(rollbackFor = AppException.class)
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PermissionsService permissionsService;

    public UserService(UserRepository userRepository, PermissionsService permissionsService) {
        this.userRepository = userRepository;
        this.permissionsService = permissionsService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return new UserPrincipal(UserModelHelper.getModel(user), user.getPassword());
        }
        String message = "cannot find username: " + username;
        log.error(message);
        throw new UsernameNotFoundException(message);
    }

    public void changePassword(String currentPassword, String newPassword) throws AppException {
        User currentUser = getCurrentUser();
        if (!new BCryptPasswordEncoder(12).matches(currentPassword, currentUser.getPassword())) {
            throw new AppException(AppException.Type.INVALID_VALUE, "Invalid Password Provided!");
        }

        currentUser.setPassword(getEncodedPassword(newPassword));
        userRepository.saveAndFlush(currentUser);
    }

    public List<UserModel> getUsers() {
        return UserModelHelper.getModels(userRepository.findAll());
    }

    public UserModel createUser(UserModel userModel) throws AppException {
        if (userModel.getId() != 0 || userModel.getUsername() == null || userModel.getUsername().trim().isEmpty() ||
                userModel.getNewSimplePassword() == null || userModel.getNewSimplePassword().trim().isEmpty()) {
            throw new AppException(AppException.Type.INVALID_VALUE, "Invalid user fields!");
        }

        User user = UserModelHelper.getEntity(userModel);
        user.setPassword(getEncodedPassword(userModel.getNewSimplePassword()));

        return UserModelHelper.getModel(userRepository.save(user));
    }

    public User getCurrentUser() {
        return userRepository.findByUsername(SecurityUtil.getPrincipalName());
    }

    public UserModel getCurrentUserModel() {
        return UserModelHelper.getModel(getCurrentUser());
    }

    private String getEncodedPassword(String simplePassword) {
        simplePassword = simplePassword.trim();
        return new BCryptPasswordEncoder(12).encode(simplePassword);
    }

    public void updateUserPermissions(long userId, List<PermissionModel> permissionModels) {
        User user = userRepository.getById(userId);
        if (permissionModels.size() > 0) {
            List<Permission> permissions = permissionsService.loadPermissionsByIds(permissionModels.stream().map(PermissionModel::getId).collect(Collectors.toList()));
            user.setPermissions(permissions);
        } else {
            user.setPermissions(null);
        }
        userRepository.save(user);
    }

    public User findUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserModel updateUser(UserModel userModel) {
        User user = userRepository.getById(userModel.getId());
        if (userModel.getNewSimplePassword() != null && !userModel.getNewSimplePassword().trim().isEmpty()) {
            user.setPassword(getEncodedPassword(userModel.getNewSimplePassword()));
        }
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setPhone(userModel.getPhone());
        user.setEmail(userModel.getEmail());
        user.setIdentificationNumber(userModel.getIdentificationNumber());

        return userModel;
    }

    public User getById(long userId) {
        return userRepository.getById(userId);
    }
}
