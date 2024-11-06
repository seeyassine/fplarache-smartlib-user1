package org.cn.fplarachesmartlibuser.web;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class RegistrationForm {
    private String username;
    private String password;
    private String repassword;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
}
