package org.cn.fplarachesmartlibuser.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private Collection<RoleDTO> roles = new ArrayList<>();
}
