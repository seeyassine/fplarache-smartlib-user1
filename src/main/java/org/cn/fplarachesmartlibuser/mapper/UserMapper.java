package org.cn.fplarachesmartlibuser.mapper;

import lombok.AllArgsConstructor;
import org.cn.fplarachesmartlibuser.dto.UserDTO;
import org.cn.fplarachesmartlibuser.entity.AppUser;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserMapper {
    private RoleMapper roleMapper;
    public UserDTO fromUser(AppUser user){
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        userDTO.setRoles(user.getRoles().stream().map(role -> roleMapper.fromRole(role)).collect(Collectors.toList()));
        return userDTO;
    }

    public AppUser fromUserDTO(UserDTO userDTO){
        AppUser user = new AppUser();
        BeanUtils.copyProperties(userDTO, user);
        if(userDTO.getRoles() != null)
            user.setRoles(userDTO.getRoles().stream().map(role -> roleMapper.fromRoleDTO(role)).collect(Collectors.toList()));
        return user;
    }
}
