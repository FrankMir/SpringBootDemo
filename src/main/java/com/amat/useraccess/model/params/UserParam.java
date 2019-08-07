package com.amat.useraccess.model.params;

import com.amat.useraccess.model.dto.base.InputConverter;
import com.amat.useraccess.model.entity.User;
import com.amat.useraccess.model.support.CreateCheck;
import com.amat.useraccess.model.support.UpdateCheck;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.util.Date;

public class UserParam implements InputConverter<User> {

    @NotBlank(message = "Login name could not be null", groups = {CreateCheck.class, UpdateCheck.class})
    @Size(max = 50, message = "Login name could not be longer than {max}", groups = {CreateCheck.class, UpdateCheck.class})
    private String loginName;

    @NotBlank(message = "First name could not be null", groups = {CreateCheck.class, UpdateCheck.class})
    @Size(max = 50, message = "First name could not be longer than {max}", groups = {CreateCheck.class, UpdateCheck.class})
    private String firstName;

    @NotBlank(message = "Last name could not be null", groups = {CreateCheck.class, UpdateCheck.class})
    @Size(max = 50, message = "Last name could not be longer than {max}", groups = {CreateCheck.class, UpdateCheck.class})
    private String lastName;

    @Email(message = "电子邮件地址的格式不正确", groups = {CreateCheck.class, UpdateCheck.class})
    @NotBlank(message = "Email could not be null", groups = {CreateCheck.class, UpdateCheck.class})
    @Size(max = 255, message = "Email could not be longer than {max}", groups = {CreateCheck.class, UpdateCheck.class})
    private String email;

    @NotBlank(message = "Password could not be null", groups = {CreateCheck.class, UpdateCheck.class})
    @Size(min = 8, max = 100, message = "Password length must between {min} - {max}", groups = {CreateCheck.class, UpdateCheck.class})
    private String password;

    @Size(max = 1023, message = "description could not be longer than {max}", groups = {CreateCheck.class, UpdateCheck.class})
    private String description;

    @NotBlank(message = "User edit name could not be null", groups = {CreateCheck.class, UpdateCheck.class})
    @Size(max = 50, message = "User edit name could not be longer than {max}", groups = {CreateCheck.class, UpdateCheck.class})
    private String userEditName;

    @PastOrPresent
    @NotBlank(message = "Expire time could not be null", groups = {CreateCheck.class, UpdateCheck.class})
    private Date expireTime;

    @NotBlank(message = "Need change password could not be null", groups = {CreateCheck.class, UpdateCheck.class})
    private String needChangePassword;
}
