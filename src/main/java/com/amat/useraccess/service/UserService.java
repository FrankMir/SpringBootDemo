package com.amat.useraccess.service;

import com.amat.useraccess.model.entity.User;
import com.amat.useraccess.model.params.UserParam;
import com.amat.useraccess.service.base.CrudService;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Optional;

public interface UserService extends CrudService<User, Integer> {

    String LOGIN_FAILURE_COUNT_KEY = "login.failure.count";

    int MAX_LOGIN_TRY = 5;

    int LOCK_MINUTES = 10;

    @NonNull
    Optional<User> getCurrentUser();

    @NonNull
    Optional<User> getByUsername(@NonNull String userName);

    @NonNull
    User getByUsernameOfNonNull(@NonNull String userName);

    @NonNull
    Optional<User> getByEmail(@NonNull String email);

    @NonNull
    User getByEmailOfNonNull(@NonNull String email);

    @NonNull
    User updatePassword(@NonNull String oldPassword, @NonNull String newPassword, @NonNull Integer userId);


    /**
     * Create an user
     * @param userParam user param must not be null
     * @return created user
     */
    @NonNull
    User createBy(@NonNull UserParam userParam);

    void mustNoExpire(@NonNull User user);

    boolean passwordMatch(@NonNull User user, @Nullable String plainPassword);

    void setPassword(@NonNull User user, @NonNull String plainPassword);
}
